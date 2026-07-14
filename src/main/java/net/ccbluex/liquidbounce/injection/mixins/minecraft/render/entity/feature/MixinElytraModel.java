package net.ccbluex.liquidbounce.injection.mixins.minecraft.render.entity.feature;

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleWings;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ElytraModel.class)
public abstract class MixinElytraModel {

    @Shadow
    @Final
    private ModelPart rightWing;

    @Shadow
    @Final
    private ModelPart leftWing;

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At("TAIL"))
    private void liquid_bounce$setupXyetaAngles(HumanoidRenderState state, CallbackInfo ci) {
        if (!ModuleWings.shouldRenderXyeta(state)) {
            return;
        }

        ModuleWings.applyXyetaAngles(state, this.leftWing, this.rightWing);
    }
}
