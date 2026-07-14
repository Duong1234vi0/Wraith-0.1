/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.q_366_O;
import lightning.product.y_2603_k;

public class C_363_w
extends X_3546_T {
    public static q_366_O v_4262_N = new q_366_O("\u041a\u043b\u0430\u0432\u0438\u0448\u0430", "\u041b\u0435\u0432\u0430\u044f", "\u041b\u0435\u0432\u0430\u044f", "\u041f\u0440\u0430\u0432\u0430\u044f");
    public static I_686_h w_1484_f = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 1.0f, 1.0f, 30.0f, 1.0f);
    private int t_148_a = 0;

    public C_363_w() {
        super("TapeMouse", y_2603_k.P_1922_E);
        this.n_1700_B(v_4262_N, w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        ++this.t_148_a;
        if ((float)this.t_148_a >= ((Float)w_1484_f.J_1907_R()).floatValue()) {
            if (v_4262_N.J_1907_R("\u041b\u0435\u0432\u0430\u044f")) {
                c_3005_b.M_182_A();
            } else if (v_4262_N.J_1907_R("\u041f\u0440\u0430\u0432\u0430\u044f")) {
                c_3005_b.t_1786_h();
            }
            this.t_148_a = 0;
        }
    }
}

