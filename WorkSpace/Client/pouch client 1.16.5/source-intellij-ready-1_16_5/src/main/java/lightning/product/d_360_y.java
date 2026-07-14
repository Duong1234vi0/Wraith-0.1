/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.Y_995_C;
import lightning.product.IpBanList;
import lightning.product.MessageArgument;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.IpBanListEntry;

public class d_360_y {
    public static final Pattern n_1700_B = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.banip.invalid"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.banip.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("ban-ip").requires(p_198222_0_ -> p_198222_0_.n_1700_B(3))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("target", StringArgumentType.word()).executes(p_198219_0_ -> d_360_y.n_1700_B((y_2498_m)p_198219_0_.getSource(), StringArgumentType.getString((CommandContext)p_198219_0_, (String)"target"), null))).then(Q_2241_p.n_1700_B("reason", MessageArgument.n_1700_B()).executes(p_198221_0_ -> d_360_y.n_1700_B((y_2498_m)p_198221_0_.getSource(), StringArgumentType.getString((CommandContext)p_198221_0_, (String)"target"), MessageArgument.n_1700_B((CommandContext<y_2498_m>)p_198221_0_, "reason"))))));
    }

    private static int n_1700_B(y_2498_m source, String username, @Nullable x_282_a reason) throws CommandSyntaxException {
        Matcher matcher = n_1700_B.matcher(username);
        if (matcher.matches()) {
            return d_360_y.J_1907_R(source, username, reason);
        }
        B_4088_l serverplayerentity = source.w_1457_N().p_178_J().n_1700_B(username);
        if (serverplayerentity != null) {
            return d_360_y.J_1907_R(source, serverplayerentity.A_4115_X(), reason);
        }
        throw J_1907_R.create();
    }

    private static int J_1907_R(y_2498_m source, String ip, @Nullable x_282_a reason) throws CommandSyntaxException {
        IpBanList ipbanlist = source.w_1457_N().p_178_J().w_1484_f();
        if (ipbanlist.n_1700_B(ip)) {
            throw R_4764_Y.create();
        }
        List<B_4088_l> list = source.w_1457_N().p_178_J().J_1907_R(ip);
        IpBanListEntry ipbanentry = new IpBanListEntry(ip, (Date)null, source.M_588_G(), (Date)null, reason == null ? null : reason.getString());
        ipbanlist.n_1700_B(ipbanentry);
        source.n_1700_B(new F_2904_S("commands.banip.success", ip, ipbanentry.R_4764_Y()), true);
        if (!list.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.banip.info", list.size(), Y_995_C.n_1700_B(list)), true);
        }
        for (B_4088_l serverplayerentity : list) {
            serverplayerentity.n_1700_B.n_1700_B(new F_2904_S("multiplayer.disconnect.ip_banned"));
        }
        return list.size();
    }
}


