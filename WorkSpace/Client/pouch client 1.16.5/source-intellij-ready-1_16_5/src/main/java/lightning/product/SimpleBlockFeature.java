/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.SimpleBlockConfiguration;
import lightning.product.WorldGenLevel;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.z_1753_f;

public class SimpleBlockFeature
extends Feature<SimpleBlockConfiguration> {
    public SimpleBlockFeature(Codec<SimpleBlockConfiguration> p_i231991_1_) {
        super(p_i231991_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, SimpleBlockConfiguration p_241855_5_) {
        if (p_241855_5_.R_4764_Y.contains(p_241855_1_.getBlockState(p_241855_4_.down())) && p_241855_5_.G_564_y.contains(p_241855_1_.getBlockState(p_241855_4_)) && p_241855_5_.P_1922_E.contains(p_241855_1_.getBlockState(p_241855_4_.up()))) {
            p_241855_1_.n_1700_B(p_241855_4_, p_241855_5_.J_1907_R, 2);
            return true;
        }
        return false;
    }
}


