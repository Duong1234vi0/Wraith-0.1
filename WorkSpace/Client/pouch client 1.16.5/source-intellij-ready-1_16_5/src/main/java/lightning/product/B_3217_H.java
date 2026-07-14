/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.Fluids;
import lightning.product.ItemUtils;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_3554_Q;
import lightning.product.U_4243_e;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.o_3946_o;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.LiquidBlockContainer;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.x_1688_C;

public class B_3217_H
extends q_1613_l {
    private final Fluid n_1700_B;

    public B_3217_H(Fluid containedFluidIn, q_1613_l.n_1700_B builder) {
        super(builder);
        this.n_1700_B = containedFluidIn;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        BlockHitResult raytraceresult = B_3217_H.n_1700_B(worldIn, playerIn, this.n_1700_B == Fluids.n_1700_B ? ClipContext.J_1907_R.J_1907_R : ClipContext.J_1907_R.n_1700_B);
        if (((HitResult)raytraceresult).R_4764_Y() == HitResult.n_1700_B.n_1700_B) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        if (((HitResult)raytraceresult).R_4764_Y() != HitResult.n_1700_B.J_1907_R) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        BlockHitResult blockraytraceresult = raytraceresult;
        c_1514_x blockpos = blockraytraceresult.n_1700_B();
        b_257_Y direction = blockraytraceresult.J_1907_R();
        c_1514_x blockpos1 = blockpos.offset(direction);
        if (worldIn.n_1700_B(playerIn, blockpos) && playerIn.n_1700_B(blockpos1, direction, itemstack)) {
            c_1514_x blockpos2;
            if (this.n_1700_B == Fluids.n_1700_B) {
                Fluid fluid;
                K_4074_S blockstate1 = worldIn.getBlockState(blockpos);
                if (blockstate1.J_1907_R() instanceof o_3946_o && (fluid = ((o_3946_o)((Object)blockstate1.J_1907_R())).J_1907_R(worldIn, blockpos, blockstate1)) != Fluids.n_1700_B) {
                    playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
                    playerIn.n_1700_B(fluid.n_1700_B(FluidTags.R_4764_Y) ? SoundEvents.O_2151_c : SoundEvents.i_2993_w, 1.0f, 1.0f);
                    Z_1993_T itemstack1 = ItemUtils.n_1700_B(itemstack, playerIn, new Z_1993_T(fluid.n_1700_B()));
                    if (!worldIn.Y_259_p) {
                        U_3554_Q.s_956_w.n_1700_B((B_4088_l)playerIn, new Z_1993_T(fluid.n_1700_B()));
                    }
                    return InteractionResultHolder.n_1700_B(itemstack1, worldIn.v_4276_D());
                }
                return InteractionResultHolder.G_564_y(itemstack);
            }
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            c_1514_x c_1514_x2 = blockpos2 = blockstate.J_1907_R() instanceof LiquidBlockContainer && this.n_1700_B == Fluids.R_4764_Y ? blockpos : blockpos1;
            if (this.n_1700_B(playerIn, worldIn, blockpos2, blockraytraceresult)) {
                this.n_1700_B(worldIn, itemstack, blockpos2);
                if (playerIn instanceof B_4088_l) {
                    U_3554_Q.q_2307_F.n_1700_B((B_4088_l)playerIn, blockpos2, itemstack);
                }
                playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
                return InteractionResultHolder.n_1700_B(this.n_1700_B(itemstack, playerIn), worldIn.v_4276_D());
            }
            return InteractionResultHolder.G_564_y(itemstack);
        }
        return InteractionResultHolder.G_564_y(itemstack);
    }

    protected Z_1993_T n_1700_B(Z_1993_T stack, a_3913_L player) {
        return !player.C_415_h.G_564_y ? new Z_1993_T(Items.G_1539_D) : stack;
    }

    public void n_1700_B(b_4507_u worldIn, Z_1993_T p_203792_2_, c_1514_x pos) {
    }

    public boolean n_1700_B(@Nullable a_3913_L player, b_4507_u worldIn, c_1514_x posIn, @Nullable BlockHitResult rayTrace) {
        boolean flag1;
        if (!(this.n_1700_B instanceof U_4243_e)) {
            return false;
        }
        K_4074_S blockstate = worldIn.getBlockState(posIn);
        T_2915_h block = blockstate.J_1907_R();
        Material material = blockstate.R_4764_Y();
        boolean flag = blockstate.n_1700_B(this.n_1700_B);
        boolean bl = flag1 = blockstate.v_4262_N() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer)((Object)block)).n_1700_B((BlockGetter)worldIn, posIn, blockstate, this.n_1700_B);
        if (!flag1) {
            return rayTrace != null && this.n_1700_B(player, worldIn, rayTrace.n_1700_B().offset(rayTrace.J_1907_R()), (BlockHitResult)null);
        }
        if (worldIn.G_624_v().G_564_y() && this.n_1700_B.n_1700_B(FluidTags.J_1907_R)) {
            int i = posIn.getX();
            int j = posIn.getY();
            int k = posIn.getZ();
            worldIn.n_1700_B(player, posIn, SoundEvents.V_1665_T, D_38_f.P_1922_E, 0.5f, 2.6f + (worldIn.w_1457_N.nextFloat() - worldIn.w_1457_N.nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                worldIn.n_1700_B(ParticleTypes.d_2461_k, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
            }
            return true;
        }
        if (block instanceof LiquidBlockContainer && this.n_1700_B == Fluids.R_4764_Y) {
            ((LiquidBlockContainer)((Object)block)).n_1700_B(worldIn, posIn, blockstate, ((U_4243_e)this.n_1700_B).n_1700_B(false));
            this.n_1700_B(player, (LevelAccessor)worldIn, posIn);
            return true;
        }
        if (!worldIn.Y_259_p && flag && !material.n_1700_B()) {
            worldIn.J_1907_R(posIn, true);
        }
        if (!worldIn.n_1700_B(posIn, this.n_1700_B.w_1484_f().v_4262_N(), 11) && !blockstate.P_4830_p().J_1907_R()) {
            return false;
        }
        this.n_1700_B(player, (LevelAccessor)worldIn, posIn);
        return true;
    }

    protected void n_1700_B(@Nullable a_3913_L player, LevelAccessor worldIn, c_1514_x pos) {
        SoundEvent soundevent = this.n_1700_B.n_1700_B(FluidTags.R_4764_Y) ? SoundEvents.RealmsLongRunningMcoTaskScreen : SoundEvents.J_4256_G;
        worldIn.n_1700_B(player, pos, soundevent, D_38_f.P_1922_E, 1.0f, 1.0f);
    }
}


