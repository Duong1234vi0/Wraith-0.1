/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.BaseCoralWallFanBlock;
import lightning.product.T_2915_h;
import lightning.product.biomeBiomes;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.k_594_Q;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;

public class BoneMealItem
extends q_1613_l {
    public BoneMealItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        b_4507_u world = context.getWorld();
        c_1514_x blockpos = context.getPos();
        c_1514_x blockpos1 = blockpos.offset(context.getFace());
        if (BoneMealItem.n_1700_B(context.getItem(), world, blockpos)) {
            if (!world.Y_259_p) {
                world.R_4764_Y(2005, blockpos, 0);
            }
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        K_4074_S blockstate = world.getBlockState(blockpos);
        boolean flag = blockstate.G_564_y((BlockGetter)world, blockpos, context.getFace());
        if (flag && BoneMealItem.n_1700_B(context.getItem(), world, blockpos1, context.getFace())) {
            if (!world.Y_259_p) {
                world.R_4764_Y(2005, blockpos1, 0);
            }
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    public static boolean n_1700_B(Z_1993_T stack, b_4507_u worldIn, c_1514_x pos) {
        BonemealableBlock igrowable;
        K_4074_S blockstate = worldIn.getBlockState(pos);
        if (blockstate.J_1907_R() instanceof BonemealableBlock && (igrowable = (BonemealableBlock)((Object)blockstate.J_1907_R())).n_1700_B((BlockGetter)worldIn, pos, blockstate, worldIn.Y_259_p)) {
            if (worldIn instanceof e_3591_l) {
                if (igrowable.n_1700_B(worldIn, worldIn.w_1457_N, pos, blockstate)) {
                    igrowable.n_1700_B((e_3591_l)worldIn, worldIn.w_1457_N, pos, blockstate);
                }
                stack.v_4262_N(1);
            }
            return true;
        }
        return false;
    }

    public static boolean n_1700_B(Z_1993_T stack, b_4507_u worldIn, c_1514_x pos, @Nullable b_257_Y side) {
        if (worldIn.getBlockState(pos).n_1700_B(a_3742_W.c_3005_b) && worldIn.getFluidState(pos).P_1922_E() == 8) {
            if (!(worldIn instanceof e_3591_l)) {
                return true;
            }
            block0: for (int i = 0; i < 128; ++i) {
                c_1514_x blockpos = pos;
                K_4074_S blockstate = a_3742_W.RowButton.multiplayerClientSuggestionProvider();
                for (int j = 0; j < i / 16; ++j) {
                    if (worldIn.getBlockState(blockpos = blockpos.add(w_1484_f.nextInt(3) - 1, (w_1484_f.nextInt(3) - 1) * w_1484_f.nextInt(3) / 2, w_1484_f.nextInt(3) - 1)).multiplayerClientSuggestionProvider(worldIn, blockpos)) continue block0;
                }
                Optional<f_2392_k<k_594_Q>> optional = worldIn.n_1700_B(blockpos);
                if (Objects.equals(optional, Optional.of(biomeBiomes.B_1668_F)) || Objects.equals(optional, Optional.of(biomeBiomes.Z_976_R))) {
                    if (i == 0 && side != null && side.h_1847_R().G_564_y()) {
                        blockstate = (K_4074_S)((T_2915_h)BlockTags.g_2268_R.n_1700_B(worldIn.w_1457_N)).multiplayerClientSuggestionProvider().n_1700_B(BaseCoralWallFanBlock.h_1847_R, side);
                    } else if (w_1484_f.nextInt(4) == 0) {
                        blockstate = ((T_2915_h)BlockTags.N_2525_X.n_1700_B(w_1484_f)).multiplayerClientSuggestionProvider();
                    }
                }
                if (blockstate.J_1907_R().n_1700_B(BlockTags.g_2268_R)) {
                    for (int k = 0; !blockstate.n_1700_B((T_1316_M)worldIn, blockpos) && k < 4; ++k) {
                        blockstate = (K_4074_S)blockstate.n_1700_B(BaseCoralWallFanBlock.h_1847_R, b_257_Y.R_4764_Y.n_1700_B.n_1700_B(w_1484_f));
                    }
                }
                if (!blockstate.n_1700_B((T_1316_M)worldIn, blockpos)) continue;
                K_4074_S blockstate1 = worldIn.getBlockState(blockpos);
                if (blockstate1.n_1700_B(a_3742_W.c_3005_b) && worldIn.getFluidState(blockpos).P_1922_E() == 8) {
                    worldIn.n_1700_B(blockpos, blockstate, 3);
                    continue;
                }
                if (!blockstate1.n_1700_B(a_3742_W.RowButton) || w_1484_f.nextInt(10) != 0) continue;
                ((BonemealableBlock)((Object)a_3742_W.RowButton)).n_1700_B((e_3591_l)worldIn, w_1484_f, blockpos, blockstate1);
            }
            stack.v_4262_N(1);
            return true;
        }
        return false;
    }

    public static void n_1700_B(LevelAccessor worldIn, c_1514_x posIn, int data) {
        K_4074_S blockstate;
        if (data == 0) {
            data = 15;
        }
        if (!(blockstate = worldIn.getBlockState(posIn)).v_4262_N()) {
            double d1;
            double d0 = 0.5;
            if (blockstate.n_1700_B(a_3742_W.c_3005_b)) {
                data *= 3;
                d1 = 1.0;
                d0 = 3.0;
            } else if (blockstate.t_148_a(worldIn, posIn)) {
                posIn = posIn.up();
                data *= 3;
                d0 = 3.0;
                d1 = 1.0;
            } else {
                d1 = blockstate.s_956_w(worldIn, posIn).R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
            }
            worldIn.n_1700_B(ParticleTypes.t_4043_B, (double)posIn.getX() + 0.5, (double)posIn.getY() + 0.5, (double)posIn.getZ() + 0.5, 0.0, 0.0, 0.0);
            for (int i = 0; i < data; ++i) {
                double d8;
                double d7;
                double d2 = w_1484_f.nextGaussian() * 0.02;
                double d3 = w_1484_f.nextGaussian() * 0.02;
                double d4 = w_1484_f.nextGaussian() * 0.02;
                double d5 = 0.5 - d0;
                double d6 = (double)posIn.getX() + d5 + w_1484_f.nextDouble() * d0 * 2.0;
                if (worldIn.getBlockState(new c_1514_x(d6, d7 = (double)posIn.getY() + w_1484_f.nextDouble() * d1, d8 = (double)posIn.getZ() + d5 + w_1484_f.nextDouble() * d0 * 2.0).down()).v_4262_N()) continue;
                worldIn.n_1700_B(ParticleTypes.t_4043_B, d6, d7, d8, d2, d3, d4);
            }
        }
    }
}


