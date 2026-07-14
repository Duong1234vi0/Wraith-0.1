/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_3628_x;
import lightning.product.F_653_Z;
import lightning.product.MultiShotEnchantment;
import lightning.product.K_1310_v;
import lightning.product.M_1891_w;
import lightning.product.M_2559_u;
import lightning.product.ArrowFireEnchantment;
import lightning.product.R_4308_M;
import lightning.product.R_636_S;
import lightning.product.S_2994_i;
import lightning.product.MendingEnchantment;
import lightning.product.S_3807_v;
import lightning.product.T_192_m;
import lightning.product.U_28_w;
import lightning.product.TridentRiptideEnchantment;
import lightning.product.V_3137_a;
import lightning.product.V_3497_U;
import lightning.product.X_2855_O;
import lightning.product.Y_1735_O;
import lightning.product.Y_1919_Y;
import lightning.product.Y_2119_j;
import lightning.product.FrostWalkerEnchantment;
import lightning.product.Z_1878_V;
import lightning.product.Z_2472_O;
import lightning.product.enchantmentMultiShotEnchantment;
import lightning.product.SoulSpeedEnchantment;
import lightning.product.SweepingEdgeEnchantment;
import lightning.product.d_1807_Q;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;
import lightning.product.ProtectionEnchantment;
import lightning.product.DamageEnchantment;
import lightning.product.p_3945_x;
import lightning.product.q_3334_C;

public class Enchantments {
    private static final e_1174_E[] G_624_v = new e_1174_E[]{e_1174_E.u_1723_Y, e_1174_E.P_1922_E, e_1174_E.G_564_y, e_1174_E.R_4764_Y};
    public static final K_1310_v n_1700_B = Enchantments.n_1700_B("protection", new ProtectionEnchantment(K_1310_v.n_1700_B.n_1700_B, ProtectionEnchantment.n_1700_B.n_1700_B, G_624_v));
    public static final K_1310_v J_1907_R = Enchantments.n_1700_B("fire_protection", new ProtectionEnchantment(K_1310_v.n_1700_B.J_1907_R, ProtectionEnchantment.n_1700_B.J_1907_R, G_624_v));
    public static final K_1310_v R_4764_Y = Enchantments.n_1700_B("feather_falling", new ProtectionEnchantment(K_1310_v.n_1700_B.J_1907_R, ProtectionEnchantment.n_1700_B.R_4764_Y, G_624_v));
    public static final K_1310_v G_564_y = Enchantments.n_1700_B("blast_protection", new ProtectionEnchantment(K_1310_v.n_1700_B.R_4764_Y, ProtectionEnchantment.n_1700_B.G_564_y, G_624_v));
    public static final K_1310_v P_1922_E = Enchantments.n_1700_B("projectile_protection", new ProtectionEnchantment(K_1310_v.n_1700_B.J_1907_R, ProtectionEnchantment.n_1700_B.P_1922_E, G_624_v));
    public static final K_1310_v u_1723_Y = Enchantments.n_1700_B("respiration", new M_1891_w(K_1310_v.n_1700_B.R_4764_Y, G_624_v));
    public static final K_1310_v v_4262_N = Enchantments.n_1700_B("aqua_affinity", new Y_2119_j(K_1310_v.n_1700_B.R_4764_Y, G_624_v));
    public static final K_1310_v w_1484_f = Enchantments.n_1700_B("thorns", new V_3497_U(K_1310_v.n_1700_B.G_564_y, G_624_v));
    public static final K_1310_v t_148_a = Enchantments.n_1700_B("depth_strider", new Y_1735_O(K_1310_v.n_1700_B.R_4764_Y, G_624_v));
    public static final K_1310_v s_956_w = Enchantments.n_1700_B("frost_walker", new FrostWalkerEnchantment(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.R_4764_Y));
    public static final K_1310_v u_2550_I = Enchantments.n_1700_B("binding_curse", new R_4308_M(K_1310_v.n_1700_B.G_564_y, G_624_v));
    public static final K_1310_v M_588_G = Enchantments.n_1700_B("soul_speed", new SoulSpeedEnchantment(K_1310_v.n_1700_B.G_564_y, e_1174_E.R_4764_Y));
    public static final K_1310_v P_4830_p = Enchantments.n_1700_B("sharpness", new DamageEnchantment(K_1310_v.n_1700_B.n_1700_B, 0, e_1174_E.n_1700_B));
    public static final K_1310_v h_1847_R = Enchantments.n_1700_B("smite", new DamageEnchantment(K_1310_v.n_1700_B.J_1907_R, 1, e_1174_E.n_1700_B));
    public static final K_1310_v Q_4569_t = Enchantments.n_1700_B("bane_of_arthropods", new DamageEnchantment(K_1310_v.n_1700_B.J_1907_R, 2, e_1174_E.n_1700_B));
    public static final K_1310_v M_182_A = Enchantments.n_1700_B("knockback", new Z_1878_V(K_1310_v.n_1700_B.J_1907_R, e_1174_E.n_1700_B));
    public static final K_1310_v t_1786_h = Enchantments.n_1700_B("fire_aspect", new p_3945_x(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.n_1700_B));
    public static final K_1310_v multiplayerClientSuggestionProvider = Enchantments.n_1700_B("looting", new Z_2472_O(K_1310_v.n_1700_B.R_4764_Y, j_123_i.u_1723_Y, e_1174_E.n_1700_B));
    public static final K_1310_v w_1457_N = Enchantments.n_1700_B("sweeping", new SweepingEdgeEnchantment(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.n_1700_B));
    public static final K_1310_v Y_601_j = Enchantments.n_1700_B("efficiency", new Y_1919_Y(K_1310_v.n_1700_B.n_1700_B, e_1174_E.n_1700_B));
    public static final K_1310_v Y_259_p = Enchantments.n_1700_B("silk_touch", new d_1807_Q(K_1310_v.n_1700_B.G_564_y, e_1174_E.n_1700_B));
    public static final K_1310_v Q_2552_b = Enchantments.n_1700_B("unbreaking", new q_3334_C(K_1310_v.n_1700_B.J_1907_R, e_1174_E.n_1700_B));
    public static final K_1310_v C_2741_M = Enchantments.n_1700_B("fortune", new Z_2472_O(K_1310_v.n_1700_B.R_4764_Y, j_123_i.v_4262_N, e_1174_E.n_1700_B));
    public static final K_1310_v k_2293_S = Enchantments.n_1700_B("power", new R_636_S(K_1310_v.n_1700_B.n_1700_B, e_1174_E.n_1700_B));
    public static final K_1310_v q_2307_F = Enchantments.n_1700_B("punch", new T_192_m(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.n_1700_B));
    public static final K_1310_v Z_875_P = Enchantments.n_1700_B("flame", new ArrowFireEnchantment(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.n_1700_B));
    public static final K_1310_v c_3005_b = Enchantments.n_1700_B("infinity", new MultiShotEnchantment(K_1310_v.n_1700_B.G_564_y, e_1174_E.n_1700_B));
    public static final K_1310_v H_2857_Y = Enchantments.n_1700_B("luck_of_the_sea", new Z_2472_O(K_1310_v.n_1700_B.R_4764_Y, j_123_i.w_1484_f, e_1174_E.n_1700_B));
    public static final K_1310_v A_4115_X = Enchantments.n_1700_B("lure", new S_2994_i(K_1310_v.n_1700_B.R_4764_Y, j_123_i.w_1484_f, e_1174_E.n_1700_B));
    public static final K_1310_v Y_1740_V = Enchantments.n_1700_B("loyalty", new M_2559_u(K_1310_v.n_1700_B.J_1907_R, e_1174_E.n_1700_B));
    public static final K_1310_v t_4043_B = Enchantments.n_1700_B("impaling", new F_653_Z(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.n_1700_B));
    public static final K_1310_v x_607_J = Enchantments.n_1700_B("riptide", new TridentRiptideEnchantment(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.n_1700_B));
    public static final K_1310_v e_4240_b = Enchantments.n_1700_B("channeling", new S_3807_v(K_1310_v.n_1700_B.G_564_y, e_1174_E.n_1700_B));
    public static final K_1310_v n_3318_d = Enchantments.n_1700_B("multishot", new enchantmentMultiShotEnchantment(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.n_1700_B));
    public static final K_1310_v d_2427_y = Enchantments.n_1700_B("quick_charge", new E_3628_x(K_1310_v.n_1700_B.J_1907_R, e_1174_E.n_1700_B));
    public static final K_1310_v z_1737_N = Enchantments.n_1700_B("piercing", new X_2855_O(K_1310_v.n_1700_B.n_1700_B, e_1174_E.n_1700_B));
    public static final K_1310_v v_4276_D = Enchantments.n_1700_B("mending", new MendingEnchantment(K_1310_v.n_1700_B.R_4764_Y, e_1174_E.values()));
    public static final K_1310_v d_2461_k = Enchantments.n_1700_B("vanishing_curse", new U_28_w(K_1310_v.n_1700_B.G_564_y, e_1174_E.values()));

    private static K_1310_v n_1700_B(String key, K_1310_v enchantment) {
        return V_3137_a.n_1700_B(V_3137_a.z_4693_k, key, enchantment);
    }
}


