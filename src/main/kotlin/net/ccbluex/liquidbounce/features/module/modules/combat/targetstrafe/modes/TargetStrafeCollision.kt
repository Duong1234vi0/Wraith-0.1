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
import net.ccbluex.liquidbounce.event.events.PlayerTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.combat.targetstrafe.ModuleTargetStrafe
import net.ccbluex.liquidbounce.utils.entity.movementForward
import net.ccbluex.liquidbounce.utils.entity.movementSideways
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3

/**
 * Velocity-nudge mode: while moving and within CollisionDistance of the KillAura
 * target, add direction × CollisionSpeed to deltaMovement each tick.
 */
object TargetStrafeCollision : Choice("Collision") {

    override val parent: ChoiceConfigurable<*>
        get() = ModuleTargetStrafe.modes

    private val speed by float("Speed", 0.08f, 0.01f..0.1f)
    private val distance by float("Distance", 1.2f, 1.0f..6.0f)

    @Suppress("unused")
    private val tickHandler = handler<PlayerTickEvent> {
        if (!ModuleKillAura.running) return@handler
        val target = ModuleKillAura.targetTracker.target as? LivingEntity ?: return@handler
        if (!target.isAlive || target.isRemoved) return@handler

        val input = player.input
        val moving = input.movementForward != 0.0f || input.movementSideways != 0.0f
        if (!moving) return@handler

        if (player.distanceTo(target) >= distance) return@handler

        val direction = target.position().subtract(player.position()).normalize()
        val s = speed.toDouble()
        val velocity = player.deltaMovement
        player.deltaMovement = Vec3(
            velocity.x + direction.x * s,
            velocity.y,
            velocity.z + direction.z * s
        )
    }
}
