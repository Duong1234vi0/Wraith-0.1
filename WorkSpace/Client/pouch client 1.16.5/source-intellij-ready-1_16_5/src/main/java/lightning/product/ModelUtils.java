/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class ModelUtils {
    public static float n_1700_B(float p_228283_0_, float p_228283_1_, float p_228283_2_) {
        float f;
        for (f = p_228283_1_ - p_228283_0_; f < (float)(-Math.PI); f += (float)Math.PI * 2) {
        }
        while (f >= (float)Math.PI) {
            f -= (float)Math.PI * 2;
        }
        return p_228283_0_ + p_228283_2_ * f;
    }
}


