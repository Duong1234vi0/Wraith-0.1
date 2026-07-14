/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.GhastModel;
import lightning.product.Ghast;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class u_588_T
extends r_1334_c<Ghast, GhastModel<Ghast>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/ghast/ghast.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/ghast/ghast_shooting.png");

    public u_588_T(w_2040_b renderManagerIn) {
        super(renderManagerIn, new GhastModel(), 1.5f);
    }

    @Override
    public g_2336_b n_1700_B(Ghast entity) {
        return entity.u_1723_Y() ? t_1786_h : n_1700_B;
    }

    @Override
    protected void n_1700_B(Ghast entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 1.0f;
        float f1 = 4.5f;
        float f2 = 4.5f;
        matrixStackIn.n_1700_B(4.5f, 4.5f, 4.5f);
    }
}


