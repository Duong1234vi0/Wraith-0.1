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
import java.util.Objects;
import lightning.product.Z_3827_g;

public class E_2109_V
extends Z_3827_g {
    public static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"minecraft:puffer_fish_spawn_egg", (Object)"minecraft:pufferfish_spawn_egg").build();

    public E_2109_V(Schema outputSchema, boolean changesType) {
        super("EntityPufferfishRenameFix", outputSchema, changesType);
    }

    @Override
    protected String n_1700_B(String name) {
        return Objects.equals("minecraft:puffer_fish", name) ? "minecraft:pufferfish" : name;
    }
}

