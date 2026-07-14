/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.Products$P2
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
import java.util.Random;
import java.util.Set;
import lightning.product.TreeFeature;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.FoliagePlacerType;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.g_1198_o;

public abstract class FoliagePlacer {
    public static final Codec<FoliagePlacer> G_564_y = V_3137_a.LongRunningTask.dispatch(FoliagePlacer::n_1700_B, FoliagePlacerType::n_1700_B);
    protected final g_1198_o P_1922_E;
    protected final g_1198_o u_1723_Y;

    protected static <P extends FoliagePlacer> Products.P2<RecordCodecBuilder.Mu<P>, g_1198_o, g_1198_o> J_1907_R(RecordCodecBuilder.Instance<P> p_242830_0_) {
        return p_242830_0_.group((App)g_1198_o.n_1700_B(0, 8, 8).fieldOf("radius").forGetter(p_242829_0_ -> p_242829_0_.P_1922_E), (App)g_1198_o.n_1700_B(0, 8, 8).fieldOf("offset").forGetter(p_242828_0_ -> p_242828_0_.u_1723_Y));
    }

    public FoliagePlacer(g_1198_o p_i241999_1_, g_1198_o p_i241999_2_) {
        this.P_1922_E = p_i241999_1_;
        this.u_1723_Y = p_i241999_2_;
    }

    protected abstract FoliagePlacerType<?> n_1700_B();

    public void n_1700_B(LevelSimulatedRW p_236752_1_, Random p_236752_2_, TreeConfiguration p_236752_3_, int p_236752_4_, n_1700_B p_236752_5_, int p_236752_6_, int p_236752_7_, Set<c_1514_x> p_236752_8_, BoundingBox p_236752_9_) {
        this.n_1700_B(p_236752_1_, p_236752_2_, p_236752_3_, p_236752_4_, p_236752_5_, p_236752_6_, p_236752_7_, p_236752_8_, this.n_1700_B(p_236752_2_), p_236752_9_);
    }

    protected abstract void n_1700_B(LevelSimulatedRW var1, Random var2, TreeConfiguration var3, int var4, n_1700_B var5, int var6, int var7, Set<c_1514_x> var8, int var9, BoundingBox var10);

    public abstract int n_1700_B(Random var1, int var2, TreeConfiguration var3);

    public int n_1700_B(Random p_230376_1_, int p_230376_2_) {
        return this.P_1922_E.n_1700_B(p_230376_1_);
    }

    private int n_1700_B(Random p_236755_1_) {
        return this.u_1723_Y.n_1700_B(p_236755_1_);
    }

    protected abstract boolean n_1700_B(Random var1, int var2, int var3, int var4, int var5, boolean var6);

    protected boolean J_1907_R(Random p_230375_1_, int p_230375_2_, int p_230375_3_, int p_230375_4_, int p_230375_5_, boolean p_230375_6_) {
        int j;
        int i;
        if (p_230375_6_) {
            i = Math.min(Math.abs(p_230375_2_), Math.abs(p_230375_2_ - 1));
            j = Math.min(Math.abs(p_230375_4_), Math.abs(p_230375_4_ - 1));
        } else {
            i = Math.abs(p_230375_2_);
            j = Math.abs(p_230375_4_);
        }
        return this.n_1700_B(p_230375_1_, i, p_230375_3_, j, p_230375_5_, p_230375_6_);
    }

    protected void n_1700_B(LevelSimulatedRW p_236753_1_, Random p_236753_2_, TreeConfiguration p_236753_3_, c_1514_x p_236753_4_, int p_236753_5_, Set<c_1514_x> p_236753_6_, int p_236753_7_, boolean p_236753_8_, BoundingBox p_236753_9_) {
        int i = p_236753_8_ ? 1 : 0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int j = -p_236753_5_; j <= p_236753_5_ + i; ++j) {
            for (int k = -p_236753_5_; k <= p_236753_5_ + i; ++k) {
                if (this.J_1907_R(p_236753_2_, j, p_236753_7_, k, p_236753_5_, p_236753_8_)) continue;
                blockpos$mutable.n_1700_B(p_236753_4_, j, p_236753_7_, k);
                if (!TreeFeature.P_1922_E(p_236753_1_, blockpos$mutable)) continue;
                p_236753_1_.n_1700_B((c_1514_x)blockpos$mutable, p_236753_3_.R_4764_Y.n_1700_B(p_236753_2_, blockpos$mutable), 19);
                p_236753_9_.J_1907_R(new BoundingBox(blockpos$mutable, blockpos$mutable));
                p_236753_6_.add(blockpos$mutable.toImmutable());
            }
        }
    }

    public static final class n_1700_B {
        private final c_1514_x n_1700_B;
        private final int J_1907_R;
        private final boolean R_4764_Y;

        public n_1700_B(c_1514_x p_i232035_1_, int p_i232035_2_, boolean p_i232035_3_) {
            this.n_1700_B = p_i232035_1_;
            this.J_1907_R = p_i232035_2_;
            this.R_4764_Y = p_i232035_3_;
        }

        public c_1514_x n_1700_B() {
            return this.n_1700_B;
        }

        public int J_1907_R() {
            return this.J_1907_R;
        }

        public boolean R_4764_Y() {
            return this.R_4764_Y;
        }
    }
}


