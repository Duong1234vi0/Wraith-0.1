/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.W_1247_f;
import lightning.product.Z_1993_T;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3270_j;
import lightning.product.k_4231_L;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class b_3746_y
extends Z_2049_e<W_1247_f> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/fishing_hook.png");
    private static final o_2576_A v_4262_N = o_2576_A.R_4764_Y(n_1700_B);

    public b_3746_y(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(W_1247_f entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        a_3913_L playerentity;
        N_4263_v hooked = entityIn.w_1484_f();
        if (hooked != null && hooked == MinecraftClient.A_4115_X().Y_259_p) {
            h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.v_4262_N);
            A_4115_X.n_1700_B(event);
            if (event.n_1700_B()) {
                return;
            }
        }
        if ((playerentity = entityIn.v_4262_N()) != null) {
            float f3;
            double d6;
            double d5;
            double d4;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
            matrixStackIn.n_1700_B(this.J_1907_R.R_4764_Y());
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
            g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
            D_1098_v matrix4f = matrixstack$entry.n_1700_B();
            o_1290_k matrix3f = matrixstack$entry.J_1907_R();
            D_4792_h ivertexbuilder = bufferIn.getBuffer(v_4262_N);
            b_3746_y.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0f, 0, 0, 1);
            b_3746_y.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0f, 0, 1, 1);
            b_3746_y.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0f, 1, 1, 0);
            b_3746_y.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0f, 1, 0, 0);
            matrixStackIn.J_1907_R();
            int i = playerentity.d_2169_p() == k_4231_L.J_1907_R ? 1 : -1;
            Z_1993_T itemstack = playerentity.A_2714_y();
            if (itemstack.J_1907_R() != Items.w_2223_C) {
                i = -i;
            }
            float f = playerentity.Y_601_j(partialTicks);
            float f1 = u_530_F.n_1700_B(u_530_F.R_4764_Y(f) * (float)Math.PI);
            float f2 = u_530_F.v_4262_N(partialTicks, playerentity.D_4361_a, playerentity.C_1162_e) * ((float)Math.PI / 180);
            double d0 = u_530_F.n_1700_B(f2);
            double d1 = u_530_F.J_1907_R(f2);
            double d2 = (double)i * 0.35;
            double d3 = 0.8;
            if ((this.J_1907_R.G_564_y == null || this.J_1907_R.G_564_y.P_4830_p().n_1700_B()) && playerentity == MinecraftClient.A_4115_X().Y_259_p) {
                double d7 = this.J_1907_R.G_564_y.R_3077_Z;
                e_2866_D vector3d = new e_2866_D((double)i * -0.36 * (d7 /= 100.0), -0.045 * d7, 0.4);
                vector3d = vector3d.n_1700_B(-u_530_F.v_4262_N(partialTicks, playerentity.UploadStatus, playerentity.f_4016_n) * ((float)Math.PI / 180));
                vector3d = vector3d.J_1907_R(-u_530_F.v_4262_N(partialTicks, playerentity.j_276_v, playerentity.p_178_J) * ((float)Math.PI / 180));
                vector3d = vector3d.J_1907_R(f1 * 0.5f);
                vector3d = vector3d.n_1700_B(-f1 * 0.7f);
                d4 = u_530_F.G_564_y((double)partialTicks, playerentity.r_715_M, playerentity.O_3598_v()) + vector3d.J_1907_R;
                d5 = u_530_F.G_564_y((double)partialTicks, playerentity.A_1038_p, playerentity.X_2960_b()) + vector3d.R_4764_Y;
                d6 = u_530_F.G_564_y((double)partialTicks, playerentity.i_1637_u, playerentity.l_2647_k()) + vector3d.G_564_y;
                f3 = playerentity.X_1313_W();
            } else {
                d4 = u_530_F.G_564_y((double)partialTicks, playerentity.r_715_M, playerentity.O_3598_v()) - d1 * d2 - d0 * 0.8;
                d5 = playerentity.A_1038_p + (double)playerentity.X_1313_W() + (playerentity.X_2960_b() - playerentity.A_1038_p) * (double)partialTicks - 0.45;
                d6 = u_530_F.G_564_y((double)partialTicks, playerentity.i_1637_u, playerentity.l_2647_k()) - d0 * d2 + d1 * 0.8;
                f3 = playerentity.Z_875_P() ? -0.1875f : 0.0f;
            }
            double d9 = u_530_F.G_564_y((double)partialTicks, entityIn.r_715_M, entityIn.O_3598_v());
            double d10 = u_530_F.G_564_y((double)partialTicks, entityIn.A_1038_p, entityIn.X_2960_b()) + 0.25;
            double d8 = u_530_F.G_564_y((double)partialTicks, entityIn.i_1637_u, entityIn.l_2647_k());
            float f4 = (float)(d4 - d9);
            float f5 = (float)(d5 - d10) + f3;
            float f6 = (float)(d6 - d8);
            D_4792_h ivertexbuilder1 = bufferIn.getBuffer(o_2576_A.C_2741_M());
            D_1098_v matrix4f1 = matrixStackIn.R_4764_Y().n_1700_B();
            int j = 16;
            for (int k = 0; k < 16; ++k) {
                b_3746_y.n_1700_B(f4, f5, f6, ivertexbuilder1, matrix4f1, b_3746_y.n_1700_B(k, 16));
                b_3746_y.n_1700_B(f4, f5, f6, ivertexbuilder1, matrix4f1, b_3746_y.n_1700_B(k + 1, 16));
            }
            matrixStackIn.J_1907_R();
            super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    private static float n_1700_B(int p_229105_0_, int p_229105_1_) {
        return (float)p_229105_0_ / (float)p_229105_1_;
    }

    private static void n_1700_B(D_4792_h p_229106_0_, D_1098_v p_229106_1_, o_1290_k p_229106_2_, int p_229106_3_, float p_229106_4_, int p_229106_5_, int p_229106_6_, int p_229106_7_) {
        p_229106_0_.n_1700_B(p_229106_1_, p_229106_4_ - 0.5f, (float)p_229106_5_ - 0.5f, 0.0f).color(255, 255, 255, 255).tex(p_229106_6_, p_229106_7_).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229106_3_).n_1700_B(p_229106_2_, 0.0f, 1.0f, 0.0f).endVertex();
    }

    private static void n_1700_B(float p_229104_0_, float p_229104_1_, float p_229104_2_, D_4792_h p_229104_3_, D_1098_v p_229104_4_, float p_229104_5_) {
        p_229104_3_.n_1700_B(p_229104_4_, p_229104_0_ * p_229104_5_, p_229104_1_ * (p_229104_5_ * p_229104_5_ + p_229104_5_) * 0.5f + 0.25f, p_229104_2_ * p_229104_5_).color(0, 0, 0, 255).endVertex();
    }

    @Override
    public g_2336_b n_1700_B(W_1247_f entity) {
        return n_1700_B;
    }
}



