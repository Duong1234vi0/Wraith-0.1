package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="Full Bright", moduleDesc="Полная яркость", moduleCategory=ModuleCategory.RENDER)
public class FullBright
extends Module {
    @Subscribe
    private void onUpdate(EventTick e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!this.mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            this.mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 3));
        }
    }

    @Override
    public void onDisable() {
        if (this.mc.player == null) {
            return;
        }
        this.mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        super.onDisable();
    }
}

