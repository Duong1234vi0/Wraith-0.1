/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class Q_3551_W
extends DataFix {
    private static final Map<String, String> n_1700_B = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209279_0_ -> {
        p_209279_0_.put("minecraft:bat", "minecraft:bat_spawn_egg");
        p_209279_0_.put("minecraft:blaze", "minecraft:blaze_spawn_egg");
        p_209279_0_.put("minecraft:cave_spider", "minecraft:cave_spider_spawn_egg");
        p_209279_0_.put("minecraft:chicken", "minecraft:chicken_spawn_egg");
        p_209279_0_.put("minecraft:cow", "minecraft:cow_spawn_egg");
        p_209279_0_.put("minecraft:creeper", "minecraft:creeper_spawn_egg");
        p_209279_0_.put("minecraft:donkey", "minecraft:donkey_spawn_egg");
        p_209279_0_.put("minecraft:elder_guardian", "minecraft:elder_guardian_spawn_egg");
        p_209279_0_.put("minecraft:enderman", "minecraft:enderman_spawn_egg");
        p_209279_0_.put("minecraft:endermite", "minecraft:endermite_spawn_egg");
        p_209279_0_.put("minecraft:evocation_illager", "minecraft:evocation_illager_spawn_egg");
        p_209279_0_.put("minecraft:ghast", "minecraft:ghast_spawn_egg");
        p_209279_0_.put("minecraft:guardian", "minecraft:guardian_spawn_egg");
        p_209279_0_.put("minecraft:horse", "minecraft:horse_spawn_egg");
        p_209279_0_.put("minecraft:husk", "minecraft:husk_spawn_egg");
        p_209279_0_.put("minecraft:llama", "minecraft:llama_spawn_egg");
        p_209279_0_.put("minecraft:magma_cube", "minecraft:magma_cube_spawn_egg");
        p_209279_0_.put("minecraft:mooshroom", "minecraft:mooshroom_spawn_egg");
        p_209279_0_.put("minecraft:mule", "minecraft:mule_spawn_egg");
        p_209279_0_.put("minecraft:ocelot", "minecraft:ocelot_spawn_egg");
        p_209279_0_.put("minecraft:pufferfish", "minecraft:pufferfish_spawn_egg");
        p_209279_0_.put("minecraft:parrot", "minecraft:parrot_spawn_egg");
        p_209279_0_.put("minecraft:pig", "minecraft:pig_spawn_egg");
        p_209279_0_.put("minecraft:polar_bear", "minecraft:polar_bear_spawn_egg");
        p_209279_0_.put("minecraft:rabbit", "minecraft:rabbit_spawn_egg");
        p_209279_0_.put("minecraft:sheep", "minecraft:sheep_spawn_egg");
        p_209279_0_.put("minecraft:shulker", "minecraft:shulker_spawn_egg");
        p_209279_0_.put("minecraft:silverfish", "minecraft:silverfish_spawn_egg");
        p_209279_0_.put("minecraft:skeleton", "minecraft:skeleton_spawn_egg");
        p_209279_0_.put("minecraft:skeleton_horse", "minecraft:skeleton_horse_spawn_egg");
        p_209279_0_.put("minecraft:slime", "minecraft:slime_spawn_egg");
        p_209279_0_.put("minecraft:spider", "minecraft:spider_spawn_egg");
        p_209279_0_.put("minecraft:squid", "minecraft:squid_spawn_egg");
        p_209279_0_.put("minecraft:stray", "minecraft:stray_spawn_egg");
        p_209279_0_.put("minecraft:turtle", "minecraft:turtle_spawn_egg");
        p_209279_0_.put("minecraft:vex", "minecraft:vex_spawn_egg");
        p_209279_0_.put("minecraft:villager", "minecraft:villager_spawn_egg");
        p_209279_0_.put("minecraft:vindication_illager", "minecraft:vindication_illager_spawn_egg");
        p_209279_0_.put("minecraft:witch", "minecraft:witch_spawn_egg");
        p_209279_0_.put("minecraft:wither_skeleton", "minecraft:wither_skeleton_spawn_egg");
        p_209279_0_.put("minecraft:wolf", "minecraft:wolf_spawn_egg");
        p_209279_0_.put("minecraft:zombie", "minecraft:zombie_spawn_egg");
        p_209279_0_.put("minecraft:zombie_horse", "minecraft:zombie_horse_spawn_egg");
        p_209279_0_.put("minecraft:zombie_pigman", "minecraft:zombie_pigman_spawn_egg");
        p_209279_0_.put("minecraft:zombie_villager", "minecraft:zombie_villager_spawn_egg");
    });

    public Q_3551_W(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        OpticFinder opticfinder1 = DSL.fieldFinder((String)"id", NamespacedSchema.n_1700_B());
        OpticFinder opticfinder2 = type.findField("tag");
        OpticFinder opticfinder3 = opticfinder2.type().findField("EntityTag");
        return this.fixTypeEverywhereTyped("ItemInstanceSpawnEggFix", type, p_206361_4_ -> {
            Typed typed;
            Typed typed1;
            Optional optional1;
            Optional optional = p_206361_4_.getOptional(opticfinder);
            if (optional.isPresent() && Objects.equals(((Pair)optional.get()).getSecond(), "minecraft:spawn_egg") && (optional1 = (typed1 = (typed = p_206361_4_.getOrCreateTyped(opticfinder2)).getOrCreateTyped(opticfinder3)).getOptional(opticfinder1)).isPresent()) {
                return p_206361_4_.set(opticfinder, (Object)Pair.of((Object)References.multiplayerClientSuggestionProvider.typeName(), (Object)n_1700_B.getOrDefault(optional1.get(), "minecraft:pig_spawn_egg")));
            }
            return p_206361_4_;
        });
    }
}


