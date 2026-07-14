/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.injection.mixins.minecraft.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraAutoBlock;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleAnimations;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleSilentHotbar;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleViewModel;
import net.ccbluex.liquidbounce.utils.client.SilentHotbar;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class MixinHeldItemRenderer {

    @Final
    @Shadow
    private Minecraft minecraft;

    @Shadow
    private ItemStack mainHandItem;

    @Shadow
    private ItemStack offHandItem;

    @Shadow
    private float mainHandHeight;

    @Shadow
    private float offHandHeight;

    @Shadow
    public abstract void renderItem(net.minecraft.world.entity.LivingEntity mob, ItemStack itemStack,
                                    ItemDisplayContext type, PoseStack poseStack,
                                    SubmitNodeCollector submitNodeCollector, int lightCoords);

    @Shadow
    private void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords,
                                 float inverseArmHeight, float attackValue, HumanoidArm arm) {
    }

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", shift = At.Shift.AFTER))
    private void hookViewModel(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand,
                               float swingProgress, ItemStack item, float equipProgress, PoseStack matrices,
                               SubmitNodeCollector submitNodeCollector, int light, CallbackInfo ci) {
        var mainArm = ModuleAnimations.INSTANCE.resolveMainArm(player);
        var arm = hand == InteractionHand.MAIN_HAND ? mainArm : mainArm.getOpposite();
        ModuleViewModel.INSTANCE.applyTransform(matrices, arm);
    }

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void hookNewSwingAnimation(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand,
                                       float swingProgress, ItemStack item, float equipProgress, PoseStack matrices,
                                       SubmitNodeCollector submitNodeCollector, int light, CallbackInfo ci) {
        if (minecraft.player == null || player.isScoping()
                || !ModuleAnimations.INSTANCE.shouldRenderNewSpecial(player, hand, item)) {
            return;
        }

        HumanoidArm arm = ModuleAnimations.INSTANCE.newSpecialArm(player, hand);
        boolean isRight = arm == HumanoidArm.RIGHT;

        matrices.pushPose();
        ModuleViewModel.INSTANCE.applyTransform(matrices, arm);

        ModuleAnimations.NewArmRender armRender = ModuleAnimations.INSTANCE.applyNewSpecialArmPose(
                matrices, player, tickDelta, hand, arm, item, equipProgress, swingProgress
        );
        if (armRender == null) {
            matrices.popPose();
            return;
        }

        renderPlayerArm(matrices, submitNodeCollector, light,
                armRender.getEquipProgress(), armRender.getSwingProgress(), arm);

        if (!item.isEmpty()) {
            ModuleAnimations.INSTANCE.applyNewSpecialItemPose(matrices, arm, item);
            renderItem(
                    player, item,
                    isRight ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
                    matrices, submitNodeCollector, light
            );
        }

        matrices.popPose();
        ci.cancel();
    }

    @Redirect(method = "renderArmWithItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/AbstractClientPlayer;getMainArm()Lnet/minecraft/world/entity/HumanoidArm;"
    ))
    private HumanoidArm hookVisualMainArm(AbstractClientPlayer instance) {
        return ModuleAnimations.INSTANCE.resolveMainArm(instance);
    }

    @WrapOperation(method = "renderArmWithItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;applyItemArmTransform(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/entity/HumanoidArm;F)V"
    ))
    private void hookSwingDown(ItemInHandRenderer instance, PoseStack matrices, HumanoidArm arm, float inverseArmHeight,
                               Operation<Void> original, AbstractClientPlayer player, float tickDelta, float pitch,
                               InteractionHand hand, float swingProgress, ItemStack item, float equipProgress,
                                               PoseStack methodMatrices, SubmitNodeCollector submitNodeCollector, int light) {
        float transformedEquipProgress = ModuleAnimations.INSTANCE.transformEquipProgress(inverseArmHeight, hand, item);

        original.call(instance, matrices, arm, transformedEquipProgress);
    }

    @WrapOperation(method = "renderArmWithItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;swingArm(FLcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/entity/HumanoidArm;)V"
    ))
    private void hookSwingAnimation(ItemInHandRenderer instance, float attack, PoseStack matrices, int invert,
                                    HumanoidArm arm, Operation<Void> original, AbstractClientPlayer player,
                                    float tickDelta, float pitch, InteractionHand hand, float swingProgress,
                                    ItemStack item, float equipProgress, PoseStack methodMatrices,
                                    SubmitNodeCollector submitNodeCollector, int light) {
        if (ModuleAnimations.INSTANCE.applySwingTransform(matrices, player, hand, arm, item, attack)) {
            return;
        }

        original.call(instance, attack, matrices, invert, arm);
    }

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void hideShield(AbstractClientPlayer player, float tickDelta, float pitch,
                                                InteractionHand hand, float swingProgress, ItemStack item, float equipProgress,
                                                PoseStack matrices, SubmitNodeCollector submitNodeCollector, int light,
                                                CallbackInfo ci) {
    }

    @Redirect(method = "renderArmWithItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/ItemUseAnimation;",
            ordinal = 0
    ))
    private ItemUseAnimation hookUseAction(ItemStack instance) {
        var item = instance.getItem();
        if (liquid_bounce$isSword(item) && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return ItemUseAnimation.BLOCK;
        }

        return instance.getUseAnimation();
    }

    @Redirect(method = "renderArmWithItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/AbstractClientPlayer;isUsingItem()Z",
            ordinal = 1
    ))
    private boolean hookIsUseItem(AbstractClientPlayer instance) {
        var item = instance.getMainHandItem().getItem();

        if (liquid_bounce$isSword(item) && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return true;
        }

        return instance.isUsingItem();
    }

    @Redirect(method = "renderArmWithItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/AbstractClientPlayer;getUsedItemHand()Lnet/minecraft/world/InteractionHand;",
            ordinal = 1
    ))
    private InteractionHand hookActiveHand(AbstractClientPlayer instance) {
        var item = instance.getMainHandItem().getItem();

        if (liquid_bounce$isSword(item) && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return InteractionHand.MAIN_HAND;
        }

        return instance.getUsedItemHand();
    }

    @Redirect(method = "renderArmWithItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/AbstractClientPlayer;getUseItemRemainingTicks()I",
            ordinal = 2
    ))
    private int hookItemUseItem(AbstractClientPlayer instance) {
        var item = instance.getMainHandItem().getItem();

        if (liquid_bounce$isSword(item) && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return 7200;
        }

        return instance.getUseItemRemainingTicks();
    }

    /**
     * This transformation was previously a VFP option but got now added to minecraft directly.
     * View the code that was used to disable the VFP option here:
     * https://github.com/CCBlueX/LiquidBounce/blob/e5a0dbf5458b063d3028e69e04762b8b25b998b5/src/main/java/net/ccbluex/liquidbounce/utils/client/vfp/VfpCompatibility.java#L44
     */
    @ModifyExpressionValue(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"))
    private Item preventConflictingCode(Item item) {
        // only applies to sword items,
        // so that future items won't be affected if minecraft decides to actually make use out of this
        if (liquid_bounce$isSword(item)) {
            return Items.SHIELD; // makes the instanceof return true and therefore not do the transformation
        }

        return item;
    }

    @Inject(method = "renderArmWithItem",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/ItemUseAnimation;")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;applyItemArmTransform(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/entity/HumanoidArm;F)V", ordinal = 2, shift = At.Shift.AFTER))
    private void transformLegacyBlockAnimations(AbstractClientPlayer player, float tickDelta, float pitch,
                                                InteractionHand hand, float swingProgress, ItemStack item, float equipProgress,
                                                PoseStack matrices, SubmitNodeCollector submitNodeCollector, int light,
                                                CallbackInfo ci) {
        var shouldAnimate = KillAuraAutoBlock.INSTANCE.getBlockVisual();

        if (shouldAnimate && liquid_bounce$isSword(item.getItem())) {
            final HumanoidArm mainArm = ModuleAnimations.INSTANCE.resolveMainArm(player);
            final HumanoidArm arm = (hand == InteractionHand.MAIN_HAND) ? mainArm : mainArm.getOpposite();

            liquid_bounce$transformOneSevenBlockAnimation(matrices, arm, swingProgress);
        }
    }

    @Unique
    private void liquid_bounce$transformOneSevenBlockAnimation(PoseStack matrices, HumanoidArm arm, float swingProgress) {
        matrices.translate(arm == HumanoidArm.RIGHT ? -0.1F : 0.1F, 0.1F, 0.0F);
        liquid_bounce$applySwingOffset(matrices, arm, swingProgress * 0.9F);
        matrices.mulPose(Axis.XP.rotationDegrees(-102.25F));
        matrices.mulPose((arm == HumanoidArm.RIGHT ? Axis.YP : Axis.YN)
                .rotationDegrees(13.365F));
        matrices.mulPose((arm == HumanoidArm.RIGHT ? Axis.ZP : Axis.ZN)
                .rotationDegrees(78.05F));
    }

    @Unique
    private void liquid_bounce$applySwingOffset(PoseStack matrices, HumanoidArm arm, float swingProgress) {
        int armSide = arm == HumanoidArm.RIGHT ? 1 : -1;
        float f = Mth.sin(swingProgress * swingProgress * Mth.PI);
        matrices.mulPose(Axis.YP.rotationDegrees(armSide * (45.0F + f * -20.0F)));
        float g = Mth.sin(Mth.sqrt(swingProgress) * Mth.PI);
        matrices.mulPose(Axis.ZP.rotationDegrees(armSide * g * -20.0F));
        matrices.mulPose(Axis.XP.rotationDegrees(g * -80.0F));
        matrices.mulPose(Axis.YP.rotationDegrees(armSide * -45.0F));
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getMainHandItem()Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack injectSilentHotbar(ItemStack original) {
        if (ModuleSilentHotbar.INSTANCE.getRunning()) {
            // noinspection DataFlowIssue
            return minecraft.player.getInventory().getNonEquipmentItems().get(SilentHotbar.INSTANCE.getClientsideSlot());
        }

        return original;
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getItemSwapScale(F)F"))
    private float injectSilentHotbarNoCooldown(float original) {
        if (ModuleSilentHotbar.INSTANCE.getRunning() && ModuleSilentHotbar.INSTANCE.getNoCooldownProgress() && SilentHotbar.INSTANCE.isSlotModified()) {
            return 1f;
        }

        return original;
    }

    @Unique
    private static boolean liquid_bounce$isSword(Item item) {
        return item == Items.WOODEN_SWORD
                || item == Items.COPPER_SWORD
                || item == Items.STONE_SWORD
                || item == Items.GOLDEN_SWORD
                || item == Items.IRON_SWORD
                || item == Items.DIAMOND_SWORD
                || item == Items.NETHERITE_SWORD;
    }

}
