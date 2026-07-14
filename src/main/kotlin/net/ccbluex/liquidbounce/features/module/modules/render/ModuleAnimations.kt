/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.core.component.DataComponents
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.BucketItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.world.item.Items
import net.minecraft.world.item.ShieldItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.TridentItem
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Pouch Client 1.16.5 `Sword Animations` port.
 */
object ModuleAnimations : ClientModule(
    "SwingAnimation",
    Category.RENDER,
    aliases = arrayOf("Animations", "SwordAnimations", "Sword Animations")
) {

    private val modeValue = enumChoice("Mode", Mode.MODE1)
    private val renderHand by enumChoice("RenderHand", RenderHand.RIGHT)
    private val angleValue = int("Angle", 100, 0..360)
        .visibleWhen { mode == Mode.MODE2 || mode == Mode.MODE4 }
    private val swingStrengthValue = int("SwingStrength", 8, 1..10)
        .visibleWhen {
            mode == Mode.MODE1 || mode == Mode.MODE2 || mode == Mode.MODE3 ||
                mode == Mode.MODE4 || mode == Mode.MODE6
        }
    private val swingDownValue = float("SwingDown", 0.0F, 0.0F..1.0F, 0.1F)
    private val swingSmoothnessValue = int("SwingSmoothness", 11, 1..20)
    private val onlyAura by boolean("OnlyAura", false)
    private val newLeftHand by boolean("NewLeftHand", false)
        .visibleWhen { mode == Mode.NEW }

    private var newPrimarySlash = false
    private var newWasSwinging = false

    val mode: Mode
        @JvmStatic get() = modeValue.get()

    private val swingStrength: Float
        get() = swingStrengthValue.get().toFloat()

    private val angle: Float
        get() = angleValue.get().toFloat()

    private fun auraReady(): Boolean =
        !onlyAura || (ModuleKillAura.running && ModuleKillAura.targetTracker.target != null)

    @JvmStatic
    fun shouldApply(): Boolean = running && auraReady()

    @JvmStatic
    fun resolveMainArm(player: AbstractClientPlayer): HumanoidArm {
        if (!running || player !== mc.player) {
            return player.mainArm
        }

        return when (renderHand) {
            RenderHand.RIGHT -> HumanoidArm.RIGHT
            RenderHand.LEFT -> HumanoidArm.LEFT
        }
    }

    @JvmStatic
    fun visualSwingDuration(original: Int, entity: Any, swingingArm: InteractionHand?): Int {
        if (entity !== mc.player || !shouldApply()) {
            return original
        }

        val hand = swingingArm ?: InteractionHand.MAIN_HAND
        return if (hand == InteractionHand.MAIN_HAND) swingSmoothnessValue.get() else original
    }

    @JvmStatic
    fun transformEquipProgress(
        original: Float,
        hand: InteractionHand,
        item: ItemStack
    ): Float {
        if (!shouldApply() || hand != InteractionHand.MAIN_HAND) {
            return original
        }

        if (mode == Mode.NEW && item.isEmpty) {
            return original
        }

        return swingDownValue.get()
    }

    @JvmStatic
    fun applySwingTransform(
        matrices: PoseStack,
        player: AbstractClientPlayer,
        hand: InteractionHand,
        arm: HumanoidArm,
        item: ItemStack,
        swingProgress: Float
    ): Boolean {
        if (!shouldApply() || hand != InteractionHand.MAIN_HAND || item.isEmpty ||
            item.has(DataComponents.MAP_ID) || item.item == Items.CROSSBOW) {
            return false
        }

        val isLeft = arm == HumanoidArm.LEFT
        val anim = sin(sqrt(swingProgress) * PI).toFloat()

        when (mode) {
            Mode.MODE1 -> {
                val swingSqrt = sqrt(swingProgress)
                val direction = if (isLeft) -1.0F else 1.0F
                matrices.mulPose(Axis.YP.rotationDegrees(
                    direction * (20.0F + sin(swingProgress * swingProgress * PI).toFloat() / 4.0F * -10.0F)
                ))
                matrices.mulPose(Axis.ZP.rotationDegrees(
                    direction * sin(swingSqrt * PI).toFloat() * -20.0F
                ))
                matrices.mulPose(Axis.XP.rotationDegrees(
                    sin(swingSqrt * PI).toFloat() * -(swingStrength * 10.0F)
                ))
                matrices.mulPose(Axis.YP.rotationDegrees(-direction * 45.0F))
            }

            Mode.MODE2 -> {
                matrices.translate(0.0, 0.15, -0.3)
                matrices.mulPose(Axis.YP.rotationDegrees(if (isLeft) -90.0F else 90.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(if (isLeft) 60.0F else -60.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-angle - swingStrength * 10.0F * anim))
            }

            Mode.MODE3 -> {
                matrices.mulPose(Axis.XP.rotationDegrees(swingProgress * PI.toFloat() - swingStrength * 10.0F * anim))
            }

            Mode.MODE4 -> {
                matrices.translate(0.0, 0.15, -0.3)
                matrices.mulPose(Axis.YP.rotationDegrees(if (isLeft) -70.0F else 70.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(if (isLeft) 30.0F else -30.0F))
                matrices.scale(0.9F, 0.9F, 0.9F)
                matrices.mulPose(Axis.XP.rotationDegrees(-angle - swingStrength * 10.0F * anim))
            }

            Mode.MODE5 -> {
                val direction = if (isLeft) -1.0F else 1.0F
                matrices.mulPose(Axis.YP.rotationDegrees(direction * 45.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(anim * -20.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(direction * anim * -20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(anim * -80.0F))
                matrices.translate(direction * 0.4F, 0.2F, 0.2F)
                matrices.translate(direction * -0.5F, 0.08F, 0.0F)
                matrices.mulPose(Axis.YP.rotationDegrees(direction * 20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-80.0F))
                matrices.mulPose(Axis.YP.rotationDegrees(direction * 20.0F))
            }

            Mode.MODE6 -> {
                val direction = if (isLeft) -1.0F else 1.0F
                val f = sin(swingProgress * swingProgress * PI).toFloat()
                matrices.mulPose(Axis.YP.rotationDegrees(direction * (45.0F + f * -20.0F)))
                val f1 = sin(sqrt(swingProgress) * PI).toFloat()
                matrices.mulPose(Axis.ZP.rotationDegrees(direction * f1 * -20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(f1 * -swingStrength * 10.0F))
                matrices.mulPose(Axis.YP.rotationDegrees(direction * -45.0F))
            }

            Mode.MODE7 -> {
                val direction = if (isLeft) -1.0F else 1.0F
                matrices.scale(1.0F, 1.0F, 1.0F)
                matrices.translate(direction * (0.4F - anim * 0.3F), 0.0F, -anim * 0.2F)
                matrices.mulPose(Axis.YP.rotationDegrees(direction * 90.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(direction * -30.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-70.0F - 30.0F * anim))
            }

            Mode.ROTATE_360 -> matrices.mulPose(Axis.XP.rotationDegrees(-360.0F * swingProgress))

            Mode.SLANT -> {
                val rotate = 35.0F
                matrices.translate(0.0, 0.0, -0.3 * anim)
                matrices.mulPose(Axis.XP.rotationDegrees(anim * -rotate))
                matrices.mulPose(Axis.ZP.rotationDegrees(anim * rotate))
            }

            Mode.NEW -> return applyNewSlashTransform(matrices, player, hand, item, isLeft, swingProgress)

            Mode.FADE -> {
                val direction = if (isLeft) -1.0F else 1.0F
                val f2 = sin(swingProgress * swingProgress * PI).toFloat()
                matrices.mulPose(Axis.YP.rotationDegrees(direction * (45.0F + f2 * -5.0F)))
                val f13 = sin(sqrt(swingProgress * swingProgress) * PI).toFloat()
                matrices.mulPose(Axis.ZP.rotationDegrees(direction * f13 * -20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(f13 * -(swingStrength * 8.0F)))
                matrices.mulPose(Axis.YP.rotationDegrees(direction * -45.0F))
            }

            Mode.SPIN_SLASH -> {
                val direction = if (isLeft) -1.0F else 1.0F
                val wave = sin(swingProgress * PI).toFloat()
                matrices.translate(direction * 0.15F, -0.1F * wave, -0.35F * wave)
                matrices.mulPose(Axis.YP.rotationDegrees(direction * (110.0F * wave)))
                matrices.mulPose(Axis.ZP.rotationDegrees(direction * (-65.0F * wave)))
                matrices.mulPose(Axis.XP.rotationDegrees(-95.0F * wave))
            }
        }

        return true
    }

    @JvmStatic
    fun shouldRenderNewSpecial(player: AbstractClientPlayer, hand: InteractionHand, item: ItemStack): Boolean {
        if (!shouldApply() || mode != Mode.NEW || player !== mc.player || player.isAutoSpinAttack) {
            return false
        }

        if (hand == InteractionHand.OFF_HAND && !newLeftHand) {
            return false
        }

        if (hand == InteractionHand.OFF_HAND && player.isInvisible) {
            return false
        }

        if (!item.isEmpty && (item.has(DataComponents.MAP_ID) || item.item == Items.CROSSBOW)) {
            return false
        }

        if (player.isUsingItem && player.useItemRemainingTicks > 0 && player.usedItemHand == hand) {
            val useAnimation = effectiveUseAnimation(item)
            if (hand == InteractionHand.MAIN_HAND &&
                useAnimation != ItemUseAnimation.EAT &&
                useAnimation != ItemUseAnimation.DRINK &&
                useAnimation != ItemUseAnimation.BLOCK) {
                return false
            }
        }

        return true
    }

    @JvmStatic
    fun newSpecialArm(player: AbstractClientPlayer, hand: InteractionHand): HumanoidArm {
        val mainArm = resolveMainArm(player)
        return if (hand == InteractionHand.MAIN_HAND) mainArm else mainArm.opposite
    }

    @JvmStatic
    fun applyNewSpecialArmPose(
        matrices: PoseStack,
        player: AbstractClientPlayer,
        tickDelta: Float,
        hand: InteractionHand,
        arm: HumanoidArm,
        item: ItemStack,
        equipProgress: Float,
        swingProgress: Float
    ): NewArmRender? {
        if (item.isEmpty) {
            return applyNewEmptyArmPose(matrices, player, arm, equipProgress, swingProgress)
        }

        val usingThisHand = player.isUsingItem && player.useItemRemainingTicks > 0 && player.usedItemHand == hand
        if (usingThisHand) {
            val useTicks = item.getUseDuration(player) - (player.useItemRemainingTicks - tickDelta + 1.0F)
            when (effectiveUseAnimation(item)) {
                ItemUseAnimation.EAT,
                ItemUseAnimation.DRINK -> {
                    applyNewEatDrinkHandPose(matrices, arm, useTicks)
                    return NewArmRender(0.0F, swingProgress)
                }

                ItemUseAnimation.BLOCK -> {
                    applyNewBlockHandPose(matrices, arm, useTicks)
                    return NewArmRender(0.0F, swingProgress)
                }

                else -> {
                    if (hand == InteractionHand.MAIN_HAND) {
                        return null
                    }
                    applyNewHandBase(matrices, equipProgress, arm)
                    return NewArmRender(0.0F, 0.0F)
                }
            }
        }

        if (hand == InteractionHand.MAIN_HAND && !applyNewSlashTransform(matrices, player, hand, item, arm == HumanoidArm.LEFT, swingProgress)) {
            return null
        }

        applyNewHandBase(matrices, equipProgress, arm)
        return NewArmRender(0.0F, 0.0F)
    }

    @JvmStatic
    fun applyNewSpecialItemPose(matrices: PoseStack, arm: HumanoidArm, item: ItemStack) {
        applyNewCommonItemAnchor(matrices, arm)
        applyNewItemPose(matrices, arm, item)
    }

    private fun applyNewSlashTransform(
        matrices: PoseStack,
        player: AbstractClientPlayer,
        hand: InteractionHand,
        item: ItemStack,
        isLeft: Boolean,
        swingProgress: Float
    ): Boolean {
        if (item.isEmpty) {
            return false
        }

        updateNewSlashState(swingProgress)

        val direction = if (isLeft) -1.0F else 1.0F
        val swingRot = newSwingRot(swingProgress)
        val swing = easeInOutBack(sin(swingProgress * PI).toFloat())
        val sword = isSwordLike(item)
        val axe = isAxeLike(item)
        val shovel = isShovelLike(item)
        val tool = isGeneralDiggerTool(item)
        val spear = isSpearLike(item)
        val block = effectiveUseAnimation(item) == ItemUseAnimation.BLOCK
        val primarySlash = newPrimarySlash || axe || spear || block

        if (primarySlash && !shovel) {
            if (sword || axe) {
                matrices.translate(0.8F * direction * swingRot, 0.3F * swingRot, -0.5F * swing)
                matrices.mulPose(Axis.YP.rotationDegrees(15.0F * swingRot * direction))
                matrices.mulPose(Axis.XP.rotationDegrees(-20.0F * swingRot))
                matrices.mulPose(Axis.ZP.rotationDegrees(-70.0F * swingRot * direction))
                matrices.mulPose(Axis.XP.rotationDegrees((if (sword) 40.0F else 30.0F) * swing))
                return true
            }

            if (spear) {
                matrices.translate(0.0, 0.0, 0.45 * swingRot)
                matrices.translate(-0.25 * direction * swing, -0.35 * swingRot, -0.6 * swing)
                matrices.translate(0.0, 0.1 * swing, 0.0)
                matrices.mulPose(Axis.YP.rotationDegrees(15.0F * swingRot * direction))
                matrices.mulPose(Axis.ZP.rotationDegrees(30.0F * swingRot * direction))
                return true
            }

            if (tool && !block) {
                matrices.translate(0.1F * direction * swingRot, 0.1F * swingRot, -0.5F * swing)
                matrices.mulPose(Axis.XP.rotationDegrees(-30.0F * swingRot))
                matrices.mulPose(Axis.ZP.rotationDegrees(-20.0F * swingRot * direction))
                matrices.mulPose(Axis.XP.rotationDegrees(40.0F * swing))
                return true
            }

            if (!block) {
                matrices.translate(0.1F * direction * swingRot, 0.1F * swingRot, -0.1F * swing)
                matrices.mulPose(Axis.XP.rotationDegrees(-30.0F * swingRot))
                matrices.mulPose(Axis.ZP.rotationDegrees(-10.0F * swingRot * direction))
                matrices.mulPose(Axis.XP.rotationDegrees(40.0F * swing))
                matrices.mulPose(Axis.YP.rotationDegrees(10.0F * swing * direction))
                return true
            }

            matrices.translate(0.1F * direction * swingRot, 0.1F * swingRot, -0.2F * swing)
            matrices.mulPose(Axis.XP.rotationDegrees(-10.0F * swingRot))
            matrices.mulPose(Axis.ZP.rotationDegrees(-10.0F * swingRot * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(20.0F * swing))
            return true
        }

        if (shovel) {
            matrices.translate(0.0, 0.15 * swingRot, -0.25 * swingRot)
            matrices.translate(0.0, 0.0, -0.2 * swing)
            matrices.mulPose(Axis.YP.rotationDegrees(15.0F * swingRot))
            matrices.mulPose(Axis.XP.rotationDegrees(-35.0F * swingRot))
            matrices.mulPose(Axis.XP.rotationDegrees(30.0F * swing))
            return true
        }

        if (sword) {
            matrices.translate(-0.55F * direction * swingRot, -0.8F * swingRot, -0.77F * swing)
            matrices.mulPose(Axis.YP.rotationDegrees(5.0F * swingRot * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(-30.0F * swingRot))
            matrices.mulPose(Axis.ZP.rotationDegrees(70.0F * swingRot * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(50.0F * swing))
            return true
        }

        if (tool) {
            matrices.translate(0.1F * direction * swingRot, 0.1F * swingRot, -0.5F * swing)
            matrices.mulPose(Axis.XP.rotationDegrees(-30.0F * swingRot))
            matrices.mulPose(Axis.ZP.rotationDegrees(-20.0F * swingRot * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(40.0F * swing))
            return true
        }

        matrices.translate(0.1F * direction * swingRot, 0.1F * swingRot, -0.1F * swing)
        matrices.mulPose(Axis.XP.rotationDegrees(-30.0F * swingRot))
        matrices.mulPose(Axis.ZP.rotationDegrees(-10.0F * swingRot * direction))
        matrices.mulPose(Axis.XP.rotationDegrees(40.0F * swing))
        matrices.mulPose(Axis.YP.rotationDegrees(10.0F * swing * direction))
        return true
    }

    private fun applyNewEmptyArmPose(
        matrices: PoseStack,
        player: AbstractClientPlayer,
        arm: HumanoidArm,
        equipProgress: Float,
        swingProgress: Float
    ): NewArmRender? {
        if (player.isInvisible) {
            return null
        }

        val direction = if (arm == HumanoidArm.RIGHT) 1.0F else -1.0F
        val movementPose = player.isSwimming || player.isVisuallySwimming || !player.onGround() && player.onClimbable()

        if (arm == HumanoidArm.LEFT) {
            val swingRot = newSwingRot(swingProgress)
            val swing = easeInOutBack(sin(swingProgress * PI).toFloat())

            if (movementPose) {
                applyNewHandBase(matrices, equipProgress, arm)
                matrices.translate(0.05, -0.08, -0.1)
                matrices.mulPose(Axis.YP.rotationDegrees(12.0F))
            } else {
                matrices.translate(-0.1, -0.08 - equipProgress * 0.05, -0.12)
                matrices.translate(-0.03 * swing, 0.04 * swingRot, -0.05 * swing)
                matrices.mulPose(Axis.YP.rotationDegrees(18.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(-10.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-12.0F))
                matrices.mulPose(Axis.YP.rotationDegrees(8.0F * swing))
                matrices.mulPose(Axis.XP.rotationDegrees(-6.0F * swingRot))
            }

            return NewArmRender(equipProgress * 0.35F, 0.0F)
        }

        if (movementPose) {
            applyNewHandBase(matrices, equipProgress, arm)
        } else {
            val swingRot = newSwingRot(swingProgress)
            val swing = easeInOutBack(sin(swingProgress * PI).toFloat())
            matrices.translate(0.0, 0.2 * swingRot, 0.15 * swingRot)
            matrices.translate(0.1 * direction * swing, 0.15 * swing, -0.45 * swing)
            matrices.mulPose(Axis.YP.rotationDegrees(35.0F * swing * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(-30.0F * swing))
            matrices.mulPose(Axis.YP.rotationDegrees(-10.0F * swingRot * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(10.0F * swingRot))
        }

        return NewArmRender(0.0F, 0.0F)
    }

    private fun applyNewHandBase(matrices: PoseStack, equipProgress: Float, arm: HumanoidArm) {
        val direction = if (arm == HumanoidArm.RIGHT) 1.0F else -1.0F
        matrices.translate(direction, -equipProgress * 0.3F, 0.3F)
        matrices.mulPose(Axis.YP.rotationDegrees(45.0F * direction))
        matrices.mulPose(Axis.ZP.rotationDegrees(-40.0F * direction))
        matrices.mulPose(Axis.XP.rotationDegrees(30.0F))
        matrices.scale(0.9F, 0.9F, 0.9F)
    }

    private fun applyNewEatDrinkHandPose(matrices: PoseStack, arm: HumanoidArm, useTicks: Float) {
        val direction = if (arm == HumanoidArm.RIGHT) 1.0F else -1.0F
        val progress = Mth.clamp(useTicks / 5.0F, 0.0F, 1.0F)
        val wobble = sin(useTicks / 2.0F * PI).toFloat() / 10.0F

        matrices.translate(direction.toDouble(), 0.1, 0.3)
        matrices.translate(0.2 * direction * progress, -0.7 * progress, -0.2 * progress)
        matrices.translate(0.0, -0.2 * wobble, -0.2 * wobble)
        matrices.translate(0.0, 0.1 * easeInOutBack(sin(progress * PI).toFloat()), 0.0)
        matrices.mulPose(Axis.YP.rotationDegrees(45.0F * direction))
        matrices.mulPose(Axis.ZP.rotationDegrees(-40.0F * direction))
        matrices.mulPose(Axis.XP.rotationDegrees(30.0F))
        matrices.scale(0.9F, 0.9F, 0.9F)
        matrices.mulPose(Axis.YP.rotationDegrees(45.0F * progress * direction))
    }

    private fun applyNewBlockHandPose(matrices: PoseStack, arm: HumanoidArm, useTicks: Float) {
        val direction = if (arm == HumanoidArm.RIGHT) 1.0F else -1.0F
        val blockProgress = Mth.clamp(useTicks / 4.0F, 0.0F, 1.0F)
        val settleProgress = Mth.clamp(useTicks / 6.0F, 0.0F, 1.0F)

        matrices.translate(0.0, -0.2, 0.0)
        matrices.translate(direction.toDouble(), 0.0, 0.3)
        matrices.translate(0.7 * blockProgress * direction, 0.0, -1.3 * blockProgress)
        matrices.translate(-0.2 * direction * settleProgress, 0.0, 0.0)
        matrices.mulPose(Axis.XP.rotationDegrees(10.0F * sin(settleProgress * PI).toFloat()))
        matrices.mulPose(Axis.YP.rotationDegrees(70.0F * blockProgress * direction))
        matrices.mulPose(Axis.YP.rotationDegrees(45.0F * direction))
        matrices.mulPose(Axis.ZP.rotationDegrees(-40.0F * direction))
        matrices.mulPose(Axis.XP.rotationDegrees(30.0F))
        matrices.mulPose(Axis.YP.rotationDegrees(5.0F * direction * blockProgress))
        matrices.mulPose(Axis.XP.rotationDegrees(-10.0F * blockProgress))
        matrices.translate(0.0, 0.0, -0.2 * blockProgress)
        matrices.scale(0.9F, 0.9F, 0.9F)
    }

    private fun applyNewCommonItemAnchor(matrices: PoseStack, arm: HumanoidArm) {
        val direction = if (arm == HumanoidArm.RIGHT) 1.0F else -1.0F
        matrices.translate(-0.3 * direction, 0.65, -0.1)
        matrices.mulPose(Axis.YP.rotationDegrees(-65.0F * direction))
        matrices.mulPose(Axis.XP.rotationDegrees(10.0F))
    }

    private fun applyNewItemPose(matrices: PoseStack, arm: HumanoidArm, stack: ItemStack) {
        val direction = if (arm == HumanoidArm.RIGHT) 1.0F else -1.0F
        val item = stack.item

        if (isTorchLike(item)) {
            matrices.scale(1.5F, 1.5F, 1.5F)
            matrices.mulPose(Axis.YN.rotationDegrees(25.0F * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(5.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(75.0F * direction))
            matrices.translate(0.2 * direction, 0.2, 0.05)
            return
        }

        if (isSmallNewItem(stack)) {
            matrices.mulPose(Axis.YN.rotationDegrees(5.0F * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(15.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(75.0F * direction))
            matrices.translate(0.0, -0.05, -0.1)
            matrices.scale(0.7F, 0.7F, 0.7F)
            return
        }

        if (isThinNewItem(stack)) {
            matrices.translate(0.0, 0.0, -0.1)
            matrices.mulPose(Axis.YN.rotationDegrees(5.0F * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(15.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(75.0F * direction))
            return
        }

        if (item is BlockItem) {
            matrices.mulPose(Axis.YN.rotationDegrees(25.0F * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(5.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(75.0F * direction))
            matrices.translate(0.2 * direction, 0.2, 0.05)
            return
        }

        if (effectiveUseAnimation(stack) == ItemUseAnimation.BLOCK) {
            matrices.mulPose(Axis.ZP.rotationDegrees(160.0F * direction))
            matrices.mulPose(Axis.YP.rotationDegrees(-60.0F * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(-70.0F))
            matrices.scale(0.75F, 0.75F, 0.75F)
            matrices.translate(0.15 * direction, if (arm == HumanoidArm.RIGHT) 0.35 else 0.45, if (arm == HumanoidArm.RIGHT) -0.15 else -0.1)
            matrices.translate(0.17 * direction, 0.0, 0.3)
            matrices.mulPose(Axis.YP.rotationDegrees(-90.0F * direction))
            return
        }

        if (isSpearLike(stack)) {
            matrices.mulPose(Axis.YN.rotationDegrees(75.0F * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(90.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(45.0F * direction))
            matrices.translate(-0.3 * direction, 0.0, 0.0)
        } else {
            matrices.mulPose(Axis.YN.rotationDegrees(75.0F * direction))
            matrices.mulPose(Axis.XP.rotationDegrees(70.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(45.0F * direction))
        }

        matrices.scale(1.2F, 1.2F, 1.2F)

        if (effectiveUseAnimation(stack) == ItemUseAnimation.BOW) {
            matrices.translate(-0.1 * direction, -0.2, 0.0)
        }
    }

    private fun updateNewSlashState(swingProgress: Float) {
        val swinging = swingProgress > 0.01F
        if (swinging && !newWasSwinging) {
            newPrimarySlash = !newPrimarySlash
        }
        newWasSwinging = swinging
    }

    private fun newSwingRot(swingProgress: Float): Float =
        if (swingProgress < 0.6F) {
            sin(Mth.clamp(swingProgress, 0.0F, 0.12506F) * 12.56F).toFloat()
        } else {
            sin(Mth.clamp(swingProgress, 0.62532F, 0.75038F) * 12.56F).toFloat()
        }

    private fun easeInOutBack(x: Float): Float {
        val c1 = 1.70158F
        val c2 = c1 * 1.525F
        return if (x < 0.5F) {
            ((2.0F * x).pow(2.0F) * ((c2 + 1.0F) * 2.0F * x - c2) / 2.0F)
        } else {
            (((2.0F * x - 2.0F).pow(2.0F) * ((c2 + 1.0F) * (x * 2.0F - 2.0F) + c2) + 2.0F) / 2.0F)
        }
    }

    private fun effectiveUseAnimation(stack: ItemStack): ItemUseAnimation = stack.useAnimation

    private fun isSpearLike(stack: ItemStack): Boolean =
        stack.item is TridentItem ||
            stack.useAnimation == ItemUseAnimation.TRIDENT ||
            stack.useAnimation == ItemUseAnimation.SPEAR

    private fun isSwordLike(stack: ItemStack): Boolean = stack.`is`(ItemTags.SWORDS)

    private fun isAxeLike(stack: ItemStack): Boolean =
        stack.item is AxeItem || stack.`is`(ItemTags.AXES)

    private fun isShovelLike(stack: ItemStack): Boolean =
        stack.item is ShovelItem || stack.`is`(ItemTags.SHOVELS)

    private fun isGeneralDiggerTool(stack: ItemStack): Boolean =
        isAxeLike(stack) ||
            isShovelLike(stack) ||
            stack.`is`(ItemTags.PICKAXES) ||
            stack.`is`(ItemTags.HOES)

    private fun isWeaponLike(stack: ItemStack): Boolean {
        val item = stack.item
        val useAnimation = effectiveUseAnimation(stack)
        return isSwordLike(stack) ||
            isGeneralDiggerTool(stack) ||
            item is ShieldItem ||
            item is TridentItem ||
            useAnimation == ItemUseAnimation.BOW ||
            useAnimation == ItemUseAnimation.BLOCK ||
            useAnimation == ItemUseAnimation.TRIDENT ||
            useAnimation == ItemUseAnimation.SPEAR ||
            item == Items.SHEARS ||
            item == Items.CARROT_ON_A_STICK ||
            item == Items.WARPED_FUNGUS_ON_A_STICK
    }

    private fun isTorchLike(item: Item): Boolean =
        item == Items.TORCH || item == Items.SOUL_TORCH || item == Items.REDSTONE_TORCH

    private fun isThinNewItem(stack: ItemStack): Boolean {
        val item = stack.item
        val block = (item as? BlockItem)?.block
        return item == Items.STRING ||
            item == Items.REDSTONE ||
            item == Items.LEVER ||
            item == Items.TRIPWIRE_HOOK ||
            block?.defaultBlockState()?.`is`(BlockTags.RAILS) == true ||
            block?.defaultBlockState()?.`is`(BlockTags.CLIMBABLE) == true ||
            block?.defaultBlockState()?.`is`(BlockTags.DOORS) == true ||
            block?.defaultBlockState()?.`is`(BlockTags.BUTTONS) == true ||
            block?.defaultBlockState()?.`is`(BlockTags.PRESSURE_PLATES) == true
    }

    private fun isSmallNewItem(stack: ItemStack): Boolean {
        val item = stack.item
        return stack.components.has(DataComponents.FOOD) ||
            item is BucketItem ||
            (!isWeaponLike(stack) && item !is BlockItem)
    }

    enum class Mode(override val choiceName: String) : NamedChoice {
        MODE1("Mode1"),
        MODE2("Mode2"),
        MODE3("Mode3"),
        MODE4("Mode4"),
        MODE5("Mode5"),
        MODE6("Mode6"),
        MODE7("Mode7"),
        ROTATE_360("360"),
        SLANT("Slant"),
        NEW("New"),
        FADE("Fade"),
        SPIN_SLASH("SpinSlash")
    }

    enum class RenderHand(override val choiceName: String) : NamedChoice {
        RIGHT("Right"),
        LEFT("Left")
    }

    class NewArmRender(
        val equipProgress: Float,
        val swingProgress: Float
    )
}
