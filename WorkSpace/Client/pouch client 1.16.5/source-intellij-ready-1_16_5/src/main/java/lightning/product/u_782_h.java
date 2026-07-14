/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.V_4824_J;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.Z_4734_t;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;

public abstract class u_782_h
extends HorizontalDirectionalBlock {
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    public static final U_1266_O Q_4569_t = BlockStateProperties.C_2741_M;

    protected u_782_h(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return u_782_h.R_4764_Y(worldIn, pos.down());
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!this.n_1700_B((T_1316_M)worldIn, pos, state)) {
            boolean flag = state.R_4764_Y(Q_4569_t);
            boolean flag1 = this.n_1700_B((b_4507_u)worldIn, pos, state);
            if (flag && !flag1) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4569_t, false), 2);
            } else if (!flag) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4569_t, true), 2);
                if (!flag1) {
                    worldIn.Q_2552_b().n_1700_B(pos, this, this.w_1484_f(state), V_4824_J.J_1907_R);
                }
            }
        }
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.J_1907_R(blockAccess, pos, side);
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        if (!blockState.R_4764_Y(Q_4569_t).booleanValue()) {
            return 0;
        }
        return blockState.R_4764_Y(w_612_n) == side ? this.J_1907_R(blockAccess, pos, blockState) : 0;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (state.n_1700_B((T_1316_M)worldIn, pos)) {
            this.R_4764_Y(worldIn, pos, state);
        } else {
            i_2154_H tileentity = this.G_564_y() ? worldIn.getTileEntity(pos) : null;
            u_782_h.n_1700_B(state, worldIn, pos, tileentity);
            worldIn.n_1700_B(pos, false);
            for (b_257_Y direction : b_257_Y.values()) {
                worldIn.J_1907_R(pos.offset(direction), this);
            }
        }
    }

    protected void R_4764_Y(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        boolean flag1;
        boolean flag;
        if (!this.n_1700_B((T_1316_M)worldIn, pos, state) && (flag = state.R_4764_Y(Q_4569_t).booleanValue()) != (flag1 = this.n_1700_B(worldIn, pos, state)) && !worldIn.u_2550_I().J_1907_R(pos, this)) {
            V_4824_J tickpriority = V_4824_J.R_4764_Y;
            if (this.R_4764_Y((BlockGetter)worldIn, pos, state)) {
                tickpriority = V_4824_J.n_1700_B;
            } else if (flag) {
                tickpriority = V_4824_J.J_1907_R;
            }
            worldIn.u_2550_I().n_1700_B(pos, this, this.w_1484_f(state), tickpriority);
        }
    }

    public boolean n_1700_B(T_1316_M worldIn, c_1514_x pos, K_4074_S state) {
        return false;
    }

    protected boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        return this.J_1907_R(worldIn, pos, state) > 0;
    }

    protected int J_1907_R(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(w_612_n);
        c_1514_x blockpos = pos.offset(direction);
        int i = worldIn.R_4764_Y(blockpos, direction);
        if (i >= 15) {
            return i;
        }
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        return Math.max(i, blockstate.n_1700_B(a_3742_W.P_5000_x) ? blockstate.R_4764_Y(Z_4734_t.t_1786_h) : 0);
    }

    protected int J_1907_R(T_1316_M worldIn, c_1514_x pos, K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(w_612_n);
        b_257_Y direction1 = direction.v_4262_N();
        b_257_Y direction2 = direction.w_1484_f();
        return Math.max(this.J_1907_R(worldIn, pos.offset(direction1), direction1), this.J_1907_R(worldIn, pos.offset(direction2), direction2));
    }

    protected int J_1907_R(T_1316_M worldIn, c_1514_x pos, b_257_Y side) {
        K_4074_S blockstate = worldIn.getBlockState(pos);
        if (this.t_148_a(blockstate)) {
            if (blockstate.n_1700_B(a_3742_W.s_3815_K)) {
                return 15;
            }
            return blockstate.n_1700_B(a_3742_W.P_5000_x) ? blockstate.R_4764_Y(Z_4734_t.t_1786_h).intValue() : worldIn.n_1700_B(pos, side);
        }
        return 0;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(w_612_n, context.getPlacementHorizontalFacing().u_1723_Y());
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        if (this.n_1700_B(worldIn, pos, state)) {
            worldIn.u_2550_I().n_1700_B(pos, this, 1);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        this.G_564_y(worldIn, pos, state);
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving && !state.n_1700_B(newState.J_1907_R())) {
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
            this.G_564_y(worldIn, pos, state);
        }
    }

    protected void G_564_y(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(w_612_n);
        c_1514_x blockpos = pos.offset(direction.u_1723_Y());
        worldIn.n_1700_B(blockpos, (T_2915_h)this, pos);
        worldIn.n_1700_B(blockpos, (T_2915_h)this, direction);
    }

    protected boolean t_148_a(K_4074_S state) {
        return state.t_148_a();
    }

    protected int J_1907_R(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return 15;
    }

    public static boolean P_4830_p(K_4074_S state) {
        return state.J_1907_R() instanceof u_782_h;
    }

    public boolean R_4764_Y(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(w_612_n).u_1723_Y();
        K_4074_S blockstate = worldIn.getBlockState(pos.offset(direction));
        return u_782_h.P_4830_p(blockstate) && blockstate.R_4764_Y(w_612_n) != direction;
    }

    protected abstract int w_1484_f(K_4074_S var1);
}


