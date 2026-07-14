/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.ThrownExperienceBottle;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;

public class h_4043_o
extends q_1613_l {
    public h_4043_o(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public boolean P_1922_E(Z_1993_T stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.h_1015_G, D_38_f.v_4262_N, 0.5f, 0.4f / (w_1484_f.nextFloat() * 0.4f + 0.8f));
        if (!worldIn.Y_259_p) {
            ThrownExperienceBottle experiencebottleentity = new ThrownExperienceBottle(worldIn, playerIn);
            experiencebottleentity.J_1907_R(itemstack);
            experiencebottleentity.n_1700_B(playerIn, playerIn.f_4016_n, playerIn.p_178_J, -20.0f, 0.7f, 1.0f);
            worldIn.a_(experiencebottleentity);
        }
        playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        if (!playerIn.C_415_h.G_564_y) {
            itemstack.v_4262_N(1);
        }
        return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
    }
}


