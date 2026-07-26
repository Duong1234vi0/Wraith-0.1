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
 *
 *
 */

package net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl

import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.KillAuraRotationsConfigurable
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.FactorAngleSmooth
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.kotlin.random
import net.minecraft.util.Mth
import net.minecraft.world.entity.LivingEntity

class LinearAngleSmooth(
    parent: ChoiceConfigurable<*>,
    horizontalTurnSpeed: ClosedFloatingPointRange<Float> = 180f..180f,
    verticalTurnSpeed: ClosedFloatingPointRange<Float> = 180f..180f,
) : FactorAngleSmooth("Linear", parent) {

    private val horizontalTurnSpeed by floatRange("HorizontalTurnSpeed", horizontalTurnSpeed,
        0.0f..180f)
    private val verticalTurnSpeed by floatRange("VerticalTurnSpeed", verticalTurnSpeed,
        0.0f..180f)

    private val vCorePitchState = LinearVCorePitchState()

    override fun enable() = vCorePitchState.reset()

    override fun disable() = vCorePitchState.reset()

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> { vCorePitchState.reset() }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> { vCorePitchState.reset() }

    override fun calculateFactors(
        rotationTarget: RotationTarget?,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Pair<Float, Float> {
        return if (rotationTarget != null) {
            horizontalTurnSpeed.random() to verticalTurnSpeed.random()
        } else {
            // Slowest turn speed, so we can calculate the slowest turn speed
            horizontalTurnSpeed.start to verticalTurnSpeed.start
        }
    }

    override fun process(
        rotationTarget: RotationTarget,
        currentRotation: Rotation,
        targetRotation: Rotation,
    ): Rotation {
        val (horizontalFactor, verticalFactor) = calculateFactors(
            rotationTarget,
            currentRotation,
            targetRotation,
        )
        val linearRotation = calculateLinearRotation(
            currentRotation,
            targetRotation,
            horizontalFactor,
            verticalFactor,
        )

        val target = rotationTarget.entity as? LivingEntity
        val localPlayer = mc.player
        val world = mc.level
        val useTrackPitch = shouldUseVCoreElytraPitch(
            linearIsSelected = KillAuraRotationsConfigurable.activeAngleSmooth === this,
            killAuraOwnsRotation = rotationTarget.provider === ModuleKillAura && ModuleKillAura.running,
            targetIsCurrent = target != null && ModuleKillAura.targetTracker.target === target,
            targetValid = target != null && target.isAlive && !target.isRemoved,
            elytraTarget = target != null && ModuleElytraTarget.shouldTarget(target),
            peakAssistApplied = target != null && ModuleEMaceHelper.isPeakAssistAimAppliedTo(target),
        )

        if (!useTrackPitch || target == null || localPlayer == null || world == null) {
            vCorePitchState.reset()
            return linearRotation
        }

        vCorePitchState.enter(target, localPlayer, world)
        val pitchAcceleration = updateVCoreTrackPitchAcceleration(
            pitchAcceleration = vCorePitchState.pitchAcceleration,
            alreadyRaytracing = ModuleKillAura.isVCorePitchRaytracingTarget(target, currentRotation),
            aimedPitchStep = VCORE_ELYTRA_AIMED_PITCH_STEP,
            maxPitchStep = VCORE_ELYTRA_MAX_PITCH_STEP,
            pitchAccelerate = VCORE_ELYTRA_PITCH_ACCELERATE,
        ).also(vCorePitchState::updatePitchAcceleration)

        val fastElytraPitch = usesVCoreFastElytraPitch(
            localPlayerFallFlying = localPlayer.isFallFlying,
            localFallFlyingTicks = localPlayer.fallFlyingTicks,
        )
        val pitchStep = resolveVCoreTrackPitchStep(
            pitchAcceleration = pitchAcceleration,
            pitchJitter = if (fastElytraPitch) 0.0f else (-1.0f..1.0f).random(),
            localPlayerFallFlying = localPlayer.isFallFlying,
            localFallFlyingTicks = localPlayer.fallFlyingTicks,
        )

        return preserveLinearYawWithVCorePitch(
            linearRotation = linearRotation,
            currentRotation = currentRotation,
            targetRotation = targetRotation,
            pitchStep = pitchStep,
        )
    }

}

internal fun calculateLinearRotation(
    currentRotation: Rotation,
    targetRotation: Rotation,
    horizontalFactor: Float,
    verticalFactor: Float,
): Rotation = currentRotation.towardsLinear(targetRotation, horizontalFactor, verticalFactor)

internal fun shouldUseVCoreElytraPitch(
    linearIsSelected: Boolean,
    killAuraOwnsRotation: Boolean,
    targetIsCurrent: Boolean,
    targetValid: Boolean,
    elytraTarget: Boolean,
    peakAssistApplied: Boolean,
): Boolean = linearIsSelected && killAuraOwnsRotation && targetIsCurrent && targetValid &&
    (elytraTarget || peakAssistApplied)

internal fun preserveLinearYawWithVCorePitch(
    linearRotation: Rotation,
    currentRotation: Rotation,
    targetRotation: Rotation,
    pitchStep: Float,
): Rotation {
    val currentPitch = currentRotation.pitch.takeIf(Float::isFinite)?.coerceIn(-90.0f, 90.0f) ?: 0.0f
    val safePitchStep = pitchStep.takeIf(Float::isFinite)?.coerceAtLeast(0.0f) ?: 0.0f
    val pitchDelta = (targetRotation.pitch - currentPitch).takeIf(Float::isFinite) ?: 0.0f
    val newPitch = (currentPitch + Mth.clamp(pitchDelta, -safePitchStep, safePitchStep))
        .coerceIn(-90.0f, 90.0f)

    return Rotation(linearRotation.yaw, newPitch)
}

internal class LinearVCorePitchState {
    private var targetIdentity: Any? = null
    private var playerIdentity: Any? = null
    private var worldIdentity: Any? = null

    var pitchAcceleration = VCORE_ELYTRA_AIMED_PITCH_STEP
        private set

    fun enter(targetIdentity: Any, playerIdentity: Any, worldIdentity: Any) {
        if (this.targetIdentity !== targetIdentity || this.playerIdentity !== playerIdentity ||
            this.worldIdentity !== worldIdentity) {
            reset()
        }

        this.targetIdentity = targetIdentity
        this.playerIdentity = playerIdentity
        this.worldIdentity = worldIdentity
    }

    fun updatePitchAcceleration(value: Float) {
        pitchAcceleration = value
    }

    fun reset() {
        targetIdentity = null
        playerIdentity = null
        worldIdentity = null
        pitchAcceleration = VCORE_ELYTRA_AIMED_PITCH_STEP
    }
}
