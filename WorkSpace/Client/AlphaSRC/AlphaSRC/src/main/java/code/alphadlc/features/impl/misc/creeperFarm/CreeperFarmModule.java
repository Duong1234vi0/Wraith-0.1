/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc.creeperFarm;

import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.misc.creeperFarm.CreeperFarmController;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;

public class CreeperFarmModule
extends Module {
    private final CreeperFarmController controller = new CreeperFarmController();

    public CreeperFarmModule() {
        super("Creeper Farm", ModuleCategory.MISC);
    }

    @EventHandler
    public void tickEvent(TickEvent tickEvent) {
        this.controller.onTick();
    }

    @EventHandler
    public void rotationUpdateEvent(RotationUpdateEvent rotationUpdateEvent) {
        this.controller.onRotationUpdate();
    }

    public CreeperFarmController getController() {
        return this.controller;
    }
}
