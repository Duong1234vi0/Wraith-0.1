/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.L_1875_m;
import lightning.product.NonNullList;
import lightning.product.Potions;
import lightning.product.S_1134_u;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.ArrowItem;
import lightning.product.x_282_a;
import lightning.product.y_528_b;

public class i_994_s
extends ArrowItem {
    public i_994_s(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public Z_1993_T Y_601_j() {
        return L_1875_m.n_1700_B(super.Y_601_j(), Potions.Y_1740_V);
    }

    @Override
    public void n_1700_B(S_1134_u group, NonNullList<Z_1993_T> items) {
        if (this.n_1700_B(group)) {
            for (y_528_b potion : V_3137_a.B_1668_F) {
                if (potion.n_1700_B().isEmpty()) continue;
                items.add(L_1875_m.n_1700_B(new Z_1993_T(this), potion));
            }
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        L_1875_m.n_1700_B(stack, tooltip, 0.125f);
    }

    @Override
    public String u_1723_Y(Z_1993_T stack) {
        return L_1875_m.G_564_y(stack).J_1907_R(this.J_1907_R() + ".effect.");
    }
}


