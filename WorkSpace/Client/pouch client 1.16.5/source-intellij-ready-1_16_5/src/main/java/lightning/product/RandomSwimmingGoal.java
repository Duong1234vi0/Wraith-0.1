/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.RandomStrollGoal;
import lightning.product.BlockGetter;
import lightning.product.W_3371_U;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.t_3546_P;

public class RandomSwimmingGoal
extends RandomStrollGoal {
    public RandomSwimmingGoal(PathfinderMob creature, double speed, int chance) {
        super(creature, speed, chance);
    }

    @Override
    @Nullable
    protected e_2866_D v_4262_N() {
        e_2866_D vector3d = W_3371_U.n_1700_B(this.n_1700_B, 10, 7);
        int i = 0;
        while (vector3d != null && !this.n_1700_B.O_508_d.getBlockState(new c_1514_x(vector3d)).n_1700_B((BlockGetter)this.n_1700_B.O_508_d, new c_1514_x(vector3d), t_3546_P.J_1907_R) && i++ < 10) {
            vector3d = W_3371_U.n_1700_B(this.n_1700_B, 10, 7);
        }
        return vector3d;
    }
}


