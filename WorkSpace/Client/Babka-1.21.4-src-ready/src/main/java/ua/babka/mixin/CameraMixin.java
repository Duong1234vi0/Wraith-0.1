package ua.babka.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ua.babka.event.RotationEvent;
import ua.babka.mixin.ICameraMixin;
import ua.babka.module.impl.render.InterpolateF5;
import ua.babka.module.impl.render.NoRender;
import ua.babka.util.Instance;

@Mixin(value={Camera.class})
public abstract class CameraMixin {
    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;setRotation(FF)V"))
    private void redirectSetRotation(Camera instance, float yaw, float pitch, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {
        RotationEvent event = new RotationEvent(yaw, pitch, tickDelta);
        event.post();
        float newYaw = event.getYaw();
        float newPitch = event.getPitch();
        if (thirdPerson && inverseView) {
            newYaw += 180.0f;
            newPitch = -newPitch;
        }
        instance.setRotation(newYaw, newPitch);
    }

    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;clipToSpace(F)F"))
    private float redirectClipToSpace(Camera instance, float distance, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {
        InterpolateF5 module;
        if (thirdPerson && (module = Instance.get(InterpolateF5.class)) != null && module.isEnabled()) {
            return instance.clipToSpace(module.getInterpolatedDistance(tickDelta));
        }
        NoRender noRender = Instance.get(NoRender.class);
        if (noRender != null && noRender.isEnabled() && noRender.elements.isEnabled("Камераклип")) {
            return distance;
        }
        return instance.clipToSpace(distance);
    }

    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;moveBy(FFF)V"))
    private void redirectMoveBy(Camera instance, float x2, float y2, float z2, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {
        InterpolateF5 module;
        float newY = y2;
        if (thirdPerson && (module = Instance.get(InterpolateF5.class)) != null && module.isEnabled()) {
            newY += module.getInterpolatedHeightOffset(tickDelta);
        }
        ((ICameraMixin)instance).callMoveBy(x2, newY, z2);
    }
}

