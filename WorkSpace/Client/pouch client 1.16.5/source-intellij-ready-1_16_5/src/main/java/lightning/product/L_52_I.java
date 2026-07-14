/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.E_4346_v;
import lightning.product.RenderLayer;
import lightning.product.PlayerModel;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.X_4340_E;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.e_1174_E;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.Cosmetics;
import net.optifine.Config;

public class L_52_I
extends RenderLayer<X_4340_E, PlayerModel<X_4340_E>> {
    public L_52_I(j_4203_m<X_4340_E, PlayerModel<X_4340_E>> playerModelIn) {
        super(playerModelIn);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, X_4340_E entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack;
        Cosmetics cosmetics = (Cosmetics)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Cosmetics.class);
        if (cosmetics != null && cosmetics.n_1700_B(entitylivingbaseIn)) {
            return;
        }
        if (entitylivingbaseIn.T_2506_i() && !entitylivingbaseIn.F_3572_x() && entitylivingbaseIn.n_1700_B(E_4346_v.n_1700_B) && entitylivingbaseIn.e_2887_G() != null && (itemstack = entitylivingbaseIn.J_1907_R(e_1174_E.P_1922_E)).J_1907_R() != Items.NyliumBlock) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, 0.0, 0.125);
            double d0 = u_530_F.G_564_y((double)partialTicks, entitylivingbaseIn.d_3244_b, entitylivingbaseIn.r_2478_U) - u_530_F.G_564_y((double)partialTicks, entitylivingbaseIn.r_715_M, entitylivingbaseIn.O_3598_v());
            double d1 = u_530_F.G_564_y((double)partialTicks, entitylivingbaseIn.v_887_r, entitylivingbaseIn.h_2848_I) - u_530_F.G_564_y((double)partialTicks, entitylivingbaseIn.A_1038_p, entitylivingbaseIn.X_2960_b());
            double d2 = u_530_F.G_564_y((double)partialTicks, entitylivingbaseIn.l_3609_d, entitylivingbaseIn.A_3244_K) - u_530_F.G_564_y((double)partialTicks, entitylivingbaseIn.i_1637_u, entitylivingbaseIn.l_2647_k());
            float f = entitylivingbaseIn.D_4361_a + (entitylivingbaseIn.C_1162_e - entitylivingbaseIn.D_4361_a);
            double d3 = u_530_F.n_1700_B(f * ((float)Math.PI / 180));
            double d4 = -u_530_F.J_1907_R(f * ((float)Math.PI / 180));
            float f1 = (float)d1 * 10.0f;
            f1 = u_530_F.n_1700_B(f1, -6.0f, 32.0f);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0f;
            f2 = u_530_F.n_1700_B(f2, 0.0f, 150.0f);
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0f;
            f3 = u_530_F.n_1700_B(f3, -20.0f, 20.0f);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            if (f2 > 165.0f) {
                f2 = 165.0f;
            }
            if (f1 < -5.0f) {
                f1 = -5.0f;
            }
            float f4 = u_530_F.v_4262_N(partialTicks, entitylivingbaseIn.X_290_I, entitylivingbaseIn.O_1795_e);
            f1 += u_530_F.n_1700_B(u_530_F.v_4262_N(partialTicks, entitylivingbaseIn.V_1446_Y, entitylivingbaseIn.PlayerInfo) * 6.0f) * 32.0f * f4;
            if (entitylivingbaseIn.Z_875_P()) {
                f1 += 25.0f;
            }
            float f5 = Config.getAverageFrameTimeSec() * 20.0f;
            f5 = Config.limit(f5, 0.02f, 1.0f);
            entitylivingbaseIn.N_2525_X = u_530_F.v_4262_N(f5, entitylivingbaseIn.N_2525_X, 6.0f + f2 / 2.0f + f1);
            entitylivingbaseIn.g_2268_R = u_530_F.v_4262_N(f5, entitylivingbaseIn.g_2268_R, f3 / 2.0f);
            entitylivingbaseIn.c_4037_x = u_530_F.v_4262_N(f5, entitylivingbaseIn.c_4037_x, 180.0f - f3 / 2.0f);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(entitylivingbaseIn.N_2525_X));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(entitylivingbaseIn.g_2268_R));
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(entitylivingbaseIn.c_4037_x));
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.J_1907_R(entitylivingbaseIn.e_2887_G()));
            ((PlayerModel)this.getEntityModel()).J_1907_R(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B);
            matrixStackIn.J_1907_R();
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (X_4340_E)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



