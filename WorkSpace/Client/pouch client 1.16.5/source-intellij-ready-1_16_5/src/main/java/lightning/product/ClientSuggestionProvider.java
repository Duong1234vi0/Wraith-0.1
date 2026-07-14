/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import lightning.product.A_2226_Q;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.I_4838_g;
import lightning.product.V_4217_p;
import lightning.product.V_772_m;
import lightning.product.W_2853_p;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.r_4097_j;
import lightning.product.EntityHitResult;

public class ClientSuggestionProvider
implements V_4217_p {
    private final W_2853_p n_1700_B;
    private final MinecraftClient J_1907_R;
    private int R_4764_Y = -1;
    private CompletableFuture<Suggestions> G_564_y;

    public ClientSuggestionProvider(W_2853_p p_i49558_1_, MinecraftClient p_i49558_2_) {
        this.n_1700_B = p_i49558_1_;
        this.J_1907_R = p_i49558_2_;
    }

    @Override
    public Collection<String> n_1700_B() {
        ArrayList list = Lists.newArrayList();
        for (A_2226_Q networkplayerinfo : this.n_1700_B.P_1922_E()) {
            list.add(networkplayerinfo.n_1700_B().getName());
        }
        return list;
    }

    @Override
    public Collection<String> J_1907_R() {
        return this.J_1907_R.Z_875_P != null && this.J_1907_R.Z_875_P.R_4764_Y() == HitResult.n_1700_B.R_4764_Y ? Collections.singleton(((EntityHitResult)this.J_1907_R.Z_875_P).n_1700_B().F_518_D()) : Collections.emptyList();
    }

    @Override
    public Collection<String> R_4764_Y() {
        return this.n_1700_B.s_956_w().Q_4569_t().G_564_y();
    }

    @Override
    public Collection<g_2336_b> G_564_y() {
        return this.J_1907_R.Z_976_R().n_1700_B();
    }

    @Override
    public Stream<g_2336_b> P_1922_E() {
        return this.n_1700_B.R_4764_Y().R_4764_Y();
    }

    @Override
    public boolean n_1700_B(int level) {
        V_772_m clientplayerentity = this.J_1907_R.Y_259_p;
        return clientplayerentity != null ? clientplayerentity.t_148_a(level) : level == 0;
    }

    @Override
    public CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> context, SuggestionsBuilder suggestionsBuilder) {
        if (this.G_564_y != null) {
            this.G_564_y.cancel(false);
        }
        this.G_564_y = new CompletableFuture();
        int i = ++this.R_4764_Y;
        this.n_1700_B.n_1700_B(new I_4838_g(i, context.getInput()));
        return this.G_564_y;
    }

    private static String n_1700_B(double p_209001_0_) {
        return String.format(Locale.ROOT, "%.2f", p_209001_0_);
    }

    private static String J_1907_R(int p_209002_0_) {
        return Integer.toString(p_209002_0_);
    }

    @Override
    public Collection<V_4217_p.n_1700_B> u_1723_Y() {
        HitResult raytraceresult = this.J_1907_R.Z_875_P;
        if (raytraceresult != null && raytraceresult.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            c_1514_x blockpos = ((BlockHitResult)raytraceresult).n_1700_B();
            return Collections.singleton(new V_4217_p.n_1700_B(ClientSuggestionProvider.J_1907_R(blockpos.getX()), ClientSuggestionProvider.J_1907_R(blockpos.getY()), ClientSuggestionProvider.J_1907_R(blockpos.getZ())));
        }
        return V_4217_p.super.u_1723_Y();
    }

    @Override
    public Collection<V_4217_p.n_1700_B> v_4262_N() {
        HitResult raytraceresult = this.J_1907_R.Z_875_P;
        if (raytraceresult != null && raytraceresult.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            e_2866_D vector3d = raytraceresult.P_1922_E();
            return Collections.singleton(new V_4217_p.n_1700_B(ClientSuggestionProvider.n_1700_B(vector3d.J_1907_R), ClientSuggestionProvider.n_1700_B(vector3d.R_4764_Y), ClientSuggestionProvider.n_1700_B(vector3d.G_564_y)));
        }
        return V_4217_p.super.v_4262_N();
    }

    @Override
    public Set<f_2392_k<b_4507_u>> w_1484_f() {
        return this.n_1700_B.h_1847_R();
    }

    @Override
    public r_4097_j t_148_a() {
        return this.n_1700_B.Q_4569_t();
    }

    public void n_1700_B(int transaction, Suggestions result) {
        if (transaction == this.R_4764_Y) {
            this.G_564_y.complete(result);
            this.G_564_y = null;
            this.R_4764_Y = -1;
        }
    }
}



