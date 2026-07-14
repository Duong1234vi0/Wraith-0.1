package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.item.Items;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="Fast Exp", moduleDesc="Быстрый опыт", moduleCategory=ModuleCategory.PLAYER)
public class FastExp
extends Module {
    @Subscribe
    private void onPlayerUpdate(EventPlayerUpdate e2) {
        if (this.mc.player.getMainHandStack().getItem() != Items.EXPERIENCE_BOTTLE && this.mc.player.getOffHandStack().getItem() != Items.EXPERIENCE_BOTTLE) {
            return;
        }
        this.mc.itemUseCooldown = 0;
    }
}

