/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.WitherBossModel;
import lightning.product.I_3700_V;
import lightning.product.WitherArmorLayer;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class WitherBossRenderer
extends r_1334_c<I_3700_V, WitherBossModel<I_3700_V>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/wither/wither_invulnerable.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/wither/wither.png");

    public WitherBossRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new WitherBossModel(0.0f), 1.0f);
        this.n_1700_B(new WitherArmorLayer(this));
    }

    @Override
    protected int n_1700_B(I_3700_V entityIn, c_1514_x partialTicks) {
        return 15;
    }

    @Override
    public g_2336_b n_1700_B(I_3700_V entity) {
        int i = entity.h_1640_b();
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? n_1700_B : t_1786_h;
    }

    @Override
    protected void n_1700_B(I_3700_V entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 2.0f;
        int i = entitylivingbaseIn.h_1640_b();
        if (i > 0) {
            f -= ((float)i - partialTickTime) / 220.0f * 0.5f;
        }
        matrixStackIn.n_1700_B(f, f, f);
    }
}


