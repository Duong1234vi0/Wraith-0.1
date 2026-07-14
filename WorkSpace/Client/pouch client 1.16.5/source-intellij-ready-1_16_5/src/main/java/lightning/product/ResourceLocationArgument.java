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
import lightning.product.A_2629_w;
import lightning.product.F_2904_S;
import lightning.product.G_3474_H;
import lightning.product.Attribute;
import lightning.product.LootItemCondition;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.k_1471_n;
import lightning.product.y_2498_m;

public class ResourceLocationArgument
implements ArgumentType<g_2336_b> {
    private static final Collection<String> n_1700_B = Arrays.asList("foo", "foo:bar", "012");
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208676_0_ -> new F_2904_S("advancement.advancementNotFound", p_208676_0_));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_208677_0_ -> new F_2904_S("recipe.notFound", p_208677_0_));
    private static final DynamicCommandExceptionType G_564_y = new DynamicCommandExceptionType(p_208674_0_ -> new F_2904_S("predicate.unknown", p_208674_0_));
    private static final DynamicCommandExceptionType P_1922_E = new DynamicCommandExceptionType(p_239091_0_ -> new F_2904_S("attribute.unknown", p_239091_0_));

    public static ResourceLocationArgument n_1700_B() {
        return new ResourceLocationArgument();
    }

    public static A_2629_w n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        g_2336_b resourcelocation = (g_2336_b)context.getArgument(name, g_2336_b.class);
        A_2629_w advancement = ((y_2498_m)context.getSource()).w_1457_N().RealmsWorldOptions().n_1700_B(resourcelocation);
        if (advancement == null) {
            throw J_1907_R.create((Object)resourcelocation);
        }
        return advancement;
    }

    public static Recipe<?> J_1907_R(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        G_3474_H recipemanager = ((y_2498_m)context.getSource()).w_1457_N().ValueObject();
        g_2336_b resourcelocation = (g_2336_b)context.getArgument(name, g_2336_b.class);
        return recipemanager.n_1700_B(resourcelocation).orElseThrow(() -> R_4764_Y.create((Object)resourcelocation));
    }

    public static LootItemCondition R_4764_Y(CommandContext<y_2498_m> p_228259_0_, String p_228259_1_) throws CommandSyntaxException {
        g_2336_b resourcelocation = (g_2336_b)p_228259_0_.getArgument(p_228259_1_, g_2336_b.class);
        k_1471_n lootpredicatemanager = ((y_2498_m)p_228259_0_.getSource()).w_1457_N().RealmsDefaultUncaughtExceptionHandler();
        LootItemCondition ilootcondition = lootpredicatemanager.n_1700_B(resourcelocation);
        if (ilootcondition == null) {
            throw G_564_y.create((Object)resourcelocation);
        }
        return ilootcondition;
    }

    public static Attribute G_564_y(CommandContext<y_2498_m> p_239094_0_, String p_239094_1_) throws CommandSyntaxException {
        g_2336_b resourcelocation = (g_2336_b)p_239094_0_.getArgument(p_239094_1_, g_2336_b.class);
        return V_3137_a.l_1233_K.J_1907_R(resourcelocation).orElseThrow(() -> P_1922_E.create((Object)resourcelocation));
    }

    public static g_2336_b P_1922_E(CommandContext<y_2498_m> context, String name) {
        return (g_2336_b)context.getArgument(name, g_2336_b.class);
    }

    public g_2336_b n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return g_2336_b.n_1700_B(p_parse_1_);
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


