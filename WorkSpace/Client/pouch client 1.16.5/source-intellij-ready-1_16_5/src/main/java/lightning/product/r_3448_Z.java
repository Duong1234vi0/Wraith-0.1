/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.ServerFunctionManager;
import lightning.product.Q_2241_p;
import lightning.product.g_2336_b;
import lightning.product.y_2498_m;

public class r_3448_Z {
    private final G_564_y[] n_1700_B;
    private final g_2336_b J_1907_R;

    public r_3448_Z(g_2336_b p_i47973_1_, G_564_y[] p_i47973_2_) {
        this.J_1907_R = p_i47973_1_;
        this.n_1700_B = p_i47973_2_;
    }

    public g_2336_b n_1700_B() {
        return this.J_1907_R;
    }

    public G_564_y[] J_1907_R() {
        return this.n_1700_B;
    }

    public static r_3448_Z n_1700_B(g_2336_b p_237140_0_, CommandDispatcher<y_2498_m> p_237140_1_, y_2498_m p_237140_2_, List<String> p_237140_3_) {
        ArrayList list = Lists.newArrayListWithCapacity((int)p_237140_3_.size());
        for (int i = 0; i < p_237140_3_.size(); ++i) {
            int j = i + 1;
            String s = p_237140_3_.get(i).trim();
            StringReader stringreader = new StringReader(s);
            if (!stringreader.canRead() || stringreader.peek() == '#') continue;
            if (stringreader.peek() == '/') {
                stringreader.skip();
                if (stringreader.peek() == '/') {
                    throw new IllegalArgumentException("Unknown or invalid command '" + s + "' on line " + j + " (if you intended to make a comment, use '#' not '//')");
                }
                String s1 = stringreader.readUnquotedString();
                throw new IllegalArgumentException("Unknown or invalid command '" + s + "' on line " + j + " (did you mean '" + s1 + "'? Do not use a preceding forwards slash.)");
            }
            try {
                ParseResults parseresults = p_237140_1_.parse(stringreader, (Object)p_237140_2_);
                if (parseresults.getReader().canRead()) {
                    throw Q_2241_p.n_1700_B(parseresults);
                }
                list.add(new J_1907_R((ParseResults<y_2498_m>)parseresults));
                continue;
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                throw new IllegalArgumentException("Whilst parsing command on line " + j + ": " + commandsyntaxexception.getMessage());
            }
        }
        return new r_3448_Z(p_237140_0_, list.toArray(new G_564_y[0]));
    }

    public static interface G_564_y {
        public void n_1700_B(ServerFunctionManager var1, y_2498_m var2, ArrayDeque<ServerFunctionManager.n_1700_B> var3, int var4) throws CommandSyntaxException;
    }

    public static class J_1907_R
    implements G_564_y {
        private final ParseResults<y_2498_m> n_1700_B;

        public J_1907_R(ParseResults<y_2498_m> p_i47816_1_) {
            this.n_1700_B = p_i47816_1_;
        }

        @Override
        public void n_1700_B(ServerFunctionManager p_196998_1_, y_2498_m p_196998_2_, ArrayDeque<ServerFunctionManager.n_1700_B> p_196998_3_, int p_196998_4_) throws CommandSyntaxException {
            p_196998_1_.J_1907_R().execute(new ParseResults(this.n_1700_B.getContext().withSource((Object)p_196998_2_), this.n_1700_B.getReader(), this.n_1700_B.getExceptions()));
        }

        public String toString() {
            return this.n_1700_B.getReader().getString();
        }
    }

    public static class R_4764_Y
    implements G_564_y {
        private final n_1700_B n_1700_B;

        public R_4764_Y(r_3448_Z functionIn) {
            this.n_1700_B = new n_1700_B(functionIn);
        }

        @Override
        public void n_1700_B(ServerFunctionManager p_196998_1_, y_2498_m p_196998_2_, ArrayDeque<ServerFunctionManager.n_1700_B> p_196998_3_, int p_196998_4_) {
            this.n_1700_B.n_1700_B(p_196998_1_).ifPresent(p_218041_4_ -> {
                G_564_y[] afunctionobject$ientry = p_218041_4_.J_1907_R();
                int i = p_196998_4_ - p_196998_3_.size();
                int j = Math.min(afunctionobject$ientry.length, i);
                for (int k = j - 1; k >= 0; --k) {
                    p_196998_3_.addFirst(new ServerFunctionManager.n_1700_B(p_196998_1_, p_196998_2_, afunctionobject$ientry[k]));
                }
            });
        }

        public String toString() {
            return "function " + String.valueOf(this.n_1700_B.n_1700_B());
        }
    }

    public static class n_1700_B {
        public static final n_1700_B n_1700_B = new n_1700_B((g_2336_b)null);
        @Nullable
        private final g_2336_b J_1907_R;
        private boolean R_4764_Y;
        private Optional<r_3448_Z> G_564_y = Optional.empty();

        public n_1700_B(@Nullable g_2336_b idIn) {
            this.J_1907_R = idIn;
        }

        public n_1700_B(r_3448_Z functionIn) {
            this.R_4764_Y = true;
            this.J_1907_R = null;
            this.G_564_y = Optional.of(functionIn);
        }

        public Optional<r_3448_Z> n_1700_B(ServerFunctionManager p_218039_1_) {
            if (!this.R_4764_Y) {
                if (this.J_1907_R != null) {
                    this.G_564_y = p_218039_1_.n_1700_B(this.J_1907_R);
                }
                this.R_4764_Y = true;
            }
            return this.G_564_y;
        }

        @Nullable
        public g_2336_b n_1700_B() {
            return this.G_564_y.map(p_218040_0_ -> p_218040_0_.J_1907_R).orElse(this.J_1907_R);
        }
    }
}


