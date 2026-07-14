/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.F_1573_j;
import lightning.product.ItemTags;
import lightning.product.R_2515_i;
import lightning.product.S_3458_C;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_933_M;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;
import lightning.product.x_2414_j;
import lightning.product.x_282_a;

public class L_3273_c
extends q_1613_l {
    public L_3273_c(q_1613_l.n_1700_B builder) {
        super(builder);
        S_3458_C.n_1700_B(this, R_2515_i.n_1700_B);
    }

    @Override
    public String u_1723_Y(Z_1993_T stack) {
        return stack.J_1907_R("BlockEntityTag") != null ? this.J_1907_R() + "." + L_3273_c.G_564_y(stack).R_4764_Y() : super.u_1723_Y(stack);
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        x_2414_j.n_1700_B(stack, tooltip);
    }

    @Override
    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return F_1573_j.G_564_y;
    }

    @Override
    public int J_1907_R(Z_1993_T stack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        playerIn.J_1907_R(handIn);
        return InteractionResultHolder.J_1907_R(itemstack);
    }

    @Override
    public boolean n_1700_B(Z_1993_T toRepair, Z_1993_T repair) {
        return ItemTags.R_4764_Y.n_1700_B(repair.J_1907_R()) || super.n_1700_B(toRepair, repair);
    }

    public static e_933_M G_564_y(Z_1993_T stack) {
        return e_933_M.n_1700_B(stack.n_1700_B("BlockEntityTag").w_1484_f("Base"));
    }
}


