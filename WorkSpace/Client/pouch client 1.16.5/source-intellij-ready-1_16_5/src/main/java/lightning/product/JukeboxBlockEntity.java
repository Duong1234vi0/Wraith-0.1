/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Clearable;
import lightning.product.K_4074_S;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.i_2154_H;
import lightning.product.BlockEntityType;

public class JukeboxBlockEntity
extends i_2154_H
implements Clearable {
    private Z_1993_T n_1700_B = Z_1993_T.J_1907_R;

    public JukeboxBlockEntity() {
        super(BlockEntityType.P_1922_E);
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        if (nbt.R_4764_Y("RecordItem", 10)) {
            this.n_1700_B(Z_1993_T.n_1700_B(nbt.M_182_A("RecordItem")));
        }
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (!this.P_1922_E().n_1700_B()) {
            compound.n_1700_B("RecordItem", this.P_1922_E().J_1907_R(new U_2912_j()));
        }
        return compound;
    }

    public Z_1993_T P_1922_E() {
        return this.n_1700_B;
    }

    public void n_1700_B(Z_1993_T p_195535_1_) {
        this.n_1700_B = p_195535_1_;
        this.J_1907_R();
    }

    @Override
    public void C_2741_M() {
        this.n_1700_B(Z_1993_T.J_1907_R);
    }
}


