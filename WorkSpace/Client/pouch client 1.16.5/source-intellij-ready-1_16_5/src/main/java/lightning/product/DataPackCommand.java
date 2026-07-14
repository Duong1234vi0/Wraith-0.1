/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.D_2103_L;
import lightning.product.PackRepository;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.ReloadCommand;
import lightning.product.ComponentUtils;
import lightning.product.y_2498_m;

public class DataPackCommand {
    private static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208808_0_ -> new F_2904_S("commands.datapack.unknown", p_208808_0_));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208818_0_ -> new F_2904_S("commands.datapack.enable.failed", p_208818_0_));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_208815_0_ -> new F_2904_S("commands.datapack.disable.failed", p_208815_0_));
    private static final SuggestionProvider<y_2498_m> G_564_y = (p_198305_0_, p_198305_1_) -> V_4217_p.J_1907_R(((y_2498_m)p_198305_0_.getSource()).w_1457_N().RegionPingResult().G_564_y().stream().map(StringArgumentType::escapeIfRequired), p_198305_1_);
    private static final SuggestionProvider<y_2498_m> P_1922_E = (p_241030_0_, p_241030_1_) -> {
        PackRepository resourcepacklist = ((y_2498_m)p_241030_0_.getSource()).w_1457_N().RegionPingResult();
        Collection<String> collection = resourcepacklist.G_564_y();
        return V_4217_p.J_1907_R(resourcepacklist.J_1907_R().stream().filter(p_241033_1_ -> !collection.contains(p_241033_1_)).map(StringArgumentType::escapeIfRequired), p_241030_1_);
    };

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("datapack").requires(p_198301_0_ -> p_198301_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("enable").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("name", StringArgumentType.string()).suggests(P_1922_E).executes(p_198292_0_ -> DataPackCommand.n_1700_B((y_2498_m)p_198292_0_.getSource(), DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198292_0_, "name", true), (List<D_2103_L> p_198289_0_, D_2103_L p_198289_1_) -> p_198289_1_.w_1484_f().n_1700_B(p_198289_0_, p_198289_1_, p_198304_0_ -> p_198304_0_, false)))).then(Q_2241_p.n_1700_B("after").then(Q_2241_p.n_1700_B("existing", StringArgumentType.string()).suggests(G_564_y).executes(p_198307_0_ -> DataPackCommand.n_1700_B((y_2498_m)p_198307_0_.getSource(), DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198307_0_, "name", true), (List<D_2103_L> p_198308_1_, D_2103_L p_198308_2_) -> p_198308_1_.add(p_198308_1_.indexOf(DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198307_0_, "existing", false)) + 1, p_198308_2_)))))).then(Q_2241_p.n_1700_B("before").then(Q_2241_p.n_1700_B("existing", StringArgumentType.string()).suggests(G_564_y).executes(p_198311_0_ -> DataPackCommand.n_1700_B((y_2498_m)p_198311_0_.getSource(), DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198311_0_, "name", true), (List<D_2103_L> p_198302_1_, D_2103_L p_198302_2_) -> p_198302_1_.add(p_198302_1_.indexOf(DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198311_0_, "existing", false)), p_198302_2_)))))).then(Q_2241_p.n_1700_B("last").executes(p_198298_0_ -> DataPackCommand.n_1700_B((y_2498_m)p_198298_0_.getSource(), DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198298_0_, "name", true), List::add)))).then(Q_2241_p.n_1700_B("first").executes(p_198300_0_ -> DataPackCommand.n_1700_B((y_2498_m)p_198300_0_.getSource(), DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198300_0_, "name", true), (List<D_2103_L> p_241034_0_, D_2103_L p_241034_1_) -> p_241034_0_.add(0, p_241034_1_))))))).then(Q_2241_p.n_1700_B("disable").then(Q_2241_p.n_1700_B("name", StringArgumentType.string()).suggests(G_564_y).executes(p_198295_0_ -> DataPackCommand.n_1700_B((y_2498_m)p_198295_0_.getSource(), DataPackCommand.n_1700_B((CommandContext<y_2498_m>)p_198295_0_, "name", false)))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("list").executes(p_198290_0_ -> DataPackCommand.n_1700_B((y_2498_m)p_198290_0_.getSource()))).then(Q_2241_p.n_1700_B("available").executes(p_198288_0_ -> DataPackCommand.J_1907_R((y_2498_m)p_198288_0_.getSource())))).then(Q_2241_p.n_1700_B("enabled").executes(p_198309_0_ -> DataPackCommand.R_4764_Y((y_2498_m)p_198309_0_.getSource())))));
    }

    private static int n_1700_B(y_2498_m source, D_2103_L pack, n_1700_B priorityCallback) throws CommandSyntaxException {
        PackRepository resourcepacklist = source.w_1457_N().RegionPingResult();
        ArrayList list = Lists.newArrayList(resourcepacklist.P_1922_E());
        priorityCallback.apply(list, pack);
        source.n_1700_B(new F_2904_S("commands.datapack.modify.enable", pack.n_1700_B(true)), true);
        ReloadCommand.n_1700_B(list.stream().map(D_2103_L::P_1922_E).collect(Collectors.toList()), source);
        return list.size();
    }

    private static int n_1700_B(y_2498_m source, D_2103_L pack) {
        PackRepository resourcepacklist = source.w_1457_N().RegionPingResult();
        ArrayList list = Lists.newArrayList(resourcepacklist.P_1922_E());
        list.remove(pack);
        source.n_1700_B(new F_2904_S("commands.datapack.modify.disable", pack.n_1700_B(true)), true);
        ReloadCommand.n_1700_B(list.stream().map(D_2103_L::P_1922_E).collect(Collectors.toList()), source);
        return list.size();
    }

    private static int n_1700_B(y_2498_m source) {
        return DataPackCommand.R_4764_Y(source) + DataPackCommand.J_1907_R(source);
    }

    private static int J_1907_R(y_2498_m source) {
        PackRepository resourcepacklist = source.w_1457_N().RegionPingResult();
        resourcepacklist.n_1700_B();
        Collection<D_2103_L> collection = resourcepacklist.P_1922_E();
        Collection<D_2103_L> collection1 = resourcepacklist.R_4764_Y();
        List list = collection1.stream().filter(p_241032_1_ -> !collection.contains(p_241032_1_)).collect(Collectors.toList());
        if (list.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.datapack.list.available.none"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.datapack.list.available.success", list.size(), ComponentUtils.J_1907_R(list, (T p_198293_0_) -> p_198293_0_.n_1700_B(false))), false);
        }
        return list.size();
    }

    private static int R_4764_Y(y_2498_m source) {
        PackRepository resourcepacklist = source.w_1457_N().RegionPingResult();
        resourcepacklist.n_1700_B();
        Collection<D_2103_L> collection = resourcepacklist.P_1922_E();
        if (collection.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.datapack.list.enabled.none"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.datapack.list.enabled.success", collection.size(), ComponentUtils.J_1907_R(collection, (T p_198306_0_) -> p_198306_0_.n_1700_B(true))), false);
        }
        return collection.size();
    }

    private static D_2103_L n_1700_B(CommandContext<y_2498_m> context, String name, boolean enabling) throws CommandSyntaxException {
        String s = StringArgumentType.getString(context, (String)name);
        PackRepository resourcepacklist = ((y_2498_m)context.getSource()).w_1457_N().RegionPingResult();
        D_2103_L resourcepackinfo = resourcepacklist.n_1700_B(s);
        if (resourcepackinfo == null) {
            throw n_1700_B.create((Object)s);
        }
        boolean flag = resourcepacklist.P_1922_E().contains(resourcepackinfo);
        if (enabling && flag) {
            throw J_1907_R.create((Object)s);
        }
        if (!enabling && !flag) {
            throw R_4764_Y.create((Object)s);
        }
        return resourcepackinfo;
    }

    static interface n_1700_B {
        public void apply(List<D_2103_L> var1, D_2103_L var2) throws CommandSyntaxException;
    }
}


