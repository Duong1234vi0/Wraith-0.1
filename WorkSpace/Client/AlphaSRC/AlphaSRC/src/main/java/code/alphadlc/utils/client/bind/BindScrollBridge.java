/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3675$class_307
 */
package code.alphadlc.utils.client.bind;

import code.alphadlc.events.keyboard.HotBarScrollEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_3675;

public class BindScrollBridge
implements QuickImports {
    @EventHandler
    public void onHotBarScroll(HotBarScrollEvent event) {
        if (BindScrollBridge.mc.field_1755 != null || event.getVertical() == 0.0) {
            return;
        }
        int code = event.getVertical() > 0.0 ? 998 : 999;
        SafeEvents.dispatch(() -> new KeyEvent(null, class_3675.class_307.field_1668, code, 1));
    }
}
