/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.g_422_i;
import lightning.product.k_2610_C;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;

public class SuspiciousStewItem
extends q_1613_l {
    public SuspiciousStewItem(q_1613_l.n_1700_B properties) {
        super(properties);
    }

    public static void n_1700_B(Z_1993_T itemStackIn, g_422_i effectIn, int effectDuration) {
        U_2912_j compoundnbt = itemStackIn.M_182_A();
        q_2896_o listnbt = compoundnbt.G_564_y("Effects", 9);
        U_2912_j compoundnbt1 = new U_2912_j();
        compoundnbt1.n_1700_B("EffectId", (byte)g_422_i.n_1700_B(effectIn));
        compoundnbt1.J_1907_R("EffectDuration", effectDuration);
        listnbt.add(compoundnbt1);
        compoundnbt.n_1700_B("Effects", listnbt);
    }

    @Override
    public Z_1993_T n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving) {
        Z_1993_T itemstack = super.n_1700_B(stack, worldIn, entityLiving);
        U_2912_j compoundnbt = stack.Q_4569_t();
        if (compoundnbt != null && compoundnbt.R_4764_Y("Effects", 9)) {
            q_2896_o listnbt = compoundnbt.G_564_y("Effects", 10);
            for (int i = 0; i < listnbt.size(); ++i) {
                g_422_i effect;
                int j = 160;
                U_2912_j compoundnbt1 = listnbt.n_1700_B(i);
                if (compoundnbt1.R_4764_Y("EffectDuration", 3)) {
                    j = compoundnbt1.w_1484_f("EffectDuration");
                }
                if ((effect = g_422_i.n_1700_B(compoundnbt1.u_1723_Y("EffectId"))) == null) continue;
                entityLiving.n_1700_B(new k_2610_C(effect, j));
            }
        }
        return entityLiving instanceof a_3913_L && ((a_3913_L)entityLiving).C_415_h.G_564_y ? itemstack : new Z_1993_T(Items.S_4088_D);
    }
}


