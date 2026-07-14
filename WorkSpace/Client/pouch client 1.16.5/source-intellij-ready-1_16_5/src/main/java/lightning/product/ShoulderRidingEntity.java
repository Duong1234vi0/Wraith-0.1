/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.C_3622_I;
import lightning.product.U_2912_j;
import lightning.product.b_4507_u;
import lightning.product.t_5_h;

public abstract class ShoulderRidingEntity
extends C_3622_I {
    private int h_1847_R;

    protected ShoulderRidingEntity(t_5_h<? extends ShoulderRidingEntity> type, b_4507_u worldIn) {
        super((t_5_h<? extends C_3622_I>)type, worldIn);
    }

    public boolean G_564_y(B_4088_l p_213439_1_) {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("id", this.RealmsLongConfirmationScreen());
        this.P_1922_E(compoundnbt);
        if (p_213439_1_.v_4262_N(compoundnbt)) {
            this.Ops();
            return true;
        }
        return false;
    }

    @Override
    public void v_() {
        ++this.h_1847_R;
        super.v_();
    }

    public boolean J_3635_s() {
        return this.h_1847_R > 100;
    }
}


