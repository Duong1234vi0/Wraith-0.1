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

public class StrongholdConfiguration {
    public static final Codec<StrongholdConfiguration> n_1700_B = RecordCodecBuilder.create(p_236661_0_ -> p_236661_0_.group((App)Codec.intRange((int)0, (int)1023).fieldOf("distance").forGetter(StrongholdConfiguration::n_1700_B), (App)Codec.intRange((int)0, (int)1023).fieldOf("spread").forGetter(StrongholdConfiguration::J_1907_R), (App)Codec.intRange((int)1, (int)4095).fieldOf("count").forGetter(StrongholdConfiguration::R_4764_Y)).apply((Applicative)p_236661_0_, StrongholdConfiguration::new));
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;

    public StrongholdConfiguration(int p_i232018_1_, int p_i232018_2_, int p_i232018_3_) {
        this.J_1907_R = p_i232018_1_;
        this.R_4764_Y = p_i232018_2_;
        this.G_564_y = p_i232018_3_;
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


