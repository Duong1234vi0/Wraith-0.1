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
package net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode

import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.utils.entity.jump
import net.ccbluex.liquidbounce.utils.entity.moving
import net.ccbluex.liquidbounce.utils.entity.velocity
import net.minecraft.world.phys.Vec3

object VelocityHylex : VelocityMode("Hylex") {

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> { event ->
        if (event.isCancelled || !player.moving || !player.isSprinting) {
            return@handler
        }

        val factor = when (player.hurtTime) {
            9 -> 0.8
            8 -> 0.11
            7 -> 0.4
            4 -> 0.37
            else -> return@handler
        }

        val velocity = player.velocity
        player.velocity = Vec3(velocity.x * factor, velocity.y, velocity.z * factor)
    }

    @Suppress("unused")
    private val repeatable = tickHandler {
        if (player.hurtTime > 5 && player.onGround()) {
            player.jump()
        }
    }

}
