/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.u_530_F;

public final class e_4654_Y {
    private e_4654_Y() {
    }

    public static float n_1700_B(float mouseSensitivity) {
        float sens = u_530_F.n_1700_B(mouseSensitivity, 0.0f, 1.0f);
        float sensCurve = sens * 0.6f + 0.2f;
        return u_530_F.n_1700_B(sensCurve / 0.5f, 0.65f, 1.55f);
    }
}

