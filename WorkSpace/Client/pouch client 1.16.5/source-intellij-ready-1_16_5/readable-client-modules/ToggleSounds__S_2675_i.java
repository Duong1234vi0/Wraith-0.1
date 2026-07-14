/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.q_366_O;
import lightning.product.y_2603_k;

public class S_2675_i
extends X_3546_T {
    public static q_366_O v_4262_N = new q_366_O("\u0422\u0438\u043f \u0437\u0432\u0443\u043a\u0430", "\u0422\u0438\u043f 1", "\u0422\u0438\u043f 1", "\u0422\u0438\u043f 2", "\u0422\u0438\u043f 3", "\u0422\u0438\u043f 4");
    public static I_686_h w_1484_f = new I_686_h("\u0413\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c", 75.0f, 0.0f, 100.0f, 1.0f);

    public S_2675_i() {
        super("ToggleSounds", y_2603_k.P_1922_E);
        this.n_1700_B(v_4262_N, w_1484_f);
    }

    public static String P_1922_E(boolean isEnable) {
        String mode;
        return switch (mode = (String)v_4262_N.J_1907_R()) {
            case "\u0422\u0438\u043f 1" -> {
                if (isEnable) {
                    yield "enabled0";
                }
                yield "disabled0";
            }
            case "\u0422\u0438\u043f 2" -> {
                if (isEnable) {
                    yield "enabled1";
                }
                yield "disabled1";
            }
            case "\u0422\u0438\u043f 3" -> {
                if (isEnable) {
                    yield "enabled2";
                }
                yield "disabled2";
            }
            case "\u0422\u0438\u043f 4" -> {
                if (isEnable) {
                    yield "enabled3";
                }
                yield "disabled3";
            }
            default -> null;
        };
    }
}

