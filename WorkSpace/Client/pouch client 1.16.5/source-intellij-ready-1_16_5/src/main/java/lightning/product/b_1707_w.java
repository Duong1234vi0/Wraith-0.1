/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.RedstoneTorchBlock;
import lightning.product.WallTorchBlock;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public class b_1707_w
extends RedstoneTorchBlock {
    public static final DirectionProperty h_1847_R = HorizontalDirectionalBlock.w_612_n;
    public static final U_1266_O Q_4569_t = RedstoneTorchBlock.P_4830_p;

    protected b_1707_w(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(h_1847_R, b_257_Y.R_4764_Y)).n_1700_B(Q_4569_t, true));
    }

    @Override
    public String P_4830_p() {
        return this.u_1723_Y().J_1907_R();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return WallTorchBlock.w_1484_f(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return a_3742_W.C_1269_X.n_1700_B(state, worldIn, pos);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return a_3742_W.C_1269_X.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = a_3742_W.C_1269_X.n_1700_B(context);
        return blockstate == null ? null : (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(h_1847_R, blockstate.R_4764_Y(h_1847_R));
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(Q_4569_t).booleanValue()) {
            b_257_Y direction = stateIn.R_4764_Y(h_1847_R).u_1723_Y();
            double d0 = 0.27;
            double d1 = (double)pos.getX() + 0.5 + (rand.nextDouble() - 0.5) * 0.2 + 0.27 * (double)direction.t_148_a();
            double d2 = (double)pos.getY() + 0.7 + (rand.nextDouble() - 0.5) * 0.2 + 0.22;
            double d3 = (double)pos.getZ() + 0.5 + (rand.nextDouble() - 0.5) * 0.2 + 0.27 * (double)direction.u_2550_I();
            worldIn.n_1700_B(this.t_1786_h, d1, d2, d3, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(h_1847_R).u_1723_Y();
        return worldIn.J_1907_R(pos.offset(direction), direction);
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(Q_4569_t) != false && blockState.R_4764_Y(h_1847_R) != side ? 15 : 0;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return a_3742_W.C_1269_X.n_1700_B(state, rot);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return a_3742_W.C_1269_X.n_1700_B(state, mirrorIn);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(h_1847_R, Q_4569_t);
    }
}


