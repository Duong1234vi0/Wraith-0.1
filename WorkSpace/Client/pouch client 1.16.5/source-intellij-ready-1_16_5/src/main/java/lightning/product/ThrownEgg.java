/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.HitResult;
import lightning.product.N_3869_i;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ThrowableItemProjectile;
import lightning.product.X_4861_v;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class ThrownEgg
extends ThrowableItemProjectile {
    public ThrownEgg(t_5_h<? extends ThrownEgg> p_i50154_1_, b_4507_u p_i50154_2_) {
        super((t_5_h<? extends ThrowableItemProjectile>)p_i50154_1_, p_i50154_2_);
    }

    public ThrownEgg(b_4507_u worldIn, r_4811_B throwerIn) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.RealmsWorldResetDto, throwerIn, worldIn);
    }

    public ThrownEgg(b_4507_u worldIn, double x, double y, double z) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.RealmsWorldResetDto, x, y, z, worldIn);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 3) {
            double d0 = 0.08;
            for (int i = 0; i < 8; ++i) {
                this.O_508_d.n_1700_B(new N_3869_i(ParticleTypes.d_2427_y, this.n_1700_B()), this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.08, ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.08, ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.08);
            }
        }
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        p_213868_1_.n_1700_B().n_1700_B(P_11_z.J_1907_R(this, this.Y_601_j()), 0.0f);
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        if (!this.O_508_d.Y_259_p) {
            if (this.RealmsWorldOptions.nextInt(8) == 0) {
                int i = 1;
                if (this.RealmsWorldOptions.nextInt(32) == 0) {
                    i = 4;
                }
                for (int j = 0; j < i; ++j) {
                    X_4861_v chickenentity = t_5_h.s_956_w.n_1700_B(this.O_508_d);
                    chickenentity.b_(-24000);
                    chickenentity.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, 0.0f);
                    this.O_508_d.a_(chickenentity);
                }
            }
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)3);
            this.Ops();
        }
    }

    @Override
    protected q_1613_l P_1922_E() {
        return Items.s_4405_m;
    }
}


