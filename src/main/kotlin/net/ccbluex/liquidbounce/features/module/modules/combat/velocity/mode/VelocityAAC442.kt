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

import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.utils.entity.velocity
import net.minecraft.world.phys.Vec3

object VelocityAAC442 : VelocityMode("AAC4.4.2") {

    private val reduce by float("Reduce", 0.62f, 0f..1f)

    @Suppress("unused")
    private val repeatable = tickHandler {
        if (player.hurtTime > 0 && !player.onGround()) {
            val velocity = player.velocity
            player.velocity = Vec3(velocity.x * reduce, velocity.y, velocity.z * reduce)
        }
    }

}
