/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Set;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.FluidState;
import lightning.product.FeatureConfiguration;

public class SpringConfiguration
implements FeatureConfiguration {
    public static final Codec<SpringConfiguration> n_1700_B = RecordCodecBuilder.create(p_236650_0_ -> p_236650_0_.group((App)FluidState.n_1700_B.fieldOf("state").forGetter(p_236655_0_ -> p_236655_0_.J_1907_R), (App)Codec.BOOL.fieldOf("requires_block_below").orElse((Object)true).forGetter(p_236654_0_ -> p_236654_0_.R_4764_Y), (App)Codec.INT.fieldOf("rock_count").orElse((Object)4).forGetter(p_236653_0_ -> p_236653_0_.G_564_y), (App)Codec.INT.fieldOf("hole_count").orElse((Object)1).forGetter(p_236652_0_ -> p_236652_0_.P_1922_E), (App)V_3137_a.q_4610_l.listOf().fieldOf("valid_blocks").xmap(ImmutableSet::copyOf, ImmutableList::copyOf).forGetter(p_236651_0_ -> p_236651_0_.u_1723_Y)).apply((Applicative)p_236650_0_, SpringConfiguration::new));
    public final FluidState J_1907_R;
    public final boolean R_4764_Y;
    public final int G_564_y;
    public final int P_1922_E;
    public final Set<T_2915_h> u_1723_Y;

    public SpringConfiguration(FluidState p_i225841_1_, boolean p_i225841_2_, int p_i225841_3_, int p_i225841_4_, Set<T_2915_h> p_i225841_5_) {
        this.J_1907_R = p_i225841_1_;
        this.R_4764_Y = p_i225841_2_;
        this.G_564_y = p_i225841_3_;
        this.P_1922_E = p_i225841_4_;
        this.u_1723_Y = p_i225841_5_;
    }
}


