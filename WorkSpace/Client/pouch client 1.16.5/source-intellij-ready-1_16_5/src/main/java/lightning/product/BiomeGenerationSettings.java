/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Keyable
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lightning.product.E_4700_p;
import lightning.product.StructureFeature;
import lightning.product.K_4573_Z;
import lightning.product.ConfiguredWorldCarver;
import lightning.product.T_3975_o;
import lightning.product.Z_927_M;
import lightning.product.ConfiguredFeature;
import lightning.product.ConfiguredSurfaceBuilder;
import lightning.product.Feature;
import lightning.product.j_3341_s;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.SurfaceBuilders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BiomeGenerationSettings {
    public static final Logger n_1700_B = LogManager.getLogger();
    public static final BiomeGenerationSettings J_1907_R = new BiomeGenerationSettings(() -> SurfaceBuilders.M_182_A, (Map<T_3975_o.n_1700_B, List<Supplier<ConfiguredWorldCarver<?>>>>)ImmutableMap.of(), (List<List<Supplier<ConfiguredFeature<?, ?>>>>)ImmutableList.of(), (List<Supplier<ConfiguredStructureFeature<?, ?>>>)ImmutableList.of());
    public static final MapCodec<BiomeGenerationSettings> R_4764_Y = RecordCodecBuilder.mapCodec(builder -> builder.group((App)ConfiguredSurfaceBuilder.J_1907_R.fieldOf("surface_builder").forGetter(settings -> settings.G_564_y), (App)Codec.simpleMap(T_3975_o.n_1700_B.R_4764_Y, (Codec)ConfiguredWorldCarver.R_4764_Y.promotePartial(j_3341_s.n_1700_B("Carver: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))), (Keyable)E_4700_p.n_1700_B(T_3975_o.n_1700_B.values())).fieldOf("carvers").forGetter(settings -> settings.P_1922_E), (App)ConfiguredFeature.R_4764_Y.promotePartial(j_3341_s.n_1700_B("Feature: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))).listOf().fieldOf("features").forGetter(settings -> settings.u_1723_Y), (App)ConfiguredStructureFeature.R_4764_Y.promotePartial(j_3341_s.n_1700_B("Structure start: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))).fieldOf("starts").forGetter(settings -> settings.v_4262_N)).apply((Applicative)builder, BiomeGenerationSettings::new));
    private final Supplier<ConfiguredSurfaceBuilder<?>> G_564_y;
    private final Map<T_3975_o.n_1700_B, List<Supplier<ConfiguredWorldCarver<?>>>> P_1922_E;
    private final List<List<Supplier<ConfiguredFeature<?, ?>>>> u_1723_Y;
    private final List<Supplier<ConfiguredStructureFeature<?, ?>>> v_4262_N;
    private final List<ConfiguredFeature<?, ?>> w_1484_f;

    private BiomeGenerationSettings(Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, Map<T_3975_o.n_1700_B, List<Supplier<ConfiguredWorldCarver<?>>>> carvers, List<List<Supplier<ConfiguredFeature<?, ?>>>> features, List<Supplier<ConfiguredStructureFeature<?, ?>>> structures) {
        this.G_564_y = surfaceBuilder;
        this.P_1922_E = carvers;
        this.u_1723_Y = features;
        this.v_4262_N = structures;
        this.w_1484_f = (List)features.stream().flatMap(Collection::stream).map(Supplier::get).flatMap(ConfiguredFeature::G_564_y).filter(configuredFeature -> configuredFeature.P_1922_E == Feature.G_564_y).collect(ImmutableList.toImmutableList());
    }

    public List<Supplier<ConfiguredWorldCarver<?>>> n_1700_B(T_3975_o.n_1700_B carvingType) {
        return (List)this.P_1922_E.getOrDefault(carvingType, (List<Supplier<ConfiguredWorldCarver<?>>>)ImmutableList.of());
    }

    public boolean n_1700_B(StructureFeature<?> structure) {
        return this.v_4262_N.stream().anyMatch(structureIn -> ((ConfiguredStructureFeature)structureIn.get()).G_564_y == structure);
    }

    public Collection<Supplier<ConfiguredStructureFeature<?, ?>>> n_1700_B() {
        return this.v_4262_N;
    }

    public ConfiguredStructureFeature<?, ?> n_1700_B(ConfiguredStructureFeature<?, ?> structure) {
        return (ConfiguredStructureFeature)DataFixUtils.orElse(this.v_4262_N.stream().map(Supplier::get).filter(structureIn -> structureIn.G_564_y == structure.G_564_y).findAny(), structure);
    }

    public List<ConfiguredFeature<?, ?>> J_1907_R() {
        return this.w_1484_f;
    }

    public List<List<Supplier<ConfiguredFeature<?, ?>>>> R_4764_Y() {
        return this.u_1723_Y;
    }

    public Supplier<ConfiguredSurfaceBuilder<?>> G_564_y() {
        return this.G_564_y;
    }

    public Z_927_M P_1922_E() {
        return this.G_564_y.get().n_1700_B();
    }

    public static class n_1700_B {
        private Optional<Supplier<ConfiguredSurfaceBuilder<?>>> n_1700_B = Optional.empty();
        private final Map<T_3975_o.n_1700_B, List<Supplier<ConfiguredWorldCarver<?>>>> J_1907_R = Maps.newLinkedHashMap();
        private final List<List<Supplier<ConfiguredFeature<?, ?>>>> R_4764_Y = Lists.newArrayList();
        private final List<Supplier<ConfiguredStructureFeature<?, ?>>> G_564_y = Lists.newArrayList();

        public n_1700_B n_1700_B(ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder) {
            return this.n_1700_B(() -> configuredSurfaceBuilder);
        }

        public n_1700_B n_1700_B(Supplier<ConfiguredSurfaceBuilder<?>> configuredSurfaceBuilderSupplier) {
            this.n_1700_B = Optional.of(configuredSurfaceBuilderSupplier);
            return this;
        }

        public n_1700_B n_1700_B(T_3975_o.J_1907_R decorationStage, ConfiguredFeature<?, ?> feature) {
            return this.n_1700_B(decorationStage.ordinal(), () -> feature);
        }

        public n_1700_B n_1700_B(int stage, Supplier<ConfiguredFeature<?, ?>> features) {
            this.n_1700_B(stage);
            this.R_4764_Y.get(stage).add(features);
            return this;
        }

        public <C extends K_4573_Z> n_1700_B n_1700_B(T_3975_o.n_1700_B carvingStage, ConfiguredWorldCarver<C> carver) {
            this.J_1907_R.computeIfAbsent(carvingStage, stage -> Lists.newArrayList()).add(() -> carver);
            return this;
        }

        public n_1700_B n_1700_B(ConfiguredStructureFeature<?, ?> structure) {
            this.G_564_y.add(() -> structure);
            return this;
        }

        private void n_1700_B(int stage) {
            while (this.R_4764_Y.size() <= stage) {
                this.R_4764_Y.add(Lists.newArrayList());
            }
        }

        public BiomeGenerationSettings n_1700_B() {
            return new BiomeGenerationSettings(this.n_1700_B.orElseThrow(() -> new IllegalStateException("Missing surface builder")), (Map)this.J_1907_R.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ImmutableList.copyOf((Collection)((Collection)entry.getValue())))), (List)this.R_4764_Y.stream().map(ImmutableList::copyOf).collect(ImmutableList.toImmutableList()), (List<Supplier<ConfiguredStructureFeature<?, ?>>>)ImmutableList.copyOf(this.G_564_y));
        }
    }
}


