/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.SpectralArrow;
import lightning.product.h_384_L;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.ArrowItem;

public class SpectralArrowItem
extends ArrowItem {
    public SpectralArrowItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public h_384_L n_1700_B(b_4507_u worldIn, Z_1993_T stack, r_4811_B shooter) {
        return new SpectralArrow(worldIn, shooter);
    }
}


