/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SpiderModel;
import lightning.product.monsterSpider;
import lightning.product.SpiderEyesLayer;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;

public class SpiderRenderer<T extends monsterSpider>
extends r_1334_c<T, SpiderModel<T>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/spider/spider.png");

    public SpiderRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new SpiderModel(), 0.8f);
        this.n_1700_B(new SpiderEyesLayer(this));
    }

    protected float J_1907_R(T entityLivingBaseIn) {
        return 180.0f;
    }

    @Override
    public g_2336_b n_1700_B(T entity) {
        return n_1700_B;
    }

    @Override
    protected /* synthetic */ float R_4764_Y(r_4811_B r_4811_B2) {
        return this.J_1907_R((T)((monsterSpider)r_4811_B2));
    }
}


