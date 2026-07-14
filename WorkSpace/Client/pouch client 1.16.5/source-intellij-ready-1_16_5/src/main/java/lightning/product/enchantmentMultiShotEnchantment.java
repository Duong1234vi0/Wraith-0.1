/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class enchantmentMultiShotEnchantment
extends K_1310_v {
    public enchantmentMultiShotEnchantment(K_1310_v.n_1700_B rarity, e_1174_E ... slots) {
        super(rarity, j_123_i.P_4830_p, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 20;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int n_1700_B() {
        return 1;
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        return super.n_1700_B(ench) && ench != Enchantments.z_1737_N;
    }
}


