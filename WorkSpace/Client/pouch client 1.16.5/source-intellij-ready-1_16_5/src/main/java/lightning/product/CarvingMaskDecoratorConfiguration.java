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
import lightning.product.T_3975_o;

public class CarvingMaskDecoratorConfiguration
implements P_1781_m {
    public static final Codec<CarvingMaskDecoratorConfiguration> n_1700_B = RecordCodecBuilder.create(p_236947_0_ -> p_236947_0_.group((App)T_3975_o.n_1700_B.R_4764_Y.fieldOf("step").forGetter(p_236949_0_ -> p_236949_0_.J_1907_R), (App)Codec.FLOAT.fieldOf("probability").forGetter(p_236948_0_ -> Float.valueOf(p_236948_0_.R_4764_Y))).apply((Applicative)p_236947_0_, CarvingMaskDecoratorConfiguration::new));
    protected final T_3975_o.n_1700_B J_1907_R;
    protected final float R_4764_Y;

    public CarvingMaskDecoratorConfiguration(T_3975_o.n_1700_B step, float probability) {
        this.J_1907_R = step;
        this.R_4764_Y = probability;
    }
}


