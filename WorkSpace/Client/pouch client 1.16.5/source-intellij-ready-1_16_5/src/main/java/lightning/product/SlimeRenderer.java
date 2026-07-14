/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_268_Q;
import lightning.product.SlimeModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.y_663_y;

public class SlimeRenderer
extends r_1334_c<A_268_Q, SlimeModel<A_268_Q>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/slime/slime.png");

    public SlimeRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new SlimeModel(16), 0.25f);
        this.n_1700_B(new y_663_y<A_268_Q>(this));
    }

    @Override
    public void n_1700_B(A_268_Q entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        this.R_4764_Y = 0.25f * (float)entityIn.o_82_k();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected void n_1700_B(A_268_Q entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 0.999f;
        matrixStackIn.n_1700_B(0.999f, 0.999f, 0.999f);
        matrixStackIn.n_1700_B(0.0, (double)0.001f, 0.0);
        float f1 = entitylivingbaseIn.o_82_k();
        float f2 = u_530_F.v_4262_N(partialTickTime, entitylivingbaseIn.R_4764_Y, entitylivingbaseIn.J_1907_R) / (f1 * 0.5f + 1.0f);
        float f3 = 1.0f / (f2 + 1.0f);
        matrixStackIn.n_1700_B(f3 * f1, 1.0f / f3 * f1, f3 * f1);
    }

    @Override
    public g_2336_b n_1700_B(A_268_Q entity) {
        return n_1700_B;
    }
}


