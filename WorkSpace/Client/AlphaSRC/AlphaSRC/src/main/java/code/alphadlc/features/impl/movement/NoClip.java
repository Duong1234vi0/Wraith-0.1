/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2708
 *  net.minecraft.class_2827
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_6374
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.block.BlockCollisionEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2708;
import net.minecraft.class_2827;
import net.minecraft.class_2828;
import net.minecraft.class_6374;

public class NoClip
extends Module {
    private List<class_2596<?>> packets = new CopyOnWriteArrayList();
    private class_238 box;
    private int tickCounter;

    public NoClip() {
        super("NoClip", ModuleCategory.MOVEMENT);
    }

    private boolean shouldPhase() {
        if (NoClip.mc.field_1724 == null || NoClip.mc.field_1687 == null) {
            return false;
        }
        class_238 hitbox = NoClip.mc.field_1724.method_5829();
        class_2338 min = new class_2338((int)Math.floor(hitbox.field_1323), (int)Math.floor(hitbox.field_1322), (int)Math.floor(hitbox.field_1321));
        class_2338 max = new class_2338((int)Math.floor(hitbox.field_1320), (int)Math.floor(hitbox.field_1325), (int)Math.floor(hitbox.field_1324));
        for (int x = min.method_10263(); x <= max.method_10263(); ++x) {
            for (int y = min.method_10264(); y <= max.method_10264(); ++y) {
                for (int z = min.method_10260(); z <= max.method_10260(); ++z) {
                    class_2338 pos = new class_2338(x, y, z);
                    class_2680 state = NoClip.mc.field_1687.method_8320(pos);
                    if (state.method_26215() || !NoClip.mc.field_1687.method_8320(pos).method_26220((class_1922)NoClip.mc.field_1687, pos).method_1090().stream().anyMatch(box -> box.method_994(hitbox.method_989((double)(-pos.method_10263()), (double)(-pos.method_10264()), (double)(-pos.method_10260()))))) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private void resumePackets() {
        if (NoClip.mc.field_1724 == null || NoClip.mc.field_1687 == null || this.state) {
            return;
        }
        if (!this.packets.isEmpty()) {
            for (class_2596<?> packet : new ArrayList(this.packets)) {
                mc.method_1562().method_52787(packet);
            }
            this.packets.clear();
            this.box = NoClip.mc.field_1724.method_5829();
        }
    }

    @EventHandler
    public void onCollide(BlockCollisionEvent e) {
        if (!this.state) {
            return;
        }
        class_2338 playerPos = class_2338.method_49638((class_2374)NoClip.mc.field_1724.method_19538());
        if (e.getBlockPos().equals((Object)playerPos.method_10074())) {
            return;
        }
        e.setState(class_2246.field_10124.method_9564());
    }

    private void adjustToSneakingSpeed() {
        if (NoClip.mc.field_1724 == null || !this.state) {
            return;
        }
        double SNEAK_SPEED_MULTIPLIER = 0.3;
        class_243 motion = NoClip.mc.field_1724.method_18798();
        double horizontalSpeed = Math.sqrt(motion.field_1352 * motion.field_1352 + motion.field_1350 * motion.field_1350);
        if (horizontalSpeed > 0.0) {
            double baseSpeed = 0.6;
            double targetSpeed = baseSpeed * 0.3;
            double scale = targetSpeed / horizontalSpeed;
            NoClip.mc.field_1724.method_18800(motion.field_1352 * scale, motion.field_1351, motion.field_1350 * scale);
        }
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (!this.state) {
            return;
        }
        double x = NoClip.mc.field_1724.method_23317();
        double y = NoClip.mc.field_1724.method_23318();
        double z = NoClip.mc.field_1724.method_23321();
        float yaw = NoClip.mc.field_1724.method_36454();
        float pitch = NoClip.mc.field_1724.method_36455();
        boolean onGround = NoClip.mc.field_1724.method_24828();
        if (event.getType() == PacketEvent.Type.SEND) {
            class_2596<?> p = event.getPacket();
            if (this.shouldPhase() && !(p instanceof class_2827) && !(p instanceof class_6374)) {
                this.packets.add(p);
                event.cancel();
            }
        }
        if (event.getType() == PacketEvent.Type.RECEIVE && event.getPacket() instanceof class_2708) {
            this.resumePackets();
            Objects.requireNonNull(mc.method_1562()).method_52787((class_2596)new class_2828.class_2830(x - 1000.0, y, z - 1000.0, yaw, pitch, false, false));
            mc.method_1562().method_52787((class_2596)new class_2828.class_2830(x, y, z, yaw, pitch, NoClip.mc.field_1724.method_24828(), false));
            NoClip.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2830(NoClip.mc.field_1724.method_23317(), NoClip.mc.field_1724.method_23318(), NoClip.mc.field_1724.method_23321(), NoClip.mc.field_1724.method_36454(), NoClip.mc.field_1724.method_36455(), NoClip.mc.field_1724.method_24828(), false));
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.resumePackets();
        this.box = null;
        this.tickCounter = 0;
    }

    public List<class_2596<?>> getPackets() {
        return this.packets;
    }

    public class_238 getBox() {
        return this.box;
    }

    public int getTickCounter() {
        return this.tickCounter;
    }
}
