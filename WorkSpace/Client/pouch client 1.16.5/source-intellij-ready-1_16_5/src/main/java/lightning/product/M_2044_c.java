/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_3833_N;
import lightning.product.VexModel;
import lightning.product.HumanoidMobRenderer;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class M_2044_c
extends HumanoidMobRenderer<D_3833_N, VexModel> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/illager/vex.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/illager/vex_charging.png");

    public M_2044_c(w_2040_b renderManagerIn) {
        super(renderManagerIn, new VexModel(), 0.3f);
    }

    @Override
    protected int n_1700_B(D_3833_N entityIn, c_1514_x partialTicks) {
        return 15;
    }

    @Override
    public g_2336_b n_1700_B(D_3833_N entity) {
        return entity.y_2447_C() ? t_1786_h : n_1700_B;
    }

    @Override
    protected void n_1700_B(D_3833_N entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(0.4f, 0.4f, 0.4f);
    }
}


