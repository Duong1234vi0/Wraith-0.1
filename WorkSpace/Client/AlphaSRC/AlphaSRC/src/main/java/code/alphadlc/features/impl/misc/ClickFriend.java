/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_239
 *  net.minecraft.class_3966
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_3966;

public class ClickFriend
extends Module {
    private final BindSetting friendBind = new BindSetting("\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0434\u0440\u0443\u0433\u0430", "\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c/\u0443\u0434\u0430\u043b\u0438\u0442\u044c \u0434\u0440\u0443\u0433\u0430");

    public ClickFriend() {
        super("ClickFriend", "Click Friend", ModuleCategory.MISC);
        this.setup(this.friendBind);
    }

    @EventHandler
    public void onKey(KeyEvent e) {
        class_3966 result;
        class_239 class_2392;
        if (e.isKeyDown(this.friendBind.getKey()) && (class_2392 = ClickFriend.mc.field_1765) instanceof class_3966 && (class_2392 = (result = (class_3966)class_2392).method_17782()) instanceof class_1657) {
            class_1657 player = (class_1657)class_2392;
            if (FriendUtils.isFriend((class_1297)player)) {
                FriendUtils.removeFriend(player);
            } else {
                FriendUtils.addFriend(player);
            }
        }
    }
}
