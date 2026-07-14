/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.regex.Matcher;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.IpBanList;
import lightning.product.d_360_y;
import lightning.product.y_2498_m;

public class PardonIpCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.pardonip.invalid"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.pardonip.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("pardon-ip").requires(p_198556_0_ -> p_198556_0_.n_1700_B(3))).then(Q_2241_p.n_1700_B("target", StringArgumentType.word()).suggests((p_198554_0_, p_198554_1_) -> V_4217_p.n_1700_B(((y_2498_m)p_198554_0_.getSource()).w_1457_N().p_178_J().w_1484_f().n_1700_B(), p_198554_1_)).executes(p_198555_0_ -> PardonIpCommand.n_1700_B((y_2498_m)p_198555_0_.getSource(), StringArgumentType.getString((CommandContext)p_198555_0_, (String)"target")))));
    }

    private static int n_1700_B(y_2498_m source, String ipAddress) throws CommandSyntaxException {
        Matcher matcher = d_360_y.n_1700_B.matcher(ipAddress);
        if (!matcher.matches()) {
            throw n_1700_B.create();
        }
        IpBanList ipbanlist = source.w_1457_N().p_178_J().w_1484_f();
        if (!ipbanlist.n_1700_B(ipAddress)) {
            throw J_1907_R.create();
        }
        ipbanlist.R_4764_Y(ipAddress);
        source.n_1700_B(new F_2904_S("commands.pardonip.success", ipAddress), true);
        return 1;
    }
}


