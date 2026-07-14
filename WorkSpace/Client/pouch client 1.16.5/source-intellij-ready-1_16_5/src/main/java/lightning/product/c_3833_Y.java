/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BiomeManager;
import lightning.product.K_2991_D;
import lightning.product.BiomeZoomer;
import lightning.product.k_594_Q;

public final class c_3833_Y
extends Enum<c_3833_Y>
implements BiomeZoomer {
    public static final /* enum */ c_3833_Y n_1700_B = new c_3833_Y();
    private static final /* synthetic */ c_3833_Y[] J_1907_R;

    public static c_3833_Y[] values() {
        return (c_3833_Y[])J_1907_R.clone();
    }

    public static c_3833_Y valueOf(String name) {
        return Enum.valueOf(c_3833_Y.class, name);
    }

    @Override
    public k_594_Q n_1700_B(long seed, int x, int y, int z, BiomeManager.n_1700_B biomeReader) {
        return K_2991_D.n_1700_B.n_1700_B(seed, x, 0, z, biomeReader);
    }

    private static /* synthetic */ c_3833_Y[] n_1700_B() {
        return new c_3833_Y[]{n_1700_B};
    }

    static {
        J_1907_R = c_3833_Y.n_1700_B();
    }
}


