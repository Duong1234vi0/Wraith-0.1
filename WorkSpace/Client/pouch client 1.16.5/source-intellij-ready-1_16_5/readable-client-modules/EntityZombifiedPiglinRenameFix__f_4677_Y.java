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

public class f_4677_Y
extends Z_3827_g {
    public static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"minecraft:zombie_pigman_spawn_egg", (Object)"minecraft:zombified_piglin_spawn_egg").build();

    public f_4677_Y(Schema p_i231453_1_) {
        super("EntityZombifiedPiglinRenameFix", p_i231453_1_, true);
    }

    @Override
    protected String n_1700_B(String name) {
        return Objects.equals("minecraft:zombie_pigman", name) ? "minecraft:zombified_piglin" : name;
    }
}

