/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import lightning.product.E_2115_e;
import lightning.product.Q_1082_O;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.W_1707_M;
import lightning.product.MinecraftAccess;
import lightning.product.b_967_P;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.q_2475_j;
import lightning.product.AttackAura;
import lightning.product.u_1980_X;
import lightning.product.v_1900_v;

public class L_103_L
extends o_2341_D {
    private static final String J_1907_R = "scripts/train_neuro.py";

    public L_103_L() {
        super("neuro", "nr");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(this::J_1907_R);
        builder.then(L_103_L.n_1700_B("record").executes(context -> {
            AttackAura aura = this.R_4764_Y();
            if (aura == null || !aura.w_1484_f()) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u0412\u043a\u043b\u044e\u0447\u0438 AttackAura \u0438 \u0432\u044b\u0431\u0435\u0440\u0438 \u0440\u0435\u0436\u0438\u043c \u041d\u0435\u0439\u0440\u043e ONNX"), new Object[0]);
                return 1;
            }
            if (aura.d_2427_y()) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u0417\u0430\u043f\u0438\u0441\u044c \u0443\u0436\u0435 \u0438\u0434\u0451\u0442! \u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 .neuro stop"), new Object[0]);
                return 1;
            }
            aura.k_2293_S();
            v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0417\u0430\u043f\u0438\u0441\u044c: \u043f\u0440\u043e\u0444\u0438\u043b\u044c \u00a7fONNX \u00ab\u0410\u0432\u0442\u043e\u00bb\u00a7a \u2014 \u0431\u0435\u0437 \u0440\u043e\u0442\u0430\u0446\u0438\u0438/\u0443\u0434\u0430\u0440\u043e\u0432 \u0430\u0443\u0440\u044b. \u041e\u043a\u043d\u043e \u00b13 \u0442\u0438\u043a\u0430 \u2014 \u043f\u043e \u00a7f\u041b\u041a\u041c\u00a7a, \u043f\u043e\u0442\u043e\u043c \u00a7f.neuro stop"), new Object[0]);
            return 1;
        }));
        builder.then(L_103_L.n_1700_B("stop").executes(context -> {
            AttackAura aura = this.R_4764_Y();
            if (aura == null) {
                return 1;
            }
            if (!aura.d_2427_y()) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0417\u0430\u043f\u0438\u0441\u044c \u043d\u0435 \u0430\u043a\u0442\u0438\u0432\u043d\u0430"), new Object[0]);
                return 1;
            }
            aura.q_2307_F();
            int count = aura.Z_875_P();
            v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0417\u0430\u043f\u0438\u0441\u044c \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430. \u00a7f" + count + " \u00a7a\u0441\u044d\u043c\u043f\u043b\u043e\u0432 \u0437\u0430\u043f\u0438\u0441\u0430\u043d\u043e."), new Object[0]);
            if (count > 0) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 \u00a7f.neuro save <\u0438\u043c\u044f> \u00a77\u0447\u0442\u043e\u0431\u044b \u0441\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u0434\u0430\u043d\u043d\u044b\u0435"), new Object[0]);
            }
            return 1;
        }));
        builder.then(L_103_L.n_1700_B("save").then(L_103_L.n_1700_B("name", StringArgumentType.word()).executes(context -> {
            String name = (String)context.getArgument("name", String.class);
            AttackAura aura = this.R_4764_Y();
            if (aura == null || aura.Z_875_P() == 0) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041d\u0435\u0442 \u0437\u0430\u043f\u0438\u0441\u0430\u043d\u043d\u044b\u0445 \u0434\u0430\u043d\u043d\u044b\u0445. \u0421\u043d\u0430\u0447\u0430\u043b\u0430 .neuro record"), new Object[0]);
                return 1;
            }
            float[][] inputs = aura.c_3005_b();
            float[][] outputs = aura.H_2857_Y();
            E_2115_e.n_1700_B();
            File dataDir = E_2115_e.G_564_y();
            String key = this.G_564_y(name);
            File file = new File(dataDir, key + ".ndata");
            try {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < inputs.length; ++i) {
                    int j;
                    for (j = 0; j < inputs[i].length; ++j) {
                        if (j > 0) {
                            sb.append(',');
                        }
                        sb.append(inputs[i][j]);
                    }
                    sb.append('|');
                    for (j = 0; j < outputs[i].length; ++j) {
                        if (j > 0) {
                            sb.append(',');
                        }
                        sb.append(outputs[i][j]);
                    }
                    sb.append('\n');
                }
                Files.writeString(file.toPath(), (CharSequence)sb.toString(), StandardCharsets.UTF_8, new OpenOption[0]);
                q_2475_j.n_1700_B(file.toPath(), file.length(), "neuro dataset " + key);
                try {
                    Path human = dataDir.toPath().resolve(key + ".ndata.human.txt");
                    Q_1082_O.n_1700_B(dataDir.toPath(), key, inputs, outputs);
                    if (Files.isRegularFile(human, new LinkOption[0])) {
                        q_2475_j.n_1700_B(human, Files.size(human), "human-readable sidecar");
                    }
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0414\u0430\u043d\u043d\u044b\u0435: \u00a7f" + key + ".ndata \u00a77+ \u00a7f" + key + ".ndata.human.txt"), new Object[0]);
            }
            catch (IOException e) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u044f: " + e.getMessage()), new Object[0]);
            }
            return 1;
        })));
        builder.then(L_103_L.n_1700_B("train").then(((RequiredArgumentBuilder)L_103_L.n_1700_B("name", StringArgumentType.word()).executes(context -> this.n_1700_B((CommandContext<V_4217_p>)context, 400))).then(L_103_L.n_1700_B("epochs", IntegerArgumentType.integer((int)10, (int)50000)).executes(context -> {
            int epochs = (Integer)context.getArgument("epochs", Integer.class);
            return this.n_1700_B((CommandContext<V_4217_p>)context, epochs);
        }))));
        builder.then(L_103_L.n_1700_B("load").then(L_103_L.n_1700_B("name", StringArgumentType.word()).executes(context -> {
            String name = (String)context.getArgument("name", String.class);
            E_2115_e.n_1700_B();
            boolean ok = W_1707_M.R_4764_Y(this.G_564_y(name));
            if (ok) {
                AttackAura aura = this.R_4764_Y();
                if (aura != null) {
                    aura.C_2741_M();
                }
                v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u041c\u043e\u0434\u0435\u043b\u044c \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u0430: \u00a7f" + name), new Object[0]);
            } else {
                v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041c\u043e\u0434\u0435\u043b\u044c \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 (.onnx): " + name), new Object[0]);
            }
            return 1;
        })));
        builder.then(L_103_L.n_1700_B("list").executes(context -> {
            E_2115_e.n_1700_B();
            File dataDir = E_2115_e.G_564_y();
            File modelsDir = E_2115_e.R_4764_Y();
            StringBuilder sb = new StringBuilder("\u00a77=== \u041d\u0435\u0439\u0440\u043e (ONNX) ===\n");
            File[] dataFiles = dataDir.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(".ndata"));
            if (dataFiles != null && dataFiles.length > 0) {
                sb.append("\u00a77\u0414\u0430\u043d\u043d\u044b\u0435:\n");
                for (File f : dataFiles) {
                    String n2 = f.getName().replace(".ndata", "");
                    try {
                        long lines = Files.lines(f.toPath()).count();
                        sb.append("  \u00a7f").append(n2).append(" \u00a77(").append(lines).append(" \u0441\u044d\u043c\u043f\u043b\u043e\u0432)\n");
                    }
                    catch (IOException e) {
                        sb.append("  \u00a7f").append(n2).append("\n");
                    }
                }
            } else {
                sb.append("\u00a77\u0414\u0430\u043d\u043d\u044b\u0435: \u043f\u0443\u0441\u0442\u043e\n");
            }
            File[] modelFiles = modelsDir.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(".onnx"));
            if (modelFiles != null && modelFiles.length > 0) {
                sb.append("\u00a77\u041c\u043e\u0434\u0435\u043b\u0438:\n");
                for (File f : modelFiles) {
                    sb.append("  \u00a7f").append(f.getName().replace(".onnx", "")).append(" \u00a77(").append(f.length() / 1024L).append(" KB)\n");
                }
            } else {
                sb.append("\u00a77\u041c\u043e\u0434\u0435\u043b\u0438: \u043f\u0443\u0441\u0442\u043e\n");
            }
            v_1900_v.n_1700_B(new U_2871_b(sb.toString().trim()), new Object[0]);
            return 1;
        }));
        builder.then(L_103_L.n_1700_B("reload").executes(context -> {
            E_2115_e.n_1700_B();
            W_1707_M.R_4764_Y();
            AttackAura aura = this.R_4764_Y();
            if (aura != null) {
                aura.C_2741_M();
            }
            v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u041c\u043e\u0434\u0435\u043b\u0438 \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u044b"), new Object[0]);
            return 1;
        }));
        builder.then(L_103_L.n_1700_B("dir").executes(context -> {
            try {
                E_2115_e.n_1700_B();
                File dir = E_2115_e.G_564_y().getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Runtime.getRuntime().exec(new String[]{"explorer", dir.getAbsolutePath()});
            }
            catch (Exception exception) {
                // empty catch block
            }
            return 1;
        }));
    }

    private int n_1700_B(CommandContext<V_4217_p> context, int epochs) {
        String name = (String)context.getArgument("name", String.class);
        String key = this.G_564_y(name);
        E_2115_e.n_1700_B();
        File dataDir = E_2115_e.G_564_y();
        File modelsDir = E_2115_e.R_4764_Y();
        File dataFile = new File(dataDir, key + ".ndata");
        if (!dataFile.exists()) {
            q_2475_j.J_1907_R("No dataset: " + dataFile.getAbsolutePath() + " \u2014 run .neuro record first");
            return 1;
        }
        File trainerScript = this.J_1907_R();
        if (trainerScript == null) {
            q_2475_j.n_1700_B("Could not create train_neuro.py in C:/Pouch/neuro/scripts/", null);
            return 1;
        }
        File outFile = new File(modelsDir, key + ".onnx");
        q_2475_j.R_4764_Y(".neuro train " + name);
        q_2475_j.n_1700_B("data=" + dataFile.getAbsolutePath() + " (" + q_2475_j.n_1700_B(dataFile.length()) + ")");
        q_2475_j.n_1700_B("script=" + trainerScript.getAbsolutePath());
        q_2475_j.n_1700_B("output=" + outFile.getAbsolutePath() + " epochs=" + epochs);
        this.J_1907_R("\u00a77\u041f\u043e\u0434\u0433\u043e\u0442\u043e\u0432\u043a\u0430 Python \u0438 \u0431\u0438\u0431\u043b\u0438\u043e\u0442\u0435\u043a \u2014 \u0441\u043c. \u043a\u043e\u043d\u0441\u043e\u043b\u044c (F3)...");
        new Thread(() -> {
            String[] launcher = u_1980_X.J_1907_R();
            if (launcher == null) {
                q_2475_j.n_1700_B("Python/libraries bootstrap failed", null);
                this.J_1907_R("\u00a7c\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0441\u043a\u0430\u0447\u0430\u0442\u044c Python / PyTorch / ONNX");
                return;
            }
            this.J_1907_R("\u00a7a\u0421\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u0435 \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d\u043e. \u0421\u0442\u0430\u0440\u0442 \u043e\u0431\u0443\u0447\u0435\u043d\u0438\u044f \u00a7f" + name + " \u00a77(" + epochs + " \u044d\u043f\u043e\u0445)...");
            try {
                ArrayList<String> argv = new ArrayList<String>();
                Collections.addAll(argv, launcher);
                argv.add(trainerScript.getAbsolutePath());
                argv.add(dataFile.getAbsolutePath());
                argv.add(outFile.getAbsolutePath());
                argv.add(String.valueOf(epochs));
                q_2475_j.n_1700_B("train_neuro", argv.toArray(new String[0]));
                long trainStarted = System.currentTimeMillis();
                ProcessBuilder pb = new ProcessBuilder(argv);
                u_1980_X.n_1700_B(pb);
                pb.redirectErrorStream(true);
                Process proc = pb.start();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8));){
                    String line;
                    while ((line = br.readLine()) != null) {
                        q_2475_j.n_1700_B("train", line);
                        this.R_4764_Y(line);
                    }
                }
                int exit = proc.waitFor();
                q_2475_j.n_1700_B("train_neuro", exit, System.currentTimeMillis() - trainStarted);
                if (exit == 0 && outFile.exists()) {
                    q_2475_j.n_1700_B(outFile.toPath(), outFile.length(), "trained onnx model");
                    boolean ok = W_1707_M.R_4764_Y(key);
                    MinecraftAccess.c_3005_b.execute(() -> {
                        AttackAura aura = this.R_4764_Y();
                        if (aura != null) {
                            aura.C_2741_M();
                        }
                    });
                    if (ok) {
                        this.J_1907_R("\u00a7a\u041c\u043e\u0434\u0435\u043b\u044c \u00a7f" + name + " \u00a7a\u043e\u0431\u0443\u0447\u0435\u043d\u0430 \u0438 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u0430 (" + outFile.length() / 1024L + " KB)");
                    } else {
                        this.J_1907_R("\u00a7e\u041c\u043e\u0434\u0435\u043b\u044c \u043e\u0431\u0443\u0447\u0435\u043d\u0430, \u043d\u043e \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0437\u0438\u043b\u0430\u0441\u044c: " + outFile.getName());
                    }
                } else {
                    q_2475_j.n_1700_B("Training aborted (exit=" + exit + ")", null);
                    this.J_1907_R("\u00a7c\u041e\u0431\u0443\u0447\u0435\u043d\u0438\u0435 \u043f\u0440\u0435\u0440\u0432\u0430\u043d\u043e (\u043a\u043e\u0434 " + exit + "). \u0421\u043c. \u043a\u043e\u043d\u0441\u043e\u043b\u044c F3");
                }
            }
            catch (IOException e) {
                u_1980_X.n_1700_B();
                q_2475_j.n_1700_B("Training process IO error", e);
                this.J_1907_R("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u043f\u0443\u0441\u043a\u0430 Python: " + e.getMessage());
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                q_2475_j.J_1907_R("Training thread interrupted");
                this.J_1907_R("\u00a7c\u041e\u0431\u0443\u0447\u0435\u043d\u0438\u0435 \u043f\u0440\u0435\u0440\u0432\u0430\u043d\u043e");
            }
            catch (Throwable t) {
                q_2475_j.n_1700_B("Training failed", t);
                this.J_1907_R("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u043e\u0431\u0443\u0447\u0435\u043d\u0438\u044f: " + t.getMessage());
            }
        }, "Neuro-PyTorch-Trainer").start();
        return 1;
    }

    private File J_1907_R() {
        File extracted = b_967_P.n_1700_B();
        if (extracted != null) {
            return extracted;
        }
        File rel = new File(J_1907_R);
        if (rel.isFile()) {
            return rel;
        }
        File abs = new File(new File("").getAbsoluteFile(), J_1907_R);
        if (abs.isFile()) {
            return abs;
        }
        return null;
    }

    private int J_1907_R(CommandContext<V_4217_p> context) {
        v_1900_v.n_1700_B(new U_2871_b("\u00a76=== \u041d\u0435\u0439\u0440\u043e ===\n\u00a77.neuro record \u00a78- \u0437\u0430\u043f\u0438\u0441\u044c: \u0440\u0443\u0447\u043d\u043e\u0439 \u043f\u0440\u0438\u0446\u0435\u043b; \u043c\u0435\u0442\u043a\u0430 \u0443\u0434\u0430\u0440\u0430 \u2014 \u00a7f\u041b\u041a\u041c\n\u00a77.neuro stop \u00a78- \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u0437\u0430\u043f\u0438\u0441\u044c\n\u00a77.neuro save <\u0438\u043c\u044f> \u00a78- .ndata \n\u00a77.neuro train <\u0438\u043c\u044f> [\u044d\u043f\u043e\u0445\u0438] \u00a78\n\u00a77.neuro load <\u0438\u043c\u044f> \u00a78- \u0437\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c .onnx \u043c\u043e\u0434\u0435\u043b\u044c\n\u00a77.neuro list \u00a78- \u0441\u043f\u0438\u0441\u043e\u043a \u0434\u0430\u043d\u043d\u044b\u0445 \u0438 \u043c\u043e\u0434\u0435\u043b\u0435\u0439\n\u00a77.neuro reload \u00a78- \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c \u0432\u0441\u0435 \u043c\u043e\u0434\u0435\u043b\u0438\n\u00a77.neuro dir \u00a78- \u043e\u0442\u043a\u0440\u044b\u0442\u044c \u043f\u0430\u043f\u043a\u0443 "), new Object[0]);
        return 1;
    }

    private void J_1907_R(String message) {
        MinecraftAccess.c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b("\u00a77[\u041d\u0435\u0439\u0440\u043e] " + message), new Object[0]));
    }

    private void R_4764_Y(String line) {
        if (line == null || line.isBlank()) {
            return;
        }
        String trimmed = line.trim();
        if (trimmed.startsWith("NEURO_EPOCH|")) {
            String[] p = trimmed.split("\\|");
            if (p.length >= 5) {
                this.J_1907_R("\u00a77\u042d\u043f\u043e\u0445\u0430 \u00a7f" + p[1] + "/" + p[2] + " \u00a77train=\u00a7f" + p[3] + " \u00a77val=\u00a7f" + p[4]);
            }
            return;
        }
        if (trimmed.equals("NEURO_DONE|ok")) {
            this.J_1907_R("\u00a7a\u042d\u043a\u0441\u043f\u043e\u0440\u0442 ONNX \u0437\u0430\u0432\u0435\u0440\u0448\u0451\u043d");
        } else if (trimmed.equals("NEURO_DONE|fail")) {
            this.J_1907_R("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0432 \u0441\u043a\u0440\u0438\u043f\u0442\u0435 \u043e\u0431\u0443\u0447\u0435\u043d\u0438\u044f");
        }
    }

    private AttackAura R_4764_Y() {
        return (AttackAura)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class);
    }

    private String G_564_y(String name) {
        return name.replaceAll("[\\\\/:*?\"<>|]", "_");
    }
}


