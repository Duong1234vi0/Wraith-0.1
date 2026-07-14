/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class Z_1878_V
extends K_1310_v {
    protected Z_1878_V(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.u_1723_Y, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 5 + 20 * (enchantmentLevel - 1);
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return super.n_1700_B(enchantmentLevel) + 50;
    }

    @Override
    public int n_1700_B() {
        return 2;
    }
}

