/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 */
package net.minecraft.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import net.minecraft.data.T_2506_i;

public class H_2857_Y {
    private final Map<T_2506_i, g_2336_b> n_1700_B = Maps.newHashMap();
    private final Set<T_2506_i> J_1907_R = Sets.newHashSet();

    public H_2857_Y n_1700_B(T_2506_i p_240349_1_, g_2336_b p_240349_2_) {
        this.n_1700_B.put(p_240349_1_, p_240349_2_);
        return this;
    }

    public Stream<T_2506_i> n_1700_B() {
        return this.J_1907_R.stream();
    }

    public H_2857_Y n_1700_B(T_2506_i p_240355_1_, T_2506_i p_240355_2_) {
        this.n_1700_B.put(p_240355_2_, this.n_1700_B.get(p_240355_1_));
        this.J_1907_R.add(p_240355_2_);
        return this;
    }

    public g_2336_b n_1700_B(T_2506_i p_240348_1_) {
        for (T_2506_i stocktexturealiases = p_240348_1_; stocktexturealiases != null; stocktexturealiases = stocktexturealiases.J_1907_R()) {
            g_2336_b resourcelocation = this.n_1700_B.get(stocktexturealiases);
            if (resourcelocation == null) continue;
            return resourcelocation;
        }
        throw new IllegalStateException("Can't find texture for slot " + String.valueOf(p_240348_1_));
    }

    public H_2857_Y J_1907_R(T_2506_i p_240360_1_, g_2336_b p_240360_2_) {
        H_2857_Y modeltextures = new H_2857_Y();
        modeltextures.n_1700_B.putAll(this.n_1700_B);
        modeltextures.J_1907_R.addAll(this.J_1907_R);
        modeltextures.n_1700_B(p_240360_1_, p_240360_2_);
        return modeltextures;
    }

    public static H_2857_Y n_1700_B(T_2915_h p_240345_0_) {
        g_2336_b resourcelocation = H_2857_Y.A_4115_X(p_240345_0_);
        return H_2857_Y.J_1907_R(resourcelocation);
    }

    public static H_2857_Y J_1907_R(T_2915_h p_240353_0_) {
        g_2336_b resourcelocation = H_2857_Y.A_4115_X(p_240353_0_);
        return H_2857_Y.n_1700_B(resourcelocation);
    }

    public static H_2857_Y n_1700_B(g_2336_b p_240350_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.J_1907_R, p_240350_0_);
    }

    public static H_2857_Y J_1907_R(g_2336_b p_240356_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.n_1700_B, p_240356_0_);
    }

    public static H_2857_Y R_4764_Y(T_2915_h p_240358_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.M_182_A, H_2857_Y.A_4115_X(p_240358_0_));
    }

    public static H_2857_Y R_4764_Y(g_2336_b p_240361_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.M_182_A, p_240361_0_);
    }

    public static H_2857_Y G_564_y(T_2915_h p_240362_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.t_1786_h, H_2857_Y.A_4115_X(p_240362_0_));
    }

    public static H_2857_Y G_564_y(g_2336_b p_240365_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.t_1786_h, p_240365_0_);
    }

    public static H_2857_Y P_1922_E(T_2915_h p_240366_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.w_1457_N, H_2857_Y.A_4115_X(p_240366_0_));
    }

    public static H_2857_Y P_1922_E(g_2336_b p_240367_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.w_1457_N, p_240367_0_);
    }

    public static H_2857_Y u_1723_Y(T_2915_h p_240368_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.Y_601_j, H_2857_Y.A_4115_X(p_240368_0_));
    }

    public static H_2857_Y v_4262_N(T_2915_h p_240369_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.q_2307_F, H_2857_Y.A_4115_X(p_240369_0_));
    }

    public static H_2857_Y n_1700_B(T_2915_h p_240346_0_, T_2915_h p_240346_1_) {
        return new H_2857_Y().n_1700_B(T_2506_i.q_2307_F, H_2857_Y.A_4115_X(p_240346_0_)).n_1700_B(T_2506_i.Z_875_P, H_2857_Y.A_4115_X(p_240346_1_));
    }

    public static H_2857_Y w_1484_f(T_2915_h p_240371_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.Y_259_p, H_2857_Y.A_4115_X(p_240371_0_));
    }

    public static H_2857_Y t_148_a(T_2915_h p_240373_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.k_2293_S, H_2857_Y.A_4115_X(p_240373_0_));
    }

    public static H_2857_Y u_1723_Y(g_2336_b p_240370_0_) {
        return H_2857_Y.R_4764_Y(T_2506_i.c_3005_b, p_240370_0_);
    }

    public static H_2857_Y J_1907_R(T_2915_h p_240354_0_, T_2915_h p_240354_1_) {
        return new H_2857_Y().n_1700_B(T_2506_i.Q_2552_b, H_2857_Y.A_4115_X(p_240354_0_)).n_1700_B(T_2506_i.C_2741_M, H_2857_Y.n_1700_B(p_240354_1_, "_top"));
    }

    public static H_2857_Y R_4764_Y(T_2506_i p_240364_0_, g_2336_b p_240364_1_) {
        return new H_2857_Y().n_1700_B(p_240364_0_, p_240364_1_);
    }

    public static H_2857_Y s_956_w(T_2915_h p_240375_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240375_0_, "_side")).n_1700_B(T_2506_i.G_564_y, H_2857_Y.n_1700_B(p_240375_0_, "_top"));
    }

    public static H_2857_Y u_2550_I(T_2915_h p_240377_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240377_0_, "_side")).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(p_240377_0_, "_top"));
    }

    public static H_2857_Y M_588_G(T_2915_h p_240378_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.A_4115_X(p_240378_0_)).n_1700_B(T_2506_i.G_564_y, H_2857_Y.n_1700_B(p_240378_0_, "_top"));
    }

    public static H_2857_Y n_1700_B(g_2336_b p_240351_0_, g_2336_b p_240351_1_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, p_240351_0_).n_1700_B(T_2506_i.G_564_y, p_240351_1_);
    }

    public static H_2857_Y P_4830_p(T_2915_h p_240379_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240379_0_, "_side")).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(p_240379_0_, "_top")).n_1700_B(T_2506_i.P_1922_E, H_2857_Y.n_1700_B(p_240379_0_, "_bottom"));
    }

    public static H_2857_Y h_1847_R(T_2915_h p_240380_0_) {
        g_2336_b resourcelocation = H_2857_Y.A_4115_X(p_240380_0_);
        return new H_2857_Y().n_1700_B(T_2506_i.multiplayerClientSuggestionProvider, resourcelocation).n_1700_B(T_2506_i.t_148_a, resourcelocation).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(p_240380_0_, "_top")).n_1700_B(T_2506_i.P_1922_E, H_2857_Y.n_1700_B(p_240380_0_, "_bottom"));
    }

    public static H_2857_Y Q_4569_t(T_2915_h p_240381_0_) {
        g_2336_b resourcelocation = H_2857_Y.A_4115_X(p_240381_0_);
        return new H_2857_Y().n_1700_B(T_2506_i.multiplayerClientSuggestionProvider, resourcelocation).n_1700_B(T_2506_i.t_148_a, resourcelocation).n_1700_B(T_2506_i.G_564_y, H_2857_Y.n_1700_B(p_240381_0_, "_top"));
    }

    public static H_2857_Y M_182_A(T_2915_h p_240382_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(p_240382_0_, "_top")).n_1700_B(T_2506_i.P_1922_E, H_2857_Y.n_1700_B(p_240382_0_, "_bottom"));
    }

    public static H_2857_Y t_1786_h(T_2915_h p_240383_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.R_4764_Y, H_2857_Y.A_4115_X(p_240383_0_));
    }

    public static H_2857_Y v_4262_N(g_2336_b p_240372_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.R_4764_Y, p_240372_0_);
    }

    public static H_2857_Y multiplayerClientSuggestionProvider(T_2915_h p_240384_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.A_4115_X, H_2857_Y.n_1700_B(p_240384_0_, "_0"));
    }

    public static H_2857_Y w_1457_N(T_2915_h p_240385_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.A_4115_X, H_2857_Y.n_1700_B(p_240385_0_, "_1"));
    }

    public static H_2857_Y Y_601_j(T_2915_h p_240386_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.Y_1740_V, H_2857_Y.A_4115_X(p_240386_0_));
    }

    public static H_2857_Y Y_259_p(T_2915_h p_240387_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.e_4240_b, H_2857_Y.A_4115_X(p_240387_0_));
    }

    public static H_2857_Y w_1484_f(g_2336_b p_240374_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.e_4240_b, p_240374_0_);
    }

    public static H_2857_Y n_1700_B(q_1613_l p_240343_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.R_4764_Y, H_2857_Y.R_4764_Y(p_240343_0_));
    }

    public static H_2857_Y Q_2552_b(T_2915_h p_240388_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240388_0_, "_side")).n_1700_B(T_2506_i.v_4262_N, H_2857_Y.n_1700_B(p_240388_0_, "_front")).n_1700_B(T_2506_i.w_1484_f, H_2857_Y.n_1700_B(p_240388_0_, "_back"));
    }

    public static H_2857_Y C_2741_M(T_2915_h p_240389_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240389_0_, "_side")).n_1700_B(T_2506_i.v_4262_N, H_2857_Y.n_1700_B(p_240389_0_, "_front")).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(p_240389_0_, "_top")).n_1700_B(T_2506_i.P_1922_E, H_2857_Y.n_1700_B(p_240389_0_, "_bottom"));
    }

    public static H_2857_Y k_2293_S(T_2915_h p_240390_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240390_0_, "_side")).n_1700_B(T_2506_i.v_4262_N, H_2857_Y.n_1700_B(p_240390_0_, "_front")).n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(p_240390_0_, "_top"));
    }

    public static H_2857_Y q_2307_F(T_2915_h p_240391_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.t_148_a, H_2857_Y.n_1700_B(p_240391_0_, "_side")).n_1700_B(T_2506_i.v_4262_N, H_2857_Y.n_1700_B(p_240391_0_, "_front")).n_1700_B(T_2506_i.G_564_y, H_2857_Y.n_1700_B(p_240391_0_, "_end"));
    }

    public static H_2857_Y Z_875_P(T_2915_h p_240392_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.u_1723_Y, H_2857_Y.n_1700_B(p_240392_0_, "_top"));
    }

    public static H_2857_Y R_4764_Y(T_2915_h p_240359_0_, T_2915_h p_240359_1_) {
        return new H_2857_Y().n_1700_B(T_2506_i.R_4764_Y, H_2857_Y.n_1700_B(p_240359_0_, "_front")).n_1700_B(T_2506_i.Q_4569_t, H_2857_Y.A_4115_X(p_240359_1_)).n_1700_B(T_2506_i.h_1847_R, H_2857_Y.n_1700_B(p_240359_0_, "_top")).n_1700_B(T_2506_i.s_956_w, H_2857_Y.n_1700_B(p_240359_0_, "_front")).n_1700_B(T_2506_i.M_588_G, H_2857_Y.n_1700_B(p_240359_0_, "_side")).n_1700_B(T_2506_i.u_2550_I, H_2857_Y.n_1700_B(p_240359_0_, "_side")).n_1700_B(T_2506_i.P_4830_p, H_2857_Y.n_1700_B(p_240359_0_, "_front"));
    }

    public static H_2857_Y G_564_y(T_2915_h p_240363_0_, T_2915_h p_240363_1_) {
        return new H_2857_Y().n_1700_B(T_2506_i.R_4764_Y, H_2857_Y.n_1700_B(p_240363_0_, "_front")).n_1700_B(T_2506_i.Q_4569_t, H_2857_Y.A_4115_X(p_240363_1_)).n_1700_B(T_2506_i.h_1847_R, H_2857_Y.n_1700_B(p_240363_0_, "_top")).n_1700_B(T_2506_i.s_956_w, H_2857_Y.n_1700_B(p_240363_0_, "_front")).n_1700_B(T_2506_i.u_2550_I, H_2857_Y.n_1700_B(p_240363_0_, "_front")).n_1700_B(T_2506_i.M_588_G, H_2857_Y.n_1700_B(p_240363_0_, "_side")).n_1700_B(T_2506_i.P_4830_p, H_2857_Y.n_1700_B(p_240363_0_, "_side"));
    }

    public static H_2857_Y c_3005_b(T_2915_h p_240339_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.d_2427_y, H_2857_Y.n_1700_B(p_240339_0_, "_log_lit")).n_1700_B(T_2506_i.A_4115_X, H_2857_Y.n_1700_B(p_240339_0_, "_fire"));
    }

    public static H_2857_Y J_1907_R(q_1613_l p_240352_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.n_3318_d, H_2857_Y.R_4764_Y(p_240352_0_));
    }

    public static H_2857_Y H_2857_Y(T_2915_h p_240340_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.n_3318_d, H_2857_Y.A_4115_X(p_240340_0_));
    }

    public static H_2857_Y t_148_a(g_2336_b p_240376_0_) {
        return new H_2857_Y().n_1700_B(T_2506_i.n_3318_d, p_240376_0_);
    }

    public static g_2336_b A_4115_X(T_2915_h p_240341_0_) {
        g_2336_b resourcelocation = V_3137_a.q_4610_l.J_1907_R(p_240341_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "block/" + resourcelocation.J_1907_R());
    }

    public static g_2336_b n_1700_B(T_2915_h p_240347_0_, String p_240347_1_) {
        g_2336_b resourcelocation = V_3137_a.q_4610_l.J_1907_R(p_240347_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "block/" + resourcelocation.J_1907_R() + p_240347_1_);
    }

    public static g_2336_b R_4764_Y(q_1613_l p_240357_0_) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(p_240357_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "item/" + resourcelocation.J_1907_R());
    }

    public static g_2336_b n_1700_B(q_1613_l p_240344_0_, String p_240344_1_) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(p_240344_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "item/" + resourcelocation.J_1907_R() + p_240344_1_);
    }
}


