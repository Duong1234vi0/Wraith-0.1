/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_666_T;
import lightning.product.Stats;
import lightning.product.Y_470_x;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;

public class ThrowablePotionItem
extends Y_470_x {
    public ThrowablePotionItem(q_1613_l.n_1700_B properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        if (!worldIn.Y_259_p) {
            F_666_T potionentity = new F_666_T(worldIn, playerIn);
            potionentity.J_1907_R(itemstack);
            potionentity.n_1700_B(playerIn, playerIn.f_4016_n, playerIn.p_178_J, -20.0f, 0.5f, 1.0f);
            worldIn.a_(potionentity);
        }
        playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        if (!playerIn.C_415_h.G_564_y) {
            itemstack.v_4262_N(1);
        }
        return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
    }
}


