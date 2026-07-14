/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ComplexItem;
import lightning.product.G_3165_y;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public class EmptyMapItem
extends ComplexItem {
    public EmptyMapItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = G_3165_y.n_1700_B(worldIn, u_530_F.R_4764_Y(playerIn.O_3598_v()), u_530_F.R_4764_Y(playerIn.l_2647_k()), (byte)0, true, false);
        Z_1993_T itemstack1 = playerIn.R_4764_Y(handIn);
        if (!playerIn.C_415_h.G_564_y) {
            itemstack1.v_4262_N(1);
        }
        playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        playerIn.n_1700_B(SoundEvents.HorizontalDirectionalBlock, 1.0f, 1.0f);
        if (itemstack1.n_1700_B()) {
            return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
        }
        if (!playerIn.l_1268_F.P_1922_E(itemstack.t_148_a())) {
            playerIn.n_1700_B(itemstack, false);
        }
        return InteractionResultHolder.n_1700_B(itemstack1, worldIn.v_4276_D());
    }
}


