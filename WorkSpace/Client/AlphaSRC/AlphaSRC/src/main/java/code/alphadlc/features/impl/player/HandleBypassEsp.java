/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1923
 *  net.minecraft.class_2596
 *  net.minecraft.class_2672
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1923;
import net.minecraft.class_2596;
import net.minecraft.class_2672;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleBypassEsp
extends Module {
    private static final Logger log = LoggerFactory.getLogger(HandleBypassEsp.class);
    private final Set<class_1923> haha = new HashSet<class_1923>();

    public HandleBypassEsp() {
        super("HandleBypassEsp", ModuleCategory.COMBAT);
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (HandleBypassEsp.mc.field_1687 == null || HandleBypassEsp.mc.field_1724 == null || event.isSend()) {
            return;
        }
        class_2596<?> class_25962 = event.getPacket();
        if (!(class_25962 instanceof class_2672)) {
            return;
        }
        class_2672 packet = (class_2672)class_25962;
        class_1923 chunkPos = new class_1923(packet.method_11523(), packet.method_11524());
        if (!this.haha.add(chunkPos)) {
            return;
        }
        mc.execute(() -> {
            for (class_1297 entity : HandleBypassEsp.mc.field_1687.method_18112()) {
                class_1657 player;
                if (!(entity instanceof class_1657) || (player = (class_1657)entity) == HandleBypassEsp.mc.field_1724 || !new class_1923(player.method_24515()).equals((Object)chunkPos)) continue;
                String string = player.method_5477().getString();
            }
        });
    }

    @Override
    public void deactivate() {
        this.haha.clear();
        super.deactivate();
    }
}
