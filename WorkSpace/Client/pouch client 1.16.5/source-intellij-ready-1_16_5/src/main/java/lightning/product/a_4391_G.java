/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;

public class a_4391_G
implements Container {
    private final Container n_1700_B;
    private final Container J_1907_R;

    public a_4391_G(Container upperChest, Container lowerChest) {
        if (upperChest == null) {
            upperChest = lowerChest;
        }
        if (lowerChest == null) {
            lowerChest = upperChest;
        }
        this.n_1700_B = upperChest;
        this.J_1907_R = lowerChest;
    }

    @Override
    public int Y_259_p() {
        return this.n_1700_B.Y_259_p() + this.J_1907_R.Y_259_p();
    }

    @Override
    public boolean Q_2552_b() {
        return this.n_1700_B.Q_2552_b() && this.J_1907_R.Q_2552_b();
    }

    public boolean n_1700_B(Container inventoryIn) {
        return this.n_1700_B == inventoryIn || this.J_1907_R == inventoryIn;
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        return index >= this.n_1700_B.Y_259_p() ? this.J_1907_R.s_956_w(index - this.n_1700_B.Y_259_p()) : this.n_1700_B.s_956_w(index);
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        return index >= this.n_1700_B.Y_259_p() ? this.J_1907_R.n_1700_B(index - this.n_1700_B.Y_259_p(), count) : this.n_1700_B.n_1700_B(index, count);
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        return index >= this.n_1700_B.Y_259_p() ? this.J_1907_R.u_2550_I(index - this.n_1700_B.Y_259_p()) : this.n_1700_B.u_2550_I(index);
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        if (index >= this.n_1700_B.Y_259_p()) {
            this.J_1907_R.J_1907_R(index - this.n_1700_B.Y_259_p(), stack);
        } else {
            this.n_1700_B.J_1907_R(index, stack);
        }
    }

    @Override
    public int J_() {
        return this.n_1700_B.J_();
    }

    @Override
    public void J_1907_R() {
        this.n_1700_B.J_1907_R();
        this.J_1907_R.J_1907_R();
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        return this.n_1700_B.R_4764_Y(player) && this.J_1907_R.R_4764_Y(player);
    }

    @Override
    public void b_(a_3913_L player) {
        this.n_1700_B.b_(player);
        this.J_1907_R.b_(player);
    }

    @Override
    public void J_1907_R(a_3913_L player) {
        this.n_1700_B.J_1907_R(player);
        this.J_1907_R.J_1907_R(player);
    }

    @Override
    public boolean a_(int index, Z_1993_T stack) {
        return index >= this.n_1700_B.Y_259_p() ? this.J_1907_R.a_(index - this.n_1700_B.Y_259_p(), stack) : this.n_1700_B.a_(index, stack);
    }

    @Override
    public void C_2741_M() {
        this.n_1700_B.C_2741_M();
        this.J_1907_R.C_2741_M();
    }
}


