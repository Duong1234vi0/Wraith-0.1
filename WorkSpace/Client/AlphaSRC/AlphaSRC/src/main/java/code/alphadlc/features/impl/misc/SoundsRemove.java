/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2767
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2596;
import net.minecraft.class_2767;

public class SoundsRemove
extends Module {
    public static SoundsRemove getInstance() {
        return Instance.get(SoundsRemove.class);
    }

    public SoundsRemove() {
        super("SoundsRemove", "SoundsRemove", ModuleCategory.MISC);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (!this.isState() || e.isSend()) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        if (!(class_25962 instanceof class_2767)) {
            return;
        }
        class_2767 packet = (class_2767)class_25962;
        String soundId = packet.method_11894().method_55840();
        if (soundId != null && soundId.contains("entity.wither")) {
            e.setCancelled(true);
        }
    }
}
