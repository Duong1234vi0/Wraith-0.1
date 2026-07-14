package ua.babka.module.impl.render;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import ua.babka.Babka;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;

@ModuleInformation(moduleName="Cape", moduleDesc="Свой плащ для игрока", moduleCategory=ModuleCategory.RENDER)
public class Cape
extends Module {
    private final ModeListSetting targets = new ModeListSetting("На кого работать", new BooleanSetting("Себя", true), new BooleanSetting("Друзья", true), new BooleanSetting("Игроки", false), new BooleanSetting("Другие", false));

    public static Cape getInstance() {
        return Babka.getInstance().getModuleStorage().get(Cape.class);
    }

    public boolean shouldRenderCape(AbstractClientPlayerEntity player) {
        if (!this.isEnabled() || player == null) {
            return false;
        }
        boolean isSelf = player == this.mc.player;
        boolean isFriend = FriendRepository.isFriend(player.getNameForScoreboard());
        boolean selfEnabled = this.targets.isEnabled("Себя");
        boolean friendsEnabled = this.targets.isEnabled("Друзья");
        boolean playersEnabled = this.targets.isEnabled("Игроки");
        boolean othersEnabled = this.targets.isEnabled("Другие");
        if (isSelf) {
            return selfEnabled;
        }
        if (isFriend) {
            return friendsEnabled;
        }
        if (playersEnabled) {
            return true;
        }
        return othersEnabled;
    }
}

