/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lightning.product.BlockStateProvider;
import lightning.product.FeatureConfiguration;

public class HugeMushroomFeatureConfiguration
implements FeatureConfiguration {
    public static final Codec<HugeMushroomFeatureConfiguration> n_1700_B = RecordCodecBuilder.create(p_236530_0_ -> p_236530_0_.group((App)BlockStateProvider.J_1907_R.fieldOf("cap_provider").forGetter(p_236532_0_ -> p_236532_0_.J_1907_R), (App)BlockStateProvider.J_1907_R.fieldOf("stem_provider").forGetter(p_236531_0_ -> p_236531_0_.R_4764_Y), (App)Codec.INT.fieldOf("foliage_radius").orElse((Object)2).forGetter(p_236529_0_ -> p_236529_0_.G_564_y)).apply((Applicative)p_236530_0_, HugeMushroomFeatureConfiguration::new));
    public final BlockStateProvider J_1907_R;
    public final BlockStateProvider R_4764_Y;
    public final int G_564_y;

    public HugeMushroomFeatureConfiguration(BlockStateProvider p_i225832_1_, BlockStateProvider p_i225832_2_, int p_i225832_3_) {
        this.J_1907_R = p_i225832_1_;
        this.R_4764_Y = p_i225832_2_;
        this.G_564_y = p_i225832_3_;
    }
}


