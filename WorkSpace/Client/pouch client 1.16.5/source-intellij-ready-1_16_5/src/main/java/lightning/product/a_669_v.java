/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SimpleContainerData;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.ContainerData;

public class a_669_v
extends a_2900_S {
    private final Container n_1700_B;
    private final ContainerData J_1907_R;

    public a_669_v(int p_i50075_1_) {
        this(p_i50075_1_, new N_1216_z(1), new SimpleContainerData(1));
    }

    public a_669_v(int id, Container p_i50076_2_, ContainerData p_i50076_3_) {
        super(MenuType.t_1786_h, id);
        a_669_v.n_1700_B(p_i50076_2_, 1);
        a_669_v.n_1700_B(p_i50076_3_, 1);
        this.n_1700_B = p_i50076_2_;
        this.J_1907_R = p_i50076_3_;
        this.J_1907_R(new Slot(p_i50076_2_, 0, 0, 0){

            @Override
            public void R_4764_Y() {
                super.R_4764_Y();
                a_669_v.this.n_1700_B(this.R_4764_Y);
            }
        });
        this.n_1700_B(p_i50076_3_);
    }

    @Override
    public boolean J_1907_R(a_3913_L playerIn, int id) {
        if (id >= 100) {
            int k = id - 100;
            this.n_1700_B(0, k);
            return true;
        }
        switch (id) {
            case 1: {
                int j = this.J_1907_R.n_1700_B(0);
                this.n_1700_B(0, j - 1);
                return true;
            }
            case 2: {
                int i = this.J_1907_R.n_1700_B(0);
                this.n_1700_B(0, i + 1);
                return true;
            }
            case 3: {
                if (!playerIn.V_537_k()) {
                    return false;
                }
                Z_1993_T itemstack = this.n_1700_B.u_2550_I(0);
                this.n_1700_B.J_1907_R();
                if (!playerIn.l_1268_F.P_1922_E(itemstack)) {
                    playerIn.n_1700_B(itemstack, false);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void n_1700_B(int id, int data) {
        super.n_1700_B(id, data);
        this.M_588_G();
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return this.n_1700_B.R_4764_Y(playerIn);
    }

    public Z_1993_T n_1700_B() {
        return this.n_1700_B.s_956_w(0);
    }

    public int J_1907_R() {
        return this.J_1907_R.n_1700_B(0);
    }
}


