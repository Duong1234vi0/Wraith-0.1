/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.ThrowablePotionItem;
import lightning.product.SoundEvents;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;

public class SplashPotionItem
extends ThrowablePotionItem {
    public SplashPotionItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.ChorusPlantBlock, D_38_f.w_1484_f, 0.5f, 0.4f / (w_1484_f.nextFloat() * 0.4f + 0.8f));
        return super.n_1700_B(worldIn, playerIn, handIn);
    }
}


