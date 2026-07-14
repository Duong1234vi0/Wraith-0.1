/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.P_1781_m;

public class ChanceDecoratorConfiguration
implements P_1781_m {
    public static final Codec<ChanceDecoratorConfiguration> n_1700_B = Codec.INT.fieldOf("chance").xmap(ChanceDecoratorConfiguration::new, p_236951_0_ -> p_236951_0_.J_1907_R).codec();
    public final int J_1907_R;

    public ChanceDecoratorConfiguration(int chance) {
        this.J_1907_R = chance;
    }
}


