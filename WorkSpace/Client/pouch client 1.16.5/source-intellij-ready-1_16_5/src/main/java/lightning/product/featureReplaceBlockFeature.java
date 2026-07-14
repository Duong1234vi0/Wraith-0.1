/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.DiskConfiguration;
import lightning.product.WorldGenLevel;
import lightning.product.GlowstoneFeature;
import lightning.product.c_1514_x;
import lightning.product.z_1753_f;

public class featureReplaceBlockFeature
extends GlowstoneFeature {
    public featureReplaceBlockFeature(Codec<DiskConfiguration> p_i231949_1_) {
        super(p_i231949_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, DiskConfiguration p_241855_5_) {
        return !p_241855_1_.getFluidState(p_241855_4_).n_1700_B(FluidTags.J_1907_R) ? false : super.n_1700_B(p_241855_1_, p_241855_2_, p_241855_3_, p_241855_4_, p_241855_5_);
    }
}


