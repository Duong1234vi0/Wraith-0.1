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
import lightning.product.P_1781_m;

public class FrequencyWithExtraChanceDecoratorConfiguration
implements P_1781_m {
    public static final Codec<FrequencyWithExtraChanceDecoratorConfiguration> n_1700_B = RecordCodecBuilder.create(p_236974_0_ -> p_236974_0_.group((App)Codec.INT.fieldOf("count").forGetter(p_236977_0_ -> p_236977_0_.J_1907_R), (App)Codec.FLOAT.fieldOf("extra_chance").forGetter(p_236976_0_ -> Float.valueOf(p_236976_0_.R_4764_Y)), (App)Codec.INT.fieldOf("extra_count").forGetter(p_236975_0_ -> p_236975_0_.G_564_y)).apply((Applicative)p_236974_0_, FrequencyWithExtraChanceDecoratorConfiguration::new));
    public final int J_1907_R;
    public final float R_4764_Y;
    public final int G_564_y;

    public FrequencyWithExtraChanceDecoratorConfiguration(int count, float extraChanceIn, int extraCountIn) {
        this.J_1907_R = count;
        this.R_4764_Y = extraChanceIn;
        this.G_564_y = extraCountIn;
    }
}


