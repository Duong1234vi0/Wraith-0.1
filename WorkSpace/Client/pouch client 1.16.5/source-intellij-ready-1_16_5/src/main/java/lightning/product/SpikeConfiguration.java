/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.c_1514_x;
import lightning.product.SpikeFeature;
import lightning.product.FeatureConfiguration;

public class SpikeConfiguration
implements FeatureConfiguration {
    public static final Codec<SpikeConfiguration> n_1700_B = RecordCodecBuilder.create(p_236645_0_ -> p_236645_0_.group((App)Codec.BOOL.fieldOf("crystal_invulnerable").orElse((Object)false).forGetter(p_236648_0_ -> p_236648_0_.J_1907_R), (App)SpikeFeature.n_1700_B.n_1700_B.listOf().fieldOf("spikes").forGetter(p_236647_0_ -> p_236647_0_.R_4764_Y), (App)c_1514_x.CODEC.optionalFieldOf("crystal_beam_target").forGetter(p_236646_0_ -> Optional.ofNullable(p_236646_0_.G_564_y))).apply((Applicative)p_236645_0_, SpikeConfiguration::new));
    private final boolean J_1907_R;
    private final List<SpikeFeature.n_1700_B> R_4764_Y;
    @Nullable
    private final c_1514_x G_564_y;

    public SpikeConfiguration(boolean crystalInvulnerable, List<SpikeFeature.n_1700_B> spikes, @Nullable c_1514_x crystalBeamTarget) {
        this(crystalInvulnerable, spikes, Optional.ofNullable(crystalBeamTarget));
    }

    private SpikeConfiguration(boolean p_i232017_1_, List<SpikeFeature.n_1700_B> p_i232017_2_, Optional<c_1514_x> p_i232017_3_) {
        this.J_1907_R = p_i232017_1_;
        this.R_4764_Y = p_i232017_2_;
        this.G_564_y = p_i232017_3_.orElse(null);
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    public List<SpikeFeature.n_1700_B> R_4764_Y() {
        return this.R_4764_Y;
    }

    @Nullable
    public c_1514_x G_564_y() {
        return this.G_564_y;
    }
}


