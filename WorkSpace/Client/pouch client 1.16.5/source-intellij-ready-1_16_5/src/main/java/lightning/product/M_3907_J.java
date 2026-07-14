/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicLike
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.OptionalDynamic
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  org.apache.commons.lang3.math.NumberUtils
 *  org.apache.commons.lang3.mutable.MutableBoolean
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicLike;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.OptionalDynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.References;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

public class M_3907_J
extends DataFix {
    private static final ImmutableMap<String, n_1700_B> n_1700_B = ImmutableMap.builder().put((Object)"minecraft:village", (Object)new n_1700_B(32, 8, 10387312)).put((Object)"minecraft:desert_pyramid", (Object)new n_1700_B(32, 8, 14357617)).put((Object)"minecraft:igloo", (Object)new n_1700_B(32, 8, 14357618)).put((Object)"minecraft:jungle_pyramid", (Object)new n_1700_B(32, 8, 14357619)).put((Object)"minecraft:swamp_hut", (Object)new n_1700_B(32, 8, 14357620)).put((Object)"minecraft:pillager_outpost", (Object)new n_1700_B(32, 8, 165745296)).put((Object)"minecraft:monument", (Object)new n_1700_B(32, 5, 10387313)).put((Object)"minecraft:endcity", (Object)new n_1700_B(20, 11, 10387313)).put((Object)"minecraft:mansion", (Object)new n_1700_B(80, 20, 10387319)).build();

    public M_3907_J(Schema p_i231469_1_) {
        super(p_i231469_1_, true);
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("WorldGenSettings building", this.getInputSchema().getType(References.q_2307_F), p_233425_0_ -> p_233425_0_.update(DSL.remainderFinder(), M_3907_J::n_1700_B));
    }

    private static <T> Dynamic<T> n_1700_B(long p_233423_0_, DynamicLike<T> p_233423_2_, Dynamic<T> p_233423_3_, Dynamic<T> p_233423_4_) {
        return p_233423_2_.createMap((Map)ImmutableMap.of((Object)p_233423_2_.createString("type"), (Object)p_233423_2_.createString("minecraft:noise"), (Object)p_233423_2_.createString("biome_source"), p_233423_4_, (Object)p_233423_2_.createString("seed"), (Object)p_233423_2_.createLong(p_233423_0_), (Object)p_233423_2_.createString("settings"), p_233423_3_));
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_233427_0_, long p_233427_1_, boolean p_233427_3_, boolean p_233427_4_) {
        ImmutableMap.Builder builder = ImmutableMap.builder().put((Object)p_233427_0_.createString("type"), (Object)p_233427_0_.createString("minecraft:vanilla_layered")).put((Object)p_233427_0_.createString("seed"), (Object)p_233427_0_.createLong(p_233427_1_)).put((Object)p_233427_0_.createString("large_biomes"), (Object)p_233427_0_.createBoolean(p_233427_4_));
        if (p_233427_3_) {
            builder.put((Object)p_233427_0_.createString("legacy_biome_init_layer"), (Object)p_233427_0_.createBoolean(p_233427_3_));
        }
        return p_233427_0_.createMap((Map)builder.build());
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_233426_0_) {
        Dynamic<T> dynamic;
        DynamicOps dynamicops = p_233426_0_.getOps();
        long i = p_233426_0_.get("RandomSeed").asLong(0L);
        Optional optional = p_233426_0_.get("generatorName").asString().map(p_233433_0_ -> p_233433_0_.toLowerCase(Locale.ROOT)).result();
        Optional optional1 = p_233426_0_.get("legacy_custom_options").asString().result().map(Optional::of).orElseGet(() -> optional.equals(Optional.of("customized")) ? p_233426_0_.get("generatorOptions").asString().result() : Optional.empty());
        boolean flag = false;
        if (optional.equals(Optional.of("customized"))) {
            dynamic = M_3907_J.n_1700_B(p_233426_0_, i);
        } else if (!optional.isPresent()) {
            dynamic = M_3907_J.n_1700_B(p_233426_0_, i);
        } else {
            switch ((String)optional.get()) {
                case "flat": {
                    OptionalDynamic optionaldynamic = p_233426_0_.get("generatorOptions");
                    Map<Dynamic<T>, Dynamic<T>> map = M_3907_J.n_1700_B(dynamicops, optionaldynamic);
                    dynamic = p_233426_0_.createMap((Map)ImmutableMap.of((Object)p_233426_0_.createString("type"), (Object)p_233426_0_.createString("minecraft:flat"), (Object)p_233426_0_.createString("settings"), (Object)p_233426_0_.createMap((Map)ImmutableMap.of((Object)p_233426_0_.createString("structures"), (Object)p_233426_0_.createMap(map), (Object)p_233426_0_.createString("layers"), (Object)optionaldynamic.get("layers").result().orElseGet(() -> p_233426_0_.createList(Stream.of(p_233426_0_.createMap((Map)ImmutableMap.of((Object)p_233426_0_.createString("height"), (Object)p_233426_0_.createInt(1), (Object)p_233426_0_.createString("block"), (Object)p_233426_0_.createString("minecraft:bedrock"))), p_233426_0_.createMap((Map)ImmutableMap.of((Object)p_233426_0_.createString("height"), (Object)p_233426_0_.createInt(2), (Object)p_233426_0_.createString("block"), (Object)p_233426_0_.createString("minecraft:dirt"))), p_233426_0_.createMap((Map)ImmutableMap.of((Object)p_233426_0_.createString("height"), (Object)p_233426_0_.createInt(1), (Object)p_233426_0_.createString("block"), (Object)p_233426_0_.createString("minecraft:grass_block")))))), (Object)p_233426_0_.createString("biome"), (Object)p_233426_0_.createString(optionaldynamic.get("biome").asString("minecraft:plains"))))));
                    break;
                }
                case "debug_all_block_states": {
                    dynamic = p_233426_0_.createMap((Map)ImmutableMap.of((Object)p_233426_0_.createString("type"), (Object)p_233426_0_.createString("minecraft:debug")));
                    break;
                }
                case "buffet": {
                    Dynamic dynamic3;
                    Dynamic dynamic1;
                    OptionalDynamic optionaldynamic1 = p_233426_0_.get("generatorOptions");
                    OptionalDynamic optionaldynamic2 = optionaldynamic1.get("chunk_generator");
                    Optional optional2 = optionaldynamic2.get("type").asString().result();
                    if (Objects.equals(optional2, Optional.of("minecraft:caves"))) {
                        dynamic1 = p_233426_0_.createString("minecraft:caves");
                        flag = true;
                    } else {
                        dynamic1 = Objects.equals(optional2, Optional.of("minecraft:floating_islands")) ? p_233426_0_.createString("minecraft:floating_islands") : p_233426_0_.createString("minecraft:overworld");
                    }
                    Dynamic dynamic2 = optionaldynamic1.get("biome_source").result().orElseGet(() -> p_233426_0_.createMap((Map)ImmutableMap.of((Object)p_233426_0_.createString("type"), (Object)p_233426_0_.createString("minecraft:fixed"))));
                    if (dynamic2.get("type").asString().result().equals(Optional.of("minecraft:fixed"))) {
                        String s1 = dynamic2.get("options").get("biomes").asStream().findFirst().flatMap(p_233440_0_ -> p_233440_0_.asString().result()).orElse("minecraft:ocean");
                        dynamic3 = dynamic2.remove("options").set("biome", p_233426_0_.createString(s1));
                    } else {
                        dynamic3 = dynamic2;
                    }
                    dynamic = M_3907_J.n_1700_B(i, p_233426_0_, dynamic1, dynamic3);
                    break;
                }
                default: {
                    boolean flag6 = ((String)optional.get()).equals("default");
                    boolean flag1 = ((String)optional.get()).equals("default_1_1") || flag6 && p_233426_0_.get("generatorVersion").asInt(0) == 0;
                    boolean flag2 = ((String)optional.get()).equals("amplified");
                    boolean flag3 = ((String)optional.get()).equals("largebiomes");
                    dynamic = M_3907_J.n_1700_B(i, p_233426_0_, p_233426_0_.createString(flag2 ? "minecraft:amplified" : "minecraft:overworld"), M_3907_J.n_1700_B(p_233426_0_, i, flag1, flag3));
                }
            }
        }
        boolean flag4 = p_233426_0_.get("MapFeatures").asBoolean(true);
        boolean flag5 = p_233426_0_.get("BonusChest").asBoolean(false);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put(dynamicops.createString("seed"), dynamicops.createLong(i));
        builder.put(dynamicops.createString("generate_features"), dynamicops.createBoolean(flag4));
        builder.put(dynamicops.createString("bonus_chest"), dynamicops.createBoolean(flag5));
        builder.put(dynamicops.createString("dimensions"), M_3907_J.n_1700_B(p_233426_0_, i, dynamic, flag));
        optional1.ifPresent(p_233424_2_ -> builder.put(dynamicops.createString("legacy_custom_options"), dynamicops.createString(p_233424_2_)));
        return new Dynamic(dynamicops, dynamicops.createMap((Map)builder.build()));
    }

    protected static <T> Dynamic<T> n_1700_B(Dynamic<T> p_241322_0_, long p_241322_1_) {
        return M_3907_J.n_1700_B(p_241322_1_, p_241322_0_, p_241322_0_.createString("minecraft:overworld"), M_3907_J.n_1700_B(p_241322_0_, p_241322_1_, false, false));
    }

    protected static <T> T n_1700_B(Dynamic<T> p_241323_0_, long p_241323_1_, Dynamic<T> p_241323_3_, boolean p_241323_4_) {
        DynamicOps dynamicops = p_241323_0_.getOps();
        return (T)dynamicops.createMap((Map)ImmutableMap.of((Object)dynamicops.createString("minecraft:overworld"), (Object)dynamicops.createMap((Map)ImmutableMap.of((Object)dynamicops.createString("type"), (Object)dynamicops.createString("minecraft:overworld" + (p_241323_4_ ? "_caves" : "")), (Object)dynamicops.createString("generator"), (Object)p_241323_3_.getValue())), (Object)dynamicops.createString("minecraft:the_nether"), (Object)dynamicops.createMap((Map)ImmutableMap.of((Object)dynamicops.createString("type"), (Object)dynamicops.createString("minecraft:the_nether"), (Object)dynamicops.createString("generator"), (Object)M_3907_J.n_1700_B(p_241323_1_, p_241323_0_, p_241323_0_.createString("minecraft:nether"), p_241323_0_.createMap((Map)ImmutableMap.of((Object)p_241323_0_.createString("type"), (Object)p_241323_0_.createString("minecraft:multi_noise"), (Object)p_241323_0_.createString("seed"), (Object)p_241323_0_.createLong(p_241323_1_), (Object)p_241323_0_.createString("preset"), (Object)p_241323_0_.createString("minecraft:nether")))).getValue())), (Object)dynamicops.createString("minecraft:the_end"), (Object)dynamicops.createMap((Map)ImmutableMap.of((Object)dynamicops.createString("type"), (Object)dynamicops.createString("minecraft:the_end"), (Object)dynamicops.createString("generator"), (Object)M_3907_J.n_1700_B(p_241323_1_, p_241323_0_, p_241323_0_.createString("minecraft:end"), p_241323_0_.createMap((Map)ImmutableMap.of((Object)p_241323_0_.createString("type"), (Object)p_241323_0_.createString("minecraft:the_end"), (Object)p_241323_0_.createString("seed"), (Object)p_241323_0_.createLong(p_241323_1_)))).getValue()))));
    }

    private static <T> Map<Dynamic<T>, Dynamic<T>> n_1700_B(DynamicOps<T> p_233430_0_, OptionalDynamic<T> p_233430_1_) {
        MutableInt mutableint = new MutableInt(32);
        MutableInt mutableint1 = new MutableInt(3);
        MutableInt mutableint2 = new MutableInt(128);
        MutableBoolean mutableboolean = new MutableBoolean(false);
        HashMap map = Maps.newHashMap();
        if (!p_233430_1_.result().isPresent()) {
            mutableboolean.setTrue();
            map.put("minecraft:village", (n_1700_B)n_1700_B.get((Object)"minecraft:village"));
        }
        p_233430_1_.get("structures").flatMap(Dynamic::getMapValues).result().ifPresent(p_233439_5_ -> p_233439_5_.forEach((p_233438_5_, p_233438_6_) -> p_233438_6_.getMapValues().result().ifPresent(p_233429_6_ -> p_233429_6_.forEach((p_233428_6_, p_233428_7_) -> {
            String s = p_233438_5_.asString("");
            String s1 = p_233428_6_.asString("");
            String s2 = p_233428_7_.asString("");
            if ("stronghold".equals(s)) {
                mutableboolean.setTrue();
                int b1 = -1;
                switch (s1.hashCode()) {
                    case -895684237: {
                        if (!s1.equals("spread")) break;
                        b1 = 1;
                        break;
                    }
                    case 94851343: {
                        if (!s1.equals("count")) break;
                        b1 = 2;
                        break;
                    }
                    case 288459765: {
                        if (!s1.equals("distance")) break;
                        b1 = 0;
                    }
                }
                switch (b1) {
                    case 0: {
                        mutableint.setValue(M_3907_J.n_1700_B(s2, mutableint.getValue(), 1));
                        return;
                    }
                    case 1: {
                        mutableint1.setValue(M_3907_J.n_1700_B(s2, mutableint1.getValue(), 1));
                        return;
                    }
                    case 2: {
                        mutableint2.setValue(M_3907_J.n_1700_B(s2, mutableint2.getValue(), 1));
                        return;
                    }
                }
            } else {
                int b0 = -1;
                switch (s1.hashCode()) {
                    case -2116852922: {
                        if (!s1.equals("separation")) break;
                        b0 = 1;
                        break;
                    }
                    case -2012158909: {
                        if (!s1.equals("spacing")) break;
                        b0 = 2;
                        break;
                    }
                    case 288459765: {
                        if (!s1.equals("distance")) break;
                        b0 = 0;
                    }
                }
                switch (b0) {
                    case 0: {
                        int b2 = -1;
                        switch (s.hashCode()) {
                            case -1606796090: {
                                if (!s.equals("endcity")) break;
                                b2 = 2;
                                break;
                            }
                            case -107033518: {
                                if (!s.equals("biome_1")) break;
                                b2 = 1;
                                break;
                            }
                            case 460367020: {
                                if (!s.equals("village")) break;
                                b2 = 0;
                                break;
                            }
                            case 835798799: {
                                if (!s.equals("mansion")) break;
                                b2 = 3;
                            }
                        }
                        switch (b2) {
                            case 0: {
                                M_3907_J.n_1700_B(map, "minecraft:village", s2, 9);
                                return;
                            }
                            case 1: {
                                M_3907_J.n_1700_B(map, "minecraft:desert_pyramid", s2, 9);
                                M_3907_J.n_1700_B(map, "minecraft:igloo", s2, 9);
                                M_3907_J.n_1700_B(map, "minecraft:jungle_pyramid", s2, 9);
                                M_3907_J.n_1700_B(map, "minecraft:swamp_hut", s2, 9);
                                M_3907_J.n_1700_B(map, "minecraft:pillager_outpost", s2, 9);
                                return;
                            }
                            case 2: {
                                M_3907_J.n_1700_B(map, "minecraft:endcity", s2, 1);
                                return;
                            }
                            case 3: {
                                M_3907_J.n_1700_B(map, "minecraft:mansion", s2, 1);
                                return;
                            }
                        }
                        return;
                    }
                    case 1: {
                        if ("oceanmonument".equals(s)) {
                            n_1700_B worldgensettings$structureseparationsettingscodec = map.getOrDefault("minecraft:monument", (n_1700_B)n_1700_B.get((Object)"minecraft:monument"));
                            int i = M_3907_J.n_1700_B(s2, worldgensettings$structureseparationsettingscodec.R_4764_Y, 1);
                            map.put("minecraft:monument", new n_1700_B(i, worldgensettings$structureseparationsettingscodec.R_4764_Y, worldgensettings$structureseparationsettingscodec.G_564_y));
                        }
                        return;
                    }
                    case 2: {
                        if ("oceanmonument".equals(s)) {
                            M_3907_J.n_1700_B(map, "minecraft:monument", s2, 1);
                        }
                        return;
                    }
                }
            }
        }))));
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put((Object)p_233430_1_.createString("structures"), (Object)p_233430_1_.createMap(map.entrySet().stream().collect(Collectors.toMap(p_233432_1_ -> p_233430_1_.createString((String)p_233432_1_.getKey()), p_233431_1_ -> ((n_1700_B)p_233431_1_.getValue()).n_1700_B(p_233430_0_)))));
        if (mutableboolean.isTrue()) {
            builder.put((Object)p_233430_1_.createString("stronghold"), (Object)p_233430_1_.createMap((Map)ImmutableMap.of((Object)p_233430_1_.createString("distance"), (Object)p_233430_1_.createInt(mutableint.getValue().intValue()), (Object)p_233430_1_.createString("spread"), (Object)p_233430_1_.createInt(mutableint1.getValue().intValue()), (Object)p_233430_1_.createString("count"), (Object)p_233430_1_.createInt(mutableint2.getValue().intValue()))));
        }
        return builder.build();
    }

    private static int n_1700_B(String p_233434_0_, int p_233434_1_) {
        return NumberUtils.toInt((String)p_233434_0_, (int)p_233434_1_);
    }

    private static int n_1700_B(String p_233435_0_, int p_233435_1_, int p_233435_2_) {
        return Math.max(p_233435_2_, M_3907_J.n_1700_B(p_233435_0_, p_233435_1_));
    }

    private static void n_1700_B(Map<String, n_1700_B> p_233436_0_, String p_233436_1_, String p_233436_2_, int p_233436_3_) {
        n_1700_B worldgensettings$structureseparationsettingscodec = p_233436_0_.getOrDefault(p_233436_1_, (n_1700_B)n_1700_B.get((Object)p_233436_1_));
        int i = M_3907_J.n_1700_B(p_233436_2_, worldgensettings$structureseparationsettingscodec.J_1907_R, p_233436_3_);
        p_233436_0_.put(p_233436_1_, new n_1700_B(i, worldgensettings$structureseparationsettingscodec.R_4764_Y, worldgensettings$structureseparationsettingscodec.G_564_y));
    }

    static final class n_1700_B {
        public static final Codec<n_1700_B> n_1700_B = RecordCodecBuilder.create(p_233448_0_ -> p_233448_0_.group((App)Codec.INT.fieldOf("spacing").forGetter(p_233453_0_ -> p_233453_0_.J_1907_R), (App)Codec.INT.fieldOf("separation").forGetter(p_233452_0_ -> p_233452_0_.R_4764_Y), (App)Codec.INT.fieldOf("salt").forGetter(p_233451_0_ -> p_233451_0_.G_564_y)).apply((Applicative)p_233448_0_, n_1700_B::new));
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        public n_1700_B(int p_i231470_1_, int p_i231470_2_, int p_i231470_3_) {
            this.J_1907_R = p_i231470_1_;
            this.R_4764_Y = p_i231470_2_;
            this.G_564_y = p_i231470_3_;
        }

        public <T> Dynamic<T> n_1700_B(DynamicOps<T> p_233447_1_) {
            return new Dynamic(p_233447_1_, n_1700_B.encodeStart(p_233447_1_, (Object)this).result().orElse(p_233447_1_.emptyMap()));
        }
    }
}


