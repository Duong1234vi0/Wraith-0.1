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

public class CaveWorldCarver
extends i_4544_r<ProbabilityFeatureConfiguration> {
    public CaveWorldCarver(Codec<ProbabilityFeatureConfiguration> p_i231917_1_, int p_i231917_2_) {
        super(p_i231917_1_, p_i231917_2_);
    }

    @Override
    public boolean n_1700_B(Random rand, int chunkX, int chunkZ, ProbabilityFeatureConfiguration config) {
        return rand.nextFloat() <= config.J_1907_R;
    }

    @Override
    public boolean n_1700_B(ChunkAccess chunk, Function<c_1514_x, k_594_Q> biomePos, Random rand, int seaLevel, int chunkXOffset, int chunkZOffset, int chunkX, int chunkZ, BitSet carvingMask, ProbabilityFeatureConfiguration config) {
        int i = (this.G_564_y() * 2 - 1) * 16;
        int j = rand.nextInt(rand.nextInt(rand.nextInt(this.n_1700_B()) + 1) + 1);
        for (int k = 0; k < j; ++k) {
            double d0 = chunkXOffset * 16 + rand.nextInt(16);
            double d1 = this.J_1907_R(rand);
            double d2 = chunkZOffset * 16 + rand.nextInt(16);
            int l = 1;
            if (rand.nextInt(4) == 0) {
                double d3 = 0.5;
                float f1 = 1.0f + rand.nextFloat() * 6.0f;
                this.n_1700_B(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f1, 0.5, carvingMask);
                l += rand.nextInt(4);
            }
            for (int k1 = 0; k1 < l; ++k1) {
                float f = rand.nextFloat() * ((float)Math.PI * 2);
                float f3 = (rand.nextFloat() - 0.5f) / 4.0f;
                float f2 = this.n_1700_B(rand);
                int i1 = i - rand.nextInt(i / 4);
                boolean j1 = false;
                this.n_1700_B(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f2, f, f3, 0, i1, this.J_1907_R(), carvingMask);
            }
        }
        return true;
    }

    protected int n_1700_B() {
        return 15;
    }

    protected float n_1700_B(Random p_230359_1_) {
        float f = p_230359_1_.nextFloat() * 2.0f + p_230359_1_.nextFloat();
        if (p_230359_1_.nextInt(10) == 0) {
            f *= p_230359_1_.nextFloat() * p_230359_1_.nextFloat() * 3.0f + 1.0f;
        }
        return f;
    }

    protected double J_1907_R() {
        return 1.0;
    }

    protected int J_1907_R(Random p_230361_1_) {
        return p_230361_1_.nextInt(p_230361_1_.nextInt(120) + 8);
    }

    protected void n_1700_B(ChunkAccess p_227205_1_, Function<c_1514_x, k_594_Q> p_227205_2_, long p_227205_3_, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float p_227205_14_, double p_227205_15_, BitSet carvingMask) {
        double d0 = 1.5 + (double)(u_530_F.n_1700_B(1.5707964f) * p_227205_14_);
        double d1 = d0 * p_227205_15_;
        this.n_1700_B(p_227205_1_, p_227205_2_, p_227205_3_, seaLevel, chunkX, chunkZ, randOffsetXCoord + 1.0, startY, randOffsetZCoord, d0, d1, carvingMask);
    }

    protected void n_1700_B(ChunkAccess chunk, Function<c_1514_x, k_594_Q> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float caveRadius, float pitch, float p_227206_16_, int p_227206_17_, int p_227206_18_, double p_227206_19_, BitSet p_227206_21_) {
        Random random = new Random(seed);
        int i = random.nextInt(p_227206_18_ / 2) + p_227206_18_ / 4;
        boolean flag = random.nextInt(6) == 0;
        float f = 0.0f;
        float f1 = 0.0f;
        for (int j = p_227206_17_; j < p_227206_18_; ++j) {
            double d0 = 1.5 + (double)(u_530_F.n_1700_B((float)Math.PI * (float)j / (float)p_227206_18_) * caveRadius);
            double d1 = d0 * p_227206_19_;
            float f2 = u_530_F.J_1907_R(p_227206_16_);
            randOffsetXCoord += (double)(u_530_F.J_1907_R(pitch) * f2);
            startY += (double)u_530_F.n_1700_B(p_227206_16_);
            randOffsetZCoord += (double)(u_530_F.n_1700_B(pitch) * f2);
            p_227206_16_ *= flag ? 0.92f : 0.7f;
            p_227206_16_ += f1 * 0.1f;
            pitch += f * 0.1f;
            f1 *= 0.9f;
            f *= 0.75f;
            f1 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            f += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;
            if (j == i && caveRadius > 1.0f) {
                this.n_1700_B(chunk, biomePos, random.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, random.nextFloat() * 0.5f + 0.5f, pitch - 1.5707964f, p_227206_16_ / 3.0f, j, p_227206_18_, 1.0, p_227206_21_);
                this.n_1700_B(chunk, biomePos, random.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, random.nextFloat() * 0.5f + 0.5f, pitch + 1.5707964f, p_227206_16_ / 3.0f, j, p_227206_18_, 1.0, p_227206_21_);
                return;
            }
            if (random.nextInt(4) == 0) continue;
            if (!this.n_1700_B(chunkX, chunkZ, randOffsetXCoord, randOffsetZCoord, j, p_227206_18_, caveRadius)) {
                return;
            }
            this.n_1700_B(chunk, biomePos, seed, seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, d0, d1, p_227206_21_);
        }
    }

    @Override
    protected boolean n_1700_B(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return p_222708_3_ <= -0.7 || p_222708_1_ * p_222708_1_ + p_222708_3_ * p_222708_3_ + p_222708_5_ * p_222708_5_ >= 1.0;
    }
}


