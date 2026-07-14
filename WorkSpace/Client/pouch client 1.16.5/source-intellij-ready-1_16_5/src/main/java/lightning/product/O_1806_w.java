/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.A_4313_D;
import lightning.product.D_4792_h;
import lightning.product.FormattedText;
import lightning.product.I_2909_y;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.StandingSignBlock;
import lightning.product.T_1808_R;
import lightning.product.T_2910_P;
import lightning.product.T_2915_h;
import lightning.product.Y_4083_F;
import lightning.product.WoodType;
import lightning.product.b_4440_Q;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FormattedCharSequence;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.i_2518_W;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.shaders.Shaders;

public class O_1806_w
extends l_1802_R<A_4313_D> {
    private final n_1700_B n_1700_B = new n_1700_B();
    private static double J_1907_R = 4096.0;

    public O_1806_w(f_2689_h rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void n_1700_B(A_4313_D tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        K_4074_S blockstate = tileEntityIn.e_4240_b();
        matrixStackIn.n_1700_B();
        float f = 0.6666667f;
        if (blockstate.J_1907_R() instanceof StandingSignBlock) {
            matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
            float f1 = -((float)(blockstate.R_4764_Y(StandingSignBlock.Q_4569_t) * 360) / 16.0f);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1));
            this.n_1700_B.J_1907_R.s_956_w = true;
        } else {
            matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
            float f4 = -blockstate.R_4764_Y(T_1808_R.Q_4569_t).Q_4569_t();
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f4));
            matrixStackIn.n_1700_B(0.0, -0.3125, -0.4375);
            this.n_1700_B.J_1907_R.s_956_w = false;
        }
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.6666667f, -0.6666667f, -0.6666667f);
        T_2910_P rendermaterial = O_1806_w.n_1700_B(blockstate.J_1907_R());
        D_4792_h ivertexbuilder = rendermaterial.n_1700_B(bufferIn, this.n_1700_B::getRenderType);
        this.n_1700_B.n_1700_B.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        this.n_1700_B.J_1907_R.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.J_1907_R();
        if (O_1806_w.n_1700_B(tileEntityIn)) {
            Y_4083_F fontrenderer = this.v_4262_N.n_1700_B();
            float f2 = 0.010416667f;
            matrixStackIn.n_1700_B(0.0, 0.3333333432674408, 0.046666666865348816);
            matrixStackIn.n_1700_B(0.010416667f, -0.010416667f, 0.010416667f);
            int i = tileEntityIn.w_1484_f().v_4262_N();
            if (Config.isCustomColors()) {
                i = CustomColors.getSignTextColor(i);
            }
            double d0 = 0.4;
            int j = (int)((double)i_2518_W.J_1907_R(i) * 0.4);
            int k = (int)((double)i_2518_W.R_4764_Y(i) * 0.4);
            int l = (int)((double)i_2518_W.G_564_y(i) * 0.4);
            int i1 = i_2518_W.n_1700_B(0, l, k, j);
            int j1 = 20;
            for (int k1 = 0; k1 < 4; ++k1) {
                FormattedCharSequence ireorderingprocessor = tileEntityIn.n_1700_B(k1, p_lambda$render$0_1_ -> {
                    List<FormattedCharSequence> list = fontrenderer.J_1907_R((FormattedText)p_lambda$render$0_1_, 90);
                    return list.isEmpty() ? FormattedCharSequence.n_1700_B : list.get(0);
                });
                if (ireorderingprocessor == null) continue;
                float f3 = -fontrenderer.n_1700_B(ireorderingprocessor) / 2;
                fontrenderer.n_1700_B(ireorderingprocessor, f3, (float)(k1 * 10 - 20), i1, false, matrixStackIn.R_4764_Y().n_1700_B(), bufferIn, false, 0, combinedLightIn);
            }
        }
        matrixStackIn.J_1907_R();
    }

    public static T_2910_P n_1700_B(T_2915_h blockIn) {
        WoodType woodtype = blockIn instanceof I_2909_y ? ((I_2909_y)blockIn).J_1907_R() : WoodType.n_1700_B;
        return b_4440_Q.t_148_a.get(woodtype);
    }

    private static boolean n_1700_B(A_4313_D p_isRenderText_0_) {
        if (Shaders.isShadowPass) {
            return false;
        }
        if (!Config.zoomMode) {
            c_1514_x blockpos = p_isRenderText_0_.x_607_J();
            N_4263_v entity = MinecraftClient.A_4115_X().g_2268_R();
            double d0 = entity.v_4262_N(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            if (d0 > J_1907_R) {
                return false;
            }
        }
        return true;
    }

    public static void n_1700_B() {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        double d0 = Config.limit(minecraft.P_4830_p.R_3077_Z, 1.0, 120.0);
        double d1 = Math.max(1.5 * (double)minecraft.RealmsServerPing().h_1847_R() / d0, 16.0);
        J_1907_R = d1 * d1;
    }

    public static final class n_1700_B
    extends v_3569_v {
        public final e_4189_z n_1700_B = new e_4189_z(64, 32, 0, 0);
        public final e_4189_z J_1907_R;

        public n_1700_B() {
            super(o_2576_A::G_564_y);
            this.n_1700_B.n_1700_B(-12.0f, -14.0f, -1.0f, 24.0f, 12.0f, 2.0f, 0.0f);
            this.J_1907_R = new e_4189_z(64, 32, 0, 14);
            this.J_1907_R.n_1700_B(-1.0f, -2.0f, -1.0f, 2.0f, 14.0f, 2.0f, 0.0f);
        }

        @Override
        public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
            this.n_1700_B.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        }
    }
}



