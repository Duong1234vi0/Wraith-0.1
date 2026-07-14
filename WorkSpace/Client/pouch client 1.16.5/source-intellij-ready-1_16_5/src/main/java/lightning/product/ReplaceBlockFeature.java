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
import lightning.product.ReplaceBlockConfiguration;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.z_1753_f;

public class ReplaceBlockFeature
extends Feature<ReplaceBlockConfiguration> {
    public ReplaceBlockFeature(Codec<ReplaceBlockConfiguration> p_i231983_1_) {
        super(p_i231983_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, ReplaceBlockConfiguration p_241855_5_) {
        if (p_241855_1_.getBlockState(p_241855_4_).n_1700_B(p_241855_5_.J_1907_R.J_1907_R())) {
            p_241855_1_.n_1700_B(p_241855_4_, p_241855_5_.R_4764_Y, 2);
        }
        return true;
    }
}


