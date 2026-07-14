/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_2149_k;
import lightning.product.HitResult;
import lightning.product.N_3869_i;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ParticleOptions;
import lightning.product.ThrowableItemProjectile;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class Snowball
extends ThrowableItemProjectile {
    public Snowball(t_5_h<? extends Snowball> p_i50159_1_, b_4507_u p_i50159_2_) {
        super((t_5_h<? extends ThrowableItemProjectile>)p_i50159_1_, p_i50159_2_);
    }

    public Snowball(b_4507_u worldIn, r_4811_B throwerIn) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.dtoRealmsServerAddress, throwerIn, worldIn);
    }

    public Snowball(b_4507_u worldIn, double x, double y, double z) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.dtoRealmsServerAddress, x, y, z, worldIn);
    }

    @Override
    protected q_1613_l P_1922_E() {
        return Items.i_770_g;
    }

    private ParticleOptions w_1484_f() {
        Z_1993_T itemstack = this.v_4262_N();
        return itemstack.n_1700_B() ? ParticleTypes.v_4276_D : new N_3869_i(ParticleTypes.d_2427_y, itemstack);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 3) {
            ParticleOptions iparticledata = this.w_1484_f();
            for (int i = 0; i < 8; ++i) {
                this.O_508_d.n_1700_B(iparticledata, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        N_4263_v entity = p_213868_1_.n_1700_B();
        int i = entity instanceof G_2149_k ? 3 : 0;
        entity.n_1700_B(P_11_z.J_1907_R(this, this.Y_601_j()), (float)i);
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        if (!this.O_508_d.Y_259_p) {
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)3);
            this.Ops();
        }
    }
}


