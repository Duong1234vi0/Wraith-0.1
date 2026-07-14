package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import net.minecraft.util.math.Vec3d;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="DragonFly", moduleDesc="Ускоряет полёт в креативе (/fly) + резкие движения", moduleCategory=ModuleCategory.MOVEMENT)
public class DragonFly
extends Module {
    private final SliderSetting speed = new SliderSetting("Скорость", 2.0, 0.5, 5.0, 0.1);
    private final BooleanSetting instantMotion = new BooleanSetting("Резкие движения", true);

    @Override
    public void onDisable() {
        super.onDisable();
        if (this.mc.player != null) {
            this.mc.player.getAbilities().setFlySpeed(0.05f);
            this.mc.player.sendAbilitiesUpdate();
        }
    }

    @Subscribe
    public void onTick(EventTick e2) {
        if (this.mc.player == null) {
            return;
        }
        if (this.mc.player.getAbilities().flying) {
            float flySpeed = (float)this.speed.getValue() * 0.05f;
            this.mc.player.getAbilities().setFlySpeed(flySpeed);
            this.mc.player.sendAbilitiesUpdate();
        } else {
            this.mc.player.getAbilities().setFlySpeed(0.05f);
            this.mc.player.sendAbilitiesUpdate();
        }
        if (!this.mc.player.getAbilities().flying || !this.instantMotion.getValue()) {
            return;
        }
        Vec3d velocity = this.mc.player.getVelocity();
        float forward = this.mc.player.input.movementForward;
        float sideways = this.mc.player.input.movementSideways;
        if (forward == 0.0f && sideways == 0.0f) {
            this.mc.player.setVelocity(0.0, velocity.y, 0.0);
        } else {
            double speedVal = this.speed.getValue();
            Vec3d forwardVec = Vec3d.fromPolar((float)0.0f, (float)this.mc.player.getYaw()).normalize();
            Vec3d rightVec = Vec3d.fromPolar((float)0.0f, (float)(this.mc.player.getYaw() - 90.0f)).normalize();
            double velX = forwardVec.x * (double)forward * speedVal + rightVec.x * (double)sideways * speedVal;
            double velZ = forwardVec.z * (double)forward * speedVal + rightVec.z * (double)sideways * speedVal;
            this.mc.player.setVelocity(velX, velocity.y, velZ);
        }
        if (this.mc.options.jumpKey.isPressed()) {
            this.mc.player.setVelocity(this.mc.player.getVelocity().x, this.speed.getValue() * 0.2, this.mc.player.getVelocity().z);
        } else if (this.mc.options.sneakKey.isPressed()) {
            this.mc.player.setVelocity(this.mc.player.getVelocity().x, -this.speed.getValue() * 0.2, this.mc.player.getVelocity().z);
        }
    }
}

