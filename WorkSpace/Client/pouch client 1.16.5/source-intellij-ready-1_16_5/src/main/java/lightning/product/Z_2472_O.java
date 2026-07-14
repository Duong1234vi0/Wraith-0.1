/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class Z_2472_O
extends K_1310_v {
    protected Z_2472_O(K_1310_v.n_1700_B rarityIn, j_123_i typeIn, e_1174_E ... slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 15 + (enchantmentLevel - 1) * 9;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return super.n_1700_B(enchantmentLevel) + 50;
    }

    @Override
    public int n_1700_B() {
        return 3;
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        return super.n_1700_B(ench) && ench != Enchantments.Y_259_p;
    }
}


