/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.gson.JsonElement
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.F_2203_T;
import lightning.product.L_2532_m;
import lightning.product.O_4606_n;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1820_h;
import lightning.product.Y_4489_t;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.d_2484_X;
import lightning.product.g_2336_b;
import lightning.product.g_3212_H;
import lightning.product.l_1530_z;
import lightning.product.m_2244_y;
import lightning.product.n_1769_f;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.u_863_c;
import lightning.product.v_3760_Q;
import lightning.product.w_801_N;
import lightning.product.y_1030_X;
import lightning.product.y_1539_W;
import lightning.product.SpawnEggItem;
import net.minecraft.data.A_4115_X;
import net.minecraft.data.G_624_v;
import net.minecraft.data.H_2857_Y;
import net.minecraft.data.multiplayerClientSuggestionProvider;
import net.minecraft.data.P_1922_E;
import net.minecraft.data.Q_2552_b;
import net.minecraft.data.T_2506_i;
import net.minecraft.data.Y_1740_V;
import net.minecraft.data.g_221_o;
import net.minecraft.data.k_2293_S;
import net.minecraft.data.s_956_w;
import net.minecraft.data.u_1723_Y;
import net.minecraft.data.w_1457_N;
import net.minecraft.data.w_1484_f;

public class v_4262_N {
    private final Consumer<Q_2552_b> n_1700_B;
    private final BiConsumer<g_2336_b, Supplier<JsonElement>> J_1907_R;
    private final Consumer<q_1613_l> R_4764_Y;

    public v_4262_N(Consumer<Q_2552_b> p_i232514_1_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_i232514_2_, Consumer<q_1613_l> p_i232514_3_) {
        this.n_1700_B = p_i232514_1_;
        this.J_1907_R = p_i232514_2_;
        this.R_4764_Y = p_i232514_3_;
    }

    private void n_1700_B(T_2915_h p_239869_1_) {
        this.R_4764_Y.accept(p_239869_1_.u_1723_Y());
    }

    private void n_1700_B(T_2915_h p_239957_1_, g_2336_b p_239957_2_) {
        this.J_1907_R.accept(A_4115_X.n_1700_B(p_239957_1_.u_1723_Y()), new w_1484_f(p_239957_2_));
    }

    private void n_1700_B(q_1613_l p_239867_1_, g_2336_b p_239867_2_) {
        this.J_1907_R.accept(A_4115_X.n_1700_B(p_239867_1_), new w_1484_f(p_239867_2_));
    }

    private void n_1700_B(q_1613_l p_239866_1_) {
        G_624_v.ValueObject.n_1700_B(A_4115_X.n_1700_B(p_239866_1_), H_2857_Y.J_1907_R(p_239866_1_), this.J_1907_R);
    }

    private void J_1907_R(T_2915_h p_239934_1_) {
        q_1613_l item = p_239934_1_.u_1723_Y();
        if (item != Items.n_1700_B) {
            G_624_v.ValueObject.n_1700_B(A_4115_X.n_1700_B(item), H_2857_Y.H_2857_Y(p_239934_1_), this.J_1907_R);
        }
    }

    private void n_1700_B(T_2915_h p_239885_1_, String p_239885_2_) {
        q_1613_l item = p_239885_1_.u_1723_Y();
        G_624_v.ValueObject.n_1700_B(A_4115_X.n_1700_B(item), H_2857_Y.t_148_a(H_2857_Y.n_1700_B(p_239885_1_, p_239885_2_)), this.J_1907_R);
    }

    private static s_956_w J_1907_R() {
        return s_956_w.n_1700_B(BlockStateProperties.q_4610_l).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B());
    }

    private static s_956_w R_4764_Y() {
        return s_956_w.n_1700_B(BlockStateProperties.q_4610_l).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B()).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y));
    }

    private static s_956_w G_564_y() {
        return s_956_w.n_1700_B(BlockStateProperties.q_4610_l).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B()).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y));
    }

    private static s_956_w P_1922_E() {
        return s_956_w.n_1700_B(BlockStateProperties.G_624_v).n_1700_B(b_257_Y.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B()).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R));
    }

    private static w_1457_N J_1907_R(T_2915_h p_239968_0_, g_2336_b p_239968_1_) {
        return w_1457_N.n_1700_B(p_239968_0_, v_4262_N.n_1700_B(p_239968_1_));
    }

    private static P_1922_E[] n_1700_B(g_2336_b p_239915_0_) {
        return new P_1922_E[]{P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239915_0_), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239915_0_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239915_0_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239915_0_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)};
    }

    private static w_1457_N n_1700_B(T_2915_h p_239979_0_, g_2336_b p_239979_1_, g_2336_b p_239979_2_) {
        return w_1457_N.n_1700_B(p_239979_0_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239979_1_), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239979_2_), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239979_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239979_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y));
    }

    private static s_956_w n_1700_B(U_1266_O p_239894_0_, g_2336_b p_239894_1_, g_2336_b p_239894_2_) {
        return s_956_w.n_1700_B(p_239894_0_).n_1700_B((Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239894_1_)).n_1700_B((Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239894_2_));
    }

    private void R_4764_Y(T_2915_h p_239953_1_) {
        g_2336_b resourcelocation = g_221_o.n_1700_B.n_1700_B(p_239953_1_, this.J_1907_R);
        g_2336_b resourcelocation1 = g_221_o.J_1907_R.n_1700_B(p_239953_1_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.n_1700_B(p_239953_1_, resourcelocation, resourcelocation1));
    }

    private void G_564_y(T_2915_h p_239965_1_) {
        g_2336_b resourcelocation = g_221_o.n_1700_B.n_1700_B(p_239965_1_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.J_1907_R(p_239965_1_, resourcelocation));
    }

    private static Q_2552_b J_1907_R(T_2915_h p_239987_0_, g_2336_b p_239987_1_, g_2336_b p_239987_2_) {
        return w_1457_N.n_1700_B(p_239987_0_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.C_2741_M).n_1700_B((Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239987_1_)).n_1700_B((Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239987_2_))).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.g_221_o, BlockStateProperties.q_4610_l).n_1700_B(F_2203_T.n_1700_B, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.R_4764_Y, P_1922_E.n_1700_B()).n_1700_B(F_2203_T.J_1907_R, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)));
    }

    private static s_956_w.J_1907_R<b_257_Y, g_3212_H, L_2532_m, Boolean> n_1700_B(s_956_w.J_1907_R<b_257_Y, g_3212_H, L_2532_m, Boolean> p_239903_0_, g_3212_H p_239903_1_, g_2336_b p_239903_2_, g_2336_b p_239903_3_) {
        return p_239903_0_.n_1700_B(b_257_Y.u_1723_Y, p_239903_1_, L_2532_m.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_)).n_1700_B(b_257_Y.G_564_y, p_239903_1_, L_2532_m.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, p_239903_1_, L_2532_m.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.R_4764_Y, p_239903_1_, L_2532_m.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.u_1723_Y, p_239903_1_, L_2532_m.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_)).n_1700_B(b_257_Y.G_564_y, p_239903_1_, L_2532_m.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, p_239903_1_, L_2532_m.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.R_4764_Y, p_239903_1_, L_2532_m.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.u_1723_Y, p_239903_1_, L_2532_m.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.G_564_y, p_239903_1_, L_2532_m.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.P_1922_E, p_239903_1_, L_2532_m.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, p_239903_1_, L_2532_m.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_3_)).n_1700_B(b_257_Y.u_1723_Y, p_239903_1_, L_2532_m.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.G_564_y, p_239903_1_, L_2532_m.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_)).n_1700_B(b_257_Y.P_1922_E, p_239903_1_, L_2532_m.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.R_4764_Y, p_239903_1_, L_2532_m.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239903_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y));
    }

    private static Q_2552_b n_1700_B(T_2915_h p_239943_0_, g_2336_b p_239943_1_, g_2336_b p_239943_2_, g_2336_b p_239943_3_, g_2336_b p_239943_4_) {
        return w_1457_N.n_1700_B(p_239943_0_).n_1700_B(v_4262_N.n_1700_B(v_4262_N.n_1700_B(s_956_w.n_1700_B(BlockStateProperties.q_4610_l, BlockStateProperties.T_3594_S, BlockStateProperties.RegionPingResult, BlockStateProperties.Y_259_p), g_3212_H.J_1907_R, p_239943_1_, p_239943_2_), g_3212_H.n_1700_B, p_239943_3_, p_239943_4_));
    }

    private static Q_2552_b R_4764_Y(T_2915_h p_239994_0_, g_2336_b p_239994_1_, g_2336_b p_239994_2_) {
        return multiplayerClientSuggestionProvider.n_1700_B(p_239994_0_).n_1700_B(P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239994_1_)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239994_2_).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239994_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239994_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239994_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true));
    }

    private static Q_2552_b n_1700_B(T_2915_h p_239970_0_, g_2336_b p_239970_1_, g_2336_b p_239970_2_, g_2336_b p_239970_3_) {
        return multiplayerClientSuggestionProvider.n_1700_B(p_239970_0_).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.e_4240_b, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_1_)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.g_164_R, Y_1820_h.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_2_).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.B_1668_F, Y_1820_h.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.X_933_l, Y_1820_h.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Z_976_R, Y_1820_h.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.g_164_R, Y_1820_h.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_3_).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.B_1668_F, Y_1820_h.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.X_933_l, Y_1820_h.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Z_976_R, Y_1820_h.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239970_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true));
    }

    private static Q_2552_b J_1907_R(T_2915_h p_239960_0_, g_2336_b p_239960_1_, g_2336_b p_239960_2_, g_2336_b p_239960_3_, g_2336_b p_239960_4_) {
        return w_1457_N.n_1700_B(p_239960_0_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(v_4262_N.R_4764_Y()).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.t_1786_h, BlockStateProperties.Y_259_p).n_1700_B((Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239960_2_)).n_1700_B((Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239960_4_)).n_1700_B((Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239960_1_)).n_1700_B((Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239960_3_)));
    }

    private static Q_2552_b J_1907_R(T_2915_h p_239980_0_, g_2336_b p_239980_1_, g_2336_b p_239980_2_, g_2336_b p_239980_3_) {
        return w_1457_N.n_1700_B(p_239980_0_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.q_4610_l, BlockStateProperties.D_4792_h, BlockStateProperties.F_1410_V).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, u_863_c.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_2_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, u_863_c.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, u_863_c.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, u_863_c.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, u_863_c.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239980_1_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)));
    }

    private static Q_2552_b R_4764_Y(T_2915_h p_239988_0_, g_2336_b p_239988_1_, g_2336_b p_239988_2_, g_2336_b p_239988_3_) {
        return w_1457_N.n_1700_B(p_239988_0_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.q_4610_l, BlockStateProperties.D_4792_h, BlockStateProperties.Y_259_p).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_2_)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_2_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_1_)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_1_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.n_1700_B)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239988_3_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)));
    }

    private static Q_2552_b G_564_y(T_2915_h p_239995_0_, g_2336_b p_239995_1_, g_2336_b p_239995_2_, g_2336_b p_239995_3_) {
        return w_1457_N.n_1700_B(p_239995_0_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.q_4610_l, BlockStateProperties.D_4792_h, BlockStateProperties.Y_259_p).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_2_)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_2_)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_2_)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_2_)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_1_)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_1_)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_1_)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_1_)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_)).n_1700_B(b_257_Y.G_564_y, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_)).n_1700_B(b_257_Y.G_564_y, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, m_2244_y.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239995_3_).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)));
    }

    private static w_1457_N R_4764_Y(T_2915_h p_239978_0_, g_2336_b p_239978_1_) {
        return w_1457_N.n_1700_B(p_239978_0_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239978_1_));
    }

    private static s_956_w u_1723_Y() {
        return s_956_w.n_1700_B(BlockStateProperties.x_607_J).n_1700_B(b_257_Y.n_1700_B.J_1907_R, P_1922_E.n_1700_B()).n_1700_B(b_257_Y.n_1700_B.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.n_1700_B.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R));
    }

    private static Q_2552_b G_564_y(T_2915_h p_239986_0_, g_2336_b p_239986_1_) {
        return w_1457_N.n_1700_B(p_239986_0_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239986_1_)).n_1700_B(v_4262_N.u_1723_Y());
    }

    private void P_1922_E(T_2915_h p_243685_1_, g_2336_b p_243685_2_) {
        this.n_1700_B.accept(v_4262_N.G_564_y(p_243685_1_, p_243685_2_));
    }

    private void n_1700_B(T_2915_h p_239882_1_, g_221_o.n_1700_B p_239882_2_) {
        g_2336_b resourcelocation = p_239882_2_.n_1700_B(p_239882_1_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.G_564_y(p_239882_1_, resourcelocation));
    }

    private void J_1907_R(T_2915_h p_239939_1_, g_221_o.n_1700_B p_239939_2_) {
        g_2336_b resourcelocation = p_239939_2_.n_1700_B(p_239939_1_, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239939_1_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(v_4262_N.J_1907_R()));
    }

    private static Q_2552_b G_564_y(T_2915_h p_240000_0_, g_2336_b p_240000_1_, g_2336_b p_240000_2_) {
        return w_1457_N.n_1700_B(p_240000_0_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.x_607_J).n_1700_B(b_257_Y.n_1700_B.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240000_1_)).n_1700_B(b_257_Y.n_1700_B.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240000_2_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.n_1700_B.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240000_2_).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)));
    }

    private void n_1700_B(T_2915_h p_239883_1_, g_221_o.n_1700_B p_239883_2_, g_221_o.n_1700_B p_239883_3_) {
        g_2336_b resourcelocation = p_239883_2_.n_1700_B(p_239883_1_, this.J_1907_R);
        g_2336_b resourcelocation1 = p_239883_3_.n_1700_B(p_239883_1_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.G_564_y(p_239883_1_, resourcelocation, resourcelocation1));
    }

    private g_2336_b n_1700_B(T_2915_h p_239886_1_, String p_239886_2_, Y_1740_V p_239886_3_, Function<g_2336_b, H_2857_Y> p_239886_4_) {
        return p_239886_3_.n_1700_B(p_239886_1_, p_239886_2_, p_239886_4_.apply(H_2857_Y.n_1700_B(p_239886_1_, p_239886_2_)), this.J_1907_R);
    }

    private static Q_2552_b P_1922_E(T_2915_h p_240006_0_, g_2336_b p_240006_1_, g_2336_b p_240006_2_) {
        return w_1457_N.n_1700_B(p_240006_0_).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.C_2741_M, p_240006_2_, p_240006_1_));
    }

    private static Q_2552_b P_1922_E(T_2915_h p_240001_0_, g_2336_b p_240001_1_, g_2336_b p_240001_2_, g_2336_b p_240001_3_) {
        return w_1457_N.n_1700_B(p_240001_0_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.ValueObject).n_1700_B(n_1769_f.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240001_1_)).n_1700_B(n_1769_f.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240001_2_)).n_1700_B(n_1769_f.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240001_3_)));
    }

    private void P_1922_E(T_2915_h p_239975_1_) {
        this.R_4764_Y(p_239975_1_, g_221_o.n_1700_B);
    }

    private void R_4764_Y(T_2915_h p_239956_1_, g_221_o.n_1700_B p_239956_2_) {
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239956_1_, p_239956_2_.n_1700_B(p_239956_1_, this.J_1907_R)));
    }

    private void n_1700_B(T_2915_h p_239880_1_, H_2857_Y p_239880_2_, Y_1740_V p_239880_3_) {
        g_2336_b resourcelocation = p_239880_3_.n_1700_B(p_239880_1_, p_239880_2_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239880_1_, resourcelocation));
    }

    private n_1700_B n_1700_B(T_2915_h p_239884_1_, g_221_o p_239884_2_) {
        return new n_1700_B(p_239884_2_.J_1907_R()).n_1700_B(p_239884_1_, p_239884_2_.n_1700_B());
    }

    private n_1700_B G_564_y(T_2915_h p_239967_1_, g_221_o.n_1700_B p_239967_2_) {
        g_221_o texturedmodel = p_239967_2_.get(p_239967_1_);
        return new n_1700_B(texturedmodel.J_1907_R()).n_1700_B(p_239967_1_, texturedmodel.n_1700_B());
    }

    private n_1700_B u_1723_Y(T_2915_h p_239984_1_) {
        return this.G_564_y(p_239984_1_, g_221_o.n_1700_B);
    }

    private n_1700_B n_1700_B(H_2857_Y p_239905_1_) {
        return new n_1700_B(p_239905_1_);
    }

    private void v_4262_N(T_2915_h p_239991_1_) {
        H_2857_Y modeltextures = H_2857_Y.M_182_A(p_239991_1_);
        g_2336_b resourcelocation = G_624_v.Q_4569_t.n_1700_B(p_239991_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.M_182_A.n_1700_B(p_239991_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.t_1786_h.n_1700_B(p_239991_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation3 = G_624_v.multiplayerClientSuggestionProvider.n_1700_B(p_239991_1_, modeltextures, this.J_1907_R);
        this.n_1700_B(p_239991_1_.u_1723_Y());
        this.n_1700_B.accept(v_4262_N.n_1700_B(p_239991_1_, resourcelocation, resourcelocation1, resourcelocation2, resourcelocation3));
    }

    private void w_1484_f(T_2915_h p_239998_1_) {
        H_2857_Y modeltextures = H_2857_Y.J_1907_R(p_239998_1_);
        g_2336_b resourcelocation = G_624_v.z_4693_k.n_1700_B(p_239998_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.g_221_o.n_1700_B(p_239998_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.e_2887_G.n_1700_B(p_239998_1_, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239998_1_, resourcelocation, resourcelocation1, resourcelocation2));
        this.n_1700_B(p_239998_1_, resourcelocation1);
    }

    private void t_148_a(T_2915_h p_240004_1_) {
        H_2857_Y modeltextures = H_2857_Y.J_1907_R(p_240004_1_);
        g_2336_b resourcelocation = G_624_v.G_624_v.n_1700_B(p_240004_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.T_2506_i.n_1700_B(p_240004_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.q_4610_l.n_1700_B(p_240004_1_, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.G_564_y(p_240004_1_, resourcelocation, resourcelocation1, resourcelocation2));
        this.n_1700_B(p_240004_1_, resourcelocation1);
    }

    private R_4764_Y s_956_w(T_2915_h p_240009_1_) {
        return new R_4764_Y(H_2857_Y.M_588_G(p_240009_1_));
    }

    private void u_2550_I(T_2915_h p_240014_1_) {
        this.n_1700_B(p_240014_1_, p_240014_1_);
    }

    private void n_1700_B(T_2915_h p_239872_1_, T_2915_h p_239872_2_) {
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239872_1_, A_4115_X.n_1700_B(p_239872_2_)));
    }

    private void n_1700_B(T_2915_h p_239877_1_, G_564_y p_239877_2_) {
        this.J_1907_R(p_239877_1_);
        this.J_1907_R(p_239877_1_, p_239877_2_);
    }

    private void n_1700_B(T_2915_h p_239878_1_, G_564_y p_239878_2_, H_2857_Y p_239878_3_) {
        this.J_1907_R(p_239878_1_);
        this.J_1907_R(p_239878_1_, p_239878_2_, p_239878_3_);
    }

    private void J_1907_R(T_2915_h p_239937_1_, G_564_y p_239937_2_) {
        H_2857_Y modeltextures = H_2857_Y.R_4764_Y(p_239937_1_);
        this.J_1907_R(p_239937_1_, p_239937_2_, modeltextures);
    }

    private void J_1907_R(T_2915_h p_239938_1_, G_564_y p_239938_2_, H_2857_Y p_239938_3_) {
        g_2336_b resourcelocation = p_239938_2_.n_1700_B().n_1700_B(p_239938_1_, p_239938_3_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239938_1_, resourcelocation));
    }

    private void n_1700_B(T_2915_h p_239874_1_, T_2915_h p_239874_2_, G_564_y p_239874_3_) {
        this.n_1700_B(p_239874_1_, p_239874_3_);
        H_2857_Y modeltextures = H_2857_Y.G_564_y(p_239874_1_);
        g_2336_b resourcelocation = p_239874_3_.J_1907_R().n_1700_B(p_239874_2_, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239874_2_, resourcelocation));
    }

    private void J_1907_R(T_2915_h p_239935_1_, T_2915_h p_239935_2_) {
        g_221_o texturedmodel = g_221_o.u_2550_I.get(p_239935_1_);
        g_2336_b resourcelocation = texturedmodel.n_1700_B(p_239935_1_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239935_1_, resourcelocation));
        g_2336_b resourcelocation1 = G_624_v.s_2632_s.n_1700_B(p_239935_2_, texturedmodel.J_1907_R(), this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239935_2_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B(v_4262_N.J_1907_R()));
        this.J_1907_R(p_239935_1_);
    }

    private void R_4764_Y(T_2915_h p_239954_1_, T_2915_h p_239954_2_) {
        this.n_1700_B(p_239954_1_.u_1723_Y());
        H_2857_Y modeltextures = H_2857_Y.v_4262_N(p_239954_1_);
        H_2857_Y modeltextures1 = H_2857_Y.n_1700_B(p_239954_1_, p_239954_2_);
        g_2336_b resourcelocation = G_624_v.UploadStatus.n_1700_B(p_239954_2_, modeltextures1, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239954_2_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.q_4610_l).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B()).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y))));
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239954_1_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.i_1637_u).n_1700_B((T1 p_239881_3_) -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.j_276_v[p_239881_3_].n_1700_B(p_239954_1_, modeltextures, this.J_1907_R)))));
    }

    private void n_1700_B(T_2915_h p_239873_1_, T_2915_h p_239873_2_, T_2915_h p_239873_3_, T_2915_h p_239873_4_, T_2915_h p_239873_5_, T_2915_h p_239873_6_, T_2915_h p_239873_7_, T_2915_h p_239873_8_) {
        this.n_1700_B(p_239873_1_, G_564_y.J_1907_R);
        this.n_1700_B(p_239873_2_, G_564_y.J_1907_R);
        this.P_1922_E(p_239873_3_);
        this.P_1922_E(p_239873_4_);
        this.J_1907_R(p_239873_5_, p_239873_7_);
        this.J_1907_R(p_239873_6_, p_239873_8_);
    }

    private void R_4764_Y(T_2915_h p_239955_1_, G_564_y p_239955_2_) {
        this.n_1700_B(p_239955_1_, "_top");
        g_2336_b resourcelocation = this.n_1700_B(p_239955_1_, "_top", p_239955_2_.n_1700_B(), H_2857_Y::R_4764_Y);
        g_2336_b resourcelocation1 = this.n_1700_B(p_239955_1_, "_bottom", p_239955_2_.n_1700_B(), H_2857_Y::R_4764_Y);
        this.u_1723_Y(p_239955_1_, resourcelocation, resourcelocation1);
    }

    private void v_4262_N() {
        this.n_1700_B(a_3742_W.V_983_n, "_front");
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.V_983_n, "_top");
        g_2336_b resourcelocation1 = this.n_1700_B(a_3742_W.V_983_n, "_bottom", G_564_y.J_1907_R.n_1700_B(), H_2857_Y::R_4764_Y);
        this.u_1723_Y(a_3742_W.V_983_n, resourcelocation, resourcelocation1);
    }

    private void w_1484_f() {
        g_2336_b resourcelocation = this.n_1700_B(a_3742_W.LongRunningTask, "_top", G_624_v.M_1641_O, H_2857_Y::n_1700_B);
        g_2336_b resourcelocation1 = this.n_1700_B(a_3742_W.LongRunningTask, "_bottom", G_624_v.M_1641_O, H_2857_Y::n_1700_B);
        this.u_1723_Y(a_3742_W.LongRunningTask, resourcelocation, resourcelocation1);
    }

    private void u_1723_Y(T_2915_h p_240011_1_, g_2336_b p_240011_2_, g_2336_b p_240011_3_) {
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240011_1_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.T_3594_S).n_1700_B(g_3212_H.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240011_3_)).n_1700_B(g_3212_H.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_240011_2_))));
    }

    private void M_588_G(T_2915_h p_240018_1_) {
        H_2857_Y modeltextures = H_2857_Y.P_1922_E(p_240018_1_);
        H_2857_Y modeltextures1 = H_2857_Y.P_1922_E(H_2857_Y.n_1700_B(p_240018_1_, "_corner"));
        g_2336_b resourcelocation = G_624_v.H_1990_U.n_1700_B(p_240018_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.N_2525_X.n_1700_B(p_240018_1_, modeltextures1, this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.c_4037_x.n_1700_B(p_240018_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation3 = G_624_v.g_2268_R.n_1700_B(p_240018_1_, modeltextures, this.J_1907_R);
        this.J_1907_R(p_240018_1_);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240018_1_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.s_2632_s).n_1700_B(w_801_N.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(w_801_N.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(w_801_N.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(w_801_N.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(w_801_N.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2)).n_1700_B(w_801_N.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3)).n_1700_B(w_801_N.v_4262_N, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B(w_801_N.w_1484_f, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(w_801_N.t_148_a, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(w_801_N.s_956_w, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y))));
    }

    private void P_4830_p(T_2915_h p_240021_1_) {
        g_2336_b resourcelocation = this.n_1700_B(p_240021_1_, "", G_624_v.H_1990_U, H_2857_Y::P_1922_E);
        g_2336_b resourcelocation1 = this.n_1700_B(p_240021_1_, "", G_624_v.c_4037_x, H_2857_Y::P_1922_E);
        g_2336_b resourcelocation2 = this.n_1700_B(p_240021_1_, "", G_624_v.g_2268_R, H_2857_Y::P_1922_E);
        g_2336_b resourcelocation3 = this.n_1700_B(p_240021_1_, "_on", G_624_v.H_1990_U, H_2857_Y::P_1922_E);
        g_2336_b resourcelocation4 = this.n_1700_B(p_240021_1_, "_on", G_624_v.c_4037_x, H_2857_Y::P_1922_E);
        g_2336_b resourcelocation5 = this.n_1700_B(p_240021_1_, "_on", G_624_v.g_2268_R, H_2857_Y::P_1922_E);
        s_956_w blockstatevariantbuilder = s_956_w.n_1700_B(BlockStateProperties.C_2741_M, BlockStateProperties.l_1233_K).n_1700_B((T1 p_239919_6_, T2 p_239919_7_) -> {
            switch (p_239919_7_) {
                case n_1700_B: {
                    return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239919_6_ != false ? resourcelocation3 : resourcelocation);
                }
                case J_1907_R: {
                    return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239919_6_ != false ? resourcelocation3 : resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R);
                }
                case R_4764_Y: {
                    return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239919_6_ != false ? resourcelocation4 : resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R);
                }
                case G_564_y: {
                    return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239919_6_ != false ? resourcelocation5 : resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R);
                }
                case P_1922_E: {
                    return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239919_6_ != false ? resourcelocation4 : resourcelocation1);
                }
                case u_1723_Y: {
                    return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239919_6_ != false ? resourcelocation5 : resourcelocation2);
                }
            }
            throw new UnsupportedOperationException("Fix you generator!");
        });
        this.J_1907_R(p_240021_1_);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240021_1_).n_1700_B(blockstatevariantbuilder));
    }

    private J_1907_R n_1700_B(g_2336_b p_239916_1_, T_2915_h p_239916_2_) {
        return new J_1907_R(p_239916_1_, p_239916_2_);
    }

    private J_1907_R G_564_y(T_2915_h p_239966_1_, T_2915_h p_239966_2_) {
        return new J_1907_R(A_4115_X.n_1700_B(p_239966_1_), p_239966_2_);
    }

    private void n_1700_B(T_2915_h p_239871_1_, q_1613_l p_239871_2_) {
        g_2336_b resourcelocation = G_624_v.x_607_J.n_1700_B(p_239871_1_, H_2857_Y.n_1700_B(p_239871_2_), this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239871_1_, resourcelocation));
    }

    private void u_1723_Y(T_2915_h p_239993_1_, g_2336_b p_239993_2_) {
        g_2336_b resourcelocation = G_624_v.x_607_J.n_1700_B(p_239993_1_, H_2857_Y.v_4262_N(p_239993_2_), this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239993_1_, resourcelocation));
    }

    private void P_1922_E(T_2915_h p_239976_1_, T_2915_h p_239976_2_) {
        this.R_4764_Y(p_239976_1_, g_221_o.n_1700_B);
        g_2336_b resourcelocation = g_221_o.t_148_a.get(p_239976_1_).n_1700_B(p_239976_2_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239976_2_, resourcelocation));
    }

    private void n_1700_B(g_221_o.n_1700_B p_239907_1_, T_2915_h ... p_239907_2_) {
        for (T_2915_h block : p_239907_2_) {
            g_2336_b resourcelocation = p_239907_1_.n_1700_B(block, this.J_1907_R);
            this.n_1700_B.accept(v_4262_N.J_1907_R(block, resourcelocation));
        }
    }

    private void J_1907_R(g_221_o.n_1700_B p_239948_1_, T_2915_h ... p_239948_2_) {
        for (T_2915_h block : p_239948_2_) {
            g_2336_b resourcelocation = p_239948_1_.n_1700_B(block, this.J_1907_R);
            this.n_1700_B.accept(w_1457_N.n_1700_B(block, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(v_4262_N.R_4764_Y()));
        }
    }

    private void u_1723_Y(T_2915_h p_239985_1_, T_2915_h p_239985_2_) {
        this.P_1922_E(p_239985_1_);
        H_2857_Y modeltextures = H_2857_Y.J_1907_R(p_239985_1_, p_239985_2_);
        g_2336_b resourcelocation = G_624_v.i_1637_u.n_1700_B(p_239985_2_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.Ping.n_1700_B(p_239985_2_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.p_178_J.n_1700_B(p_239985_2_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation3 = G_624_v.r_715_M.n_1700_B(p_239985_2_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation4 = G_624_v.A_1038_p.n_1700_B(p_239985_2_, modeltextures, this.J_1907_R);
        q_1613_l item = p_239985_2_.u_1723_Y();
        G_624_v.ValueObject.n_1700_B(A_4115_X.n_1700_B(item), H_2857_Y.H_2857_Y(p_239985_1_), this.J_1907_R);
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(p_239985_2_).n_1700_B(P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)));
    }

    private void h_1847_R(T_2915_h p_240023_1_) {
        H_2857_Y modeltextures = H_2857_Y.Q_2552_b(p_240023_1_);
        g_2336_b resourcelocation = G_624_v.RealmsClientConfig.n_1700_B(p_240023_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = this.n_1700_B(p_240023_1_, "_conditional", G_624_v.RealmsClientConfig, (g_2336_b p_239947_1_) -> modeltextures.J_1907_R(T_2506_i.t_148_a, (g_2336_b)p_239947_1_));
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240023_1_).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.R_4764_Y, resourcelocation1, resourcelocation)).n_1700_B(v_4262_N.P_1922_E()));
    }

    private void Q_4569_t(T_2915_h p_240025_1_) {
        g_2336_b resourcelocation = g_221_o.P_4830_p.n_1700_B(p_240025_1_, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240025_1_, resourcelocation).n_1700_B(v_4262_N.R_4764_Y()));
    }

    private List<P_1922_E> n_1700_B(int p_239864_1_) {
        String s = "_age" + p_239864_1_;
        return IntStream.range(1, 5).mapToObj(p_239913_1_ -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.t_1509_b, p_239913_1_ + s))).collect(Collectors.toList());
    }

    private void t_148_a() {
        this.n_1700_B(a_3742_W.t_1509_b);
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.t_1509_b).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1333_t, 0), this.n_1700_B(0)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1333_t, 1), this.n_1700_B(1)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.l_4537_E, d_2484_X.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.t_1509_b, "_small_leaves"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.l_4537_E, d_2484_X.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.t_1509_b, "_large_leaves"))));
    }

    private s_956_w s_956_w() {
        return s_956_w.n_1700_B(BlockStateProperties.G_624_v).n_1700_B(b_257_Y.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.J_1907_R, P_1922_E.n_1700_B()).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R));
    }

    private void u_2550_I() {
        g_2336_b resourcelocation = H_2857_Y.n_1700_B(a_3742_W.y_254_d, "_top_open");
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.y_254_d).n_1700_B(this.s_956_w()).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.Y_259_p).n_1700_B((Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, g_221_o.P_1922_E.n_1700_B(a_3742_W.y_254_d, this.J_1907_R))).n_1700_B((Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, g_221_o.P_1922_E.get(a_3742_W.y_254_d).n_1700_B((H_2857_Y p_239973_1_) -> p_239973_1_.n_1700_B(T_2506_i.u_1723_Y, resourcelocation)).n_1700_B(a_3742_W.y_254_d, "_open", this.J_1907_R)))));
    }

    private static <T extends Comparable<T>> s_956_w n_1700_B(v_3760_Q<T> p_239895_0_, T p_239895_1_, g_2336_b p_239895_2_, g_2336_b p_239895_3_) {
        P_1922_E blockmodeldefinition = P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239895_2_);
        P_1922_E blockmodeldefinition1 = P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239895_3_);
        return s_956_w.n_1700_B(p_239895_0_).n_1700_B((T1 p_239909_3_) -> {
            boolean flag = p_239909_3_.compareTo(p_239895_1_) >= 0;
            return flag ? blockmodeldefinition : blockmodeldefinition1;
        });
    }

    private void n_1700_B(T_2915_h p_239887_1_, Function<T_2915_h, H_2857_Y> p_239887_2_) {
        H_2857_Y modeltextures = p_239887_2_.apply(p_239887_1_).n_1700_B(T_2506_i.t_148_a, T_2506_i.R_4764_Y);
        H_2857_Y modeltextures1 = modeltextures.J_1907_R(T_2506_i.v_4262_N, H_2857_Y.n_1700_B(p_239887_1_, "_front_honey"));
        g_2336_b resourcelocation = G_624_v.s_956_w.n_1700_B(p_239887_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.s_956_w.n_1700_B(p_239887_1_, "_honey", modeltextures1, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239887_1_).n_1700_B(v_4262_N.J_1907_R()).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.t_4219_U, 5, resourcelocation1, resourcelocation)));
    }

    private void n_1700_B(T_2915_h p_239876_1_, v_3760_Q<Integer> p_239876_2_, int ... p_239876_3_) {
        if (p_239876_2_.n_1700_B().size() != p_239876_3_.length) {
            throw new IllegalArgumentException();
        }
        Int2ObjectOpenHashMap int2objectmap = new Int2ObjectOpenHashMap();
        s_956_w blockstatevariantbuilder = s_956_w.n_1700_B(p_239876_2_).n_1700_B(arg_0 -> this.n_1700_B(p_239876_3_, (Int2ObjectMap)int2objectmap, p_239876_1_, arg_0));
        this.n_1700_B(p_239876_1_.u_1723_Y());
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239876_1_).n_1700_B(blockstatevariantbuilder));
    }

    private void M_588_G() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.l_3370_o, "_floor");
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.l_3370_o, "_ceiling");
        g_2336_b resourcelocation2 = A_4115_X.n_1700_B(a_3742_W.l_3370_o, "_wall");
        g_2336_b resourcelocation3 = A_4115_X.n_1700_B(a_3742_W.l_3370_o, "_between_walls");
        this.n_1700_B(Items.SpongeBlock);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.l_3370_o).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.q_4610_l, BlockStateProperties.e_2887_G).n_1700_B(b_257_Y.R_4764_Y, l_1530_z.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(b_257_Y.G_564_y, l_1530_z.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, l_1530_z.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, l_1530_z.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, l_1530_z.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B(b_257_Y.G_564_y, l_1530_z.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.u_1723_Y, l_1530_z.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.P_1922_E, l_1530_z.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.R_4764_Y, l_1530_z.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.G_564_y, l_1530_z.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.u_1723_Y, l_1530_z.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2)).n_1700_B(b_257_Y.P_1922_E, l_1530_z.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.G_564_y, l_1530_z.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.R_4764_Y, l_1530_z.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(b_257_Y.u_1723_Y, l_1530_z.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3)).n_1700_B(b_257_Y.P_1922_E, l_1530_z.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y))));
    }

    private void P_4830_p() {
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.v_2826_q, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.v_2826_q))).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.g_221_o, BlockStateProperties.q_4610_l).n_1700_B(F_2203_T.n_1700_B, b_257_Y.R_4764_Y, P_1922_E.n_1700_B()).n_1700_B(F_2203_T.n_1700_B, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y))));
    }

    private void P_1922_E(T_2915_h p_239977_1_, g_221_o.n_1700_B p_239977_2_) {
        g_2336_b resourcelocation = p_239977_2_.n_1700_B(p_239977_1_, this.J_1907_R);
        g_2336_b resourcelocation1 = H_2857_Y.n_1700_B(p_239977_1_, "_front_on");
        g_2336_b resourcelocation2 = p_239977_2_.get(p_239977_1_).n_1700_B((H_2857_Y p_239963_1_) -> p_239963_1_.n_1700_B(T_2506_i.v_4262_N, resourcelocation1)).n_1700_B(p_239977_1_, "_on", this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239977_1_).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, resourcelocation2, resourcelocation)).n_1700_B(v_4262_N.J_1907_R()));
    }

    private void n_1700_B(T_2915_h ... p_239921_1_) {
        g_2336_b resourcelocation = A_4115_X.n_1700_B("campfire_off");
        for (T_2915_h block : p_239921_1_) {
            g_2336_b resourcelocation1 = G_624_v.PlayerInfo.n_1700_B(block, H_2857_Y.c_3005_b(block), this.J_1907_R);
            this.n_1700_B(block.u_1723_Y());
            this.n_1700_B.accept(w_1457_N.n_1700_B(block).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, resourcelocation1, resourcelocation)).n_1700_B(v_4262_N.R_4764_Y()));
        }
    }

    private void h_1847_R() {
        H_2857_Y modeltextures = H_2857_Y.n_1700_B(H_2857_Y.A_4115_X(a_3742_W.UploadTokenCache), H_2857_Y.A_4115_X(a_3742_W.h_1847_R));
        g_2336_b resourcelocation = G_624_v.P_1922_E.n_1700_B(a_3742_W.UploadTokenCache, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(a_3742_W.UploadTokenCache, resourcelocation));
    }

    private void Q_4569_t() {
        this.n_1700_B(Items.v_570_f);
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.P_5000_x).n_1700_B(k_2293_S.n_1700_B(k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.N_2525_X, y_1030_X.R_4764_Y).n_1700_B(BlockStateProperties.H_1990_U, y_1030_X.R_4764_Y).n_1700_B(BlockStateProperties.c_4037_x, y_1030_X.R_4764_Y).n_1700_B(BlockStateProperties.g_2268_R, y_1030_X.R_4764_Y), k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.N_2525_X, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}).n_1700_B(BlockStateProperties.H_1990_U, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}), k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.H_1990_U, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}).n_1700_B(BlockStateProperties.c_4037_x, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}), k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.c_4037_x, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}).n_1700_B(BlockStateProperties.g_2268_R, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}), k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.g_2268_R, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}).n_1700_B(BlockStateProperties.N_2525_X, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B})), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_dot"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.N_2525_X, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_side0"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.c_4037_x, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_side_alt0"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.H_1990_U, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_side_alt1")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.g_2268_R, (Comparable)((Object)y_1030_X.J_1907_R), (Comparable[])new y_1030_X[]{y_1030_X.n_1700_B}), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_side1")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.N_2525_X, y_1030_X.n_1700_B), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_up"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.H_1990_U, y_1030_X.n_1700_B), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_up")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.c_4037_x, y_1030_X.n_1700_B), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_up")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.g_2268_R, y_1030_X.n_1700_B), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B("redstone_dust_up")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)));
    }

    private void M_182_A() {
        this.n_1700_B(Items.f_4340_D);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.N_4006_T).n_1700_B(v_4262_N.R_4764_Y()).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.RealmsWorldResetDto, BlockStateProperties.C_2741_M).n_1700_B(Y_4489_t.n_1700_B, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.N_4006_T))).n_1700_B(Y_4489_t.n_1700_B, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.N_4006_T, "_on"))).n_1700_B(Y_4489_t.J_1907_R, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.N_4006_T, "_subtract"))).n_1700_B(Y_4489_t.J_1907_R, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.N_4006_T, "_on_subtract")))));
    }

    private void t_1786_h() {
        H_2857_Y modeltextures = H_2857_Y.n_1700_B(a_3742_W.SuperFirework);
        H_2857_Y modeltextures1 = H_2857_Y.n_1700_B(H_2857_Y.n_1700_B(a_3742_W.MoveHelper, "_side"), modeltextures.n_1700_B(T_2506_i.u_1723_Y));
        g_2336_b resourcelocation = G_624_v.e_4240_b.n_1700_B(a_3742_W.MoveHelper, modeltextures1, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.n_3318_d.n_1700_B(a_3742_W.MoveHelper, modeltextures1, this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.P_1922_E.J_1907_R(a_3742_W.MoveHelper, "_double", modeltextures1, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.P_1922_E(a_3742_W.MoveHelper, resourcelocation, resourcelocation1, resourcelocation2));
        this.n_1700_B.accept(v_4262_N.R_4764_Y(a_3742_W.SuperFirework, G_624_v.R_4764_Y.n_1700_B(a_3742_W.SuperFirework, modeltextures, this.J_1907_R)));
    }

    private void multiplayerClientSuggestionProvider() {
        this.n_1700_B(Items.Q_1036_Q);
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.e_837_t).n_1700_B(P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.A_4115_X(a_3742_W.e_837_t))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.u_2550_I, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.e_837_t, "_bottle0"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.M_588_G, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.e_837_t, "_bottle1"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.P_4830_p, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.e_837_t, "_bottle2"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.u_2550_I, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.e_837_t, "_empty0"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.M_588_G, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.e_837_t, "_empty1"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.P_4830_p, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.e_837_t, "_empty2"))));
    }

    private void M_182_A(T_2915_h p_240027_1_) {
        g_2336_b resourcelocation = G_624_v.R_3908_n.n_1700_B(p_240027_1_, H_2857_Y.J_1907_R(p_240027_1_), this.J_1907_R);
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B("mushroom_block_inside");
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(p_240027_1_).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.e_4240_b, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.n_3318_d, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, false)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, false)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, false)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.e_4240_b, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, false)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.n_3318_d, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, false)));
        this.n_1700_B(p_240027_1_, g_221_o.n_1700_B.n_1700_B(p_240027_1_, "_inventory", this.J_1907_R));
    }

    private void w_1457_N() {
        this.n_1700_B(Items.I_3736_z);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.a_178_J).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.RealmsClientConfig).n_1700_B((Integer)0, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.a_178_J))).n_1700_B((Integer)1, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.a_178_J, "_slice1"))).n_1700_B((Integer)2, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.a_178_J, "_slice2"))).n_1700_B((Integer)3, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.a_178_J, "_slice3"))).n_1700_B((Integer)4, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.a_178_J, "_slice4"))).n_1700_B((Integer)5, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.a_178_J, "_slice5"))).n_1700_B((Integer)6, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.a_178_J, "_slice6")))));
    }

    private void Y_601_j() {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.j_1376_w, "_side3")).n_1700_B(T_2506_i.Q_4569_t, H_2857_Y.A_4115_X(a_3742_W.w_1457_N)).n_1700_B(T_2506_i.h_1847_R, H_2857_Y.n_1700_B(a_3742_W.j_1376_w, "_top")).n_1700_B(T_2506_i.s_956_w, H_2857_Y.n_1700_B(a_3742_W.j_1376_w, "_side3")).n_1700_B(T_2506_i.M_588_G, H_2857_Y.n_1700_B(a_3742_W.j_1376_w, "_side3")).n_1700_B(T_2506_i.u_2550_I, H_2857_Y.n_1700_B(a_3742_W.j_1376_w, "_side1")).n_1700_B(T_2506_i.P_4830_p, H_2857_Y.n_1700_B(a_3742_W.j_1376_w, "_side2"));
        this.n_1700_B.accept(v_4262_N.R_4764_Y(a_3742_W.j_1376_w, G_624_v.n_1700_B.n_1700_B(a_3742_W.j_1376_w, modeltextures, this.J_1907_R)));
    }

    private void Y_259_p() {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.i_4833_u, "_front")).n_1700_B(T_2506_i.Q_4569_t, H_2857_Y.n_1700_B(a_3742_W.i_4833_u, "_bottom")).n_1700_B(T_2506_i.h_1847_R, H_2857_Y.n_1700_B(a_3742_W.i_4833_u, "_top")).n_1700_B(T_2506_i.s_956_w, H_2857_Y.n_1700_B(a_3742_W.i_4833_u, "_front")).n_1700_B(T_2506_i.u_2550_I, H_2857_Y.n_1700_B(a_3742_W.i_4833_u, "_front")).n_1700_B(T_2506_i.M_588_G, H_2857_Y.n_1700_B(a_3742_W.i_4833_u, "_side")).n_1700_B(T_2506_i.P_4830_p, H_2857_Y.n_1700_B(a_3742_W.i_4833_u, "_side"));
        this.n_1700_B.accept(v_4262_N.R_4764_Y(a_3742_W.i_4833_u, G_624_v.n_1700_B.n_1700_B(a_3742_W.i_4833_u, modeltextures, this.J_1907_R)));
    }

    private void n_1700_B(T_2915_h p_239875_1_, T_2915_h p_239875_2_, BiFunction<T_2915_h, T_2915_h, H_2857_Y> p_239875_3_) {
        H_2857_Y modeltextures = p_239875_3_.apply(p_239875_1_, p_239875_2_);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_239875_1_, G_624_v.n_1700_B.n_1700_B(p_239875_1_, modeltextures, this.J_1907_R)));
    }

    private void Q_2552_b() {
        H_2857_Y modeltextures = H_2857_Y.s_956_w(a_3742_W.A_3244_K);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(a_3742_W.A_3244_K, A_4115_X.n_1700_B(a_3742_W.A_3244_K)));
        this.n_1700_B(a_3742_W.X_2048_Y, modeltextures);
        this.n_1700_B(a_3742_W.l_2647_k, modeltextures);
    }

    private void n_1700_B(T_2915_h p_239879_1_, H_2857_Y p_239879_2_) {
        g_2336_b resourcelocation = G_624_v.t_148_a.n_1700_B(p_239879_1_, p_239879_2_.J_1907_R(T_2506_i.v_4262_N, H_2857_Y.A_4115_X(p_239879_1_)), this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239879_1_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(v_4262_N.J_1907_R()));
    }

    private void C_2741_M() {
        this.n_1700_B(Items.TickTrigger);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.m_1621_v).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.k_3961_g).n_1700_B((Integer)0, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.m_1621_v))).n_1700_B((Integer)1, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.m_1621_v, "_level1"))).n_1700_B((Integer)2, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.m_1621_v, "_level2"))).n_1700_B((Integer)3, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.m_1621_v, "_level3")))));
    }

    private void v_4262_N(T_2915_h p_239992_1_, T_2915_h p_239992_2_) {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.G_564_y, H_2857_Y.n_1700_B(p_239992_2_, "_top")).n_1700_B(T_2506_i.t_148_a, H_2857_Y.A_4115_X(p_239992_1_));
        this.n_1700_B(p_239992_1_, modeltextures, G_624_v.P_1922_E);
    }

    private void k_2293_S() {
        H_2857_Y modeltextures = H_2857_Y.J_1907_R(a_3742_W.ChorusExploit);
        g_2336_b resourcelocation = G_624_v.z_1333_t.n_1700_B(a_3742_W.ChorusExploit, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = this.n_1700_B(a_3742_W.ChorusExploit, "_dead", G_624_v.z_1333_t, (g_2336_b p_239906_1_) -> modeltextures.J_1907_R(T_2506_i.J_1907_R, (g_2336_b)p_239906_1_));
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.ChorusExploit).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.A_1038_p, 5, resourcelocation1, resourcelocation)));
    }

    private void t_1786_h(T_2915_h p_240029_1_) {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(a_3742_W.P_925_e, "_top")).n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(a_3742_W.P_925_e, "_side")).n_1700_B(T_2506_i.v_4262_N, H_2857_Y.n_1700_B(p_240029_1_, "_front"));
        H_2857_Y modeltextures1 = new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(a_3742_W.P_925_e, "_top")).n_1700_B(T_2506_i.v_4262_N, H_2857_Y.n_1700_B(p_240029_1_, "_front_vertical"));
        g_2336_b resourcelocation = G_624_v.t_148_a.n_1700_B(p_240029_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.u_2550_I.n_1700_B(p_240029_1_, modeltextures1, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240029_1_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.G_624_v).n_1700_B(b_257_Y.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y))));
    }

    private void q_2307_F() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.l_2995_s);
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.l_2995_s, "_filled");
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.l_2995_s).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.w_1484_f).n_1700_B((Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B((Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1))).n_1700_B(v_4262_N.R_4764_Y()));
    }

    private void Z_875_P() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.ChestStealer, "_side");
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.ChestStealer, "_noside");
        g_2336_b resourcelocation2 = A_4115_X.n_1700_B(a_3742_W.ChestStealer, "_noside1");
        g_2336_b resourcelocation3 = A_4115_X.n_1700_B(a_3742_W.ChestStealer, "_noside2");
        g_2336_b resourcelocation4 = A_4115_X.n_1700_B(a_3742_W.ChestStealer, "_noside3");
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.ChestStealer).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.e_4240_b, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.n_3318_d, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.P_1922_E, 2), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.P_1922_E, 2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.P_1922_E, 2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.P_1922_E, 2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.e_4240_b, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.P_1922_E, 2).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.G_564_y, true)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.n_3318_d, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.P_1922_E, 2).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.G_564_y, true)));
    }

    private void c_3005_b() {
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.P_2068_y).n_1700_B(P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.A_4115_X(a_3742_W.P_2068_y))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 1), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents1"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 2), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents2"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 3), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents3"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 4), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents4"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 5), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents5"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 6), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents6"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 7), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents7"))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.Ops, 8), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.P_2068_y, "_contents_ready"))));
    }

    private void multiplayerClientSuggestionProvider(T_2915_h p_240031_1_) {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.P_1922_E, H_2857_Y.A_4115_X(a_3742_W.i_3196_G)).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.A_4115_X(p_240031_1_)).n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240031_1_, "_side"));
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240031_1_, G_624_v.w_1484_f.n_1700_B(p_240031_1_, modeltextures, this.J_1907_R)));
    }

    private void H_2857_Y() {
        g_2336_b resourcelocation = H_2857_Y.n_1700_B(a_3742_W.k_1608_N, "_side");
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(a_3742_W.k_1608_N, "_top")).n_1700_B(T_2506_i.t_148_a, resourcelocation);
        H_2857_Y modeltextures1 = new H_2857_Y().n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(a_3742_W.k_1608_N, "_inverted_top")).n_1700_B(T_2506_i.t_148_a, resourcelocation);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.k_1608_N).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.M_182_A).n_1700_B((Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.O_508_d.n_1700_B(a_3742_W.k_1608_N, modeltextures, this.J_1907_R))).n_1700_B((Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.O_508_d.n_1700_B(A_4115_X.n_1700_B(a_3742_W.k_1608_N, "_inverted"), modeltextures1, this.J_1907_R)))));
    }

    private void w_1457_N(T_2915_h p_239839_1_) {
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239839_1_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(p_239839_1_))).n_1700_B(this.s_956_w()));
    }

    private void A_4115_X() {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.H_2857_Y, H_2857_Y.A_4115_X(a_3742_W.s_956_w)).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.A_4115_X(a_3742_W.Z_735_d));
        H_2857_Y modeltextures1 = new H_2857_Y().n_1700_B(T_2506_i.H_2857_Y, H_2857_Y.A_4115_X(a_3742_W.s_956_w)).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(a_3742_W.Z_735_d, "_moist"));
        g_2336_b resourcelocation = G_624_v.D_60_a.n_1700_B(a_3742_W.Z_735_d, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.D_60_a.n_1700_B(H_2857_Y.n_1700_B(a_3742_W.Z_735_d, "_moist"), modeltextures1, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.Z_735_d).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.PlayerInfo, 7, resourcelocation1, resourcelocation)));
    }

    private List<g_2336_b> Y_601_j(T_2915_h p_240033_1_) {
        g_2336_b resourcelocation = G_624_v.k_3961_g.n_1700_B(A_4115_X.n_1700_B(p_240033_1_, "_floor0"), H_2857_Y.multiplayerClientSuggestionProvider(p_240033_1_), this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.k_3961_g.n_1700_B(A_4115_X.n_1700_B(p_240033_1_, "_floor1"), H_2857_Y.w_1457_N(p_240033_1_), this.J_1907_R);
        return ImmutableList.of((Object)resourcelocation, (Object)resourcelocation1);
    }

    private List<g_2336_b> Y_259_p(T_2915_h p_240035_1_) {
        g_2336_b resourcelocation = G_624_v.Ops.n_1700_B(A_4115_X.n_1700_B(p_240035_1_, "_side0"), H_2857_Y.multiplayerClientSuggestionProvider(p_240035_1_), this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.Ops.n_1700_B(A_4115_X.n_1700_B(p_240035_1_, "_side1"), H_2857_Y.w_1457_N(p_240035_1_), this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.h_4320_q.n_1700_B(A_4115_X.n_1700_B(p_240035_1_, "_side_alt0"), H_2857_Y.multiplayerClientSuggestionProvider(p_240035_1_), this.J_1907_R);
        g_2336_b resourcelocation3 = G_624_v.h_4320_q.n_1700_B(A_4115_X.n_1700_B(p_240035_1_, "_side_alt1"), H_2857_Y.w_1457_N(p_240035_1_), this.J_1907_R);
        return ImmutableList.of((Object)resourcelocation, (Object)resourcelocation1, (Object)resourcelocation2, (Object)resourcelocation3);
    }

    private List<g_2336_b> Q_2552_b(T_2915_h p_240037_1_) {
        g_2336_b resourcelocation = G_624_v.t_4219_U.n_1700_B(A_4115_X.n_1700_B(p_240037_1_, "_up0"), H_2857_Y.multiplayerClientSuggestionProvider(p_240037_1_), this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.t_4219_U.n_1700_B(A_4115_X.n_1700_B(p_240037_1_, "_up1"), H_2857_Y.w_1457_N(p_240037_1_), this.J_1907_R);
        g_2336_b resourcelocation2 = G_624_v.V_1446_Y.n_1700_B(A_4115_X.n_1700_B(p_240037_1_, "_up_alt0"), H_2857_Y.multiplayerClientSuggestionProvider(p_240037_1_), this.J_1907_R);
        g_2336_b resourcelocation3 = G_624_v.V_1446_Y.n_1700_B(A_4115_X.n_1700_B(p_240037_1_, "_up_alt1"), H_2857_Y.w_1457_N(p_240037_1_), this.J_1907_R);
        return ImmutableList.of((Object)resourcelocation, (Object)resourcelocation1, (Object)resourcelocation2, (Object)resourcelocation3);
    }

    private static List<P_1922_E> n_1700_B(List<g_2336_b> p_239914_0_, UnaryOperator<P_1922_E> p_239914_1_) {
        return p_239914_0_.stream().map(p_239950_0_ -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239950_0_)).map(p_239914_1_).collect(Collectors.toList());
    }

    private void Y_1740_V() {
        k_2293_S.J_1907_R imultipartpredicatebuilder = k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false).n_1700_B(BlockStateProperties.z_1737_N, false).n_1700_B(BlockStateProperties.v_4276_D, false).n_1700_B(BlockStateProperties.d_2461_k, false).n_1700_B(BlockStateProperties.e_4240_b, false);
        List<g_2336_b> list = this.Y_601_j(a_3742_W.x_612_B);
        List<g_2336_b> list1 = this.Y_259_p(a_3742_W.x_612_B);
        List<g_2336_b> list2 = this.Q_2552_b(a_3742_W.x_612_B);
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.x_612_B).n_1700_B((k_2293_S)imultipartpredicatebuilder, v_4262_N.n_1700_B(list, p_240016_0_ -> p_240016_0_)).n_1700_B(k_2293_S.n_1700_B(k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, true), imultipartpredicatebuilder), v_4262_N.n_1700_B(list1, p_240012_0_ -> p_240012_0_)).n_1700_B(k_2293_S.n_1700_B(k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, true), imultipartpredicatebuilder), v_4262_N.n_1700_B(list1, p_240007_0_ -> p_240007_0_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R))).n_1700_B(k_2293_S.n_1700_B(k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, true), imultipartpredicatebuilder), v_4262_N.n_1700_B(list1, p_240002_0_ -> p_240002_0_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y))).n_1700_B(k_2293_S.n_1700_B(k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, true), imultipartpredicatebuilder), v_4262_N.n_1700_B(list1, p_239996_0_ -> p_239996_0_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y))).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.e_4240_b, true), v_4262_N.n_1700_B(list2, p_239989_0_ -> p_239989_0_)));
    }

    private void t_4043_B() {
        List<g_2336_b> list = this.Y_601_j(a_3742_W.t_1446_I);
        List<g_2336_b> list1 = this.Y_259_p(a_3742_W.t_1446_I);
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.t_1446_I).n_1700_B(v_4262_N.n_1700_B(list, p_239981_0_ -> p_239981_0_)).n_1700_B(v_4262_N.n_1700_B(list1, p_239971_0_ -> p_239971_0_)).n_1700_B(v_4262_N.n_1700_B(list1, p_239961_0_ -> p_239961_0_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R))).n_1700_B(v_4262_N.n_1700_B(list1, p_239945_0_ -> p_239945_0_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y))).n_1700_B(v_4262_N.n_1700_B(list1, p_239904_0_ -> p_239904_0_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y))));
    }

    private void C_2741_M(T_2915_h p_240039_1_) {
        g_2336_b resourcelocation = g_221_o.Q_4569_t.n_1700_B(p_240039_1_, this.J_1907_R);
        g_2336_b resourcelocation1 = g_221_o.M_182_A.n_1700_B(p_240039_1_, this.J_1907_R);
        this.n_1700_B(p_240039_1_.u_1723_Y());
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240039_1_).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.s_956_w, resourcelocation1, resourcelocation)));
    }

    private void x_607_J() {
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.LeaveTracker).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.r_715_M).n_1700_B((Integer)0, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, this.n_1700_B(a_3742_W.LeaveTracker, "_0", G_624_v.R_4764_Y, H_2857_Y::J_1907_R))).n_1700_B((Integer)1, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, this.n_1700_B(a_3742_W.LeaveTracker, "_1", G_624_v.R_4764_Y, H_2857_Y::J_1907_R))).n_1700_B((Integer)2, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, this.n_1700_B(a_3742_W.LeaveTracker, "_2", G_624_v.R_4764_Y, H_2857_Y::J_1907_R))).n_1700_B((Integer)3, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, this.n_1700_B(a_3742_W.LeaveTracker, "_3", G_624_v.R_4764_Y, H_2857_Y::J_1907_R)))));
    }

    private void e_4240_b() {
        g_2336_b resourcelocation = H_2857_Y.A_4115_X(a_3742_W.s_956_w);
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.P_1922_E, resourcelocation).n_1700_B(T_2506_i.P_1922_E, T_2506_i.R_4764_Y).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(a_3742_W.t_148_a, "_top")).n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(a_3742_W.t_148_a, "_snow"));
        P_1922_E blockmodeldefinition = P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.w_1484_f.n_1700_B(a_3742_W.t_148_a, "_snow", modeltextures, this.J_1907_R));
        this.n_1700_B(a_3742_W.t_148_a, A_4115_X.n_1700_B(a_3742_W.t_148_a), blockmodeldefinition);
        g_2336_b resourcelocation1 = g_221_o.P_1922_E.get(a_3742_W.A_2714_y).n_1700_B((H_2857_Y p_239951_1_) -> p_239951_1_.n_1700_B(T_2506_i.P_1922_E, resourcelocation)).n_1700_B(a_3742_W.A_2714_y, this.J_1907_R);
        this.n_1700_B(a_3742_W.A_2714_y, resourcelocation1, blockmodeldefinition);
        g_2336_b resourcelocation2 = g_221_o.P_1922_E.get(a_3742_W.M_588_G).n_1700_B((H_2857_Y p_239917_1_) -> p_239917_1_.n_1700_B(T_2506_i.P_1922_E, resourcelocation)).n_1700_B(a_3742_W.M_588_G, this.J_1907_R);
        this.n_1700_B(a_3742_W.M_588_G, resourcelocation2, blockmodeldefinition);
    }

    private void n_1700_B(T_2915_h p_239889_1_, g_2336_b p_239889_2_, P_1922_E p_239889_3_) {
        List<P_1922_E> list = Arrays.asList(v_4262_N.n_1700_B(p_239889_2_));
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239889_1_).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.Z_875_P).n_1700_B((Boolean)true, p_239889_3_).n_1700_B((Boolean)false, list)));
    }

    private void n_3318_d() {
        this.n_1700_B(Items.M_712_N);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.U_3823_u).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.O_508_d).n_1700_B((Integer)0, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_3823_u, "_stage0"))).n_1700_B((Integer)1, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_3823_u, "_stage1"))).n_1700_B((Integer)2, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_3823_u, "_stage2")))).n_1700_B(v_4262_N.R_4764_Y()));
    }

    private void d_2427_y() {
        this.n_1700_B.accept(v_4262_N.J_1907_R(a_3742_W.InvManager, A_4115_X.n_1700_B(a_3742_W.InvManager)));
    }

    private void w_1484_f(T_2915_h p_239999_1_, T_2915_h p_239999_2_) {
        H_2857_Y modeltextures = H_2857_Y.J_1907_R(p_239999_2_);
        g_2336_b resourcelocation = G_624_v.Y_1740_V.n_1700_B(p_239999_1_, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.t_4043_B.n_1700_B(p_239999_1_, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239999_1_).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.q_1982_R, 1, resourcelocation1, resourcelocation)));
    }

    private void z_1737_N() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.p_3749_n);
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.p_3749_n, "_side");
        this.n_1700_B(Items.SoundEventRegistration);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.p_3749_n).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.T_2506_i).n_1700_B(b_257_Y.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B(b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B(b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y))));
    }

    private void t_148_a(T_2915_h p_240005_1_, T_2915_h p_240005_2_) {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(p_240005_1_);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240005_2_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)));
        this.n_1700_B(p_240005_2_, resourcelocation);
    }

    private void v_4276_D() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.Z_4720_K, "_post_ends");
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.Z_4720_K, "_post");
        g_2336_b resourcelocation2 = A_4115_X.n_1700_B(a_3742_W.Z_4720_K, "_cap");
        g_2336_b resourcelocation3 = A_4115_X.n_1700_B(a_3742_W.Z_4720_K, "_cap_alt");
        g_2336_b resourcelocation4 = A_4115_X.n_1700_B(a_3742_W.Z_4720_K, "_side");
        g_2336_b resourcelocation5 = A_4115_X.n_1700_B(a_3742_W.Z_4720_K, "_side_alt");
        this.n_1700_B.accept(multiplayerClientSuggestionProvider.n_1700_B(a_3742_W.Z_4720_K).n_1700_B(P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false).n_1700_B(BlockStateProperties.z_1737_N, false).n_1700_B(BlockStateProperties.v_4276_D, false).n_1700_B(BlockStateProperties.d_2461_k, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation1)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, true).n_1700_B(BlockStateProperties.z_1737_N, false).n_1700_B(BlockStateProperties.v_4276_D, false).n_1700_B(BlockStateProperties.d_2461_k, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false).n_1700_B(BlockStateProperties.z_1737_N, true).n_1700_B(BlockStateProperties.v_4276_D, false).n_1700_B(BlockStateProperties.d_2461_k, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation2).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false).n_1700_B(BlockStateProperties.z_1737_N, false).n_1700_B(BlockStateProperties.v_4276_D, true).n_1700_B(BlockStateProperties.d_2461_k, false), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, false).n_1700_B(BlockStateProperties.z_1737_N, false).n_1700_B(BlockStateProperties.v_4276_D, false).n_1700_B(BlockStateProperties.d_2461_k, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation3).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2427_y, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.z_1737_N, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.v_4276_D, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation5)).n_1700_B((k_2293_S)k_2293_S.n_1700_B().n_1700_B(BlockStateProperties.d_2461_k, true), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation5).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)));
        this.J_1907_R(a_3742_W.Z_4720_K);
    }

    private void k_2293_S(T_2915_h p_240041_1_) {
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240041_1_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(p_240041_1_))).n_1700_B(v_4262_N.J_1907_R()));
    }

    private void d_2461_k() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.x_92_N);
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.x_92_N, "_on");
        this.J_1907_R(a_3742_W.x_92_N);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.x_92_N).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.C_2741_M, resourcelocation, resourcelocation1)).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.g_221_o, BlockStateProperties.q_4610_l).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.R_4764_Y, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.R_4764_Y, P_1922_E.n_1700_B()).n_1700_B(F_2203_T.n_1700_B, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.n_1700_B, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.u_1723_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.G_564_y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B(F_2203_T.J_1907_R, b_257_Y.P_1922_E, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y))));
    }

    private void G_624_v() {
        this.J_1907_R(a_3742_W.S_4035_N);
        this.n_1700_B.accept(v_4262_N.J_1907_R(a_3742_W.S_4035_N, A_4115_X.n_1700_B(a_3742_W.S_4035_N)));
    }

    private void T_2506_i() {
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.M_766_z).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.t_4043_B).n_1700_B(b_257_Y.n_1700_B.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.M_766_z, "_ns"))).n_1700_B(b_257_Y.n_1700_B.R_4764_Y, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.M_766_z, "_ew")))));
    }

    private void q_4610_l() {
        g_2336_b resourcelocation = g_221_o.n_1700_B.n_1700_B(a_3742_W.i_3196_G, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.i_3196_G, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.R_4764_Y), P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y)));
    }

    private void z_4693_k() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.RegionExploit);
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.RegionExploit, "_on");
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.RegionExploit).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.C_2741_M, resourcelocation1, resourcelocation)).n_1700_B(v_4262_N.P_1922_E()));
    }

    private void g_221_o() {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.P_1922_E, H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_bottom")).n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_side"));
        g_2336_b resourcelocation = H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_top_sticky");
        g_2336_b resourcelocation1 = H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_top");
        H_2857_Y modeltextures1 = modeltextures.J_1907_R(T_2506_i.t_4043_B, resourcelocation);
        H_2857_Y modeltextures2 = modeltextures.J_1907_R(T_2506_i.t_4043_B, resourcelocation1);
        g_2336_b resourcelocation2 = A_4115_X.n_1700_B(a_3742_W.j_2266_I, "_base");
        this.n_1700_B(a_3742_W.j_2266_I, resourcelocation2, modeltextures2);
        this.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler, resourcelocation2, modeltextures1);
        g_2336_b resourcelocation3 = G_624_v.w_1484_f.n_1700_B(a_3742_W.j_2266_I, "_inventory", modeltextures.J_1907_R(T_2506_i.u_1723_Y, resourcelocation1), this.J_1907_R);
        g_2336_b resourcelocation4 = G_624_v.w_1484_f.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler, "_inventory", modeltextures.J_1907_R(T_2506_i.u_1723_Y, resourcelocation), this.J_1907_R);
        this.n_1700_B(a_3742_W.j_2266_I, resourcelocation3);
        this.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler, resourcelocation4);
    }

    private void n_1700_B(T_2915_h p_239890_1_, g_2336_b p_239890_2_, H_2857_Y p_239890_3_) {
        g_2336_b resourcelocation = G_624_v.w_612_n.n_1700_B(p_239890_1_, p_239890_3_, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_239890_1_).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.v_4262_N, p_239890_2_, resourcelocation)).n_1700_B(v_4262_N.P_1922_E()));
    }

    private void e_2887_G() {
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.x_607_J, H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_top")).n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_side"));
        H_2857_Y modeltextures1 = modeltextures.J_1907_R(T_2506_i.t_4043_B, H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_top_sticky"));
        H_2857_Y modeltextures2 = modeltextures.J_1907_R(T_2506_i.t_4043_B, H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_top"));
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.S_980_j).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.k_2293_S, BlockStateProperties.R_3908_n).n_1700_B((Boolean)false, y_1539_W.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.RealmsServerPing.n_1700_B(a_3742_W.j_2266_I, "_head", modeltextures2, this.J_1907_R))).n_1700_B((Boolean)false, y_1539_W.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.RealmsServerPing.n_1700_B(a_3742_W.j_2266_I, "_head_sticky", modeltextures1, this.J_1907_R))).n_1700_B((Boolean)true, y_1539_W.n_1700_B, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.j_1564_a.n_1700_B(a_3742_W.j_2266_I, "_head_short", modeltextures2, this.J_1907_R))).n_1700_B((Boolean)true, y_1539_W.J_1907_R, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.j_1564_a.n_1700_B(a_3742_W.j_2266_I, "_head_short_sticky", modeltextures1, this.J_1907_R)))).n_1700_B(v_4262_N.P_1922_E()));
    }

    private void B_1668_F() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.i_770_g, "_stable");
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.i_770_g, "_unstable");
        this.n_1700_B(a_3742_W.i_770_g, resourcelocation);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.i_770_g).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.J_1907_R, resourcelocation1, resourcelocation)));
    }

    private void g_164_R() {
        g_2336_b resourcelocation = g_221_o.n_1700_B.n_1700_B(a_3742_W.B_3040_x, this.J_1907_R);
        g_2336_b resourcelocation1 = this.n_1700_B(a_3742_W.B_3040_x, "_on", G_624_v.R_4764_Y, H_2857_Y::J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.B_3040_x).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, resourcelocation1, resourcelocation)));
    }

    private void s_956_w(T_2915_h p_240010_1_, T_2915_h p_240010_2_) {
        H_2857_Y modeltextures = H_2857_Y.Y_259_p(p_240010_1_);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240010_1_, G_624_v.q_1982_R.n_1700_B(p_240010_1_, modeltextures, this.J_1907_R)));
        this.n_1700_B.accept(w_1457_N.n_1700_B(p_240010_2_, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, G_624_v.dtoRealmsServerAddress.n_1700_B(p_240010_2_, modeltextures, this.J_1907_R))).n_1700_B(v_4262_N.G_564_y()));
        this.J_1907_R(p_240010_1_);
        this.n_1700_B(p_240010_2_);
    }

    private void X_933_l() {
        H_2857_Y modeltextures = H_2857_Y.Y_259_p(a_3742_W.H_1873_g);
        H_2857_Y modeltextures1 = H_2857_Y.w_1484_f(H_2857_Y.n_1700_B(a_3742_W.H_1873_g, "_off"));
        g_2336_b resourcelocation = G_624_v.q_1982_R.n_1700_B(a_3742_W.H_1873_g, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation1 = G_624_v.q_1982_R.n_1700_B(a_3742_W.H_1873_g, "_off", modeltextures1, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.H_1873_g).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, resourcelocation, resourcelocation1)));
        g_2336_b resourcelocation2 = G_624_v.dtoRealmsServerAddress.n_1700_B(a_3742_W.n_3864_h, modeltextures, this.J_1907_R);
        g_2336_b resourcelocation3 = G_624_v.dtoRealmsServerAddress.n_1700_B(a_3742_W.n_3864_h, "_off", modeltextures1, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.n_3864_h).n_1700_B(v_4262_N.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, resourcelocation2, resourcelocation3)).n_1700_B(v_4262_N.G_564_y()));
        this.J_1907_R(a_3742_W.H_1873_g);
        this.n_1700_B(a_3742_W.n_3864_h);
    }

    private void Z_976_R() {
        this.n_1700_B(Items.m_229_F);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.T_437_o).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.f_4016_n, BlockStateProperties.w_1457_N, BlockStateProperties.C_2741_M).n_1700_B((T1 p_239911_0_, T2 p_239911_1_, T3 p_239911_2_) -> {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append('_').append(p_239911_0_).append("tick");
            if (p_239911_2_.booleanValue()) {
                stringbuilder.append("_on");
            }
            if (p_239911_1_.booleanValue()) {
                stringbuilder.append("_locked");
            }
            return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.T_437_o, stringbuilder.toString()));
        })).n_1700_B(v_4262_N.R_4764_Y()));
    }

    private void H_1990_U() {
        this.n_1700_B(Items.RealmsDefaultUncaughtExceptionHandler);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.Easing).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.U_1241_n, BlockStateProperties.A_4115_X).n_1700_B((Integer)1, (Boolean)false, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("dead_sea_pickle")))).n_1700_B((Integer)2, (Boolean)false, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("two_dead_sea_pickles")))).n_1700_B((Integer)3, (Boolean)false, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("three_dead_sea_pickles")))).n_1700_B((Integer)4, (Boolean)false, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("four_dead_sea_pickles")))).n_1700_B((Integer)1, (Boolean)true, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("sea_pickle")))).n_1700_B((Integer)2, (Boolean)true, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("two_sea_pickles")))).n_1700_B((Integer)3, (Boolean)true, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("three_sea_pickles")))).n_1700_B((Integer)4, (Boolean)true, Arrays.asList(v_4262_N.n_1700_B(A_4115_X.n_1700_B("four_sea_pickles"))))));
    }

    private void N_2525_X() {
        H_2857_Y modeltextures = H_2857_Y.n_1700_B(a_3742_W.X_290_I);
        g_2336_b resourcelocation = G_624_v.R_4764_Y.n_1700_B(a_3742_W.l_697_B, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.X_290_I).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.D_60_a).n_1700_B((T1 p_239918_1_) -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, p_239918_1_ < 8 ? A_4115_X.n_1700_B(a_3742_W.X_290_I, "_height" + p_239918_1_ * 2) : resourcelocation))));
        this.n_1700_B(a_3742_W.X_290_I, A_4115_X.n_1700_B(a_3742_W.X_290_I, "_height2"));
        this.n_1700_B.accept(v_4262_N.R_4764_Y(a_3742_W.l_697_B, resourcelocation));
    }

    private void c_4037_x() {
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.f_4705_f, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.f_4705_f))).n_1700_B(v_4262_N.J_1907_R()));
    }

    private void g_2268_R() {
        g_2336_b resourcelocation = g_221_o.n_1700_B.n_1700_B(a_3742_W.l_14_c, this.J_1907_R);
        this.n_1700_B(a_3742_W.l_14_c, resourcelocation);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.l_14_c).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.S_4022_R).n_1700_B((T1 p_239896_1_) -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, this.n_1700_B(a_3742_W.l_14_c, "_" + p_239896_1_.n_1700_B(), G_624_v.R_4764_Y, H_2857_Y::J_1907_R)))));
    }

    private void T_3594_S() {
        this.n_1700_B(Items.D_265_n);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.s_4405_m).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.r_715_M).n_1700_B((T1 p_239910_1_) -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, this.n_1700_B(a_3742_W.s_4405_m, "_stage" + p_239910_1_, G_624_v.B_1668_F, H_2857_Y::R_4764_Y)))));
    }

    private void D_4792_h() {
        this.n_1700_B(Items.Animation);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.I_3637_j).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.n_1700_B, BlockStateProperties.z_1737_N, BlockStateProperties.d_2427_y, BlockStateProperties.v_4276_D, BlockStateProperties.d_2461_k).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_ns"))).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_n")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_n"))).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_n")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_n")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_ne"))).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_ne")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_ne")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_ne")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_ns"))).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_ns")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_nse"))).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_nse")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_nse")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_nse")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_nsew"))).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_ns"))).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_n"))).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_n")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_n")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_n")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_ne"))).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_ne")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_ne")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_ne")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_ns"))).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_ns")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_nse"))).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_nse")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_nse")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_nse")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.I_3637_j, "_attached_nsew")))));
    }

    private void s_2632_s() {
        this.J_1907_R(a_3742_W.d_2169_p);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.d_2169_p).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.n_1700_B, BlockStateProperties.C_2741_M).n_1700_B((T1 p_239908_0_, T2 p_239908_1_) -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, H_2857_Y.n_1700_B(a_3742_W.d_2169_p, (p_239908_0_ != false ? "_attached" : "") + (p_239908_1_ != false ? "_on" : ""))))).n_1700_B(v_4262_N.J_1907_R()));
    }

    private g_2336_b n_1700_B(int p_239865_1_, String p_239865_2_, H_2857_Y p_239865_3_) {
        switch (p_239865_1_) {
            case 1: {
                return G_624_v.RealmsWorldOptions.n_1700_B(A_4115_X.n_1700_B(p_239865_2_ + "turtle_egg"), p_239865_3_, this.J_1907_R);
            }
            case 2: {
                return G_624_v.RealmsWorldResetDto.n_1700_B(A_4115_X.n_1700_B("two_" + p_239865_2_ + "turtle_eggs"), p_239865_3_, this.J_1907_R);
            }
            case 3: {
                return G_624_v.RegionPingResult.n_1700_B(A_4115_X.n_1700_B("three_" + p_239865_2_ + "turtle_eggs"), p_239865_3_, this.J_1907_R);
            }
            case 4: {
                return G_624_v.H_1083_k.n_1700_B(A_4115_X.n_1700_B("four_" + p_239865_2_ + "turtle_eggs"), p_239865_3_, this.J_1907_R);
            }
        }
        throw new UnsupportedOperationException();
    }

    private g_2336_b n_1700_B(Integer p_239912_1_, Integer p_239912_2_) {
        switch (p_239912_2_) {
            case 0: {
                return this.n_1700_B(p_239912_1_, "", H_2857_Y.J_1907_R(H_2857_Y.A_4115_X(a_3742_W.d_560_A)));
            }
            case 1: {
                return this.n_1700_B(p_239912_1_, "slightly_cracked_", H_2857_Y.J_1907_R(H_2857_Y.n_1700_B(a_3742_W.d_560_A, "_slightly_cracked")));
            }
            case 2: {
                return this.n_1700_B(p_239912_1_, "very_cracked_", H_2857_Y.J_1907_R(H_2857_Y.n_1700_B(a_3742_W.d_560_A, "_very_cracked")));
            }
        }
        throw new UnsupportedOperationException();
    }

    private void l_1233_K() {
        this.n_1700_B(Items.FastPlace);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.d_560_A).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.UploadStatus, BlockStateProperties.e_1992_r).J_1907_R((T1 p_239949_1_, T2 p_239949_2_) -> Arrays.asList(v_4262_N.n_1700_B(this.n_1700_B((Integer)p_239949_1_, (Integer)p_239949_2_))))));
    }

    private void z_1333_t() {
        this.J_1907_R(a_3742_W.U_4087_m);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.U_4087_m).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.z_1737_N, BlockStateProperties.d_2427_y, BlockStateProperties.v_4276_D, BlockStateProperties.e_4240_b, BlockStateProperties.d_2461_k).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1"))).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1"))).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2"))).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2_opposite"))).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2_opposite")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3"))).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_4"))).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_u"))).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1u"))).n_1700_B((Boolean)false, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_1u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2u"))).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)false, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2u_opposite"))).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_2u_opposite")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)false, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3u"))).n_1700_B((Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R)).n_1700_B((Boolean)false, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)false, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_3u")).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y)).n_1700_B((Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, (Boolean)true, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, A_4115_X.n_1700_B(a_3742_W.U_4087_m, "_4u")))));
    }

    private void O_508_d() {
        this.n_1700_B.accept(v_4262_N.R_4764_Y(a_3742_W.LevitationControl, G_624_v.R_4764_Y.n_1700_B(a_3742_W.LevitationControl, H_2857_Y.J_1907_R(A_4115_X.n_1700_B("magma")), this.J_1907_R)));
    }

    private void q_2307_F(T_2915_h p_240043_1_) {
        this.R_4764_Y(p_240043_1_, g_221_o.M_588_G);
        G_624_v.l_4537_E.n_1700_B(A_4115_X.n_1700_B(p_240043_1_.u_1723_Y()), H_2857_Y.t_1786_h(p_240043_1_), this.J_1907_R);
    }

    private void J_1907_R(T_2915_h p_239936_1_, T_2915_h p_239936_2_, G_564_y p_239936_3_) {
        this.J_1907_R(p_239936_1_, p_239936_3_);
        this.J_1907_R(p_239936_2_, p_239936_3_);
    }

    private void u_2550_I(T_2915_h p_240015_1_, T_2915_h p_240015_2_) {
        G_624_v.F_2624_D.n_1700_B(A_4115_X.n_1700_B(p_240015_1_.u_1723_Y()), H_2857_Y.t_1786_h(p_240015_2_), this.J_1907_R);
    }

    private void r_715_M() {
        g_2336_b resourcelocation = A_4115_X.n_1700_B(a_3742_W.J_1907_R);
        g_2336_b resourcelocation1 = A_4115_X.n_1700_B(a_3742_W.J_1907_R, "_mirrored");
        this.n_1700_B.accept(v_4262_N.n_1700_B(a_3742_W.b_3528_u, resourcelocation, resourcelocation1));
        this.n_1700_B(a_3742_W.b_3528_u, resourcelocation);
    }

    private void M_588_G(T_2915_h p_240019_1_, T_2915_h p_240019_2_) {
        this.n_1700_B(p_240019_1_, G_564_y.J_1907_R);
        H_2857_Y modeltextures = H_2857_Y.G_564_y(H_2857_Y.n_1700_B(p_240019_1_, "_pot"));
        g_2336_b resourcelocation = G_564_y.J_1907_R.J_1907_R().n_1700_B(p_240019_2_, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240019_2_, resourcelocation));
    }

    private void A_1038_p() {
        g_2336_b resourcelocation = H_2857_Y.n_1700_B(a_3742_W.WrappedMinMaxBounds, "_bottom");
        g_2336_b resourcelocation1 = H_2857_Y.n_1700_B(a_3742_W.WrappedMinMaxBounds, "_top_off");
        g_2336_b resourcelocation2 = H_2857_Y.n_1700_B(a_3742_W.WrappedMinMaxBounds, "_top");
        g_2336_b[] aresourcelocation = new g_2336_b[5];
        for (int i = 0; i < 5; ++i) {
            H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.P_1922_E, resourcelocation).n_1700_B(T_2506_i.u_1723_Y, i == 0 ? resourcelocation1 : resourcelocation2).n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(a_3742_W.WrappedMinMaxBounds, "_side" + i));
            aresourcelocation[i] = G_624_v.w_1484_f.n_1700_B(a_3742_W.WrappedMinMaxBounds, "_" + i, modeltextures, this.J_1907_R);
        }
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.WrappedMinMaxBounds).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.RealmsServerPing).n_1700_B((T1 p_239922_1_) -> P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, aresourcelocation[p_239922_1_]))));
        this.n_1700_B(Items.Y_1820_h, aresourcelocation[0]);
    }

    private P_1922_E n_1700_B(O_4606_n p_239898_1_, P_1922_E p_239898_2_) {
        switch (p_239898_1_) {
            case J_1907_R: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R);
            }
            case R_4764_Y: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y);
            }
            case G_564_y: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y);
            }
            case n_1700_B: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.J_1907_R).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R);
            }
            case u_1723_Y: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y);
            }
            case v_4262_N: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y);
            }
            case w_1484_f: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R);
            }
            case P_1922_E: {
                return p_239898_2_.n_1700_B(u_1723_Y.n_1700_B, u_1723_Y.n_1700_B.G_564_y).n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y);
            }
            case u_2550_I: {
                return p_239898_2_;
            }
            case M_588_G: {
                return p_239898_2_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.R_4764_Y);
            }
            case t_148_a: {
                return p_239898_2_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.G_564_y);
            }
            case s_956_w: {
                return p_239898_2_.n_1700_B(u_1723_Y.J_1907_R, u_1723_Y.n_1700_B.J_1907_R);
            }
        }
        throw new UnsupportedOperationException("Rotation " + String.valueOf(p_239898_1_) + " can't be expressed with existing x and y values");
    }

    private void i_1637_u() {
        g_2336_b resourcelocation = H_2857_Y.n_1700_B(a_3742_W.q_2034_t, "_top");
        g_2336_b resourcelocation1 = H_2857_Y.n_1700_B(a_3742_W.q_2034_t, "_bottom");
        g_2336_b resourcelocation2 = H_2857_Y.n_1700_B(a_3742_W.q_2034_t, "_side");
        g_2336_b resourcelocation3 = H_2857_Y.n_1700_B(a_3742_W.q_2034_t, "_lock");
        H_2857_Y modeltextures = new H_2857_Y().n_1700_B(T_2506_i.Q_4569_t, resourcelocation2).n_1700_B(T_2506_i.P_4830_p, resourcelocation2).n_1700_B(T_2506_i.M_588_G, resourcelocation2).n_1700_B(T_2506_i.R_4764_Y, resourcelocation).n_1700_B(T_2506_i.s_956_w, resourcelocation).n_1700_B(T_2506_i.u_2550_I, resourcelocation1).n_1700_B(T_2506_i.h_1847_R, resourcelocation3);
        g_2336_b resourcelocation4 = G_624_v.J_1907_R.n_1700_B(a_3742_W.q_2034_t, modeltextures, this.J_1907_R);
        this.n_1700_B.accept(w_1457_N.n_1700_B(a_3742_W.q_2034_t, P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation4)).n_1700_B(s_956_w.n_1700_B(BlockStateProperties.z_4693_k).n_1700_B((T1 p_239897_1_) -> this.n_1700_B((O_4606_n)p_239897_1_, P_1922_E.n_1700_B()))));
    }

    public void n_1700_B() {
        this.u_2550_I(a_3742_W.n_1700_B);
        this.n_1700_B(a_3742_W.a_1344_X, a_3742_W.n_1700_B);
        this.n_1700_B(a_3742_W.z_2759_Q, a_3742_W.n_1700_B);
        this.u_2550_I(a_3742_W.k_578_l);
        this.u_2550_I(a_3742_W.d_3244_b);
        this.n_1700_B(a_3742_W.S_4325_V, a_3742_W.c_3005_b);
        this.u_2550_I(a_3742_W.F_391_H);
        this.u_2550_I(a_3742_W.T_797_O);
        this.u_2550_I(a_3742_W.E_453_w);
        this.u_2550_I(a_3742_W.r_4790_y);
        this.n_1700_B(Items.f_1706_V);
        this.u_2550_I(a_3742_W.B_1335_M);
        this.u_2550_I(a_3742_W.c_3005_b);
        this.u_2550_I(a_3742_W.H_2857_Y);
        this.u_2550_I(a_3742_W.g_4841_c);
        this.n_1700_B(Items.n_4539_g);
        this.u_2550_I(a_3742_W.r_2090_h);
        this.u_2550_I(a_3742_W.f_2787_O);
        this.n_1700_B(a_3742_W.N_4890_q, Items.AimAssist);
        this.n_1700_B(Items.AimAssist);
        this.n_1700_B(a_3742_W.PearlLogger, Items.D_3097_e);
        this.n_1700_B(Items.D_3097_e);
        this.u_1723_Y(a_3742_W.O_2151_c, H_2857_Y.n_1700_B(a_3742_W.j_2266_I, "_side"));
        this.R_4764_Y(a_3742_W.n_3318_d, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.ItemHelper, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.L_4248_u, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.O_1309_Q, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.V_1665_T, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.B_2580_P, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.x_607_J, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.d_2427_y, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.y_2772_m, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.e_4240_b, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.H_1883_T, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.B_368_w, g_221_o.R_4764_Y);
        this.R_4764_Y(a_3742_W.LightPredicate, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.D_60_a, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.k_3961_g, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.N_2266_w, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.o_1800_r, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.s_3815_K, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.S_3844_E, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.G_4691_Q, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.e_2973_e, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.v_887_r, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.u_2550_I, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.b_1557_h, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.g_1734_y, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.MinMaxBounds, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.e_1231_S, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.X_2960_b, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.t_4043_B, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.K_4518_s, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.O_1795_e, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.r_2478_U, g_221_o.u_1723_Y);
        this.R_4764_Y(a_3742_W.m_3052_r, g_221_o.R_4764_Y);
        this.R_4764_Y(a_3742_W.E_3343_g, g_221_o.R_4764_Y);
        this.R_4764_Y(a_3742_W.LockSlot, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.PlayerInfo, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.ServerHelper, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.ClientBootstrap, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.AbstractBannerBlock, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.T_33_Q, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.CriterionTrigger, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.C_415_h, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.v_165_F, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.j_306_t, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.j_276_v, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.RowButton, g_221_o.t_1786_h);
        this.n_1700_B(Items.F_2624_D);
        this.R_4764_Y(a_3742_W.TextRenderingUtils, g_221_o.P_1922_E);
        this.R_4764_Y(a_3742_W.Y_2805_J, g_221_o.R_4764_Y);
        this.R_4764_Y(a_3742_W.J_1008_m, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.UploadStatus, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.j_2461_G, g_221_o.n_1700_B);
        this.R_4764_Y(a_3742_W.Q_4222_k, g_221_o.R_4764_Y.n_1700_B((H_2857_Y p_239982_0_) -> p_239982_0_.n_1700_B(T_2506_i.t_148_a, H_2857_Y.A_4115_X(a_3742_W.Q_4222_k))));
        this.R_4764_Y(a_3742_W.I_3457_f, g_221_o.n_1700_B);
        this.v_4262_N(a_3742_W.t_4219_U, a_3742_W.h_4320_q);
        this.v_4262_N(a_3742_W.BoatNoClip, a_3742_W.BlockFly);
        this.R_4764_Y(a_3742_W.Y_3588_g, g_221_o.n_1700_B);
        this.w_1484_f(a_3742_W.O_3016_i, a_3742_W.y_2772_m);
        this.w_1484_f(a_3742_W.b_2037_V, a_3742_W.H_1883_T);
        this.h_1847_R();
        this.multiplayerClientSuggestionProvider();
        this.w_1457_N();
        this.n_1700_B(new T_2915_h[]{a_3742_W.k_1366_K, a_3742_W.y_4842_Z});
        this.Y_601_j();
        this.C_2741_M();
        this.k_2293_S();
        this.Z_875_P();
        this.c_3005_b();
        this.H_2857_Y();
        this.q_2307_F();
        this.w_1457_N(a_3742_W.BaritoneSettings);
        this.A_4115_X();
        this.Y_1740_V();
        this.t_4043_B();
        this.x_607_J();
        this.e_4240_b();
        this.n_3318_d();
        this.d_2427_y();
        this.P_4830_p();
        this.z_1737_N();
        this.v_4276_D();
        this.d_2461_k();
        this.G_624_v();
        this.T_2506_i();
        this.q_4610_l();
        this.z_4693_k();
        this.g_221_o();
        this.e_2887_G();
        this.B_1668_F();
        this.X_933_l();
        this.g_164_R();
        this.Z_976_R();
        this.H_1990_U();
        this.Y_259_p();
        this.N_2525_X();
        this.c_4037_x();
        this.g_2268_R();
        this.T_3594_S();
        this.D_4792_h();
        this.s_2632_s();
        this.l_1233_K();
        this.z_1333_t();
        this.O_508_d();
        this.i_1637_u();
        this.k_2293_S(a_3742_W.L_3570_A);
        this.J_1907_R(a_3742_W.L_3570_A);
        this.k_2293_S(a_3742_W.F_489_x);
        this.s_956_w(a_3742_W.o_2341_D, a_3742_W.C_1269_X);
        this.s_956_w(a_3742_W.I_4348_c, a_3742_W.O_3598_v);
        this.n_1700_B(a_3742_W.O_2934_T, a_3742_W.h_1847_R, H_2857_Y::R_4764_Y);
        this.n_1700_B(a_3742_W.W_3801_h, a_3742_W.M_182_A, H_2857_Y::G_564_y);
        this.multiplayerClientSuggestionProvider(a_3742_W.ServerFunctionManager);
        this.multiplayerClientSuggestionProvider(a_3742_W.ServerAdvancementManager);
        this.t_1786_h(a_3742_W.Ops);
        this.t_1786_h(a_3742_W.c_1732_c);
        this.C_2741_M(a_3742_W.K_4237_u);
        this.C_2741_M(a_3742_W.Z_3822_q);
        this.P_1922_E(a_3742_W.r_4879_Z, A_4115_X.n_1700_B(a_3742_W.r_4879_Z));
        this.n_1700_B(a_3742_W.s_4990_V, g_221_o.R_4764_Y);
        this.n_1700_B(a_3742_W.b_2312_j, g_221_o.R_4764_Y);
        this.n_1700_B(a_3742_W.Nuker, g_221_o.R_4764_Y);
        this.G_564_y(a_3742_W.s_956_w);
        this.G_564_y(a_3742_W.A_4115_X);
        this.G_564_y(a_3742_W.Y_1740_V);
        this.R_4764_Y(a_3742_W.Z_875_P);
        this.n_1700_B(a_3742_W.M_4609_z, g_221_o.R_4764_Y, g_221_o.G_564_y);
        this.n_1700_B(a_3742_W.CrystalOptimizer, g_221_o.multiplayerClientSuggestionProvider, g_221_o.w_1457_N);
        this.n_1700_B(a_3742_W.f_887_Z, g_221_o.multiplayerClientSuggestionProvider, g_221_o.w_1457_N);
        this.J_1907_R(a_3742_W.m_1628_s, g_221_o.w_1484_f);
        this.Q_2552_b();
        this.n_1700_B(a_3742_W.w_4866_k, H_2857_Y::C_2741_M);
        this.n_1700_B(a_3742_W.t_4057_p, H_2857_Y::q_2307_F);
        this.n_1700_B(a_3742_W.FreeCam, BlockStateProperties.r_715_M, 0, 1, 2, 3);
        this.n_1700_B(a_3742_W.P_2295_B, BlockStateProperties.i_1637_u, 0, 0, 1, 1, 2, 2, 2, 3);
        this.n_1700_B(a_3742_W.W_3729_Q, BlockStateProperties.r_715_M, 0, 1, 1, 2);
        this.n_1700_B(a_3742_W.U_1697_c, BlockStateProperties.i_1637_u, 0, 0, 1, 1, 2, 2, 2, 3);
        this.n_1700_B(a_3742_W.l_4088_R, BlockStateProperties.i_1637_u, 0, 1, 2, 3, 4, 5, 6, 7);
        this.n_1700_B(A_4115_X.n_1700_B("banner"), a_3742_W.h_1847_R).n_1700_B(G_624_v.RealmsDefaultUncaughtExceptionHandler, a_3742_W.t_2598_a, a_3742_W.RussianRoulette, a_3742_W.SRPSpoof, a_3742_W.ScoreboardHealth, a_3742_W.Spammer, a_3742_W.AhHelper, a_3742_W.TPLoot, a_3742_W.TapeMouse, a_3742_W.ToggleSounds, a_3742_W.TrashTalk, a_3742_W.UseTracker, a_3742_W.VoiceChat, a_3742_W.a_2587_Z, a_3742_W.D_3097_e, a_3742_W.q_3386_W, a_3742_W.n_3932_q).J_1907_R(a_3742_W.w_3483_v, a_3742_W.R_1148_E, a_3742_W.m_2594_d, a_3742_W.o_1343_U, a_3742_W.p_4879_r, a_3742_W.w_1672_Y, a_3742_W.f_360_U, a_3742_W.M_1321_u, a_3742_W.Y_4144_v, a_3742_W.I_1654_f, a_3742_W.C_2712_Y, a_3742_W.a_1255_F, a_3742_W.AirJump, a_3742_W.AirStuck, a_3742_W.AutoJump, a_3742_W.Blink);
        this.n_1700_B(A_4115_X.n_1700_B("bed"), a_3742_W.h_1847_R).J_1907_R(a_3742_W.V_1225_t, a_3742_W.U_1241_n, a_3742_W.q_1982_R, a_3742_W.dtoRealmsServerAddress, a_3742_W.w_612_n, a_3742_W.RealmsServerPing, a_3742_W.j_1564_a, a_3742_W.M_1641_O, a_3742_W.RealmsWorldOptions, a_3742_W.RealmsWorldResetDto, a_3742_W.RegionPingResult, a_3742_W.H_1083_k, a_3742_W.R_3908_n, a_3742_W.ValueObject, a_3742_W.F_1410_V, a_3742_W.S_4022_R);
        this.u_2550_I(a_3742_W.V_1225_t, a_3742_W.R_3077_Z);
        this.u_2550_I(a_3742_W.U_1241_n, a_3742_W.RealmsScreenWithCallback);
        this.u_2550_I(a_3742_W.q_1982_R, a_3742_W.M_2677_i);
        this.u_2550_I(a_3742_W.dtoRealmsServerAddress, a_3742_W.c_132_F);
        this.u_2550_I(a_3742_W.w_612_n, a_3742_W.g_4106_L);
        this.u_2550_I(a_3742_W.RealmsServerPing, a_3742_W.RealmsClientOutdatedScreen);
        this.u_2550_I(a_3742_W.j_1564_a, a_3742_W.W_3464_O);
        this.u_2550_I(a_3742_W.M_1641_O, a_3742_W.RealmsConfirmScreen);
        this.u_2550_I(a_3742_W.RealmsWorldOptions, a_3742_W.RealmsCreateRealmScreen);
        this.u_2550_I(a_3742_W.RealmsWorldResetDto, a_3742_W.C_290_v);
        this.u_2550_I(a_3742_W.RegionPingResult, a_3742_W.w_728_N);
        this.u_2550_I(a_3742_W.H_1083_k, a_3742_W.J_4256_G);
        this.u_2550_I(a_3742_W.R_3908_n, a_3742_W.RealmsLongConfirmationScreen);
        this.u_2550_I(a_3742_W.ValueObject, a_3742_W.RealmsLongRunningMcoTaskScreen);
        this.u_2550_I(a_3742_W.F_1410_V, a_3742_W.i_2993_w);
        this.u_2550_I(a_3742_W.S_4022_R, a_3742_W.RealmsParentalConsentScreen);
        this.n_1700_B(A_4115_X.n_1700_B("skull"), a_3742_W.C_415_h).n_1700_B(G_624_v.y_1700_S, a_3742_W.BooleanSetting, a_3742_W.Setting, a_3742_W.Module, a_3742_W.D_3612_q, a_3742_W.ModuleCategory).n_1700_B(a_3742_W.H_1491_c).J_1907_R(a_3742_W.SoundEventRegistration, a_3742_W.h_2367_h, a_3742_W.KeyBindSetting, a_3742_W.ModuleManager, a_3742_W.R_2822_N, a_3742_W.p_1458_L);
        this.q_2307_F(a_3742_W.k_1052_R);
        this.q_2307_F(a_3742_W.Ambience);
        this.q_2307_F(a_3742_W.x_555_z);
        this.q_2307_F(a_3742_W.AnomalyESP);
        this.q_2307_F(a_3742_W.ArmorDurability);
        this.q_2307_F(a_3742_W.Arrows);
        this.q_2307_F(a_3742_W.AspectRatio);
        this.q_2307_F(a_3742_W.BlockESP);
        this.q_2307_F(a_3742_W.BlockOverlay);
        this.q_2307_F(a_3742_W.Chams);
        this.q_2307_F(a_3742_W.ChatBubbles);
        this.q_2307_F(a_3742_W.Cosmetics);
        this.q_2307_F(a_3742_W.Crosshair);
        this.q_2307_F(a_3742_W.CrystalESP);
        this.q_2307_F(a_3742_W.DistantAlpha);
        this.q_2307_F(a_3742_W.Emotions);
        this.q_2307_F(a_3742_W.EntityESP);
        this.R_4764_Y(a_3742_W.V_3441_j, g_221_o.M_588_G);
        this.n_1700_B(a_3742_W.V_3441_j);
        this.n_1700_B(A_4115_X.n_1700_B("chest"), a_3742_W.h_1847_R).J_1907_R(a_3742_W.L_1362_X, a_3742_W.NumberSetting);
        this.n_1700_B(A_4115_X.n_1700_B("ender_chest"), a_3742_W.ClientBootstrap).J_1907_R(a_3742_W.k_2348_i);
        this.G_564_y(a_3742_W.M_2562_s, a_3742_W.ClientBootstrap).n_1700_B(a_3742_W.M_2562_s, a_3742_W.ItemRelease);
        this.P_1922_E(a_3742_W.Particular);
        this.P_1922_E(a_3742_W.Prediction);
        this.P_1922_E(a_3742_W.Removals);
        this.P_1922_E(a_3742_W.SantaHat);
        this.P_1922_E(a_3742_W.SeeInvisibles);
        this.P_1922_E(a_3742_W.ShulkerPreview);
        this.P_1922_E(a_3742_W.Skeleton);
        this.P_1922_E(a_3742_W.t_1595_x);
        this.P_1922_E(a_3742_W.Tags);
        this.P_1922_E(a_3742_W.ThirdPerson);
        this.P_1922_E(a_3742_W.TotemPop);
        this.P_1922_E(a_3742_W.Tracers);
        this.P_1922_E(a_3742_W.Trails);
        this.P_1922_E(a_3742_W.Trajectory);
        this.P_1922_E(a_3742_W.ViewModel);
        this.P_1922_E(a_3742_W.WorldParticles);
        this.n_1700_B(g_221_o.n_1700_B, a_3742_W.CavityFinder, a_3742_W.e_87_p, a_3742_W.K_2336_H, a_3742_W.n_421_x, a_3742_W.p_1976_q, a_3742_W.A_4252_m, a_3742_W.a_794_m, a_3742_W.E_170_p, a_3742_W.m_229_F, a_3742_W.f_4340_D, a_3742_W.A_2204_Z, a_3742_W.R_4912_F, a_3742_W.S_315_z, a_3742_W.o_977_F, a_3742_W.S_1165_y, a_3742_W.E_738_L);
        this.P_1922_E(a_3742_W.InventoryPlus);
        this.P_1922_E(a_3742_W.I_2209_R);
        this.P_1922_E(a_3742_W.h_3858_e);
        this.P_1922_E(a_3742_W.l_4397_i);
        this.P_1922_E(a_3742_W.t_4433_T);
        this.P_1922_E(a_3742_W.AimAssist);
        this.P_1922_E(a_3742_W.AntiBot);
        this.P_1922_E(a_3742_W.AntiSurround);
        this.P_1922_E(a_3742_W.s_4447_V);
        this.P_1922_E(a_3742_W.AttackAura);
        this.P_1922_E(a_3742_W.AutoAnchor);
        this.P_1922_E(a_3742_W.AutoCrystal);
        this.P_1922_E(a_3742_W.AutoExplosion);
        this.P_1922_E(a_3742_W.AutoSwap);
        this.P_1922_E(a_3742_W.AutoTotem);
        this.P_1922_E(a_3742_W.AutoTrap);
        this.P_1922_E(a_3742_W.s_4054_j);
        this.u_1723_Y(a_3742_W.e_1992_r, a_3742_W.q_839_y);
        this.u_1723_Y(a_3742_W.y_1945_D, a_3742_W.HoleFill);
        this.u_1723_Y(a_3742_W.U_532_X, a_3742_W.KBDisplacement);
        this.u_1723_Y(a_3742_W.P_4639_N, a_3742_W.NoEntityTrace);
        this.u_1723_Y(a_3742_W.i_4434_b, a_3742_W.NoFriendDamage);
        this.u_1723_Y(a_3742_W.P_328_a, a_3742_W.NoServerDesync);
        this.u_1723_Y(a_3742_W.l_4627_h, a_3742_W.r_4217_P);
        this.u_1723_Y(a_3742_W.K_3372_t, a_3742_W.Velocity);
        this.u_1723_Y(a_3742_W.Q_2467_v, a_3742_W.PacketCriticals);
        this.u_1723_Y(a_3742_W.m_2262_U, a_3742_W.Surround);
        this.u_1723_Y(a_3742_W.S_4258_d, a_3742_W.TargetPearl);
        this.u_1723_Y(a_3742_W.m_891_U, a_3742_W.TargetStrafe);
        this.u_1723_Y(a_3742_W.T_2971_J, a_3742_W.TriggerBot);
        this.u_1723_Y(a_3742_W.Q_3581_n, a_3742_W.r_4601_j);
        this.u_1723_Y(a_3742_W.I_685_r, a_3742_W.O_726_g);
        this.u_1723_Y(a_3742_W.h_3270_j, a_3742_W.E_2115_e);
        this.u_1723_Y(a_3742_W.M_3508_C, a_3742_W.W_1707_M);
        this.J_1907_R(g_221_o.s_956_w, a_3742_W.ExtendedTab, a_3742_W.FireworkESP, a_3742_W.FullBright, a_3742_W.Glint, a_3742_W.f_2247_K, a_3742_W.HitEffect, a_3742_W.Interface, a_3742_W.ItemPhysics, a_3742_W.ItemRadius, a_3742_W.JumpCircle, a_3742_W.KillEffect, a_3742_W.LogoutSpots, a_3742_W.w_2099_r, a_3742_W.R_4688_l, a_3742_W.ObjectInfo, a_3742_W.Particles);
        this.P_1922_E(a_3742_W.R_3077_Z, a_3742_W.AuctionHelper);
        this.P_1922_E(a_3742_W.RealmsScreenWithCallback, a_3742_W.AutoAccept);
        this.P_1922_E(a_3742_W.M_2677_i, a_3742_W.AutoContract);
        this.P_1922_E(a_3742_W.c_132_F, a_3742_W.AutoDuel);
        this.P_1922_E(a_3742_W.g_4106_L, a_3742_W.BedrockProxy);
        this.P_1922_E(a_3742_W.RealmsClientOutdatedScreen, a_3742_W.BetterMinecraft);
        this.P_1922_E(a_3742_W.W_3464_O, a_3742_W.BotAutoCollector);
        this.P_1922_E(a_3742_W.RealmsConfirmScreen, a_3742_W.Bots);
        this.P_1922_E(a_3742_W.RealmsCreateRealmScreen, a_3742_W.ClickFriend);
        this.P_1922_E(a_3742_W.C_290_v, a_3742_W.ClientSpoof);
        this.P_1922_E(a_3742_W.w_728_N, a_3742_W.DeathCoords);
        this.P_1922_E(a_3742_W.J_4256_G, a_3742_W.DiscordRPC);
        this.P_1922_E(a_3742_W.RealmsLongConfirmationScreen, a_3742_W.EcSaver);
        this.P_1922_E(a_3742_W.RealmsLongRunningMcoTaskScreen, a_3742_W.ElytraHelper);
        this.P_1922_E(a_3742_W.i_2993_w, a_3742_W.FlagDetector);
        this.P_1922_E(a_3742_W.RealmsParentalConsentScreen, a_3742_W.Globals);
        this.n_1700_B(a_3742_W.RetryCallException, a_3742_W.I_1790_n, G_564_y.n_1700_B);
        this.n_1700_B(a_3742_W.s_1671_u, a_3742_W.C_332_W, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.RealmsResetNormalWorldScreen, a_3742_W.L_3537_K, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.C_3538_G, a_3742_W.z_3000_g, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.A_3959_N, a_3742_W.n_4915_F, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.G_424_k, a_3742_W.y_2622_c, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.RealmsSettingsScreen, a_3742_W.n_473_l, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.f_1043_S, a_3742_W.r_4414_L, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.F_4247_a, a_3742_W.P_2272_O, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.J_739_q, a_3742_W.S_2828_i, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.C_1162_e, a_3742_W.y_4642_Y, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.D_4361_a, a_3742_W.h_1640_b, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.u_55_V, a_3742_W.V_1176_p, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.f_3449_S, a_3742_W.y_2447_C, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.RealmsPersistence, a_3742_W.J_3635_s, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.JsonUtils, a_3742_W.o_82_k, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.r_3651_U, a_3742_W.h_973_D, G_564_y.J_1907_R);
        this.M_182_A(a_3742_W.b_2625_m);
        this.M_182_A(a_3742_W.q_3401_q);
        this.M_182_A(a_3742_W.u_796_y);
        this.n_1700_B(a_3742_W.u_744_e, G_564_y.n_1700_B);
        this.J_1907_R(a_3742_W.l_3609_d, G_564_y.n_1700_B);
        this.n_1700_B(Items.RealmsPersistence);
        this.J_1907_R(a_3742_W.R_1796_s, a_3742_W.g_24_p, G_564_y.n_1700_B);
        this.n_1700_B(Items.y_2772_m);
        this.n_1700_B(a_3742_W.g_24_p);
        this.J_1907_R(a_3742_W.RequirementsStrategy, a_3742_W.S_4998_h, G_564_y.J_1907_R);
        this.J_1907_R(a_3742_W.SimpleCriterionTrigger, a_3742_W.T_2391_T, G_564_y.J_1907_R);
        this.n_1700_B(a_3742_W.RequirementsStrategy, "_plant");
        this.n_1700_B(a_3742_W.S_4998_h);
        this.n_1700_B(a_3742_W.SimpleCriterionTrigger, "_plant");
        this.n_1700_B(a_3742_W.T_2391_T);
        this.n_1700_B(a_3742_W.m_3828_C, G_564_y.n_1700_B, H_2857_Y.R_4764_Y(H_2857_Y.n_1700_B(a_3742_W.t_1509_b, "_stage0")));
        this.t_148_a();
        this.n_1700_B(a_3742_W.y_1700_S, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.X_812_G, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.NameProtect, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.OpenWalls, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.Party, G_564_y.n_1700_B);
        this.R_4764_Y(a_3742_W.PotionTracker, G_564_y.n_1700_B);
        this.v_4262_N();
        this.w_1484_f();
        this.n_1700_B(a_3742_W.n_2412_y, a_3742_W.Y_3623_f, a_3742_W.S_234_U, a_3742_W.k_2273_q, a_3742_W.N_2592_G, a_3742_W.w_2152_d, a_3742_W.A_4514_U, a_3742_W.K_1200_E);
        this.n_1700_B(a_3742_W.W_2756_H, a_3742_W.z_4066_l, a_3742_W.B_707_U, a_3742_W.D_1621_L, a_3742_W.s_1124_y, a_3742_W.Z_1243_X, a_3742_W.S_4088_D, a_3742_W.D_563_q);
        this.n_1700_B(a_3742_W.i_1894_C, a_3742_W.Y_4293_u, a_3742_W.N_1833_W, a_3742_W.ServerHandshakePacketListener, a_3742_W.C_1577_A, a_3742_W.r_976_u, a_3742_W.MinecraftAccess, a_3742_W.K_1964_I);
        this.n_1700_B(a_3742_W.u_4724_w, a_3742_W.z_283_n, a_3742_W.a_2727_J, a_3742_W.q_4124_m, a_3742_W.M_2029_A, a_3742_W.E_390_U, a_3742_W.Animation, a_3742_W.O_922_L);
        this.n_1700_B(a_3742_W.H_1952_g, a_3742_W.a_1887_j, a_3742_W.D_1410_T, a_3742_W.m_396_H, a_3742_W.q_3148_R, a_3742_W.Z_256_c, a_3742_W.H_274_C, a_3742_W.D_940_S);
        this.R_4764_Y(a_3742_W.n_4539_g, a_3742_W.J_2061_p);
        this.R_4764_Y(a_3742_W.L_1733_J, a_3742_W.i_789_Q);
        this.u_1723_Y(a_3742_W.multiplayerClientSuggestionProvider).n_1700_B(a_3742_W.y_3417_N).R_4764_Y(a_3742_W.AutoLes).G_564_y(a_3742_W.AutoEat).P_1922_E(a_3742_W.l_1268_F).n_1700_B(a_3742_W.P_2947_S, a_3742_W.I_1407_m).u_1723_Y(a_3742_W.GuiMove).v_4262_N(a_3742_W.I_4683_a);
        this.v_4262_N(a_3742_W.AutoTool);
        this.w_1484_f(a_3742_W.g_4560_H);
        this.s_956_w(a_3742_W.T_2506_i).R_4764_Y(a_3742_W.T_2506_i).n_1700_B(a_3742_W.g_2268_R);
        this.s_956_w(a_3742_W.B_1668_F).R_4764_Y(a_3742_W.B_1668_F).n_1700_B(a_3742_W.O_508_d);
        this.n_1700_B(a_3742_W.k_2293_S, a_3742_W.t_2932_z, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.RealmsClientConfig, g_221_o.h_1847_R);
        this.u_1723_Y(a_3742_W.M_182_A).n_1700_B(a_3742_W.o_4117_e).R_4764_Y(a_3742_W.AutoJoiner).G_564_y(a_3742_W.AutoBuy).P_1922_E(a_3742_W.Z_759_W).n_1700_B(a_3742_W.n_3197_X, a_3742_W.V_118_c).u_1723_Y(a_3742_W.Flight).v_4262_N(a_3742_W.g_1031_K);
        this.v_4262_N(a_3742_W.AutoRespawn);
        this.w_1484_f(a_3742_W.Q_2753_H);
        this.s_956_w(a_3742_W.d_2461_k).R_4764_Y(a_3742_W.d_2461_k).n_1700_B(a_3742_W.N_2525_X);
        this.s_956_w(a_3742_W.g_221_o).R_4764_Y(a_3742_W.g_221_o).n_1700_B(a_3742_W.l_1233_K);
        this.n_1700_B(a_3742_W.Q_2552_b, a_3742_W.c_776_E, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.Ping, g_221_o.h_1847_R);
        this.u_1723_Y(a_3742_W.h_1847_R).n_1700_B(a_3742_W.V_537_k).R_4764_Y(a_3742_W.h_2848_I).G_564_y(a_3742_W.k_3129_Y).P_1922_E(a_3742_W.X_1313_W).n_1700_B(a_3742_W.X_4895_T, a_3742_W.k_2302_P).u_1723_Y(a_3742_W.ElytraMotion).u_1723_Y(a_3742_W.NoPush).v_4262_N(a_3742_W.F_3572_x);
        this.v_4262_N(a_3742_W.F_518_D);
        this.t_148_a(a_3742_W.q_817_e);
        this.s_956_w(a_3742_W.z_1737_N).R_4764_Y(a_3742_W.z_1737_N).n_1700_B(a_3742_W.Z_976_R);
        this.s_956_w(a_3742_W.X_933_l).R_4764_Y(a_3742_W.X_933_l).n_1700_B(a_3742_W.D_4792_h);
        this.n_1700_B(a_3742_W.Y_601_j, a_3742_W.x_2635_q, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.A_1038_p, g_221_o.h_1847_R);
        this.u_1723_Y(a_3742_W.Q_4569_t).n_1700_B(a_3742_W.c_2086_l).R_4764_Y(a_3742_W.AutoFish).G_564_y(a_3742_W.AutoArmor).P_1922_E(a_3742_W.x_4991_F).n_1700_B(a_3742_W.L_103_L, a_3742_W.t_3452_g).u_1723_Y(a_3742_W.F_3698_k).v_4262_N(a_3742_W.U_144_f);
        this.v_4262_N(a_3742_W.AutoPotion);
        this.w_1484_f(a_3742_W.r_260_T);
        this.s_956_w(a_3742_W.v_4276_D).R_4764_Y(a_3742_W.v_4276_D).n_1700_B(a_3742_W.H_1990_U);
        this.s_956_w(a_3742_W.z_4693_k).R_4764_Y(a_3742_W.z_4693_k).n_1700_B(a_3742_W.s_2632_s);
        this.n_1700_B(a_3742_W.Y_259_p, a_3742_W.f_2403_E, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.i_1637_u, g_221_o.h_1847_R);
        this.u_1723_Y(a_3742_W.w_1457_N).n_1700_B(a_3742_W.A_1306_N).R_4764_Y(a_3742_W.AutoPilot).G_564_y(a_3742_W.AutoFarm).P_1922_E(a_3742_W.J_303_C).n_1700_B(a_3742_W.w_2705_t, a_3742_W.d_2545_n).u_1723_Y(a_3742_W.HighJump).v_4262_N(a_3742_W.n_2689_l);
        this.v_4262_N(a_3742_W.AutoTrade);
        this.t_148_a(a_3742_W.z_2025_Z);
        this.s_956_w(a_3742_W.q_4610_l).R_4764_Y(a_3742_W.q_4610_l).n_1700_B(a_3742_W.T_3594_S);
        this.s_956_w(a_3742_W.g_164_R).R_4764_Y(a_3742_W.g_164_R).n_1700_B(a_3742_W.r_715_M);
        this.n_1700_B(a_3742_W.q_2307_F, a_3742_W.m_3147_m, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.f_4016_n, g_221_o.h_1847_R);
        this.u_1723_Y(a_3742_W.t_1786_h).n_1700_B(a_3742_W.U_3758_B).R_4764_Y(a_3742_W.AutoLeave).G_564_y(a_3742_W.AutoDupe).P_1922_E(a_3742_W.f_1574_f).n_1700_B(a_3742_W.O_4761_U, a_3742_W.o_2767_H).u_1723_Y(a_3742_W.c_892_d).v_4262_N(a_3742_W.g_134_G);
        this.v_4262_N(a_3742_W.AutoSoup);
        this.w_1484_f(a_3742_W.Y_2080_q);
        this.s_956_w(a_3742_W.G_624_v).R_4764_Y(a_3742_W.G_624_v).n_1700_B(a_3742_W.c_4037_x);
        this.s_956_w(a_3742_W.e_2887_G).R_4764_Y(a_3742_W.e_2887_G).n_1700_B(a_3742_W.z_1333_t);
        this.n_1700_B(a_3742_W.C_2741_M, a_3742_W.z_2372_L, G_564_y.J_1907_R);
        this.R_4764_Y(a_3742_W.p_178_J, g_221_o.h_1847_R);
        this.u_1723_Y(a_3742_W.d_3769_f).n_1700_B(a_3742_W.V_3982_O).R_4764_Y(a_3742_W.P_1965_C).G_564_y(a_3742_W.o_3456_E).P_1922_E(a_3742_W.q_608_V).n_1700_B(a_3742_W.b_1430_k, a_3742_W.b_4067_I).u_1723_Y(a_3742_W.z_936_s).v_4262_N(a_3742_W.m_3168_q);
        this.v_4262_N(a_3742_W.D_3640_k);
        this.w_1484_f(a_3742_W.r_1970_q);
        this.s_956_w(a_3742_W.T_4001_f).J_1907_R(a_3742_W.T_4001_f).n_1700_B(a_3742_W.M_712_N);
        this.s_956_w(a_3742_W.B_3068_A).J_1907_R(a_3742_W.B_3068_A).n_1700_B(a_3742_W.W_4813_f);
        this.n_1700_B(a_3742_W.h_1723_G, a_3742_W.y_2836_h, G_564_y.J_1907_R);
        this.M_588_G(a_3742_W.D_4237_z, a_3742_W.x_2711_Y);
        this.u_1723_Y(a_3742_W.n_4560_z).n_1700_B(a_3742_W.k_200_a).R_4764_Y(a_3742_W.K_4866_h).G_564_y(a_3742_W.I_3736_z).P_1922_E(a_3742_W.Z_2021_u).n_1700_B(a_3742_W.n_3115_n, a_3742_W.E_4068_x).u_1723_Y(a_3742_W.I_4421_I).v_4262_N(a_3742_W.A_1604_A);
        this.v_4262_N(a_3742_W.U_4107_W);
        this.w_1484_f(a_3742_W.DamageSourcePredicate);
        this.s_956_w(a_3742_W.X_1303_p).J_1907_R(a_3742_W.X_1303_p).n_1700_B(a_3742_W.A_2629_w);
        this.s_956_w(a_3742_W.w_2223_C).J_1907_R(a_3742_W.w_2223_C).n_1700_B(a_3742_W.AdvancementList);
        this.n_1700_B(a_3742_W.C_3304_p, a_3742_W.h_2396_v, G_564_y.J_1907_R);
        this.M_588_G(a_3742_W.U_1258_d, a_3742_W.w_2892_f);
        this.J_1907_R(a_3742_W.U_3554_Q, G_564_y.J_1907_R);
        this.n_1700_B(Items.f_3449_S);
        this.n_1700_B(H_2857_Y.n_1700_B(a_3742_W.J_1907_R)).n_1700_B((H_2857_Y p_239972_1_) -> {
            g_2336_b resourcelocation = G_624_v.R_4764_Y.n_1700_B(a_3742_W.J_1907_R, (H_2857_Y)p_239972_1_, this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.G_564_y.n_1700_B(a_3742_W.J_1907_R, (H_2857_Y)p_239972_1_, this.J_1907_R);
            this.n_1700_B.accept(v_4262_N.n_1700_B(a_3742_W.J_1907_R, resourcelocation, resourcelocation1));
            return resourcelocation;
        }).u_1723_Y(a_3742_W.Jesus).P_1922_E(a_3742_W.i_601_W).n_1700_B(a_3742_W.o_3599_Z).v_4262_N(a_3742_W.j_2302_z);
        this.v_4262_N(a_3742_W.h_2739_B);
        this.t_148_a(a_3742_W.q_2475_j);
        this.u_1723_Y(a_3742_W.f_691_R).J_1907_R(a_3742_W.U_3005_m).v_4262_N(a_3742_W.F_2860_q).u_1723_Y(a_3742_W.Phase);
        this.u_1723_Y(a_3742_W.I_4481_g).J_1907_R(a_3742_W.t_4562_T).v_4262_N(a_3742_W.F_747_P).u_1723_Y(a_3742_W.O_1043_U);
        this.u_1723_Y(a_3742_W.P_4830_p).J_1907_R(a_3742_W.h_3859_C).v_4262_N(a_3742_W.S_3139_t).u_1723_Y(a_3742_W.NoSlow);
        this.u_1723_Y(a_3742_W.U_1341_G).J_1907_R(a_3742_W.F_1446_q).v_4262_N(a_3742_W.k_2282_P).u_1723_Y(a_3742_W.j_2129_E);
        this.u_1723_Y(a_3742_W.z_2311_U).J_1907_R(a_3742_W.U_567_E).v_4262_N(a_3742_W.e_4654_Y).u_1723_Y(a_3742_W.l_1757_S);
        this.u_1723_Y(a_3742_W.Q_1082_O).v_4262_N(a_3742_W.b_967_P).u_1723_Y(a_3742_W.t_4864_b);
        this.u_1723_Y(a_3742_W.G_3540_E).v_4262_N(a_3742_W.P_459_I).u_1723_Y(a_3742_W.u_1980_X);
        this.G_564_y(a_3742_W.h_4320_q, g_221_o.Y_601_j).J_1907_R(a_3742_W.u_925_K).v_4262_N(a_3742_W.E_4256_w).u_1723_Y(a_3742_W.NoFall);
        this.n_1700_B(a_3742_W.Timer, g_221_o.n_1700_B(H_2857_Y.n_1700_B(a_3742_W.h_4320_q, "_top"))).u_1723_Y(a_3742_W.j_1654_T).v_4262_N(a_3742_W.q_4361_M);
        this.n_1700_B(a_3742_W.V_1446_Y, g_221_o.R_4764_Y.get(a_3742_W.h_4320_q).n_1700_B((H_2857_Y p_239962_0_) -> p_239962_0_.n_1700_B(T_2506_i.t_148_a, H_2857_Y.A_4115_X(a_3742_W.V_1446_Y)))).u_1723_Y(a_3742_W.NoJumpDelay);
        this.G_564_y(a_3742_W.BlockFly, g_221_o.Y_601_j).J_1907_R(a_3742_W.i_1479_B).v_4262_N(a_3742_W.ElytraJump).u_1723_Y(a_3742_W.Sprint);
        this.n_1700_B(a_3742_W.AntiAFK, g_221_o.n_1700_B(H_2857_Y.n_1700_B(a_3742_W.BlockFly, "_top"))).u_1723_Y(a_3742_W.u_488_m).v_4262_N(a_3742_W.R_2329_T);
        this.n_1700_B(a_3742_W.ElytraResolver, g_221_o.R_4764_Y.get(a_3742_W.BlockFly).n_1700_B((H_2857_Y p_239946_0_) -> p_239946_0_.n_1700_B(T_2506_i.t_148_a, H_2857_Y.A_4115_X(a_3742_W.ElytraResolver)))).u_1723_Y(a_3742_W.Step);
        this.u_1723_Y(a_3742_W.d_4007_L).J_1907_R(a_3742_W.Y_2143_L).v_4262_N(a_3742_W.B_1146_q).u_1723_Y(a_3742_W.NoWeb);
        this.u_1723_Y(a_3742_W.h_1015_G).R_4764_Y(a_3742_W.d_4500_Q).J_1907_R(a_3742_W.G_1539_D).v_4262_N(a_3742_W.O_2761_o).u_1723_Y(a_3742_W.Speed);
        this.u_1723_Y(a_3742_W.ClickPearl).v_4262_N(a_3742_W.FastBreak).u_1723_Y(a_3742_W.Strafe);
        this.u_1723_Y(a_3742_W.P_1922_E).J_1907_R(a_3742_W.v_570_f).v_4262_N(a_3742_W.k_4946_A).u_1723_Y(a_3742_W.U_3443_A);
        this.u_1723_Y(a_3742_W.u_1723_Y).v_4262_N(a_3742_W.T_1170_t).u_1723_Y(a_3742_W.v_1900_v);
        this.u_1723_Y(a_3742_W.R_4764_Y).J_1907_R(a_3742_W.H_3529_d).v_4262_N(a_3742_W.A_1603_w).u_1723_Y(a_3742_W.q_3115_L);
        this.u_1723_Y(a_3742_W.G_564_y).v_4262_N(a_3742_W.f_800_j).u_1723_Y(a_3742_W.m_4644_u);
        this.u_1723_Y(a_3742_W.v_4262_N).J_1907_R(a_3742_W.W_2770_z).v_4262_N(a_3742_W.V_4557_X).u_1723_Y(a_3742_W.p_863_D);
        this.u_1723_Y(a_3742_W.w_1484_f).v_4262_N(a_3742_W.m_38_G).u_1723_Y(a_3742_W.v_143_j);
        this.u_1723_Y(a_3742_W.FastPlace).J_1907_R(a_3742_W.Z_361_l).v_4262_N(a_3742_W.U_2474_c).u_1723_Y(a_3742_W.W_1488_x);
        this.G_564_y(a_3742_W.P_3676_m, g_221_o.R_4764_Y).v_4262_N(a_3742_W.R_3213_X).u_1723_Y(a_3742_W.Spider);
        this.n_1700_B(a_3742_W.WaterSpeed, g_221_o.n_1700_B(H_2857_Y.n_1700_B(a_3742_W.P_3676_m, "_bottom"))).v_4262_N(a_3742_W.f_508_U).u_1723_Y(a_3742_W.l_3729_r);
        this.u_1723_Y(a_3742_W.NoInteract).u_1723_Y(a_3742_W.E_4612_l).v_4262_N(a_3742_W.h_3066_J).J_1907_R(a_3742_W.u_1934_K);
        this.G_564_y(a_3742_W.m_1964_F, g_221_o.Y_259_p).J_1907_R(a_3742_W.A_2487_t).v_4262_N(a_3742_W.v_2746_S).u_1723_Y(a_3742_W.b_3334_n);
        this.u_1723_Y(a_3742_W.g_1096_r).J_1907_R(a_3742_W.C_3528_u).v_4262_N(a_3742_W.Y_3066_B).u_1723_Y(a_3742_W.r_2687_x);
        this.u_1723_Y(a_3742_W.u_3578_p).J_1907_R(a_3742_W.z_1100_b).P_1922_E(a_3742_W.V_1824_v).n_1700_B(a_3742_W.e_1503_j).v_4262_N(a_3742_W.Q_1036_Q).u_1723_Y(a_3742_W.TickTrigger);
        this.t_1786_h();
        this.M_588_G(a_3742_W.Y_776_s);
        this.P_4830_p(a_3742_W.l_4537_E);
        this.P_4830_p(a_3742_W.F_2624_D);
        this.P_4830_p(a_3742_W.H_1475_K);
        this.M_182_A();
        this.h_1847_R(a_3742_W.N_260_m);
        this.h_1847_R(a_3742_W.ItemScroller);
        this.h_1847_R(a_3742_W.ItemsCooldown);
        this.Q_4569_t(a_3742_W.c_1608_O);
        this.Q_4569_t(a_3742_W.ModeSetting);
        this.Q_4569_t(a_3742_W.MultiBooleanSetting);
        this.u_2550_I();
        this.M_588_G();
        this.P_1922_E(a_3742_W.P_925_e, g_221_o.v_4262_N);
        this.P_1922_E(a_3742_W.F_2052_z, g_221_o.v_4262_N);
        this.P_1922_E(a_3742_W.H_2506_c, g_221_o.w_1484_f);
        this.Q_4569_t();
        this.A_1038_p();
        this.t_148_a(a_3742_W.I_3457_f, a_3742_W.A_229_v);
        this.t_148_a(a_3742_W.P_4830_p, a_3742_W.I_4477_R);
        this.t_148_a(a_3742_W.g_1734_y, a_3742_W.J_4125_o);
        this.t_148_a(a_3742_W.I_4481_g, a_3742_W.Z_2812_M);
        this.r_715_M();
        this.t_148_a(a_3742_W.f_691_R, a_3742_W.g_46_E);
        SpawnEggItem.v_4262_N().forEach(p_239868_1_ -> this.n_1700_B((q_1613_l)p_239868_1_, A_4115_X.J_1907_R("template_spawn_egg")));
    }

    private /* synthetic */ P_1922_E n_1700_B(int[] p_239876_3_, Int2ObjectMap int2objectmap, T_2915_h p_239876_1_, Integer p_239920_4_) {
        int i = p_239876_3_[p_239920_4_];
        g_2336_b resourcelocation = (g_2336_b)int2objectmap.computeIfAbsent(i, p_239870_3_ -> this.n_1700_B(p_239876_1_, "_stage" + i, G_624_v.e_1992_r, H_2857_Y::u_1723_Y));
        return P_1922_E.n_1700_B().n_1700_B(u_1723_Y.R_4764_Y, resourcelocation);
    }

    class n_1700_B {
        private final H_2857_Y J_1907_R;
        @Nullable
        private g_2336_b R_4764_Y;

        public n_1700_B(H_2857_Y p_i232516_2_) {
            this.J_1907_R = p_i232516_2_;
        }

        public n_1700_B n_1700_B(T_2915_h p_240058_1_, Y_1740_V p_240058_2_) {
            this.R_4764_Y = p_240058_2_.n_1700_B(p_240058_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240058_1_, this.R_4764_Y));
            return this;
        }

        public n_1700_B n_1700_B(Function<H_2857_Y, g_2336_b> p_240059_1_) {
            this.R_4764_Y = p_240059_1_.apply(this.J_1907_R);
            return this;
        }

        public n_1700_B n_1700_B(T_2915_h p_240056_1_) {
            g_2336_b resourcelocation = G_624_v.M_588_G.n_1700_B(p_240056_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.P_4830_p.n_1700_B(p_240056_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.J_1907_R(p_240056_1_, resourcelocation, resourcelocation1));
            g_2336_b resourcelocation2 = G_624_v.h_1847_R.n_1700_B(p_240056_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B(p_240056_1_, resourcelocation2);
            return this;
        }

        public n_1700_B J_1907_R(T_2915_h p_240060_1_) {
            g_2336_b resourcelocation = G_624_v.Q_2552_b.n_1700_B(p_240060_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.C_2741_M.n_1700_B(p_240060_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation2 = G_624_v.k_2293_S.n_1700_B(p_240060_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.n_1700_B(p_240060_1_, resourcelocation, resourcelocation1, resourcelocation2));
            g_2336_b resourcelocation3 = G_624_v.q_2307_F.n_1700_B(p_240060_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B(p_240060_1_, resourcelocation3);
            return this;
        }

        public n_1700_B R_4764_Y(T_2915_h p_240061_1_) {
            g_2336_b resourcelocation = G_624_v.w_1457_N.n_1700_B(p_240061_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.Y_601_j.n_1700_B(p_240061_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240061_1_, resourcelocation, resourcelocation1));
            g_2336_b resourcelocation2 = G_624_v.Y_259_p.n_1700_B(p_240061_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B(p_240061_1_, resourcelocation2);
            return this;
        }

        public n_1700_B G_564_y(T_2915_h p_240062_1_) {
            g_2336_b resourcelocation = G_624_v.c_3005_b.n_1700_B(p_240062_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.Z_875_P.n_1700_B(p_240062_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation2 = G_624_v.A_4115_X.n_1700_B(p_240062_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation3 = G_624_v.H_2857_Y.n_1700_B(p_240062_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.J_1907_R(p_240062_1_, resourcelocation, resourcelocation1, resourcelocation2, resourcelocation3));
            return this;
        }

        public n_1700_B P_1922_E(T_2915_h p_240063_1_) {
            g_2336_b resourcelocation = G_624_v.Y_1740_V.n_1700_B(p_240063_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.t_4043_B.n_1700_B(p_240063_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.P_1922_E(p_240063_1_, resourcelocation, resourcelocation1));
            return this;
        }

        public n_1700_B n_1700_B(T_2915_h p_240057_1_, T_2915_h p_240057_2_) {
            g_2336_b resourcelocation = G_624_v.x_607_J.n_1700_B(p_240057_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240057_1_, resourcelocation));
            v_4262_N.this.n_1700_B.accept(v_4262_N.R_4764_Y(p_240057_2_, resourcelocation));
            v_4262_N.this.n_1700_B(p_240057_1_.u_1723_Y());
            v_4262_N.this.n_1700_B(p_240057_2_);
            return this;
        }

        public n_1700_B u_1723_Y(T_2915_h p_240064_1_) {
            if (this.R_4764_Y == null) {
                throw new IllegalStateException("Full block not generated yet");
            }
            g_2336_b resourcelocation = G_624_v.e_4240_b.n_1700_B(p_240064_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.n_3318_d.n_1700_B(p_240064_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.P_1922_E(p_240064_1_, resourcelocation, resourcelocation1, this.R_4764_Y));
            return this;
        }

        public n_1700_B v_4262_N(T_2915_h p_240065_1_) {
            g_2336_b resourcelocation = G_624_v.v_4276_D.n_1700_B(p_240065_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.z_1737_N.n_1700_B(p_240065_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation2 = G_624_v.d_2461_k.n_1700_B(p_240065_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.J_1907_R(p_240065_1_, resourcelocation, resourcelocation1, resourcelocation2));
            return this;
        }
    }

    class R_4764_Y {
        private final H_2857_Y J_1907_R;

        public R_4764_Y(H_2857_Y p_i232518_2_) {
            this.J_1907_R = p_i232518_2_;
        }

        public R_4764_Y n_1700_B(T_2915_h p_240070_1_) {
            H_2857_Y modeltextures = this.J_1907_R.J_1907_R(T_2506_i.G_564_y, this.J_1907_R.n_1700_B(T_2506_i.t_148_a));
            g_2336_b resourcelocation = G_624_v.P_1922_E.n_1700_B(p_240070_1_, modeltextures, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.G_564_y(p_240070_1_, resourcelocation));
            return this;
        }

        public R_4764_Y J_1907_R(T_2915_h p_240071_1_) {
            g_2336_b resourcelocation = G_624_v.P_1922_E.n_1700_B(p_240071_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.G_564_y(p_240071_1_, resourcelocation));
            return this;
        }

        public R_4764_Y R_4764_Y(T_2915_h p_240072_1_) {
            g_2336_b resourcelocation = G_624_v.P_1922_E.n_1700_B(p_240072_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            g_2336_b resourcelocation1 = G_624_v.u_1723_Y.n_1700_B(p_240072_1_, this.J_1907_R, v_4262_N.this.J_1907_R);
            v_4262_N.this.n_1700_B.accept(v_4262_N.G_564_y(p_240072_1_, resourcelocation, resourcelocation1));
            return this;
        }
    }

    static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y();
        public static final /* enum */ G_564_y J_1907_R = new G_564_y();
        private static final /* synthetic */ G_564_y[] R_4764_Y;

        public static G_564_y[] values() {
            return (G_564_y[])R_4764_Y.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        public Y_1740_V n_1700_B() {
            return this == n_1700_B ? G_624_v.g_164_R : G_624_v.B_1668_F;
        }

        public Y_1740_V J_1907_R() {
            return this == n_1700_B ? G_624_v.Z_976_R : G_624_v.X_933_l;
        }

        private static /* synthetic */ G_564_y[] R_4764_Y() {
            return new G_564_y[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = G_564_y.R_4764_Y();
        }
    }

    class J_1907_R {
        private final g_2336_b J_1907_R;

        public J_1907_R(g_2336_b p_i232515_2_, T_2915_h p_i232515_3_) {
            this.J_1907_R = G_624_v.x_607_J.n_1700_B(p_i232515_2_, H_2857_Y.t_1786_h(p_i232515_3_), v_4262_N.this.J_1907_R);
        }

        public J_1907_R n_1700_B(T_2915_h ... p_240051_1_) {
            for (T_2915_h block : p_240051_1_) {
                v_4262_N.this.n_1700_B.accept(v_4262_N.R_4764_Y(block, this.J_1907_R));
            }
            return this;
        }

        public J_1907_R J_1907_R(T_2915_h ... p_240052_1_) {
            for (T_2915_h block : p_240052_1_) {
                v_4262_N.this.n_1700_B(block);
            }
            return this.n_1700_B(p_240052_1_);
        }

        public J_1907_R n_1700_B(Y_1740_V p_240050_1_, T_2915_h ... p_240050_2_) {
            for (T_2915_h block : p_240050_2_) {
                p_240050_1_.n_1700_B(A_4115_X.n_1700_B(block.u_1723_Y()), H_2857_Y.t_1786_h(block), v_4262_N.this.J_1907_R);
            }
            return this.n_1700_B(p_240050_2_);
        }
    }
}



