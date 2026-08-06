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
package net.ccbluex.liquidbounce.features.module.modules.combat.criticals

import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.ModuleCriticals.CriticalsSelectionMode

internal data class CriticalsSelectionContext(
    val mode: CriticalsSelectionMode,
    val physicalJumpKeyPressed: Boolean,
    val transformedPlayerInputJump: Boolean,
    val manualJumpCycleActive: Boolean,
    val naturalCriticalEnvironmentPossible: Boolean,
    val gliding: Boolean,
    val onGround: Boolean,
    val fallDistance: Float,
    val verticalVelocity: Double,
    val sprinting: Boolean,
    val serverSprinting: Boolean,
    val attackStrengthReady: Boolean,
    val wouldDoCriticalHit: Boolean,
    val wouldDoCriticalHitIgnoringSprint: Boolean,
    val smartHeuristicPermitsAttack: Boolean,
)

internal data class CriticalsSelectionDecision(
    val context: CriticalsSelectionContext,
    val manualJumpGateActive: Boolean,
    val permitsAttack: Boolean,
    val requiresKillAuraSprintStop: Boolean,
)

internal fun decideCriticalSelection(context: CriticalsSelectionContext): CriticalsSelectionDecision {
    val manualJumpGateActive = context.mode == CriticalsSelectionMode.SMART &&
        isManualJumpCriticalGateActive(
            manualJumpCycleActive = context.manualJumpCycleActive,
            naturalCriticalEnvironmentPossible = context.naturalCriticalEnvironmentPossible,
            gliding = context.gliding,
        )

    val naturalCriticalReady = context.wouldDoCriticalHit && !context.serverSprinting
    val permitsAttack = when (context.mode) {
        CriticalsSelectionMode.IGNORE -> true
        CriticalsSelectionMode.ALWAYS -> naturalCriticalReady
        CriticalsSelectionMode.SMART -> if (manualJumpGateActive) {
            naturalCriticalReady
        } else {
            context.smartHeuristicPermitsAttack
        }
    }

    val requiresKillAuraSprintStop = (context.sprinting || context.serverSprinting) &&
        context.wouldDoCriticalHitIgnoringSprint && !naturalCriticalReady && when (context.mode) {
            CriticalsSelectionMode.IGNORE -> false
            CriticalsSelectionMode.ALWAYS -> true
            CriticalsSelectionMode.SMART -> manualJumpGateActive
        }

    return CriticalsSelectionDecision(
        context = context,
        manualJumpGateActive = manualJumpGateActive,
        permitsAttack = permitsAttack,
        requiresKillAuraSprintStop = requiresKillAuraSprintStop,
    )
}

internal fun isManualJumpCriticalGateActive(
    manualJumpCycleActive: Boolean,
    naturalCriticalEnvironmentPossible: Boolean,
    gliding: Boolean,
): Boolean = manualJumpCycleActive && naturalCriticalEnvironmentPossible && !gliding
