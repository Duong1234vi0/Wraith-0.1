/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.SimplexNoise;
import lightning.product.u_530_F;

public final class ImprovedNoise {
    private final byte[] G_564_y;
    public final double n_1700_B;
    public final double J_1907_R;
    public final double R_4764_Y;

    public ImprovedNoise(Random rand) {
        this.n_1700_B = rand.nextDouble() * 256.0;
        this.J_1907_R = rand.nextDouble() * 256.0;
        this.R_4764_Y = rand.nextDouble() * 256.0;
        this.G_564_y = new byte[256];
        for (int i = 0; i < 256; ++i) {
            this.G_564_y[i] = (byte)i;
        }
        for (int k = 0; k < 256; ++k) {
            int j = rand.nextInt(256 - k);
            byte b0 = this.G_564_y[k];
            this.G_564_y[k] = this.G_564_y[k + j];
            this.G_564_y[k + j] = b0;
        }
    }

    public double n_1700_B(double x, double y, double z, double p_215456_7_, double p_215456_9_) {
        double d9;
        double d0 = x + this.n_1700_B;
        double d1 = y + this.J_1907_R;
        double d2 = z + this.R_4764_Y;
        int i = u_530_F.R_4764_Y(d0);
        int j = u_530_F.R_4764_Y(d1);
        int k = u_530_F.R_4764_Y(d2);
        double d3 = d0 - (double)i;
        double d4 = d1 - (double)j;
        double d5 = d2 - (double)k;
        double d6 = u_530_F.t_148_a(d3);
        double d7 = u_530_F.t_148_a(d4);
        double d8 = u_530_F.t_148_a(d5);
        if (p_215456_7_ != 0.0) {
            double d10 = Math.min(p_215456_9_, d4);
            d9 = (double)u_530_F.R_4764_Y(d10 / p_215456_7_) * p_215456_7_;
        } else {
            d9 = 0.0;
        }
        return this.n_1700_B(i, j, k, d3, d4 - d9, d5, d6, d7, d8);
    }

    private static double n_1700_B(int gradIndex, double xFactor, double yFactor, double zFactor) {
        int i = gradIndex & 0xF;
        return SimplexNoise.n_1700_B(SimplexNoise.n_1700_B[i], xFactor, yFactor, zFactor);
    }

    private int n_1700_B(int permutIndex) {
        return this.G_564_y[permutIndex & 0xFF] & 0xFF;
    }

    public double n_1700_B(int p_215459_1_, int p_215459_2_, int p_215459_3_, double p_215459_4_, double p_215459_6_, double p_215459_8_, double p_215459_10_, double p_215459_12_, double p_215459_14_) {
        int i = this.n_1700_B(p_215459_1_) + p_215459_2_;
        int j = this.n_1700_B(i) + p_215459_3_;
        int k = this.n_1700_B(i + 1) + p_215459_3_;
        int l = this.n_1700_B(p_215459_1_ + 1) + p_215459_2_;
        int i1 = this.n_1700_B(l) + p_215459_3_;
        int j1 = this.n_1700_B(l + 1) + p_215459_3_;
        double d0 = ImprovedNoise.n_1700_B(this.n_1700_B(j), p_215459_4_, p_215459_6_, p_215459_8_);
        double d1 = ImprovedNoise.n_1700_B(this.n_1700_B(i1), p_215459_4_ - 1.0, p_215459_6_, p_215459_8_);
        double d2 = ImprovedNoise.n_1700_B(this.n_1700_B(k), p_215459_4_, p_215459_6_ - 1.0, p_215459_8_);
        double d3 = ImprovedNoise.n_1700_B(this.n_1700_B(j1), p_215459_4_ - 1.0, p_215459_6_ - 1.0, p_215459_8_);
        double d4 = ImprovedNoise.n_1700_B(this.n_1700_B(j + 1), p_215459_4_, p_215459_6_, p_215459_8_ - 1.0);
        double d5 = ImprovedNoise.n_1700_B(this.n_1700_B(i1 + 1), p_215459_4_ - 1.0, p_215459_6_, p_215459_8_ - 1.0);
        double d6 = ImprovedNoise.n_1700_B(this.n_1700_B(k + 1), p_215459_4_, p_215459_6_ - 1.0, p_215459_8_ - 1.0);
        double d7 = ImprovedNoise.n_1700_B(this.n_1700_B(j1 + 1), p_215459_4_ - 1.0, p_215459_6_ - 1.0, p_215459_8_ - 1.0);
        return u_530_F.n_1700_B(p_215459_10_, p_215459_12_, p_215459_14_, d0, d1, d2, d3, d4, d5, d6, d7);
    }
}


