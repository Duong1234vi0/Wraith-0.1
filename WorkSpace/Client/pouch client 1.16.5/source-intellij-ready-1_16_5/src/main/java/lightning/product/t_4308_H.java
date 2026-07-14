/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.FloatArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Locale;
import lightning.product.F_2904_S;
import lightning.product.P_3504_Q;
import lightning.product.Q_2241_p;
import lightning.product.T_603_v;
import lightning.product.Vec2Argument;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;

public class t_4308_H {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.center.failed"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.set.failed.nochange"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.set.failed.small."));
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.set.failed.big."));
    private static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.warning.time.failed"));
    private static final SimpleCommandExceptionType u_1723_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.warning.distance.failed"));
    private static final SimpleCommandExceptionType v_4262_N = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.damage.buffer.failed"));
    private static final SimpleCommandExceptionType w_1484_f = new SimpleCommandExceptionType((Message)new F_2904_S("commands.worldborder.damage.amount.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("worldborder").requires(p_198903_0_ -> p_198903_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("add").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("distance", FloatArgumentType.floatArg((float)-6.0E7f, (float)6.0E7f)).executes(p_198908_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198908_0_.getSource(), ((y_2498_m)p_198908_0_.getSource()).h_1847_R().H_2857_Y().t_148_a() + (double)FloatArgumentType.getFloat((CommandContext)p_198908_0_, (String)"distance"), 0L))).then(Q_2241_p.n_1700_B("time", IntegerArgumentType.integer((int)0)).executes(p_198901_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198901_0_.getSource(), ((y_2498_m)p_198901_0_.getSource()).h_1847_R().H_2857_Y().t_148_a() + (double)FloatArgumentType.getFloat((CommandContext)p_198901_0_, (String)"distance"), ((y_2498_m)p_198901_0_.getSource()).h_1847_R().H_2857_Y().s_956_w() + (long)IntegerArgumentType.getInteger((CommandContext)p_198901_0_, (String)"time") * 1000L)))))).then(Q_2241_p.n_1700_B("set").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("distance", FloatArgumentType.floatArg((float)-6.0E7f, (float)6.0E7f)).executes(p_198906_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198906_0_.getSource(), FloatArgumentType.getFloat((CommandContext)p_198906_0_, (String)"distance"), 0L))).then(Q_2241_p.n_1700_B("time", IntegerArgumentType.integer((int)0)).executes(p_198909_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198909_0_.getSource(), FloatArgumentType.getFloat((CommandContext)p_198909_0_, (String)"distance"), (long)IntegerArgumentType.getInteger((CommandContext)p_198909_0_, (String)"time") * 1000L)))))).then(Q_2241_p.n_1700_B("center").then(Q_2241_p.n_1700_B("pos", Vec2Argument.n_1700_B()).executes(p_198893_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198893_0_.getSource(), Vec2Argument.n_1700_B((CommandContext<y_2498_m>)p_198893_0_, "pos")))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("damage").then(Q_2241_p.n_1700_B("amount").then(Q_2241_p.n_1700_B("damagePerBlock", FloatArgumentType.floatArg((float)0.0f)).executes(p_198897_0_ -> t_4308_H.J_1907_R((y_2498_m)p_198897_0_.getSource(), FloatArgumentType.getFloat((CommandContext)p_198897_0_, (String)"damagePerBlock")))))).then(Q_2241_p.n_1700_B("buffer").then(Q_2241_p.n_1700_B("distance", FloatArgumentType.floatArg((float)0.0f)).executes(p_198905_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198905_0_.getSource(), FloatArgumentType.getFloat((CommandContext)p_198905_0_, (String)"distance"))))))).then(Q_2241_p.n_1700_B("get").executes(p_198900_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198900_0_.getSource())))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("warning").then(Q_2241_p.n_1700_B("distance").then(Q_2241_p.n_1700_B("distance", IntegerArgumentType.integer((int)0)).executes(p_198892_0_ -> t_4308_H.J_1907_R((y_2498_m)p_198892_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198892_0_, (String)"distance")))))).then(Q_2241_p.n_1700_B("time").then(Q_2241_p.n_1700_B("time", IntegerArgumentType.integer((int)0)).executes(p_198907_0_ -> t_4308_H.n_1700_B((y_2498_m)p_198907_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198907_0_, (String)"time")))))));
    }

    private static int n_1700_B(y_2498_m source, float distance) throws CommandSyntaxException {
        T_603_v worldborder = source.h_1847_R().H_2857_Y();
        if (worldborder.h_1847_R() == (double)distance) {
            throw v_4262_N.create();
        }
        worldborder.J_1907_R(distance);
        source.n_1700_B(new F_2904_S("commands.worldborder.damage.buffer.success", String.format(Locale.ROOT, "%.2f", Float.valueOf(distance))), true);
        return (int)distance;
    }

    private static int J_1907_R(y_2498_m source, float damagePerBlock) throws CommandSyntaxException {
        T_603_v worldborder = source.h_1847_R().H_2857_Y();
        if (worldborder.Q_4569_t() == (double)damagePerBlock) {
            throw w_1484_f.create();
        }
        worldborder.R_4764_Y(damagePerBlock);
        source.n_1700_B(new F_2904_S("commands.worldborder.damage.amount.success", String.format(Locale.ROOT, "%.2f", Float.valueOf(damagePerBlock))), true);
        return (int)damagePerBlock;
    }

    private static int n_1700_B(y_2498_m source, int time) throws CommandSyntaxException {
        T_603_v worldborder = source.h_1847_R().H_2857_Y();
        if (worldborder.t_1786_h() == time) {
            throw P_1922_E.create();
        }
        worldborder.J_1907_R(time);
        source.n_1700_B(new F_2904_S("commands.worldborder.warning.time.success", time), true);
        return time;
    }

    private static int J_1907_R(y_2498_m source, int distance) throws CommandSyntaxException {
        T_603_v worldborder = source.h_1847_R().H_2857_Y();
        if (worldborder.multiplayerClientSuggestionProvider() == distance) {
            throw u_1723_Y.create();
        }
        worldborder.R_4764_Y(distance);
        source.n_1700_B(new F_2904_S("commands.worldborder.warning.distance.success", distance), true);
        return distance;
    }

    private static int n_1700_B(y_2498_m source) {
        double d0 = source.h_1847_R().H_2857_Y().t_148_a();
        source.n_1700_B(new F_2904_S("commands.worldborder.get", String.format(Locale.ROOT, "%.0f", d0)), false);
        return u_530_F.R_4764_Y(d0 + 0.5);
    }

    private static int n_1700_B(y_2498_m source, P_3504_Q pos) throws CommandSyntaxException {
        T_603_v worldborder = source.h_1847_R().H_2857_Y();
        if (worldborder.n_1700_B() == (double)pos.t_148_a && worldborder.J_1907_R() == (double)pos.s_956_w) {
            throw n_1700_B.create();
        }
        worldborder.J_1907_R(pos.t_148_a, pos.s_956_w);
        source.n_1700_B(new F_2904_S("commands.worldborder.center.success", String.format(Locale.ROOT, "%.2f", Float.valueOf(pos.t_148_a)), String.format("%.2f", Float.valueOf(pos.s_956_w))), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, double newSize, long time) throws CommandSyntaxException {
        T_603_v worldborder = source.h_1847_R().H_2857_Y();
        double d0 = worldborder.t_148_a();
        if (d0 == newSize) {
            throw J_1907_R.create();
        }
        if (newSize < 1.0) {
            throw R_4764_Y.create();
        }
        if (newSize > 6.0E7) {
            throw G_564_y.create();
        }
        if (time > 0L) {
            worldborder.n_1700_B(d0, newSize, time);
            if (newSize > d0) {
                source.n_1700_B(new F_2904_S("commands.worldborder.set.grow", String.format(Locale.ROOT, "%.1f", newSize), Long.toString(time / 1000L)), true);
            } else {
                source.n_1700_B(new F_2904_S("commands.worldborder.set.shrink", String.format(Locale.ROOT, "%.1f", newSize), Long.toString(time / 1000L)), true);
            }
        } else {
            worldborder.n_1700_B(newSize);
            source.n_1700_B(new F_2904_S("commands.worldborder.set.immediate", String.format(Locale.ROOT, "%.1f", newSize)), true);
        }
        return (int)(newSize - d0);
    }
}


