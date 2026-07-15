/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.ItemUseAnimation
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemUseAnimation;

public class DispatcherStorage {
    private static final List<ItemStack> renderedItems = new ArrayList<ItemStack>();

    public static void setItem(ItemStack item) {
        if (!item.isEmpty() && item.getUseAnimation() != ItemUseAnimation.BLOCK && item.getUseAnimation() != ItemUseAnimation.TRIDENT) {
            renderedItems.add(item);
        }
    }

    public static ItemStack getRenderedItem() {
        if (!renderedItems.isEmpty()) {
            ItemStack stack = renderedItems.getFirst();
            renderedItems.remove(stack);
            return stack;
        }
        return Items.AIR.getDefaultInstance();
    }

    public static void clear() {
        renderedItems.clear();
    }
}
