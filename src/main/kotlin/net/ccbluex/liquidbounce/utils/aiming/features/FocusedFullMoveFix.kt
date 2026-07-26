package net.ccbluex.liquidbounce.utils.aiming.features

import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Pure helpers for the V-Core MoveFix.Mode.Full input path.
 *
 * Keep the intentionally unwrapped final subtraction: V-Core wraps each
 * direction independently, then compares the two wrapped values directly.
 */
object FocusedFullMoveFix {

    @JvmStatic
    fun quantizedTargetYaw(
        playerX: Double,
        playerEyeY: Double,
        playerZ: Double,
        targetX: Double,
        targetY: Double,
        targetZ: Double,
        mouseSensitivity: Double,
    ): Float {
        val deltaX = targetX - playerX
        val deltaY = targetY - playerEyeY
        val deltaZ = targetZ - playerZ
        val horizontalDistance = sqrt(deltaX * deltaX + deltaZ * deltaZ)
        var yaw = Math.toDegrees(atan2(deltaZ, deltaX)).toFloat() - 90.0f
        val pitch = -(Math.toDegrees(atan2(deltaY, horizontalDistance))).toFloat()
        val sensitivity = mouseSensitivity.pow(1.5).toFloat() * 0.05f + 0.1f
        val gcd = sensitivity * sensitivity * sensitivity * 1.2f

        yaw -= yaw % gcd
        // Preserve V-Core's pitch calculation and quantization even though
        // this source-shaped helper returns only the target yaw.
        @Suppress("UNUSED_VARIABLE")
        val quantizedPitch = pitch - pitch % (gcd * sensitivity)
        quantizedPitch
        return yaw
    }

    @JvmStatic
    fun selectInput(
        activeRotationYaw: Float,
        quantizedTargetYaw: Float,
        forward: Float,
        strafe: Float,
        sprinting: Boolean = false,
    ): FloatArray {
        if (forward == 0.0f && strafe == 0.0f) {
            return floatArrayOf(0.0f, 0.0f)
        }

        val desiredAngle = wrapDegrees(Math.toDegrees(direction(quantizedTargetYaw, forward, strafe)))
        var closestForward = 0.0f
        var closestStrafe = 0.0f
        var closestDifference = Float.MAX_VALUE

        for (candidate in candidateInputs(sprinting)) {
            val predictedAngle = wrapDegrees(
                Math.toDegrees(direction(activeRotationYaw, candidate[0], candidate[1]))
            )
            val difference = abs((desiredAngle - predictedAngle).toFloat())
            if (difference < closestDifference) {
                closestDifference = difference
                closestForward = candidate[0]
                closestStrafe = candidate[1]
            }
        }

        return floatArrayOf(closestForward, closestStrafe)
    }

    @JvmStatic
    fun candidateInputs(sprinting: Boolean = false): Array<FloatArray> = buildList {
        for (forward in -1..1) {
            for (strafe in -1..1) {
                if (forward != 0 || strafe != 0) {
                    if (sprinting && forward != 1) {
                        continue
                    }
                    add(floatArrayOf(forward.toFloat(), strafe.toFloat()))
                }
            }
        }
    }.toTypedArray()

    @JvmStatic
    fun isSprintLegal(forward: Float, strafe: Float): Boolean =
        forward > 0.0f && strafe in -1.0f..1.0f

    @JvmStatic
    fun directionDegrees(rotationYaw: Float, forward: Float, strafe: Float): Double =
        Math.toDegrees(direction(rotationYaw, forward, strafe))

    @JvmStatic
    fun unwrappedDifference(firstWrappedAngle: Double, secondWrappedAngle: Double): Float =
        abs((firstWrappedAngle - secondWrappedAngle).toFloat())

    private fun direction(rotationYaw: Float, moveForward: Float, moveStrafing: Float): Double {
        var yaw = rotationYaw
        if (moveForward < 0.0f) {
            yaw += 180.0f
        }

        val forward = when {
            moveForward < 0.0f -> -0.5f
            moveForward > 0.0f -> 0.5f
            else -> 1.0f
        }

        if (moveStrafing > 0.0f) {
            yaw -= 90.0f * forward
        }
        if (moveStrafing < 0.0f) {
            yaw += 90.0f * forward
        }

        return Math.toRadians(yaw.toDouble())
    }

    private fun wrapDegrees(value: Double): Double {
        var wrapped = value % 360.0
        if (wrapped >= 180.0) wrapped -= 360.0
        if (wrapped < -180.0) wrapped += 360.0
        return wrapped
    }
}
