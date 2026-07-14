/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.K_4074_S;

public class NetherVines {
    public static boolean n_1700_B(K_4074_S state) {
        return state.v_4262_N();
    }

    public static int n_1700_B(Random rand) {
        double d0 = 1.0;
        int i = 0;
        while (rand.nextDouble() < d0) {
            d0 *= 0.826;
            ++i;
        }
        return i;
    }
}


