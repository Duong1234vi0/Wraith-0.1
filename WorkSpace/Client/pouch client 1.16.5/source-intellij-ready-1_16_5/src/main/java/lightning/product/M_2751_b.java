/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;

public class M_2751_b
extends q_1613_l {
    public M_2751_b(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public Z_1993_T n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving) {
        Z_1993_T itemstack = super.n_1700_B(stack, worldIn, entityLiving);
        return entityLiving instanceof a_3913_L && ((a_3913_L)entityLiving).C_415_h.G_564_y ? itemstack : new Z_1993_T(Items.S_4088_D);
    }
}


