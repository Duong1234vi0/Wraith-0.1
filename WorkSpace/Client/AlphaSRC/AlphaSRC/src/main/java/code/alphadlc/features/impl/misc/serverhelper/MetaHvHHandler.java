/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1713
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1304;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;

public class MetaHvHHandler
extends ServerHelperHandler {
    private final List<BindSetting> binds;
    private final SelectSetting helmetType;
    private final SliderSettings swapDelay;
    private final BooleanSetting autoBootsWater;
    private final SliderSettings ecRadius;
    private final StopWatch swapTimer = new StopWatch();
    private boolean autoEcEnabled;
    private class_2338 miningPos = class_2338.field_10980;

    public MetaHvHHandler(List<BindSetting> binds, SelectSetting helmetType, SliderSettings swapDelay, BooleanSetting autoBootsWater, SliderSettings ecRadius) {
        this.binds = binds;
        this.helmetType = helmetType;
        this.swapDelay = swapDelay;
        this.autoBootsWater = autoBootsWater;
        this.ecRadius = ecRadius;
    }

    @Override
    public String getModeId() {
        return "MetaHvH";
    }

    @Override
    public String getOverlayTitle() {
        return "Meta";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.binds;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        boolean hasSanta = this.hasSantaHead();
        items.add(new ServerHelperRenderItem("\u0421\u0432\u0430\u043f \u0433\u043e\u043b\u043e\u0432\u044b", "H", hasSanta ? "\u2713" : "\u2014", class_1802.field_8575).withBind(this.binds.isEmpty() ? -1 : this.binds.get(0).getKey()));
        boolean hasBoots = MetaHvHHandler.findSlotByItem(class_1802.field_8753) != -1 || MetaHvHHandler.findSlotByItem(class_1802.field_22030) != -1 || MetaHvHHandler.findSlotByItem(class_1802.field_8285) != -1;
        items.add(new ServerHelperRenderItem("\u0421\u0432\u0430\u043f \u0431\u043e\u0442\u0438\u043d\u043e\u043a", "B", hasBoots ? "\u2713" : "\u2014", class_1802.field_8753).withBind(this.binds.size() > 1 ? this.binds.get(1).getKey() : -1));
        items.add(new ServerHelperRenderItem("\u0410\u0432\u0442\u043e EC", "EC", this.autoEcEnabled ? "ON" : "OFF", class_1802.field_8466).withBind(this.binds.size() > 2 ? this.binds.get(2).getKey() : -1));
        return items;
    }

    @Override
    public boolean onKey(int keyCode) {
        if (this.binds.isEmpty() || MetaHvHHandler.mc.field_1724 == null) {
            return false;
        }
        if (this.binds.get(0).getKey() != -1 && this.binds.get(0).getKey() == keyCode) {
            if (this.swapTimer.finished((long)(this.swapDelay.getValue() * 1000.0f))) {
                this.swapHead();
                this.swapTimer.reset();
            }
            return true;
        }
        if (this.binds.size() > 1 && this.binds.get(1).getKey() != -1 && this.binds.get(1).getKey() == keyCode) {
            this.swapBoots();
            return true;
        }
        if (this.binds.size() > 2 && this.binds.get(2).getKey() != -1 && this.binds.get(2).getKey() == keyCode) {
            this.autoEcEnabled = !this.autoEcEnabled;
            MetaHvHHandler.notify(this.autoEcEnabled ? "\u00a7a\u0410\u0432\u0442\u043e EC \u0432\u043a\u043b\u044e\u0447\u0435\u043d" : "\u00a7c\u0410\u0432\u0442\u043e EC \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d");
            if (!this.autoEcEnabled) {
                this.miningPos = class_2338.field_10980;
            }
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        if (this.autoBootsWater.isValue()) {
            this.handleWaterBoots();
        }
        if (this.autoEcEnabled) {
            this.handleAutoEc();
        }
    }

    private void handleWaterBoots() {
        if (MetaHvHHandler.mc.field_1724 == null) {
            return;
        }
        class_1799 boots = MetaHvHHandler.mc.field_1724.method_6118(class_1304.field_6166);
        int goldSlot = MetaHvHHandler.findSlotByItem(class_1802.field_8753);
        class_1792 normal = MetaHvHHandler.findSlotByItem(class_1802.field_22030) != -1 ? class_1802.field_22030 : class_1802.field_8285;
        int normalSlot = MetaHvHHandler.findSlotByItem(normal);
        boolean inWater = MetaHvHHandler.mc.field_1724.method_5799();
        if (inWater && normalSlot != -1 && !boots.method_31574(normal)) {
            this.swapArmor(normalSlot, 8);
        } else if (!inWater && goldSlot != -1 && !boots.method_31574(class_1802.field_8753)) {
            this.swapArmor(goldSlot, 8);
        }
    }

    private void handleAutoEc() {
        if (MetaHvHHandler.mc.field_1724 == null || MetaHvHHandler.mc.field_1687 == null || MetaHvHHandler.mc.field_1761 == null) {
            return;
        }
        int pickSlot = MetaHvHHandler.findSlotByItem(class_1802.field_22024);
        if (pickSlot == -1) {
            pickSlot = MetaHvHHandler.findSlotByItem(class_1802.field_8377);
        }
        if (pickSlot == -1 || MetaHvHHandler.mc.field_1724.method_6115()) {
            return;
        }
        for (class_2338 pos : this.findEcBlocks()) {
            int from;
            if (!MetaHvHHandler.mc.field_1687.method_8320(pos).method_27852(class_2246.field_10443)) continue;
            int oldSlot = MetaHvHHandler.mc.field_1724.method_31548().field_7545;
            if (pickSlot < 9) {
                MetaHvHHandler.mc.field_1724.method_31548().field_7545 = pickSlot;
                this.syncSlot();
            } else {
                from = pickSlot < 9 ? pickSlot + 36 : pickSlot;
                InventoryTask.clickSlot(from, oldSlot, class_1713.field_7791, false);
            }
            MetaHvHHandler.mc.field_1761.method_2910(pos, class_2350.field_11036);
            this.miningPos = pos;
            if (pickSlot >= 9) {
                from = pickSlot < 9 ? pickSlot + 36 : pickSlot;
                InventoryTask.clickSlot(from, oldSlot, class_1713.field_7791, false);
            }
            MetaHvHHandler.mc.field_1724.method_31548().field_7545 = oldSlot;
            this.syncSlot();
            break;
        }
    }

    private CopyOnWriteArrayList<class_2338> findEcBlocks() {
        CopyOnWriteArrayList<class_2338> blocks = new CopyOnWriteArrayList<class_2338>();
        if (MetaHvHHandler.mc.field_1724 == null || MetaHvHHandler.mc.field_1687 == null) {
            return blocks;
        }
        class_2338 start = MetaHvHHandler.mc.field_1724.method_24515();
        int radius = this.ecRadius.getInt();
        for (int y = 3; y >= 0; --y) {
            for (int x = radius; x >= -radius; --x) {
                for (int z = radius; z >= -radius; --z) {
                    class_2248 block;
                    class_2338 pos = start.method_10069(x, y, z);
                    if (pos.method_10264() <= 0 || (block = MetaHvHHandler.mc.field_1687.method_8320(pos).method_26204()) != class_2246.field_10443) continue;
                    blocks.add(pos);
                }
            }
        }
        return blocks;
    }

    private void swapHead() {
        class_1792 helmetItem = this.helmetType.isSelected("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439") ? class_1802.field_22027 : class_1802.field_8862;
        class_1799 current = MetaHvHHandler.mc.field_1724.method_6118(class_1304.field_6169);
        int helmetSlot = MetaHvHHandler.findSlotByItem(helmetItem);
        boolean wearingHelmet = current.method_31574(helmetItem);
        boolean wearingSanta = current.method_31574(class_1802.field_8575);
        if (wearingHelmet) {
            this.equipSantaHead();
        } else if (wearingSanta && helmetSlot != -1) {
            this.swapArmor(helmetSlot, 5);
        } else {
            MetaHvHHandler.notify("\u00a7c\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0448\u043b\u0435\u043c \u0438\u043b\u0438 \u0433\u043e\u043b\u043e\u0432\u0430 \u0434\u043b\u044f \u0441\u0432\u0430\u043f\u0430!");
        }
    }

    private void equipSantaHead() {
        for (int slot = 0; slot < MetaHvHHandler.mc.field_1724.method_31548().method_5439(); ++slot) {
            class_1799 stack = MetaHvHHandler.mc.field_1724.method_31548().method_5438(slot);
            if (!stack.method_31574(class_1802.field_8575) || !this.isSantaHead(stack)) continue;
            this.swapArmor(slot, 5);
            return;
        }
        MetaHvHHandler.notify("\u00a7c\u0413\u043e\u043b\u043e\u0432\u0430 \u0441\u0430\u043d\u0442\u044b \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435!");
    }

    private boolean hasSantaHead() {
        for (int slot = 0; slot < MetaHvHHandler.mc.field_1724.method_31548().method_5439(); ++slot) {
            class_1799 stack = MetaHvHHandler.mc.field_1724.method_31548().method_5438(slot);
            if (!stack.method_31574(class_1802.field_8575) || !this.isSantaHead(stack)) continue;
            return true;
        }
        return MetaHvHHandler.mc.field_1724.method_6118(class_1304.field_6169).method_31574(class_1802.field_8575);
    }

    private boolean isSantaHead(class_1799 stack) {
        return stack.method_31574(class_1802.field_8575);
    }

    private void swapBoots() {
        class_1799 current = MetaHvHHandler.mc.field_1724.method_6118(class_1304.field_6166);
        int goldSlot = MetaHvHHandler.findSlotByItem(class_1802.field_8753);
        class_1792 normal = MetaHvHHandler.findSlotByItem(class_1802.field_22030) != -1 ? class_1802.field_22030 : class_1802.field_8285;
        int normalSlot = MetaHvHHandler.findSlotByItem(normal);
        if (current.method_31574(class_1802.field_8753) && normalSlot != -1) {
            this.swapArmor(normalSlot, 8);
        } else if (goldSlot != -1) {
            this.swapArmor(goldSlot, 8);
        } else {
            MetaHvHHandler.notify("\u00a7c\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b \u0431\u043e\u0442\u0438\u043d\u043a\u0438 \u0434\u043b\u044f \u0441\u0432\u0430\u043f\u0430!");
        }
    }

    private void swapArmor(int fromSlot, int armorSlot) {
        int invFrom = fromSlot < 9 ? fromSlot + 36 : fromSlot;
        InventoryTask.clickSlot(invFrom, 0, class_1713.field_7790, false);
        InventoryTask.clickSlot(armorSlot, 0, class_1713.field_7790, false);
        InventoryTask.clickSlot(invFrom, 0, class_1713.field_7790, false);
    }

    private void syncSlot() {
        if (MetaHvHHandler.mc.field_1761 != null) {
            MetaHvHHandler.mc.field_1761.method_2911();
        }
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
        this.autoEcEnabled = false;
        this.miningPos = class_2338.field_10980;
    }
}
