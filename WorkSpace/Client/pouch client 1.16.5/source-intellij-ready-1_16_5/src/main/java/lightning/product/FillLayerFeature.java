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
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.LayerConfiguration;
import lightning.product.z_1753_f;

public class FillLayerFeature
extends Feature<LayerConfiguration> {
    public FillLayerFeature(Codec<LayerConfiguration> p_i231954_1_) {
        super(p_i231954_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, LayerConfiguration p_241855_5_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = p_241855_4_.getX() + i;
                int l = p_241855_4_.getZ() + j;
                int i1 = p_241855_5_.J_1907_R;
                blockpos$mutable.n_1700_B(k, i1, l);
                if (!p_241855_1_.getBlockState(blockpos$mutable).v_4262_N()) continue;
                p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, p_241855_5_.R_4764_Y, 2);
            }
        }
        return true;
    }
}


