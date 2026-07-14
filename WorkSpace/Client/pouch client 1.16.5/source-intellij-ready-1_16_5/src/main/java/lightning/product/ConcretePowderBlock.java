/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FluidTags;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.W_4464_I;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.FallingBlock;

public class ConcretePowderBlock
extends FallingBlock {
    private final K_4074_S P_4830_p;

    public ConcretePowderBlock(T_2915_h solidified, q_4293_E.P_1922_E properties) {
        super(properties);
        this.P_4830_p = solidified.multiplayerClientSuggestionProvider();
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S fallingState, K_4074_S hitState, W_4464_I fallingBlock) {
        if (ConcretePowderBlock.J_1907_R(worldIn, pos, hitState)) {
            worldIn.n_1700_B(pos, this.P_4830_p, 3);
        }
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate;
        c_1514_x blockpos;
        b_4507_u iblockreader = context.getWorld();
        return ConcretePowderBlock.J_1907_R(iblockreader, blockpos = context.getPos(), blockstate = iblockreader.getBlockState(blockpos)) ? this.P_4830_p : super.n_1700_B(context);
    }

    private static boolean J_1907_R(BlockGetter reader, c_1514_x pos, K_4074_S state) {
        return ConcretePowderBlock.w_1484_f(state) || ConcretePowderBlock.n_1700_B(reader, pos);
    }

    private static boolean n_1700_B(BlockGetter reader, c_1514_x pos) {
        boolean flag = false;
        c_1514_x.n_1700_B blockpos$mutable = pos.toMutable();
        for (b_257_Y direction : b_257_Y.values()) {
            K_4074_S blockstate = reader.getBlockState(blockpos$mutable);
            if (direction == b_257_Y.n_1700_B && !ConcretePowderBlock.w_1484_f(blockstate)) continue;
            blockpos$mutable.n_1700_B(pos, direction);
            blockstate = reader.getBlockState(blockpos$mutable);
            if (!ConcretePowderBlock.w_1484_f(blockstate) || blockstate.G_564_y(reader, pos, direction.u_1723_Y())) continue;
            flag = true;
            break;
        }
        return flag;
    }

    private static boolean w_1484_f(K_4074_S state) {
        return state.P_4830_p().n_1700_B(FluidTags.J_1907_R);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return ConcretePowderBlock.n_1700_B(worldIn, currentPos) ? this.P_4830_p : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public int v_4262_N(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return state.G_564_y((BlockGetter)reader, (c_1514_x)pos).i_1637_u;
    }
}


