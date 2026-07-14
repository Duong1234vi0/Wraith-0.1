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
import java.util.function.Supplier;
import lightning.product.X_2241_P;
import lightning.product.FeatureConfiguration;

public class JigsawConfiguration
implements FeatureConfiguration {
    public static final Codec<JigsawConfiguration> n_1700_B = RecordCodecBuilder.create(p_236535_0_ -> p_236535_0_.group((App)X_2241_P.J_1907_R.fieldOf("start_pool").forGetter(JigsawConfiguration::R_4764_Y), (App)Codec.intRange((int)0, (int)7).fieldOf("size").forGetter(JigsawConfiguration::J_1907_R)).apply((Applicative)p_236535_0_, JigsawConfiguration::new));
    private final Supplier<X_2241_P> J_1907_R;
    private final int R_4764_Y;

    public JigsawConfiguration(Supplier<X_2241_P> p_i241987_1_, int p_i241987_2_) {
        this.J_1907_R = p_i241987_1_;
        this.R_4764_Y = p_i241987_2_;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public Supplier<X_2241_P> R_4764_Y() {
        return this.J_1907_R;
    }
}


