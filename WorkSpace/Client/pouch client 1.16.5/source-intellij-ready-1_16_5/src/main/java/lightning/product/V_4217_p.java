/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.r_4097_j;

public interface V_4217_p {
    public Collection<String> n_1700_B();

    default public Collection<String> J_1907_R() {
        return Collections.emptyList();
    }

    public Collection<String> R_4764_Y();

    public Collection<g_2336_b> G_564_y();

    public Stream<g_2336_b> P_1922_E();

    public CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> var1, SuggestionsBuilder var2);

    default public Collection<n_1700_B> u_1723_Y() {
        return Collections.singleton(n_1700_B.J_1907_R);
    }

    default public Collection<n_1700_B> v_4262_N() {
        return Collections.singleton(n_1700_B.J_1907_R);
    }

    public Set<f_2392_k<b_4507_u>> w_1484_f();

    public r_4097_j t_148_a();

    public boolean n_1700_B(int var1);

    public static <T> void n_1700_B(Iterable<T> p_210512_0_, String p_210512_1_, Function<T, g_2336_b> p_210512_2_, Consumer<T> p_210512_3_) {
        boolean flag = p_210512_1_.indexOf(58) > -1;
        for (T t : p_210512_0_) {
            g_2336_b resourcelocation = p_210512_2_.apply(t);
            if (flag) {
                String s = resourcelocation.toString();
                if (!V_4217_p.n_1700_B(p_210512_1_, s)) continue;
                p_210512_3_.accept(t);
                continue;
            }
            if (!V_4217_p.n_1700_B(p_210512_1_, resourcelocation.R_4764_Y()) && (!resourcelocation.R_4764_Y().equals("minecraft") || !V_4217_p.n_1700_B(p_210512_1_, resourcelocation.J_1907_R()))) continue;
            p_210512_3_.accept(t);
        }
    }

    public static <T> void n_1700_B(Iterable<T> p_210511_0_, String p_210511_1_, String p_210511_2_, Function<T, g_2336_b> p_210511_3_, Consumer<T> p_210511_4_) {
        if (p_210511_1_.isEmpty()) {
            p_210511_0_.forEach(p_210511_4_);
        } else {
            String s = Strings.commonPrefix((CharSequence)p_210511_1_, (CharSequence)p_210511_2_);
            if (!s.isEmpty()) {
                String s1 = p_210511_1_.substring(s.length());
                V_4217_p.n_1700_B(p_210511_0_, s1, p_210511_3_, p_210511_4_);
            }
        }
    }

    public static CompletableFuture<Suggestions> n_1700_B(Iterable<g_2336_b> p_197006_0_, SuggestionsBuilder p_197006_1_, String prefix) {
        String s = p_197006_1_.getRemaining().toLowerCase(Locale.ROOT);
        V_4217_p.n_1700_B(p_197006_0_, s, prefix, p_210519_0_ -> p_210519_0_, p_210518_2_ -> p_197006_1_.suggest(prefix + String.valueOf(p_210518_2_)));
        return p_197006_1_.buildFuture();
    }

    public static CompletableFuture<Suggestions> n_1700_B(Iterable<g_2336_b> p_197014_0_, SuggestionsBuilder builder) {
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        V_4217_p.n_1700_B(p_197014_0_, s, (T p_210517_0_) -> p_210517_0_, (T p_210513_1_) -> builder.suggest(p_210513_1_.toString()));
        return builder.buildFuture();
    }

    public static <T> CompletableFuture<Suggestions> n_1700_B(Iterable<T> p_210514_0_, SuggestionsBuilder builder, Function<T, g_2336_b> p_210514_2_, Function<T, Message> p_210514_3_) {
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        V_4217_p.n_1700_B(p_210514_0_, s, p_210514_2_, (T p_210515_3_) -> builder.suggest(((g_2336_b)p_210514_2_.apply(p_210515_3_)).toString(), (Message)p_210514_3_.apply(p_210515_3_)));
        return builder.buildFuture();
    }

    public static CompletableFuture<Suggestions> n_1700_B(Stream<g_2336_b> p_212476_0_, SuggestionsBuilder builder) {
        return V_4217_p.n_1700_B(p_212476_0_::iterator, builder);
    }

    public static <T> CompletableFuture<Suggestions> n_1700_B(Stream<T> p_201725_0_, SuggestionsBuilder builder, Function<T, g_2336_b> p_201725_2_, Function<T, Message> p_201725_3_) {
        return V_4217_p.n_1700_B(p_201725_0_::iterator, builder, p_201725_2_, p_201725_3_);
    }

    public static CompletableFuture<Suggestions> n_1700_B(String p_209000_0_, Collection<n_1700_B> p_209000_1_, SuggestionsBuilder builder, Predicate<String> p_209000_3_) {
        ArrayList list;
        block4: {
            String[] astring;
            block5: {
                block3: {
                    list = Lists.newArrayList();
                    if (!Strings.isNullOrEmpty((String)p_209000_0_)) break block3;
                    for (n_1700_B isuggestionprovider$coordinates : p_209000_1_) {
                        String s = isuggestionprovider$coordinates.R_4764_Y + " " + isuggestionprovider$coordinates.G_564_y + " " + isuggestionprovider$coordinates.P_1922_E;
                        if (!p_209000_3_.test(s)) continue;
                        list.add(isuggestionprovider$coordinates.R_4764_Y);
                        list.add(isuggestionprovider$coordinates.R_4764_Y + " " + isuggestionprovider$coordinates.G_564_y);
                        list.add(s);
                    }
                    break block4;
                }
                astring = p_209000_0_.split(" ");
                if (astring.length != 1) break block5;
                for (n_1700_B isuggestionprovider$coordinates1 : p_209000_1_) {
                    String s1 = astring[0] + " " + isuggestionprovider$coordinates1.G_564_y + " " + isuggestionprovider$coordinates1.P_1922_E;
                    if (!p_209000_3_.test(s1)) continue;
                    list.add(astring[0] + " " + isuggestionprovider$coordinates1.G_564_y);
                    list.add(s1);
                }
                break block4;
            }
            if (astring.length != 2) break block4;
            for (n_1700_B isuggestionprovider$coordinates2 : p_209000_1_) {
                String s2 = astring[0] + " " + astring[1] + " " + isuggestionprovider$coordinates2.P_1922_E;
                if (!p_209000_3_.test(s2)) continue;
                list.add(s2);
            }
        }
        return V_4217_p.J_1907_R(list, builder);
    }

    public static CompletableFuture<Suggestions> J_1907_R(String p_211269_0_, Collection<n_1700_B> p_211269_1_, SuggestionsBuilder builder, Predicate<String> p_211269_3_) {
        ArrayList list;
        block3: {
            block2: {
                list = Lists.newArrayList();
                if (!Strings.isNullOrEmpty((String)p_211269_0_)) break block2;
                for (n_1700_B isuggestionprovider$coordinates : p_211269_1_) {
                    String s = isuggestionprovider$coordinates.R_4764_Y + " " + isuggestionprovider$coordinates.P_1922_E;
                    if (!p_211269_3_.test(s)) continue;
                    list.add(isuggestionprovider$coordinates.R_4764_Y);
                    list.add(s);
                }
                break block3;
            }
            String[] astring = p_211269_0_.split(" ");
            if (astring.length != 1) break block3;
            for (n_1700_B isuggestionprovider$coordinates1 : p_211269_1_) {
                String s1 = astring[0] + " " + isuggestionprovider$coordinates1.P_1922_E;
                if (!p_211269_3_.test(s1)) continue;
                list.add(s1);
            }
        }
        return V_4217_p.J_1907_R(list, builder);
    }

    public static CompletableFuture<Suggestions> J_1907_R(Iterable<String> p_197005_0_, SuggestionsBuilder builder) {
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        for (String s1 : p_197005_0_) {
            if (!V_4217_p.n_1700_B(s, s1.toLowerCase(Locale.ROOT))) continue;
            builder.suggest(s1);
        }
        return builder.buildFuture();
    }

    public static CompletableFuture<Suggestions> J_1907_R(Stream<String> p_197013_0_, SuggestionsBuilder builder) {
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        p_197013_0_.filter(p_197007_1_ -> V_4217_p.n_1700_B(s, p_197007_1_.toLowerCase(Locale.ROOT))).forEach(arg_0 -> ((SuggestionsBuilder)builder).suggest(arg_0));
        return builder.buildFuture();
    }

    public static CompletableFuture<Suggestions> n_1700_B(String[] p_197008_0_, SuggestionsBuilder builder) {
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        for (String s1 : p_197008_0_) {
            if (!V_4217_p.n_1700_B(s, s1.toLowerCase(Locale.ROOT))) continue;
            builder.suggest(s1);
        }
        return builder.buildFuture();
    }

    public static boolean n_1700_B(String p_237256_0_, String p_237256_1_) {
        int i = 0;
        while (!p_237256_1_.startsWith(p_237256_0_, i)) {
            if ((i = p_237256_1_.indexOf(95, i)) < 0) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static class n_1700_B {
        public static final n_1700_B n_1700_B = new n_1700_B("^", "^", "^");
        public static final n_1700_B J_1907_R = new n_1700_B("~", "~", "~");
        public final String R_4764_Y;
        public final String G_564_y;
        public final String P_1922_E;

        public n_1700_B(String xIn, String yIn, String zIn) {
            this.R_4764_Y = xIn;
            this.G_564_y = yIn;
            this.P_1922_E = zIn;
        }
    }
}

