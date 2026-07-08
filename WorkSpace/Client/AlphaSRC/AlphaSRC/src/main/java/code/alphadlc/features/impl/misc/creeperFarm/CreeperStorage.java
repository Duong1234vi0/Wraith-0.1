/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2596
 *  net.minecraft.class_2604
 */
package code.alphadlc.features.impl.misc.creeperFarm;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import net.minecraft.class_1297;
import net.minecraft.class_2596;
import net.minecraft.class_2604;
import org.jetbrains.annotations.Nullable;

public class CreeperStorage
implements QuickImports {
    Deque<class_1297> entityDeque = new ArrayDeque<class_1297>();

    public void onTick() {
        this.entityDeque.removeIf(entity -> !entity.method_5805() || entity.method_31481());
    }

    public void onPacket(PacketEvent packetEvent) {
        class_2604 packet;
        class_1297 entity;
        class_2596<?> class_25962 = packetEvent.getPacket();
        if (class_25962 instanceof class_2604 && (entity = CreeperStorage.mc.field_1687.method_8469((packet = (class_2604)class_25962).method_11167())) != null && entity.method_5805() && !entity.method_31481() && !this.entityDeque.contains(entity)) {
            this.entityDeque.add(entity);
        }
    }

    @Nullable
    public class_1297 getTarget() {
        return !this.entityDeque.isEmpty() ? this.getClosestEntity() : null;
    }

    @Nullable
    private class_1297 getClosestEntity() {
        return this.entityDeque.stream().min(Comparator.comparingDouble(entity -> CreeperStorage.mc.field_1724.method_5739(entity))).orElse(null);
    }
}
