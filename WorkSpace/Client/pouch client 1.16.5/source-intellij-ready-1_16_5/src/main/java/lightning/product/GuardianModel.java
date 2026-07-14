/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.G_1455_B;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class GuardianModel
extends ListModel<G_1455_B> {
    private static final float[] n_1700_B = new float[]{1.75f, 0.25f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 1.25f, 0.75f, 0.0f, 0.0f};
    private static final float[] J_1907_R = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 1.75f, 1.25f, 0.75f, 0.0f, 0.0f, 0.0f, 0.0f};
    private static final float[] R_4764_Y = new float[]{0.0f, 0.0f, 0.25f, 1.75f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.75f, 1.25f};
    private static final float[] G_564_y = new float[]{0.0f, 0.0f, 8.0f, -8.0f, -8.0f, 8.0f, 8.0f, -8.0f, 0.0f, 0.0f, 8.0f, -8.0f};
    private static final float[] P_1922_E = new float[]{-8.0f, -8.0f, -8.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 8.0f, 8.0f, 8.0f, 8.0f};
    private static final float[] u_1723_Y = new float[]{8.0f, -8.0f, 0.0f, 0.0f, -8.0f, -8.0f, 8.0f, 8.0f, 8.0f, -8.0f, 0.0f, 0.0f};
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;
    private final e_4189_z[] t_148_a;
    private final e_4189_z[] s_956_w;

    public GuardianModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.t_148_a = new e_4189_z[12];
        this.v_4262_N = new e_4189_z(this);
        this.v_4262_N.n_1700_B(0, 0).n_1700_B(-6.0f, 10.0f, -8.0f, 12.0f, 12.0f, 16.0f);
        this.v_4262_N.n_1700_B(0, 28).n_1700_B(-8.0f, 10.0f, -6.0f, 2.0f, 12.0f, 12.0f);
        this.v_4262_N.n_1700_B(0, 28).n_1700_B(6.0f, 10.0f, -6.0f, 2.0f, 12.0f, 12.0f, true);
        this.v_4262_N.n_1700_B(16, 40).n_1700_B(-6.0f, 8.0f, -6.0f, 12.0f, 2.0f, 12.0f);
        this.v_4262_N.n_1700_B(16, 40).n_1700_B(-6.0f, 22.0f, -6.0f, 12.0f, 2.0f, 12.0f);
        for (int i = 0; i < this.t_148_a.length; ++i) {
            this.t_148_a[i] = new e_4189_z(this, 0, 0);
            this.t_148_a[i].n_1700_B(-1.0f, -4.5f, -1.0f, 2.0f, 9.0f, 2.0f);
            this.v_4262_N.J_1907_R(this.t_148_a[i]);
        }
        this.w_1484_f = new e_4189_z(this, 8, 0);
        this.w_1484_f.n_1700_B(-1.0f, 15.0f, 0.0f, 2.0f, 2.0f, 1.0f);
        this.v_4262_N.J_1907_R(this.w_1484_f);
        this.s_956_w = new e_4189_z[3];
        this.s_956_w[0] = new e_4189_z(this, 40, 0);
        this.s_956_w[0].n_1700_B(-2.0f, 14.0f, 7.0f, 4.0f, 4.0f, 8.0f);
        this.s_956_w[1] = new e_4189_z(this, 0, 54);
        this.s_956_w[1].n_1700_B(0.0f, 14.0f, 0.0f, 3.0f, 3.0f, 7.0f);
        this.s_956_w[2] = new e_4189_z(this);
        this.s_956_w[2].n_1700_B(41, 32).n_1700_B(0.0f, 14.0f, 0.0f, 2.0f, 2.0f, 6.0f);
        this.s_956_w[2].n_1700_B(25, 19).n_1700_B(1.0f, 10.5f, 3.0f, 1.0f, 9.0f, 9.0f);
        this.v_4262_N.J_1907_R(this.s_956_w[0]);
        this.s_956_w[0].J_1907_R(this.s_956_w[1]);
        this.s_956_w[1].J_1907_R(this.s_956_w[2]);
        this.n_1700_B(0.0f, 0.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.v_4262_N);
    }

    @Override
    public void n_1700_B(G_1455_B entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float)entityIn.RealmsWorldResetDto;
        this.v_4262_N.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.v_4262_N.u_1723_Y = headPitch * ((float)Math.PI / 180);
        float f1 = (1.0f - entityIn.H_2857_Y(f)) * 0.55f;
        this.n_1700_B(ageInTicks, f1);
        this.w_1484_f.P_1922_E = -8.25f;
        N_4263_v entity = MinecraftClient.A_4115_X().g_2268_R();
        if (entityIn.o_82_k()) {
            entity = entityIn.h_973_D();
        }
        if (entity != null) {
            e_2866_D vector3d = entity.u_2550_I(0.0f);
            e_2866_D vector3d1 = entityIn.u_2550_I(0.0f);
            double d0 = vector3d.R_4764_Y - vector3d1.R_4764_Y;
            this.w_1484_f.G_564_y = d0 > 0.0 ? 0.0f : 1.0f;
            e_2866_D vector3d2 = entityIn.t_148_a(0.0f);
            vector3d2 = new e_2866_D(vector3d2.J_1907_R, 0.0, vector3d2.G_564_y);
            e_2866_D vector3d3 = new e_2866_D(vector3d1.J_1907_R - vector3d.J_1907_R, 0.0, vector3d1.G_564_y - vector3d.G_564_y).G_564_y().J_1907_R(1.5707964f);
            double d1 = vector3d2.J_1907_R(vector3d3);
            this.w_1484_f.R_4764_Y = u_530_F.R_4764_Y((float)Math.abs(d1)) * 2.0f * (float)Math.signum(d1);
        }
        this.w_1484_f.s_956_w = true;
        float f2 = entityIn.c_3005_b(f);
        this.s_956_w[0].v_4262_N = u_530_F.n_1700_B(f2) * (float)Math.PI * 0.05f;
        this.s_956_w[1].v_4262_N = u_530_F.n_1700_B(f2) * (float)Math.PI * 0.1f;
        this.s_956_w[1].R_4764_Y = -1.5f;
        this.s_956_w[1].G_564_y = 0.5f;
        this.s_956_w[1].P_1922_E = 14.0f;
        this.s_956_w[2].v_4262_N = u_530_F.n_1700_B(f2) * (float)Math.PI * 0.15f;
        this.s_956_w[2].R_4764_Y = 0.5f;
        this.s_956_w[2].G_564_y = 0.5f;
        this.s_956_w[2].P_1922_E = 6.0f;
    }

    private void n_1700_B(float p_228261_1_, float p_228261_2_) {
        for (int i = 0; i < 12; ++i) {
            this.t_148_a[i].u_1723_Y = (float)Math.PI * n_1700_B[i];
            this.t_148_a[i].v_4262_N = (float)Math.PI * J_1907_R[i];
            this.t_148_a[i].w_1484_f = (float)Math.PI * R_4764_Y[i];
            this.t_148_a[i].R_4764_Y = G_564_y[i] * (1.0f + u_530_F.J_1907_R(p_228261_1_ * 1.5f + (float)i) * 0.01f - p_228261_2_);
            this.t_148_a[i].G_564_y = 16.0f + P_1922_E[i] * (1.0f + u_530_F.J_1907_R(p_228261_1_ * 1.5f + (float)i) * 0.01f - p_228261_2_);
            this.t_148_a[i].P_1922_E = u_1723_Y[i] * (1.0f + u_530_F.J_1907_R(p_228261_1_ * 1.5f + (float)i) * 0.01f - p_228261_2_);
        }
    }
}



