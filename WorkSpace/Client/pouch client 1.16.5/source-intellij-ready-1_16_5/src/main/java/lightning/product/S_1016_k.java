/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.I_14_v;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.i_4556_r;
import lightning.product.y_2498_m;

public class S_1016_k {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.spectate.self"));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_229830_0_ -> new F_2904_S("commands.spectate.not_spectator", p_229830_0_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> p_229826_0_) {
        p_229826_0_.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("spectate").requires(p_229828_0_ -> p_229828_0_.n_1700_B(2))).executes(p_229832_0_ -> S_1016_k.n_1700_B((y_2498_m)p_229832_0_.getSource(), null, ((y_2498_m)p_229832_0_.getSource()).t_1786_h()))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("target", i_4556_r.n_1700_B()).executes(p_229831_0_ -> S_1016_k.n_1700_B((y_2498_m)p_229831_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_229831_0_, "target"), ((y_2498_m)p_229831_0_.getSource()).t_1786_h()))).then(Q_2241_p.n_1700_B("player", i_4556_r.R_4764_Y()).executes(p_229827_0_ -> S_1016_k.n_1700_B((y_2498_m)p_229827_0_.getSource(), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_229827_0_, "target"), i_4556_r.P_1922_E((CommandContext<y_2498_m>)p_229827_0_, "player"))))));
    }

    private static int n_1700_B(y_2498_m p_229829_0_, @Nullable N_4263_v p_229829_1_, B_4088_l p_229829_2_) throws CommandSyntaxException {
        if (p_229829_2_ == p_229829_1_) {
            throw n_1700_B.create();
        }
        if (p_229829_2_.R_4764_Y.J_1907_R() != I_14_v.P_1922_E) {
            throw J_1907_R.create((Object)p_229829_2_.c_());
        }
        p_229829_2_.t_4043_B(p_229829_1_);
        if (p_229829_1_ != null) {
            p_229829_0_.n_1700_B(new F_2904_S("commands.spectate.success.started", p_229829_1_.c_()), false);
        } else {
            p_229829_0_.n_1700_B(new F_2904_S("commands.spectate.success.stopped"), false);
        }
        return 1;
    }
}

