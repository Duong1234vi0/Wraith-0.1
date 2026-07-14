#!/usr/bin/env python3
"""
Pouch neuro trainer: .ndata (42 floats in | 2 floats out) -> ONNX for AttackAura.

Usage:
  python train_neuro.py <data.ndata> <output.onnx> [epochs]
"""

from __future__ import annotations

import os
import sys
from pathlib import Path

INPUT_DIM = 42
OUTPUT_DIM = 2


def log(msg: str) -> None:
    print(msg, flush=True)


def configure_stdio() -> None:
    """Avoid UnicodeEncodeError on Windows (cp1251) when PyTorch prints ONNX status."""
    for stream in (sys.stdout, sys.stderr):
        reconfigure = getattr(stream, "reconfigure", None)
        if reconfigure is not None:
            try:
                reconfigure(encoding="utf-8", errors="replace")
            except Exception:
                pass


def export_onnx(model, dummy, out_path: Path) -> None:
    import torch

    kwargs = {
        "input_names": ["input"],
        "output_names": ["output"],
        "dynamic_axes": None,
        "opset_version": 17,
    }
    try:
        torch.onnx.export(model, dummy, str(out_path), dynamo=False, **kwargs)
    except TypeError:
        torch.onnx.export(model, dummy, str(out_path), **kwargs)


def parse_ndata(path: Path) -> tuple[list[list[float]], list[list[float]]]:
    inputs: list[list[float]] = []
    outputs: list[list[float]] = []
    with path.open("r", encoding="utf-8") as fh:
        for line_no, raw in enumerate(fh, 1):
            line = raw.strip()
            if not line or line.startswith("#"):
                continue
            if "|" not in line:
                continue
            inp_s, out_s = line.split("|", 1)
            try:
                inp = [float(x) for x in inp_s.split(",") if x.strip()]
                out = [float(x) for x in out_s.split(",") if x.strip()]
            except ValueError:
                continue
            if len(inp) != INPUT_DIM or len(out) < OUTPUT_DIM:
                continue
            inputs.append(inp)
            outputs.append(out[:OUTPUT_DIM])
    return inputs, outputs


def main() -> int:
    configure_stdio()
    if len(sys.argv) < 3:
        log("Usage: train_neuro.py <data.ndata> <output.onnx> [epochs]")
        return 2

    data_path = Path(sys.argv[1])
    out_path = Path(sys.argv[2])
    epochs = int(sys.argv[3]) if len(sys.argv) > 3 else 400

    if not data_path.is_file():
        log(f"[Neuro/PyTorch] Data file not found: {data_path}")
        return 1

    try:
        import torch
        import torch.nn as nn
    except ImportError:
        log("[Neuro/PyTorch] torch not found")
        return 1

    threads = max(1, os.cpu_count() or 4)
    torch.set_num_threads(threads)
    try:
        torch.set_num_interop_threads(max(1, threads // 2))
    except RuntimeError:
        pass

    inputs, outputs = parse_ndata(data_path)
    n = len(inputs)
    if n < 8:
        log(f"[Neuro/PyTorch] Too few samples ({n}). Need at least 8.")
        return 1

    log(f"[Neuro/PyTorch] Loaded {n} samples, threads={threads}")

    x = torch.tensor(inputs, dtype=torch.float32)
    y = torch.tensor(outputs, dtype=torch.float32)

    val_count = max(1, n // 10)
    train_count = n - val_count
    gen = torch.Generator().manual_seed(42)
    perm = torch.randperm(n, generator=gen)
    val_idx = perm[:val_count]
    train_idx = perm[val_count:]

    x_train = x[train_idx]
    y_train = y[train_idx]
    x_val = x[val_idx]
    y_val = y[val_idx]

    class NeuroMlp(nn.Module):
        def __init__(self) -> None:
            super().__init__()
            self.net = nn.Sequential(
                nn.Linear(INPUT_DIM, 96),
                nn.ReLU(inplace=True),
                nn.Linear(96, 48),
                nn.ReLU(inplace=True),
                nn.Linear(48, OUTPUT_DIM),
            )

        def forward(self, inp: torch.Tensor) -> torch.Tensor:
            return self.net(inp)

    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model = NeuroMlp().to(device)
    x_train = x_train.to(device)
    y_train = y_train.to(device)
    x_val = x_val.to(device)
    y_val = y_val.to(device)

    optimizer = torch.optim.AdamW(model.parameters(), lr=2e-3, weight_decay=1e-5)
    loss_fn = nn.MSELoss()

    log(f"[Neuro/PyTorch] Training on {device} for {epochs} epochs (full-batch)")

    best_val = float("inf")
    best_state = None
    val_every = max(5, epochs // 40)
    log_every = max(1, epochs // 25)

    for epoch in range(1, epochs + 1):
        model.train()
        optimizer.zero_grad(set_to_none=True)
        pred = model(x_train)
        train_loss_t = loss_fn(pred, y_train)
        train_loss_t.backward()
        optimizer.step()
        train_loss = float(train_loss_t.item())

        run_val = epoch == 1 or epoch % val_every == 0 or epoch == epochs
        val_loss = best_val
        if run_val:
            model.eval()
            with torch.inference_mode():
                val_loss = float(loss_fn(model(x_val), y_val).item())
            if val_loss < best_val:
                best_val = val_loss
                best_state = {k: v.detach().cpu().clone() for k, v in model.state_dict().items()}

        if epoch == 1 or epoch % log_every == 0 or epoch == epochs:
            log(
                f"[Neuro/PyTorch] epoch {epoch}/{epochs} "
                f"train={train_loss:.6f} val={val_loss:.6f}"
            )
            log(f"NEURO_EPOCH|{epoch}|{epochs}|{train_loss:.6f}|{val_loss:.6f}")

    if best_state is not None:
        model.load_state_dict(best_state)

    model.eval()
    model_cpu = model.to("cpu")
    dummy = torch.zeros(1, INPUT_DIM, dtype=torch.float32)

    out_path.parent.mkdir(parents=True, exist_ok=True)
    export_onnx(model_cpu, dummy, out_path)

    log(f"[Neuro/PyTorch] Exported ONNX -> {out_path} ({out_path.stat().st_size // 1024} KB)")
    log(f"[Neuro/PyTorch] Best validation MSE: {best_val:.6f}")
    log("NEURO_DONE|ok")
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except Exception as exc:
        log(f"[Neuro/PyTorch] Error: {exc}")
        log("NEURO_DONE|fail")
        raise
