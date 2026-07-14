/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_1216_z;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.q_2896_o;
import lightning.product.s_3081_t;

public class PlayerEnderChestContainer
extends N_1216_z {
    private s_3081_t n_1700_B;

    public PlayerEnderChestContainer() {
        super(27);
    }

    public void n_1700_B(s_3081_t chestTileEntity) {
        this.n_1700_B = chestTileEntity;
    }

    @Override
    public void n_1700_B(q_2896_o p_70486_1_) {
        for (int i = 0; i < this.Y_259_p(); ++i) {
            this.J_1907_R(i, Z_1993_T.J_1907_R);
        }
        for (int k = 0; k < p_70486_1_.size(); ++k) {
            U_2912_j compoundnbt = p_70486_1_.n_1700_B(k);
            int j = compoundnbt.u_1723_Y("Slot") & 0xFF;
            if (j < 0 || j >= this.Y_259_p()) continue;
            this.J_1907_R(j, Z_1993_T.n_1700_B(compoundnbt));
        }
    }

    @Override
    public q_2896_o R_4764_Y() {
        q_2896_o listnbt = new q_2896_o();
        for (int i = 0; i < this.Y_259_p(); ++i) {
            Z_1993_T itemstack = this.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("Slot", (byte)i);
            itemstack.J_1907_R(compoundnbt);
            listnbt.add(compoundnbt);
        }
        return listnbt;
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        return this.n_1700_B != null && !this.n_1700_B.n_1700_B(player) ? false : super.R_4764_Y(player);
    }

    @Override
    public void b_(a_3913_L player) {
        if (this.n_1700_B != null) {
            this.n_1700_B.v_4262_N();
        }
        super.b_(player);
    }

    @Override
    public void J_1907_R(a_3913_L player) {
        if (this.n_1700_B != null) {
            this.n_1700_B.w_1484_f();
        }
        super.J_1907_R(player);
        this.n_1700_B = null;
    }
}


