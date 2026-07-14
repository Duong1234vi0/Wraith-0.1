package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="No Jump Delay", moduleDesc="Убирает задержку прыжка", moduleCategory=ModuleCategory.MOVEMENT)
public class NoJumpDelay
extends Module {
    @Subscribe
    private void onUpdate(EventTick e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        this.mc.player.jumpingCooldown = 0;
    }
}

