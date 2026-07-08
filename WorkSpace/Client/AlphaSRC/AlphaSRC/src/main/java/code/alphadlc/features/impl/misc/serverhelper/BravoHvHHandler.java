/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1713
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_2338;

public class BravoHvHHandler
extends ServerHelperHandler {
    private final List<BindSetting> binds;
    private final SliderSettings lowHpThreshold;
    private final BooleanSetting autoMedic;
    private final StopWatch medicTimer = new StopWatch();

    public BravoHvHHandler(List<BindSetting> binds, SliderSettings lowHpThreshold, BooleanSetting autoMedic) {
        this.binds = binds;
        this.lowHpThreshold = lowHpThreshold;
        this.autoMedic = autoMedic;
    }

    @Override
    public String getModeId() {
        return "BravoHvH";
    }

    @Override
    public String getOverlayTitle() {
        return "Bravo";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.binds;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        items.add(new ServerHelperRenderItem("\u0417\u0435\u043b\u044c\u0435 \u043a\u0438\u043b\u043b\u0435\u0440\u0430", "K", BravoHvHHandler.findSlotByName("\u043a\u0438\u043b\u043b\u0435\u0440") == -1 ? "\u2014" : "\u2713", class_1802.field_8436).withBind(this.binds.isEmpty() ? -1 : this.binds.get(0).getKey()));
        items.add(new ServerHelperRenderItem("\u0417\u0435\u043b\u044c\u0435 \u043c\u0435\u0434\u0438\u043a\u0430", "M", BravoHvHHandler.findSlotByName("\u043c\u0435\u0434\u0438\u043a") == -1 ? "\u2014" : "\u2713", class_1802.field_8436).withBind(this.binds.size() > 1 ? this.binds.get(1).getKey() : -1));
        items.add(new ServerHelperRenderItem("\u041f\u043e\u0437\u0432\u0430\u0442\u044c \u043f\u043e\u043c\u043e\u0449\u044c", "H", "\u2713", class_1802.field_8535).withBind(this.binds.size() > 2 ? this.binds.get(2).getKey() : -1));
        return items;
    }

    @Override
    public boolean onKey(int keyCode) {
        if (this.binds.isEmpty() || BravoHvHHandler.mc.field_1724 == null) {
            return false;
        }
        if (this.binds.get(0).getKey() != -1 && this.binds.get(0).getKey() == keyCode) {
            this.usePotion("\u043a\u0438\u043b\u043b\u0435\u0440", "\u0417\u0435\u043b\u044c\u0435 \u043a\u0438\u043b\u043b\u0435\u0440\u0430", false);
            return true;
        }
        if (this.binds.size() > 1 && this.binds.get(1).getKey() != -1 && this.binds.get(1).getKey() == keyCode) {
            this.usePotion("\u043c\u0435\u0434\u0438\u043a", "\u0417\u0435\u043b\u044c\u0435 \u043c\u0435\u0434\u0438\u043a\u0430", true);
            return true;
        }
        if (this.binds.size() > 2 && this.binds.get(2).getKey() != -1 && this.binds.get(2).getKey() == keyCode) {
            this.sendHelp();
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        int slot;
        if (!this.autoMedic.isValue() || BravoHvHHandler.mc.field_1724 == null) {
            return;
        }
        if (BravoHvHHandler.mc.field_1724.method_6032() <= this.lowHpThreshold.getValue() && this.medicTimer.finished(1000.0) && (slot = BravoHvHHandler.findSlotByName("\u043c\u0435\u0434\u0438\u043a")) != -1) {
            this.usePotionSlot(slot, true);
            this.medicTimer.reset();
        }
    }

    private void usePotion(String search, String display, boolean splashDown) {
        int slot = BravoHvHHandler.findSlotByName(search);
        if (slot == -1) {
            BravoHvHHandler.notify("\u00a7c" + display + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e!");
            return;
        }
        this.usePotionSlot(slot, splashDown);
    }

    private void usePotionSlot(int slot, boolean splashDown) {
        if (BravoHvHHandler.mc.field_1724 == null || BravoHvHHandler.mc.field_1761 == null) {
            return;
        }
        float oldPitch = BravoHvHHandler.mc.field_1724.method_36455();
        if (splashDown) {
            BravoHvHHandler.mc.field_1724.method_36457(90.0f);
        }
        int current = BravoHvHHandler.mc.field_1724.method_31548().field_7545;
        if (slot < 9) {
            BravoHvHHandler.mc.field_1724.method_31548().field_7545 = slot;
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            BravoHvHHandler.mc.field_1724.method_31548().field_7545 = current;
        } else {
            InventoryTask.clickSlot(slot, current, class_1713.field_7791, false);
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            InventoryTask.clickSlot(slot, current, class_1713.field_7791, false);
        }
        if (splashDown) {
            BravoHvHHandler.mc.field_1724.method_36457(oldPitch);
        }
    }

    private void sendHelp() {
        if (BravoHvHHandler.mc.field_1724 == null) {
            return;
        }
        class_2338 pos = BravoHvHHandler.mc.field_1724.method_24515();
        BravoHvHHandler.mc.field_1724.field_3944.method_45729("! \u041f\u043e\u043c\u043e\u0433\u0438\u0442\u0435, " + pos.method_10263() + " " + pos.method_10264() + " " + pos.method_10260());
        BravoHvHHandler.notify("\u00a7a\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u044b \u0432 \u0447\u0430\u0442!");
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
