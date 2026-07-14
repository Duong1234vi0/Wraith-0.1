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

public class NoiseSlideSettings {
    public static final Codec<NoiseSlideSettings> n_1700_B = RecordCodecBuilder.create(p_236187_0_ -> p_236187_0_.group((App)Codec.INT.fieldOf("target").forGetter(NoiseSlideSettings::n_1700_B), (App)Codec.intRange((int)0, (int)256).fieldOf("size").forGetter(NoiseSlideSettings::J_1907_R), (App)Codec.INT.fieldOf("offset").forGetter(NoiseSlideSettings::R_4764_Y)).apply((Applicative)p_236187_0_, NoiseSlideSettings::new));
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;

    public NoiseSlideSettings(int p_i231911_1_, int p_i231911_2_, int p_i231911_3_) {
        this.J_1907_R = p_i231911_1_;
        this.R_4764_Y = p_i231911_2_;
        this.G_564_y = p_i231911_3_;
    }

    public int n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }
}


