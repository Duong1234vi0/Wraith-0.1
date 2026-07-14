/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.K_4074_S;
import lightning.product.S_1806_m;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Features;
import lightning.product.NetherForestVegetationFeature;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_4702_v;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class NyliumBlock
extends T_2915_h
implements BonemealableBlock {
    protected NyliumBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    private static boolean J_1907_R(K_4074_S state, T_1316_M reader, c_1514_x pos) {
        c_1514_x blockpos = pos.up();
        K_4074_S blockstate = reader.getBlockState(blockpos);
        int i = i_4702_v.n_1700_B(reader, state, pos, blockstate, blockpos, b_257_Y.J_1907_R, blockstate.J_1907_R(reader, blockpos));
        return i < reader.Z_875_P();
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (!NyliumBlock.J_1907_R(state, (T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, a_3742_W.i_3196_G.multiplayerClientSuggestionProvider());
        }
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
        K_4074_S blockstate = worldIn.getBlockState(pos);
        c_1514_x blockpos = pos.up();
        if (blockstate.n_1700_B(a_3742_W.ServerFunctionManager)) {
            NetherForestVegetationFeature.n_1700_B(worldIn, rand, blockpos, Features.n_1700_B.u_2550_I, 3, 1);
        } else if (blockstate.n_1700_B(a_3742_W.ServerAdvancementManager)) {
            NetherForestVegetationFeature.n_1700_B(worldIn, rand, blockpos, Features.n_1700_B.M_588_G, 3, 1);
            NetherForestVegetationFeature.n_1700_B(worldIn, rand, blockpos, Features.n_1700_B.P_4830_p, 3, 1);
            if (rand.nextInt(8) == 0) {
                S_1806_m.n_1700_B((LevelAccessor)worldIn, rand, blockpos, 3, 1, 2);
            }
        }
    }
}


