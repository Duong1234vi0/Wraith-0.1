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
import lightning.product.K_4074_S;
import lightning.product.FeatureConfiguration;

public class LayerConfiguration
implements FeatureConfiguration {
    public static final Codec<LayerConfiguration> n_1700_B = RecordCodecBuilder.create(p_236539_0_ -> p_236539_0_.group((App)Codec.intRange((int)0, (int)255).fieldOf("height").forGetter(p_236540_0_ -> p_236540_0_.J_1907_R), (App)K_4074_S.J_1907_R.fieldOf("state").forGetter(p_236538_0_ -> p_236538_0_.R_4764_Y)).apply((Applicative)p_236539_0_, LayerConfiguration::new));
    public final int J_1907_R;
    public final K_4074_S R_4764_Y;

    public LayerConfiguration(int height, K_4074_S state) {
        this.J_1907_R = height;
        this.R_4764_Y = state;
    }
}


