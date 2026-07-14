package ua.babka.module.impl.player;

import ua.babka.friend.Friend;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.StringSetting;

@ModuleInformation(moduleName="Streamer Mode", moduleDesc="Скрывает ник в стрим-режиме", moduleCategory=ModuleCategory.MISC)
public class StreamerMode
extends Module {
    private final StringSetting myNick = new StringSetting("Мой ник", "babka.xyz");
    private final StringSetting friendNick = new StringSetting("Ник друзей", "Friend");
    public final BooleanSetting hideFriends = new BooleanSetting("Скрыть друзей", false);

    public String getCustomName() {
        String nick = this.myNick.getValue().isEmpty() ? "babka.xyz" : this.myNick.getValue();
        return this.isEnabled() ? nick : this.mc.player.getNameForScoreboard();
    }

    public String getCustomName(String originalName) {
        String nick;
        if (!this.isEnabled() || this.mc.player == null) {
            return originalName;
        }
        String me = this.mc.player.getNameForScoreboard();
        String string = nick = this.myNick.getValue().isEmpty() ? "babka.xyz" : this.myNick.getValue();
        if (originalName.contains(me)) {
            return originalName.replace(me, nick);
        }
        if (this.hideFriends.getValue()) {
            String fNick = this.friendNick.getValue().isEmpty() ? "Friend" : this.friendNick.getValue();
            for (Friend friend : FriendRepository.getFriends()) {
                if (!originalName.contains(friend.name())) continue;
                return originalName.replace(friend.name(), fNick);
            }
        }
        return originalName;
    }
}

