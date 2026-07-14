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

public class EntityCodSalmonFix
extends Z_3827_g {
    public static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"minecraft:salmon_mob", (Object)"minecraft:salmon").put((Object)"minecraft:cod_mob", (Object)"minecraft:cod").build();
    public static final Map<String, String> J_1907_R = ImmutableMap.builder().put((Object)"minecraft:salmon_mob_spawn_egg", (Object)"minecraft:salmon_spawn_egg").put((Object)"minecraft:cod_mob_spawn_egg", (Object)"minecraft:cod_spawn_egg").build();

    public EntityCodSalmonFix(Schema outputSchema, boolean changesType) {
        super("EntityCodSalmonFix", outputSchema, changesType);
    }

    @Override
    protected String n_1700_B(String name) {
        return n_1700_B.getOrDefault(name, name);
    }
}


