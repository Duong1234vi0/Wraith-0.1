/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.F_2203_T;
import lightning.product.BlockGetter;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class FaceAttachedHorizontalDirectionalBlock
extends HorizontalDirectionalBlock {
    public static final e_563_h<F_2203_T> RealmsServerPing = BlockStateProperties.g_221_o;

    protected FaceAttachedHorizontalDirectionalBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return FaceAttachedHorizontalDirectionalBlock.J_1907_R(worldIn, pos, FaceAttachedHorizontalDirectionalBlock.w_1484_f(state).u_1723_Y());
    }

    public static boolean J_1907_R(T_1316_M reader, c_1514_x pos, b_257_Y direction) {
        c_1514_x blockpos = pos.offset(direction);
        return reader.getBlockState(blockpos).G_564_y((BlockGetter)reader, blockpos, direction.u_1723_Y());
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        for (b_257_Y direction : context.G_564_y()) {
            K_4074_S blockstate = direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R ? (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(RealmsServerPing, direction == b_257_Y.J_1907_R ? F_2203_T.R_4764_Y : F_2203_T.n_1700_B)).n_1700_B(w_612_n, context.getPlacementHorizontalFacing()) : (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(RealmsServerPing, F_2203_T.J_1907_R)).n_1700_B(w_612_n, direction.u_1723_Y());
            if (!blockstate.n_1700_B((T_1316_M)context.getWorld(), context.getPos())) continue;
            return blockstate;
        }
        return null;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return FaceAttachedHorizontalDirectionalBlock.w_1484_f(stateIn).u_1723_Y() == facing && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    protected static b_257_Y w_1484_f(K_4074_S state) {
        switch (state.R_4764_Y(RealmsServerPing)) {
            case R_4764_Y: {
                return b_257_Y.n_1700_B;
            }
            case n_1700_B: {
                return b_257_Y.J_1907_R;
            }
        }
        return state.R_4764_Y(w_612_n);
    }
}


