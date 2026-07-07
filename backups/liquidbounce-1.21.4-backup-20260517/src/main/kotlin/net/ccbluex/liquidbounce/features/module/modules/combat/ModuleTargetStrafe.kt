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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.PlayerTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.entity.getMovementDirectionOfInput
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.util.math.Vec3d
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

/**
 * Dreamcore Collision TargetStrafe, constrained to real entity collision before applying boost.
 */
object ModuleTargetStrafe : ClientModule("TargetStrafe", Category.COMBAT) {

    private val speed by float("Speed", 0.003f, 0f..0.10f, "b/t")
        .onChange { value -> value.roundToStep(0.001f).coerceIn(0f, 0.10f) }
    private val distance by float("Distance", 2.75f, 1f..3f)
        .onChange { value -> value.roundToStep(0.01f).coerceIn(1f, 3f) }
    private val expand by float("Expand", 0f, 0f..0.2f, "b")
        .onChange { value -> value.roundToStep(0.01f).coerceIn(0f, 0.2f) }
    private val direction by enumChoice("Direction", Direction.INPUT_YAW)
    private val warmupTicks by int("WarmupTicks", 1, 0..3, "ticks")
    private val pulseDelay by int("PulseDelay", 2, 0..6, "ticks")

    private var collisionTicks = 0
    private var pulseTicks = 0

    @Suppress("unused")
    private val tickHandler = handler<PlayerTickEvent> {
        val input = DirectionalInput(player.input)

        if (!input.isMoving || !ModuleKillAura.running) {
            resetCollisionState()
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target ?: run {
            resetCollisionState()
            return@handler
        }

        if (!target.isAlive || target.isRemoved) {
            resetCollisionState()
            return@handler
        }

        if (player.distanceTo(target) >= distance) {
            resetCollisionState()
            return@handler
        }

        val playerBox = player.boundingBox.expand(expand.toDouble())
        if (!playerBox.intersects(target.boundingBox)) {
            resetCollisionState()
            return@handler
        }

        collisionTicks++
        if (collisionTicks <= warmupTicks) {
            return@handler
        }

        if (pulseTicks > 0) {
            pulseTicks--
            return@handler
        }

        val velocity = player.velocity
        val speedValue = speed.toDouble()

        if (speedValue <= 0.0) {
            return@handler
        }

        val boost = direction.getBoostVector(input, speedValue) ?: return@handler

        player.velocity = Vec3d(
            velocity.x + boost.x,
            velocity.y,
            velocity.z + boost.z
        )

        pulseTicks = pulseDelay
    }

    private fun Float.roundToStep(step: Float): Float {
        return round(this / step) * step
    }

    private fun resetCollisionState() {
        collisionTicks = 0
        pulseTicks = 0
    }

    private fun Direction.getBoostVector(input: DirectionalInput, speed: Double): Vec3d? {
        return when (this) {
            Direction.INPUT_YAW -> {
                val yaw = getMovementDirectionOfInput(
                    RotationManager.currentRotation?.yaw ?: player.yaw,
                    input
                )
                val angle = Math.toRadians(yaw.toDouble())

                Vec3d(-sin(angle) * speed, 0.0, cos(angle) * speed)
            }

            Direction.TARGET -> {
                val delta = targetDeltaHorizontal() ?: return null

                delta.normalize().multiply(speed)
            }
        }
    }

    private fun targetDeltaHorizontal(): Vec3d? {
        val target = ModuleKillAura.targetTracker.target ?: return null
        val delta = target.pos.subtract(player.pos)
        val horizontal = Vec3d(delta.x, 0.0, delta.z)

        return horizontal.takeIf { it.lengthSquared() > 1.0E-6 }
    }

    private enum class Direction(override val choiceName: String) : NamedChoice {
        INPUT_YAW("InputYaw"),
        TARGET("Target")
    }

}
