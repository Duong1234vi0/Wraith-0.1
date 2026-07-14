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
package net.ccbluex.liquidbounce.features.module.modules.misc.debugrecorder.modes

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.misc.debugrecorder.ModuleDebugRecorder
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.warning
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.deltaMovement
import net.ccbluex.liquidbounce.utils.entity.eyePos
import net.ccbluex.liquidbounce.utils.entity.pitch
import net.ccbluex.liquidbounce.utils.entity.pos
import net.ccbluex.liquidbounce.utils.entity.yaw
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.READ_FINAL_STATE
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
import net.minecraft.network.protocol.game.ServerboundAttackPacket
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.EntityHitResult
import java.util.TreeMap
import java.util.UUID

object PacketRotationRecorder : ModuleDebugRecorder.DebugRecorderMode<PacketRotationSample>("PacketRotation") {

    private const val SCHEMA_VERSION = 1
    private const val PENDING_ATTACK_MATCH_WINDOW_TICKS = 2

    private val manualOnly by boolean("ManualOnly", true)
    private val combatWindow by int("CombatWindow", 40, 1..200, "ticks")
    private val maxSamples by int("MaxSamples", 50000, 1000..500000)

    override val exportGson: Gson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .create()

    private var sessionId = ""
    private var sampleIndex = 0
    private var maxSamplesWarningShown = false
    private var enableNanoTime = 0L
    private var lastSampleNanoTime = 0L
    private var clientTick = 0L

    private var ticksSinceAttack = Int.MAX_VALUE
    private var latestTargetEntityId: Int? = null
    private var pendingAttackTargetEntityId: Int? = null
    private var pendingAttackAgeTicks = 0

    private var segmentId = 0
    private var segmentHasSamples = false
    private var featureGeneration = 0L

    private var hasLastRotation = false
    private var lastYaw = 0f
    private var lastPitch = 0f
    private var lastDeltaYaw = 0f
    private var lastDeltaPitch = 0f
    private var lastYawAcceleration = 0f
    private var lastPitchAcceleration = 0f
    private var currentYawAcceleration = 0f
    private var currentPitchAcceleration = 0f

    private val captureLock = Any()
    private val pendingCaptures = TreeMap<Long, PacketCapture>()
    private var nextCaptureSequence = 0L
    private var nextDrainSequence = 0L
    private var drainScheduled = false

    private val inCombatWindow: Boolean
        get() = ticksSinceAttack <= combatWindow

    override fun enable() {
        super.enable()
        sessionId = UUID.randomUUID().toString()
        sampleIndex = 0
        maxSamplesWarningShown = false
        enableNanoTime = System.nanoTime()
        lastSampleNanoTime = 0L
        clientTick = 0L
        segmentId = 0
        segmentHasSamples = false
        featureGeneration = 0L
        resetCombatState()
        resetFeatureState(startNewSegment = false)
        clearPendingCaptures()
    }

    override fun disable() {
        super.disable()
        sessionId = ""
        sampleIndex = 0
        maxSamplesWarningShown = false
        enableNanoTime = 0L
        lastSampleNanoTime = 0L
        clientTick = 0L
        resetCombatState()
        resetFeatureState(startNewSegment = false)
        clearPendingAttack()
        clearPendingCaptures()
    }

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent>(priority = READ_FINAL_STATE) { event ->
        if (event.isCancelled) {
            return@handler
        }

        val attackedPlayer = event.entity as? Player ?: return@handler
        if (mc.player == null || mc.level == null) {
            clearPendingAttack()
            return@handler
        }

        pendingAttackTargetEntityId = attackedPlayer.id
        pendingAttackAgeTicks = 0
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        clientTick++

        val wasInCombat = inCombatWindow
        if (ticksSinceAttack <= combatWindow + 1) {
            ticksSinceAttack++
        }

        if (wasInCombat && !inCombatWindow) {
            resetFeatureState(startNewSegment = true)
        }

        if (pendingAttackTargetEntityId != null) {
            pendingAttackAgeTicks++
            if (pendingAttackAgeTicks > PENDING_ATTACK_MATCH_WINDOW_TICKS) {
                clearPendingAttack()
            }
        }
    }

    @Suppress("unused")
    private val sentPacketHandler = handler<OutgoingPacketSentEvent> { event ->
        when (val packet = event.packet) {
            is ServerboundAttackPacket -> handleSentAttackPacket(packet)
            is ServerboundMovePlayerPacket -> {
                if (!packet.hasRotation()) {
                    return@handler
                }

                enqueueCapture(PacketCapture.from(packet, createSendSnapshot()))
            }
        }
    }

    @Suppress("unused")
    private val teleportHandler = handler<PacketEvent>(priority = READ_FINAL_STATE) { event ->
        if (event.isCancelled || event.packet !is ClientboundPlayerPositionPacket) {
            return@handler
        }

        resetFeatureState(startNewSegment = true)
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetCombatState()
        resetFeatureState(startNewSegment = true)
        clearPendingAttack()
        clearPendingCaptures()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        resetCombatState()
        resetFeatureState(startNewSegment = true)
        clearPendingAttack()
        clearPendingCaptures()
    }

    private fun handleSentAttackPacket(packet: ServerboundAttackPacket) {
        val level = mc.level
        if (mc.player == null || level == null) {
            clearPendingAttack()
            return
        }

        val targetId = packet.entityId()
        val target = level.getEntity(targetId) as? Player ?: return

        if (!inCombatWindow) {
            resetFeatureState(startNewSegment = true)
        }

        ticksSinceAttack = 0
        latestTargetEntityId = target.id
        clearPendingAttack()
    }

    private fun createSendSnapshot() = SendSnapshot(
        combatActive = inCombatWindow,
        ticksSinceAttack = ticksSinceAttack,
        latestTargetEntityId = latestTargetEntityId,
        rotationControllerActive = RotationManager.currentRotation != null ||
            RotationManager.activeRotationTarget != null,
        clientTick = clientTick,
        segmentId = segmentId,
        featureGeneration = featureGeneration
    )

    private fun enqueueCapture(capture: PacketCapture) {
        synchronized(captureLock) {
            pendingCaptures[nextCaptureSequence] = capture.copy(sequence = nextCaptureSequence)
            nextCaptureSequence++
        }

        if (mc.isSameThread) {
            drainPendingCaptures()
        } else {
            scheduleDrain()
        }
    }

    private fun scheduleDrain() {
        val shouldSchedule = synchronized(captureLock) {
            if (drainScheduled) {
                false
            } else {
                drainScheduled = true
                true
            }
        }

        if (shouldSchedule) {
            mc.execute(::drainPendingCaptures)
        }
    }

    private fun drainPendingCaptures() {
        if (!mc.isSameThread) {
            scheduleDrain()
            return
        }

        while (true) {
            val capture = synchronized(captureLock) {
                val next = pendingCaptures.remove(nextDrainSequence)
                if (next == null) {
                    drainScheduled = false
                } else {
                    nextDrainSequence++
                }
                next
            } ?: return

            processCapture(capture)
        }
    }

    private fun processCapture(capture: PacketCapture) {
        val localPlayer = mc.player
        val level = mc.level
        if (localPlayer == null || level == null) {
            resetCombatState()
            resetFeatureState(startNewSegment = true)
            return
        }

        if (capture.featureGeneration != featureGeneration) {
            return
        }

        if (!capture.combatActive) {
            return
        }

        if (manualOnly && capture.rotationControllerActive) {
            resetFeatureState(startNewSegment = true, forceSegmentIncrement = true)
            return
        }

        if (sampleIndex >= maxSamples) {
            if (!maxSamplesWarningShown) {
                chat(warning("PacketRotation reached MaxSamples=$maxSamples; no further samples will be recorded."))
                maxSamplesWarningShown = true
            }
            resetFeatureState(startNewSegment = true)
            return
        }

        val now = capture.nanoTime
        val elapsedNanos = now - enableNanoTime
        val intervalNanos = if (lastSampleNanoTime == 0L) {
            0L
        } else {
            now - lastSampleNanoTime
        }

        val features = calculateFeatures(capture.yaw, capture.pitch)
        val playerVelocity = localPlayer.deltaMovement
        val target = capture.latestTargetEntityId?.let { level.getEntity(it) as? Player }
        val targetVelocity = target?.deltaMovement
        val targetEye = target?.eyePos
        val targetBox = target?.boundingBox
        val crosshairEntity = (mc.crosshairTarget as? EntityHitResult)?.entity

        recordPacket(
            PacketRotationSample(
                schemaVersion = SCHEMA_VERSION,
                sessionId = sessionId,
                segmentId = capture.segmentId,
                sampleIndex = sampleIndex,

                wallTimeMillis = capture.wallTimeMillis,
                elapsedNanos = elapsedNanos,
                intervalNanos = intervalNanos,
                clientTick = capture.clientTick,
                ticksSinceAttack = capture.ticksSinceAttack,

                packetType = capture.packetType,
                hasPosition = capture.hasPosition,
                packetX = capture.x,
                packetY = capture.y,
                packetZ = capture.z,
                yaw = capture.yaw,
                pitch = capture.pitch,
                onGround = capture.onGround,
                horizontalCollision = capture.horizontalCollision,

                deltaYaw = features.deltaYaw,
                deltaPitch = features.deltaPitch,
                accelYaw = features.accelYaw,
                accelPitch = features.accelPitch,
                jerkYaw = features.jerkYaw,
                jerkPitch = features.jerkPitch,

                rotationControllerActive = capture.rotationControllerActive,
                cameraYaw = localPlayer.yaw,
                cameraPitch = localPlayer.pitch,

                playerX = localPlayer.x,
                playerY = localPlayer.y,
                playerZ = localPlayer.z,
                playerVelocityX = playerVelocity.x,
                playerVelocityY = playerVelocity.y,
                playerVelocityZ = playerVelocity.z,
                playerOnGround = localPlayer.onGround(),
                playerHorizontalCollision = localPlayer.horizontalCollision,
                playerFallFlying = localPlayer.isFallFlying,
                playerSprinting = localPlayer.isSprinting,
                playerCrouching = localPlayer.isCrouching,

                targetEntityId = target?.id,
                targetX = target?.x,
                targetY = target?.y,
                targetZ = target?.z,
                targetVelocityX = targetVelocity?.x,
                targetVelocityY = targetVelocity?.y,
                targetVelocityZ = targetVelocity?.z,
                targetEyeX = targetEye?.x,
                targetEyeY = targetEye?.y,
                targetEyeZ = targetEye?.z,
                targetBoxMinX = targetBox?.minX,
                targetBoxMinY = targetBox?.minY,
                targetBoxMinZ = targetBox?.minZ,
                targetBoxMaxX = targetBox?.maxX,
                targetBoxMaxY = targetBox?.maxY,
                targetBoxMaxZ = targetBox?.maxZ,
                distanceToTarget = target?.distanceTo(localPlayer)?.toDouble(),
                crosshairOnTarget = target?.let { crosshairEntity?.id == it.id }
            )
        )

        sampleIndex++
        segmentHasSamples = true
        lastSampleNanoTime = now
    }

    private fun calculateFeatures(yaw: Float, pitch: Float): RotationFeatures {
        if (!hasLastRotation) {
            lastYaw = yaw
            lastPitch = pitch
            lastDeltaYaw = 0f
            lastDeltaPitch = 0f
            lastYawAcceleration = 0f
            lastPitchAcceleration = 0f
            currentYawAcceleration = 0f
            currentPitchAcceleration = 0f
            hasLastRotation = true
            return RotationFeatures.ZERO
        }

        val deltaYaw = normalizeAngle(yaw - lastYaw)
        val deltaPitch = pitch - lastPitch

        lastYawAcceleration = currentYawAcceleration
        lastPitchAcceleration = currentPitchAcceleration

        currentYawAcceleration = deltaYaw - lastDeltaYaw
        currentPitchAcceleration = deltaPitch - lastDeltaPitch

        val jerkYaw = currentYawAcceleration - lastYawAcceleration
        val jerkPitch = currentPitchAcceleration - lastPitchAcceleration

        lastYaw = yaw
        lastPitch = pitch
        lastDeltaYaw = deltaYaw
        lastDeltaPitch = deltaPitch

        return RotationFeatures(
            deltaYaw = deltaYaw,
            deltaPitch = deltaPitch,
            accelYaw = currentYawAcceleration,
            accelPitch = currentPitchAcceleration,
            jerkYaw = jerkYaw,
            jerkPitch = jerkPitch
        )
    }

    private fun normalizeAngle(value: Float): Float {
        var angle = value
        while (angle > 180f) {
            angle -= 360f
        }
        while (angle < -180f) {
            angle += 360f
        }
        return angle
    }

    private fun resetCombatState() {
        ticksSinceAttack = combatWindow + 2
        latestTargetEntityId = null
        clearPendingAttack()
    }

    private fun resetFeatureState(startNewSegment: Boolean, forceSegmentIncrement: Boolean = false) {
        featureGeneration++

        if (startNewSegment && (segmentHasSamples || forceSegmentIncrement)) {
            segmentId++
        }

        hasLastRotation = false
        lastYaw = 0f
        lastPitch = 0f
        lastDeltaYaw = 0f
        lastDeltaPitch = 0f
        lastYawAcceleration = 0f
        lastPitchAcceleration = 0f
        currentYawAcceleration = 0f
        currentPitchAcceleration = 0f
        lastSampleNanoTime = 0L
        segmentHasSamples = false
    }

    private fun clearPendingAttack() {
        pendingAttackTargetEntityId = null
        pendingAttackAgeTicks = 0
    }

    private fun clearPendingCaptures() {
        synchronized(captureLock) {
            pendingCaptures.clear()
            nextCaptureSequence = 0L
            nextDrainSequence = 0L
            drainScheduled = false
        }
    }

    private data class PacketCapture(
        val sequence: Long,
        val wallTimeMillis: Long,
        val nanoTime: Long,
        val combatActive: Boolean,
        val ticksSinceAttack: Int,
        val latestTargetEntityId: Int?,
        val rotationControllerActive: Boolean,
        val clientTick: Long,
        val segmentId: Int,
        val featureGeneration: Long,
        val packetType: String,
        val hasPosition: Boolean,
        val x: Double?,
        val y: Double?,
        val z: Double?,
        val yaw: Float,
        val pitch: Float,
        val onGround: Boolean,
        val horizontalCollision: Boolean
    ) {
        companion object {
            fun from(packet: ServerboundMovePlayerPacket, snapshot: SendSnapshot): PacketCapture {
                val hasPosition = packet.hasPosition()

                return PacketCapture(
                    sequence = -1L,
                    wallTimeMillis = System.currentTimeMillis(),
                    nanoTime = System.nanoTime(),
                    combatActive = snapshot.combatActive,
                    ticksSinceAttack = snapshot.ticksSinceAttack,
                    latestTargetEntityId = snapshot.latestTargetEntityId,
                    rotationControllerActive = snapshot.rotationControllerActive,
                    clientTick = snapshot.clientTick,
                    segmentId = snapshot.segmentId,
                    featureGeneration = snapshot.featureGeneration,
                    packetType = "ServerboundMovePlayerPacket.${packet.javaClass.simpleName}",
                    hasPosition = hasPosition,
                    x = if (hasPosition) packet.x else null,
                    y = if (hasPosition) packet.y else null,
                    z = if (hasPosition) packet.z else null,
                    yaw = packet.yRot,
                    pitch = packet.xRot,
                    onGround = packet.isOnGround,
                    horizontalCollision = packet.horizontalCollision()
                )
            }
        }
    }

    private data class SendSnapshot(
        val combatActive: Boolean,
        val ticksSinceAttack: Int,
        val latestTargetEntityId: Int?,
        val rotationControllerActive: Boolean,
        val clientTick: Long,
        val segmentId: Int,
        val featureGeneration: Long
    )

    private data class RotationFeatures(
        val deltaYaw: Float,
        val deltaPitch: Float,
        val accelYaw: Float,
        val accelPitch: Float,
        val jerkYaw: Float,
        val jerkPitch: Float
    ) {
        companion object {
            val ZERO = RotationFeatures(0f, 0f, 0f, 0f, 0f, 0f)
        }
    }
}
