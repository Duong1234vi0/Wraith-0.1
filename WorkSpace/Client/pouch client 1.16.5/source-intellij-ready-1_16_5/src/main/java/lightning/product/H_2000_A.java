/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.Merchant;
import lightning.product.MerchantOffers;
import lightning.product.ContainerHelper;
import lightning.product.MerchantOffer;

public class H_2000_A
implements Container {
    private final Merchant n_1700_B;
    private final NonNullList<Z_1993_T> J_1907_R = NonNullList.n_1700_B(3, Z_1993_T.J_1907_R);
    @Nullable
    private MerchantOffer R_4764_Y;
    private int G_564_y;
    private int P_1922_E;

    public H_2000_A(Merchant merchantIn) {
        this.n_1700_B = merchantIn;
    }

    @Override
    public int Y_259_p() {
        return this.J_1907_R.size();
    }

    @Override
    public boolean Q_2552_b() {
        for (Z_1993_T itemstack : this.J_1907_R) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        return this.J_1907_R.get(index);
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        Z_1993_T itemstack = this.J_1907_R.get(index);
        if (index == 2 && !itemstack.n_1700_B()) {
            return ContainerHelper.n_1700_B(this.J_1907_R, index, itemstack.t_4043_B());
        }
        Z_1993_T itemstack1 = ContainerHelper.n_1700_B(this.J_1907_R, index, count);
        if (!itemstack1.n_1700_B() && this.J_1907_R(index)) {
            this.R_4764_Y();
        }
        return itemstack1;
    }

    private boolean J_1907_R(int slotIn) {
        return slotIn == 0 || slotIn == 1;
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        return ContainerHelper.n_1700_B(this.J_1907_R, index);
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        this.J_1907_R.set(index, stack);
        if (!stack.n_1700_B() && stack.t_4043_B() > this.J_()) {
            stack.P_1922_E(this.J_());
        }
        if (this.J_1907_R(index)) {
            this.R_4764_Y();
        }
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        return this.n_1700_B.n_1700_B() == player;
    }

    @Override
    public void J_1907_R() {
        this.R_4764_Y();
    }

    public void R_4764_Y() {
        Z_1993_T itemstack1;
        Z_1993_T itemstack;
        this.R_4764_Y = null;
        if (this.J_1907_R.get(0).n_1700_B()) {
            itemstack = this.J_1907_R.get(1);
            itemstack1 = Z_1993_T.J_1907_R;
        } else {
            itemstack = this.J_1907_R.get(0);
            itemstack1 = this.J_1907_R.get(1);
        }
        if (itemstack.n_1700_B()) {
            this.J_1907_R(2, Z_1993_T.J_1907_R);
            this.P_1922_E = 0;
        } else {
            MerchantOffers merchantoffers = this.n_1700_B.J_1907_R();
            if (!merchantoffers.isEmpty()) {
                MerchantOffer merchantoffer = merchantoffers.n_1700_B(itemstack, itemstack1, this.G_564_y);
                if (merchantoffer == null || merchantoffer.M_182_A()) {
                    this.R_4764_Y = merchantoffer;
                    merchantoffer = merchantoffers.n_1700_B(itemstack1, itemstack, this.G_564_y);
                }
                if (merchantoffer != null && !merchantoffer.M_182_A()) {
                    this.R_4764_Y = merchantoffer;
                    this.J_1907_R(2, merchantoffer.u_1723_Y());
                    this.P_1922_E = merchantoffer.Q_4569_t();
                } else {
                    this.J_1907_R(2, Z_1993_T.J_1907_R);
                    this.P_1922_E = 0;
                }
            }
            this.n_1700_B.n_1700_B(this.s_956_w(2));
        }
    }

    @Nullable
    public MerchantOffer G_564_y() {
        return this.R_4764_Y;
    }

    public void n_1700_B(int currentRecipeIndexIn) {
        this.G_564_y = currentRecipeIndexIn;
        this.R_4764_Y();
    }

    @Override
    public void C_2741_M() {
        this.J_1907_R.clear();
    }

    public int P_1922_E() {
        return this.P_1922_E;
    }
}


