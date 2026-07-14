/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.ContainerHelper;
import lightning.product.r_4432_i;
import lightning.product.y_452_M;

public class CraftingContainer
implements Container,
y_452_M {
    private final NonNullList<Z_1993_T> n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final a_2900_S G_564_y;

    public CraftingContainer(a_2900_S eventHandlerIn, int width, int height) {
        this.n_1700_B = NonNullList.n_1700_B(width * height, Z_1993_T.J_1907_R);
        this.G_564_y = eventHandlerIn;
        this.J_1907_R = width;
        this.R_4764_Y = height;
    }

    @Override
    public int Y_259_p() {
        return this.n_1700_B.size();
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
        return index >= this.Y_259_p() ? Z_1993_T.J_1907_R : this.n_1700_B.get(index);
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        return ContainerHelper.n_1700_B(this.n_1700_B, index);
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        Z_1993_T itemstack = ContainerHelper.n_1700_B(this.n_1700_B, index, count);
        if (!itemstack.n_1700_B()) {
            this.G_564_y.n_1700_B(this);
        }
        return itemstack;
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        this.n_1700_B.set(index, stack);
        this.G_564_y.n_1700_B(this);
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

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public int G_564_y() {
        return this.J_1907_R;
    }

    @Override
    public void n_1700_B(r_4432_i helper) {
        for (Z_1993_T itemstack : this.n_1700_B) {
            helper.n_1700_B(itemstack);
        }
    }
}


