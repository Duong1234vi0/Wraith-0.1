/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import java.util.Collections;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.I_14_v;
import lightning.product.Q_2241_p;
import lightning.product.i_4556_r;
import lightning.product.j_3341_s;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class A_723_Z {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralArgumentBuilder literalargumentbuilder = (LiteralArgumentBuilder)Q_2241_p.n_1700_B("gamemode").requires(p_198485_0_ -> p_198485_0_.n_1700_B(2));
        for (I_14_v gametype : I_14_v.values()) {
            if (gametype == I_14_v.n_1700_B) continue;
            literalargumentbuilder.then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B(gametype.J_1907_R()).executes(p_198483_1_ -> A_723_Z.n_1700_B((CommandContext<y_2498_m>)p_198483_1_, Collections.singleton(((y_2498_m)p_198483_1_.getSource()).t_1786_h()), gametype))).then(Q_2241_p.n_1700_B("target", i_4556_r.G_564_y()).executes(p_198486_1_ -> A_723_Z.n_1700_B((CommandContext<y_2498_m>)p_198486_1_, i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198486_1_, "target"), gametype))));
        }
        dispatcher.register(literalargumentbuilder);
    }

    private static void n_1700_B(y_2498_m source, B_4088_l player, I_14_v gameTypeIn) {
        F_2904_S itextcomponent = new F_2904_S("gameMode." + gameTypeIn.J_1907_R());
        if (source.Q_4569_t() == player) {
            source.n_1700_B(new F_2904_S("commands.gamemode.success.self", itextcomponent), true);
        } else {
            if (source.h_1847_R().H_1990_U().J_1907_R(A_2352_Z.h_1847_R)) {
                player.n_1700_B((x_282_a)new F_2904_S("gameMode.changed", itextcomponent), j_3341_s.J_1907_R);
            }
            source.n_1700_B(new F_2904_S("commands.gamemode.success.other", player.c_(), itextcomponent), true);
        }
    }

    private static int n_1700_B(CommandContext<y_2498_m> source, Collection<B_4088_l> players, I_14_v gameTypeIn) {
        int i = 0;
        for (B_4088_l serverplayerentity : players) {
            if (serverplayerentity.R_4764_Y.J_1907_R() == gameTypeIn) continue;
            serverplayerentity.n_1700_B(gameTypeIn);
            A_723_Z.n_1700_B((y_2498_m)source.getSource(), serverplayerentity, gameTypeIn);
            ++i;
        }
        return i;
    }
}

