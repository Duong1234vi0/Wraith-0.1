/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;

public abstract class Projectile
extends N_4263_v {
    private UUID n_1700_B;
    private int J_1907_R;
    private boolean R_4764_Y;

    Projectile(t_5_h<? extends Projectile> p_i231584_1_, b_4507_u p_i231584_2_) {
        super(p_i231584_1_, p_i231584_2_);
    }

    public void J_1907_R(@Nullable N_4263_v entityIn) {
        if (entityIn != null) {
            this.n_1700_B = entityIn.w_2705_t();
            this.J_1907_R = entityIn.j_276_v();
        }
    }

    @Nullable
    public N_4263_v Y_601_j() {
        if (this.n_1700_B != null && this.O_508_d instanceof e_3591_l) {
            return ((e_3591_l)this.O_508_d).J_1907_R(this.n_1700_B);
        }
        return this.J_1907_R != 0 ? this.O_508_d.J_1907_R(this.J_1907_R) : null;
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        if (this.n_1700_B != null) {
            compound.n_1700_B("Owner", this.n_1700_B);
        }
        if (this.R_4764_Y) {
            compound.n_1700_B("LeftOwner", true);
        }
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        if (compound.J_1907_R("Owner")) {
            this.n_1700_B = compound.n_1700_B("Owner");
        }
        this.R_4764_Y = compound.t_1786_h("LeftOwner");
    }

    @Override
    public void v_() {
        if (!this.R_4764_Y) {
            this.R_4764_Y = this.P_1922_E();
        }
        super.v_();
    }

    private boolean P_1922_E() {
        N_4263_v entity = this.Y_601_j();
        if (entity != null) {
            for (N_4263_v entity1 : this.O_508_d.J_1907_R((N_4263_v)this, this.i_601_W().expand(this.I_4348_c()).grow(1.0), p_234613_0_ -> !p_234613_0_.d_2461_k() && p_234613_0_.C_290_v())) {
                if (entity1.d_3244_b() != entity.d_3244_b()) continue;
                return false;
            }
        }
        return true;
    }

    public void R_4764_Y(double x, double y, double z, float velocity, float inaccuracy) {
        e_2866_D vector3d = new e_2866_D(x, y, z).G_564_y().J_1907_R(this.RealmsWorldOptions.nextGaussian() * (double)0.0075f * (double)inaccuracy, this.RealmsWorldOptions.nextGaussian() * (double)0.0075f * (double)inaccuracy, this.RealmsWorldOptions.nextGaussian() * (double)0.0075f * (double)inaccuracy).n_1700_B((double)velocity);
        this.v_4262_N(vector3d);
        float f = u_530_F.n_1700_B(Projectile.R_4764_Y(vector3d));
        this.p_178_J = (float)(u_530_F.G_564_y(vector3d.J_1907_R, vector3d.G_564_y) * 57.2957763671875);
        this.f_4016_n = (float)(u_530_F.G_564_y(vector3d.R_4764_Y, (double)f) * 57.2957763671875);
        this.j_276_v = this.p_178_J;
        this.UploadStatus = this.f_4016_n;
    }

    public void n_1700_B(N_4263_v p_234612_1_, float p_234612_2_, float p_234612_3_, float p_234612_4_, float p_234612_5_, float p_234612_6_) {
        float f = -u_530_F.n_1700_B(p_234612_3_ * ((float)Math.PI / 180)) * u_530_F.J_1907_R(p_234612_2_ * ((float)Math.PI / 180));
        float f1 = -u_530_F.n_1700_B((p_234612_2_ + p_234612_4_) * ((float)Math.PI / 180));
        float f2 = u_530_F.J_1907_R(p_234612_3_ * ((float)Math.PI / 180)) * u_530_F.J_1907_R(p_234612_2_ * ((float)Math.PI / 180));
        this.R_4764_Y(f, f1, f2, p_234612_5_, p_234612_6_);
        e_2866_D vector3d = p_234612_1_.I_4348_c();
        this.v_4262_N(this.I_4348_c().J_1907_R(vector3d.J_1907_R, p_234612_1_.M_1641_O() ? 0.0 : vector3d.R_4764_Y, vector3d.G_564_y));
    }

    protected void n_1700_B(HitResult result) {
        HitResult.n_1700_B raytraceresult$type = result.R_4764_Y();
        if (raytraceresult$type == HitResult.n_1700_B.R_4764_Y) {
            this.n_1700_B((EntityHitResult)result);
        } else if (raytraceresult$type == HitResult.n_1700_B.J_1907_R) {
            this.n_1700_B((BlockHitResult)result);
        }
    }

    protected void n_1700_B(EntityHitResult p_213868_1_) {
    }

    protected void n_1700_B(BlockHitResult p_230299_1_) {
        K_4074_S blockstate = this.O_508_d.getBlockState(p_230299_1_.n_1700_B());
        blockstate.n_1700_B(this.O_508_d, blockstate, p_230299_1_, this);
    }

    @Override
    public void s_956_w(double x, double y, double z) {
        this.h_1847_R(x, y, z);
        if (this.UploadStatus == 0.0f && this.j_276_v == 0.0f) {
            float f = u_530_F.n_1700_B(x * x + z * z);
            this.f_4016_n = (float)(u_530_F.G_564_y(y, (double)f) * 57.2957763671875);
            this.p_178_J = (float)(u_530_F.G_564_y(x, z) * 57.2957763671875);
            this.UploadStatus = this.f_4016_n;
            this.j_276_v = this.p_178_J;
            this.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, this.f_4016_n);
        }
    }

    protected boolean n_1700_B(N_4263_v p_230298_1_) {
        if (!p_230298_1_.d_2461_k() && p_230298_1_.RealmsLongRunningMcoTaskScreen() && p_230298_1_.C_290_v()) {
            N_4263_v entity = this.Y_601_j();
            return entity == null || this.R_4764_Y || !entity.Y_259_p(p_230298_1_);
        }
        return false;
    }

    protected void Y_259_p() {
        e_2866_D vector3d = this.I_4348_c();
        float f = u_530_F.n_1700_B(Projectile.R_4764_Y(vector3d));
        this.f_4016_n = Projectile.n_1700_B(this.UploadStatus, (float)(u_530_F.G_564_y(vector3d.R_4764_Y, (double)f) * 57.2957763671875));
        this.p_178_J = Projectile.n_1700_B(this.j_276_v, (float)(u_530_F.G_564_y(vector3d.J_1907_R, vector3d.G_564_y) * 57.2957763671875));
    }

    protected static float n_1700_B(float p_234614_0_, float p_234614_1_) {
        while (p_234614_1_ - p_234614_0_ < -180.0f) {
            p_234614_0_ -= 360.0f;
        }
        while (p_234614_1_ - p_234614_0_ >= 180.0f) {
            p_234614_0_ += 360.0f;
        }
        return u_530_F.v_4262_N(0.2f, p_234614_0_, p_234614_1_);
    }
}


