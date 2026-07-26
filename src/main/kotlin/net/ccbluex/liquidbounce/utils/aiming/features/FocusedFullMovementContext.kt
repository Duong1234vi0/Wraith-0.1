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
package net.ccbluex.liquidbounce.utils.aiming.features

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleAutoCart
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.movement.ModuleSprint
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.combat.CombatManager
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.client.player.KeyboardInput
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import kotlin.math.hypot

/**
 * One immutable Focused Full input/rotation snapshot for one LocalPlayer movement cycle.
 *
 * Minecraft.tick() fires Wraith's GameTickEvent before level.tickEntities(). RotationManager
 * therefore commits the Aura rotation before LocalPlayer.aiStep() calls KeyboardInput.tick().
 * This snapshot is latched there, before sprint/local travel, and is released only after
 * LocalPlayer.sendPosition(). It intentionally owns the active RotationTarget entity instead
 * of KillAura's mutable attack target.
 */
data class FocusedFullMovementContext(
    val cycleSerial: Long,
    val playerTick: Int,
    val providerIdentity: String,
    val movementCorrection: MovementCorrection,
    val rotationTargetIdentity: Int,
    val targetEntityId: Int,
    val targetPosition: Vec3,
    val desiredTargetYaw: Float,
    val movementRotation: Rotation,
    val packetRotation: Rotation,
    val rawPhysicalInput: DirectionalInput,
    val postMovementEventInput: DirectionalInput,
    val correctedInput: DirectionalInput,
    val serverFacingSprint: Boolean,
    val swimming: Boolean,
    val diagnosticRelevant: Boolean,
    val active: Boolean,
    val resetReason: String?,
)

object FocusedFullMovementContextManager : EventListener {

    private const val PREFIX = "[FocusedFullDiag]"
    private const val MAX_CYCLES = 200
    private const val MAX_DURATION_MS = 30_000L

    private var serial = 0L
    private var context: FocusedFullMovementContext? = null
    private var sessionStartedAt = 0L
    private var sessionActive = false
    private var summaryLogged = false
    private var cycles = 0
    private var targetAcquisitions = 0
    private var targetSwitches = 0
    private var ownershipMismatches = 0
    private var rawLocalSilentPacketMismatches = 0
    private var preventedMixedFallbacks = 0
    private var sprintIllegalAttempts = 0
    private var legalFallbacks = 0
    private var maximumModeledDifference = 0.0
    private var maximumDeltaDiscontinuity = 0.0
    private var previousHorizontalDelta = 0.0
    private var velocityYawUsed: Float? = null
    private var jumpYawUsed: Float? = null
    private var expectedAcceleration: Vec3? = null
    private var pendingTargetChange: TargetChange? = null
    private var inputSprintDecision: Boolean? = null
    private var networkSprintDecision: Boolean? = null

    private data class TargetChange(val oldId: Int?, val newId: Int?, val source: String, val afterLatch: Boolean)

    @JvmStatic
    fun beginSession() {
        finishSummary("new-session")
        sessionActive = true
        summaryLogged = false
        sessionStartedAt = System.currentTimeMillis()
        cycles = 0
        targetAcquisitions = 0
        targetSwitches = 0
        ownershipMismatches = 0
        rawLocalSilentPacketMismatches = 0
        preventedMixedFallbacks = 0
        sprintIllegalAttempts = 0
        legalFallbacks = 0
        maximumModeledDifference = 0.0
        maximumDeltaDiscontinuity = 0.0
        previousHorizontalDelta = 0.0
        velocityYawUsed = null
        jumpYawUsed = null
        expectedAcceleration = null
        pendingTargetChange = null
        inputSprintDecision = null
        networkSprintDecision = null
        context = null
    }

    @JvmStatic
    fun endSession(reason: String) {
        context = null
        velocityYawUsed = null
        jumpYawUsed = null
        expectedAcceleration = null
        inputSprintDecision = null
        networkSprintDecision = null
        finishSummary(reason)
        sessionActive = false
    }

    @JvmStatic
    fun noteTargetChange(oldId: Int?, newId: Int?, source: String) {
        if (oldId == newId) return
        if (oldId == null && newId != null) targetAcquisitions++ else if (oldId != null && newId != null) targetSwitches++
        pendingTargetChange = TargetChange(oldId, newId, source, context != null)
        if (context != null && withinBudget()) {
            logger.warn("$PREFIX cycle=${context?.cycleSerial} warning=target-changed-after-input-latch old=$oldId new=$newId source=$source")
        }
    }

    /** Returns null when this cycle must remain entirely vanilla/raw. */
    @JvmStatic
    fun latch(
        rawPhysicalInput: DirectionalInput,
        postMovementEventInput: DirectionalInput,
        physicalSprintInput: Boolean,
    ): DirectionalInput? {
        val previousContext = context
        if (previousContext != null && withinBudget()) {
            logger.warn(
                "$PREFIX cycle=${previousContext.cycleSerial} warning=selected-input-changed-more-than-once"
            )
        }
        context = null
        val player = mc.player ?: return null
        val rotationTarget = RotationManager.activeRotationTarget ?: return null
        if (rotationTarget.movementCorrection != MovementCorrection.FOCUSED_FULL) return null

        val rotation = RotationManager.currentRotation
        val target = rotationTarget.entity
        val invalidReason = when {
            !ModuleKillAura.running -> "killaura-disabled"
            rotationTarget.provider !== ModuleKillAura -> "provider-not-killaura"
            rotation == null || !rotation.yaw.isFinite() || !rotation.pitch.isFinite() -> "invalid-rotation"
            target == null || target.isRemoved -> "missing-rotation-target"
            target is LivingEntity && target.isDeadOrDying -> "dead-rotation-target"
            player.isPassenger -> "passenger"
            player.isDeadOrDying -> "dead"
            ModuleAutoCart.isAutoCartMoveFixActive() -> "autocart-owns-movement"
            CombatManager.shouldPauseCombat -> "combat-paused"
            CombatManager.shouldPauseRotation -> "rotation-paused"
            else -> null
        }
        if (invalidReason != null) {
            warnMixedFallbackIfNeeded(invalidReason, rotation)
            return null
        }

        if (!sessionActive) beginSession()
        serial++
        cycles++
        velocityYawUsed = null
        jumpYawUsed = null
        expectedAcceleration = null
        inputSprintDecision = null
        networkSprintDecision = null

        val forward = KeyboardInput.calculateImpulse(
            postMovementEventInput.forwards,
            postMovementEventInput.backwards,
        )
        val strafe = KeyboardInput.calculateImpulse(postMovementEventInput.left, postMovementEventInput.right)
        val targetPosition = target!!.position()
        val desiredYaw = FocusedFullMoveFix.quantizedTargetYaw(
            player.x,
            player.eyeY,
            player.z,
            targetPosition.x,
            targetPosition.y,
            targetPosition.z,
            mc.options.sensitivity().get(),
        )

        // A non-forward result makes vanilla and Sprint Legit stop Sprint before local travel
        // and before sendPosition() emits STOP, so it remains legal. Only a forward-bearing
        // vanilla request or a ModuleSprint mode that actively keeps/requests Sprint requires
        // Grim's forward-only candidate set.
        val unrestricted = FocusedFullMoveFix.selectInput(rotation!!.yaw, desiredYaw, forward, strafe)
        val unrestrictedInput = DirectionalInput(unrestricted[0], unrestricted[1])
        val forwardBearing = unrestrictedInput.forwards && !unrestrictedInput.backwards
        val vanillaSprintWillRemainOrStart = forwardBearing && (player.isSprinting || physicalSprintInput)
        val moduleSprintWillRemainOrStart = ModuleSprint.willRequestSprintFor(unrestrictedInput) &&
            !ModuleKillAura.shouldBlockSprinting
        val sprinting = unrestrictedInput.isMoving && !player.isSwimming &&
            (vanillaSprintWillRemainOrStart || moduleSprintWillRemainOrStart)
        val selected = FocusedFullMoveFix.selectInput(rotation.yaw, desiredYaw, forward, strafe, sprinting)
        val corrected = DirectionalInput(selected[0], selected[1])
        if (sprinting && !FocusedFullMoveFix.isSprintLegal(selected[0], selected[1])) {
            sprintIllegalAttempts++
        }
        if (sprinting && unrestrictedInput != corrected) {
            legalFallbacks++
        }

        val trackerId = ModuleKillAura.targetTracker.target?.id
        if (trackerId != null && trackerId != target.id) {
            ownershipMismatches++
            if (withinBudget()) {
                logger.warn(
                    "$PREFIX cycle=$serial warning=tracker-active-rotation-target-mismatch " +
                        "tracker=$trackerId active=${target.id}"
                )
            }
        }

        val rotationSnapshot = Rotation(rotation.yaw, rotation.pitch, rotation.isNormalized)
        context = FocusedFullMovementContext(
            cycleSerial = serial,
            playerTick = player.tickCount,
            providerIdentity = rotationTarget.provider?.name ?: "unknown",
            movementCorrection = rotationTarget.movementCorrection,
            rotationTargetIdentity = System.identityHashCode(rotationTarget),
            targetEntityId = target.id,
            targetPosition = targetPosition,
            desiredTargetYaw = desiredYaw,
            movementRotation = rotationSnapshot,
            packetRotation = rotationSnapshot.copy(),
            rawPhysicalInput = rawPhysicalInput,
            postMovementEventInput = postMovementEventInput,
            correctedInput = corrected,
            serverFacingSprint = sprinting,
            swimming = player.isSwimming,
            diagnosticRelevant = postMovementEventInput.isMoving || pendingTargetChange != null,
            active = true,
            resetReason = null,
        )

        if (withinBudget() && (postMovementEventInput.isMoving || pendingTargetChange != null)) {
            val change = pendingTargetChange
            val mismatch = if (trackerId != null && trackerId != target.id) "tracker-active-target" else "none"
            logger.info(
                "$PREFIX cycle=$serial tick=${player.tickCount} raw=$rawPhysicalInput post=$postMovementEventInput " +
                    "selected=$corrected sprintLocal=${player.isSprinting} sprintExpected=$sprinting " +
                    "physicalSprint=$physicalSprintInput moduleSprint=${ModuleSprint.diagnosticState()} " +
                    "visualYaw=${player.yRot} currentYaw=${rotation.yaw} actualServerYaw=${RotationManager.actualServerRotation.yaw} " +
                    "movementYaw=${rotationSnapshot.yaw} packetYaw=${rotationSnapshot.yaw} target=${target.id} tracker=$trackerId " +
                    "provider=${rotationTarget.provider?.name} correction=${rotationTarget.movementCorrection} active=true " +
                    "targetChanged=${change != null} old=${change?.oldId} new=${change?.newId} source=${change?.source} " +
                    "afterLatch=${change?.afterLatch} desiredYaw=$desiredYaw worldAngle=" +
                    FocusedFullMoveFix.directionDegrees(rotationSnapshot.yaw, selected[0], selected[1]) +
                    " inputBits=${inputBits(corrected)} mismatch=$mismatch"
            )
        }
        pendingTargetChange = null
        exhaustIfNeeded()
        return corrected
    }

    @JvmStatic
    fun observeInputSprintDecision(outgoingSprint: Boolean) {
        if (context != null) {
            inputSprintDecision = outgoingSprint
        }
    }

    /** Value read by LocalPlayer.sendIsSprintingIfNeeded() before its START/STOP command. */
    @JvmStatic
    fun observeNetworkSprintDecision(outgoingSprint: Boolean) {
        val current = context ?: return
        networkSprintDecision = outgoingSprint
        if (outgoingSprint && !current.swimming && !current.correctedInput.forwards) {
            sprintIllegalAttempts++
            if (withinBudget()) {
                logger.warn(
                    "$PREFIX cycle=${current.cycleSerial} warning=sprint-true-without-forward " +
                        "selected=${current.correctedInput}"
                )
            }
        }
    }

    @JvmStatic
    fun activeContext(): FocusedFullMovementContext? = context

    @JvmStatic
    fun isActive(): Boolean = context?.active == true

    @JvmStatic
    fun movementYaw(): Float? = context?.movementRotation?.yaw

    @JvmStatic
    fun observeVelocity(yaw: Float, acceleration: Vec3) {
        val current = context ?: return
        velocityYawUsed = yaw
        expectedAcceleration = acceleration
        if (kotlin.math.abs(yaw - current.movementRotation.yaw) > 1.0E-4f && withinBudget()) {
            rawLocalSilentPacketMismatches++
            logger.warn("$PREFIX cycle=${current.cycleSerial} warning=keyboard-velocity-yaw-mismatch keyboardYaw=${current.movementRotation.yaw} velocityYaw=$yaw")
        }
        if (kotlin.math.abs(yaw - current.packetRotation.yaw) > 1.0E-4f && withinBudget()) {
            rawLocalSilentPacketMismatches++
            logger.warn("$PREFIX cycle=${current.cycleSerial} warning=velocity-packet-yaw-mismatch velocityYaw=$yaw packetYaw=${current.packetRotation.yaw}")
        }
    }

    @JvmStatic
    fun jumpYaw(): Float? = context?.movementRotation?.yaw

    @JvmStatic
    fun observeJumpYaw(yaw: Float) {
        jumpYawUsed = yaw
    }

    @JvmStatic
    fun packetRotation(): Rotation? = context?.packetRotation

    @JvmStatic
    fun observePacket(packet: Any) {
        val current = context ?: return
        when (packet) {
            is ServerboundPlayerInputPacket -> if (withinBudget()) {
                val input = DirectionalInput(packet.input())
                if (input != current.correctedInput) {
                    rawLocalSilentPacketMismatches++
                    logger.warn("$PREFIX cycle=${current.cycleSerial} warning=local-packet-input-split local=${current.correctedInput} packet=$input")
                }
            }
            is ServerboundMovePlayerPacket -> logMovementPacket(current, packet)
        }
    }

    @JvmStatic
    fun completeCycle() {
        context = null
        velocityYawUsed = null
        jumpYawUsed = null
        expectedAcceleration = null
        inputSprintDecision = null
        networkSprintDecision = null
        exhaustIfNeeded()
    }

    private fun logMovementPacket(current: FocusedFullMovementContext, packet: ServerboundMovePlayerPacket) {
        if (!withinBudget() || !current.diagnosticRelevant) return
        val player = mc.player ?: return
        val packetYaw = if (packet.hasRot) packet.yRot else RotationManager.actualServerRotation.yaw
        if (kotlin.math.abs(packetYaw - current.packetRotation.yaw) > 1.0E-4f) {
            rawLocalSilentPacketMismatches++
            logger.warn("$PREFIX cycle=${current.cycleSerial} warning=velocity-packet-yaw-mismatch movementYaw=${current.movementRotation.yaw} packetYaw=$packetYaw")
        }

        val forward = impulse(current.correctedInput.forwards, current.correctedInput.backwards)
        val strafe = impulse(current.correctedInput.left, current.correctedInput.right)
        val packetExpected = unitAcceleration(packetYaw, forward, strafe)
        val localExpected = unitAcceleration(velocityYawUsed ?: current.movementRotation.yaw, forward, strafe)
        val accelerationScale = expectedAcceleration?.let { hypot(it.x, it.z) } ?: 1.0
        maximumModeledDifference = maxOf(
            maximumModeledDifference,
            packetExpected.distanceTo(localExpected) * accelerationScale,
        )
        val horizontalDelta = hypot(player.x - player.xo, player.z - player.zo)
        maximumDeltaDiscontinuity = maxOf(maximumDeltaDiscontinuity, kotlin.math.abs(horizontalDelta - previousHorizontalDelta))
        previousHorizontalDelta = horizontalDelta

        logger.info(
            "$PREFIX cycle=${current.cycleSerial} packet=${packet.javaClass.simpleName} packetYaw=$packetYaw " +
                "velocityYaw=$velocityYawUsed jumpYaw=$jumpYawUsed outgoingSprint=${player.isSprinting} " +
                "inputSprint=$inputSprintDecision networkSprint=$networkSprintDecision " +
                "inputBits=${inputBits(current.correctedInput)} actualDeltaX=${player.x - player.xo} " +
                "actualDeltaZ=${player.z - player.zo} expectedAccelX=${expectedAcceleration?.x} " +
                "expectedAccelZ=${expectedAcceleration?.z}"
        )
    }

    private fun warnMixedFallbackIfNeeded(reason: String, rotation: Rotation?) {
        if (rotation == null || !sessionActive || !withinBudget()) return
        preventedMixedFallbacks++
        logger.warn("$PREFIX warning=packet-yaw-exists-while-focused-local-disabled reason=$reason currentYaw=${rotation.yaw}")
    }

    private fun unitAcceleration(yaw: Float, forward: Float, strafe: Float): Vec3 {
        if (forward == 0.0f && strafe == 0.0f) return Vec3.ZERO
        val length = hypot(forward.toDouble(), strafe.toDouble()).coerceAtLeast(1.0)
        val normalizedForward = forward / length
        val normalizedStrafe = strafe / length
        val radians = Math.toRadians(yaw.toDouble())
        val sin = kotlin.math.sin(radians)
        val cos = kotlin.math.cos(radians)
        return Vec3(normalizedStrafe * cos - normalizedForward * sin, 0.0, normalizedForward * cos + normalizedStrafe * sin)
    }

    private fun inputBits(input: DirectionalInput): String = buildString {
        if (input.forwards) append('F')
        if (input.backwards) append('B')
        if (input.left) append('L')
        if (input.right) append('R')
        if (isEmpty()) append('-')
    }

    private fun impulse(positive: Boolean, negative: Boolean): Float = when {
        positive == negative -> 0.0f
        positive -> 1.0f
        else -> -1.0f
    }

    private fun withinBudget(): Boolean = sessionActive && !summaryLogged &&
        cycles <= MAX_CYCLES && System.currentTimeMillis() - sessionStartedAt <= MAX_DURATION_MS

    private fun exhaustIfNeeded() {
        if (sessionActive && !summaryLogged && !withinBudget()) finishSummary("budget-exhausted")
    }

    private fun finishSummary(reason: String) {
        if (!sessionActive || summaryLogged) return
        summaryLogged = true
        logger.info(
            "$PREFIX summary reason=$reason cycles=$cycles acquisitions=$targetAcquisitions switches=$targetSwitches " +
                "ownershipMismatches=$ownershipMismatches rawLocalSilentPacketMismatches=$rawLocalSilentPacketMismatches " +
                "preventedMixedFallbacks=$preventedMixedFallbacks " +
                "sprintIllegalAttempts=$sprintIllegalAttempts legalFallbacks=$legalFallbacks " +
                "maxModeledAccelerationDifference=$maximumModeledDifference " +
                "maxHorizontalDeltaDiscontinuity=$maximumDeltaDiscontinuity"
        )
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> { endSession("world-change") }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> { endSession("disconnect") }
}
