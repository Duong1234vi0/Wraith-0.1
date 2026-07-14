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
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.BlockPredicateArgument;
import lightning.product.F_2904_S;
import lightning.product.Clearable;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.Q_2241_p;
import lightning.product.U_2912_j;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.BlockPosArgument;
import lightning.product.BlockInWorld;
import lightning.product.y_2498_m;

public class CloneCommands {
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.clone.overlap"));
    private static final Dynamic2CommandExceptionType R_4764_Y = new Dynamic2CommandExceptionType((p_208796_0_, p_208796_1_) -> new F_2904_S("commands.clone.toobig", p_208796_0_, p_208796_1_));
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.clone.failed"));
    public static final Predicate<BlockInWorld> n_1700_B = p_198275_0_ -> !p_198275_0_.n_1700_B().v_4262_N();

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("clone").requires(p_198271_0_ -> p_198271_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("begin", BlockPosArgument.n_1700_B()).then(Q_2241_p.n_1700_B("end", BlockPosArgument.n_1700_B()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("destination", BlockPosArgument.n_1700_B()).executes(p_198264_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198264_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198264_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198264_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198264_0_, "destination"), p_198269_0_ -> true, lightning.product.CloneCommands$J_1907_R.R_4764_Y))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("replace").executes(p_198268_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198268_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198268_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198268_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198268_0_, "destination"), p_198272_0_ -> true, lightning.product.CloneCommands$J_1907_R.R_4764_Y))).then(Q_2241_p.n_1700_B("force").executes(p_198277_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198277_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198277_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198277_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198277_0_, "destination"), p_198262_0_ -> true, lightning.product.CloneCommands$J_1907_R.n_1700_B)))).then(Q_2241_p.n_1700_B("move").executes(p_198280_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198280_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198280_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198280_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198280_0_, "destination"), p_198281_0_ -> true, lightning.product.CloneCommands$J_1907_R.J_1907_R)))).then(Q_2241_p.n_1700_B("normal").executes(p_198270_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198270_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198270_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198270_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198270_0_, "destination"), p_198279_0_ -> true, lightning.product.CloneCommands$J_1907_R.R_4764_Y))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("masked").executes(p_198276_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198276_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198276_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198276_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198276_0_, "destination"), n_1700_B, lightning.product.CloneCommands$J_1907_R.R_4764_Y))).then(Q_2241_p.n_1700_B("force").executes(p_198282_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198282_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198282_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198282_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198282_0_, "destination"), n_1700_B, lightning.product.CloneCommands$J_1907_R.n_1700_B)))).then(Q_2241_p.n_1700_B("move").executes(p_198263_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198263_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198263_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198263_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198263_0_, "destination"), n_1700_B, lightning.product.CloneCommands$J_1907_R.J_1907_R)))).then(Q_2241_p.n_1700_B("normal").executes(p_198266_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198266_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198266_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198266_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198266_0_, "destination"), n_1700_B, lightning.product.CloneCommands$J_1907_R.R_4764_Y))))).then(Q_2241_p.n_1700_B("filtered").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("filter", BlockPredicateArgument.n_1700_B()).executes(p_198273_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198273_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198273_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198273_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198273_0_, "destination"), BlockPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_198273_0_, "filter"), lightning.product.CloneCommands$J_1907_R.R_4764_Y))).then(Q_2241_p.n_1700_B("force").executes(p_198267_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198267_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198267_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198267_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198267_0_, "destination"), BlockPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_198267_0_, "filter"), lightning.product.CloneCommands$J_1907_R.n_1700_B)))).then(Q_2241_p.n_1700_B("move").executes(p_198261_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198261_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198261_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198261_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198261_0_, "destination"), BlockPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_198261_0_, "filter"), lightning.product.CloneCommands$J_1907_R.J_1907_R)))).then(Q_2241_p.n_1700_B("normal").executes(p_198278_0_ -> CloneCommands.n_1700_B((y_2498_m)p_198278_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198278_0_, "begin"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198278_0_, "end"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_198278_0_, "destination"), BlockPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_198278_0_, "filter"), lightning.product.CloneCommands$J_1907_R.R_4764_Y)))))))));
    }

    private static int n_1700_B(y_2498_m source, c_1514_x beginPos, c_1514_x endPos, c_1514_x destPos, Predicate<BlockInWorld> filterPredicate, J_1907_R cloneMode) throws CommandSyntaxException {
        BoundingBox mutableboundingbox = new BoundingBox(beginPos, endPos);
        c_1514_x blockpos = destPos.add(mutableboundingbox.R_4764_Y());
        BoundingBox mutableboundingbox1 = new BoundingBox(destPos, blockpos);
        if (!cloneMode.n_1700_B() && mutableboundingbox1.n_1700_B(mutableboundingbox)) {
            throw J_1907_R.create();
        }
        int i = mutableboundingbox.G_564_y() * mutableboundingbox.P_1922_E() * mutableboundingbox.u_1723_Y();
        if (i > 32768) {
            throw R_4764_Y.create((Object)32768, (Object)i);
        }
        e_3591_l serverworld = source.h_1847_R();
        if (serverworld.n_1700_B(beginPos, endPos) && serverworld.n_1700_B(destPos, blockpos)) {
            ArrayList list = Lists.newArrayList();
            ArrayList list1 = Lists.newArrayList();
            ArrayList list2 = Lists.newArrayList();
            LinkedList deque = Lists.newLinkedList();
            c_1514_x blockpos1 = new c_1514_x(mutableboundingbox1.n_1700_B - mutableboundingbox.n_1700_B, mutableboundingbox1.J_1907_R - mutableboundingbox.J_1907_R, mutableboundingbox1.R_4764_Y - mutableboundingbox.R_4764_Y);
            for (int j = mutableboundingbox.R_4764_Y; j <= mutableboundingbox.u_1723_Y; ++j) {
                for (int k = mutableboundingbox.J_1907_R; k <= mutableboundingbox.P_1922_E; ++k) {
                    for (int l = mutableboundingbox.n_1700_B; l <= mutableboundingbox.G_564_y; ++l) {
                        c_1514_x blockpos2 = new c_1514_x(l, k, j);
                        c_1514_x blockpos3 = blockpos2.add(blockpos1);
                        BlockInWorld cachedblockinfo = new BlockInWorld(serverworld, blockpos2, false);
                        K_4074_S blockstate = cachedblockinfo.n_1700_B();
                        if (!filterPredicate.test(cachedblockinfo)) continue;
                        i_2154_H tileentity = serverworld.getTileEntity(blockpos2);
                        if (tileentity != null) {
                            U_2912_j compoundnbt = tileentity.n_1700_B(new U_2912_j());
                            list1.add(new n_1700_B(blockpos3, blockstate, compoundnbt));
                            deque.addLast(blockpos2);
                            continue;
                        }
                        if (!blockstate.t_148_a(serverworld, blockpos2) && !blockstate.multiplayerClientSuggestionProvider(serverworld, blockpos2)) {
                            list2.add(new n_1700_B(blockpos3, blockstate, null));
                            deque.addFirst(blockpos2);
                            continue;
                        }
                        list.add(new n_1700_B(blockpos3, blockstate, null));
                        deque.addLast(blockpos2);
                    }
                }
            }
            if (cloneMode == lightning.product.CloneCommands$J_1907_R.J_1907_R) {
                for (c_1514_x blockpos4 : deque) {
                    i_2154_H tileentity1 = serverworld.getTileEntity(blockpos4);
                    Clearable.n_1700_B(tileentity1);
                    serverworld.n_1700_B(blockpos4, a_3742_W.N_4890_q.multiplayerClientSuggestionProvider(), 2);
                }
                for (c_1514_x blockpos5 : deque) {
                    serverworld.n_1700_B(blockpos5, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3);
                }
            }
            ArrayList list3 = Lists.newArrayList();
            list3.addAll(list);
            list3.addAll(list1);
            list3.addAll(list2);
            List list4 = Lists.reverse((List)list3);
            for (n_1700_B clonecommand$blockinfo : list4) {
                i_2154_H tileentity2 = serverworld.getTileEntity(clonecommand$blockinfo.n_1700_B);
                Clearable.n_1700_B(tileentity2);
                serverworld.n_1700_B(clonecommand$blockinfo.n_1700_B, a_3742_W.N_4890_q.multiplayerClientSuggestionProvider(), 2);
            }
            int i1 = 0;
            for (n_1700_B clonecommand$blockinfo1 : list3) {
                if (!serverworld.n_1700_B(clonecommand$blockinfo1.n_1700_B, clonecommand$blockinfo1.J_1907_R, 2)) continue;
                ++i1;
            }
            for (n_1700_B clonecommand$blockinfo2 : list1) {
                i_2154_H tileentity3 = serverworld.getTileEntity(clonecommand$blockinfo2.n_1700_B);
                if (clonecommand$blockinfo2.R_4764_Y != null && tileentity3 != null) {
                    clonecommand$blockinfo2.R_4764_Y.J_1907_R("x", clonecommand$blockinfo2.n_1700_B.getX());
                    clonecommand$blockinfo2.R_4764_Y.J_1907_R("y", clonecommand$blockinfo2.n_1700_B.getY());
                    clonecommand$blockinfo2.R_4764_Y.J_1907_R("z", clonecommand$blockinfo2.n_1700_B.getZ());
                    tileentity3.n_1700_B(clonecommand$blockinfo2.J_1907_R, clonecommand$blockinfo2.R_4764_Y);
                    tileentity3.J_1907_R();
                }
                serverworld.n_1700_B(clonecommand$blockinfo2.n_1700_B, clonecommand$blockinfo2.J_1907_R, 2);
            }
            for (n_1700_B clonecommand$blockinfo3 : list4) {
                serverworld.n_1700_B(clonecommand$blockinfo3.n_1700_B, clonecommand$blockinfo3.J_1907_R.J_1907_R());
            }
            serverworld.Q_2552_b().n_1700_B(mutableboundingbox, blockpos1);
            if (i1 == 0) {
                throw G_564_y.create();
            }
            source.n_1700_B(new F_2904_S("commands.clone.success", i1), true);
            return i1;
        }
        throw BlockPosArgument.n_1700_B.create();
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(true);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(true);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(false);
        private final boolean G_564_y;
        private static final /* synthetic */ J_1907_R[] P_1922_E;

        public static J_1907_R[] values() {
            return (J_1907_R[])P_1922_E.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(boolean allowOverlapIn) {
            this.G_564_y = allowOverlapIn;
        }

        public boolean n_1700_B() {
            return this.G_564_y;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            P_1922_E = lightning.product.CloneCommands$J_1907_R.J_1907_R();
        }
    }

    static class n_1700_B {
        public final c_1514_x n_1700_B;
        public final K_4074_S J_1907_R;
        @Nullable
        public final U_2912_j R_4764_Y;

        public n_1700_B(c_1514_x posIn, K_4074_S stateIn, @Nullable U_2912_j tagIn) {
            this.n_1700_B = posIn;
            this.J_1907_R = stateIn;
            this.R_4764_Y = tagIn;
        }
    }
}


