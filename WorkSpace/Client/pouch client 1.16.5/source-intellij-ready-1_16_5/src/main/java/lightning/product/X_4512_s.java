/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lightning.product.F_2904_S;
import lightning.product.V_4217_p;
import lightning.product.e_1174_E;
import lightning.product.j_3341_s;
import lightning.product.y_2498_m;

public class X_4512_s
implements ArgumentType<Integer> {
    private static final Collection<String> n_1700_B = Arrays.asList("container.5", "12", "weapon");
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208679_0_ -> new F_2904_S("slot.unknown", p_208679_0_));
    private static final Map<String, Integer> R_4764_Y = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_209386_0_) -> {
        for (int i = 0; i < 54; ++i) {
            p_209386_0_.put("container." + i, i);
        }
        for (int j = 0; j < 9; ++j) {
            p_209386_0_.put("hotbar." + j, j);
        }
        for (int k = 0; k < 27; ++k) {
            p_209386_0_.put("inventory." + k, 9 + k);
        }
        for (int l = 0; l < 27; ++l) {
            p_209386_0_.put("enderchest." + l, 200 + l);
        }
        for (int i1 = 0; i1 < 8; ++i1) {
            p_209386_0_.put("villager." + i1, 300 + i1);
        }
        for (int j1 = 0; j1 < 15; ++j1) {
            p_209386_0_.put("horse." + j1, 500 + j1);
        }
        p_209386_0_.put("weapon", 98);
        p_209386_0_.put("weapon.mainhand", 98);
        p_209386_0_.put("weapon.offhand", 99);
        p_209386_0_.put("armor.head", 100 + e_1174_E.u_1723_Y.J_1907_R());
        p_209386_0_.put("armor.chest", 100 + e_1174_E.P_1922_E.J_1907_R());
        p_209386_0_.put("armor.legs", 100 + e_1174_E.G_564_y.J_1907_R());
        p_209386_0_.put("armor.feet", 100 + e_1174_E.R_4764_Y.J_1907_R());
        p_209386_0_.put("horse.saddle", 400);
        p_209386_0_.put("horse.armor", 401);
        p_209386_0_.put("horse.chest", 499);
    });

    public static X_4512_s n_1700_B() {
        return new X_4512_s();
    }

    public static int n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (Integer)context.getArgument(name, Integer.class);
    }

    public Integer n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        String s = p_parse_1_.readUnquotedString();
        if (!R_4764_Y.containsKey(s)) {
            throw J_1907_R.create((Object)s);
        }
        return R_4764_Y.get(s);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.J_1907_R(R_4764_Y.keySet(), p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}

