/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.google.common.collect.Sets;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.HashSet;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.i_4556_r;
import lightning.product.ComponentUtils;
import lightning.product.y_2498_m;

public class TagCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.tag.add.failed"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.tag.remove.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("tag").requires(p_198751_0_ -> p_198751_0_.n_1700_B(2))).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("name", StringArgumentType.word()).executes(p_198746_0_ -> TagCommand.n_1700_B((y_2498_m)p_198746_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198746_0_, "targets"), StringArgumentType.getString((CommandContext)p_198746_0_, (String)"name")))))).then(Q_2241_p.n_1700_B("remove").then(Q_2241_p.n_1700_B("name", StringArgumentType.word()).suggests((p_198745_0_, p_198745_1_) -> V_4217_p.J_1907_R(TagCommand.n_1700_B(i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198745_0_, "targets")), p_198745_1_)).executes(p_198742_0_ -> TagCommand.J_1907_R((y_2498_m)p_198742_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198742_0_, "targets"), StringArgumentType.getString((CommandContext)p_198742_0_, (String)"name")))))).then(Q_2241_p.n_1700_B("list").executes(p_198747_0_ -> TagCommand.n_1700_B((y_2498_m)p_198747_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198747_0_, "targets"))))));
    }

    private static Collection<String> n_1700_B(Collection<? extends N_4263_v> entities) {
        HashSet set = Sets.newHashSet();
        for (N_4263_v n_4263_v : entities) {
            set.addAll(n_4263_v.UploadStatus());
        }
        return set;
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> entities, String tagName) throws CommandSyntaxException {
        int i = 0;
        for (N_4263_v n_4263_v : entities) {
            if (!n_4263_v.G_564_y(tagName)) continue;
            ++i;
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        if (entities.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.tag.add.success.single", tagName, entities.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.tag.add.success.multiple", tagName, entities.size()), true);
        }
        return i;
    }

    private static int J_1907_R(y_2498_m source, Collection<? extends N_4263_v> entities, String tagName) throws CommandSyntaxException {
        int i = 0;
        for (N_4263_v n_4263_v : entities) {
            if (!n_4263_v.P_1922_E(tagName)) continue;
            ++i;
        }
        if (i == 0) {
            throw J_1907_R.create();
        }
        if (entities.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.tag.remove.success.single", tagName, entities.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.tag.remove.success.multiple", tagName, entities.size()), true);
        }
        return i;
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> entities) {
        HashSet set = Sets.newHashSet();
        for (N_4263_v n_4263_v : entities) {
            set.addAll(n_4263_v.UploadStatus());
        }
        if (entities.size() == 1) {
            N_4263_v entity1 = entities.iterator().next();
            if (set.isEmpty()) {
                source.n_1700_B(new F_2904_S("commands.tag.list.single.empty", entity1.c_()), false);
            } else {
                source.n_1700_B(new F_2904_S("commands.tag.list.single.success", entity1.c_(), set.size(), ComponentUtils.n_1700_B(set)), false);
            }
        } else if (set.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.tag.list.multiple.empty", entities.size()), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.tag.list.multiple.success", entities.size(), set.size(), ComponentUtils.n_1700_B(set)), false);
        }
        return set.size();
    }
}


