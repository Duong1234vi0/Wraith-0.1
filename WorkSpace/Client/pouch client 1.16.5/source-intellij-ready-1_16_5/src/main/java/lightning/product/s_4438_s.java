/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BaseFireBlock;
import lightning.product.Z_530_i;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Fireball;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class s_4438_s
extends Fireball {
    public s_4438_s(t_5_h<? extends s_4438_s> p_i50160_1_, b_4507_u p_i50160_2_) {
        super((t_5_h<? extends Fireball>)p_i50160_1_, p_i50160_2_);
    }

    public s_4438_s(b_4507_u worldIn, r_4811_B shooter, double accelX, double accelY, double accelZ) {
        super((t_5_h<? extends Fireball>)t_5_h.U_1241_n, shooter, accelX, accelY, accelZ, worldIn);
    }

    public s_4438_s(b_4507_u worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super((t_5_h<? extends Fireball>)t_5_h.U_1241_n, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        N_4263_v entity;
        super.n_1700_B(p_213868_1_);
        if (!this.O_508_d.Y_259_p && !(entity = p_213868_1_.n_1700_B()).r_3651_U()) {
            N_4263_v entity1 = this.Y_601_j();
            int i = entity.w_612_n();
            entity.P_1922_E(5);
            boolean flag = entity.n_1700_B(P_11_z.n_1700_B(this, entity1), 5.0f);
            if (!flag) {
                entity.u_1723_Y(i);
            } else if (entity1 instanceof r_4811_B) {
                this.n_1700_B((r_4811_B)entity1, entity);
            }
        }
    }

    @Override
    protected void n_1700_B(BlockHitResult p_230299_1_) {
        c_1514_x blockpos;
        N_4263_v entity;
        super.n_1700_B(p_230299_1_);
        if (!this.O_508_d.Y_259_p && ((entity = this.Y_601_j()) == null || !(entity instanceof Z_530_i) || this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) && this.O_508_d.u_1723_Y(blockpos = p_230299_1_.n_1700_B().offset(p_230299_1_.J_1907_R()))) {
            this.O_508_d.J_1907_R(blockpos, BaseFireBlock.n_1700_B(this.O_508_d, blockpos));
        }
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        if (!this.O_508_d.Y_259_p) {
            this.Ops();
        }
    }

    @Override
    public boolean C_290_v() {
        return false;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return false;
    }
}


