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
@file:Suppress("detekt:TooManyFunctions")

package net.ccbluex.liquidbounce.features.module.modules.render
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.events.MovementInputEvent
import net.ccbluex.liquidbounce.event.events.PerspectiveEvent
import net.ccbluex.liquidbounce.event.events.RotationUpdateEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.RotationsConfigurable
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.utils.raycast
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.entity.withStrafe
import net.ccbluex.liquidbounce.utils.input.isPressed
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.math.plus
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.ccbluex.liquidbounce.utils.navigation.NavigationBaseConfigurable
import net.minecraft.client.CameraType
import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.core.Direction
import net.minecraft.world.phys.Vec3
import org.lwjgl.glfw.GLFW

/**
 * FreeCam module
 *
 * Allows you to move out of your body.
 */
object ModuleFreeCam : ClientModule("FreeCam", Category.RENDER, disableOnQuit = true) {

    private val speed by float("Speed", 1f, 0.1f..2f)

    /**
     * Allows to interact from the camera perspective. This is very useful to interact with blocks that
     * are behind the player or walls. Similar functionality to the GhostBlock module.
     */
    private object CameraInteract : ToggleableConfigurable(ModuleFreeCam, "AllowCameraInteract", true) {
        val lookAt by boolean("LookAt", true)
    }

    /**
     * Navigation configuration for the FreeCam module
     */
    private object Navigation : NavigationBaseConfigurable<Unit>(ModuleFreeCam, "Navigation", false) {

        private val controlKey by key("Key", GLFW.GLFW_KEY_LEFT_CONTROL)

        val shouldBeGoing
            get() = running && controlKey != InputConstants.UNKNOWN && controlKey.isPressed

        /**
         * Creates context for navigation
         */
        override fun createNavigationContext() { }

        /**
         * Calculates the desired position to move towards
         *
         * @return Target position as Vec3
         */
        override fun calculateGoalPosition(context: Unit): Vec3? {
            if (!shouldBeGoing) {
                return null
            }

            val pos = pos ?: return null
            val cameraPosition = pos.interpolate(1f)
            val target = raycast(
                range = 100.0,
                start = cameraPosition,
                direction = mc.cameraEntity?.rotation?.directionVector ?: return null
            )

            return target.pos
        }

    }

    private val rotationsConfigurable = tree(RotationsConfigurable(this))

    init {
        tree(CameraInteract)
        tree(Navigation)
    }

    private data class PositionPair(var pos: Vec3, var lastPos: Vec3) {
        operator fun plusAssign(velocity: Vec3) {
            lastPos = pos
            pos += velocity
        }

        fun interpolate(tickDelta: Float) = Vec3(
            lastPos.x + (pos.x - lastPos.x) * tickDelta,
            lastPos.y + (pos.y - lastPos.y) * tickDelta,
            lastPos.z + (pos.z - lastPos.z) * tickDelta
        )

    }

    private var pos: PositionPair? = null

    override fun enable() {
        updatePosition(Vec3.ZERO)
        super.enable()
    }

    override fun disable() {
        pos = null

        // Reset player rotation
        val rotation = RotationManager.currentRotation ?: RotationManager.serverRotation
        player.yaw = rotation.yaw
        player.pitch = rotation.pitch
        super.disable()
    }

    @Suppress("unused")
    private val inputHandler = handler<MovementInputEvent>(priority = FIRST_PRIORITY) { event ->
        val speed = this.speed.toDouble()
        val yAxisMovement = when {
            event.jump -> 1.0f
            event.sneak -> -1.0f
            else -> 0.0f
        }

        ModuleDebug.debugParameter(this, "DirectionalInput", event.directionalInput)
        val strafed = Vec3.ZERO.withStrafe(speed, input = event.directionalInput)
        val velocity = Vec3(strafed.x, yAxisMovement * speed, strafed.z)
        ModuleDebug.debugParameter(this, "Velocity", velocity.toString())
        updatePosition(velocity)

        event.directionalInput = DirectionalInput.NONE
        event.jump = false
        event.sneak = false
    }

    @Suppress("unused")
    private val perspectiveHandler = handler<PerspectiveEvent> { event ->
        event.perspective = CameraType.FIRST_PERSON
    }

    @Suppress("unused")
    private val rotationHandler = handler<RotationUpdateEvent> {
        val lookAt = if (Navigation.shouldBeGoing) {
            // Look at target position
            Navigation.getMovementRotation()
        } else if (CameraInteract.running && CameraInteract.lookAt) {
            // Aim at crosshair target
            val crosshairTarget = mc.crosshairTarget ?: return@handler
            Rotation.lookingAt(crosshairTarget.pos, player.eyePos)
        } else {
            return@handler
        }

        RotationManager.setRotationTarget(rotationsConfigurable.toRotationTarget(lookAt),
            Priority.NOT_IMPORTANT, ModuleFreeCam)
    }

    fun applyCameraPosition(entity: Entity, tickDelta: Float) {
        val camera = mc.gameRenderer.camera

        if (!running || entity != player) {
            return
        }

        return camera.setPosition(pos?.interpolate(tickDelta) ?: return)
    }

    fun renderPlayerFromAllPerspectives(entity: LivingEntity): Boolean {
        if (!running || entity != player) {
            return entity.isSleeping
        }

        return entity.isSleeping || !mc.gameRenderer.camera.isThirdPerson
    }

    /**
     * Modify the raycast position
     */
    fun modifyRaycast(original: Vec3, entity: Entity, tickDelta: Float): Vec3 {
        if (!running || entity != mc.player || !CameraInteract.running) {
            return original
        }

        return pos?.interpolate(tickDelta) ?: original
    }

    fun shouldDisableCameraInteract() = running && !CameraInteract.running

    private fun updatePosition(velocity: Vec3) {
        pos = (pos ?: PositionPair(player.eyePos, player.eyePos)).apply { this += velocity }
    }

}
