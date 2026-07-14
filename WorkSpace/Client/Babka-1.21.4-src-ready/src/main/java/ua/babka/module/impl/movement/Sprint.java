package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.network.OtherClientPlayerEntity;
import ua.babka.Babka;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.player.FreeCamera;
import ua.babka.module.setting.BooleanSetting;

@ModuleInformation(moduleName="Sprint", moduleDesc="Авто-бег", moduleCategory=ModuleCategory.MOVEMENT)
public class Sprint
extends Module {
    private final BooleanSetting disableInWater = new BooleanSetting("Отжимать в воде", false);

    @Subscribe
    public void onUpdate(EventTick event) {
        boolean canSprint;
        if (this.mc.player == null) {
            return;
        }
        this.mc.options.sprintKey.setPressed(false);
        OtherClientPlayerEntity fakePlayer = Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer;
        boolean bl = canSprint = fakePlayer == null;
        if (this.disableInWater.getValue() && (this.mc.player.isTouchingWater() || this.mc.player.isSubmergedInWater())) {
            return;
        }
        canSprint = canSprint && !this.mc.player.isTouchingWater() || this.mc.player.isSubmergedInWater();
        canSprint = canSprint && !this.mc.player.isGliding() && this.mc.player.isWalking() && this.mc.player.canSprint() && !this.mc.player.isUsingItem() && !this.mc.player.isBlind() && (!this.mc.player.hasVehicle() || this.mc.player.getVehicle().canSprintAsVehicle() && this.mc.player.getVehicle().isLogicalSideForUpdatingMovement() && !this.mc.player.isGliding() && (!this.mc.player.shouldSlowDown() || this.mc.player.isSubmergedInWater())) && this.mc.player.input.hasForwardMovement() && !this.mc.player.horizontalCollision && !this.mc.player.collidedSoftly;
        this.mc.player.setSprinting(canSprint);
    }
}

