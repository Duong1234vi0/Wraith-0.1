/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.UserBanListEntry;
import lightning.product.Q_2241_p;
import lightning.product.MessageArgument;
import lightning.product.q_1829_g;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.GameProfileArgument;
import lightning.product.y_2498_m;

public class BanPlayerCommands {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.ban.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("ban").requires(p_198238_0_ -> p_198238_0_.n_1700_B(3))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", GameProfileArgument.n_1700_B()).executes(p_198234_0_ -> BanPlayerCommands.n_1700_B((y_2498_m)p_198234_0_.getSource(), GameProfileArgument.n_1700_B((CommandContext<y_2498_m>)p_198234_0_, "targets"), null))).then(Q_2241_p.n_1700_B("reason", MessageArgument.n_1700_B()).executes(p_198237_0_ -> BanPlayerCommands.n_1700_B((y_2498_m)p_198237_0_.getSource(), GameProfileArgument.n_1700_B((CommandContext<y_2498_m>)p_198237_0_, "targets"), MessageArgument.n_1700_B((CommandContext<y_2498_m>)p_198237_0_, "reason"))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<GameProfile> gameProfiles, @Nullable x_282_a reason) throws CommandSyntaxException {
        q_1829_g banlist = source.w_1457_N().p_178_J().v_4262_N();
        int i = 0;
        for (GameProfile gameprofile : gameProfiles) {
            if (banlist.n_1700_B(gameprofile)) continue;
            UserBanListEntry profilebanentry = new UserBanListEntry(gameprofile, (Date)null, source.M_588_G(), (Date)null, reason == null ? null : reason.getString());
            banlist.n_1700_B(profilebanentry);
            ++i;
            source.n_1700_B(new F_2904_S("commands.ban.success", ComponentUtils.n_1700_B(gameprofile), profilebanentry.R_4764_Y()), true);
            B_4088_l serverplayerentity = source.w_1457_N().p_178_J().n_1700_B(gameprofile.getId());
            if (serverplayerentity == null) continue;
            serverplayerentity.n_1700_B.n_1700_B(new F_2904_S("multiplayer.disconnect.banned"));
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        return i;
    }
}


