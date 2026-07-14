/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_2717_K;
import lightning.product.q_2896_o;

public class Rotations {
    protected final float n_1700_B;
    protected final float J_1907_R;
    protected final float R_4764_Y;

    public Rotations(float x, float y, float z) {
        this.n_1700_B = !Float.isInfinite(x) && !Float.isNaN(x) ? x % 360.0f : 0.0f;
        this.J_1907_R = !Float.isInfinite(y) && !Float.isNaN(y) ? y % 360.0f : 0.0f;
        this.R_4764_Y = !Float.isInfinite(z) && !Float.isNaN(z) ? z % 360.0f : 0.0f;
    }

    public Rotations(q_2896_o nbt) {
        this(nbt.w_1484_f(0), nbt.w_1484_f(1), nbt.w_1484_f(2));
    }

    public q_2896_o n_1700_B() {
        q_2896_o listnbt = new q_2896_o();
        listnbt.add(T_2717_K.n_1700_B(this.n_1700_B));
        listnbt.add(T_2717_K.n_1700_B(this.J_1907_R));
        listnbt.add(T_2717_K.n_1700_B(this.R_4764_Y));
        return listnbt;
    }

    public boolean equals(Object p_equals_1_) {
        if (!(p_equals_1_ instanceof Rotations)) {
            return false;
        }
        Rotations rotations = (Rotations)p_equals_1_;
        return this.n_1700_B == rotations.n_1700_B && this.J_1907_R == rotations.J_1907_R && this.R_4764_Y == rotations.R_4764_Y;
    }

    public float J_1907_R() {
        return this.n_1700_B;
    }

    public float R_4764_Y() {
        return this.J_1907_R;
    }

    public float G_564_y() {
        return this.R_4764_Y;
    }
}


