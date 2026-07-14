/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.BlockPredicateArgument;
import lightning.product.BlockInput;
import lightning.product.F_2904_S;
import lightning.product.Clearable;
import lightning.product.BoundingBox;
import lightning.product.Q_2241_p;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.BlockPosArgument;
import lightning.product.SetBlockCommand;
import lightning.product.BlockInWorld;
import lightning.product.BlockStateArgument;
import lightning.product.y_2498_m;

public class a_3139_m {
    private static final Dynamic2CommandExceptionType n_1700_B = new Dynamic2CommandExceptionType((p_208897_0_, p_208897_1_) -> new F_2904_S("commands.fill.toobig", p_208897_0_, p_208897_1_));
    private static final BlockInput J_1907_R = new BlockInput(a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), Collections.emptySet(), null);
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.fill.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("fill").requires(p_198471_0_ -> p_198471_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("from", BlockPosArgument.n_1700_B()).then(Q_2241_p.n_1700_B("to", BlockPosArgument.n_1700_B()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("block", BlockStateArgument.n_1700_B()).executes(p_198472_0_ -> a_3139_m.n_1700_B((y_2498_m)p_198472_0_.getSource(), new BoundingBox(BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198472_0_, "from"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198472_0_, "to")), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198472_0_, "block"), lightning.product.a_3139_m$n_1700_B.n_1700_B, null))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("replace").executes(p_198464_0_ -> a_3139_m.n_1700_B((y_2498_m)p_198464_0_.getSource(), new BoundingBox(BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198464_0_, "from"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198464_0_, "to")), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198464_0_, "block"), lightning.product.a_3139_m$n_1700_B.n_1700_B, null))).then(Q_2241_p.n_1700_B("filter", BlockPredicateArgument.n_1700_B()).executes(p_198466_0_ -> a_3139_m.n_1700_B((y_2498_m)p_198466_0_.getSource(), new BoundingBox(BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198466_0_, "from"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198466_0_, "to")), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198466_0_, "block"), lightning.product.a_3139_m$n_1700_B.n_1700_B, BlockPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_198466_0_, "filter")))))).then(Q_2241_p.n_1700_B("keep").executes(p_198462_0_ -> a_3139_m.n_1700_B((y_2498_m)p_198462_0_.getSource(), new BoundingBox(BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198462_0_, "from"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198462_0_, "to")), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198462_0_, "block"), lightning.product.a_3139_m$n_1700_B.n_1700_B, p_198469_0_ -> p_198469_0_.R_4764_Y().u_1723_Y(p_198469_0_.G_564_y()))))).then(Q_2241_p.n_1700_B("outline").executes(p_198467_0_ -> a_3139_m.n_1700_B((y_2498_m)p_198467_0_.getSource(), new BoundingBox(BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198467_0_, "from"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198467_0_, "to")), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198467_0_, "block"), lightning.product.a_3139_m$n_1700_B.J_1907_R, null)))).then(Q_2241_p.n_1700_B("hollow").executes(p_198461_0_ -> a_3139_m.n_1700_B((y_2498_m)p_198461_0_.getSource(), new BoundingBox(BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198461_0_, "from"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198461_0_, "to")), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198461_0_, "block"), lightning.product.a_3139_m$n_1700_B.R_4764_Y, null)))).then(Q_2241_p.n_1700_B("destroy").executes(p_198468_0_ -> a_3139_m.n_1700_B((y_2498_m)p_198468_0_.getSource(), new BoundingBox(BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198468_0_, "from"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198468_0_, "to")), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198468_0_, "block"), lightning.product.a_3139_m$n_1700_B.G_564_y, null)))))));
    }

    private static int n_1700_B(y_2498_m source, BoundingBox area, BlockInput newBlock, n_1700_B mode, @Nullable Predicate<BlockInWorld> replacingPredicate) throws CommandSyntaxException {
        int i = area.G_564_y() * area.P_1922_E() * area.u_1723_Y();
        if (i > 32768) {
            throw n_1700_B.create((Object)32768, (Object)i);
        }
        ArrayList list = Lists.newArrayList();
        e_3591_l serverworld = source.h_1847_R();
        int j = 0;
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(area.n_1700_B, area.J_1907_R, area.R_4764_Y, area.G_564_y, area.P_1922_E, area.u_1723_Y)) {
            BlockInput blockstateinput;
            if (replacingPredicate != null && !replacingPredicate.test(new BlockInWorld(serverworld, blockpos, true)) || (blockstateinput = mode.P_1922_E.filter(area, blockpos, newBlock, serverworld)) == null) continue;
            i_2154_H tileentity = serverworld.getTileEntity(blockpos);
            Clearable.n_1700_B(tileentity);
            if (!blockstateinput.n_1700_B(serverworld, blockpos, 2)) continue;
            list.add(blockpos.toImmutable());
            ++j;
        }
        for (c_1514_x blockpos1 : list) {
            T_2915_h block = serverworld.getBlockState(blockpos1).J_1907_R();
            serverworld.n_1700_B(blockpos1, block);
        }
        if (j == 0) {
            throw R_4764_Y.create();
        }
        source.n_1700_B(new F_2904_S("commands.fill.success", j), true);
        return j;
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B((p_198450_0_, p_198450_1_, p_198450_2_, p_198450_3_) -> p_198450_2_);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B((p_198454_0_, p_198454_1_, p_198454_2_, p_198454_3_) -> p_198454_1_.getX() != p_198454_0_.n_1700_B && p_198454_1_.getX() != p_198454_0_.G_564_y && p_198454_1_.getY() != p_198454_0_.J_1907_R && p_198454_1_.getY() != p_198454_0_.P_1922_E && p_198454_1_.getZ() != p_198454_0_.R_4764_Y && p_198454_1_.getZ() != p_198454_0_.u_1723_Y ? null : p_198454_2_);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B((p_198453_0_, p_198453_1_, p_198453_2_, p_198453_3_) -> p_198453_1_.getX() != p_198453_0_.n_1700_B && p_198453_1_.getX() != p_198453_0_.G_564_y && p_198453_1_.getY() != p_198453_0_.J_1907_R && p_198453_1_.getY() != p_198453_0_.P_1922_E && p_198453_1_.getZ() != p_198453_0_.R_4764_Y && p_198453_1_.getZ() != p_198453_0_.u_1723_Y ? J_1907_R : p_198453_2_);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B((p_198452_0_, p_198452_1_, p_198452_2_, p_198452_3_) -> {
            p_198452_3_.J_1907_R(p_198452_1_, true);
            return p_198452_2_;
        });
        public final SetBlockCommand.n_1700_B P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(SetBlockCommand.n_1700_B filterIn) {
            this.P_1922_E = filterIn;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            u_1723_Y = lightning.product.a_3139_m$n_1700_B.n_1700_B();
        }
    }
}


