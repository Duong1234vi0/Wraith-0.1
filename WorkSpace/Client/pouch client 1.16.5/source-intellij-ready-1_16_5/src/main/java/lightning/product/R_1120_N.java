/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.W_3491_f;
import lightning.product.ResultContainer;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ContainerLevelAccess;

public abstract class R_1120_N
extends a_2900_S {
    protected final ResultContainer n_1700_B = new ResultContainer();
    protected final Container J_1907_R = new N_1216_z(2){

        @Override
        public void J_1907_R() {
            super.J_1907_R();
            R_1120_N.this.n_1700_B(this);
        }
    };
    protected final ContainerLevelAccess R_4764_Y;
    protected final a_3913_L G_564_y;

    protected abstract boolean n_1700_B(a_3913_L var1, boolean var2);

    protected abstract Z_1993_T n_1700_B(a_3913_L var1, Z_1993_T var2);

    protected abstract boolean n_1700_B(K_4074_S var1);

    public R_1120_N(@Nullable MenuType<?> p_i231587_1_, int p_i231587_2_, W_3491_f p_i231587_3_, ContainerLevelAccess p_i231587_4_) {
        super(p_i231587_1_, p_i231587_2_);
        this.R_4764_Y = p_i231587_4_;
        this.G_564_y = p_i231587_3_.P_1922_E;
        this.J_1907_R(new Slot(this.J_1907_R, 0, 27, 47));
        this.J_1907_R(new Slot(this.J_1907_R, 1, 76, 47));
        this.J_1907_R(new Slot(this.n_1700_B, 2, 134, 47){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return false;
            }

            @Override
            public boolean n_1700_B(a_3913_L playerIn) {
                return R_1120_N.this.n_1700_B(playerIn, this.J_1907_R());
            }

            @Override
            public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
                return R_1120_N.this.n_1700_B(thePlayer, stack);
            }
        });
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.J_1907_R(new Slot(p_i231587_3_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.J_1907_R(new Slot(p_i231587_3_, k, 8 + k * 18, 142));
        }
    }

    public abstract void n_1700_B();

    @Override
    public void n_1700_B(Container inventoryIn) {
        super.n_1700_B(inventoryIn);
        if (inventoryIn == this.J_1907_R) {
            this.n_1700_B();
        }
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.R_4764_Y.n_1700_B((b_4507_u p_234647_2_, c_1514_x p_234647_3_) -> this.n_1700_B(playerIn, (b_4507_u)p_234647_2_, this.J_1907_R));
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return this.R_4764_Y.n_1700_B((b_4507_u p_234646_2_, c_1514_x p_234646_3_) -> !this.n_1700_B(p_234646_2_.getBlockState((c_1514_x)p_234646_3_)) ? false : playerIn.v_4262_N((double)p_234646_3_.getX() + 0.5, (double)p_234646_3_.getY() + 0.5, (double)p_234646_3_.getZ() + 0.5) <= 64.0, true);
    }

    protected boolean n_1700_B(Z_1993_T p_241210_1_) {
        return false;
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            if (index == 2) {
                if (!this.n_1700_B(itemstack1, 3, 39, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (index != 0 && index != 1) {
                if (index >= 3 && index < 39) {
                    int i;
                    int n = i = this.n_1700_B(itemstack) ? 1 : 0;
                    if (!this.n_1700_B(itemstack1, i, 2, false)) {
                        return Z_1993_T.J_1907_R;
                    }
                }
            } else if (!this.n_1700_B(itemstack1, 3, 39, false)) {
                return Z_1993_T.J_1907_R;
            }
            if (itemstack1.n_1700_B()) {
                slot.J_1907_R(Z_1993_T.J_1907_R);
            } else {
                slot.R_4764_Y();
            }
            if (itemstack1.t_4043_B() == itemstack.t_4043_B()) {
                return Z_1993_T.J_1907_R;
            }
            slot.n_1700_B(playerIn, itemstack1);
        }
        return itemstack;
    }
}


