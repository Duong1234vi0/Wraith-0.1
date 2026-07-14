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
import java.util.Collection;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.ParticleOptions;
import lightning.product.V_3137_a;
import lightning.product.e_2866_D;
import lightning.product.ParticleArgument;
import lightning.product.i_4556_r;
import lightning.product.u_1579_Y;
import lightning.product.y_2498_m;

public class ParticleCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.particle.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("particle").requires(p_198568_0_ -> p_198568_0_.n_1700_B(2))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("name", ParticleArgument.n_1700_B()).executes(p_198562_0_ -> ParticleCommand.n_1700_B((y_2498_m)p_198562_0_.getSource(), ParticleArgument.n_1700_B((CommandContext<y_2498_m>)p_198562_0_, "name"), ((y_2498_m)p_198562_0_.getSource()).P_4830_p(), e_2866_D.n_1700_B, 0.0f, 0, false, ((y_2498_m)p_198562_0_.getSource()).w_1457_N().p_178_J().w_1457_N()))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pos", u_1579_Y.n_1700_B()).executes(p_201226_0_ -> ParticleCommand.n_1700_B((y_2498_m)p_201226_0_.getSource(), ParticleArgument.n_1700_B((CommandContext<y_2498_m>)p_201226_0_, "name"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_201226_0_, "pos"), e_2866_D.n_1700_B, 0.0f, 0, false, ((y_2498_m)p_201226_0_.getSource()).w_1457_N().p_178_J().w_1457_N()))).then(Q_2241_p.n_1700_B("delta", u_1579_Y.n_1700_B(false)).then(Q_2241_p.n_1700_B("speed", FloatArgumentType.floatArg((float)0.0f)).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("count", IntegerArgumentType.integer((int)0)).executes(p_198565_0_ -> ParticleCommand.n_1700_B((y_2498_m)p_198565_0_.getSource(), ParticleArgument.n_1700_B((CommandContext<y_2498_m>)p_198565_0_, "name"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198565_0_, "pos"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198565_0_, "delta"), FloatArgumentType.getFloat((CommandContext)p_198565_0_, (String)"speed"), IntegerArgumentType.getInteger((CommandContext)p_198565_0_, (String)"count"), false, ((y_2498_m)p_198565_0_.getSource()).w_1457_N().p_178_J().w_1457_N()))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("force").executes(p_198561_0_ -> ParticleCommand.n_1700_B((y_2498_m)p_198561_0_.getSource(), ParticleArgument.n_1700_B((CommandContext<y_2498_m>)p_198561_0_, "name"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198561_0_, "pos"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198561_0_, "delta"), FloatArgumentType.getFloat((CommandContext)p_198561_0_, (String)"speed"), IntegerArgumentType.getInteger((CommandContext)p_198561_0_, (String)"count"), true, ((y_2498_m)p_198561_0_.getSource()).w_1457_N().p_178_J().w_1457_N()))).then(Q_2241_p.n_1700_B("viewers", i_4556_r.G_564_y()).executes(p_198566_0_ -> ParticleCommand.n_1700_B((y_2498_m)p_198566_0_.getSource(), ParticleArgument.n_1700_B((CommandContext<y_2498_m>)p_198566_0_, "name"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198566_0_, "pos"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198566_0_, "delta"), FloatArgumentType.getFloat((CommandContext)p_198566_0_, (String)"speed"), IntegerArgumentType.getInteger((CommandContext)p_198566_0_, (String)"count"), true, i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198566_0_, "viewers")))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("normal").executes(p_198560_0_ -> ParticleCommand.n_1700_B((y_2498_m)p_198560_0_.getSource(), ParticleArgument.n_1700_B((CommandContext<y_2498_m>)p_198560_0_, "name"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198560_0_, "pos"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198560_0_, "delta"), FloatArgumentType.getFloat((CommandContext)p_198560_0_, (String)"speed"), IntegerArgumentType.getInteger((CommandContext)p_198560_0_, (String)"count"), false, ((y_2498_m)p_198560_0_.getSource()).w_1457_N().p_178_J().w_1457_N()))).then(Q_2241_p.n_1700_B("viewers", i_4556_r.G_564_y()).executes(p_198567_0_ -> ParticleCommand.n_1700_B((y_2498_m)p_198567_0_.getSource(), ParticleArgument.n_1700_B((CommandContext<y_2498_m>)p_198567_0_, "name"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198567_0_, "pos"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198567_0_, "delta"), FloatArgumentType.getFloat((CommandContext)p_198567_0_, (String)"speed"), IntegerArgumentType.getInteger((CommandContext)p_198567_0_, (String)"count"), false, i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198567_0_, "viewers")))))))))));
    }

    private static int n_1700_B(y_2498_m source, ParticleOptions particleData, e_2866_D pos, e_2866_D delta, float speed, int count, boolean force, Collection<B_4088_l> viewers) throws CommandSyntaxException {
        int i = 0;
        for (B_4088_l serverplayerentity : viewers) {
            if (!source.h_1847_R().n_1700_B(serverplayerentity, particleData, force, pos.J_1907_R, pos.R_4764_Y, pos.G_564_y, count, delta.J_1907_R, delta.R_4764_Y, delta.G_564_y, speed)) continue;
            ++i;
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        source.n_1700_B(new F_2904_S("commands.particle.success", V_3137_a.g_164_R.J_1907_R(particleData.G_564_y()).toString()), true);
        return i;
    }
}


