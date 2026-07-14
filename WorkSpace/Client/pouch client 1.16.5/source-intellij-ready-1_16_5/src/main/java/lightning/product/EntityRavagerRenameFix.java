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

public class EntityRavagerRenameFix
extends Z_3827_g {
    public static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"minecraft:illager_beast_spawn_egg", (Object)"minecraft:ravager_spawn_egg").build();

    public EntityRavagerRenameFix(Schema p_i50427_1_, boolean p_i50427_2_) {
        super("EntityRavagerRenameFix", p_i50427_1_, p_i50427_2_);
    }

    @Override
    protected String n_1700_B(String name) {
        return Objects.equals("minecraft:illager_beast", name) ? "minecraft:ravager" : name;
    }
}


