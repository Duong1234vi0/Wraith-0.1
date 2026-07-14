/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.AgableMob;
import lightning.product.N_4263_v;
import lightning.product.U_2534_D;
import lightning.product.AgeableListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class HorseModel<T extends U_2534_D>
extends AgeableListModel<T> {
    protected final e_4189_z n_1700_B;
    protected final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;
    private final e_4189_z t_148_a;
    private final e_4189_z s_956_w;
    private final e_4189_z u_2550_I;
    private final e_4189_z[] M_588_G;
    private final e_4189_z[] P_4830_p;

    public HorseModel(float p_i51065_1_) {
        super(true, 16.2f, 1.36f, 2.7272f, 2.0f, 20.0f);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 0, 32);
        this.n_1700_B.n_1700_B(-5.0f, -8.0f, -17.0f, 10.0f, 10.0f, 22.0f, 0.05f);
        this.n_1700_B.n_1700_B(0.0f, 11.0f, 5.0f);
        this.J_1907_R = new e_4189_z(this, 0, 35);
        this.J_1907_R.n_1700_B(-2.05f, -6.0f, -2.0f, 4.0f, 12.0f, 7.0f);
        this.J_1907_R.u_1723_Y = 0.5235988f;
        e_4189_z modelrenderer = new e_4189_z(this, 0, 13);
        modelrenderer.n_1700_B(-3.0f, -11.0f, -2.0f, 6.0f, 5.0f, 7.0f, p_i51065_1_);
        e_4189_z modelrenderer1 = new e_4189_z(this, 56, 36);
        modelrenderer1.n_1700_B(-1.0f, -11.0f, 5.01f, 2.0f, 16.0f, 2.0f, p_i51065_1_);
        e_4189_z modelrenderer2 = new e_4189_z(this, 0, 25);
        modelrenderer2.n_1700_B(-2.0f, -11.0f, -7.0f, 4.0f, 5.0f, 5.0f, p_i51065_1_);
        this.J_1907_R.J_1907_R(modelrenderer);
        this.J_1907_R.J_1907_R(modelrenderer1);
        this.J_1907_R.J_1907_R(modelrenderer2);
        this.n_1700_B(this.J_1907_R);
        this.R_4764_Y = new e_4189_z(this, 48, 21);
        this.R_4764_Y.t_148_a = true;
        this.R_4764_Y.n_1700_B(-3.0f, -1.01f, -1.0f, 4.0f, 11.0f, 4.0f, p_i51065_1_);
        this.R_4764_Y.n_1700_B(4.0f, 14.0f, 7.0f);
        this.G_564_y = new e_4189_z(this, 48, 21);
        this.G_564_y.n_1700_B(-1.0f, -1.01f, -1.0f, 4.0f, 11.0f, 4.0f, p_i51065_1_);
        this.G_564_y.n_1700_B(-4.0f, 14.0f, 7.0f);
        this.P_1922_E = new e_4189_z(this, 48, 21);
        this.P_1922_E.t_148_a = true;
        this.P_1922_E.n_1700_B(-3.0f, -1.01f, -1.9f, 4.0f, 11.0f, 4.0f, p_i51065_1_);
        this.P_1922_E.n_1700_B(4.0f, 6.0f, -12.0f);
        this.u_1723_Y = new e_4189_z(this, 48, 21);
        this.u_1723_Y.n_1700_B(-1.0f, -1.01f, -1.9f, 4.0f, 11.0f, 4.0f, p_i51065_1_);
        this.u_1723_Y.n_1700_B(-4.0f, 6.0f, -12.0f);
        float f = 5.5f;
        this.v_4262_N = new e_4189_z(this, 48, 21);
        this.v_4262_N.t_148_a = true;
        this.v_4262_N.n_1700_B(-3.0f, -1.01f, -1.0f, 4.0f, 11.0f, 4.0f, p_i51065_1_, p_i51065_1_ + 5.5f, p_i51065_1_);
        this.v_4262_N.n_1700_B(4.0f, 14.0f, 7.0f);
        this.w_1484_f = new e_4189_z(this, 48, 21);
        this.w_1484_f.n_1700_B(-1.0f, -1.01f, -1.0f, 4.0f, 11.0f, 4.0f, p_i51065_1_, p_i51065_1_ + 5.5f, p_i51065_1_);
        this.w_1484_f.n_1700_B(-4.0f, 14.0f, 7.0f);
        this.t_148_a = new e_4189_z(this, 48, 21);
        this.t_148_a.t_148_a = true;
        this.t_148_a.n_1700_B(-3.0f, -1.01f, -1.9f, 4.0f, 11.0f, 4.0f, p_i51065_1_, p_i51065_1_ + 5.5f, p_i51065_1_);
        this.t_148_a.n_1700_B(4.0f, 6.0f, -12.0f);
        this.s_956_w = new e_4189_z(this, 48, 21);
        this.s_956_w.n_1700_B(-1.0f, -1.01f, -1.9f, 4.0f, 11.0f, 4.0f, p_i51065_1_, p_i51065_1_ + 5.5f, p_i51065_1_);
        this.s_956_w.n_1700_B(-4.0f, 6.0f, -12.0f);
        this.u_2550_I = new e_4189_z(this, 42, 36);
        this.u_2550_I.n_1700_B(-1.5f, 0.0f, 0.0f, 3.0f, 14.0f, 4.0f, p_i51065_1_);
        this.u_2550_I.n_1700_B(0.0f, -5.0f, 2.0f);
        this.u_2550_I.u_1723_Y = 0.5235988f;
        this.n_1700_B.J_1907_R(this.u_2550_I);
        e_4189_z modelrenderer3 = new e_4189_z(this, 26, 0);
        modelrenderer3.n_1700_B(-5.0f, -8.0f, -9.0f, 10.0f, 9.0f, 9.0f, 0.5f);
        this.n_1700_B.J_1907_R(modelrenderer3);
        e_4189_z modelrenderer4 = new e_4189_z(this, 29, 5);
        modelrenderer4.n_1700_B(2.0f, -9.0f, -6.0f, 1.0f, 2.0f, 2.0f, p_i51065_1_);
        this.J_1907_R.J_1907_R(modelrenderer4);
        e_4189_z modelrenderer5 = new e_4189_z(this, 29, 5);
        modelrenderer5.n_1700_B(-3.0f, -9.0f, -6.0f, 1.0f, 2.0f, 2.0f, p_i51065_1_);
        this.J_1907_R.J_1907_R(modelrenderer5);
        e_4189_z modelrenderer6 = new e_4189_z(this, 32, 2);
        modelrenderer6.n_1700_B(3.1f, -6.0f, -8.0f, 0.0f, 3.0f, 16.0f, p_i51065_1_);
        modelrenderer6.u_1723_Y = -0.5235988f;
        this.J_1907_R.J_1907_R(modelrenderer6);
        e_4189_z modelrenderer7 = new e_4189_z(this, 32, 2);
        modelrenderer7.n_1700_B(-3.1f, -6.0f, -8.0f, 0.0f, 3.0f, 16.0f, p_i51065_1_);
        modelrenderer7.u_1723_Y = -0.5235988f;
        this.J_1907_R.J_1907_R(modelrenderer7);
        e_4189_z modelrenderer8 = new e_4189_z(this, 1, 1);
        modelrenderer8.n_1700_B(-3.0f, -11.0f, -1.9f, 6.0f, 5.0f, 6.0f, 0.2f);
        this.J_1907_R.J_1907_R(modelrenderer8);
        e_4189_z modelrenderer9 = new e_4189_z(this, 19, 0);
        modelrenderer9.n_1700_B(-2.0f, -11.0f, -4.0f, 4.0f, 5.0f, 2.0f, 0.2f);
        this.J_1907_R.J_1907_R(modelrenderer9);
        this.M_588_G = new e_4189_z[]{modelrenderer3, modelrenderer4, modelrenderer5, modelrenderer8, modelrenderer9};
        this.P_4830_p = new e_4189_z[]{modelrenderer6, modelrenderer7};
    }

    protected void n_1700_B(e_4189_z p_199047_1_) {
        e_4189_z modelrenderer = new e_4189_z(this, 19, 16);
        modelrenderer.n_1700_B(0.55f, -13.0f, 4.0f, 2.0f, 3.0f, 1.0f, -0.001f);
        e_4189_z modelrenderer1 = new e_4189_z(this, 19, 16);
        modelrenderer1.n_1700_B(-2.55f, -13.0f, 4.0f, 2.0f, 3.0f, 1.0f, -0.001f);
        p_199047_1_.J_1907_R(modelrenderer);
        p_199047_1_.J_1907_R(modelrenderer1);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = ((U_2534_D)entityIn).G_564_y();
        boolean flag1 = ((N_4263_v)entityIn).H_1883_T();
        for (e_4189_z modelrenderer : this.M_588_G) {
            modelrenderer.s_956_w = flag;
        }
        for (e_4189_z modelrenderer1 : this.P_4830_p) {
            modelrenderer1.s_956_w = flag1 && flag;
        }
        this.n_1700_B.G_564_y = 11.0f;
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.J_1907_R);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.t_148_a, (Object)this.s_956_w);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
        float f = u_530_F.t_148_a(((U_2534_D)entityIn).D_4361_a, ((U_2534_D)entityIn).C_1162_e, partialTick);
        float f1 = u_530_F.t_148_a(((U_2534_D)entityIn).JsonUtils, ((U_2534_D)entityIn).f_3449_S, partialTick);
        float f2 = u_530_F.v_4262_N(partialTick, ((U_2534_D)entityIn).UploadStatus, ((U_2534_D)entityIn).f_4016_n);
        float f3 = f1 - f;
        float f4 = f2 * ((float)Math.PI / 180);
        if (f3 > 20.0f) {
            f3 = 20.0f;
        }
        if (f3 < -20.0f) {
            f3 = -20.0f;
        }
        if (limbSwingAmount > 0.2f) {
            f4 += u_530_F.J_1907_R(limbSwing * 0.4f) * 0.15f * limbSwingAmount;
        }
        float f5 = ((U_2534_D)entityIn).c_3005_b(partialTick);
        float f6 = ((U_2534_D)entityIn).H_2857_Y(partialTick);
        float f7 = 1.0f - f6;
        float f8 = ((U_2534_D)entityIn).A_4115_X(partialTick);
        boolean flag = ((U_2534_D)entityIn).h_1847_R != 0;
        float f9 = (float)((U_2534_D)entityIn).RealmsWorldResetDto + partialTick;
        this.J_1907_R.G_564_y = 4.0f;
        this.J_1907_R.P_1922_E = -12.0f;
        this.n_1700_B.u_1723_Y = 0.0f;
        this.J_1907_R.u_1723_Y = 0.5235988f + f4;
        this.J_1907_R.v_4262_N = f3 * ((float)Math.PI / 180);
        float f10 = ((N_4263_v)entityIn).RowButton() ? 0.2f : 1.0f;
        float f11 = u_530_F.J_1907_R(f10 * limbSwing * 0.6662f + (float)Math.PI);
        float f12 = f11 * 0.8f * limbSwingAmount;
        float f13 = (1.0f - Math.max(f6, f5)) * (0.5235988f + f4 + f8 * u_530_F.n_1700_B(f9) * 0.05f);
        this.J_1907_R.u_1723_Y = f6 * (0.2617994f + f4) + f5 * (2.1816616f + u_530_F.n_1700_B(f9) * 0.05f) + f13;
        this.J_1907_R.v_4262_N = f6 * f3 * ((float)Math.PI / 180) + (1.0f - Math.max(f6, f5)) * this.J_1907_R.v_4262_N;
        this.J_1907_R.G_564_y = f6 * -4.0f + f5 * 11.0f + (1.0f - Math.max(f6, f5)) * this.J_1907_R.G_564_y;
        this.J_1907_R.P_1922_E = f6 * -4.0f + f5 * -12.0f + (1.0f - Math.max(f6, f5)) * this.J_1907_R.P_1922_E;
        this.n_1700_B.u_1723_Y = f6 * -0.7853982f + f7 * this.n_1700_B.u_1723_Y;
        float f14 = 0.2617994f * f6;
        float f15 = u_530_F.J_1907_R(f9 * 0.6f + (float)Math.PI);
        this.P_1922_E.G_564_y = 2.0f * f6 + 14.0f * f7;
        this.P_1922_E.P_1922_E = -6.0f * f6 - 10.0f * f7;
        this.u_1723_Y.G_564_y = this.P_1922_E.G_564_y;
        this.u_1723_Y.P_1922_E = this.P_1922_E.P_1922_E;
        float f16 = (-1.0471976f + f15) * f6 + f12 * f7;
        float f17 = (-1.0471976f - f15) * f6 - f12 * f7;
        this.R_4764_Y.u_1723_Y = f14 - f11 * 0.5f * limbSwingAmount * f7;
        this.G_564_y.u_1723_Y = f14 + f11 * 0.5f * limbSwingAmount * f7;
        this.P_1922_E.u_1723_Y = f16;
        this.u_1723_Y.u_1723_Y = f17;
        this.u_2550_I.u_1723_Y = 0.5235988f + limbSwingAmount * 0.75f;
        this.u_2550_I.G_564_y = -5.0f + limbSwingAmount;
        this.u_2550_I.P_1922_E = 2.0f + limbSwingAmount * 2.0f;
        this.u_2550_I.v_4262_N = flag ? u_530_F.J_1907_R(f9 * 0.7f) : 0.0f;
        this.v_4262_N.G_564_y = this.R_4764_Y.G_564_y;
        this.v_4262_N.P_1922_E = this.R_4764_Y.P_1922_E;
        this.v_4262_N.u_1723_Y = this.R_4764_Y.u_1723_Y;
        this.w_1484_f.G_564_y = this.G_564_y.G_564_y;
        this.w_1484_f.P_1922_E = this.G_564_y.P_1922_E;
        this.w_1484_f.u_1723_Y = this.G_564_y.u_1723_Y;
        this.t_148_a.G_564_y = this.P_1922_E.G_564_y;
        this.t_148_a.P_1922_E = this.P_1922_E.P_1922_E;
        this.t_148_a.u_1723_Y = this.P_1922_E.u_1723_Y;
        this.s_956_w.G_564_y = this.u_1723_Y.G_564_y;
        this.s_956_w.P_1922_E = this.u_1723_Y.P_1922_E;
        this.s_956_w.u_1723_Y = this.u_1723_Y.u_1723_Y;
        boolean flag1 = ((AgableMob)entityIn).d_();
        this.R_4764_Y.s_956_w = !flag1;
        this.G_564_y.s_956_w = !flag1;
        this.P_1922_E.s_956_w = !flag1;
        this.u_1723_Y.s_956_w = !flag1;
        this.v_4262_N.s_956_w = flag1;
        this.w_1484_f.s_956_w = flag1;
        this.t_148_a.s_956_w = flag1;
        this.s_956_w.s_956_w = flag1;
        this.n_1700_B.G_564_y = flag1 ? 10.8f : 0.0f;
    }
}


