/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.Products$P3
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Instance
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Mu
 */
package lightning.product;

import com.mojang.datafixers.Products;
import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lightning.product.TrunkPlacerType;
import lightning.product.K_4074_S;
import lightning.product.TreeFeature;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.FoliagePlacer;
import lightning.product.LevelSimulatedReader;
import lightning.product.LevelWriter;

public abstract class TrunkPlacer {
    public static final Codec<TrunkPlacer> n_1700_B = V_3137_a.j_2266_I.dispatch(TrunkPlacer::n_1700_B, TrunkPlacerType::n_1700_B);
    protected final int J_1907_R;
    protected final int R_4764_Y;
    protected final int G_564_y;

    protected static <P extends TrunkPlacer> Products.P3<RecordCodecBuilder.Mu<P>, Integer, Integer, Integer> n_1700_B(RecordCodecBuilder.Instance<P> p_236915_0_) {
        return p_236915_0_.group((App)Codec.intRange((int)0, (int)32).fieldOf("base_height").forGetter(p_236919_0_ -> p_236919_0_.J_1907_R), (App)Codec.intRange((int)0, (int)24).fieldOf("height_rand_a").forGetter(p_236918_0_ -> p_236918_0_.R_4764_Y), (App)Codec.intRange((int)0, (int)24).fieldOf("height_rand_b").forGetter(p_236916_0_ -> p_236916_0_.G_564_y));
    }

    public TrunkPlacer(int p_i232060_1_, int p_i232060_2_, int p_i232060_3_) {
        this.J_1907_R = p_i232060_1_;
        this.R_4764_Y = p_i232060_2_;
        this.G_564_y = p_i232060_3_;
    }

    protected abstract TrunkPlacerType<?> n_1700_B();

    public abstract List<FoliagePlacer.n_1700_B> n_1700_B(LevelSimulatedRW var1, Random var2, int var3, c_1514_x var4, Set<c_1514_x> var5, BoundingBox var6, TreeConfiguration var7);

    public int n_1700_B(Random p_236917_1_) {
        return this.J_1907_R + p_236917_1_.nextInt(this.R_4764_Y + 1) + p_236917_1_.nextInt(this.G_564_y + 1);
    }

    protected static void n_1700_B(LevelWriter p_236913_0_, c_1514_x p_236913_1_, K_4074_S p_236913_2_, BoundingBox p_236913_3_) {
        TreeFeature.J_1907_R(p_236913_0_, p_236913_1_, p_236913_2_);
        p_236913_3_.J_1907_R(new BoundingBox(p_236913_1_, p_236913_1_));
    }

    private static boolean n_1700_B(LevelSimulatedReader p_236912_0_, c_1514_x p_236912_1_) {
        return p_236912_0_.n_1700_B(p_236912_1_, p_236914_0_ -> {
            T_2915_h block = p_236914_0_.J_1907_R();
            return Feature.J_1907_R(block) && !p_236914_0_.n_1700_B(a_3742_W.t_148_a) && !p_236914_0_.n_1700_B(a_3742_W.A_2714_y);
        });
    }

    protected static void n_1700_B(LevelSimulatedRW p_236909_0_, c_1514_x p_236909_1_) {
        if (!TrunkPlacer.n_1700_B((LevelSimulatedReader)p_236909_0_, p_236909_1_)) {
            TreeFeature.J_1907_R(p_236909_0_, p_236909_1_, a_3742_W.s_956_w.multiplayerClientSuggestionProvider());
        }
    }

    protected static boolean n_1700_B(LevelSimulatedRW p_236911_0_, Random p_236911_1_, c_1514_x p_236911_2_, Set<c_1514_x> p_236911_3_, BoundingBox p_236911_4_, TreeConfiguration p_236911_5_) {
        if (TreeFeature.P_1922_E(p_236911_0_, p_236911_2_)) {
            TrunkPlacer.n_1700_B(p_236911_0_, p_236911_2_, p_236911_5_.J_1907_R.n_1700_B(p_236911_1_, p_236911_2_), p_236911_4_);
            p_236911_3_.add(p_236911_2_.toImmutable());
            return true;
        }
        return false;
    }

    protected static void n_1700_B(LevelSimulatedRW p_236910_0_, Random p_236910_1_, c_1514_x.n_1700_B p_236910_2_, Set<c_1514_x> p_236910_3_, BoundingBox p_236910_4_, TreeConfiguration p_236910_5_) {
        if (TreeFeature.R_4764_Y(p_236910_0_, p_236910_2_)) {
            TrunkPlacer.n_1700_B(p_236910_0_, p_236910_1_, (c_1514_x)p_236910_2_, p_236910_3_, p_236910_4_, p_236910_5_);
        }
    }
}


