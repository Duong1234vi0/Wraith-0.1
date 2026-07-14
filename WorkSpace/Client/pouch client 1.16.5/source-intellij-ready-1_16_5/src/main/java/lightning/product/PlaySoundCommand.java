/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.FloatArgumentType
 *  com.mojang.brigadier.builder.ArgumentBuilder
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
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Iterator;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.ClientboundCustomSoundPacket;
import lightning.product.Q_2241_p;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.h_4126_t;
import lightning.product.i_4556_r;
import lightning.product.ResourceLocationArgument;
import lightning.product.u_1579_Y;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;

public class PlaySoundCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.playsound.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        RequiredArgumentBuilder requiredargumentbuilder = Q_2241_p.n_1700_B("sound", ResourceLocationArgument.n_1700_B()).suggests(h_4126_t.R_4764_Y);
        for (D_38_f soundcategory : D_38_f.values()) {
            requiredargumentbuilder.then(PlaySoundCommand.n_1700_B(soundcategory));
        }
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("playsound").requires(p_198576_0_ -> p_198576_0_.n_1700_B(2))).then((ArgumentBuilder)requiredargumentbuilder));
    }

    private static LiteralArgumentBuilder<y_2498_m> n_1700_B(D_38_f category) {
        return (LiteralArgumentBuilder)Q_2241_p.n_1700_B(category.n_1700_B()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).executes(p_198575_1_ -> PlaySoundCommand.n_1700_B((y_2498_m)p_198575_1_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198575_1_, "targets"), ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_198575_1_, "sound"), category, ((y_2498_m)p_198575_1_.getSource()).P_4830_p(), 1.0f, 1.0f, 0.0f))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pos", u_1579_Y.n_1700_B()).executes(p_198578_1_ -> PlaySoundCommand.n_1700_B((y_2498_m)p_198578_1_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198578_1_, "targets"), ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_198578_1_, "sound"), category, u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198578_1_, "pos"), 1.0f, 1.0f, 0.0f))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("volume", FloatArgumentType.floatArg((float)0.0f)).executes(p_198571_1_ -> PlaySoundCommand.n_1700_B((y_2498_m)p_198571_1_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198571_1_, "targets"), ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_198571_1_, "sound"), category, u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198571_1_, "pos"), ((Float)p_198571_1_.getArgument("volume", Float.class)).floatValue(), 1.0f, 0.0f))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pitch", FloatArgumentType.floatArg((float)0.0f, (float)2.0f)).executes(p_198574_1_ -> PlaySoundCommand.n_1700_B((y_2498_m)p_198574_1_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198574_1_, "targets"), ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_198574_1_, "sound"), category, u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198574_1_, "pos"), ((Float)p_198574_1_.getArgument("volume", Float.class)).floatValue(), ((Float)p_198574_1_.getArgument("pitch", Float.class)).floatValue(), 0.0f))).then(Q_2241_p.n_1700_B("minVolume", FloatArgumentType.floatArg((float)0.0f, (float)1.0f)).executes(p_198570_1_ -> PlaySoundCommand.n_1700_B((y_2498_m)p_198570_1_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198570_1_, "targets"), ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_198570_1_, "sound"), category, u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198570_1_, "pos"), ((Float)p_198570_1_.getArgument("volume", Float.class)).floatValue(), ((Float)p_198570_1_.getArgument("pitch", Float.class)).floatValue(), ((Float)p_198570_1_.getArgument("minVolume", Float.class)).floatValue())))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets, g_2336_b soundIn, D_38_f category, e_2866_D pos, float volume, float pitch, float minVolume) throws CommandSyntaxException {
        double d0 = Math.pow(volume > 1.0f ? (double)(volume * 16.0f) : 16.0, 2.0);
        int i = 0;
        Iterator<B_4088_l> iterator = targets.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                if (i == 0) {
                    throw n_1700_B.create();
                }
                if (targets.size() == 1) {
                    source.n_1700_B(new F_2904_S("commands.playsound.success.single", soundIn, targets.iterator().next().c_()), true);
                } else {
                    source.n_1700_B(new F_2904_S("commands.playsound.success.multiple", soundIn, targets.size()), true);
                }
                return i;
            }
            B_4088_l serverplayerentity = iterator.next();
            double d1 = pos.J_1907_R - serverplayerentity.O_3598_v();
            double d2 = pos.R_4764_Y - serverplayerentity.X_2960_b();
            double d3 = pos.G_564_y - serverplayerentity.l_2647_k();
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;
            e_2866_D vector3d = pos;
            float f = volume;
            if (d4 > d0) {
                if (minVolume <= 0.0f) continue;
                double d5 = u_530_F.n_1700_B(d4);
                vector3d = new e_2866_D(serverplayerentity.O_3598_v() + d1 / d5 * 2.0, serverplayerentity.X_2960_b() + d2 / d5 * 2.0, serverplayerentity.l_2647_k() + d3 / d5 * 2.0);
                f = minVolume;
            }
            serverplayerentity.n_1700_B.n_1700_B(new ClientboundCustomSoundPacket(soundIn, category, vector3d, f, pitch));
            ++i;
        }
    }
}


