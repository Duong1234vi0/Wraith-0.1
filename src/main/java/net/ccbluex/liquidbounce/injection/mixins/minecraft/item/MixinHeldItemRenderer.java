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

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", shift = At.Shift.AFTER))
    private void hookViewModel(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand,
                               float swingProgress, ItemStack item, float equipProgress, PoseStack matrices,
                               SubmitNodeCollector submitNodeCollector, int light, CallbackInfo ci) {
        // #Edit By Codex 2026-06-28: when ModuleAnimations takes over this hand's full item render, it
        // applies its own ViewModel offset inside applyVCoreTransforms, so skip this generic offset.
        var arm = hand == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
        if (ModuleAnimations.INSTANCE.shouldRenderCustom(item)) {
            return;
        }

        var viewModel = ModuleViewModel.INSTANCE;
        if (!viewModel.getRunning()) {
            return;
        }

        if (arm == HumanoidArm.RIGHT) {
            matrices.translate(viewModel.getRightX(), viewModel.getRightY(), viewModel.getRightZ());
        } else {
            matrices.translate(viewModel.getLeftX(), viewModel.getLeftY(), viewModel.getLeftZ());
        }
    }

    // #Edit By Codex 2026-06-28: VCore cancels first-person item rendering for every non-map item
    // while Animations is active, then rebuilds crossbow/use/bow/spear/brush/normal transforms itself.
    // The previous port only handled main-hand melee, which let KillAura and air-click paths fall back
    // into vanilla ItemInHandRenderer branches.
    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void hookAnimations(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand,
                                float swingProgress, ItemStack item, float equipProgress, PoseStack matrices,
                                SubmitNodeCollector submitNodeCollector, int light, CallbackInfo ci) {
        if (minecraft.player == null || player.isScoping() || !ModuleAnimations.INSTANCE.shouldRenderCustom(item)) {
            return;
        }

        HumanoidArm arm = hand == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
        boolean isRight = arm == HumanoidArm.RIGHT;

        matrices.pushPose();
        ModuleAnimations.INSTANCE.applyVCoreTransforms(player, tickDelta, hand, swingProgress, item, equipProgress, matrices);
        renderItem(
                player, item,
                isRight ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
                matrices, submitNodeCollector, light
        );
        matrices.popPose();

        ci.cancel();
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void hookAnimationsDisableSwap(CallbackInfo ci) {
        if (minecraft.player == null) {
            return;
        }

        // #Edit By Codex 2026-06-28: VCore DisableSwapMain/Off keeps equip progress pinned at 1
        // and refreshes the renderer's visible stack, preventing vanilla slot-swap dip/shrink.
        if (ModuleAnimations.INSTANCE.shouldDisableSwapMain() && mainHandHeight <= 1.0F) {
            mainHandHeight = 1.0F;
            mainHandItem = minecraft.player.getMainHandItem();
        }

        if (ModuleAnimations.INSTANCE.shouldDisableSwapOff() && offHandHeight <= 1.0F) {
            offHandHeight = 1.0F;
            offHandItem = minecraft.player.getOffhandItem();
        }
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
            final HumanoidArm arm = (hand == InteractionHand.MAIN_HAND) ? player.getMainArm() : player.getMainArm().getOpposite();

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
