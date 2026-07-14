/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 *  it.unimi.dsi.fastutil.ints.IntBidirectionalIterator
 *  it.unimi.dsi.fastutil.ints.IntRBTreeSet
 *  it.unimi.dsi.fastutil.ints.IntSortedSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.ints.IntBidirectionalIterator;
import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
import it.unimi.dsi.fastutil.ints.IntSortedSet;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.ImprovedNoise;
import lightning.product.WorldgenRandom;
import lightning.product.SurfaceNoise;
import lightning.product.u_530_F;

public class PerlinNoise
implements SurfaceNoise {
    private final ImprovedNoise[] n_1700_B;
    private final DoubleList J_1907_R;
    private final double R_4764_Y;
    private final double G_564_y;

    public PerlinNoise(WorldgenRandom p_i232142_1_, IntStream p_i232142_2_) {
        this(p_i232142_1_, (List)p_i232142_2_.boxed().collect(ImmutableList.toImmutableList()));
    }

    public PerlinNoise(WorldgenRandom p_i232141_1_, List<Integer> p_i232141_2_) {
        this(p_i232141_1_, (IntSortedSet)new IntRBTreeSet(p_i232141_2_));
    }

    public static PerlinNoise n_1700_B(WorldgenRandom p_242932_0_, int p_242932_1_, DoubleList p_242932_2_) {
        return new PerlinNoise(p_242932_0_, (Pair<Integer, DoubleList>)Pair.of((Object)p_242932_1_, (Object)p_242932_2_));
    }

    private static Pair<Integer, DoubleList> n_1700_B(IntSortedSet p_242933_0_) {
        int j;
        if (p_242933_0_.isEmpty()) {
            throw new IllegalArgumentException("Need some octaves!");
        }
        int i = -p_242933_0_.firstInt();
        int k = i + (j = p_242933_0_.lastInt()) + 1;
        if (k < 1) {
            throw new IllegalArgumentException("Total number of octaves needs to be >= 1");
        }
        DoubleArrayList doublelist = new DoubleArrayList(new double[k]);
        IntBidirectionalIterator intbidirectionaliterator = p_242933_0_.iterator();
        while (intbidirectionaliterator.hasNext()) {
            int l = intbidirectionaliterator.nextInt();
            doublelist.set(l + i, 1.0);
        }
        return Pair.of((Object)(-i), (Object)doublelist);
    }

    private PerlinNoise(WorldgenRandom p_i225879_1_, IntSortedSet p_i225879_2_) {
        this(p_i225879_1_, PerlinNoise.n_1700_B(p_i225879_2_));
    }

    private PerlinNoise(WorldgenRandom p_i242040_1_, Pair<Integer, DoubleList> p_i242040_2_) {
        double d0;
        int i = (Integer)p_i242040_2_.getFirst();
        this.J_1907_R = (DoubleList)p_i242040_2_.getSecond();
        ImprovedNoise improvednoisegenerator = new ImprovedNoise(p_i242040_1_);
        int j = this.J_1907_R.size();
        int k = -i;
        this.n_1700_B = new ImprovedNoise[j];
        if (k >= 0 && k < j && (d0 = this.J_1907_R.getDouble(k)) != 0.0) {
            this.n_1700_B[k] = improvednoisegenerator;
        }
        for (int i1 = k - 1; i1 >= 0; --i1) {
            if (i1 < j) {
                double d1 = this.J_1907_R.getDouble(i1);
                if (d1 != 0.0) {
                    this.n_1700_B[i1] = new ImprovedNoise(p_i242040_1_);
                    continue;
                }
                p_i242040_1_.n_1700_B(262);
                continue;
            }
            p_i242040_1_.n_1700_B(262);
        }
        if (k < j - 1) {
            long j1 = (long)(improvednoisegenerator.n_1700_B(0.0, 0.0, 0.0, 0.0, 0.0) * 9.223372036854776E18);
            WorldgenRandom sharedseedrandom = new WorldgenRandom(j1);
            for (int l = k + 1; l < j; ++l) {
                if (l >= 0) {
                    double d2 = this.J_1907_R.getDouble(l);
                    if (d2 != 0.0) {
                        this.n_1700_B[l] = new ImprovedNoise(sharedseedrandom);
                        continue;
                    }
                    sharedseedrandom.n_1700_B(262);
                    continue;
                }
                sharedseedrandom.n_1700_B(262);
            }
        }
        this.G_564_y = Math.pow(2.0, -k);
        this.R_4764_Y = Math.pow(2.0, j - 1) / (Math.pow(2.0, j) - 1.0);
    }

    public double n_1700_B(double p_205563_1_, double p_205563_3_, double p_205563_5_) {
        return this.n_1700_B(p_205563_1_, p_205563_3_, p_205563_5_, 0.0, 0.0, false);
    }

    public double n_1700_B(double x, double y, double z, double p_215462_7_, double p_215462_9_, boolean p_215462_11_) {
        double d0 = 0.0;
        double d1 = this.G_564_y;
        double d2 = this.R_4764_Y;
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            ImprovedNoise improvednoisegenerator = this.n_1700_B[i];
            if (improvednoisegenerator != null) {
                d0 += this.J_1907_R.getDouble(i) * improvednoisegenerator.n_1700_B(PerlinNoise.n_1700_B(x * d1), p_215462_11_ ? -improvednoisegenerator.J_1907_R : PerlinNoise.n_1700_B(y * d1), PerlinNoise.n_1700_B(z * d1), p_215462_7_ * d1, p_215462_9_ * d1) * d2;
            }
            d1 *= 2.0;
            d2 /= 2.0;
        }
        return d0;
    }

    @Nullable
    public ImprovedNoise n_1700_B(int octaveIndex) {
        return this.n_1700_B[this.n_1700_B.length - 1 - octaveIndex];
    }

    public static double n_1700_B(double p_215461_0_) {
        return p_215461_0_ - (double)u_530_F.G_564_y(p_215461_0_ / 3.3554432E7 + 0.5) * 3.3554432E7;
    }

    @Override
    public double n_1700_B(double x, double y, double z, double p_215460_7_) {
        return this.n_1700_B(x, y, 0.0, z, p_215460_7_, false);
    }
}


