/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class MendingEnchantment
extends K_1310_v {
    public MendingEnchantment(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.s_956_w, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return enchantmentLevel * 25;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 50;
    }

    @Override
    public boolean J_1907_R() {
        return true;
    }

    @Override
    public int n_1700_B() {
        return 1;
    }
}


