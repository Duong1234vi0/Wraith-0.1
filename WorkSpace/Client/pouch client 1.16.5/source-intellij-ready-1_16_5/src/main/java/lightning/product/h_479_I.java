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
import lightning.product.RotatedPillarBlock;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;

public class h_479_I
extends RotatedPillarBlock
implements SimpleWaterloggedBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.A_4115_X;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(6.5, 6.5, 0.0, 9.5, 9.5, 16.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(0.0, 6.5, 6.5, 16.0, 9.5, 9.5);

    public h_479_I(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(t_1786_h, b_257_Y.n_1700_B.J_1907_R));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch ((b_257_Y.n_1700_B)state.R_4764_Y(t_1786_h)) {
            default: {
                return M_182_A;
            }
            case R_4764_Y: {
                return Q_4569_t;
            }
            case J_1907_R: 
        }
        return h_1847_R;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = fluidstate.n_1700_B() == Fluids.R_4764_Y;
        return (K_4074_S)super.n_1700_B(context).n_1700_B(P_4830_p, flag);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p}).n_1700_B(new v_3760_Q[]{t_1786_h});
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


