/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.J_2538_C;
import lightning.product.L_3848_p;
import lightning.product.T_2910_P;
import lightning.product.WoodType;
import lightning.product.e_933_M;
import lightning.product.f_395_A;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.o_2576_A;
import lightning.product.p_1429_o;
import lightning.product.s_3081_t;

public class b_4440_Q {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/atlas/shulker_boxes.png");
    public static final g_2336_b J_1907_R = new g_2336_b("textures/atlas/beds.png");
    public static final g_2336_b R_4764_Y = new g_2336_b("textures/atlas/banner_patterns.png");
    public static final g_2336_b G_564_y = new g_2336_b("textures/atlas/shield_patterns.png");
    public static final g_2336_b P_1922_E = new g_2336_b("textures/atlas/signs.png");
    public static final g_2336_b u_1723_Y = new g_2336_b("textures/atlas/chest.png");
    private static final o_2576_A Y_259_p = o_2576_A.G_564_y(n_1700_B);
    private static final o_2576_A Q_2552_b = o_2576_A.J_1907_R(J_1907_R);
    private static final o_2576_A C_2741_M = o_2576_A.u_2550_I(R_4764_Y);
    private static final o_2576_A k_2293_S = o_2576_A.u_2550_I(G_564_y);
    private static final o_2576_A q_2307_F = o_2576_A.G_564_y(P_1922_E);
    private static final o_2576_A Z_875_P = o_2576_A.R_4764_Y(u_1723_Y);
    private static final o_2576_A c_3005_b = o_2576_A.J_1907_R(L_3848_p.n_1700_B);
    private static final o_2576_A H_2857_Y = o_2576_A.R_4764_Y(L_3848_p.n_1700_B);
    private static final o_2576_A A_4115_X = o_2576_A.u_1723_Y(L_3848_p.n_1700_B);
    private static final o_2576_A Y_1740_V = o_2576_A.v_4262_N(L_3848_p.n_1700_B);
    public static final T_2910_P v_4262_N = new T_2910_P(n_1700_B, new g_2336_b("entity/shulker/shulker"));
    public static final List<T_2910_P> w_1484_f = (List)Stream.of("white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black").map(shulkerColor -> new T_2910_P(n_1700_B, new g_2336_b("entity/shulker/shulker_" + shulkerColor))).collect(ImmutableList.toImmutableList());
    public static final Map<WoodType, T_2910_P> t_148_a = WoodType.n_1700_B().collect(Collectors.toMap(Function.identity(), b_4440_Q::n_1700_B));
    public static final T_2910_P[] s_956_w = (T_2910_P[])Arrays.stream(e_933_M.values()).sorted(Comparator.comparingInt(e_933_M::J_1907_R)).map(color -> new T_2910_P(J_1907_R, new g_2336_b("entity/bed/" + color.R_4764_Y()))).toArray(T_2910_P[]::new);
    public static final T_2910_P u_2550_I = b_4440_Q.n_1700_B("trapped");
    public static final T_2910_P M_588_G = b_4440_Q.n_1700_B("trapped_left");
    public static final T_2910_P P_4830_p = b_4440_Q.n_1700_B("trapped_right");
    public static final T_2910_P h_1847_R = b_4440_Q.n_1700_B("christmas");
    public static final T_2910_P Q_4569_t = b_4440_Q.n_1700_B("christmas_left");
    public static final T_2910_P M_182_A = b_4440_Q.n_1700_B("christmas_right");
    public static final T_2910_P t_1786_h = b_4440_Q.n_1700_B("normal");
    public static final T_2910_P multiplayerClientSuggestionProvider = b_4440_Q.n_1700_B("normal_left");
    public static final T_2910_P w_1457_N = b_4440_Q.n_1700_B("normal_right");
    public static final T_2910_P Y_601_j = b_4440_Q.n_1700_B("ender");

    public static o_2576_A n_1700_B() {
        return C_2741_M;
    }

    public static o_2576_A J_1907_R() {
        return k_2293_S;
    }

    public static o_2576_A R_4764_Y() {
        return Q_2552_b;
    }

    public static o_2576_A G_564_y() {
        return Y_259_p;
    }

    public static o_2576_A P_1922_E() {
        return q_2307_F;
    }

    public static o_2576_A u_1723_Y() {
        return Z_875_P;
    }

    public static o_2576_A v_4262_N() {
        return c_3005_b;
    }

    public static o_2576_A w_1484_f() {
        return H_2857_Y;
    }

    public static o_2576_A t_148_a() {
        return A_4115_X;
    }

    public static o_2576_A s_956_w() {
        return Y_1740_V;
    }

    public static void n_1700_B(Consumer<T_2910_P> materialConsumer) {
        materialConsumer.accept(v_4262_N);
        w_1484_f.forEach(materialConsumer);
        for (J_2538_C j_2538_C : J_2538_C.values()) {
            materialConsumer.accept(new T_2910_P(R_4764_Y, j_2538_C.n_1700_B(true)));
            materialConsumer.accept(new T_2910_P(G_564_y, j_2538_C.n_1700_B(false)));
        }
        t_148_a.values().forEach(materialConsumer);
        for (T_2910_P t_2910_P : s_956_w) {
            materialConsumer.accept(t_2910_P);
        }
        materialConsumer.accept(u_2550_I);
        materialConsumer.accept(M_588_G);
        materialConsumer.accept(P_4830_p);
        materialConsumer.accept(h_1847_R);
        materialConsumer.accept(Q_4569_t);
        materialConsumer.accept(M_182_A);
        materialConsumer.accept(t_1786_h);
        materialConsumer.accept(multiplayerClientSuggestionProvider);
        materialConsumer.accept(w_1457_N);
        materialConsumer.accept(Y_601_j);
    }

    public static T_2910_P n_1700_B(WoodType woodType) {
        return new T_2910_P(P_1922_E, new g_2336_b("entity/signs/" + woodType.J_1907_R()));
    }

    private static T_2910_P n_1700_B(String chestName) {
        return new T_2910_P(u_1723_Y, new g_2336_b("entity/chest/" + chestName));
    }

    public static T_2910_P n_1700_B(i_2154_H tileEntity, p_1429_o chestType, boolean holiday) {
        if (holiday) {
            return b_4440_Q.n_1700_B(chestType, h_1847_R, Q_4569_t, M_182_A);
        }
        if (tileEntity instanceof f_395_A) {
            return b_4440_Q.n_1700_B(chestType, u_2550_I, M_588_G, P_4830_p);
        }
        return tileEntity instanceof s_3081_t ? Y_601_j : b_4440_Q.n_1700_B(chestType, t_1786_h, multiplayerClientSuggestionProvider, w_1457_N);
    }

    private static T_2910_P n_1700_B(p_1429_o chestType, T_2910_P doubleMaterial, T_2910_P leftMaterial, T_2910_P rightMaterial) {
        switch (chestType) {
            case J_1907_R: {
                return leftMaterial;
            }
            case R_4764_Y: {
                return rightMaterial;
            }
        }
        return doubleMaterial;
    }
}


