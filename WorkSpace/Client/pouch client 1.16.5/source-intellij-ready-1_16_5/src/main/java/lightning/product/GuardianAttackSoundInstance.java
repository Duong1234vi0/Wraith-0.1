/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.G_1455_B;
import lightning.product.SoundEvents;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.SoundInstance;

public class GuardianAttackSoundInstance
extends AbstractTickableSoundInstance {
    private final G_1455_B n_1700_B;

    public GuardianAttackSoundInstance(G_1455_B guardian) {
        super(SoundEvents.N_4006_T, D_38_f.u_1723_Y);
        this.n_1700_B = guardian;
        this.M_588_G = SoundInstance.n_1700_B.n_1700_B;
        this.s_956_w = true;
        this.u_2550_I = 0;
    }

    @Override
    public boolean P_1922_E() {
        return !this.n_1700_B.y_1700_S();
    }

    @Override
    public void R_4764_Y() {
        if (!this.n_1700_B.t_4219_U && this.n_1700_B.t_148_a() == null) {
            this.v_4262_N = (float)this.n_1700_B.O_3598_v();
            this.w_1484_f = (float)this.n_1700_B.X_2960_b();
            this.t_148_a = (float)this.n_1700_B.l_2647_k();
            float f = this.n_1700_B.A_4115_X(0.0f);
            this.P_1922_E = 0.0f + 1.0f * f * f;
            this.u_1723_Y = 0.7f + 0.5f * f;
        } else {
            this.w_1457_N();
        }
    }
}


