/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.tree.ArgumentCommandNode
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 *  com.mojang.brigadier.tree.RootCommandNode
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.ColorArgument;
import lightning.product.C_131_O;
import lightning.product.BlockPredicateArgument;
import lightning.product.EmptyArgumentSerializer;
import lightning.product.SharedConstants;
import lightning.product.K_1178_t;
import lightning.product.RangeArgument;
import lightning.product.Q_2106_h;
import lightning.product.RotationArgument;
import lightning.product.R_4849_J;
import lightning.product.ScoreboardSlotArgument;
import lightning.product.CompoundTagArgument;
import lightning.product.ObjectiveCriteriaArgument;
import lightning.product.X_4512_s;
import lightning.product.TimeArgument;
import lightning.product.SwizzleArgument;
import lightning.product.ItemEnchantmentArgument;
import lightning.product.b_2585_i;
import lightning.product.c_853_z;
import lightning.product.d_4673_Y;
import lightning.product.ParticleArgument;
import lightning.product.g_168_b;
import lightning.product.g_2336_b;
import lightning.product.BrigadierArgumentSerializers;
import lightning.product.ComponentArgument;
import lightning.product.Vec2Argument;
import lightning.product.ObjectiveArgument;
import lightning.product.i_4556_r;
import lightning.product.j_284_m;
import lightning.product.ResourceLocationArgument;
import lightning.product.BlockPosArgument;
import lightning.product.MessageArgument;
import lightning.product.MobEffectArgument;
import lightning.product.NbtTagArgument;
import lightning.product.ArgumentSerializer;
import lightning.product.UuidArgument;
import lightning.product.ItemPredicateArgument;
import lightning.product.EntitySummonArgument;
import lightning.product.r_2127_N;
import lightning.product.BlockStateArgument;
import lightning.product.ColumnPosArgument;
import lightning.product.u_1579_Y;
import lightning.product.GameProfileArgument;
import lightning.product.ItemArgument;
import lightning.product.EntityAnchorArgument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A_958_X {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Map<Class<?>, n_1700_B<?>> J_1907_R = Maps.newHashMap();
    private static final Map<g_2336_b, n_1700_B<?>> R_4764_Y = Maps.newHashMap();

    public static <T extends ArgumentType<?>> void n_1700_B(String p_218136_0_, Class<T> p_218136_1_, ArgumentSerializer<T> p_218136_2_) {
        g_2336_b resourcelocation = new g_2336_b(p_218136_0_);
        if (J_1907_R.containsKey(p_218136_1_)) {
            throw new IllegalArgumentException("Class " + p_218136_1_.getName() + " already has a serializer!");
        }
        if (R_4764_Y.containsKey(resourcelocation)) {
            throw new IllegalArgumentException("'" + String.valueOf(resourcelocation) + "' is already a registered serializer!");
        }
        n_1700_B<T> entry = new n_1700_B<T>(p_218136_1_, p_218136_2_, resourcelocation);
        J_1907_R.put(p_218136_1_, entry);
        R_4764_Y.put(resourcelocation, entry);
    }

    public static void n_1700_B() {
        BrigadierArgumentSerializers.n_1700_B();
        A_958_X.n_1700_B("entity", i_4556_r.class, new i_4556_r.n_1700_B());
        A_958_X.n_1700_B("game_profile", GameProfileArgument.class, new EmptyArgumentSerializer<GameProfileArgument>(GameProfileArgument::n_1700_B));
        A_958_X.n_1700_B("block_pos", BlockPosArgument.class, new EmptyArgumentSerializer<BlockPosArgument>(BlockPosArgument::n_1700_B));
        A_958_X.n_1700_B("column_pos", ColumnPosArgument.class, new EmptyArgumentSerializer<ColumnPosArgument>(ColumnPosArgument::n_1700_B));
        A_958_X.n_1700_B("vec3", u_1579_Y.class, new EmptyArgumentSerializer<u_1579_Y>(u_1579_Y::n_1700_B));
        A_958_X.n_1700_B("vec2", Vec2Argument.class, new EmptyArgumentSerializer<Vec2Argument>(Vec2Argument::n_1700_B));
        A_958_X.n_1700_B("block_state", BlockStateArgument.class, new EmptyArgumentSerializer<BlockStateArgument>(BlockStateArgument::n_1700_B));
        A_958_X.n_1700_B("block_predicate", BlockPredicateArgument.class, new EmptyArgumentSerializer<BlockPredicateArgument>(BlockPredicateArgument::n_1700_B));
        A_958_X.n_1700_B("item_stack", ItemArgument.class, new EmptyArgumentSerializer<ItemArgument>(ItemArgument::n_1700_B));
        A_958_X.n_1700_B("item_predicate", ItemPredicateArgument.class, new EmptyArgumentSerializer<ItemPredicateArgument>(ItemPredicateArgument::n_1700_B));
        A_958_X.n_1700_B("color", ColorArgument.class, new EmptyArgumentSerializer<ColorArgument>(ColorArgument::n_1700_B));
        A_958_X.n_1700_B("component", ComponentArgument.class, new EmptyArgumentSerializer<ComponentArgument>(ComponentArgument::n_1700_B));
        A_958_X.n_1700_B("message", MessageArgument.class, new EmptyArgumentSerializer<MessageArgument>(MessageArgument::n_1700_B));
        A_958_X.n_1700_B("nbt_compound_tag", CompoundTagArgument.class, new EmptyArgumentSerializer<CompoundTagArgument>(CompoundTagArgument::n_1700_B));
        A_958_X.n_1700_B("nbt_tag", NbtTagArgument.class, new EmptyArgumentSerializer<NbtTagArgument>(NbtTagArgument::n_1700_B));
        A_958_X.n_1700_B("nbt_path", K_1178_t.class, new EmptyArgumentSerializer<K_1178_t>(K_1178_t::n_1700_B));
        A_958_X.n_1700_B("objective", ObjectiveArgument.class, new EmptyArgumentSerializer<ObjectiveArgument>(ObjectiveArgument::n_1700_B));
        A_958_X.n_1700_B("objective_criteria", ObjectiveCriteriaArgument.class, new EmptyArgumentSerializer<ObjectiveCriteriaArgument>(ObjectiveCriteriaArgument::n_1700_B));
        A_958_X.n_1700_B("operation", j_284_m.class, new EmptyArgumentSerializer<j_284_m>(j_284_m::n_1700_B));
        A_958_X.n_1700_B("particle", ParticleArgument.class, new EmptyArgumentSerializer<ParticleArgument>(ParticleArgument::n_1700_B));
        A_958_X.n_1700_B("angle", d_4673_Y.class, new EmptyArgumentSerializer<d_4673_Y>(d_4673_Y::n_1700_B));
        A_958_X.n_1700_B("rotation", RotationArgument.class, new EmptyArgumentSerializer<RotationArgument>(RotationArgument::n_1700_B));
        A_958_X.n_1700_B("scoreboard_slot", ScoreboardSlotArgument.class, new EmptyArgumentSerializer<ScoreboardSlotArgument>(ScoreboardSlotArgument::n_1700_B));
        A_958_X.n_1700_B("score_holder", C_131_O.class, new C_131_O.R_4764_Y());
        A_958_X.n_1700_B("swizzle", SwizzleArgument.class, new EmptyArgumentSerializer<SwizzleArgument>(SwizzleArgument::n_1700_B));
        A_958_X.n_1700_B("team", Q_2106_h.class, new EmptyArgumentSerializer<Q_2106_h>(Q_2106_h::n_1700_B));
        A_958_X.n_1700_B("item_slot", X_4512_s.class, new EmptyArgumentSerializer<X_4512_s>(X_4512_s::n_1700_B));
        A_958_X.n_1700_B("resource_location", ResourceLocationArgument.class, new EmptyArgumentSerializer<ResourceLocationArgument>(ResourceLocationArgument::n_1700_B));
        A_958_X.n_1700_B("mob_effect", MobEffectArgument.class, new EmptyArgumentSerializer<MobEffectArgument>(MobEffectArgument::n_1700_B));
        A_958_X.n_1700_B("function", c_853_z.class, new EmptyArgumentSerializer<c_853_z>(c_853_z::n_1700_B));
        A_958_X.n_1700_B("entity_anchor", EntityAnchorArgument.class, new EmptyArgumentSerializer<EntityAnchorArgument>(EntityAnchorArgument::n_1700_B));
        A_958_X.n_1700_B("int_range", RangeArgument.J_1907_R.class, new EmptyArgumentSerializer<RangeArgument.J_1907_R>(RangeArgument::n_1700_B));
        A_958_X.n_1700_B("float_range", RangeArgument.n_1700_B.class, new EmptyArgumentSerializer<RangeArgument.n_1700_B>(RangeArgument::J_1907_R));
        A_958_X.n_1700_B("item_enchantment", ItemEnchantmentArgument.class, new EmptyArgumentSerializer<ItemEnchantmentArgument>(ItemEnchantmentArgument::n_1700_B));
        A_958_X.n_1700_B("entity_summon", EntitySummonArgument.class, new EmptyArgumentSerializer<EntitySummonArgument>(EntitySummonArgument::n_1700_B));
        A_958_X.n_1700_B("dimension", g_168_b.class, new EmptyArgumentSerializer<g_168_b>(g_168_b::n_1700_B));
        A_958_X.n_1700_B("time", TimeArgument.class, new EmptyArgumentSerializer<TimeArgument>(TimeArgument::n_1700_B));
        A_958_X.n_1700_B("uuid", UuidArgument.class, new EmptyArgumentSerializer<UuidArgument>(UuidArgument::n_1700_B));
        if (SharedConstants.G_564_y) {
            A_958_X.n_1700_B("test_argument", R_4849_J.class, new EmptyArgumentSerializer<R_4849_J>(R_4849_J::n_1700_B));
            A_958_X.n_1700_B("test_class", r_2127_N.class, new EmptyArgumentSerializer<r_2127_N>(r_2127_N::n_1700_B));
        }
    }

    @Nullable
    private static n_1700_B<?> n_1700_B(g_2336_b id) {
        return R_4764_Y.get(id);
    }

    @Nullable
    private static n_1700_B<?> J_1907_R(ArgumentType<?> type) {
        return J_1907_R.get(type.getClass());
    }

    public static <T extends ArgumentType<?>> void n_1700_B(b_2585_i buffer, T type) {
        n_1700_B<?> entry = A_958_X.J_1907_R(type);
        if (entry == null) {
            n_1700_B.error("Could not serialize {} ({}) - will not be sent to client!", type, (Object)type.getClass());
            buffer.n_1700_B(new g_2336_b(""));
        } else {
            buffer.n_1700_B(entry.R_4764_Y);
            entry.J_1907_R.n_1700_B(type, buffer);
        }
    }

    @Nullable
    public static ArgumentType<?> n_1700_B(b_2585_i buffer) {
        g_2336_b resourcelocation = buffer.P_4830_p();
        n_1700_B<?> entry = A_958_X.n_1700_B(resourcelocation);
        if (entry == null) {
            n_1700_B.error("Could not deserialize {}", (Object)resourcelocation);
            return null;
        }
        return entry.J_1907_R.n_1700_B(buffer);
    }

    private static <T extends ArgumentType<?>> void n_1700_B(JsonObject json, T type) {
        n_1700_B<?> entry = A_958_X.J_1907_R(type);
        if (entry == null) {
            n_1700_B.error("Could not serialize argument {} ({})!", type, (Object)type.getClass());
            json.addProperty("type", "unknown");
        } else {
            json.addProperty("type", "argument");
            json.addProperty("parser", entry.R_4764_Y.toString());
            JsonObject jsonobject = new JsonObject();
            entry.J_1907_R.n_1700_B(type, jsonobject);
            if (jsonobject.size() > 0) {
                json.add("properties", (JsonElement)jsonobject);
            }
        }
    }

    public static <S> JsonObject n_1700_B(CommandDispatcher<S> dispatcher, CommandNode<S> node) {
        Collection collection;
        JsonObject jsonobject = new JsonObject();
        if (node instanceof RootCommandNode) {
            jsonobject.addProperty("type", "root");
        } else if (node instanceof LiteralCommandNode) {
            jsonobject.addProperty("type", "literal");
        } else if (node instanceof ArgumentCommandNode) {
            A_958_X.n_1700_B(jsonobject, ((ArgumentCommandNode)node).getType());
        } else {
            n_1700_B.error("Could not serialize node {} ({})!", node, node.getClass());
            jsonobject.addProperty("type", "unknown");
        }
        JsonObject jsonobject1 = new JsonObject();
        for (CommandNode commandnode : node.getChildren()) {
            jsonobject1.add(commandnode.getName(), (JsonElement)A_958_X.n_1700_B(dispatcher, commandnode));
        }
        if (jsonobject1.size() > 0) {
            jsonobject.add("children", (JsonElement)jsonobject1);
        }
        if (node.getCommand() != null) {
            jsonobject.addProperty("executable", Boolean.valueOf(true));
        }
        if (node.getRedirect() != null && !(collection = dispatcher.getPath(node.getRedirect())).isEmpty()) {
            JsonArray jsonarray = new JsonArray();
            for (String s : collection) {
                jsonarray.add(s);
            }
            jsonobject.add("redirect", (JsonElement)jsonarray);
        }
        return jsonobject;
    }

    public static boolean n_1700_B(ArgumentType<?> p_243510_0_) {
        return A_958_X.J_1907_R(p_243510_0_) != null;
    }

    public static <T> Set<ArgumentType<?>> n_1700_B(CommandNode<T> p_243511_0_) {
        Set set = Sets.newIdentityHashSet();
        HashSet set1 = Sets.newHashSet();
        A_958_X.n_1700_B(p_243511_0_, set1, set);
        return set1;
    }

    private static <T> void n_1700_B(CommandNode<T> p_243512_0_, Set<ArgumentType<?>> p_243512_1_, Set<CommandNode<T>> p_243512_2_) {
        if (p_243512_2_.add(p_243512_0_)) {
            if (p_243512_0_ instanceof ArgumentCommandNode) {
                p_243512_1_.add(((ArgumentCommandNode)p_243512_0_).getType());
            }
            p_243512_0_.getChildren().forEach(p_243513_2_ -> A_958_X.n_1700_B(p_243513_2_, p_243512_1_, p_243512_2_));
            CommandNode commandnode = p_243512_0_.getRedirect();
            if (commandnode != null) {
                A_958_X.n_1700_B(commandnode, p_243512_1_, p_243512_2_);
            }
        }
    }

    static class n_1700_B<T extends ArgumentType<?>> {
        public final Class<T> n_1700_B;
        public final ArgumentSerializer<T> J_1907_R;
        public final g_2336_b R_4764_Y;

        private n_1700_B(Class<T> argumentClassIn, ArgumentSerializer<T> serializerIn, g_2336_b idIn) {
            this.n_1700_B = argumentClassIn;
            this.J_1907_R = serializerIn;
            this.R_4764_Y = idIn;
        }
    }
}


