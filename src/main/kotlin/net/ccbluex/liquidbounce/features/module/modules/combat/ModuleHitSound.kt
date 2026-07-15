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

import net.ccbluex.liquidbounce.event.events.CriticalAttackEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource

object ModuleHitSound : ClientModule(
    "HitSound",
    Category.COMBAT
) {

    private val volume by float("Volume", 1.0f, 0.1f..2.0f, 0.05f)
    private val pitch by float("Pitch", 1.0f, 0.5f..2.0f, 0.05f)

    @Suppress("unused")
    private val criticalAttackHandler = handler<CriticalAttackEvent> { event ->
        val player = mc.player ?: return@handler
        val level = mc.level ?: return@handler

        if (event.attacker != player || !event.target.isAlive) {
            return@handler
        }

        level.playLocalSound(
            player.x,
            player.y,
            player.z,
            SoundEvents.WITHER_AMBIENT,
            SoundSource.PLAYERS,
            volume,
            pitch,
            false
        )
    }

}
