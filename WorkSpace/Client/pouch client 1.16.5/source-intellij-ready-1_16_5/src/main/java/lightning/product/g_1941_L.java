/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.RandomStrollGoal;
import lightning.product.W_3371_U;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;

public class g_1941_L
extends RandomStrollGoal {
    protected final float w_1484_f;

    public g_1941_L(PathfinderMob creature, double speedIn) {
        this(creature, speedIn, 0.001f);
    }

    public g_1941_L(PathfinderMob creature, double speedIn, float probabilityIn) {
        super(creature, speedIn);
        this.w_1484_f = probabilityIn;
    }

    @Override
    @Nullable
    protected e_2866_D v_4262_N() {
        if (this.n_1700_B.S_980_j()) {
            e_2866_D vector3d = W_3371_U.J_1907_R(this.n_1700_B, 15, 7);
            return vector3d == null ? super.v_4262_N() : vector3d;
        }
        return this.n_1700_B.M_3508_C().nextFloat() >= this.w_1484_f ? W_3371_U.J_1907_R(this.n_1700_B, 10, 7) : super.v_4262_N();
    }
}


