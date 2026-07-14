/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.Saddleable;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public class SaddleItem
extends q_1613_l {
    public SaddleItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(Z_1993_T stack, a_3913_L playerIn, r_4811_B target, x_1688_C hand) {
        Saddleable iequipable;
        if (target instanceof Saddleable && target.RealmsLongRunningMcoTaskScreen() && !(iequipable = (Saddleable)((Object)target)).G_564_y() && iequipable.n_1700_B()) {
            if (!playerIn.O_508_d.Y_259_p) {
                iequipable.n_1700_B(D_38_f.v_4262_N);
                stack.v_4262_N(1);
            }
            return m_3054_I.n_1700_B(playerIn.O_508_d.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }
}


