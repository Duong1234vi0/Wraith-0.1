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

public class DepthAverageConfigation
implements P_1781_m {
    public static final Codec<DepthAverageConfigation> n_1700_B = RecordCodecBuilder.create(p_236956_0_ -> p_236956_0_.group((App)Codec.INT.fieldOf("baseline").forGetter(p_236959_0_ -> p_236959_0_.J_1907_R), (App)Codec.INT.fieldOf("spread").forGetter(p_236958_0_ -> p_236958_0_.R_4764_Y)).apply((Applicative)p_236956_0_, DepthAverageConfigation::new));
    public final int J_1907_R;
    public final int R_4764_Y;

    public DepthAverageConfigation(int p_i242022_1_, int p_i242022_2_) {
        this.J_1907_R = p_i242022_1_;
        this.R_4764_Y = p_i242022_2_;
    }
}


