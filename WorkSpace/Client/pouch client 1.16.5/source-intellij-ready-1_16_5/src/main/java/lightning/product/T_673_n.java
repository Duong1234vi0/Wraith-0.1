/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.N_4263_v;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.SoundEvent;

public class T_673_n
extends AbstractTickableSoundInstance {
    private final N_4263_v n_1700_B;

    public T_673_n(SoundEvent sound, D_38_f category, N_4263_v entity) {
        this(sound, category, 1.0f, 1.0f, entity);
    }

    public T_673_n(SoundEvent sound, D_38_f category, float volume, float pitch, N_4263_v entity) {
        super(sound, category);
        this.P_1922_E = volume;
        this.u_1723_Y = pitch;
        this.n_1700_B = entity;
        this.v_4262_N = (float)this.n_1700_B.O_3598_v();
        this.w_1484_f = (float)this.n_1700_B.X_2960_b();
        this.t_148_a = (float)this.n_1700_B.l_2647_k();
    }

    @Override
    public boolean P_1922_E() {
        return !this.n_1700_B.y_1700_S();
    }

    @Override
    public void R_4764_Y() {
        if (this.n_1700_B.t_4219_U) {
            this.w_1457_N();
        } else {
            this.v_4262_N = (float)this.n_1700_B.O_3598_v();
            this.w_1484_f = (float)this.n_1700_B.X_2960_b();
            this.t_148_a = (float)this.n_1700_B.l_2647_k();
        }
    }
}


