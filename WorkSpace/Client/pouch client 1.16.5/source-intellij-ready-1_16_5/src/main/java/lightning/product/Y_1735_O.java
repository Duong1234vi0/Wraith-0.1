/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class Y_1735_O
extends K_1310_v {
    public Y_1735_O(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.J_1907_R, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 15;
    }

    @Override
    public int n_1700_B() {
        return 3;
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        return super.n_1700_B(ench) && ench != Enchantments.s_956_w;
    }
}


