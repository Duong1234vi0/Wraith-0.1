/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MinecraftAccess;

public class f_800_j
implements MinecraftAccess {
    public static float n_1700_B(float value) {
        if (c_3005_b == null || f_800_j.c_3005_b.P_4830_p == null) {
            return value;
        }
        double sensitivity = f_800_j.c_3005_b.P_4830_p.n_1700_B;
        double d = sensitivity * 0.6 + 0.2;
        double gcd = d * d * d * 8.0 * 0.15;
        return (float)((double)value - (double)value % gcd);
    }
}


