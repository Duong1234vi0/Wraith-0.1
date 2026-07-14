package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import ua.babka.event.EventEntityHitBox;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;

@ModuleInformation(moduleName="NoEntityTrace", moduleDesc="Игнорирует сущности при клике", moduleCategory=ModuleCategory.COMBAT)
public class NoEntityTrace
extends Module {
    private final BooleanSetting invisible = new BooleanSetting("Невидимки", true);

    @Subscribe
    private void onEntityHitBox(EventEntityHitBox e2) {
        Entity entity = e2.getEntity();
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity2 = (LivingEntity)entity;
        if (entity2.isInvisible() && !this.invisible.getValue()) {
            return;
        }
        e2.setSize(-0.75f);
    }
}

