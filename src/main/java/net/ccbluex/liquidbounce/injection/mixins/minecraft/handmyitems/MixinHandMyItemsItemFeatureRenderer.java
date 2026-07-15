package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.QuadInstance;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.DispatcherStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelContext;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.ModelScriptCache;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.ScriptHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFeatureRenderer.class)
public abstract class MixinHandMyItemsItemFeatureRenderer {

    @Shadow
    @Final
    private QuadInstance quadInstance;

    @Shadow
    private static VertexConsumer getFoilBuffer(
        MultiBufferSource bufferSource,
        RenderType renderType,
        PoseStack.Pose foilDecalPose
    ) {
        throw new AssertionError();
    }

    @Shadow
    private static PoseStack.Pose computeFoilDecalPose(ItemDisplayContext displayContext, PoseStack.Pose pose) {
        throw new AssertionError();
    }

    @Shadow
    private static int getLayerColorSafe(int[] tintLayers, BakedQuad.MaterialInfo material) {
        throw new AssertionError();
    }

    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    private void hmi$renderAnimatedItem(
        MultiBufferSource.BufferSource bufferSource,
        OutlineBufferSource outlineBufferSource,
        SubmitNodeStorage.ItemSubmit submit,
        CallbackInfo ci
    ) {
        if (!HandMyItemsRuntime.isActive()) {
            return;
        }

        PoseStack.Pose originalPose = submit.pose();
        ItemStackRenderState.FoilType foilType = submit.foilType();
        PoseStack.Pose foilDecalPose = foilType == ItemStackRenderState.FoilType.SPECIAL
            ? computeFoilDecalPose(submit.displayContext(), originalPose)
            : null;

        this.quadInstance.setLightCoords(submit.lightCoords());
        this.quadInstance.setOverlayCoords(submit.overlayCoords());
        if (submit.outlineColor() != 0) {
            outlineBufferSource.setColor(submit.outlineColor());
        }

        try {
            this.hmi$executeModelScript(submit.displayContext());
            PoseStack animatedPoseStack = new PoseStack();
            int index = 0;
            for (BakedQuad quad : submit.quads()) {
                animatedPoseStack.last().set(originalPose);
                GlobalsStorage.modelPartAnimator.applyPoses(index, animatedPoseStack);
                PoseStack.Pose animatedPose = animatedPoseStack.last();

                BakedQuad.MaterialInfo material = quad.materialInfo();
                RenderType renderType = material.itemRenderType();
                this.quadInstance.setColor(getLayerColorSafe(submit.tintLayers(), material));

                if (foilType != ItemStackRenderState.FoilType.NONE) {
                    getFoilBuffer(bufferSource, renderType, foilDecalPose).putBakedQuad(animatedPose, quad, this.quadInstance);
                }
                if (submit.outlineColor() != 0) {
                    outlineBufferSource.getBuffer(renderType).putBakedQuad(animatedPose, quad, this.quadInstance);
                }
                bufferSource.getBuffer(renderType).putBakedQuad(animatedPose, quad, this.quadInstance);
                index++;
            }
        } finally {
            GlobalsStorage.modelPartAnimator.clear();
        }
        ci.cancel();
    }

    @Unique
    private void hmi$executeModelScript(ItemDisplayContext displayContext) {
        if (displayContext != ItemDisplayContext.THIRD_PERSON_LEFT_HAND
            && displayContext != ItemDisplayContext.THIRD_PERSON_RIGHT_HAND) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        AbstractClientPlayer player = minecraft.player;
        if (!minecraft.options.getCameraType().isFirstPerson() || player == null) {
            return;
        }

        ItemModelContext context = ItemModelStorage.get();
        ItemStack renderedItem = DispatcherStorage.getRenderedItem();
        ScriptHolder.itemModelCache.executeModel(context, renderedItem, player, GlobalsStorage.modelPartAnimator);
        for (ModelScriptCache addon : ScriptHolder.itemModelAddonsCache) {
            addon.executeModel(context, renderedItem, player, GlobalsStorage.modelPartAnimator);
        }
    }
}
