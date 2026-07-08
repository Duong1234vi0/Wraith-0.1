/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1703
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2281
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_2745
 *  net.minecraft.class_2769
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 *  net.minecraft.class_437
 *  net.minecraft.class_476
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.utils.RaycastAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.task.TaskPriority;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1268;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_2769;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_476;

public class ChestStealer
extends Module {
    private final StopWatch stopWatch = new StopWatch();
    private final StopWatch openDelayWatch = new StopWatch();
    private final Map<String, class_1792> itemMap = new HashMap<String, class_1792>();
    private final SelectSetting modeSetting = new SelectSetting("\u0422\u0438\u043f", "\u0412\u044b\u0431\u0438\u0440\u0430\u0435\u0442 \u0442\u0438\u043f \u0441\u0442\u0438\u043b\u0430").value("\u0424\u0430\u043d\u0422\u0430\u0439\u043c", "\u0418\u0437\u0431\u0440\u0430\u043d\u043d\u044b\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439");
    private final SliderSettings delaySetting = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043c\u0435\u0436\u0434\u0443 \u043a\u043b\u0438\u043a\u0430\u043c\u0438 \u043f\u043e \u0441\u043b\u043e\u0442\u0443").setValue(100.0f).range(0, 1000).visible(() -> {
        String mode = this.modeSetting.getSelected();
        return mode.equals("\u0418\u0437\u0431\u0440\u0430\u043d\u043d\u044b\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") || mode.equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439");
    });
    private final BooleanSetting autoOpen = new BooleanSetting("\u0410\u0432\u0442\u043e \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u0435", "\u041d\u0430\u0432\u043e\u0434\u0438\u0442\u0441\u044f \u043d\u0430 \u0441\u0443\u043d\u0434\u0443\u043a \u0438 \u043e\u0442\u043a\u0440\u044b\u0432\u0430\u0435\u0442 \u0435\u0433\u043e \u0441\u0430\u043c").visible(() -> this.modeSetting.getSelected().equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439"));
    private final MultiSelectSetting itemSettings = new MultiSelectSetting("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b, \u043a\u043e\u0442\u043e\u0440\u044b\u0435 \u0432\u043e\u0440 \u0431\u0443\u0434\u0435\u0442 \u043f\u043e\u0434\u0431\u0438\u0440\u0430\u0442\u044c").value("\u0413\u043e\u043b\u043e\u0432\u0430 \u0438\u0433\u0440\u043e\u043a\u0430", "\u0422\u043e\u0442\u0435\u043c \u0431\u0435\u0441\u0441\u043c\u0435\u0440\u0442\u0438\u044f", "\u042d\u043b\u0438\u0442\u0440\u044b", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043c\u0435\u0447", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0448\u043b\u0435\u043c", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0435 \u043f\u043e\u043d\u043e\u0436\u0438", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0435 \u0431\u043e\u0442\u0438\u043d\u043a\u0438", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0441\u043b\u0438\u0442\u043e\u043a", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043b\u043e\u043c").visible(() -> this.modeSetting.getSelected().equals("\u0418\u0437\u0431\u0440\u0430\u043d\u043d\u044b\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"));

    public ChestStealer() {
        super("ChestStealer", "ChestStealer", ModuleCategory.MISC);
        this.setup(this.modeSetting, this.delaySetting, this.autoOpen, this.itemSettings);
        this.initializeItemMap();
    }

    private void initializeItemMap() {
        this.itemMap.put("\u0413\u043e\u043b\u043e\u0432\u0430 \u0438\u0433\u0440\u043e\u043a\u0430", class_1802.field_8575);
        this.itemMap.put("\u0422\u043e\u0442\u0435\u043c \u0431\u0435\u0441\u0441\u043c\u0435\u0440\u0442\u0438\u044f", class_1802.field_8288);
        this.itemMap.put("\u042d\u043b\u0438\u0442\u0440\u044b", class_1802.field_8833);
        this.itemMap.put("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043c\u0435\u0447", class_1802.field_22022);
        this.itemMap.put("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0448\u043b\u0435\u043c", class_1802.field_22027);
        this.itemMap.put("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", class_1802.field_22028);
        this.itemMap.put("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0435 \u043f\u043e\u043d\u043e\u0436\u0438", class_1802.field_22029);
        this.itemMap.put("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0435 \u0431\u043e\u0442\u0438\u043d\u043a\u0438", class_1802.field_22030);
        this.itemMap.put("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0441\u043b\u0438\u0442\u043e\u043a", class_1802.field_22020);
        this.itemMap.put("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043b\u043e\u043c", class_1802.field_22024);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        String mode;
        if (ChestStealer.mc.field_1724 == null || ChestStealer.mc.field_1687 == null) {
            return;
        }
        switch (mode = this.modeSetting.getSelected()) {
            case "\u0424\u0430\u043d\u0422\u0430\u0439\u043c": {
                this.handleFunTimeMode();
                break;
            }
            case "\u0418\u0437\u0431\u0440\u0430\u043d\u043d\u044b\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b": 
            case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439": {
                this.handleDefaultModes(mode);
            }
        }
    }

    private void handleFunTimeMode() {
        class_476 sh;
        class_437 class_4372 = ChestStealer.mc.field_1755;
        if (class_4372 instanceof class_476 && (sh = (class_476)class_4372).method_25440().getString().toLowerCase().contains("\u043c\u0438\u0441\u0442\u0438\u0447\u0435\u0441\u043a\u0438\u0439") && !ChestStealer.mc.field_1724.method_7357().method_7904(class_1802.field_8054.method_7854())) {
            ((class_1707)sh.method_17577()).field_7761.stream().filter(s -> s.method_7681() && !s.field_7871.equals((Object)ChestStealer.mc.field_1724.method_31548()) && this.stopWatch.every(150.0)).forEach(s -> InventoryTask.clickSlot(s, 0, class_1713.field_7794, true));
        }
    }

    private void handleDefaultModes(String mode) {
        class_1703 class_17032 = ChestStealer.mc.field_1724.field_7512;
        if (class_17032 instanceof class_1707) {
            class_1707 sh = (class_1707)class_17032;
            sh.field_7761.forEach(s -> {
                boolean isDefaultMode = mode.equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439");
                if (s.method_7681() && !s.field_7871.equals((Object)ChestStealer.mc.field_1724.method_31548()) && (isDefaultMode || this.whiteList(s.method_7677().method_7909())) && this.stopWatch.every(this.delaySetting.getValue())) {
                    InventoryTask.clickSlot(s, 0, class_1713.field_7794, true);
                }
            });
        }
        if (mode.equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439") && this.autoOpen.isValue() && ChestStealer.mc.field_1755 == null) {
            this.tryOpenChest(this.findNearestChest(6, 200.25), false);
        }
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (e.getType() != 0 || ChestStealer.mc.field_1724 == null || ChestStealer.mc.field_1687 == null || ChestStealer.mc.field_1755 != null) {
            return;
        }
        if (!this.modeSetting.getSelected().equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439") || !this.autoOpen.isValue()) {
            return;
        }
        class_2338 chestPos = this.findNearestChest(6, 200.25);
        if (chestPos == null) {
            return;
        }
        Turns target = MathAngle.calculateAngle(this.getChestInteractVec(chestPos));
        TurnsConnection.INSTANCE.rotateTo(target, TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_1, this);
    }

    private class_2338 findNearestChest(int range, double maxDistanceSq) {
        class_2338 playerPos = ChestStealer.mc.field_1724.method_24515();
        class_2338.class_2339 nearest = new class_2338.class_2339();
        double nearestDistSq = Double.MAX_VALUE;
        for (class_2338 pos : class_2338.method_10097((class_2338)playerPos.method_10069(-range, -range, -range), (class_2338)playerPos.method_10069(range, range, range))) {
            double distSq;
            class_2680 state = ChestStealer.mc.field_1687.method_8320(pos);
            if (!state.method_27852(class_2246.field_10034) && !state.method_27852(class_2246.field_10380) || !((distSq = ChestStealer.mc.field_1724.method_5707(class_243.method_24953((class_2382)pos))) < nearestDistSq) || !(distSq <= maxDistanceSq)) continue;
            nearestDistSq = distSq;
            nearest.method_10101((class_2382)pos);
        }
        return nearestDistSq < Double.MAX_VALUE ? nearest.method_10062() : null;
    }

    private class_243 getChestInteractVec(class_2338 pos) {
        class_243 eye = ChestStealer.mc.field_1724.method_33571();
        class_243 center = class_243.method_24953((class_2382)pos);
        double dx = eye.field_1352 - center.field_1352;
        double dy = eye.field_1351 - center.field_1351;
        double dz = eye.field_1350 - center.field_1350;
        double absX = Math.abs(dx);
        double absY = Math.abs(dy);
        double absZ = Math.abs(dz);
        if (absX >= absY && absX >= absZ) {
            return new class_243(center.field_1352 + (dx > 0.0 ? 0.45 : -0.45), class_3532.method_15350((double)eye.field_1351, (double)(center.field_1351 - 0.4), (double)(center.field_1351 + 0.4)), class_3532.method_15350((double)eye.field_1350, (double)(center.field_1350 - 0.45), (double)(center.field_1350 + 0.45)));
        }
        if (absZ >= absY) {
            return new class_243(class_3532.method_15350((double)eye.field_1352, (double)(center.field_1352 - 0.45), (double)(center.field_1352 + 0.45)), class_3532.method_15350((double)eye.field_1351, (double)(center.field_1351 - 0.4), (double)(center.field_1351 + 0.4)), center.field_1350 + (dz > 0.0 ? 0.45 : -0.45));
        }
        return new class_243(class_3532.method_15350((double)eye.field_1352, (double)(center.field_1352 - 0.45), (double)(center.field_1352 + 0.45)), center.field_1351 + (dy > 0.0 ? 0.45 : -0.45), class_3532.method_15350((double)eye.field_1350, (double)(center.field_1350 - 0.45), (double)(center.field_1350 + 0.45)));
    }

    private boolean canInteractWithChest(class_2338 chestPos, double reach) {
        class_3965 hit;
        Turns rotation = TurnsConnection.INSTANCE.getRotation();
        if (rotation == null) {
            rotation = new Turns(ChestStealer.mc.field_1724.method_36454(), ChestStealer.mc.field_1724.method_36455());
        }
        if ((hit = RaycastAngle.raycast(reach, rotation, false)) == null || hit.method_17783() != class_239.class_240.field_1332) {
            return false;
        }
        class_2338 hitPos = hit.method_17777();
        if (hitPos.equals((Object)chestPos)) {
            return true;
        }
        class_2338 partner = this.getDoubleChestPartner(chestPos);
        return partner != null && hitPos.equals((Object)partner);
    }

    private void tryOpenChest(class_2338 chestPos, boolean ignored) {
        class_3965 hit;
        if (chestPos == null || ChestStealer.mc.field_1755 != null) {
            return;
        }
        if (ChestStealer.mc.field_1724.method_5707(class_243.method_24953((class_2382)chestPos)) > 200.25 || !this.openDelayWatch.finished(200.0)) {
            return;
        }
        if (this.canInteractWithChest(chestPos, 6.0) && (hit = RaycastAngle.raycast(6.0, TurnsConnection.INSTANCE.getRotation(), false)) != null && hit.method_17783() == class_239.class_240.field_1332) {
            ChestStealer.mc.field_1761.method_2896(ChestStealer.mc.field_1724, class_1268.field_5808, hit);
            ChestStealer.mc.field_1724.method_6104(class_1268.field_5808);
            this.openDelayWatch.reset();
        }
    }

    private class_2338 getDoubleChestPartner(class_2338 pos) {
        class_2680 state = ChestStealer.mc.field_1687.method_8320(pos);
        class_2248 class_22482 = state.method_26204();
        if (!(class_22482 instanceof class_2281)) {
            return null;
        }
        class_2281 chestBlock = (class_2281)class_22482;
        class_2745 type = (class_2745)state.method_11654((class_2769)class_2281.field_10770);
        if (type == class_2745.field_12569) {
            return null;
        }
        class_2350 facing = (class_2350)state.method_11654((class_2769)class_2281.field_10768);
        class_2338 partner = pos.method_10093(type == class_2745.field_12574 ? facing.method_10160() : facing.method_10170());
        if (ChestStealer.mc.field_1687.method_8320(partner).method_26204() instanceof class_2281) {
            return partner.method_10062();
        }
        return null;
    }

    @Override
    public void deactivate() {
        TurnsConnection.INSTANCE.clear();
        TurnsConnection.INSTANCE.setRotation(null);
        super.deactivate();
    }

    private boolean whiteList(class_1792 item) {
        List<String> selected = this.itemSettings.getSelected();
        if (selected == null || selected.isEmpty()) {
            return false;
        }
        for (String selectedName : selected) {
            class_1792 mappedItem = this.itemMap.get(selectedName);
            if (mappedItem == null || !mappedItem.equals(item)) continue;
            return true;
        }
        if (ChestStealer.mc.field_1687 != null && ChestStealer.mc.field_1724 != null) {
            class_1799 stack = item.method_7854();
            String itemNameLower = stack.method_7964().getString().toLowerCase();
            for (String selectedName : selected) {
                String selectedLower = selectedName.toLowerCase();
                if (!itemNameLower.contains(selectedLower) && !selectedLower.contains(itemNameLower)) continue;
                return true;
            }
        }
        return false;
    }
}
