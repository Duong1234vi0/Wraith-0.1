/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 */
package net.minecraft.data;

import com.google.gson.JsonElement;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.T_2915_h;
import lightning.product.g_2336_b;
import net.minecraft.data.G_624_v;
import net.minecraft.data.H_2857_Y;
import net.minecraft.data.Y_1740_V;

public class g_221_o {
    public static final n_1700_B n_1700_B = g_221_o.n_1700_B(H_2857_Y::n_1700_B, G_624_v.R_4764_Y);
    public static final n_1700_B J_1907_R = g_221_o.n_1700_B(H_2857_Y::n_1700_B, G_624_v.G_564_y);
    public static final n_1700_B R_4764_Y = g_221_o.n_1700_B(H_2857_Y::s_956_w, G_624_v.P_1922_E);
    public static final n_1700_B G_564_y = g_221_o.n_1700_B(H_2857_Y::s_956_w, G_624_v.u_1723_Y);
    public static final n_1700_B P_1922_E = g_221_o.n_1700_B(H_2857_Y::P_4830_p, G_624_v.w_1484_f);
    public static final n_1700_B u_1723_Y = g_221_o.n_1700_B(H_2857_Y::u_2550_I, G_624_v.v_4262_N);
    public static final n_1700_B v_4262_N = g_221_o.n_1700_B(H_2857_Y::k_2293_S, G_624_v.t_148_a);
    public static final n_1700_B w_1484_f = g_221_o.n_1700_B(H_2857_Y::C_2741_M, G_624_v.s_956_w);
    public static final n_1700_B t_148_a = g_221_o.n_1700_B(H_2857_Y::u_1723_Y, G_624_v.T_3594_S);
    public static final n_1700_B s_956_w = g_221_o.n_1700_B(H_2857_Y::w_1484_f, G_624_v.l_1233_K);
    public static final n_1700_B u_2550_I = g_221_o.n_1700_B(H_2857_Y::t_148_a, G_624_v.D_4792_h);
    public static final n_1700_B M_588_G = g_221_o.n_1700_B(H_2857_Y::t_1786_h, G_624_v.x_607_J);
    public static final n_1700_B P_4830_p = g_221_o.n_1700_B(H_2857_Y::Z_875_P, G_624_v.f_4016_n);
    public static final n_1700_B h_1847_R = g_221_o.n_1700_B(H_2857_Y::n_1700_B, G_624_v.d_2427_y);
    public static final n_1700_B Q_4569_t = g_221_o.n_1700_B(H_2857_Y::Y_601_j, G_624_v.V_1225_t);
    public static final n_1700_B M_182_A = g_221_o.n_1700_B(H_2857_Y::Y_601_j, G_624_v.U_1241_n);
    public static final n_1700_B t_1786_h = g_221_o.n_1700_B(H_2857_Y::J_1907_R, G_624_v.M_1641_O);
    public static final n_1700_B multiplayerClientSuggestionProvider = g_221_o.n_1700_B(H_2857_Y::M_588_G, G_624_v.P_1922_E);
    public static final n_1700_B w_1457_N = g_221_o.n_1700_B(H_2857_Y::M_588_G, G_624_v.u_1723_Y);
    public static final n_1700_B Y_601_j = g_221_o.n_1700_B(H_2857_Y::h_1847_R, G_624_v.w_1484_f);
    public static final n_1700_B Y_259_p = g_221_o.n_1700_B(H_2857_Y::Q_4569_t, G_624_v.P_1922_E);
    private final H_2857_Y Q_2552_b;
    private final Y_1740_V C_2741_M;

    private g_221_o(H_2857_Y p_i232548_1_, Y_1740_V p_i232548_2_) {
        this.Q_2552_b = p_i232548_1_;
        this.C_2741_M = p_i232548_2_;
    }

    public Y_1740_V n_1700_B() {
        return this.C_2741_M;
    }

    public H_2857_Y J_1907_R() {
        return this.Q_2552_b;
    }

    public g_221_o n_1700_B(Consumer<H_2857_Y> p_240460_1_) {
        p_240460_1_.accept(this.Q_2552_b);
        return this;
    }

    public g_2336_b n_1700_B(T_2915_h p_240459_1_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240459_2_) {
        return this.C_2741_M.n_1700_B(p_240459_1_, this.Q_2552_b, p_240459_2_);
    }

    public g_2336_b n_1700_B(T_2915_h p_240458_1_, String p_240458_2_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240458_3_) {
        return this.C_2741_M.n_1700_B(p_240458_1_, p_240458_2_, this.Q_2552_b, p_240458_3_);
    }

    private static n_1700_B n_1700_B(Function<T_2915_h, H_2857_Y> p_240461_0_, Y_1740_V p_240461_1_) {
        return p_240462_2_ -> new g_221_o((H_2857_Y)p_240461_0_.apply(p_240462_2_), p_240461_1_);
    }

    public static g_221_o n_1700_B(g_2336_b p_240463_0_) {
        return new g_221_o(H_2857_Y.J_1907_R(p_240463_0_), G_624_v.R_4764_Y);
    }

    @FunctionalInterface
    public static interface n_1700_B {
        public g_221_o get(T_2915_h var1);

        default public g_2336_b n_1700_B(T_2915_h p_240466_1_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240466_2_) {
            return this.get(p_240466_1_).n_1700_B(p_240466_1_, p_240466_2_);
        }

        default public g_2336_b n_1700_B(T_2915_h p_240465_1_, String p_240465_2_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240465_3_) {
            return this.get(p_240465_1_).n_1700_B(p_240465_1_, p_240465_2_, p_240465_3_);
        }

        default public n_1700_B n_1700_B(Consumer<H_2857_Y> p_240467_1_) {
            return p_240468_2_ -> this.get(p_240468_2_).n_1700_B(p_240467_1_);
        }
    }
}


