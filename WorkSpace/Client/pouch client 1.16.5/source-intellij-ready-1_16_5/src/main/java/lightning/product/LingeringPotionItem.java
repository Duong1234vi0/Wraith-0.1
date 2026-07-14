/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.ThrowablePotionItem;
import lightning.product.L_1875_m;
import lightning.product.SoundEvents;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class LingeringPotionItem
extends ThrowablePotionItem {
    public LingeringPotionItem(q_1613_l.n_1700_B blockIn) {
        super(blockIn);
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        L_1875_m.n_1700_B(stack, tooltip, 0.25f);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.Spammer, D_38_f.v_4262_N, 0.5f, 0.4f / (w_1484_f.nextFloat() * 0.4f + 0.8f));
        return super.n_1700_B(worldIn, playerIn, handIn);
    }
}



