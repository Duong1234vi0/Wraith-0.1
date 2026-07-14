/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2534_D;
import lightning.product.HorseModel;
import lightning.product.g_221_o;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public abstract class AbstractHorseRenderer<T extends U_2534_D, M extends HorseModel<T>>
extends r_1334_c<T, M> {
    private final float n_1700_B;

    public AbstractHorseRenderer(w_2040_b renderManagerIn, M p_i50975_2_, float scaleIn) {
        super(renderManagerIn, p_i50975_2_, 0.75f);
        this.n_1700_B = scaleIn;
    }

    @Override
    protected void n_1700_B(T entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(this.n_1700_B, this.n_1700_B, this.n_1700_B);
        super.n_1700_B(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}


