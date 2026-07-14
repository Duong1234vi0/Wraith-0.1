/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import java.util.stream.IntStream;
import lightning.product.BlockStateProperties;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.Fluids;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.e_563_h;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.DirectionProperty;
import lightning.product.m_2244_y;
import lightning.product.m_3054_I;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.u_863_c;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.y_3008_A;

public class z_2909_G
extends T_2915_h
implements SimpleWaterloggedBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final e_563_h<m_2244_y> h_1847_R = BlockStateProperties.D_4792_h;
    public static final e_563_h<u_863_c> Q_4569_t = BlockStateProperties.F_1410_V;
    public static final U_1266_O M_182_A = BlockStateProperties.A_4115_X;
    protected static final s_1395_c t_1786_h = y_3008_A.M_182_A;
    protected static final s_1395_c multiplayerClientSuggestionProvider = y_3008_A.Q_4569_t;
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(0.0, 0.0, 8.0, 8.0, 8.0, 16.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(0.0, 8.0, 0.0, 8.0, 16.0, 8.0);
    protected static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(0.0, 8.0, 8.0, 8.0, 16.0, 16.0);
    protected static final s_1395_c C_2741_M = T_2915_h.n_1700_B(8.0, 0.0, 0.0, 16.0, 8.0, 8.0);
    protected static final s_1395_c k_2293_S = T_2915_h.n_1700_B(8.0, 0.0, 8.0, 16.0, 8.0, 16.0);
    protected static final s_1395_c q_2307_F = T_2915_h.n_1700_B(8.0, 8.0, 0.0, 16.0, 16.0, 8.0);
    protected static final s_1395_c Z_875_P = T_2915_h.n_1700_B(8.0, 8.0, 8.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c[] c_3005_b = z_2909_G.n_1700_B(t_1786_h, w_1457_N, C_2741_M, Y_601_j, k_2293_S);
    protected static final s_1395_c[] H_2857_Y = z_2909_G.n_1700_B(multiplayerClientSuggestionProvider, Y_259_p, q_2307_F, Q_2552_b, Z_875_P);
    private static final int[] A_4115_X = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
    private final T_2915_h e_4240_b;
    private final K_4074_S n_3318_d;

    private static s_1395_c[] n_1700_B(s_1395_c slabShape, s_1395_c nwCorner, s_1395_c neCorner, s_1395_c swCorner, s_1395_c seCorner) {
        return (s_1395_c[])IntStream.range(0, 16).mapToObj(bits -> z_2909_G.n_1700_B(bits, slabShape, nwCorner, neCorner, swCorner, seCorner)).toArray(s_1395_c[]::new);
    }

    private static s_1395_c n_1700_B(int bitfield, s_1395_c slabShape, s_1395_c nwCorner, s_1395_c neCorner, s_1395_c swCorner, s_1395_c seCorner) {
        s_1395_c voxelshape = slabShape;
        if ((bitfield & 1) != 0) {
            voxelshape = x_268_Y.n_1700_B(slabShape, nwCorner);
        }
        if ((bitfield & 2) != 0) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, neCorner);
        }
        if ((bitfield & 4) != 0) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, swCorner);
        }
        if ((bitfield & 8) != 0) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, seCorner);
        }
        return voxelshape;
    }

    protected z_2909_G(K_4074_S state, q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, m_2244_y.J_1907_R)).n_1700_B(Q_4569_t, u_863_c.n_1700_B)).n_1700_B(M_182_A, false));
        this.e_4240_b = state.J_1907_R();
        this.n_3318_d = state;
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return (state.R_4764_Y(h_1847_R) == m_2244_y.n_1700_B ? c_3005_b : H_2857_Y)[A_4115_X[this.t_148_a(state)]];
    }

    private int t_148_a(K_4074_S state) {
        return state.R_4764_Y(Q_4569_t).ordinal() * 4 + state.R_4764_Y(P_4830_p).G_564_y();
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        this.e_4240_b.n_1700_B(stateIn, worldIn, pos, rand);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        this.n_3318_d.n_1700_B(worldIn, pos, player);
    }

    @Override
    public void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
        this.e_4240_b.n_1700_B(worldIn, pos, state);
    }

    @Override
    public float u_2550_I() {
        return this.e_4240_b.u_2550_I();
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!state.n_1700_B(state.J_1907_R())) {
            this.n_3318_d.n_1700_B(worldIn, pos, a_3742_W.n_1700_B, pos, false);
            this.e_4240_b.n_1700_B(this.n_3318_d, worldIn, pos, oldState, false);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            this.n_3318_d.J_1907_R(worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        this.e_4240_b.n_1700_B(worldIn, pos, entityIn);
    }

    @Override
    public boolean a_(K_4074_S state) {
        return this.e_4240_b.a_(state);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        this.e_4240_b.n_1700_B(state, worldIn, pos, random);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        this.e_4240_b.J_1907_R(state, worldIn, pos, rand);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        return this.n_3318_d.n_1700_B(worldIn, player, handIn, hit);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, F_1241_B explosionIn) {
        this.e_4240_b.n_1700_B(worldIn, pos, explosionIn);
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y direction = context.getFace();
        c_1514_x blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(blockpos);
        K_4074_S blockstate = (K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing())).n_1700_B(h_1847_R, !(direction == b_257_Y.n_1700_B || direction != b_257_Y.J_1907_R && context.getHitVec().R_4764_Y - (double)blockpos.getY() > 0.5) ? m_2244_y.J_1907_R : m_2244_y.n_1700_B)).n_1700_B(M_182_A, fluidstate.n_1700_B() == Fluids.R_4764_Y);
        return (K_4074_S)blockstate.n_1700_B(Q_4569_t, z_2909_G.v_4262_N(blockstate, context.getWorld(), blockpos));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(M_182_A).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return facing.h_1847_R().G_564_y() ? (K_4074_S)stateIn.n_1700_B(Q_4569_t, z_2909_G.v_4262_N(stateIn, worldIn, currentPos)) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    private static u_863_c v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        b_257_Y direction2;
        b_257_Y direction1;
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        K_4074_S blockstate = worldIn.getBlockState(pos.offset(direction));
        if (z_2909_G.w_1484_f(blockstate) && state.R_4764_Y(h_1847_R) == blockstate.R_4764_Y(h_1847_R) && (direction1 = blockstate.R_4764_Y(P_4830_p)).h_1847_R() != state.R_4764_Y(P_4830_p).h_1847_R() && z_2909_G.G_564_y(state, worldIn, pos, direction1.u_1723_Y())) {
            if (direction1 == direction.w_1484_f()) {
                return u_863_c.G_564_y;
            }
            return u_863_c.P_1922_E;
        }
        K_4074_S blockstate1 = worldIn.getBlockState(pos.offset(direction.u_1723_Y()));
        if (z_2909_G.w_1484_f(blockstate1) && state.R_4764_Y(h_1847_R) == blockstate1.R_4764_Y(h_1847_R) && (direction2 = blockstate1.R_4764_Y(P_4830_p)).h_1847_R() != state.R_4764_Y(P_4830_p).h_1847_R() && z_2909_G.G_564_y(state, worldIn, pos, direction2)) {
            if (direction2 == direction.w_1484_f()) {
                return u_863_c.J_1907_R;
            }
            return u_863_c.R_4764_Y;
        }
        return u_863_c.n_1700_B;
    }

    private static boolean G_564_y(K_4074_S state, BlockGetter worldIn, c_1514_x pos, b_257_Y face) {
        K_4074_S blockstate = worldIn.getBlockState(pos.offset(face));
        return !z_2909_G.w_1484_f(blockstate) || blockstate.R_4764_Y(P_4830_p) != state.R_4764_Y(P_4830_p) || blockstate.R_4764_Y(h_1847_R) != state.R_4764_Y(h_1847_R);
    }

    public static boolean w_1484_f(K_4074_S state) {
        return state.J_1907_R() instanceof z_2909_G;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        u_863_c stairsshape = state.R_4764_Y(Q_4569_t);
        switch (mirrorIn) {
            case J_1907_R: {
                if (direction.h_1847_R() != b_257_Y.n_1700_B.R_4764_Y) break;
                switch (stairsshape) {
                    case J_1907_R: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.R_4764_Y);
                    }
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.J_1907_R);
                    }
                    case G_564_y: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.P_1922_E);
                    }
                    case P_1922_E: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.G_564_y);
                    }
                }
                return state.n_1700_B(W_2163_m.R_4764_Y);
            }
            case R_4764_Y: {
                if (direction.h_1847_R() != b_257_Y.n_1700_B.n_1700_B) break;
                switch (stairsshape) {
                    case J_1907_R: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.J_1907_R);
                    }
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.R_4764_Y);
                    }
                    case G_564_y: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.P_1922_E);
                    }
                    case P_1922_E: {
                        return (K_4074_S)state.n_1700_B(W_2163_m.R_4764_Y).n_1700_B(Q_4569_t, u_863_c.G_564_y);
                    }
                    case n_1700_B: {
                        return state.n_1700_B(W_2163_m.R_4764_Y);
                    }
                }
            }
        }
        return super.n_1700_B(state, mirrorIn);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t, M_182_A);
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(M_182_A) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


