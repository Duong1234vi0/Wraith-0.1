/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.y_2603_k;

public class X_2937_s
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("MinAlpha", 0.5f, 0.0f, 1.0f, 0.1f);
    private final I_686_h w_1484_f = new I_686_h("StartDistance", 1.5f, 1.0f, 2.0f, 0.1f);
    private final I_686_h t_148_a = new I_686_h("KillDistance", 0.5f, 0.0f, 1.0f, 0.01f);

    public X_2937_s() {
        super("DistantAlpha", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    public float n_1700_B(r_4811_B entity) {
        if (!this.w_1484_f() || entity == null || X_2937_s.c_3005_b.Y_259_p == null) {
            return 1.0f;
        }
        if (entity == X_2937_s.c_3005_b.Y_259_p) {
            return 1.0f;
        }
        double distance = X_2937_s.c_3005_b.Y_259_p.R_4764_Y(entity);
        if (distance <= (double)((Float)this.t_148_a.J_1907_R()).floatValue()) {
            return ((Float)this.v_4262_N.J_1907_R()).floatValue();
        }
        if (distance <= (double)((Float)this.w_1484_f.J_1907_R()).floatValue()) {
            float progress = (float)((distance - (double)((Float)this.t_148_a.J_1907_R()).floatValue()) / (double)(((Float)this.w_1484_f.J_1907_R()).floatValue() - ((Float)this.t_148_a.J_1907_R()).floatValue()));
            return u_530_F.v_4262_N(progress, ((Float)this.v_4262_N.J_1907_R()).floatValue(), 1.0f);
        }
        return 1.0f;
    }
}

