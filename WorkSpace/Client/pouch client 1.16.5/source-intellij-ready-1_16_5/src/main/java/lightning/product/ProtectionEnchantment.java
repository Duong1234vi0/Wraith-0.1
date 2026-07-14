/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.P_11_z;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class ProtectionEnchantment
extends K_1310_v {
    public final n_1700_B n_1700_B;

    public ProtectionEnchantment(K_1310_v.n_1700_B rarityIn, n_1700_B protectionTypeIn, e_1174_E ... slots) {
        super(rarityIn, protectionTypeIn == lightning.product.ProtectionEnchantment$n_1700_B.R_4764_Y ? j_123_i.J_1907_R : j_123_i.n_1700_B, slots);
        this.n_1700_B = protectionTypeIn;
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return this.n_1700_B.n_1700_B() + (enchantmentLevel - 1) * this.n_1700_B.J_1907_R();
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + this.n_1700_B.J_1907_R();
    }

    @Override
    public int n_1700_B() {
        return 4;
    }

    @Override
    public int n_1700_B(int level, P_11_z source) {
        if (source.w_1484_f()) {
            return 0;
        }
        if (this.n_1700_B == lightning.product.ProtectionEnchantment$n_1700_B.n_1700_B) {
            return level;
        }
        if (this.n_1700_B == lightning.product.ProtectionEnchantment$n_1700_B.J_1907_R && source.M_182_A()) {
            return level * 2;
        }
        if (this.n_1700_B == lightning.product.ProtectionEnchantment$n_1700_B.R_4764_Y && source == P_11_z.u_2550_I) {
            return level * 3;
        }
        if (this.n_1700_B == lightning.product.ProtectionEnchantment$n_1700_B.G_564_y && source.G_564_y()) {
            return level * 2;
        }
        return this.n_1700_B == lightning.product.ProtectionEnchantment$n_1700_B.P_1922_E && source.J_1907_R() ? level * 2 : 0;
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        if (ench instanceof ProtectionEnchantment) {
            ProtectionEnchantment protectionenchantment = (ProtectionEnchantment)ench;
            if (this.n_1700_B == protectionenchantment.n_1700_B) {
                return false;
            }
            return this.n_1700_B == lightning.product.ProtectionEnchantment$n_1700_B.R_4764_Y || protectionenchantment.n_1700_B == lightning.product.ProtectionEnchantment$n_1700_B.R_4764_Y;
        }
        return super.n_1700_B(ench);
    }

    public static int n_1700_B(r_4811_B livingEntity, int level) {
        int i = K_4096_w.n_1700_B(Enchantments.J_1907_R, livingEntity);
        if (i > 0) {
            level -= u_530_F.G_564_y((float)level * (float)i * 0.15f);
        }
        return level;
    }

    public static double n_1700_B(r_4811_B entityLivingBaseIn, double damage) {
        int i = K_4096_w.n_1700_B(Enchantments.G_564_y, entityLivingBaseIn);
        if (i > 0) {
            damage -= (double)u_530_F.R_4764_Y(damage * (double)((float)i * 0.15f));
        }
        return damage;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("all", 1, 11);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("fire", 10, 8);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("fall", 5, 6);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("explosion", 5, 8);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("projectile", 3, 6);
        private final String u_1723_Y;
        private final int v_4262_N;
        private final int w_1484_f;
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String typeName, int minEnchantability, int levelCost) {
            this.u_1723_Y = typeName;
            this.v_4262_N = minEnchantability;
            this.w_1484_f = levelCost;
        }

        public int n_1700_B() {
            return this.v_4262_N;
        }

        public int J_1907_R() {
            return this.w_1484_f;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            t_148_a = lightning.product.ProtectionEnchantment$n_1700_B.R_4764_Y();
        }
    }
}


