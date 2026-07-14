/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.LeavesBlock;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_4440_Q;
import lightning.product.MinecraftClient;
import lightning.product.FluidState;
import lightning.product.j_3341_s;
import lightning.product.o_2576_A;
import lightning.product.q_1613_l;
import lightning.product.Fluid;
import lightning.product.v_1669_V;

public class d_1620_j {
    private static final Map<T_2915_h, o_2576_A> n_1700_B = j_3341_s.n_1700_B(Maps.newHashMap(), p_228395_0_ -> {
        o_2576_A rendertype = o_2576_A.Q_2552_b();
        p_228395_0_.put(a_3742_W.I_3637_j, rendertype);
        o_2576_A rendertype1 = o_2576_A.v_4262_N();
        p_228395_0_.put(a_3742_W.t_148_a, rendertype1);
        p_228395_0_.put(a_3742_W.Z_4720_K, rendertype1);
        p_228395_0_.put(a_3742_W.q_839_y, rendertype1);
        p_228395_0_.put(a_3742_W.d_2169_p, rendertype1);
        p_228395_0_.put(a_3742_W.p_3749_n, rendertype1);
        p_228395_0_.put(a_3742_W.r_4879_Z, rendertype1);
        p_228395_0_.put(a_3742_W.p_178_J, rendertype1);
        p_228395_0_.put(a_3742_W.A_1038_p, rendertype1);
        p_228395_0_.put(a_3742_W.i_1637_u, rendertype1);
        p_228395_0_.put(a_3742_W.RealmsClientConfig, rendertype1);
        p_228395_0_.put(a_3742_W.Ping, rendertype1);
        p_228395_0_.put(a_3742_W.f_4016_n, rendertype1);
        o_2576_A rendertype2 = o_2576_A.w_1484_f();
        p_228395_0_.put(a_3742_W.Y_601_j, rendertype2);
        p_228395_0_.put(a_3742_W.Y_259_p, rendertype2);
        p_228395_0_.put(a_3742_W.Q_2552_b, rendertype2);
        p_228395_0_.put(a_3742_W.C_2741_M, rendertype2);
        p_228395_0_.put(a_3742_W.k_2293_S, rendertype2);
        p_228395_0_.put(a_3742_W.q_2307_F, rendertype2);
        p_228395_0_.put(a_3742_W.e_1992_r, rendertype2);
        p_228395_0_.put(a_3742_W.V_1225_t, rendertype2);
        p_228395_0_.put(a_3742_W.U_1241_n, rendertype2);
        p_228395_0_.put(a_3742_W.q_1982_R, rendertype2);
        p_228395_0_.put(a_3742_W.dtoRealmsServerAddress, rendertype2);
        p_228395_0_.put(a_3742_W.w_612_n, rendertype2);
        p_228395_0_.put(a_3742_W.RealmsServerPing, rendertype2);
        p_228395_0_.put(a_3742_W.j_1564_a, rendertype2);
        p_228395_0_.put(a_3742_W.M_1641_O, rendertype2);
        p_228395_0_.put(a_3742_W.RealmsWorldOptions, rendertype2);
        p_228395_0_.put(a_3742_W.RealmsWorldResetDto, rendertype2);
        p_228395_0_.put(a_3742_W.RegionPingResult, rendertype2);
        p_228395_0_.put(a_3742_W.H_1083_k, rendertype2);
        p_228395_0_.put(a_3742_W.R_3908_n, rendertype2);
        p_228395_0_.put(a_3742_W.ValueObject, rendertype2);
        p_228395_0_.put(a_3742_W.F_1410_V, rendertype2);
        p_228395_0_.put(a_3742_W.S_4022_R, rendertype2);
        p_228395_0_.put(a_3742_W.l_4537_E, rendertype2);
        p_228395_0_.put(a_3742_W.F_2624_D, rendertype2);
        p_228395_0_.put(a_3742_W.y_1700_S, rendertype2);
        p_228395_0_.put(a_3742_W.u_744_e, rendertype2);
        p_228395_0_.put(a_3742_W.RetryCallException, rendertype2);
        p_228395_0_.put(a_3742_W.r_3651_U, rendertype2);
        p_228395_0_.put(a_3742_W.RowButton, rendertype2);
        p_228395_0_.put(a_3742_W.LongRunningTask, rendertype2);
        p_228395_0_.put(a_3742_W.s_1671_u, rendertype2);
        p_228395_0_.put(a_3742_W.RealmsResetNormalWorldScreen, rendertype2);
        p_228395_0_.put(a_3742_W.C_3538_G, rendertype2);
        p_228395_0_.put(a_3742_W.A_3959_N, rendertype2);
        p_228395_0_.put(a_3742_W.G_424_k, rendertype2);
        p_228395_0_.put(a_3742_W.RealmsSettingsScreen, rendertype2);
        p_228395_0_.put(a_3742_W.f_1043_S, rendertype2);
        p_228395_0_.put(a_3742_W.F_4247_a, rendertype2);
        p_228395_0_.put(a_3742_W.J_739_q, rendertype2);
        p_228395_0_.put(a_3742_W.C_1162_e, rendertype2);
        p_228395_0_.put(a_3742_W.D_4361_a, rendertype2);
        p_228395_0_.put(a_3742_W.f_3449_S, rendertype2);
        p_228395_0_.put(a_3742_W.u_55_V, rendertype2);
        p_228395_0_.put(a_3742_W.JsonUtils, rendertype2);
        p_228395_0_.put(a_3742_W.RealmsPersistence, rendertype2);
        p_228395_0_.put(a_3742_W.o_2341_D, rendertype2);
        p_228395_0_.put(a_3742_W.C_1269_X, rendertype2);
        p_228395_0_.put(a_3742_W.I_4348_c, rendertype2);
        p_228395_0_.put(a_3742_W.O_3598_v, rendertype2);
        p_228395_0_.put(a_3742_W.x_612_B, rendertype2);
        p_228395_0_.put(a_3742_W.t_1446_I, rendertype2);
        p_228395_0_.put(a_3742_W.j_306_t, rendertype2);
        p_228395_0_.put(a_3742_W.P_5000_x, rendertype2);
        p_228395_0_.put(a_3742_W.l_4088_R, rendertype2);
        p_228395_0_.put(a_3742_W.F_518_D, rendertype2);
        p_228395_0_.put(a_3742_W.L_3570_A, rendertype2);
        p_228395_0_.put(a_3742_W.Y_776_s, rendertype2);
        p_228395_0_.put(a_3742_W.h_2739_B, rendertype2);
        p_228395_0_.put(a_3742_W.H_1873_g, rendertype2);
        p_228395_0_.put(a_3742_W.n_3864_h, rendertype2);
        p_228395_0_.put(a_3742_W.d_3244_b, rendertype2);
        p_228395_0_.put(a_3742_W.l_3609_d, rendertype2);
        p_228395_0_.put(a_3742_W.T_437_o, rendertype2);
        p_228395_0_.put(a_3742_W.q_817_e, rendertype2);
        p_228395_0_.put(a_3742_W.r_260_T, rendertype2);
        p_228395_0_.put(a_3742_W.Q_2753_H, rendertype2);
        p_228395_0_.put(a_3742_W.Y_2080_q, rendertype2);
        p_228395_0_.put(a_3742_W.g_4560_H, rendertype2);
        p_228395_0_.put(a_3742_W.z_2025_Z, rendertype2);
        p_228395_0_.put(a_3742_W.r_1970_q, rendertype2);
        p_228395_0_.put(a_3742_W.DamageSourcePredicate, rendertype2);
        p_228395_0_.put(a_3742_W.i_789_Q, rendertype2);
        p_228395_0_.put(a_3742_W.J_2061_p, rendertype2);
        p_228395_0_.put(a_3742_W.L_1733_J, rendertype2);
        p_228395_0_.put(a_3742_W.n_4539_g, rendertype2);
        p_228395_0_.put(a_3742_W.U_4087_m, rendertype2);
        p_228395_0_.put(a_3742_W.S_4035_N, rendertype2);
        p_228395_0_.put(a_3742_W.W_3729_Q, rendertype2);
        p_228395_0_.put(a_3742_W.e_837_t, rendertype2);
        p_228395_0_.put(a_3742_W.U_3823_u, rendertype2);
        p_228395_0_.put(a_3742_W.k_578_l, rendertype2);
        p_228395_0_.put(a_3742_W.r_4790_y, rendertype2);
        p_228395_0_.put(a_3742_W.x_2635_q, rendertype2);
        p_228395_0_.put(a_3742_W.f_2403_E, rendertype2);
        p_228395_0_.put(a_3742_W.c_776_E, rendertype2);
        p_228395_0_.put(a_3742_W.z_2372_L, rendertype2);
        p_228395_0_.put(a_3742_W.t_2932_z, rendertype2);
        p_228395_0_.put(a_3742_W.m_3147_m, rendertype2);
        p_228395_0_.put(a_3742_W.I_1790_n, rendertype2);
        p_228395_0_.put(a_3742_W.C_332_W, rendertype2);
        p_228395_0_.put(a_3742_W.L_3537_K, rendertype2);
        p_228395_0_.put(a_3742_W.z_3000_g, rendertype2);
        p_228395_0_.put(a_3742_W.n_4915_F, rendertype2);
        p_228395_0_.put(a_3742_W.y_2622_c, rendertype2);
        p_228395_0_.put(a_3742_W.n_473_l, rendertype2);
        p_228395_0_.put(a_3742_W.r_4414_L, rendertype2);
        p_228395_0_.put(a_3742_W.P_2272_O, rendertype2);
        p_228395_0_.put(a_3742_W.S_2828_i, rendertype2);
        p_228395_0_.put(a_3742_W.y_4642_Y, rendertype2);
        p_228395_0_.put(a_3742_W.h_1640_b, rendertype2);
        p_228395_0_.put(a_3742_W.V_1176_p, rendertype2);
        p_228395_0_.put(a_3742_W.y_2447_C, rendertype2);
        p_228395_0_.put(a_3742_W.J_3635_s, rendertype2);
        p_228395_0_.put(a_3742_W.o_82_k, rendertype2);
        p_228395_0_.put(a_3742_W.h_973_D, rendertype2);
        p_228395_0_.put(a_3742_W.f_2787_O, rendertype2);
        p_228395_0_.put(a_3742_W.P_2295_B, rendertype2);
        p_228395_0_.put(a_3742_W.U_1697_c, rendertype2);
        p_228395_0_.put(a_3742_W.N_4006_T, rendertype2);
        p_228395_0_.put(a_3742_W.H_1475_K, rendertype2);
        p_228395_0_.put(a_3742_W.q_2475_j, rendertype2);
        p_228395_0_.put(a_3742_W.V_983_n, rendertype2);
        p_228395_0_.put(a_3742_W.X_812_G, rendertype2);
        p_228395_0_.put(a_3742_W.NameProtect, rendertype2);
        p_228395_0_.put(a_3742_W.OpenWalls, rendertype2);
        p_228395_0_.put(a_3742_W.Party, rendertype2);
        p_228395_0_.put(a_3742_W.PotionTracker, rendertype2);
        p_228395_0_.put(a_3742_W.AutoPotion, rendertype2);
        p_228395_0_.put(a_3742_W.AutoRespawn, rendertype2);
        p_228395_0_.put(a_3742_W.AutoSoup, rendertype2);
        p_228395_0_.put(a_3742_W.AutoTool, rendertype2);
        p_228395_0_.put(a_3742_W.AutoTrade, rendertype2);
        p_228395_0_.put(a_3742_W.BaritoneSettings, rendertype2);
        p_228395_0_.put(a_3742_W.ChestStealer, rendertype2);
        p_228395_0_.put(a_3742_W.ChorusExploit, rendertype2);
        p_228395_0_.put(a_3742_W.FreeCam, rendertype2);
        p_228395_0_.put(a_3742_W.R_1796_s, rendertype2);
        p_228395_0_.put(a_3742_W.g_24_p, rendertype2);
        p_228395_0_.put(a_3742_W.d_560_A, rendertype2);
        p_228395_0_.put(a_3742_W.Y_3623_f, rendertype2);
        p_228395_0_.put(a_3742_W.z_4066_l, rendertype2);
        p_228395_0_.put(a_3742_W.Y_4293_u, rendertype2);
        p_228395_0_.put(a_3742_W.z_283_n, rendertype2);
        p_228395_0_.put(a_3742_W.a_1887_j, rendertype2);
        p_228395_0_.put(a_3742_W.n_2412_y, rendertype2);
        p_228395_0_.put(a_3742_W.W_2756_H, rendertype2);
        p_228395_0_.put(a_3742_W.i_1894_C, rendertype2);
        p_228395_0_.put(a_3742_W.u_4724_w, rendertype2);
        p_228395_0_.put(a_3742_W.H_1952_g, rendertype2);
        p_228395_0_.put(a_3742_W.w_2152_d, rendertype2);
        p_228395_0_.put(a_3742_W.Z_1243_X, rendertype2);
        p_228395_0_.put(a_3742_W.r_976_u, rendertype2);
        p_228395_0_.put(a_3742_W.E_390_U, rendertype2);
        p_228395_0_.put(a_3742_W.Z_256_c, rendertype2);
        p_228395_0_.put(a_3742_W.N_2592_G, rendertype2);
        p_228395_0_.put(a_3742_W.s_1124_y, rendertype2);
        p_228395_0_.put(a_3742_W.C_1577_A, rendertype2);
        p_228395_0_.put(a_3742_W.M_2029_A, rendertype2);
        p_228395_0_.put(a_3742_W.q_3148_R, rendertype2);
        p_228395_0_.put(a_3742_W.K_1200_E, rendertype2);
        p_228395_0_.put(a_3742_W.D_563_q, rendertype2);
        p_228395_0_.put(a_3742_W.K_1964_I, rendertype2);
        p_228395_0_.put(a_3742_W.O_922_L, rendertype2);
        p_228395_0_.put(a_3742_W.D_940_S, rendertype2);
        p_228395_0_.put(a_3742_W.A_4514_U, rendertype2);
        p_228395_0_.put(a_3742_W.S_4088_D, rendertype2);
        p_228395_0_.put(a_3742_W.MinecraftAccess, rendertype2);
        p_228395_0_.put(a_3742_W.Animation, rendertype2);
        p_228395_0_.put(a_3742_W.H_274_C, rendertype2);
        p_228395_0_.put(a_3742_W.Easing, rendertype2);
        p_228395_0_.put(a_3742_W.V_3441_j, rendertype2);
        p_228395_0_.put(a_3742_W.m_3828_C, rendertype2);
        p_228395_0_.put(a_3742_W.t_1509_b, rendertype2);
        p_228395_0_.put(a_3742_W.r_2090_h, rendertype2);
        p_228395_0_.put(a_3742_W.i_770_g, rendertype2);
        p_228395_0_.put(a_3742_W.f_4705_f, rendertype2);
        p_228395_0_.put(a_3742_W.K_4237_u, rendertype2);
        p_228395_0_.put(a_3742_W.Z_3822_q, rendertype2);
        p_228395_0_.put(a_3742_W.k_1366_K, rendertype2);
        p_228395_0_.put(a_3742_W.y_4842_Z, rendertype2);
        p_228395_0_.put(a_3742_W.s_4405_m, rendertype2);
        p_228395_0_.put(a_3742_W.RequirementsStrategy, rendertype2);
        p_228395_0_.put(a_3742_W.S_4998_h, rendertype2);
        p_228395_0_.put(a_3742_W.SimpleCriterionTrigger, rendertype2);
        p_228395_0_.put(a_3742_W.T_2391_T, rendertype2);
        p_228395_0_.put(a_3742_W.U_3554_Q, rendertype2);
        p_228395_0_.put(a_3742_W.h_1723_G, rendertype2);
        p_228395_0_.put(a_3742_W.C_3304_p, rendertype2);
        p_228395_0_.put(a_3742_W.D_4237_z, rendertype2);
        p_228395_0_.put(a_3742_W.U_1258_d, rendertype2);
        p_228395_0_.put(a_3742_W.y_2836_h, rendertype2);
        p_228395_0_.put(a_3742_W.h_2396_v, rendertype2);
        p_228395_0_.put(a_3742_W.x_2711_Y, rendertype2);
        p_228395_0_.put(a_3742_W.w_2892_f, rendertype2);
        p_228395_0_.put(a_3742_W.D_3640_k, rendertype2);
        p_228395_0_.put(a_3742_W.U_4107_W, rendertype2);
        o_2576_A rendertype3 = o_2576_A.t_148_a();
        p_228395_0_.put(a_3742_W.O_1795_e, rendertype3);
        p_228395_0_.put(a_3742_W.M_766_z, rendertype3);
        p_228395_0_.put(a_3742_W.y_1945_D, rendertype3);
        p_228395_0_.put(a_3742_W.U_532_X, rendertype3);
        p_228395_0_.put(a_3742_W.P_4639_N, rendertype3);
        p_228395_0_.put(a_3742_W.i_4434_b, rendertype3);
        p_228395_0_.put(a_3742_W.P_328_a, rendertype3);
        p_228395_0_.put(a_3742_W.l_4627_h, rendertype3);
        p_228395_0_.put(a_3742_W.K_3372_t, rendertype3);
        p_228395_0_.put(a_3742_W.Q_2467_v, rendertype3);
        p_228395_0_.put(a_3742_W.m_2262_U, rendertype3);
        p_228395_0_.put(a_3742_W.S_4258_d, rendertype3);
        p_228395_0_.put(a_3742_W.m_891_U, rendertype3);
        p_228395_0_.put(a_3742_W.T_2971_J, rendertype3);
        p_228395_0_.put(a_3742_W.Q_3581_n, rendertype3);
        p_228395_0_.put(a_3742_W.I_685_r, rendertype3);
        p_228395_0_.put(a_3742_W.h_3270_j, rendertype3);
        p_228395_0_.put(a_3742_W.M_3508_C, rendertype3);
        p_228395_0_.put(a_3742_W.HoleFill, rendertype3);
        p_228395_0_.put(a_3742_W.KBDisplacement, rendertype3);
        p_228395_0_.put(a_3742_W.NoEntityTrace, rendertype3);
        p_228395_0_.put(a_3742_W.NoFriendDamage, rendertype3);
        p_228395_0_.put(a_3742_W.NoServerDesync, rendertype3);
        p_228395_0_.put(a_3742_W.r_4217_P, rendertype3);
        p_228395_0_.put(a_3742_W.Velocity, rendertype3);
        p_228395_0_.put(a_3742_W.PacketCriticals, rendertype3);
        p_228395_0_.put(a_3742_W.Surround, rendertype3);
        p_228395_0_.put(a_3742_W.TargetPearl, rendertype3);
        p_228395_0_.put(a_3742_W.TargetStrafe, rendertype3);
        p_228395_0_.put(a_3742_W.TriggerBot, rendertype3);
        p_228395_0_.put(a_3742_W.r_4601_j, rendertype3);
        p_228395_0_.put(a_3742_W.O_726_g, rendertype3);
        p_228395_0_.put(a_3742_W.E_2115_e, rendertype3);
        p_228395_0_.put(a_3742_W.W_1707_M, rendertype3);
        p_228395_0_.put(a_3742_W.g_4841_c, rendertype3);
        p_228395_0_.put(a_3742_W.B_1335_M, rendertype3);
        p_228395_0_.put(a_3742_W.LeaveTracker, rendertype3);
        p_228395_0_.put(a_3742_W.S_4325_V, rendertype3);
    });
    private static final Map<Fluid, o_2576_A> J_1907_R = j_3341_s.n_1700_B(Maps.newHashMap(), p_228392_0_ -> {
        o_2576_A rendertype = o_2576_A.t_148_a();
        p_228392_0_.put(Fluids.J_1907_R, rendertype);
        p_228392_0_.put(Fluids.R_4764_Y, rendertype);
    });
    private static boolean R_4764_Y;

    public static o_2576_A n_1700_B(K_4074_S blockStateIn) {
        T_2915_h block = blockStateIn.J_1907_R();
        if (block instanceof LeavesBlock) {
            return R_4764_Y ? o_2576_A.v_4262_N() : o_2576_A.u_1723_Y();
        }
        o_2576_A rendertype = n_1700_B.get(block);
        return rendertype != null ? rendertype : o_2576_A.u_1723_Y();
    }

    public static o_2576_A J_1907_R(K_4074_S p_239221_0_) {
        T_2915_h block = p_239221_0_.J_1907_R();
        if (block instanceof LeavesBlock) {
            return R_4764_Y ? o_2576_A.v_4262_N() : o_2576_A.u_1723_Y();
        }
        o_2576_A rendertype = n_1700_B.get(block);
        if (rendertype != null) {
            return rendertype == o_2576_A.t_148_a() ? o_2576_A.s_956_w() : rendertype;
        }
        return o_2576_A.u_1723_Y();
    }

    public static o_2576_A n_1700_B(K_4074_S p_239220_0_, boolean p_239220_1_) {
        o_2576_A rendertype = d_1620_j.n_1700_B(p_239220_0_);
        if (rendertype == o_2576_A.t_148_a()) {
            if (!MinecraftClient.c_3005_b()) {
                return b_4440_Q.s_956_w();
            }
            return p_239220_1_ ? b_4440_Q.s_956_w() : b_4440_Q.t_148_a();
        }
        return b_4440_Q.w_1484_f();
    }

    public static o_2576_A n_1700_B(Z_1993_T p_239219_0_, boolean p_239219_1_) {
        q_1613_l item = p_239219_0_.J_1907_R();
        if (item instanceof v_1669_V) {
            T_2915_h block = ((v_1669_V)item).v_4262_N();
            return d_1620_j.n_1700_B(block.multiplayerClientSuggestionProvider(), p_239219_1_);
        }
        return p_239219_1_ ? b_4440_Q.s_956_w() : b_4440_Q.t_148_a();
    }

    public static o_2576_A n_1700_B(FluidState fluidStateIn) {
        o_2576_A rendertype = J_1907_R.get(fluidStateIn.n_1700_B());
        return rendertype != null ? rendertype : o_2576_A.u_1723_Y();
    }

    public static void n_1700_B(boolean fancyIn) {
        R_4764_Y = fancyIn;
    }
}



