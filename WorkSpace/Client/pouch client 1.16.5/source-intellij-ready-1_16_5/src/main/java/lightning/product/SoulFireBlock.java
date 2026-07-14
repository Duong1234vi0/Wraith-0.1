/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.BaseFireBlock;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class SoulFireBlock
extends BaseFireBlock {
    public SoulFireBlock(q_4293_E.P_1922_E properties) {
        super(properties, 2.0f);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return this.n_1700_B(stateIn, worldIn, currentPos) ? this.multiplayerClientSuggestionProvider() : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return SoulFireBlock.n_1700_B(worldIn.getBlockState(pos.down()).J_1907_R());
    }

    public static boolean n_1700_B(T_2915_h block) {
        return block.n_1700_B(BlockTags.PlayerInfo);
    }

    @Override
    protected boolean v_4262_N(K_4074_S state) {
        return true;
    }
}


