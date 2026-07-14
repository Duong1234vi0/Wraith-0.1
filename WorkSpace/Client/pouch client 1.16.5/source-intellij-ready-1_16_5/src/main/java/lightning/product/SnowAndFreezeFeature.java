/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.SnowyDirtBlock;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.k_594_Q;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class SnowAndFreezeFeature
extends Feature<o_2105_O> {
    public SnowAndFreezeFeature(Codec<o_2105_O> p_i231993_1_) {
        super(p_i231993_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        c_1514_x.n_1700_B blockpos$mutable1 = new c_1514_x.n_1700_B();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = p_241855_4_.getX() + i;
                int l = p_241855_4_.getZ() + j;
                int i1 = p_241855_1_.n_1700_B(z_2963_s.n_1700_B.P_1922_E, k, l);
                blockpos$mutable.n_1700_B(k, i1, l);
                blockpos$mutable1.n_1700_B(blockpos$mutable).n_1700_B(b_257_Y.n_1700_B, 1);
                k_594_Q biome = p_241855_1_.P_1922_E(blockpos$mutable);
                if (biome.n_1700_B(p_241855_1_, blockpos$mutable1, false)) {
                    p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable1, a_3742_W.O_1795_e.multiplayerClientSuggestionProvider(), 2);
                }
                if (!biome.J_1907_R(p_241855_1_, blockpos$mutable)) continue;
                p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, a_3742_W.X_290_I.multiplayerClientSuggestionProvider(), 2);
                K_4074_S blockstate = p_241855_1_.getBlockState(blockpos$mutable1);
                if (!blockstate.J_1907_R(SnowyDirtBlock.P_4830_p)) continue;
                p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable1, (K_4074_S)blockstate.n_1700_B(SnowyDirtBlock.P_4830_p, true), 2);
            }
        }
        return true;
    }
}


