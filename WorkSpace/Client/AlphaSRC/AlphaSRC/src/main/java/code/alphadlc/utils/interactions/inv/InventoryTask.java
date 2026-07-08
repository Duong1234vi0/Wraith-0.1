/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMaps
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1291
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1713
 *  net.minecraft.class_1730
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1844
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2813
 *  net.minecraft.class_2815
 *  net.minecraft.class_4081
 *  net.minecraft.class_4174
 *  net.minecraft.class_6880
 *  net.minecraft.class_7923
 *  net.minecraft.class_9334
 */
package code.alphadlc.utils.interactions.inv;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.item.ItemTask;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.math.script.Script;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1730;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2813;
import net.minecraft.class_2815;
import net.minecraft.class_4081;
import net.minecraft.class_4174;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import net.minecraft.class_9334;

public class InventoryTask
implements QuickImports {
    private static final Script swapAndUseScript = new Script();

    public static void moveItem(class_1735 from, int to) {
        if (from != null) {
            InventoryTask.moveItem(from.field_7874, to, false, false);
        }
    }

    public static void moveItem(class_1735 from, int to, boolean task) {
        InventoryTask.moveItem(from, to, task, false);
    }

    public static void moveItem(class_1735 from, int to, boolean task, boolean updateInventory) {
        if (from != null) {
            InventoryTask.moveItem(from.field_7874, to, task, updateInventory);
        }
    }

    public static void moveItem(int from, int to, boolean task, boolean updateInventory) {
        if (from == to || from == -1) {
            return;
        }
        int count = Math.toIntExact(InventoryTask.slots().count()) - 10;
        if (from >= count && count == 36) {
            if (task) {
                InventoryFlowManager.addTask(() -> InventoryTask.clickSlot(to, from - count, class_1713.field_7791, false));
            } else {
                InventoryTask.clickSlot(to, from - count, class_1713.field_7791, false);
            }
            return;
        }
        if (task) {
            InventoryFlowManager.addTask(() -> InventoryTask.moveItem(from, to, updateInventory));
        } else {
            InventoryTask.moveItem(from, to, updateInventory);
        }
    }

    public static void moveItem(int from, int to, boolean updateInventory) {
        InventoryTask.clickSlot(from, 0, class_1713.field_7791, false);
        InventoryTask.clickSlot(to, 0, class_1713.field_7791, false);
        InventoryTask.clickSlot(from, 0, class_1713.field_7791, false);
        if (updateInventory) {
            InventoryTask.updateSlots();
        }
    }

    public static void swapHand(class_1735 slot, class_1268 hand, boolean task) {
        InventoryTask.swapHand(slot, hand, task, false);
    }

    public static void swapHand(class_1735 slot, class_1268 hand, boolean task, boolean updateInventory) {
        int button;
        if (slot == null || slot.field_7874 == -1 || hand.equals((Object)class_1268.field_5810) && !(slot.field_7871 instanceof class_1661) && !(slot.field_7871 instanceof class_1730)) {
            return;
        }
        int n = button = hand.equals((Object)class_1268.field_5808) ? InventoryTask.mc.field_1724.method_31548().field_7545 : 40;
        if (task) {
            InventoryFlowManager.addTask(() -> InventoryTask.swap(slot, button, updateInventory));
        } else {
            InventoryTask.swap(slot, button, updateInventory);
        }
    }

    public static void swap(class_1735 slot, int button, boolean updateInventory) {
        InventoryTask.clickSlot(slot, button, class_1713.field_7791, false);
        if (updateInventory && GuiMove.mode.isSelected("Funtime")) {
            InventoryTask.updateSlots();
        }
    }

    public static void swapAndUse(class_1735 slot, String text, boolean task) {
        if (slot == null) {
            Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + text + String.valueOf(class_124.field_1070) + " - \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!", 3000);
            return;
        }
        if (task) {
            InventoryFlowManager.addTask(() -> InventoryTask.swapAndUse(slot, MathAngle.cameraAngle()));
        } else {
            InventoryTask.swapAndUse(slot, MathAngle.cameraAngle());
        }
    }

    public static void swapAndUse(class_1792 item, boolean task) {
        InventoryTask.swapAndUse(item, MathAngle.cameraAngle(), task);
    }

    public static void clickSlot(int id, int button, class_1713 type) {
        if (id == -1 || InventoryTask.mc.field_1761 == null || InventoryTask.mc.field_1724 == null) {
            return;
        }
        InventoryTask.mc.field_1761.method_2906(InventoryTask.mc.field_1724.field_7512.field_7763, id, button, type, (class_1657)InventoryTask.mc.field_1724);
    }

    public static void switchTo(int slot) {
        if (InventoryTask.mc.field_1724 == null || mc.method_1562() == null) {
            return;
        }
        if (InventoryTask.mc.field_1724.method_31548().field_7545 == slot) {
            return;
        }
        InventoryTask.mc.field_1724.method_31548().field_7545 = slot;
    }

    public static void swapSlot(class_1735 targetSlot) {
        InventoryTask.swapSlot(targetSlot, false);
    }

    public static void swapSlot(class_1735 targetSlot, boolean task) {
        if (targetSlot == null || targetSlot.field_7874 == -1 || InventoryTask.mc.field_1724 == null) {
            return;
        }
        InventoryTask.mc.field_1724.method_31548().field_7545 = targetSlot.field_7874;
    }

    public static void swapSlot(class_1792 item) {
        InventoryTask.swapSlot(InventoryTask.getSlot(item));
    }

    public static void swapSlot(class_1792 item, boolean task) {
        InventoryTask.swapSlot(InventoryTask.getSlot(item), task);
    }

    public static void swapAndUse(class_1792 item, String searchName, boolean task) {
        float cooldownProgress = ItemTask.getCooldownProgress(item);
        if (cooldownProgress > 0.0f) {
            String time = Calculate.round(cooldownProgress, 0.1) + "\u0441";
            Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - \u0432 \u043a\u0434 \u0435\u0449\u0435 " + time, 2000);
            return;
        }
        class_1735 slot = InventoryTask.getSlot((class_1735 s) -> s.method_7677().method_7909().equals(item) && InventoryTask.getCleanName(s.method_7677().method_7964()).contains(searchName.toLowerCase()));
        if (slot == null) {
            Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!", 2000);
            return;
        }
        if (task) {
            InventoryFlowManager.addTask(() -> InventoryTask.swapAndUse(slot, MathAngle.cameraAngle()));
        } else {
            InventoryTask.swapAndUse(slot, MathAngle.cameraAngle());
        }
    }

    public static void swapAndUse(class_1792 item, Turns angle, boolean task) {
        float cooldownProgress = ItemTask.getCooldownProgress(item);
        if (cooldownProgress > 0.0f) {
            String time = Calculate.round(cooldownProgress, 0.1) + "\u0441";
            Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - \u0432 \u043a\u0434 \u0435\u0449\u0435 " + time, 2000);
            return;
        }
        class_1735 slot = InventoryTask.getSlot(item);
        if (slot == null) {
            Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!", 2000);
            return;
        }
        if (task) {
            InventoryFlowManager.addTask(() -> InventoryTask.swapAndUse(slot, angle));
        } else {
            InventoryTask.swapAndUse(slot, angle);
        }
    }

    public static void updateSwapAndUseScript() {
        swapAndUseScript.update();
    }

    public static void swapAndUse(class_1735 slot, Turns angle) {
        int delay2;
        int delay1;
        if (GuiMove.mode.isSelected("Holyworld")) {
            delay1 = 2;
            delay2 = 1;
        } else if (GuiMove.mode.isSelected("Funtime")) {
            delay1 = 0;
            delay2 = 1;
        } else {
            delay1 = 0;
            delay2 = 0;
        }
        swapAndUseScript.cleanup().addTickStep(0, () -> InventoryTask.swapHand(slot, class_1268.field_5808, false)).addTickStep(delay1, () -> PlayerInteractionHelper.interactItem(class_1268.field_5808)).addTickStep(delay2, () -> InventoryTask.swapHand(slot, class_1268.field_5808, false, true));
    }

    public static void updateSlots() {
        class_1703 screenHandler = InventoryTask.mc.field_1724.field_7512;
        class_1799 stack = ((class_1792)class_7923.field_41178.method_10200(Calculate.getRandom(0, 100))).method_7854();
        InventoryTask.mc.field_1724.field_3944.method_52787((class_2596)new class_2813(screenHandler.field_7763, screenHandler.method_37421(), 0, 0, class_1713.field_7793, stack, Int2ObjectMaps.singleton((int)0, (Object)stack)));
    }

    public static void closeScreen(boolean packet) {
        if (packet) {
            InventoryTask.mc.field_1724.field_3944.method_52787((class_2596)new class_2815(InventoryTask.mc.field_1724.field_7512.field_7763));
        } else {
            InventoryTask.mc.field_1724.method_7346();
        }
    }

    public static void clickSlot(class_1735 slot, int button, class_1713 clickType, boolean silent) {
        if (slot != null) {
            InventoryTask.clickSlot(slot.field_7874, button, clickType, silent);
        }
    }

    public static void clickSlot(int slotId, int buttonId, class_1713 clickType, boolean silent) {
        InventoryTask.clickSlot(slotId, buttonId, clickType, silent, false);
    }

    public static void clickSlot(int slotId, int buttonId, class_1713 clickType, boolean silent, boolean task) {
        InventoryTask.clickSlot(InventoryTask.mc.field_1724.field_7512.field_7763, slotId, buttonId, clickType, silent, task);
    }

    public static void clickSlot(int windowId, int slotId, int buttonId, class_1713 clickType, boolean silent, boolean task) {
        if (task) {
            InventoryFlowManager.addTask(() -> {
                InventoryTask.mc.field_1761.method_2906(windowId, slotId, buttonId, clickType, (class_1657)InventoryTask.mc.field_1724);
                if (silent) {
                    InventoryTask.mc.field_1724.field_7512.method_7593(slotId, buttonId, clickType, (class_1657)InventoryTask.mc.field_1724);
                }
            });
        } else {
            InventoryTask.mc.field_1761.method_2906(windowId, slotId, buttonId, clickType, (class_1657)InventoryTask.mc.field_1724);
            if (silent) {
                InventoryTask.mc.field_1724.field_7512.method_7593(slotId, buttonId, clickType, (class_1657)InventoryTask.mc.field_1724);
            }
        }
    }

    public static void clickSlot(int windowId, int slotId, int buttonId, class_1713 clickType, boolean silent) {
        InventoryTask.clickSlot(windowId, slotId, buttonId, clickType, silent, false);
    }

    public static class_1735 getSlot(class_1792 item) {
        return InventoryTask.getSlot(item, (class_1735 s) -> true);
    }

    public static class_1735 getSlot(class_1792 item, Predicate<class_1735> filter) {
        return InventoryTask.getSlot(item, Comparator.comparingInt(s -> 0), filter);
    }

    public static class_1735 getSlot(Predicate<class_1735> filter) {
        return InventoryTask.slots().filter(filter).findFirst().orElse(null);
    }

    public static class_1735 getSlot(Predicate<class_1735> filter, Comparator<class_1735> comparator) {
        return InventoryTask.slots().filter(filter).max(comparator).orElse(null);
    }

    public static class_1735 getSlot(class_1792 item, Comparator<class_1735> comparator, Predicate<class_1735> filter) {
        return InventoryTask.slots().filter(s -> s.method_7677().method_7909().equals(item)).filter(filter).max(comparator).orElse(null);
    }

    public static class_1735 getFoodMaxSaturationSlot() {
        return InventoryTask.slots().filter(s -> s.method_7677().method_57824(class_9334.field_50075) != null && !((class_4174)s.method_7677().method_57824(class_9334.field_50075)).comp_2493()).max(Comparator.comparingDouble(s -> ((class_4174)s.method_7677().method_57824(class_9334.field_50075)).comp_2492())).orElse(null);
    }

    public static class_1735 getSlot(List<class_1792> item) {
        return InventoryTask.slots().filter(s -> item.contains(s.method_7677().method_7909())).findFirst().orElse(null);
    }

    public static class_1735 getPotion(class_6880<class_1291> effect) {
        return InventoryTask.slots().filter(s -> {
            class_1844 component = (class_1844)s.method_7677().method_57824(class_9334.field_49651);
            if (component == null) {
                return false;
            }
            return StreamSupport.stream(component.method_57397().spliterator(), false).anyMatch(e -> e.method_5579().equals((Object)effect));
        }).findFirst().orElse(null);
    }

    public static class_1735 getPotionFromCategory(class_4081 category) {
        return InventoryTask.slots().filter(s -> {
            class_1799 stack = s.method_7677();
            class_1844 component = (class_1844)stack.method_57824(class_9334.field_49651);
            if (!stack.method_7909().equals(class_1802.field_8436) || component == null) {
                return false;
            }
            class_4081 category2 = category.equals((Object)class_4081.field_18271) ? class_4081.field_18272 : class_4081.field_18271;
            long effects = StreamSupport.stream(component.method_57397().spliterator(), false).filter(e -> ((class_1291)e.method_5579().comp_349()).method_18792().equals((Object)category)).count();
            long effects2 = StreamSupport.stream(component.method_57397().spliterator(), false).filter(e -> ((class_1291)e.method_5579().comp_349()).method_18792().equals((Object)category2)).count();
            return effects >= effects2;
        }).findFirst().orElse(null);
    }

    public static int getInventoryCount(class_1792 item) {
        return IntStream.range(0, 45).filter(i -> Objects.requireNonNull(InventoryTask.mc.field_1724).method_31548().method_5438(i).method_7909().equals(item)).map(i -> InventoryTask.mc.field_1724.method_31548().method_5438(i).method_7947()).sum();
    }

    public static int findFreeHotbarSlot() {
        for (int i = 0; i < 9; ++i) {
            if (!InventoryTask.mc.field_1724.method_31548().method_5438(i).method_7960()) continue;
            return i;
        }
        return 8;
    }

    public static int findHotbarSlot(class_1792 item) {
        for (int i = 0; i < 9; ++i) {
            if (!InventoryTask.mc.field_1724.method_31548().method_5438(i).method_31574(item)) continue;
            return i;
        }
        return -1;
    }

    public static int findInventorySlot(class_1792 item) {
        for (int i = 9; i < 36; ++i) {
            if (!InventoryTask.mc.field_1724.method_31548().method_5438(i).method_31574(item)) continue;
            return i;
        }
        return -1;
    }

    public static void swapSlots(int fromSlot, int toSlot) {
        if (InventoryTask.mc.field_1761 == null || InventoryTask.mc.field_1724.field_7512 == null) {
            return;
        }
        InventoryTask.mc.field_1761.method_2906(InventoryTask.mc.field_1724.field_7512.field_7763, fromSlot, toSlot, class_1713.field_7791, (class_1657)InventoryTask.mc.field_1724);
    }

    public static void useItemFromSlotMOMENTALNO(int slot) {
        if (InventoryTask.mc.field_1724 == null || InventoryTask.mc.field_1761 == null) {
            return;
        }
        class_1799 stack = InventoryTask.mc.field_1724.method_31548().method_5438(slot);
        if (!stack.method_7960() && !InventoryTask.mc.field_1724.method_7357().method_7904(stack)) {
            int prevSlot = InventoryTask.mc.field_1724.method_31548().field_7545;
            InventoryTask.mc.field_1724.method_31548().field_7545 = slot;
            InventoryTask.mc.field_1761.method_2919((class_1657)InventoryTask.mc.field_1724, class_1268.field_5808);
            InventoryTask.mc.field_1724.method_6104(class_1268.field_5808);
            InventoryTask.mc.field_1724.method_31548().field_7545 = prevSlot;
        }
    }

    public static int getHotbarItems(List<class_1792> items) {
        return IntStream.range(0, 9).filter(i -> items.contains(InventoryTask.mc.field_1724.method_31548().method_5438(i).method_7909())).findFirst().orElse(-1);
    }

    public static int getHotbarSlotId(IntPredicate filter) {
        return IntStream.range(0, 9).filter(filter).findFirst().orElse(-1);
    }

    public static int getCount(Predicate<class_1735> filter) {
        return InventoryTask.slots().filter(filter).mapToInt(s -> s.method_7677().method_7947()).sum();
    }

    public static class_1735 mainHandSlot() {
        long count = InventoryTask.slots().count();
        int i = count == 46L ? 10 : 9;
        return InventoryTask.slots().toList().get(Math.toIntExact(count - (long)i + (long)InventoryTask.mc.field_1724.method_31548().field_7545));
    }

    public static boolean isServerScreen() {
        return InventoryTask.slots().toList().size() != 46;
    }

    public static Stream<class_1735> slots() {
        return InventoryTask.mc.field_1724.field_7512.field_7761.stream();
    }

    public static void selectCompass() {
        class_1735 slot = InventoryTask.getSlot(class_1802.field_8251);
        if (slot != null) {
            InventoryTask.mc.field_1724.method_31548().field_7545 = slot.field_7874 < 9 ? slot.field_7874 : 0;
            InventoryTask.swapHand(slot, class_1268.field_5808, false, true);
        }
    }

    public static String getCleanName(class_2561 text) {
        if (text == null) {
            return "";
        }
        String name = text.getString();
        if (name == null) {
            return "";
        }
        return name.replaceAll("\u00a7[0-9a-fk-or]", "").toLowerCase();
    }
}
