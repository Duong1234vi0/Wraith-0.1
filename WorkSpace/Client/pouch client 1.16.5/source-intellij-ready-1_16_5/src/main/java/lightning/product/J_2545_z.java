/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.primitives.Doubles
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

import com.google.common.primitives.Doubles;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.W_4351_m;
import lightning.product.Y_995_C;
import lightning.product.e_2866_D;
import lightning.product.MinMaxBounds;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.WrappedMinMaxBounds;

public class J_2545_z {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.invalid"));
    public static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208703_0_ -> new F_2904_S("argument.entity.selector.unknown", p_208703_0_));
    public static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.selector.not_allowed"));
    public static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.selector.missing"));
    public static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.options.unterminated"));
    public static final DynamicCommandExceptionType u_1723_Y = new DynamicCommandExceptionType(p_208711_0_ -> new F_2904_S("argument.entity.options.valueless", p_208711_0_));
    public static final BiConsumer<e_2866_D, List<? extends N_4263_v>> v_4262_N = (p_197402_0_, p_197402_1_) -> {};
    public static final BiConsumer<e_2866_D, List<? extends N_4263_v>> w_1484_f = (p_197392_0_, p_197392_1_) -> p_197392_1_.sort((p_197393_1_, p_197393_2_) -> Doubles.compare((double)p_197393_1_.u_1723_Y((e_2866_D)p_197392_0_), (double)p_197393_2_.u_1723_Y((e_2866_D)p_197392_0_)));
    public static final BiConsumer<e_2866_D, List<? extends N_4263_v>> t_148_a = (p_197383_0_, p_197383_1_) -> p_197383_1_.sort((p_197369_1_, p_197369_2_) -> Doubles.compare((double)p_197369_2_.u_1723_Y((e_2866_D)p_197383_0_), (double)p_197369_1_.u_1723_Y((e_2866_D)p_197383_0_)));
    public static final BiConsumer<e_2866_D, List<? extends N_4263_v>> s_956_w = (p_197368_0_, p_197368_1_) -> Collections.shuffle(p_197368_1_);
    public static final BiFunction<SuggestionsBuilder, Consumer<SuggestionsBuilder>, CompletableFuture<Suggestions>> u_2550_I = (p_201342_0_, p_201342_1_) -> p_201342_0_.buildFuture();
    private final StringReader M_588_G;
    private final boolean P_4830_p;
    private int h_1847_R;
    private boolean Q_4569_t;
    private boolean M_182_A;
    private MinMaxBounds.n_1700_B t_1786_h = MinMaxBounds.n_1700_B.P_1922_E;
    private MinMaxBounds.G_564_y multiplayerClientSuggestionProvider = MinMaxBounds.G_564_y.P_1922_E;
    @Nullable
    private Double w_1457_N;
    @Nullable
    private Double Y_601_j;
    @Nullable
    private Double Y_259_p;
    @Nullable
    private Double Q_2552_b;
    @Nullable
    private Double C_2741_M;
    @Nullable
    private Double k_2293_S;
    private WrappedMinMaxBounds q_2307_F = WrappedMinMaxBounds.n_1700_B;
    private WrappedMinMaxBounds Z_875_P = WrappedMinMaxBounds.n_1700_B;
    private Predicate<N_4263_v> c_3005_b = p_197375_0_ -> true;
    private BiConsumer<e_2866_D, List<? extends N_4263_v>> H_2857_Y = v_4262_N;
    private boolean A_4115_X;
    @Nullable
    private String Y_1740_V;
    private int t_4043_B;
    @Nullable
    private UUID x_607_J;
    private BiFunction<SuggestionsBuilder, Consumer<SuggestionsBuilder>, CompletableFuture<Suggestions>> e_4240_b = u_2550_I;
    private boolean n_3318_d;
    private boolean d_2427_y;
    private boolean z_1737_N;
    private boolean v_4276_D;
    private boolean d_2461_k;
    private boolean G_624_v;
    private boolean T_2506_i;
    private boolean q_4610_l;
    @Nullable
    private t_5_h<?> z_4693_k;
    private boolean g_221_o;
    private boolean e_2887_G;
    private boolean B_1668_F;
    private boolean g_164_R;

    public J_2545_z(StringReader readerIn) {
        this(readerIn, true);
    }

    public J_2545_z(StringReader readerIn, boolean hasPermissionIn) {
        this.M_588_G = readerIn;
        this.P_4830_p = hasPermissionIn;
    }

    public Y_995_C n_1700_B() {
        I_4817_s axisalignedbb;
        if (this.Q_2552_b == null && this.C_2741_M == null && this.k_2293_S == null) {
            if (this.t_1786_h.J_1907_R() != null) {
                float f = ((Float)this.t_1786_h.J_1907_R()).floatValue();
                axisalignedbb = new I_4817_s(-f, -f, -f, f + 1.0f, f + 1.0f, f + 1.0f);
            } else {
                axisalignedbb = null;
            }
        } else {
            axisalignedbb = this.n_1700_B(this.Q_2552_b == null ? 0.0 : this.Q_2552_b, this.C_2741_M == null ? 0.0 : this.C_2741_M, this.k_2293_S == null ? 0.0 : this.k_2293_S);
        }
        Function<e_2866_D, e_2866_D> function = this.w_1457_N == null && this.Y_601_j == null && this.Y_259_p == null ? p_197379_0_ -> p_197379_0_ : p_197367_1_ -> new e_2866_D(this.w_1457_N == null ? p_197367_1_.J_1907_R : this.w_1457_N, this.Y_601_j == null ? p_197367_1_.R_4764_Y : this.Y_601_j, this.Y_259_p == null ? p_197367_1_.G_564_y : this.Y_259_p);
        return new Y_995_C(this.h_1847_R, this.Q_4569_t, this.M_182_A, this.c_3005_b, this.t_1786_h, function, axisalignedbb, this.H_2857_Y, this.A_4115_X, this.Y_1740_V, this.x_607_J, this.z_4693_k, this.g_164_R);
    }

    private I_4817_s n_1700_B(double sizeX, double sizeY, double sizeZ) {
        boolean flag = sizeX < 0.0;
        boolean flag1 = sizeY < 0.0;
        boolean flag2 = sizeZ < 0.0;
        double d0 = flag ? sizeX : 0.0;
        double d1 = flag1 ? sizeY : 0.0;
        double d2 = flag2 ? sizeZ : 0.0;
        double d3 = (flag ? 0.0 : sizeX) + 1.0;
        double d4 = (flag1 ? 0.0 : sizeY) + 1.0;
        double d5 = (flag2 ? 0.0 : sizeZ) + 1.0;
        return new I_4817_s(d0, d1, d2, d3, d4, d5);
    }

    private void e_4240_b() {
        if (this.q_2307_F != WrappedMinMaxBounds.n_1700_B) {
            this.c_3005_b = this.c_3005_b.and(this.n_1700_B(this.q_2307_F, (N_4263_v p_197386_0_) -> p_197386_0_.f_4016_n));
        }
        if (this.Z_875_P != WrappedMinMaxBounds.n_1700_B) {
            this.c_3005_b = this.c_3005_b.and(this.n_1700_B(this.Z_875_P, (N_4263_v p_197385_0_) -> p_197385_0_.p_178_J));
        }
        if (!this.multiplayerClientSuggestionProvider.R_4764_Y()) {
            this.c_3005_b = this.c_3005_b.and(p_197371_1_ -> !(p_197371_1_ instanceof B_4088_l) ? false : this.multiplayerClientSuggestionProvider.R_4764_Y(((B_4088_l)p_197371_1_).v_165_F));
        }
    }

    private Predicate<N_4263_v> n_1700_B(WrappedMinMaxBounds angleBounds, ToDoubleFunction<N_4263_v> angleFunc) {
        double d0 = u_530_F.v_4262_N(angleBounds.n_1700_B() == null ? 0.0f : angleBounds.n_1700_B().floatValue());
        double d1 = u_530_F.v_4262_N(angleBounds.J_1907_R() == null ? 359.0f : angleBounds.J_1907_R().floatValue());
        return p_197374_5_ -> {
            double d2 = u_530_F.u_1723_Y(angleFunc.applyAsDouble((N_4263_v)p_197374_5_));
            if (d0 > d1) {
                return d2 >= d0 || d2 <= d1;
            }
            return d2 >= d0 && d2 <= d1;
        };
    }

    protected void J_1907_R() throws CommandSyntaxException {
        this.g_164_R = true;
        this.e_4240_b = this::G_564_y;
        if (!this.M_588_G.canRead()) {
            throw G_564_y.createWithContext((ImmutableStringReader)this.M_588_G);
        }
        int i = this.M_588_G.getCursor();
        char c0 = this.M_588_G.read();
        if (c0 == 'p') {
            this.h_1847_R = 1;
            this.Q_4569_t = false;
            this.H_2857_Y = w_1484_f;
            this.n_1700_B(t_5_h.g_4106_L);
        } else if (c0 == 'a') {
            this.h_1847_R = Integer.MAX_VALUE;
            this.Q_4569_t = false;
            this.H_2857_Y = v_4262_N;
            this.n_1700_B(t_5_h.g_4106_L);
        } else if (c0 == 'r') {
            this.h_1847_R = 1;
            this.Q_4569_t = false;
            this.H_2857_Y = s_956_w;
            this.n_1700_B(t_5_h.g_4106_L);
        } else if (c0 == 's') {
            this.h_1847_R = 1;
            this.Q_4569_t = true;
            this.A_4115_X = true;
        } else {
            if (c0 != 'e') {
                this.M_588_G.setCursor(i);
                throw J_1907_R.createWithContext((ImmutableStringReader)this.M_588_G, (Object)("@" + String.valueOf(c0)));
            }
            this.h_1847_R = Integer.MAX_VALUE;
            this.Q_4569_t = true;
            this.H_2857_Y = v_4262_N;
            this.c_3005_b = N_4263_v::RealmsLongRunningMcoTaskScreen;
        }
        this.e_4240_b = this::P_1922_E;
        if (this.M_588_G.canRead() && this.M_588_G.peek() == '[') {
            this.M_588_G.skip();
            this.e_4240_b = this::u_1723_Y;
            this.G_564_y();
        }
    }

    protected void R_4764_Y() throws CommandSyntaxException {
        if (this.M_588_G.canRead()) {
            this.e_4240_b = this::R_4764_Y;
        }
        int i = this.M_588_G.getCursor();
        String s = this.M_588_G.readString();
        try {
            this.x_607_J = UUID.fromString(s);
            this.Q_4569_t = true;
        }
        catch (IllegalArgumentException illegalargumentexception) {
            if (s.isEmpty() || s.length() > 16) {
                this.M_588_G.setCursor(i);
                throw n_1700_B.createWithContext((ImmutableStringReader)this.M_588_G);
            }
            this.Q_4569_t = false;
            this.Y_1740_V = s;
        }
        this.h_1847_R = 1;
    }

    protected void G_564_y() throws CommandSyntaxException {
        this.e_4240_b = this::v_4262_N;
        this.M_588_G.skipWhitespace();
        while (this.M_588_G.canRead() && this.M_588_G.peek() != ']') {
            this.M_588_G.skipWhitespace();
            int i = this.M_588_G.getCursor();
            String s = this.M_588_G.readString();
            W_4351_m.n_1700_B entityoptions$ifilter = W_4351_m.n_1700_B(this, s, i);
            this.M_588_G.skipWhitespace();
            if (!this.M_588_G.canRead() || this.M_588_G.peek() != '=') {
                this.M_588_G.setCursor(i);
                throw u_1723_Y.createWithContext((ImmutableStringReader)this.M_588_G, (Object)s);
            }
            this.M_588_G.skip();
            this.M_588_G.skipWhitespace();
            this.e_4240_b = u_2550_I;
            entityoptions$ifilter.handle(this);
            this.M_588_G.skipWhitespace();
            this.e_4240_b = this::w_1484_f;
            if (!this.M_588_G.canRead()) continue;
            if (this.M_588_G.peek() == ',') {
                this.M_588_G.skip();
                this.e_4240_b = this::v_4262_N;
                continue;
            }
            if (this.M_588_G.peek() == ']') break;
            throw P_1922_E.createWithContext((ImmutableStringReader)this.M_588_G);
        }
        if (this.M_588_G.canRead()) {
            this.M_588_G.skip();
            this.e_4240_b = u_2550_I;
            return;
        }
        throw P_1922_E.createWithContext((ImmutableStringReader)this.M_588_G);
    }

    public boolean P_1922_E() {
        this.M_588_G.skipWhitespace();
        if (this.M_588_G.canRead() && this.M_588_G.peek() == '!') {
            this.M_588_G.skip();
            this.M_588_G.skipWhitespace();
            return true;
        }
        return false;
    }

    public boolean u_1723_Y() {
        this.M_588_G.skipWhitespace();
        if (this.M_588_G.canRead() && this.M_588_G.peek() == '#') {
            this.M_588_G.skip();
            this.M_588_G.skipWhitespace();
            return true;
        }
        return false;
    }

    public StringReader v_4262_N() {
        return this.M_588_G;
    }

    public void n_1700_B(Predicate<N_4263_v> filterIn) {
        this.c_3005_b = this.c_3005_b.and(filterIn);
    }

    public void w_1484_f() {
        this.M_182_A = true;
    }

    public MinMaxBounds.n_1700_B t_148_a() {
        return this.t_1786_h;
    }

    public void n_1700_B(MinMaxBounds.n_1700_B distanceIn) {
        this.t_1786_h = distanceIn;
    }

    public MinMaxBounds.G_564_y s_956_w() {
        return this.multiplayerClientSuggestionProvider;
    }

    public void n_1700_B(MinMaxBounds.G_564_y levelIn) {
        this.multiplayerClientSuggestionProvider = levelIn;
    }

    public WrappedMinMaxBounds u_2550_I() {
        return this.q_2307_F;
    }

    public void n_1700_B(WrappedMinMaxBounds xRotationIn) {
        this.q_2307_F = xRotationIn;
    }

    public WrappedMinMaxBounds M_588_G() {
        return this.Z_875_P;
    }

    public void J_1907_R(WrappedMinMaxBounds yRotationIn) {
        this.Z_875_P = yRotationIn;
    }

    @Nullable
    public Double P_4830_p() {
        return this.w_1457_N;
    }

    @Nullable
    public Double h_1847_R() {
        return this.Y_601_j;
    }

    @Nullable
    public Double Q_4569_t() {
        return this.Y_259_p;
    }

    public void n_1700_B(double xIn) {
        this.w_1457_N = xIn;
    }

    public void J_1907_R(double yIn) {
        this.Y_601_j = yIn;
    }

    public void R_4764_Y(double zIn) {
        this.Y_259_p = zIn;
    }

    public void G_564_y(double dxIn) {
        this.Q_2552_b = dxIn;
    }

    public void P_1922_E(double dyIn) {
        this.C_2741_M = dyIn;
    }

    public void u_1723_Y(double dzIn) {
        this.k_2293_S = dzIn;
    }

    @Nullable
    public Double M_182_A() {
        return this.Q_2552_b;
    }

    @Nullable
    public Double t_1786_h() {
        return this.C_2741_M;
    }

    @Nullable
    public Double multiplayerClientSuggestionProvider() {
        return this.k_2293_S;
    }

    public void n_1700_B(int limitIn) {
        this.h_1847_R = limitIn;
    }

    public void n_1700_B(boolean includeNonPlayersIn) {
        this.Q_4569_t = includeNonPlayersIn;
    }

    public void n_1700_B(BiConsumer<e_2866_D, List<? extends N_4263_v>> sorterIn) {
        this.H_2857_Y = sorterIn;
    }

    public Y_995_C w_1457_N() throws CommandSyntaxException {
        this.t_4043_B = this.M_588_G.getCursor();
        this.e_4240_b = this::J_1907_R;
        if (this.M_588_G.canRead() && this.M_588_G.peek() == '@') {
            if (!this.P_4830_p) {
                throw R_4764_Y.createWithContext((ImmutableStringReader)this.M_588_G);
            }
            this.M_588_G.skip();
            this.J_1907_R();
        } else {
            this.R_4764_Y();
        }
        this.e_4240_b();
        return this.n_1700_B();
    }

    private static void n_1700_B(SuggestionsBuilder suggestionBuilder) {
        suggestionBuilder.suggest("@p", (Message)new F_2904_S("argument.entity.selector.nearestPlayer"));
        suggestionBuilder.suggest("@a", (Message)new F_2904_S("argument.entity.selector.allPlayers"));
        suggestionBuilder.suggest("@r", (Message)new F_2904_S("argument.entity.selector.randomPlayer"));
        suggestionBuilder.suggest("@s", (Message)new F_2904_S("argument.entity.selector.self"));
        suggestionBuilder.suggest("@e", (Message)new F_2904_S("argument.entity.selector.allEntities"));
    }

    private CompletableFuture<Suggestions> J_1907_R(SuggestionsBuilder suggestionBuilder, Consumer<SuggestionsBuilder> consumer) {
        consumer.accept(suggestionBuilder);
        if (this.P_4830_p) {
            J_2545_z.n_1700_B(suggestionBuilder);
        }
        return suggestionBuilder.buildFuture();
    }

    private CompletableFuture<Suggestions> R_4764_Y(SuggestionsBuilder builder, Consumer<SuggestionsBuilder> consumer) {
        SuggestionsBuilder suggestionsbuilder = builder.createOffset(this.t_4043_B);
        consumer.accept(suggestionsbuilder);
        return builder.add(suggestionsbuilder).buildFuture();
    }

    private CompletableFuture<Suggestions> G_564_y(SuggestionsBuilder builder, Consumer<SuggestionsBuilder> consumer) {
        SuggestionsBuilder suggestionsbuilder = builder.createOffset(builder.getStart() - 1);
        J_2545_z.n_1700_B(suggestionsbuilder);
        builder.add(suggestionsbuilder);
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> P_1922_E(SuggestionsBuilder builder, Consumer<SuggestionsBuilder> consumer) {
        builder.suggest(String.valueOf('['));
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> u_1723_Y(SuggestionsBuilder builder, Consumer<SuggestionsBuilder> consumer) {
        builder.suggest(String.valueOf(']'));
        W_4351_m.n_1700_B(this, builder);
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> v_4262_N(SuggestionsBuilder builder, Consumer<SuggestionsBuilder> consumer) {
        W_4351_m.n_1700_B(this, builder);
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> w_1484_f(SuggestionsBuilder builder, Consumer<SuggestionsBuilder> consumer) {
        builder.suggest(String.valueOf(','));
        builder.suggest(String.valueOf(']'));
        return builder.buildFuture();
    }

    public boolean Y_601_j() {
        return this.A_4115_X;
    }

    public void n_1700_B(BiFunction<SuggestionsBuilder, Consumer<SuggestionsBuilder>, CompletableFuture<Suggestions>> suggestionHandlerIn) {
        this.e_4240_b = suggestionHandlerIn;
    }

    public CompletableFuture<Suggestions> n_1700_B(SuggestionsBuilder builder, Consumer<SuggestionsBuilder> consumer) {
        return this.e_4240_b.apply(builder.createOffset(this.M_588_G.getCursor()), consumer);
    }

    public boolean Y_259_p() {
        return this.n_3318_d;
    }

    public void J_1907_R(boolean value) {
        this.n_3318_d = value;
    }

    public boolean Q_2552_b() {
        return this.d_2427_y;
    }

    public void R_4764_Y(boolean value) {
        this.d_2427_y = value;
    }

    public boolean C_2741_M() {
        return this.z_1737_N;
    }

    public void G_564_y(boolean value) {
        this.z_1737_N = value;
    }

    public boolean k_2293_S() {
        return this.v_4276_D;
    }

    public void P_1922_E(boolean value) {
        this.v_4276_D = value;
    }

    public boolean q_2307_F() {
        return this.d_2461_k;
    }

    public void u_1723_Y(boolean value) {
        this.d_2461_k = value;
    }

    public boolean Z_875_P() {
        return this.G_624_v;
    }

    public void v_4262_N(boolean value) {
        this.G_624_v = value;
    }

    public boolean c_3005_b() {
        return this.T_2506_i;
    }

    public void w_1484_f(boolean value) {
        this.T_2506_i = value;
    }

    public void t_148_a(boolean value) {
        this.q_4610_l = value;
    }

    public void n_1700_B(t_5_h<?> p_218114_1_) {
        this.z_4693_k = p_218114_1_;
    }

    public void H_2857_Y() {
        this.g_221_o = true;
    }

    public boolean A_4115_X() {
        return this.z_4693_k != null;
    }

    public boolean Y_1740_V() {
        return this.g_221_o;
    }

    public boolean t_4043_B() {
        return this.e_2887_G;
    }

    public void s_956_w(boolean value) {
        this.e_2887_G = value;
    }

    public boolean x_607_J() {
        return this.B_1668_F;
    }

    public void u_2550_I(boolean value) {
        this.B_1668_F = value;
    }
}


