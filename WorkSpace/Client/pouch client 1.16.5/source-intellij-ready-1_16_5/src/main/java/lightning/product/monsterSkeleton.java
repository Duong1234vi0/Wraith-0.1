/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.AbstractSkeleton;
import lightning.product.b_3485_j;
import lightning.product.b_4507_u;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.t_5_h;

public class monsterSkeleton
extends AbstractSkeleton {
    public monsterSkeleton(t_5_h<? extends monsterSkeleton> p_i50194_1_, b_4507_u p_i50194_2_) {
        super((t_5_h<? extends AbstractSkeleton>)p_i50194_1_, p_i50194_2_);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.b_3334_n;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.TickTrigger;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.u_3578_p;
    }

    @Override
    SoundEvent y_4642_Y() {
        return SoundEvents.e_1503_j;
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        b_3485_j creeperentity;
        super.n_1700_B(source, looting, recentlyHitIn);
        N_4263_v entity = source.u_2550_I();
        if (entity instanceof b_3485_j && (creeperentity = (b_3485_j)entity).J_3635_s()) {
            creeperentity.o_82_k();
            this.n_1700_B((q_1803_e)Items.DragonEggBlock);
        }
    }
}


