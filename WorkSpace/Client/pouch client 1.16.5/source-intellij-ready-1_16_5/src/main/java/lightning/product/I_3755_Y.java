/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_69_b;
import lightning.product.PlayerModel;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.AbstractPiglin;
import lightning.product.e_4189_z;
import lightning.product.AnimationUtils;
import lightning.product.q_2464_b;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public class I_3755_Y<T extends Z_530_i>
extends PlayerModel<T> {
    public final e_4189_z M_588_G;
    public final e_4189_z P_4830_p;
    private final e_4189_z Q_2552_b;
    private final e_4189_z C_2741_M;
    private final e_4189_z k_2293_S;
    private final e_4189_z q_2307_F;

    public I_3755_Y(float p_i232336_1_, int p_i232336_2_, int p_i232336_3_) {
        super(p_i232336_1_, false);
        this.textureWidth = p_i232336_2_;
        this.textureHeight = p_i232336_3_;
        this.R_4764_Y = new e_4189_z(this, 16, 16);
        this.R_4764_Y.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, p_i232336_1_);
        this.n_1700_B = new e_4189_z(this);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-5.0f, -8.0f, -4.0f, 10.0f, 8.0f, 8.0f, p_i232336_1_);
        this.n_1700_B.n_1700_B(31, 1).n_1700_B(-2.0f, -4.0f, -5.0f, 4.0f, 4.0f, 1.0f, p_i232336_1_);
        this.n_1700_B.n_1700_B(2, 4).n_1700_B(2.0f, -2.0f, -5.0f, 1.0f, 2.0f, 1.0f, p_i232336_1_);
        this.n_1700_B.n_1700_B(2, 0).n_1700_B(-3.0f, -2.0f, -5.0f, 1.0f, 2.0f, 1.0f, p_i232336_1_);
        this.M_588_G = new e_4189_z(this);
        this.M_588_G.n_1700_B(4.5f, -6.0f, 0.0f);
        this.M_588_G.n_1700_B(51, 6).n_1700_B(0.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f, p_i232336_1_);
        this.n_1700_B.J_1907_R(this.M_588_G);
        this.P_4830_p = new e_4189_z(this);
        this.P_4830_p.n_1700_B(-4.5f, -6.0f, 0.0f);
        this.P_4830_p.n_1700_B(39, 6).n_1700_B(-1.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f, p_i232336_1_);
        this.n_1700_B.J_1907_R(this.P_4830_p);
        this.J_1907_R = new e_4189_z(this);
        this.Q_2552_b = this.R_4764_Y.n_1700_B();
        this.C_2741_M = this.n_1700_B.n_1700_B();
        this.k_2293_S = this.P_1922_E.n_1700_B();
        this.q_2307_F = this.P_1922_E.n_1700_B();
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.R_4764_Y.n_1700_B(this.Q_2552_b);
        this.n_1700_B.n_1700_B(this.C_2741_M);
        this.P_1922_E.n_1700_B(this.k_2293_S);
        this.G_564_y.n_1700_B(this.q_2307_F);
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float f = 0.5235988f;
        float f1 = ageInTicks * 0.1f + limbSwing * 0.5f;
        float f2 = 0.08f + limbSwingAmount * 0.4f;
        this.M_588_G.w_1484_f = -0.5235988f - u_530_F.J_1907_R(f1 * 1.2f) * f2;
        this.P_4830_p.w_1484_f = 0.5235988f + u_530_F.J_1907_R(f1) * f2;
        if (entityIn instanceof AbstractPiglin) {
            AbstractPiglin abstractpiglinentity = (AbstractPiglin)entityIn;
            q_2464_b piglinaction = abstractpiglinentity.J_3635_s();
            if (piglinaction == q_2464_b.P_1922_E) {
                float f3 = ageInTicks / 60.0f;
                this.P_4830_p.w_1484_f = 0.5235988f + (float)Math.PI / 180 * u_530_F.n_1700_B(f3 * 30.0f) * 10.0f;
                this.M_588_G.w_1484_f = -0.5235988f - (float)Math.PI / 180 * u_530_F.J_1907_R(f3 * 30.0f) * 10.0f;
                this.n_1700_B.R_4764_Y = u_530_F.n_1700_B(f3 * 10.0f);
                this.n_1700_B.G_564_y = u_530_F.n_1700_B(f3 * 40.0f) + 0.4f;
                this.G_564_y.w_1484_f = (float)Math.PI / 180 * (70.0f + u_530_F.J_1907_R(f3 * 40.0f) * 10.0f);
                this.P_1922_E.w_1484_f = this.G_564_y.w_1484_f * -1.0f;
                this.G_564_y.G_564_y = u_530_F.n_1700_B(f3 * 40.0f) * 0.5f + 1.5f;
                this.P_1922_E.G_564_y = u_530_F.n_1700_B(f3 * 40.0f) * 0.5f + 1.5f;
                this.R_4764_Y.G_564_y = u_530_F.n_1700_B(f3 * 40.0f) * 0.35f;
            } else if (piglinaction == q_2464_b.n_1700_B && this.h_1847_R == 0.0f) {
                this.n_1700_B(entityIn);
            } else if (piglinaction == q_2464_b.J_1907_R) {
                AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, this.n_1700_B, !((Z_530_i)entityIn).r_4414_L());
            } else if (piglinaction == q_2464_b.R_4764_Y) {
                AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, entityIn, !((Z_530_i)entityIn).r_4414_L());
            } else if (piglinaction == q_2464_b.G_564_y) {
                this.n_1700_B.u_1723_Y = 0.5f;
                this.n_1700_B.v_4262_N = 0.0f;
                if (((Z_530_i)entityIn).r_4414_L()) {
                    this.G_564_y.v_4262_N = -0.5f;
                    this.G_564_y.u_1723_Y = -0.9f;
                } else {
                    this.P_1922_E.v_4262_N = 0.5f;
                    this.P_1922_E.u_1723_Y = -0.9f;
                }
            }
        } else if (((N_4263_v)entityIn).f_4016_n() == t_5_h.c_132_F) {
            AnimationUtils.n_1700_B(this.P_1922_E, this.G_564_y, ((Z_530_i)entityIn).P_2272_O(), this.h_1847_R, ageInTicks);
        }
        this.w_1457_N.n_1700_B(this.v_4262_N);
        this.Y_601_j.n_1700_B(this.u_1723_Y);
        this.t_1786_h.n_1700_B(this.P_1922_E);
        this.multiplayerClientSuggestionProvider.n_1700_B(this.G_564_y);
        this.Y_259_p.n_1700_B(this.R_4764_Y);
        this.J_1907_R.n_1700_B(this.n_1700_B);
    }

    @Override
    protected void n_1700_B(T p_230486_1_, float p_230486_2_) {
        if (this.h_1847_R > 0.0f && p_230486_1_ instanceof A_69_b && ((A_69_b)p_230486_1_).J_3635_s() == q_2464_b.n_1700_B) {
            AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, p_230486_1_, this.h_1847_R, p_230486_2_);
        } else {
            super.n_1700_B(p_230486_1_, p_230486_2_);
        }
    }

    private void n_1700_B(T p_239117_1_) {
        if (((Z_530_i)p_239117_1_).r_4414_L()) {
            this.P_1922_E.u_1723_Y = -1.8f;
        } else {
            this.G_564_y.u_1723_Y = -1.8f;
        }
    }
}


