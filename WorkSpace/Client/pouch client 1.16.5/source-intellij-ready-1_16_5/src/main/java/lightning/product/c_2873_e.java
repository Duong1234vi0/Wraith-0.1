/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.PlayerModel;
import lightning.product.ParrotRenderer;
import lightning.product.N_4263_v;
import lightning.product.P_2855_e;
import lightning.product.U_2912_j;
import lightning.product.X_4340_E;
import lightning.product.Z_3224_L;
import lightning.product.a_3913_L;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.ShoulderRidingEntity;
import lightning.product.o_3091_w;
import lightning.product.t_5_h;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public class c_2873_e<T extends a_3913_L>
extends RenderLayer<T, PlayerModel<T>> {
    private final P_2855_e n_1700_B = new P_2855_e();

    public c_2873_e(j_4203_m<T, PlayerModel<T>> rendererIn) {
        super(rendererIn);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, netHeadYaw, headPitch, true);
        this.n_1700_B(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, netHeadYaw, headPitch, false);
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch, boolean leftShoulderIn) {
        U_2912_j compoundnbt = leftShoulderIn ? ((a_3913_L)entitylivingbaseIn).A_1306_N() : ((a_3913_L)entitylivingbaseIn).D_3612_q();
        t_5_h.n_1700_B(compoundnbt.M_588_G("id")).filter(p_lambda$renderParrot$0_0_ -> p_lambda$renderParrot$0_0_ == t_5_h.O_508_d).ifPresent(p_lambda$renderParrot$1_11_ -> {
            N_4263_v entity = Config.getRenderGlobal().R_4764_Y;
            if (entitylivingbaseIn instanceof X_4340_E) {
                ShoulderRidingEntity entity1;
                X_4340_E abstractclientplayerentity = (X_4340_E)entitylivingbaseIn;
                ShoulderRidingEntity l_3580_k2 = entity1 = leftShoulderIn ? abstractclientplayerentity.Z_976_R : abstractclientplayerentity.H_1990_U;
                if (entity1 != null) {
                    Config.getRenderGlobal().R_4764_Y = entity1;
                    if (Config.isShaders()) {
                        Shaders.nextEntity(entity1);
                    }
                }
            }
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(leftShoulderIn ? (double)0.4f : (double)-0.4f, entitylivingbaseIn.Z_875_P() ? (double)-1.3f : -1.5, 0.0);
            D_4792_h ivertexbuilder = bufferIn.getBuffer(this.n_1700_B.getRenderType(ParrotRenderer.n_1700_B[compoundnbt.w_1484_f("Variant")]));
            this.n_1700_B.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, limbSwing, limbSwingAmount, netHeadYaw, headPitch, entitylivingbaseIn.RealmsWorldResetDto);
            matrixStackIn.J_1907_R();
            Config.getRenderGlobal().R_4764_Y = entity;
            if (Config.isShaders()) {
                Shaders.nextEntity(entity);
            }
        });
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (a_3913_L)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


