/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Locale;
import java.util.function.IntFunction;
import lightning.product.f_2403_E;
import lightning.product.u_530_F;

public final class Q_1082_O {
    public static final int n_1700_B = 7;
    public static final int J_1907_R = 42;
    public static final int R_4764_Y = 6;

    private Q_1082_O() {
    }

    public static String n_1700_B(String modelName) {
        if (modelName == null || modelName.isEmpty()) {
            return "model";
        }
        if ("tf-0000".equals(modelName)) {
            return "tf";
        }
        String p = modelName.replace('/', '_').replace('\\', '_');
        return p.isEmpty() ? "model" : p;
    }

    public static String n_1700_B(int i) {
        switch (i) {
            case 0: {
                return "T-3";
            }
            case 1: {
                return "T-2";
            }
            case 2: {
                return "T-1";
            }
            case 3: {
                return "T0 (\u0443\u0434\u0430\u0440)";
            }
            case 4: {
                return "T+1";
            }
            case 5: {
                return "T+2";
            }
            case 6: {
                return "T+3";
            }
        }
        return "T?";
    }

    private static float n_1700_B() {
        return Math.max(5.0f, f_2403_E.J_1907_R());
    }

    public static String n_1700_B(float[] v) {
        if (v == null || v.length != 42) {
            return "(\u043d\u0435\u0432\u0435\u0440\u043d\u0430\u044f \u0434\u043b\u0438\u043d\u0430 \u0432\u0445\u043e\u0434\u0430, \u043e\u0436\u0438\u0434\u0430\u0435\u0442\u0441\u044f 42)\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; ++i) {
            int b = 6 * i;
            float yaw = v[b];
            float dYaw = v[b + 1];
            float pitch = v[b + 2];
            float yawRs = v[b + 3];
            float dYawRs = v[b + 4];
            float pitchRs = v[b + 5];
            sb.append(String.format(Locale.ROOT, "%s: %.8f (yaw)  %.8f (deltaYaw)  %.8f (pitch)  |  %.8f (yaw \u00b0/s)  %.8f (deltaYaw \u00b0/s)  %.8f (pitch \u00b0/s)\n", Q_1082_O.n_1700_B(i), Float.valueOf(yaw), Float.valueOf(dYaw), Float.valueOf(pitch), Float.valueOf(yawRs), Float.valueOf(dYawRs), Float.valueOf(pitchRs)));
        }
        return sb.toString();
    }

    public static String n_1700_B(float yawDelta, float pitchDelta) {
        return String.format(Locale.ROOT, "%.8f (next deltaYaw)  %.8f (next deltaPitch)\n", Float.valueOf(yawDelta), Float.valueOf(pitchDelta));
    }

    public static void n_1700_B(Path modelDir, String prefix, float[] exampleIn, float[] out2) throws IOException {
        Path human = modelDir.resolve(prefix + "-0000.params.human.txt");
        StringBuilder sb = new StringBuilder();
        sb.append("# \u0420\u044f\u0434\u043e\u043c \u0441 \u0431\u0438\u043d\u0430\u0440\u043d\u044b\u043c ").append(prefix).append("-0000.params\n");
        sb.append("# \u0412\u043d\u0443\u0442\u0440\u044c .params \u0442\u0435\u043a\u0441\u0442 \u043d\u0435\u043b\u044c\u0437\u044f \u2014 \u0442\u043e\u043b\u044c\u043a\u043e \u044d\u0442\u043e\u0442 \u0444\u0430\u0439\u043b.\n\n");
        sb.append("\u0412\u0445\u043e\u0434: 7 \u0442\u0438\u043a\u043e\u0432 \u00d7 6 \u0447\u0438\u0441\u0435\u043b = ").append(42).append(": yaw, deltaYaw, pitch, yaw\u00b0/s, deltaYaw\u00b0/s, pitch\u00b0/s.\n");
        sb.append("\u0412\u044b\u0445\u043e\u0434 \u043c\u043e\u0434\u0435\u043b\u0438: delta yaw \u0438 delta pitch \u043d\u0430 \u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0438\u0439 \u0442\u0438\u043a.\n\n");
        sb.append("=== \u0418\u043d\u0434\u0435\u043a\u0441\u044b \u0432 CSV / float[] ===\n");
        for (int i = 0; i < 7; ++i) {
            int b = 6 * i;
            sb.append(String.format(Locale.ROOT, "  [%d..%d]  %s: yaw, dYaw, pitch, yaw\u00b0/s, dYaw\u00b0/s, pitch\u00b0/s\n", b, b + 6 - 1, Q_1082_O.n_1700_B(i)));
        }
        sb.append("\n");
        if (exampleIn != null && exampleIn.length == 42) {
            sb.append("=== \u041f\u0435\u0440\u0432\u044b\u0439 \u0432\u0435\u043a\u0442\u043e\u0440 \u0438\u0437 \u0434\u0430\u043d\u043d\u044b\u0445 \u043e\u0431\u0443\u0447\u0435\u043d\u0438\u044f ===\n");
            sb.append(Q_1082_O.n_1700_B(exampleIn));
            if (out2 != null && out2.length >= 2) {
                sb.append("\u0412\u044b\u0445\u043e\u0434 (\u0446\u0435\u043b\u0435\u0432\u043e\u0439):\n");
                sb.append(Q_1082_O.n_1700_B(out2[0], out2[1]));
            }
        }
        Files.writeString(human, (CharSequence)sb.toString(), StandardCharsets.UTF_8, new OpenOption[0]);
    }

    public static void J_1907_R(Path dataDirPath, String baseName, float[] in42, float[] out2) throws IOException {
        float[][] fArrayArray;
        float[][] fArrayArray2;
        if (in42 == null) {
            fArrayArray2 = null;
        } else {
            float[][] fArrayArray3 = new float[1][];
            fArrayArray2 = fArrayArray3;
            fArrayArray3[0] = in42;
        }
        float[][] inputs = fArrayArray2;
        if (out2 == null) {
            fArrayArray = null;
        } else {
            float[][] fArrayArray4 = new float[1][];
            fArrayArray = fArrayArray4;
            fArrayArray4[0] = out2;
        }
        float[][] outputs = fArrayArray;
        Q_1082_O.n_1700_B(dataDirPath, baseName, inputs, outputs);
    }

    public static void n_1700_B(Path dataDirPath, String baseName, float[][] inputs42, float[][] outputs2) throws IOException {
        Path human = dataDirPath.resolve(baseName + ".ndata.human.txt");
        StringBuilder sb = new StringBuilder();
        int count = inputs42 == null ? 0 : inputs42.length;
        sb.append("# \u041f\u043e\u0434\u043f\u0438\u0441\u0430\u043d\u043d\u044b\u0435 \u0437\u0430\u043f\u0438\u0441\u0438 \u0438\u0437 ").append(baseName).append(".ndata\n");
        sb.append("# \u0412\u0441\u0435\u0433\u043e \u0441\u044d\u043c\u043f\u043b\u043e\u0432: ").append(count).append("\n\n");
        if (inputs42 != null && inputs42.length > 0) {
            for (int i = 0; i < inputs42.length; ++i) {
                float[] out2;
                float[] in42 = inputs42[i];
                if (in42 == null || in42.length != 42) {
                    sb.append("=== \u0421\u044d\u043c\u043f\u043b #").append(i + 1).append(" ===\n");
                    sb.append("(\u043f\u0440\u043e\u043f\u0443\u0449\u0435\u043d: \u043d\u0435\u0432\u0435\u0440\u043d\u0430\u044f \u0434\u043b\u0438\u043d\u0430 \u0432\u0445\u043e\u0434\u0430)\n\n");
                    continue;
                }
                sb.append("=== \u0421\u044d\u043c\u043f\u043b #").append(i + 1).append(" ===\n");
                sb.append(Q_1082_O.n_1700_B(in42));
                if (outputs2 != null && i < outputs2.length && (out2 = outputs2[i]) != null && out2.length >= 2) {
                    sb.append("\u0412\u044b\u0445\u043e\u0434:\n");
                    sb.append(Q_1082_O.n_1700_B(out2[0], out2[1]));
                }
                sb.append("\n");
            }
        }
        Files.writeString(human, (CharSequence)sb.toString(), StandardCharsets.UTF_8, new OpenOption[0]);
    }

    public static float n_1700_B(float deg) {
        return u_530_F.v_4262_N(deg);
    }

    public static void n_1700_B(float[] out, IntFunction<Float> yawAtAge, IntFunction<Float> pitchAtAge, int historyCount) {
        float tps = Q_1082_O.n_1700_B();
        for (int i = 0; i < 7; ++i) {
            int a = 6 - i;
            int b = 6 * i;
            float yaw = yawAtAge.apply(a).floatValue();
            float pitch = pitchAtAge.apply(a).floatValue();
            float dYaw = Q_1082_O.n_1700_B(yawAtAge.apply(a).floatValue() - yawAtAge.apply(a + 1).floatValue());
            float dPitch = pitchAtAge.apply(a).floatValue() - pitchAtAge.apply(a + 1).floatValue();
            float dYawPrev = dYaw;
            if (a + 2 < historyCount) {
                dYawPrev = Q_1082_O.n_1700_B(yawAtAge.apply(a + 1).floatValue() - yawAtAge.apply(a + 2).floatValue());
            }
            float yawRate = dYaw * tps;
            float deltaYawRate = Q_1082_O.n_1700_B(dYaw - dYawPrev) * tps;
            float pitchRate = dPitch * tps;
            out[b] = yaw;
            out[b + 1] = dYaw;
            out[b + 2] = pitch;
            out[b + 3] = yawRate;
            out[b + 4] = deltaYawRate;
            out[b + 5] = pitchRate;
        }
    }
}

