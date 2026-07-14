/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.N_4263_v;
import lightning.product.SoundEvents;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.u_530_F;
import lightning.product.y_4319_k;

public class MinecartSoundInstance
extends AbstractTickableSoundInstance {
    private final y_4319_k n_1700_B;
    private float Q_4569_t = 0.0f;

    public MinecartSoundInstance(y_4319_k minecartIn) {
        super(SoundEvents.ElytraJump, D_38_f.v_4262_N);
        this.n_1700_B = minecartIn;
        this.s_956_w = true;
        this.u_2550_I = 0;
        this.P_1922_E = 0.0f;
        this.v_4262_N = (float)minecartIn.O_3598_v();
        this.w_1484_f = (float)minecartIn.X_2960_b();
        this.t_148_a = (float)minecartIn.l_2647_k();
    }

    @Override
    public boolean P_1922_E() {
        return !this.n_1700_B.y_1700_S();
    }

    @Override
    public boolean G_564_y() {
        return true;
    }

    @Override
    public void R_4764_Y() {
        if (this.n_1700_B.t_4219_U) {
            this.w_1457_N();
        } else {
            this.v_4262_N = (float)this.n_1700_B.O_3598_v();
            this.w_1484_f = (float)this.n_1700_B.X_2960_b();
            this.t_148_a = (float)this.n_1700_B.l_2647_k();
            float f = u_530_F.n_1700_B(N_4263_v.R_4764_Y(this.n_1700_B.I_4348_c()));
            if ((double)f >= 0.01) {
                this.Q_4569_t = u_530_F.n_1700_B(this.Q_4569_t + 0.0025f, 0.0f, 1.0f);
                this.P_1922_E = u_530_F.v_4262_N(u_530_F.n_1700_B(f, 0.0f, 0.5f), 0.0f, 0.7f);
            } else {
                this.Q_4569_t = 0.0f;
                this.P_1922_E = 0.0f;
            }
        }
    }
}



