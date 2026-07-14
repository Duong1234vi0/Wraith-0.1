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
import lightning.product.A_4919_q;
import lightning.product.B_1335_M;
import lightning.product.B_368_w;
import lightning.product.D_3640_k;
import lightning.product.D_4237_z;
import lightning.product.F_2904_S;
import lightning.product.StructureFeature;
import lightning.product.I_3736_z;
import lightning.product.J_1008_m;
import lightning.product.MobEffects;
import lightning.product.DamageSourcePredicate;
import lightning.product.ItemTags;
import lightning.product.P_2068_y;
import lightning.product.P_2605_j;
import lightning.product.P_4711_N;
import lightning.product.Q_1036_Q;
import lightning.product.biomeBiomes;
import lightning.product.W_2672_e;
import lightning.product.W_4813_f;
import lightning.product.Y_2805_J;
import lightning.product.Y_3066_B;
import lightning.product.a_3742_W;
import lightning.product.b_1430_k;
import lightning.product.b_3334_n;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.g_1096_r;
import lightning.product.g_2336_b;
import lightning.product.k_200_a;
import lightning.product.k_594_Q;
import lightning.product.m_1964_F;
import lightning.product.m_3052_r;
import lightning.product.n_4560_z;
import lightning.product.o_3456_E;
import lightning.product.q_1704_m;
import lightning.product.Items;
import lightning.product.q_608_V;
import lightning.product.r_2687_x;
import lightning.product.RequirementsStrategy;
import lightning.product.MinMaxBounds;
import lightning.product.t_5_h;
import lightning.product.w_4866_k;
import lightning.product.x_2711_Y;
import lightning.product.x_282_a;
import lightning.product.y_2836_h;
import lightning.product.z_936_s;

public class J_1617_l
implements Consumer<Consumer<A_2629_w>> {
    private static final List<f_2392_k<k_594_Q>> n_1700_B = ImmutableList.of(biomeBiomes.t_148_a, biomeBiomes.V_1225_t, biomeBiomes.q_1982_R, biomeBiomes.U_1241_n, biomeBiomes.dtoRealmsServerAddress);
    private static final b_1430_k.n_1700_B J_1907_R = b_1430_k.n_1700_B.n_1700_B(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, b_1430_k.J_1907_R.n_1700_B().n_1700_B(k_200_a.n_1700_B.n_1700_B().n_1700_B(w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.h_3066_J).J_1907_R()).J_1907_R())).n_1700_B().build(), W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, b_1430_k.J_1907_R.n_1700_B().n_1700_B(k_200_a.n_1700_B.n_1700_B().J_1907_R(w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.m_38_G).J_1907_R()).J_1907_R())).n_1700_B().build(), W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, b_1430_k.J_1907_R.n_1700_B().n_1700_B(k_200_a.n_1700_B.n_1700_B().R_4764_Y(w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.k_4946_A).J_1907_R()).J_1907_R())).n_1700_B().build(), W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, b_1430_k.J_1907_R.n_1700_B().n_1700_B(k_200_a.n_1700_B.n_1700_B().G_564_y(w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.m_4644_u).J_1907_R()).J_1907_R())).n_1700_B().build());

    public void n_1700_B(Consumer<A_2629_w> p_accept_1_) {
        A_2629_w advancement = A_2629_w.n_1700_B.n_1700_B().n_1700_B(a_3742_W.NoInteract, (x_282_a)new F_2904_S("advancements.nether.root.title"), (x_282_a)new F_2904_S("advancements.nether.root.description"), new g_2336_b("textures/gui/advancements/backgrounds/nether.png"), W_4813_f.n_1700_B, false, false, false).n_1700_B("entered_nether", z_936_s.n_1700_B.n_1700_B(b_4507_u.v_4262_N)).n_1700_B(p_accept_1_, "nether/root");
        A_2629_w advancement1 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.CraftingTableBlock, (x_282_a)new F_2904_S("advancements.nether.return_to_sender.title"), (x_282_a)new F_2904_S("advancements.nether.return_to_sender.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(50)).n_1700_B("killed_ghast", B_1335_M.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.Y_1740_V), DamageSourcePredicate.n_1700_B.n_1700_B().n_1700_B(true).n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.T_2506_i)))).n_1700_B(p_accept_1_, "nether/return_to_sender");
        A_2629_w advancement2 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(a_3742_W.h_1015_G, (x_282_a)new F_2904_S("advancements.nether.find_fortress.title"), (x_282_a)new F_2904_S("advancements.nether.find_fortress.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("fortress", b_3334_n.n_1700_B.n_1700_B(B_368_w.n_1700_B(StructureFeature.h_1847_R))).n_1700_B(p_accept_1_, "nether/find_fortress");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.S_1431_H, (x_282_a)new F_2904_S("advancements.nether.fast_travel.title"), (x_282_a)new F_2904_S("advancements.nether.fast_travel.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B("travelled", x_2711_Y.n_1700_B.n_1700_B(o_3456_E.n_1700_B(MinMaxBounds.n_1700_B.n_1700_B(7000.0f)))).n_1700_B(p_accept_1_, "nether/fast_travel");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement1).n_1700_B(Items.b_3334_n, (x_282_a)new F_2904_S("advancements.nether.uneasy_alliance.title"), (x_282_a)new F_2904_S("advancements.nether.uneasy_alliance.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B("killed_ghast", B_1335_M.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.Y_1740_V).n_1700_B(B_368_w.J_1907_R(b_4507_u.u_1723_Y)))).n_1700_B(p_accept_1_, "nether/uneasy_alliance");
        A_2629_w advancement3 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement2).n_1700_B(a_3742_W.ModuleCategory, (x_282_a)new F_2904_S("advancements.nether.get_wither_skull.title"), (x_282_a)new F_2904_S("advancements.nether.get_wither_skull.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("wither_skull", P_2068_y.n_1700_B.n_1700_B(a_3742_W.ModuleCategory)).n_1700_B(p_accept_1_, "nether/get_wither_skull");
        A_2629_w advancement4 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.FallingBlock, (x_282_a)new F_2904_S("advancements.nether.summon_wither.title"), (x_282_a)new F_2904_S("advancements.nether.summon_wither.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("summoned", Y_3066_B.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.r_3651_U))).n_1700_B(p_accept_1_, "nether/summon_wither");
        A_2629_w advancement5 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement2).n_1700_B(Items.A_2487_t, (x_282_a)new F_2904_S("advancements.nether.obtain_blaze_rod.title"), (x_282_a)new F_2904_S("advancements.nether.obtain_blaze_rod.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("blaze_rod", P_2068_y.n_1700_B.n_1700_B(Items.A_2487_t)).n_1700_B(p_accept_1_, "nether/obtain_blaze_rod");
        A_2629_w advancement6 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement4).n_1700_B(a_3742_W.k_578_l, (x_282_a)new F_2904_S("advancements.nether.create_beacon.title"), (x_282_a)new F_2904_S("advancements.nether.create_beacon.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("beacon", q_608_V.n_1700_B.n_1700_B(MinMaxBounds.G_564_y.J_1907_R(1))).n_1700_B(p_accept_1_, "nether/create_beacon");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement6).n_1700_B(a_3742_W.k_578_l, (x_282_a)new F_2904_S("advancements.nether.create_full_beacon.title"), (x_282_a)new F_2904_S("advancements.nether.create_full_beacon.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("beacon", q_608_V.n_1700_B.n_1700_B(MinMaxBounds.G_564_y.n_1700_B(4))).n_1700_B(p_accept_1_, "nether/create_full_beacon");
        A_2629_w advancement7 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement5).n_1700_B(Items.j_2461_G, (x_282_a)new F_2904_S("advancements.nether.brew_potion.title"), (x_282_a)new F_2904_S("advancements.nether.brew_potion.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("potion", n_4560_z.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "nether/brew_potion");
        A_2629_w advancement8 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement7).n_1700_B(Items.H_2506_c, (x_282_a)new F_2904_S("advancements.nether.all_potions.title"), (x_282_a)new F_2904_S("advancements.nether.all_potions.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B("all_effects", I_3736_z.n_1700_B.n_1700_B(y_2836_h.n_1700_B().n_1700_B(MobEffects.n_1700_B).n_1700_B(MobEffects.J_1907_R).n_1700_B(MobEffects.P_1922_E).n_1700_B(MobEffects.w_1484_f).n_1700_B(MobEffects.s_956_w).n_1700_B(MobEffects.M_588_G).n_1700_B(MobEffects.P_4830_p).n_1700_B(MobEffects.h_1847_R).n_1700_B(MobEffects.M_182_A).n_1700_B(MobEffects.multiplayerClientSuggestionProvider).n_1700_B(MobEffects.w_1457_N).n_1700_B(MobEffects.H_2857_Y).n_1700_B(MobEffects.u_2550_I))).n_1700_B(p_accept_1_, "nether/all_potions");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement8).n_1700_B(Items.G_1539_D, (x_282_a)new F_2904_S("advancements.nether.all_effects.title"), (x_282_a)new F_2904_S("advancements.nether.all_effects.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, true).n_1700_B(J_1008_m.n_1700_B.n_1700_B(1000)).n_1700_B("all_effects", I_3736_z.n_1700_B.n_1700_B(y_2836_h.n_1700_B().n_1700_B(MobEffects.n_1700_B).n_1700_B(MobEffects.J_1907_R).n_1700_B(MobEffects.P_1922_E).n_1700_B(MobEffects.w_1484_f).n_1700_B(MobEffects.s_956_w).n_1700_B(MobEffects.M_588_G).n_1700_B(MobEffects.P_4830_p).n_1700_B(MobEffects.h_1847_R).n_1700_B(MobEffects.M_182_A).n_1700_B(MobEffects.multiplayerClientSuggestionProvider).n_1700_B(MobEffects.w_1457_N).n_1700_B(MobEffects.Y_601_j).n_1700_B(MobEffects.R_4764_Y).n_1700_B(MobEffects.G_564_y).n_1700_B(MobEffects.q_2307_F).n_1700_B(MobEffects.k_2293_S).n_1700_B(MobEffects.Q_2552_b).n_1700_B(MobEffects.t_1786_h).n_1700_B(MobEffects.t_148_a).n_1700_B(MobEffects.u_2550_I).n_1700_B(MobEffects.H_2857_Y).n_1700_B(MobEffects.A_4115_X).n_1700_B(MobEffects.Y_1740_V).n_1700_B(MobEffects.Q_4569_t).n_1700_B(MobEffects.t_4043_B).n_1700_B(MobEffects.x_607_J))).n_1700_B(p_accept_1_, "nether/all_effects");
        A_2629_w advancement9 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.TallFlowerBlock, (x_282_a)new F_2904_S("advancements.nether.obtain_ancient_debris.title"), (x_282_a)new F_2904_S("advancements.nether.obtain_ancient_debris.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("ancient_debris", P_2068_y.n_1700_B.n_1700_B(Items.TallFlowerBlock)).n_1700_B(p_accept_1_, "nether/obtain_ancient_debris");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement9).n_1700_B(Items.O_1043_U, (x_282_a)new F_2904_S("advancements.nether.netherite_armor.title"), (x_282_a)new F_2904_S("advancements.nether.netherite_armor.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B("netherite_armor", P_2068_y.n_1700_B.n_1700_B(Items.u_488_m, Items.O_1043_U, Items.v_1900_v, Items.j_2129_E)).n_1700_B(p_accept_1_, "nether/netherite_armor");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement9).n_1700_B(Items.SweetBerryBushBlock, (x_282_a)new F_2904_S("advancements.nether.use_lodestone.title"), (x_282_a)new F_2904_S("advancements.nether.use_lodestone.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("use_lodestone", g_1096_r.n_1700_B.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(D_4237_z.n_1700_B.n_1700_B().n_1700_B(a_3742_W.m_3052_r).J_1907_R()), w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.X_1303_p))).n_1700_B(p_accept_1_, "nether/use_lodestone");
        A_2629_w advancement10 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.TallSeagrass, (x_282_a)new F_2904_S("advancements.nether.obtain_crying_obsidian.title"), (x_282_a)new F_2904_S("advancements.nether.obtain_crying_obsidian.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("crying_obsidian", P_2068_y.n_1700_B.n_1700_B(Items.TallSeagrass)).n_1700_B(p_accept_1_, "nether/obtain_crying_obsidian");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement10).n_1700_B(Items.Y_1820_h, (x_282_a)new F_2904_S("advancements.nether.charge_respawn_anchor.title"), (x_282_a)new F_2904_S("advancements.nether.charge_respawn_anchor.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("charge_respawn_anchor", g_1096_r.n_1700_B.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(D_4237_z.n_1700_B.n_1700_B().n_1700_B(a_3742_W.WrappedMinMaxBounds).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(P_2605_j.P_4830_p, 4).J_1907_R()).J_1907_R()), w_4866_k.n_1700_B.n_1700_B().n_1700_B(a_3742_W.X_2960_b))).n_1700_B(p_accept_1_, "nether/charge_respawn_anchor");
        A_2629_w advancement11 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.j_3599_p, (x_282_a)new F_2904_S("advancements.nether.ride_strider.title"), (x_282_a)new F_2904_S("advancements.nether.ride_strider.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("used_warped_fungus_on_a_stick", Y_2805_J.n_1700_B.n_1700_B(b_1430_k.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.RealmsWorldOptions).J_1907_R()).J_1907_R()), w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.j_3599_p).J_1907_R(), MinMaxBounds.G_564_y.P_1922_E)).n_1700_B(p_accept_1_, "nether/ride_strider");
        P_4711_N.n_1700_B(A_2629_w.n_1700_B.n_1700_B(), n_1700_B).n_1700_B(advancement11).n_1700_B(Items.j_2129_E, (x_282_a)new F_2904_S("advancements.nether.explore_nether.title"), (x_282_a)new F_2904_S("advancements.nether.explore_nether.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(500)).n_1700_B(p_accept_1_, "nether/explore_nether");
        A_2629_w advancement12 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.M_3703_h, (x_282_a)new F_2904_S("advancements.nether.find_bastion.title"), (x_282_a)new F_2904_S("advancements.nether.find_bastion.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("bastion", b_3334_n.n_1700_B.n_1700_B(B_368_w.n_1700_B(StructureFeature.w_1457_N))).n_1700_B(p_accept_1_, "nether/find_bastion");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement12).n_1700_B(a_3742_W.L_1362_X, (x_282_a)new F_2904_S("advancements.nether.loot_bastion.title"), (x_282_a)new F_2904_S("advancements.nether.loot_bastion.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B("loot_bastion_other", m_1964_F.n_1700_B.n_1700_B(new g_2336_b("minecraft:chests/bastion_other"))).n_1700_B("loot_bastion_treasure", m_1964_F.n_1700_B.n_1700_B(new g_2336_b("minecraft:chests/bastion_treasure"))).n_1700_B("loot_bastion_hoglin_stable", m_1964_F.n_1700_B.n_1700_B(new g_2336_b("minecraft:chests/bastion_hoglin_stable"))).n_1700_B("loot_bastion_bridge", m_1964_F.n_1700_B.n_1700_B(new g_2336_b("minecraft:chests/bastion_bridge"))).n_1700_B(p_accept_1_, "nether/loot_bastion");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B(Items.ServerHandshakePacketListener, (x_282_a)new F_2904_S("advancements.nether.distract_piglin.title"), (x_282_a)new F_2904_S("advancements.nether.distract_piglin.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("distract_piglin", Q_1036_Q.n_1700_B.n_1700_B(J_1907_R, w_4866_k.n_1700_B.n_1700_B().n_1700_B(ItemTags.q_4610_l), b_1430_k.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.i_1637_u).n_1700_B(D_3640_k.n_1700_B.n_1700_B().J_1907_R(false).J_1907_R()).J_1907_R()))).n_1700_B("distract_piglin_directly", m_3052_r.n_1700_B.n_1700_B(J_1907_R, w_4866_k.n_1700_B.n_1700_B().n_1700_B(A_4919_q.n_1700_B), b_1430_k.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.i_1637_u).n_1700_B(D_3640_k.n_1700_B.n_1700_B().J_1907_R(false).J_1907_R()).J_1907_R()))).n_1700_B(p_accept_1_, "nether/distract_piglin");
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((Consumer)object);
    }
}



