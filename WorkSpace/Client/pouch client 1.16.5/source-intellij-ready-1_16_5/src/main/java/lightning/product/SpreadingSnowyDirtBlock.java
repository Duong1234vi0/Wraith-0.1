/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.K_4074_S;
import lightning.product.SnowyDirtBlock;
import lightning.product.T_1316_M;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_4702_v;
import lightning.product.q_4293_E;
import lightning.product.SnowLayerBlock;

public abstract class SpreadingSnowyDirtBlock
extends SnowyDirtBlock {
    protected SpreadingSnowyDirtBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    private static boolean J_1907_R(K_4074_S state, T_1316_M worldReader, c_1514_x pos) {
        c_1514_x blockpos = pos.up();
        K_4074_S blockstate = worldReader.getBlockState(blockpos);
        if (blockstate.n_1700_B(a_3742_W.X_290_I) && blockstate.R_4764_Y(SnowLayerBlock.P_4830_p) == 1) {
            return true;
        }
        if (blockstate.P_4830_p().P_1922_E() == 8) {
            return false;
        }
        int i = i_4702_v.n_1700_B(worldReader, state, pos, blockstate, blockpos, b_257_Y.J_1907_R, blockstate.J_1907_R(worldReader, blockpos));
        return i < worldReader.Z_875_P();
    }

    private static boolean R_4764_Y(K_4074_S state, T_1316_M worldReader, c_1514_x pos) {
        c_1514_x blockpos = pos.up();
        return SpreadingSnowyDirtBlock.J_1907_R(state, worldReader, pos) && !worldReader.getFluidState(blockpos).n_1700_B(FluidTags.J_1907_R);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (!SpreadingSnowyDirtBlock.J_1907_R(state, (T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, a_3742_W.s_956_w.multiplayerClientSuggestionProvider());
        } else if (worldIn.u_2550_I(pos.up()) >= 9) {
            K_4074_S blockstate = this.multiplayerClientSuggestionProvider();
            for (int i = 0; i < 4; ++i) {
                c_1514_x blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (!worldIn.getBlockState(blockpos).n_1700_B(a_3742_W.s_956_w) || !SpreadingSnowyDirtBlock.R_4764_Y(blockstate, worldIn, blockpos)) continue;
                worldIn.J_1907_R(blockpos, (K_4074_S)blockstate.n_1700_B(P_4830_p, worldIn.getBlockState(blockpos.up()).n_1700_B(a_3742_W.X_290_I)));
            }
        }
    }
}


