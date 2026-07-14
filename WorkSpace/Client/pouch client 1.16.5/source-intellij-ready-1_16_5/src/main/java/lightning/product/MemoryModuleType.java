/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lightning.product.AgableMob;
import lightning.product.F_427_K;
import lightning.product.SerializableUUID;
import lightning.product.WalkTarget;
import lightning.product.N_4263_v;
import lightning.product.O_1984_z;
import lightning.product.P_11_z;
import lightning.product.Hoglin;
import lightning.product.V_3137_a;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.AbstractPiglin;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.n_1494_c;
import lightning.product.PositionTracker;
import lightning.product.r_4811_B;

public class MemoryModuleType<U> {
    public static final MemoryModuleType<Void> n_1700_B = MemoryModuleType.n_1700_B("dummy");
    public static final MemoryModuleType<F_427_K> J_1907_R = MemoryModuleType.n_1700_B("home", F_427_K.n_1700_B);
    public static final MemoryModuleType<F_427_K> R_4764_Y = MemoryModuleType.n_1700_B("job_site", F_427_K.n_1700_B);
    public static final MemoryModuleType<F_427_K> G_564_y = MemoryModuleType.n_1700_B("potential_job_site", F_427_K.n_1700_B);
    public static final MemoryModuleType<F_427_K> P_1922_E = MemoryModuleType.n_1700_B("meeting_point", F_427_K.n_1700_B);
    public static final MemoryModuleType<List<F_427_K>> u_1723_Y = MemoryModuleType.n_1700_B("secondary_job_site");
    public static final MemoryModuleType<List<r_4811_B>> v_4262_N = MemoryModuleType.n_1700_B("mobs");
    public static final MemoryModuleType<List<r_4811_B>> w_1484_f = MemoryModuleType.n_1700_B("visible_mobs");
    public static final MemoryModuleType<List<r_4811_B>> t_148_a = MemoryModuleType.n_1700_B("visible_villager_babies");
    public static final MemoryModuleType<List<a_3913_L>> s_956_w = MemoryModuleType.n_1700_B("nearest_players");
    public static final MemoryModuleType<a_3913_L> u_2550_I = MemoryModuleType.n_1700_B("nearest_visible_player");
    public static final MemoryModuleType<a_3913_L> M_588_G = MemoryModuleType.n_1700_B("nearest_visible_targetable_player");
    public static final MemoryModuleType<WalkTarget> P_4830_p = MemoryModuleType.n_1700_B("walk_target");
    public static final MemoryModuleType<PositionTracker> h_1847_R = MemoryModuleType.n_1700_B("look_target");
    public static final MemoryModuleType<r_4811_B> Q_4569_t = MemoryModuleType.n_1700_B("attack_target");
    public static final MemoryModuleType<Boolean> M_182_A = MemoryModuleType.n_1700_B("attack_cooling_down");
    public static final MemoryModuleType<r_4811_B> t_1786_h = MemoryModuleType.n_1700_B("interaction_target");
    public static final MemoryModuleType<AgableMob> multiplayerClientSuggestionProvider = MemoryModuleType.n_1700_B("breed_target");
    public static final MemoryModuleType<N_4263_v> w_1457_N = MemoryModuleType.n_1700_B("ride_target");
    public static final MemoryModuleType<b_1722_e> Y_601_j = MemoryModuleType.n_1700_B("path");
    public static final MemoryModuleType<List<F_427_K>> Y_259_p = MemoryModuleType.n_1700_B("interactable_doors");
    public static final MemoryModuleType<Set<F_427_K>> Q_2552_b = MemoryModuleType.n_1700_B("doors_to_close");
    public static final MemoryModuleType<c_1514_x> C_2741_M = MemoryModuleType.n_1700_B("nearest_bed");
    public static final MemoryModuleType<P_11_z> k_2293_S = MemoryModuleType.n_1700_B("hurt_by");
    public static final MemoryModuleType<r_4811_B> q_2307_F = MemoryModuleType.n_1700_B("hurt_by_entity");
    public static final MemoryModuleType<r_4811_B> Z_875_P = MemoryModuleType.n_1700_B("avoid_target");
    public static final MemoryModuleType<r_4811_B> c_3005_b = MemoryModuleType.n_1700_B("nearest_hostile");
    public static final MemoryModuleType<F_427_K> H_2857_Y = MemoryModuleType.n_1700_B("hiding_place");
    public static final MemoryModuleType<Long> A_4115_X = MemoryModuleType.n_1700_B("heard_bell_time");
    public static final MemoryModuleType<Long> Y_1740_V = MemoryModuleType.n_1700_B("cant_reach_walk_target_since");
    public static final MemoryModuleType<Boolean> t_4043_B = MemoryModuleType.n_1700_B("golem_detected_recently", Codec.BOOL);
    public static final MemoryModuleType<Long> x_607_J = MemoryModuleType.n_1700_B("last_slept", Codec.LONG);
    public static final MemoryModuleType<Long> e_4240_b = MemoryModuleType.n_1700_B("last_woken", Codec.LONG);
    public static final MemoryModuleType<Long> n_3318_d = MemoryModuleType.n_1700_B("last_worked_at_poi", Codec.LONG);
    public static final MemoryModuleType<AgableMob> d_2427_y = MemoryModuleType.n_1700_B("nearest_visible_adult");
    public static final MemoryModuleType<n_1494_c> z_1737_N = MemoryModuleType.n_1700_B("nearest_visible_wanted_item");
    public static final MemoryModuleType<Z_530_i> v_4276_D = MemoryModuleType.n_1700_B("nearest_visible_nemesis");
    public static final MemoryModuleType<UUID> d_2461_k = MemoryModuleType.n_1700_B("angry_at", SerializableUUID.n_1700_B);
    public static final MemoryModuleType<Boolean> G_624_v = MemoryModuleType.n_1700_B("universal_anger", Codec.BOOL);
    public static final MemoryModuleType<Boolean> T_2506_i = MemoryModuleType.n_1700_B("admiring_item", Codec.BOOL);
    public static final MemoryModuleType<Integer> q_4610_l = MemoryModuleType.n_1700_B("time_trying_to_reach_admire_item");
    public static final MemoryModuleType<Boolean> z_4693_k = MemoryModuleType.n_1700_B("disable_walk_to_admire_item");
    public static final MemoryModuleType<Boolean> g_221_o = MemoryModuleType.n_1700_B("admiring_disabled", Codec.BOOL);
    public static final MemoryModuleType<Boolean> e_2887_G = MemoryModuleType.n_1700_B("hunted_recently", Codec.BOOL);
    public static final MemoryModuleType<c_1514_x> B_1668_F = MemoryModuleType.n_1700_B("celebrate_location");
    public static final MemoryModuleType<Boolean> g_164_R = MemoryModuleType.n_1700_B("dancing");
    public static final MemoryModuleType<Hoglin> X_933_l = MemoryModuleType.n_1700_B("nearest_visible_huntable_hoglin");
    public static final MemoryModuleType<Hoglin> Z_976_R = MemoryModuleType.n_1700_B("nearest_visible_baby_hoglin");
    public static final MemoryModuleType<a_3913_L> H_1990_U = MemoryModuleType.n_1700_B("nearest_targetable_player_not_wearing_gold");
    public static final MemoryModuleType<List<AbstractPiglin>> N_2525_X = MemoryModuleType.n_1700_B("nearby_adult_piglins");
    public static final MemoryModuleType<List<AbstractPiglin>> c_4037_x = MemoryModuleType.n_1700_B("nearest_visible_adult_piglins");
    public static final MemoryModuleType<List<Hoglin>> g_2268_R = MemoryModuleType.n_1700_B("nearest_visible_adult_hoglins");
    public static final MemoryModuleType<AbstractPiglin> T_3594_S = MemoryModuleType.n_1700_B("nearest_visible_adult_piglin");
    public static final MemoryModuleType<r_4811_B> D_4792_h = MemoryModuleType.n_1700_B("nearest_visible_zombified");
    public static final MemoryModuleType<Integer> s_2632_s = MemoryModuleType.n_1700_B("visible_adult_piglin_count");
    public static final MemoryModuleType<Integer> l_1233_K = MemoryModuleType.n_1700_B("visible_adult_hoglin_count");
    public static final MemoryModuleType<a_3913_L> z_1333_t = MemoryModuleType.n_1700_B("nearest_player_holding_wanted_item");
    public static final MemoryModuleType<Boolean> O_508_d = MemoryModuleType.n_1700_B("ate_recently");
    public static final MemoryModuleType<c_1514_x> r_715_M = MemoryModuleType.n_1700_B("nearest_repellent");
    public static final MemoryModuleType<Boolean> A_1038_p = MemoryModuleType.n_1700_B("pacified");
    private final Optional<Codec<O_1984_z<U>>> i_1637_u;

    private MemoryModuleType(Optional<Codec<U>> optionalCodec) {
        this.i_1637_u = optionalCodec.map(O_1984_z::n_1700_B);
    }

    public String toString() {
        return V_3137_a.i_1637_u.J_1907_R(this).toString();
    }

    public Optional<Codec<O_1984_z<U>>> n_1700_B() {
        return this.i_1637_u;
    }

    private static <U> MemoryModuleType<U> n_1700_B(String identifier, Codec<U> codec) {
        return V_3137_a.n_1700_B(V_3137_a.i_1637_u, new g_2336_b(identifier), new MemoryModuleType<U>(Optional.of(codec)));
    }

    private static <U> MemoryModuleType<U> n_1700_B(String identifier) {
        return V_3137_a.n_1700_B(V_3137_a.i_1637_u, new g_2336_b(identifier), new MemoryModuleType<U>(Optional.empty()));
    }
}


