/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.Dynamic3CommandExceptionType
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
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.Dynamic3CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import lightning.product.E_2561_m;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.Y_1835_y;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.r_4318_c;
import lightning.product.v_3760_Q;

public class f_71_T {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.block.tag.disallowed"));
    public static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208687_0_ -> new F_2904_S("argument.block.id.invalid", p_208687_0_));
    public static final Dynamic2CommandExceptionType R_4764_Y = new Dynamic2CommandExceptionType((p_208685_0_, p_208685_1_) -> new F_2904_S("argument.block.property.unknown", p_208685_0_, p_208685_1_));
    public static final Dynamic2CommandExceptionType G_564_y = new Dynamic2CommandExceptionType((p_208690_0_, p_208690_1_) -> new F_2904_S("argument.block.property.duplicate", p_208690_1_, p_208690_0_));
    public static final Dynamic3CommandExceptionType P_1922_E = new Dynamic3CommandExceptionType((p_208684_0_, p_208684_1_, p_208684_2_) -> new F_2904_S("argument.block.property.invalid", p_208684_0_, p_208684_2_, p_208684_1_));
    public static final Dynamic2CommandExceptionType u_1723_Y = new Dynamic2CommandExceptionType((p_208689_0_, p_208689_1_) -> new F_2904_S("argument.block.property.novalue", p_208689_0_, p_208689_1_));
    public static final SimpleCommandExceptionType v_4262_N = new SimpleCommandExceptionType((Message)new F_2904_S("argument.block.property.unclosed"));
    private static final BiFunction<SuggestionsBuilder, E_2561_m<T_2915_h>, CompletableFuture<Suggestions>> w_1484_f = (p_239308_0_, p_239308_1_) -> p_239308_0_.buildFuture();
    private final StringReader t_148_a;
    private final boolean s_956_w;
    private final Map<v_3760_Q<?>, Comparable<?>> u_2550_I = Maps.newHashMap();
    private final Map<String, String> M_588_G = Maps.newHashMap();
    private g_2336_b P_4830_p = new g_2336_b("");
    private Y_1835_y<T_2915_h, K_4074_S> h_1847_R;
    private K_4074_S Q_4569_t;
    @Nullable
    private U_2912_j M_182_A;
    private g_2336_b t_1786_h = new g_2336_b("");
    private int multiplayerClientSuggestionProvider;
    private BiFunction<SuggestionsBuilder, E_2561_m<T_2915_h>, CompletableFuture<Suggestions>> w_1457_N = w_1484_f;

    public f_71_T(StringReader readerIn, boolean allowTags) {
        this.t_148_a = readerIn;
        this.s_956_w = allowTags;
    }

    public Map<v_3760_Q<?>, Comparable<?>> n_1700_B() {
        return this.u_2550_I;
    }

    @Nullable
    public K_4074_S J_1907_R() {
        return this.Q_4569_t;
    }

    @Nullable
    public U_2912_j R_4764_Y() {
        return this.M_182_A;
    }

    @Nullable
    public g_2336_b G_564_y() {
        return this.t_1786_h;
    }

    public f_71_T n_1700_B(boolean parseTileEntity) throws CommandSyntaxException {
        this.w_1457_N = this::M_588_G;
        if (this.t_148_a.canRead() && this.t_148_a.peek() == '#') {
            this.u_1723_Y();
            this.w_1457_N = this::t_148_a;
            if (this.t_148_a.canRead() && this.t_148_a.peek() == '[') {
                this.w_1484_f();
                this.w_1457_N = this::u_1723_Y;
            }
        } else {
            this.P_1922_E();
            this.w_1457_N = this::s_956_w;
            if (this.t_148_a.canRead() && this.t_148_a.peek() == '[') {
                this.v_4262_N();
                this.w_1457_N = this::u_1723_Y;
            }
        }
        if (parseTileEntity && this.t_148_a.canRead() && this.t_148_a.peek() == '{') {
            this.w_1457_N = w_1484_f;
            this.t_148_a();
        }
        return this;
    }

    private CompletableFuture<Suggestions> J_1907_R(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197252_2_) {
        if (builder.getRemaining().isEmpty()) {
            builder.suggest(String.valueOf(']'));
        }
        return this.G_564_y(builder, p_197252_2_);
    }

    private CompletableFuture<Suggestions> R_4764_Y(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_200136_2_) {
        if (builder.getRemaining().isEmpty()) {
            builder.suggest(String.valueOf(']'));
        }
        return this.P_1922_E(builder, p_200136_2_);
    }

    private CompletableFuture<Suggestions> G_564_y(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197256_2_) {
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        for (v_3760_Q property : this.Q_4569_t.k_2293_S()) {
            if (this.u_2550_I.containsKey(property) || !property.P_1922_E().startsWith(s)) continue;
            builder.suggest(property.P_1922_E() + "=");
        }
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> P_1922_E(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_200134_2_) {
        r_109_r<T_2915_h> itag;
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        if (this.t_1786_h != null && !this.t_1786_h.J_1907_R().isEmpty() && (itag = p_200134_2_.n_1700_B(this.t_1786_h)) != null) {
            for (T_2915_h block : itag.n_1700_B()) {
                for (v_3760_Q<?> property : block.t_1786_h().G_564_y()) {
                    if (this.M_588_G.containsKey(property.P_1922_E()) || !property.P_1922_E().startsWith(s)) continue;
                    builder.suggest(property.P_1922_E() + "=");
                }
            }
        }
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> u_1723_Y(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197244_2_) {
        if (builder.getRemaining().isEmpty() && this.n_1700_B(p_197244_2_)) {
            builder.suggest(String.valueOf('{'));
        }
        return builder.buildFuture();
    }

    private boolean n_1700_B(E_2561_m<T_2915_h> p_212598_1_) {
        r_109_r<T_2915_h> itag;
        if (this.Q_4569_t != null) {
            return this.Q_4569_t.J_1907_R().G_564_y();
        }
        if (this.t_1786_h != null && (itag = p_212598_1_.n_1700_B(this.t_1786_h)) != null) {
            for (T_2915_h block : itag.n_1700_B()) {
                if (!block.G_564_y()) continue;
                return true;
            }
        }
        return false;
    }

    private CompletableFuture<Suggestions> v_4262_N(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197246_2_) {
        if (builder.getRemaining().isEmpty()) {
            builder.suggest(String.valueOf('='));
        }
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> w_1484_f(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197248_2_) {
        if (builder.getRemaining().isEmpty()) {
            builder.suggest(String.valueOf(']'));
        }
        if (builder.getRemaining().isEmpty() && this.u_2550_I.size() < this.Q_4569_t.k_2293_S().size()) {
            builder.suggest(String.valueOf(','));
        }
        return builder.buildFuture();
    }

    private static <T extends Comparable<T>> SuggestionsBuilder n_1700_B(SuggestionsBuilder builder, v_3760_Q<T> property) {
        for (Comparable t : property.n_1700_B()) {
            if (t instanceof Integer) {
                builder.suggest(((Integer)t).intValue());
                continue;
            }
            builder.suggest(property.n_1700_B(t));
        }
        return builder;
    }

    private CompletableFuture<Suggestions> n_1700_B(SuggestionsBuilder p_239295_1_, E_2561_m<T_2915_h> p_239295_2_, String p_239295_3_) {
        r_109_r<T_2915_h> itag;
        boolean flag = false;
        if (this.t_1786_h != null && !this.t_1786_h.J_1907_R().isEmpty() && (itag = p_239295_2_.n_1700_B(this.t_1786_h)) != null) {
            block0: for (T_2915_h block : itag.n_1700_B()) {
                v_3760_Q<?> property = block.t_1786_h().n_1700_B(p_239295_3_);
                if (property != null) {
                    f_71_T.n_1700_B(p_239295_1_, property);
                }
                if (flag) continue;
                for (v_3760_Q<?> property1 : block.t_1786_h().G_564_y()) {
                    if (this.M_588_G.containsKey(property1.P_1922_E())) continue;
                    flag = true;
                    continue block0;
                }
            }
        }
        if (flag) {
            p_239295_1_.suggest(String.valueOf(','));
        }
        p_239295_1_.suggest(String.valueOf(']'));
        return p_239295_1_.buildFuture();
    }

    private CompletableFuture<Suggestions> t_148_a(SuggestionsBuilder p_212599_1_, E_2561_m<T_2915_h> p_212599_2_) {
        r_109_r<T_2915_h> itag;
        if (p_212599_1_.getRemaining().isEmpty() && (itag = p_212599_2_.n_1700_B(this.t_1786_h)) != null) {
            T_2915_h block;
            boolean flag = false;
            boolean flag1 = false;
            Iterator<T_2915_h> iterator = itag.n_1700_B().iterator();
            while (!(!iterator.hasNext() || (flag |= !(block = iterator.next()).t_1786_h().G_564_y().isEmpty()) && (flag1 |= block.G_564_y()))) {
            }
            if (flag) {
                p_212599_1_.suggest(String.valueOf('['));
            }
            if (flag1) {
                p_212599_1_.suggest(String.valueOf('{'));
            }
        }
        return this.u_2550_I(p_212599_1_, p_212599_2_);
    }

    private CompletableFuture<Suggestions> s_956_w(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197255_2_) {
        if (builder.getRemaining().isEmpty()) {
            if (!this.Q_4569_t.J_1907_R().t_1786_h().G_564_y().isEmpty()) {
                builder.suggest(String.valueOf('['));
            }
            if (this.Q_4569_t.J_1907_R().G_564_y()) {
                builder.suggest(String.valueOf('{'));
            }
        }
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> u_2550_I(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_201953_2_) {
        return V_4217_p.n_1700_B(p_201953_2_.J_1907_R(), builder.createOffset(this.multiplayerClientSuggestionProvider).add(builder));
    }

    private CompletableFuture<Suggestions> M_588_G(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197250_2_) {
        if (this.s_956_w) {
            V_4217_p.n_1700_B(p_197250_2_.J_1907_R(), builder, String.valueOf('#'));
        }
        V_4217_p.n_1700_B(V_3137_a.q_4610_l.G_564_y(), builder);
        return builder.buildFuture();
    }

    public void P_1922_E() throws CommandSyntaxException {
        int i = this.t_148_a.getCursor();
        this.P_4830_p = g_2336_b.n_1700_B(this.t_148_a);
        T_2915_h block = V_3137_a.q_4610_l.J_1907_R(this.P_4830_p).orElseThrow(() -> {
            this.t_148_a.setCursor(i);
            return J_1907_R.createWithContext((ImmutableStringReader)this.t_148_a, (Object)this.P_4830_p.toString());
        });
        this.h_1847_R = block.t_1786_h();
        this.Q_4569_t = block.multiplayerClientSuggestionProvider();
    }

    public void u_1723_Y() throws CommandSyntaxException {
        if (!this.s_956_w) {
            throw n_1700_B.create();
        }
        this.w_1457_N = this::u_2550_I;
        this.t_148_a.expect('#');
        this.multiplayerClientSuggestionProvider = this.t_148_a.getCursor();
        this.t_1786_h = g_2336_b.n_1700_B(this.t_148_a);
    }

    public void v_4262_N() throws CommandSyntaxException {
        this.t_148_a.skip();
        this.w_1457_N = this::J_1907_R;
        this.t_148_a.skipWhitespace();
        while (this.t_148_a.canRead() && this.t_148_a.peek() != ']') {
            this.t_148_a.skipWhitespace();
            int i = this.t_148_a.getCursor();
            String s = this.t_148_a.readString();
            v_3760_Q<?> property = this.h_1847_R.n_1700_B(s);
            if (property == null) {
                this.t_148_a.setCursor(i);
                throw R_4764_Y.createWithContext((ImmutableStringReader)this.t_148_a, (Object)this.P_4830_p.toString(), (Object)s);
            }
            if (this.u_2550_I.containsKey(property)) {
                this.t_148_a.setCursor(i);
                throw G_564_y.createWithContext((ImmutableStringReader)this.t_148_a, (Object)this.P_4830_p.toString(), (Object)s);
            }
            this.t_148_a.skipWhitespace();
            this.w_1457_N = this::v_4262_N;
            if (!this.t_148_a.canRead() || this.t_148_a.peek() != '=') {
                throw u_1723_Y.createWithContext((ImmutableStringReader)this.t_148_a, (Object)this.P_4830_p.toString(), (Object)s);
            }
            this.t_148_a.skip();
            this.t_148_a.skipWhitespace();
            this.w_1457_N = (p_197251_1_, p_197251_2_) -> f_71_T.n_1700_B(p_197251_1_, property).buildFuture();
            int j = this.t_148_a.getCursor();
            this.n_1700_B(property, this.t_148_a.readString(), j);
            this.w_1457_N = this::w_1484_f;
            this.t_148_a.skipWhitespace();
            if (!this.t_148_a.canRead()) continue;
            if (this.t_148_a.peek() == ',') {
                this.t_148_a.skip();
                this.w_1457_N = this::G_564_y;
                continue;
            }
            if (this.t_148_a.peek() == ']') break;
            throw v_4262_N.createWithContext((ImmutableStringReader)this.t_148_a);
        }
        if (this.t_148_a.canRead()) {
            this.t_148_a.skip();
            return;
        }
        throw v_4262_N.createWithContext((ImmutableStringReader)this.t_148_a);
    }

    public void w_1484_f() throws CommandSyntaxException {
        this.t_148_a.skip();
        this.w_1457_N = this::R_4764_Y;
        int i = -1;
        this.t_148_a.skipWhitespace();
        while (this.t_148_a.canRead() && this.t_148_a.peek() != ']') {
            this.t_148_a.skipWhitespace();
            int j = this.t_148_a.getCursor();
            String s = this.t_148_a.readString();
            if (this.M_588_G.containsKey(s)) {
                this.t_148_a.setCursor(j);
                throw G_564_y.createWithContext((ImmutableStringReader)this.t_148_a, (Object)this.P_4830_p.toString(), (Object)s);
            }
            this.t_148_a.skipWhitespace();
            if (!this.t_148_a.canRead() || this.t_148_a.peek() != '=') {
                this.t_148_a.setCursor(j);
                throw u_1723_Y.createWithContext((ImmutableStringReader)this.t_148_a, (Object)this.P_4830_p.toString(), (Object)s);
            }
            this.t_148_a.skip();
            this.t_148_a.skipWhitespace();
            this.w_1457_N = (p_200138_2_, p_200138_3_) -> this.n_1700_B((SuggestionsBuilder)p_200138_2_, (E_2561_m<T_2915_h>)p_200138_3_, s);
            i = this.t_148_a.getCursor();
            String s1 = this.t_148_a.readString();
            this.M_588_G.put(s, s1);
            this.t_148_a.skipWhitespace();
            if (!this.t_148_a.canRead()) continue;
            i = -1;
            if (this.t_148_a.peek() == ',') {
                this.t_148_a.skip();
                this.w_1457_N = this::P_1922_E;
                continue;
            }
            if (this.t_148_a.peek() == ']') break;
            throw v_4262_N.createWithContext((ImmutableStringReader)this.t_148_a);
        }
        if (this.t_148_a.canRead()) {
            this.t_148_a.skip();
            return;
        }
        if (i >= 0) {
            this.t_148_a.setCursor(i);
        }
        throw v_4262_N.createWithContext((ImmutableStringReader)this.t_148_a);
    }

    public void t_148_a() throws CommandSyntaxException {
        this.M_182_A = new r_4318_c(this.t_148_a).u_1723_Y();
    }

    private <T extends Comparable<T>> void n_1700_B(v_3760_Q<T> property, String value, int valuePosition) throws CommandSyntaxException {
        Optional<T> optional = property.J_1907_R(value);
        if (!optional.isPresent()) {
            this.t_148_a.setCursor(valuePosition);
            throw P_1922_E.createWithContext((ImmutableStringReader)this.t_148_a, (Object)this.P_4830_p.toString(), (Object)property.P_1922_E(), (Object)value);
        }
        this.Q_4569_t = (K_4074_S)this.Q_4569_t.n_1700_B(property, (Comparable)optional.get());
        this.u_2550_I.put(property, (Comparable)optional.get());
    }

    public static String n_1700_B(K_4074_S state) {
        StringBuilder stringbuilder = new StringBuilder(V_3137_a.q_4610_l.J_1907_R(state.J_1907_R()).toString());
        if (!state.k_2293_S().isEmpty()) {
            stringbuilder.append('[');
            boolean flag = false;
            for (Map.Entry entry : state.q_2307_F().entrySet()) {
                if (flag) {
                    stringbuilder.append(',');
                }
                f_71_T.n_1700_B(stringbuilder, (v_3760_Q)entry.getKey(), (Comparable)entry.getValue());
                flag = true;
            }
            stringbuilder.append(']');
        }
        return stringbuilder.toString();
    }

    private static <T extends Comparable<T>> void n_1700_B(StringBuilder builder, v_3760_Q<T> property, Comparable<?> value) {
        builder.append(property.P_1922_E());
        builder.append('=');
        builder.append(property.n_1700_B(value));
    }

    public CompletableFuture<Suggestions> n_1700_B(SuggestionsBuilder builder, E_2561_m<T_2915_h> p_197245_2_) {
        return this.w_1457_N.apply(builder.createOffset(this.t_148_a.getCursor()), p_197245_2_);
    }

    public Map<String, String> s_956_w() {
        return this.M_588_G;
    }
}


