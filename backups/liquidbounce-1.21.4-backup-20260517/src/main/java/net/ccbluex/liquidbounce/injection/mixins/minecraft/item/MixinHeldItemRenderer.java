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
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleSilentHotbar;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleSwingAnimations;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleViewModel;
import net.ccbluex.liquidbounce.utils.client.SilentHotbar;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class MixinHeldItemRenderer {

    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", shift = At.Shift.AFTER))
    private void hookViewModel(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand,
                               float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices,
                               VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        var viewModel = ModuleViewModel.INSTANCE;
        if (!viewModel.getRunning()) {
            return;
        }

        var arm = hand == Hand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
        if (arm == Arm.RIGHT) {
            matrices.translate(viewModel.getRightX(), viewModel.getRightY(), viewModel.getRightZ());
        } else {
            matrices.translate(viewModel.getLeftX(), viewModel.getLeftY(), viewModel.getLeftZ());
        }
    }

    @Inject(method = "swingArm", at = @At("HEAD"), cancellable = true)
    private void hookSwingAnimations(float swingProgress, float equipProgress, MatrixStack matrices, int side, Arm arm,
                                     CallbackInfo ci) {
        var swingAnimations = ModuleSwingAnimations.INSTANCE;

        if (!swingAnimations.getRunning() || swingAnimations.isSwingless() || client.player == null
                || arm != client.player.getMainArm()) {
            return;
        }

        switch (swingAnimations.getMode()) {
            case SMOOTH_TRANSLATE ->
                    liquid_bounce$smoothTranslate(swingProgress, swingAnimations, matrices);
            case JUMP_TRANSLATE -> {
                float vanillaZ = -0.2F * MathHelper.sin(swingProgress * MathHelper.PI);
                liquid_bounce$jumpTranslate(swingAnimations, vanillaZ, matrices);
            }
            case SMOOTH_OLD ->
                    liquid_bounce$smoothOld(swingProgress, swingAnimations, matrices);
            case SMOOTH ->
                    liquid_bounce$smooth(swingProgress, swingAnimations, side, matrices);
            case DEFAULT ->
                    liquid_bounce$defaultAnim(swingProgress, swingAnimations, matrices);
            case BIG ->
                    liquid_bounce$big(swingAnimations, swingProgress, matrices);
            case FAP -> {
                float vanillaZ = -0.2F * MathHelper.sin(swingProgress * MathHelper.PI);
                liquid_bounce$fap(swingAnimations, vanillaZ, matrices);
            }
            case SNAP ->
                    liquid_bounce$snap(matrices, swingProgress, side, swingAnimations);
            case SMOOTH_BACK ->
                    liquid_bounce$smoothBack(matrices, swingProgress, side, swingAnimations);
        }

        ci.cancel();
    }

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), cancellable = true)
    private void hideShield(AbstractClientPlayerEntity player, float tickDelta, float pitch,
                                                Hand hand, float swingProgress, ItemStack item, float equipProgress,
                                                MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                                                CallbackInfo ci) {
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/consume/UseAction;",
            ordinal = 0
    ))
    private UseAction hookUseAction(ItemStack instance) {
        var item = instance.getItem();
        if (item instanceof SwordItem && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return UseAction.BLOCK;
        }

        return instance.getUseAction();
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingItem()Z",
            ordinal = 1
    ))
    private boolean hookIsUseItem(AbstractClientPlayerEntity instance) {
        var item = instance.getMainHandStack().getItem();

        if (item instanceof SwordItem && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return true;
        }

        return instance.isUsingItem();
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getActiveHand()Lnet/minecraft/util/Hand;",
            ordinal = 1
    ))
    private Hand hookActiveHand(AbstractClientPlayerEntity instance) {
        var item = instance.getMainHandStack().getItem();

        if (item instanceof SwordItem && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return Hand.MAIN_HAND;
        }

        return instance.getActiveHand();
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getItemUseTimeLeft()I",
            ordinal = 2
    ))
    private int hookItemUseItem(AbstractClientPlayerEntity instance) {
        var item = instance.getMainHandStack().getItem();

        if (item instanceof SwordItem && KillAuraAutoBlock.INSTANCE.getBlockVisual()) {
            return 7200;
        }

        return instance.getItemUseTimeLeft();
    }

    /**
     * This transformation was previously a VFP option but got now added to minecraft directly.
     * View the code that was used to disable the VFP option here:
     * https://github.com/CCBlueX/LiquidBounce/blob/e5a0dbf5458b063d3028e69e04762b8b25b998b5/src/main/java/net/ccbluex/liquidbounce/utils/client/vfp/VfpCompatibility.java#L44
     */
    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item preventConflictingCode(Item item) {
        // only applies to sword items,
        // so that future items won't be affected if minecraft decides to actually make use out of this
        if (item instanceof SwordItem) {
            return Items.SHIELD; // makes the instanceof return true and therefore not do the transformation
        }

        return item;
    }

    @Inject(method = "renderFirstPersonItem",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/consume/UseAction;")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;applyEquipOffset(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/Arm;F)V", ordinal = 2, shift = At.Shift.AFTER))
    private void transformLegacyBlockAnimations(AbstractClientPlayerEntity player, float tickDelta, float pitch,
                                                Hand hand, float swingProgress, ItemStack item, float equipProgress,
                                                MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                                                CallbackInfo ci) {
        var shouldAnimate = KillAuraAutoBlock.INSTANCE.getBlockVisual();

        if (shouldAnimate && item.getItem() instanceof SwordItem) {
            final Arm arm = (hand == Hand.MAIN_HAND) ? player.getMainArm() : player.getMainArm().getOpposite();

            liquid_bounce$transformOneSevenBlockAnimation(matrices, arm, swingProgress);
        }
    }

    @Unique
    private void liquid_bounce$transformOneSevenBlockAnimation(MatrixStack matrices, Arm arm, float swingProgress) {
        matrices.translate(arm == Arm.RIGHT ? -0.1F : 0.1F, 0.1F, 0.0F);
        liquid_bounce$applySwingOffset(matrices, arm, swingProgress * 0.9F);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-102.25F));
        matrices.multiply((arm == Arm.RIGHT ? RotationAxis.POSITIVE_Y : RotationAxis.NEGATIVE_Y)
                .rotationDegrees(13.365F));
        matrices.multiply((arm == Arm.RIGHT ? RotationAxis.POSITIVE_Z : RotationAxis.NEGATIVE_Z)
                .rotationDegrees(78.05F));
    }

    @Unique
    private void liquid_bounce$applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress) {
        int armSide = arm == Arm.RIGHT ? 1 : -1;
        float f = MathHelper.sin(swingProgress * swingProgress * MathHelper.PI);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(armSide * (45.0F + f * -20.0F)));
        float g = MathHelper.sin(MathHelper.sqrt(swingProgress) * MathHelper.PI);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(armSide * g * -20.0F));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(g * -80.0F));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(armSide * -45.0F));
    }

    @Unique
    private void liquid_bounce$smoothOld(float swingProgress, ModuleSwingAnimations swingAnimations, MatrixStack stack) {
        stack.translate(0.4F, 0.0F, -0.8F);
        stack.translate(0.56F, -0.44F, -0.71999997F);
        stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(72.0F));

        float f = MathHelper.sin(MathHelper.sqrt(swingProgress) * MathHelper.PI);

        stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f * -10.0F));
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(f * -swingAnimations.getAnimationStrength()));
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-20.0F));

        stack.translate(-0.5F, 0.08F, 0.0F);

        stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(20.0F));
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-80.0F));
        stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(20.0F));
    }

    @Unique
    private void liquid_bounce$snap(MatrixStack matrixStack, float swingProgress, float side,
                                    ModuleSwingAnimations swingAnimations) {
        matrixStack.translate(side * 0.56F, -0.52F, -0.72F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(
                swingProgress * -360.0F * swingAnimations.getAnimationSmooth()));
    }

    @Unique
    private void liquid_bounce$smoothBack(MatrixStack matrixStack, float swingProgress, float side,
                                          ModuleSwingAnimations swingAnimations) {
        matrixStack.translate(side * 0.56F, -0.52F, -0.72F);

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(
                swingProgress * (45.0F + MathHelper.sin(side * side * MathHelper.PI) / 4.0F * -20.0F)));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(
                swingProgress * MathHelper.sin(swingProgress * MathHelper.PI) * -20.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(
                MathHelper.sin(swingProgress * MathHelper.PI) * -swingAnimations.getAnimationStrength()));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(swingProgress * -45.0F));
    }

    @Unique
    private void liquid_bounce$fap(ModuleSwingAnimations swingAnimations, float vanillaZ, MatrixStack matrixStack) {
        matrixStack.translate(1.2F, -0.1F, -0.71999997F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45.0F));

        matrixStack.translate(-0.5F, 0.2F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(30.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-80.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(60.0F));

        int phase = (int) Math.min(255L, (System.currentTimeMillis() % 255L > 127L
                ? Math.abs(Math.abs(System.currentTimeMillis()) % 255L - 255L)
                : System.currentTimeMillis() % 255L) * 2L);
        float swing = vanillaZ > 0.5F ? 1.0F - vanillaZ : vanillaZ;

        matrixStack.translate(0.3F, 0.0F, 0.4F);
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));

        matrixStack.translate(0.6F, 0.5F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-10.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-swing * 10.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(10.0F));

        matrixStack.translate(0.0F, 0.0F, -0.5F);
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(
                client.player != null && client.player.handSwinging
                        ? -phase / (110.0F - swingAnimations.getAnimationStrength())
                        : 1.0F));
        matrixStack.translate(0.0F, 0.0F, 0.5F);
    }

    @Unique
    private void liquid_bounce$defaultAnim(float swingProgress, ModuleSwingAnimations swingAnimations,
                                           MatrixStack matrixStack) {
        float angle = -swingAnimations.getAnimationStrength()
                * (float) Math.sin(swingProgress * Math.PI)
                * swingAnimations.getAnimationStrength() / 100.0F;

        matrixStack.translate(1.2F, -0.1F, -0.71999997F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45.0F));

        matrixStack.translate(-0.5F, 0.2F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(30.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-80.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(60.0F));

        matrixStack.translate(0.3F, 0.0F, 0.4F);
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));

        matrixStack.translate(0.6F, 0.5F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-10.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(10.0F));

        matrixStack.translate(0.0F, 0.0F, -0.5F);
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(angle));
        matrixStack.translate(0.0F, 0.0F, 0.5F);
    }

    @Unique
    private void liquid_bounce$big(ModuleSwingAnimations swingAnimations, float swingProgress, MatrixStack matrixStack) {
        matrixStack.translate(0.5F, -0.3F, -0.65F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45.0F));

        float f = MathHelper.sin(swingProgress * swingProgress * MathHelper.PI);
        float g = MathHelper.sin(MathHelper.sqrt(swingProgress) * MathHelper.PI);

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * -20.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(g * -20.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(g * -swingAnimations.getAnimationStrength()));

        matrixStack.scale(0.5F, 0.5F, 0.5F);

        matrixStack.translate(-0.5F, 0.2F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(30.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-80.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(60.0F));
    }

    @Unique
    private void liquid_bounce$smooth(float swingProgress, ModuleSwingAnimations swingAnimations, float side,
                                      MatrixStack matrixStack) {
        matrixStack.translate(side * 0.56F, -0.52F, -0.72F);

        float sqrtSwing = MathHelper.sqrt(swingProgress);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(
                side * (45.0F + MathHelper.sin(swingProgress * swingProgress * MathHelper.PI) / 4.0F * -20.0F)));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(
                side * MathHelper.sin(sqrtSwing * MathHelper.PI) * -20.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(
                MathHelper.sin(sqrtSwing * MathHelper.PI) * -swingAnimations.getAnimationStrength()));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(side * -45.0F));
    }

    @Unique
    private void liquid_bounce$jumpTranslate(ModuleSwingAnimations swingAnimations, float vanillaZ,
                                             MatrixStack matrixStack) {
        float swing = MathHelper.sqrt(Math.abs(vanillaZ)) * MathHelper.PI;

        matrixStack.translate(0.4F, -0.15F, -0.5F);
        matrixStack.translate(0.0F, vanillaZ * -swingAnimations.getAnimationStrength() / 100.0F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(
                MathHelper.sin(vanillaZ * vanillaZ * MathHelper.PI) * -34.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.sin(swing) * -20.7F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.sin(swing) * -68.6F));
        matrixStack.scale(0.4F, 0.4F, 0.4F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(30.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-80.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(60.0F));
    }

    @Unique
    private void liquid_bounce$smoothTranslate(float swingProgress, ModuleSwingAnimations swingAnimations,
                                               MatrixStack matrixStack) {
        matrixStack.translate(0.6F, 0.0F, -0.8F);
        matrixStack.translate(0.56F, -1.0F, -0.82F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-60.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(
                -swingAnimations.getRotationAngle() - 40.0F
                        * (float) Math.sin(swingProgress * Math.PI)
                        * swingAnimations.getAnimationStrength() / 50.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-20.0F));
        matrixStack.translate(-0.5F, 0.08F, 0.0F);
    }

    @ModifyExpressionValue(method = "updateHeldItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getMainHandStack()Lnet/minecraft/item/ItemStack;"))
    private ItemStack injectSilentHotbar(ItemStack original) {
        if (ModuleSilentHotbar.INSTANCE.getRunning()) {
            // noinspection DataFlowIssue
            return client.player.getInventory().main.get(SilentHotbar.INSTANCE.getClientsideSlot());
        }

        return original;
    }

    @ModifyExpressionValue(method = "updateHeldItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F"))
    private float injectSilentHotbarNoCooldown(float original) {
        if (ModuleSilentHotbar.INSTANCE.getRunning() && ModuleSilentHotbar.INSTANCE.getNoCooldownProgress() && SilentHotbar.INSTANCE.isSlotModified()) {
            return 1f;
        }

        return original;
    }

}
