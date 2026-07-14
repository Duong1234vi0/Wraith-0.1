/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.B_1132_Q;
import lightning.product.D_3925_G;
import lightning.product.HitResult;
import lightning.product.MobEffects;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ParticleOptions;
import lightning.product.b_4507_u;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class DragonFireball
extends D_3925_G {
    public DragonFireball(t_5_h<? extends DragonFireball> p_i50171_1_, b_4507_u p_i50171_2_) {
        super((t_5_h<? extends D_3925_G>)p_i50171_1_, p_i50171_2_);
    }

    public DragonFireball(b_4507_u worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(t_5_h.M_182_A, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    public DragonFireball(b_4507_u worldIn, r_4811_B shooter, double accelX, double accelY, double accelZ) {
        super(t_5_h.M_182_A, shooter, accelX, accelY, accelZ, worldIn);
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        N_4263_v entity = this.Y_601_j();
        if (!(result.R_4764_Y() == HitResult.n_1700_B.R_4764_Y && ((EntityHitResult)result).n_1700_B().M_182_A(entity) || this.O_508_d.Y_259_p)) {
            List<r_4811_B> list = this.O_508_d.n_1700_B(r_4811_B.class, this.i_601_W().grow(4.0, 2.0, 4.0));
            B_1132_Q areaeffectcloudentity = new B_1132_Q(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
            if (entity instanceof r_4811_B) {
                areaeffectcloudentity.n_1700_B((r_4811_B)entity);
            }
            areaeffectcloudentity.n_1700_B(ParticleTypes.t_148_a);
            areaeffectcloudentity.n_1700_B(3.0f);
            areaeffectcloudentity.J_1907_R(600);
            areaeffectcloudentity.u_1723_Y((7.0f - areaeffectcloudentity.P_1922_E()) / (float)areaeffectcloudentity.t_148_a());
            areaeffectcloudentity.n_1700_B(new k_2610_C(MobEffects.v_4262_N, 1, 1));
            if (!list.isEmpty()) {
                for (r_4811_B livingentity : list) {
                    double d0 = this.G_564_y(livingentity);
                    if (!(d0 < 16.0)) continue;
                    areaeffectcloudentity.J_1907_R(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k());
                    break;
                }
            }
            this.O_508_d.R_4764_Y(2006, this.b_2312_j(), this.y_1700_S() ? -1 : 1);
            this.O_508_d.a_(areaeffectcloudentity);
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
    protected ParticleOptions v_4262_N() {
        return ParticleTypes.t_148_a;
    }

    @Override
    protected boolean u_1723_Y() {
        return false;
    }
}


