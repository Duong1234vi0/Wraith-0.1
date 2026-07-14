/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.x_1688_C;

public class ItemUtils {
    public static InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u world, a_3913_L player, x_1688_C hand) {
        player.J_1907_R(hand);
        return InteractionResultHolder.J_1907_R(player.R_4764_Y(hand));
    }

    public static Z_1993_T n_1700_B(Z_1993_T empty, a_3913_L player, Z_1993_T filled, boolean preventDuplicates) {
        boolean flag = player.C_415_h.G_564_y;
        if (preventDuplicates && flag) {
            if (!player.l_1268_F.w_1484_f(filled)) {
                player.l_1268_F.P_1922_E(filled);
            }
            return empty;
        }
        if (!flag) {
            empty.v_4262_N(1);
        }
        if (empty.n_1700_B()) {
            return filled;
        }
        if (!player.l_1268_F.P_1922_E(filled)) {
            player.n_1700_B(filled, false);
        }
        return empty;
    }

    public static Z_1993_T n_1700_B(Z_1993_T empty, a_3913_L player, Z_1993_T filled) {
        return ItemUtils.n_1700_B(empty, player, filled, true);
    }
}


