/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828
 *  net.minecraft.class_2828$class_2830
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.MotionEvent;
import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class AirStuck
extends Module {
    private BooleanSetting grimBypass = new BooleanSetting("\u041e\u0431\u0445\u043e\u0434 \u0433\u0440\u0438\u043c", "\u0421\u043f\u0443\u0444\u0438\u0442 onGround \u0438 \u0440\u0435\u0434\u043a\u0438\u0435 \u0444\u0438\u043a\u0441-\u043f\u0430\u043a\u0435\u0442\u044b \u0431\u0435\u0437 \u0434\u0451\u0440\u0433\u0430\u043d\u0438\u0439").setValue(false);
    private SliderSettings spoofDelay = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 spoof", "\u0418\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u043e\u0442\u043f\u0440\u0430\u0432\u043a\u0438 \u0444\u0438\u043a\u0441-\u043f\u0430\u043a\u0435\u0442\u0430 \u0432 \u043c\u0441").setValue(150.0f).range(50, 500).step(10.0f).visible(() -> this.grimBypass.isValue());
    private class_243 frozenPosition = null;
    private boolean frozenOnGround = false;
    private long lastSpoofTime = 0L;
    private boolean sendingPacket = false;

    public AirStuck() {
        super("AirStuck", "AirStuck", ModuleCategory.MOVEMENT);
        this.setup(this.grimBypass, this.spoofDelay);
    }

    @Override
    public void activate() {
        super.activate();
        if (AirStuck.mc.field_1724 != null) {
            this.frozenPosition = AirStuck.mc.field_1724.method_19538();
            this.frozenOnGround = AirStuck.mc.field_1724.method_24828();
            this.lastSpoofTime = 0L;
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.frozenPosition = null;
        this.frozenOnGround = false;
        this.lastSpoofTime = 0L;
        this.sendingPacket = false;
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (AirStuck.mc.field_1724 == null || AirStuck.mc.field_1687 == null || this.frozenPosition == null) {
            return;
        }
        AirStuck.mc.field_1724.method_18799(class_243.field_1353);
        AirStuck.mc.field_1724.field_6007 = true;
        AirStuck.mc.field_1724.field_6017 = 0.0f;
        if (AirStuck.mc.field_1724.method_19538().method_1025(this.frozenPosition) > 1.0E-4) {
            AirStuck.mc.field_1724.method_5814(this.frozenPosition.field_1352, this.frozenPosition.field_1351, this.frozenPosition.field_1350);
        }
        AirStuck.mc.field_1724.field_6014 = this.frozenPosition.field_1352;
        AirStuck.mc.field_1724.field_6036 = this.frozenPosition.field_1351;
        AirStuck.mc.field_1724.field_5969 = this.frozenPosition.field_1350;
        if (this.grimBypass.isValue()) {
            AirStuck.mc.field_1724.method_24830(true);
            this.sendSpoofPacketIfNeeded();
        }
    }

    @EventHandler
    public void onMotion(MotionEvent e) {
        if (AirStuck.mc.field_1724 == null || AirStuck.mc.field_1687 == null || this.frozenPosition == null) {
            return;
        }
        e.setX(this.frozenPosition.field_1352);
        e.setY(this.frozenPosition.field_1351);
        e.setZ(this.frozenPosition.field_1350);
        e.setOnGround(this.grimBypass.isValue() || this.frozenOnGround);
    }

    @EventHandler
    public void onMove(MoveEvent e) {
        if (AirStuck.mc.field_1724 == null || AirStuck.mc.field_1687 == null || this.frozenPosition == null) {
            return;
        }
        e.setMovement(class_243.field_1353);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (AirStuck.mc.field_1724 == null || AirStuck.mc.field_1687 == null || this.frozenPosition == null) {
            return;
        }
        if (this.sendingPacket) {
            return;
        }
        if (e.getType() == PacketEvent.Type.SEND && e.getPacket() instanceof class_2828) {
            e.cancel();
            this.sendFrozenPacket(this.grimBypass.isValue() || this.frozenOnGround);
        }
    }

    private void sendSpoofPacketIfNeeded() {
        long now = System.currentTimeMillis();
        if (now - this.lastSpoofTime < (long)this.spoofDelay.getValue()) {
            return;
        }
        this.sendFrozenPacket(true);
        this.lastSpoofTime = now;
    }

    private void sendFrozenPacket(boolean onGround) {
        if (AirStuck.mc.field_1724 == null || AirStuck.mc.field_1724.field_3944 == null || this.frozenPosition == null) {
            return;
        }
        this.sendingPacket = true;
        try {
            AirStuck.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2830(this.frozenPosition.field_1352, this.frozenPosition.field_1351, this.frozenPosition.field_1350, AirStuck.mc.field_1724.method_36454(), AirStuck.mc.field_1724.method_36455(), onGround, false));
        }
        finally {
            this.sendingPacket = false;
        }
    }

    public BooleanSetting getGrimBypass() {
        return this.grimBypass;
    }

    public SliderSettings getSpoofDelay() {
        return this.spoofDelay;
    }

    public class_243 getFrozenPosition() {
        return this.frozenPosition;
    }

    public boolean isFrozenOnGround() {
        return this.frozenOnGround;
    }

    public long getLastSpoofTime() {
        return this.lastSpoofTime;
    }

    public boolean isSendingPacket() {
        return this.sendingPacket;
    }
}
