/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_3485_j;
import lightning.product.CreeperModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.r_4811_B;
import lightning.product.CreeperPowerLayer;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class CreeperRenderer
extends r_1334_c<b_3485_j, CreeperModel<b_3485_j>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/creeper/creeper.png");

    public CreeperRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new CreeperModel(), 0.5f);
        this.n_1700_B(new CreeperPowerLayer(this));
    }

    @Override
    protected void n_1700_B(b_3485_j entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = entitylivingbaseIn.c_3005_b(partialTickTime);
        float f1 = 1.0f + u_530_F.n_1700_B(f * 100.0f) * f * 0.01f;
        f = u_530_F.n_1700_B(f, 0.0f, 1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        matrixStackIn.n_1700_B(f2, f3, f2);
    }

    @Override
    protected float n_1700_B(b_3485_j livingEntityIn, float partialTicks) {
        float f = livingEntityIn.c_3005_b(partialTicks);
        return (int)(f * 10.0f) % 2 == 0 ? 0.0f : u_530_F.n_1700_B(f, 0.5f, 1.0f);
    }

    @Override
    public g_2336_b n_1700_B(b_3485_j entity) {
        return n_1700_B;
    }

    @Override
    protected /* synthetic */ float J_1907_R(r_4811_B r_4811_B2, float f) {
        return this.n_1700_B((b_3485_j)r_4811_B2, f);
    }
}


