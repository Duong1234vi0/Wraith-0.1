/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BiomeManager;
import lightning.product.BiomeZoomer;
import lightning.product.k_594_Q;

public final class D_4011_s
extends Enum<D_4011_s>
implements BiomeZoomer {
    public static final /* enum */ D_4011_s n_1700_B = new D_4011_s();
    private static final /* synthetic */ D_4011_s[] J_1907_R;

    public static D_4011_s[] values() {
        return (D_4011_s[])J_1907_R.clone();
    }

    public static D_4011_s valueOf(String name) {
        return Enum.valueOf(D_4011_s.class, name);
    }

    @Override
    public k_594_Q n_1700_B(long seed, int x, int y, int z, BiomeManager.n_1700_B biomeReader) {
        return biomeReader.G_564_y(x >> 2, y >> 2, z >> 2);
    }

    private static /* synthetic */ D_4011_s[] n_1700_B() {
        return new D_4011_s[]{n_1700_B};
    }

    static {
        J_1907_R = D_4011_s.n_1700_B();
    }
}


