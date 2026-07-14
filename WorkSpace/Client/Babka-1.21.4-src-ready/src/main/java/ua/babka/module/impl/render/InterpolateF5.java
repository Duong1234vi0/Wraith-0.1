package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import net.minecraft.util.math.MathHelper;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.event.RotationEvent;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.ui.component.FreeLookComponent;

@ModuleInformation(moduleName="BetterF5", moduleDesc="Плавная камера от F5", moduleCategory=ModuleCategory.RENDER)
public class InterpolateF5
extends Module {
    private static final float SWITCH_ANIM_SPEED = 0.26f;
    private static final float DISTANCE_SPEED = 0.13f;
    private static final float ROTATION_SMOOTH = 0.28f;
    private static final float CAMERA_DISTANCE = 4.1f;
    private static final float SNEAK_OFFSET = 0.5f;
    private static final float JUMP_MULTIPLIER = 2.0f;
    private static final float ANIM_SPEED = 0.13f;
    private float currentDistance;
    private float prevDistance;
    private float currentYaw;
    private float prevYaw;
    private float currentPitch;
    private float prevPitch;
    private float heightOffset;
    private float prevHeightOffset;
    private boolean switchAnimating;
    private boolean wasThirdPerson;
    private boolean needsInit = true;

    @Subscribe
    private void onPlayerUpdate(EventPlayerUpdate event) {
        boolean isThirdPerson;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        boolean bl = isThirdPerson = !this.mc.options.getPerspective().isFirstPerson();
        if (isThirdPerson && !this.wasThirdPerson) {
            this.initCamera(true);
        }
        if (!isThirdPerson && this.wasThirdPerson) {
            this.needsInit = true;
            this.switchAnimating = false;
        }
        this.wasThirdPerson = isThirdPerson;
        if (isThirdPerson) {
            this.updateCamera();
        }
    }

    @Subscribe
    private void onRotation(RotationEvent event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (this.mc.options.getPerspective().isFirstPerson()) {
            return;
        }
        event.setYaw(this.getInterpolatedYaw(event.getPartialTicks()));
        event.setPitch(this.getInterpolatedPitch(event.getPartialTicks()));
    }

    private void initCamera(boolean animateSwitch) {
        if (this.mc.player == null) {
            return;
        }
        this.currentYaw = this.prevYaw = this.getReferenceYaw();
        this.currentPitch = this.prevPitch = this.getReferencePitch();
        this.prevDistance = animateSwitch ? 0.0f : 4.1f;
        this.currentDistance = this.prevDistance;
        this.prevHeightOffset = 0.0f;
        this.heightOffset = 0.0f;
        this.switchAnimating = animateSwitch;
        this.needsInit = false;
    }

    private void updateCamera() {
        if (this.mc.player == null) {
            return;
        }
        if (this.needsInit) {
            this.initCamera(true);
            return;
        }
        this.prevYaw = this.currentYaw;
        this.prevPitch = this.currentPitch;
        this.prevDistance = this.currentDistance;
        this.prevHeightOffset = this.heightOffset;
        this.currentYaw += MathHelper.wrapDegrees((float)(this.getReferenceYaw() - this.currentYaw)) * 0.28f;
        this.currentPitch = MathHelper.clamp((float)(this.currentPitch + (this.getReferencePitch() - this.currentPitch) * 0.28f), (float)-90.0f, (float)90.0f);
        float distanceSpeed = this.switchAnimating ? 0.26f : 0.13f;
        this.currentDistance += (4.1f - this.currentDistance) * distanceSpeed;
        if (this.switchAnimating && Math.abs(4.1f - this.currentDistance) <= 0.02f) {
            this.currentDistance = 4.1f;
            this.switchAnimating = false;
        }
        float targetOffset = 0.0f;
        if (this.mc.player.isSneaking()) {
            targetOffset = -0.5f;
        }
        if (!this.mc.player.isOnGround()) {
            targetOffset += (float)(-this.mc.player.getVelocity().y * 2.0);
        }
        this.heightOffset += (targetOffset - this.heightOffset) * 0.13f;
    }

    public float getInterpolatedYaw(float partialTicks) {
        if (this.mc.player == null) {
            return 0.0f;
        }
        return this.prevYaw + (this.currentYaw - this.prevYaw) * partialTicks;
    }

    public float getInterpolatedPitch(float partialTicks) {
        if (this.mc.player == null) {
            return 0.0f;
        }
        return MathHelper.clamp((float)(this.prevPitch + (this.currentPitch - this.prevPitch) * partialTicks), (float)-90.0f, (float)90.0f);
    }

    public float getInterpolatedDistance(float partialTicks) {
        return this.prevDistance + (this.currentDistance - this.prevDistance) * partialTicks;
    }

    public float getInterpolatedHeightOffset(float partialTicks) {
        return this.prevHeightOffset + (this.heightOffset - this.prevHeightOffset) * partialTicks;
    }

    private float getReferenceYaw() {
        if (FreeLookComponent.isActive()) {
            return FreeLookComponent.getFreeYaw();
        }
        return this.mc.player != null ? this.mc.player.getYaw() : 0.0f;
    }

    private float getReferencePitch() {
        if (FreeLookComponent.isActive()) {
            return FreeLookComponent.getFreePitch();
        }
        return this.mc.player != null ? this.mc.player.getPitch() : 0.0f;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.needsInit = true;
        this.wasThirdPerson = false;
        if (this.mc.player != null && !this.mc.options.getPerspective().isFirstPerson()) {
            this.initCamera(true);
            this.wasThirdPerson = true;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.needsInit = true;
        this.heightOffset = 0.0f;
        this.prevHeightOffset = 0.0f;
    }
}

