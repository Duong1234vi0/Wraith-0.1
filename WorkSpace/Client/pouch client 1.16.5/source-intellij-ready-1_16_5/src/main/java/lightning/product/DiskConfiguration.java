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
import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.g_1198_o;
import lightning.product.FeatureConfiguration;

public class DiskConfiguration
implements FeatureConfiguration {
    public static final Codec<DiskConfiguration> n_1700_B = RecordCodecBuilder.create(p_236518_0_ -> p_236518_0_.group((App)K_4074_S.J_1907_R.fieldOf("state").forGetter(p_236521_0_ -> p_236521_0_.J_1907_R), (App)g_1198_o.n_1700_B(0, 4, 4).fieldOf("radius").forGetter(p_236520_0_ -> p_236520_0_.R_4764_Y), (App)Codec.intRange((int)0, (int)4).fieldOf("half_height").forGetter(p_236519_0_ -> p_236519_0_.G_564_y), (App)K_4074_S.J_1907_R.listOf().fieldOf("targets").forGetter(p_236517_0_ -> p_236517_0_.P_1922_E)).apply((Applicative)p_236518_0_, DiskConfiguration::new));
    public final K_4074_S J_1907_R;
    public final g_1198_o R_4764_Y;
    public final int G_564_y;
    public final List<K_4074_S> P_1922_E;

    public DiskConfiguration(K_4074_S p_i241986_1_, g_1198_o p_i241986_2_, int p_i241986_3_, List<K_4074_S> p_i241986_4_) {
        this.J_1907_R = p_i241986_1_;
        this.R_4764_Y = p_i241986_2_;
        this.G_564_y = p_i241986_3_;
        this.P_1922_E = p_i241986_4_;
    }
}


