/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.ServerHelper;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1802;

public class MineBlazeHandler
extends ServerHelperHandler {
    private static final String[] SEARCH = new String[]{"\u0444\u0438\u0442\u0438\u043b\u044c", "\u043f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0437\u0435\u043b\u044c\u0435", "\u0432\u0437\u0440\u044b\u0432\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435"};
    private static final int[] TINTS = new int[]{ColorAssist.getColor(255, 165, 0, 200), ColorAssist.getColor(138, 43, 226, 200), ColorAssist.getColor(220, 20, 60, 200)};
    private final List<BindSetting> binds;
    private final Map<String, Integer> slotCache = new HashMap<String, Integer>();
    private long lastScan;
    private boolean onlyHotbar;

    public MineBlazeHandler(List<BindSetting> binds) {
        this.binds = binds;
    }

    @Override
    public String getModeId() {
        return "MineBlaze";
    }

    @Override
    public String getOverlayTitle() {
        return "MB";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.binds;
    }

    @Override
    public void setOnlyHotbar(boolean onlyHotbar) {
        this.onlyHotbar = onlyHotbar;
    }

    private void scan() {
        long now = System.currentTimeMillis();
        if (now - this.lastScan < 100L) {
            return;
        }
        this.lastScan = now;
        this.slotCache.clear();
        if (MineBlazeHandler.mc.field_1724 == null) {
            return;
        }
        int max = this.onlyHotbar ? 9 : 36;
        for (int i = 0; i < max; ++i) {
            String name = MineBlazeHandler.cleanName(MineBlazeHandler.mc.field_1724.method_31548().method_5438(i).method_7964().getString());
            for (String search : SEARCH) {
                if (!name.contains(search) || this.slotCache.containsKey(search)) continue;
                this.slotCache.put(search, i);
            }
        }
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        this.scan();
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        String[] labels = new String[]{"\u0424\u0438\u0442\u0438\u043b\u044c", "\u041f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435", "\u0412\u0437\u0440\u044b\u0432\u043d\u043e\u0435"};
        String[] shorts = new String[]{"\u0424", "\u041f\u0417", "\u0412\u0417"};
        for (int i = 0; i < SEARCH.length; ++i) {
            int slot = this.slotCache.getOrDefault(SEARCH[i], -1);
            items.add(new ServerHelperRenderItem(labels[i], shorts[i], slot == -1 ? "\u2014" : "\u2713", class_1802.field_8436).withBind(this.binds.size() > i ? this.binds.get(i).getKey() : -1).withTint(TINTS[i]));
        }
        return items;
    }

    @Override
    public boolean onKey(int keyCode) {
        this.scan();
        for (int i = 0; i < SEARCH.length && i < this.binds.size(); ++i) {
            if (this.binds.get(i).getKey() == -1 || this.binds.get(i).getKey() != keyCode) continue;
            int slot = this.slotCache.getOrDefault(SEARCH[i], -1);
            if (slot == -1) {
                MineBlazeHandler.notifyMissing(SEARCH[i]);
                return true;
            }
            ServerHelper.getInstance().queueSlotUse(slot);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPendingUse() {
        return false;
    }

    @Override
    public int getPendingSlot() {
        return -1;
    }

    @Override
    public void clearPending() {
    }
}
