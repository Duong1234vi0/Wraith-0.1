/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  it.unimi.dsi.fastutil.ints.IntRBTreeSet
 *  it.unimi.dsi.fastutil.ints.IntSortedSet
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
import it.unimi.dsi.fastutil.ints.IntSortedSet;
import java.util.List;
import java.util.stream.IntStream;
import lightning.product.SimplexNoise;
import lightning.product.WorldgenRandom;
import lightning.product.SurfaceNoise;

public class PerlinSimplexNoise
implements SurfaceNoise {
    private final SimplexNoise[] n_1700_B;
    private final double J_1907_R;
    private final double R_4764_Y;

    public PerlinSimplexNoise(WorldgenRandom p_i232144_1_, IntStream p_i232144_2_) {
        this(p_i232144_1_, (List)p_i232144_2_.boxed().collect(ImmutableList.toImmutableList()));
    }

    public PerlinSimplexNoise(WorldgenRandom p_i232143_1_, List<Integer> p_i232143_2_) {
        this(p_i232143_1_, (IntSortedSet)new IntRBTreeSet(p_i232143_2_));
    }

    private PerlinSimplexNoise(WorldgenRandom p_i225881_1_, IntSortedSet p_i225881_2_) {
        int j;
        if (p_i225881_2_.isEmpty()) {
            throw new IllegalArgumentException("Need some octaves!");
        }
        int i = -p_i225881_2_.firstInt();
        int k = i + (j = p_i225881_2_.lastInt()) + 1;
        if (k < 1) {
            throw new IllegalArgumentException("Total number of octaves needs to be >= 1");
        }
        SimplexNoise simplexnoisegenerator = new SimplexNoise(p_i225881_1_);
        int l = j;
        this.n_1700_B = new SimplexNoise[k];
        if (j >= 0 && j < k && p_i225881_2_.contains(0)) {
            this.n_1700_B[j] = simplexnoisegenerator;
        }
        for (int i1 = j + 1; i1 < k; ++i1) {
            if (i1 >= 0 && p_i225881_2_.contains(l - i1)) {
                this.n_1700_B[i1] = new SimplexNoise(p_i225881_1_);
                continue;
            }
            p_i225881_1_.n_1700_B(262);
        }
        if (j > 0) {
            long k1 = (long)(simplexnoisegenerator.n_1700_B(simplexnoisegenerator.J_1907_R, simplexnoisegenerator.R_4764_Y, simplexnoisegenerator.G_564_y) * 9.223372036854776E18);
            WorldgenRandom sharedseedrandom = new WorldgenRandom(k1);
            for (int j1 = l - 1; j1 >= 0; --j1) {
                if (j1 < k && p_i225881_2_.contains(l - j1)) {
                    this.n_1700_B[j1] = new SimplexNoise(sharedseedrandom);
                    continue;
                }
                sharedseedrandom.n_1700_B(262);
            }
        }
        this.R_4764_Y = Math.pow(2.0, j);
        this.J_1907_R = 1.0 / (Math.pow(2.0, k) - 1.0);
    }

    public double n_1700_B(double x, double y, boolean useNoiseOffsets) {
        double d0 = 0.0;
        double d1 = this.R_4764_Y;
        double d2 = this.J_1907_R;
        for (SimplexNoise simplexnoisegenerator : this.n_1700_B) {
            if (simplexnoisegenerator != null) {
                d0 += simplexnoisegenerator.n_1700_B(x * d1 + (useNoiseOffsets ? simplexnoisegenerator.J_1907_R : 0.0), y * d1 + (useNoiseOffsets ? simplexnoisegenerator.R_4764_Y : 0.0)) * d2;
            }
            d1 /= 2.0;
            d2 *= 2.0;
        }
        return d0;
    }

    @Override
    public double n_1700_B(double x, double y, double z, double p_215460_7_) {
        return this.n_1700_B(x, y, true) * 0.55;
    }
}


