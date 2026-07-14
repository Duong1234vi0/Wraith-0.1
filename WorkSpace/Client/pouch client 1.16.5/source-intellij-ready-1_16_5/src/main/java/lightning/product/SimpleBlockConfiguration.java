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
import lightning.product.FeatureConfiguration;

public class SimpleBlockConfiguration
implements FeatureConfiguration {
    public static final Codec<SimpleBlockConfiguration> n_1700_B = RecordCodecBuilder.create(p_236638_0_ -> p_236638_0_.group((App)K_4074_S.J_1907_R.fieldOf("to_place").forGetter(p_236641_0_ -> p_236641_0_.J_1907_R), (App)K_4074_S.J_1907_R.listOf().fieldOf("place_on").forGetter(p_236640_0_ -> p_236640_0_.R_4764_Y), (App)K_4074_S.J_1907_R.listOf().fieldOf("place_in").forGetter(p_236639_0_ -> p_236639_0_.G_564_y), (App)K_4074_S.J_1907_R.listOf().fieldOf("place_under").forGetter(p_236637_0_ -> p_236637_0_.P_1922_E)).apply((Applicative)p_236638_0_, SimpleBlockConfiguration::new));
    public final K_4074_S J_1907_R;
    public final List<K_4074_S> R_4764_Y;
    public final List<K_4074_S> G_564_y;
    public final List<K_4074_S> P_1922_E;

    public SimpleBlockConfiguration(K_4074_S toPlace, List<K_4074_S> placeOn, List<K_4074_S> placeIn, List<K_4074_S> placeUnder) {
        this.J_1907_R = toPlace;
        this.R_4764_Y = placeOn;
        this.G_564_y = placeIn;
        this.P_1922_E = placeUnder;
    }
}


