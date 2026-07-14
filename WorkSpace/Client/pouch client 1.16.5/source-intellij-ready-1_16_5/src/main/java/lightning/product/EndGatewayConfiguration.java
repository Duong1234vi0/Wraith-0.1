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
import java.util.Optional;
import lightning.product.c_1514_x;
import lightning.product.FeatureConfiguration;

public class EndGatewayConfiguration
implements FeatureConfiguration {
    public static final Codec<EndGatewayConfiguration> n_1700_B = RecordCodecBuilder.create(p_236524_0_ -> p_236524_0_.group((App)c_1514_x.CODEC.optionalFieldOf("exit").forGetter(p_236525_0_ -> p_236525_0_.J_1907_R), (App)Codec.BOOL.fieldOf("exact").forGetter(p_236523_0_ -> p_236523_0_.R_4764_Y)).apply((Applicative)p_236524_0_, EndGatewayConfiguration::new));
    private final Optional<c_1514_x> J_1907_R;
    private final boolean R_4764_Y;

    private EndGatewayConfiguration(Optional<c_1514_x> exit, boolean exact) {
        this.J_1907_R = exit;
        this.R_4764_Y = exact;
    }

    public static EndGatewayConfiguration n_1700_B(c_1514_x p_214702_0_, boolean p_214702_1_) {
        return new EndGatewayConfiguration(Optional.of(p_214702_0_), p_214702_1_);
    }

    public static EndGatewayConfiguration J_1907_R() {
        return new EndGatewayConfiguration(Optional.empty(), false);
    }

    public Optional<c_1514_x> R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }
}


