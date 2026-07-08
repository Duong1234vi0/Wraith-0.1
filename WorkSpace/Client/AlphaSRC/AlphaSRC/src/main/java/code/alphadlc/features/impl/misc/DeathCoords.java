/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_5892
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_5892;

public class DeathCoords
extends Module {
    public DeathCoords() {
        super("DeathCoords", ModuleCategory.MISC);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (e.getPacket() instanceof class_5892) {
            String text = String.format("%.0f %.0f %.0f", DeathCoords.mc.field_1724.method_23317(), DeathCoords.mc.field_1724.method_23318(), DeathCoords.mc.field_1724.method_23321());
            ChatMessage.send((class_2561)class_2561.method_43470((String)text).method_27694(style -> style.method_10958(new class_2558(class_2558.class_2559.field_21462, text))));
        }
    }
}
