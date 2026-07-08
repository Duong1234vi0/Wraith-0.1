/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1280
 *  net.minecraft.class_1282
 *  net.minecraft.class_1294
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1748
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1810
 *  net.minecraft.class_1821
 *  net.minecraft.class_1829
 *  net.minecraft.class_2248
 *  net.minecraft.class_2596
 *  net.minecraft.class_2868
 *  net.minecraft.class_5134
 *  net.minecraft.class_7923
 *  net.minecraft.class_9285
 *  net.minecraft.class_9285$class_9287
 *  net.minecraft.class_9334
 */
package code.alphadlc.utils.interactions.inv;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.SlotSearchResult;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1280;
import net.minecraft.class_1282;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1748;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1810;
import net.minecraft.class_1821;
import net.minecraft.class_1829;
import net.minecraft.class_2248;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_5134;
import net.minecraft.class_7923;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import org.jetbrains.annotations.NotNull;

public final class HotbarUtil
implements QuickImports {
    private static int cachedSlot = -1;

    public static int getItemCount(class_1792 item) {
        if (HotbarUtil.mc.field_1724 == null) {
            return 0;
        }
        int counter = 0;
        for (int i = 0; i < HotbarUtil.mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 stack = HotbarUtil.mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_31574(item)) continue;
            counter += stack.method_7947();
        }
        return counter;
    }

    public static SlotSearchResult getAxe() {
        return HotbarUtil.findBest(itemStack -> itemStack.method_7909() instanceof class_1743, false);
    }

    public static SlotSearchResult getAxeHotBar() {
        return HotbarUtil.findBest(itemStack -> itemStack.method_7909() instanceof class_1743, true);
    }

    public static SlotSearchResult getPickAxe() {
        return HotbarUtil.findBest(itemStack -> itemStack.method_7909() instanceof class_1810, false);
    }

    public static SlotSearchResult getPickAxeHotbar() {
        return HotbarUtil.getPickAxeHotBar();
    }

    public static SlotSearchResult getPickAxeHotBar() {
        return HotbarUtil.findBest(itemStack -> itemStack.method_7909() instanceof class_1810, true);
    }

    public static SlotSearchResult getSword() {
        return HotbarUtil.findBest(itemStack -> itemStack.method_7909() instanceof class_1829, false);
    }

    public static SlotSearchResult getSwordHotBar() {
        return HotbarUtil.findBest(itemStack -> itemStack.method_7909() instanceof class_1829, true);
    }

    public static SlotSearchResult getSkull() {
        return HotbarUtil.findInHotBar(stack -> stack.method_31574(class_1802.field_8398) || stack.method_31574(class_1802.field_8791) || stack.method_31574(class_1802.field_8681) || stack.method_31574(class_1802.field_8575) || stack.method_31574(class_1802.field_8470));
    }

    public static int getElytra() {
        if (HotbarUtil.mc.field_1724 == null) {
            return -1;
        }
        for (class_1799 stack : HotbarUtil.mc.field_1724.method_31548().field_7548) {
            if (!stack.method_31574(class_1802.field_8833) || stack.method_7919() >= stack.method_7936() - 1) continue;
            return -2;
        }
        for (int i = 0; i < 36; ++i) {
            class_1799 stack;
            stack = HotbarUtil.mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_31574(class_1802.field_8833) || stack.method_7919() >= stack.method_7936() - 1) continue;
            return i < 9 ? i + 36 : i;
        }
        return -1;
    }

    public static SlotSearchResult findInHotBar(Searcher searcher) {
        if (HotbarUtil.mc.field_1724 != null) {
            if (searcher.isValid(HotbarUtil.mc.field_1724.method_6079())) {
                return SlotSearchResult.inOffhand(HotbarUtil.mc.field_1724.method_6079());
            }
            for (int i = 0; i < 9; ++i) {
                class_1799 stack = HotbarUtil.mc.field_1724.method_31548().method_5438(i);
                if (!searcher.isValid(stack)) continue;
                return new SlotSearchResult(i, true, stack);
            }
        }
        return SlotSearchResult.notFound();
    }

    public static SlotSearchResult findItemInHotBar(List<class_1792> items) {
        return HotbarUtil.findInHotBar(stack -> items.contains(stack.method_7909()));
    }

    public static SlotSearchResult findItemInHotBar(class_1792 ... items) {
        return HotbarUtil.findItemInHotBar(Arrays.asList(items));
    }

    public static SlotSearchResult findInInventory(Searcher searcher) {
        if (HotbarUtil.mc.field_1724 != null) {
            for (int i = 35; i >= 0; --i) {
                class_1799 stack = HotbarUtil.mc.field_1724.method_31548().method_5438(i);
                if (!searcher.isValid(stack)) continue;
                return new SlotSearchResult(i, true, stack);
            }
        }
        return SlotSearchResult.notFound();
    }

    public static SlotSearchResult findItemInInventory(List<class_1792> items) {
        return HotbarUtil.findInInventory(stack -> items.contains(stack.method_7909()));
    }

    public static SlotSearchResult findItemInInventory(class_1792 ... items) {
        return HotbarUtil.findItemInInventory(Arrays.asList(items));
    }

    public static SlotSearchResult findBlockInHotBar(@NotNull List<class_2248> blocks) {
        return HotbarUtil.findItemInHotBar(blocks.stream().map(class_2248::method_8389).toList());
    }

    public static SlotSearchResult findBlockInHotBar(class_2248 ... blocks) {
        return HotbarUtil.findItemInHotBar(Arrays.stream(blocks).map(class_2248::method_8389).toList());
    }

    public static SlotSearchResult findBlockInInventory(@NotNull List<class_2248> blocks) {
        return HotbarUtil.findItemInInventory(blocks.stream().map(class_2248::method_8389).toList());
    }

    public static SlotSearchResult findBlockInInventory(class_2248 ... blocks) {
        return HotbarUtil.findItemInInventory(Arrays.stream(blocks).map(class_2248::method_8389).toList());
    }

    public static void saveSlot() {
        if (HotbarUtil.mc.field_1724 != null) {
            cachedSlot = HotbarUtil.mc.field_1724.method_31548().field_7545;
        }
    }

    public static void returnSlot() {
        if (cachedSlot != -1) {
            HotbarUtil.switchTo(cachedSlot);
        }
        cachedSlot = -1;
    }

    public static void saveAndSwitchTo(int slot) {
        HotbarUtil.saveSlot();
        HotbarUtil.switchTo(slot);
    }

    public static void switchTo(int slot) {
        if (HotbarUtil.mc.field_1724 == null || mc.method_1562() == null || slot < 0 || slot > 8) {
            return;
        }
        if (HotbarUtil.mc.field_1724.method_31548().field_7545 == slot) {
            return;
        }
        HotbarUtil.mc.field_1724.method_31548().field_7545 = slot;
        mc.method_1562().method_52787((class_2596)new class_2868(slot));
    }

    public static void switchToSilent(int slot) {
        if (HotbarUtil.mc.field_1724 == null || mc.method_1562() == null || slot < 0 || slot > 8) {
            return;
        }
        mc.method_1562().method_52787((class_2596)new class_2868(slot));
    }

    public static SlotSearchResult getAntiWeaknessItem() {
        if (HotbarUtil.mc.field_1724 == null) {
            return SlotSearchResult.notFound();
        }
        class_1792 mainHand = HotbarUtil.mc.field_1724.method_6047().method_7909();
        if (mainHand instanceof class_1829 || mainHand instanceof class_1810 || mainHand instanceof class_1743 || mainHand instanceof class_1821) {
            return new SlotSearchResult(HotbarUtil.mc.field_1724.method_31548().field_7545, true, HotbarUtil.mc.field_1724.method_6047());
        }
        return HotbarUtil.findInHotBar(stack -> stack.method_7909() instanceof class_1829 || stack.method_7909() instanceof class_1810 || stack.method_7909() instanceof class_1743 || stack.method_7909() instanceof class_1821);
    }

    public static float getHitDamage(@NotNull class_1799 weapon, class_1657 entity) {
        if (HotbarUtil.mc.field_1724 == null || HotbarUtil.mc.field_1687 == null) {
            return 0.0f;
        }
        float baseDamage = HotbarUtil.getBaseAttackDamage(weapon);
        if (HotbarUtil.mc.field_1724.field_6017 > 0.0f) {
            baseDamage += baseDamage / 2.0f;
        }
        if (HotbarUtil.mc.field_1724.method_6059(class_1294.field_5910)) {
            int strength = Objects.requireNonNull(HotbarUtil.mc.field_1724.method_6112(class_1294.field_5910)).method_5578() + 1;
            baseDamage += 3.0f * (float)strength;
        }
        return class_1280.method_5496((class_1309)entity, (float)baseDamage, (class_1282)HotbarUtil.mc.field_1687.method_48963().method_48830(), (float)entity.method_6096(), (float)((float)entity.method_45325(class_5134.field_23725)));
    }

    public static SlotSearchResult findBedInHotBar() {
        return HotbarUtil.findInHotBar(stack -> stack.method_7909() instanceof class_1748);
    }

    public static SlotSearchResult findBed() {
        return HotbarUtil.findInInventory(stack -> stack.method_7909() instanceof class_1748);
    }

    public static class_1792 getItem(String name) {
        if (name == null) {
            return class_1802.field_8162;
        }
        String normalized = name.toLowerCase();
        for (class_2248 block : class_7923.field_41175) {
            if (!block.method_63499().replace("block.minecraft.", "").equals(normalized)) continue;
            return class_1792.method_7867((class_2248)block);
        }
        for (class_1792 item : class_7923.field_41178) {
            if (!item.method_7876().replace("item.minecraft.", "").equals(normalized)) continue;
            return item;
        }
        return class_1802.field_8831;
    }

    public static int getBedsCount() {
        if (HotbarUtil.mc.field_1724 == null) {
            return 0;
        }
        int counter = 0;
        for (int i = 0; i < HotbarUtil.mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 stack = HotbarUtil.mc.field_1724.method_31548().method_5438(i);
            if (!(stack.method_7909() instanceof class_1748)) continue;
            counter += stack.method_7947();
        }
        return counter;
    }

    private static SlotSearchResult findBest(Searcher searcher, boolean hotbarOnly) {
        if (HotbarUtil.mc.field_1724 == null) {
            return SlotSearchResult.notFound();
        }
        int bestSlot = -1;
        float bestDamage = 0.0f;
        int end = hotbarOnly ? 8 : 35;
        for (int i = 0; i <= end; ++i) {
            float damage;
            class_1799 stack = HotbarUtil.mc.field_1724.method_31548().method_5438(i);
            if (!searcher.isValid(stack) || !((damage = HotbarUtil.getBaseAttackDamage(stack)) > bestDamage)) continue;
            bestDamage = damage;
            bestSlot = i;
        }
        return bestSlot == -1 ? SlotSearchResult.notFound() : new SlotSearchResult(bestSlot, true, HotbarUtil.mc.field_1724.method_31548().method_5438(bestSlot));
    }

    private static float getBaseAttackDamage(class_1799 stack) {
        class_9285 component = (class_9285)stack.method_57825(class_9334.field_49636, (Object)class_9285.field_49326);
        double damage = 1.0;
        for (class_9285.class_9287 entry : component.comp_2393()) {
            if (!entry.comp_2395().equals((Object)class_5134.field_23721)) continue;
            damage += entry.comp_2396().comp_2449();
        }
        return (float)damage;
    }

    public static boolean isHolding(class_1792 item) {
        return HotbarUtil.mc.field_1724 != null && (HotbarUtil.mc.field_1724.method_6047().method_31574(item) || HotbarUtil.mc.field_1724.method_6079().method_31574(item));
    }

    public static class_1268 getHand(class_1792 item) {
        if (HotbarUtil.mc.field_1724 == null) {
            return null;
        }
        if (HotbarUtil.mc.field_1724.method_6079().method_31574(item)) {
            return class_1268.field_5810;
        }
        if (HotbarUtil.mc.field_1724.method_6047().method_31574(item)) {
            return class_1268.field_5808;
        }
        return null;
    }

    private HotbarUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static interface Searcher {
        public boolean isValid(class_1799 var1);
    }
}
