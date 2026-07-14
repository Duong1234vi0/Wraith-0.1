/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import lightning.product.E_2561_m;
import lightning.product.F_2904_S;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import lightning.product.r_4318_c;
import lightning.product.v_3760_Q;

public class ItemParser {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.item.tag.disallowed"));
    public static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208696_0_ -> new F_2904_S("argument.item.id.invalid", p_208696_0_));
    private static final BiFunction<SuggestionsBuilder, E_2561_m<q_1613_l>, CompletableFuture<Suggestions>> R_4764_Y = (p_239571_0_, p_239571_1_) -> p_239571_0_.buildFuture();
    private final StringReader G_564_y;
    private final boolean P_1922_E;
    private final Map<v_3760_Q<?>, Comparable<?>> u_1723_Y = Maps.newHashMap();
    private q_1613_l v_4262_N;
    @Nullable
    private U_2912_j w_1484_f;
    private g_2336_b t_148_a = new g_2336_b("");
    private int s_956_w;
    private BiFunction<SuggestionsBuilder, E_2561_m<q_1613_l>, CompletableFuture<Suggestions>> u_2550_I = R_4764_Y;

    public ItemParser(StringReader readerIn, boolean allowTags) {
        this.G_564_y = readerIn;
        this.P_1922_E = allowTags;
    }

    public q_1613_l n_1700_B() {
        return this.v_4262_N;
    }

    @Nullable
    public U_2912_j J_1907_R() {
        return this.w_1484_f;
    }

    public g_2336_b R_4764_Y() {
        return this.t_148_a;
    }

    public void G_564_y() throws CommandSyntaxException {
        int i = this.G_564_y.getCursor();
        g_2336_b resourcelocation = g_2336_b.n_1700_B(this.G_564_y);
        this.v_4262_N = V_3137_a.e_2887_G.J_1907_R(resourcelocation).orElseThrow(() -> {
            this.G_564_y.setCursor(i);
            return J_1907_R.createWithContext((ImmutableStringReader)this.G_564_y, (Object)resourcelocation.toString());
        });
    }

    public void P_1922_E() throws CommandSyntaxException {
        if (!this.P_1922_E) {
            throw n_1700_B.create();
        }
        this.u_2550_I = this::R_4764_Y;
        this.G_564_y.expect('#');
        this.s_956_w = this.G_564_y.getCursor();
        this.t_148_a = g_2336_b.n_1700_B(this.G_564_y);
    }

    public void u_1723_Y() throws CommandSyntaxException {
        this.w_1484_f = new r_4318_c(this.G_564_y).u_1723_Y();
    }

    public ItemParser v_4262_N() throws CommandSyntaxException {
        this.u_2550_I = this::G_564_y;
        if (this.G_564_y.canRead() && this.G_564_y.peek() == '#') {
            this.P_1922_E();
        } else {
            this.G_564_y();
            this.u_2550_I = this::J_1907_R;
        }
        if (this.G_564_y.canRead() && this.G_564_y.peek() == '{') {
            this.u_2550_I = R_4764_Y;
            this.u_1723_Y();
        }
        return this;
    }

    private CompletableFuture<Suggestions> J_1907_R(SuggestionsBuilder builder, E_2561_m<q_1613_l> p_197328_2_) {
        if (builder.getRemaining().isEmpty()) {
            builder.suggest(String.valueOf('{'));
        }
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> R_4764_Y(SuggestionsBuilder builder, E_2561_m<q_1613_l> p_201955_2_) {
        return V_4217_p.n_1700_B(p_201955_2_.J_1907_R(), builder.createOffset(this.s_956_w));
    }

    private CompletableFuture<Suggestions> G_564_y(SuggestionsBuilder builder, E_2561_m<q_1613_l> p_197331_2_) {
        if (this.P_1922_E) {
            V_4217_p.n_1700_B(p_197331_2_.J_1907_R(), builder, String.valueOf('#'));
        }
        return V_4217_p.n_1700_B(V_3137_a.e_2887_G.G_564_y(), builder);
    }

    public CompletableFuture<Suggestions> n_1700_B(SuggestionsBuilder builder, E_2561_m<q_1613_l> p_197329_2_) {
        return this.u_2550_I.apply(builder.createOffset(this.G_564_y.getCursor()), p_197329_2_);
    }
}


