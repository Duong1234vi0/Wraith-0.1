/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1802;

public class NoDelay
extends Module {
    public final MultiSelectSetting ignoreSetting = new MultiSelectSetting("\u0423\u0431\u0438\u0440\u0430\u0442\u044c \u0437\u0430\u0434\u0434\u0435\u0440\u0436\u043a\u0443 \u0443", "\u0420\u0430\u0437\u0440\u0435\u0448\u0430\u0435\u0442 \u0432\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0435 \u0432\u0430\u043c\u0438 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f").value("\u041f\u0440\u044b\u0436\u043a\u0430", "\u041f\u041a\u041c", "\u041b\u043e\u043c\u0430\u043d\u0438\u044f \u0431\u043b\u043e\u043a\u0430", "\u041f\u0443\u0437\u044b\u0440\u0435\u043a \u043e\u043f\u044b\u0442\u0430");

    public static NoDelay getInstance() {
        return Instance.get(NoDelay.class);
    }

    public NoDelay() {
        super("NoDelay", "NoDelay", ModuleCategory.PLAYER);
        this.setup(this.ignoreSetting);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (this.ignoreSetting.isSelected("\u041b\u043e\u043c\u0430\u043d\u0438\u044f \u0431\u043b\u043e\u043a\u0430")) {
            NoDelay.mc.field_1761.field_3716 = 0;
        }
        if (this.ignoreSetting.isSelected("\u041f\u0440\u044b\u0436\u043a\u0430")) {
            NoDelay.mc.field_1724.field_6228 = 0;
        }
        if (this.ignoreSetting.isSelected("\u041f\u041a\u041c") && (NoDelay.mc.field_1724 == null || !NoDelay.mc.field_1724.method_6115())) {
            NoDelay.mc.field_1752 = 0;
        }
        if (this.ignoreSetting.isSelected("\u041f\u0443\u0437\u044b\u0440\u0435\u043a \u043e\u043f\u044b\u0442\u0430") && NoDelay.mc.field_1724 != null && !NoDelay.mc.field_1724.method_6115() && NoDelay.mc.field_1724.method_6047().method_7909() == class_1802.field_8287) {
            NoDelay.mc.field_1752 = 0;
        }
    }
}
