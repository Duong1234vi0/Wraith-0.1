/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package lightning.product;

import javax.annotation.Nonnull;
import lightning.product.e_2866_D;
import lightning.product.u_530_F;

public class CubicSampler {
    private static final double[] n_1700_B = new double[]{0.0, 1.0, 4.0, 6.0, 4.0, 1.0, 0.0};

    @Nonnull
    public static e_2866_D n_1700_B(e_2866_D p_240807_0_, n_1700_B p_240807_1_) {
        int i = u_530_F.R_4764_Y(p_240807_0_.n_1700_B());
        int j = u_530_F.R_4764_Y(p_240807_0_.J_1907_R());
        int k = u_530_F.R_4764_Y(p_240807_0_.R_4764_Y());
        double d0 = p_240807_0_.n_1700_B() - (double)i;
        double d1 = p_240807_0_.J_1907_R() - (double)j;
        double d2 = p_240807_0_.R_4764_Y() - (double)k;
        double d3 = 0.0;
        e_2866_D vector3d = e_2866_D.n_1700_B;
        for (int l = 0; l < 6; ++l) {
            double d4 = u_530_F.G_564_y(d0, n_1700_B[l + 1], n_1700_B[l]);
            int i1 = i - 2 + l;
            for (int j1 = 0; j1 < 6; ++j1) {
                double d5 = u_530_F.G_564_y(d1, n_1700_B[j1 + 1], n_1700_B[j1]);
                int k1 = j - 2 + j1;
                for (int l1 = 0; l1 < 6; ++l1) {
                    double d6 = u_530_F.G_564_y(d2, n_1700_B[l1 + 1], n_1700_B[l1]);
                    int i2 = k - 2 + l1;
                    double d7 = d4 * d5 * d6;
                    d3 += d7;
                    vector3d = vector3d.P_1922_E(p_240807_1_.fetch(i1, k1, i2).n_1700_B(d7));
                }
            }
        }
        return vector3d.n_1700_B(1.0 / d3);
    }

    public static interface n_1700_B {
        public e_2866_D fetch(int var1, int var2, int var3);
    }
}


