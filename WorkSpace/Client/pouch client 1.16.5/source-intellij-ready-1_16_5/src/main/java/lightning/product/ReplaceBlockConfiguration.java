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

public class ReplaceBlockConfiguration
implements FeatureConfiguration {
    public static final Codec<ReplaceBlockConfiguration> n_1700_B = RecordCodecBuilder.create(p_236606_0_ -> p_236606_0_.group((App)K_4074_S.J_1907_R.fieldOf("target").forGetter(p_236607_0_ -> p_236607_0_.J_1907_R), (App)K_4074_S.J_1907_R.fieldOf("state").forGetter(p_236605_0_ -> p_236605_0_.R_4764_Y)).apply((Applicative)p_236606_0_, ReplaceBlockConfiguration::new));
    public final K_4074_S J_1907_R;
    public final K_4074_S R_4764_Y;

    public ReplaceBlockConfiguration(K_4074_S target, K_4074_S state) {
        this.J_1907_R = target;
        this.R_4764_Y = state;
    }
}


