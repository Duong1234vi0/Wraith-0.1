/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_2334_m;
import lightning.product.CollisionContext;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.v_3760_Q;
import lightning.product.w_1454_v;
import lightning.product.w_801_N;

public abstract class g_2711_h
extends T_2915_h {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    private final boolean Q_4569_t;

    public static boolean n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        return g_2711_h.v_4262_N(worldIn.getBlockState(pos));
    }

    public static boolean v_4262_N(K_4074_S state) {
        return state.n_1700_B(BlockTags.n_3318_d) && state.J_1907_R() instanceof g_2711_h;
    }

    protected g_2711_h(boolean isDisableCorner, q_4293_E.P_1922_E builder) {
        super(builder);
        this.Q_4569_t = isDisableCorner;
    }

    public boolean J_1907_R() {
        return this.Q_4569_t;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        w_801_N railshape = state.n_1700_B(this) ? state.R_4764_Y(this.t_148_a()) : null;
        return railshape != null && railshape.J_1907_R() ? h_1847_R : P_4830_p;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return g_2711_h.R_4764_Y(worldIn, pos.down());
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R())) {
            this.n_1700_B(state, worldIn, pos, isMoving);
        }
    }

    protected K_4074_S n_1700_B(K_4074_S state, b_4507_u world, c_1514_x pos, boolean isMoving) {
        state = this.n_1700_B(world, pos, state, true);
        if (this.Q_4569_t) {
            state.n_1700_B(world, pos, this, pos, isMoving);
        }
        return state;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (!worldIn.Y_259_p && worldIn.getBlockState(pos).n_1700_B(this)) {
            w_801_N railshape = state.R_4764_Y(this.t_148_a());
            if (g_2711_h.n_1700_B(pos, worldIn, railshape)) {
                g_2711_h.G_564_y(state, worldIn, pos);
                worldIn.n_1700_B(pos, isMoving);
            } else {
                this.n_1700_B(state, worldIn, pos, blockIn);
            }
        }
    }

    private static boolean n_1700_B(c_1514_x pos, b_4507_u world, w_801_N railShape) {
        if (!g_2711_h.R_4764_Y(world, pos.down())) {
            return true;
        }
        switch (railShape) {
            case R_4764_Y: {
                return !g_2711_h.R_4764_Y(world, pos.east());
            }
            case G_564_y: {
                return !g_2711_h.R_4764_Y(world, pos.west());
            }
            case P_1922_E: {
                return !g_2711_h.R_4764_Y(world, pos.north());
            }
            case u_1723_Y: {
                return !g_2711_h.R_4764_Y(world, pos.south());
            }
        }
        return false;
    }

    protected void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn) {
    }

    protected K_4074_S n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, boolean placing) {
        if (worldIn.Y_259_p) {
            return state;
        }
        w_801_N railshape = state.R_4764_Y(this.t_148_a());
        return new U_2334_m(worldIn, pos, state).n_1700_B(worldIn.Y_601_j(pos), placing, railshape).R_4764_Y();
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.n_1700_B;
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving) {
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
            if (state.R_4764_Y(this.t_148_a()).J_1907_R()) {
                worldIn.J_1907_R(pos.up(), this);
            }
            if (this.Q_4569_t) {
                worldIn.J_1907_R(pos, this);
                worldIn.J_1907_R(pos.down(), this);
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = super.multiplayerClientSuggestionProvider();
        b_257_Y direction = context.getPlacementHorizontalFacing();
        boolean flag = direction == b_257_Y.u_1723_Y || direction == b_257_Y.P_1922_E;
        return (K_4074_S)blockstate.n_1700_B(this.t_148_a(), flag ? w_801_N.J_1907_R : w_801_N.n_1700_B);
    }

    public abstract v_3760_Q<w_801_N> t_148_a();
}


