package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.LivingEntity;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.module.setting.ValueUnit;
import ua.babka.util.Instance;

@ModuleInformation(moduleName="Elytra Motion", moduleDesc="Контроль полёта на элитре", moduleCategory=ModuleCategory.MOVEMENT)
public class ElytraMotion
extends Module {
    private boolean waitTarget;
    private final SliderSetting distance = new SliderSetting("Дистанция", ValueUnit.countable("блок", "блока", "блоков"), 3.0, 1.0, 6.0, 0.1f);
    private final BooleanSetting bypass = new BooleanSetting("Обход", false);

    @Subscribe
    private void onPlayerTick(EventPlayerUpdate e2) {
        if (this.mc.player == null) {
            return;
        }
        LivingEntity target = Instance.get(AttackAura.class).getTarget();
        if (target == null) {
            if (!this.waitTarget) {
                this.mc.player.setNoGravity(false);
                this.waitTarget = true;
            }
            return;
        }
        this.waitTarget = false;
        float dist = (float)this.mc.player.getEyePos().distanceTo(target.getBoundingBox().getCenter());
        if (this.mc.player.isGliding() && dist < this.distance.getFloatValue()) {
            if (this.bypass.getValue()) {
                float yaw = this.mc.player.getYaw();
                double rad = Math.toRadians(yaw);
                double forward = 0.01;
                double down = -1.0E-4;
                double moveX = -Math.sin(rad) * forward;
                double moveZ = Math.cos(rad) * forward;
                this.mc.player.setVelocity(moveX, down, moveZ);
            } else {
                this.mc.player.setVelocity(0.0, 0.0, 0.0);
            }
            this.mc.player.setNoGravity(true);
        } else {
            this.mc.player.setNoGravity(false);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (this.mc.player == null) {
            return;
        }
        this.waitTarget = false;
        this.mc.player.setNoGravity(false);
    }
}

