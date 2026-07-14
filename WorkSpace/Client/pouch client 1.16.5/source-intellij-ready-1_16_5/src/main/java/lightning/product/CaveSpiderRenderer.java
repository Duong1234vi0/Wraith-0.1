/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CaveSpider;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.SpiderRenderer;
import lightning.product.w_2040_b;

public class CaveSpiderRenderer
extends SpiderRenderer<CaveSpider> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/spider/cave_spider.png");

    public CaveSpiderRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y *= 0.7f;
    }

    @Override
    protected void n_1700_B(CaveSpider entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(0.7f, 0.7f, 0.7f);
    }

    @Override
    public g_2336_b n_1700_B(CaveSpider entity) {
        return n_1700_B;
    }
}


