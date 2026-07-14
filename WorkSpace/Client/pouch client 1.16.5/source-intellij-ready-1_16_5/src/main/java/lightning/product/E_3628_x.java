/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class E_3628_x
extends K_1310_v {
    public E_3628_x(K_1310_v.n_1700_B rarityIn, e_1174_E ... slotTypes) {
        super(rarityIn, j_123_i.P_4830_p, slotTypes);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 12 + (enchantmentLevel - 1) * 20;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int n_1700_B() {
        return 3;
    }
}

