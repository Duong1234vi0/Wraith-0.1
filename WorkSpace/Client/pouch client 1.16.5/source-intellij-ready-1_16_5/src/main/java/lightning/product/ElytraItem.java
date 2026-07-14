/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_2530_r;
import lightning.product.R_2515_i;
import lightning.product.S_3458_C;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_1174_E;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.x_1688_C;

public class ElytraItem
extends q_1613_l
implements D_2530_r {
    public ElytraItem(q_1613_l.n_1700_B builder) {
        super(builder);
        S_3458_C.n_1700_B(this, R_2515_i.n_1700_B);
    }

    public static boolean G_564_y(Z_1993_T stack) {
        return stack.v_4262_N() < stack.w_1484_f() - 1;
    }

    @Override
    public boolean n_1700_B(Z_1993_T toRepair, Z_1993_T repair) {
        return repair.J_1907_R() == Items.RotatedPillarBlock;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstack);
        Z_1993_T itemstack1 = playerIn.J_1907_R(equipmentslottype);
        if (itemstack1.n_1700_B()) {
            playerIn.n_1700_B(equipmentslottype, itemstack.t_148_a());
            itemstack.P_1922_E(0);
            return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
        }
        return InteractionResultHolder.G_564_y(itemstack);
    }
}


