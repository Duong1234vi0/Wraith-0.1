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
package net.ccbluex.liquidbounce.features.module.modules.combat.targetstrafe.modes

import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.events.PlayerTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.combat.targetstrafe.ModuleTargetStrafe
import net.ccbluex.liquidbounce.utils.entity.forwardKey
import net.ccbluex.liquidbounce.utils.entity.jumpKey
import net.ccbluex.liquidbounce.utils.entity.leftKey
import net.ccbluex.liquidbounce.utils.entity.rightKey
import net.ccbluex.liquidbounce.utils.entity.setPressed
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MoverType
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.Vec3
import net.minecraft.util.Mth
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/**
 * Orbit mode: predicts an orbit point around the KillAura target each tick and
 * queues motion through PlayerMoveEvent so vanilla move() consumes it.
 * Avoids lava/fire/cobweb/void cells along the orbit footprint.
 */
object TargetStrafeDefault : Choice("Default") {

    override val parent: ChoiceConfigurable<*>
        get() = ModuleTargetStrafe.modes

    private val jump by boolean("Jump", true)
    private val distance by float("Distance", 2.4f, 0.1f..6.0f)
    private val speed by float("Speed", 0.4f, 0.05f..1.0f)
    private val damageBoost by boolean("DamageBoost", false)
    private val boostSpeed by float("BoostSpeed", 0.5f, 0.1f..1.5f)
        .visibleWhen { damageBoost }

    private var clockwise = true
    private var hasQueuedMotion = false
    private var queuedMotionX = 0.0
    private var queuedMotionZ = 0.0
    private var jumpTicks = 0

    override fun disable() {
        hasQueuedMotion = false
        jumpTicks = 0
    }

    @Suppress("unused")
    private val tickHandler = handler<PlayerTickEvent> {
        if (!ModuleKillAura.running) {
            hasQueuedMotion = false
            return@handler
        }
        val target = ModuleKillAura.targetTracker.target as? LivingEntity ?: run {
            hasQueuedMotion = false
            return@handler
        }
        if (!target.isAlive || target.isRemoved) {
            hasQueuedMotion = false
            return@handler
        }

        jumpTicks = (jumpTicks - 1).coerceAtLeast(0)
        hasQueuedMotion = false

        if (jump && player.onGround()) {
            mc.options.jumpKey.setPressed(false)
            player.jumpFromGround()
        }

        val maxDistance = ModuleKillAura.targetStrafeMaxRange
        val distanceToTarget = player.distanceTo(target)
        if (distanceToTarget > maxDistance) return@handler

        mc.options.forwardKey.setPressed(false)

        var s = speed
        if (damageBoost && player.hurtTime > 0 && player.isAlive) {
            s += boostSpeed
        }

        val clampDist = Mth.clamp(distanceToTarget, 0.01f, maxDistance)
        val baseAngle = atan2(player.z - target.z, player.x - target.x)
        val angleStep = Mth.clamp(s / clampDist, 0.01f, 1.0f).toDouble()

        var orbitAngle = baseAngle + if (clockwise) angleStep else -angleStep
        var orbitX = target.x + distance * cos(orbitAngle)
        var orbitZ = target.z + distance * sin(orbitAngle)

        if (isUnsafeAreaAround(orbitX, orbitZ)) {
            clockwise = !clockwise
            orbitAngle = baseAngle + if (clockwise) angleStep else -angleStep
            orbitX = target.x + distance * cos(orbitAngle)
            orbitZ = target.z + distance * sin(orbitAngle)
        }

        val angleTo = Math.toRadians(calculateAngleToTarget(orbitX, orbitZ))
        queuedMotionX = s * -sin(angleTo)
        queuedMotionZ = s * cos(angleTo)
        hasQueuedMotion = !queuedMotionX.isNaN() && !queuedMotionZ.isNaN()
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        if (!hasQueuedMotion || event.type != MoverType.SELF) return@handler
        event.movement = Vec3(queuedMotionX, event.movement.y, queuedMotionZ)
        hasQueuedMotion = false
    }

    private fun isUnsafeAreaAround(x: Double, z: Double): Boolean {
        if (player.horizontalCollision || (
                (mc.options.leftKey.isDown || mc.options.rightKey.isDown) && jumpTicks <= 0
            )) {
            jumpTicks = 10
            return true
        }

        val level = mc.level ?: return true
        val mutable = BlockPos.MutableBlockPos()
        val top = (player.y + 4.0).toInt()
        val blockX = Mth.floor(x)
        val blockZ = Mth.floor(z)

        for (y in top downTo level.minY) {
            mutable.set(blockX, y, blockZ)
            val state = level.getBlockState(mutable)
            val block = state.block
            if (block === Blocks.LAVA || block === Blocks.FIRE || block === Blocks.COBWEB) {
                return true
            }
            if (!state.isAir) return false
        }

        return isVoidAboveVoid(x, z)
    }

    private fun isVoidAboveVoid(x: Double, z: Double): Boolean {
        val level = mc.level ?: return true
        val mutable = BlockPos.MutableBlockPos()
        val blockX = Mth.floor(x)
        val blockZ = Mth.floor(z)
        for (y in player.y.toInt() downTo (level.minY + 1)) {
            mutable.set(blockX, y, blockZ)
            if (!level.getBlockState(mutable).isAir) return false
        }
        return true
    }

    private fun calculateAngleToTarget(x: Double, z: Double): Double {
        val diffX = x - player.x
        val diffZ = z - player.z
        return Math.toDegrees(atan2(diffZ, diffX)) - 90.0
    }
}
