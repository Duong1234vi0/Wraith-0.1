/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class TridentRiptideEnchantment
extends K_1310_v {
    public TridentRiptideEnchantment(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.t_148_a, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 10 + enchantmentLevel * 7;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int n_1700_B() {
        return 3;
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        return super.n_1700_B(ench) && ench != Enchantments.Y_1740_V && ench != Enchantments.e_4240_b;
    }
}


