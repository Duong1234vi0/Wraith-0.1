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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.BlockStateProvider;
import lightning.product.q_4293_E;
import lightning.product.FeatureConfiguration;
import lightning.product.BlockPlacer;

public class RandomPatchConfiguration
implements FeatureConfiguration {
    public static final Codec<RandomPatchConfiguration> n_1700_B = RecordCodecBuilder.create(p_236589_0_ -> p_236589_0_.group((App)BlockStateProvider.J_1907_R.fieldOf("state_provider").forGetter(p_236599_0_ -> p_236599_0_.J_1907_R), (App)BlockPlacer.n_1700_B.fieldOf("block_placer").forGetter(p_236598_0_ -> p_236598_0_.R_4764_Y), (App)K_4074_S.J_1907_R.listOf().fieldOf("whitelist").forGetter(p_236597_0_ -> p_236597_0_.G_564_y.stream().map(T_2915_h::multiplayerClientSuggestionProvider).collect(Collectors.toList())), (App)K_4074_S.J_1907_R.listOf().fieldOf("blacklist").forGetter(p_236596_0_ -> ImmutableList.copyOf(p_236596_0_.P_1922_E)), (App)Codec.INT.fieldOf("tries").orElse((Object)128).forGetter(p_236595_0_ -> p_236595_0_.u_1723_Y), (App)Codec.INT.fieldOf("xspread").orElse((Object)7).forGetter(p_236594_0_ -> p_236594_0_.v_4262_N), (App)Codec.INT.fieldOf("yspread").orElse((Object)3).forGetter(p_236593_0_ -> p_236593_0_.w_1484_f), (App)Codec.INT.fieldOf("zspread").orElse((Object)7).forGetter(p_236592_0_ -> p_236592_0_.t_148_a), (App)Codec.BOOL.fieldOf("can_replace").orElse((Object)false).forGetter(p_236591_0_ -> p_236591_0_.s_956_w), (App)Codec.BOOL.fieldOf("project").orElse((Object)true).forGetter(p_236590_0_ -> p_236590_0_.u_2550_I), (App)Codec.BOOL.fieldOf("need_water").orElse((Object)false).forGetter(p_236588_0_ -> p_236588_0_.M_588_G)).apply((Applicative)p_236589_0_, RandomPatchConfiguration::new));
    public final BlockStateProvider J_1907_R;
    public final BlockPlacer R_4764_Y;
    public final Set<T_2915_h> G_564_y;
    public final Set<K_4074_S> P_1922_E;
    public final int u_1723_Y;
    public final int v_4262_N;
    public final int w_1484_f;
    public final int t_148_a;
    public final boolean s_956_w;
    public final boolean u_2550_I;
    public final boolean M_588_G;

    private RandomPatchConfiguration(BlockStateProvider p_i232014_1_, BlockPlacer p_i232014_2_, List<K_4074_S> p_i232014_3_, List<K_4074_S> p_i232014_4_, int p_i232014_5_, int p_i232014_6_, int p_i232014_7_, int p_i232014_8_, boolean p_i232014_9_, boolean p_i232014_10_, boolean p_i232014_11_) {
        this(p_i232014_1_, p_i232014_2_, p_i232014_3_.stream().map(q_4293_E.n_1700_B::J_1907_R).collect(Collectors.toSet()), (Set<K_4074_S>)ImmutableSet.copyOf(p_i232014_4_), p_i232014_5_, p_i232014_6_, p_i232014_7_, p_i232014_8_, p_i232014_9_, p_i232014_10_, p_i232014_11_);
    }

    private RandomPatchConfiguration(BlockStateProvider stateProvider, BlockPlacer blockPlacer, Set<T_2915_h> whitelist, Set<K_4074_S> p_i225836_4_, int p_i225836_5_, int p_i225836_6_, int p_i225836_7_, int p_i225836_8_, boolean p_i225836_9_, boolean p_i225836_10_, boolean p_i225836_11_) {
        this.J_1907_R = stateProvider;
        this.R_4764_Y = blockPlacer;
        this.G_564_y = whitelist;
        this.P_1922_E = p_i225836_4_;
        this.u_1723_Y = p_i225836_5_;
        this.v_4262_N = p_i225836_6_;
        this.w_1484_f = p_i225836_7_;
        this.t_148_a = p_i225836_8_;
        this.s_956_w = p_i225836_9_;
        this.u_2550_I = p_i225836_10_;
        this.M_588_G = p_i225836_11_;
    }

    public static class n_1700_B {
        private final BlockStateProvider n_1700_B;
        private final BlockPlacer J_1907_R;
        private Set<T_2915_h> R_4764_Y = ImmutableSet.of();
        private Set<K_4074_S> G_564_y = ImmutableSet.of();
        private int P_1922_E = 64;
        private int u_1723_Y = 7;
        private int v_4262_N = 3;
        private int w_1484_f = 7;
        private boolean t_148_a;
        private boolean s_956_w = true;
        private boolean u_2550_I = false;

        public n_1700_B(BlockStateProvider p_i225838_1_, BlockPlacer p_i225838_2_) {
            this.n_1700_B = p_i225838_1_;
            this.J_1907_R = p_i225838_2_;
        }

        public n_1700_B n_1700_B(Set<T_2915_h> p_227316_1_) {
            this.R_4764_Y = p_227316_1_;
            return this;
        }

        public n_1700_B J_1907_R(Set<K_4074_S> p_227319_1_) {
            this.G_564_y = p_227319_1_;
            return this;
        }

        public n_1700_B n_1700_B(int p_227315_1_) {
            this.P_1922_E = p_227315_1_;
            return this;
        }

        public n_1700_B J_1907_R(int p_227318_1_) {
            this.u_1723_Y = p_227318_1_;
            return this;
        }

        public n_1700_B R_4764_Y(int p_227321_1_) {
            this.v_4262_N = p_227321_1_;
            return this;
        }

        public n_1700_B G_564_y(int p_227323_1_) {
            this.w_1484_f = p_227323_1_;
            return this;
        }

        public n_1700_B n_1700_B() {
            this.t_148_a = true;
            return this;
        }

        public n_1700_B J_1907_R() {
            this.s_956_w = false;
            return this;
        }

        public n_1700_B R_4764_Y() {
            this.u_2550_I = true;
            return this;
        }

        public RandomPatchConfiguration G_564_y() {
            return new RandomPatchConfiguration(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I);
        }
    }
}


