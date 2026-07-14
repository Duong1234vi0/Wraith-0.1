/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.DiggerItem;
import lightning.product.Tier;
import lightning.product.q_1613_l;
import lightning.product.Material;

public class s_2612_h
extends DiggerItem {
    private static final Set<T_2915_h> n_1700_B = ImmutableSet.of((Object)a_3742_W.H_1475_K, (Object)a_3742_W.n_3318_d, (Object)a_3742_W.P_4830_p, (Object)a_3742_W.F_2624_D, (Object)a_3742_W.O_1309_Q, (Object)a_3742_W.L_4248_u, (Object[])new T_2915_h[]{a_3742_W.l_4537_E, a_3742_W.y_2772_m, a_3742_W.x_607_J, a_3742_W.d_2427_y, a_3742_W.O_1795_e, a_3742_W.H_1883_T, a_3742_W.e_4240_b, a_3742_W.k_3961_g, a_3742_W.D_60_a, a_3742_W.U_1341_G, a_3742_W.i_3196_G, a_3742_W.ServerHelper, a_3742_W.G_4691_Q, a_3742_W.Y_776_s, a_3742_W.o_1800_r, a_3742_W.h_4320_q, a_3742_W.t_4219_U, a_3742_W.V_1446_Y, a_3742_W.BoatNoClip, a_3742_W.ElytraResolver, a_3742_W.BlockFly, a_3742_W.J_1907_R, a_3742_W.R_4764_Y, a_3742_W.G_564_y, a_3742_W.P_1922_E, a_3742_W.u_1723_Y, a_3742_W.v_4262_N, a_3742_W.w_1484_f, a_3742_W.Jesus, a_3742_W.MoveHelper, a_3742_W.NoFall, a_3742_W.NoPush, a_3742_W.NoSlow, a_3742_W.NoWeb, a_3742_W.Phase, a_3742_W.Speed, a_3742_W.Spider, a_3742_W.Sprint, a_3742_W.Strafe, a_3742_W.WaterSpeed, a_3742_W.AntiAFK, a_3742_W.Timer, a_3742_W.SuperFirework, a_3742_W.o_3599_Z, a_3742_W.i_601_W, a_3742_W.m_4644_u, a_3742_W.u_488_m, a_3742_W.O_1043_U, a_3742_W.v_1900_v, a_3742_W.j_2129_E, a_3742_W.W_1488_x, a_3742_W.j_1654_T, a_3742_W.l_3729_r, a_3742_W.q_3115_L, a_3742_W.p_863_D, a_3742_W.E_4612_l, a_3742_W.v_143_j, a_3742_W.U_3443_A, a_3742_W.k_1052_R, a_3742_W.EntityESP, a_3742_W.Crosshair, a_3742_W.CrystalESP, a_3742_W.ChatBubbles, a_3742_W.BlockOverlay, a_3742_W.DistantAlpha, a_3742_W.ArmorDurability, a_3742_W.Chams, a_3742_W.AspectRatio, a_3742_W.AnomalyESP, a_3742_W.x_555_z, a_3742_W.BlockESP, a_3742_W.Cosmetics, a_3742_W.Emotions, a_3742_W.Ambience, a_3742_W.Arrows, a_3742_W.j_2266_I, a_3742_W.RealmsDefaultUncaughtExceptionHandler, a_3742_W.S_980_j});

    protected s_2612_h(Tier tier, int attackDamageIn, float attackSpeedIn, q_1613_l.n_1700_B builder) {
        super(attackDamageIn, attackSpeedIn, tier, n_1700_B, builder);
    }

    @Override
    public boolean J_1907_R(K_4074_S blockIn) {
        int i = this.w_1484_f().G_564_y();
        if (!(blockIn.n_1700_B(a_3742_W.ClientBootstrap) || blockIn.n_1700_B(a_3742_W.MinMaxBounds) || blockIn.n_1700_B(a_3742_W.LightPredicate) || blockIn.n_1700_B(a_3742_W.WrappedMinMaxBounds) || blockIn.n_1700_B(a_3742_W.B_368_w))) {
            if (!(blockIn.n_1700_B(a_3742_W.O_1309_Q) || blockIn.n_1700_B(a_3742_W.L_4248_u) || blockIn.n_1700_B(a_3742_W.V_1665_T) || blockIn.n_1700_B(a_3742_W.B_2580_P) || blockIn.n_1700_B(a_3742_W.y_2772_m) || blockIn.n_1700_B(a_3742_W.x_607_J) || blockIn.n_1700_B(a_3742_W.o_1800_r))) {
                if (!(blockIn.n_1700_B(a_3742_W.H_1883_T) || blockIn.n_1700_B(a_3742_W.e_4240_b) || blockIn.n_1700_B(a_3742_W.k_3961_g) || blockIn.n_1700_B(a_3742_W.D_60_a))) {
                    Material material = blockIn.R_4764_Y();
                    return material == Material.d_2427_y || material == Material.z_1737_N || material == Material.d_2461_k || blockIn.n_1700_B(a_3742_W.d_2427_y);
                }
                return i >= 1;
            }
            return i >= 2;
        }
        return i >= 3;
    }

    @Override
    public float n_1700_B(Z_1993_T stack, K_4074_S state) {
        Material material = state.R_4764_Y();
        return material != Material.z_1737_N && material != Material.d_2461_k && material != Material.d_2427_y ? super.n_1700_B(stack, state) : this.J_1907_R;
    }
}



