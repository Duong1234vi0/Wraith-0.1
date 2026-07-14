/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.GuardianRenderer;
import lightning.product.G_1455_B;
import lightning.product.ElderGuardian;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class ElderGuardianRenderer
extends GuardianRenderer {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/guardian_elder.png");

    public ElderGuardianRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, 1.2f);
    }

    @Override
    protected void n_1700_B(G_1455_B entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(ElderGuardian.n_1700_B, ElderGuardian.n_1700_B, ElderGuardian.n_1700_B);
    }

    @Override
    public g_2336_b n_1700_B(G_1455_B entity) {
        return n_1700_B;
    }
}


