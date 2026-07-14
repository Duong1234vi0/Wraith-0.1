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
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.BlockInput;
import lightning.product.F_2904_S;
import lightning.product.Clearable;
import lightning.product.BoundingBox;
import lightning.product.Q_2241_p;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.BlockPosArgument;
import lightning.product.BlockInWorld;
import lightning.product.BlockStateArgument;
import lightning.product.y_2498_m;

public class SetBlockCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.setblock.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("setblock").requires(p_198688_0_ -> p_198688_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("pos", BlockPosArgument.n_1700_B()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("block", BlockStateArgument.n_1700_B()).executes(p_198682_0_ -> SetBlockCommand.n_1700_B((y_2498_m)p_198682_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198682_0_, "pos"), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198682_0_, "block"), J_1907_R.n_1700_B, null))).then(Q_2241_p.n_1700_B("destroy").executes(p_198685_0_ -> SetBlockCommand.n_1700_B((y_2498_m)p_198685_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198685_0_, "pos"), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198685_0_, "block"), J_1907_R.J_1907_R, null)))).then(Q_2241_p.n_1700_B("keep").executes(p_198681_0_ -> SetBlockCommand.n_1700_B((y_2498_m)p_198681_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198681_0_, "pos"), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198681_0_, "block"), J_1907_R.n_1700_B, p_198687_0_ -> p_198687_0_.R_4764_Y().u_1723_Y(p_198687_0_.G_564_y()))))).then(Q_2241_p.n_1700_B("replace").executes(p_198686_0_ -> SetBlockCommand.n_1700_B((y_2498_m)p_198686_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198686_0_, "pos"), BlockStateArgument.n_1700_B((CommandContext<y_2498_m>)p_198686_0_, "block"), J_1907_R.n_1700_B, null))))));
    }

    private static int n_1700_B(y_2498_m source, c_1514_x pos, BlockInput state, J_1907_R mode, @Nullable Predicate<BlockInWorld> predicate) throws CommandSyntaxException {
        boolean flag;
        e_3591_l serverworld = source.h_1847_R();
        if (predicate != null && !predicate.test(new BlockInWorld(serverworld, pos, true))) {
            throw n_1700_B.create();
        }
        if (mode == J_1907_R.J_1907_R) {
            serverworld.J_1907_R(pos, true);
            flag = !state.n_1700_B().v_4262_N() || !serverworld.getBlockState(pos).v_4262_N();
        } else {
            i_2154_H tileentity = serverworld.getTileEntity(pos);
            Clearable.n_1700_B(tileentity);
            flag = true;
        }
        if (flag && !state.n_1700_B(serverworld, pos, 2)) {
            throw n_1700_B.create();
        }
        serverworld.n_1700_B(pos, state.n_1700_B().J_1907_R());
        source.n_1700_B(new F_2904_S("commands.setblock.success", pos.getX(), pos.getY(), pos.getZ()), true);
        return 1;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] R_4764_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])R_4764_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.SetBlockCommand$J_1907_R.n_1700_B();
        }
    }

    public static interface n_1700_B {
        @Nullable
        public BlockInput filter(BoundingBox var1, c_1514_x var2, BlockInput var3, e_3591_l var4);
    }
}


