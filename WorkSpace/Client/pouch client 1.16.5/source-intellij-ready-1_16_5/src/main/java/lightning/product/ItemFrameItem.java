/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_44_d;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_1613_l;
import lightning.product.t_5_h;

public class ItemFrameItem
extends M_44_d {
    public ItemFrameItem(q_1613_l.n_1700_B builder) {
        super(t_5_h.G_624_v, builder);
    }

    @Override
    protected boolean n_1700_B(a_3913_L playerIn, b_257_Y directionIn, Z_1993_T itemStackIn, c_1514_x posIn) {
        return !b_4507_u.Q_4569_t(posIn) && playerIn.n_1700_B(posIn, directionIn, itemStackIn);
    }
}


