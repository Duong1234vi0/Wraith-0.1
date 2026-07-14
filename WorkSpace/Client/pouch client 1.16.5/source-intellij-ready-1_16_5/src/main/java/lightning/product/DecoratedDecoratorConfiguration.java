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
import lightning.product.ConfiguredDecorator;

public class DecoratedDecoratorConfiguration
implements P_1781_m {
    public static final Codec<DecoratedDecoratorConfiguration> n_1700_B = RecordCodecBuilder.create(p_242887_0_ -> p_242887_0_.group((App)ConfiguredDecorator.n_1700_B.fieldOf("outer").forGetter(DecoratedDecoratorConfiguration::n_1700_B), (App)ConfiguredDecorator.n_1700_B.fieldOf("inner").forGetter(DecoratedDecoratorConfiguration::J_1907_R)).apply((Applicative)p_242887_0_, DecoratedDecoratorConfiguration::new));
    private final ConfiguredDecorator<?> J_1907_R;
    private final ConfiguredDecorator<?> R_4764_Y;

    public DecoratedDecoratorConfiguration(ConfiguredDecorator<?> p_i242020_1_, ConfiguredDecorator<?> p_i242020_2_) {
        this.J_1907_R = p_i242020_1_;
        this.R_4764_Y = p_i242020_2_;
    }

    public ConfiguredDecorator<?> n_1700_B() {
        return this.J_1907_R;
    }

    public ConfiguredDecorator<?> J_1907_R() {
        return this.R_4764_Y;
    }
}


