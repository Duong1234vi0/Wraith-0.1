/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.ArrayList;
import java.util.Collection;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.X_4512_s;
import lightning.product.Z_1993_T;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.i_4556_r;
import lightning.product.BlockPosArgument;
import lightning.product.y_2498_m;
import lightning.product.ItemArgument;

public class ReplaceItemCommand {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.replaceitem.block.failed"));
    public static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_211409_0_ -> new F_2904_S("commands.replaceitem.slot.inapplicable", p_211409_0_));
    public static final Dynamic2CommandExceptionType R_4764_Y = new Dynamic2CommandExceptionType((p_211411_0_, p_211411_1_) -> new F_2904_S("commands.replaceitem.entity.failed", p_211411_0_, p_211411_1_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("replaceitem").requires(p_198607_0_ -> p_198607_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("block").then(Q_2241_p.n_1700_B("pos", BlockPosArgument.n_1700_B()).then(Q_2241_p.n_1700_B("slot", X_4512_s.n_1700_B()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("item", ItemArgument.n_1700_B()).executes(p_198601_0_ -> ReplaceItemCommand.n_1700_B((y_2498_m)p_198601_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198601_0_, "pos"), X_4512_s.n_1700_B((CommandContext<y_2498_m>)p_198601_0_, "slot"), ItemArgument.n_1700_B(p_198601_0_, "item").n_1700_B(1, false)))).then(Q_2241_p.n_1700_B("count", IntegerArgumentType.integer((int)1, (int)64)).executes(p_198605_0_ -> ReplaceItemCommand.n_1700_B((y_2498_m)p_198605_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198605_0_, "pos"), X_4512_s.n_1700_B((CommandContext<y_2498_m>)p_198605_0_, "slot"), ItemArgument.n_1700_B(p_198605_0_, "item").n_1700_B(IntegerArgumentType.getInteger((CommandContext)p_198605_0_, (String)"count"), true))))))))).then(Q_2241_p.n_1700_B("entity").then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).then(Q_2241_p.n_1700_B("slot", X_4512_s.n_1700_B()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("item", ItemArgument.n_1700_B()).executes(p_198600_0_ -> ReplaceItemCommand.n_1700_B((y_2498_m)p_198600_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198600_0_, "targets"), X_4512_s.n_1700_B((CommandContext<y_2498_m>)p_198600_0_, "slot"), ItemArgument.n_1700_B(p_198600_0_, "item").n_1700_B(1, false)))).then(Q_2241_p.n_1700_B("count", IntegerArgumentType.integer((int)1, (int)64)).executes(p_198606_0_ -> ReplaceItemCommand.n_1700_B((y_2498_m)p_198606_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198606_0_, "targets"), X_4512_s.n_1700_B((CommandContext<y_2498_m>)p_198606_0_, "slot"), ItemArgument.n_1700_B(p_198606_0_, "item").n_1700_B(IntegerArgumentType.getInteger((CommandContext)p_198606_0_, (String)"count"), true)))))))));
    }

    private static int n_1700_B(y_2498_m source, c_1514_x pos, int slotIn, Z_1993_T newStack) throws CommandSyntaxException {
        i_2154_H tileentity = source.h_1847_R().getTileEntity(pos);
        if (!(tileentity instanceof Container)) {
            throw n_1700_B.create();
        }
        Container iinventory = (Container)((Object)tileentity);
        if (slotIn >= 0 && slotIn < iinventory.Y_259_p()) {
            iinventory.J_1907_R(slotIn, newStack);
            source.n_1700_B(new F_2904_S("commands.replaceitem.block.success", pos.getX(), pos.getY(), pos.getZ(), newStack.A_4115_X()), true);
            return 1;
        }
        throw J_1907_R.create((Object)slotIn);
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets, int slotIn, Z_1993_T newStack) throws CommandSyntaxException {
        ArrayList list = Lists.newArrayListWithCapacity((int)targets.size());
        for (N_4263_v n_4263_v : targets) {
            if (n_4263_v instanceof B_4088_l) {
                ((B_4088_l)n_4263_v).o_1800_r.M_588_G();
            }
            if (!n_4263_v.n_1700_B(slotIn, newStack.t_148_a())) continue;
            list.add(n_4263_v);
            if (!(n_4263_v instanceof B_4088_l)) continue;
            ((B_4088_l)n_4263_v).o_1800_r.M_588_G();
        }
        if (list.isEmpty()) {
            throw R_4764_Y.create((Object)newStack.A_4115_X(), (Object)slotIn);
        }
        if (list.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.replaceitem.entity.success.single", ((N_4263_v)list.iterator().next()).c_(), newStack.A_4115_X()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.replaceitem.entity.success.multiple", list.size(), newStack.A_4115_X()), true);
        }
        return list.size();
    }
}


