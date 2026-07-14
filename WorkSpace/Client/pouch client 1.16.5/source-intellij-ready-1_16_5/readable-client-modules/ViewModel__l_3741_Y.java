/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_453_w;
import lightning.product.H_1491_c;
import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.g_221_o;
import lightning.product.k_4231_L;
import lightning.product.y_2603_k;

public class l_3741_Y
extends X_3546_T {
    public final I_686_h v_4262_N = new I_686_h("\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430 X", 0.0f, -2.0f, 2.0f, 0.1f);
    public final I_686_h w_1484_f = new I_686_h("\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Y", 0.0f, -2.0f, 2.0f, 0.1f);
    public final I_686_h t_148_a = new I_686_h("\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Z", 0.0f, -2.0f, 2.0f, 0.1f);
    public final I_686_h s_956_w = new I_686_h("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430 X", 0.0f, -2.0f, 2.0f, 0.1f);
    public final I_686_h u_2550_I = new I_686_h("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Y", 0.0f, -2.0f, 2.0f, 0.1f);
    public final I_686_h M_588_G = new I_686_h("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Z", 0.0f, -2.0f, 2.0f, 0.1f);
    public final H_1491_c P_4830_p = new H_1491_c("\u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c", () -> {
        this.v_4262_N.n_1700_B(Float.valueOf(0.0f));
        this.w_1484_f.n_1700_B(Float.valueOf(0.0f));
        this.t_148_a.n_1700_B(Float.valueOf(0.0f));
        this.s_956_w.n_1700_B(Float.valueOf(0.0f));
        this.u_2550_I.n_1700_B(Float.valueOf(0.0f));
        this.M_588_G.n_1700_B(Float.valueOf(0.0f));
    });

    public l_3741_Y() {
        super("ViewModel", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p);
    }

    @Y_1740_V
    public void n_1700_B(E_453_w e) {
        g_221_o matrixStack = e.J_1907_R();
        if (e.R_4764_Y() == k_4231_L.J_1907_R) {
            matrixStack.n_1700_B((double)((Float)this.v_4262_N.J_1907_R()).floatValue(), (double)((Float)this.w_1484_f.J_1907_R()).floatValue(), (double)((Float)this.t_148_a.J_1907_R()).floatValue());
        } else {
            matrixStack.n_1700_B((double)((Float)this.s_956_w.J_1907_R()).floatValue(), (double)((Float)this.u_2550_I.J_1907_R()).floatValue(), (double)((Float)this.M_588_G.J_1907_R()).floatValue());
        }
    }
}

