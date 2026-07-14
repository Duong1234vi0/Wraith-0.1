/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class S_3807_v
extends K_1310_v {
    public S_3807_v(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.t_148_a, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 25;
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
        return super.n_1700_B(ench);
    }
}

