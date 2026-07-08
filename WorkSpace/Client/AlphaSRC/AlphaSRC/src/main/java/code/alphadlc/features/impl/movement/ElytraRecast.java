/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class ElytraRecast
extends Module {
    public ElytraRecast() {
        super("ElytraRecast", "Elytra Recast", ModuleCategory.MOVEMENT);
        this.setup(new Setting[0]);
    }

    private boolean wearingElytra() {
        if (ElytraRecast.mc.field_1724 == null) {
            return false;
        }
        class_1799 chest = ElytraRecast.mc.field_1724.method_6118(class_1304.field_6174);
        return chest.method_31574(class_1802.field_8833);
    }

    private boolean grimContext() {
        return true;
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (PlayerInteractionHelper.nullCheck() || !this.wearingElytra() || !this.grimContext()) {
            return;
        }
        if (ElytraRecast.mc.field_1724.method_24828()) {
            event.setJumping(true);
        } else if (!ElytraRecast.mc.field_1724.method_6128()) {
            PlayerInteractionHelper.startFallFlying();
        }
    }
}
