/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import lightning.product.FeatureSize;
import lightning.product.TreeDecorator;
import lightning.product.TrunkPlacer;
import lightning.product.BlockStateProvider;
import lightning.product.FoliagePlacer;
import lightning.product.FeatureConfiguration;
import lightning.product.z_2963_s;

public class TreeConfiguration
implements FeatureConfiguration {
    public static final Codec<TreeConfiguration> n_1700_B = RecordCodecBuilder.create(p_236683_0_ -> p_236683_0_.group((App)BlockStateProvider.J_1907_R.fieldOf("trunk_provider").forGetter(p_236693_0_ -> p_236693_0_.J_1907_R), (App)BlockStateProvider.J_1907_R.fieldOf("leaves_provider").forGetter(p_236692_0_ -> p_236692_0_.R_4764_Y), (App)FoliagePlacer.G_564_y.fieldOf("foliage_placer").forGetter(p_236691_0_ -> p_236691_0_.u_1723_Y), (App)TrunkPlacer.n_1700_B.fieldOf("trunk_placer").forGetter(p_236690_0_ -> p_236690_0_.v_4262_N), (App)FeatureSize.n_1700_B.fieldOf("minimum_size").forGetter(p_236689_0_ -> p_236689_0_.w_1484_f), (App)TreeDecorator.R_4764_Y.listOf().fieldOf("decorators").forGetter(p_236688_0_ -> p_236688_0_.G_564_y), (App)Codec.INT.fieldOf("max_water_depth").orElse((Object)0).forGetter(p_236687_0_ -> p_236687_0_.t_148_a), (App)Codec.BOOL.fieldOf("ignore_vines").orElse((Object)false).forGetter(p_236686_0_ -> p_236686_0_.s_956_w), (App)z_2963_s.n_1700_B.v_4262_N.fieldOf("heightmap").forGetter(p_236684_0_ -> p_236684_0_.u_2550_I)).apply((Applicative)p_236683_0_, TreeConfiguration::new));
    public final BlockStateProvider J_1907_R;
    public final BlockStateProvider R_4764_Y;
    public final List<TreeDecorator> G_564_y;
    public transient boolean P_1922_E;
    public final FoliagePlacer u_1723_Y;
    public final TrunkPlacer v_4262_N;
    public final FeatureSize w_1484_f;
    public final int t_148_a;
    public final boolean s_956_w;
    public final z_2963_s.n_1700_B u_2550_I;

    protected TreeConfiguration(BlockStateProvider p_i232020_1_, BlockStateProvider p_i232020_2_, FoliagePlacer p_i232020_3_, TrunkPlacer p_i232020_4_, FeatureSize p_i232020_5_, List<TreeDecorator> p_i232020_6_, int p_i232020_7_, boolean p_i232020_8_, z_2963_s.n_1700_B p_i232020_9_) {
        this.J_1907_R = p_i232020_1_;
        this.R_4764_Y = p_i232020_2_;
        this.G_564_y = p_i232020_6_;
        this.u_1723_Y = p_i232020_3_;
        this.w_1484_f = p_i232020_5_;
        this.v_4262_N = p_i232020_4_;
        this.t_148_a = p_i232020_7_;
        this.s_956_w = p_i232020_8_;
        this.u_2550_I = p_i232020_9_;
    }

    public void n_1700_B() {
        this.P_1922_E = true;
    }

    public TreeConfiguration n_1700_B(List<TreeDecorator> p_236685_1_) {
        return new TreeConfiguration(this.J_1907_R, this.R_4764_Y, this.u_1723_Y, this.v_4262_N, this.w_1484_f, p_236685_1_, this.t_148_a, this.s_956_w, this.u_2550_I);
    }

    public static class n_1700_B {
        public final BlockStateProvider n_1700_B;
        public final BlockStateProvider J_1907_R;
        private final FoliagePlacer R_4764_Y;
        private final TrunkPlacer G_564_y;
        private final FeatureSize P_1922_E;
        private List<TreeDecorator> u_1723_Y = ImmutableList.of();
        private int v_4262_N;
        private boolean w_1484_f;
        private z_2963_s.n_1700_B t_148_a = z_2963_s.n_1700_B.G_564_y;

        public n_1700_B(BlockStateProvider p_i232021_1_, BlockStateProvider p_i232021_2_, FoliagePlacer p_i232021_3_, TrunkPlacer p_i232021_4_, FeatureSize p_i232021_5_) {
            this.n_1700_B = p_i232021_1_;
            this.J_1907_R = p_i232021_2_;
            this.R_4764_Y = p_i232021_3_;
            this.G_564_y = p_i232021_4_;
            this.P_1922_E = p_i232021_5_;
        }

        public n_1700_B n_1700_B(List<TreeDecorator> p_236703_1_) {
            this.u_1723_Y = p_236703_1_;
            return this;
        }

        public n_1700_B n_1700_B(int p_236701_1_) {
            this.v_4262_N = p_236701_1_;
            return this;
        }

        public n_1700_B n_1700_B() {
            this.w_1484_f = true;
            return this;
        }

        public n_1700_B n_1700_B(z_2963_s.n_1700_B p_236702_1_) {
            this.t_148_a = p_236702_1_;
            return this;
        }

        public TreeConfiguration J_1907_R() {
            return new TreeConfiguration(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a);
        }
    }
}


