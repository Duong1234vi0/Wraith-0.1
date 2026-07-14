/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BiFunction;
import lightning.product.A_2178_U;
import lightning.product.B_4977_Y;
import lightning.product.D_1150_F;
import lightning.product.D_1818_V;
import lightning.product.D_408_h;
import lightning.product.E_3705_H;
import lightning.product.Serializer;
import lightning.product.J_4436_F;
import lightning.product.O_215_U;
import lightning.product.T_328_T;
import lightning.product.V_3137_a;
import lightning.product.ApplyExplosionDecay;
import lightning.product.Y_1767_j;
import lightning.product.Z_1993_T;
import lightning.product.a_1277_M;
import lightning.product.b_203_K;
import lightning.product.b_4643_Y;
import lightning.product.d_1107_Z;
import lightning.product.d_150_Y;
import lightning.product.d_4035_P;
import lightning.product.g_2336_b;
import lightning.product.SmeltItemFunction;
import lightning.product.i_4685_W;
import lightning.product.i_736_r;
import lightning.product.m_4962_f;
import lightning.product.p_1840_B;
import lightning.product.q_1704_m;
import lightning.product.GsonAdapterFactory;
import lightning.product.s_2146_V;

public class LootItemFunctions {
    public static final BiFunction<Z_1993_T, q_1704_m, Z_1993_T> n_1700_B = (p_216240_0_, p_216240_1_) -> p_216240_0_;
    public static final B_4977_Y J_1907_R = LootItemFunctions.n_1700_B("set_count", new E_3705_H.n_1700_B());
    public static final B_4977_Y R_4764_Y = LootItemFunctions.n_1700_B("enchant_with_levels", new d_4035_P.J_1907_R());
    public static final B_4977_Y G_564_y = LootItemFunctions.n_1700_B("enchant_randomly", new b_4643_Y.J_1907_R());
    public static final B_4977_Y P_1922_E = LootItemFunctions.n_1700_B("set_nbt", new i_4685_W.n_1700_B());
    public static final B_4977_Y u_1723_Y = LootItemFunctions.n_1700_B("furnace_smelt", new SmeltItemFunction.n_1700_B());
    public static final B_4977_Y v_4262_N = LootItemFunctions.n_1700_B("looting_enchant", new d_1107_Z.J_1907_R());
    public static final B_4977_Y w_1484_f = LootItemFunctions.n_1700_B("set_damage", new b_203_K.n_1700_B());
    public static final B_4977_Y t_148_a = LootItemFunctions.n_1700_B("set_attributes", new Y_1767_j.J_1907_R());
    public static final B_4977_Y s_956_w = LootItemFunctions.n_1700_B("set_name", new D_408_h.n_1700_B());
    public static final B_4977_Y u_2550_I = LootItemFunctions.n_1700_B("exploration_map", new a_1277_M.J_1907_R());
    public static final B_4977_Y M_588_G = LootItemFunctions.n_1700_B("set_stew_effect", new D_1818_V.J_1907_R());
    public static final B_4977_Y P_4830_p = LootItemFunctions.n_1700_B("copy_name", new d_150_Y.n_1700_B());
    public static final B_4977_Y h_1847_R = LootItemFunctions.n_1700_B("set_contents", new J_4436_F.J_1907_R());
    public static final B_4977_Y Q_4569_t = LootItemFunctions.n_1700_B("limit_count", new O_215_U.n_1700_B());
    public static final B_4977_Y M_182_A = LootItemFunctions.n_1700_B("apply_bonus", new m_4962_f.P_1922_E());
    public static final B_4977_Y t_1786_h = LootItemFunctions.n_1700_B("set_loot_table", new D_1150_F.n_1700_B());
    public static final B_4977_Y multiplayerClientSuggestionProvider = LootItemFunctions.n_1700_B("explosion_decay", new ApplyExplosionDecay.n_1700_B());
    public static final B_4977_Y w_1457_N = LootItemFunctions.n_1700_B("set_lore", new i_736_r.n_1700_B());
    public static final B_4977_Y Y_601_j = LootItemFunctions.n_1700_B("fill_player_head", new s_2146_V.n_1700_B());
    public static final B_4977_Y Y_259_p = LootItemFunctions.n_1700_B("copy_nbt", new p_1840_B.G_564_y());
    public static final B_4977_Y Q_2552_b = LootItemFunctions.n_1700_B("copy_state", new T_328_T.J_1907_R());

    private static B_4977_Y n_1700_B(String p_237451_0_, Serializer<? extends A_2178_U> p_237451_1_) {
        return V_3137_a.n_1700_B(V_3137_a.j_276_v, new g_2336_b(p_237451_0_), new B_4977_Y(p_237451_1_));
    }

    public static Object n_1700_B() {
        return GsonAdapterFactory.n_1700_B(V_3137_a.j_276_v, "function", "function", A_2178_U::J_1907_R).n_1700_B();
    }

    public static BiFunction<Z_1993_T, q_1704_m, Z_1993_T> n_1700_B(BiFunction<Z_1993_T, q_1704_m, Z_1993_T>[] p_216241_0_) {
        switch (p_216241_0_.length) {
            case 0: {
                return n_1700_B;
            }
            case 1: {
                return p_216241_0_[0];
            }
            case 2: {
                BiFunction<Z_1993_T, q_1704_m, Z_1993_T> bifunction = p_216241_0_[0];
                BiFunction<Z_1993_T, q_1704_m, Z_1993_T> bifunction1 = p_216241_0_[1];
                return (p_216239_2_, p_216239_3_) -> (Z_1993_T)bifunction1.apply((Z_1993_T)bifunction.apply((Z_1993_T)p_216239_2_, (q_1704_m)p_216239_3_), (q_1704_m)p_216239_3_);
            }
        }
        return (p_216238_1_, p_216238_2_) -> {
            for (BiFunction bifunction2 : p_216241_0_) {
                p_216238_1_ = (Z_1993_T)bifunction2.apply(p_216238_1_, p_216238_2_);
            }
            return p_216238_1_;
        };
    }
}


