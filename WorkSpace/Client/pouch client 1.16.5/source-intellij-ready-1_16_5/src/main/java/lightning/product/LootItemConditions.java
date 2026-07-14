/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.B_4423_D;
import lightning.product.C_2007_r;
import lightning.product.E_2006_R;
import lightning.product.Serializer;
import lightning.product.H_1285_S;
import lightning.product.LootItemCondition;
import lightning.product.T_1611_w;
import lightning.product.V_3137_a;
import lightning.product.W_1923_h;
import lightning.product.W_2672_e;
import lightning.product.a_307_A;
import lightning.product.d_1384_D;
import lightning.product.f_3980_l;
import lightning.product.g_1926_q;
import lightning.product.g_2336_b;
import lightning.product.n_430_n;
import lightning.product.n_4800_F;
import lightning.product.o_3000_u;
import lightning.product.GsonAdapterFactory;
import lightning.product.u_3844_p;
import lightning.product.w_2512_g;
import lightning.product.y_3142_C;

public class LootItemConditions {
    public static final o_3000_u n_1700_B = LootItemConditions.n_1700_B("inverted", new W_1923_h.n_1700_B());
    public static final o_3000_u J_1907_R = LootItemConditions.n_1700_B("alternative", new f_3980_l.J_1907_R());
    public static final o_3000_u R_4764_Y = LootItemConditions.n_1700_B("random_chance", new n_430_n.n_1700_B());
    public static final o_3000_u G_564_y = LootItemConditions.n_1700_B("random_chance_with_looting", new n_4800_F.n_1700_B());
    public static final o_3000_u P_1922_E = LootItemConditions.n_1700_B("entity_properties", new W_2672_e.n_1700_B());
    public static final o_3000_u u_1723_Y = LootItemConditions.n_1700_B("killed_by_player", new u_3844_p.n_1700_B());
    public static final o_3000_u v_4262_N = LootItemConditions.n_1700_B("entity_scores", new C_2007_r.n_1700_B());
    public static final o_3000_u w_1484_f = LootItemConditions.n_1700_B("block_state_property", new w_2512_g.J_1907_R());
    public static final o_3000_u t_148_a = LootItemConditions.n_1700_B("match_tool", new y_3142_C.n_1700_B());
    public static final o_3000_u s_956_w = LootItemConditions.n_1700_B("table_bonus", new E_2006_R.n_1700_B());
    public static final o_3000_u u_2550_I = LootItemConditions.n_1700_B("survives_explosion", new g_1926_q.n_1700_B());
    public static final o_3000_u M_588_G = LootItemConditions.n_1700_B("damage_source_properties", new H_1285_S.n_1700_B());
    public static final o_3000_u P_4830_p = LootItemConditions.n_1700_B("location_check", new d_1384_D.n_1700_B());
    public static final o_3000_u h_1847_R = LootItemConditions.n_1700_B("weather_check", new a_307_A.n_1700_B());
    public static final o_3000_u Q_4569_t = LootItemConditions.n_1700_B("reference", new T_1611_w.n_1700_B());
    public static final o_3000_u M_182_A = LootItemConditions.n_1700_B("time_check", new B_4423_D.n_1700_B());

    private static o_3000_u n_1700_B(String registryName, Serializer<? extends LootItemCondition> serializer) {
        return V_3137_a.n_1700_B(V_3137_a.UploadStatus, new g_2336_b(registryName), new o_3000_u(serializer));
    }

    public static Object n_1700_B() {
        return GsonAdapterFactory.n_1700_B(V_3137_a.UploadStatus, "condition", "condition", LootItemCondition::J_1907_R).n_1700_B();
    }

    public static <T> Predicate<T> n_1700_B(Predicate<T>[] p_216305_0_) {
        switch (p_216305_0_.length) {
            case 0: {
                return p_216304_0_ -> true;
            }
            case 1: {
                return p_216305_0_[0];
            }
            case 2: {
                return p_216305_0_[0].and(p_216305_0_[1]);
            }
        }
        return p_216307_1_ -> {
            for (Predicate predicate : p_216305_0_) {
                if (predicate.test(p_216307_1_)) continue;
                return false;
            }
            return true;
        };
    }

    public static <T> Predicate<T> J_1907_R(Predicate<T>[] p_216306_0_) {
        switch (p_216306_0_.length) {
            case 0: {
                return p_216308_0_ -> false;
            }
            case 1: {
                return p_216306_0_[0];
            }
            case 2: {
                return p_216306_0_[0].or(p_216306_0_[1]);
            }
        }
        return p_216309_1_ -> {
            for (Predicate predicate : p_216306_0_) {
                if (!predicate.test(p_216309_1_)) continue;
                return true;
            }
            return false;
        };
    }
}


