/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.H_4584_y;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.BaseCoralWallFanBlock;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.BlockTags;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public abstract class CoralFeature
extends Feature<o_2105_O> {
    public CoralFeature(Codec<o_2105_O> p_i231940_1_) {
        super(p_i231940_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        K_4074_S blockstate = ((T_2915_h)BlockTags.c_4037_x.n_1700_B(p_241855_3_)).multiplayerClientSuggestionProvider();
        return this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_4_, blockstate);
    }

    protected abstract boolean n_1700_B(LevelAccessor var1, Random var2, c_1514_x var3, K_4074_S var4);

    protected boolean J_1907_R(LevelAccessor p_204624_1_, Random p_204624_2_, c_1514_x p_204624_3_, K_4074_S p_204624_4_) {
        c_1514_x blockpos = p_204624_3_.up();
        K_4074_S blockstate = p_204624_1_.getBlockState(p_204624_3_);
        if ((blockstate.n_1700_B(a_3742_W.c_3005_b) || blockstate.n_1700_B(BlockTags.D_4792_h)) && p_204624_1_.getBlockState(blockpos).n_1700_B(a_3742_W.c_3005_b)) {
            p_204624_1_.n_1700_B(p_204624_3_, p_204624_4_, 3);
            if (p_204624_2_.nextFloat() < 0.25f) {
                p_204624_1_.n_1700_B(blockpos, ((T_2915_h)BlockTags.D_4792_h.n_1700_B(p_204624_2_)).multiplayerClientSuggestionProvider(), 2);
            } else if (p_204624_2_.nextFloat() < 0.05f) {
                p_204624_1_.n_1700_B(blockpos, (K_4074_S)a_3742_W.Easing.multiplayerClientSuggestionProvider().n_1700_B(H_4584_y.P_4830_p, p_204624_2_.nextInt(4) + 1), 2);
            }
            for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                c_1514_x blockpos1;
                if (!(p_204624_2_.nextFloat() < 0.2f) || !p_204624_1_.getBlockState(blockpos1 = p_204624_3_.offset(direction)).n_1700_B(a_3742_W.c_3005_b)) continue;
                K_4074_S blockstate1 = (K_4074_S)((T_2915_h)BlockTags.g_2268_R.n_1700_B(p_204624_2_)).multiplayerClientSuggestionProvider().n_1700_B(BaseCoralWallFanBlock.h_1847_R, direction);
                p_204624_1_.n_1700_B(blockpos1, blockstate1, 2);
            }
            return true;
        }
        return false;
    }
}


