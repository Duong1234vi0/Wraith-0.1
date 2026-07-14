/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  it.unimi.dsi.fastutil.longs.LongSet
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import it.unimi.dsi.fastutil.longs.LongSet;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.W_2944_a;
import lightning.product.Y_1387_d;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.BlockPosArgument;
import lightning.product.ColumnPosArgument;
import lightning.product.y_2498_m;

public class d_3113_t {
    private static final Dynamic2CommandExceptionType n_1700_B = new Dynamic2CommandExceptionType((p_212724_0_, p_212724_1_) -> new F_2904_S("commands.forceload.toobig", p_212724_0_, p_212724_1_));
    private static final Dynamic2CommandExceptionType J_1907_R = new Dynamic2CommandExceptionType((p_212717_0_, p_212717_1_) -> new F_2904_S("commands.forceload.query.failure", p_212717_0_, p_212717_1_));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.forceload.added.failure"));
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.forceload.removed.failure"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("forceload").requires(p_212716_0_ -> p_212716_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("add").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("from", ColumnPosArgument.n_1700_B()).executes(p_212711_0_ -> d_3113_t.n_1700_B((y_2498_m)p_212711_0_.getSource(), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_212711_0_, "from"), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_212711_0_, "from"), true))).then(Q_2241_p.n_1700_B("to", ColumnPosArgument.n_1700_B()).executes(p_212714_0_ -> d_3113_t.n_1700_B((y_2498_m)p_212714_0_.getSource(), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_212714_0_, "from"), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_212714_0_, "to"), true)))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("remove").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("from", ColumnPosArgument.n_1700_B()).executes(p_218850_0_ -> d_3113_t.n_1700_B((y_2498_m)p_218850_0_.getSource(), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218850_0_, "from"), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218850_0_, "from"), false))).then(Q_2241_p.n_1700_B("to", ColumnPosArgument.n_1700_B()).executes(p_212718_0_ -> d_3113_t.n_1700_B((y_2498_m)p_212718_0_.getSource(), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_212718_0_, "from"), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_212718_0_, "to"), false))))).then(Q_2241_p.n_1700_B("all").executes(p_212715_0_ -> d_3113_t.J_1907_R((y_2498_m)p_212715_0_.getSource()))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("query").executes(p_212710_0_ -> d_3113_t.n_1700_B((y_2498_m)p_212710_0_.getSource()))).then(Q_2241_p.n_1700_B("pos", ColumnPosArgument.n_1700_B()).executes(p_212723_0_ -> d_3113_t.n_1700_B((y_2498_m)p_212723_0_.getSource(), ColumnPosArgument.n_1700_B((CommandContext<y_2498_m>)p_212723_0_, "pos"))))));
    }

    private static int n_1700_B(y_2498_m p_212713_0_, W_2944_a p_212713_1_) throws CommandSyntaxException {
        Y_1387_d chunkpos = new Y_1387_d(p_212713_1_.n_1700_B >> 4, p_212713_1_.J_1907_R >> 4);
        e_3591_l serverworld = p_212713_0_.h_1847_R();
        f_2392_k<b_4507_u> registrykey = serverworld.g_2268_R();
        boolean flag = serverworld.Ping().contains(chunkpos.n_1700_B());
        if (flag) {
            p_212713_0_.n_1700_B(new F_2904_S("commands.forceload.query.success", chunkpos, registrykey.n_1700_B()), false);
            return 1;
        }
        throw J_1907_R.create((Object)chunkpos, (Object)registrykey.n_1700_B());
    }

    private static int n_1700_B(y_2498_m p_212721_0_) {
        e_3591_l serverworld = p_212721_0_.h_1847_R();
        f_2392_k<b_4507_u> registrykey = serverworld.g_2268_R();
        LongSet longset = serverworld.Ping();
        int i = longset.size();
        if (i > 0) {
            String s = Joiner.on((String)", ").join(longset.stream().sorted().map(Y_1387_d::new).map(Y_1387_d::toString).iterator());
            if (i == 1) {
                p_212721_0_.n_1700_B(new F_2904_S("commands.forceload.list.single", registrykey.n_1700_B(), s), false);
            } else {
                p_212721_0_.n_1700_B(new F_2904_S("commands.forceload.list.multiple", i, registrykey.n_1700_B(), s), false);
            }
        } else {
            p_212721_0_.n_1700_B(new F_2904_S("commands.forceload.added.none", registrykey.n_1700_B()));
        }
        return i;
    }

    private static int J_1907_R(y_2498_m p_212722_0_) {
        e_3591_l serverworld = p_212722_0_.h_1847_R();
        f_2392_k<b_4507_u> registrykey = serverworld.g_2268_R();
        LongSet longset = serverworld.Ping();
        longset.forEach(p_212720_1_ -> serverworld.n_1700_B(Y_1387_d.n_1700_B(p_212720_1_), Y_1387_d.J_1907_R(p_212720_1_), false));
        p_212722_0_.n_1700_B(new F_2904_S("commands.forceload.removed.all", registrykey.n_1700_B()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m p_212719_0_, W_2944_a p_212719_1_, W_2944_a p_212719_2_, boolean p_212719_3_) throws CommandSyntaxException {
        int i = Math.min(p_212719_1_.n_1700_B, p_212719_2_.n_1700_B);
        int j = Math.min(p_212719_1_.J_1907_R, p_212719_2_.J_1907_R);
        int k = Math.max(p_212719_1_.n_1700_B, p_212719_2_.n_1700_B);
        int l = Math.max(p_212719_1_.J_1907_R, p_212719_2_.J_1907_R);
        if (i >= -30000000 && j >= -30000000 && k < 30000000 && l < 30000000) {
            int k1 = k >> 4;
            int i1 = i >> 4;
            int l1 = l >> 4;
            int j1 = j >> 4;
            long i2 = ((long)(k1 - i1) + 1L) * ((long)(l1 - j1) + 1L);
            if (i2 > 256L) {
                throw n_1700_B.create((Object)256, (Object)i2);
            }
            e_3591_l serverworld = p_212719_0_.h_1847_R();
            f_2392_k<b_4507_u> registrykey = serverworld.g_2268_R();
            Y_1387_d chunkpos = null;
            int j2 = 0;
            for (int k2 = i1; k2 <= k1; ++k2) {
                for (int l2 = j1; l2 <= l1; ++l2) {
                    boolean flag = serverworld.n_1700_B(k2, l2, p_212719_3_);
                    if (!flag) continue;
                    ++j2;
                    if (chunkpos != null) continue;
                    chunkpos = new Y_1387_d(k2, l2);
                }
            }
            if (j2 == 0) {
                throw (p_212719_3_ ? R_4764_Y : G_564_y).create();
            }
            if (j2 == 1) {
                p_212719_0_.n_1700_B(new F_2904_S("commands.forceload." + (p_212719_3_ ? "added" : "removed") + ".single", chunkpos, registrykey.n_1700_B()), true);
            } else {
                Y_1387_d chunkpos1 = new Y_1387_d(i1, j1);
                Y_1387_d chunkpos2 = new Y_1387_d(k1, l1);
                p_212719_0_.n_1700_B(new F_2904_S("commands.forceload." + (p_212719_3_ ? "added" : "removed") + ".multiple", j2, registrykey.n_1700_B(), chunkpos1, chunkpos2), true);
            }
            return j2;
        }
        throw BlockPosArgument.J_1907_R.create();
    }
}


