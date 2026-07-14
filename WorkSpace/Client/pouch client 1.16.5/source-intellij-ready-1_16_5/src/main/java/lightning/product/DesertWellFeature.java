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
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.g_3049_G;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;

public class DesertWellFeature
extends Feature<o_2105_O> {
    private static final g_3049_G n_1700_B = g_3049_G.n_1700_B(a_3742_W.A_4115_X);
    private final K_4074_S D_4792_h = a_3742_W.NoFall.multiplayerClientSuggestionProvider();
    private final K_4074_S s_2632_s = a_3742_W.h_4320_q.multiplayerClientSuggestionProvider();
    private final K_4074_S l_1233_K = a_3742_W.c_3005_b.multiplayerClientSuggestionProvider();

    public DesertWellFeature(Codec<o_2105_O> p_i231948_1_) {
        super(p_i231948_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        p_241855_4_ = p_241855_4_.up();
        while (p_241855_1_.u_1723_Y(p_241855_4_) && p_241855_4_.getY() > 2) {
            p_241855_4_ = p_241855_4_.down();
        }
        if (!n_1700_B.n_1700_B(p_241855_1_.getBlockState(p_241855_4_))) {
            return false;
        }
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                if (!p_241855_1_.u_1723_Y(p_241855_4_.add(i, -1, j)) || !p_241855_1_.u_1723_Y(p_241855_4_.add(i, -2, j))) continue;
                return false;
            }
        }
        for (int l = -1; l <= 0; ++l) {
            for (int l1 = -2; l1 <= 2; ++l1) {
                for (int k = -2; k <= 2; ++k) {
                    p_241855_1_.n_1700_B(p_241855_4_.add(l1, l, k), this.s_2632_s, 2);
                }
            }
        }
        p_241855_1_.n_1700_B(p_241855_4_, this.l_1233_K, 2);
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            p_241855_1_.n_1700_B(p_241855_4_.offset(direction), this.l_1233_K, 2);
        }
        for (int i1 = -2; i1 <= 2; ++i1) {
            for (int i2 = -2; i2 <= 2; ++i2) {
                if (i1 != -2 && i1 != 2 && i2 != -2 && i2 != 2) continue;
                p_241855_1_.n_1700_B(p_241855_4_.add(i1, 1, i2), this.s_2632_s, 2);
            }
        }
        p_241855_1_.n_1700_B(p_241855_4_.add(2, 1, 0), this.D_4792_h, 2);
        p_241855_1_.n_1700_B(p_241855_4_.add(-2, 1, 0), this.D_4792_h, 2);
        p_241855_1_.n_1700_B(p_241855_4_.add(0, 1, 2), this.D_4792_h, 2);
        p_241855_1_.n_1700_B(p_241855_4_.add(0, 1, -2), this.D_4792_h, 2);
        for (int j1 = -1; j1 <= 1; ++j1) {
            for (int j2 = -1; j2 <= 1; ++j2) {
                if (j1 == 0 && j2 == 0) {
                    p_241855_1_.n_1700_B(p_241855_4_.add(j1, 4, j2), this.s_2632_s, 2);
                    continue;
                }
                p_241855_1_.n_1700_B(p_241855_4_.add(j1, 4, j2), this.D_4792_h, 2);
            }
        }
        for (int k1 = 1; k1 <= 3; ++k1) {
            p_241855_1_.n_1700_B(p_241855_4_.add(-1, k1, -1), this.s_2632_s, 2);
            p_241855_1_.n_1700_B(p_241855_4_.add(-1, k1, 1), this.s_2632_s, 2);
            p_241855_1_.n_1700_B(p_241855_4_.add(1, k1, -1), this.s_2632_s, 2);
            p_241855_1_.n_1700_B(p_241855_4_.add(1, k1, 1), this.s_2632_s, 2);
        }
        return true;
    }
}



