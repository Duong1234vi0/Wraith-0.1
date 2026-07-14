/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import lightning.product.F_2904_S;
import lightning.product.J_2545_z;
import lightning.product.N_4263_v;
import lightning.product.V_4217_p;
import lightning.product.Y_995_C;
import lightning.product.b_2585_i;
import lightning.product.i_4556_r;
import lightning.product.ArgumentSerializer;
import lightning.product.y_2498_m;

public class C_131_O
implements ArgumentType<n_1700_B> {
    public static final SuggestionProvider<y_2498_m> n_1700_B = (p_201323_0_, p_201323_1_) -> {
        StringReader stringreader = new StringReader(p_201323_1_.getInput());
        stringreader.setCursor(p_201323_1_.getStart());
        J_2545_z entityselectorparser = new J_2545_z(stringreader);
        try {
            entityselectorparser.w_1457_N();
        }
        catch (CommandSyntaxException commandSyntaxException) {
            // empty catch block
        }
        return entityselectorparser.n_1700_B(p_201323_1_, p_201949_1_ -> V_4217_p.J_1907_R(((y_2498_m)p_201323_0_.getSource()).n_1700_B(), p_201949_1_));
    };
    private static final Collection<String> J_1907_R = Arrays.asList("Player", "0123", "*", "@e");
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.scoreHolder.empty"));
    private final boolean G_564_y;

    public C_131_O(boolean allowMultipleIn) {
        this.G_564_y = allowMultipleIn;
    }

    public static String n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return C_131_O.J_1907_R(context, name).iterator().next();
    }

    public static Collection<String> J_1907_R(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return C_131_O.n_1700_B(context, name, Collections::emptyList);
    }

    public static Collection<String> R_4764_Y(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return C_131_O.n_1700_B(context, name, ((y_2498_m)context.getSource()).w_1457_N().S_4022_R()::R_4764_Y);
    }

    public static Collection<String> n_1700_B(CommandContext<y_2498_m> context, String name, Supplier<Collection<String>> objectives) throws CommandSyntaxException {
        Collection<String> collection = ((n_1700_B)context.getArgument(name, n_1700_B.class)).getNames((y_2498_m)context.getSource(), objectives);
        if (collection.isEmpty()) {
            throw i_4556_r.G_564_y.create();
        }
        return collection;
    }

    public static C_131_O n_1700_B() {
        return new C_131_O(false);
    }

    public static C_131_O J_1907_R() {
        return new C_131_O(true);
    }

    public n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        if (p_parse_1_.canRead() && p_parse_1_.peek() == '@') {
            J_2545_z entityselectorparser = new J_2545_z(p_parse_1_);
            Y_995_C entityselector = entityselectorparser.w_1457_N();
            if (!this.G_564_y && entityselector.n_1700_B() > 1) {
                throw i_4556_r.n_1700_B.create();
            }
            return new J_1907_R(entityselector);
        }
        int i = p_parse_1_.getCursor();
        while (p_parse_1_.canRead() && p_parse_1_.peek() != ' ') {
            p_parse_1_.skip();
        }
        String s = p_parse_1_.getString().substring(i, p_parse_1_.getCursor());
        if (s.equals("*")) {
            return (p_197208_0_, p_197208_1_) -> {
                Collection collection1 = (Collection)p_197208_1_.get();
                if (collection1.isEmpty()) {
                    throw R_4764_Y.create();
                }
                return collection1;
            };
        }
        Set<String> collection = Collections.singleton(s);
        return (p_197212_1_, p_197212_2_) -> collection;
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    @FunctionalInterface
    public static interface n_1700_B {
        public Collection<String> getNames(y_2498_m var1, Supplier<Collection<String>> var2) throws CommandSyntaxException;
    }

    public static class J_1907_R
    implements n_1700_B {
        private final Y_995_C n_1700_B;

        public J_1907_R(Y_995_C selectorIn) {
            this.n_1700_B = selectorIn;
        }

        @Override
        public Collection<String> getNames(y_2498_m p_getNames_1_, Supplier<Collection<String>> p_getNames_2_) throws CommandSyntaxException {
            List<? extends N_4263_v> list = this.n_1700_B.J_1907_R(p_getNames_1_);
            if (list.isEmpty()) {
                throw i_4556_r.G_564_y.create();
            }
            ArrayList list1 = Lists.newArrayList();
            for (N_4263_v n_4263_v : list) {
                list1.add(n_4263_v.L_3570_A());
            }
            return list1;
        }
    }

    public static class R_4764_Y
    implements ArgumentSerializer<C_131_O> {
        @Override
        public void n_1700_B(C_131_O argument, b_2585_i buffer) {
            int b0 = 0;
            if (argument.G_564_y) {
                b0 = (byte)(b0 | 1);
            }
            buffer.writeByte(b0);
        }

        public C_131_O J_1907_R(b_2585_i buffer) {
            byte b0 = buffer.readByte();
            boolean flag = (b0 & 1) != 0;
            return new C_131_O(flag);
        }

        @Override
        public void n_1700_B(C_131_O p_212244_1_, JsonObject p_212244_2_) {
            p_212244_2_.addProperty("amount", p_212244_1_.G_564_y ? "multiple" : "single");
        }

        @Override
        public /* synthetic */ ArgumentType n_1700_B(b_2585_i b_2585_i2) {
            return this.J_1907_R(b_2585_i2);
        }
    }
}


