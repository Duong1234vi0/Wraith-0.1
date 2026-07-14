/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.FluidTags;
import lightning.product.B_1132_Q;
import lightning.product.D_38_f;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.ItemUtils;
import lightning.product.L_1875_m;
import lightning.product.Potions;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_2971_b;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.x_1688_C;

public class BottleItem
extends q_1613_l {
    public BottleItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        List<B_1132_Q> list = worldIn.n_1700_B(B_1132_Q.class, playerIn.i_601_W().grow(2.0), (? super T p_210311_0_) -> p_210311_0_ != null && p_210311_0_.RealmsLongRunningMcoTaskScreen() && p_210311_0_.u_2550_I() instanceof b_2971_b);
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        if (!list.isEmpty()) {
            B_1132_Q areaeffectcloudentity = list.get(0);
            areaeffectcloudentity.n_1700_B(areaeffectcloudentity.P_1922_E() - 0.5f);
            worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.g_4106_L, D_38_f.v_4262_N, 1.0f, 1.0f);
            return InteractionResultHolder.n_1700_B(this.n_1700_B(itemstack, playerIn, new Z_1993_T(Items.O_3671_t)), worldIn.v_4276_D());
        }
        BlockHitResult raytraceresult = BottleItem.n_1700_B(worldIn, playerIn, ClipContext.J_1907_R.J_1907_R);
        if (((HitResult)raytraceresult).R_4764_Y() == HitResult.n_1700_B.n_1700_B) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        if (((HitResult)raytraceresult).R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            c_1514_x blockpos = raytraceresult.n_1700_B();
            if (!worldIn.n_1700_B(playerIn, blockpos)) {
                return InteractionResultHolder.R_4764_Y(itemstack);
            }
            if (worldIn.getFluidState(blockpos).n_1700_B(FluidTags.J_1907_R)) {
                worldIn.n_1700_B(playerIn, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.c_132_F, D_38_f.v_4262_N, 1.0f, 1.0f);
                return InteractionResultHolder.n_1700_B(this.n_1700_B(itemstack, playerIn, L_1875_m.n_1700_B(new Z_1993_T(Items.j_2461_G), Potions.J_1907_R)), worldIn.v_4276_D());
            }
        }
        return InteractionResultHolder.R_4764_Y(itemstack);
    }

    protected Z_1993_T n_1700_B(Z_1993_T bottleStack, a_3913_L player, Z_1993_T stack) {
        player.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        return ItemUtils.n_1700_B(bottleStack, player, stack);
    }
}


