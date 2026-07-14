/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_1316_M;
import lightning.product.Z_3903_F;

public interface LevelTimeAccess
extends T_1316_M {
    public long A_4115_X();

    default public float Y_1740_V() {
        return Z_3903_F.P_1922_E[this.G_624_v().J_1907_R(this.A_4115_X())];
    }

    default public float G_564_y(float p_242415_1_) {
        return this.G_624_v().n_1700_B(this.A_4115_X());
    }

    default public int t_4043_B() {
        return this.G_624_v().J_1907_R(this.A_4115_X());
    }
}


