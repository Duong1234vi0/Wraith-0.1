/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.u_530_F;

public class SimplexNoise {
    protected static final int[][] n_1700_B = new int[][]{{1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}, {1, 1, 0}, {0, -1, 1}, {-1, 1, 0}, {0, -1, -1}};
    private static final double P_1922_E = Math.sqrt(3.0);
    private static final double u_1723_Y = 0.5 * (P_1922_E - 1.0);
    private static final double v_4262_N = (3.0 - P_1922_E) / 6.0;
    private final int[] w_1484_f = new int[512];
    public final double J_1907_R;
    public final double R_4764_Y;
    public final double G_564_y;

    public SimplexNoise(Random seed) {
        this.J_1907_R = seed.nextDouble() * 256.0;
        this.R_4764_Y = seed.nextDouble() * 256.0;
        this.G_564_y = seed.nextDouble() * 256.0;
        int i = 0;
        while (i < 256) {
            this.w_1484_f[i] = i++;
        }
        for (int l = 0; l < 256; ++l) {
            int j = seed.nextInt(256 - l);
            int k = this.w_1484_f[l];
            this.w_1484_f[l] = this.w_1484_f[j + l];
            this.w_1484_f[j + l] = k;
        }
    }

    private int n_1700_B(int permutIndex) {
        return this.w_1484_f[permutIndex & 0xFF];
    }

    protected static double n_1700_B(int[] gradElement, double xFactor, double yFactor, double zFactor) {
        return (double)gradElement[0] * xFactor + (double)gradElement[1] * yFactor + (double)gradElement[2] * zFactor;
    }

    private double n_1700_B(int gradIndex, double x, double y, double z, double offset) {
        double d0;
        double d1 = offset - x * x - y * y - z * z;
        if (d1 < 0.0) {
            d0 = 0.0;
        } else {
            d1 *= d1;
            d0 = d1 * d1 * SimplexNoise.n_1700_B(n_1700_B[gradIndex], x, y, z);
        }
        return d0;
    }

    public double n_1700_B(double x, double y) {
        int l;
        int k;
        double d3;
        double d5;
        int j;
        double d1;
        double d0 = (x + y) * u_1723_Y;
        int i = u_530_F.R_4764_Y(x + d0);
        double d2 = (double)i - (d1 = (double)(i + (j = u_530_F.R_4764_Y(y + d0))) * v_4262_N);
        double d4 = x - d2;
        if (d4 > (d5 = y - (d3 = (double)j - d1))) {
            k = 1;
            l = 0;
        } else {
            k = 0;
            l = 1;
        }
        double d6 = d4 - (double)k + v_4262_N;
        double d7 = d5 - (double)l + v_4262_N;
        double d8 = d4 - 1.0 + 2.0 * v_4262_N;
        double d9 = d5 - 1.0 + 2.0 * v_4262_N;
        int i1 = i & 0xFF;
        int j1 = j & 0xFF;
        int k1 = this.n_1700_B(i1 + this.n_1700_B(j1)) % 12;
        int l1 = this.n_1700_B(i1 + k + this.n_1700_B(j1 + l)) % 12;
        int i2 = this.n_1700_B(i1 + 1 + this.n_1700_B(j1 + 1)) % 12;
        double d10 = this.n_1700_B(k1, d4, d5, 0.0, 0.5);
        double d11 = this.n_1700_B(l1, d6, d7, 0.0, 0.5);
        double d12 = this.n_1700_B(i2, d8, d9, 0.0, 0.5);
        return 70.0 * (d10 + d11 + d12);
    }

    public double n_1700_B(double p_227464_1_, double p_227464_3_, double p_227464_5_) {
        int i2;
        int l1;
        int k1;
        int j1;
        int i1;
        int l;
        double d0 = 0.3333333333333333;
        double d1 = (p_227464_1_ + p_227464_3_ + p_227464_5_) * 0.3333333333333333;
        int i = u_530_F.R_4764_Y(p_227464_1_ + d1);
        int j = u_530_F.R_4764_Y(p_227464_3_ + d1);
        int k = u_530_F.R_4764_Y(p_227464_5_ + d1);
        double d2 = 0.16666666666666666;
        double d3 = (double)(i + j + k) * 0.16666666666666666;
        double d4 = (double)i - d3;
        double d5 = (double)j - d3;
        double d6 = (double)k - d3;
        double d7 = p_227464_1_ - d4;
        double d8 = p_227464_3_ - d5;
        double d9 = p_227464_5_ - d6;
        if (d7 >= d8) {
            if (d8 >= d9) {
                l = 1;
                i1 = 0;
                j1 = 0;
                k1 = 1;
                l1 = 1;
                i2 = 0;
            } else if (d7 >= d9) {
                l = 1;
                i1 = 0;
                j1 = 0;
                k1 = 1;
                l1 = 0;
                i2 = 1;
            } else {
                l = 0;
                i1 = 0;
                j1 = 1;
                k1 = 1;
                l1 = 0;
                i2 = 1;
            }
        } else if (d8 < d9) {
            l = 0;
            i1 = 0;
            j1 = 1;
            k1 = 0;
            l1 = 1;
            i2 = 1;
        } else if (d7 < d9) {
            l = 0;
            i1 = 1;
            j1 = 0;
            k1 = 0;
            l1 = 1;
            i2 = 1;
        } else {
            l = 0;
            i1 = 1;
            j1 = 0;
            k1 = 1;
            l1 = 1;
            i2 = 0;
        }
        double d10 = d7 - (double)l + 0.16666666666666666;
        double d11 = d8 - (double)i1 + 0.16666666666666666;
        double d12 = d9 - (double)j1 + 0.16666666666666666;
        double d13 = d7 - (double)k1 + 0.3333333333333333;
        double d14 = d8 - (double)l1 + 0.3333333333333333;
        double d15 = d9 - (double)i2 + 0.3333333333333333;
        double d16 = d7 - 1.0 + 0.5;
        double d17 = d8 - 1.0 + 0.5;
        double d18 = d9 - 1.0 + 0.5;
        int j2 = i & 0xFF;
        int k2 = j & 0xFF;
        int l2 = k & 0xFF;
        int i3 = this.n_1700_B(j2 + this.n_1700_B(k2 + this.n_1700_B(l2))) % 12;
        int j3 = this.n_1700_B(j2 + l + this.n_1700_B(k2 + i1 + this.n_1700_B(l2 + j1))) % 12;
        int k3 = this.n_1700_B(j2 + k1 + this.n_1700_B(k2 + l1 + this.n_1700_B(l2 + i2))) % 12;
        int l3 = this.n_1700_B(j2 + 1 + this.n_1700_B(k2 + 1 + this.n_1700_B(l2 + 1))) % 12;
        double d19 = this.n_1700_B(i3, d7, d8, d9, 0.6);
        double d20 = this.n_1700_B(j3, d10, d11, d12, 0.6);
        double d21 = this.n_1700_B(k3, d13, d14, d15, 0.6);
        double d22 = this.n_1700_B(l3, d16, d17, d18, 0.6);
        return 32.0 * (d19 + d20 + d21 + d22);
    }
}


