/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.registry.Registries
 *  net.minecraft.resources.Identifier
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class JSItems {
    @Safe
    public Item get(String name) {
        Identifier id = Identifier.parse(name);
        return BuiltInRegistries.ITEM.getValue(id);
    }

    @Safe
    public String checkItemName(ItemStack item) {
        return item.getCustomName().toString();
    }
}
