/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.t_5_h;
import lightning.product.y_2498_m;

public class EntitySummonArgument
implements ArgumentType<g_2336_b> {
    private static final Collection<String> J_1907_R = Arrays.asList("minecraft:pig", "cow");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_211367_0_ -> new F_2904_S("entity.notFound", p_211367_0_));

    public static EntitySummonArgument n_1700_B() {
        return new EntitySummonArgument();
    }

    public static g_2336_b n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return EntitySummonArgument.n_1700_B((g_2336_b)context.getArgument(name, g_2336_b.class));
    }

    private static g_2336_b n_1700_B(g_2336_b id) throws CommandSyntaxException {
        V_3137_a.g_221_o.J_1907_R(id).filter(t_5_h::J_1907_R).orElseThrow(() -> n_1700_B.create((Object)id));
        return id;
    }

    public g_2336_b n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return EntitySummonArgument.n_1700_B(g_2336_b.n_1700_B(p_parse_1_));
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


