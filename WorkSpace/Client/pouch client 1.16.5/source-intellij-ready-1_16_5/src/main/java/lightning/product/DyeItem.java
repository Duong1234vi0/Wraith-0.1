/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.G_4536_S;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.e_933_M;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public class DyeItem
extends q_1613_l {
    private static final Map<e_933_M, DyeItem> n_1700_B = Maps.newEnumMap(e_933_M.class);
    private final e_933_M J_1907_R;

    public DyeItem(e_933_M dyeColorIn, q_1613_l.n_1700_B builder) {
        super(builder);
        this.J_1907_R = dyeColorIn;
        n_1700_B.put(dyeColorIn, this);
    }

    @Override
    public m_3054_I n_1700_B(Z_1993_T stack, a_3913_L playerIn, r_4811_B target, x_1688_C hand) {
        G_4536_S sheepentity;
        if (target instanceof G_4536_S && (sheepentity = (G_4536_S)target).RealmsLongRunningMcoTaskScreen() && !sheepentity.V_1176_p() && sheepentity.h_1640_b() != this.J_1907_R) {
            if (!playerIn.O_508_d.Y_259_p) {
                sheepentity.J_1907_R(this.J_1907_R);
                stack.v_4262_N(1);
            }
            return m_3054_I.n_1700_B(playerIn.O_508_d.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    public e_933_M R_4764_Y() {
        return this.J_1907_R;
    }

    public static DyeItem n_1700_B(e_933_M color) {
        return n_1700_B.get(color);
    }
}


