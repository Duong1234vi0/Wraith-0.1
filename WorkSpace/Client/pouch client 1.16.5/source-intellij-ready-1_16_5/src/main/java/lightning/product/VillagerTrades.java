/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.E_414_E;
import lightning.product.F_2904_S;
import lightning.product.F_3620_e;
import lightning.product.StructureFeature;
import lightning.product.G_3165_y;
import lightning.product.I_2154_Z;
import lightning.product.J_2020_G;
import lightning.product.MobEffects;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.L_1875_m;
import lightning.product.SuspiciousStewItem;
import lightning.product.N_4263_v;
import lightning.product.VillagerProfession;
import lightning.product.R_3043_n;
import lightning.product.T_2915_h;
import lightning.product.T_4041_i;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.a_3189_D;
import lightning.product.a_3742_W;
import lightning.product.DyeItem;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.g_422_i;
import lightning.product.j_3341_s;
import lightning.product.EnchantedBookItem;
import lightning.product.DyeableLeatherItem;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.MerchantOffer;
import lightning.product.y_528_b;

public class VillagerTrades {
    public static final Map<VillagerProfession, Int2ObjectMap<v_4262_N[]>> n_1700_B = j_3341_s.n_1700_B(Maps.newHashMap(), p_221237_0_ -> {
        p_221237_0_.put(VillagerProfession.u_1723_Y, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.V_3441_j, 20, 16, 2), new J_1907_R(Items.l_683_e, 26, 16, 2), new J_1907_R(Items.BaseCoralWallFanBlock, 22, 16, 2), new J_1907_R(Items.s_3401_U, 15, 16, 2), new s_956_w(Items.m_3828_C, 1, 6, 16, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(a_3742_W.A_3244_K, 6, 12, 10), new s_956_w(Items.y_2012_u, 1, 4, 5), new s_956_w(Items.E_738_L, 1, 4, 16, 5)}, (Object)3, (Object)new v_4262_N[]{new s_956_w(Items.B_1335_M, 3, 18, 10), new J_1907_R(a_3742_W.E_3343_g, 4, 12, 20)}, (Object)4, (Object)new v_4262_N[]{new s_956_w(a_3742_W.a_178_J, 1, 1, 12, 15), new u_2550_I(MobEffects.M_182_A, 100, 15), new u_2550_I(MobEffects.w_1484_f, 160, 15), new u_2550_I(MobEffects.multiplayerClientSuggestionProvider, 140, 15), new u_2550_I(MobEffects.Q_4569_t, 120, 15), new u_2550_I(MobEffects.w_1457_N, 280, 15), new u_2550_I(MobEffects.C_2741_M, 7, 15)}, (Object)5, (Object)new v_4262_N[]{new s_956_w(Items.DoublePlantBlock, 3, 3, 30), new s_956_w(Items.e_1503_j, 4, 3, 30)})));
        p_221237_0_.put(VillagerProfession.v_4262_N, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.Animation, 20, 16, 2), new J_1907_R(Items.T_797_O, 10, 16, 2), new t_148_a(Items.ServerAdvancementManager, 6, Items.U_3554_Q, 6, 16, 1), new s_956_w(Items.W_3801_h, 3, 1, 16, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.ServerAdvancementManager, 15, 16, 10), new t_148_a(Items.C_3304_p, 6, Items.T_4001_f, 6, 16, 5), new s_956_w(Items.z_2909_G, 2, 1, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.C_3304_p, 13, 16, 20), new u_1723_Y(Items.w_2223_C, 3, 3, 10, 0.2f)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.J_1008_m, 6, 12, 30)}, (Object)5, (Object)new v_4262_N[]{new J_1907_R(Items.U_1258_d, 4, 12, 30), new G_564_y(1, 12, 30, (Map<R_3043_n, q_1613_l>)ImmutableMap.builder().put((Object)R_3043_n.R_4764_Y, (Object)Items.m_1628_s).put((Object)R_3043_n.v_4262_N, (Object)Items.ObserverBlock).put((Object)R_3043_n.P_1922_E, (Object)Items.ObserverBlock).put((Object)R_3043_n.n_1700_B, (Object)Items.IronBarsBlock).put((Object)R_3043_n.J_1907_R, (Object)Items.IronBarsBlock).put((Object)R_3043_n.G_564_y, (Object)Items.h_4152_b).put((Object)R_3043_n.u_1723_Y, (Object)Items.M_1398_d).build())})));
        p_221237_0_.put(VillagerProfession.P_4830_p, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(a_3742_W.R_3077_Z, 18, 16, 2), new J_1907_R(a_3742_W.RealmsLongConfirmationScreen, 18, 16, 2), new J_1907_R(a_3742_W.RealmsParentalConsentScreen, 18, 16, 2), new J_1907_R(a_3742_W.RealmsConfirmScreen, 18, 16, 2), new s_956_w(Items.LightPredicate, 2, 1, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.ServerFunctionManager, 12, 16, 10), new J_1907_R(Items.D_4237_z, 12, 16, 10), new J_1907_R(Items.K_4866_h, 12, 16, 10), new J_1907_R(Items.RequirementsStrategy, 12, 16, 10), new J_1907_R(Items.SimpleCriterionTrigger, 12, 16, 10), new s_956_w(a_3742_W.R_3077_Z, 1, 1, 16, 5), new s_956_w(a_3742_W.RealmsScreenWithCallback, 1, 1, 16, 5), new s_956_w(a_3742_W.M_2677_i, 1, 1, 16, 5), new s_956_w(a_3742_W.c_132_F, 1, 1, 16, 5), new s_956_w(a_3742_W.g_4106_L, 1, 1, 16, 5), new s_956_w(a_3742_W.RealmsClientOutdatedScreen, 1, 1, 16, 5), new s_956_w(a_3742_W.W_3464_O, 1, 1, 16, 5), new s_956_w(a_3742_W.RealmsConfirmScreen, 1, 1, 16, 5), new s_956_w(a_3742_W.RealmsCreateRealmScreen, 1, 1, 16, 5), new s_956_w(a_3742_W.C_290_v, 1, 1, 16, 5), new s_956_w(a_3742_W.w_728_N, 1, 1, 16, 5), new s_956_w(a_3742_W.J_4256_G, 1, 1, 16, 5), new s_956_w(a_3742_W.RealmsLongConfirmationScreen, 1, 1, 16, 5), new s_956_w(a_3742_W.RealmsLongRunningMcoTaskScreen, 1, 1, 16, 5), new s_956_w(a_3742_W.i_2993_w, 1, 1, 16, 5), new s_956_w(a_3742_W.RealmsParentalConsentScreen, 1, 1, 16, 5), new s_956_w(a_3742_W.AuctionHelper, 1, 4, 16, 5), new s_956_w(a_3742_W.AutoAccept, 1, 4, 16, 5), new s_956_w(a_3742_W.AutoContract, 1, 4, 16, 5), new s_956_w(a_3742_W.AutoDuel, 1, 4, 16, 5), new s_956_w(a_3742_W.BedrockProxy, 1, 4, 16, 5), new s_956_w(a_3742_W.BetterMinecraft, 1, 4, 16, 5), new s_956_w(a_3742_W.BotAutoCollector, 1, 4, 16, 5), new s_956_w(a_3742_W.Bots, 1, 4, 16, 5), new s_956_w(a_3742_W.ClickFriend, 1, 4, 16, 5), new s_956_w(a_3742_W.ClientSpoof, 1, 4, 16, 5), new s_956_w(a_3742_W.DeathCoords, 1, 4, 16, 5), new s_956_w(a_3742_W.DiscordRPC, 1, 4, 16, 5), new s_956_w(a_3742_W.EcSaver, 1, 4, 16, 5), new s_956_w(a_3742_W.ElytraHelper, 1, 4, 16, 5), new s_956_w(a_3742_W.FlagDetector, 1, 4, 16, 5), new s_956_w(a_3742_W.Globals, 1, 4, 16, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.S_4998_h, 12, 16, 20), new J_1907_R(Items.d_3769_f, 12, 16, 20), new J_1907_R(Items.h_1723_G, 12, 16, 20), new J_1907_R(Items.P_1965_C, 12, 16, 20), new J_1907_R(Items.T_2391_T, 12, 16, 20), new s_956_w(a_3742_W.V_1225_t, 3, 1, 12, 10), new s_956_w(a_3742_W.w_612_n, 3, 1, 12, 10), new s_956_w(a_3742_W.F_1410_V, 3, 1, 12, 10), new s_956_w(a_3742_W.S_4022_R, 3, 1, 12, 10), new s_956_w(a_3742_W.H_1083_k, 3, 1, 12, 10), new s_956_w(a_3742_W.R_3908_n, 3, 1, 12, 10), new s_956_w(a_3742_W.RealmsWorldResetDto, 3, 1, 12, 10), new s_956_w(a_3742_W.M_1641_O, 3, 1, 12, 10), new s_956_w(a_3742_W.ValueObject, 3, 1, 12, 10), new s_956_w(a_3742_W.dtoRealmsServerAddress, 3, 1, 12, 10), new s_956_w(a_3742_W.RealmsWorldOptions, 3, 1, 12, 10), new s_956_w(a_3742_W.RealmsServerPing, 3, 1, 12, 10), new s_956_w(a_3742_W.q_1982_R, 3, 1, 12, 10), new s_956_w(a_3742_W.U_1241_n, 3, 1, 12, 10), new s_956_w(a_3742_W.j_1564_a, 3, 1, 12, 10), new s_956_w(a_3742_W.RegionPingResult, 3, 1, 12, 10)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.q_608_V, 12, 16, 30), new J_1907_R(Items.z_936_s, 12, 16, 30), new J_1907_R(Items.I_4421_I, 12, 16, 30), new J_1907_R(Items.Z_2021_u, 12, 16, 30), new J_1907_R(Items.CriterionTrigger, 12, 16, 30), new J_1907_R(Items.n_4560_z, 12, 16, 30), new s_956_w(Items.o_3946_o, 3, 1, 12, 15), new s_956_w(Items.R_2215_C, 3, 1, 12, 15), new s_956_w(Items.k_2789_z, 3, 1, 12, 15), new s_956_w(Items.K_3256_W, 3, 1, 12, 15), new s_956_w(Items.JigsawBlock, 3, 1, 12, 15), new s_956_w(Items.h_355_y, 3, 1, 12, 15), new s_956_w(Items.IceBlock, 3, 1, 12, 15), new s_956_w(Items.JukeboxBlock, 3, 1, 12, 15), new s_956_w(Items.WaterlilyBlock, 3, 1, 12, 15), new s_956_w(Items.C_1985_D, 3, 1, 12, 15), new s_956_w(Items.LiquidBlockContainer, 3, 1, 12, 15), new s_956_w(Items.V_1395_p, 3, 1, 12, 15), new s_956_w(Items.LeavesBlock, 3, 1, 12, 15), new s_956_w(Items.SimpleWaterloggedBlock, 3, 1, 12, 15), new s_956_w(Items.BonemealableBlock, 3, 1, 12, 15), new s_956_w(Items.KelpPlantBlock, 3, 1, 12, 15)}, (Object)5, (Object)new v_4262_N[]{new s_956_w(Items.q_3115_L, 2, 3, 30)})));
        p_221237_0_.put(VillagerProfession.w_1484_f, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.A_4514_U, 32, 16, 2), new s_956_w(Items.g_24_p, 1, 16, 1), new t_148_a(a_3742_W.t_4043_B, 10, Items.W_1488_x, 10, 12, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.W_1488_x, 26, 12, 10), new s_956_w(Items.R_1796_s, 2, 1, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.Animation, 14, 16, 20), new s_956_w(Items.V_2454_J, 3, 1, 10)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.H_274_C, 24, 16, 30), new u_1723_Y(Items.R_1796_s, 2, 3, 15)}, (Object)5, (Object)new v_4262_N[]{new J_1907_R(Items.k_578_l, 8, 12, 30), new u_1723_Y(Items.V_2454_J, 3, 3, 15), new w_1484_f(Items.g_24_p, 5, Items.NetherWartBlock, 5, 2, 12, 30)})));
        p_221237_0_.put(VillagerProfession.s_956_w, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.builder().put((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.l_3370_o, 24, 16, 2), new P_1922_E(1), new s_956_w(a_3742_W.UploadTokenCache, 9, 1, 12, 1)}).put((Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.K_4237_u, 4, 12, 10), new P_1922_E(5), new s_956_w(Items.SpreadingSnowyDirtBlock, 1, 1, 5)}).put((Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.B_3068_A, 5, 12, 20), new P_1922_E(10), new s_956_w(Items.q_1982_R, 1, 4, 10)}).put((Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.CropBlock, 2, 12, 30), new P_1922_E(15), new s_956_w(Items.A_2629_w, 5, 1, 15), new s_956_w(Items.X_1303_p, 4, 1, 15)}).put((Object)5, (Object)new v_4262_N[]{new s_956_w(Items.HorizontalDirectionalBlock, 20, 1, 30)}).build()));
        p_221237_0_.put(VillagerProfession.G_564_y, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.l_3370_o, 24, 16, 2), new s_956_w(Items.S_1431_H, 7, 1, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.U_4087_m, 11, 16, 10), new R_4764_Y(13, StructureFeature.M_588_G, J_2020_G.n_1700_B.s_956_w, 12, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.X_1303_p, 1, 12, 20), new R_4764_Y(14, StructureFeature.G_564_y, J_2020_G.n_1700_B.t_148_a, 12, 10)}, (Object)4, (Object)new v_4262_N[]{new s_956_w(Items.DeadBushBlock, 7, 1, 15), new s_956_w(Items.o_3946_o, 3, 1, 15), new s_956_w(Items.R_2215_C, 3, 1, 15), new s_956_w(Items.k_2789_z, 3, 1, 15), new s_956_w(Items.K_3256_W, 3, 1, 15), new s_956_w(Items.JigsawBlock, 3, 1, 15), new s_956_w(Items.h_355_y, 3, 1, 15), new s_956_w(Items.IceBlock, 3, 1, 15), new s_956_w(Items.JukeboxBlock, 3, 1, 15), new s_956_w(Items.WaterlilyBlock, 3, 1, 15), new s_956_w(Items.C_1985_D, 3, 1, 15), new s_956_w(Items.LiquidBlockContainer, 3, 1, 15), new s_956_w(Items.V_1395_p, 3, 1, 15), new s_956_w(Items.LeavesBlock, 3, 1, 15), new s_956_w(Items.SimpleWaterloggedBlock, 3, 1, 15), new s_956_w(Items.BonemealableBlock, 3, 1, 15), new s_956_w(Items.KelpPlantBlock, 3, 1, 15)}, (Object)5, (Object)new v_4262_N[]{new s_956_w(Items.PlayerHeadBlock, 8, 1, 30)})));
        p_221237_0_.put(VillagerProfession.P_1922_E, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.m_1964_F, 32, 16, 2), new s_956_w(Items.v_570_f, 1, 2, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.ServerHandshakePacketListener, 3, 12, 10), new s_956_w(Items.W_4813_f, 1, 1, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.GlazedTerracottaBlock, 2, 12, 20), new s_956_w(a_3742_W.X_2960_b, 4, 1, 12, 10)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.o_977_F, 4, 12, 30), new J_1907_R(Items.Y_3588_g, 9, 12, 30), new s_956_w(Items.v_2746_S, 5, 1, 15)}, (Object)5, (Object)new v_4262_N[]{new J_1907_R(Items.g_1096_r, 22, 12, 30), new s_956_w(Items.s_3084_y, 3, 1, 30)})));
        p_221237_0_.put(VillagerProfession.J_1907_R, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.T_797_O, 15, 16, 2), new s_956_w(new Z_1993_T(Items.U_2474_c), 7, 1, 12, 1, 0.2f), new s_956_w(new Z_1993_T(Items.j_2302_z), 4, 1, 12, 1, 0.2f), new s_956_w(new Z_1993_T(Items.T_1170_t), 5, 1, 12, 1, 0.2f), new s_956_w(new Z_1993_T(Items.k_2282_P), 9, 1, 12, 1, 0.2f)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.D_1621_L, 4, 12, 10), new s_956_w(new Z_1993_T(Items.SpongeBlock), 36, 1, 12, 5, 0.2f), new s_956_w(new Z_1993_T(Items.F_747_P), 1, 1, 12, 5, 0.2f), new s_956_w(new Z_1993_T(Items.R_2329_T), 3, 1, 12, 5, 0.2f)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.u_1934_K, 1, 12, 20), new J_1907_R(Items.k_2273_q, 1, 12, 20), new s_956_w(new Z_1993_T(Items.S_4325_V), 1, 1, 12, 10, 0.2f), new s_956_w(new Z_1993_T(Items.f_800_j), 4, 1, 12, 10, 0.2f), new s_956_w(new Z_1993_T(Items.NoteBlock), 5, 1, 12, 10, 0.2f)}, (Object)4, (Object)new v_4262_N[]{new u_1723_Y(Items.A_1603_w, 14, 3, 15, 0.2f), new u_1723_Y(Items.V_4557_X, 8, 3, 15, 0.2f)}, (Object)5, (Object)new v_4262_N[]{new u_1723_Y(Items.q_4361_M, 8, 3, 30, 0.2f), new u_1723_Y(Items.f_508_U, 16, 3, 30, 0.2f)})));
        p_221237_0_.put(VillagerProfession.Q_4569_t, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.T_797_O, 15, 16, 2), new s_956_w(new Z_1993_T(Items.E_390_U), 3, 1, 12, 1, 0.2f), new u_1723_Y(Items.w_2152_d, 2, 3, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.D_1621_L, 4, 12, 10), new s_956_w(new Z_1993_T(Items.SpongeBlock), 36, 1, 12, 5, 0.2f)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.W_1488_x, 24, 12, 20)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.k_2273_q, 1, 12, 30), new u_1723_Y(Items.M_2029_A, 12, 3, 15, 0.2f)}, (Object)5, (Object)new v_4262_N[]{new u_1723_Y(Items.N_2592_G, 8, 3, 30, 0.2f)})));
        p_221237_0_.put(VillagerProfession.h_1847_R, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.T_797_O, 15, 16, 2), new s_956_w(new Z_1993_T(Items.z_283_n), 1, 1, 12, 1, 0.2f), new s_956_w(new Z_1993_T(Items.z_4066_l), 1, 1, 12, 1, 0.2f), new s_956_w(new Z_1993_T(Items.Y_4293_u), 1, 1, 12, 1, 0.2f), new s_956_w(new Z_1993_T(Items.a_1887_j), 1, 1, 12, 1, 0.2f)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.D_1621_L, 4, 12, 10), new s_956_w(new Z_1993_T(Items.SpongeBlock), 36, 1, 12, 5, 0.2f)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.W_1488_x, 30, 12, 20), new u_1723_Y(Items.E_390_U, 1, 3, 10, 0.2f), new u_1723_Y(Items.Z_1243_X, 2, 3, 10, 0.2f), new u_1723_Y(Items.r_976_u, 3, 3, 10, 0.2f), new s_956_w(new Z_1993_T(Items.q_3148_R), 4, 1, 3, 10, 0.2f)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.k_2273_q, 1, 12, 30), new u_1723_Y(Items.M_2029_A, 12, 3, 15, 0.2f), new u_1723_Y(Items.s_1124_y, 5, 3, 15, 0.2f)}, (Object)5, (Object)new v_4262_N[]{new u_1723_Y(Items.C_1577_A, 13, 3, 30, 0.2f)})));
        p_221237_0_.put(VillagerProfession.R_4764_Y, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.w_2892_f, 14, 16, 2), new J_1907_R(Items.j_1654_T, 7, 16, 2), new J_1907_R(Items.FungusBlock, 4, 16, 2), new s_956_w(Items.N_3347_G, 1, 1, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.T_797_O, 15, 16, 2), new s_956_w(Items.l_3729_r, 1, 5, 16, 5), new s_956_w(Items.m_3052_r, 1, 8, 16, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.s_3698_N, 7, 16, 20), new J_1907_R(Items.h_2396_v, 10, 16, 20)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.f_4705_f, 10, 12, 30)}, (Object)5, (Object)new v_4262_N[]{new J_1907_R(Items.D_265_n, 10, 12, 30)})));
        p_221237_0_.put(VillagerProfession.t_148_a, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.y_254_d, 6, 16, 2), new n_1700_B(Items.z_2759_Q, 3), new n_1700_B(Items.r_2090_h, 7)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(Items.W_1488_x, 26, 12, 10), new n_1700_B(Items.t_1509_b, 5, 12, 5), new n_1700_B(Items.a_1344_X, 4, 12, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(Items.GrassBlock, 9, 12, 20), new n_1700_B(Items.r_2090_h, 7)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.o_977_F, 4, 12, 30), new n_1700_B(Items.HoneyBlock, 6, 12, 15)}, (Object)5, (Object)new v_4262_N[]{new s_956_w(new Z_1993_T(Items.Z_361_l), 6, 1, 12, 30, 0.2f), new n_1700_B(Items.t_1509_b, 5, 12, 30)})));
        p_221237_0_.put(VillagerProfession.u_2550_I, VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new J_1907_R(Items.i_4833_u, 10, 16, 2), new s_956_w(Items.F_489_x, 1, 10, 16, 1)}, (Object)2, (Object)new v_4262_N[]{new J_1907_R(a_3742_W.J_1907_R, 20, 16, 10), new s_956_w(a_3742_W.I_3457_f, 1, 4, 16, 5)}, (Object)3, (Object)new v_4262_N[]{new J_1907_R(a_3742_W.R_4764_Y, 16, 16, 20), new J_1907_R(a_3742_W.v_4262_N, 16, 16, 20), new J_1907_R(a_3742_W.P_1922_E, 16, 16, 20), new s_956_w(a_3742_W.w_1484_f, 1, 4, 16, 10), new s_956_w(a_3742_W.u_1723_Y, 1, 4, 16, 10), new s_956_w(a_3742_W.G_564_y, 1, 4, 16, 10)}, (Object)4, (Object)new v_4262_N[]{new J_1907_R(Items.FlowerBlock, 12, 12, 30), new s_956_w(a_3742_W.h_3858_e, 1, 1, 12, 15), new s_956_w(a_3742_W.I_2209_R, 1, 1, 12, 15), new s_956_w(a_3742_W.AutoExplosion, 1, 1, 12, 15), new s_956_w(a_3742_W.t_4433_T, 1, 1, 12, 15), new s_956_w(a_3742_W.s_4447_V, 1, 1, 12, 15), new s_956_w(a_3742_W.AttackAura, 1, 1, 12, 15), new s_956_w(a_3742_W.s_4054_j, 1, 1, 12, 15), new s_956_w(a_3742_W.AutoTrap, 1, 1, 12, 15), new s_956_w(a_3742_W.AntiSurround, 1, 1, 12, 15), new s_956_w(a_3742_W.l_4397_i, 1, 1, 12, 15), new s_956_w(a_3742_W.AntiBot, 1, 1, 12, 15), new s_956_w(a_3742_W.AutoTotem, 1, 1, 12, 15), new s_956_w(a_3742_W.AutoAnchor, 1, 1, 12, 15), new s_956_w(a_3742_W.AutoCrystal, 1, 1, 12, 15), new s_956_w(a_3742_W.AimAssist, 1, 1, 12, 15), new s_956_w(a_3742_W.AutoSwap, 1, 1, 12, 15), new s_956_w(a_3742_W.FireworkESP, 1, 1, 12, 15), new s_956_w(a_3742_W.ExtendedTab, 1, 1, 12, 15), new s_956_w(a_3742_W.LogoutSpots, 1, 1, 12, 15), new s_956_w(a_3742_W.Glint, 1, 1, 12, 15), new s_956_w(a_3742_W.ItemPhysics, 1, 1, 12, 15), new s_956_w(a_3742_W.ItemRadius, 1, 1, 12, 15), new s_956_w(a_3742_W.Particles, 1, 1, 12, 15), new s_956_w(a_3742_W.ObjectInfo, 1, 1, 12, 15), new s_956_w(a_3742_W.Interface, 1, 1, 12, 15), new s_956_w(a_3742_W.FullBright, 1, 1, 12, 15), new s_956_w(a_3742_W.HitEffect, 1, 1, 12, 15), new s_956_w(a_3742_W.R_4688_l, 1, 1, 12, 15), new s_956_w(a_3742_W.JumpCircle, 1, 1, 12, 15), new s_956_w(a_3742_W.KillEffect, 1, 1, 12, 15), new s_956_w(a_3742_W.f_2247_K, 1, 1, 12, 15), new s_956_w(a_3742_W.w_2099_r, 1, 1, 12, 15)}, (Object)5, (Object)new v_4262_N[]{new s_956_w(a_3742_W.f_887_Z, 1, 1, 12, 30), new s_956_w(a_3742_W.P_3676_m, 1, 1, 12, 30)})));
    });
    public static final Int2ObjectMap<v_4262_N[]> J_1907_R = VillagerTrades.n_1700_B((ImmutableMap<Integer, v_4262_N[]>)ImmutableMap.of((Object)1, (Object)new v_4262_N[]{new s_956_w(Items.RealmsDefaultUncaughtExceptionHandler, 2, 1, 5, 1), new s_956_w(Items.Z_3822_q, 4, 1, 5, 1), new s_956_w(Items.Q_2753_H, 2, 1, 5, 1), new s_956_w(Items.SandBlock, 5, 1, 5, 1), new s_956_w(Items.S_4022_R, 1, 1, 12, 1), new s_956_w(Items.RealmsPersistence, 1, 1, 8, 1), new s_956_w(Items.m_891_U, 1, 1, 4, 1), new s_956_w(Items.y_2772_m, 3, 1, 12, 1), new s_956_w(Items.T_437_o, 3, 1, 8, 1), new s_956_w(Items.C_290_v, 1, 1, 12, 1), new s_956_w(Items.w_728_N, 1, 1, 12, 1), new s_956_w(Items.J_4256_G, 1, 1, 8, 1), new s_956_w(Items.RealmsLongConfirmationScreen, 1, 1, 12, 1), new s_956_w(Items.RealmsLongRunningMcoTaskScreen, 1, 1, 12, 1), new s_956_w(Items.i_2993_w, 1, 1, 12, 1), new s_956_w(Items.RealmsParentalConsentScreen, 1, 1, 12, 1), new s_956_w(Items.O_2151_c, 1, 1, 12, 1), new s_956_w(Items.s_1671_u, 1, 1, 12, 1), new s_956_w(Items.RealmsResetNormalWorldScreen, 1, 1, 12, 1), new s_956_w(Items.C_3538_G, 1, 1, 12, 1), new s_956_w(Items.A_3959_N, 1, 1, 7, 1), new s_956_w(Items.G_4691_Q, 1, 1, 12, 1), new s_956_w(Items.MushroomBlock, 1, 1, 12, 1), new s_956_w(Items.WrappedMinMaxBounds, 1, 1, 12, 1), new s_956_w(Items.y_2836_h, 1, 1, 12, 1), new s_956_w(Items.H_2857_Y, 5, 1, 8, 1), new s_956_w(Items.Z_875_P, 5, 1, 8, 1), new s_956_w(Items.A_4115_X, 5, 1, 8, 1), new s_956_w(Items.c_3005_b, 5, 1, 8, 1), new s_956_w(Items.k_2293_S, 5, 1, 8, 1), new s_956_w(Items.q_2307_F, 5, 1, 8, 1), new s_956_w(Items.P_1965_C, 1, 3, 12, 1), new s_956_w(Items.ServerFunctionManager, 1, 3, 12, 1), new s_956_w(Items.I_4421_I, 1, 3, 12, 1), new s_956_w(Items.T_2391_T, 1, 3, 12, 1), new s_956_w(Items.K_4866_h, 1, 3, 12, 1), new s_956_w(Items.Z_2021_u, 1, 3, 12, 1), new s_956_w(Items.d_3769_f, 1, 3, 12, 1), new s_956_w(Items.CriterionTrigger, 1, 3, 12, 1), new s_956_w(Items.S_4998_h, 1, 3, 12, 1), new s_956_w(Items.D_4237_z, 1, 3, 12, 1), new s_956_w(Items.z_936_s, 1, 3, 12, 1), new s_956_w(Items.RequirementsStrategy, 1, 3, 12, 1), new s_956_w(Items.SimpleCriterionTrigger, 1, 3, 12, 1), new s_956_w(Items.h_1723_G, 1, 3, 12, 1), new s_956_w(Items.q_608_V, 1, 3, 12, 1), new s_956_w(Items.n_4560_z, 1, 3, 12, 1), new s_956_w(Items.LevitationControl, 3, 1, 8, 1), new s_956_w(Items.LockSlot, 3, 1, 8, 1), new s_956_w(Items.NoInteract, 3, 1, 8, 1), new s_956_w(Items.Nuker, 3, 1, 8, 1), new s_956_w(Items.LeaveTracker, 3, 1, 8, 1), new s_956_w(Items.B_1146_q, 1, 1, 12, 1), new s_956_w(Items.RealmsSettingsScreen, 1, 1, 12, 1), new s_956_w(Items.f_1043_S, 1, 1, 12, 1), new s_956_w(Items.l_2995_s, 1, 2, 5, 1), new s_956_w(Items.t_4043_B, 1, 8, 8, 1), new s_956_w(Items.x_607_J, 1, 4, 6, 1)}, (Object)2, (Object)new v_4262_N[]{new s_956_w(Items.v_2826_q, 5, 1, 4, 1), new s_956_w(Items.F_2052_z, 5, 1, 4, 1), new s_956_w(Items.TargetPearl, 3, 1, 6, 1), new s_956_w(Items.FireworkESP, 6, 1, 6, 1), new s_956_w(Items.Easing, 1, 1, 8, 1), new s_956_w(Items.M_588_G, 3, 3, 6, 1)}));

    private static Int2ObjectMap<v_4262_N[]> n_1700_B(ImmutableMap<Integer, v_4262_N[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap(p_221238_0_);
    }

    public static interface v_4262_N {
        @Nullable
        public MerchantOffer n_1700_B(N_4263_v var1, Random var2);
    }

    static class J_1907_R
    implements v_4262_N {
        private final q_1613_l n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final float P_1922_E;

        public J_1907_R(q_1803_e tradeItemIn, int countIn, int maxUsesIn, int xpValueIn) {
            this.n_1700_B = tradeItemIn.u_1723_Y();
            this.J_1907_R = countIn;
            this.R_4764_Y = maxUsesIn;
            this.G_564_y = xpValueIn;
            this.P_1922_E = 0.05f;
        }

        @Override
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            Z_1993_T itemstack = new Z_1993_T(this.n_1700_B, this.J_1907_R);
            return new MerchantOffer(itemstack, new Z_1993_T(Items.Y_2905_A), this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }
    }

    static class s_956_w
    implements v_4262_N {
        private final Z_1993_T n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;
        private final float u_1723_Y;

        public s_956_w(T_2915_h sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new Z_1993_T(sellingItem), emeraldCount, sellingItemCount, maxUses, xpValue);
        }

        public s_956_w(q_1613_l sellingItem, int emeraldCount, int sellingItemCount, int xpValue) {
            this(new Z_1993_T(sellingItem), emeraldCount, sellingItemCount, 12, xpValue);
        }

        public s_956_w(q_1613_l sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new Z_1993_T(sellingItem), emeraldCount, sellingItemCount, maxUses, xpValue);
        }

        public s_956_w(Z_1993_T sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue) {
            this(sellingItem, emeraldCount, sellingItemCount, maxUses, xpValue, 0.05f);
        }

        public s_956_w(Z_1993_T sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
            this.n_1700_B = sellingItem;
            this.J_1907_R = emeraldCount;
            this.R_4764_Y = sellingItemCount;
            this.G_564_y = maxUses;
            this.P_1922_E = xpValue;
            this.u_1723_Y = priceMultiplier;
        }

        @Override
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            return new MerchantOffer(new Z_1993_T(Items.Y_2905_A, this.J_1907_R), new Z_1993_T(this.n_1700_B.J_1907_R(), this.R_4764_Y), this.G_564_y, this.P_1922_E, this.u_1723_Y);
        }
    }

    static class u_2550_I
    implements v_4262_N {
        final g_422_i n_1700_B;
        final int J_1907_R;
        final int R_4764_Y;
        private final float G_564_y;

        public u_2550_I(g_422_i effectIn, int durationIn, int xpValue) {
            this.n_1700_B = effectIn;
            this.J_1907_R = durationIn;
            this.R_4764_Y = xpValue;
            this.G_564_y = 0.05f;
        }

        @Override
        @Nullable
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            Z_1993_T itemstack = new Z_1993_T(Items.Q_2342_H, 1);
            SuspiciousStewItem.n_1700_B(itemstack, this.n_1700_B, this.J_1907_R);
            return new MerchantOffer(new Z_1993_T(Items.Y_2905_A, 1), itemstack, 12, this.R_4764_Y, this.G_564_y);
        }
    }

    static class t_148_a
    implements v_4262_N {
        private final Z_1993_T n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final Z_1993_T G_564_y;
        private final int P_1922_E;
        private final int u_1723_Y;
        private final int v_4262_N;
        private final float w_1484_f;

        public t_148_a(q_1803_e buyingItem, int buyingItemCount, q_1613_l sellingItem, int sellingItemCount, int maxUses, int xpValue) {
            this(buyingItem, buyingItemCount, 1, sellingItem, sellingItemCount, maxUses, xpValue);
        }

        public t_148_a(q_1803_e buyingItem, int buyingItemCount, int emeraldCount, q_1613_l sellingItem, int sellingItemCount, int maxUses, int xpValue) {
            this.n_1700_B = new Z_1993_T(buyingItem);
            this.J_1907_R = buyingItemCount;
            this.R_4764_Y = emeraldCount;
            this.G_564_y = new Z_1993_T(sellingItem);
            this.P_1922_E = sellingItemCount;
            this.u_1723_Y = maxUses;
            this.v_4262_N = xpValue;
            this.w_1484_f = 0.05f;
        }

        @Override
        @Nullable
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            return new MerchantOffer(new Z_1993_T(Items.Y_2905_A, this.R_4764_Y), new Z_1993_T(this.n_1700_B.J_1907_R(), this.J_1907_R), new Z_1993_T(this.G_564_y.J_1907_R(), this.P_1922_E), this.u_1723_Y, this.v_4262_N, this.w_1484_f);
        }
    }

    static class u_1723_Y
    implements v_4262_N {
        private final Z_1993_T n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final float P_1922_E;

        public u_1723_Y(q_1613_l p_i50535_1_, int emeraldCount, int maxUses, int xpValue) {
            this(p_i50535_1_, emeraldCount, maxUses, xpValue, 0.05f);
        }

        public u_1723_Y(q_1613_l sellItem, int emeraldCount, int maxUses, int xpValue, float priceMultiplier) {
            this.n_1700_B = new Z_1993_T(sellItem);
            this.J_1907_R = emeraldCount;
            this.R_4764_Y = maxUses;
            this.G_564_y = xpValue;
            this.P_1922_E = priceMultiplier;
        }

        @Override
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            int i = 5 + rand.nextInt(15);
            Z_1993_T itemstack = K_4096_w.n_1700_B(rand, new Z_1993_T(this.n_1700_B.J_1907_R()), i, false);
            int j = Math.min(this.J_1907_R + i, 64);
            Z_1993_T itemstack1 = new Z_1993_T(Items.Y_2905_A, j);
            return new MerchantOffer(itemstack1, itemstack, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }
    }

    static class G_564_y
    implements v_4262_N {
        private final Map<R_3043_n, q_1613_l> n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        public G_564_y(int count, int maxUsesIn, int xpValueIn, Map<R_3043_n, q_1613_l> villagerTypeItemsIn) {
            V_3137_a.O_508_d.u_1723_Y().filter(villagerType -> !villagerTypeItemsIn.containsKey(villagerType)).findAny().ifPresent(villagerType -> {
                throw new IllegalStateException("Missing trade for villager type: " + String.valueOf(V_3137_a.O_508_d.J_1907_R((R_3043_n)villagerType)));
            });
            this.n_1700_B = villagerTypeItemsIn;
            this.J_1907_R = count;
            this.R_4764_Y = maxUsesIn;
            this.G_564_y = xpValueIn;
        }

        @Override
        @Nullable
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            if (trader instanceof I_2154_Z) {
                Z_1993_T itemstack = new Z_1993_T(this.n_1700_B.get(((I_2154_Z)((Object)trader)).c_2086_l().n_1700_B()), this.J_1907_R);
                return new MerchantOffer(itemstack, new Z_1993_T(Items.Y_2905_A), this.R_4764_Y, this.G_564_y, 0.05f);
            }
            return null;
        }
    }

    static class w_1484_f
    implements v_4262_N {
        private final Z_1993_T n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;
        private final q_1613_l u_1723_Y;
        private final int v_4262_N;
        private final float w_1484_f;

        public w_1484_f(q_1613_l buyingItem, int buyingItemCount, q_1613_l p_i50526_3_, int p_i50526_4_, int emeralds, int maxUses, int xpValue) {
            this.n_1700_B = new Z_1993_T(p_i50526_3_);
            this.R_4764_Y = emeralds;
            this.G_564_y = maxUses;
            this.P_1922_E = xpValue;
            this.u_1723_Y = buyingItem;
            this.v_4262_N = buyingItemCount;
            this.J_1907_R = p_i50526_4_;
            this.w_1484_f = 0.05f;
        }

        @Override
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            Z_1993_T itemstack = new Z_1993_T(Items.Y_2905_A, this.R_4764_Y);
            List list = V_3137_a.B_1668_F.u_1723_Y().filter(potion -> !potion.n_1700_B().isEmpty() && E_414_E.n_1700_B(potion)).collect(Collectors.toList());
            y_528_b potion2 = (y_528_b)list.get(rand.nextInt(list.size()));
            Z_1993_T itemstack1 = L_1875_m.n_1700_B(new Z_1993_T(this.n_1700_B.J_1907_R(), this.J_1907_R), potion2);
            return new MerchantOffer(itemstack, new Z_1993_T(this.u_1723_Y, this.v_4262_N), itemstack1, this.G_564_y, this.P_1922_E, this.w_1484_f);
        }
    }

    static class P_1922_E
    implements v_4262_N {
        private final int n_1700_B;

        public P_1922_E(int xpValueIn) {
            this.n_1700_B = xpValueIn;
        }

        @Override
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            List list = V_3137_a.z_4693_k.u_1723_Y().filter(K_1310_v::w_1484_f).collect(Collectors.toList());
            K_1310_v enchantment = (K_1310_v)list.get(rand.nextInt(list.size()));
            int i = u_530_F.n_1700_B(rand, enchantment.P_1922_E(), enchantment.n_1700_B());
            Z_1993_T itemstack = EnchantedBookItem.n_1700_B(new T_4041_i(enchantment, i));
            int j = 2 + rand.nextInt(5 + i * 10) + 3 * i;
            if (enchantment.J_1907_R()) {
                j *= 2;
            }
            if (j > 64) {
                j = 64;
            }
            return new MerchantOffer(new Z_1993_T(Items.Y_2905_A, j), new Z_1993_T(Items.K_4237_u), itemstack, 12, this.n_1700_B, 0.2f);
        }
    }

    static class R_4764_Y
    implements v_4262_N {
        private final int n_1700_B;
        private final StructureFeature<?> J_1907_R;
        private final J_2020_G.n_1700_B R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;

        public R_4764_Y(int count, StructureFeature<?> structureName, J_2020_G.n_1700_B mapDecorationType, int maxUses, int xpValue) {
            this.n_1700_B = count;
            this.J_1907_R = structureName;
            this.R_4764_Y = mapDecorationType;
            this.G_564_y = maxUses;
            this.P_1922_E = xpValue;
        }

        @Override
        @Nullable
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            if (!(trader.O_508_d instanceof e_3591_l)) {
                return null;
            }
            e_3591_l serverworld = (e_3591_l)trader.O_508_d;
            c_1514_x blockpos = serverworld.n_1700_B(this.J_1907_R, trader.b_2312_j(), 100, true);
            if (blockpos != null) {
                Z_1993_T itemstack = G_3165_y.n_1700_B(serverworld, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                G_3165_y.n_1700_B(serverworld, itemstack);
                F_3620_e.n_1700_B(itemstack, blockpos, "+", this.R_4764_Y);
                itemstack.n_1700_B(new F_2904_S("filled_map." + this.J_1907_R.v_4262_N().toLowerCase(Locale.ROOT)));
                return new MerchantOffer(new Z_1993_T(Items.Y_2905_A, this.n_1700_B), new Z_1993_T(Items.X_1303_p), itemstack, this.G_564_y, this.P_1922_E, 0.2f);
            }
            return null;
        }
    }

    static class n_1700_B
    implements v_4262_N {
        private final q_1613_l n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        public n_1700_B(q_1613_l itemIn, int priceIn) {
            this(itemIn, priceIn, 12, 1);
        }

        public n_1700_B(q_1613_l tradeItemIn, int priceIn, int maxUsesIn, int xpValueIn) {
            this.n_1700_B = tradeItemIn;
            this.J_1907_R = priceIn;
            this.R_4764_Y = maxUsesIn;
            this.G_564_y = xpValueIn;
        }

        @Override
        public MerchantOffer n_1700_B(N_4263_v trader, Random rand) {
            Z_1993_T itemstack = new Z_1993_T(Items.Y_2905_A, this.J_1907_R);
            Z_1993_T itemstack1 = new Z_1993_T(this.n_1700_B);
            if (this.n_1700_B instanceof a_3189_D) {
                ArrayList list = Lists.newArrayList();
                list.add(lightning.product.VillagerTrades$n_1700_B.n_1700_B(rand));
                if (rand.nextFloat() > 0.7f) {
                    list.add(lightning.product.VillagerTrades$n_1700_B.n_1700_B(rand));
                }
                if (rand.nextFloat() > 0.8f) {
                    list.add(lightning.product.VillagerTrades$n_1700_B.n_1700_B(rand));
                }
                itemstack1 = DyeableLeatherItem.n_1700_B(itemstack1, list);
            }
            return new MerchantOffer(itemstack, itemstack1, this.R_4764_Y, this.G_564_y, 0.2f);
        }

        private static DyeItem n_1700_B(Random p_221232_0_) {
            return DyeItem.n_1700_B(e_933_M.n_1700_B(p_221232_0_.nextInt(16)));
        }
    }
}



