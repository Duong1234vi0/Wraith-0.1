/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import lightning.product.SpreadingSnowyDirtBlock;
import lightning.product.AbstractFlowerFeature;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;

public class GrassBlock
extends SpreadingSnowyDirtBlock
implements BonemealableBlock {
    public GrassBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).v_4262_N();
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        c_1514_x blockpos = pos.up();
        K_4074_S blockstate = a_3742_W.u_744_e.multiplayerClientSuggestionProvider();
        block0: for (int i = 0; i < 128; ++i) {
            K_4074_S blockstate1;
            c_1514_x blockpos1 = blockpos;
            for (int j = 0; j < i / 16; ++j) {
                if (!worldIn.getBlockState((blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1)).down()).n_1700_B(this) || worldIn.getBlockState(blockpos1).multiplayerClientSuggestionProvider(worldIn, blockpos1)) continue block0;
            }
            K_4074_S blockstate2 = worldIn.getBlockState(blockpos1);
            if (blockstate2.n_1700_B(blockstate.J_1907_R()) && rand.nextInt(10) == 0) {
                ((BonemealableBlock)((Object)blockstate.J_1907_R())).n_1700_B(worldIn, rand, blockpos1, blockstate2);
            }
            if (!blockstate2.v_4262_N()) continue;
            if (rand.nextInt(8) == 0) {
                List<ConfiguredFeature<?, ?>> list = worldIn.P_1922_E(blockpos1).P_1922_E().J_1907_R();
                if (list.isEmpty()) continue;
                ConfiguredFeature<?, ?> configuredfeature = list.get(0);
                AbstractFlowerFeature flowersfeature = (AbstractFlowerFeature)configuredfeature.P_1922_E;
                blockstate1 = flowersfeature.n_1700_B(rand, blockpos1, configuredfeature.R_4764_Y());
            } else {
                blockstate1 = blockstate;
            }
            if (!blockstate1.n_1700_B((T_1316_M)worldIn, blockpos1)) continue;
            worldIn.n_1700_B(blockpos1, blockstate1, 3);
        }
    }
}


