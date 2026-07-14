/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class X_2855_O
extends K_1310_v {
    public X_2855_O(K_1310_v.n_1700_B rarity, e_1174_E ... slots) {
        super(rarity, j_123_i.P_4830_p, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int n_1700_B() {
        return 4;
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        return super.n_1700_B(ench) && ench != Enchantments.n_3318_d;
    }
}


