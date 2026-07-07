/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 */
@file:Suppress("All")

package net.ccbluex.liquidbounce.utils.entity

import net.ccbluex.liquidbounce.event.EventManager.callEvent
import net.ccbluex.liquidbounce.event.events.PlayerSafeWalkEvent
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.ccbluex.liquidbounce.utils.movement.getDegreesRelativeToView
import net.ccbluex.liquidbounce.utils.movement.getDirectionalInputForDegrees
import net.minecraft.util.Mth
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import kotlin.math.cos
import kotlin.math.sin

private const val WALK_ACCELERATION = 0.098
private const val SPRINT_ACCELERATION = 0.13
private const val GROUND_FRICTION = 0.6
private const val AIR_FRICTION = 0.91
private const val GRAVITY = 0.08

class SimulatedPlayer(
    private val player: Player,
    var input: SimulatedPlayerInput,
    override var pos: Vec3,
    var velocity: Vec3,
    private var boundingBox: AABB,
    var yaw: Float,
    var pitch: Float,
    private var sprinting: Boolean,
    var fallDistance: Float,
    private var jumpingCooldown: Int,
    private var isJumping: Boolean,
    private var isFallFlying: Boolean,
    var onGround: Boolean,
    var horizontalCollision: Boolean,
    private var verticalCollision: Boolean,
    private var touchingWater: Boolean,
    private var isSwimming: Boolean,
    private var submergedInWater: Boolean,
    private var fluidHeight: Any? = null,
    private var submergedFluidTag: Any? = null
) : PlayerSimulation {

    var clipLedged = false
        private set

    var deltaMovement: Vec3
        get() = velocity
        set(value) {
            velocity = value
        }

    fun jump() {
        if (onGround && jumpingCooldown == 0) {
            velocity = Vec3(velocity.x, 0.42, velocity.z)
            onGround = false
            jumpingCooldown = 10
        }
    }

    override fun tick() {
        clipLedged = false
        input.update()

        if (jumpingCooldown > 0) {
            jumpingCooldown--
        }

        isJumping = input.playerInput.jump
        sprinting = input.sprinting

        var motion = velocity

        if (isJumping && onGround && jumpingCooldown == 0) {
            motion = Vec3(motion.x, 0.42, motion.z)
            onGround = false
            jumpingCooldown = 10
        }

        val speed = if (sprinting) SPRINT_ACCELERATION else WALK_ACCELERATION
        val forward = input.movementForward.toDouble()
        val strafe = input.movementSideways.toDouble()
        if (forward != 0.0 || strafe != 0.0) {
            val radians = Math.toRadians(yaw.toDouble())
            val sin = sin(radians)
            val cos = cos(radians)
            motion = motion.add(
                strafe * cos - forward * sin,
                0.0,
                forward * cos + strafe * sin
            ).scale(speed)
        }

        if (!onGround) {
            motion = motion.add(0.0, -GRAVITY, 0.0)
            fallDistance += (-motion.y).coerceAtLeast(0.0).toFloat()
        }

        pos = pos.add(motion)
        boundingBox = boundingBox.move(motion)

        if (pos.y <= player.y) {
            pos = Vec3(pos.x, player.y, pos.z)
            motion = Vec3(motion.x, 0.0, motion.z)
            onGround = true
            fallDistance = 0f
        }

        val friction = if (onGround) GROUND_FRICTION else AIR_FRICTION
        velocity = Vec3(motion.x * friction, motion.y * 0.98, motion.z * friction)
    }

    fun clone(): SimulatedPlayer {
        return SimulatedPlayer(
            player,
            input,
            pos,
            velocity,
            boundingBox,
            yaw,
            pitch,
            sprinting,
            fallDistance,
            jumpingCooldown,
            isJumping,
            isFallFlying,
            onGround,
            horizontalCollision,
            verticalCollision,
            touchingWater,
            isSwimming,
            submergedInWater,
            fluidHeight,
            submergedFluidTag
        )
    }

    companion object {
        fun fromClientPlayer(input: SimulatedPlayerInput): SimulatedPlayer {
            return SimulatedPlayer(
                player,
                input,
                player.position(),
                player.deltaMovement,
                player.boundingBox,
                player.yRot,
                player.xRot,
                player.isSprinting,
                player.fallDistance.toFloat(),
                0,
                input.playerInput.jump,
                player.isFallFlying,
                player.onGround(),
                player.horizontalCollision,
                player.verticalCollision,
                player.isInWater,
                player.isSwimming,
                false
            )
        }

        fun fromOtherPlayer(player: Player, input: SimulatedPlayerInput): SimulatedPlayer {
            return SimulatedPlayer(
                player,
                input,
                player.position(),
                player.position().subtract(Vec3(player.xo, player.yo, player.zo)),
                player.boundingBox,
                player.yRot,
                player.xRot,
                player.isSprinting,
                player.fallDistance.toFloat(),
                0,
                input.playerInput.jump,
                player.isFallFlying,
                player.onGround(),
                player.horizontalCollision,
                player.verticalCollision,
                player.isInWater,
                player.isSwimming,
                false
            )
        }
    }

    class SimulatedPlayerInput(
        val directionalInput: DirectionalInput,
        jumping: Boolean,
        var sprinting: Boolean,
        sneaking: Boolean
    ) {
        var forceSafeWalk: Boolean = false
        var playerInput = SimplePlayerInput(
            forward = directionalInput.forwards,
            backward = directionalInput.backwards,
            left = directionalInput.left,
            right = directionalInput.right,
            jump = jumping,
            sneak = sneaking
        )
        var movementForward = 0.0f
        var movementSideways = 0.0f

        init {
            update()
        }

        fun update() {
            movementForward = when {
                playerInput.forward == playerInput.backward -> 0.0f
                playerInput.forward -> 1.0f
                else -> -1.0f
            }
            movementSideways = when {
                playerInput.left == playerInput.right -> 0.0f
                playerInput.left -> 1.0f
                else -> -1.0f
            }

            if (playerInput.sneak) {
                movementSideways *= 0.3f
                movementForward *= 0.3f
            }
        }

        override fun toString(): String {
            return "SimulatedPlayerInput(forwards=${playerInput.forward}, backwards=${playerInput.backward}, left=${playerInput.left}, right=${playerInput.right}, jumping=${playerInput.jump}, sprinting=$sprinting, slowDown=${playerInput.sneak})"
        }

        data class SimplePlayerInput(
            val forward: Boolean,
            val backward: Boolean,
            val left: Boolean,
            val right: Boolean,
            val jump: Boolean,
            val sneak: Boolean
        )

        companion object {
            private const val MAX_WALKING_SPEED = 0.121

            fun fromClientPlayer(
                directionalInput: DirectionalInput,
                jump: Boolean = player.input.keyPresses.jump(),
                sprinting: Boolean = player.isSprinting,
                sneaking: Boolean = player.isShiftKeyDown
            ): SimulatedPlayerInput {
                val input = SimulatedPlayerInput(directionalInput, jump, sprinting, sneaking)
                val safeWalkEvent = PlayerSafeWalkEvent()

                callEvent(safeWalkEvent)

                if (safeWalkEvent.isSafeWalk) {
                    input.forceSafeWalk = true
                }

                return input
            }

            fun guessInput(entity: Player): SimulatedPlayerInput {
                val velocity = entity.position().subtract(Vec3(entity.xo, entity.yo, entity.zo))
                val horizontalVelocity = velocity.horizontalDistanceSqr()
                val sprinting = horizontalVelocity >= MAX_WALKING_SPEED * MAX_WALKING_SPEED

                val directionalInput = if (horizontalVelocity > 0.05 * 0.05) {
                    val velocityAngle = getDegreesRelativeToView(velocity, yaw = entity.yRot)
                    getDirectionalInputForDegrees(DirectionalInput.NONE, Mth.wrapDegrees(velocityAngle))
                } else {
                    DirectionalInput.NONE
                }

                return SimulatedPlayerInput(
                    directionalInput,
                    jumping = !entity.onGround(),
                    sprinting = sprinting,
                    sneaking = entity.isShiftKeyDown
                )
            }
        }
    }
}
