/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.A_69_b;
import lightning.product.B_4271_P;
import lightning.product.C_4816_K;
import lightning.product.Giant;
import lightning.product.D_2364_U;
import lightning.product.D_3833_N;
import lightning.product.D_4381_C;
import lightning.product.Pillager;
import lightning.product.F_4355_q;
import lightning.product.G_1455_B;
import lightning.product.G_2149_k;
import lightning.product.G_4536_S;
import lightning.product.I_3700_V;
import lightning.product.SharedConstants;
import lightning.product.Squid;
import lightning.product.K_550_M;
import lightning.product.L_2225_p;
import lightning.product.L_3233_K;
import lightning.product.M_2433_H;
import lightning.product.M_914_T;
import lightning.product.N_1077_C;
import lightning.product.monsterSpider;
import lightning.product.Q_3816_H;
import lightning.product.Hoglin;
import lightning.product.R_1299_M;
import lightning.product.S_3014_o;
import lightning.product.S_922_s;
import lightning.product.U_2534_D;
import lightning.product.Ghast;
import lightning.product.V_3137_a;
import lightning.product.AbstractFish;
import lightning.product.CaveSpider;
import lightning.product.W_3443_Y;
import lightning.product.X_1275_n;
import lightning.product.X_4861_v;
import lightning.product.Cow;
import lightning.product.Y_559_r;
import lightning.product.AbstractSkeleton;
import lightning.product.Z_530_i;
import lightning.product.Z_749_F;
import lightning.product.ElderGuardian;
import lightning.product.a_3913_L;
import lightning.product.b_1913_J;
import lightning.product.b_2971_b;
import lightning.product.b_3485_j;
import lightning.product.Bat;
import lightning.product.e_3714_r;
import lightning.product.Silverfish;
import lightning.product.g_1253_u;
import lightning.product.g_4407_j;
import lightning.product.i_1663_p;
import lightning.product.ZombifiedPiglin;
import lightning.product.j_3013_R;
import lightning.product.Monster;
import lightning.product.l_3090_i;
import lightning.product.m_1605_o;
import lightning.product.q_2335_j;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.ZombieHorse;
import lightning.product.w_3611_Y;
import lightning.product.y_2798_W;
import lightning.product.Endermite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class m_3216_j {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Map<t_5_h<? extends r_4811_B>, s_1415_m> J_1907_R = ImmutableMap.builder().put(t_5_h.J_1907_R, (Object)r_4811_B.P_4639_N().n_1700_B()).put(t_5_h.G_564_y, (Object)Bat.u_1723_Y().n_1700_B()).put(t_5_h.P_1922_E, (Object)b_1913_J.o_4117_e().n_1700_B()).put(t_5_h.u_1723_Y, (Object)G_2149_k.u_1723_Y().n_1700_B()).put(t_5_h.w_1484_f, (Object)K_550_M.c_2086_l().n_1700_B()).put(t_5_h.t_148_a, (Object)CaveSpider.u_1723_Y().n_1700_B()).put(t_5_h.s_956_w, (Object)X_4861_v.y_4642_Y().n_1700_B()).put(t_5_h.u_2550_I, (Object)AbstractFish.u_1723_Y().n_1700_B()).put(t_5_h.M_588_G, (Object)Cow.y_4642_Y().n_1700_B()).put(t_5_h.P_4830_p, (Object)b_3485_j.u_1723_Y().n_1700_B()).put(t_5_h.h_1847_R, (Object)Y_559_r.V_1176_p().n_1700_B()).put(t_5_h.Q_4569_t, (Object)W_3443_Y.h_1640_b().n_1700_B()).put(t_5_h.t_1786_h, (Object)F_4355_q.f_2787_O().n_1700_B()).put(t_5_h.multiplayerClientSuggestionProvider, (Object)ElderGuardian.u_1723_Y().n_1700_B()).put(t_5_h.Y_259_p, (Object)M_914_T.y_4642_Y().n_1700_B()).put(t_5_h.Q_2552_b, (Object)Endermite.u_1723_Y().n_1700_B()).put(t_5_h.Y_601_j, (Object)b_2971_b.u_1723_Y().n_1700_B()).put(t_5_h.C_2741_M, (Object)e_3714_r.U_1697_c().n_1700_B()).put(t_5_h.A_4115_X, (Object)g_1253_u.y_4642_Y().n_1700_B()).put(t_5_h.Y_1740_V, (Object)Ghast.Q_4569_t().n_1700_B()).put(t_5_h.t_4043_B, (Object)Giant.u_1723_Y().n_1700_B()).put(t_5_h.x_607_J, (Object)G_1455_B.y_2447_C().n_1700_B()).put(t_5_h.e_4240_b, (Object)Hoglin.y_4642_Y().n_1700_B()).put(t_5_h.n_3318_d, (Object)U_2534_D.BooleanSetting().n_1700_B()).put(t_5_h.d_2427_y, (Object)F_4355_q.f_2787_O().n_1700_B()).put(t_5_h.z_1737_N, (Object)y_2798_W.U_1697_c().n_1700_B()).put(t_5_h.v_4276_D, (Object)D_2364_U.y_4642_Y().n_1700_B()).put(t_5_h.g_221_o, (Object)g_4407_j.p_3749_n().n_1700_B()).put(t_5_h.B_1668_F, (Object)S_922_s.u_1723_Y().n_1700_B()).put(t_5_h.D_4792_h, (Object)Cow.y_4642_Y().n_1700_B()).put(t_5_h.T_3594_S, (Object)W_3443_Y.h_1640_b().n_1700_B()).put(t_5_h.s_2632_s, (Object)l_3090_i.y_4642_Y().n_1700_B()).put(t_5_h.z_1333_t, (Object)j_3013_R.y_3417_N().n_1700_B()).put(t_5_h.O_508_d, (Object)R_1299_M.y_4642_Y().n_1700_B()).put(t_5_h.r_715_M, (Object)Monster.o_4117_e().n_1700_B()).put(t_5_h.A_1038_p, (Object)B_4271_P.y_4642_Y().n_1700_B()).put(t_5_h.i_1637_u, (Object)A_69_b.f_2787_O().n_1700_B()).put(t_5_h.Ping, (Object)S_3014_o.f_2787_O().n_1700_B()).put(t_5_h.p_178_J, (Object)Pillager.U_1697_c().n_1700_B()).put(t_5_h.g_4106_L, (Object)a_3913_L.L_3537_K().n_1700_B()).put(t_5_h.RealmsClientConfig, (Object)Q_3816_H.y_4642_Y().n_1700_B()).put(t_5_h.j_276_v, (Object)AbstractFish.u_1723_Y().n_1700_B()).put(t_5_h.UploadStatus, (Object)M_2433_H.h_1640_b().n_1700_B()).put(t_5_h.e_1992_r, (Object)X_1275_n.u_1723_Y().n_1700_B()).put(t_5_h.D_60_a, (Object)AbstractFish.u_1723_Y().n_1700_B()).put(t_5_h.k_3961_g, (Object)G_4536_S.y_4642_Y().n_1700_B()).put(t_5_h.Ops, (Object)m_1605_o.u_1723_Y().n_1700_B()).put(t_5_h.t_4219_U, (Object)Silverfish.u_1723_Y().n_1700_B()).put(t_5_h.V_1446_Y, (Object)AbstractSkeleton.u_1723_Y().n_1700_B()).put(t_5_h.PlayerInfo, (Object)D_4381_C.h_1640_b().n_1700_B()).put(t_5_h.V_1225_t, (Object)Monster.o_4117_e().n_1700_B()).put(t_5_h.q_1982_R, (Object)N_1077_C.u_1723_Y().n_1700_B()).put(t_5_h.RealmsServerPing, (Object)monsterSpider.y_4642_Y().n_1700_B()).put(t_5_h.j_1564_a, (Object)Squid.u_1723_Y().n_1700_B()).put(t_5_h.M_1641_O, (Object)AbstractSkeleton.u_1723_Y().n_1700_B()).put(t_5_h.RealmsWorldOptions, (Object)L_3233_K.V_1176_p().n_1700_B()).put(t_5_h.F_1410_V, (Object)g_4407_j.p_3749_n().n_1700_B()).put(t_5_h.S_4022_R, (Object)AbstractFish.u_1723_Y().n_1700_B()).put(t_5_h.l_4537_E, (Object)t_4149_i.V_1176_p().n_1700_B()).put(t_5_h.F_2624_D, (Object)D_3833_N.u_1723_Y().n_1700_B()).put(t_5_h.RealmsDefaultUncaughtExceptionHandler, (Object)L_2225_p.h_973_D().n_1700_B()).put(t_5_h.y_1700_S, (Object)i_1663_p.U_1697_c().n_1700_B()).put(t_5_h.u_744_e, (Object)Z_530_i.multiplayerClientSuggestionProvider().n_1700_B()).put(t_5_h.RetryCallException, (Object)w_3611_Y.U_1697_c().n_1700_B()).put(t_5_h.r_3651_U, (Object)I_3700_V.y_4642_Y().n_1700_B()).put(t_5_h.RowButton, (Object)AbstractSkeleton.u_1723_Y().n_1700_B()).put(t_5_h.j_2266_I, (Object)q_2335_j.y_4642_Y().n_1700_B()).put(t_5_h.S_980_j, (Object)C_4816_K.u_1723_Y().n_1700_B()).put(t_5_h.R_3077_Z, (Object)F_4355_q.f_2787_O().n_1700_B()).put(t_5_h.RealmsScreenWithCallback, (Object)ZombieHorse.h_1640_b().n_1700_B()).put(t_5_h.M_2677_i, (Object)F_4355_q.f_2787_O().n_1700_B()).put(t_5_h.c_132_F, (Object)ZombifiedPiglin.c_2086_l().n_1700_B()).build();

    public static s_1415_m n_1700_B(t_5_h<? extends r_4811_B> livingEntity) {
        return J_1907_R.get(livingEntity);
    }

    public static boolean J_1907_R(t_5_h<?> entityType) {
        return J_1907_R.containsKey(entityType);
    }

    public static void n_1700_B() {
        V_3137_a.g_221_o.u_1723_Y().filter(entityType -> entityType.P_1922_E() != Z_749_F.u_1723_Y).filter(entityType -> !m_3216_j.J_1907_R(entityType)).map(V_3137_a.g_221_o::J_1907_R).forEach(entityId -> {
            if (SharedConstants.G_564_y) {
                throw new IllegalStateException("Entity " + String.valueOf(entityId) + " has no attributes");
            }
            n_1700_B.error("Entity {} has no attributes", entityId);
        });
    }
}


