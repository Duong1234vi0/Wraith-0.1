/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.AgableMob;
import lightning.product.P_11_z;
import lightning.product.SoundEvents;
import lightning.product.W_3443_Y;
import lightning.product.SoundEvent;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.t_5_h;

public class Mule
extends W_3443_Y {
    public Mule(t_5_h<? extends Mule> p_i50236_1_, b_4507_u p_i50236_2_) {
        super((t_5_h<? extends W_3443_Y>)p_i50236_1_, p_i50236_2_);
    }

    @Override
    protected SoundEvent z_4693_k() {
        super.z_4693_k();
        return SoundEvents.HighJump;
    }

    @Override
    protected SoundEvent KeyBindSetting() {
        super.KeyBindSetting();
        return SoundEvents.Jesus;
    }

    @Override
    protected SoundEvent u_796_y() {
        super.u_796_y();
        return SoundEvents.NoFall;
    }

    @Override
    @Nullable
    protected SoundEvent Setting() {
        return SoundEvents.NoJumpDelay;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        super.P_1922_E(damageSourceIn);
        return SoundEvents.NoPush;
    }

    @Override
    protected void J_3635_s() {
        this.n_1700_B(SoundEvents.MoveHelper, 1.0f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
    }

    @Override
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.T_3594_S.n_1700_B(p_241840_1_);
    }
}



