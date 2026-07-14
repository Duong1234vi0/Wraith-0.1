/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;

public class NetherrackBlock
extends T_2915_h
implements BonemealableBlock {
    public NetherrackBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        if (!worldIn.getBlockState(pos.up()).n_1700_B(worldIn, pos)) {
            return false;
        }
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            if (!worldIn.getBlockState(blockpos).n_1700_B(BlockTags.UploadStatus)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        boolean flag = false;
        boolean flag1 = false;
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            if (blockstate.n_1700_B(a_3742_W.ServerAdvancementManager)) {
                flag1 = true;
            }
            if (blockstate.n_1700_B(a_3742_W.ServerFunctionManager)) {
                flag = true;
            }
            if (!flag1 || !flag) continue;
            break;
        }
        if (flag1 && flag) {
            worldIn.n_1700_B(pos, rand.nextBoolean() ? a_3742_W.ServerAdvancementManager.multiplayerClientSuggestionProvider() : a_3742_W.ServerFunctionManager.multiplayerClientSuggestionProvider(), 3);
        } else if (flag1) {
            worldIn.n_1700_B(pos, a_3742_W.ServerAdvancementManager.multiplayerClientSuggestionProvider(), 3);
        } else if (flag) {
            worldIn.n_1700_B(pos, a_3742_W.ServerFunctionManager.multiplayerClientSuggestionProvider(), 3);
        }
    }
}


