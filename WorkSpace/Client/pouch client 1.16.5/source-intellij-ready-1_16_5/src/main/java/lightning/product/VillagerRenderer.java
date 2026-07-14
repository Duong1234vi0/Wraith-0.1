/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CrossedArmsItemLayer;
import lightning.product.L_2225_p;
import lightning.product.ReloadableResourceManager;
import lightning.product.X_3615_B;
import lightning.product.g_2016_P;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.VillagerModel;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class VillagerRenderer
extends r_1334_c<L_2225_p, VillagerModel<L_2225_p>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/villager/villager.png");

    public VillagerRenderer(w_2040_b renderManagerIn, ReloadableResourceManager resourceManagerIn) {
        super(renderManagerIn, new VillagerModel(0.0f), 0.5f);
        this.n_1700_B(new g_2016_P<L_2225_p, VillagerModel<L_2225_p>>(this));
        this.n_1700_B(new X_3615_B<L_2225_p, VillagerModel<L_2225_p>>(this, resourceManagerIn, "villager"));
        this.n_1700_B(new CrossedArmsItemLayer<L_2225_p, VillagerModel<L_2225_p>>(this));
    }

    @Override
    public g_2336_b n_1700_B(L_2225_p entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(L_2225_p entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 0.9375f;
        if (entitylivingbaseIn.d_()) {
            f = (float)((double)f * 0.5);
            this.R_4764_Y = 0.25f;
        } else {
            this.R_4764_Y = 0.5f;
        }
        matrixStackIn.n_1700_B(f, f, f);
    }
}


