/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.serialization.DynamicLike
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.serialization.DynamicLike;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.C_1375_J;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.Q_2241_p;
import lightning.product.U_2912_j;
import lightning.product.y_2498_m;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A_2352_Z {
    private static final Logger n_3318_d = LogManager.getLogger();
    private static final Map<u_1723_Y<?>, v_4262_N<?>> d_2427_y = Maps.newTreeMap(Comparator.comparing(key -> key.n_1700_B));
    public static final u_1723_Y<n_1700_B> n_1700_B = A_2352_Z.n_1700_B("doFireTick", lightning.product.A_2352_Z$J_1907_R.P_1922_E, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> J_1907_R = A_2352_Z.n_1700_B("mobGriefing", lightning.product.A_2352_Z$J_1907_R.J_1907_R, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> R_4764_Y = A_2352_Z.n_1700_B("keepInventory", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(false));
    public static final u_1723_Y<n_1700_B> G_564_y = A_2352_Z.n_1700_B("doMobSpawning", lightning.product.A_2352_Z$J_1907_R.R_4764_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> P_1922_E = A_2352_Z.n_1700_B("doMobLoot", lightning.product.A_2352_Z$J_1907_R.G_564_y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> u_1723_Y = A_2352_Z.n_1700_B("doTileDrops", lightning.product.A_2352_Z$J_1907_R.G_564_y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> v_4262_N = A_2352_Z.n_1700_B("doEntityDrops", lightning.product.A_2352_Z$J_1907_R.G_564_y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> w_1484_f = A_2352_Z.n_1700_B("commandBlockOutput", lightning.product.A_2352_Z$J_1907_R.u_1723_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> t_148_a = A_2352_Z.n_1700_B("naturalRegeneration", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> s_956_w = A_2352_Z.n_1700_B("doDaylightCycle", lightning.product.A_2352_Z$J_1907_R.P_1922_E, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> u_2550_I = A_2352_Z.n_1700_B("logAdminCommands", lightning.product.A_2352_Z$J_1907_R.u_1723_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> M_588_G = A_2352_Z.n_1700_B("showDeathMessages", lightning.product.A_2352_Z$J_1907_R.u_1723_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<P_1922_E> P_4830_p = A_2352_Z.n_1700_B("randomTickSpeed", lightning.product.A_2352_Z$J_1907_R.P_1922_E, lightning.product.A_2352_Z$P_1922_E.n_1700_B(3));
    public static final u_1723_Y<n_1700_B> h_1847_R = A_2352_Z.n_1700_B("sendCommandFeedback", lightning.product.A_2352_Z$J_1907_R.u_1723_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> Q_4569_t = A_2352_Z.n_1700_B("reducedDebugInfo", lightning.product.A_2352_Z$J_1907_R.v_4262_N, lightning.product.A_2352_Z$n_1700_B.n_1700_B(false, (net.minecraft.server.G_564_y server, n_1700_B value) -> {
        byte b0 = (byte)(value.n_1700_B() ? 22 : 23);
        for (B_4088_l serverplayerentity : server.p_178_J().w_1457_N()) {
            serverplayerentity.n_1700_B.n_1700_B(new C_1375_J(serverplayerentity, b0));
        }
    }));
    public static final u_1723_Y<n_1700_B> M_182_A = A_2352_Z.n_1700_B("spectatorsGenerateChunks", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<P_1922_E> t_1786_h = A_2352_Z.n_1700_B("spawnRadius", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$P_1922_E.n_1700_B(10));
    public static final u_1723_Y<n_1700_B> multiplayerClientSuggestionProvider = A_2352_Z.n_1700_B("disableElytraMovementCheck", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(false));
    public static final u_1723_Y<P_1922_E> w_1457_N = A_2352_Z.n_1700_B("maxEntityCramming", lightning.product.A_2352_Z$J_1907_R.J_1907_R, lightning.product.A_2352_Z$P_1922_E.n_1700_B(24));
    public static final u_1723_Y<n_1700_B> Y_601_j = A_2352_Z.n_1700_B("doWeatherCycle", lightning.product.A_2352_Z$J_1907_R.P_1922_E, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> Y_259_p = A_2352_Z.n_1700_B("doLimitedCrafting", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(false));
    public static final u_1723_Y<P_1922_E> Q_2552_b = A_2352_Z.n_1700_B("maxCommandChainLength", lightning.product.A_2352_Z$J_1907_R.v_4262_N, lightning.product.A_2352_Z$P_1922_E.n_1700_B(65536));
    public static final u_1723_Y<n_1700_B> C_2741_M = A_2352_Z.n_1700_B("announceAdvancements", lightning.product.A_2352_Z$J_1907_R.u_1723_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> k_2293_S = A_2352_Z.n_1700_B("disableRaids", lightning.product.A_2352_Z$J_1907_R.J_1907_R, lightning.product.A_2352_Z$n_1700_B.n_1700_B(false));
    public static final u_1723_Y<n_1700_B> q_2307_F = A_2352_Z.n_1700_B("doInsomnia", lightning.product.A_2352_Z$J_1907_R.R_4764_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> Z_875_P = A_2352_Z.n_1700_B("doImmediateRespawn", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(false, (net.minecraft.server.G_564_y server, n_1700_B value) -> {
        for (B_4088_l serverplayerentity : server.p_178_J().w_1457_N()) {
            serverplayerentity.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.M_588_G, value.n_1700_B() ? 1.0f : 0.0f));
        }
    }));
    public static final u_1723_Y<n_1700_B> c_3005_b = A_2352_Z.n_1700_B("drowningDamage", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> H_2857_Y = A_2352_Z.n_1700_B("fallDamage", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> A_4115_X = A_2352_Z.n_1700_B("fireDamage", lightning.product.A_2352_Z$J_1907_R.n_1700_B, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> Y_1740_V = A_2352_Z.n_1700_B("doPatrolSpawning", lightning.product.A_2352_Z$J_1907_R.R_4764_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> t_4043_B = A_2352_Z.n_1700_B("doTraderSpawning", lightning.product.A_2352_Z$J_1907_R.R_4764_Y, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> x_607_J = A_2352_Z.n_1700_B("forgiveDeadPlayers", lightning.product.A_2352_Z$J_1907_R.J_1907_R, lightning.product.A_2352_Z$n_1700_B.n_1700_B(true));
    public static final u_1723_Y<n_1700_B> e_4240_b = A_2352_Z.n_1700_B("universalAnger", lightning.product.A_2352_Z$J_1907_R.J_1907_R, lightning.product.A_2352_Z$n_1700_B.n_1700_B(false));
    private final Map<u_1723_Y<?>, w_1484_f<?>> z_1737_N;

    private static <T extends w_1484_f<T>> u_1723_Y<T> n_1700_B(String name, J_1907_R category, v_4262_N<T> type) {
        u_1723_Y rulekey = new u_1723_Y(name, category);
        v_4262_N<T> ruletype = d_2427_y.put(rulekey, type);
        if (ruletype != null) {
            throw new IllegalStateException("Duplicate game rule registration for " + name);
        }
        return rulekey;
    }

    public A_2352_Z(DynamicLike<?> dynamic) {
        this();
        this.n_1700_B(dynamic);
    }

    public A_2352_Z() {
        this.z_1737_N = (Map)d_2427_y.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ((v_4262_N)entry.getValue()).n_1700_B()));
    }

    private A_2352_Z(Map<u_1723_Y<?>, w_1484_f<?>> keyToValueMap) {
        this.z_1737_N = keyToValueMap;
    }

    public <T extends w_1484_f<T>> T n_1700_B(u_1723_Y<T> key) {
        return (T)this.z_1737_N.get(key);
    }

    public U_2912_j n_1700_B() {
        U_2912_j compoundnbt = new U_2912_j();
        this.z_1737_N.forEach((key, value) -> compoundnbt.n_1700_B(key.n_1700_B, value.J_1907_R()));
        return compoundnbt;
    }

    private void n_1700_B(DynamicLike<?> dynamic) {
        this.z_1737_N.forEach((key, value) -> dynamic.get(key.n_1700_B).asString().result().ifPresent(value::n_1700_B));
    }

    public A_2352_Z J_1907_R() {
        return new A_2352_Z((Map)this.z_1737_N.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ((w_1484_f)entry.getValue()).u_1723_Y())));
    }

    public static void n_1700_B(G_564_y visitor) {
        d_2427_y.forEach((key, type) -> A_2352_Z.n_1700_B(visitor, key, type));
    }

    private static <T extends w_1484_f<T>> void n_1700_B(G_564_y visitor, u_1723_Y<?> key, v_4262_N<?> type) {
        visitor.R_4764_Y(key, type);
        type.n_1700_B(visitor, key);
    }

    public void n_1700_B(A_2352_Z rules, @Nullable net.minecraft.server.G_564_y server) {
        rules.z_1737_N.keySet().forEach(key -> this.n_1700_B((u_1723_Y)key, rules, server));
    }

    private <T extends w_1484_f<T>> void n_1700_B(u_1723_Y<T> key, A_2352_Z rules, @Nullable net.minecraft.server.G_564_y server) {
        T t = rules.n_1700_B(key);
        ((w_1484_f)this.n_1700_B(key)).n_1700_B(t, server);
    }

    public boolean J_1907_R(u_1723_Y<n_1700_B> key) {
        return this.n_1700_B(key).n_1700_B();
    }

    public int R_4764_Y(u_1723_Y<P_1922_E> key) {
        return this.n_1700_B(key).n_1700_B();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return this.J_1907_R();
    }

    public static final class u_1723_Y<T extends w_1484_f<T>> {
        private final String n_1700_B;
        private final J_1907_R J_1907_R;

        public u_1723_Y(String gameRuleName, J_1907_R category) {
            this.n_1700_B = gameRuleName;
            this.J_1907_R = category;
        }

        public String toString() {
            return this.n_1700_B;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            return p_equals_1_ instanceof u_1723_Y && ((u_1723_Y)p_equals_1_).n_1700_B.equals(this.n_1700_B);
        }

        public int hashCode() {
            return this.n_1700_B.hashCode();
        }

        public String n_1700_B() {
            return this.n_1700_B;
        }

        public String J_1907_R() {
            return "gamerule." + this.n_1700_B;
        }

        public J_1907_R R_4764_Y() {
            return this.J_1907_R;
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("gamerule.category.player");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("gamerule.category.mobs");
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("gamerule.category.spawning");
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R("gamerule.category.drops");
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R("gamerule.category.updates");
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R("gamerule.category.chat");
        public static final /* enum */ J_1907_R v_4262_N = new J_1907_R("gamerule.category.misc");
        private final String w_1484_f;
        private static final /* synthetic */ J_1907_R[] t_148_a;

        public static J_1907_R[] values() {
            return (J_1907_R[])t_148_a.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String localeString) {
            this.w_1484_f = localeString;
        }

        public String n_1700_B() {
            return this.w_1484_f;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            t_148_a = lightning.product.A_2352_Z$J_1907_R.J_1907_R();
        }
    }

    public static class v_4262_N<T extends w_1484_f<T>> {
        private final Supplier<ArgumentType<?>> n_1700_B;
        private final Function<v_4262_N<T>, T> J_1907_R;
        private final BiConsumer<net.minecraft.server.G_564_y, T> R_4764_Y;
        private final R_4764_Y<T> G_564_y;

        private v_4262_N(Supplier<ArgumentType<?>> argTypeSupplier, Function<v_4262_N<T>, T> valueCreator, BiConsumer<net.minecraft.server.G_564_y, T> changeListener, R_4764_Y<T> rule) {
            this.n_1700_B = argTypeSupplier;
            this.J_1907_R = valueCreator;
            this.R_4764_Y = changeListener;
            this.G_564_y = rule;
        }

        public RequiredArgumentBuilder<y_2498_m, ?> n_1700_B(String name) {
            return Q_2241_p.n_1700_B(name, this.n_1700_B.get());
        }

        public T n_1700_B() {
            return (T)((w_1484_f)this.J_1907_R.apply(this));
        }

        public void n_1700_B(G_564_y visitor, u_1723_Y<T> key) {
            this.G_564_y.call(visitor, key, this);
        }
    }

    public static abstract class w_1484_f<T extends w_1484_f<T>> {
        protected final v_4262_N<T> n_1700_B;

        public w_1484_f(v_4262_N<T> type) {
            this.n_1700_B = type;
        }

        protected abstract void n_1700_B(CommandContext<y_2498_m> var1, String var2);

        public void J_1907_R(CommandContext<y_2498_m> context, String paramName) {
            this.n_1700_B(context, paramName);
            this.n_1700_B(((y_2498_m)context.getSource()).w_1457_N());
        }

        protected void n_1700_B(@Nullable net.minecraft.server.G_564_y server) {
            if (server != null) {
                this.n_1700_B.R_4764_Y.accept(server, (net.minecraft.server.G_564_y)this.v_4262_N());
            }
        }

        protected abstract void n_1700_B(String var1);

        public abstract String J_1907_R();

        public String toString() {
            return this.J_1907_R();
        }

        public abstract int R_4764_Y();

        protected abstract T v_4262_N();

        protected abstract T u_1723_Y();

        public abstract void n_1700_B(T var1, @Nullable net.minecraft.server.G_564_y var2);

        protected /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.u_1723_Y();
        }
    }

    public static interface G_564_y {
        default public <T extends w_1484_f<T>> void R_4764_Y(u_1723_Y<T> key, v_4262_N<T> type) {
        }

        default public void n_1700_B(u_1723_Y<n_1700_B> value1, v_4262_N<n_1700_B> value2) {
        }

        default public void J_1907_R(u_1723_Y<P_1922_E> value1, v_4262_N<P_1922_E> value2) {
        }
    }

    public static class n_1700_B
    extends w_1484_f<n_1700_B> {
        private boolean J_1907_R;

        private static v_4262_N<n_1700_B> n_1700_B(boolean defaultValue, BiConsumer<net.minecraft.server.G_564_y, n_1700_B> changeListener) {
            return new v_4262_N<n_1700_B>(BoolArgumentType::bool, type -> new n_1700_B((v_4262_N<n_1700_B>)type, defaultValue), changeListener, G_564_y::n_1700_B);
        }

        private static v_4262_N<n_1700_B> n_1700_B(boolean defaultValue) {
            return lightning.product.A_2352_Z$n_1700_B.n_1700_B(defaultValue, (net.minecraft.server.G_564_y server, n_1700_B value) -> {});
        }

        public n_1700_B(v_4262_N<n_1700_B> type, boolean defaultValue) {
            super(type);
            this.J_1907_R = defaultValue;
        }

        @Override
        protected void n_1700_B(CommandContext<y_2498_m> context, String paramName) {
            this.J_1907_R = BoolArgumentType.getBool(context, (String)paramName);
        }

        public boolean n_1700_B() {
            return this.J_1907_R;
        }

        @Override
        public void n_1700_B(boolean valueIn, @Nullable net.minecraft.server.G_564_y server) {
            this.J_1907_R = valueIn;
            this.n_1700_B(server);
        }

        @Override
        public String J_1907_R() {
            return Boolean.toString(this.J_1907_R);
        }

        @Override
        protected void n_1700_B(String valueIn) {
            this.J_1907_R = Boolean.parseBoolean(valueIn);
        }

        @Override
        public int R_4764_Y() {
            return this.J_1907_R ? 1 : 0;
        }

        protected n_1700_B G_564_y() {
            return this;
        }

        protected n_1700_B P_1922_E() {
            return new n_1700_B(this.n_1700_B, this.J_1907_R);
        }

        @Override
        public void n_1700_B(n_1700_B value, @Nullable net.minecraft.server.G_564_y server) {
            this.J_1907_R = value.J_1907_R;
            this.n_1700_B(server);
        }

        @Override
        protected /* synthetic */ w_1484_f u_1723_Y() {
            return this.P_1922_E();
        }

        @Override
        protected /* synthetic */ w_1484_f v_4262_N() {
            return this.G_564_y();
        }

        @Override
        protected /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.P_1922_E();
        }
    }

    public static class P_1922_E
    extends w_1484_f<P_1922_E> {
        private int J_1907_R;

        private static v_4262_N<P_1922_E> n_1700_B(int defaultValue, BiConsumer<net.minecraft.server.G_564_y, P_1922_E> changeListener) {
            return new v_4262_N<P_1922_E>(IntegerArgumentType::integer, type -> new P_1922_E((v_4262_N<P_1922_E>)type, defaultValue), changeListener, G_564_y::J_1907_R);
        }

        private static v_4262_N<P_1922_E> n_1700_B(int defaultValue) {
            return lightning.product.A_2352_Z$P_1922_E.n_1700_B(defaultValue, (net.minecraft.server.G_564_y server, P_1922_E value) -> {});
        }

        public P_1922_E(v_4262_N<P_1922_E> type, int defaultValue) {
            super(type);
            this.J_1907_R = defaultValue;
        }

        @Override
        protected void n_1700_B(CommandContext<y_2498_m> context, String paramName) {
            this.J_1907_R = IntegerArgumentType.getInteger(context, (String)paramName);
        }

        public int n_1700_B() {
            return this.J_1907_R;
        }

        @Override
        public String J_1907_R() {
            return Integer.toString(this.J_1907_R);
        }

        @Override
        protected void n_1700_B(String valueIn) {
            this.J_1907_R = lightning.product.A_2352_Z$P_1922_E.R_4764_Y(valueIn);
        }

        public boolean J_1907_R(String name) {
            try {
                this.J_1907_R = Integer.parseInt(name);
                return true;
            }
            catch (NumberFormatException numberformatexception) {
                return false;
            }
        }

        private static int R_4764_Y(String strValue) {
            if (!strValue.isEmpty()) {
                try {
                    return Integer.parseInt(strValue);
                }
                catch (NumberFormatException numberformatexception) {
                    n_3318_d.warn("Failed to parse integer {}", (Object)strValue);
                }
            }
            return 0;
        }

        @Override
        public int R_4764_Y() {
            return this.J_1907_R;
        }

        protected P_1922_E G_564_y() {
            return this;
        }

        protected P_1922_E P_1922_E() {
            return new P_1922_E(this.n_1700_B, this.J_1907_R);
        }

        @Override
        public void n_1700_B(P_1922_E value, @Nullable net.minecraft.server.G_564_y server) {
            this.J_1907_R = value.J_1907_R;
            this.n_1700_B(server);
        }

        @Override
        protected /* synthetic */ w_1484_f u_1723_Y() {
            return this.P_1922_E();
        }

        @Override
        protected /* synthetic */ w_1484_f v_4262_N() {
            return this.G_564_y();
        }

        @Override
        protected /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.P_1922_E();
        }
    }

    static interface R_4764_Y<T extends w_1484_f<T>> {
        public void call(G_564_y var1, u_1723_Y<T> var2, v_4262_N<T> var3);
    }
}


