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

public class NoiseSamplingSettings {
    private static final Codec<Double> J_1907_R = Codec.doubleRange((double)0.001, (double)1000.0);
    public static final Codec<NoiseSamplingSettings> n_1700_B = RecordCodecBuilder.create(p_236152_0_ -> p_236152_0_.group((App)J_1907_R.fieldOf("xz_scale").forGetter(NoiseSamplingSettings::n_1700_B), (App)J_1907_R.fieldOf("y_scale").forGetter(NoiseSamplingSettings::J_1907_R), (App)J_1907_R.fieldOf("xz_factor").forGetter(NoiseSamplingSettings::R_4764_Y), (App)J_1907_R.fieldOf("y_factor").forGetter(NoiseSamplingSettings::G_564_y)).apply((Applicative)p_236152_0_, NoiseSamplingSettings::new));
    private final double R_4764_Y;
    private final double G_564_y;
    private final double P_1922_E;
    private final double u_1723_Y;

    public NoiseSamplingSettings(double p_i231909_1_, double p_i231909_3_, double p_i231909_5_, double p_i231909_7_) {
        this.R_4764_Y = p_i231909_1_;
        this.G_564_y = p_i231909_3_;
        this.P_1922_E = p_i231909_5_;
        this.u_1723_Y = p_i231909_7_;
    }

    public double n_1700_B() {
        return this.R_4764_Y;
    }

    public double J_1907_R() {
        return this.G_564_y;
    }

    public double R_4764_Y() {
        return this.P_1922_E;
    }

    public double G_564_y() {
        return this.u_1723_Y;
    }
}


