/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2868
 */
package code.alphadlc.main.listener.impl;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.item.UsingItemEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.main.listener.Listener;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import java.lang.runtime.SwitchBootstraps;
import java.util.Objects;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_2868;

public class EventListener
implements Listener {
    public static boolean serverSprint;
    public static int selectedSlot;

    @EventHandler
    public void onTick(TickEvent e) {
        Network.tick();
        InventoryFlowManager.tick();
        AlphaDLC.getInstance().getDraggableRepository().draggable().forEach(AbstractDraggable::tick);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2848.class, class_2868.class}, class_25963, n)) {
            case 0: {
                class_2848 command = (class_2848)class_25963;
                serverSprint = switch (command.method_12365()) {
                    case class_2848.class_2849.field_12981 -> true;
                    case class_2848.class_2849.field_12985 -> false;
                    default -> serverSprint;
                };
                break;
            }
            case 1: {
                class_2868 slot = (class_2868)class_25963;
                selectedSlot = slot.method_12442();
                break;
            }
        }
        Network.packet(e);
        AlphaDLC.getInstance().getAttackPerpetrator().onPacket(e);
        AlphaDLC.getInstance().getDraggableRepository().draggable().forEach(drag -> drag.packet(e));
    }

    @EventHandler
    public void onUsingItemEvent(UsingItemEvent e) {
        AlphaDLC.getInstance().getAttackPerpetrator().onUsingItem(e);
    }
}
