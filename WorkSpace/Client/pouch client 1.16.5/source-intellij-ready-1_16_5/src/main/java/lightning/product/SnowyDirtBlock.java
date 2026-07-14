/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;

public class SnowyDirtBlock
extends T_2915_h {
    public static final U_1266_O P_4830_p = BlockStateProperties.Z_875_P;

    protected SnowyDirtBlock(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing != b_257_Y.J_1907_R ? super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos) : (K_4074_S)stateIn.n_1700_B(P_4830_p, facingState.n_1700_B(a_3742_W.l_697_B) || facingState.n_1700_B(a_3742_W.X_290_I));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos().up());
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, blockstate.n_1700_B(a_3742_W.l_697_B) || blockstate.n_1700_B(a_3742_W.X_290_I));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


