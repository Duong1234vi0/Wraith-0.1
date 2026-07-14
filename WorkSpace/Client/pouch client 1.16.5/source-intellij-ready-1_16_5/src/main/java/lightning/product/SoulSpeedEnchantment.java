/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class SoulSpeedEnchantment
extends K_1310_v {
    public SoulSpeedEnchantment(K_1310_v.n_1700_B rarity, e_1174_E ... slots) {
        super(rarity, j_123_i.J_1907_R, slots);
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
    public boolean J_1907_R() {
        return true;
    }

    @Override
    public boolean w_1484_f() {
        return false;
    }

    @Override
    public boolean t_148_a() {
        return false;
    }

    @Override
    public int n_1700_B() {
        return 3;
    }
}


