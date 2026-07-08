/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc.newAutoMine;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.misc.newAutoMine.AutoMineController;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;

public class NewAutoMineModule
extends Module {
    AutoMineController controller = new AutoMineController();

    public NewAutoMineModule() {
        super("Test Auto Mine", ModuleCategory.MISC);
    }

    @EventHandler
    public void onTick(TickEvent tickEvent) {
        this.controller.onTick();
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent rotationUpdateEvent) {
        this.controller.onRotationUpdate(rotationUpdateEvent);
    }

    @EventHandler
    public void onInput(InputEvent inputEvent) {
        this.controller.onInput(inputEvent);
    }
}
