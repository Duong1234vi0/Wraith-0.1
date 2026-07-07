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
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.item.CrossbowItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.world.item.Items
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * VCore 0.8 `Animations` port.
 *
 * #Edit By Codex 2026-06-28: the previous local port only replaced the main-hand melee branch.
 * VCore cancels first-person item rendering for every non-map item while the module is active, then
 * rebuilds the crossbow/use/bow/spear/brush/normal transforms itself. This file now exposes the same
 * transform state to the 1.26.2 ItemInHandRenderer mixin instead of falling back to vanilla branches.
 */
object ModuleAnimations : ClientModule("Animations", Category.RENDER) {

    private val onlyAura by boolean("OnlyAura", false)
    private val disableSwapMain by boolean("DisableSwapMain", true)
    private val disableSwapOff by boolean("DisableSwapOff", true)
    private val slowAnimation by boolean("SlowAnimation", true)
    private val onlyOnHit by boolean("OnlyOnHit", false)
    private val mode by enumChoice("Mode", Mode.SMOOTH)
    private val offset by int("Offset", 0, 0..10)
    private val power by int("Power", 6, 1..10)
    private val angle by float("Angle", 0.0F, 0.0F..360.0F)
    private val slowAnimationVal by int("SlowValue", 12, 1..20)

    private var lastHitTime = 0L

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> {
        lastHitTime = System.currentTimeMillis()
    }

    private fun auraReady(): Boolean =
        !onlyAura || (ModuleKillAura.running && ModuleKillAura.targetTracker.target != null)

    private fun hitGateReady(): Boolean =
        !onlyOnHit || System.currentTimeMillis() - lastHitTime < HIT_ANIMATION_DURATION_MS

    fun shouldAnimate(item: ItemStack): Boolean = running && auraReady() && hitGateReady()

    fun shouldRenderCustom(item: ItemStack): Boolean =
        shouldAnimate(item) && !item.isEmpty && !item.has(DataComponents.MAP_ID)

    fun shouldDisableSwapMain(): Boolean = running && disableSwapMain

    fun shouldDisableSwapOff(): Boolean = running && disableSwapOff

    /** VCore SlowAnimation: extends the local hand-swing duration by SlowValue ticks. */
    fun extraSwingDuration(): Int = if (running && auraReady() && slowAnimation) slowAnimationVal else 0

    /**
     * Applies the full VCore first-person transform before the mixin submits the item.
     * The mixin owns push/pop and renderItem, matching VCore's HEAD-cancel render hook.
     */
    fun applyVCoreTransforms(
        player: AbstractClientPlayer,
        tickDelta: Float,
        hand: InteractionHand,
        swingProgress: Float,
        item: ItemStack,
        equipProgress: Float,
        matrices: PoseStack
    ) {
        val mainHand = hand == InteractionHand.MAIN_HAND
        val arm = if (mainHand) player.mainArm else player.mainArm.opposite

        if (item.item == Items.CROSSBOW) {
            applyCrossbowTransform(player, tickDelta, hand, swingProgress, item, equipProgress, arm, mainHand, matrices)
            return
        }

        val chosenMode = if (isSword(item.item) || isAxe(item.item) || isPickaxe(item.item)) {
            mode
        } else {
            Mode.FADE
        }
        val right = arm == HumanoidArm.RIGHT

        if (player.isUsingItem && player.getUseItemRemainingTicks() > 0 && player.usedItemHand == hand) {
            val side = if (right) 1 else -1

            when (item.useAnimation) {
                ItemUseAnimation.NONE,
                ItemUseAnimation.BLOCK -> applyEquipOffset(matrices, arm, equipProgress)

                ItemUseAnimation.EAT,
                ItemUseAnimation.DRINK -> {
                    applyEatOrDrinkTransformationCustom(matrices, tickDelta, arm, item, player)
                    applyEquipOffset(matrices, arm, equipProgress)
                }

                ItemUseAnimation.BOW -> applyBowTransform(matrices, tickDelta, side, arm, equipProgress, item, player)

                ItemUseAnimation.TRIDENT,
                ItemUseAnimation.SPEAR -> applySpearTransform(matrices, tickDelta, side, arm, equipProgress, item, player)

                ItemUseAnimation.BRUSH -> applyBrushTransformation(matrices, tickDelta, arm, item, equipProgress, player)

                else -> applyEquipOffset(matrices, arm, equipProgress)
            }
        } else if (player.isAutoSpinAttack) {
            val side = if (right) 1 else -1
            applyEquipOffset(matrices, arm, equipProgress)
            matrices.translate(side * -0.4F, 0.8F, 0.3F)
            matrices.mulPose(Axis.YP.rotationDegrees(side * 65.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(side * -85.0F))
        } else {
            renderSwordAnimation(matrices, swingProgress, equipProgress, arm, hand, chosenMode)
        }

        applyHeldItemViewModel(matrices, hand)
        applySelfOldMainHandOffset(matrices, hand, chosenMode)
    }

    private fun applyCrossbowTransform(
        player: AbstractClientPlayer,
        tickDelta: Float,
        hand: InteractionHand,
        swingProgress: Float,
        item: ItemStack,
        equipProgress: Float,
        arm: HumanoidArm,
        mainHand: Boolean,
        matrices: PoseStack
    ) {
        val charged = CrossbowItem.isCharged(item)
        val right = arm == HumanoidArm.RIGHT
        val side = if (right) 1 else -1

        if (player.isUsingItem && player.getUseItemRemainingTicks() > 0 && player.usedItemHand == hand && !charged) {
            applyEquipOffset(matrices, arm, equipProgress)
            matrices.translate(side * -0.4785682F, -0.094387F, 0.05731531F)
            matrices.mulPose(Axis.XP.rotationDegrees(-11.935F))
            matrices.mulPose(Axis.YP.rotationDegrees(side * 65.3F))
            matrices.mulPose(Axis.ZP.rotationDegrees(side * -9.785F))

            val useTime = item.getUseDuration(player) - (player.getUseItemRemainingTicks() - tickDelta + 1.0F)
            var charge = useTime / CrossbowItem.getChargeDuration(item, player)
            if (charge > 1.0F) {
                charge = 1.0F
            }
            if (charge > 0.1F) {
                val wave = sin((useTime - 0.1F) * 1.3F).toFloat()
                val strength = charge - 0.1F
                val jiggle = wave * strength
                matrices.translate(jiggle * 0.0F, jiggle * 0.004F, jiggle * 0.0F)
            }

            matrices.translate(charge * 0.0F, charge * 0.0F, charge * 0.04F)
            matrices.scale(1.0F, 1.0F, 1.0F + charge * 0.2F)
            matrices.mulPose(Axis.YN.rotationDegrees(side * 45.0F))
        } else {
            val x = -0.4F * sin(sqrt(swingProgress) * PI).toFloat()
            val y = 0.2F * sin(sqrt(swingProgress) * PI * 2.0).toFloat()
            val z = -0.2F * sin(swingProgress * PI).toFloat()
            matrices.translate(side * x, y, z)
            applyEquipOffset(matrices, arm, equipProgress)
            applySwingOffset(matrices, arm, swingProgress)
            if (charged && swingProgress < 0.001F && mainHand) {
                matrices.translate(side * -0.641864F, 0.0F, 0.0F)
                matrices.mulPose(Axis.YP.rotationDegrees(side * 10.0F))
            }
        }
    }

    private fun applyBowTransform(
        matrices: PoseStack,
        tickDelta: Float,
        side: Int,
        arm: HumanoidArm,
        equipProgress: Float,
        item: ItemStack,
        player: AbstractClientPlayer
    ) {
        applyEquipOffset(matrices, arm, equipProgress)
        matrices.translate(side * -0.2785682F, 0.18344387F, 0.15731531F)
        matrices.mulPose(Axis.XP.rotationDegrees(-13.935F))
        matrices.mulPose(Axis.YP.rotationDegrees(side * 35.3F))
        matrices.mulPose(Axis.ZP.rotationDegrees(side * -9.785F))

        val useTime = item.getUseDuration(player) - (player.getUseItemRemainingTicks() - tickDelta + 1.0F)
        var pull = useTime / 20.0F
        pull = (pull * pull + pull * 2.0F) / 3.0F
        if (pull > 1.0F) {
            pull = 1.0F
        }
        if (pull > 0.1F) {
            val wave = sin((useTime - 0.1F) * 1.3F).toFloat()
            val strength = pull - 0.1F
            val jiggle = wave * strength
            matrices.translate(jiggle * 0.0F, jiggle * 0.004F, jiggle * 0.0F)
        }

        matrices.translate(pull * 0.0F, pull * 0.0F, pull * 0.04F)
        matrices.scale(1.0F, 1.0F, 1.0F + pull * 0.2F)
        matrices.mulPose(Axis.YN.rotationDegrees(side * 45.0F))
    }

    private fun applySpearTransform(
        matrices: PoseStack,
        tickDelta: Float,
        side: Int,
        arm: HumanoidArm,
        equipProgress: Float,
        item: ItemStack,
        player: AbstractClientPlayer
    ) {
        applyEquipOffset(matrices, arm, equipProgress)
        matrices.translate(side * -0.5F, 0.7F, 0.1F)
        matrices.mulPose(Axis.XP.rotationDegrees(-55.0F))
        matrices.mulPose(Axis.YP.rotationDegrees(side * 35.3F))
        matrices.mulPose(Axis.ZP.rotationDegrees(side * -9.785F))

        val useTime = item.getUseDuration(player) - (player.getUseItemRemainingTicks() - tickDelta + 1.0F)
        var pull = useTime / 10.0F
        if (pull > 1.0F) {
            pull = 1.0F
        }
        if (pull > 0.1F) {
            val wave = sin((useTime - 0.1F) * 1.3F).toFloat()
            val strength = pull - 0.1F
            val jiggle = wave * strength
            matrices.translate(jiggle * 0.0F, jiggle * 0.004F, jiggle * 0.0F)
        }

        matrices.translate(0.0F, 0.0F, pull * 0.2F)
        matrices.scale(1.0F, 1.0F, 1.0F + pull * 0.2F)
        matrices.mulPose(Axis.YN.rotationDegrees(side * 45.0F))
    }

    private fun applyBrushTransformation(
        matrices: PoseStack,
        tickDelta: Float,
        arm: HumanoidArm,
        stack: ItemStack,
        equipProgress: Float,
        player: AbstractClientPlayer
    ) {
        applyEquipOffset(matrices, arm, equipProgress)
        val f = player.getUseItemRemainingTicks() - tickDelta + 1.0F
        val g = 1.0F - f / stack.getUseDuration(player)
        val m = -15.0F + 75.0F * cos(g * 45.0F * PI).toFloat()
        if (arm != HumanoidArm.RIGHT) {
            matrices.translate(0.1F, 0.83F, 0.35F)
            matrices.mulPose(Axis.XP.rotationDegrees(-80.0F))
            matrices.mulPose(Axis.YP.rotationDegrees(-90.0F))
            matrices.mulPose(Axis.XP.rotationDegrees(m))
            matrices.translate(-0.3F, 0.22F, 0.35F)
        } else {
            matrices.translate(-0.25F, 0.22F, 0.35F)
            matrices.mulPose(Axis.XP.rotationDegrees(-80.0F))
            matrices.mulPose(Axis.YP.rotationDegrees(90.0F))
            matrices.mulPose(Axis.ZP.rotationDegrees(0.0F))
            matrices.mulPose(Axis.XP.rotationDegrees(m))
        }
    }

    private fun applyEatOrDrinkTransformationCustom(
        matrices: PoseStack,
        tickDelta: Float,
        arm: HumanoidArm,
        stack: ItemStack,
        player: AbstractClientPlayer
    ) {
        val f = player.getUseItemRemainingTicks() - tickDelta + 1.0F
        val g = f / stack.getUseDuration(player)
        if (g < 0.8F) {
            val h = abs(cos(f / 4.0F * PI) * 0.005F).toFloat()
            matrices.translate(0.0F, h, 0.0F)
        }

        val h = 1.0F - g.toDouble().pow(27.0).toFloat()
        val side = if (arm == HumanoidArm.RIGHT) 1 else -1
        matrices.translate(h * 0.6F * side, h * -0.5F, h * 0.0F)
        matrices.mulPose(Axis.YP.rotationDegrees(side * h * 90.0F))
        matrices.mulPose(Axis.XP.rotationDegrees(h * 10.0F))
        matrices.mulPose(Axis.ZP.rotationDegrees(side * h * 30.0F))
    }

    private fun renderSwordAnimation(
        matrices: PoseStack,
        swingProgress: Float,
        equipProgress: Float,
        arm: HumanoidArm,
        hand: InteractionHand,
        animMode: Mode
    ) {
        if (arm == HumanoidArm.LEFT && (animMode == Mode.SELF || animMode == Mode.OLD)) {
            val viewModelX = getSelfOldViewModelX(hand, animMode)
            val viewModelY = getSelfOldViewModelY(hand, animMode)
            val viewModelZ = viewModelZ()
            applyEquipOffset(matrices, arm, equipProgress)
            matrices.translate(-viewModelX, viewModelY, viewModelZ)
            applySwingOffset(matrices, arm, swingProgress)
            matrices.translate(viewModelX, -viewModelY, -viewModelZ)
            return
        }

        val sp = swingProgress.toDouble()
        val powF = power.toFloat()

        when (animMode) {
            Mode.DEFAULT -> {
                applyEquipOffset(matrices, arm, equipProgress)
                translateToViewModel(matrices, arm)
                val sin2 = sin(sqrt(sp) * PI).toFloat()
                matrices.mulPose(Axis.XP.rotationDegrees(sin2 * -(powF * 10.0F)))
                matrices.mulPose(Axis.ZP.rotationDegrees(sin2 * 45.0F))
                matrices.mulPose(Axis.YP.rotationDegrees(sin2 * 15.0F))
                translateBack(matrices, arm)
            }
            Mode.SMOOTH -> {
                applyEquipOffset(matrices, arm, equipProgress)
                translateToViewModel(matrices, arm)
                val f = sin(sp * sp * PI).toFloat()
                val sin2 = sin(sqrt(sp) * PI).toFloat()
                matrices.mulPose(Axis.YP.rotationDegrees(sin2 * (45.0F + f * -20.0F)))
                matrices.mulPose(Axis.ZP.rotationDegrees(sin2 * sin2 * -20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(sin2 * -(powF * 10.0F)))
                matrices.mulPose(Axis.YP.rotationDegrees(sin2 * -45.0F))
                translateBack(matrices, arm)
            }
            Mode.FADE -> {
                applyEquipOffset(matrices, arm, equipProgress)
                translateToViewModel(matrices, arm)
                val handDir = if (arm == HumanoidArm.RIGHT) 1 else -1
                val sinSq = sin(sp * sp * PI).toFloat()
                val sinSqrt = sin(sqrt(sp) * PI).toFloat()
                val sinLinear = sin(sp * PI).toFloat()
                matrices.mulPose(Axis.YP.rotationDegrees(handDir * sinSqrt * (45.0F + sinSq * -5.0F)))
                matrices.mulPose(Axis.ZP.rotationDegrees(handDir * sinSqrt * sinLinear * -20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(sinLinear * -((powF + 2.0F) * 8.0F)))
                matrices.mulPose(Axis.YP.rotationDegrees(handDir * sinSqrt * -45.0F))
                translateBack(matrices, arm)
            }
            Mode.SELF -> {
                val anim = sin(sp * PI).toFloat()
                applyEquipOffset(matrices, arm, 0.0F)
                translateToSelfOldViewModel(matrices, hand, animMode)
                matrices.mulPose(Axis.YP.rotationDegrees(90.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(-70.0F))
                val baseX = -90.0F + offset * 10.0F
                matrices.mulPose(Axis.XP.rotationDegrees(baseX - powF * 10.0F * anim))
                translateBackSelfOldViewModel(matrices, hand, animMode)
            }
            Mode.OLD -> {
                val anim = sin(sp * PI).toFloat()
                applyEquipOffset(matrices, arm, 0.0F)
                translateToSelfOldViewModel(matrices, hand, animMode)
                matrices.mulPose(Axis.YP.rotationDegrees(90.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(-30.0F))
                val baseX = -90.0F + offset * 10.0F
                matrices.mulPose(Axis.XP.rotationDegrees(baseX - powF * 10.0F * anim))
                translateBackSelfOldViewModel(matrices, hand, animMode)
            }
            Mode.WYVERN_SELF2,
            Mode.WYVERN_DOWN,
            Mode.WYVERN_FORWARD,
            Mode.WYVERN_TOUCH,
            Mode.WYVERN_PANDER,
            Mode.WYVERN_CURT,
            Mode.WYVERN_BLOCK_HIT -> renderWyvernAnimation(
                matrices,
                swingProgress,
                equipProgress,
                arm,
                animMode
            )
        }
    }

    private fun renderWyvernAnimation(
        matrices: PoseStack,
        swingProgress: Float,
        equipProgress: Float,
        arm: HumanoidArm,
        animMode: Mode
    ) {
        // Wyvern-DLC applies its custom SwingAnimation only for the right arm; left arm falls back
        // to the original held-item swing path.
        if (arm != HumanoidArm.RIGHT) {
            applyEquipOffset(matrices, arm, equipProgress)
            applySwingOffset(matrices, arm, swingProgress)
            return
        }

        val anim = sin(swingProgress * (PI / 2.0) * 2.0).toFloat()
        val sin2 = sin(sqrt(swingProgress) * PI).toFloat()
        val powerF = power.toFloat()

        when (animMode) {
            Mode.WYVERN_SELF2 -> {
                matrices.translate(0.56F, -0.52F, -0.72F)
                matrices.mulPose(Axis.YP.rotationDegrees(70.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(-60.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-angle - powerF * 10.0F * anim))
            }
            Mode.WYVERN_DOWN -> {
                matrices.translate(0.56F, -0.52F - anim * powerF / 24.0F, -0.72F)
                matrices.mulPose(Axis.YP.rotationDegrees(90.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(-30.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-90.0F))
            }
            Mode.WYVERN_FORWARD -> {
                matrices.translate(0.56F, -0.52F, -0.72F)
                val f = 35.0F
                matrices.translate(0.0F, 0.0F, -0.3F * sin2)
                matrices.mulPose(Axis.XP.rotationDegrees(sin2 * -f))
                matrices.mulPose(Axis.ZP.rotationDegrees(sin2 * f))
            }
            Mode.WYVERN_TOUCH -> {
                matrices.translate(0.56F, -0.52F, -0.72F)
                matrices.scale(1.0F, 1.0F, 1.0F + anim * powerF / 4.0F)
                matrices.translate(0.0F, 0.0F, -0.265F)
                matrices.mulPose(Axis.XP.rotationDegrees(-100.0F))
            }
            Mode.WYVERN_PANDER -> {
                matrices.translate(0.56F, -0.52F, -0.72F)
                matrices.scale(0.8F, 0.8F, 0.8F)
                val swingOffset = anim * 0.15F
                matrices.translate(0.3F - swingOffset, 0.2F - equipProgress * 0.12F, -0.15F - anim * 0.13F)
                matrices.mulPose(Axis.YP.rotationDegrees(76.0F - 10.0F * anim))
                matrices.mulPose(Axis.ZP.rotationDegrees(-16.0F - 8.0F * anim))
                matrices.mulPose(Axis.XP.rotationDegrees(-83.0F - 26.0F * anim))
            }
            Mode.WYVERN_CURT -> {
                matrices.translate(0.56F, -0.52F, -0.72F)
                val sqrtSwing = sqrt(swingProgress)
                val g = sin(sqrtSwing * PI).toFloat()
                val sinExtra = sin(swingProgress * PI).toFloat()
                matrices.translate(0.4F - g * 0.2F, -0.2F + g * 0.3F, -0.5F - sinExtra * 0.2F)
                matrices.mulPose(Axis.YP.rotationDegrees(91.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(-40.0F + g * -100.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-60.0F))
            }
            Mode.WYVERN_BLOCK_HIT -> {
                matrices.translate(0.56F, -0.52F, -0.72F)
                val f = sin(swingProgress * swingProgress * PI).toFloat()
                matrices.mulPose(Axis.YP.rotationDegrees(45.0F))
                val g = sin(sqrt(swingProgress) * PI).toFloat()
                matrices.mulPose(Axis.XP.rotationDegrees(f * -20.0F))
                matrices.mulPose(Axis.ZP.rotationDegrees(g * -20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(g * -80.0F))
                matrices.translate(0.4F, 0.2F, 0.2F)
                matrices.translate(-0.5F, 0.08F, 0.0F)
                matrices.mulPose(Axis.YP.rotationDegrees(20.0F))
                matrices.mulPose(Axis.XP.rotationDegrees(-80.0F))
                matrices.mulPose(Axis.YP.rotationDegrees(20.0F))
            }
            else -> Unit
        }
    }

    private fun applyEquipOffset(matrices: PoseStack, arm: HumanoidArm, equipProgress: Float) {
        val side = if (arm == HumanoidArm.RIGHT) 1 else -1
        matrices.translate(side * 0.56F, -0.52F + equipProgress * -0.6F, -0.72F)
    }

    private fun applySwingOffset(matrices: PoseStack, arm: HumanoidArm, swingProgress: Float) {
        val side = if (arm == HumanoidArm.RIGHT) 1 else -1
        val f = sin(swingProgress * swingProgress * PI).toFloat()
        matrices.mulPose(Axis.YP.rotationDegrees(side * (45.0F + f * -20.0F)))
        val g = sin(sqrt(swingProgress) * PI).toFloat()
        matrices.mulPose(Axis.ZP.rotationDegrees(side * g * -20.0F))
        matrices.mulPose(Axis.XP.rotationDegrees(g * -80.0F))
        matrices.mulPose(Axis.YP.rotationDegrees(side * -45.0F))
    }

    private fun translateToViewModel(matrices: PoseStack, arm: HumanoidArm) {
        if (ModuleViewModel.running) {
            matrices.translate(vmX(arm), vmY(arm), vmZ(arm))
        }
    }

    private fun translateBack(matrices: PoseStack, arm: HumanoidArm) {
        if (ModuleViewModel.running) {
            matrices.translate(-vmX(arm), -vmY(arm), -vmZ(arm))
        }
    }

    private fun translateToSelfOldViewModel(matrices: PoseStack, hand: InteractionHand, animMode: Mode) {
        matrices.translate(getSelfOldViewModelX(hand, animMode), getSelfOldViewModelY(hand, animMode), viewModelZ())
    }

    private fun translateBackSelfOldViewModel(matrices: PoseStack, hand: InteractionHand, animMode: Mode) {
        matrices.translate(-getSelfOldViewModelX(hand, animMode), -getSelfOldViewModelY(hand, animMode), -viewModelZ())
    }

    private fun applySelfOldMainHandOffset(matrices: PoseStack, hand: InteractionHand, animMode: Mode) {
        val xOffset = getSelfOldMainHandXOffset(hand, animMode)
        val yOffset = getSelfOldMainHandYOffset(hand, animMode)
        if (xOffset != 0.0F || yOffset != 0.0F) {
            matrices.translate(xOffset, yOffset, 0.0F)
        }
    }

    private fun applyHeldItemViewModel(matrices: PoseStack, hand: InteractionHand) {
        if (!ModuleViewModel.running) {
            return
        }

        if (hand == InteractionHand.MAIN_HAND) {
            matrices.translate(ModuleViewModel.rightX, ModuleViewModel.rightY, ModuleViewModel.rightZ)
        } else {
            matrices.translate(ModuleViewModel.leftX, ModuleViewModel.leftY, ModuleViewModel.leftZ)
        }
    }

    private fun getSelfOldViewModelX(hand: InteractionHand, animMode: Mode): Float =
        viewModelX(HumanoidArm.RIGHT) + getSelfOldMainHandXOffset(hand, animMode)

    private fun getSelfOldViewModelY(hand: InteractionHand, animMode: Mode): Float =
        viewModelY(HumanoidArm.RIGHT) + getSelfOldMainHandYOffset(hand, animMode)

    private fun getSelfOldMainHandXOffset(hand: InteractionHand, animMode: Mode): Float =
        if (hand == InteractionHand.MAIN_HAND && (animMode == Mode.SELF || animMode == Mode.OLD)) SELF_OLD_X else 0.0F

    private fun getSelfOldMainHandYOffset(hand: InteractionHand, animMode: Mode): Float =
        if (hand == InteractionHand.MAIN_HAND && (animMode == Mode.SELF || animMode == Mode.OLD)) SELF_OLD_Y else 0.0F

    private fun vmX(arm: HumanoidArm): Float = viewModelX(arm)
    private fun vmY(arm: HumanoidArm): Float = viewModelY(arm)
    private fun vmZ(arm: HumanoidArm): Float = viewModelZ(arm)

    private fun viewModelX(arm: HumanoidArm): Float = if (!ModuleViewModel.running) 0.0F else {
        if (arm == HumanoidArm.RIGHT) ModuleViewModel.rightX else ModuleViewModel.leftX
    }

    private fun viewModelY(arm: HumanoidArm): Float = if (!ModuleViewModel.running) 0.0F else {
        if (arm == HumanoidArm.RIGHT) ModuleViewModel.rightY else ModuleViewModel.leftY
    }

    private fun viewModelZ(arm: HumanoidArm = HumanoidArm.RIGHT): Float = if (!ModuleViewModel.running) 0.0F else {
        if (arm == HumanoidArm.RIGHT) ModuleViewModel.rightZ else ModuleViewModel.leftZ
    }

    private fun isSword(i: Item) = i == Items.WOODEN_SWORD || i == Items.STONE_SWORD ||
        i == Items.IRON_SWORD || i == Items.GOLDEN_SWORD || i == Items.DIAMOND_SWORD || i == Items.NETHERITE_SWORD ||
        i == Items.COPPER_SWORD

    private fun isAxe(i: Item) = i == Items.WOODEN_AXE || i == Items.STONE_AXE ||
        i == Items.IRON_AXE || i == Items.GOLDEN_AXE || i == Items.DIAMOND_AXE || i == Items.NETHERITE_AXE ||
        i == Items.COPPER_AXE

    private fun isPickaxe(i: Item) = i == Items.WOODEN_PICKAXE || i == Items.STONE_PICKAXE ||
        i == Items.IRON_PICKAXE || i == Items.GOLDEN_PICKAXE || i == Items.DIAMOND_PICKAXE ||
        i == Items.NETHERITE_PICKAXE || i == Items.COPPER_PICKAXE

    private const val SELF_OLD_X = 0.12F
    private const val SELF_OLD_Y = 0.1F
    private const val HIT_ANIMATION_DURATION_MS = 300L

    enum class Mode(override val choiceName: String) : NamedChoice {
        DEFAULT("Default"),
        SMOOTH("Smooth"),
        FADE("Fade"),
        SELF("Self"),
        OLD("Old"),
        WYVERN_SELF2("Self2"),
        WYVERN_DOWN("Down"),
        WYVERN_FORWARD("Forward"),
        WYVERN_TOUCH("Touch"),
        WYVERN_PANDER("Pander"),
        WYVERN_CURT("Curt"),
        WYVERN_BLOCK_HIT("BlockHit")
    }
}
