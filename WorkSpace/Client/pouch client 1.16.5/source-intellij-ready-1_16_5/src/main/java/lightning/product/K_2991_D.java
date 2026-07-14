/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BiomeManager;
import lightning.product.BiomeZoomer;
import lightning.product.k_594_Q;
import lightning.product.LinearCongruentialGenerator;

public final class K_2991_D
extends Enum<K_2991_D>
implements BiomeZoomer {
    public static final /* enum */ K_2991_D n_1700_B = new K_2991_D();
    ThreadLocal<double[]> J_1907_R = ThreadLocal.withInitial(() -> new double[8]);
    private static final /* synthetic */ K_2991_D[] R_4764_Y;

    public static K_2991_D[] values() {
        return (K_2991_D[])R_4764_Y.clone();
    }

    public static K_2991_D valueOf(String name) {
        return Enum.valueOf(K_2991_D.class, name);
    }

    @Override
    public k_594_Q n_1700_B(long seed, int x, int y, int z, BiomeManager.n_1700_B biomeReader) {
        int i = x - 2;
        int j = y - 2;
        int k = z - 2;
        int l = i >> 2;
        int i1 = j >> 2;
        int j1 = k >> 2;
        double d0 = (double)(i & 3) / 4.0;
        double d1 = (double)(j & 3) / 4.0;
        double d2 = (double)(k & 3) / 4.0;
        double[] adouble = this.J_1907_R.get();
        for (int k1 = 0; k1 < 8; ++k1) {
            boolean flag = (k1 & 4) == 0;
            boolean flag1 = (k1 & 2) == 0;
            boolean flag2 = (k1 & 1) == 0;
            int l1 = flag ? l : l + 1;
            int i2 = flag1 ? i1 : i1 + 1;
            int j2 = flag2 ? j1 : j1 + 1;
            double d3 = flag ? d0 : d0 - 1.0;
            double d4 = flag1 ? d1 : d1 - 1.0;
            double d5 = flag2 ? d2 : d2 - 1.0;
            adouble[k1] = K_2991_D.n_1700_B(seed, l1, i2, j2, d3, d4, d5);
        }
        int k2 = 0;
        double d6 = adouble[0];
        for (int l2 = 1; l2 < 8; ++l2) {
            if (!(d6 > adouble[l2])) continue;
            k2 = l2;
            d6 = adouble[l2];
        }
        int i3 = (k2 & 4) == 0 ? l : l + 1;
        int j3 = (k2 & 2) == 0 ? i1 : i1 + 1;
        int k3 = (k2 & 1) == 0 ? j1 : j1 + 1;
        return biomeReader.G_564_y(i3, j3, k3);
    }

    private static double n_1700_B(long seed, int x, int y, int z, double scaleX, double scaleY, double scaleZ) {
        long i = LinearCongruentialGenerator.n_1700_B(seed, x);
        i = LinearCongruentialGenerator.n_1700_B(i, y);
        i = LinearCongruentialGenerator.n_1700_B(i, z);
        i = LinearCongruentialGenerator.n_1700_B(i, x);
        i = LinearCongruentialGenerator.n_1700_B(i, y);
        i = LinearCongruentialGenerator.n_1700_B(i, z);
        double d0 = K_2991_D.n_1700_B(i);
        i = LinearCongruentialGenerator.n_1700_B(i, seed);
        double d1 = K_2991_D.n_1700_B(i);
        i = LinearCongruentialGenerator.n_1700_B(i, seed);
        double d2 = K_2991_D.n_1700_B(i);
        return K_2991_D.n_1700_B(scaleZ + d2) + K_2991_D.n_1700_B(scaleY + d1) + K_2991_D.n_1700_B(scaleX + d0);
    }

    private static double n_1700_B(long seed) {
        double d0 = (double)((int)Math.floorMod(seed >> 24, 1024L)) / 1024.0;
        return (d0 - 0.5) * 0.9;
    }

    private static double n_1700_B(double x) {
        return x * x;
    }

    private static /* synthetic */ K_2991_D[] J_1907_R() {
        return new K_2991_D[]{n_1700_B};
    }

    static {
        R_4764_Y = K_2991_D.J_1907_R();
    }
}


