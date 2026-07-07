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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.render.BoxRenderer
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.math.sq
import net.minecraft.util.Mth
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import kotlin.math.hypot
import kotlin.math.min
import kotlin.math.sqrt

/**
 * VCore 0.8 ElytraTarget port.
 */
object ModuleElytraTarget : ClientModule("ElytraTarget", Category.COMBAT) {

    private val prediction by boolean("Prediction", true)
    private val visual by boolean("Visual", true)
    private val color by color("Color", Color4b(16752640))
    private val mode by enumChoice("Mode", Mode.AUTO)
    private val predictRange by float("Predict Range", 2.6f, 1.5f..6.0f)
    private val predictMax by float("Predict Max", 10.0f, 1.5f..10.0f)

    var status = true
        private set

    var disableForward = false
        private set

    private var bps = 0.0
    var scale = 0.0
        private set
    private var lastHurtAt = 0L

    override fun disable() {
        status = true
        disableForward = false
        bps = 0.0
        scale = 0.0
        lastHurtAt = 0L
    }

    @Suppress("unused")
    private val tickHandler = tickHandler {
        val localPlayer = mc.player ?: return@tickHandler
        val target = ModuleKillAura.targetTracker.target

        status = prediction
        if (target != null && shouldTarget(target)) {
            bps = getBps(target)
            scale = min(sqrt(bps) / 2.0, predictMax.toDouble())
        } else {
            bps = 0.0
        }

        if (target == null) {
            disableForward = false
            return@tickHandler
        }

        if (localPlayer.hurtTime > 0) {
            disableForward = true
            lastHurtAt = System.currentTimeMillis()
        }

        if (disableForward && System.currentTimeMillis() - lastHurtAt >= HURT_DISABLE_MS) {
            disableForward = false
        }
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (!visual || !prediction) {
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target ?: return@handler
        if (!shouldTarget(target)) {
            return@handler
        }

        val predicted = getPredictedVec(target) ?: return@handler
        val box = AABB(
            -VISUAL_BOX_SIZE,
            -VISUAL_BOX_SIZE,
            -VISUAL_BOX_SIZE,
            VISUAL_BOX_SIZE,
            VISUAL_BOX_SIZE,
            VISUAL_BOX_SIZE
        )

        renderEnvironmentForWorld(event.matrixStack) {
            withPositionRelativeToCamera(predicted) {
                BoxRenderer.drawWith(this) {
                    drawBox(box, color.with(a = 120), color.with(a = 255))
                }
            }
        }
    }

    fun shouldTarget(target: LivingEntity?): Boolean {
        val localPlayer = mc.player ?: return false
        return running &&
            target != null &&
            !disableForward &&
            status &&
            localPlayer.isGliding &&
            target.isGliding
    }

    fun getPrediction(target: LivingEntity): Double {
        return if (mode == Mode.AUTO) {
            scale
        } else {
            predictRange.toDouble()
        }
    }

    fun getBps(entity: LivingEntity?): Double {
        if (entity == null) {
            return 0.0
        }

        val velocity = entity.deltaMovement
        return hypot(velocity.x, velocity.z) * TICKS_PER_SECOND
    }

    fun getPredictedVec(target: LivingEntity, applyPrediction: Boolean = true): Vec3? {
        val localPlayer = mc.player ?: return null
        val playerEye = localPlayer.eyePos
        val box = target.boundingBox
        var basePos = Vec3(
            Mth.clamp(playerEye.x, box.minX, box.maxX),
            Mth.clamp(playerEye.y, box.minY, box.maxY),
            Mth.clamp(playerEye.z, box.minZ, box.maxZ)
        )

        if (applyPrediction && shouldTarget(target) && getBps(target) >= FAST_TARGET_BPS) {
            basePos = basePos.add(target.deltaMovement.scale(getPrediction(target)))
        }

        return basePos
    }

    fun canAttack(target: LivingEntity): Boolean {
        val localPlayer = mc.player ?: return false
        val targetVec = getPredictedVec(target, applyPrediction = true) ?: return false
        val prediction = getPrediction(target)

        return localPlayer.pos.distanceTo(targetVec) <= prediction - 0.5 ||
            localPlayer.boundingBox.intersects(AABB(targetVec, targetVec))
    }

    fun squaredDistanceToPredicted(target: LivingEntity): Double {
        val localPlayer = mc.player ?: return Double.MAX_VALUE
        val targetVec = getPredictedVec(target, applyPrediction = true) ?: return Double.MAX_VALUE
        return localPlayer.eyePos.distanceToSqr(targetVec)
    }

    fun isInAimRange(target: LivingEntity, range: Float): Boolean {
        return squaredDistanceToPredicted(target) <= range.sq()
    }

    private enum class Mode(
        override val choiceName: String
    ) : NamedChoice {
        AUTO("Auto"),
        DEFAULT("Default")
    }

    private const val TICKS_PER_SECOND = 20.0
    private const val FAST_TARGET_BPS = 20.0
    private const val HURT_DISABLE_MS = 500L
    private const val VISUAL_BOX_SIZE = 0.2
}
