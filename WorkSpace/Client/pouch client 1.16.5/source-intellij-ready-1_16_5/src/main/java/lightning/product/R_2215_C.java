/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.w_1454_v;
import lightning.product.x_268_Y;

public class R_2215_C
extends T_2915_h
implements SimpleWaterloggedBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.s_956_w;
    public static final U_1266_O h_1847_R = BlockStateProperties.A_4115_X;
    protected static final s_1395_c Q_4569_t = x_268_Y.n_1700_B(T_2915_h.n_1700_B(5.0, 0.0, 5.0, 11.0, 7.0, 11.0), T_2915_h.n_1700_B(6.0, 7.0, 6.0, 10.0, 9.0, 10.0));
    protected static final s_1395_c M_182_A = x_268_Y.n_1700_B(T_2915_h.n_1700_B(5.0, 1.0, 5.0, 11.0, 8.0, 11.0), T_2915_h.n_1700_B(6.0, 8.0, 6.0, 10.0, 10.0, 10.0));

    public R_2215_C(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, false));
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        for (b_257_Y direction : context.G_564_y()) {
            K_4074_S blockstate;
            if (direction.h_1847_R() != b_257_Y.n_1700_B.J_1907_R || !(blockstate = (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, direction == b_257_Y.J_1907_R)).n_1700_B((T_1316_M)context.getWorld(), context.getPos())) continue;
            return (K_4074_S)blockstate.n_1700_B(h_1847_R, fluidstate.n_1700_B() == Fluids.R_4764_Y);
        }
        return null;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return state.R_4764_Y(P_4830_p) != false ? M_182_A : Q_4569_t;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        b_257_Y direction = R_2215_C.w_1484_f(state).u_1723_Y();
        return T_2915_h.n_1700_B(worldIn, pos.offset(direction), direction.u_1723_Y());
    }

    protected static b_257_Y w_1484_f(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) != false ? b_257_Y.n_1700_B : b_257_Y.J_1907_R;
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.J_1907_R;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return R_2215_C.w_1484_f(stateIn).u_1723_Y() == facing && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(h_1847_R) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


