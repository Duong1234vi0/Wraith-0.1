/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.LavaSlimeModel;
import lightning.product.S_922_s;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class MagmaCubeRenderer
extends r_1334_c<S_922_s, LavaSlimeModel<S_922_s>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/slime/magmacube.png");

    public MagmaCubeRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new LavaSlimeModel(), 0.25f);
    }

    @Override
    protected int n_1700_B(S_922_s entityIn, c_1514_x partialTicks) {
        return 15;
    }

    @Override
    public g_2336_b n_1700_B(S_922_s entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(S_922_s entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        int i = entitylivingbaseIn.o_82_k();
        float f = u_530_F.v_4262_N(partialTickTime, entitylivingbaseIn.R_4764_Y, entitylivingbaseIn.J_1907_R) / ((float)i * 0.5f + 1.0f);
        float f1 = 1.0f / (f + 1.0f);
        matrixStackIn.n_1700_B(f1 * (float)i, 1.0f / f1 * (float)i, f1 * (float)i);
    }
}


