/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4514_U;

public class h_3066_J
implements A_4514_U {
    public static float n_1700_B(float angle) {
        return angle / h_3066_J.n_1700_B() * h_3066_J.n_1700_B();
    }

    public static float J_1907_R(float angle) {
        return (float)Math.round(angle / h_3066_J.n_1700_B()) * h_3066_J.n_1700_B();
    }

    public static float n_1700_B() {
        return h_3066_J.J_1907_R() * 0.15f;
    }

    public static float J_1907_R() {
        double sensitivity = h_3066_J.n_1700_B.P_4830_p.n_1700_B;
        double value = sensitivity * 0.6 + 0.2;
        double result = Math.pow(value, 3.0) * 0.78;
        return (float)result;
    }
}

