/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.render.TextFactoryEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;

public class NameProtect
extends Module {
    TextSetting nameSetting = new TextSetting("\u0418\u043c\u044f", "").setText("Protected").setMax(16);
    BooleanSetting friendsSetting = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0434\u0440\u0443\u0437\u0435\u0439", "\u0421\u043a\u0440\u044b\u0432\u0430\u0435\u0442 \u043d\u0438\u043a\u043d\u0435\u0439\u043c\u044b \u0434\u0440\u0443\u0437\u0435\u0439").setValue(true);

    public NameProtect() {
        super("NameProtect", "NameProtect", ModuleCategory.PLAYER);
        this.setup(this.nameSetting, this.friendsSetting);
    }

    @EventHandler
    public void onTextFactory(TextFactoryEvent e) {
        e.replaceText(mc.method_1548().method_1676(), this.nameSetting.getText());
        if (this.friendsSetting.isValue()) {
            FriendUtils.getFriends().forEach(friend -> e.replaceText(friend.getName(), this.nameSetting.getText()));
        }
    }
}
