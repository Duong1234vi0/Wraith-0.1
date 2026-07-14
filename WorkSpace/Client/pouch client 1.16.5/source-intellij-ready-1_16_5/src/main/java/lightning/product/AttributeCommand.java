/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.Dynamic3CommandExceptionType
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.Dynamic3CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.UUID;
import lightning.product.A_4388_s;
import lightning.product.AttributeMap;
import lightning.product.F_2904_S;
import lightning.product.Attribute;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.U_1880_G;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.i_4556_r;
import lightning.product.ResourceLocationArgument;
import lightning.product.UuidArgument;
import lightning.product.r_4811_B;
import lightning.product.y_2498_m;

public class AttributeCommand {
    private static final SuggestionProvider<y_2498_m> n_1700_B = (p_241005_0_, p_241005_1_) -> V_4217_p.n_1700_B(V_3137_a.l_1233_K.G_564_y(), p_241005_1_);
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_241011_0_ -> new F_2904_S("commands.attribute.failed.entity", p_241011_0_));
    private static final Dynamic2CommandExceptionType R_4764_Y = new Dynamic2CommandExceptionType((p_241012_0_, p_241012_1_) -> new F_2904_S("commands.attribute.failed.no_attribute", p_241012_0_, p_241012_1_));
    private static final Dynamic3CommandExceptionType G_564_y = new Dynamic3CommandExceptionType((p_241017_0_, p_241017_1_, p_241017_2_) -> new F_2904_S("commands.attribute.failed.no_modifier", p_241017_1_, p_241017_0_, p_241017_2_));
    private static final Dynamic3CommandExceptionType P_1922_E = new Dynamic3CommandExceptionType((p_241013_0_, p_241013_1_, p_241013_2_) -> new F_2904_S("commands.attribute.failed.modifier_already_present", p_241013_2_, p_241013_1_, p_241013_0_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> p_241003_0_) {
        p_241003_0_.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("attribute").requires(p_241006_0_ -> p_241006_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("target", i_4556_r.n_1700_B()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("attribute", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("get").executes(p_241027_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241027_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241027_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241027_0_, "attribute"), 1.0))).then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).executes(p_241026_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241026_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241026_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241026_0_, "attribute"), DoubleArgumentType.getDouble((CommandContext)p_241026_0_, (String)"scale")))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("base").then(Q_2241_p.n_1700_B("set").then(Q_2241_p.n_1700_B("value", DoubleArgumentType.doubleArg()).executes(p_241025_0_ -> AttributeCommand.R_4764_Y((y_2498_m)p_241025_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241025_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241025_0_, "attribute"), DoubleArgumentType.getDouble((CommandContext)p_241025_0_, (String)"value")))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("get").executes(p_241024_0_ -> AttributeCommand.J_1907_R((y_2498_m)p_241024_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241024_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241024_0_, "attribute"), 1.0))).then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).executes(p_241023_0_ -> AttributeCommand.J_1907_R((y_2498_m)p_241023_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241023_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241023_0_, "attribute"), DoubleArgumentType.getDouble((CommandContext)p_241023_0_, (String)"scale"))))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("modifier").then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("uuid", UuidArgument.n_1700_B()).then(Q_2241_p.n_1700_B("name", StringArgumentType.string()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("value", DoubleArgumentType.doubleArg()).then(Q_2241_p.n_1700_B("add").executes(p_241022_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241022_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241022_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241022_0_, "attribute"), UuidArgument.n_1700_B((CommandContext<y_2498_m>)p_241022_0_, "uuid"), StringArgumentType.getString((CommandContext)p_241022_0_, (String)"name"), DoubleArgumentType.getDouble((CommandContext)p_241022_0_, (String)"value"), U_1880_G.n_1700_B.n_1700_B)))).then(Q_2241_p.n_1700_B("multiply").executes(p_241021_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241021_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241021_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241021_0_, "attribute"), UuidArgument.n_1700_B((CommandContext<y_2498_m>)p_241021_0_, "uuid"), StringArgumentType.getString((CommandContext)p_241021_0_, (String)"name"), DoubleArgumentType.getDouble((CommandContext)p_241021_0_, (String)"value"), U_1880_G.n_1700_B.R_4764_Y)))).then(Q_2241_p.n_1700_B("multiply_base").executes(p_241020_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241020_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241020_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241020_0_, "attribute"), UuidArgument.n_1700_B((CommandContext<y_2498_m>)p_241020_0_, "uuid"), StringArgumentType.getString((CommandContext)p_241020_0_, (String)"name"), DoubleArgumentType.getDouble((CommandContext)p_241020_0_, (String)"value"), U_1880_G.n_1700_B.J_1907_R)))))))).then(Q_2241_p.n_1700_B("remove").then(Q_2241_p.n_1700_B("uuid", UuidArgument.n_1700_B()).executes(p_241018_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241018_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241018_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241018_0_, "attribute"), UuidArgument.n_1700_B((CommandContext<y_2498_m>)p_241018_0_, "uuid")))))).then(Q_2241_p.n_1700_B("value").then(Q_2241_p.n_1700_B("get").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("uuid", UuidArgument.n_1700_B()).executes(p_241015_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241015_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241015_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241015_0_, "attribute"), UuidArgument.n_1700_B((CommandContext<y_2498_m>)p_241015_0_, "uuid"), 1.0))).then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).executes(p_241004_0_ -> AttributeCommand.n_1700_B((y_2498_m)p_241004_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_241004_0_, "target"), ResourceLocationArgument.G_564_y((CommandContext<y_2498_m>)p_241004_0_, "attribute"), UuidArgument.n_1700_B((CommandContext<y_2498_m>)p_241004_0_, "uuid"), DoubleArgumentType.getDouble((CommandContext)p_241004_0_, (String)"scale")))))))))));
    }

    private static A_4388_s n_1700_B(N_4263_v p_241002_0_, Attribute p_241002_1_) throws CommandSyntaxException {
        A_4388_s modifiableattributeinstance = AttributeCommand.n_1700_B(p_241002_0_).B_1146_q().n_1700_B(p_241002_1_);
        if (modifiableattributeinstance == null) {
            throw R_4764_Y.create((Object)p_241002_0_.O_1309_Q(), (Object)new F_2904_S(p_241002_1_.R_4764_Y()));
        }
        return modifiableattributeinstance;
    }

    private static r_4811_B n_1700_B(N_4263_v p_241001_0_) throws CommandSyntaxException {
        if (!(p_241001_0_ instanceof r_4811_B)) {
            throw J_1907_R.create((Object)p_241001_0_.O_1309_Q());
        }
        return (r_4811_B)p_241001_0_;
    }

    private static r_4811_B J_1907_R(N_4263_v p_241014_0_, Attribute p_241014_1_) throws CommandSyntaxException {
        r_4811_B livingentity = AttributeCommand.n_1700_B(p_241014_0_);
        if (!livingentity.B_1146_q().J_1907_R(p_241014_1_)) {
            throw R_4764_Y.create((Object)p_241014_0_.O_1309_Q(), (Object)new F_2904_S(p_241014_1_.R_4764_Y()));
        }
        return livingentity;
    }

    private static int n_1700_B(y_2498_m p_241007_0_, N_4263_v p_241007_1_, Attribute p_241007_2_, double p_241007_3_) throws CommandSyntaxException {
        r_4811_B livingentity = AttributeCommand.J_1907_R(p_241007_1_, p_241007_2_);
        double d0 = livingentity.J_1907_R(p_241007_2_);
        p_241007_0_.n_1700_B(new F_2904_S("commands.attribute.value.get.success", new F_2904_S(p_241007_2_.R_4764_Y()), p_241007_1_.O_1309_Q(), d0), false);
        return (int)(d0 * p_241007_3_);
    }

    private static int J_1907_R(y_2498_m p_241016_0_, N_4263_v p_241016_1_, Attribute p_241016_2_, double p_241016_3_) throws CommandSyntaxException {
        r_4811_B livingentity = AttributeCommand.J_1907_R(p_241016_1_, p_241016_2_);
        double d0 = livingentity.R_4764_Y(p_241016_2_);
        p_241016_0_.n_1700_B(new F_2904_S("commands.attribute.base_value.get.success", new F_2904_S(p_241016_2_.R_4764_Y()), p_241016_1_.O_1309_Q(), d0), false);
        return (int)(d0 * p_241016_3_);
    }

    private static int n_1700_B(y_2498_m p_241009_0_, N_4263_v p_241009_1_, Attribute p_241009_2_, UUID p_241009_3_, double p_241009_4_) throws CommandSyntaxException {
        r_4811_B livingentity = AttributeCommand.J_1907_R(p_241009_1_, p_241009_2_);
        AttributeMap attributemodifiermanager = livingentity.B_1146_q();
        if (!attributemodifiermanager.n_1700_B(p_241009_2_, p_241009_3_)) {
            throw G_564_y.create((Object)p_241009_1_.O_1309_Q(), (Object)new F_2904_S(p_241009_2_.R_4764_Y()), (Object)p_241009_3_);
        }
        double d0 = attributemodifiermanager.J_1907_R(p_241009_2_, p_241009_3_);
        p_241009_0_.n_1700_B(new F_2904_S("commands.attribute.modifier.value.get.success", p_241009_3_, new F_2904_S(p_241009_2_.R_4764_Y()), p_241009_1_.O_1309_Q(), d0), false);
        return (int)(d0 * p_241009_4_);
    }

    private static int R_4764_Y(y_2498_m p_241019_0_, N_4263_v p_241019_1_, Attribute p_241019_2_, double p_241019_3_) throws CommandSyntaxException {
        AttributeCommand.n_1700_B(p_241019_1_, p_241019_2_).n_1700_B(p_241019_3_);
        p_241019_0_.n_1700_B(new F_2904_S("commands.attribute.base_value.set.success", new F_2904_S(p_241019_2_.R_4764_Y()), p_241019_1_.O_1309_Q(), p_241019_3_), false);
        return 1;
    }

    private static int n_1700_B(y_2498_m p_241010_0_, N_4263_v p_241010_1_, Attribute p_241010_2_, UUID p_241010_3_, String p_241010_4_, double p_241010_5_, U_1880_G.n_1700_B p_241010_7_) throws CommandSyntaxException {
        U_1880_G attributemodifier;
        A_4388_s modifiableattributeinstance = AttributeCommand.n_1700_B(p_241010_1_, p_241010_2_);
        if (modifiableattributeinstance.n_1700_B(attributemodifier = new U_1880_G(p_241010_3_, p_241010_4_, p_241010_5_, p_241010_7_))) {
            throw P_1922_E.create((Object)p_241010_1_.O_1309_Q(), (Object)new F_2904_S(p_241010_2_.R_4764_Y()), (Object)p_241010_3_);
        }
        modifiableattributeinstance.R_4764_Y(attributemodifier);
        p_241010_0_.n_1700_B(new F_2904_S("commands.attribute.modifier.add.success", p_241010_3_, new F_2904_S(p_241010_2_.R_4764_Y()), p_241010_1_.O_1309_Q()), false);
        return 1;
    }

    private static int n_1700_B(y_2498_m p_241008_0_, N_4263_v p_241008_1_, Attribute p_241008_2_, UUID p_241008_3_) throws CommandSyntaxException {
        A_4388_s modifiableattributeinstance = AttributeCommand.n_1700_B(p_241008_1_, p_241008_2_);
        if (modifiableattributeinstance.R_4764_Y(p_241008_3_)) {
            p_241008_0_.n_1700_B(new F_2904_S("commands.attribute.modifier.remove.success", p_241008_3_, new F_2904_S(p_241008_2_.R_4764_Y()), p_241008_1_.O_1309_Q()), false);
            return 1;
        }
        throw G_564_y.create((Object)p_241008_1_.O_1309_Q(), (Object)new F_2904_S(p_241008_2_.R_4764_Y()), (Object)p_241008_3_);
    }
}


