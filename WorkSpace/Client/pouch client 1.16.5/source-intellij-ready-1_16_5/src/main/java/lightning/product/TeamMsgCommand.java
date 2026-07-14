/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.List;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.Z_1567_W;
import lightning.product.PlayerTeam;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.MessageArgument;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class TeamMsgCommand {
    private static final Z_1567_W n_1700_B = Z_1567_W.n_1700_B.n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new F_2904_S("chat.type.team.hover"))).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.G_564_y, "/teammsg "));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.teammsg.failed.noteam"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> p_218915_0_) {
        LiteralCommandNode literalcommandnode = p_218915_0_.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("teammsg").then(Q_2241_p.n_1700_B("message", MessageArgument.n_1700_B()).executes(p_218916_0_ -> TeamMsgCommand.n_1700_B((y_2498_m)p_218916_0_.getSource(), MessageArgument.n_1700_B((CommandContext<y_2498_m>)p_218916_0_, "message")))));
        p_218915_0_.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("tm").redirect((CommandNode)literalcommandnode));
    }

    private static int n_1700_B(y_2498_m p_218917_0_, x_282_a p_218917_1_) throws CommandSyntaxException {
        N_4263_v entity = p_218917_0_.M_182_A();
        PlayerTeam scoreplayerteam = (PlayerTeam)entity.L_1362_X();
        if (scoreplayerteam == null) {
            throw J_1907_R.create();
        }
        MutableComponent itextcomponent = scoreplayerteam.R_4764_Y().J_1907_R(n_1700_B);
        List<B_4088_l> list = p_218917_0_.w_1457_N().p_178_J().w_1457_N();
        for (B_4088_l serverplayerentity : list) {
            if (serverplayerentity == entity) {
                serverplayerentity.n_1700_B((x_282_a)new F_2904_S("chat.type.team.sent", itextcomponent, p_218917_0_.u_2550_I(), p_218917_1_), entity.w_2705_t());
                continue;
            }
            if (serverplayerentity.L_1362_X() != scoreplayerteam) continue;
            serverplayerentity.n_1700_B((x_282_a)new F_2904_S("chat.type.team.text", itextcomponent, p_218917_0_.u_2550_I(), p_218917_1_), entity.w_2705_t());
        }
        return list.size();
    }
}


