/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.SpellcasterIllager;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.IllagerRenderer;
import lightning.product.o_3091_w;
import lightning.product.w_2040_b;
import lightning.product.IllagerModel;
import lightning.product.x_4904_Z;

public class EvokerRenderer<T extends SpellcasterIllager>
extends IllagerRenderer<T> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/illager/evoker.png");

    public EvokerRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new IllagerModel(0.0f, 0.0f, 64, 64), 0.5f);
        this.n_1700_B(new x_4904_Z<T, IllagerModel<T>>(this, this){

            @Override
            public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (((SpellcasterIllager)entitylivingbaseIn).ModuleCategory()) {
                    super.n_1700_B(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
                }
            }

            @Override
            public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
                this.n_1700_B(g_221_o2, o_3091_w2, n, (Object)((SpellcasterIllager)n_4263_v), f, f2, f3, f4, f5, f6);
            }
        });
    }

    @Override
    public g_2336_b n_1700_B(T entity) {
        return n_1700_B;
    }
}



