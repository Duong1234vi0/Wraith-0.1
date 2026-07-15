/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.ItemUseAnimation
 *  net.minecraft.world.InteractionHand
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelContext;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.InteractionHand;

public class ItemModelStorage {
    private static final List<ItemModelContext> data = new ArrayList<ItemModelContext>();

    public static void addData(ItemModelContext info, ItemStack item) {
        if (!item.isEmpty() && item.getUseAnimation() != ItemUseAnimation.BLOCK && item.getUseAnimation() != ItemUseAnimation.TRIDENT) {
            data.add(info);
        }
    }

    public static ItemModelContext get() {
        if (!data.isEmpty()) {
            ItemModelContext record = data.getFirst();
            data.remove(record);
            return record;
        }
        return new ItemModelContext(false, 0.0f, Minecraft.getInstance().player, InteractionHand.MAIN_HAND, false, LuaTestHMI.deltaTime, 0.0f, 0.0f, 0.0f, false, false, false, false, false, false, Items.AIR.getDefaultInstance());
    }

    public static void clear() {
        data.clear();
    }
}
