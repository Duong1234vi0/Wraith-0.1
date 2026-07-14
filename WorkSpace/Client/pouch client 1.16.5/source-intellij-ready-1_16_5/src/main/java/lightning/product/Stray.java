/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.Arrow;
import lightning.product.MobEffects;
import lightning.product.P_11_z;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.AbstractSkeleton;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.h_384_L;
import lightning.product.k_2610_C;
import lightning.product.t_5_h;

public class Stray
extends AbstractSkeleton {
    public Stray(t_5_h<? extends Stray> p_i50191_1_, b_4507_u p_i50191_2_) {
        super((t_5_h<? extends AbstractSkeleton>)p_i50191_1_, p_i50191_2_);
    }

    public static boolean n_1700_B(t_5_h<Stray> p_223327_0_, ServerLevelAccessor p_223327_1_, a_3160_D reason, c_1514_x p_223327_3_, Random p_223327_4_) {
        return Stray.J_1907_R(p_223327_0_, p_223327_1_, reason, p_223327_3_, p_223327_4_) && (reason == a_3160_D.R_4764_Y || p_223327_1_.canSeeSky(p_223327_3_));
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.CropBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.Y_2905_A;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.CryingObsidianBlock;
    }

    @Override
    SoundEvent y_4642_Y() {
        return SoundEvents.DeadBushBlock;
    }

    @Override
    protected h_384_L J_1907_R(Z_1993_T arrowStack, float distanceFactor) {
        h_384_L abstractarrowentity = super.J_1907_R(arrowStack, distanceFactor);
        if (abstractarrowentity instanceof Arrow) {
            ((Arrow)abstractarrowentity).n_1700_B(new k_2610_C(MobEffects.J_1907_R, 600));
        }
        return abstractarrowentity;
    }
}


