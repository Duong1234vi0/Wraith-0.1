/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CrossedArmsItemLayer;
import lightning.product.T_426_Y;
import lightning.product.g_2016_P;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.VillagerModel;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class WanderingTraderRenderer
extends r_1334_c<T_426_Y, VillagerModel<T_426_Y>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/wandering_trader.png");

    public WanderingTraderRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new VillagerModel(0.0f), 0.5f);
        this.n_1700_B(new g_2016_P<T_426_Y, VillagerModel<T_426_Y>>(this));
        this.n_1700_B(new CrossedArmsItemLayer<T_426_Y, VillagerModel<T_426_Y>>(this));
    }

    @Override
    public g_2336_b n_1700_B(T_426_Y entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(T_426_Y entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 0.9375f;
        matrixStackIn.n_1700_B(0.9375f, 0.9375f, 0.9375f);
    }
}


