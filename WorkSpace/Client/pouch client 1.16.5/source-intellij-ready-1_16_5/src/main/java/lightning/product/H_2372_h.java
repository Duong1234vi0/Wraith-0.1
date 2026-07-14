/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.N_4263_v;
import lightning.product.SoundEvents;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.SoundInstance;
import lightning.product.a_3913_L;
import lightning.product.u_530_F;
import lightning.product.y_4319_k;

public class H_2372_h
extends AbstractTickableSoundInstance {
    private final a_3913_L n_1700_B;
    private final y_4319_k Q_4569_t;

    public H_2372_h(a_3913_L playerIn, y_4319_k minecartIn) {
        super(SoundEvents.ElytraResolver, D_38_f.v_4262_N);
        this.n_1700_B = playerIn;
        this.Q_4569_t = minecartIn;
        this.M_588_G = SoundInstance.n_1700_B.n_1700_B;
        this.s_956_w = true;
        this.u_2550_I = 0;
        this.P_1922_E = 0.0f;
    }

    @Override
    public boolean P_1922_E() {
        return !this.Q_4569_t.y_1700_S();
    }

    @Override
    public boolean G_564_y() {
        return true;
    }

    @Override
    public void R_4764_Y() {
        if (!this.Q_4569_t.t_4219_U && this.n_1700_B.y_2772_m() && this.n_1700_B.l_3609_d() == this.Q_4569_t) {
            float f = u_530_F.n_1700_B(N_4263_v.R_4764_Y(this.Q_4569_t.I_4348_c()));
            this.P_1922_E = (double)f >= 0.01 ? 0.0f + u_530_F.n_1700_B(f, 0.0f, 1.0f) * 0.75f : 0.0f;
        } else {
            this.w_1457_N();
        }
    }
}



