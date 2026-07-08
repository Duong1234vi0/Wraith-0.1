/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2868
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.block.BlockBreakingEvent;
import code.alphadlc.events.keyboard.HotBarScrollEvent;
import code.alphadlc.events.player.HotBarUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.ItemRendererEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.script.Script;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.Comparator;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2846;
import net.minecraft.class_2868;

public class AutoTool
extends Module {
    private final StopWatch swap = new StopWatch();
    private final StopWatch breaking = new StopWatch();
    private final Script script = new Script();
    private final Script swapBackScript = new Script();
    private final RadioSetting mode = new RadioSetting("\u0420\u0435\u0436\u0438\u043c", "\u0420\u0435\u0436\u0438\u043c AutoTool", new String[]{"\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041f\u0430\u043a\u0435\u0442\u043d\u044b\u0439"}, "\u041e\u0431\u044b\u0447\u043d\u044b\u0439");
    private class_1799 renderStack;
    private class_2338 lastBreakPos;
    private int previousSelectedSlot = -1;
    private long lastPacketBreakAt = 0L;

    public AutoTool() {
        super("AutoTool", "AutoTool", ModuleCategory.PLAYER);
        this.setup(this.mode);
    }

    @EventHandler
    public void onItemRenderer(ItemRendererEvent e) {
        if (this.renderStack != null && e.getHand().equals((Object)class_1268.field_5808) && Objects.equals(AutoTool.mc.field_1724, e.getPlayer())) {
            e.setStack(this.renderStack);
        }
    }

    @EventHandler
    public void onHotBarUpdate(HotBarUpdateEvent e) {
        if (!this.swapBackScript.isFinished()) {
            e.cancel();
        }
    }

    @EventHandler
    public void onHotBarScroll(HotBarScrollEvent e) {
        if (!this.swapBackScript.isFinished()) {
            e.cancel();
        }
    }

    @EventHandler
    public void onBlockBreaking(BlockBreakingEvent e) {
        class_1735 currentBestSlot;
        this.breaking.reset();
        this.lastBreakPos = e.blockPos();
        if ("\u041f\u0430\u043a\u0435\u0442\u043d\u044b\u0439".equals(this.mode.get())) {
            this.tryPacketBreak(e.blockPos(), e.direction());
            return;
        }
        if (!AutoTool.mc.field_1724.method_7337() && this.swapBackScript.isFinished() && this.swap.finished(350.0) && (currentBestSlot = this.findBestTool(this.lastBreakPos)) != null && currentBestSlot != InventoryTask.mainHandSlot()) {
            int hotbarSlot = this.getHotbarSlotFromSlot(currentBestSlot);
            if (hotbarSlot != -1) {
                if (this.previousSelectedSlot == -1) {
                    this.previousSelectedSlot = AutoTool.mc.field_1724.method_31548().field_7545;
                }
                InventoryTask.switchTo(hotbarSlot);
                this.swapBackScript.cleanup().addTickStep(0, () -> {
                    if (this.previousSelectedSlot != -1) {
                        InventoryTask.switchTo(this.previousSelectedSlot);
                        this.previousSelectedSlot = -1;
                    }
                });
            } else {
                this.renderStack = AutoTool.mc.field_1724.method_6047();
                InventoryTask.swapHand(currentBestSlot, class_1268.field_5808, true);
                this.swapBackScript.cleanup().addTickStep(0, () -> InventoryTask.swapHand(currentBestSlot, class_1268.field_5808, true, true));
            }
            this.swap.reset();
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        class_1735 currentBestSlot;
        this.script.update();
        if (!this.swapBackScript.isFinished() && this.swap.finished(350.0) && ((currentBestSlot = this.findBestTool(this.lastBreakPos)) != InventoryTask.mainHandSlot() || this.breaking.finished(100.0))) {
            if ((currentBestSlot == InventoryTask.mainHandSlot() || this.breaking.finished(100.0)) && this.previousSelectedSlot != -1) {
                InventoryTask.switchTo(this.previousSelectedSlot);
                this.previousSelectedSlot = -1;
            }
            this.script.cleanup().addTickStep(4, () -> {
                this.renderStack = null;
            });
            this.swapBackScript.update();
            this.swap.reset();
        }
    }

    private class_1735 findBestTool(class_2338 blockPos) {
        class_2680 state = AutoTool.mc.field_1687.method_8320(blockPos);
        if (PlayerInteractionHelper.isAir(state)) {
            return InventoryTask.mainHandSlot();
        }
        class_1735 bestHotbarTool = null;
        double bestHotbarSpeed = 0.0;
        for (int i = 0; i < 9; ++i) {
            double speed;
            int savedSelectedSlot = AutoTool.mc.field_1724.method_31548().field_7545;
            AutoTool.mc.field_1724.method_31548().field_7545 = i;
            class_1735 hotbarSlot = InventoryTask.mainHandSlot();
            AutoTool.mc.field_1724.method_31548().field_7545 = savedSelectedSlot;
            if (hotbarSlot == null || hotbarSlot.method_7677().method_7960() || (speed = (double)hotbarSlot.method_7677().method_7924(state)) == 1.0 || !(speed > bestHotbarSpeed)) continue;
            bestHotbarSpeed = speed;
            bestHotbarTool = hotbarSlot;
        }
        if (bestHotbarTool != null && bestHotbarSpeed > 1.0) {
            return bestHotbarTool;
        }
        return InventoryTask.slots().sorted(Comparator.comparing(slot -> slot.equals(InventoryTask.mainHandSlot()))).filter(s -> s.method_7677().method_7924(state) != 1.0f).max(Comparator.comparingDouble(s -> s.method_7677().method_7924(state))).orElse(null);
    }

    private void tryPacketBreak(class_2338 pos, class_2350 direction) {
        if (AutoTool.mc.field_1724 == null || mc.method_1562() == null || AutoTool.mc.field_1724.method_7337()) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastPacketBreakAt < 75L) {
            return;
        }
        class_1735 bestSlot = this.findBestTool(pos);
        int hotbarSlot = this.getHotbarSlotFromSlot(bestSlot);
        if (hotbarSlot == -1) {
            return;
        }
        int currentSlot = AutoTool.mc.field_1724.method_31548().field_7545;
        if (hotbarSlot == currentSlot) {
            return;
        }
        mc.method_1562().method_52787((class_2596)new class_2868(hotbarSlot));
        mc.method_1562().method_52787((class_2596)new class_2846(class_2846.class_2847.field_12968, pos, direction));
        mc.method_1562().method_52787((class_2596)new class_2846(class_2846.class_2847.field_12973, pos, direction));
        mc.method_1562().method_52787((class_2596)new class_2868(currentSlot));
        this.lastPacketBreakAt = now;
    }

    private int getHotbarSlotFromSlot(class_1735 slot) {
        if (slot == null || AutoTool.mc.field_1724 == null) {
            return -1;
        }
        int i = 0;
        while (i < 9) {
            int savedSelectedSlot = AutoTool.mc.field_1724.method_31548().field_7545;
            AutoTool.mc.field_1724.method_31548().field_7545 = i++;
            class_1735 hotbarSlot = InventoryTask.mainHandSlot();
            AutoTool.mc.field_1724.method_31548().field_7545 = savedSelectedSlot;
            if (!slot.equals(hotbarSlot)) continue;
            return i;
        }
        return -1;
    }
}
