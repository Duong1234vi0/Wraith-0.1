/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Lifecycle
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lightning.product.NoiseSlideSettings;
import lightning.product.NoiseSamplingSettings;

public class NoiseSettings {
    public static final Codec<NoiseSettings> n_1700_B = RecordCodecBuilder.create(p_236170_0_ -> p_236170_0_.group((App)Codec.intRange((int)0, (int)256).fieldOf("height").forGetter(NoiseSettings::n_1700_B), (App)NoiseSamplingSettings.n_1700_B.fieldOf("sampling").forGetter(NoiseSettings::J_1907_R), (App)NoiseSlideSettings.n_1700_B.fieldOf("top_slide").forGetter(NoiseSettings::R_4764_Y), (App)NoiseSlideSettings.n_1700_B.fieldOf("bottom_slide").forGetter(NoiseSettings::G_564_y), (App)Codec.intRange((int)1, (int)4).fieldOf("size_horizontal").forGetter(NoiseSettings::P_1922_E), (App)Codec.intRange((int)1, (int)4).fieldOf("size_vertical").forGetter(NoiseSettings::u_1723_Y), (App)Codec.DOUBLE.fieldOf("density_factor").forGetter(NoiseSettings::v_4262_N), (App)Codec.DOUBLE.fieldOf("density_offset").forGetter(NoiseSettings::w_1484_f), (App)Codec.BOOL.fieldOf("simplex_surface_noise").forGetter(NoiseSettings::t_148_a), (App)Codec.BOOL.optionalFieldOf("random_density_offset", (Object)false, Lifecycle.experimental()).forGetter(NoiseSettings::s_956_w), (App)Codec.BOOL.optionalFieldOf("island_noise_override", (Object)false, Lifecycle.experimental()).forGetter(NoiseSettings::u_2550_I), (App)Codec.BOOL.optionalFieldOf("amplified", (Object)false, Lifecycle.experimental()).forGetter(NoiseSettings::M_588_G)).apply((Applicative)p_236170_0_, NoiseSettings::new));
    private final int J_1907_R;
    private final NoiseSamplingSettings R_4764_Y;
    private final NoiseSlideSettings G_564_y;
    private final NoiseSlideSettings P_1922_E;
    private final int u_1723_Y;
    private final int v_4262_N;
    private final double w_1484_f;
    private final double t_148_a;
    private final boolean s_956_w;
    private final boolean u_2550_I;
    private final boolean M_588_G;
    private final boolean P_4830_p;

    public NoiseSettings(int p_i231910_1_, NoiseSamplingSettings p_i231910_2_, NoiseSlideSettings p_i231910_3_, NoiseSlideSettings p_i231910_4_, int p_i231910_5_, int p_i231910_6_, double p_i231910_7_, double p_i231910_9_, boolean p_i231910_11_, boolean p_i231910_12_, boolean p_i231910_13_, boolean p_i231910_14_) {
        this.J_1907_R = p_i231910_1_;
        this.R_4764_Y = p_i231910_2_;
        this.G_564_y = p_i231910_3_;
        this.P_1922_E = p_i231910_4_;
        this.u_1723_Y = p_i231910_5_;
        this.v_4262_N = p_i231910_6_;
        this.w_1484_f = p_i231910_7_;
        this.t_148_a = p_i231910_9_;
        this.s_956_w = p_i231910_11_;
        this.u_2550_I = p_i231910_12_;
        this.M_588_G = p_i231910_13_;
        this.P_4830_p = p_i231910_14_;
    }

    public int n_1700_B() {
        return this.J_1907_R;
    }

    public NoiseSamplingSettings J_1907_R() {
        return this.R_4764_Y;
    }

    public NoiseSlideSettings R_4764_Y() {
        return this.G_564_y;
    }

    public NoiseSlideSettings G_564_y() {
        return this.P_1922_E;
    }

    public int P_1922_E() {
        return this.u_1723_Y;
    }

    public int u_1723_Y() {
        return this.v_4262_N;
    }

    public double v_4262_N() {
        return this.w_1484_f;
    }

    public double w_1484_f() {
        return this.t_148_a;
    }

    @Deprecated
    public boolean t_148_a() {
        return this.s_956_w;
    }

    @Deprecated
    public boolean s_956_w() {
        return this.u_2550_I;
    }

    @Deprecated
    public boolean u_2550_I() {
        return this.M_588_G;
    }

    @Deprecated
    public boolean M_588_G() {
        return this.P_4830_p;
    }
}


