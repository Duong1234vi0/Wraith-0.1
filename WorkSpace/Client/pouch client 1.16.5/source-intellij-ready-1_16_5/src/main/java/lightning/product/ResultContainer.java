/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.L_2125_Q;
import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.Recipe;
import lightning.product.ContainerHelper;

public class ResultContainer
implements L_2125_Q,
Container {
    private final NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B(1, Z_1993_T.J_1907_R);
    @Nullable
    private Recipe<?> J_1907_R;

    @Override
    public int Y_259_p() {
        return 1;
    }

    @Override
    public boolean Q_2552_b() {
        for (Z_1993_T itemstack : this.n_1700_B) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        return this.n_1700_B.get(0);
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        return ContainerHelper.n_1700_B(this.n_1700_B, 0);
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        return ContainerHelper.n_1700_B(this.n_1700_B, 0);
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        this.n_1700_B.set(0, stack);
    }

    @Override
    public void J_1907_R() {
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        return true;
    }

    @Override
    public void C_2741_M() {
        this.n_1700_B.clear();
    }

    @Override
    public void n_1700_B(@Nullable Recipe<?> recipe) {
        this.J_1907_R = recipe;
    }

    @Override
    @Nullable
    public Recipe<?> R_4764_Y() {
        return this.J_1907_R;
    }
}


