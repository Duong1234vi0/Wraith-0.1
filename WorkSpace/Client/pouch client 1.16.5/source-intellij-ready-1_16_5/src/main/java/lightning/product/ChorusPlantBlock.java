/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.PipeBlock;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;

public class ChorusPlantBlock
extends PipeBlock {
    protected ChorusPlantBlock(q_4293_E.P_1922_E builder) {
        super(0.3125f, builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, false)).n_1700_B(multiplayerClientSuggestionProvider, false));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return this.n_1700_B((BlockGetter)context.getWorld(), context.getPos());
    }

    public K_4074_S n_1700_B(BlockGetter blockReader, c_1514_x pos) {
        T_2915_h block = blockReader.getBlockState(pos.down()).J_1907_R();
        T_2915_h block1 = blockReader.getBlockState(pos.up()).J_1907_R();
        T_2915_h block2 = blockReader.getBlockState(pos.north()).J_1907_R();
        T_2915_h block3 = blockReader.getBlockState(pos.east()).J_1907_R();
        T_2915_h block4 = blockReader.getBlockState(pos.south()).J_1907_R();
        T_2915_h block5 = blockReader.getBlockState(pos.west()).J_1907_R();
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(multiplayerClientSuggestionProvider, block == this || block == a_3742_W.ChorusExploit || block == a_3742_W.e_1231_S)).n_1700_B(t_1786_h, block1 == this || block1 == a_3742_W.ChorusExploit)).n_1700_B(P_4830_p, block2 == this || block2 == a_3742_W.ChorusExploit)).n_1700_B(h_1847_R, block3 == this || block3 == a_3742_W.ChorusExploit)).n_1700_B(Q_4569_t, block4 == this || block4 == a_3742_W.ChorusExploit)).n_1700_B(M_182_A, block5 == this || block5 == a_3742_W.ChorusExploit);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
            return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        boolean flag = facingState.J_1907_R() == this || facingState.n_1700_B(a_3742_W.ChorusExploit) || facing == b_257_Y.n_1700_B && facingState.n_1700_B(a_3742_W.e_1231_S);
        return (K_4074_S)stateIn.n_1700_B((v_3760_Q)w_1457_N.get(facing), flag);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, true);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.down());
        boolean flag = !worldIn.getBlockState(pos.up()).v_4262_N() && !blockstate.v_4262_N();
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = pos.offset(direction);
            T_2915_h block = worldIn.getBlockState(blockpos).J_1907_R();
            if (block != this) continue;
            if (flag) {
                return false;
            }
            T_2915_h block1 = worldIn.getBlockState(blockpos.down()).J_1907_R();
            if (block1 != this && block1 != a_3742_W.e_1231_S) continue;
            return true;
        }
        T_2915_h block2 = blockstate.J_1907_R();
        return block2 == this || block2 == a_3742_W.e_1231_S;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}



