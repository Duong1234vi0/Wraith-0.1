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
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1792;
import net.minecraft.class_1802;

public class SunriseHandler
extends ServerHelperHandler {
    private static final String[] NAMES = new String[]{"\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u0425\u043e\u0440\u043e\u0448\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041e\u0442\u043b\u0438\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u042d\u043f\u0438\u0447\u0435\u0441\u043a\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041b\u0438\u0432\u0430\u043b\u043a\u0430", "\u0410\u043c\u0443\u043b\u0435\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u0438", "\u0421\u043a\u043e\u043f\u043b\u0435\u043d\u0438\u0435 \u0441\u0432\u0435\u0442\u0430", "\u0412\u043e\u043b\u043d\u0430 \u043e\u0433\u043d\u044f", "\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0435\u043d\u0438\u044f", "\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0434\u0435\u0437\u043e\u0440\u0433\u0430\u043d\u0438\u0437\u0430\u0446\u0438\u0438", "\u0420\u0430\u0437\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u0421\u0442\u043e\u0435\u043a"};
    private static final String[] SHORT = new String[]{"\u041e\u0422", "\u0425\u0422", "\u041e\u0442\u0422", "\u042d\u0422", "\u041b\u0422", "\u041b\u0438\u0432", "\u0410\u0422", "\u0421\u0421", "\u0412\u041e", "\u041f\u0412", "\u041f\u0414", "\u0420\u0421"};
    private static final class_1792[] ICONS = new class_1792[]{class_1802.field_22021, class_1802.field_8463, class_1802.field_8367, class_1802.field_8288, class_1802.field_8137, class_1802.field_8153, class_1802.field_8634, class_1802.field_8601, class_1802.field_8814, class_1802.field_8054, class_1802.field_8479, class_1802.field_8475};
    private final List<BindSetting> binds;
    private final int[] cachedSlots = new int[NAMES.length];
    private long lastScan;
    private boolean onlyHotbar;
    private String pendingName;
    private List<ServerHelperRenderItem> cachedRender;
    private boolean cachedOnlyHotbar;

    public SunriseHandler(List<BindSetting> binds) {
        this.binds = binds;
        for (int i = 0; i < this.cachedSlots.length; ++i) {
            this.cachedSlots[i] = -1;
        }
    }

    @Override
    public String getModeId() {
        return "Sunrise";
    }

    @Override
    public String getOverlayTitle() {
        return "Sunrise";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.binds;
    }

    @Override
    public void setOnlyHotbar(boolean onlyHotbar) {
        this.onlyHotbar = onlyHotbar;
    }

    private void scanSlots() {
        long now = System.currentTimeMillis();
        if (now - this.lastScan < 100L) {
            return;
        }
        this.lastScan = now;
        if (SunriseHandler.mc.field_1724 == null) {
            return;
        }
        for (int i = 0; i < this.cachedSlots.length; ++i) {
            this.cachedSlots[i] = -1;
        }
        for (int slot = 0; slot < 36; ++slot) {
            String cleanName = SunriseHandler.cleanName(SunriseHandler.mc.field_1724.method_31548().method_5438(slot).method_7964().getString());
            if (cleanName.isEmpty()) continue;
            for (int i = 0; i < NAMES.length; ++i) {
                if (this.cachedSlots[i] != -1 || !cleanName.contains(NAMES[i].toLowerCase())) continue;
                this.cachedSlots[i] = slot;
            }
        }
        this.cachedRender = null;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        this.scanSlots();
        if (this.cachedRender != null && this.cachedOnlyHotbar == onlyBar) {
            return this.cachedRender;
        }
        this.cachedOnlyHotbar = onlyBar;
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            int slot = this.cachedSlots[i];
            String status = slot == -1 ? "\u2014" : (onlyBar && slot >= 9 ? "\u2014" : "\u2713");
            items.add(new ServerHelperRenderItem(NAMES[i], SHORT[i], status, ICONS[i]).withBind(this.binds.get(i).getKey()));
        }
        this.cachedRender = items;
        return items;
    }

    @Override
    public boolean onKey(int keyCode) {
        this.scanSlots();
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            if (this.binds.get(i).getKey() == -1 || this.binds.get(i).getKey() != keyCode) continue;
            int slot = this.cachedSlots[i];
            if (slot == -1) {
                SunriseHandler.notifyMissing(NAMES[i]);
                return true;
            }
            if (this.onlyHotbar && slot >= 9) {
                SunriseHandler.notifyHotbarOnly(NAMES[i]);
                return true;
            }
            this.pendingName = NAMES[i];
            return true;
        }
        return false;
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
        this.scanSlots();
        for (int i = 0; i < NAMES.length; ++i) {
            if (!NAMES[i].equals(this.pendingName)) continue;
            return this.cachedSlots[i];
        }
        return -1;
    }

    @Override
    public void clearPending() {
        this.pendingName = null;
    }
}
