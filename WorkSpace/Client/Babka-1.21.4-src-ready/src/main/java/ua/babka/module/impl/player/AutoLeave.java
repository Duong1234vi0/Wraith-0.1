package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import ua.babka.event.EventTick;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="Auto Leave", moduleDesc="Автоматический выход из игры", moduleCategory=ModuleCategory.PLAYER)
public class AutoLeave
extends Module {
    private final SliderSetting distance = new SliderSetting("Дистанция", 8.0, 1.0, 64.0, 1.0);
    private final ModeSetting mode = new ModeSetting("Действие", "Hub", "Hub", "Spawn", "Disconnect");

    @Subscribe
    private void onTick(EventTick event) {
        if (this.mc.player == null || this.mc.world == null || this.mc.getNetworkHandler() == null) {
            return;
        }
        double triggerDistance = this.distance.getValue();
        for (PlayerEntity player : this.mc.world.getPlayers()) {
            if (player == this.mc.player || player.isSpectator() || FriendRepository.isFriend(player.getNameForScoreboard()) || (double)this.mc.player.distanceTo((Entity)player) > triggerDistance) continue;
            if (this.mode.is("Hub")) {
                this.mc.getNetworkHandler().sendChatCommand("hub");
            } else if (this.mode.is("Spawn")) {
                this.mc.getNetworkHandler().sendChatCommand("spawn");
            } else {
                this.mc.getNetworkHandler().getConnection().disconnect((Text)Text.literal((String)"AutoLeave"));
            }
            this.setEnabled(false);
            return;
        }
    }
}

