/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AxeItem;
import lightning.product.MobEffects;
import lightning.product.K_1310_v;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.e_1174_E;
import lightning.product.MobType;
import lightning.product.j_123_i;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;

public class DamageEnchantment
extends K_1310_v {
    private static final String[] G_564_y = new String[]{"all", "undead", "arthropods"};
    private static final int[] P_1922_E = new int[]{1, 5, 5};
    private static final int[] u_1723_Y = new int[]{11, 8, 8};
    private static final int[] v_4262_N = new int[]{20, 20, 20};
    public final int n_1700_B;

    public DamageEnchantment(K_1310_v.n_1700_B rarityIn, int damageTypeIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.u_1723_Y, slots);
        this.n_1700_B = damageTypeIn;
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return P_1922_E[this.n_1700_B] + (enchantmentLevel - 1) * u_1723_Y[this.n_1700_B];
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + v_4262_N[this.n_1700_B];
    }

    @Override
    public int n_1700_B() {
        return 5;
    }

    @Override
    public float n_1700_B(int level, MobType creatureType) {
        if (this.n_1700_B == 0) {
            return 1.0f + (float)Math.max(0, level - 1) * 0.5f;
        }
        if (this.n_1700_B == 1 && creatureType == MobType.J_1907_R) {
            return (float)level * 2.5f;
        }
        return this.n_1700_B == 2 && creatureType == MobType.R_4764_Y ? (float)level * 2.5f : 0.0f;
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        return !(ench instanceof DamageEnchantment);
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack) {
        return stack.J_1907_R() instanceof AxeItem ? true : super.n_1700_B(stack);
    }

    @Override
    public void n_1700_B(r_4811_B user, N_4263_v target, int level) {
        if (target instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)target;
            if (this.n_1700_B == 2 && livingentity.F_2860_q() == MobType.R_4764_Y) {
                int i = 20 + user.M_3508_C().nextInt(10 * level);
                livingentity.n_1700_B(new k_2610_C(MobEffects.J_1907_R, i, 3));
            }
        }
    }
}


