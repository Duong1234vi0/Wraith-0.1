/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemTask;
import net.minecraft.class_1802;

public class ClickPearl
extends Module {
    private final BindSetting keySetting = new BindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u043d\u0430\u0436\u0430\u0442\u0438\u044f", "\u041a\u043d\u043e\u043f\u043a\u0430 \u0434\u043b\u044f \u0431\u0440\u043e\u0441\u043a\u0430 \u044d\u043d\u0434\u0435\u0440-\u0436\u0435\u043c\u0447\u0443\u0433\u0430");
    private boolean queued;

    public ClickPearl() {
        super("ClickPearl", "ClickPearl", ModuleCategory.PLAYER);
        this.setup(this.keySetting);
    }

    @EventHandler
    public void onKey(KeyEvent event) {
        if (event.isKeyDown(this.keySetting.getKey())) {
            this.queued = true;
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (ClickPearl.mc.field_1724 == null || ClickPearl.mc.field_1687 == null || !this.queued) {
            return;
        }
        if (ItemTask.getCooldownProgress(class_1802.field_8634) > 0.0f) {
            this.queued = false;
            return;
        }
        InventoryTask.swapAndUse(class_1802.field_8634, true);
        this.queued = false;
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.queued = false;
    }
}
