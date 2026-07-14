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

public class Salmon
extends AbstractSchoolingFish {
    public Salmon(t_5_h<? extends Salmon> p_i50246_1_, b_4507_u p_i50246_2_) {
        super((t_5_h<? extends AbstractSchoolingFish>)p_i50246_1_, p_i50246_2_);
    }

    @Override
    public int y_2447_C() {
        return 5;
    }

    @Override
    protected Z_1993_T y_4642_Y() {
        return new Z_1993_T(Items.j_1376_w);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.D_4237_z;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.d_3769_f;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.z_936_s;
    }

    @Override
    protected SoundEvent V_1176_p() {
        return SoundEvents.n_4560_z;
    }
}


