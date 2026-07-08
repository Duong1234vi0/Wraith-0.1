/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1839
 */
package code.alphadlc.utils.interactions.item;

import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1839;

public final class ItemUseHelper {
    private ItemUseHelper() {
    }

    public static boolean isPriorityUseItem(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return false;
        }
        class_1839 action = stack.method_7976();
        return action == class_1839.field_8950 || action == class_1839.field_8946 || action == class_1839.field_8953 || action == class_1839.field_8947 || action == class_1839.field_27079 || action == class_1839.field_8949;
    }

    public static boolean isConsumable(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return false;
        }
        class_1839 action = stack.method_7976();
        return action == class_1839.field_8950 || action == class_1839.field_8946;
    }

    public static boolean isUsingPriorityItem(class_1657 player) {
        if (player == null || !player.method_6115()) {
            return false;
        }
        return ItemUseHelper.isPriorityUseItem(player.method_6030());
    }

    public static class_1799 getStackForHand(class_1657 player, class_1268 hand) {
        if (player == null || hand == null) {
            return class_1799.field_8037;
        }
        return player.method_5998(hand);
    }
}
