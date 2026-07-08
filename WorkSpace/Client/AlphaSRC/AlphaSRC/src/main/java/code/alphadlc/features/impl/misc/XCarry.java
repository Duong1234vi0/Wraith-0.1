/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2815
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2596;
import net.minecraft.class_2815;

public class XCarry
extends Module {
    public XCarry() {
        super("XCarry", ModuleCategory.MISC);
    }

    @EventHandler
    public void onS(PacketEvent e) {
        class_2815 zalupa2;
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_2815 && (zalupa2 = (class_2815)class_25962).method_36168() == 0) {
            e.cancel();
        }
    }
}
