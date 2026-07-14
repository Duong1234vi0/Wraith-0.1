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

public class RangeDecoratorConfiguration
implements P_1781_m {
    public static final Codec<RangeDecoratorConfiguration> n_1700_B = RecordCodecBuilder.create(p_236986_0_ -> p_236986_0_.group((App)Codec.INT.fieldOf("bottom_offset").orElse((Object)0).forGetter(p_236988_0_ -> p_236988_0_.J_1907_R), (App)Codec.INT.fieldOf("top_offset").orElse((Object)0).forGetter(p_236987_0_ -> p_236987_0_.R_4764_Y), (App)Codec.INT.fieldOf("maximum").orElse((Object)0).forGetter(p_242816_0_ -> p_242816_0_.G_564_y)).apply((Applicative)p_236986_0_, RangeDecoratorConfiguration::new));
    public final int J_1907_R;
    public final int R_4764_Y;
    public final int G_564_y;

    public RangeDecoratorConfiguration(int p_i241992_1_, int p_i241992_2_, int p_i241992_3_) {
        this.J_1907_R = p_i241992_1_;
        this.R_4764_Y = p_i241992_2_;
        this.G_564_y = p_i241992_3_;
    }
}


