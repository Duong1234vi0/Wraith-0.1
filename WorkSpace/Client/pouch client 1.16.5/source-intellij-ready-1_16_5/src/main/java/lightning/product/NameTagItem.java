/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public class NameTagItem
extends q_1613_l {
    public NameTagItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(Z_1993_T stack, a_3913_L playerIn, r_4811_B target, x_1688_C hand) {
        if (stack.Y_601_j() && !(target instanceof a_3913_L)) {
            if (!playerIn.O_508_d.Y_259_p && target.RealmsLongRunningMcoTaskScreen()) {
                target.n_1700_B(stack.multiplayerClientSuggestionProvider());
                if (target instanceof Z_530_i) {
                    ((Z_530_i)target).T_3594_S();
                }
                stack.v_4262_N(1);
            }
            return m_3054_I.n_1700_B(playerIn.O_508_d.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }
}


