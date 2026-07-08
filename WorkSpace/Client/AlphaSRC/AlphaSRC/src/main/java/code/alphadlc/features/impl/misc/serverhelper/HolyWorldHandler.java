/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1713
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemConfig;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemRegistry;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_1268;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class HolyWorldHandler
extends ServerHelperHandler {
    private static final String[] NAMES = new String[]{"\u041f\u0443\u0437\u044b\u0440\u0451\u043a \u043e\u043f\u044b\u0442\u0430", "\u0422\u0440\u0430\u043f\u043a\u0430", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041f\u0440\u043e\u0449\u0430\u043b\u044c\u043d\u044b\u0439 \u0433\u0443\u043b", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0448\u0442\u0443\u0447\u043a\u0430", "\u0421\u0442\u0430\u043d", "\u041a\u043e\u043c \u0441\u043d\u0435\u0433\u0430"};
    private static final class_1792[] ITEMS = new class_1792[]{class_1802.field_8287, class_1802.field_8882, class_1802.field_8662, class_1802.field_8693, class_1802.field_8814, class_1802.field_8137, class_1802.field_8543};
    private final List<BindSetting> binds;
    private final BooleanSetting antiCrash;
    private final BindSetting backpackBind;
    private boolean onlyHotbar;
    private String pendingName;
    private class_1792 pendingItem;
    private boolean backpackActive;
    private int backpackSlot = -1;
    private int originalSlot = -1;
    private int backpackTicks;

    public HolyWorldHandler(List<BindSetting> binds, BooleanSetting antiCrash, BindSetting backpackBind) {
        this.binds = binds;
        this.antiCrash = antiCrash;
        this.backpackBind = backpackBind;
    }

    @Override
    public BooleanSetting getAntiCrashSetting() {
        return this.antiCrash;
    }

    @Override
    public String getModeId() {
        return "HolyWorld";
    }

    @Override
    public String getOverlayTitle() {
        return "Holy";
    }

    @Override
    public List<BindSetting> getBinds() {
        ArrayList<BindSetting> all = new ArrayList<BindSetting>(this.binds);
        all.add(this.backpackBind);
        return all;
    }

    @Override
    public void setOnlyHotbar(boolean onlyHotbar) {
        this.onlyHotbar = onlyHotbar;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            int slot = this.resolveSlot(NAMES[i], ITEMS[i]);
            String status = this.formatStatus(slot, onlyBar, ITEMS[i]);
            String shortName = NAMES[i].length() > 8 ? NAMES[i].substring(0, 8) : NAMES[i];
            items.add(new ServerHelperRenderItem(NAMES[i], shortName, status, ITEMS[i]).withBind(this.binds.get(i).getKey()));
        }
        return items;
    }

    private int resolveSlot(String name, class_1792 fallback) {
        int slot;
        ServerHelperItemConfig config = ServerHelperItemRegistry.get(name);
        if (config != null && (slot = HolyWorldHandler.findSlotByConfig(config)) != -1) {
            return slot;
        }
        return HolyWorldHandler.findSlotByItem(fallback);
    }

    private String formatStatus(int slot, boolean onlyBar, class_1792 item) {
        if (slot == -1) {
            return "\u2014";
        }
        if (onlyBar && slot >= 9) {
            return "\u2014";
        }
        return HolyWorldHandler.cooldownStatus(item);
    }

    @Override
    public boolean onKey(int keyCode) {
        if (this.backpackBind.getKey() != -1 && this.backpackBind.getKey() == keyCode) {
            this.openBackpack();
            return true;
        }
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            if (this.binds.get(i).getKey() == -1 || this.binds.get(i).getKey() != keyCode) continue;
            this.queueUse(NAMES[i], ITEMS[i]);
            return true;
        }
        return false;
    }

    private void queueUse(String itemName, class_1792 item) {
        int slot = this.resolveSlot(itemName, item);
        if (slot == -1) {
            HolyWorldHandler.notifyMissing(itemName);
            return;
        }
        if (this.onlyHotbar && slot >= 9) {
            HolyWorldHandler.notifyHotbarOnly(itemName);
            return;
        }
        if (ItemTask.getCooldownProgress(item) > 0.0f) {
            HolyWorldHandler.notifyCooldown(itemName);
            return;
        }
        this.pendingName = itemName;
        this.pendingItem = item;
    }

    private void openBackpack() {
        int slot = this.findBackpackSlot();
        if (slot == -1) {
            HolyWorldHandler.notify("\u00a7c\u0420\u044e\u043a\u0437\u0430\u043a \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435");
            return;
        }
        this.backpackSlot = slot;
        this.backpackActive = true;
    }

    private int findBackpackSlot() {
        if (HolyWorldHandler.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = HolyWorldHandler.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960()) continue;
            String translationKey = stack.method_7909().method_7876();
            if (translationKey.contains("shulker_box")) {
                return i;
            }
            String displayName = stack.method_7909().method_63680().getString().toLowerCase(Locale.ROOT);
            if (displayName.contains("\u0448\u0430\u043b\u043a\u0435\u0440")) {
                return i;
            }
            String cleanName = HolyWorldHandler.cleanName(stack.method_7964().getString());
            if (!cleanName.contains("\u0440\u044e\u043a\u0437\u0430\u043a")) continue;
            return i;
        }
        return -1;
    }

    @Override
    public void tick() {
        if (!this.backpackActive && this.backpackTicks <= 0) {
            return;
        }
        if (this.backpackActive) {
            this.originalSlot = HolyWorldHandler.mc.field_1724.method_31548().field_7545;
            if (this.backpackSlot < 9) {
                HolyWorldHandler.mc.field_1724.method_31548().field_7545 = this.backpackSlot;
                this.syncSlot();
            } else {
                InventoryTask.clickSlot(this.backpackSlot < 9 ? this.backpackSlot + 36 : this.backpackSlot, HolyWorldHandler.mc.field_1724.method_31548().field_7545, class_1713.field_7791, false);
                this.syncSlot();
            }
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            this.backpackActive = false;
            this.backpackTicks = 0;
            return;
        }
        if (this.backpackTicks >= 0 && HolyWorldHandler.mc.field_1755 == null) {
            ++this.backpackTicks;
            if (this.backpackTicks >= 3 && HolyWorldHandler.mc.field_1724.field_7512.field_7763 == 0) {
                if (this.backpackSlot >= 9) {
                    InventoryTask.clickSlot(this.backpackSlot, this.originalSlot, class_1713.field_7791, false);
                }
                HolyWorldHandler.mc.field_1724.method_31548().field_7545 = this.originalSlot;
                this.syncSlot();
                this.backpackSlot = -1;
                this.originalSlot = -1;
                this.backpackTicks = -1;
            }
        }
    }

    private void syncSlot() {
        if (HolyWorldHandler.mc.field_1761 != null) {
            HolyWorldHandler.mc.field_1761.method_2911();
        }
    }

    @Override
    public boolean hasPendingUse() {
        return this.pendingName != null;
    }

    @Override
    public int getPendingSlot() {
        if (this.pendingName == null) {
            return -1;
        }
        return this.resolveSlot(this.pendingName, this.pendingItem);
    }

    @Override
    public void clearPending() {
        this.pendingName = null;
        this.pendingItem = null;
    }

    public boolean isBackpackBusy() {
        return this.backpackActive || this.backpackTicks >= 0;
    }
}
