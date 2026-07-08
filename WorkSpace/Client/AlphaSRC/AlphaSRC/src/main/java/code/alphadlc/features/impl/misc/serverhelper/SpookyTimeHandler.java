/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.interactions.item.ItemTask;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1792;
import net.minecraft.class_1802;

public class SpookyTimeHandler
extends ServerHelperHandler {
    private static final String[] NAMES = new String[]{"\u0414\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f", "\u042f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", "\u041f\u043b\u0430\u0441\u0442", "\u0422\u0440\u0430\u043f\u043a\u0430 Spooky", "\u041e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u0441\u043c\u0435\u0440\u0447", "\u0421\u043d\u0435\u0436\u043e\u043a \u0437\u0430\u043c\u043e\u0440\u043e\u0437\u043a\u0430", "\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430"};
    private static final String[] SEARCH = new String[]{"\u0434\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442", "\u044f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", "\u043f\u043b\u0430\u0441\u0442", "\u0442\u0440\u0430\u043f\u043a\u0430", "\u0441\u043c\u0435\u0440\u0447", "\u0437\u0430\u043c\u043e\u0440\u043e\u0437\u043a\u0430", "\u0431\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430"};
    private static final class_1792[] ITEMS = new class_1792[]{class_1802.field_8449, class_1802.field_8479, class_1802.field_8551, class_1802.field_22021, class_1802.field_8814, class_1802.field_8543, class_1802.field_8614};
    private final List<BindSetting> binds;
    private boolean onlyHotbar;
    private String pendingSearch;
    private class_1792 pendingItem;

    public SpookyTimeHandler(List<BindSetting> binds) {
        this.binds = binds;
    }

    @Override
    public String getModeId() {
        return "SpookyTime";
    }

    @Override
    public String getOverlayTitle() {
        return "Spooky";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.binds;
    }

    @Override
    public void setOnlyHotbar(boolean onlyHotbar) {
        this.onlyHotbar = onlyHotbar;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            int slot = SpookyTimeHandler.findSlotByName(SEARCH[i]);
            String status = slot == -1 ? "\u2014" : (onlyBar && slot >= 9 ? "\u2014" : SpookyTimeHandler.cooldownStatus(ITEMS[i]));
            String shortName = NAMES[i].length() > 8 ? NAMES[i].substring(0, 8) : NAMES[i];
            items.add(new ServerHelperRenderItem(NAMES[i], shortName, status, ITEMS[i]).withBind(this.binds.get(i).getKey()));
        }
        return items;
    }

    @Override
    public boolean onKey(int keyCode) {
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            if (this.binds.get(i).getKey() == -1 || this.binds.get(i).getKey() != keyCode) continue;
            this.queueUse(NAMES[i], SEARCH[i], ITEMS[i]);
            return true;
        }
        return false;
    }

    private void queueUse(String itemName, String search, class_1792 item) {
        int slot = SpookyTimeHandler.findSlotByName(search);
        if (slot == -1) {
            SpookyTimeHandler.notifyMissing(itemName);
            return;
        }
        if (this.onlyHotbar && slot >= 9) {
            SpookyTimeHandler.notifyHotbarOnly(itemName);
            return;
        }
        if (ItemTask.getCooldownProgress(item) > 0.0f) {
            SpookyTimeHandler.notifyCooldown(itemName);
            return;
        }
        this.pendingSearch = search;
        this.pendingItem = item;
    }

    @Override
    public boolean hasPendingUse() {
        return this.pendingSearch != null;
    }

    @Override
    public int getPendingSlot() {
        return this.pendingSearch == null ? -1 : SpookyTimeHandler.findSlotByName(this.pendingSearch);
    }

    @Override
    public void clearPending() {
        this.pendingSearch = null;
        this.pendingItem = null;
    }
}
