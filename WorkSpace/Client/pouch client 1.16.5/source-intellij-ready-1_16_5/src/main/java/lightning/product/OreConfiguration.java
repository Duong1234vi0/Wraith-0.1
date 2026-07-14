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
import lightning.product.RuleTest;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.TagMatchTest;
import lightning.product.BlockTags;
import lightning.product.FeatureConfiguration;
import lightning.product.BlockMatchTest;

public class OreConfiguration
implements FeatureConfiguration {
    public static final Codec<OreConfiguration> n_1700_B = RecordCodecBuilder.create(p_236568_0_ -> p_236568_0_.group((App)RuleTest.R_4764_Y.fieldOf("target").forGetter(p_236570_0_ -> p_236570_0_.J_1907_R), (App)K_4074_S.J_1907_R.fieldOf("state").forGetter(p_236569_0_ -> p_236569_0_.G_564_y), (App)Codec.intRange((int)0, (int)64).fieldOf("size").forGetter(p_236567_0_ -> p_236567_0_.R_4764_Y)).apply((Applicative)p_236568_0_, OreConfiguration::new));
    public final RuleTest J_1907_R;
    public final int R_4764_Y;
    public final K_4074_S G_564_y;

    public OreConfiguration(RuleTest p_i241989_1_, K_4074_S p_i241989_2_, int p_i241989_3_) {
        this.R_4764_Y = p_i241989_3_;
        this.G_564_y = p_i241989_2_;
        this.J_1907_R = p_i241989_1_;
    }

    public static final class n_1700_B {
        public static final RuleTest n_1700_B = new TagMatchTest(BlockTags.RegionPingResult);
        public static final RuleTest J_1907_R = new BlockMatchTest(a_3742_W.i_3196_G);
        public static final RuleTest R_4764_Y = new TagMatchTest(BlockTags.H_1083_k);
    }
}


