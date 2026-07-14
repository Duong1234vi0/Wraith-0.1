/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Projectile;
import lightning.product.BlockHitResult;
import lightning.product.H_2333_J;
import lightning.product.HitResult;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.g_4407_j;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;

public class r_214_x
extends Projectile {
    public r_214_x(t_5_h<? extends r_214_x> p_i50162_1_, b_4507_u p_i50162_2_) {
        super((t_5_h<? extends Projectile>)p_i50162_1_, p_i50162_2_);
    }

    public r_214_x(b_4507_u worldIn, g_4407_j p_i47273_2_) {
        this((t_5_h<? extends r_214_x>)t_5_h.e_2887_G, worldIn);
        super.J_1907_R(p_i47273_2_);
        this.J_1907_R(p_i47273_2_.O_3598_v() - (double)(p_i47273_2_.C_415_h() + 1.0f) * 0.5 * (double)u_530_F.n_1700_B(p_i47273_2_.C_1162_e * ((float)Math.PI / 180)), p_i47273_2_.X_2048_Y() - (double)0.1f, p_i47273_2_.l_2647_k() + (double)(p_i47273_2_.C_415_h() + 1.0f) * 0.5 * (double)u_530_F.J_1907_R(p_i47273_2_.C_1162_e * ((float)Math.PI / 180)));
    }

    public r_214_x(b_4507_u worldIn, double x, double y, double z, double p_i47274_8_, double p_i47274_10_, double p_i47274_12_) {
        this((t_5_h<? extends r_214_x>)t_5_h.e_2887_G, worldIn);
        this.J_1907_R(x, y, z);
        for (int i = 0; i < 7; ++i) {
            double d0 = 0.4 + 0.1 * (double)i;
            worldIn.n_1700_B(ParticleTypes.X_933_l, x, y, z, p_i47274_8_ * d0, p_i47274_10_, p_i47274_12_ * d0);
        }
        this.h_1847_R(p_i47274_8_, p_i47274_10_, p_i47274_12_);
    }

    @Override
    public void v_() {
        super.v_();
        e_2866_D vector3d = this.I_4348_c();
        HitResult raytraceresult = H_2333_J.n_1700_B((N_4263_v)this, this::n_1700_B);
        if (raytraceresult != null) {
            this.n_1700_B(raytraceresult);
        }
        double d0 = this.O_3598_v() + vector3d.J_1907_R;
        double d1 = this.X_2960_b() + vector3d.R_4764_Y;
        double d2 = this.l_2647_k() + vector3d.G_564_y;
        this.Y_259_p();
        float f = 0.99f;
        float f1 = 0.06f;
        if (this.O_508_d.n_1700_B(this.i_601_W()).noneMatch(q_4293_E.n_1700_B::v_4262_N)) {
            this.Ops();
        } else if (this.S_980_j()) {
            this.Ops();
        } else {
            this.v_4262_N(vector3d.n_1700_B((double)0.99f));
            if (!this.u_744_e()) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.06f, 0.0));
            }
            this.J_1907_R(d0, d1, d2);
        }
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        N_4263_v entity = this.Y_601_j();
        if (entity instanceof r_4811_B) {
            p_213868_1_.n_1700_B().n_1700_B(P_11_z.n_1700_B((N_4263_v)this, (r_4811_B)entity).R_4764_Y(), 1.0f);
        }
    }

    @Override
    protected void n_1700_B(BlockHitResult p_230299_1_) {
        super.n_1700_B(p_230299_1_);
        if (!this.O_508_d.Y_259_p) {
            this.Ops();
        }
    }

    @Override
    protected void a_() {
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}


