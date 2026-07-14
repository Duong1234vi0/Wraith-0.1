/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class T_192_m
extends K_1310_v {
    public T_192_m(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.u_2550_I, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 12 + (enchantmentLevel - 1) * 20;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 25;
    }

    @Override
    public int n_1700_B() {
        return 2;
    }
}

