#!/usr/bin/env python3
"""
Pouch Neuro Aura — PyTorch trainer -> ONNX exporter.

Usage:
    python train_neuro.py <data_file.ndata> <output_model.onnx> [epochs] [batch] [lr]

Data format (.ndata, one sample per line):
    f1,f2,...,fN | o1,o2,...,oM
    (separator: pipe '|'; numbers comma-separated; UTF-8 BOM tolerated)

Architecture: 2-layer GRU over the (7-tick × 6-feature) window + small MLP head.
The ONNX input stays flat (batch, in_dim) — reshape happens inside the model — so
the Java inference path needs no changes.

    input: (B, 42)
        -> reshape to (B, 7, 6)         # 7 ticks, 6 features per tick
        -> GRU(hidden=96, layers=2, dropout=0.15, batch_first=True)
        -> take last timestep:  (B, 96)
        -> LayerNorm(96)
        -> Linear(96 -> 48) + GELU + Dropout(0.1)
        -> Linear(48 -> out)
    output: (B, 2)   # next-tick deltaYaw, deltaPitch
"""

from __future__ import annotations

import os
import subprocess
import sys
from pathlib import Path
from typing import List, Tuple

REQUIRED = ("torch", "onnx")


def _pip_install(packages):
    """Install packages into the current interpreter via pip. Streams output."""
    print(f"[train_neuro] installing dependencies: {', '.join(packages)} (first run, may take a while)", flush=True)
    try:
        subprocess.check_call(
            [sys.executable, "-m", "ensurepip", "--upgrade"],
            stdout=subprocess.DEVNULL,
            stderr=subprocess.DEVNULL,
        )
    except Exception:
        pass
    try:
        subprocess.check_call(
            [sys.executable, "-m", "pip", "install", "--upgrade", "--disable-pip-version-check", "pip"],
            stdout=subprocess.DEVNULL,
        )
    except Exception:
        pass
    cmd = [sys.executable, "-m", "pip", "install", "--disable-pip-version-check", *packages]
    subprocess.check_call(cmd)


def _ensure_deps():
    missing = []
    for pkg in REQUIRED:
        try:
            __import__(pkg)
        except ImportError:
            missing.append(pkg)
    if not missing:
        return
    try:
        _pip_install(missing)
    except subprocess.CalledProcessError as e:
        print(f"[train_neuro] ERROR: failed to install {missing}: {e}", file=sys.stderr)
        sys.exit(2)
    for pkg in missing:
        try:
            __import__(pkg)
        except ImportError as e:
            print(f"[train_neuro] ERROR: {pkg} still not importable after install: {e}", file=sys.stderr)
            sys.exit(2)


_ensure_deps()

import torch  # noqa: E402
from torch import nn  # noqa: E402
from torch.utils.data import DataLoader, TensorDataset  # noqa: E402


def _strip_bom(s: str) -> str:
    while s.startswith("﻿"):
        s = s[1:]
    return s.strip()


def load_ndata(path: Path) -> Tuple[List[List[float]], List[List[float]]]:
    inputs: List[List[float]] = []
    outputs: List[List[float]] = []
    with path.open("r", encoding="utf-8") as f:
        for raw in f:
            line = _strip_bom(raw)
            if not line:
                continue
            parts = line.split("|")
            if len(parts) != 2:
                continue
            try:
                xs = [float(_strip_bom(x)) for x in parts[0].split(",") if x.strip()]
                ys = [float(_strip_bom(y)) for y in parts[1].split(",") if y.strip()]
            except ValueError:
                continue
            if not xs or not ys:
                continue
            inputs.append(xs)
            outputs.append(ys)
    return inputs, outputs


TICKS = 7
FEATURES_PER_TICK = 6
WINDOW_SIZE = TICKS * FEATURES_PER_TICK  # 42


class NeuroGRU(nn.Module):
    """GRU over the head-history window with a small MLP head."""

    def __init__(self, in_dim: int, out_dim: int, hidden: int = 96, num_layers: int = 2,
                 dropout: float = 0.15):
        super().__init__()
        if in_dim != WINDOW_SIZE:
            raise ValueError(
                f"NeuroGRU expects in_dim={WINDOW_SIZE} ({TICKS} ticks * {FEATURES_PER_TICK} features), "
                f"got {in_dim}. Re-record data with the current Java client."
            )
        self.hidden = hidden
        self.gru = nn.GRU(
            input_size=FEATURES_PER_TICK,
            hidden_size=hidden,
            num_layers=num_layers,
            batch_first=True,
            dropout=dropout if num_layers > 1 else 0.0,
        )
        self.norm = nn.LayerNorm(hidden)
        self.head = nn.Sequential(
            nn.Linear(hidden, 48),
            nn.GELU(),
            nn.Dropout(0.10),
            nn.Linear(48, out_dim),
        )

    def forward(self, x):
        # x: (B, 42) -> (B, 7, 6)
        b = x.size(0)
        seq = x.view(b, TICKS, FEATURES_PER_TICK)
        out, _ = self.gru(seq)         # (B, 7, hidden)
        last = out[:, -1, :]           # (B, hidden) — most recent tick state
        return self.head(self.norm(last))


def train(
    data_path: Path,
    out_path: Path,
    epochs: int,
    batch_size: int,
    lr: float,
) -> None:
    inputs, outputs = load_ndata(data_path)
    if len(inputs) < 2:
        print(f"[train_neuro] ERROR: need at least 2 samples, got {len(inputs)}", file=sys.stderr)
        sys.exit(3)

    in_dim = len(inputs[0])
    out_dim = len(outputs[0])
    for i, (xs, ys) in enumerate(zip(inputs, outputs)):
        if len(xs) != in_dim or len(ys) != out_dim:
            print(
                f"[train_neuro] ERROR: inconsistent sample at index {i}: "
                f"got in={len(xs)} out={len(ys)}, expected in={in_dim} out={out_dim}",
                file=sys.stderr,
            )
            sys.exit(4)

    x = torch.tensor(inputs, dtype=torch.float32)
    y = torch.tensor(outputs, dtype=torch.float32)

    # 85/15 train/val split (random shuffle). With < ~30 samples we just use all data for both.
    n = x.size(0)
    if n >= 30:
        perm = torch.randperm(n)
        n_val = max(2, n // 7)
        val_idx = perm[:n_val]
        train_idx = perm[n_val:]
        x_train, y_train = x[train_idx], y[train_idx]
        x_val, y_val = x[val_idx], y[val_idx]
    else:
        x_train, y_train = x, y
        x_val, y_val = x, y

    actual_batch = max(2, min(batch_size, x_train.size(0)))
    loader = DataLoader(
        TensorDataset(x_train, y_train),
        batch_size=actual_batch,
        shuffle=True,
        drop_last=False,
    )

    device = "cuda" if torch.cuda.is_available() else "cpu"
    model = NeuroGRU(in_dim, out_dim).to(device)
    opt = torch.optim.AdamW(model.parameters(), lr=lr, weight_decay=1e-4)
    sched = torch.optim.lr_scheduler.CosineAnnealingLR(opt, T_max=epochs, eta_min=lr * 0.05)
    loss_fn = nn.SmoothL1Loss(beta=0.5)  # robust to outliers vs plain MSE

    n_params = sum(p.numel() for p in model.parameters() if p.requires_grad)
    print(
        f"[train_neuro] device={device} arch=GRU(96x2)+MLP params={n_params} "
        f"in={in_dim} out={out_dim} train={x_train.size(0)} val={x_val.size(0)} "
        f"epochs={epochs} batch={actual_batch} lr={lr}",
        flush=True,
    )

    x_val_d = x_val.to(device)
    y_val_d = y_val.to(device)
    best_val = float("inf")
    best_state = None
    patience = max(40, epochs // 6)
    bad_epochs = 0

    for epoch in range(1, epochs + 1):
        model.train()
        epoch_loss = 0.0
        n_batches = 0
        for xb, yb in loader:
            xb = xb.to(device, non_blocking=True)
            yb = yb.to(device, non_blocking=True)
            pred = model(xb)
            loss = loss_fn(pred, yb)
            opt.zero_grad(set_to_none=True)
            loss.backward()
            torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=1.0)
            opt.step()
            epoch_loss += loss.item()
            n_batches += 1
        sched.step()

        model.eval()
        with torch.no_grad():
            val_loss = loss_fn(model(x_val_d), y_val_d).item()

        if val_loss < best_val - 1e-6:
            best_val = val_loss
            best_state = {k: v.detach().clone() for k, v in model.state_dict().items()}
            bad_epochs = 0
        else:
            bad_epochs += 1

        if epoch == 1 or epoch % 25 == 0 or epoch == epochs:
            avg = epoch_loss / max(1, n_batches)
            lr_now = sched.get_last_lr()[0]
            print(
                f"[train_neuro] epoch {epoch}/{epochs} train={avg:.6f} val={val_loss:.6f} "
                f"best_val={best_val:.6f} lr={lr_now:.2e}",
                flush=True,
            )

        if bad_epochs >= patience:
            print(f"[train_neuro] early stop at epoch {epoch} (no val improvement for {patience} epochs)", flush=True)
            break

    if best_state is not None:
        model.load_state_dict(best_state)
        print(f"[train_neuro] restored best checkpoint (val={best_val:.6f})", flush=True)
    model.eval()
    out_path.parent.mkdir(parents=True, exist_ok=True)
    # Export on CPU — ONNX Runtime in Java runs CPU; avoids spurious device tensors in the graph.
    model_cpu = model.to("cpu").eval()
    dummy = torch.zeros(1, in_dim, dtype=torch.float32)
    torch.onnx.export(
        model_cpu,
        dummy,
        out_path.as_posix(),
        input_names=["input"],
        output_names=["output"],
        dynamic_axes={"input": {0: "batch"}, "output": {0: "batch"}},
        opset_version=17,
        do_constant_folding=True,
    )
    print(f"[train_neuro] saved ONNX -> {out_path}", flush=True)


def main(argv: List[str]) -> int:
    if len(argv) < 3:
        print(__doc__, file=sys.stderr)
        return 1

    data_path = Path(argv[1])
    out_path = Path(argv[2])
    epochs = int(argv[3]) if len(argv) > 3 else 600
    batch = int(argv[4]) if len(argv) > 4 else 64
    lr = float(argv[5]) if len(argv) > 5 else 1.5e-3

    if not data_path.is_file():
        print(f"[train_neuro] ERROR: data file not found: {data_path}", file=sys.stderr)
        return 5

    train(data_path, out_path, epochs, batch, lr)
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
