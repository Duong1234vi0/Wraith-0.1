/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2246;
import net.minecraft.class_2338;

public class SafeWalk
extends Module {
    public SafeWalk() {
        super("SafeWalk", ModuleCategory.PLAYER);
        this.setup(new Setting[0]);
    }

    @EventHandler
    public void onInput(InputEvent e) {
        boolean atEdge;
        if (SafeWalk.mc.field_1724 == null || SafeWalk.mc.field_1687 == null) {
            return;
        }
        class_2338 pos = SafeWalk.mc.field_1724.method_24515().method_10074();
        boolean bl = atEdge = SafeWalk.mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10124 && SafeWalk.mc.field_1724.method_24828();
        if (atEdge) {
            e.setSneak(true);
        }
    }
}
