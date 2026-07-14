/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public abstract class W_4144_J<T extends N_4263_v, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    public W_4144_J(j_4203_m<T, M> p_i226039_1_) {
        super(p_i226039_1_);
    }

    @Override
    public void render(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        D_4792_h ivertexbuilder = bufferIn.getBuffer(this.n_1700_B());
        if (Config.isShaders()) {
            Shaders.beginSpiderEyes();
        }
        Config.getRenderGlobal().v_4262_N = true;
        ((v_3569_v)this.getEntityModel()).render(matrixStackIn, ivertexbuilder, 0xF00000, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        Config.getRenderGlobal().v_4262_N = false;
        if (Config.isShaders()) {
            Shaders.endSpiderEyes();
        }
    }

    public abstract o_2576_A n_1700_B();
}


