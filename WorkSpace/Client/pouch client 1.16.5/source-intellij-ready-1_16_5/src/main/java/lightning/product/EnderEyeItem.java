/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.EndPortalFrameBlock;
import lightning.product.D_38_f;
import lightning.product.StructureFeature;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.BlockPattern;
import lightning.product.R_137_s;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;

public class EnderEyeItem
extends q_1613_l {
    public EnderEyeItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        if (blockstate.n_1700_B(a_3742_W.l_2995_s) && !blockstate.R_4764_Y(EndPortalFrameBlock.h_1847_R).booleanValue()) {
            if (world.Y_259_p) {
                return m_3054_I.n_1700_B;
            }
            K_4074_S blockstate1 = (K_4074_S)blockstate.n_1700_B(EndPortalFrameBlock.h_1847_R, true);
            T_2915_h.n_1700_B(blockstate, blockstate1, world, blockpos);
            world.n_1700_B(blockpos, blockstate1, 2);
            world.R_4764_Y(blockpos, a_3742_W.l_2995_s);
            context.getItem().v_4262_N(1);
            world.R_4764_Y(1503, blockpos, 0);
            BlockPattern.J_1907_R blockpattern$patternhelper = EndPortalFrameBlock.J_1907_R().n_1700_B(world, blockpos);
            if (blockpattern$patternhelper != null) {
                c_1514_x blockpos1 = blockpattern$patternhelper.n_1700_B().add(-3, 0, -3);
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        world.n_1700_B(blockpos1.add(i, 0, j), a_3742_W.M_2562_s.multiplayerClientSuggestionProvider(), 2);
                    }
                }
                world.J_1907_R(1038, blockpos1.add(1, 0, 1), 0);
            }
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        c_1514_x blockpos;
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        BlockHitResult raytraceresult = EnderEyeItem.n_1700_B(worldIn, playerIn, ClipContext.J_1907_R.n_1700_B);
        if (((HitResult)raytraceresult).R_4764_Y() == HitResult.n_1700_B.J_1907_R && worldIn.getBlockState(raytraceresult.n_1700_B()).n_1700_B(a_3742_W.l_2995_s)) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        playerIn.J_1907_R(handIn);
        if (worldIn instanceof e_3591_l && (blockpos = ((e_3591_l)worldIn).Y_259_p().t_148_a().n_1700_B((e_3591_l)worldIn, StructureFeature.u_2550_I, playerIn.b_2312_j(), 100, false)) != null) {
            R_137_s eyeofenderentity = new R_137_s(worldIn, playerIn.O_3598_v(), playerIn.P_1922_E(0.5), playerIn.l_2647_k());
            eyeofenderentity.J_1907_R(itemstack);
            eyeofenderentity.n_1700_B(blockpos);
            worldIn.a_(eyeofenderentity);
            if (playerIn instanceof B_4088_l) {
                U_3554_Q.P_4830_p.n_1700_B((B_4088_l)playerIn, blockpos);
            }
            worldIn.n_1700_B((a_3913_L)null, playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), SoundEvents.I_3457_f, D_38_f.v_4262_N, 0.5f, 0.4f / (w_1484_f.nextFloat() * 0.4f + 0.8f));
            worldIn.n_1700_B((a_3913_L)null, 1003, playerIn.b_2312_j(), 0);
            if (!playerIn.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
            playerIn.n_1700_B(handIn, true);
            return InteractionResultHolder.n_1700_B(itemstack);
        }
        return InteractionResultHolder.J_1907_R(itemstack);
    }
}


