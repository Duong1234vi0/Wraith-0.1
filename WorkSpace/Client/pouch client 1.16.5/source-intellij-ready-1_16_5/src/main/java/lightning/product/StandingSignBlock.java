/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.I_2909_y;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.WoodType;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.g_88_D;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;

public class StandingSignBlock
extends I_2909_y {
    public static final g_88_D Q_4569_t = BlockStateProperties.j_1564_a;

    public StandingSignBlock(q_4293_E.P_1922_E properties, WoodType type) {
        super(properties, type);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(Q_4569_t, 0)).n_1700_B(P_4830_p, false));
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return worldIn.getBlockState(pos.down()).R_4764_Y().J_1907_R();
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(Q_4569_t, u_530_F.R_4764_Y((double)((180.0f + context.getPlacementYaw()) * 16.0f / 360.0f) + 0.5) & 0xF)).n_1700_B(P_4830_p, fluidstate.n_1700_B() == Fluids.R_4764_Y);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == b_257_Y.n_1700_B && !this.n_1700_B(stateIn, worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(Q_4569_t, rot.n_1700_B(state.R_4764_Y(Q_4569_t), 16));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return (K_4074_S)state.n_1700_B(Q_4569_t, mirrorIn.n_1700_B(state.R_4764_Y(Q_4569_t), 16));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(Q_4569_t, P_4830_p);
    }
}


