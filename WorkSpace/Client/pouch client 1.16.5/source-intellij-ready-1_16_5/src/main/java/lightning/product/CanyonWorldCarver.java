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
import java.util.function.Function;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.i_4544_r;
import lightning.product.k_594_Q;
import lightning.product.u_530_F;

public class CanyonWorldCarver
extends i_4544_r<ProbabilityFeatureConfiguration> {
    private final float[] P_4830_p = new float[1024];

    public CanyonWorldCarver(Codec<ProbabilityFeatureConfiguration> p_i231916_1_) {
        super(p_i231916_1_, 256);
    }

    @Override
    public boolean n_1700_B(Random rand, int chunkX, int chunkZ, ProbabilityFeatureConfiguration config) {
        return rand.nextFloat() <= config.J_1907_R;
    }

    @Override
    public boolean n_1700_B(ChunkAccess chunk, Function<c_1514_x, k_594_Q> biomePos, Random rand, int seaLevel, int chunkXOffset, int chunkZOffset, int chunkX, int chunkZ, BitSet carvingMask, ProbabilityFeatureConfiguration config) {
        int i = (this.G_564_y() * 2 - 1) * 16;
        double d0 = chunkXOffset * 16 + rand.nextInt(16);
        double d1 = rand.nextInt(rand.nextInt(40) + 8) + 20;
        double d2 = chunkZOffset * 16 + rand.nextInt(16);
        float f = rand.nextFloat() * ((float)Math.PI * 2);
        float f1 = (rand.nextFloat() - 0.5f) * 2.0f / 8.0f;
        double d3 = 3.0;
        float f2 = (rand.nextFloat() * 2.0f + rand.nextFloat()) * 2.0f;
        int j = i - rand.nextInt(i / 4);
        boolean k = false;
        this.n_1700_B(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f2, f, f1, 0, j, 3.0, carvingMask);
        return true;
    }

    private void n_1700_B(ChunkAccess p_227204_1_, Function<c_1514_x, k_594_Q> p_227204_2_, long p_227204_3_, int p_227204_5_, int p_227204_6_, int p_227204_7_, double p_227204_8_, double p_227204_10_, double p_227204_12_, float p_227204_14_, float p_227204_15_, float p_227204_16_, int p_227204_17_, int p_227204_18_, double p_227204_19_, BitSet p_227204_21_) {
        Random random = new Random(p_227204_3_);
        float f = 1.0f;
        for (int i = 0; i < 256; ++i) {
            if (i == 0 || random.nextInt(3) == 0) {
                f = 1.0f + random.nextFloat() * random.nextFloat();
            }
            this.P_4830_p[i] = f * f;
        }
        float f4 = 0.0f;
        float f1 = 0.0f;
        for (int j = p_227204_17_; j < p_227204_18_; ++j) {
            double d0 = 1.5 + (double)(u_530_F.n_1700_B((float)j * (float)Math.PI / (float)p_227204_18_) * p_227204_14_);
            double d1 = d0 * p_227204_19_;
            d0 *= (double)random.nextFloat() * 0.25 + 0.75;
            d1 *= (double)random.nextFloat() * 0.25 + 0.75;
            float f2 = u_530_F.J_1907_R(p_227204_16_);
            float f3 = u_530_F.n_1700_B(p_227204_16_);
            p_227204_8_ += (double)(u_530_F.J_1907_R(p_227204_15_) * f2);
            p_227204_10_ += (double)f3;
            p_227204_12_ += (double)(u_530_F.n_1700_B(p_227204_15_) * f2);
            p_227204_16_ *= 0.7f;
            p_227204_16_ += f1 * 0.05f;
            p_227204_15_ += f4 * 0.05f;
            f1 *= 0.8f;
            f4 *= 0.5f;
            f1 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;
            if (random.nextInt(4) == 0) continue;
            if (!this.n_1700_B(p_227204_6_, p_227204_7_, p_227204_8_, p_227204_12_, j, p_227204_18_, p_227204_14_)) {
                return;
            }
            this.n_1700_B(p_227204_1_, p_227204_2_, p_227204_3_, p_227204_5_, p_227204_6_, p_227204_7_, p_227204_8_, p_227204_10_, p_227204_12_, d0, d1, p_227204_21_);
        }
    }

    @Override
    protected boolean n_1700_B(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return (p_222708_1_ * p_222708_1_ + p_222708_5_ * p_222708_5_) * (double)this.P_4830_p[p_222708_7_ - 1] + p_222708_3_ * p_222708_3_ / 6.0 >= 1.0;
    }
}


