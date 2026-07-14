/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Arrow;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.h_384_L;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;

public class ArrowItem
extends q_1613_l {
    public ArrowItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    public h_384_L n_1700_B(b_4507_u worldIn, Z_1993_T stack, r_4811_B shooter) {
        Arrow arrowentity = new Arrow(worldIn, shooter);
        arrowentity.J_1907_R(stack);
        return arrowentity;
    }
}


