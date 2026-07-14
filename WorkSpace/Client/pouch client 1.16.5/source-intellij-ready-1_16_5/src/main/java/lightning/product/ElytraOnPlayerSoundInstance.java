/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.SoundEvents;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.X_4340_E;
import lightning.product.u_530_F;

public class ElytraOnPlayerSoundInstance
extends AbstractTickableSoundInstance {
    private final X_4340_E n_1700_B;
    private int Q_4569_t;

    public ElytraOnPlayerSoundInstance(X_4340_E playerIn) {
        super(SoundEvents.I_685_r, D_38_f.w_1484_f);
        this.n_1700_B = playerIn;
        this.s_956_w = true;
        this.u_2550_I = 0;
        this.P_1922_E = 0.1f;
    }

    @Override
    public void R_4764_Y() {
        ++this.Q_4569_t;
        if (!this.n_1700_B.t_4219_U && (this.Q_4569_t <= 20 || this.n_1700_B.k_578_l())) {
            this.v_4262_N = (float)this.n_1700_B.O_3598_v();
            this.w_1484_f = (float)this.n_1700_B.X_2960_b();
            this.t_148_a = (float)this.n_1700_B.l_2647_k();
            float f = (float)this.n_1700_B.I_4348_c().v_4262_N();
            this.P_1922_E = (double)f >= 1.0E-7 ? u_530_F.n_1700_B(f / 4.0f, 0.0f, 1.0f) : 0.0f;
            if (this.Q_4569_t < 20) {
                this.P_1922_E = 0.0f;
            } else if (this.Q_4569_t < 40) {
                this.P_1922_E = (float)((double)this.P_1922_E * ((double)(this.Q_4569_t - 20) / 20.0));
            }
            float f1 = 0.8f;
            this.u_1723_Y = this.P_1922_E > 0.8f ? 1.0f + (this.P_1922_E - 0.8f) : 1.0f;
        } else {
            this.w_1457_N();
        }
    }
}


