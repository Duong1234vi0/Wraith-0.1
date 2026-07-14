/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Q_3816_H;
import lightning.product.PolarBearModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class PolarBearRenderer
extends r_1334_c<Q_3816_H, PolarBearModel<Q_3816_H>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/bear/polarbear.png");

    public PolarBearRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new PolarBearModel(), 0.9f);
    }

    @Override
    public g_2336_b n_1700_B(Q_3816_H entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(Q_3816_H entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(1.2f, 1.2f, 1.2f);
        super.n_1700_B(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}


