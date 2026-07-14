package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;
import ua.babka.module.setting.ValueUnit;

@ModuleInformation(moduleName="Timer", moduleDesc="Ускоряет передвижение игрока", moduleCategory=ModuleCategory.MOVEMENT)
public class Timer
extends Module {
    private final SliderSetting speed = new SliderSetting("Скорость", ValueUnit.abbreviation("x"), 1.0, 1.0, 2.0, 0.1);

    @Subscribe
    private void onPlayerUpdate(EventPlayerUpdate e2) {
        if (this.mc.player == null) {
            return;
        }
        EntityAttributeInstance attr = this.mc.player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        if (attr != null) {
            attr.setBaseValue(0.1 * (double)this.speed.getFloatValue());
        }
    }

    @Override
    public void onDisable() {
        EntityAttributeInstance attr;
        super.onDisable();
        if (this.mc.player != null && (attr = this.mc.player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED)) != null) {
            attr.setBaseValue(0.1);
        }
    }
}

