/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2561
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.display.hud.Notifications;
import code.alphadlc.display.hud.StaffList;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import net.minecraft.class_1297;
import net.minecraft.class_2561;

public class AutoLeave
extends Module {
    private final SelectSetting leaveType = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0432\u044b\u0431\u0440\u0430\u0442\u044c \u0442\u0438\u043f \u0432\u044b\u0445\u043e\u0434\u0430").value("/hub", "\u0412\u044b\u0445\u043e\u0434 \u0441 \u0441\u0435\u0440\u0432\u0435\u0440\u0430", "/clan home", "/spawn");
    private final MultiSelectSetting triggerSetting = new MultiSelectSetting("\u0421\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0442\u044c \u043d\u0430", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435, \u0432 \u043a\u0430\u043a\u0438\u0445 \u0441\u043b\u0443\u0447\u0430\u044f\u0445 \u043f\u0440\u043e\u0438\u0437\u043e\u0439\u0434\u0435\u0442 \u0432\u044b\u0445\u043e\u0434").value("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", "\u0410\u0434\u043c\u0438\u043d\u043e\u0432");
    private final SliderSettings distanceSetting = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043b\u044f \u0430\u043a\u0442\u0438\u0432\u0430\u0446\u0438\u0438 \u0430\u0432\u0442\u043e-\u0432\u044b\u0445\u043e\u0434\u0430").setValue(10.0f).range(5, 40).visible(() -> this.triggerSetting.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432"));

    public AutoLeave() {
        super("AutoLeave", "AutoLeave", ModuleCategory.MISC);
        this.setup(this.leaveType, this.triggerSetting, this.distanceSetting);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (Network.isPvp()) {
            return;
        }
        if (this.triggerSetting.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432")) {
            AutoLeave.mc.field_1687.method_18456().stream().filter(p -> AutoLeave.mc.field_1724.method_5739((class_1297)p) < this.distanceSetting.getValue() && AutoLeave.mc.field_1724 != p && !FriendUtils.isFriend((class_1297)p)).findFirst().ifPresent(p -> this.leave((class_2561)p.method_5477().method_27661().method_27693(" - \u041f\u043e\u044f\u0432\u0438\u043b\u0441\u044f \u0440\u044f\u0434\u043e\u043c " + AutoLeave.mc.field_1724.method_5739((class_1297)p) + "\u043c")));
        }
        StaffList staffList = Instance.getDraggable(StaffList.class);
        if (this.triggerSetting.isSelected("\u0410\u0434\u043c\u0438\u043d\u043e\u0432") && staffList != null && staffList.hasStaff()) {
            this.leave(class_2561.method_30163((String)"\u0421\u0442\u0430\u0444\u0444 \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440\u0435"));
        }
    }

    public void leave(class_2561 text) {
        switch (this.leaveType.getSelected()) {
            case "/hub": {
                Notifications.getInstance().addList((class_2561)class_2561.method_30163((String)"[AutoLeave] ").method_27661().method_10852(text), 10000);
                mc.method_1562().method_45730("hub");
                break;
            }
            case "\u0432\u044b\u0445\u043e\u0434 \u0441 \u0441\u0435\u0440\u0432\u0435\u0440\u0430": {
                mc.method_1562().method_48296().method_10747((class_2561)class_2561.method_30163((String)"[Auto Leave] \n").method_27661().method_10852(text));
                break;
            }
            case "/spawn": {
                assert (AutoLeave.mc.field_1724 != null);
                AutoLeave.mc.field_1724.field_3944.method_45729("/spawn");
                break;
            }
            case "/clan home": {
                assert (AutoLeave.mc.field_1724 != null);
                AutoLeave.mc.field_1724.field_3944.method_45729("/clan home");
            }
        }
        this.setState(false);
    }
}
