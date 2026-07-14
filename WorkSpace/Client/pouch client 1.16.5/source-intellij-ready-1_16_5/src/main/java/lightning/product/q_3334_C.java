/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.K_1310_v;
import lightning.product.R_2515_i;
import lightning.product.Z_1993_T;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;

public class q_3334_C
extends K_1310_v {
    protected q_3334_C(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.s_956_w, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 5 + (enchantmentLevel - 1) * 8;
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
    public boolean n_1700_B(Z_1993_T stack) {
        return stack.P_1922_E() ? true : super.n_1700_B(stack);
    }

    public static boolean n_1700_B(Z_1993_T stack, int level, Random rand) {
        if (stack.J_1907_R() instanceof R_2515_i && rand.nextFloat() < 0.6f) {
            return false;
        }
        return rand.nextInt(level + 1) > 0;
    }
}

