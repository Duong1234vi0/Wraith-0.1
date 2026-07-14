/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.Projectile;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.G_2722_I;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.Fluids;
import lightning.product.K_3065_y;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.P_11_z;
import lightning.product.BlockPlaceContext;
import lightning.product.S_3924_b;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.SimpleParticleType;
import lightning.product.BlockTags;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class C_4998_y
extends BaseEntityBlock
implements SimpleWaterloggedBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);
    public static final U_1266_O h_1847_R = BlockStateProperties.multiplayerClientSuggestionProvider;
    public static final U_1266_O Q_4569_t = BlockStateProperties.q_2307_F;
    public static final U_1266_O M_182_A = BlockStateProperties.A_4115_X;
    public static final DirectionProperty t_1786_h = BlockStateProperties.q_4610_l;
    private static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    private final boolean w_1457_N;
    private final int Y_601_j;

    public C_4998_y(boolean smokey, int fireDamage, q_4293_E.P_1922_E properties) {
        super(properties);
        this.w_1457_N = smokey;
        this.Y_601_j = fireDamage;
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(h_1847_R, true)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, b_257_Y.R_4764_Y));
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        Z_1993_T itemstack;
        G_2722_I campfiretileentity;
        Optional<S_3924_b> optional;
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof G_2722_I && (optional = (campfiretileentity = (G_2722_I)tileentity).n_1700_B(itemstack = player.R_4764_Y(handIn))).isPresent()) {
            if (!worldIn.Y_259_p && campfiretileentity.n_1700_B(player.C_415_h.G_564_y ? itemstack.t_148_a() : itemstack, optional.get().P_1922_E())) {
                player.J_1907_R(Stats.t_4219_U);
                return m_3054_I.n_1700_B;
            }
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (!entityIn.r_3651_U() && state.R_4764_Y(h_1847_R).booleanValue() && entityIn instanceof r_4811_B && !K_4096_w.t_148_a((r_4811_B)entityIn)) {
            entityIn.n_1700_B(P_11_z.n_1700_B, (float)this.Y_601_j);
        }
        super.n_1700_B(state, worldIn, pos, entityIn);
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            i_2154_H tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof G_2722_I) {
                K_3065_y.n_1700_B(worldIn, pos, ((G_2722_I)tileentity).n_1700_B());
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        c_1514_x blockpos;
        b_4507_u iworld = context.getWorld();
        boolean flag = iworld.getFluidState(blockpos = context.getPos()).n_1700_B() == Fluids.R_4764_Y;
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(M_182_A, flag)).n_1700_B(Q_4569_t, this.P_4830_p(iworld.getBlockState(blockpos.down())))).n_1700_B(h_1847_R, !flag)).n_1700_B(t_1786_h, context.getPlacementHorizontalFacing());
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(M_182_A).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return facing == b_257_Y.n_1700_B ? (K_4074_S)stateIn.n_1700_B(Q_4569_t, this.P_4830_p(facingState)) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    private boolean P_4830_p(K_4074_S stateIn) {
        return stateIn.n_1700_B(a_3742_W.M_4609_z);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            if (rand.nextInt(10) == 0) {
                worldIn.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.s_1671_u, D_38_f.P_1922_E, 0.5f + rand.nextFloat(), rand.nextFloat() * 0.7f + 0.6f, false);
            }
            if (this.w_1457_N && rand.nextInt(5) == 0) {
                for (int i = 0; i < rand.nextInt(1) + 1; ++i) {
                    worldIn.n_1700_B(ParticleTypes.G_624_v, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, (double)(rand.nextFloat() / 2.0f), 5.0E-5, rand.nextFloat() / 2.0f);
                }
            }
        }
    }

    public static void R_4764_Y(LevelAccessor world, c_1514_x pos, K_4074_S state) {
        i_2154_H tileentity;
        if (world.v_4276_D()) {
            for (int i = 0; i < 20; ++i) {
                C_4998_y.n_1700_B((b_4507_u)world, pos, state.R_4764_Y(Q_4569_t), true);
            }
        }
        if ((tileentity = world.getTileEntity(pos)) instanceof G_2722_I) {
            ((G_2722_I)tileentity).v_4262_N();
        }
    }

    @Override
    public boolean n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state, FluidState fluidStateIn) {
        if (!state.R_4764_Y(BlockStateProperties.A_4115_X).booleanValue() && fluidStateIn.n_1700_B() == Fluids.R_4764_Y) {
            boolean flag = state.R_4764_Y(h_1847_R);
            if (flag) {
                if (!worldIn.v_4276_D()) {
                    worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.y_4642_Y, D_38_f.P_1922_E, 1.0f, 1.0f);
                }
                C_4998_y.R_4764_Y(worldIn, pos, state);
            }
            worldIn.n_1700_B(pos, (K_4074_S)((K_4074_S)state.n_1700_B(M_182_A, true)).n_1700_B(h_1847_R, false), 3);
            worldIn.M_588_G().n_1700_B(pos, fluidStateIn.n_1700_B(), fluidStateIn.n_1700_B().n_1700_B(worldIn));
            return true;
        }
        return false;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, K_4074_S state, BlockHitResult hit, Projectile projectile) {
        if (!worldIn.Y_259_p && projectile.RealmsPersistence()) {
            boolean flag;
            N_4263_v entity = projectile.Y_601_j();
            boolean bl = flag = entity == null || entity instanceof a_3913_L || worldIn.H_1990_U().J_1907_R(A_2352_Z.J_1907_R);
            if (flag && !state.R_4764_Y(h_1847_R).booleanValue() && !state.R_4764_Y(M_182_A).booleanValue()) {
                c_1514_x blockpos = hit.n_1700_B();
                worldIn.n_1700_B(blockpos, (K_4074_S)state.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, true), 11);
            }
        }
    }

    public static void n_1700_B(b_4507_u worldIn, c_1514_x pos, boolean isSignalFire, boolean spawnExtraSmoke) {
        Random random = worldIn.e_4240_b();
        SimpleParticleType basicparticletype = isSignalFire ? ParticleTypes.A_1038_p : ParticleTypes.r_715_M;
        worldIn.J_1907_R(basicparticletype, true, (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
        if (spawnExtraSmoke) {
            worldIn.n_1700_B(ParticleTypes.B_1668_F, (double)pos.getX() + 0.25 + random.nextDouble() / 2.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4, (double)pos.getZ() + 0.25 + random.nextDouble() / 2.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.005, 0.0);
        }
    }

    public static boolean n_1700_B(b_4507_u world, c_1514_x pos) {
        for (int i = 1; i <= 5; ++i) {
            c_1514_x blockpos = pos.down(i);
            K_4074_S blockstate = world.getBlockState(blockpos);
            if (C_4998_y.w_1484_f(blockstate)) {
                return true;
            }
            boolean flag = x_268_Y.R_4764_Y(multiplayerClientSuggestionProvider, blockstate.R_4764_Y((BlockGetter)world, pos, CollisionContext.J_1907_R()), BooleanOp.t_148_a);
            if (!flag) continue;
            K_4074_S blockstate1 = world.getBlockState(blockpos.down());
            return C_4998_y.w_1484_f(blockstate1);
        }
        return false;
    }

    public static boolean w_1484_f(K_4074_S state) {
        return state.J_1907_R(h_1847_R) && state.n_1700_B(BlockTags.U_1241_n) && state.R_4764_Y(h_1847_R) != false;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(M_182_A) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(t_1786_h, rot.n_1700_B(state.R_4764_Y(t_1786_h)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(t_1786_h)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(h_1847_R, Q_4569_t, M_182_A, t_1786_h);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new G_2722_I();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }

    public static boolean t_148_a(K_4074_S state) {
        return state.n_1700_B(BlockTags.U_1241_n, (q_4293_E.n_1700_B stateIn) -> stateIn.J_1907_R(BlockStateProperties.A_4115_X) && stateIn.J_1907_R(BlockStateProperties.multiplayerClientSuggestionProvider)) && state.R_4764_Y(BlockStateProperties.A_4115_X) == false && state.R_4764_Y(BlockStateProperties.multiplayerClientSuggestionProvider) == false;
    }
}


