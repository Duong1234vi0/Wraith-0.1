/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Suppliers
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 */
package lightning.product;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lightning.product.J_2868_p;
import lightning.product.K_4074_S;
import lightning.product.VillagerProfession;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.a_3742_W;
import lightning.product.g_2336_b;
import lightning.product.h_2829_o;
import lightning.product.j_3341_s;

public class q_2232_A {
    private static final Supplier<Set<q_2232_A>> q_2307_F = Suppliers.memoize(() -> V_3137_a.r_715_M.u_1723_Y().map(VillagerProfession::n_1700_B).collect(Collectors.toSet()));
    public static final Predicate<q_2232_A> n_1700_B = type -> q_2307_F.get().contains(type);
    public static final Predicate<q_2232_A> J_1907_R = type -> true;
    private static final Set<K_4074_S> Z_875_P = (Set)ImmutableList.of((Object)a_3742_W.F_1410_V, (Object)a_3742_W.S_4022_R, (Object)a_3742_W.H_1083_k, (Object)a_3742_W.R_3908_n, (Object)a_3742_W.RealmsWorldResetDto, (Object)a_3742_W.M_1641_O, (Object)a_3742_W.ValueObject, (Object)a_3742_W.dtoRealmsServerAddress, (Object)a_3742_W.RealmsWorldOptions, (Object)a_3742_W.RealmsServerPing, (Object)a_3742_W.q_1982_R, (Object)a_3742_W.U_1241_n, (Object[])new T_2915_h[]{a_3742_W.j_1564_a, a_3742_W.RegionPingResult, a_3742_W.V_1225_t, a_3742_W.w_612_n}).stream().flatMap(block -> block.t_1786_h().n_1700_B().stream()).filter(state -> state.R_4764_Y(J_2868_p.P_4830_p) == h_2829_o.n_1700_B).collect(ImmutableSet.toImmutableSet());
    private static final Map<K_4074_S, q_2232_A> c_3005_b = Maps.newHashMap();
    public static final q_2232_A R_4764_Y = q_2232_A.n_1700_B("unemployed", (Set<K_4074_S>)ImmutableSet.of(), 1, n_1700_B, 1);
    public static final q_2232_A G_564_y = q_2232_A.n_1700_B("armorer", q_2232_A.n_1700_B(a_3742_W.F_2052_z), 1, 1);
    public static final q_2232_A P_1922_E = q_2232_A.n_1700_B("butcher", q_2232_A.n_1700_B(a_3742_W.H_2506_c), 1, 1);
    public static final q_2232_A u_1723_Y = q_2232_A.n_1700_B("cartographer", q_2232_A.n_1700_B(a_3742_W.j_1376_w), 1, 1);
    public static final q_2232_A v_4262_N = q_2232_A.n_1700_B("cleric", q_2232_A.n_1700_B(a_3742_W.e_837_t), 1, 1);
    public static final q_2232_A w_1484_f = q_2232_A.n_1700_B("farmer", q_2232_A.n_1700_B(a_3742_W.P_2068_y), 1, 1);
    public static final q_2232_A t_148_a = q_2232_A.n_1700_B("fisherman", q_2232_A.n_1700_B(a_3742_W.y_254_d), 1, 1);
    public static final q_2232_A s_956_w = q_2232_A.n_1700_B("fletcher", q_2232_A.n_1700_B(a_3742_W.W_3801_h), 1, 1);
    public static final q_2232_A u_2550_I = q_2232_A.n_1700_B("leatherworker", q_2232_A.n_1700_B(a_3742_W.m_1621_v), 1, 1);
    public static final q_2232_A M_588_G = q_2232_A.n_1700_B("librarian", q_2232_A.n_1700_B(a_3742_W.F_489_x), 1, 1);
    public static final q_2232_A P_4830_p = q_2232_A.n_1700_B("mason", q_2232_A.n_1700_B(a_3742_W.f_4705_f), 1, 1);
    public static final q_2232_A h_1847_R = q_2232_A.n_1700_B("nitwit", (Set<K_4074_S>)ImmutableSet.of(), 1, 1);
    public static final q_2232_A Q_4569_t = q_2232_A.n_1700_B("shepherd", q_2232_A.n_1700_B(a_3742_W.m_1628_s), 1, 1);
    public static final q_2232_A M_182_A = q_2232_A.n_1700_B("toolsmith", q_2232_A.n_1700_B(a_3742_W.i_4833_u), 1, 1);
    public static final q_2232_A t_1786_h = q_2232_A.n_1700_B("weaponsmith", q_2232_A.n_1700_B(a_3742_W.v_2826_q), 1, 1);
    public static final q_2232_A multiplayerClientSuggestionProvider = q_2232_A.n_1700_B("home", Z_875_P, 1, 1);
    public static final q_2232_A w_1457_N = q_2232_A.n_1700_B("meeting", q_2232_A.n_1700_B(a_3742_W.l_3370_o), 32, 6);
    public static final q_2232_A Y_601_j = q_2232_A.n_1700_B("beehive", q_2232_A.n_1700_B(a_3742_W.t_4057_p), 0, 1);
    public static final q_2232_A Y_259_p = q_2232_A.n_1700_B("bee_nest", q_2232_A.n_1700_B(a_3742_W.w_4866_k), 0, 1);
    public static final q_2232_A Q_2552_b = q_2232_A.n_1700_B("nether_portal", q_2232_A.n_1700_B(a_3742_W.M_766_z), 0, 1);
    public static final q_2232_A C_2741_M = q_2232_A.n_1700_B("lodestone", q_2232_A.n_1700_B(a_3742_W.m_3052_r), 0, 1);
    protected static final Set<K_4074_S> k_2293_S = new ObjectOpenHashSet(c_3005_b.keySet());
    private final String H_2857_Y;
    private final Set<K_4074_S> A_4115_X;
    private final int Y_1740_V;
    private final Predicate<q_2232_A> t_4043_B;
    private final int x_607_J;

    private static Set<K_4074_S> n_1700_B(T_2915_h blockIn) {
        return ImmutableSet.copyOf(blockIn.t_1786_h().n_1700_B());
    }

    private q_2232_A(String nameIn, Set<K_4074_S> blockStatesIn, int maxFreeTicketsIn, Predicate<q_2232_A> predicate, int validRange) {
        this.H_2857_Y = nameIn;
        this.A_4115_X = ImmutableSet.copyOf(blockStatesIn);
        this.Y_1740_V = maxFreeTicketsIn;
        this.t_4043_B = predicate;
        this.x_607_J = validRange;
    }

    private q_2232_A(String nameIn, Set<K_4074_S> blockStatesIn, int maxFreeTicketsIn, int validRange) {
        this.H_2857_Y = nameIn;
        this.A_4115_X = ImmutableSet.copyOf(blockStatesIn);
        this.Y_1740_V = maxFreeTicketsIn;
        this.t_4043_B = type -> type == this;
        this.x_607_J = validRange;
    }

    public int n_1700_B() {
        return this.Y_1740_V;
    }

    public Predicate<q_2232_A> J_1907_R() {
        return this.t_4043_B;
    }

    public int R_4764_Y() {
        return this.x_607_J;
    }

    public String toString() {
        return this.H_2857_Y;
    }

    private static q_2232_A n_1700_B(String key, Set<K_4074_S> blockStates, int maxFreeTickets, int validRange) {
        return q_2232_A.n_1700_B(V_3137_a.n_1700_B(V_3137_a.A_1038_p, new g_2336_b(key), new q_2232_A(key, blockStates, maxFreeTickets, validRange)));
    }

    private static q_2232_A n_1700_B(String key, Set<K_4074_S> blockStates, int maxFreeTickets, Predicate<q_2232_A> predicate, int validRange) {
        return q_2232_A.n_1700_B(V_3137_a.n_1700_B(V_3137_a.A_1038_p, new g_2336_b(key), new q_2232_A(key, blockStates, maxFreeTickets, predicate, validRange)));
    }

    private static q_2232_A n_1700_B(q_2232_A poit) {
        poit.A_4115_X.forEach(state -> {
            q_2232_A pointofinteresttype = c_3005_b.put((K_4074_S)state, poit);
            if (pointofinteresttype != null) {
                throw j_3341_s.R_4764_Y(new IllegalStateException(String.format("%s is defined in too many tags", state)));
            }
        });
        return poit;
    }

    public static Optional<q_2232_A> n_1700_B(K_4074_S state) {
        return Optional.ofNullable(c_3005_b.get(state));
    }
}


