/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.render.trajectories

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.render.BoxRenderer
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.GlStateManager
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.vertex
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.entity.drawItem
import net.ccbluex.liquidbounce.utils.entity.eyePos
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.ccbluex.liquidbounce.utils.entity.pitch
import net.ccbluex.liquidbounce.utils.entity.yaw
import net.ccbluex.liquidbounce.utils.render.WorldToScreen
import net.minecraft.client.gui.DrawContext
import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.projectile.arrow.AbstractArrow
import net.minecraft.world.entity.projectile.arrow.ThrownTrident
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Wyvern-DLC Predictions port. Simulates active projectile and dropped item motion.
 */
object ModulePredictions : ClientModule(
    "Predictions",
    Category.RENDER,
    aliases = arrayOf("Prediction", "Trajectories")
) {

    private val bowMarkers by boolean("Markers", true)
    private val bowLandingBox by boolean("LandingBox", true)
    private val bowThickness by float("Thickness", 2.0f, 0.5f..5.0f)

    private val landingPoints = ArrayList<LandingPoint>(32)

    @Suppress("unused")
    private val worldRenderHandler = handler<WorldRenderEvent> { event ->
        val level = mc.level ?: return@handler
        if (mc.player == null) {
            landingPoints.clear()
            return@handler
        }

        val projectiles = level.entitiesForRendering()
            .asSequence()
            .filter { it.isPredictedEntity() && !it.isSettledProjectile() }
            .take(MAX_PROJECTILES)
            .toList()
        val bowPrediction = simulateHeldBow()

        landingPoints.clear()
        if (projectiles.isEmpty() && bowPrediction == null) {
            return@handler
        }

        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.enableBlend()
            RenderSystem.defaultBlendFunc()
            RenderSystem.disableDepthTest()
            RenderSystem.disableCull()
            RenderSystem.depthMask(false)
            drawPredictionMesh(projectiles, event.camera.position())
            bowPrediction?.let { drawBowPrediction(it, event.camera.position()) }
            RenderSystem.depthMask(true)
            RenderSystem.enableCull()
            RenderSystem.enableDepthTest()
            RenderSystem.defaultBlendFunc()
        }
    }

    @Suppress("unused")
    private val overlayHandler = handler<OverlayRenderEvent> { event ->
        if (landingPoints.isEmpty()) {
            return@handler
        }

        for (point in landingPoints) {
            drawLandingLabel(event.context, point)
        }
    }

    override fun disable() {
        landingPoints.clear()
        super.disable()
    }

    private fun WorldRenderEnvironment.drawPredictionMesh(projectiles: List<Entity>, cameraPos: Vec3) {
        val predictions = ArrayList<PredictionPath>(projectiles.size)
        for (entity in projectiles) {
            val prediction = simulate(entity) ?: continue
            predictions += prediction
            prediction.landingPoint?.let(landingPoints::add)
        }
        if (predictions.isEmpty()) {
            return
        }

        RenderSystem.lineWidth(2.0f)
        drawCustomMesh(
            VertexFormat.Mode.DEBUG_LINES,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (prediction in predictions) {
                val points = prediction.points
                for (tick in 0 until points.lastIndex) {
                    val from = points[tick]
                    val to = points[tick + 1]
                    val alpha = (tick / 25.0f).coerceIn(0.15f, 1.0f)
                    val lineColor = themeColor(tick, alpha)
                    this.vertex(
                        matrix,
                        (from.x - cameraPos.x).toFloat(),
                        (from.y - cameraPos.y).toFloat(),
                        (from.z - cameraPos.z).toFloat()
                    ).setColor(lineColor)
                    this.vertex(
                        matrix,
                        (to.x - cameraPos.x).toFloat(),
                        (to.y - cameraPos.y).toFloat(),
                        (to.z - cameraPos.z).toFloat()
                    ).setColor(lineColor)
                }
            }
        }

        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
        RenderSystem.enableAdditive()
        RenderSystem.lineWidth(5.5f)
        drawCustomMesh(
            VertexFormat.Mode.DEBUG_LINES,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (prediction in predictions) {
                val points = prediction.points
                for (tick in 0 until points.lastIndex) {
                    val from = points[tick]
                    val to = points[tick + 1]
                    val alpha = (tick / 50.0f).coerceIn(0.0f, 0.25f)
                    val glowColor = themeColor(tick, alpha)
                    this.vertex(
                        matrix,
                        (from.x - cameraPos.x).toFloat(),
                        (from.y - cameraPos.y).toFloat(),
                        (from.z - cameraPos.z).toFloat()
                    ).setColor(glowColor)
                    this.vertex(
                        matrix,
                        (to.x - cameraPos.x).toFloat(),
                        (to.y - cameraPos.y).toFloat(),
                        (to.z - cameraPos.z).toFloat()
                    ).setColor(glowColor)
                }
            }
        }
        RenderSystem.defaultBlendFunc()
    }

    private fun WorldRenderEnvironment.drawBowPrediction(prediction: BowPredictionPath, cameraPos: Vec3) {
        val points = prediction.points
        if (points.size < 2) {
            return
        }

        RenderSystem.defaultBlendFunc()
        RenderSystem.lineWidth(bowThickness)
        drawCustomMesh(
            VertexFormat.Mode.DEBUG_LINES,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (i in 0 until points.lastIndex) {
                val from = points[i]
                val to = points[i + 1]
                val color = bowColor(1.0f)
                this.vertex(
                    matrix,
                    (from.x - cameraPos.x).toFloat(),
                    (from.y - cameraPos.y).toFloat(),
                    (from.z - cameraPos.z).toFloat()
                ).setColor(color)
                this.vertex(
                    matrix,
                    (to.x - cameraPos.x).toFloat(),
                    (to.y - cameraPos.y).toFloat(),
                    (to.z - cameraPos.z).toFloat()
                ).setColor(color)
            }
        }

        if (bowMarkers) {
            drawBowMarkers(points, cameraPos)
        }
        if (bowLandingBox) {
            drawBowLandingBox(prediction.hit, cameraPos)
        }
    }

    private fun WorldRenderEnvironment.drawBowMarkers(points: List<Vec3>, cameraPos: Vec3) {
        val faceColor = bowBoxColor(45)
        val outlineColor = bowBoxColor(255)

        RenderSystem.lineWidth(1.0f)
        BoxRenderer.drawWith(this) {
            for (i in 0 until points.lastIndex) {
                if (i % 3 != 0) {
                    continue
                }

                val end = points[i + 1]
                val marker = AABB(
                    end.subtract(BOW_MARKER_SIZE, BOW_MARKER_SIZE, BOW_MARKER_SIZE),
                    end.add(BOW_MARKER_SIZE, BOW_MARKER_SIZE, BOW_MARKER_SIZE)
                ).move(-cameraPos.x, -cameraPos.y, -cameraPos.z)
                drawBox(marker, faceColor, outlineColor)
            }
        }
    }

    private fun WorldRenderEnvironment.drawBowLandingBox(hit: HitResult?, cameraPos: Vec3) {
        if (hit !is BlockHitResult) {
            return
        }

        val hitPos = hit.location
        val landing = AABB(
            hitPos.subtract(BOW_LANDING_BOX_SIZE, BOW_LANDING_BOX_SIZE, BOW_LANDING_BOX_SIZE),
            hitPos.add(BOW_LANDING_BOX_SIZE, BOW_LANDING_BOX_SIZE, BOW_LANDING_BOX_SIZE)
        ).move(-cameraPos.x, -cameraPos.y, -cameraPos.z)

        RenderSystem.lineWidth(1.5f)
        BoxRenderer.drawWith(this) {
            drawBox(landing, bowBoxColor(55), bowBoxColor(255))
        }
    }

    private fun simulate(entity: Entity): PredictionPath? {
        val level = mc.level ?: return null
        var pos = entity.position()
        var motion = entity.deltaMovement
        var ticks = 0
        val points = ArrayList<Vec3>(MAX_TICKS + 1)
        var landingPoint: LandingPoint? = null
        points += pos

        while (ticks < MAX_TICKS) {
            val previous = pos
            pos = pos.add(motion)
            val blockHit = level.clip(ClipContext(previous, pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
            if (blockHit.type != HitResult.Type.MISS) {
                pos = blockHit.location
            }

            points += pos

            val entityHit = hitsEntity(entity, previous, pos)
            if (blockHit.type != HitResult.Type.MISS || entityHit || pos.y < level.minY - 8.0) {
                landingPoint = LandingPoint(entity.landingStack(), pos, ticks)
                break
            }

            motion = calculateMotion(entity, previous, motion)
            ticks++
        }

        return if (points.size > 1) PredictionPath(points, landingPoint) else null
    }

    private fun simulateHeldBow(): BowPredictionPath? {
        val level = mc.level ?: return null
        val localPlayer = mc.player ?: return null
        val isUsingBow = localPlayer.mainHandItem.item is BowItem || localPlayer.offhandItem.item is BowItem
        if (!isUsingBow) {
            return null
        }

        var pullProgress = bowPullProgress(localPlayer.ticksUsingItem)
        if (pullProgress == 0.0f && !localPlayer.isUsingItem) {
            return null
        }
        if (pullProgress == 0.0f) {
            pullProgress = 1.0f
        }

        val velocity = pullProgress * 3.0
        val yawRad = Math.toRadians(localPlayer.yaw.toDouble())
        val pitchRad = Math.toRadians(localPlayer.pitch.toDouble())
        var motionX = -sin(yawRad) * cos(pitchRad)
        var motionY = -sin(pitchRad)
        var motionZ = cos(yawRad) * cos(pitchRad)
        val length = sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ)
        if (length <= 0.0) {
            return null
        }

        motionX = motionX / length * velocity
        motionY = motionY / length * velocity
        motionZ = motionZ / length * velocity

        var pos = localPlayer.eyePos
        val trajectory = ArrayList<Vec3>(BOW_MAX_TICKS + 1)
        trajectory += pos
        var hit: HitResult? = null

        for (i in 0 until BOW_MAX_TICKS) {
            var nextPos = pos.add(motionX, motionY, motionZ)
            val raycast = level.clip(
                ClipContext(pos, nextPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, localPlayer)
            )

            if (raycast.type != HitResult.Type.MISS) {
                nextPos = raycast.location
                trajectory += nextPos
                hit = raycast
                break
            }

            trajectory += nextPos
            pos = nextPos

            motionX *= 0.99
            motionY *= 0.99
            motionZ *= 0.99
            motionY -= 0.05
        }

        return if (trajectory.size > 1) BowPredictionPath(trajectory, hit) else null
    }

    private fun bowPullProgress(useTime: Int): Float {
        var pullProgress = useTime / 20.0f
        pullProgress = (pullProgress * pullProgress + pullProgress * 2.0f) / 3.0f
        return pullProgress.coerceAtMost(1.0f)
    }

    private fun calculateMotion(entity: Entity, previous: Vec3, motion: Vec3): Vec3 {
        val level = mc.level ?: return motion
        val inWater = level.getFluidState(BlockPos.containing(previous)).`is`(FluidTags.WATER)
        val drag = when (entity) {
            is ThrownTrident -> 0.99
            is AbstractArrow -> if (inWater) 0.6 else 0.99
            else -> if (inWater) 0.8 else 0.99
        }
        val gravity = if (entity.isNoGravity) 0.0 else entity.gravity
        return motion.scale(drag).add(0.0, -gravity, 0.0)
    }

    private fun hitsEntity(projectile: Entity, previous: Vec3, next: Vec3): Boolean {
        val level = mc.level ?: return false
        val box = AABB(previous, next).inflate(0.3)
        return level.getEntities(projectile, box) { entity ->
            entity !== projectile &&
                entity !== player &&
                entity is LivingEntity &&
                entity.isAlive &&
                !entity.isSpectator &&
                entity.boundingBox.inflate(0.25).clip(previous, next).isPresent
        }.isNotEmpty()
    }

    private fun drawLandingLabel(context: DrawContext, point: LandingPoint) {
        val screen = WorldToScreen.calculateScreenPos(point.pos) ?: return
        if (screen.z < 0.0 || screen.z > 1.0) {
            return
        }

        val text = "${((point.ticks * 50.0) / 1000.0).roundToTenth()}s"
        val textSize = 7.5f
        val textWidth = ExosGuiRenderer.bodyWidth(text, textSize)
        val width = textWidth + 19.0f
        val height = 13.0f
        val x = screen.x.toFloat() - width * 0.5f
        val y = screen.y.toFloat() - height * 0.5f
        val matrices = context.matrices

        ExosGuiRenderer.roundedRect(matrices, x, y, width, height, 2.5f, 0xA8000000.toInt())
        context.pose().pushMatrix()
        try {
            context.pose().translate(x + 2.0f, y + 1.8f)
            context.pose().scale(0.55f, 0.55f)
            context.drawItem(point.stack, 0, 0)
        } finally {
            context.pose().popMatrix()
        }
        ExosGuiRenderer.body(matrices, text, x + 14.0f, y + 2.1f, 0xFFFFFFFF.toInt(), textSize)
    }

    private fun Entity.isPredictedEntity(): Boolean {
        return this is AbstractArrow || this is ThrowableItemProjectile || this is ItemEntity
    }

    private fun Entity.isSettledProjectile(): Boolean {
        val unmoving = deltaMovement.lengthSqr() < 1.0E-5
        val settledItem = this is ItemEntity && (onGround() || isInWater)
        return unmoving || settledItem
    }

    private fun Entity.landingStack(): ItemStack {
        return when (this) {
            is ItemEntity -> item
            is ThrowableItemProjectile -> item
            is AbstractArrow -> pickupItemStackOrigin
            else -> ItemStack.EMPTY
        }.copy()
    }

    private fun themeColor(tick: Int, alpha: Float): Int {
        val color = if (tick % 40 < 20) ClientTheme.accentA else ClientTheme.accentB
        val alphaChannel = (alpha.coerceIn(0.0f, 1.0f) * 255.0f).roundToInt().coerceIn(0, 255)
        return (alphaChannel shl 24) or (color and 0x00FFFFFF)
    }

    private fun bowColor(alpha: Float): Int {
        val alphaChannel = (alpha.coerceIn(0.0f, 1.0f) * 255.0f).roundToInt().coerceIn(0, 255)
        return (alphaChannel shl 24) or (ClientTheme.accentA and 0x00FFFFFF)
    }

    private fun bowBoxColor(alpha: Int): Color4b {
        val color = ClientTheme.accentA
        return Color4b(
            (color shr 16) and 0xFF,
            (color shr 8) and 0xFF,
            color and 0xFF,
            alpha.coerceIn(0, 255)
        )
    }

    private fun Double.roundToTenth(): String {
        val rounded = (this * 10.0).roundToInt() / 10.0
        return if (rounded % 1.0 == 0.0) rounded.toInt().toString() else rounded.toString()
    }

    private data class PredictionPath(val points: List<Vec3>, val landingPoint: LandingPoint?)

    private data class BowPredictionPath(val points: List<Vec3>, val hit: HitResult?)

    private data class LandingPoint(val stack: ItemStack, val pos: Vec3, val ticks: Int)

    private const val MAX_TICKS = 300
    private const val MAX_PROJECTILES = 32
    private const val BOW_MAX_TICKS = 100
    private const val BOW_MARKER_SIZE = 0.05
    private const val BOW_LANDING_BOX_SIZE = 0.2
}
