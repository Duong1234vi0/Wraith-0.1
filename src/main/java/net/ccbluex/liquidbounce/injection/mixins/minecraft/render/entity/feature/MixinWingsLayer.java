package net.ccbluex.liquidbounce.injection.mixins.minecraft.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleWings;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WingsLayer.class)
public abstract class MixinWingsLayer<S extends HumanoidRenderState, M extends EntityModel<S>> {

    @Shadow
    @Final
    private ElytraModel elytraModel;

    @Shadow
    @Final
    private ElytraModel elytraBabyModel;

    @Shadow
    @Final
    private EquipmentLayerRenderer equipmentRenderer;

    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void liquid_bounce$submitXyetaWings(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            S state,
            float yRot,
            float xRot,
            CallbackInfo ci
    ) {
        if (!ModuleWings.shouldRenderXyeta(state)) {
            return;
        }

        ItemStack itemStack = state.chestEquipment.is(Items.ELYTRA) ? state.chestEquipment : Items.ELYTRA.getDefaultInstance();
        ElytraModel model = state.isBaby ? this.elytraBabyModel : this.elytraModel;
        model.setupAnim(state);

        poseStack.pushPose();
        try {
            poseStack.translate(0.0F, 0.0F, 0.125F);
            this.equipmentRenderer.renderLayers(
                    EquipmentClientInfo.LayerType.WINGS,
                    (ResourceKey<EquipmentAsset>) EquipmentAssets.ELYTRA,
                    model,
                    state,
                    itemStack,
                    poseStack,
                    submitNodeCollector,
                    lightCoords,
                    ModuleWings.selectedXyetaTexture(),
                    state.outlineColor,
                    0
            );
        } finally {
            poseStack.popPose();
        }

        ci.cancel();
    }
}
