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
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import kotlin.math.PI
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
    fun transformEquipProgress(original: Float, hand: InteractionHand): Float {
        if (!shouldApply() || hand != InteractionHand.MAIN_HAND) {
            return original
        }

        return swingDownValue.get()
    }

    @JvmStatic
    fun applySwingTransform(
        matrices: PoseStack,
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
        FADE("Fade"),
        SPIN_SLASH("SpinSlash")
    }

    enum class RenderHand(override val choiceName: String) : NamedChoice {
        RIGHT("Right"),
        LEFT("Left")
    }
}
