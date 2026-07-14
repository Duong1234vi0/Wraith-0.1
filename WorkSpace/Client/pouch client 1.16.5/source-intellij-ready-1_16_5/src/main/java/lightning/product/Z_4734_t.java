/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.ObserverBlock;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.RepeaterBlock;
import lightning.product.e_563_h;
import lightning.product.g_88_D;
import lightning.product.m_3054_I;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.DustParticleOptions;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.y_1030_X;

public class Z_4734_t
extends T_2915_h {
    public static final e_563_h<y_1030_X> P_4830_p = BlockStateProperties.N_2525_X;
    public static final e_563_h<y_1030_X> h_1847_R = BlockStateProperties.H_1990_U;
    public static final e_563_h<y_1030_X> Q_4569_t = BlockStateProperties.c_4037_x;
    public static final e_563_h<y_1030_X> M_182_A = BlockStateProperties.g_2268_R;
    public static final g_88_D t_1786_h = BlockStateProperties.q_1982_R;
    public static final Map<b_257_Y, e_563_h<y_1030_X>> multiplayerClientSuggestionProvider = Maps.newEnumMap((Map)ImmutableMap.of((Object)b_257_Y.R_4764_Y, P_4830_p, (Object)b_257_Y.u_1723_Y, h_1847_R, (Object)b_257_Y.G_564_y, Q_4569_t, (Object)b_257_Y.P_1922_E, M_182_A));
    private static final s_1395_c w_1457_N = T_2915_h.n_1700_B(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
    private static final Map<b_257_Y, s_1395_c> Y_601_j = Maps.newEnumMap((Map)ImmutableMap.of((Object)b_257_Y.R_4764_Y, (Object)T_2915_h.n_1700_B(3.0, 0.0, 0.0, 13.0, 1.0, 13.0), (Object)b_257_Y.G_564_y, (Object)T_2915_h.n_1700_B(3.0, 0.0, 3.0, 13.0, 1.0, 16.0), (Object)b_257_Y.u_1723_Y, (Object)T_2915_h.n_1700_B(3.0, 0.0, 3.0, 16.0, 1.0, 13.0), (Object)b_257_Y.P_1922_E, (Object)T_2915_h.n_1700_B(0.0, 0.0, 3.0, 13.0, 1.0, 13.0)));
    private static final Map<b_257_Y, s_1395_c> Y_259_p = Maps.newEnumMap((Map)ImmutableMap.of((Object)b_257_Y.R_4764_Y, (Object)x_268_Y.n_1700_B(Y_601_j.get(b_257_Y.R_4764_Y), T_2915_h.n_1700_B(3.0, 0.0, 0.0, 13.0, 16.0, 1.0)), (Object)b_257_Y.G_564_y, (Object)x_268_Y.n_1700_B(Y_601_j.get(b_257_Y.G_564_y), T_2915_h.n_1700_B(3.0, 0.0, 15.0, 13.0, 16.0, 16.0)), (Object)b_257_Y.u_1723_Y, (Object)x_268_Y.n_1700_B(Y_601_j.get(b_257_Y.u_1723_Y), T_2915_h.n_1700_B(15.0, 0.0, 3.0, 16.0, 16.0, 13.0)), (Object)b_257_Y.P_1922_E, (Object)x_268_Y.n_1700_B(Y_601_j.get(b_257_Y.P_1922_E), T_2915_h.n_1700_B(0.0, 0.0, 3.0, 1.0, 16.0, 13.0))));
    private final Map<K_4074_S, s_1395_c> Q_2552_b = Maps.newHashMap();
    private static final M_1336_P[] C_2741_M = new M_1336_P[16];
    private final K_4074_S k_2293_S;
    private boolean q_2307_F = true;

    public Z_4734_t(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, y_1030_X.R_4764_Y)).n_1700_B(h_1847_R, y_1030_X.R_4764_Y)).n_1700_B(Q_4569_t, y_1030_X.R_4764_Y)).n_1700_B(M_182_A, y_1030_X.R_4764_Y)).n_1700_B(t_1786_h, 0));
        this.k_2293_S = (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, y_1030_X.J_1907_R)).n_1700_B(h_1847_R, y_1030_X.J_1907_R)).n_1700_B(Q_4569_t, y_1030_X.J_1907_R)).n_1700_B(M_182_A, y_1030_X.J_1907_R);
        for (K_4074_S blockstate : this.t_1786_h().n_1700_B()) {
            if (blockstate.R_4764_Y(t_1786_h) != 0) continue;
            this.Q_2552_b.put(blockstate, this.t_148_a(blockstate));
        }
    }

    private s_1395_c t_148_a(K_4074_S state) {
        s_1395_c voxelshape = w_1457_N;
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            y_1030_X redstoneside = (y_1030_X)state.R_4764_Y(multiplayerClientSuggestionProvider.get(direction));
            if (redstoneside == y_1030_X.J_1907_R) {
                voxelshape = x_268_Y.n_1700_B(voxelshape, Y_601_j.get(direction));
                continue;
            }
            if (redstoneside != y_1030_X.n_1700_B) continue;
            voxelshape = x_268_Y.n_1700_B(voxelshape, Y_259_p.get(direction));
        }
        return voxelshape;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.Q_2552_b.get(state.n_1700_B(t_1786_h, 0));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return this.n_1700_B((BlockGetter)context.getWorld(), this.k_2293_S, context.getPos());
    }

    private K_4074_S n_1700_B(BlockGetter reader, K_4074_S state, c_1514_x pos) {
        boolean flag6;
        boolean flag = Z_4734_t.h_1847_R(state);
        state = this.J_1907_R(reader, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(t_1786_h, state.R_4764_Y(t_1786_h)), pos);
        if (flag && Z_4734_t.h_1847_R(state)) {
            return state;
        }
        boolean flag1 = state.R_4764_Y(P_4830_p).J_1907_R();
        boolean flag2 = state.R_4764_Y(Q_4569_t).J_1907_R();
        boolean flag3 = state.R_4764_Y(h_1847_R).J_1907_R();
        boolean flag4 = state.R_4764_Y(M_182_A).J_1907_R();
        boolean flag5 = !flag1 && !flag2;
        boolean bl = flag6 = !flag3 && !flag4;
        if (!flag4 && flag5) {
            state = (K_4074_S)state.n_1700_B(M_182_A, y_1030_X.J_1907_R);
        }
        if (!flag3 && flag5) {
            state = (K_4074_S)state.n_1700_B(h_1847_R, y_1030_X.J_1907_R);
        }
        if (!flag1 && flag6) {
            state = (K_4074_S)state.n_1700_B(P_4830_p, y_1030_X.J_1907_R);
        }
        if (!flag2 && flag6) {
            state = (K_4074_S)state.n_1700_B(Q_4569_t, y_1030_X.J_1907_R);
        }
        return state;
    }

    private K_4074_S J_1907_R(BlockGetter reader, K_4074_S state, c_1514_x pos) {
        boolean flag = !reader.getBlockState(pos.up()).v_4262_N(reader, pos);
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            if (((y_1030_X)state.R_4764_Y(multiplayerClientSuggestionProvider.get(direction))).J_1907_R()) continue;
            y_1030_X redstoneside = this.n_1700_B(reader, pos, direction, flag);
            state = (K_4074_S)state.n_1700_B(multiplayerClientSuggestionProvider.get(direction), redstoneside);
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == b_257_Y.n_1700_B) {
            return stateIn;
        }
        if (facing == b_257_Y.J_1907_R) {
            return this.n_1700_B((BlockGetter)worldIn, stateIn, currentPos);
        }
        y_1030_X redstoneside = this.n_1700_B((BlockGetter)worldIn, currentPos, facing);
        return redstoneside.J_1907_R() == ((y_1030_X)stateIn.R_4764_Y(multiplayerClientSuggestionProvider.get(facing))).J_1907_R() && !Z_4734_t.P_4830_p(stateIn) ? (K_4074_S)stateIn.n_1700_B(multiplayerClientSuggestionProvider.get(facing), redstoneside) : this.n_1700_B((BlockGetter)worldIn, (K_4074_S)((K_4074_S)this.k_2293_S.n_1700_B(t_1786_h, stateIn.R_4764_Y(t_1786_h))).n_1700_B(multiplayerClientSuggestionProvider.get(facing), redstoneside), currentPos);
    }

    private static boolean P_4830_p(K_4074_S state) {
        return state.R_4764_Y(P_4830_p).J_1907_R() && state.R_4764_Y(Q_4569_t).J_1907_R() && state.R_4764_Y(h_1847_R).J_1907_R() && state.R_4764_Y(M_182_A).J_1907_R();
    }

    private static boolean h_1847_R(K_4074_S state) {
        return !state.R_4764_Y(P_4830_p).J_1907_R() && !state.R_4764_Y(Q_4569_t).J_1907_R() && !state.R_4764_Y(h_1847_R).J_1907_R() && !state.R_4764_Y(M_182_A).J_1907_R();
    }

    @Override
    public void n_1700_B(K_4074_S state, LevelAccessor worldIn, c_1514_x pos, int flags, int recursionLeft) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            y_1030_X redstoneside = (y_1030_X)state.R_4764_Y(multiplayerClientSuggestionProvider.get(direction));
            if (redstoneside == y_1030_X.R_4764_Y || worldIn.getBlockState(blockpos$mutable.n_1700_B(pos, direction)).n_1700_B(this)) continue;
            blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            K_4074_S blockstate = worldIn.getBlockState(blockpos$mutable);
            if (!blockstate.n_1700_B(a_3742_W.RegionExploit)) {
                c_1514_x blockpos = blockpos$mutable.offset(direction.u_1723_Y());
                K_4074_S blockstate1 = blockstate.n_1700_B(direction.u_1723_Y(), worldIn.getBlockState(blockpos), worldIn, (c_1514_x)blockpos$mutable, blockpos);
                Z_4734_t.n_1700_B(blockstate, blockstate1, worldIn, blockpos$mutable, flags, recursionLeft);
            }
            blockpos$mutable.n_1700_B(pos, direction).n_1700_B(b_257_Y.J_1907_R);
            K_4074_S blockstate3 = worldIn.getBlockState(blockpos$mutable);
            if (blockstate3.n_1700_B(a_3742_W.RegionExploit)) continue;
            c_1514_x blockpos1 = blockpos$mutable.offset(direction.u_1723_Y());
            K_4074_S blockstate2 = blockstate3.n_1700_B(direction.u_1723_Y(), worldIn.getBlockState(blockpos1), worldIn, (c_1514_x)blockpos$mutable, blockpos1);
            Z_4734_t.n_1700_B(blockstate3, blockstate2, worldIn, blockpos$mutable, flags, recursionLeft);
        }
    }

    private y_1030_X n_1700_B(BlockGetter worldIn, c_1514_x pos, b_257_Y face) {
        return this.n_1700_B(worldIn, pos, face, !worldIn.getBlockState(pos.up()).v_4262_N(worldIn, pos));
    }

    private y_1030_X n_1700_B(BlockGetter reader, c_1514_x pos, b_257_Y direction, boolean nonNormalCubeAbove) {
        boolean flag;
        c_1514_x blockpos = pos.offset(direction);
        K_4074_S blockstate = reader.getBlockState(blockpos);
        if (nonNormalCubeAbove && (flag = this.J_1907_R(reader, blockpos, blockstate)) && Z_4734_t.w_1484_f(reader.getBlockState(blockpos.up()))) {
            if (blockstate.G_564_y(reader, blockpos, direction.u_1723_Y())) {
                return y_1030_X.n_1700_B;
            }
            return y_1030_X.J_1907_R;
        }
        return !Z_4734_t.n_1700_B(blockstate, direction) && (blockstate.v_4262_N(reader, blockpos) || !Z_4734_t.w_1484_f(reader.getBlockState(blockpos.down()))) ? y_1030_X.R_4764_Y : y_1030_X.J_1907_R;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        return this.J_1907_R((BlockGetter)worldIn, blockpos, blockstate);
    }

    private boolean J_1907_R(BlockGetter reader, c_1514_x pos, K_4074_S state) {
        return state.G_564_y(reader, pos, b_257_Y.J_1907_R) || state.n_1700_B(a_3742_W.p_3749_n);
    }

    private void n_1700_B(b_4507_u world, c_1514_x pos, K_4074_S state) {
        int i = this.n_1700_B(world, pos);
        if (state.R_4764_Y(t_1786_h) != i) {
            if (world.getBlockState(pos) == state) {
                world.n_1700_B(pos, (K_4074_S)state.n_1700_B(t_1786_h, i), 2);
            }
            HashSet set = Sets.newHashSet();
            set.add(pos);
            for (b_257_Y direction : b_257_Y.values()) {
                set.add(pos.offset(direction));
            }
            for (c_1514_x blockpos : set) {
                world.J_1907_R(blockpos, this);
            }
        }
    }

    private int n_1700_B(b_4507_u world, c_1514_x pos) {
        this.q_2307_F = false;
        int i = world.Y_259_p(pos);
        this.q_2307_F = true;
        int j = 0;
        if (i < 15) {
            for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                c_1514_x blockpos = pos.offset(direction);
                K_4074_S blockstate = world.getBlockState(blockpos);
                j = Math.max(j, this.Q_4569_t(blockstate));
                c_1514_x blockpos1 = pos.up();
                if (blockstate.v_4262_N(world, blockpos) && !world.getBlockState(blockpos1).v_4262_N(world, blockpos1)) {
                    j = Math.max(j, this.Q_4569_t(world.getBlockState(blockpos.up())));
                    continue;
                }
                if (blockstate.v_4262_N(world, blockpos)) continue;
                j = Math.max(j, this.Q_4569_t(world.getBlockState(blockpos.down())));
            }
        }
        return Math.max(i, j - 1);
    }

    private int Q_4569_t(K_4074_S state) {
        return state.n_1700_B(this) ? state.R_4764_Y(t_1786_h) : 0;
    }

    private void J_1907_R(b_4507_u worldIn, c_1514_x pos) {
        if (worldIn.getBlockState(pos).n_1700_B(this)) {
            worldIn.J_1907_R(pos, this);
            for (b_257_Y direction : b_257_Y.values()) {
                worldIn.J_1907_R(pos.offset(direction), this);
            }
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R()) && !worldIn.Y_259_p) {
            this.n_1700_B(worldIn, pos, state);
            for (b_257_Y direction : b_257_Y.R_4764_Y.J_1907_R) {
                worldIn.J_1907_R(pos.offset(direction), this);
            }
            this.G_564_y(worldIn, pos);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving && !state.n_1700_B(newState.J_1907_R())) {
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
            if (!worldIn.Y_259_p) {
                for (b_257_Y direction : b_257_Y.values()) {
                    worldIn.J_1907_R(pos.offset(direction), this);
                }
                this.n_1700_B(worldIn, pos, state);
                this.G_564_y(worldIn, pos);
            }
        }
    }

    private void G_564_y(b_4507_u world, c_1514_x pos) {
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            this.J_1907_R(world, pos.offset(direction));
        }
        for (b_257_Y direction1 : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = pos.offset(direction1);
            if (world.getBlockState(blockpos).v_4262_N(world, blockpos)) {
                this.J_1907_R(world, blockpos.up());
                continue;
            }
            this.J_1907_R(world, blockpos.down());
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (!worldIn.Y_259_p) {
            if (state.n_1700_B((T_1316_M)worldIn, pos)) {
                this.n_1700_B(worldIn, pos, state);
            } else {
                Z_4734_t.G_564_y(state, worldIn, pos);
                worldIn.n_1700_B(pos, false);
            }
        }
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return !this.q_2307_F ? 0 : blockState.J_1907_R(blockAccess, pos, side);
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        if (this.q_2307_F && side != b_257_Y.n_1700_B) {
            int i = blockState.R_4764_Y(t_1786_h);
            if (i == 0) {
                return 0;
            }
            return side != b_257_Y.J_1907_R && !((y_1030_X)this.n_1700_B(blockAccess, blockState, pos).R_4764_Y(multiplayerClientSuggestionProvider.get(side.u_1723_Y()))).J_1907_R() ? 0 : i;
        }
        return 0;
    }

    protected static boolean w_1484_f(K_4074_S state) {
        return Z_4734_t.n_1700_B(state, (b_257_Y)null);
    }

    protected static boolean n_1700_B(K_4074_S blockState, @Nullable b_257_Y side) {
        if (blockState.n_1700_B(a_3742_W.P_5000_x)) {
            return true;
        }
        if (blockState.n_1700_B(a_3742_W.T_437_o)) {
            b_257_Y direction = blockState.R_4764_Y(RepeaterBlock.w_612_n);
            return direction == side || direction.u_1723_Y() == side;
        }
        if (blockState.n_1700_B(a_3742_W.RegionExploit)) {
            return side == blockState.R_4764_Y(ObserverBlock.P_4830_p);
        }
        return blockState.t_148_a() && side != null;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return this.q_2307_F;
    }

    public static int J_1907_R(int power) {
        M_1336_P vector3f = C_2741_M[power];
        return u_530_F.P_1922_E(vector3f.n_1700_B(), vector3f.J_1907_R(), vector3f.R_4764_Y());
    }

    private void n_1700_B(b_4507_u world, Random rand, c_1514_x pos, M_1336_P rgbVector, b_257_Y directionFrom, b_257_Y directionTo, float minChance, float maxChance) {
        float f = maxChance - minChance;
        if (!(rand.nextFloat() >= 0.2f * f)) {
            float f1 = 0.4375f;
            float f2 = minChance + f * rand.nextFloat();
            double d0 = 0.5 + (double)(0.4375f * (float)directionFrom.t_148_a()) + (double)(f2 * (float)directionTo.t_148_a());
            double d1 = 0.5 + (double)(0.4375f * (float)directionFrom.s_956_w()) + (double)(f2 * (float)directionTo.s_956_w());
            double d2 = 0.5 + (double)(0.4375f * (float)directionFrom.u_2550_I()) + (double)(f2 * (float)directionTo.u_2550_I());
            world.n_1700_B(new DustParticleOptions(rgbVector.n_1700_B(), rgbVector.J_1907_R(), rgbVector.R_4764_Y(), 1.0f), (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        int i = stateIn.R_4764_Y(t_1786_h);
        if (i != 0) {
            block4: for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                y_1030_X redstoneside = (y_1030_X)stateIn.R_4764_Y(multiplayerClientSuggestionProvider.get(direction));
                switch (redstoneside) {
                    case n_1700_B: {
                        this.n_1700_B(worldIn, rand, pos, C_2741_M[i], direction, b_257_Y.J_1907_R, -0.5f, 0.5f);
                    }
                    case J_1907_R: {
                        this.n_1700_B(worldIn, rand, pos, C_2741_M[i], b_257_Y.n_1700_B, direction, 0.0f, 0.5f);
                        continue block4;
                    }
                }
                this.n_1700_B(worldIn, rand, pos, C_2741_M[i], b_257_Y.n_1700_B, direction, 0.0f, 0.3f);
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(Q_4569_t))).n_1700_B(h_1847_R, state.R_4764_Y(M_182_A))).n_1700_B(Q_4569_t, state.R_4764_Y(P_4830_p))).n_1700_B(M_182_A, state.R_4764_Y(h_1847_R));
            }
            case G_564_y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(h_1847_R))).n_1700_B(h_1847_R, state.R_4764_Y(Q_4569_t))).n_1700_B(Q_4569_t, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(P_4830_p));
            }
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(M_182_A))).n_1700_B(h_1847_R, state.R_4764_Y(P_4830_p))).n_1700_B(Q_4569_t, state.R_4764_Y(h_1847_R))).n_1700_B(M_182_A, state.R_4764_Y(Q_4569_t));
            }
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        switch (mirrorIn) {
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(Q_4569_t))).n_1700_B(Q_4569_t, state.R_4764_Y(P_4830_p));
            }
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(h_1847_R));
            }
        }
        return super.n_1700_B(state, mirrorIn);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (!player.C_415_h.P_1922_E) {
            return m_3054_I.R_4764_Y;
        }
        if (Z_4734_t.P_4830_p(state) || Z_4734_t.h_1847_R(state)) {
            K_4074_S blockstate = Z_4734_t.P_4830_p(state) ? this.multiplayerClientSuggestionProvider() : this.k_2293_S;
            blockstate = (K_4074_S)blockstate.n_1700_B(t_1786_h, state.R_4764_Y(t_1786_h));
            if ((blockstate = this.n_1700_B((BlockGetter)worldIn, blockstate, pos)) != state) {
                worldIn.n_1700_B(pos, blockstate, 3);
                this.n_1700_B(worldIn, pos, state, blockstate);
                return m_3054_I.n_1700_B;
            }
        }
        return m_3054_I.R_4764_Y;
    }

    private void n_1700_B(b_4507_u world, c_1514_x pos, K_4074_S prevState, K_4074_S newState) {
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = pos.offset(direction);
            if (((y_1030_X)prevState.R_4764_Y(multiplayerClientSuggestionProvider.get(direction))).J_1907_R() == ((y_1030_X)newState.R_4764_Y(multiplayerClientSuggestionProvider.get(direction))).J_1907_R() || !world.getBlockState(blockpos).v_4262_N(world, blockpos)) continue;
            world.n_1700_B(blockpos, newState.J_1907_R(), direction.u_1723_Y());
        }
    }

    static {
        for (int i = 0; i <= 15; ++i) {
            float f;
            float f1 = f * 0.6f + ((f = (float)i / 15.0f) > 0.0f ? 0.4f : 0.3f);
            float f2 = u_530_F.n_1700_B(f * f * 0.7f - 0.5f, 0.0f, 1.0f);
            float f3 = u_530_F.n_1700_B(f * f * 0.6f - 0.7f, 0.0f, 1.0f);
            Z_4734_t.C_2741_M[i] = new M_1336_P(f1, f2, f3);
        }
    }
}



