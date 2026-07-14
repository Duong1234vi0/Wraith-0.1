/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.Z_1993_T;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;
import lightning.product.Items;

public class Y_1919_Y
extends K_1310_v {
    protected Y_1919_Y(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.v_4262_N, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 1 + 10 * (enchantmentLevel - 1);
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return super.n_1700_B(enchantmentLevel) + 50;
    }

    @Override
    public int n_1700_B() {
        return 5;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack) {
        return stack.J_1907_R() == Items.LightPredicate ? true : super.n_1700_B(stack);
    }
}


