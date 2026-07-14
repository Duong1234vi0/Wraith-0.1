/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.LootContextParams;
import lightning.product.B_4088_l;
import lightning.product.C_4998_y;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.F_997_G;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.I_3700_V;
import lightning.product.I_4817_s;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.M_4472_P;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.BlockPlaceContext;
import lightning.product.WitherSkull;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_1913_J;
import lightning.product.b_257_Y;
import lightning.product.b_3485_j;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.g_88_D;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.BlockTags;
import lightning.product.q_1704_m;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.r_1637_F;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.PrimedTnt;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public class v_1577_d
extends BaseEntityBlock {
    private static final b_257_Y[] Q_4569_t = new b_257_Y[]{b_257_Y.P_1922_E, b_257_Y.u_1723_Y, b_257_Y.G_564_y};
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final g_88_D h_1847_R = BlockStateProperties.t_4219_U;

    public v_1577_d(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(h_1847_R, 0)).n_1700_B(P_4830_p, b_257_Y.R_4764_Y));
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return blockState.R_4764_Y(h_1847_R);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, a_3913_L player, c_1514_x pos, K_4074_S state, @Nullable i_2154_H te, Z_1993_T stack) {
        super.n_1700_B(worldIn, player, pos, state, te, stack);
        if (!worldIn.Y_259_p && te instanceof F_997_G) {
            F_997_G beehivetileentity = (F_997_G)te;
            if (K_4096_w.n_1700_B(Enchantments.Y_259_p, stack) == 0) {
                beehivetileentity.n_1700_B(player, state, F_997_G.J_1907_R.R_4764_Y);
                worldIn.R_4764_Y(pos, this);
                this.J_1907_R(worldIn, pos);
            }
            U_3554_Q.v_4276_D.n_1700_B((B_4088_l)player, state.J_1907_R(), stack, beehivetileentity.s_956_w());
        }
    }

    private void J_1907_R(b_4507_u world, c_1514_x pos) {
        List<b_1913_J> list = world.n_1700_B(b_1913_J.class, new I_4817_s(pos).grow(8.0, 6.0, 8.0));
        if (!list.isEmpty()) {
            List<a_3913_L> list1 = world.n_1700_B(a_3913_L.class, new I_4817_s(pos).grow(8.0, 6.0, 8.0));
            int i = list1.size();
            for (b_1913_J beeentity : list) {
                if (beeentity.t_148_a() != null) continue;
                beeentity.R_4764_Y((r_4811_B)list1.get(world.w_1457_N.nextInt(i)));
            }
        }
    }

    public static void n_1700_B(b_4507_u world, c_1514_x pos) {
        v_1577_d.n_1700_B(world, pos, new Z_1993_T(Items.StemGrownBlock, 3));
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        int i = state.R_4764_Y(h_1847_R);
        boolean flag = false;
        if (i >= 5) {
            if (itemstack.J_1907_R() == Items.LightPredicate) {
                worldIn.n_1700_B(player, player.O_3598_v(), player.X_2960_b(), player.l_2647_k(), SoundEvents.RegionPingResult, D_38_f.v_4262_N, 1.0f, 1.0f);
                v_1577_d.n_1700_B(worldIn, pos);
                itemstack.n_1700_B(1, player, (T playerEntity) -> playerEntity.G_564_y(handIn));
                flag = true;
            } else if (itemstack.J_1907_R() == Items.Y_3588_g) {
                itemstack.v_4262_N(1);
                worldIn.n_1700_B(player, player.O_3598_v(), player.X_2960_b(), player.l_2647_k(), SoundEvents.c_132_F, D_38_f.v_4262_N, 1.0f, 1.0f);
                if (itemstack.n_1700_B()) {
                    player.n_1700_B(handIn, new Z_1993_T(Items.StructureBlock));
                } else if (!player.l_1268_F.P_1922_E(new Z_1993_T(Items.StructureBlock))) {
                    player.n_1700_B(new Z_1993_T(Items.StructureBlock), false);
                }
                flag = true;
            }
        }
        if (flag) {
            if (!C_4998_y.n_1700_B(worldIn, pos)) {
                if (this.G_564_y(worldIn, pos)) {
                    this.J_1907_R(worldIn, pos);
                }
                this.n_1700_B(worldIn, state, pos, player, F_997_G.J_1907_R.R_4764_Y);
            } else {
                this.n_1700_B(worldIn, state, pos);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return super.n_1700_B(state, worldIn, pos, player, handIn, hit);
    }

    private boolean G_564_y(b_4507_u world, c_1514_x pos) {
        i_2154_H tileentity = world.getTileEntity(pos);
        if (tileentity instanceof F_997_G) {
            F_997_G beehivetileentity = (F_997_G)tileentity;
            return !beehivetileentity.v_4262_N();
        }
        return false;
    }

    public void n_1700_B(b_4507_u world, K_4074_S state, c_1514_x pos, @Nullable a_3913_L player, F_997_G.J_1907_R tileState) {
        this.n_1700_B(world, state, pos);
        i_2154_H tileentity = world.getTileEntity(pos);
        if (tileentity instanceof F_997_G) {
            F_997_G beehivetileentity = (F_997_G)tileentity;
            beehivetileentity.n_1700_B(player, state, tileState);
        }
    }

    public void n_1700_B(b_4507_u world, K_4074_S state, c_1514_x pos) {
        world.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, 0), 3);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(h_1847_R) >= 5) {
            for (int i = 0; i < rand.nextInt(1) + 1; ++i) {
                this.n_1700_B(worldIn, pos, stateIn);
            }
        }
    }

    private void n_1700_B(b_4507_u world, c_1514_x pos, K_4074_S state) {
        s_1395_c voxelshape;
        double d0;
        if (state.P_4830_p().R_4764_Y() && !(world.w_1457_N.nextFloat() < 0.3f) && (d0 = (voxelshape = state.u_2550_I(world, pos)).R_4764_Y(b_257_Y.n_1700_B.J_1907_R)) >= 1.0 && !state.n_1700_B(BlockTags.H_1990_U)) {
            double d1 = voxelshape.J_1907_R(b_257_Y.n_1700_B.J_1907_R);
            if (d1 > 0.0) {
                this.n_1700_B(world, pos, voxelshape, (double)pos.getY() + d1 - 0.05);
            } else {
                c_1514_x blockpos = pos.down();
                K_4074_S blockstate = world.getBlockState(blockpos);
                s_1395_c voxelshape1 = blockstate.u_2550_I(world, blockpos);
                double d2 = voxelshape1.R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
                if ((d2 < 1.0 || !blockstate.multiplayerClientSuggestionProvider(world, blockpos)) && blockstate.P_4830_p().R_4764_Y()) {
                    this.n_1700_B(world, pos, voxelshape, (double)pos.getY() - 0.05);
                }
            }
        }
    }

    private void n_1700_B(b_4507_u world, c_1514_x pos, s_1395_c shape, double y) {
        this.n_1700_B(world, (double)pos.getX() + shape.J_1907_R(b_257_Y.n_1700_B.n_1700_B), (double)pos.getX() + shape.R_4764_Y(b_257_Y.n_1700_B.n_1700_B), (double)pos.getZ() + shape.J_1907_R(b_257_Y.n_1700_B.R_4764_Y), (double)pos.getZ() + shape.R_4764_Y(b_257_Y.n_1700_B.R_4764_Y), y);
    }

    private void n_1700_B(b_4507_u particleData, double x1, double x2, double z1, double z2, double y) {
        particleData.n_1700_B(ParticleTypes.i_1637_u, u_530_F.G_564_y(particleData.w_1457_N.nextDouble(), x1, x2), y, u_530_F.G_564_y(particleData.w_1457_N.nextDouble(), z1, z2), 0.0, 0.0, 0.0);
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing().u_1723_Y());
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(h_1847_R, P_4830_p);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    @Nullable
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new F_997_G();
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        i_2154_H tileentity;
        if (!worldIn.Y_259_p && player.G_624_v() && worldIn.H_1990_U().J_1907_R(A_2352_Z.u_1723_Y) && (tileentity = worldIn.getTileEntity(pos)) instanceof F_997_G) {
            boolean flag;
            F_997_G beehivetileentity = (F_997_G)tileentity;
            Z_1993_T itemstack = new Z_1993_T(this);
            int i = state.R_4764_Y(h_1847_R);
            boolean bl = flag = !beehivetileentity.v_4262_N();
            if (!flag && i == 0) {
                return;
            }
            if (flag) {
                U_2912_j compoundnbt = new U_2912_j();
                compoundnbt.n_1700_B("Bees", beehivetileentity.P_4830_p());
                itemstack.n_1700_B("BlockEntityTag", compoundnbt);
            }
            U_2912_j compoundnbt1 = new U_2912_j();
            compoundnbt1.J_1907_R("honey_level", i);
            itemstack.n_1700_B("BlockStateTag", compoundnbt1);
            n_1494_c itementity = new n_1494_c(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemstack);
            itementity.t_148_a();
            worldIn.a_(itementity);
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    @Override
    public List<Z_1993_T> n_1700_B(K_4074_S state, q_1704_m.n_1700_B builder) {
        i_2154_H tileentity;
        N_4263_v entity = builder.J_1907_R(LootContextParams.n_1700_B);
        if ((entity instanceof PrimedTnt || entity instanceof b_3485_j || entity instanceof WitherSkull || entity instanceof I_3700_V || entity instanceof r_1637_F) && (tileentity = builder.J_1907_R(LootContextParams.w_1484_f)) instanceof F_997_G) {
            F_997_G beehivetileentity = (F_997_G)tileentity;
            beehivetileentity.n_1700_B(null, state, F_997_G.J_1907_R.R_4764_Y);
        }
        return super.n_1700_B(state, builder);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        i_2154_H tileentity;
        if (worldIn.getBlockState(facingPos).J_1907_R() instanceof M_4472_P && (tileentity = worldIn.getTileEntity(currentPos)) instanceof F_997_G) {
            F_997_G beehivetileentity = (F_997_G)tileentity;
            beehivetileentity.n_1700_B(null, stateIn, F_997_G.J_1907_R.R_4764_Y);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public static b_257_Y n_1700_B(Random rand) {
        return j_3341_s.n_1700_B(Q_4569_t, rand);
    }
}


