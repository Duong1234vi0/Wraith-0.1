/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2720
 *  net.minecraft.class_2856
 *  net.minecraft.class_2856$class_2857
 *  net.minecraft.class_634
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.math.time.TimerUtil;
import net.minecraft.class_2596;
import net.minecraft.class_2720;
import net.minecraft.class_2856;
import net.minecraft.class_634;

public class ServerRPSpoofer
extends Module {
    private ResourcePackAction currentAction = ResourcePackAction.WAIT;
    private final TimerUtil counter = TimerUtil.create();

    public ServerRPSpoofer() {
        super("ServerRPSpoof", "RPSpoofer", ModuleCategory.MISC);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (e.getPacket() instanceof class_2720) {
            this.currentAction = ResourcePackAction.ACCEPT;
            e.cancel();
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        class_634 networkHandler = mc.method_1562();
        if (networkHandler != null) {
            if (this.currentAction == ResourcePackAction.ACCEPT) {
                networkHandler.method_52787((class_2596)new class_2856(ServerRPSpoofer.mc.field_1724.method_5667(), class_2856.class_2857.field_13016));
                this.currentAction = ResourcePackAction.SEND;
                this.counter.resetCounter();
            } else if (this.currentAction == ResourcePackAction.SEND && this.counter.isReached(300L)) {
                networkHandler.method_52787((class_2596)new class_2856(ServerRPSpoofer.mc.field_1724.method_5667(), class_2856.class_2857.field_13017));
                this.currentAction = ResourcePackAction.WAIT;
            }
        }
    }

    @Override
    public void deactivate() {
        this.currentAction = ResourcePackAction.WAIT;
        super.deactivate();
    }

    public static enum ResourcePackAction {
        ACCEPT,
        SEND,
        WAIT;

    }
}
