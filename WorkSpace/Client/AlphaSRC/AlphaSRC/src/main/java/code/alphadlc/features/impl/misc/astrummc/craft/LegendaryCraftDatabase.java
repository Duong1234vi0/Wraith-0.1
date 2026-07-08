/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.misc.astrummc.craft;

import code.alphadlc.features.impl.misc.astrummc.craft.CraftIngredient;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftRecipe;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftRecipes;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;
import net.minecraft.class_1792;
import net.minecraft.class_1802;

public final class LegendaryCraftDatabase {
    private static final Map<String, class_1792> ITEMS = Map.ofEntries(Map.entry("obsidian", class_1802.field_8281), Map.entry("diamond_hoe", class_1802.field_8527), Map.entry("coal", class_1802.field_8713), Map.entry("soul_sand", class_1802.field_8067), Map.entry("golden_apple", class_1802.field_8463), Map.entry("quartz", class_1802.field_8155), Map.entry("golden_sword", class_1802.field_8845), Map.entry("diamond", class_1802.field_8477), Map.entry("echo_shard", class_1802.field_38746), Map.entry("iron_sword", class_1802.field_8371), Map.entry("emerald", class_1802.field_8687), Map.entry("bell", class_1802.field_16315), Map.entry("golden_carrot", class_1802.field_8071), Map.entry("feather", class_1802.field_8153), Map.entry("cake", class_1802.field_17534), Map.entry("spider_eye", class_1802.field_8680), Map.entry("poisonous_potato", class_1802.field_8635), Map.entry("raw_copper_block", class_1802.field_33506), Map.entry("blaze_powder", class_1802.field_8183), Map.entry("iron_block", class_1802.field_8773), Map.entry("blaze_rod", class_1802.field_8894), Map.entry("ender_pearl", class_1802.field_8634), Map.entry("netherite_scrap", class_1802.field_22021), Map.entry("wind_charge", class_1802.field_49098), Map.entry("redstone_torch", class_1802.field_8530), Map.entry("red_candle", class_1802.field_27061), Map.entry("gold_ingot", class_1802.field_8695), Map.entry("bone", class_1802.field_8606), Map.entry("experience_bottle", class_1802.field_8287), Map.entry("redstone_block", class_1802.field_8793), Map.entry("skeleton_skull", class_1802.field_8398), Map.entry("pufferfish", class_1802.field_8323), Map.entry("copper_block", class_1802.field_27071), Map.entry("tnt", class_1802.field_8626), Map.entry("nautilus_shell", class_1802.field_8864), Map.entry("seagrass", class_1802.field_8158), Map.entry("trident", class_1802.field_8547), Map.entry("gold_block", class_1802.field_8494), Map.entry("lodestone", class_1802.field_23256), Map.entry("iron_axe", class_1802.field_8475), Map.entry("magma_cream", class_1802.field_8135), Map.entry("totem", class_1802.field_8288), Map.entry("bow", class_1802.field_8102), Map.entry("ender_eye", class_1802.field_8449), Map.entry("lapis_block", class_1802.field_8055), Map.entry("clock", class_1802.field_8557), Map.entry("diamond_sword", class_1802.field_8802), Map.entry("diamond_axe", class_1802.field_8556), Map.entry("lightning_rod", class_1802.field_27051), Map.entry("firework_star", class_1802.field_8450), Map.entry("crying_obsidian", class_1802.field_22421), Map.entry("enchanted_golden_apple", class_1802.field_8367), Map.entry("emerald_block", class_1802.field_8733));

    private LegendaryCraftDatabase() {
    }

    public static class_1792 getItem(String id) {
        return ITEMS.get(id);
    }

    public static Map<String, Integer> collectInventoryCounts(ToIntFunction<class_1792> counter) {
        HashMap<String, Integer> inventory = new HashMap<String, Integer>();
        for (LegendaryCraftRecipe recipe : LegendaryCraftRecipes.ALL) {
            for (CraftIngredient ingredient : recipe.ingredients()) {
                class_1792 item = ITEMS.get(ingredient.id());
                if (item == null) continue;
                inventory.put(ingredient.id(), counter.applyAsInt(item));
            }
        }
        return inventory;
    }

    public static String buildFingerprint(Map<String, Integer> inventory) {
        StringBuilder builder = new StringBuilder();
        ITEMS.keySet().stream().sorted().forEach(id -> builder.append((String)id).append('=').append(inventory.getOrDefault(id, 0)).append(';'));
        return builder.toString();
    }
}
