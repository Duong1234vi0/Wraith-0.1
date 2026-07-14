/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  it.unimi.dsi.fastutil.Hash$Strategy
 *  it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.D_3318_r;
import lightning.product.E_270_p;
import lightning.product.E_688_b;
import lightning.product.H_3330_w;
import lightning.product.T_2978_m;
import lightning.product.b_1213_w;
import lightning.product.g_2336_b;
import lightning.product.o_2840_r;
import net.optifine.Config;
import net.optifine.EmissiveTextures;
import net.optifine.RandomEntities;
import net.optifine.SmartAnimations;
import net.optifine.render.RenderStateManager;
import net.optifine.render.RenderUtils;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.CompareUtils;
import net.optifine.util.CompoundKey;

public abstract class o_2576_A
extends E_270_p {
    private static final o_2576_A c_4037_x = o_2576_A.n_1700_B("solid", E_688_b.w_1484_f, 7, 0x200000, true, false, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(P_4830_p).n_1700_B(Y_259_p).n_1700_B(h_1847_R).n_1700_B(true));
    private static final o_2576_A g_2268_R = o_2576_A.n_1700_B("cutout_mipped", E_688_b.w_1484_f, 7, 131072, true, false, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(P_4830_p).n_1700_B(Y_259_p).n_1700_B(h_1847_R).n_1700_B(u_2550_I).n_1700_B(true));
    private static final o_2576_A T_3594_S = o_2576_A.n_1700_B("cutout", E_688_b.w_1484_f, 7, 131072, true, false, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(P_4830_p).n_1700_B(Y_259_p).n_1700_B(Q_4569_t).n_1700_B(u_2550_I).n_1700_B(true));
    private static final o_2576_A D_4792_h = o_2576_A.n_1700_B("translucent", E_688_b.w_1484_f, 7, 262144, true, true, o_2576_A.v_4276_D());
    private static final o_2576_A s_2632_s = o_2576_A.n_1700_B("translucent_moving_block", E_688_b.w_1484_f, 7, 262144, false, true, o_2576_A.d_2461_k());
    private static final o_2576_A l_1233_K = o_2576_A.n_1700_B("translucent_no_crumbling", E_688_b.w_1484_f, 7, 262144, false, true, o_2576_A.v_4276_D());
    private static final o_2576_A z_1333_t = o_2576_A.n_1700_B("leash", E_688_b.Y_259_p, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(M_182_A).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(false));
    private static final o_2576_A O_508_d = o_2576_A.n_1700_B("water_mask", E_688_b.w_1457_N, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(M_182_A).n_1700_B(n_3318_d).n_1700_B(false));
    private static final o_2576_A r_715_M = o_2576_A.n_1700_B("armor_glint", E_688_b.Q_2552_b, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(H_3330_w.n_1700_B, true, false)).n_1700_B(e_4240_b).n_1700_B(H_2857_Y).n_1700_B(Y_1740_V).n_1700_B(P_1922_E).n_1700_B(w_1457_N).n_1700_B(v_4276_D).n_1700_B(false));
    private static final o_2576_A A_1038_p = o_2576_A.n_1700_B("armor_entity_glint", E_688_b.Q_2552_b, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(H_3330_w.n_1700_B, true, false)).n_1700_B(e_4240_b).n_1700_B(H_2857_Y).n_1700_B(Y_1740_V).n_1700_B(P_1922_E).n_1700_B(Y_601_j).n_1700_B(v_4276_D).n_1700_B(false));
    private static final o_2576_A i_1637_u = o_2576_A.n_1700_B("glint_translucent", E_688_b.Q_2552_b, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(H_3330_w.n_1700_B, true, false)).n_1700_B(e_4240_b).n_1700_B(H_2857_Y).n_1700_B(Y_1740_V).n_1700_B(P_1922_E).n_1700_B(w_1457_N).n_1700_B(X_933_l).n_1700_B(false));
    private static final o_2576_A Ping = o_2576_A.n_1700_B("glint", E_688_b.Q_2552_b, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(H_3330_w.n_1700_B, true, false)).n_1700_B(e_4240_b).n_1700_B(H_2857_Y).n_1700_B(Y_1740_V).n_1700_B(P_1922_E).n_1700_B(w_1457_N).n_1700_B(false));
    private static final o_2576_A p_178_J = o_2576_A.n_1700_B("glint_direct", E_688_b.Q_2552_b, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(H_3330_w.n_1700_B, true, false)).n_1700_B(e_4240_b).n_1700_B(H_2857_Y).n_1700_B(Y_1740_V).n_1700_B(P_1922_E).n_1700_B(w_1457_N).n_1700_B(false));
    private static final o_2576_A RealmsClientConfig = o_2576_A.n_1700_B("entity_glint", E_688_b.Q_2552_b, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(H_3330_w.n_1700_B, true, false)).n_1700_B(e_4240_b).n_1700_B(H_2857_Y).n_1700_B(Y_1740_V).n_1700_B(P_1922_E).n_1700_B(X_933_l).n_1700_B(Y_601_j).n_1700_B(false));
    private static final o_2576_A f_4016_n = o_2576_A.n_1700_B("entity_glint_direct", E_688_b.Q_2552_b, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(H_3330_w.n_1700_B, true, false)).n_1700_B(e_4240_b).n_1700_B(H_2857_Y).n_1700_B(Y_1740_V).n_1700_B(P_1922_E).n_1700_B(Y_601_j).n_1700_B(false));
    private static final o_2576_A j_276_v = o_2576_A.n_1700_B("lightning", E_688_b.Y_601_j, 7, 256, false, true, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(x_607_J).n_1700_B(G_564_y).n_1700_B(B_1668_F).n_1700_B(P_4830_p).n_1700_B(false));
    private static final o_2576_A UploadStatus = o_2576_A.n_1700_B("tripwire", E_688_b.w_1484_f, 7, 262144, true, true, o_2576_A.G_624_v());
    public static final R_4764_Y H_1990_U = o_2576_A.n_1700_B("lines", E_688_b.Y_601_j, 1, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.t_148_a(OptionalDouble.empty())).n_1700_B(v_4276_D).n_1700_B(v_4262_N).n_1700_B(X_933_l).n_1700_B(x_607_J).n_1700_B(false));
    private final b_1213_w e_1992_r;
    private final int D_60_a;
    private final int k_3961_g;
    private final boolean Ops;
    private final boolean h_4320_q;
    private final Optional<o_2576_A> t_4219_U;
    private int V_1446_Y = -1;
    public static final o_2576_A[] N_2525_X = o_2576_A.z_1737_N();
    private static Map<CompoundKey, o_2576_A> PlayerInfo;

    public int G_564_y() {
        return this.V_1446_Y;
    }

    public boolean P_1922_E() {
        return this.h_4320_q;
    }

    private static o_2576_A[] z_1737_N() {
        o_2576_A[] arendertype = o_2576_A.k_2293_S().toArray(new o_2576_A[0]);
        int i = 0;
        while (i < arendertype.length) {
            o_2576_A rendertype = arendertype[i];
            rendertype.V_1446_Y = i++;
        }
        return arendertype;
    }

    public static o_2576_A u_1723_Y() {
        return c_4037_x;
    }

    public static o_2576_A v_4262_N() {
        return g_2268_R;
    }

    public static o_2576_A w_1484_f() {
        return T_3594_S;
    }

    private static J_1907_R v_4276_D() {
        return lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(P_4830_p).n_1700_B(Y_259_p).n_1700_B(h_1847_R).n_1700_B(v_4262_N).n_1700_B(g_221_o).n_1700_B(true);
    }

    public static o_2576_A t_148_a() {
        return D_4792_h;
    }

    private static J_1907_R d_2461_k() {
        return lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(P_4830_p).n_1700_B(Y_259_p).n_1700_B(h_1847_R).n_1700_B(v_4262_N).n_1700_B(X_933_l).n_1700_B(true);
    }

    public static o_2576_A s_956_w() {
        return s_2632_s;
    }

    public static o_2576_A u_2550_I() {
        return l_1233_K;
    }

    public static o_2576_A n_1700_B(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("armor_cutout_no_cull", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(J_1907_R).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(v_4276_D).n_1700_B(true);
            return o_2576_A.n_1700_B("armor_cutout_no_cull", E_688_b.t_148_a, 7, 256, true, false, rendertype$state);
        });
    }

    public static o_2576_A J_1907_R(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_solid", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(J_1907_R).n_1700_B(q_2307_F).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(true);
            return o_2576_A.n_1700_B("entity_solid", E_688_b.t_148_a, 7, 256, true, false, rendertype$state);
        });
    }

    public static o_2576_A R_4764_Y(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_cutout", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(J_1907_R).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(true);
            return o_2576_A.n_1700_B("entity_cutout", E_688_b.t_148_a, 7, 256, true, false, rendertype$state);
        });
    }

    public static o_2576_A n_1700_B(g_2336_b locationIn, boolean outlineIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_cutout_no_cull", locationIn, outlineIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(J_1907_R).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(outlineIn);
            return o_2576_A.n_1700_B("entity_cutout_no_cull", E_688_b.t_148_a, 7, 256, true, false, rendertype$state);
        });
    }

    public static o_2576_A G_564_y(g_2336_b locationIn) {
        return o_2576_A.n_1700_B(locationIn, true);
    }

    public static o_2576_A J_1907_R(g_2336_b locationIn, boolean outlineIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_cutout_no_cull_z_offset", locationIn, outlineIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(J_1907_R).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(v_4276_D).n_1700_B(outlineIn);
            return o_2576_A.n_1700_B("entity_cutout_no_cull_z_offset", E_688_b.t_148_a, 7, 256, true, false, rendertype$state);
        });
    }

    public static o_2576_A P_1922_E(g_2336_b locationIn) {
        return o_2576_A.J_1907_R(locationIn, true);
    }

    public static o_2576_A u_1723_Y(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("item_entity_translucent_cull", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(v_4262_N).n_1700_B(X_933_l).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(E_270_p.x_607_J).n_1700_B(true);
            return o_2576_A.n_1700_B("item_entity_translucent_cull", E_688_b.t_148_a, 7, 256, true, true, rendertype$state);
        });
    }

    public static o_2576_A v_4262_N(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_translucent_cull", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(v_4262_N).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(true);
            return o_2576_A.n_1700_B("entity_translucent_cull", E_688_b.t_148_a, 7, 256, true, true, rendertype$state);
        });
    }

    public static o_2576_A R_4764_Y(g_2336_b LocationIn, boolean outlineIn) {
        g_2336_b temp = LocationIn = o_2576_A.multiplayerClientSuggestionProvider(LocationIn);
        return o_2576_A.n_1700_B("entity_translucent", LocationIn, outlineIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(v_4262_N).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(outlineIn);
            return o_2576_A.n_1700_B("entity_translucent", E_688_b.t_148_a, 7, 256, true, true, rendertype$state);
        });
    }

    public static o_2576_A w_1484_f(g_2336_b locationIn) {
        return o_2576_A.R_4764_Y(locationIn, true);
    }

    public static o_2576_A t_148_a(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_smooth_cutout", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(s_956_w).n_1700_B(q_2307_F).n_1700_B(P_4830_p).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(true);
            return o_2576_A.n_1700_B("entity_smooth_cutout", E_688_b.t_148_a, 7, 256, rendertype$state);
        });
    }

    public static o_2576_A G_564_y(g_2336_b locationIn, boolean colorFlagIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("beacon_beam", locationIn, colorFlagIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(colorFlagIn ? v_4262_N : J_1907_R).n_1700_B(colorFlagIn ? e_4240_b : x_607_J).n_1700_B(d_2461_k).n_1700_B(false);
            return o_2576_A.n_1700_B("beacon_beam", E_688_b.w_1484_f, 7, 256, false, true, rendertype$state);
        });
    }

    public static o_2576_A s_956_w(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_decal", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(Y_1740_V).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(false);
            return o_2576_A.n_1700_B("entity_decal", E_688_b.t_148_a, 7, 256, rendertype$state);
        });
    }

    public static o_2576_A u_2550_I(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_no_outline", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(v_4262_N).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(e_4240_b).n_1700_B(false);
            return o_2576_A.n_1700_B("entity_no_outline", E_688_b.t_148_a, 7, 256, false, true, rendertype$state);
        });
    }

    public static o_2576_A M_588_G(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_shadow", locationIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(v_4262_N).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(c_3005_b).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(e_4240_b).n_1700_B(t_4043_B).n_1700_B(v_4276_D).n_1700_B(false);
            return o_2576_A.n_1700_B("entity_shadow", E_688_b.t_148_a, 7, 256, false, false, rendertype$state);
        });
    }

    public static o_2576_A n_1700_B(g_2336_b locationIn, float refIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("entity_alpha", locationIn, refIn, () -> {
            J_1907_R rendertype$state = lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(new E_270_p.n_1700_B(refIn)).n_1700_B(H_2857_Y).n_1700_B(true);
            return o_2576_A.n_1700_B("entity_alpha", E_688_b.t_148_a, 7, 256, rendertype$state);
        });
    }

    public static o_2576_A P_4830_p(g_2336_b locationIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("eyes", locationIn, () -> {
            E_270_p.Q_4569_t renderstate$texturestate = new E_270_p.Q_4569_t(temp, false, false);
            return o_2576_A.n_1700_B("eyes", E_688_b.t_148_a, 7, 256, false, true, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(renderstate$texturestate).n_1700_B(R_4764_Y).n_1700_B(e_4240_b).n_1700_B(T_2506_i).n_1700_B(false));
        });
    }

    public static o_2576_A n_1700_B(g_2336_b locationIn, float uIn, float vIn) {
        g_2336_b temp = locationIn = o_2576_A.multiplayerClientSuggestionProvider(locationIn);
        return o_2576_A.n_1700_B("energy_swirl", locationIn, uIn, vIn, () -> o_2576_A.n_1700_B("energy_swirl", E_688_b.t_148_a, 7, 256, false, true, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(temp, false, false)).n_1700_B(new E_270_p.s_956_w(uIn, vIn)).n_1700_B(T_2506_i).n_1700_B(R_4764_Y).n_1700_B(q_2307_F).n_1700_B(t_148_a).n_1700_B(H_2857_Y).n_1700_B(Y_259_p).n_1700_B(C_2741_M).n_1700_B(false)));
    }

    public static o_2576_A M_588_G() {
        return z_1333_t;
    }

    public static o_2576_A P_4830_p() {
        return O_508_d;
    }

    public static o_2576_A h_1847_R(g_2336_b locationIn) {
        return o_2576_A.n_1700_B(locationIn, H_2857_Y);
    }

    public static o_2576_A n_1700_B(g_2336_b locationIn, E_270_p.R_4764_Y cull) {
        return o_2576_A.n_1700_B("outline", locationIn, cull == c_3005_b, () -> o_2576_A.n_1700_B("outline", E_688_b.C_2741_M, 7, 256, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(locationIn, false, false)).n_1700_B(cull).n_1700_B(A_4115_X).n_1700_B(t_148_a).n_1700_B(multiplayerClientSuggestionProvider).n_1700_B(d_2461_k).n_1700_B(z_4693_k).n_1700_B(lightning.product.o_2576_A$n_1700_B.J_1907_R)));
    }

    public static o_2576_A h_1847_R() {
        return r_715_M;
    }

    public static o_2576_A Q_4569_t() {
        return A_1038_p;
    }

    public static o_2576_A M_182_A() {
        return i_1637_u;
    }

    public static o_2576_A t_1786_h() {
        return Ping;
    }

    public static o_2576_A multiplayerClientSuggestionProvider() {
        return p_178_J;
    }

    public static o_2576_A w_1457_N() {
        return RealmsClientConfig;
    }

    public static o_2576_A Y_601_j() {
        return f_4016_n;
    }

    public static o_2576_A Q_4569_t(g_2336_b locationIn) {
        return o_2576_A.n_1700_B("crumbling", locationIn, () -> {
            E_270_p.Q_4569_t renderstate$texturestate = new E_270_p.Q_4569_t(locationIn, false, false);
            return o_2576_A.n_1700_B("crumbling", E_688_b.w_1484_f, 7, 256, false, true, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(renderstate$texturestate).n_1700_B(t_148_a).n_1700_B(u_1723_Y).n_1700_B(e_4240_b).n_1700_B(z_1737_N).n_1700_B(false));
        });
    }

    public static o_2576_A M_182_A(g_2336_b locationIn) {
        return o_2576_A.n_1700_B("text", locationIn, () -> o_2576_A.n_1700_B("text", E_688_b.q_2307_F, 7, 256, false, false, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(locationIn, false, false)).n_1700_B(t_148_a).n_1700_B(v_4262_N).n_1700_B(Y_259_p).n_1700_B(false)));
    }

    public static o_2576_A t_1786_h(g_2336_b locationIn) {
        return o_2576_A.n_1700_B("text_see_through", locationIn, () -> o_2576_A.n_1700_B("text_see_through", E_688_b.q_2307_F, 7, 256, false, false, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(new E_270_p.Q_4569_t(locationIn, false, false)).n_1700_B(t_148_a).n_1700_B(v_4262_N).n_1700_B(Y_259_p).n_1700_B(A_4115_X).n_1700_B(e_4240_b).n_1700_B(false)));
    }

    public static o_2576_A Y_259_p() {
        return j_276_v;
    }

    private static J_1907_R G_624_v() {
        return lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(P_4830_p).n_1700_B(Y_259_p).n_1700_B(h_1847_R).n_1700_B(v_4262_N).n_1700_B(B_1668_F).n_1700_B(true);
    }

    public static o_2576_A Q_2552_b() {
        return UploadStatus;
    }

    public static o_2576_A n_1700_B(int iterationIn) {
        return o_2576_A.n_1700_B("end_portal", iterationIn, () -> {
            E_270_p.Q_4569_t renderstate$texturestate;
            E_270_p.t_1786_h renderstate$transparencystate;
            if (iterationIn <= 1) {
                renderstate$transparencystate = v_4262_N;
                renderstate$texturestate = new E_270_p.Q_4569_t(T_2978_m.n_1700_B, false, false);
            } else {
                renderstate$transparencystate = R_4764_Y;
                renderstate$texturestate = new E_270_p.Q_4569_t(T_2978_m.J_1907_R, false, false);
            }
            return o_2576_A.n_1700_B("end_portal", E_688_b.Y_601_j, 7, 256, false, true, lightning.product.o_2576_A$J_1907_R.n_1700_B().n_1700_B(renderstate$transparencystate).n_1700_B(renderstate$texturestate).n_1700_B(new E_270_p.M_588_G(iterationIn)).n_1700_B(T_2506_i).n_1700_B(false));
        });
    }

    public static o_2576_A C_2741_M() {
        return H_1990_U;
    }

    public o_2576_A(String nameIn, b_1213_w formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, setupTaskIn, clearTaskIn);
        this.e_1992_r = formatIn;
        this.D_60_a = drawModeIn;
        this.k_3961_g = bufferSizeIn;
        this.Ops = useDelegateIn;
        this.h_4320_q = needsSortingIn;
        this.t_4219_U = Optional.of(this);
    }

    public static R_4764_Y n_1700_B(String nameIn, b_1213_w vertexFormatIn, int drawModeIn, int bufferSizeIn, J_1907_R renderStateIn) {
        return o_2576_A.n_1700_B(nameIn, vertexFormatIn, drawModeIn, bufferSizeIn, false, false, renderStateIn);
    }

    public static R_4764_Y n_1700_B(String name, b_1213_w vertexFormatIn, int glMode, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, J_1907_R renderStateIn) {
        return lightning.product.o_2576_A$R_4764_Y.J_1907_R(name, vertexFormatIn, glMode, bufferSizeIn, useDelegateIn, needsSortingIn, renderStateIn);
    }

    public void n_1700_B(D_3318_r buffer, int cameraX, int cameraY, int cameraZ) {
        if (buffer.s_956_w()) {
            if (this.h_4320_q) {
                buffer.n_1700_B((float)cameraX, (float)cameraY, (float)cameraZ);
            }
            if (buffer.u_2550_I != null) {
                SmartAnimations.spritesRendered(buffer.u_2550_I);
            }
            buffer.u_1723_Y();
            this.n_1700_B();
            if (Config.isShaders()) {
                RenderUtils.setFlushRenderBuffers(false);
                Shaders.pushProgram();
                ShadersRender.preRender(this, buffer);
            }
            o_2840_r.n_1700_B(buffer);
            if (Config.isShaders()) {
                ShadersRender.postRender(this, buffer);
                Shaders.popProgram();
                RenderUtils.setFlushRenderBuffers(true);
            }
            this.J_1907_R();
        }
    }

    @Override
    public String toString() {
        return this.n_1700_B;
    }

    public static List<o_2576_A> k_2293_S() {
        return ImmutableList.of((Object)o_2576_A.u_1723_Y(), (Object)o_2576_A.v_4262_N(), (Object)o_2576_A.w_1484_f(), (Object)o_2576_A.t_148_a(), (Object)o_2576_A.Q_2552_b());
    }

    public int q_2307_F() {
        return this.k_3961_g;
    }

    public b_1213_w Z_875_P() {
        return this.e_1992_r;
    }

    public int c_3005_b() {
        return this.D_60_a;
    }

    public Optional<o_2576_A> H_2857_Y() {
        return Optional.empty();
    }

    public boolean A_4115_X() {
        return false;
    }

    public boolean Y_1740_V() {
        return this.Ops;
    }

    public Optional<o_2576_A> t_4043_B() {
        return this.t_4219_U;
    }

    private static o_2576_A n_1700_B(String p_getRenderType_0_, g_2336_b p_getRenderType_1_, Supplier<o_2576_A> p_getRenderType_2_) {
        CompoundKey compoundkey = new CompoundKey(p_getRenderType_0_, p_getRenderType_1_);
        return o_2576_A.n_1700_B(compoundkey, p_getRenderType_2_);
    }

    private static o_2576_A n_1700_B(String p_getRenderType_0_, g_2336_b p_getRenderType_1_, boolean p_getRenderType_2_, Supplier<o_2576_A> p_getRenderType_3_) {
        CompoundKey compoundkey = new CompoundKey(p_getRenderType_0_, p_getRenderType_1_, p_getRenderType_2_);
        return o_2576_A.n_1700_B(compoundkey, p_getRenderType_3_);
    }

    private static o_2576_A n_1700_B(String p_getRenderType_0_, g_2336_b p_getRenderType_1_, float p_getRenderType_2_, Supplier<o_2576_A> p_getRenderType_3_) {
        CompoundKey compoundkey = new CompoundKey(p_getRenderType_0_, p_getRenderType_1_, Float.valueOf(p_getRenderType_2_));
        return o_2576_A.n_1700_B(compoundkey, p_getRenderType_3_);
    }

    private static o_2576_A n_1700_B(String p_getRenderType_0_, g_2336_b p_getRenderType_1_, float p_getRenderType_2_, float p_getRenderType_3_, Supplier<o_2576_A> p_getRenderType_4_) {
        CompoundKey compoundkey = new CompoundKey(p_getRenderType_0_, p_getRenderType_1_, Float.valueOf(p_getRenderType_2_), Float.valueOf(p_getRenderType_3_));
        return o_2576_A.n_1700_B(compoundkey, p_getRenderType_4_);
    }

    private static o_2576_A n_1700_B(String p_getRenderType_0_, int p_getRenderType_1_, Supplier<o_2576_A> p_getRenderType_2_) {
        CompoundKey compoundkey = new CompoundKey(p_getRenderType_0_, p_getRenderType_1_);
        return o_2576_A.n_1700_B(compoundkey, p_getRenderType_2_);
    }

    private static o_2576_A n_1700_B(CompoundKey p_getRenderType_0_, Supplier<o_2576_A> p_getRenderType_1_) {
        o_2576_A rendertype;
        if (PlayerInfo == null) {
            PlayerInfo = new HashMap<CompoundKey, o_2576_A>();
        }
        if ((rendertype = PlayerInfo.get(p_getRenderType_0_)) != null) {
            return rendertype;
        }
        rendertype = p_getRenderType_1_.get();
        PlayerInfo.put(p_getRenderType_0_, rendertype);
        return rendertype;
    }

    public static g_2336_b multiplayerClientSuggestionProvider(g_2336_b p_getCustomTexture_0_) {
        if (Config.isRandomEntities()) {
            p_getCustomTexture_0_ = RandomEntities.getTextureLocation(p_getCustomTexture_0_);
        }
        if (EmissiveTextures.isActive()) {
            p_getCustomTexture_0_ = EmissiveTextures.getEmissiveTexture(p_getCustomTexture_0_);
        }
        return p_getCustomTexture_0_;
    }

    public boolean x_607_J() {
        return this.R_4764_Y().equals("entity_solid");
    }

    public static int e_4240_b() {
        return o_2576_A.H_1990_U.g_2268_R.t_1786_h.size();
    }

    public g_2336_b n_3318_d() {
        return null;
    }

    public boolean d_2427_y() {
        return this.n_3318_d() == H_3330_w.n_1700_B;
    }

    public static final class J_1907_R {
        private final E_270_p.Q_4569_t n_1700_B;
        private final E_270_p.t_1786_h J_1907_R;
        private final E_270_p.P_1922_E R_4764_Y;
        private final E_270_p.P_4830_p G_564_y;
        private final E_270_p.n_1700_B P_1922_E;
        private final E_270_p.G_564_y u_1723_Y;
        private final E_270_p.R_4764_Y v_4262_N;
        private final E_270_p.w_1484_f w_1484_f;
        private final E_270_p.u_2550_I t_148_a;
        private final E_270_p.u_1723_Y s_956_w;
        private final E_270_p.v_4262_N u_2550_I;
        private final E_270_p.h_1847_R M_588_G;
        private final E_270_p.M_182_A P_4830_p;
        private final E_270_p.multiplayerClientSuggestionProvider h_1847_R;
        private final E_270_p.t_148_a Q_4569_t;
        private final lightning.product.o_2576_A$n_1700_B M_182_A;
        private final ImmutableList<E_270_p> t_1786_h;

        private J_1907_R(E_270_p.Q_4569_t p_i230053_1_, E_270_p.t_1786_h p_i230053_2_, E_270_p.P_1922_E p_i230053_3_, E_270_p.P_4830_p p_i230053_4_, E_270_p.n_1700_B p_i230053_5_, E_270_p.G_564_y p_i230053_6_, E_270_p.R_4764_Y p_i230053_7_, E_270_p.w_1484_f p_i230053_8_, E_270_p.u_2550_I p_i230053_9_, E_270_p.u_1723_Y p_i230053_10_, E_270_p.v_4262_N p_i230053_11_, E_270_p.h_1847_R p_i230053_12_, E_270_p.M_182_A p_i230053_13_, E_270_p.multiplayerClientSuggestionProvider p_i230053_14_, E_270_p.t_148_a p_i230053_15_, lightning.product.o_2576_A$n_1700_B p_i230053_16_) {
            this.n_1700_B = p_i230053_1_;
            this.J_1907_R = p_i230053_2_;
            this.R_4764_Y = p_i230053_3_;
            this.G_564_y = p_i230053_4_;
            this.P_1922_E = p_i230053_5_;
            this.u_1723_Y = p_i230053_6_;
            this.v_4262_N = p_i230053_7_;
            this.w_1484_f = p_i230053_8_;
            this.t_148_a = p_i230053_9_;
            this.s_956_w = p_i230053_10_;
            this.u_2550_I = p_i230053_11_;
            this.M_588_G = p_i230053_12_;
            this.P_4830_p = p_i230053_13_;
            this.h_1847_R = p_i230053_14_;
            this.Q_4569_t = p_i230053_15_;
            this.M_182_A = p_i230053_16_;
            this.t_1786_h = ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.t_148_a, (Object)this.s_956_w, (Object)this.u_2550_I, (Object)this.M_588_G, (Object[])new E_270_p[]{this.P_4830_p, this.h_1847_R, this.Q_4569_t});
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                J_1907_R rendertype$state = (J_1907_R)p_equals_1_;
                return this.M_182_A == rendertype$state.M_182_A && this.t_1786_h.equals(rendertype$state.t_1786_h);
            }
            return false;
        }

        public int hashCode() {
            return CompareUtils.hash(this.t_1786_h, (Object)this.M_182_A);
        }

        public String toString() {
            return "CompositeState[" + String.valueOf(this.t_1786_h) + ", outlineProperty=" + String.valueOf((Object)this.M_182_A) + "]";
        }

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B J_1907_R() {
            n_1700_B rendertype$state$builder = new n_1700_B();
            rendertype$state$builder.n_1700_B(this.n_1700_B);
            rendertype$state$builder.n_1700_B(this.J_1907_R);
            rendertype$state$builder.n_1700_B(this.R_4764_Y);
            rendertype$state$builder.n_1700_B(this.G_564_y);
            rendertype$state$builder.n_1700_B(this.P_1922_E);
            rendertype$state$builder.n_1700_B(this.u_1723_Y);
            rendertype$state$builder.n_1700_B(this.v_4262_N);
            rendertype$state$builder.n_1700_B(this.w_1484_f);
            rendertype$state$builder.n_1700_B(this.t_148_a);
            rendertype$state$builder.n_1700_B(this.s_956_w);
            rendertype$state$builder.n_1700_B(this.u_2550_I);
            rendertype$state$builder.n_1700_B(this.M_588_G);
            rendertype$state$builder.n_1700_B(this.P_4830_p);
            rendertype$state$builder.n_1700_B(this.h_1847_R);
            rendertype$state$builder.n_1700_B(this.Q_4569_t);
            return rendertype$state$builder;
        }

        public static class n_1700_B {
            private E_270_p.Q_4569_t n_1700_B = E_270_p.M_182_A;
            private E_270_p.t_1786_h J_1907_R = E_270_p.J_1907_R;
            private E_270_p.P_1922_E R_4764_Y = E_270_p.Z_875_P;
            private E_270_p.P_4830_p G_564_y = E_270_p.M_588_G;
            private E_270_p.n_1700_B P_1922_E = E_270_p.w_1484_f;
            private E_270_p.G_564_y u_1723_Y = E_270_p.t_4043_B;
            private E_270_p.R_4764_Y v_4262_N = E_270_p.c_3005_b;
            private E_270_p.w_1484_f w_1484_f = E_270_p.Q_2552_b;
            private E_270_p.u_2550_I t_148_a = E_270_p.k_2293_S;
            private E_270_p.u_1723_Y s_956_w = E_270_p.G_624_v;
            private E_270_p.v_4262_N u_2550_I = E_270_p.d_2427_y;
            private E_270_p.h_1847_R M_588_G = E_270_p.q_4610_l;
            private E_270_p.M_182_A P_4830_p = E_270_p.t_1786_h;
            private E_270_p.multiplayerClientSuggestionProvider h_1847_R = E_270_p.x_607_J;
            private E_270_p.t_148_a Q_4569_t = E_270_p.Z_976_R;

            private n_1700_B() {
            }

            public n_1700_B n_1700_B(E_270_p.Q_4569_t p_228724_1_) {
                this.n_1700_B = p_228724_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.t_1786_h p_228726_1_) {
                this.J_1907_R = p_228726_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.P_1922_E p_228716_1_) {
                this.R_4764_Y = p_228716_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.P_4830_p p_228723_1_) {
                this.G_564_y = p_228723_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.n_1700_B p_228713_1_) {
                this.P_1922_E = p_228713_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.G_564_y p_228715_1_) {
                this.u_1723_Y = p_228715_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.R_4764_Y p_228714_1_) {
                this.v_4262_N = p_228714_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.w_1484_f p_228719_1_) {
                this.w_1484_f = p_228719_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.u_2550_I p_228722_1_) {
                this.t_148_a = p_228722_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.u_1723_Y p_228717_1_) {
                this.s_956_w = p_228717_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.v_4262_N p_228718_1_) {
                this.u_2550_I = p_228718_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.h_1847_R p_228721_1_) {
                this.M_588_G = p_228721_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.M_182_A p_228725_1_) {
                this.P_4830_p = p_228725_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.multiplayerClientSuggestionProvider p_228727_1_) {
                this.h_1847_R = p_228727_1_;
                return this;
            }

            public n_1700_B n_1700_B(E_270_p.t_148_a p_228720_1_) {
                this.Q_4569_t = p_228720_1_;
                return this;
            }

            public J_1907_R n_1700_B(boolean outlineIn) {
                return this.n_1700_B(outlineIn ? lightning.product.o_2576_A$n_1700_B.R_4764_Y : lightning.product.o_2576_A$n_1700_B.n_1700_B);
            }

            public J_1907_R n_1700_B(lightning.product.o_2576_A$n_1700_B p_230173_1_) {
                return new J_1907_R(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, p_230173_1_);
            }
        }
    }

    static final class R_4764_Y
    extends o_2576_A {
        private static final ObjectOpenCustomHashSet<R_4764_Y> c_4037_x = new ObjectOpenCustomHashSet((Hash.Strategy)n_1700_B.n_1700_B);
        private final J_1907_R g_2268_R;
        private final int T_3594_S;
        private final Optional<o_2576_A> D_4792_h;
        private final boolean s_2632_s;
        private Map<g_2336_b, R_4764_Y> l_1233_K = new HashMap<g_2336_b, R_4764_Y>();

        private R_4764_Y(String p_i225993_1_, b_1213_w p_i225993_2_, int p_i225993_3_, int p_i225993_4_, boolean p_i225993_5_, boolean p_i225993_6_, J_1907_R p_i225993_7_) {
            super(p_i225993_1_, p_i225993_2_, p_i225993_3_, p_i225993_4_, p_i225993_5_, p_i225993_6_, () -> RenderStateManager.setupRenderStates(p_i225993_7_.t_1786_h), () -> RenderStateManager.clearRenderStates(p_i225993_7_.t_1786_h));
            this.g_2268_R = p_i225993_7_;
            this.D_4792_h = p_i225993_7_.M_182_A == lightning.product.o_2576_A$n_1700_B.R_4764_Y ? p_i225993_7_.n_1700_B.G_564_y().map(p_lambda$new$2_1_ -> lightning.product.o_2576_A$R_4764_Y.n_1700_B(p_lambda$new$2_1_, p_i225993_7_.v_4262_N)) : Optional.empty();
            this.s_2632_s = p_i225993_7_.M_182_A == lightning.product.o_2576_A$n_1700_B.J_1907_R;
            this.T_3594_S = CompareUtils.hash(super.hashCode(), (Object)p_i225993_7_);
        }

        private static R_4764_Y J_1907_R(String p_228676_0_, b_1213_w p_228676_1_, int p_228676_2_, int p_228676_3_, boolean p_228676_4_, boolean p_228676_5_, J_1907_R p_228676_6_) {
            return (R_4764_Y)c_4037_x.addOrGet((Object)new R_4764_Y(p_228676_0_, p_228676_1_, p_228676_2_, p_228676_3_, p_228676_4_, p_228676_5_, p_228676_6_));
        }

        @Override
        public Optional<o_2576_A> H_2857_Y() {
            return this.D_4792_h;
        }

        @Override
        public boolean A_4115_X() {
            return this.s_2632_s;
        }

        @Override
        public boolean equals(@Nullable Object p_equals_1_) {
            return this == p_equals_1_;
        }

        @Override
        public int hashCode() {
            return this.T_3594_S;
        }

        @Override
        public String toString() {
            return this.n_1700_B + ":RenderType[" + String.valueOf(this.g_2268_R) + ", ]";
        }

        @Override
        public R_4764_Y w_1457_N(g_2336_b p_getTextured_1_) {
            if (p_getTextured_1_ == null) {
                return this;
            }
            Optional<g_2336_b> optional = this.g_2268_R.n_1700_B.G_564_y();
            if (!optional.isPresent()) {
                return this;
            }
            g_2336_b resourcelocation = optional.get();
            if (resourcelocation == null) {
                return this;
            }
            if (p_getTextured_1_.equals(resourcelocation)) {
                return this;
            }
            R_4764_Y rendertype$type = this.l_1233_K.get(p_getTextured_1_);
            if (rendertype$type == null) {
                J_1907_R.n_1700_B rendertype$state$builder = this.g_2268_R.J_1907_R();
                rendertype$state$builder.n_1700_B(new E_270_p.Q_4569_t(p_getTextured_1_, this.g_2268_R.n_1700_B.P_1922_E(), this.g_2268_R.n_1700_B.u_1723_Y()));
                J_1907_R rendertype$state = rendertype$state$builder.n_1700_B(this.s_2632_s);
                rendertype$type = lightning.product.o_2576_A$R_4764_Y.n_1700_B(this.n_1700_B, this.Z_875_P(), this.c_3005_b(), this.q_2307_F(), this.Y_1740_V(), this.P_1922_E(), rendertype$state);
                this.l_1233_K.put(p_getTextured_1_, rendertype$type);
            }
            return rendertype$type;
        }

        @Override
        public g_2336_b n_3318_d() {
            Optional<g_2336_b> optional = this.g_2268_R.n_1700_B.G_564_y();
            return !optional.isPresent() ? null : optional.get();
        }

        static final class n_1700_B
        extends Enum<n_1700_B>
        implements Hash.Strategy<R_4764_Y> {
            public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
            private static final /* synthetic */ n_1700_B[] J_1907_R;

            public static n_1700_B[] values() {
                return (n_1700_B[])J_1907_R.clone();
            }

            public static n_1700_B valueOf(String name) {
                return Enum.valueOf(n_1700_B.class, name);
            }

            public int n_1700_B(@Nullable R_4764_Y p_hashCode_1_) {
                return p_hashCode_1_ == null ? 0 : p_hashCode_1_.T_3594_S;
            }

            public boolean n_1700_B(@Nullable R_4764_Y p_equals_1_, @Nullable R_4764_Y p_equals_2_) {
                if (p_equals_1_ == p_equals_2_) {
                    return true;
                }
                return p_equals_1_ != null && p_equals_2_ != null ? Objects.equals(p_equals_1_.g_2268_R, p_equals_2_.g_2268_R) : false;
            }

            public /* synthetic */ boolean equals(@Nullable Object object, @Nullable Object object2) {
                return this.n_1700_B((R_4764_Y)object, (R_4764_Y)object2);
            }

            public /* synthetic */ int hashCode(@Nullable Object object) {
                return this.n_1700_B((R_4764_Y)object);
            }

            private static /* synthetic */ n_1700_B[] n_1700_B() {
                return new n_1700_B[]{n_1700_B};
            }

            static {
                J_1907_R = lightning.product.o_2576_A$R_4764_Y$n_1700_B.n_1700_B();
            }
        }
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("none");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("is_outline");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("affects_outline");
        private final String G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String name) {
            this.G_564_y = name;
        }

        public String toString() {
            return this.G_564_y;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            P_1922_E = lightning.product.o_2576_A$n_1700_B.n_1700_B();
        }
    }
}


