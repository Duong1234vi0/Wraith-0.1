/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.block.PushEvent;
import code.alphadlc.events.player.PlayerCollisionEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2246;
import net.minecraft.class_2248;

public class NoPush
extends Module {
    private final MultiSelectSetting ignoreSetting = new MultiSelectSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c", "\u0420\u0430\u0437\u0440\u0435\u0448\u0430\u0435\u0442 \u0432\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0435 \u0432\u0430\u043c\u0438 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f").value("\u0412\u043e\u0434\u0430", "\u041a\u043e\u043b\u043b\u0438\u0437\u0438\u044f \u0431\u043b\u043e\u043a\u043e\u0432", "\u041a\u043e\u043b\u043b\u0438\u0437\u0438\u044f \u044d\u043d\u0442\u0438\u0442\u0438", "\u0420\u044b\u0445\u043b\u044b\u0439 \u0441\u043d\u0435\u0433", "\u0421\u043b\u0430\u0434\u043a\u0438\u0435 \u044f\u0433\u043e\u0434\u044b");

    public NoPush() {
        super("NoPush", "NoPush", ModuleCategory.PLAYER);
        this.setup(this.ignoreSetting);
    }

    @EventHandler
    public void onPush(PushEvent e) {
        switch (e.getType()) {
            case COLLISION: {
                e.setCancelled(this.ignoreSetting.isSelected("\u041a\u043e\u043b\u043b\u0438\u0437\u0438\u044f \u044d\u043d\u0442\u0438\u0442\u0438"));
                break;
            }
            case WATER: {
                e.setCancelled(this.ignoreSetting.isSelected("\u0412\u043e\u0434\u0430"));
                break;
            }
            case BLOCK: {
                e.setCancelled(this.ignoreSetting.isSelected("\u041a\u043e\u043b\u043b\u0438\u0437\u0438\u044f \u0431\u043b\u043e\u043a\u043e\u0432"));
            }
        }
    }

    @EventHandler
    public void onPlayerCollision(PlayerCollisionEvent e) {
        class_2248 block = e.getBlock();
        if (block.equals(class_2246.field_27879)) {
            e.setCancelled(this.ignoreSetting.isSelected("\u0420\u044b\u0445\u043b\u044b\u0439 \u0441\u043d\u0435\u0433"));
        } else if (block.equals(class_2246.field_16999)) {
            e.setCancelled(this.ignoreSetting.isSelected("\u0421\u043b\u0430\u0434\u043a\u0438\u0435 \u044f\u0433\u043e\u0434\u044b"));
        }
    }
}
