/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 */
package code.alphadlc.features.impl.movement.autoMystGlider.handers;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.features.impl.movement.autoMystGlider.handers.InternalNoclipFly;
import code.alphadlc.features.impl.movement.autoMystGlider.states.ConnectionState;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlideState;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_2596;

public class NoClipHandler
implements QuickImports {
    private final List<class_2596<?>> packets = new CopyOnWriteArrayList();
    private final ConnectionState connectionState;
    private final GlideState glideState;
    private long lastPulseTime;
    private long fullyDoneTime;

    public NoClipHandler(GlideState glideState, ConnectionState connectionState) {
        this.glideState = glideState;
        this.connectionState = connectionState;
    }

    public void onPacket(PacketEvent packetEvent) {
        if (this.isDone()) {
            if (PlayerInteractionHelper.nullCheck()) {
                return;
            }
            if (packetEvent.isSend()) {
                this.packets.add(packetEvent.getPacket());
                packetEvent.cancel();
            }
        }
    }

    public void onTick() {
        if (NoClipHandler.mc.field_1724 == null) {
            return;
        }
        if (this.isDone()) {
            if (this.lastPulseTime == -1L) {
                this.start();
            }
            InternalNoclipFly.get().onTick();
            this.update();
        } else {
            this.end();
        }
    }

    public void onMove(MoveEvent moveEvent) {
        if (NoClipHandler.mc.field_1724 == null) {
            return;
        }
        if (this.isDone()) {
            InternalNoclipFly.get().onMove(moveEvent);
        }
    }

    private boolean isDone() {
        if (this.glideState.fullyDone()) {
            if (this.fullyDoneTime == -1L) {
                this.fullyDoneTime = System.currentTimeMillis();
            }
        } else {
            this.fullyDoneTime = -1L;
        }
        return System.currentTimeMillis() - this.fullyDoneTime > 3000L && !this.connectionState.isReconnected();
    }

    public void start() {
        if (NoClipHandler.mc.field_1724 != null) {
            this.lastPulseTime = System.currentTimeMillis();
        }
    }

    public void end() {
        InternalNoclipFly.get().reset();
        if (!this.packets.isEmpty()) {
            this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
            this.packets.clear();
        }
        this.lastPulseTime = -1L;
    }

    public void update() {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastPulseTime >= 400L) {
            this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
            this.packets.clear();
            this.lastPulseTime = now;
        }
    }
}
