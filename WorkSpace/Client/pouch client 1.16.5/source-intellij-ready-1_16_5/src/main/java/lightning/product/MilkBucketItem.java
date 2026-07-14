/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.F_1573_j;
import lightning.product.ItemUtils;
import lightning.product.Stats;
import lightning.product.U_3554_Q;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public class MilkBucketItem
extends q_1613_l {
    public MilkBucketItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public Z_1993_T n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving) {
        if (entityLiving instanceof B_4088_l) {
            B_4088_l serverplayerentity = (B_4088_l)entityLiving;
            U_3554_Q.Z_875_P.n_1700_B(serverplayerentity, stack);
            serverplayerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        }
        if (entityLiving instanceof a_3913_L && !((a_3913_L)entityLiving).C_415_h.G_564_y) {
            stack.v_4262_N(1);
        }
        if (!worldIn.Y_259_p) {
            entityLiving.g_1734_y();
        }
        return stack.n_1700_B() ? new Z_1993_T(Items.G_1539_D) : stack;
    }

    @Override
    public int J_1907_R(Z_1993_T stack) {
        return 32;
    }

    @Override
    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return F_1573_j.R_4764_Y;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        return ItemUtils.n_1700_B(worldIn, playerIn, handIn);
    }
}


