/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AgableMob;
import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.MinecraftClient;
import lightning.product.e_3977_C;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.s_4023_U;
import lightning.product.u_530_F;
import lightning.product.w_3245_r;
import net.optifine.Config;

public class N_4235_V<T extends s_4023_U>
extends RenderLayer<T, w_3245_r<T>> {
    private e_4189_z n_1700_B;
    private static final g_2336_b J_1907_R = new g_2336_b("textures/entity/cow/red_mushroom.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("textures/entity/cow/brown_mushroom.png");
    private static boolean G_564_y = false;
    private static boolean P_1922_E = false;

    public N_4235_V(j_4203_m<T, w_3245_r<T>> rendererIn) {
        super(rendererIn);
        j_4203_m<T, w_3245_r<T>> mooshroomrenderer = rendererIn;
        this.n_1700_B = new e_4189_z(mooshroomrenderer.n_1700_B());
        this.n_1700_B.J_1907_R(16, 16);
        this.n_1700_B.R_4764_Y = 8.0f;
        this.n_1700_B.P_1922_E = 8.0f;
        this.n_1700_B.v_4262_N = u_530_F.J_1907_R / 4.0f;
        int[][] aint = new int[][]{null, null, {16, 16, 0, 0}, {16, 16, 0, 0}, null, null};
        this.n_1700_B.n_1700_B(aint, -10.0f, 0.0f, 0.0f, 20.0f, 16.0f, 0.0f, 0.0f);
        int[][] aint1 = new int[][]{null, null, null, null, {16, 16, 0, 0}, {16, 16, 0, 0}};
        this.n_1700_B.n_1700_B(aint1, 0.0f, 0.0f, -10.0f, 0.0f, 16.0f, 20.0f, 0.0f);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!((AgableMob)entitylivingbaseIn).d_() && !((N_4263_v)entitylivingbaseIn).F_3572_x()) {
            e_3977_C blockrendererdispatcher = MinecraftClient.A_4115_X().z_1333_t();
            K_4074_S blockstate = ((s_4023_U)entitylivingbaseIn).h_1640_b().n_1700_B();
            g_2336_b resourcelocation = this.n_1700_B(blockstate);
            D_4792_h ivertexbuilder = null;
            if (resourcelocation != null) {
                ivertexbuilder = bufferIn.getBuffer(o_2576_A.R_4764_Y(resourcelocation));
            }
            int i = o_4479_Q.R_4764_Y(entitylivingbaseIn, 0.0f);
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B((double)0.2f, (double)-0.35f, 0.5);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-48.0f));
            matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
            matrixStackIn.n_1700_B(-0.5, -0.5, -0.5);
            if (resourcelocation != null) {
                this.n_1700_B.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
            } else {
                blockrendererdispatcher.n_1700_B(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            }
            matrixStackIn.J_1907_R();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B((double)0.2f, (double)-0.35f, 0.5);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(42.0f));
            matrixStackIn.n_1700_B((double)0.1f, 0.0, (double)-0.6f);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-48.0f));
            matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
            matrixStackIn.n_1700_B(-0.5, -0.5, -0.5);
            if (resourcelocation != null) {
                this.n_1700_B.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
            } else {
                blockrendererdispatcher.n_1700_B(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            }
            matrixStackIn.J_1907_R();
            matrixStackIn.n_1700_B();
            ((w_3245_r)this.getEntityModel()).R_4764_Y().n_1700_B(matrixStackIn);
            matrixStackIn.n_1700_B(0.0, (double)-0.7f, (double)-0.2f);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-78.0f));
            matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
            matrixStackIn.n_1700_B(-0.5, -0.5, -0.5);
            if (resourcelocation != null) {
                this.n_1700_B.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
            } else {
                blockrendererdispatcher.n_1700_B(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            }
            matrixStackIn.J_1907_R();
        }
    }

    private g_2336_b n_1700_B(K_4074_S p_getCustomMushroom_1_) {
        T_2915_h block = p_getCustomMushroom_1_.J_1907_R();
        if (block == a_3742_W.RealmsPersistence && G_564_y) {
            return J_1907_R;
        }
        return block == a_3742_W.JsonUtils && P_1922_E ? R_4764_Y : null;
    }

    public static void n_1700_B() {
        G_564_y = Config.hasResource(J_1907_R);
        P_1922_E = Config.hasResource(R_4764_Y);
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (s_4023_U)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



