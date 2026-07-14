/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Queues
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.tree.ArgumentCommandNode
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 *  com.mojang.brigadier.tree.RootCommandNode
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntMaps
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_958_X;
import lightning.product.V_4217_p;
import lightning.product.b_2585_i;
import lightning.product.h_4126_t;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class B_3790_C
implements Packet<ClientGamePacketListener> {
    private RootCommandNode<V_4217_p> n_1700_B;

    public B_3790_C() {
    }

    public B_3790_C(RootCommandNode<V_4217_p> rootIn) {
        this.n_1700_B = rootIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        n_1700_B[] ascommandlistpacket$entry = new n_1700_B[buf.u_1723_Y()];
        for (int i = 0; i < ascommandlistpacket$entry.length; ++i) {
            ascommandlistpacket$entry[i] = B_3790_C.R_4764_Y(buf);
        }
        B_3790_C.n_1700_B(ascommandlistpacket$entry);
        this.n_1700_B = (RootCommandNode)ascommandlistpacket$entry[buf.u_1723_Y()].P_1922_E;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        Object2IntMap<CommandNode<V_4217_p>> object2intmap = B_3790_C.n_1700_B(this.n_1700_B);
        CommandNode<V_4217_p>[] commandnode = B_3790_C.n_1700_B(object2intmap);
        buf.G_564_y(commandnode.length);
        for (CommandNode<V_4217_p> commandnode1 : commandnode) {
            B_3790_C.n_1700_B(buf, commandnode1, object2intmap);
        }
        buf.G_564_y(object2intmap.get(this.n_1700_B));
    }

    private static void n_1700_B(n_1700_B[] p_244294_0_) {
        ArrayList list = Lists.newArrayList((Object[])p_244294_0_);
        while (!list.isEmpty()) {
            boolean flag = list.removeIf(p_244295_1_ -> p_244295_1_.n_1700_B(p_244294_0_));
            if (flag) continue;
            throw new IllegalStateException("Server sent an impossible command tree");
        }
    }

    private static Object2IntMap<CommandNode<V_4217_p>> n_1700_B(RootCommandNode<V_4217_p> p_244292_0_) {
        CommandNode commandnode;
        Object2IntOpenHashMap object2intmap = new Object2IntOpenHashMap();
        ArrayDeque queue = Queues.newArrayDeque();
        queue.add(p_244292_0_);
        while ((commandnode = (CommandNode)queue.poll()) != null) {
            if (object2intmap.containsKey((Object)commandnode)) continue;
            int i = object2intmap.size();
            object2intmap.put((Object)commandnode, i);
            queue.addAll(commandnode.getChildren());
            if (commandnode.getRedirect() == null) continue;
            queue.add(commandnode.getRedirect());
        }
        return object2intmap;
    }

    private static CommandNode<V_4217_p>[] n_1700_B(Object2IntMap<CommandNode<V_4217_p>> p_244293_0_) {
        CommandNode[] commandnode = new CommandNode[p_244293_0_.size()];
        for (Object2IntMap.Entry entry : Object2IntMaps.fastIterable(p_244293_0_)) {
            commandnode[entry.getIntValue()] = (CommandNode)entry.getKey();
        }
        return commandnode;
    }

    private static n_1700_B R_4764_Y(b_2585_i p_197692_0_) {
        byte b0 = p_197692_0_.readByte();
        int[] aint = p_197692_0_.J_1907_R();
        int i = (b0 & 8) != 0 ? p_197692_0_.u_1723_Y() : 0;
        ArgumentBuilder<V_4217_p, ?> argumentbuilder = B_3790_C.n_1700_B(p_197692_0_, b0);
        return new n_1700_B(argumentbuilder, b0, i, aint);
    }

    @Nullable
    private static ArgumentBuilder<V_4217_p, ?> n_1700_B(b_2585_i p_197695_0_, byte buf) {
        int i = buf & 3;
        if (i == 2) {
            String s = p_197695_0_.P_1922_E(Short.MAX_VALUE);
            ArgumentType<?> argumenttype = A_958_X.n_1700_B(p_197695_0_);
            if (argumenttype == null) {
                return null;
            }
            RequiredArgumentBuilder requiredargumentbuilder = RequiredArgumentBuilder.argument((String)s, argumenttype);
            if ((buf & 0x10) != 0) {
                requiredargumentbuilder.suggests(h_4126_t.n_1700_B(p_197695_0_.P_4830_p()));
            }
            return requiredargumentbuilder;
        }
        return i == 1 ? LiteralArgumentBuilder.literal((String)p_197695_0_.P_1922_E(Short.MAX_VALUE)) : null;
    }

    private static void n_1700_B(b_2585_i p_197696_0_, CommandNode<V_4217_p> buf, Map<CommandNode<V_4217_p>, Integer> node) {
        int b0 = 0;
        if (buf.getRedirect() != null) {
            b0 = (byte)(b0 | 8);
        }
        if (buf.getCommand() != null) {
            b0 = (byte)(b0 | 4);
        }
        if (buf instanceof RootCommandNode) {
            b0 = (byte)(b0 | 0);
        } else if (buf instanceof ArgumentCommandNode) {
            b0 = (byte)(b0 | 2);
            if (((ArgumentCommandNode)buf).getCustomSuggestions() != null) {
                b0 = (byte)(b0 | 0x10);
            }
        } else {
            if (!(buf instanceof LiteralCommandNode)) {
                throw new UnsupportedOperationException("Unknown node type " + String.valueOf(buf));
            }
            b0 = (byte)(b0 | 1);
        }
        p_197696_0_.writeByte(b0);
        p_197696_0_.G_564_y(buf.getChildren().size());
        for (CommandNode commandnode : buf.getChildren()) {
            p_197696_0_.G_564_y(node.get(commandnode));
        }
        if (buf.getRedirect() != null) {
            p_197696_0_.G_564_y(node.get(buf.getRedirect()));
        }
        if (buf instanceof ArgumentCommandNode) {
            ArgumentCommandNode argumentcommandnode = (ArgumentCommandNode)buf;
            p_197696_0_.n_1700_B(argumentcommandnode.getName());
            A_958_X.n_1700_B(p_197696_0_, argumentcommandnode.getType());
            if (argumentcommandnode.getCustomSuggestions() != null) {
                p_197696_0_.n_1700_B(h_4126_t.n_1700_B((SuggestionProvider<V_4217_p>)argumentcommandnode.getCustomSuggestions()));
            }
        } else if (buf instanceof LiteralCommandNode) {
            p_197696_0_.n_1700_B(((LiteralCommandNode)buf).getLiteral());
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public RootCommandNode<V_4217_p> J_1907_R() {
        return this.n_1700_B;
    }

    static class n_1700_B {
        @Nullable
        private final ArgumentBuilder<V_4217_p, ?> n_1700_B;
        private final byte J_1907_R;
        private final int R_4764_Y;
        private final int[] G_564_y;
        @Nullable
        private CommandNode<V_4217_p> P_1922_E;

        private n_1700_B(@Nullable ArgumentBuilder<V_4217_p, ?> argBuilderIn, byte flagsIn, int redirectTargetIn, int[] childrenIn) {
            this.n_1700_B = argBuilderIn;
            this.J_1907_R = flagsIn;
            this.R_4764_Y = redirectTargetIn;
            this.G_564_y = childrenIn;
        }

        public boolean n_1700_B(n_1700_B[] nodeArray) {
            if (this.P_1922_E == null) {
                if (this.n_1700_B == null) {
                    this.P_1922_E = new RootCommandNode();
                } else {
                    if ((this.J_1907_R & 8) != 0) {
                        if (nodeArray[this.R_4764_Y].P_1922_E == null) {
                            return false;
                        }
                        this.n_1700_B.redirect(nodeArray[this.R_4764_Y].P_1922_E);
                    }
                    if ((this.J_1907_R & 4) != 0) {
                        this.n_1700_B.executes(p_197724_0_ -> 0);
                    }
                    this.P_1922_E = this.n_1700_B.build();
                }
            }
            for (int i : this.G_564_y) {
                if (nodeArray[i].P_1922_E != null) continue;
                return false;
            }
            for (int j : this.G_564_y) {
                CommandNode<V_4217_p> commandnode = nodeArray[j].P_1922_E;
                if (commandnode instanceof RootCommandNode) continue;
                this.P_1922_E.addChild(commandnode);
            }
            return true;
        }
    }
}


