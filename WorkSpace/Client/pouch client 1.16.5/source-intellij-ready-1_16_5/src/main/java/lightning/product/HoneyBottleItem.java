/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.F_1573_j;
import lightning.product.MobEffects;
import lightning.product.ItemUtils;
import lightning.product.Stats;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public class HoneyBottleItem
extends q_1613_l {
    public HoneyBottleItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public Z_1993_T n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving) {
        super.n_1700_B(stack, worldIn, entityLiving);
        if (entityLiving instanceof B_4088_l) {
            B_4088_l serverplayerentity = (B_4088_l)entityLiving;
            U_3554_Q.Z_875_P.n_1700_B(serverplayerentity, stack);
            serverplayerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        }
        if (!worldIn.Y_259_p) {
            entityLiving.G_564_y(MobEffects.w_1457_N);
        }
        if (stack.n_1700_B()) {
            return new Z_1993_T(Items.Y_3588_g);
        }
        if (entityLiving instanceof a_3913_L && !((a_3913_L)entityLiving).C_415_h.G_564_y) {
            Z_1993_T itemstack = new Z_1993_T(Items.Y_3588_g);
            a_3913_L playerentity = (a_3913_L)entityLiving;
            if (!playerentity.l_1268_F.P_1922_E(itemstack)) {
                playerentity.n_1700_B(itemstack, false);
            }
        }
        return stack;
    }

    @Override
    public int J_1907_R(Z_1993_T stack) {
        return 40;
    }

    @Override
    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return F_1573_j.R_4764_Y;
    }

    @Override
    public SoundEvent C_() {
        return SoundEvents.AutoCrystal;
    }

    @Override
    public SoundEvent D_() {
        return SoundEvents.AutoCrystal;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        return ItemUtils.n_1700_B(worldIn, playerIn, handIn);
    }
}



