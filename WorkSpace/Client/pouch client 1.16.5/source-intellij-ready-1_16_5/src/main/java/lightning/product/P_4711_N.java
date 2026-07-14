/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Consumer;
import lightning.product.A_2629_w;
import lightning.product.B_1335_M;
import lightning.product.B_368_w;
import lightning.product.F_2904_S;
import lightning.product.I_4421_I;
import lightning.product.J_1008_m;
import lightning.product.DamageSourcePredicate;
import lightning.product.S_3844_E;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.W_4813_f;
import lightning.product.Y_3066_B;
import lightning.product.Y_3588_g;
import lightning.product.a_3742_W;
import lightning.product.b_1430_k;
import lightning.product.b_3129_s;
import lightning.product.b_3334_n;
import lightning.product.e_1503_j;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.j_2461_G;
import lightning.product.k_200_a;
import lightning.product.k_594_Q;
import lightning.product.o_3456_E;
import lightning.product.EntityTypeTags;
import lightning.product.Items;
import lightning.product.r_1970_q;
import lightning.product.RequirementsStrategy;
import lightning.product.MinMaxBounds;
import lightning.product.t_4057_p;
import lightning.product.t_5_h;
import lightning.product.v_2746_S;
import lightning.product.x_282_a;
import lightning.product.z_1100_b;

public class P_4711_N
implements Consumer<Consumer<A_2629_w>> {
    private static final List<f_2392_k<k_594_Q>> n_1700_B = ImmutableList.of(biomeBiomes.A_4115_X, biomeBiomes.w_1484_f, biomeBiomes.v_4262_N, biomeBiomes.R_4764_Y, biomeBiomes.w_1457_N, biomeBiomes.n_3318_d, biomeBiomes.t_4043_B, biomeBiomes.d_2461_k, biomeBiomes.P_1922_E, biomeBiomes.Z_875_P, biomeBiomes.P_4830_p, biomeBiomes.Y_601_j, (Object[])new f_2392_k[]{biomeBiomes.h_1847_R, biomeBiomes.G_624_v, biomeBiomes.z_1737_N, biomeBiomes.J_1907_R, biomeBiomes.M_588_G, biomeBiomes.e_4240_b, biomeBiomes.c_3005_b, biomeBiomes.C_2741_M, biomeBiomes.k_2293_S, biomeBiomes.M_182_A, biomeBiomes.G_564_y, biomeBiomes.multiplayerClientSuggestionProvider, biomeBiomes.Q_2552_b, biomeBiomes.t_1786_h, biomeBiomes.v_4276_D, biomeBiomes.x_607_J, biomeBiomes.T_2506_i, biomeBiomes.Y_1740_V, biomeBiomes.u_1723_Y, biomeBiomes.H_2857_Y, biomeBiomes.Q_4569_t, biomeBiomes.d_2427_y, biomeBiomes.B_1668_F, biomeBiomes.g_164_R, biomeBiomes.X_933_l, biomeBiomes.H_1990_U, biomeBiomes.N_2525_X, biomeBiomes.c_4037_x, biomeBiomes.V_1446_Y, biomeBiomes.PlayerInfo});
    private static final t_5_h<?>[] J_1907_R = new t_5_h[]{t_5_h.u_1723_Y, t_5_h.t_148_a, t_5_h.P_4830_p, t_5_h.t_1786_h, t_5_h.multiplayerClientSuggestionProvider, t_5_h.Y_601_j, t_5_h.Y_259_p, t_5_h.Q_2552_b, t_5_h.C_2741_M, t_5_h.Y_1740_V, t_5_h.x_607_J, t_5_h.e_4240_b, t_5_h.d_2427_y, t_5_h.B_1668_F, t_5_h.r_715_M, t_5_h.i_1637_u, t_5_h.Ping, t_5_h.p_178_J, t_5_h.e_1992_r, t_5_h.Ops, t_5_h.t_4219_U, t_5_h.V_1446_Y, t_5_h.V_1225_t, t_5_h.RealmsServerPing, t_5_h.M_1641_O, t_5_h.F_2624_D, t_5_h.y_1700_S, t_5_h.RetryCallException, t_5_h.RowButton, t_5_h.r_3651_U, t_5_h.S_980_j, t_5_h.M_2677_i, t_5_h.R_3077_Z, t_5_h.c_132_F};

    public void n_1700_B(Consumer<A_2629_w> p_accept_1_) {
        A_2629_w advancement = A_2629_w.n_1700_B.n_1700_B().n_1700_B(Items.S_1431_H, (x_282_a)new F_2904_S("advancements.adventure.root.title"), (x_282_a)new F_2904_S("advancements.adventure.root.description"), new g_2336_b("textures/gui/advancements/backgrounds/adventure.png"), W_4813_f.n_1700_B, false, false, false).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B("killed_something", B_1335_M.n_1700_B.J_1907_R()).n_1700_B("killed_by_something", B_1335_M.n_1700_B.G_564_y()).n_1700_B(p_accept_1_, "adventure/root");
        A_2629_w advancement1 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(a_3742_W.F_1410_V, (x_282_a)new F_2904_S("advancements.adventure.sleep_in_bed.title"), (x_282_a)new F_2904_S("advancements.adventure.sleep_in_bed.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("slept_in_bed", b_3334_n.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "adventure/sleep_in_bed");
        P_4711_N.n_1700_B(A_2629_w.n_1700_B.n_1700_B(), n_1700_B).n_1700_B(advancement1).n_1700_B(Items.V_4557_X, (x_282_a)new F_2904_S("advancements.adventure.adventuring_time.title"), (x_282_a)new F_2904_S("advancements.adventure.adventuring_time.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(500)).n_1700_B(p_accept_1_, "adventure/adventuring_time");
        A_2629_w advancement2 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.Y_2905_A, (x_282_a)new F_2904_S("advancements.adventure.trade.title"), (x_282_a)new F_2904_S("advancements.adventure.trade.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("traded", z_1100_b.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "adventure/trade");
        A_2629_w advancement3 = this.n_1700_B(A_2629_w.n_1700_B.n_1700_B()).n_1700_B(advancement).n_1700_B(Items.w_2152_d, (x_282_a)new F_2904_S("advancements.adventure.kill_a_mob.title"), (x_282_a)new F_2904_S("advancements.adventure.kill_a_mob.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B(p_accept_1_, "adventure/kill_a_mob");
        this.n_1700_B(A_2629_w.n_1700_B.n_1700_B()).n_1700_B(advancement3).n_1700_B(Items.N_2592_G, (x_282_a)new F_2904_S("advancements.adventure.kill_all_mobs.title"), (x_282_a)new F_2904_S("advancements.adventure.kill_all_mobs.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B(p_accept_1_, "adventure/kill_all_mobs");
        A_2629_w advancement4 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.R_1796_s, (x_282_a)new F_2904_S("advancements.adventure.shoot_arrow.title"), (x_282_a)new F_2904_S("advancements.adventure.shoot_arrow.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("shot_arrow", v_2746_S.n_1700_B.n_1700_B(r_1970_q.n_1700_B.n_1700_B().n_1700_B(DamageSourcePredicate.n_1700_B.n_1700_B().n_1700_B(true).n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(EntityTypeTags.P_1922_E))))).n_1700_B(p_accept_1_, "adventure/shoot_arrow");
        A_2629_w advancement5 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.P_2605_j, (x_282_a)new F_2904_S("advancements.adventure.throw_trident.title"), (x_282_a)new F_2904_S("advancements.adventure.throw_trident.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("shot_trident", v_2746_S.n_1700_B.n_1700_B(r_1970_q.n_1700_B.n_1700_B().n_1700_B(DamageSourcePredicate.n_1700_B.n_1700_B().n_1700_B(true).n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.ValueObject))))).n_1700_B(p_accept_1_, "adventure/throw_trident");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement5).n_1700_B(Items.P_2605_j, (x_282_a)new F_2904_S("advancements.adventure.very_very_frightening.title"), (x_282_a)new F_2904_S("advancements.adventure.very_very_frightening.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("struck_villager", I_4421_I.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler).J_1907_R())).n_1700_B(p_accept_1_, "adventure/very_very_frightening");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement2).n_1700_B(a_3742_W.X_2048_Y, (x_282_a)new F_2904_S("advancements.adventure.summon_iron_golem.title"), (x_282_a)new F_2904_S("advancements.adventure.summon_iron_golem.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("summoned_golem", Y_3066_B.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.v_4276_D))).n_1700_B(p_accept_1_, "adventure/summon_iron_golem");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement4).n_1700_B(Items.g_24_p, (x_282_a)new F_2904_S("advancements.adventure.sniper_duel.title"), (x_282_a)new F_2904_S("advancements.adventure.sniper_duel.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(50)).n_1700_B("killed_skeleton", B_1335_M.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.V_1446_Y).n_1700_B(o_3456_E.n_1700_B(MinMaxBounds.n_1700_B.n_1700_B(50.0f))), DamageSourcePredicate.n_1700_B.n_1700_B().n_1700_B(true))).n_1700_B(p_accept_1_, "adventure/sniper_duel");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.N_81_X, (x_282_a)new F_2904_S("advancements.adventure.totem_of_undying.title"), (x_282_a)new F_2904_S("advancements.adventure.totem_of_undying.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("used_totem", e_1503_j.n_1700_B.n_1700_B(Items.N_81_X)).n_1700_B(p_accept_1_, "adventure/totem_of_undying");
        A_2629_w advancement6 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.V_2454_J, (x_282_a)new F_2904_S("advancements.adventure.ol_betsy.title"), (x_282_a)new F_2904_S("advancements.adventure.ol_betsy.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("shot_crossbow", j_2461_G.n_1700_B.n_1700_B(Items.V_2454_J)).n_1700_B(p_accept_1_, "adventure/ol_betsy");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement6).n_1700_B(Items.V_2454_J, (x_282_a)new F_2904_S("advancements.adventure.whos_the_pillager_now.title"), (x_282_a)new F_2904_S("advancements.adventure.whos_the_pillager_now.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("kill_pillager", t_4057_p.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.p_178_J))).n_1700_B(p_accept_1_, "adventure/whos_the_pillager_now");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement6).n_1700_B(Items.V_2454_J, (x_282_a)new F_2904_S("advancements.adventure.two_birds_one_arrow.title"), (x_282_a)new F_2904_S("advancements.adventure.two_birds_one_arrow.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(65)).n_1700_B("two_birds", t_4057_p.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.r_715_M), b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.r_715_M))).n_1700_B(p_accept_1_, "adventure/two_birds_one_arrow");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement6).n_1700_B(Items.V_2454_J, (x_282_a)new F_2904_S("advancements.adventure.arbalistic.title"), (x_282_a)new F_2904_S("advancements.adventure.arbalistic.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, true).n_1700_B(J_1008_m.n_1700_B.n_1700_B(85)).n_1700_B("arbalistic", t_4057_p.n_1700_B.n_1700_B(MinMaxBounds.G_564_y.n_1700_B(5))).n_1700_B(p_accept_1_, "adventure/arbalistic");
        A_2629_w advancement7 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(b_3129_s.t_1786_h(), (x_282_a)new F_2904_S("advancements.adventure.voluntary_exile.title"), (x_282_a)new F_2904_S("advancements.adventure.voluntary_exile.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, true).n_1700_B("voluntary_exile", B_1335_M.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(EntityTypeTags.R_4764_Y).n_1700_B(k_200_a.J_1907_R))).n_1700_B(p_accept_1_, "adventure/voluntary_exile");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement7).n_1700_B(b_3129_s.t_1786_h(), (x_282_a)new F_2904_S("advancements.adventure.hero_of_the_village.title"), (x_282_a)new F_2904_S("advancements.adventure.hero_of_the_village.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, true).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B("hero_of_the_village", b_3334_n.n_1700_B.G_564_y()).n_1700_B(p_accept_1_, "adventure/hero_of_the_village");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(a_3742_W.B_1335_M.u_1723_Y(), (x_282_a)new F_2904_S("advancements.adventure.honey_block_slide.title"), (x_282_a)new F_2904_S("advancements.adventure.honey_block_slide.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("honey_block_slide", Y_3588_g.n_1700_B.n_1700_B(a_3742_W.B_1335_M)).n_1700_B(p_accept_1_, "adventure/honey_block_slide");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement4).n_1700_B(a_3742_W.Y_2805_J.u_1723_Y(), (x_282_a)new F_2904_S("advancements.adventure.bullseye.title"), (x_282_a)new F_2904_S("advancements.adventure.bullseye.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(50)).n_1700_B("bullseye", S_3844_E.n_1700_B.n_1700_B(MinMaxBounds.G_564_y.n_1700_B(15), b_1430_k.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(o_3456_E.n_1700_B(MinMaxBounds.n_1700_B.n_1700_B(30.0f))).J_1907_R()))).n_1700_B(p_accept_1_, "adventure/bullseye");
    }

    private A_2629_w.n_1700_B n_1700_B(A_2629_w.n_1700_B builder) {
        for (t_5_h<?> entitytype : J_1907_R) {
            builder.n_1700_B(V_3137_a.g_221_o.J_1907_R(entitytype).toString(), B_1335_M.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(entitytype)));
        }
        return builder;
    }

    protected static A_2629_w.n_1700_B n_1700_B(A_2629_w.n_1700_B builder, List<f_2392_k<k_594_Q>> biomes) {
        for (f_2392_k<k_594_Q> registrykey : biomes) {
            builder.n_1700_B(registrykey.n_1700_B().toString(), b_3334_n.n_1700_B.n_1700_B(B_368_w.n_1700_B(registrykey)));
        }
        return builder;
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((Consumer)object);
    }
}


