/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.FluidTags;
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
import lightning.product.v_3760_Q;

public class N_482_I
extends T_2915_h
implements SimpleWaterloggedBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.A_4115_X;
    private static final s_1395_c h_1847_R = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);

    protected N_482_I(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, true));
    }

    protected void n_1700_B(K_4074_S state, LevelAccessor worldIn, c_1514_x pos) {
        if (!N_482_I.u_1723_Y(state, worldIn, pos)) {
            worldIn.u_2550_I().n_1700_B(pos, this, 60 + worldIn.e_4240_b().nextInt(40));
        }
    }

    protected static boolean u_1723_Y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            return true;
        }
        for (b_257_Y direction : b_257_Y.values()) {
            if (!worldIn.getFluidState(pos.offset(direction)).n_1700_B(FluidTags.J_1907_R)) continue;
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, fluidstate.n_1700_B(FluidTags.J_1907_R) && fluidstate.P_1922_E() == 8);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return facing == b_257_Y.n_1700_B && !this.n_1700_B(stateIn, (T_1316_M)worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        return worldIn.getBlockState(blockpos).G_564_y((BlockGetter)worldIn, blockpos, b_257_Y.J_1907_R);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }
}


