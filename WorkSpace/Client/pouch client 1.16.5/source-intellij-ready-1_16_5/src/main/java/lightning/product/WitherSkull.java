/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.C_4114_x;
import lightning.product.D_3925_G;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.I_3700_V;
import lightning.product.HitResult;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class WitherSkull
extends D_3925_G {
    private static final h_256_u<Boolean> G_564_y = C_4114_x.n_1700_B(WitherSkull.class, EntityDataSerializers.t_148_a);

    public WitherSkull(t_5_h<? extends WitherSkull> p_i50147_1_, b_4507_u p_i50147_2_) {
        super((t_5_h<? extends D_3925_G>)p_i50147_1_, p_i50147_2_);
    }

    public WitherSkull(b_4507_u worldIn, r_4811_B shooter, double accelX, double accelY, double accelZ) {
        super(t_5_h.LongRunningTask, shooter, accelX, accelY, accelZ, worldIn);
    }

    public WitherSkull(b_4507_u worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(t_5_h.LongRunningTask, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    @Override
    protected float w_1484_f() {
        return this.P_1922_E() ? 0.73f : super.w_1484_f();
    }

    @Override
    public boolean RealmsPersistence() {
        return false;
    }

    @Override
    public float n_1700_B(F_1241_B explosionIn, BlockGetter worldIn, c_1514_x pos, K_4074_S blockStateIn, FluidState fluidState, float explosionPower) {
        return this.P_1922_E() && I_3700_V.R_4764_Y(blockStateIn) ? Math.min(0.8f, explosionPower) : explosionPower;
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        if (!this.O_508_d.Y_259_p) {
            boolean flag;
            N_4263_v entity = p_213868_1_.n_1700_B();
            N_4263_v entity1 = this.Y_601_j();
            if (entity1 instanceof r_4811_B) {
                r_4811_B livingentity = (r_4811_B)entity1;
                flag = entity.n_1700_B(P_11_z.n_1700_B(this, (N_4263_v)livingentity), 8.0f);
                if (flag) {
                    if (entity.RealmsLongRunningMcoTaskScreen()) {
                        this.n_1700_B(livingentity, entity);
                    } else {
                        livingentity.n_1700_B(5.0f);
                    }
                }
            } else {
                flag = entity.n_1700_B(P_11_z.Q_4569_t, 5.0f);
            }
            if (flag && entity instanceof r_4811_B) {
                int i = 0;
                if (this.O_508_d.x_607_J() == R_2450_T.R_4764_Y) {
                    i = 10;
                } else if (this.O_508_d.x_607_J() == R_2450_T.G_564_y) {
                    i = 40;
                }
                if (i > 0) {
                    ((r_4811_B)entity).n_1700_B(new k_2610_C(MobEffects.Y_601_j, 20 * i, 1));
                }
            }
        }
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        if (!this.O_508_d.Y_259_p) {
            F_1241_B.n_1700_B explosion$mode = this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R) ? F_1241_B.n_1700_B.R_4764_Y : F_1241_B.n_1700_B.n_1700_B;
            this.O_508_d.n_1700_B(this, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), 1.0f, false, explosion$mode);
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

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(G_564_y, false);
    }

    public boolean P_1922_E() {
        return this.l_4537_E.n_1700_B(G_564_y);
    }

    public void n_1700_B(boolean invulnerable) {
        this.l_4537_E.J_1907_R(G_564_y, invulnerable);
    }

    @Override
    protected boolean u_1723_Y() {
        return false;
    }
}


