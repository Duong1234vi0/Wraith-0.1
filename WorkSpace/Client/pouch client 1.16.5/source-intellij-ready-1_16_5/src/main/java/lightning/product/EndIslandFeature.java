/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;

public class EndIslandFeature
extends Feature<o_2105_O> {
    public EndIslandFeature(Codec<o_2105_O> p_i231952_1_) {
        super(p_i231952_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        float f = p_241855_3_.nextInt(3) + 4;
        int i = 0;
        while (f > 0.5f) {
            for (int j = u_530_F.G_564_y(-f); j <= u_530_F.u_1723_Y(f); ++j) {
                for (int k = u_530_F.G_564_y(-f); k <= u_530_F.u_1723_Y(f); ++k) {
                    if (!((float)(j * j + k * k) <= (f + 1.0f) * (f + 1.0f))) continue;
                    this.n_1700_B(p_241855_1_, p_241855_4_.add(j, i, k), a_3742_W.e_1231_S.multiplayerClientSuggestionProvider());
                }
            }
            f = (float)((double)f - ((double)p_241855_3_.nextInt(2) + 0.5));
            --i;
        }
        return true;
    }
}


