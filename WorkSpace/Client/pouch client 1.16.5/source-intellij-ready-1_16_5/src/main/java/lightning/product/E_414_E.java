/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Predicate;
import lightning.product.L_1875_m;
import lightning.product.Potions;
import lightning.product.V_3137_a;
import lightning.product.Y_470_x;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.y_528_b;

public class E_414_E {
    private static final List<n_1700_B<y_528_b>> n_1700_B = Lists.newArrayList();
    private static final List<n_1700_B<q_1613_l>> J_1907_R = Lists.newArrayList();
    private static final List<b_3278_X> R_4764_Y = Lists.newArrayList();
    private static final Predicate<Z_1993_T> G_564_y = p_210319_0_ -> {
        for (b_3278_X ingredient : R_4764_Y) {
            if (!ingredient.n_1700_B((Z_1993_T)p_210319_0_)) continue;
            return true;
        }
        return false;
    };

    public static boolean n_1700_B(Z_1993_T stack) {
        return E_414_E.J_1907_R(stack) || E_414_E.R_4764_Y(stack);
    }

    protected static boolean J_1907_R(Z_1993_T stack) {
        int j = J_1907_R.size();
        for (int i = 0; i < j; ++i) {
            if (!E_414_E.J_1907_R.get((int)i).J_1907_R.n_1700_B(stack)) continue;
            return true;
        }
        return false;
    }

    protected static boolean R_4764_Y(Z_1993_T stack) {
        int j = n_1700_B.size();
        for (int i = 0; i < j; ++i) {
            if (!E_414_E.n_1700_B.get((int)i).J_1907_R.n_1700_B(stack)) continue;
            return true;
        }
        return false;
    }

    public static boolean n_1700_B(y_528_b potion) {
        int j = n_1700_B.size();
        for (int i = 0; i < j; ++i) {
            if (E_414_E.n_1700_B.get((int)i).R_4764_Y != potion) continue;
            return true;
        }
        return false;
    }

    public static boolean n_1700_B(Z_1993_T input, Z_1993_T reagent) {
        if (!G_564_y.test(input)) {
            return false;
        }
        return E_414_E.J_1907_R(input, reagent) || E_414_E.R_4764_Y(input, reagent);
    }

    protected static boolean J_1907_R(Z_1993_T input, Z_1993_T reagent) {
        q_1613_l item = input.J_1907_R();
        int j = J_1907_R.size();
        for (int i = 0; i < j; ++i) {
            n_1700_B<q_1613_l> mixpredicate = J_1907_R.get(i);
            if (mixpredicate.n_1700_B != item || !mixpredicate.J_1907_R.n_1700_B(reagent)) continue;
            return true;
        }
        return false;
    }

    protected static boolean R_4764_Y(Z_1993_T input, Z_1993_T reagent) {
        y_528_b potion = L_1875_m.G_564_y(input);
        int j = n_1700_B.size();
        for (int i = 0; i < j; ++i) {
            n_1700_B<y_528_b> mixpredicate = n_1700_B.get(i);
            if (mixpredicate.n_1700_B != potion || !mixpredicate.J_1907_R.n_1700_B(reagent)) continue;
            return true;
        }
        return false;
    }

    public static Z_1993_T G_564_y(Z_1993_T reagent, Z_1993_T potionIn) {
        if (!potionIn.n_1700_B()) {
            int i;
            y_528_b potion = L_1875_m.G_564_y(potionIn);
            q_1613_l item = potionIn.J_1907_R();
            int j = J_1907_R.size();
            for (i = 0; i < j; ++i) {
                n_1700_B<q_1613_l> mixpredicate = J_1907_R.get(i);
                if (mixpredicate.n_1700_B != item || !mixpredicate.J_1907_R.n_1700_B(reagent)) continue;
                return L_1875_m.n_1700_B(new Z_1993_T((q_1803_e)mixpredicate.R_4764_Y), potion);
            }
            int k = n_1700_B.size();
            for (i = 0; i < k; ++i) {
                n_1700_B<y_528_b> mixpredicate1 = n_1700_B.get(i);
                if (mixpredicate1.n_1700_B != potion || !mixpredicate1.J_1907_R.n_1700_B(reagent)) continue;
                return L_1875_m.n_1700_B(new Z_1993_T(item), (y_528_b)mixpredicate1.R_4764_Y);
            }
        }
        return potionIn;
    }

    public static void n_1700_B() {
        E_414_E.n_1700_B(Items.j_2461_G);
        E_414_E.n_1700_B(Items.g_2492_v);
        E_414_E.n_1700_B(Items.NetherrackBlock);
        E_414_E.n_1700_B(Items.j_2461_G, Items.Easing, Items.g_2492_v);
        E_414_E.n_1700_B(Items.g_2492_v, Items.O_3671_t, Items.NetherrackBlock);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.e_1503_j, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.b_3334_n, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.GlazedTerracottaBlock, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.C_3528_u, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.r_2687_x, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.o_3456_E, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.S_3844_E, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.AdvancementList, Potions.G_564_y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.v_570_f, Potions.R_4764_Y);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.g_1096_r, Potions.P_1922_E);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.DoublePlantBlock, Potions.u_1723_Y);
        E_414_E.n_1700_B(Potions.u_1723_Y, Items.v_570_f, Potions.v_4262_N);
        E_414_E.n_1700_B(Potions.u_1723_Y, Items.Y_3066_B, Potions.w_1484_f);
        E_414_E.n_1700_B(Potions.v_4262_N, Items.Y_3066_B, Potions.t_148_a);
        E_414_E.n_1700_B(Potions.w_1484_f, Items.v_570_f, Potions.t_148_a);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.S_3844_E, Potions.P_4830_p);
        E_414_E.n_1700_B(Potions.P_4830_p, Items.v_570_f, Potions.h_1847_R);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.GlazedTerracottaBlock, Potions.s_956_w);
        E_414_E.n_1700_B(Potions.s_956_w, Items.v_570_f, Potions.u_2550_I);
        E_414_E.n_1700_B(Potions.s_956_w, Items.AdvancementList, Potions.M_588_G);
        E_414_E.n_1700_B(Potions.s_956_w, Items.Y_3066_B, Potions.multiplayerClientSuggestionProvider);
        E_414_E.n_1700_B(Potions.u_2550_I, Items.Y_3066_B, Potions.w_1457_N);
        E_414_E.n_1700_B(Potions.multiplayerClientSuggestionProvider, Items.v_570_f, Potions.w_1457_N);
        E_414_E.n_1700_B(Potions.multiplayerClientSuggestionProvider, Items.AdvancementList, Potions.Y_601_j);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.S_315_z, Potions.Y_259_p);
        E_414_E.n_1700_B(Potions.Y_259_p, Items.v_570_f, Potions.Q_2552_b);
        E_414_E.n_1700_B(Potions.Y_259_p, Items.AdvancementList, Potions.C_2741_M);
        E_414_E.n_1700_B(Potions.Q_4569_t, Items.Y_3066_B, Potions.multiplayerClientSuggestionProvider);
        E_414_E.n_1700_B(Potions.M_182_A, Items.Y_3066_B, Potions.w_1457_N);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.o_3456_E, Potions.Q_4569_t);
        E_414_E.n_1700_B(Potions.Q_4569_t, Items.v_570_f, Potions.M_182_A);
        E_414_E.n_1700_B(Potions.Q_4569_t, Items.AdvancementList, Potions.t_1786_h);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.U_1258_d, Potions.k_2293_S);
        E_414_E.n_1700_B(Potions.k_2293_S, Items.v_570_f, Potions.q_2307_F);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.e_1503_j, Potions.Z_875_P);
        E_414_E.n_1700_B(Potions.Z_875_P, Items.AdvancementList, Potions.c_3005_b);
        E_414_E.n_1700_B(Potions.Z_875_P, Items.Y_3066_B, Potions.H_2857_Y);
        E_414_E.n_1700_B(Potions.c_3005_b, Items.Y_3066_B, Potions.A_4115_X);
        E_414_E.n_1700_B(Potions.H_2857_Y, Items.AdvancementList, Potions.A_4115_X);
        E_414_E.n_1700_B(Potions.Y_1740_V, Items.Y_3066_B, Potions.H_2857_Y);
        E_414_E.n_1700_B(Potions.t_4043_B, Items.Y_3066_B, Potions.H_2857_Y);
        E_414_E.n_1700_B(Potions.x_607_J, Items.Y_3066_B, Potions.A_4115_X);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.r_2687_x, Potions.Y_1740_V);
        E_414_E.n_1700_B(Potions.Y_1740_V, Items.v_570_f, Potions.t_4043_B);
        E_414_E.n_1700_B(Potions.Y_1740_V, Items.AdvancementList, Potions.x_607_J);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.b_3334_n, Potions.e_4240_b);
        E_414_E.n_1700_B(Potions.e_4240_b, Items.v_570_f, Potions.n_3318_d);
        E_414_E.n_1700_B(Potions.e_4240_b, Items.AdvancementList, Potions.d_2427_y);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.C_3528_u, Potions.z_1737_N);
        E_414_E.n_1700_B(Potions.z_1737_N, Items.v_570_f, Potions.v_4276_D);
        E_414_E.n_1700_B(Potions.z_1737_N, Items.AdvancementList, Potions.d_2461_k);
        E_414_E.n_1700_B(Potions.J_1907_R, Items.Y_3066_B, Potions.G_624_v);
        E_414_E.n_1700_B(Potions.G_624_v, Items.v_570_f, Potions.T_2506_i);
        E_414_E.n_1700_B(Potions.P_1922_E, Items.RotatedPillarBlock, Potions.z_4693_k);
        E_414_E.n_1700_B(Potions.z_4693_k, Items.v_570_f, Potions.g_221_o);
    }

    private static void n_1700_B(q_1613_l p_196207_0_, q_1613_l p_196207_1_, q_1613_l p_196207_2_) {
        if (!(p_196207_0_ instanceof Y_470_x)) {
            throw new IllegalArgumentException("Expected a potion, got: " + String.valueOf(V_3137_a.e_2887_G.J_1907_R(p_196207_0_)));
        }
        if (!(p_196207_2_ instanceof Y_470_x)) {
            throw new IllegalArgumentException("Expected a potion, got: " + String.valueOf(V_3137_a.e_2887_G.J_1907_R(p_196207_2_)));
        }
        J_1907_R.add(new n_1700_B<q_1613_l>(p_196207_0_, b_3278_X.n_1700_B(p_196207_1_), p_196207_2_));
    }

    private static void n_1700_B(q_1613_l p_196208_0_) {
        if (!(p_196208_0_ instanceof Y_470_x)) {
            throw new IllegalArgumentException("Expected a potion, got: " + String.valueOf(V_3137_a.e_2887_G.J_1907_R(p_196208_0_)));
        }
        R_4764_Y.add(b_3278_X.n_1700_B(p_196208_0_));
    }

    private static void n_1700_B(y_528_b potionEntry, q_1613_l potionIngredient, y_528_b potionResult) {
        n_1700_B.add(new n_1700_B<y_528_b>(potionEntry, b_3278_X.n_1700_B(potionIngredient), potionResult));
    }

    static class n_1700_B<T> {
        private final T n_1700_B;
        private final b_3278_X J_1907_R;
        private final T R_4764_Y;

        public n_1700_B(T inputIn, b_3278_X reagentIn, T outputIn) {
            this.n_1700_B = inputIn;
            this.J_1907_R = reagentIn;
            this.R_4764_Y = outputIn;
        }
    }
}


