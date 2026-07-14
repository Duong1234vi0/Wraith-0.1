/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.e_1174_E;
import lightning.product.MobType;
import lightning.product.j_123_i;

public class F_653_Z
extends K_1310_v {
    public F_653_Z(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.t_148_a, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 8;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 20;
    }

    @Override
    public int n_1700_B() {
        return 5;
    }

    @Override
    public float n_1700_B(int level, MobType creatureType) {
        return creatureType == MobType.P_1922_E ? (float)level * 2.5f : 0.0f;
    }
}


