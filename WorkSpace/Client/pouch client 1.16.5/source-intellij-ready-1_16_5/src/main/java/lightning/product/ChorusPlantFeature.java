/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.ChorusFlowerBlock;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;

public class ChorusPlantFeature
extends Feature<o_2105_O> {
    public ChorusPlantFeature(Codec<o_2105_O> p_i231936_1_) {
        super(p_i231936_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        if (p_241855_1_.u_1723_Y(p_241855_4_) && p_241855_1_.getBlockState(p_241855_4_.down()).n_1700_B(a_3742_W.e_1231_S)) {
            ChorusFlowerBlock.n_1700_B(p_241855_1_, p_241855_4_, p_241855_3_, 8);
            return true;
        }
        return false;
    }
}


