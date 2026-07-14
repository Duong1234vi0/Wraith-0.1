/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.schemas.Schema;
import java.util.Map;
import lightning.product.Z_3827_g;

public class M_1932_A
extends Z_3827_g {
    public static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"minecraft:commandblock_minecart", (Object)"minecraft:command_block_minecart").put((Object)"minecraft:ender_crystal", (Object)"minecraft:end_crystal").put((Object)"minecraft:snowman", (Object)"minecraft:snow_golem").put((Object)"minecraft:evocation_illager", (Object)"minecraft:evoker").put((Object)"minecraft:evocation_fangs", (Object)"minecraft:evoker_fangs").put((Object)"minecraft:illusion_illager", (Object)"minecraft:illusioner").put((Object)"minecraft:vindication_illager", (Object)"minecraft:vindicator").put((Object)"minecraft:villager_golem", (Object)"minecraft:iron_golem").put((Object)"minecraft:xp_orb", (Object)"minecraft:experience_orb").put((Object)"minecraft:xp_bottle", (Object)"minecraft:experience_bottle").put((Object)"minecraft:eye_of_ender_signal", (Object)"minecraft:eye_of_ender").put((Object)"minecraft:fireworks_rocket", (Object)"minecraft:firework_rocket").build();
    public static final Map<String, String> J_1907_R = ImmutableMap.builder().put((Object)"minecraft:portal", (Object)"minecraft:nether_portal").put((Object)"minecraft:oak_bark", (Object)"minecraft:oak_wood").put((Object)"minecraft:spruce_bark", (Object)"minecraft:spruce_wood").put((Object)"minecraft:birch_bark", (Object)"minecraft:birch_wood").put((Object)"minecraft:jungle_bark", (Object)"minecraft:jungle_wood").put((Object)"minecraft:acacia_bark", (Object)"minecraft:acacia_wood").put((Object)"minecraft:dark_oak_bark", (Object)"minecraft:dark_oak_wood").put((Object)"minecraft:stripped_oak_bark", (Object)"minecraft:stripped_oak_wood").put((Object)"minecraft:stripped_spruce_bark", (Object)"minecraft:stripped_spruce_wood").put((Object)"minecraft:stripped_birch_bark", (Object)"minecraft:stripped_birch_wood").put((Object)"minecraft:stripped_jungle_bark", (Object)"minecraft:stripped_jungle_wood").put((Object)"minecraft:stripped_acacia_bark", (Object)"minecraft:stripped_acacia_wood").put((Object)"minecraft:stripped_dark_oak_bark", (Object)"minecraft:stripped_dark_oak_wood").put((Object)"minecraft:mob_spawner", (Object)"minecraft:spawner").build();
    public static final Map<String, String> R_4764_Y = ImmutableMap.builder().putAll(J_1907_R).put((Object)"minecraft:clownfish", (Object)"minecraft:tropical_fish").put((Object)"minecraft:chorus_fruit_popped", (Object)"minecraft:popped_chorus_fruit").put((Object)"minecraft:evocation_illager_spawn_egg", (Object)"minecraft:evoker_spawn_egg").put((Object)"minecraft:vindication_illager_spawn_egg", (Object)"minecraft:vindicator_spawn_egg").build();

    public M_1932_A(Schema outputSchema, boolean changesType) {
        super("EntityTheRenameningBlock", outputSchema, changesType);
    }

    @Override
    protected String n_1700_B(String name) {
        if (((String)name).startsWith("minecraft:bred_")) {
            name = "minecraft:" + ((String)name).substring("minecraft:bred_".length());
        }
        return n_1700_B.getOrDefault(name, (String)name);
    }
}

