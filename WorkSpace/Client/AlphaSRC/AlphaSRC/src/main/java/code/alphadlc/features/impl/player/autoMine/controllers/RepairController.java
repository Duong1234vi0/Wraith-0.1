/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1810
 *  net.minecraft.class_2371
 *  net.minecraft.class_2561
 *  net.minecraft.class_437
 *  net.minecraft.class_476
 *  net.minecraft.class_6880
 *  net.minecraft.class_9290
 *  net.minecraft.class_9304
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.player.autoMine.controllers;

import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryResult;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.inv.InventoryToolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1810;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_6880;
import net.minecraft.class_9290;
import net.minecraft.class_9304;
import net.minecraft.class_9334;

public class RepairController
implements QuickImports {
    static final int DURABILITY_THRESHOLD_PERCENT = 15;
    static final int MAX_XP_STACK_PRICE = 100000;
    static final int XP_STACKS_TO_BUY = 3;
    static final int MIN_STACK_SIZE = 64;
    static final long WAITING_AFTER_BUY_MS = 2500L;
    static final int SLOTS_TO_CLEAR = 3;
    static final long AUCTION_ACTION_DELAY = 350L;
    static final long POST_PURCHASE_DELAY_MS = 1000L;
    static final long THROW_DELAY = 0L;
    static final long POST_SWAP_DELAY_MS = 250L;
    static final long POST_COMMAND_DELAY_MS = 2000L;
    static final long AUCTION_LOAD_DELAY = 3000L;
    static final long BUY_TIMEOUT = 30000L;
    static final Pattern PRICE_PATTERN = Pattern.compile("(?:\\$\\s*([\\d,.\\s]+)|([\\d,.\\s]+)\\s*\\$|(?:\u0426\u0435\u043d\u0430|Price)[:\\s]*([\\d,.\\s]+))");
    private RepairPhase phase = RepairPhase.IDLE;
    private long phaseStartTime = 0L;
    private long lastActionTime = 0L;
    private long lastPurchaseTime = 0L;
    private int stacksBought = 0;
    private boolean auctionOpened = false;
    private int refreshCount = 0;
    private int throwTickCounter = 0;
    private long lastThrowTime = 0L;
    private boolean needReleaseUseKey = false;
    private int dropActionIndex = 0;
    private List<Integer> slotsToDrop = new ArrayList<Integer>();
    private int consolidateTicks = 0;
    private int consolidateTargetFree = 0;
    private int noItemsRetryCount = 0;

    public boolean needsRepair() {
        if (RepairController.mc.field_1724 == null) {
            return false;
        }
        class_1799 pickaxe = this.findPickaxe();
        if (pickaxe == null || pickaxe.method_7960()) {
            return false;
        }
        if (!pickaxe.method_7963()) {
            return false;
        }
        if (!this.hasMending(pickaxe)) {
            return false;
        }
        int maxDurability = pickaxe.method_7936();
        int currentDamage = pickaxe.method_7919();
        int remaining = maxDurability - currentDamage;
        int percent = maxDurability > 0 ? remaining * 100 / maxDurability : 100;
        return percent <= 15;
    }

    private class_1799 findPickaxe() {
        if (RepairController.mc.field_1724 == null) {
            return null;
        }
        class_1799 mainHand = RepairController.mc.field_1724.method_6047();
        if (mainHand.method_7909() instanceof class_1810) {
            return mainHand;
        }
        InventoryResult result = InventoryToolkit.findInInventory(stack -> stack.method_7909() instanceof class_1810);
        return result != null && result.slot() != -1 ? result.stack() : null;
    }

    private boolean hasMending(class_1799 stack) {
        class_9304 enchantments = (class_9304)stack.method_57824(class_9334.field_49633);
        if (enchantments == null || enchantments.method_57543()) {
            return false;
        }
        for (class_6880 entry : enchantments.method_57534()) {
            String id = entry.method_55840();
            if (id == null || !id.toLowerCase().contains("mending")) continue;
            return true;
        }
        return false;
    }

    public boolean isPickaxeFullyRepaired() {
        if (RepairController.mc.field_1724 == null) {
            return true;
        }
        class_1799 pickaxe = this.findPickaxe();
        if (pickaxe == null || pickaxe.method_7960()) {
            return true;
        }
        if (!pickaxe.method_7963()) {
            return true;
        }
        return pickaxe.method_7919() <= 5;
    }

    public int getPickaxeDurabilityPercent() {
        class_1799 pickaxe = this.findPickaxe();
        if (pickaxe == null || !pickaxe.method_7963()) {
            return 100;
        }
        int max = pickaxe.method_7936();
        int dmg = pickaxe.method_7919();
        return max > 0 ? (max - dmg) * 100 / max : 100;
    }

    public void startRepair() {
        ChatMessage.brandmessage("[AutoMine Repair] \u041d\u0430\u0447\u0438\u043d\u0430\u044e \u043f\u043e\u0447\u0438\u043d\u043a\u0443 \u043a\u0438\u0440\u043a\u0438");
        this.phase = RepairPhase.CLEARING_INVENTORY;
        this.phaseStartTime = System.currentTimeMillis();
        this.lastActionTime = 0L;
        this.stacksBought = 0;
        this.auctionOpened = false;
        this.refreshCount = 0;
        this.throwTickCounter = 0;
        this.lastThrowTime = 0L;
        this.dropActionIndex = 0;
        this.slotsToDrop.clear();
        this.consolidateTicks = 0;
        this.consolidateTargetFree = 0;
        this.noItemsRetryCount = 0;
    }

    public boolean tickRepair() {
        if (RepairController.mc.field_1724 == null) {
            return true;
        }
        long now = System.currentTimeMillis();
        if (this.phase == RepairPhase.DROPPING_ITEMS || this.phase == RepairPhase.CONSOLIDATING_DIAMONDS) {
            RepairController.mc.field_1724.method_36457(0.0f);
        } else if (this.phase == RepairPhase.SWAPPING_PICKAXE_AND_XP || this.phase == RepairPhase.THROWING_XP || this.phase == RepairPhase.RETURNING_PICKAXE) {
            RepairController.mc.field_1724.method_36457(90.0f);
        }
        switch (this.phase.ordinal()) {
            case 1: {
                this.tickClearingInventory(now);
                break;
            }
            case 2: {
                this.tickDroppingItems(now);
                break;
            }
            case 3: {
                this.tickConsolidatingDiamonds(now);
                break;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                this.tickBuying();
                break;
            }
            case 8: {
                if (now - this.phaseStartTime < 2500L) break;
                ChatMessage.brandmessage("[AutoMine Repair] \u041d\u0430\u0447\u0438\u043d\u0430\u044e \u043f\u043e\u0447\u0438\u043d\u043a\u0443");
                this.phase = RepairPhase.SWAPPING_PICKAXE_AND_XP;
                break;
            }
            case 9: {
                this.tickSwappingPickaxeAndXp(now);
                break;
            }
            case 10: {
                this.tickThrowingXp(now);
                break;
            }
            case 11: {
                this.tickReturningPickaxe(now);
                break;
            }
            case 12: {
                return true;
            }
            default: {
                this.phase = RepairPhase.CLEARING_INVENTORY;
            }
        }
        return this.phase == RepairPhase.DONE_REPAIR;
    }

    private void tickClearingInventory(long now) {
        if (this.hasAnyXpBottles() && this.findPickaxeInvIndex() >= 0) {
            ChatMessage.brandmessage("[AutoMine Repair] \u0415\u0441\u0442\u044c \u043e\u043f\u044b\u0442 \u0438 \u043a\u0438\u0440\u043a\u0430 \u2014 \u0447\u0438\u043d\u044e");
            this.phase = RepairPhase.SWAPPING_PICKAXE_AND_XP;
            return;
        }
        int freeSlots = this.countFreeSlots();
        if (freeSlots >= 3) {
            ChatMessage.brandmessage("[AutoMine Repair] \u0414\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0441\u0432\u043e\u0431\u043e\u0434\u043d\u044b\u0445 \u0441\u043b\u043e\u0442\u043e\u0432: " + freeSlots);
            this.phase = RepairPhase.OPENING_AUCTION;
            this.startBuying();
            return;
        }
        this.slotsToDrop.clear();
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = RepairController.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || this.isKeepItem(stack)) continue;
            this.slotsToDrop.add(i);
        }
        if (!this.slotsToDrop.isEmpty()) {
            ChatMessage.brandmessage("[AutoMine Repair] \u0412\u044b\u043a\u0438\u0434\u044b\u0432\u0430\u044e " + this.slotsToDrop.size() + " \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432");
            this.phase = RepairPhase.DROPPING_ITEMS;
            this.dropActionIndex = 0;
        } else {
            int slotsToFree = 3 - freeSlots;
            if (slotsToFree > 0 && this.canConsolidateDiamonds()) {
                ChatMessage.brandmessage("[AutoMine Repair] \u041d\u0435\u0442 \u043c\u0435\u0441\u0442\u0430 \u2014 \u043a\u043e\u043d\u0441\u043e\u043b\u0438\u0434\u0438\u0440\u0443\u044e \u0434\u043e " + slotsToFree + " \u0441\u043b\u043e\u0442\u043e\u0432");
                this.phase = RepairPhase.CONSOLIDATING_DIAMONDS;
                this.consolidateTicks = 0;
                this.consolidateTargetFree = 3;
            } else {
                ChatMessage.brandmessage("[AutoMine Repair] \u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043e\u0441\u0432\u043e\u0431\u043e\u0434\u0438\u0442\u044c \u0441\u043b\u043e\u0442\u044b, \u043f\u0440\u043e\u0434\u043e\u043b\u0436\u0430\u044e");
                this.phase = RepairPhase.OPENING_AUCTION;
                this.startBuying();
            }
        }
    }

    private void tickDroppingItems(long now) {
        if (!this.canAct(now)) {
            return;
        }
        if (this.dropActionIndex >= this.slotsToDrop.size()) {
            this.phase = RepairPhase.CLEARING_INVENTORY;
            return;
        }
        int invIdx = this.slotsToDrop.get(this.dropActionIndex);
        if (RepairController.mc.field_1724.method_31548().method_5438(invIdx).method_7960()) {
            ++this.dropActionIndex;
            return;
        }
        class_1735 slot = this.getSlotFromInvIndex(invIdx);
        if (slot != null) {
            RepairController.mc.field_1761.method_2906(RepairController.mc.field_1724.field_7512.field_7763, slot.field_7874, 1, class_1713.field_7795, (class_1657)RepairController.mc.field_1724);
        }
        ++this.dropActionIndex;
        this.lastActionTime = now;
    }

    private void tickConsolidatingDiamonds(long now) {
        if (!this.canAct(now)) {
            return;
        }
        if (this.countFreeSlots() >= this.consolidateTargetFree) {
            this.phase = RepairPhase.CLEARING_INVENTORY;
            return;
        }
        ++this.consolidateTicks;
        if (this.consolidateTicks > 30) {
            this.phase = RepairPhase.CLEARING_INVENTORY;
            return;
        }
        class_1735 singleSlot = this.findSingleDiamondSlot();
        class_1735 stackSlot = this.findDiamondStackWithSpace();
        if (singleSlot == null || stackSlot == null || singleSlot.field_7874 == stackSlot.field_7874) {
            this.phase = RepairPhase.CLEARING_INVENTORY;
            return;
        }
        RepairController.mc.field_1761.method_2906(RepairController.mc.field_1724.field_7512.field_7763, singleSlot.field_7874, 0, class_1713.field_7790, (class_1657)RepairController.mc.field_1724);
        RepairController.mc.field_1761.method_2906(RepairController.mc.field_1724.field_7512.field_7763, stackSlot.field_7874, 0, class_1713.field_7790, (class_1657)RepairController.mc.field_1724);
        if (!RepairController.mc.field_1724.field_7512.method_34255().method_7960()) {
            RepairController.mc.field_1761.method_2906(RepairController.mc.field_1724.field_7512.field_7763, singleSlot.field_7874, 0, class_1713.field_7790, (class_1657)RepairController.mc.field_1724);
        }
        this.lastActionTime = now;
    }

    private int countFreeSlots() {
        int free = 0;
        for (int i = 0; i < 36; ++i) {
            if (!RepairController.mc.field_1724.method_31548().method_5438(i).method_7960()) continue;
            ++free;
        }
        return free;
    }

    private boolean hasAnyXpBottles() {
        for (int i = 0; i < 36; ++i) {
            if (RepairController.mc.field_1724.method_31548().method_5438(i).method_7909() != class_1802.field_8287) continue;
            return true;
        }
        return false;
    }

    private int findPickaxeInvIndex() {
        for (int i = 0; i < 36; ++i) {
            if (!(RepairController.mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1810)) continue;
            return i;
        }
        return -1;
    }

    private class_1735 getSlotFromInvIndex(int invIndex) {
        if (RepairController.mc.field_1724.field_7512 == null || invIndex < 0 || invIndex >= 36) {
            return null;
        }
        class_1661 inv = RepairController.mc.field_1724.method_31548();
        for (class_1735 slot : RepairController.mc.field_1724.field_7512.field_7761) {
            if (slot.field_7871 != inv || slot.method_34266() != invIndex) continue;
            return slot;
        }
        return null;
    }

    private boolean isKeepItem(class_1799 stack) {
        if (stack.method_7909() == class_1802.field_8477) {
            return true;
        }
        if (stack.method_7909() instanceof class_1810) {
            return true;
        }
        return stack.method_7909() == class_1802.field_8287;
    }

    private boolean canConsolidateDiamonds() {
        return this.findSingleDiamondSlot() != null && this.findDiamondStackWithSpace() != null;
    }

    private class_1735 findSingleDiamondSlot() {
        for (int i = 0; i < 36; ++i) {
            class_1735 slot;
            class_1799 stack = RepairController.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_8477 || stack.method_7947() != 1 || (slot = this.getSlotFromInvIndex(i)) == null) continue;
            return slot;
        }
        return null;
    }

    private class_1735 findDiamondStackWithSpace() {
        class_1735 best = null;
        int bestCount = 0;
        for (int i = 0; i < 36; ++i) {
            class_1735 slot;
            class_1799 stack = RepairController.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_8477 || stack.method_7947() >= 64 || stack.method_7947() <= bestCount || (slot = this.getSlotFromInvIndex(i)) == null) continue;
            best = slot;
            bestCount = stack.method_7947();
        }
        return best;
    }

    private void startBuying() {
        ChatMessage.brandmessage("[AutoMine Repair] \u0421\u0442\u0430\u0440\u0442 \u043f\u043e\u043a\u0443\u043f\u043a\u0438 \u043f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432 \u043e\u043f\u044b\u0442\u0430");
        this.phase = RepairPhase.OPENING_AUCTION;
        this.phaseStartTime = System.currentTimeMillis();
        this.lastActionTime = 0L;
        this.lastPurchaseTime = 0L;
        this.stacksBought = 0;
        this.auctionOpened = false;
        this.refreshCount = 0;
    }

    public boolean tickBuying() {
        if (RepairController.mc.field_1724 == null) {
            return true;
        }
        long now = System.currentTimeMillis();
        if (now - this.phaseStartTime > 30000L && this.phase != RepairPhase.DONE_BUYING) {
            ChatMessage.brandmessage("[AutoMine Repair] \u041f\u043e\u043a\u0443\u043f\u043a\u0430: \u0442\u0430\u0439\u043c\u0430\u0443\u0442 30 \u0441\u0435\u043a");
            this.phase = RepairPhase.DONE_BUYING;
        }
        switch (this.phase.ordinal()) {
            case 4: {
                if (!this.canAct(now)) break;
                if (RepairController.mc.field_1755 != null) {
                    RepairController.mc.field_1724.method_7346();
                    this.lastActionTime = now;
                    return false;
                }
                ChatMessage.brandmessage("[AutoMine Repair] \u041e\u0442\u043a\u0440\u044b\u0432\u0430\u044e \u0430\u0443\u043a\u0446\u0438\u043e\u043d: /ah search \u041f\u0443\u0437\u044b\u0440\u0451\u043a \u043e\u043f\u044b\u0442\u0430");
                this.sendCommand("/ah search \u041f\u0443\u0437\u044b\u0440\u0451\u043a \u043e\u043f\u044b\u0442\u0430");
                this.phase = RepairPhase.WAITING_AUCTION;
                this.phaseStartTime = now;
                this.lastActionTime = now;
                break;
            }
            case 5: {
                long elapsed = now - this.phaseStartTime;
                if (RepairController.mc.field_1755 instanceof class_476 && elapsed >= 2000L) {
                    this.auctionOpened = true;
                    ChatMessage.brandmessage("[AutoMine Repair] \u0410\u0443\u043a\u0446\u0438\u043e\u043d \u043e\u0442\u043a\u0440\u044b\u0442, \u0438\u0449\u0443 \u043f\u0443\u0437\u044b\u0440\u044c\u043a\u0438");
                    this.phase = RepairPhase.SCANNING_BUYING;
                    this.lastActionTime = now;
                    break;
                }
                if (elapsed <= 3000L) break;
                ChatMessage.brandmessage("[AutoMine Repair] \u0410\u0443\u043a\u0446\u0438\u043e\u043d \u043d\u0435 \u043e\u0442\u043a\u0440\u044b\u043b\u0441\u044f, \u043f\u043e\u0432\u0442\u043e\u0440");
                this.phase = RepairPhase.OPENING_AUCTION;
                this.phaseStartTime = now;
                break;
            }
            case 6: {
                class_437 class_4372 = RepairController.mc.field_1755;
                if (!(class_4372 instanceof class_476)) {
                    this.phase = RepairPhase.OPENING_AUCTION;
                    return false;
                }
                class_476 container = (class_476)class_4372;
                if (this.stacksBought >= 3) {
                    ChatMessage.brandmessage("[AutoMine Repair] \u041a\u0443\u043f\u043b\u0435\u043d\u043e \u0441\u0442\u0430\u043a\u043e\u0432: " + this.stacksBought);
                    this.phase = RepairPhase.DONE_BUYING;
                    return false;
                }
                if (!this.canAct(now)) {
                    return false;
                }
                if (this.lastPurchaseTime > 0L && now - this.lastPurchaseTime < 1000L) {
                    return false;
                }
                class_2371 slots = ((class_1707)container.method_17577()).field_7761;
                int syncId = ((class_1707)container.method_17577()).field_7763;
                int bestSlot = -1;
                int bestPrice = Integer.MAX_VALUE;
                int maxSlot = Math.min(45, slots.size());
                for (int i = 0; i < maxSlot; ++i) {
                    int price;
                    class_1799 stack;
                    class_1735 slot = (class_1735)slots.get(i);
                    if (slot == null || (stack = slot.method_7677()) == null || stack.method_7960() || stack.method_7909() != class_1802.field_8287 || stack.method_7947() < 64 || (price = this.extractPrice(stack)) <= 0 || price > 100000 || price >= bestPrice) continue;
                    bestPrice = price;
                    bestSlot = i;
                }
                if (bestSlot >= 0) {
                    RepairController.mc.field_1761.method_2906(syncId, bestSlot, 0, class_1713.field_7794, (class_1657)RepairController.mc.field_1724);
                    ++this.stacksBought;
                    this.lastPurchaseTime = now;
                    ChatMessage.brandmessage("[AutoMine Repair] \u041a\u0443\u043f\u043b\u0435\u043d \u0441\u0442\u0430\u043a " + this.stacksBought + "/3, \u0446\u0435\u043d\u0430 " + bestPrice + ", \u0436\u0434\u0443 1 \u0441\u0435\u043a");
                    this.lastActionTime = now;
                    break;
                }
                if (this.refreshCount < 5) {
                    int containerSize = slots.size() - 36;
                    int refreshSlot = containerSize > 0 ? containerSize - 5 : 49;
                    RepairController.mc.field_1761.method_2906(syncId, refreshSlot, 0, class_1713.field_7794, (class_1657)RepairController.mc.field_1724);
                    ++this.refreshCount;
                    ChatMessage.brandmessage("[AutoMine Repair] \u041e\u0431\u043d\u043e\u0432\u043b\u044f\u044e \u0430\u0443\u043a\u0446\u0438\u043e\u043d, \u043f\u043e\u043f\u044b\u0442\u043a\u0430 " + this.refreshCount);
                    this.lastActionTime = now;
                    break;
                }
                ChatMessage.brandmessage("[AutoMine Repair] \u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e \u043f\u043e\u0434\u0445\u043e\u0434\u044f\u0449\u0438\u0445 \u0441\u0442\u0430\u043a\u043e\u0432, \u043a\u0443\u043f\u043b\u0435\u043d\u043e " + this.stacksBought);
                this.phase = RepairPhase.DONE_BUYING;
                break;
            }
            case 7: {
                if (RepairController.mc.field_1755 != null) {
                    RepairController.mc.field_1724.method_7346();
                }
                ChatMessage.brandmessage("[AutoMine Repair] \u041f\u043e\u043a\u0443\u043f\u043a\u0430 \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d\u0430, \u0441\u0442\u0430\u043a\u043e\u0432: " + this.stacksBought + ", \u0436\u0434\u0443 \u0441\u0438\u043d\u0445\u0440\u043e\u043d\u0438\u0437\u0430\u0446\u0438\u044e...");
                this.phase = RepairPhase.WAITING_AFTER_BUY;
                this.phaseStartTime = now;
                return false;
            }
            default: {
                return true;
            }
        }
        return false;
    }

    private int extractPrice(class_1799 stack) {
        String name;
        Matcher m;
        class_9290 lore = (class_9290)stack.method_57824(class_9334.field_49632);
        if (lore != null && !lore.comp_2400().isEmpty()) {
            for (class_2561 line : lore.comp_2400()) {
                String text = line.getString();
                Matcher matcher = PRICE_PATTERN.matcher(text);
                if (!matcher.find()) continue;
                for (int g = 1; g <= 3; ++g) {
                    String priceStr = matcher.group(g);
                    if (priceStr == null) continue;
                    try {
                        String clean = priceStr.replaceAll("[\\s,.]", "");
                        return Integer.parseInt(clean);
                    }
                    catch (NumberFormatException numberFormatException) {
                        // empty catch block
                    }
                }
            }
        }
        if ((m = PRICE_PATTERN.matcher(name = stack.method_7964().getString())).find()) {
            for (int g = 1; g <= 3; ++g) {
                String priceStr = m.group(g);
                if (priceStr == null) continue;
                try {
                    String clean = priceStr.replaceAll("[\\s,.]", "");
                    return Integer.parseInt(clean);
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
        }
        return -1;
    }

    private void tickSwappingPickaxeAndXp(long now) {
        boolean xpInMainhand;
        boolean hasPickaxe;
        if (RepairController.mc.field_1755 != null) {
            RepairController.mc.field_1724.method_7346();
            this.lastActionTime = now;
            return;
        }
        if (!this.canAct(now)) {
            return;
        }
        boolean pickaxeInOffhand = RepairController.mc.field_1724.method_6079().method_7909() instanceof class_1810;
        int pickaxeInv = this.findPickaxeInvIndex();
        int xpInv = this.findXpBottleSlot();
        class_1735 pickaxeSlot = pickaxeInv >= 0 ? this.getSlotFromInvIndex(pickaxeInv) : null;
        class_1735 xpSlot = xpInv >= 0 ? this.getSlotFromInvIndex(xpInv) : null;
        boolean bl = hasPickaxe = pickaxeInOffhand || pickaxeSlot != null;
        if (!hasPickaxe || xpSlot == null) {
            if (this.noItemsRetryCount < 2 && this.stacksBought > 0) {
                ++this.noItemsRetryCount;
                ChatMessage.brandmessage("[AutoMine Repair] \u0416\u0434\u0443 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b, \u043f\u043e\u0432\u0442\u043e\u0440 " + this.noItemsRetryCount);
                this.phase = RepairPhase.WAITING_AFTER_BUY;
                this.phaseStartTime = now;
            } else {
                ChatMessage.brandmessage("[AutoMine Repair] \u041d\u0435\u0442 \u043a\u0438\u0440\u043a\u0438 \u0438\u043b\u0438 \u043f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432");
                this.phase = RepairPhase.DONE_REPAIR;
            }
            return;
        }
        this.noItemsRetryCount = 0;
        boolean bl2 = xpInMainhand = RepairController.mc.field_1724.method_6047().method_7909() == class_1802.field_8287;
        if (pickaxeInOffhand && xpInMainhand) {
            ChatMessage.brandmessage("[AutoMine Repair] \u041a\u0438\u0440\u043a\u0430 \u0432 \u043b\u0435\u0432\u043e\u0439, \u043e\u043f\u044b\u0442 \u0432 \u043f\u0440\u0430\u0432\u043e\u0439 \u2014 \u043a\u0438\u0434\u0430\u044e");
            this.phase = RepairPhase.THROWING_XP;
            this.lastThrowTime = now;
            return;
        }
        if (!pickaxeInOffhand) {
            InventoryTask.swapHand(pickaxeSlot, class_1268.field_5810, false, true);
            this.lastActionTime = now;
            return;
        }
        if (!xpInMainhand) {
            if (xpInv < 9) {
                InventoryTask.switchTo(xpInv);
            } else {
                int sel = RepairController.mc.field_1724.method_31548().field_7545;
                RepairController.mc.field_1761.method_2906(RepairController.mc.field_1724.field_7512.field_7763, xpSlot.field_7874, sel, class_1713.field_7791, (class_1657)RepairController.mc.field_1724);
            }
            this.lastActionTime = now;
        }
    }

    private void tickThrowingXp(long now) {
        long delay;
        if (this.needReleaseUseKey) {
            RepairController.mc.field_1690.field_1904.method_23481(false);
            this.needReleaseUseKey = false;
        }
        if (this.isPickaxeFullyRepaired()) {
            ChatMessage.brandmessage("[AutoMine Repair] \u041a\u0438\u0440\u043a\u0430 \u043f\u043e\u0447\u0438\u043d\u0435\u043d\u0430");
            this.phase = RepairPhase.RETURNING_PICKAXE;
            return;
        }
        if (RepairController.mc.field_1724.method_6047().method_7909() != class_1802.field_8287) {
            int nextXp = this.findXpBottleSlot();
            if (nextXp == -1) {
                ChatMessage.brandmessage("[AutoMine Repair] \u041f\u0443\u0437\u044b\u0440\u044c\u043a\u0438 \u0437\u0430\u043a\u043e\u043d\u0447\u0438\u043b\u0438\u0441\u044c");
                this.phase = RepairPhase.RETURNING_PICKAXE;
                return;
            }
            this.phase = RepairPhase.SWAPPING_PICKAXE_AND_XP;
            return;
        }
        long l = delay = this.throwTickCounter == 0 ? 250L : 0L;
        if (now - this.lastThrowTime >= delay) {
            RepairController.mc.field_1690.field_1904.method_23481(true);
            this.needReleaseUseKey = true;
            this.lastThrowTime = now;
            ++this.throwTickCounter;
        }
    }

    private void tickReturningPickaxe(long now) {
        if (!this.canAct(now)) {
            return;
        }
        if (RepairController.mc.field_1724.method_6079().method_7909() instanceof class_1810) {
            class_1735 offhandSlot;
            class_2371 slots = RepairController.mc.field_1724.field_7512.field_7761;
            class_1735 class_17352 = offhandSlot = slots.size() >= 46 ? (class_1735)slots.get(45) : null;
            if (offhandSlot != null) {
                InventoryTask.swapHand(offhandSlot, class_1268.field_5808, false, true);
                ChatMessage.brandmessage("[AutoMine Repair] \u041a\u0438\u0440\u043a\u0430 \u0432 \u043e\u0441\u043d\u043e\u0432\u043d\u0443\u044e \u0440\u0443\u043a\u0443, \u043f\u0440\u043e\u0434\u043e\u043b\u0436\u0430\u044e \u043c\u0430\u0439\u043d\u0438\u0442\u044c");
            }
        }
        this.phase = RepairPhase.DONE_REPAIR;
    }

    private int findXpBottleSlot() {
        if (RepairController.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = RepairController.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_8287 || stack.method_7960()) continue;
            return i;
        }
        return -1;
    }

    public boolean hasXpBottles() {
        return this.findXpBottleSlot() != -1;
    }

    public void reset() {
        this.phase = RepairPhase.IDLE;
        this.stacksBought = 0;
        this.auctionOpened = false;
        this.refreshCount = 0;
        this.throwTickCounter = 0;
        this.lastThrowTime = 0L;
    }

    private boolean canAct(long now) {
        return now - this.lastActionTime >= 350L;
    }

    private void sendCommand(String command) {
        if (RepairController.mc.field_1724 != null && mc.method_1562() != null) {
            String cmd = command.startsWith("/") ? command.substring(1) : command;
            mc.method_1562().method_45731(cmd);
        }
    }

    public RepairPhase getPhase() {
        return this.phase;
    }

    public long getPhaseStartTime() {
        return this.phaseStartTime;
    }

    public long getLastActionTime() {
        return this.lastActionTime;
    }

    public long getLastPurchaseTime() {
        return this.lastPurchaseTime;
    }

    public int getStacksBought() {
        return this.stacksBought;
    }

    public boolean isAuctionOpened() {
        return this.auctionOpened;
    }

    public int getRefreshCount() {
        return this.refreshCount;
    }

    public int getThrowTickCounter() {
        return this.throwTickCounter;
    }

    public long getLastThrowTime() {
        return this.lastThrowTime;
    }

    public boolean isNeedReleaseUseKey() {
        return this.needReleaseUseKey;
    }

    public int getDropActionIndex() {
        return this.dropActionIndex;
    }

    public List<Integer> getSlotsToDrop() {
        return this.slotsToDrop;
    }

    public int getConsolidateTicks() {
        return this.consolidateTicks;
    }

    public int getConsolidateTargetFree() {
        return this.consolidateTargetFree;
    }

    public int getNoItemsRetryCount() {
        return this.noItemsRetryCount;
    }

    public static enum RepairPhase {
        IDLE,
        CLEARING_INVENTORY,
        DROPPING_ITEMS,
        CONSOLIDATING_DIAMONDS,
        OPENING_AUCTION,
        WAITING_AUCTION,
        SCANNING_BUYING,
        DONE_BUYING,
        WAITING_AFTER_BUY,
        SWAPPING_PICKAXE_AND_XP,
        THROWING_XP,
        RETURNING_PICKAXE,
        DONE_REPAIR;

    }
}
