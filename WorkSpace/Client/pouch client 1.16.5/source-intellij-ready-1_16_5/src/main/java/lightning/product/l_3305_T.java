/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.BitSet;
import java.util.Random;
import lightning.product.WorldGenLevel;
import lightning.product.c_1514_x;
import lightning.product.OreConfiguration;
import lightning.product.Feature;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class l_3305_T
extends Feature<OreConfiguration> {
    public l_3305_T(Codec<OreConfiguration> p_i231976_1_) {
        super(p_i231976_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, OreConfiguration p_241855_5_) {
        float f = p_241855_3_.nextFloat() * (float)Math.PI;
        float f1 = (float)p_241855_5_.R_4764_Y / 8.0f;
        int i = u_530_F.u_1723_Y(((float)p_241855_5_.R_4764_Y / 16.0f * 2.0f + 1.0f) / 2.0f);
        double d0 = (double)p_241855_4_.getX() + Math.sin(f) * (double)f1;
        double d1 = (double)p_241855_4_.getX() - Math.sin(f) * (double)f1;
        double d2 = (double)p_241855_4_.getZ() + Math.cos(f) * (double)f1;
        double d3 = (double)p_241855_4_.getZ() - Math.cos(f) * (double)f1;
        int j = 2;
        double d4 = p_241855_4_.getY() + p_241855_3_.nextInt(3) - 2;
        double d5 = p_241855_4_.getY() + p_241855_3_.nextInt(3) - 2;
        int k = p_241855_4_.getX() - u_530_F.u_1723_Y(f1) - i;
        int l = p_241855_4_.getY() - 2 - i;
        int i1 = p_241855_4_.getZ() - u_530_F.u_1723_Y(f1) - i;
        int j1 = 2 * (u_530_F.u_1723_Y(f1) + i);
        int k1 = 2 * (2 + i);
        for (int l1 = k; l1 <= k + j1; ++l1) {
            for (int i2 = i1; i2 <= i1 + j1; ++i2) {
                if (l > p_241855_1_.n_1700_B(z_2963_s.n_1700_B.R_4764_Y, l1, i2)) continue;
                return this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_5_, d0, d1, d2, d3, d4, d5, k, l, i1, j1, k1);
            }
        }
        return false;
    }

    protected boolean n_1700_B(LevelAccessor worldIn, Random random, OreConfiguration config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
        int i = 0;
        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int j = config.R_4764_Y;
        double[] adouble = new double[j * 4];
        for (int k = 0; k < j; ++k) {
            float f = (float)k / (float)j;
            double d0 = u_530_F.G_564_y((double)f, p_207803_4_, p_207803_6_);
            double d2 = u_530_F.G_564_y((double)f, p_207803_12_, p_207803_14_);
            double d4 = u_530_F.G_564_y((double)f, p_207803_8_, p_207803_10_);
            double d6 = random.nextDouble() * (double)j / 16.0;
            double d7 = ((double)(u_530_F.n_1700_B((float)Math.PI * f) + 1.0f) * d6 + 1.0) / 2.0;
            adouble[k * 4 + 0] = d0;
            adouble[k * 4 + 1] = d2;
            adouble[k * 4 + 2] = d4;
            adouble[k * 4 + 3] = d7;
        }
        for (int i3 = 0; i3 < j - 1; ++i3) {
            if (adouble[i3 * 4 + 3] <= 0.0) continue;
            for (int k3 = i3 + 1; k3 < j; ++k3) {
                double d14;
                double d13;
                double d12;
                double d15;
                if (adouble[k3 * 4 + 3] <= 0.0 || !((d15 = adouble[i3 * 4 + 3] - adouble[k3 * 4 + 3]) * d15 > (d12 = adouble[i3 * 4 + 0] - adouble[k3 * 4 + 0]) * d12 + (d13 = adouble[i3 * 4 + 1] - adouble[k3 * 4 + 1]) * d13 + (d14 = adouble[i3 * 4 + 2] - adouble[k3 * 4 + 2]) * d14)) continue;
                if (d15 > 0.0) {
                    adouble[k3 * 4 + 3] = -1.0;
                    continue;
                }
                adouble[i3 * 4 + 3] = -1.0;
            }
        }
        for (int j3 = 0; j3 < j; ++j3) {
            double d11 = adouble[j3 * 4 + 3];
            if (d11 < 0.0) continue;
            double d1 = adouble[j3 * 4 + 0];
            double d3 = adouble[j3 * 4 + 1];
            double d5 = adouble[j3 * 4 + 2];
            int l = Math.max(u_530_F.R_4764_Y(d1 - d11), p_207803_16_);
            int l3 = Math.max(u_530_F.R_4764_Y(d3 - d11), p_207803_17_);
            int i1 = Math.max(u_530_F.R_4764_Y(d5 - d11), p_207803_18_);
            int j1 = Math.max(u_530_F.R_4764_Y(d1 + d11), l);
            int k1 = Math.max(u_530_F.R_4764_Y(d3 + d11), l3);
            int l1 = Math.max(u_530_F.R_4764_Y(d5 + d11), i1);
            for (int i2 = l; i2 <= j1; ++i2) {
                double d8 = ((double)i2 + 0.5 - d1) / d11;
                if (!(d8 * d8 < 1.0)) continue;
                for (int j2 = l3; j2 <= k1; ++j2) {
                    double d9 = ((double)j2 + 0.5 - d3) / d11;
                    if (!(d8 * d8 + d9 * d9 < 1.0)) continue;
                    for (int k2 = i1; k2 <= l1; ++k2) {
                        int l2;
                        double d10 = ((double)k2 + 0.5 - d5) / d11;
                        if (!(d8 * d8 + d9 * d9 + d10 * d10 < 1.0) || bitset.get(l2 = i2 - p_207803_16_ + (j2 - p_207803_17_) * p_207803_19_ + (k2 - p_207803_18_) * p_207803_19_ * p_207803_20_)) continue;
                        bitset.set(l2);
                        blockpos$mutable.n_1700_B(i2, j2, k2);
                        if (!config.J_1907_R.n_1700_B(worldIn.getBlockState(blockpos$mutable), random)) continue;
                        worldIn.n_1700_B((c_1514_x)blockpos$mutable, config.G_564_y, 2);
                        ++i;
                    }
                }
            }
        }
        return i > 0;
    }
}


