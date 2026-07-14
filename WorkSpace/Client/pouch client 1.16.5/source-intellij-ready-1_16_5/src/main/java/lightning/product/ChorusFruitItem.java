/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.g_1253_u;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class ChorusFruitItem
extends q_1613_l {
    public ChorusFruitItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public Z_1993_T n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving) {
        Z_1993_T itemstack = super.n_1700_B(stack, worldIn, entityLiving);
        if (!worldIn.Y_259_p) {
            double d0 = entityLiving.O_3598_v();
            double d1 = entityLiving.X_2960_b();
            double d2 = entityLiving.l_2647_k();
            for (int i = 0; i < 16; ++i) {
                double d3 = entityLiving.O_3598_v() + (entityLiving.M_3508_C().nextDouble() - 0.5) * 16.0;
                double d4 = u_530_F.n_1700_B(entityLiving.X_2960_b() + (double)(entityLiving.M_3508_C().nextInt(16) - 8), 0.0, (double)(worldIn.n_3318_d() - 1));
                double d5 = entityLiving.l_2647_k() + (entityLiving.M_3508_C().nextDouble() - 0.5) * 16.0;
                if (entityLiving.y_2772_m()) {
                    entityLiving.A_3959_N();
                }
                if (!entityLiving.n_1700_B(d3, d4, d5, true)) continue;
                SoundEvent soundevent = entityLiving instanceof g_1253_u ? SoundEvents.z_2372_L : SoundEvents.t_1446_I;
                worldIn.n_1700_B((a_3913_L)null, d0, d1, d2, soundevent, D_38_f.w_1484_f, 1.0f, 1.0f);
                entityLiving.n_1700_B(soundevent, 1.0f, 1.0f);
                break;
            }
            if (entityLiving instanceof a_3913_L) {
                ((a_3913_L)entityLiving).p_1458_L().n_1700_B((q_1613_l)this, 20);
            }
        }
        return itemstack;
    }
}


