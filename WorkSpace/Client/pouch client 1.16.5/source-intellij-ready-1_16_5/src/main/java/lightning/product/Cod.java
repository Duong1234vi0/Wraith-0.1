/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_11_z;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.AbstractSchoolingFish;
import lightning.product.Items;
import lightning.product.t_5_h;

public class Cod
extends AbstractSchoolingFish {
    public Cod(t_5_h<? extends Cod> p_i50279_1_, b_4507_u p_i50279_2_) {
        super((t_5_h<? extends AbstractSchoolingFish>)p_i50279_1_, p_i50279_2_);
    }

    @Override
    protected Z_1993_T y_4642_Y() {
        return new Z_1993_T(Items.W_3801_h);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.j_306_t;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.F_3572_x;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.P_5000_x;
    }

    @Override
    protected SoundEvent V_1176_p() {
        return SoundEvents.L_1362_X;
    }
}


