/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.Collection;
import java.util.Collections;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.a_3913_L;
import lightning.product.g_2336_b;
import lightning.product.ComponentArgument;
import lightning.product.BossEvent;
import lightning.product.i_4556_r;
import lightning.product.ResourceLocationArgument;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import net.minecraft.server.J_1907_R;
import net.minecraft.server.n_1700_B;

public class Z_4195_o {
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208783_0_ -> new F_2904_S("commands.bossbar.create.failed", p_208783_0_));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_208782_0_ -> new F_2904_S("commands.bossbar.unknown", p_208782_0_));
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.players.unchanged"));
    private static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.name.unchanged"));
    private static final SimpleCommandExceptionType u_1723_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.color.unchanged"));
    private static final SimpleCommandExceptionType v_4262_N = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.style.unchanged"));
    private static final SimpleCommandExceptionType w_1484_f = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.value.unchanged"));
    private static final SimpleCommandExceptionType t_148_a = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.max.unchanged"));
    private static final SimpleCommandExceptionType s_956_w = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.visibility.unchanged.hidden"));
    private static final SimpleCommandExceptionType u_2550_I = new SimpleCommandExceptionType((Message)new F_2904_S("commands.bossbar.set.visibility.unchanged.visible"));
    public static final SuggestionProvider<y_2498_m> n_1700_B = (p_201404_0_, p_201404_1_) -> V_4217_p.n_1700_B(((y_2498_m)p_201404_0_.getSource()).w_1457_N().u_744_e().n_1700_B(), p_201404_1_);

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("bossbar").requires(p_201423_0_ -> p_201423_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("id", ResourceLocationArgument.n_1700_B()).then(Q_2241_p.n_1700_B("name", ComponentArgument.n_1700_B()).executes(p_201426_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201426_0_.getSource(), ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_201426_0_, "id"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_201426_0_, "name"))))))).then(Q_2241_p.n_1700_B("remove").then(Q_2241_p.n_1700_B("id", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_201429_0_ -> Z_4195_o.P_1922_E((y_2498_m)p_201429_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201429_0_)))))).then(Q_2241_p.n_1700_B("list").executes(p_201396_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201396_0_.getSource())))).then(Q_2241_p.n_1700_B("set").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("id", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).then(Q_2241_p.n_1700_B("name").then(Q_2241_p.n_1700_B("name", ComponentArgument.n_1700_B()).executes(p_201401_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201401_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201401_0_), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_201401_0_, "name")))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("color").then(Q_2241_p.n_1700_B("pink").executes(p_201409_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201409_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201409_0_), BossEvent.n_1700_B.n_1700_B)))).then(Q_2241_p.n_1700_B("blue").executes(p_201422_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201422_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201422_0_), BossEvent.n_1700_B.J_1907_R)))).then(Q_2241_p.n_1700_B("red").executes(p_201417_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201417_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201417_0_), BossEvent.n_1700_B.R_4764_Y)))).then(Q_2241_p.n_1700_B("green").executes(p_201424_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201424_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201424_0_), BossEvent.n_1700_B.G_564_y)))).then(Q_2241_p.n_1700_B("yellow").executes(p_201393_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201393_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201393_0_), BossEvent.n_1700_B.P_1922_E)))).then(Q_2241_p.n_1700_B("purple").executes(p_201391_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201391_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201391_0_), BossEvent.n_1700_B.u_1723_Y)))).then(Q_2241_p.n_1700_B("white").executes(p_201406_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201406_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201406_0_), BossEvent.n_1700_B.v_4262_N))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("style").then(Q_2241_p.n_1700_B("progress").executes(p_201399_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201399_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201399_0_), BossEvent.J_1907_R.n_1700_B)))).then(Q_2241_p.n_1700_B("notched_6").executes(p_201419_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201419_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201419_0_), BossEvent.J_1907_R.J_1907_R)))).then(Q_2241_p.n_1700_B("notched_10").executes(p_201412_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201412_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201412_0_), BossEvent.J_1907_R.R_4764_Y)))).then(Q_2241_p.n_1700_B("notched_12").executes(p_201421_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201421_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201421_0_), BossEvent.J_1907_R.G_564_y)))).then(Q_2241_p.n_1700_B("notched_20").executes(p_201403_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201403_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201403_0_), BossEvent.J_1907_R.P_1922_E))))).then(Q_2241_p.n_1700_B("value").then(Q_2241_p.n_1700_B("value", IntegerArgumentType.integer((int)0)).executes(p_201408_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201408_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201408_0_), IntegerArgumentType.getInteger((CommandContext)p_201408_0_, (String)"value")))))).then(Q_2241_p.n_1700_B("max").then(Q_2241_p.n_1700_B("max", IntegerArgumentType.integer((int)1)).executes(p_201395_0_ -> Z_4195_o.J_1907_R((y_2498_m)p_201395_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201395_0_), IntegerArgumentType.getInteger((CommandContext)p_201395_0_, (String)"max")))))).then(Q_2241_p.n_1700_B("visible").then(Q_2241_p.n_1700_B("visible", BoolArgumentType.bool()).executes(p_201427_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201427_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201427_0_), BoolArgumentType.getBool((CommandContext)p_201427_0_, (String)"visible")))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("players").executes(p_201430_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201430_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201430_0_), Collections.emptyList()))).then(Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).executes(p_201411_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201411_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201411_0_), i_4556_r.G_564_y((CommandContext<y_2498_m>)p_201411_0_, "targets")))))))).then(Q_2241_p.n_1700_B("get").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("id", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).then(Q_2241_p.n_1700_B("value").executes(p_201418_0_ -> Z_4195_o.n_1700_B((y_2498_m)p_201418_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201418_0_))))).then(Q_2241_p.n_1700_B("max").executes(p_201398_0_ -> Z_4195_o.J_1907_R((y_2498_m)p_201398_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201398_0_))))).then(Q_2241_p.n_1700_B("visible").executes(p_201392_0_ -> Z_4195_o.R_4764_Y((y_2498_m)p_201392_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201392_0_))))).then(Q_2241_p.n_1700_B("players").executes(p_201388_0_ -> Z_4195_o.G_564_y((y_2498_m)p_201388_0_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201388_0_)))))));
    }

    private static int n_1700_B(y_2498_m source, n_1700_B bossbar) {
        source.n_1700_B(new F_2904_S("commands.bossbar.get.value", bossbar.u_1723_Y(), bossbar.G_564_y()), true);
        return bossbar.G_564_y();
    }

    private static int J_1907_R(y_2498_m source, n_1700_B bossbar) {
        source.n_1700_B(new F_2904_S("commands.bossbar.get.max", bossbar.u_1723_Y(), bossbar.P_1922_E()), true);
        return bossbar.P_1922_E();
    }

    private static int R_4764_Y(y_2498_m source, n_1700_B bossbar) {
        if (bossbar.Q_4569_t()) {
            source.n_1700_B(new F_2904_S("commands.bossbar.get.visible.visible", bossbar.u_1723_Y()), true);
            return 1;
        }
        source.n_1700_B(new F_2904_S("commands.bossbar.get.visible.hidden", bossbar.u_1723_Y()), true);
        return 0;
    }

    private static int G_564_y(y_2498_m source, n_1700_B bossbar) {
        if (bossbar.M_182_A().isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.bossbar.get.players.none", bossbar.u_1723_Y()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.bossbar.get.players.some", bossbar.u_1723_Y(), bossbar.M_182_A().size(), ComponentUtils.J_1907_R(bossbar.M_182_A(), a_3913_L::c_)), true);
        }
        return bossbar.M_182_A().size();
    }

    private static int n_1700_B(y_2498_m source, n_1700_B bossbar, boolean visible) throws CommandSyntaxException {
        if (bossbar.Q_4569_t() == visible) {
            if (visible) {
                throw u_2550_I.create();
            }
            throw s_956_w.create();
        }
        bossbar.G_564_y(visible);
        if (visible) {
            source.n_1700_B(new F_2904_S("commands.bossbar.set.visible.success.visible", bossbar.u_1723_Y()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.bossbar.set.visible.success.hidden", bossbar.u_1723_Y()), true);
        }
        return 0;
    }

    private static int n_1700_B(y_2498_m source, n_1700_B bossbar, int value) throws CommandSyntaxException {
        if (bossbar.G_564_y() == value) {
            throw w_1484_f.create();
        }
        bossbar.n_1700_B(value);
        source.n_1700_B(new F_2904_S("commands.bossbar.set.value.success", bossbar.u_1723_Y(), value), true);
        return value;
    }

    private static int J_1907_R(y_2498_m source, n_1700_B bossbar, int max) throws CommandSyntaxException {
        if (bossbar.P_1922_E() == max) {
            throw t_148_a.create();
        }
        bossbar.J_1907_R(max);
        source.n_1700_B(new F_2904_S("commands.bossbar.set.max.success", bossbar.u_1723_Y(), max), true);
        return max;
    }

    private static int n_1700_B(y_2498_m source, n_1700_B bossbar, BossEvent.n_1700_B color) throws CommandSyntaxException {
        if (bossbar.s_956_w().equals((Object)color)) {
            throw u_1723_Y.create();
        }
        bossbar.n_1700_B(color);
        source.n_1700_B(new F_2904_S("commands.bossbar.set.color.success", bossbar.u_1723_Y()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, n_1700_B bossbar, BossEvent.J_1907_R styleIn) throws CommandSyntaxException {
        if (bossbar.u_2550_I().equals((Object)styleIn)) {
            throw v_4262_N.create();
        }
        bossbar.n_1700_B(styleIn);
        source.n_1700_B(new F_2904_S("commands.bossbar.set.style.success", bossbar.u_1723_Y()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, n_1700_B bossbar, x_282_a name) throws CommandSyntaxException {
        MutableComponent itextcomponent = ComponentUtils.n_1700_B(source, name, (N_4263_v)null, 0);
        if (bossbar.t_148_a().equals(itextcomponent)) {
            throw P_1922_E.create();
        }
        bossbar.n_1700_B(itextcomponent);
        source.n_1700_B(new F_2904_S("commands.bossbar.set.name.success", bossbar.u_1723_Y()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, n_1700_B bossbar, Collection<B_4088_l> players) throws CommandSyntaxException {
        boolean flag = bossbar.n_1700_B(players);
        if (!flag) {
            throw G_564_y.create();
        }
        if (bossbar.M_182_A().isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.bossbar.set.players.success.none", bossbar.u_1723_Y()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.bossbar.set.players.success.some", bossbar.u_1723_Y(), players.size(), ComponentUtils.J_1907_R(players, a_3913_L::c_)), true);
        }
        return bossbar.M_182_A().size();
    }

    private static int n_1700_B(y_2498_m source) {
        Collection<n_1700_B> collection = source.w_1457_N().u_744_e().J_1907_R();
        if (collection.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.bossbar.list.bars.none"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.bossbar.list.bars.some", collection.size(), ComponentUtils.J_1907_R(collection, n_1700_B::u_1723_Y)), false);
        }
        return collection.size();
    }

    private static int n_1700_B(y_2498_m source, g_2336_b id, x_282_a displayName) throws CommandSyntaxException {
        J_1907_R customserverbossinfomanager = source.w_1457_N().u_744_e();
        if (customserverbossinfomanager.n_1700_B(id) != null) {
            throw J_1907_R.create((Object)id.toString());
        }
        n_1700_B customserverbossinfo = customserverbossinfomanager.n_1700_B(id, ComponentUtils.n_1700_B(source, displayName, (N_4263_v)null, 0));
        source.n_1700_B(new F_2904_S("commands.bossbar.create.success", customserverbossinfo.u_1723_Y()), true);
        return customserverbossinfomanager.J_1907_R().size();
    }

    private static int P_1922_E(y_2498_m source, n_1700_B bossbar) {
        J_1907_R customserverbossinfomanager = source.w_1457_N().u_744_e();
        bossbar.R_4764_Y();
        customserverbossinfomanager.n_1700_B(bossbar);
        source.n_1700_B(new F_2904_S("commands.bossbar.remove.success", bossbar.u_1723_Y()), true);
        return customserverbossinfomanager.J_1907_R().size();
    }

    public static n_1700_B n_1700_B(CommandContext<y_2498_m> source) throws CommandSyntaxException {
        g_2336_b resourcelocation = ResourceLocationArgument.P_1922_E(source, "id");
        n_1700_B customserverbossinfo = ((y_2498_m)source.getSource()).w_1457_N().u_744_e().n_1700_B(resourcelocation);
        if (customserverbossinfo == null) {
            throw R_4764_Y.create((Object)resourcelocation.toString());
        }
        return customserverbossinfo;
    }
}


