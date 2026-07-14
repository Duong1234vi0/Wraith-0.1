/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.DirectionalBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class ObserverBlock
extends DirectionalBlock {
    public static final U_1266_O h_1847_R = BlockStateProperties.C_2741_M;

    public ObserverBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.G_564_y)).n_1700_B(h_1847_R, false));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (state.R_4764_Y(h_1847_R).booleanValue()) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, false), 2);
        } else {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, true), 2);
            worldIn.Q_2552_b().n_1700_B(pos, this, 2);
        }
        this.n_1700_B((b_4507_u)worldIn, pos, state);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(P_4830_p) == facing && !stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            this.n_1700_B(worldIn, currentPos);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    private void n_1700_B(LevelAccessor worldIn, c_1514_x pos) {
        if (!worldIn.v_4276_D() && !worldIn.u_2550_I().n_1700_B(pos, this)) {
            worldIn.u_2550_I().n_1700_B(pos, this, 2);
        }
    }

    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        c_1514_x blockpos = pos.offset(direction.u_1723_Y());
        worldIn.n_1700_B(blockpos, (T_2915_h)this, pos);
        worldIn.n_1700_B(blockpos, (T_2915_h)this, direction);
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.J_1907_R(blockAccess, pos, side);
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(h_1847_R) != false && blockState.R_4764_Y(P_4830_p) == side ? 15 : 0;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!state.n_1700_B(oldState.J_1907_R()) && !worldIn.v_4276_D() && state.R_4764_Y(h_1847_R).booleanValue() && !worldIn.u_2550_I().n_1700_B(pos, this)) {
            K_4074_S blockstate = (K_4074_S)state.n_1700_B(h_1847_R, false);
            worldIn.n_1700_B(pos, blockstate, 18);
            this.n_1700_B(worldIn, pos, blockstate);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R()) && !worldIn.Y_259_p && state.R_4764_Y(h_1847_R).booleanValue() && worldIn.u_2550_I().n_1700_B(pos, this)) {
            this.n_1700_B(worldIn, pos, (K_4074_S)state.n_1700_B(h_1847_R, false));
        }
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.R_4764_Y().u_1723_Y().u_1723_Y());
    }
}


