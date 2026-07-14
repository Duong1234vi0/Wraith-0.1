/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.F_1241_B;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.b_4507_u;
import lightning.product.Fireball;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class LargeFireball
extends Fireball {
    public int G_564_y = 1;

    public LargeFireball(t_5_h<? extends LargeFireball> p_i50163_1_, b_4507_u p_i50163_2_) {
        super((t_5_h<? extends Fireball>)p_i50163_1_, p_i50163_2_);
    }

    public LargeFireball(b_4507_u worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super((t_5_h<? extends Fireball>)t_5_h.T_2506_i, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    public LargeFireball(b_4507_u worldIn, r_4811_B shooter, double accelX, double accelY, double accelZ) {
        super((t_5_h<? extends Fireball>)t_5_h.T_2506_i, shooter, accelX, accelY, accelZ, worldIn);
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        if (!this.O_508_d.Y_259_p) {
            boolean flag = this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R);
            this.O_508_d.n_1700_B((N_4263_v)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.G_564_y, flag, flag ? F_1241_B.n_1700_B.R_4764_Y : F_1241_B.n_1700_B.n_1700_B);
            this.Ops();
        }
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        if (!this.O_508_d.Y_259_p) {
            N_4263_v entity = p_213868_1_.n_1700_B();
            N_4263_v entity1 = this.Y_601_j();
            entity.n_1700_B(P_11_z.n_1700_B(this, entity1), 6.0f);
            if (entity1 instanceof r_4811_B) {
                this.n_1700_B((r_4811_B)entity1, entity);
            }
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("ExplosionPower", this.G_564_y);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("ExplosionPower", 99)) {
            this.G_564_y = compound.w_1484_f("ExplosionPower");
        }
    }
}


