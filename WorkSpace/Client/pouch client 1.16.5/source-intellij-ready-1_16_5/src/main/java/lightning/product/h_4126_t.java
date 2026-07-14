/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lightning.product.F_2904_S;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.t_5_h;
import lightning.product.y_2498_m;

public class h_4126_t {
    private static final Map<g_2336_b, SuggestionProvider<V_4217_p>> u_1723_Y = Maps.newHashMap();
    private static final g_2336_b v_4262_N = new g_2336_b("ask_server");
    public static final SuggestionProvider<V_4217_p> n_1700_B = h_4126_t.n_1700_B(v_4262_N, (SuggestionProvider<V_4217_p>)((SuggestionProvider)(p_197500_0_, p_197500_1_) -> ((V_4217_p)p_197500_0_.getSource()).n_1700_B((CommandContext<V_4217_p>)p_197500_0_, p_197500_1_)));
    public static final SuggestionProvider<y_2498_m> J_1907_R = h_4126_t.n_1700_B(new g_2336_b("all_recipes"), (SuggestionProvider<V_4217_p>)((SuggestionProvider)(p_197501_0_, p_197501_1_) -> V_4217_p.n_1700_B(((V_4217_p)p_197501_0_.getSource()).P_1922_E(), p_197501_1_)));
    public static final SuggestionProvider<y_2498_m> R_4764_Y = h_4126_t.n_1700_B(new g_2336_b("available_sounds"), (SuggestionProvider<V_4217_p>)((SuggestionProvider)(p_197495_0_, p_197495_1_) -> V_4217_p.n_1700_B(((V_4217_p)p_197495_0_.getSource()).G_564_y(), p_197495_1_)));
    public static final SuggestionProvider<y_2498_m> G_564_y = h_4126_t.n_1700_B(new g_2336_b("available_biomes"), (SuggestionProvider<V_4217_p>)((SuggestionProvider)(p_239577_0_, p_239577_1_) -> V_4217_p.n_1700_B(((V_4217_p)p_239577_0_.getSource()).t_148_a().J_1907_R(V_3137_a.PlayerInfo).G_564_y(), p_239577_1_)));
    public static final SuggestionProvider<y_2498_m> P_1922_E = h_4126_t.n_1700_B(new g_2336_b("summonable_entities"), (SuggestionProvider<V_4217_p>)((SuggestionProvider)(p_201210_0_, p_201210_1_) -> V_4217_p.n_1700_B(V_3137_a.g_221_o.u_1723_Y().filter(t_5_h::J_1907_R), p_201210_1_, t_5_h::n_1700_B, p_201209_0_ -> new F_2904_S(j_3341_s.n_1700_B("entity", t_5_h.n_1700_B(p_201209_0_))))));

    public static <S extends V_4217_p> SuggestionProvider<S> n_1700_B(g_2336_b id, SuggestionProvider<V_4217_p> provider) {
        if (u_1723_Y.containsKey(id)) {
            throw new IllegalArgumentException("A command suggestion provider is already registered with the name " + String.valueOf(id));
        }
        u_1723_Y.put(id, provider);
        return new n_1700_B(id, provider);
    }

    public static SuggestionProvider<V_4217_p> n_1700_B(g_2336_b id) {
        return u_1723_Y.getOrDefault(id, n_1700_B);
    }

    public static g_2336_b n_1700_B(SuggestionProvider<V_4217_p> provider) {
        return provider instanceof n_1700_B ? ((n_1700_B)provider).J_1907_R : v_4262_N;
    }

    public static SuggestionProvider<V_4217_p> J_1907_R(SuggestionProvider<V_4217_p> provider) {
        return provider instanceof n_1700_B ? provider : n_1700_B;
    }

    public static class n_1700_B
    implements SuggestionProvider<V_4217_p> {
        private final SuggestionProvider<V_4217_p> n_1700_B;
        private final g_2336_b J_1907_R;

        public n_1700_B(g_2336_b idIn, SuggestionProvider<V_4217_p> providerIn) {
            this.n_1700_B = providerIn;
            this.J_1907_R = idIn;
        }

        public CompletableFuture<Suggestions> getSuggestions(CommandContext<V_4217_p> p_getSuggestions_1_, SuggestionsBuilder p_getSuggestions_2_) throws CommandSyntaxException {
            return this.n_1700_B.getSuggestions(p_getSuggestions_1_, p_getSuggestions_2_);
        }
    }
}


