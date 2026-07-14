/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.h_355_y;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.x_1688_C;

public class WritableBookItem
extends q_1613_l {
    public WritableBookItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        if (blockstate.n_1700_B(a_3742_W.F_489_x)) {
            return h_355_y.n_1700_B(world, blockpos, blockstate, context.getItem()) ? m_3054_I.n_1700_B(world.Y_259_p) : m_3054_I.R_4764_Y;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        playerIn.n_1700_B(itemstack, handIn);
        playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
    }

    public static boolean n_1700_B(@Nullable U_2912_j nbt) {
        if (nbt == null) {
            return false;
        }
        if (!nbt.R_4764_Y("pages", 9)) {
            return false;
        }
        q_2896_o listnbt = nbt.G_564_y("pages", 8);
        for (int i = 0; i < listnbt.size(); ++i) {
            String s = listnbt.t_148_a(i);
            if (s.length() <= Short.MAX_VALUE) continue;
            return false;
        }
        return true;
    }
}


