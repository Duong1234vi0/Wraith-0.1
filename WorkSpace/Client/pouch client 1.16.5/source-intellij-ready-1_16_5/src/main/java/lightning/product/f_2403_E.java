/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.Q_2753_H;
import lightning.product.Y_1740_V;
import lightning.product.ClientboundSetTimePacket;
import lightning.product.u_530_F;
import lombok.Generated;

public class f_2403_E {
    private static float J_1907_R = 0.0f;
    long n_1700_B;
    private static float R_4764_Y = 20.0f;

    @Y_1740_V
    private void n_1700_B(Q_2753_H e) {
        if (e.G_564_y() instanceof ClientboundSetTimePacket) {
            this.R_4764_Y();
        }
    }

    private void R_4764_Y() {
        long delay = System.nanoTime() - this.n_1700_B;
        float maxTPS = 20.0f;
        float rawTPS = maxTPS * (1.0E9f / (float)delay);
        float boundedTPS = u_530_F.n_1700_B(rawTPS, 0.0f, maxTPS);
        R_4764_Y = (float)Math.round(boundedTPS * 2.0f) / 2.0f;
        J_1907_R = boundedTPS - maxTPS;
        this.n_1700_B = System.nanoTime();
    }

    @Generated
    public static float n_1700_B() {
        return J_1907_R;
    }

    @Generated
    public static float J_1907_R() {
        return R_4764_Y;
    }
}


