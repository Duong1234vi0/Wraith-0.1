/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Keyable
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.E_4700_p;
import lightning.product.V_3137_a;
import lightning.product.Z_749_F;
import lightning.product.WeighedRandom;
import lightning.product.j_3341_s;
import lightning.product.t_5_h;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobSpawnSettings {
    public static final Logger n_1700_B = LogManager.getLogger();
    public static final MobSpawnSettings J_1907_R = new MobSpawnSettings(0.1f, (Map)Stream.of(Z_749_F.values()).collect(ImmutableMap.toImmutableMap(classification -> classification, classification -> ImmutableList.of())), (Map<t_5_h<?>, J_1907_R>)ImmutableMap.of(), false);
    public static final MapCodec<MobSpawnSettings> R_4764_Y = RecordCodecBuilder.mapCodec(builder -> builder.group((App)Codec.FLOAT.optionalFieldOf("creature_spawn_probability", (Object)Float.valueOf(0.1f)).forGetter(spawnInfo -> Float.valueOf(spawnInfo.G_564_y)), (App)Codec.simpleMap(Z_749_F.v_4262_N, (Codec)lightning.product.MobSpawnSettings$R_4764_Y.n_1700_B.listOf().promotePartial(j_3341_s.n_1700_B("Spawn data: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))), (Keyable)E_4700_p.n_1700_B(Z_749_F.values())).fieldOf("spawners").forGetter(spawnInfo -> spawnInfo.P_1922_E), (App)Codec.simpleMap(V_3137_a.g_221_o, lightning.product.MobSpawnSettings$J_1907_R.n_1700_B, V_3137_a.g_221_o).fieldOf("spawn_costs").forGetter(spawnInfo -> spawnInfo.u_1723_Y), (App)Codec.BOOL.fieldOf("player_spawn_friendly").orElse((Object)false).forGetter(MobSpawnSettings::J_1907_R)).apply((Applicative)builder, MobSpawnSettings::new));
    private final float G_564_y;
    private final Map<Z_749_F, List<R_4764_Y>> P_1922_E;
    private final Map<t_5_h<?>, J_1907_R> u_1723_Y;
    private final boolean v_4262_N;

    private MobSpawnSettings(float creatureSpawnProbability, Map<Z_749_F, List<R_4764_Y>> spawners, Map<t_5_h<?>, J_1907_R> spawnCosts, boolean isValidSpawnBiomeForPlayer) {
        this.G_564_y = creatureSpawnProbability;
        this.P_1922_E = spawners;
        this.u_1723_Y = spawnCosts;
        this.v_4262_N = isValidSpawnBiomeForPlayer;
    }

    public List<R_4764_Y> n_1700_B(Z_749_F classification) {
        return this.P_1922_E.getOrDefault(classification, (List<R_4764_Y>)ImmutableList.of());
    }

    @Nullable
    public J_1907_R n_1700_B(t_5_h<?> entityType) {
        return this.u_1723_Y.get(entityType);
    }

    public float n_1700_B() {
        return this.G_564_y;
    }

    public boolean J_1907_R() {
        return this.v_4262_N;
    }

    public static class J_1907_R {
        public static final Codec<J_1907_R> n_1700_B = RecordCodecBuilder.create(builder -> builder.group((App)Codec.DOUBLE.fieldOf("energy_budget").forGetter(spawnCosts -> spawnCosts.J_1907_R), (App)Codec.DOUBLE.fieldOf("charge").forGetter(spawnCosts -> spawnCosts.R_4764_Y)).apply((Applicative)builder, J_1907_R::new));
        private final double J_1907_R;
        private final double R_4764_Y;

        private J_1907_R(double maxSpawnCost, double entitySpawnCost) {
            this.J_1907_R = maxSpawnCost;
            this.R_4764_Y = entitySpawnCost;
        }

        public double n_1700_B() {
            return this.J_1907_R;
        }

        public double J_1907_R() {
            return this.R_4764_Y;
        }
    }

    public static class R_4764_Y
    extends WeighedRandom.n_1700_B {
        public static final Codec<R_4764_Y> n_1700_B = RecordCodecBuilder.create(builder -> builder.group((App)V_3137_a.g_221_o.fieldOf("type").forGetter(spawner -> spawner.J_1907_R), (App)Codec.INT.fieldOf("weight").forGetter(spawner -> spawner.R_4764_Y), (App)Codec.INT.fieldOf("minCount").forGetter(spawner -> spawner.G_564_y), (App)Codec.INT.fieldOf("maxCount").forGetter(spawner -> spawner.P_1922_E)).apply((Applicative)builder, R_4764_Y::new));
        public final t_5_h<?> J_1907_R;
        public final int G_564_y;
        public final int P_1922_E;

        public R_4764_Y(t_5_h<?> type, int weight, int minCount, int maxCount) {
            super(weight);
            this.J_1907_R = type.P_1922_E() == Z_749_F.u_1723_Y ? t_5_h.A_1038_p : type;
            this.G_564_y = minCount;
            this.P_1922_E = maxCount;
        }

        public String toString() {
            return String.valueOf(t_5_h.n_1700_B(this.J_1907_R)) + "*(" + this.G_564_y + "-" + this.P_1922_E + "):" + this.R_4764_Y;
        }
    }

    public static class n_1700_B {
        private final Map<Z_749_F, List<R_4764_Y>> n_1700_B = (Map)Stream.of(Z_749_F.values()).collect(ImmutableMap.toImmutableMap(classification -> classification, classification -> Lists.newArrayList()));
        private final Map<t_5_h<?>, J_1907_R> J_1907_R = Maps.newLinkedHashMap();
        private float R_4764_Y = 0.1f;
        private boolean G_564_y;

        public n_1700_B n_1700_B(Z_749_F classification, R_4764_Y spawner) {
            this.n_1700_B.get(classification).add(spawner);
            return this;
        }

        public n_1700_B n_1700_B(t_5_h<?> entityType, double spawnCostPerEntity, double maxSpawnCost) {
            this.J_1907_R.put(entityType, new J_1907_R(maxSpawnCost, spawnCostPerEntity));
            return this;
        }

        public n_1700_B n_1700_B(float probability) {
            this.R_4764_Y = probability;
            return this;
        }

        public n_1700_B n_1700_B() {
            this.G_564_y = true;
            return this;
        }

        public MobSpawnSettings J_1907_R() {
            return new MobSpawnSettings(this.R_4764_Y, (Map)this.n_1700_B.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ImmutableList.copyOf((Collection)((Collection)entry.getValue())))), (Map<t_5_h<?>, J_1907_R>)ImmutableMap.copyOf(this.J_1907_R), this.G_564_y);
        }
    }
}


