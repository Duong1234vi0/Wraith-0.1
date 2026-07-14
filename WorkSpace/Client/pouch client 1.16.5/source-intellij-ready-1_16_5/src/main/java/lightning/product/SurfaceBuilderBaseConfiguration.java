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
import lightning.product.Z_927_M;

public class SurfaceBuilderBaseConfiguration
implements Z_927_M {
    public static final Codec<SurfaceBuilderBaseConfiguration> n_1700_B = RecordCodecBuilder.create(p_237204_0_ -> p_237204_0_.group((App)K_4074_S.J_1907_R.fieldOf("top_material").forGetter(p_237207_0_ -> p_237207_0_.J_1907_R), (App)K_4074_S.J_1907_R.fieldOf("under_material").forGetter(p_237206_0_ -> p_237206_0_.R_4764_Y), (App)K_4074_S.J_1907_R.fieldOf("underwater_material").forGetter(p_237205_0_ -> p_237205_0_.G_564_y)).apply((Applicative)p_237204_0_, SurfaceBuilderBaseConfiguration::new));
    private final K_4074_S J_1907_R;
    private final K_4074_S R_4764_Y;
    private final K_4074_S G_564_y;

    public SurfaceBuilderBaseConfiguration(K_4074_S topMaterial, K_4074_S underMaterial, K_4074_S underWaterMaterial) {
        this.J_1907_R = topMaterial;
        this.R_4764_Y = underMaterial;
        this.G_564_y = underWaterMaterial;
    }

    @Override
    public K_4074_S n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public K_4074_S J_1907_R() {
        return this.R_4764_Y;
    }

    public K_4074_S R_4764_Y() {
        return this.G_564_y;
    }
}


