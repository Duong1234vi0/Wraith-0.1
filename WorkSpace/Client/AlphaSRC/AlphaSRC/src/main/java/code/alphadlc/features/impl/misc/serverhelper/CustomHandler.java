/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.ServerHelper;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemConfig;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemRegistry;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1792;

public class CustomHandler
extends ServerHelperHandler {
    private final BindSetting openGuiBind = new BindSetting("\u041e\u0442\u043a\u0440\u044b\u0442\u044c GUI", "\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u0440\u0435\u0434\u0430\u043a\u0442\u043e\u0440 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432").visible(() -> ServerHelper.getInstance() != null && ServerHelper.getInstance().isCustomMode());
    private final Map<CustomItemEntry, BindSetting> bindMap = new HashMap<CustomItemEntry, BindSetting>();
    private final List<CustomItemEntry> entries = new ArrayList<CustomItemEntry>();
    private boolean onlyHotbar;
    private class_1792 pendingItem;

    public CustomHandler() {
        this.refreshBinds(List.of());
    }

    public void setEntries(List<CustomItemEntry> configs) {
        this.entries.clear();
        this.entries.addAll(configs);
        this.refreshBinds(configs);
    }

    private void refreshBinds(List<CustomItemEntry> configs) {
        HashMap<CustomItemEntry, BindSetting> old = new HashMap<CustomItemEntry, BindSetting>(this.bindMap);
        this.bindMap.clear();
        for (CustomItemEntry entry : configs) {
            if (entry.bindKey() == -1) continue;
            BindSetting bind = (BindSetting)old.get(entry);
            if (bind == null) {
                bind = new BindSetting(entry.displayName(), "\u041f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u044c\u0441\u043a\u0438\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442").visible(() -> ServerHelper.getInstance() != null && ServerHelper.getInstance().isCustomMode());
            }
            bind.setKey(entry.bindKey());
            this.bindMap.put(entry, bind);
        }
    }

    public List<BindSetting> getDynamicBinds() {
        ArrayList<BindSetting> binds = new ArrayList<BindSetting>();
        binds.add(this.openGuiBind);
        binds.addAll(this.bindMap.values());
        return binds;
    }

    public BindSetting getOpenGuiBind() {
        return this.openGuiBind;
    }

    @Override
    public String getModeId() {
        return "Custom";
    }

    @Override
    public String getOverlayTitle() {
        return "C";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.getDynamicBinds();
    }

    @Override
    public void setOnlyHotbar(boolean onlyHotbar) {
        this.onlyHotbar = onlyHotbar;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        for (CustomItemEntry entry : this.entries) {
            if (entry.bindKey() == -1) continue;
            int slot = this.resolveSlot(entry);
            String status = slot == -1 ? "\u2014" : (onlyBar && slot >= 9 ? "\u2014" : (ItemTask.getCooldownProgress(entry.item()) > 0.0f ? CustomHandler.cooldownStatus(entry.item()) : "\u2713"));
            String shortName = entry.displayName().length() > 8 ? entry.displayName().substring(0, 8) : entry.displayName();
            items.add(new ServerHelperRenderItem(entry.displayName(), shortName, status, entry.item()).withBind(entry.bindKey()));
        }
        return items;
    }

    private int resolveSlot(CustomItemEntry entry) {
        int slot;
        ServerHelperItemConfig config;
        if (!entry.potionName().isEmpty() && (config = ServerHelperItemRegistry.get(entry.potionName())) != null && (slot = CustomHandler.findSlotByConfig(config)) != -1) {
            return slot;
        }
        return InventoryTask.findHotbarSlot(entry.item()) != -1 ? InventoryTask.findHotbarSlot(entry.item()) : InventoryTask.findInventorySlot(entry.item());
    }

    @Override
    public boolean onKey(int keyCode) {
        if (this.openGuiBind.getKey() != -1 && this.openGuiBind.getKey() == keyCode) {
            CustomHandler.notify("\u00a7e\u0420\u0435\u0434\u0430\u043a\u0442\u043e\u0440 Custom \u043f\u043e\u043a\u0430 \u043d\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0451\u043d \u2014 \u0434\u043e\u0431\u0430\u0432\u044c\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u043a\u043e\u043d\u0444\u0438\u0433.");
            return true;
        }
        for (CustomItemEntry entry : this.entries) {
            if (entry.bindKey() == -1 || entry.bindKey() != keyCode) continue;
            this.useEntry(entry);
            return true;
        }
        if (CustomHandler.mc.field_1724 != null && CustomHandler.mc.field_1724.method_5715() && keyCode == 344) {
            for (CustomItemEntry entry : this.entries) {
                if (!entry.activateOnShift()) continue;
                this.useEntry(entry);
                return true;
            }
        }
        return false;
    }

    private void useEntry(CustomItemEntry entry) {
        int slot = this.resolveSlot(entry);
        if (slot == -1) {
            CustomHandler.notifyMissing(entry.displayName());
            return;
        }
        if (this.onlyHotbar && slot >= 9) {
            CustomHandler.notifyHotbarOnly(entry.displayName());
            return;
        }
        if (ItemTask.getCooldownProgress(entry.item()) > 0.0f) {
            CustomHandler.notifyCooldown(entry.displayName());
            return;
        }
        if (entry.useServerHelperQueue()) {
            ServerHelper.getInstance().queueSlotUse(slot);
            return;
        }
        this.pendingItem = entry.item();
    }

    @Override
    public boolean hasPendingUse() {
        return this.pendingItem != null;
    }

    @Override
    public int getPendingSlot() {
        return this.pendingItem == null ? -1 : CustomHandler.findSlotByItem(this.pendingItem);
    }

    @Override
    public void clearPending() {
        this.pendingItem = null;
    }

    public record CustomItemEntry(class_1792 item, String displayName, int bindKey, String potionName, boolean useServerHelperQueue, boolean activateOnShift) {
    }
}
