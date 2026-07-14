/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class Y_2119_j
extends K_1310_v {
    public Y_2119_j(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.P_1922_E, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 1;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 40;
    }

    @Override
    public int n_1700_B() {
        return 1;
    }
}

