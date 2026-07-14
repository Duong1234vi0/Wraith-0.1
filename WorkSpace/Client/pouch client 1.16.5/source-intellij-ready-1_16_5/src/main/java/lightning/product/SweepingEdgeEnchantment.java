/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class SweepingEdgeEnchantment
extends K_1310_v {
    public SweepingEdgeEnchantment(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.u_1723_Y, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 5 + (enchantmentLevel - 1) * 9;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 15;
    }

    @Override
    public int n_1700_B() {
        return 3;
    }

    public static float P_1922_E(int level) {
        return 1.0f - 1.0f / (float)(level + 1);
    }
}


