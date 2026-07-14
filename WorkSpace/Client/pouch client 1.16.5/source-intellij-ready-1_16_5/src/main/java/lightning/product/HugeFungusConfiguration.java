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
import lightning.product.a_3742_W;
import lightning.product.FeatureConfiguration;

public class HugeFungusConfiguration
implements FeatureConfiguration {
    public static final Codec<HugeFungusConfiguration> n_1700_B = RecordCodecBuilder.create(p_236309_0_ -> p_236309_0_.group((App)K_4074_S.J_1907_R.fieldOf("valid_base_block").forGetter(p_236313_0_ -> p_236313_0_.u_1723_Y), (App)K_4074_S.J_1907_R.fieldOf("stem_state").forGetter(p_236312_0_ -> p_236312_0_.v_4262_N), (App)K_4074_S.J_1907_R.fieldOf("hat_state").forGetter(p_236311_0_ -> p_236311_0_.w_1484_f), (App)K_4074_S.J_1907_R.fieldOf("decor_state").forGetter(p_236310_0_ -> p_236310_0_.t_148_a), (App)Codec.BOOL.fieldOf("planted").orElse((Object)false).forGetter(p_236308_0_ -> p_236308_0_.s_956_w)).apply((Applicative)p_236309_0_, HugeFungusConfiguration::new));
    public static final HugeFungusConfiguration J_1907_R = new HugeFungusConfiguration(a_3742_W.ServerFunctionManager.multiplayerClientSuggestionProvider(), a_3742_W.T_4001_f.multiplayerClientSuggestionProvider(), a_3742_W.LockSlot.multiplayerClientSuggestionProvider(), a_3742_W.CriterionTrigger.multiplayerClientSuggestionProvider(), true);
    public static final HugeFungusConfiguration R_4764_Y;
    public static final HugeFungusConfiguration G_564_y;
    public static final HugeFungusConfiguration P_1922_E;
    public final K_4074_S u_1723_Y;
    public final K_4074_S v_4262_N;
    public final K_4074_S w_1484_f;
    public final K_4074_S t_148_a;
    public final boolean s_956_w;

    public HugeFungusConfiguration(K_4074_S p_i231958_1_, K_4074_S p_i231958_2_, K_4074_S p_i231958_3_, K_4074_S p_i231958_4_, boolean p_i231958_5_) {
        this.u_1723_Y = p_i231958_1_;
        this.v_4262_N = p_i231958_2_;
        this.w_1484_f = p_i231958_3_;
        this.t_148_a = p_i231958_4_;
        this.s_956_w = p_i231958_5_;
    }

    static {
        G_564_y = new HugeFungusConfiguration(a_3742_W.ServerAdvancementManager.multiplayerClientSuggestionProvider(), a_3742_W.X_1303_p.multiplayerClientSuggestionProvider(), a_3742_W.J_1008_m.multiplayerClientSuggestionProvider(), a_3742_W.CriterionTrigger.multiplayerClientSuggestionProvider(), true);
        R_4764_Y = new HugeFungusConfiguration(HugeFungusConfiguration.J_1907_R.u_1723_Y, HugeFungusConfiguration.J_1907_R.v_4262_N, HugeFungusConfiguration.J_1907_R.w_1484_f, HugeFungusConfiguration.J_1907_R.t_148_a, false);
        P_1922_E = new HugeFungusConfiguration(HugeFungusConfiguration.G_564_y.u_1723_Y, HugeFungusConfiguration.G_564_y.v_4262_N, HugeFungusConfiguration.G_564_y.w_1484_f, HugeFungusConfiguration.G_564_y.t_148_a, false);
    }
}



