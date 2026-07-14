/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import java.util.Random;
import lightning.product.A_4115_X;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2515_i;
import lightning.product.U_4087_m;
import lightning.product.Z_1993_T;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;
import lightning.product.r_4811_B;

public class V_3497_U
extends K_1310_v {
    public V_3497_U(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.G_564_y, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return 10 + 20 * (enchantmentLevel - 1);
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
        return stack.J_1907_R() instanceof R_2515_i ? true : super.n_1700_B(stack);
    }

    @Override
    public void J_1907_R(r_4811_B user, N_4263_v attacker, int level) {
        Random random = user.M_3508_C();
        Map.Entry<e_1174_E, Z_1993_T> entry = K_4096_w.J_1907_R(Enchantments.w_1484_f, user);
        if (V_3497_U.n_1700_B(level, random)) {
            float damage = V_3497_U.J_1907_R(level, random);
            U_4087_m eventThorns = new U_4087_m(user, attacker, level, damage);
            A_4115_X.n_1700_B(eventThorns);
            if (eventThorns.n_1700_B()) {
                return;
            }
            damage = eventThorns.P_1922_E();
            if (attacker != null) {
                attacker.n_1700_B(P_11_z.n_1700_B(user), damage);
            }
            if (entry != null) {
                entry.getValue().n_1700_B(2, user, livingEntity -> livingEntity.R_4764_Y((e_1174_E)((Object)((Object)entry.getKey()))));
            }
        }
    }

    public static boolean n_1700_B(int level, Random rnd) {
        if (level <= 0) {
            return false;
        }
        return rnd.nextFloat() < 0.15f * (float)level;
    }

    public static int J_1907_R(int level, Random rnd) {
        return level > 10 ? level - 10 : 1 + rnd.nextInt(4);
    }
}


