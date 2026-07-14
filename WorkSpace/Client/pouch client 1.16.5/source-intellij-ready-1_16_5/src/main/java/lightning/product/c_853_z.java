/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import lightning.product.F_2904_S;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.r_3448_Z;
import lightning.product.y_2498_m;

public class c_853_z
implements ArgumentType<n_1700_B> {
    private static final Collection<String> n_1700_B = Arrays.asList("foo", "foo:bar", "#foo");
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208691_0_ -> new F_2904_S("arguments.function.tag.unknown", p_208691_0_));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_208694_0_ -> new F_2904_S("arguments.function.unknown", p_208694_0_));

    public static c_853_z n_1700_B() {
        return new c_853_z();
    }

    public n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        if (p_parse_1_.canRead() && p_parse_1_.peek() == '#') {
            p_parse_1_.skip();
            final g_2336_b resourcelocation1 = g_2336_b.n_1700_B(p_parse_1_);
            return new n_1700_B(){

                @Override
                public Collection<r_3448_Z> n_1700_B(CommandContext<y_2498_m> p_223252_1_) throws CommandSyntaxException {
                    r_109_r<r_3448_Z> itag = c_853_z.J_1907_R(p_223252_1_, resourcelocation1);
                    return itag.n_1700_B();
                }

                @Override
                public Pair<g_2336_b, Either<r_3448_Z, r_109_r<r_3448_Z>>> J_1907_R(CommandContext<y_2498_m> p_218102_1_) throws CommandSyntaxException {
                    return Pair.of((Object)resourcelocation1, (Object)Either.right(c_853_z.J_1907_R(p_218102_1_, resourcelocation1)));
                }
            };
        }
        final g_2336_b resourcelocation = g_2336_b.n_1700_B(p_parse_1_);
        return new n_1700_B(){

            @Override
            public Collection<r_3448_Z> n_1700_B(CommandContext<y_2498_m> p_223252_1_) throws CommandSyntaxException {
                return Collections.singleton(c_853_z.n_1700_B(p_223252_1_, resourcelocation));
            }

            @Override
            public Pair<g_2336_b, Either<r_3448_Z, r_109_r<r_3448_Z>>> J_1907_R(CommandContext<y_2498_m> p_218102_1_) throws CommandSyntaxException {
                return Pair.of((Object)resourcelocation, (Object)Either.left((Object)c_853_z.n_1700_B(p_218102_1_, resourcelocation)));
            }
        };
    }

    private static r_3448_Z n_1700_B(CommandContext<y_2498_m> p_218108_0_, g_2336_b p_218108_1_) throws CommandSyntaxException {
        return ((y_2498_m)p_218108_0_.getSource()).w_1457_N().RealmsWorldResetDto().n_1700_B(p_218108_1_).orElseThrow(() -> R_4764_Y.create((Object)p_218108_1_.toString()));
    }

    private static r_109_r<r_3448_Z> J_1907_R(CommandContext<y_2498_m> p_218111_0_, g_2336_b p_218111_1_) throws CommandSyntaxException {
        r_109_r<r_3448_Z> itag = ((y_2498_m)p_218111_0_.getSource()).w_1457_N().RealmsWorldResetDto().J_1907_R(p_218111_1_);
        if (itag == null) {
            throw J_1907_R.create((Object)p_218111_1_.toString());
        }
        return itag;
    }

    public static Collection<r_3448_Z> n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((n_1700_B)context.getArgument(name, n_1700_B.class)).n_1700_B(context);
    }

    public static Pair<g_2336_b, Either<r_3448_Z, r_109_r<r_3448_Z>>> J_1907_R(CommandContext<y_2498_m> p_218110_0_, String p_218110_1_) throws CommandSyntaxException {
        return ((n_1700_B)p_218110_0_.getArgument(p_218110_1_, n_1700_B.class)).J_1907_R(p_218110_0_);
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    public static interface n_1700_B {
        public Collection<r_3448_Z> n_1700_B(CommandContext<y_2498_m> var1) throws CommandSyntaxException;

        public Pair<g_2336_b, Either<r_3448_Z, r_109_r<r_3448_Z>>> J_1907_R(CommandContext<y_2498_m> var1) throws CommandSyntaxException;
    }
}


