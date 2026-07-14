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

@Suppress("LongParameterList")
data class PacketRotationSample(
    val schemaVersion: Int,
    val sessionId: String,
    val segmentId: Int,
    val sampleIndex: Int,

    val wallTimeMillis: Long,
    val elapsedNanos: Long,
    val intervalNanos: Long,
    val clientTick: Long,
    val ticksSinceAttack: Int,

    val packetType: String,
    val hasPosition: Boolean,
    val packetX: Double?,
    val packetY: Double?,
    val packetZ: Double?,
    val yaw: Float,
    val pitch: Float,
    val onGround: Boolean,
    val horizontalCollision: Boolean,

    val deltaYaw: Float,
    val deltaPitch: Float,
    val accelYaw: Float,
    val accelPitch: Float,
    val jerkYaw: Float,
    val jerkPitch: Float,

    val rotationControllerActive: Boolean,
    val cameraYaw: Float,
    val cameraPitch: Float,

    val playerX: Double,
    val playerY: Double,
    val playerZ: Double,
    val playerVelocityX: Double,
    val playerVelocityY: Double,
    val playerVelocityZ: Double,
    val playerOnGround: Boolean,
    val playerHorizontalCollision: Boolean,
    val playerFallFlying: Boolean,
    val playerSprinting: Boolean,
    val playerCrouching: Boolean,

    val targetEntityId: Int?,
    val targetX: Double?,
    val targetY: Double?,
    val targetZ: Double?,
    val targetVelocityX: Double?,
    val targetVelocityY: Double?,
    val targetVelocityZ: Double?,
    val targetEyeX: Double?,
    val targetEyeY: Double?,
    val targetEyeZ: Double?,
    val targetBoxMinX: Double?,
    val targetBoxMinY: Double?,
    val targetBoxMinZ: Double?,
    val targetBoxMaxX: Double?,
    val targetBoxMaxY: Double?,
    val targetBoxMaxZ: Double?,
    val distanceToTarget: Double?,
    val crosshairOnTarget: Boolean?
)
