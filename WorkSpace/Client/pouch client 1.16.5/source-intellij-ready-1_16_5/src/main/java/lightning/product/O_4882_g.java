/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.J_4485_t;
import lightning.product.K_4096_w;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.W_1247_f;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;

public class O_4882_g
extends q_1613_l
implements J_4485_t {
    public O_4882_g(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        if (playerIn.X_2960_b != null) {
            if (!worldIn.Y_259_p) {
                int i = playerIn.X_2960_b.J_1907_R(itemstack);
                itemstack.n_1700_B(i, playerIn, (T p_220000_1_) -> p_220000_1_.G_564_y(handIn));
            }
            worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.d_2169_p, D_38_f.v_4262_N, 1.0f, 0.4f / (w_1484_f.nextFloat() * 0.4f + 0.8f));
        } else {
            worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.B_2580_P, D_38_f.v_4262_N, 0.5f, 0.4f / (w_1484_f.nextFloat() * 0.4f + 0.8f));
            if (!worldIn.Y_259_p) {
                int k = K_4096_w.R_4764_Y(itemstack);
                int j = K_4096_w.J_1907_R(itemstack);
                worldIn.a_(new W_1247_f(playerIn, worldIn, j, k));
            }
            playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        }
        return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
    }

    @Override
    public int G_564_y() {
        return 1;
    }
}


