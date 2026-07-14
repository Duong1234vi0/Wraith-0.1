/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.D_38_f;
import lightning.product.O_2761_o;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.w_2989_N;
import lightning.product.x_1688_C;

public class r_3240_w
extends q_1613_l {
    public r_3240_w(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.r_4879_Z, D_38_f.v_4262_N, 0.5f, 0.4f / (w_1484_f.nextFloat() * 0.4f + 0.8f));
        O_2761_o event = new O_2761_o(itemstack, 20);
        A_4115_X.n_1700_B(event);
        playerIn.p_1458_L().n_1700_B((q_1613_l)this, event.R_4764_Y());
        if (!worldIn.Y_259_p) {
            w_2989_N enderpearlentity = new w_2989_N(worldIn, playerIn);
            enderpearlentity.J_1907_R(itemstack);
            enderpearlentity.n_1700_B(playerIn, playerIn.f_4016_n, playerIn.p_178_J, 0.0f, 1.5f, 1.0f);
            worldIn.a_(enderpearlentity);
        }
        playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        if (!playerIn.C_415_h.G_564_y) {
            itemstack.v_4262_N(1);
        }
        return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
    }
}


