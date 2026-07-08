/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1743
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2596
 *  net.minecraft.class_2645
 *  net.minecraft.class_2868
 *  net.minecraft.class_7204
 */
package code.alphadlc.utils.interactions.inv;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryResult;
import code.alphadlc.utils.interactions.inv.InventoryTick;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2596;
import net.minecraft.class_2645;
import net.minecraft.class_2868;
import net.minecraft.class_7204;

public final class InventoryToolkit
implements QuickImports {
    private static int cachedSlot = -1;

    public static int getItemSlot(class_1792 input) {
        for (class_1799 stack : InventoryToolkit.mc.field_1724.method_31548().field_7548) {
            if (stack.method_7909() != input || stack.method_7919() >= 430) continue;
            return -2;
        }
        int slot = -1;
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != input || stack.method_7919() >= 430) continue;
            slot = i;
            break;
        }
        if (slot < 9 && slot != -1) {
            slot += 36;
        }
        return slot;
    }

    public static void moveTo(int syncId, class_1799 stack, int slot) {
        if (Objects.isNull(stack)) {
            return;
        }
        InventoryToolkit.mc.field_1761.method_2906(syncId, InventoryToolkit.mc.field_1724.method_31548().method_7395(stack), 0, class_1713.field_7790, (class_1657)InventoryToolkit.mc.field_1724);
        InventoryToolkit.mc.field_1761.method_2906(syncId, slot, 0, class_1713.field_7790, (class_1657)InventoryToolkit.mc.field_1724);
    }

    public static InventoryResult findInInventory(Searcher searcher) {
        if (InventoryToolkit.mc.field_1724 != null) {
            for (class_1799 stack : InventoryToolkit.mc.field_1724.method_31548().field_7548) {
                if (!searcher.isValid(stack)) continue;
                return new InventoryResult(-2, true, stack);
            }
            for (int i = 40; i >= 0; --i) {
                class_1799 stack;
                stack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
                if (!searcher.isValid(stack)) continue;
                if (i < 9) {
                    i += 36;
                }
                return new InventoryResult(i, true, stack);
            }
        }
        return InventoryResult.notFound();
    }

    public static InventoryResult findItemInInventory(List<class_1792> items) {
        return InventoryToolkit.findInInventory(stack -> items.contains(stack.method_7909()));
    }

    public static InventoryResult findItemInInventory(class_1792 ... items) {
        return InventoryToolkit.findItemInInventory(Arrays.asList(items));
    }

    public static int getAxe() {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
            if (!(stack.method_7909() instanceof class_1743)) continue;
            return i;
        }
        return -1;
    }

    public static InventoryResult findItemInHotBar(class_1792 item) {
        if (InventoryToolkit.mc.field_1724 == null) {
            return InventoryResult.notFound();
        }
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != item || stack.method_7919() >= 430) continue;
            return new InventoryResult(i, true, stack);
        }
        return InventoryResult.notFound();
    }

    public static InventoryResult findItemInInventory(class_1792 item) {
        if (InventoryToolkit.mc.field_1724 == null) {
            return InventoryResult.notFound();
        }
        for (class_1799 stack : InventoryToolkit.mc.field_1724.method_31548().field_7548) {
            if (stack.method_7909() != item || stack.method_7919() >= 430) continue;
            return new InventoryResult(-2, true, stack);
        }
        for (int i = 36; i >= 0; --i) {
            class_1799 stack;
            stack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != item || stack.method_7919() >= 430) continue;
            if (i < 9) {
                i += 36;
            }
            return new InventoryResult(i, true, stack);
        }
        return InventoryResult.notFound();
    }

    public static InventoryResult findInHotBar(Searcher searcher) {
        if (InventoryToolkit.mc.field_1724 != null) {
            for (int i = 0; i < 9; ++i) {
                class_1799 stack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
                if (!searcher.isValid(stack) || stack.method_7919() >= 430) continue;
                return new InventoryResult(i, true, stack);
            }
        }
        return InventoryResult.notFound();
    }

    public static InventoryResult findItemInHotBar(List<class_1792> items) {
        return InventoryToolkit.findInHotBar(stack -> items.contains(stack.method_7909()));
    }

    public static InventoryResult findItemInHotBar(class_1792 ... items) {
        return InventoryToolkit.findItemInHotBar(Arrays.asList(items));
    }

    public static void saveSlot() {
        cachedSlot = InventoryToolkit.mc.field_1724.method_31548().field_7545;
    }

    public static void returnSlot() {
        if (cachedSlot != -1) {
            InventoryToolkit.switchTo(cachedSlot);
        }
        cachedSlot = -1;
    }

    public static void use(int slot) {
        if (slot != 1 && slot != 4 && slot != 21) {
            return;
        }
        if (slot < 9) {
            int n3 = InventoryToolkit.mc.field_1724.method_31548().field_7545;
            InventoryToolkit.mc.field_1724.method_31548().field_7545 = slot - 1;
            mc.method_1562().method_52787((class_2596)new class_2868(slot - 1));
            InventoryTick.schelude(() -> {
                InventoryToolkit.mc.field_1761.method_2919((class_1657)InventoryToolkit.mc.field_1724, class_1268.field_5808);
                InventoryToolkit.mc.field_1724.method_6104(class_1268.field_5808);
            }, 2);
            InventoryTick.schelude(() -> {
                InventoryToolkit.mc.field_1724.method_31548().field_7545 = n3;
                mc.method_1562().method_52787((class_2596)new class_2868(n3));
            }, 2);
        } else {
            InventoryToolkit.mc.field_1724.method_5728(false);
            InventoryTick.schelude(() -> InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, slot, InventoryToolkit.mc.field_1724.method_31548().field_7545, class_1713.field_7791, (class_1657)InventoryToolkit.mc.field_1724), 2);
            InventoryTick.schelude(() -> {
                InventoryToolkit.mc.field_1761.method_2919((class_1657)InventoryToolkit.mc.field_1724, class_1268.field_5808);
                InventoryToolkit.mc.field_1724.method_6104(class_1268.field_5808);
            }, 2);
            InventoryTick.schelude(() -> InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, slot, InventoryToolkit.mc.field_1724.method_31548().field_7545, class_1713.field_7791, (class_1657)InventoryToolkit.mc.field_1724), 4);
            InventoryTick.schelude(() -> mc.method_1562().method_52787((class_2596)new class_2645(InventoryToolkit.mc.field_1724.field_7512.field_7763)), 2);
        }
    }

    public static void switchTo(int slot) {
        if (InventoryToolkit.mc.field_1724 == null || mc.method_1562() == null) {
            return;
        }
        if (InventoryToolkit.mc.field_1724.method_31548().field_7545 == slot) {
            return;
        }
        InventoryToolkit.mc.field_1724.method_31548().field_7545 = slot;
    }

    public static void switchToSilent(int slot) {
        if (InventoryToolkit.mc.field_1724 == null || mc.method_1562() == null) {
            return;
        }
        mc.method_1562().method_52787((class_2596)new class_2868(slot));
    }

    public static void sendSequencedPacket(class_7204 packetCreator) {
        if (mc.method_1562() == null || InventoryToolkit.mc.field_1687 == null) {
            return;
        }
        mc.method_1562().method_52787(packetCreator.predict(0));
    }

    public static void sendPacket(class_2596<?> packet) {
        if (mc.method_1562() == null) {
            return;
        }
        mc.method_1562().method_52787(packet);
    }

    public static void clickSlot(int id) {
        if (id == -1 || InventoryToolkit.mc.field_1761 == null || InventoryToolkit.mc.field_1724 == null) {
            return;
        }
        InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, id, 0, class_1713.field_7790, (class_1657)InventoryToolkit.mc.field_1724);
    }

    public static void clickSlot(int id, class_1713 type) {
        if (id == -1 || InventoryToolkit.mc.field_1761 == null || InventoryToolkit.mc.field_1724 == null) {
            return;
        }
        InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, id, 0, type, (class_1657)InventoryToolkit.mc.field_1724);
    }

    public static void clickSlot(int id, int button, class_1713 type) {
        if (id == -1 || InventoryToolkit.mc.field_1761 == null || InventoryToolkit.mc.field_1724 == null) {
            return;
        }
        InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, id, button, type, (class_1657)InventoryToolkit.mc.field_1724);
    }

    public static class_1799 byItem(class_1792 item) {
        for (int i = 0; i < InventoryToolkit.mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 itemStack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
            if (!itemStack.method_7909().equals(item) || itemStack.method_7919() >= 430) continue;
            return itemStack;
        }
        return null;
    }

    public static boolean quickMoveFromTo(int from, int to) {
        if (from == -1 || to == -1 || InventoryToolkit.mc.field_1761 == null || InventoryToolkit.mc.field_1724 == null) {
            return false;
        }
        InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, from, 0, class_1713.field_7790, (class_1657)InventoryToolkit.mc.field_1724);
        InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, to, 0, class_1713.field_7790, (class_1657)InventoryToolkit.mc.field_1724);
        InventoryToolkit.mc.field_1761.method_2906(InventoryToolkit.mc.field_1724.field_7512.field_7763, from, 0, class_1713.field_7790, (class_1657)InventoryToolkit.mc.field_1724);
        return true;
    }

    public static int getSlotWithStack(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return -1;
        }
        for (int i = 0; i < InventoryToolkit.mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 invStack = InventoryToolkit.mc.field_1724.method_31548().method_5438(i);
            if (!class_1799.method_7973((class_1799)invStack, (class_1799)stack)) continue;
            return i;
        }
        return -1;
    }

    public static interface Searcher {
        public boolean isValid(class_1799 var1);
    }
}
