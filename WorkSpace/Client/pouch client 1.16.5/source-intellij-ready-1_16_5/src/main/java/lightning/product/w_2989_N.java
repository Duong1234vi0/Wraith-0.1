/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.B_4088_l;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ThrowableItemProjectile;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.v_887_r;
import lightning.product.EntityHitResult;
import lightning.product.Endermite;

public class w_2989_N
extends ThrowableItemProjectile {
    public w_2989_N(t_5_h<? extends w_2989_N> p_i50153_1_, b_4507_u world) {
        super((t_5_h<? extends ThrowableItemProjectile>)p_i50153_1_, world);
    }

    public w_2989_N(b_4507_u worldIn, r_4811_B throwerIn) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.RegionPingResult, throwerIn, worldIn);
    }

    public w_2989_N(b_4507_u worldIn, double x, double y, double z) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.RegionPingResult, x, y, z, worldIn);
    }

    @Override
    protected q_1613_l P_1922_E() {
        return Items.v_2746_S;
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        p_213868_1_.n_1700_B().n_1700_B(P_11_z.J_1907_R(this, this.Y_601_j()), 0.0f);
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        N_4263_v entity = this.Y_601_j();
        A_4115_X.n_1700_B(new v_887_r(v_887_r.n_1700_B.R_4764_Y));
        for (int i = 0; i < 32; ++i) {
            this.O_508_d.n_1700_B(ParticleTypes.g_221_o, this.O_3598_v(), this.X_2960_b() + this.RealmsWorldOptions.nextDouble() * 2.0, this.l_2647_k(), this.RealmsWorldOptions.nextGaussian(), 0.0, this.RealmsWorldOptions.nextGaussian());
        }
        if (!this.O_508_d.Y_259_p && !this.t_4219_U) {
            if (entity instanceof B_4088_l) {
                B_4088_l serverplayerentity = (B_4088_l)entity;
                if (serverplayerentity.n_1700_B.getNetworkManager().u_1723_Y() && serverplayerentity.O_508_d == this.O_508_d && !serverplayerentity.z_2372_L()) {
                    if (this.RealmsWorldOptions.nextFloat() < 0.05f && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.G_564_y)) {
                        Endermite endermiteentity = t_5_h.Q_2552_b.n_1700_B(this.O_508_d);
                        endermiteentity.w_1457_N(true);
                        endermiteentity.J_1907_R(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), entity.p_178_J, entity.f_4016_n);
                        this.O_508_d.a_(endermiteentity);
                    }
                    if (entity.y_2772_m()) {
                        entity.A_3959_N();
                    }
                    entity.P_4830_p(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
                    entity.U_1241_n = 0.0f;
                    entity.n_1700_B(P_11_z.u_2550_I, 5.0f);
                }
            } else if (entity != null) {
                entity.P_4830_p(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
                entity.U_1241_n = 0.0f;
            }
            this.Ops();
        }
    }

    @Override
    public void v_() {
        N_4263_v entity = this.Y_601_j();
        if (entity instanceof a_3913_L && !entity.RealmsLongRunningMcoTaskScreen()) {
            this.Ops();
        } else {
            super.v_();
        }
    }

    @Override
    @Nullable
    public N_4263_v n_1700_B(e_3591_l server) {
        N_4263_v entity = this.Y_601_j();
        if (entity != null && entity.O_508_d.g_2268_R() != server.g_2268_R()) {
            this.J_1907_R((N_4263_v)null);
        }
        return super.n_1700_B(server);
    }
}


