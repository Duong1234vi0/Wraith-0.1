/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.hash.Hashing
 */
package lightning.product;

import com.google.common.hash.Hashing;
import lightning.product.BiomeZoomer;
import lightning.product.c_1514_x;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.u_530_F;

public class BiomeManager {
    private final n_1700_B n_1700_B;
    private final long J_1907_R;
    private final BiomeZoomer R_4764_Y;

    public BiomeManager(n_1700_B readerIn, long seedIn, BiomeZoomer magnifierIn) {
        this.n_1700_B = readerIn;
        this.J_1907_R = seedIn;
        this.R_4764_Y = magnifierIn;
    }

    public static long n_1700_B(long seed) {
        return Hashing.sha256().hashLong(seed).asLong();
    }

    public BiomeManager n_1700_B(BiomeSource newProvider) {
        return new BiomeManager(newProvider, this.J_1907_R, this.R_4764_Y);
    }

    public k_594_Q n_1700_B(c_1514_x posIn) {
        return this.R_4764_Y.n_1700_B(this.J_1907_R, posIn.getX(), posIn.getY(), posIn.getZ(), this.n_1700_B);
    }

    public k_594_Q n_1700_B(double x, double y, double z) {
        int i = u_530_F.R_4764_Y(x) >> 2;
        int j = u_530_F.R_4764_Y(y) >> 2;
        int k = u_530_F.R_4764_Y(z) >> 2;
        return this.n_1700_B(i, j, k);
    }

    public k_594_Q J_1907_R(c_1514_x pos) {
        int i = pos.getX() >> 2;
        int j = pos.getY() >> 2;
        int k = pos.getZ() >> 2;
        return this.n_1700_B(i, j, k);
    }

    public k_594_Q n_1700_B(int x, int y, int z) {
        return this.n_1700_B.G_564_y(x, y, z);
    }

    public static interface n_1700_B {
        public k_594_Q G_564_y(int var1, int var2, int var3);
    }
}


