package ua.babka.module.impl.render;

import net.minecraft.entity.player.PlayerEntity;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;

@ModuleInformation(moduleName="CustomModels", moduleDesc="Свои скины и модели", moduleCategory=ModuleCategory.RENDER)
public class CustomModels
extends Module {
    public static final String RABBIT = "Crazy Rabbit";
    public final ModeSetting models = new ModeSetting("Моделька", "Crazy Rabbit", "Crazy Rabbit");
    public final BooleanSetting friends = new BooleanSetting("Друзья", true);

    public String getModelName() {
        return this.models.getValue();
    }

    public boolean shouldApplyTo(PlayerEntity player) {
        boolean localPlayer;
        if (!this.isEnabled() || player == null || player.isSpectator()) {
            return false;
        }
        boolean bl = localPlayer = this.mc.player != null && player == this.mc.player;
        if (localPlayer) {
            return true;
        }
        return this.friends.getValue() && FriendRepository.isFriend(player.getNameForScoreboard());
    }
}

