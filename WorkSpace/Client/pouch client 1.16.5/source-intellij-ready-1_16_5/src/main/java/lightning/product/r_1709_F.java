/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.G_4584_Z;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.g_1995_W;
import lightning.product.UserWhiteListEntry;
import lightning.product.ComponentUtils;
import lightning.product.GameProfileArgument;
import lightning.product.y_2498_m;

public class r_1709_F {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.whitelist.alreadyOn"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.whitelist.alreadyOff"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.whitelist.add.failed"));
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.whitelist.remove.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("whitelist").requires(p_198877_0_ -> p_198877_0_.n_1700_B(3))).then(Q_2241_p.n_1700_B("on").executes(p_198872_0_ -> r_1709_F.J_1907_R((y_2498_m)p_198872_0_.getSource())))).then(Q_2241_p.n_1700_B("off").executes(p_198874_0_ -> r_1709_F.R_4764_Y((y_2498_m)p_198874_0_.getSource())))).then(Q_2241_p.n_1700_B("list").executes(p_198878_0_ -> r_1709_F.G_564_y((y_2498_m)p_198878_0_.getSource())))).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("targets", GameProfileArgument.n_1700_B()).suggests((p_198879_0_, p_198879_1_) -> {
            g_1995_W playerlist = ((y_2498_m)p_198879_0_.getSource()).w_1457_N().p_178_J();
            return V_4217_p.J_1907_R(playerlist.w_1457_N().stream().filter(p_198871_1_ -> !playerlist.s_956_w().n_1700_B(p_198871_1_.y_4642_Y())).map(p_200567_0_ -> p_200567_0_.y_4642_Y().getName()), p_198879_1_);
        }).executes(p_198875_0_ -> r_1709_F.n_1700_B((y_2498_m)p_198875_0_.getSource(), GameProfileArgument.n_1700_B((CommandContext<y_2498_m>)p_198875_0_, "targets")))))).then(Q_2241_p.n_1700_B("remove").then(Q_2241_p.n_1700_B("targets", GameProfileArgument.n_1700_B()).suggests((p_198881_0_, p_198881_1_) -> V_4217_p.n_1700_B(((y_2498_m)p_198881_0_.getSource()).w_1457_N().p_178_J().u_2550_I(), p_198881_1_)).executes(p_198870_0_ -> r_1709_F.J_1907_R((y_2498_m)p_198870_0_.getSource(), GameProfileArgument.n_1700_B((CommandContext<y_2498_m>)p_198870_0_, "targets")))))).then(Q_2241_p.n_1700_B("reload").executes(p_198882_0_ -> r_1709_F.n_1700_B((y_2498_m)p_198882_0_.getSource()))));
    }

    private static int n_1700_B(y_2498_m source) {
        source.w_1457_N().p_178_J().n_1700_B();
        source.n_1700_B(new F_2904_S("commands.whitelist.reloaded"), true);
        source.w_1457_N().n_1700_B(source);
        return 1;
    }

    private static int n_1700_B(y_2498_m source, Collection<GameProfile> players) throws CommandSyntaxException {
        G_4584_Z whitelist = source.w_1457_N().p_178_J().s_956_w();
        int i = 0;
        for (GameProfile gameprofile : players) {
            if (whitelist.n_1700_B(gameprofile)) continue;
            UserWhiteListEntry whitelistentry = new UserWhiteListEntry(gameprofile);
            whitelist.n_1700_B(whitelistentry);
            source.n_1700_B(new F_2904_S("commands.whitelist.add.success", ComponentUtils.n_1700_B(gameprofile)), true);
            ++i;
        }
        if (i == 0) {
            throw R_4764_Y.create();
        }
        return i;
    }

    private static int J_1907_R(y_2498_m source, Collection<GameProfile> players) throws CommandSyntaxException {
        G_4584_Z whitelist = source.w_1457_N().p_178_J().s_956_w();
        int i = 0;
        for (GameProfile gameprofile : players) {
            if (!whitelist.n_1700_B(gameprofile)) continue;
            UserWhiteListEntry whitelistentry = new UserWhiteListEntry(gameprofile);
            whitelist.J_1907_R(whitelistentry);
            source.n_1700_B(new F_2904_S("commands.whitelist.remove.success", ComponentUtils.n_1700_B(gameprofile)), true);
            ++i;
        }
        if (i == 0) {
            throw G_564_y.create();
        }
        source.w_1457_N().n_1700_B(source);
        return i;
    }

    private static int J_1907_R(y_2498_m source) throws CommandSyntaxException {
        g_1995_W playerlist = source.w_1457_N().p_178_J();
        if (playerlist.M_182_A()) {
            throw n_1700_B.create();
        }
        playerlist.n_1700_B(true);
        source.n_1700_B(new F_2904_S("commands.whitelist.enabled"), true);
        source.w_1457_N().n_1700_B(source);
        return 1;
    }

    private static int R_4764_Y(y_2498_m source) throws CommandSyntaxException {
        g_1995_W playerlist = source.w_1457_N().p_178_J();
        if (!playerlist.M_182_A()) {
            throw J_1907_R.create();
        }
        playerlist.n_1700_B(false);
        source.n_1700_B(new F_2904_S("commands.whitelist.disabled"), true);
        return 1;
    }

    private static int G_564_y(y_2498_m source) {
        CharSequence[] astring = source.w_1457_N().p_178_J().u_2550_I();
        if (astring.length == 0) {
            source.n_1700_B(new F_2904_S("commands.whitelist.none"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.whitelist.list", astring.length, String.join((CharSequence)", ", astring)), false);
        }
        return astring.length;
    }
}


