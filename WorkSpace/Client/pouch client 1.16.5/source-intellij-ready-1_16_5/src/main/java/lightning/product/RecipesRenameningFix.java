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
import lightning.product.RecipesRenameFix;

public class RecipesRenameningFix
extends RecipesRenameFix {
    private static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"minecraft:acacia_bark", (Object)"minecraft:acacia_wood").put((Object)"minecraft:birch_bark", (Object)"minecraft:birch_wood").put((Object)"minecraft:dark_oak_bark", (Object)"minecraft:dark_oak_wood").put((Object)"minecraft:jungle_bark", (Object)"minecraft:jungle_wood").put((Object)"minecraft:oak_bark", (Object)"minecraft:oak_wood").put((Object)"minecraft:spruce_bark", (Object)"minecraft:spruce_wood").build();

    public RecipesRenameningFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "Recipes renamening fix", p_230077_0_ -> n_1700_B.getOrDefault(p_230077_0_, (String)p_230077_0_));
    }
}


