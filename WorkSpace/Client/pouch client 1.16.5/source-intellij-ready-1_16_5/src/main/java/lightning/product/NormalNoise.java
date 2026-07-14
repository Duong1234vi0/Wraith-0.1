/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 *  it.unimi.dsi.fastutil.doubles.DoubleListIterator
 */
package lightning.product;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleListIterator;
import lightning.product.WorldgenRandom;
import lightning.product.PerlinNoise;

public class NormalNoise {
    private final double n_1700_B;
    private final PerlinNoise J_1907_R;
    private final PerlinNoise R_4764_Y;

    public static NormalNoise n_1700_B(WorldgenRandom p_242930_0_, int p_242930_1_, DoubleList p_242930_2_) {
        return new NormalNoise(p_242930_0_, p_242930_1_, p_242930_2_);
    }

    private NormalNoise(WorldgenRandom p_i242039_1_, int p_i242039_2_, DoubleList p_i242039_3_) {
        this.J_1907_R = PerlinNoise.n_1700_B(p_i242039_1_, p_i242039_2_, p_i242039_3_);
        this.R_4764_Y = PerlinNoise.n_1700_B(p_i242039_1_, p_i242039_2_, p_i242039_3_);
        int i = Integer.MAX_VALUE;
        int j = Integer.MIN_VALUE;
        DoubleListIterator doublelistiterator = p_i242039_3_.iterator();
        while (doublelistiterator.hasNext()) {
            int k = doublelistiterator.nextIndex();
            double d0 = doublelistiterator.nextDouble();
            if (d0 == 0.0) continue;
            i = Math.min(i, k);
            j = Math.max(j, k);
        }
        this.n_1700_B = 0.16666666666666666 / NormalNoise.n_1700_B(j - i);
    }

    private static double n_1700_B(int p_237212_0_) {
        return 0.1 * (1.0 + 1.0 / (double)(p_237212_0_ + 1));
    }

    public double n_1700_B(double p_237211_1_, double p_237211_3_, double p_237211_5_) {
        double d0 = p_237211_1_ * 1.0181268882175227;
        double d1 = p_237211_3_ * 1.0181268882175227;
        double d2 = p_237211_5_ * 1.0181268882175227;
        return (this.J_1907_R.n_1700_B(p_237211_1_, p_237211_3_, p_237211_5_) + this.R_4764_Y.n_1700_B(d0, d1, d2)) * this.n_1700_B;
    }
}


