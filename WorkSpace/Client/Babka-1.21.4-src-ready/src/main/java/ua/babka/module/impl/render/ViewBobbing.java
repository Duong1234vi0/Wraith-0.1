package ua.babka.module.impl.render;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;

@ModuleInformation(moduleName="ViewBobbing", moduleDesc="Настройка покачивания камеры", moduleCategory=ModuleCategory.RENDER)
public class ViewBobbing
extends Module {
    private final ModeSetting mode = new ModeSetting("Режим", "None", "None", "Reduced");

    public boolean shouldCancel() {
        return this.isEnabled() && this.mode.is("None");
    }

    public boolean shouldReplace() {
        return this.isEnabled() && this.mode.is("Reduced");
    }

    public void applyReducedBobbing(MatrixStack matrices, float tickDelta) {
        Entity entity = this.mc.getCameraEntity();
        if (!(entity instanceof AbstractClientPlayerEntity)) {
            return;
        }
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)entity;
        float f2 = player.distanceMoved - player.lastDistanceMoved;
        float g2 = -(player.distanceMoved + f2 * tickDelta);
        float h2 = MathHelper.lerp((float)tickDelta, (float)player.prevStrideDistance, (float)player.strideDistance);
        float amplitude = 0.3f;
        float pitch = Math.abs(MathHelper.cos((float)(g2 * (float)Math.PI - 0.2f)) * h2) * 5.0f * amplitude;
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pitch));
        float roll = MathHelper.sin((float)(g2 * (float)Math.PI)) * h2 * 3.0f * amplitude;
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(roll));
        float tx = MathHelper.sin((float)(g2 * (float)Math.PI)) * h2 * 0.5f * amplitude;
        matrices.translate(tx, Math.abs(tx) * 0.3f, 0.0f);
    }
}

