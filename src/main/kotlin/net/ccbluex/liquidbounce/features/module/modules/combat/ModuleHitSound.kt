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

import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.logger
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.LivingEntity

object ModuleHitSound : ClientModule(
    "HitSound",
    Category.COMBAT
) {

    private val volume by float("Volume", 1.0f, 0.1f..2.0f, 0.05f)
    private val pitch by float("Pitch", 1.0f, 0.5f..2.0f, 0.05f)
    private var diagnosticAttackEvents = 0
    private var diagnosticExceptionLogged = false

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> { event ->
        val player = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        val target = event.entity
        val targetAlive = (target as? LivingEntity)?.isAlive == true
        val shouldLog = diagnosticAttackEvents < MAX_DIAGNOSTIC_ATTACK_EVENTS

        if (shouldLog) {
            diagnosticAttackEvents++
            logger.info(
                "[HitSoundKlackDiag] AttackEntityEvent reached " +
                    "targetType=${target.javaClass.name} " +
                    "targetAlive=$targetAlive " +
                    "fallDistance=${player.fallDistance} " +
                    "onGround=${player.onGround()} " +
                    "eventCancelled=${event.isCancelled} " +
                    "moduleRunning=$running"
            )
        }

        if (target !is LivingEntity || !target.isAlive) {
            return@handler
        }

        val isCrit = player.fallDistance > 0.0f && !player.onGround()

        if (!isCrit) {
            if (shouldLog) {
                logger.info("[HitSoundKlackDiag] Klack predicate failed; no sound")
            }
            return@handler
        }

        if (shouldLog) {
            logger.info("[HitSoundKlackDiag] Klack predicate passed; calling playSound")
        }

        try {
            level.playSound(
                player,
                player.x,
                player.y,
                player.z,
                SoundEvents.WITHER_AMBIENT,
                SoundSource.PLAYERS,
                volume,
                pitch
            )
            if (shouldLog) {
                logger.info("[HitSoundKlackDiag] playSound returned")
            }
        } catch (e: Exception) {
            if (!diagnosticExceptionLogged) {
                diagnosticExceptionLogged = true
                logger.error("[HitSoundKlackDiag] playSound threw", e)
            }
        }
    }

    private const val MAX_DIAGNOSTIC_ATTACK_EVENTS = 10

}
