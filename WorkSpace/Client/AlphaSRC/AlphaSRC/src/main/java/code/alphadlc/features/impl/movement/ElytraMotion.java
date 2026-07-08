/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1735
 *  net.minecraft.class_1802
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2868
 *  net.minecraft.class_2886
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemTask;
import code.alphadlc.utils.math.time.StopWatch;
import java.lang.runtime.SwitchBootstraps;
import java.util.Objects;
import java.util.Random;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_2886;

public class ElytraMotion
extends Module {
    private StopWatch timer = new StopWatch();
    private class_243 targetPosition = null;
    private Random random = new Random();
    private double rotationAngle = 0.0;
    private final BooleanSetting auto = new BooleanSetting("\u0410\u0432\u0442\u043e-\u0444\u0435\u0439\u0440", "\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442 \u0444\u0435\u0439\u0440\u0432\u0435\u0440\u043a");
    private final RadioSetting mode = new RadioSetting("\u0420\u0435\u0436\u0438\u043c", "\u0420\u0435\u0436\u0438\u043c \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0430", new String[]{"\u041b\u0435\u0433\u0438\u0442\u043d\u043e", "\u041f\u0430\u043a\u0435\u0442\u043d\u043e"}, "\u041b\u0435\u0433\u0438\u0442\u043d\u043e").visible(() -> this.auto.isValue());
    private final SliderSettings timer2 = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0438\u0441\u043f.", "").setValue(500.0f).range(0.0f, 10000.0f).step(10.0f).visible(() -> this.auto.isValue());

    public static ElytraMotion getInstance() {
        return Instance.get(ElytraMotion.class);
    }

    public ElytraMotion() {
        super("ElytraMotion", "ElytraMotion", ModuleCategory.MOVEMENT);
        this.setup(this.auto, this.mode, this.timer2);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (!this.state || ElytraMotion.mc.field_1724 == null || ElytraMotion.mc.field_1687 == null || !ElytraMotion.mc.field_1724.method_6128()) {
            return;
        }
        AttackAura aura = Instance.get(AttackAura.class);
        if (this.auto.isValue() && this.timer.every(this.timer2.getValue())) {
            this.useFirework();
            this.timer.reset();
        }
        if (aura.isState() && aura.isState() && aura.getTarget() != null && ElytraMotion.mc.field_1724.method_5739((class_1297)aura.getTarget()) < aura.attackRange.getValue() - 0.425f) {
            ElytraMotion.mc.field_1724.method_18800(0.0, 0.02, 0.0);
        }
    }

    private void useFirework() {
        if ("\u041f\u0430\u043a\u0435\u0442\u043d\u043e".equals(this.mode.getValue())) {
            this.useFireworkPacket();
        } else {
            this.useFireworkLegit();
        }
    }

    private void useFireworkLegit() {
        if (ElytraMotion.mc.field_1724.method_6115() && ElytraMotion.mc.field_1724.method_6058() == class_1268.field_5808) {
            return;
        }
        if (ElytraMotion.mc.field_1724.method_6047().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            ElytraMotion.mc.field_1724.method_6104(class_1268.field_5808);
            return;
        }
        if (ElytraMotion.mc.field_1724.method_6079().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.interactItem(class_1268.field_5810);
            ElytraMotion.mc.field_1724.method_6104(class_1268.field_5810);
            return;
        }
        int hotbarSlot = InventoryTask.findHotbarSlot(class_1802.field_8639);
        if (hotbarSlot != -1) {
            InventoryTask.switchTo(hotbarSlot);
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            ElytraMotion.mc.field_1724.method_6104(class_1268.field_5808);
            return;
        }
        class_1735 slot = InventoryTask.getSlot(class_1802.field_8639);
        if (slot == null) {
            return;
        }
        InventoryTask.swapHand(slot, class_1268.field_5810, false);
        PlayerInteractionHelper.interactItem(class_1268.field_5810);
        ElytraMotion.mc.field_1724.method_6104(class_1268.field_5810);
    }

    private void useFireworkPacket() {
        if (mc.method_1562() == null) {
            return;
        }
        if (ItemTask.getCooldownProgress(class_1802.field_8639) > 0.0f) {
            return;
        }
        if (ElytraMotion.mc.field_1724.method_6047().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5808, seq, ElytraMotion.mc.field_1724.method_36454(), ElytraMotion.mc.field_1724.method_36455()));
            return;
        }
        if (ElytraMotion.mc.field_1724.method_6079().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5810, seq, ElytraMotion.mc.field_1724.method_36454(), ElytraMotion.mc.field_1724.method_36455()));
            return;
        }
        int rocketHotbarSlot = InventoryTask.findHotbarSlot(class_1802.field_8639);
        if (rocketHotbarSlot != -1) {
            int previousSlot = ElytraMotion.mc.field_1724.method_31548().field_7545;
            if (rocketHotbarSlot != previousSlot) {
                mc.method_1562().method_52787((class_2596)new class_2868(rocketHotbarSlot));
            }
            PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5808, seq, ElytraMotion.mc.field_1724.method_36454(), ElytraMotion.mc.field_1724.method_36455()));
            if (rocketHotbarSlot != previousSlot) {
                mc.method_1562().method_52787((class_2596)new class_2868(previousSlot));
            }
            return;
        }
        class_1735 slot = InventoryTask.getSlot(class_1802.field_8639);
        if (slot == null) {
            ChatMessage.brandmessage("\u00a7c\u041d\u0435\u0442 \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0430 \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435");
            return;
        }
        InventoryTask.swapHand(slot, class_1268.field_5810, false);
        PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5810, seq, ElytraMotion.mc.field_1724.method_36454(), ElytraMotion.mc.field_1724.method_36455()));
        InventoryTask.swapHand(slot, class_1268.field_5810, false, true);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        AttackAura aura = Instance.get(AttackAura.class);
        if (aura.isState() && aura.getTarget() != null && ElytraMotion.mc.field_1724.method_5739((class_1297)aura.getTarget()) < aura.attackRange.getValue() - 0.15f) {
            class_2596<?> class_25962 = e.getPacket();
            Objects.requireNonNull(class_25962);
            class_2596<?> class_25963 = class_25962;
            int n = 0;
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{}, class_25963, n)) {
                default: 
            }
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }

    public StopWatch getTimer() {
        return this.timer;
    }

    public class_243 getTargetPosition() {
        return this.targetPosition;
    }

    public Random getRandom() {
        return this.random;
    }

    public double getRotationAngle() {
        return this.rotationAngle;
    }

    public BooleanSetting getAuto() {
        return this.auto;
    }

    public RadioSetting getMode() {
        return this.mode;
    }

    public SliderSettings getTimer2() {
        return this.timer2;
    }
}
