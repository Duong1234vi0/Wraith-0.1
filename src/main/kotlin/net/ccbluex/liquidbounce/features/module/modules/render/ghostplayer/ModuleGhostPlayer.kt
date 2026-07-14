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
package net.ccbluex.liquidbounce.features.module.modules.render.ghostplayer

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.injection.mixins.minecraft.render.MixinModelPartAccessor
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.entity.player.AvatarRenderer
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import net.minecraft.world.entity.Pose
import net.minecraft.world.phys.Vec3
import kotlin.math.min

/**
 * Strict port of StichDLC `GhostPlayer`, whose visible module name is `TotemAngel`.
 */
@Suppress("MagicNumber")
object ModuleGhostPlayer : ClientModule("TotemAngel", Category.RENDER) {

    private val ghosts = ArrayList<GhostModel>()

    @Suppress("unused")
    private val totemPopHandler = handler<TotemPopEvent> { event ->
        val popped = event.entity as? AbstractClientPlayer ?: return@handler
        if (popped === mc.player || mc.player == null || mc.level == null) {
            return@handler
        }

        runCatching {
            createGhostModel(popped)
        }.onSuccess { ghost ->
            if (ghost != null) {
                ghosts += ghost
            }
        }.onFailure { throwable ->
            logger.error("Failed to create TotemAngel ghost snapshot.", throwable)
        }
    }

    override fun disable() {
        ghosts.clear()
        super.disable()
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (mc.player == null || mc.level == null || ghosts.isEmpty()) {
            return@handler
        }

        val provider = mc.renderBuffers().bufferSource()
        val cameraPos = event.camera.position()
        val matrices = event.matrixStack
        val iterator = ghosts.iterator()

        try {
            while (iterator.hasNext()) {
                val ghost = iterator.next()
                if (ghost.isExpired()) {
                    iterator.remove()
                    continue
                }

                renderGhostModel(ghost, matrices, provider, cameraPos)
            }
        } finally {
            provider.endBatch()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun createGhostModel(player: AbstractClientPlayer): GhostModel? {
        val renderer = mc.entityRenderDispatcher.getRenderer(player) as? AvatarRenderer<AbstractClientPlayer>
            ?: return null
        val model = renderer.model
        val partialTicks = mc.deltaTracker.getGameTimeDeltaPartialTick(false)

        val bodyYaw = Mth.rotLerp(partialTicks, player.yBodyRotO, player.yBodyRot)
        val headYaw = Mth.rotLerp(partialTicks, player.yHeadRotO, player.yHeadRot)
        val headPitch = Mth.lerp(partialTicks, player.xRotO, player.xRot)

        val state = renderer.createRenderState()
        renderer.extractRenderState(player, state, partialTicks)
        state.bodyRot = bodyYaw
        state.yRot = headYaw - bodyYaw
        state.xRot = headPitch
        state.walkAnimationSpeed = min(state.walkAnimationSpeed, 1.0f)
        state.wornHeadAnimationPos = state.walkAnimationPos
        state.isCrouching = player.isCrouching
        state.pose = if (player.isCrouching) Pose.CROUCHING else Pose.STANDING

        model.setupAnim(state)

        val startPos = Vec3(
            Mth.lerp(partialTicks.toDouble(), player.xo, player.x),
            Mth.lerp(partialTicks.toDouble(), player.yo, player.y),
            Mth.lerp(partialTicks.toDouble(), player.zo, player.z)
        )

        return GhostModel(
            startPos = startPos,
            yaw = bodyYaw,
            model = CopiedModel(model.head, model.body, model.rightArm, model.leftArm, model.rightLeg, model.leftLeg),
            sneaking = player.isCrouching
        )
    }

    private fun renderGhostModel(
        ghost: GhostModel,
        matrices: PoseStack,
        provider: MultiBufferSource,
        cameraPos: Vec3
    ) {
        val ghostPos = ghost.currentPosition()
        val progress = ghost.progress()
        val alpha = 0.5f * (1.0f - progress)
        val alphaChannel = (alpha * 255.0f).toInt().coerceIn(0, 255)
        if (alphaChannel <= 0) {
            return
        }

        val consumer = provider.getBuffer(GhostPlayerRenderTypes.GHOST)

        matrices.pushPose()
        matrices.translate(ghostPos.x - cameraPos.x, ghostPos.y - cameraPos.y, ghostPos.z - cameraPos.z)
        matrices.translate(0.0, 1.501, 0.0)
        matrices.scale(1.0f, -1.0f, -1.0f)
        matrices.mulPose(Axis.YP.rotationDegrees(180.0f - ghost.yaw))
        if (ghost.sneaking) {
            matrices.translate(0.0, 0.125, 0.0)
        }

        renderModelPartWorld(matrices, consumer, ghost.model.head, 0.0f, progress * 0.5f, progress * 0.2f, alphaChannel)
        renderModelPartWorld(matrices, consumer, ghost.model.body, 0.0f, progress * 0.3f, 0.0f, alphaChannel)
        renderModelPartWorld(matrices, consumer, ghost.model.rightArm, -progress * 0.4f, progress * 0.35f, 0.0f, alphaChannel)
        renderModelPartWorld(matrices, consumer, ghost.model.leftArm, progress * 0.4f, progress * 0.35f, 0.0f, alphaChannel)
        renderModelPartWorld(matrices, consumer, ghost.model.rightLeg, -progress * 0.3f, progress * 0.2f, 0.0f, alphaChannel)
        renderModelPartWorld(matrices, consumer, ghost.model.leftLeg, progress * 0.3f, progress * 0.2f, 0.0f, alphaChannel)
        matrices.popPose()
    }

    private fun renderModelPartWorld(
        matrices: PoseStack,
        consumer: VertexConsumer,
        part: CopiedPart,
        offsetX: Float,
        offsetY: Float,
        offsetZ: Float,
        alpha: Int
    ) {
        matrices.pushPose()
        matrices.translate(offsetX.toDouble(), offsetY.toDouble(), offsetZ.toDouble())
        matrices.translate((part.pivotX / 16.0f).toDouble(), (part.pivotY / 16.0f).toDouble(), (part.pivotZ / 16.0f).toDouble())
        if (part.zRotation != 0.0f) {
            matrices.mulPose(Axis.ZP.rotation(part.zRotation))
        }
        if (part.yRotation != 0.0f) {
            matrices.mulPose(Axis.YP.rotation(part.yRotation))
        }
        if (part.xRotation != 0.0f) {
            matrices.mulPose(Axis.XP.rotation(part.xRotation))
        }

        renderCubeWorld(consumer, matrices.last(), part, alpha)
        matrices.popPose()
    }

    private fun renderCubeWorld(consumer: VertexConsumer, pose: PoseStack.Pose, part: CopiedPart, alpha: Int) {
        val minX = part.minX / 16.0f
        val minY = part.minY / 16.0f
        val minZ = part.minZ / 16.0f
        val maxX = part.maxX / 16.0f
        val maxY = part.maxY / 16.0f
        val maxZ = part.maxZ / 16.0f

        quadWorld(consumer, pose, minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, alpha)
        quadWorld(consumer, pose, maxX, minY, minZ, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, alpha)
        quadWorld(consumer, pose, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, alpha)
        quadWorld(consumer, pose, minX, minY, maxZ, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, alpha)
        quadWorld(consumer, pose, maxX, minY, maxZ, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, alpha)
        quadWorld(consumer, pose, minX, minY, minZ, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, alpha)
    }

    private fun quadWorld(
        consumer: VertexConsumer,
        pose: PoseStack.Pose,
        x0: Float,
        y0: Float,
        z0: Float,
        x1: Float,
        y1: Float,
        z1: Float,
        x2: Float,
        y2: Float,
        z2: Float,
        x3: Float,
        y3: Float,
        z3: Float,
        alpha: Int
    ) {
        posWorld(consumer, pose, x0, y0, z0, alpha)
        posWorld(consumer, pose, x1, y1, z1, alpha)
        posWorld(consumer, pose, x2, y2, z2, alpha)
        posWorld(consumer, pose, x3, y3, z3, alpha)
    }

    private fun posWorld(consumer: VertexConsumer, pose: PoseStack.Pose, x: Float, y: Float, z: Float, alpha: Int) {
        consumer.addVertex(pose, x, y, z).setColor(255, 255, 255, alpha)
    }

    private data class GhostModel(
        val startPos: Vec3,
        val yaw: Float,
        val model: CopiedModel,
        val sneaking: Boolean,
        val startTime: Long = System.currentTimeMillis()
    ) {
        fun progress(): Float = min(1.0f, (System.currentTimeMillis() - startTime).toFloat() / DURATION_MS)

        fun currentPosition(): Vec3 = startPos.add(0.0, (progress() * SPEED * 2.0f).toDouble(), 0.0)

        fun isExpired(): Boolean = System.currentTimeMillis() - startTime >= DURATION_MS
    }

    private data class CopiedModel(
        val head: CopiedPart,
        val body: CopiedPart,
        val rightArm: CopiedPart,
        val leftArm: CopiedPart,
        val rightLeg: CopiedPart,
        val leftLeg: CopiedPart
    ) {
        constructor(
            head: ModelPart,
            body: ModelPart,
            rightArm: ModelPart,
            leftArm: ModelPart,
            rightLeg: ModelPart,
            leftLeg: ModelPart
        ) : this(
            CopiedPart(head),
            CopiedPart(body),
            CopiedPart(rightArm),
            CopiedPart(leftArm),
            CopiedPart(rightLeg),
            CopiedPart(leftLeg)
        )
    }

    private data class CopiedPart(
        val pivotX: Float,
        val pivotY: Float,
        val pivotZ: Float,
        val xRotation: Float,
        val yRotation: Float,
        val zRotation: Float,
        val minX: Float,
        val minY: Float,
        val minZ: Float,
        val maxX: Float,
        val maxY: Float,
        val maxZ: Float
    ) {
        constructor(source: ModelPart) : this(
            pivotX = source.x,
            pivotY = source.y,
            pivotZ = source.z,
            xRotation = source.xRot,
            yRotation = source.yRot,
            zRotation = source.zRot,
            cube = firstCube(source)
        )

        private constructor(
            pivotX: Float,
            pivotY: Float,
            pivotZ: Float,
            xRotation: Float,
            yRotation: Float,
            zRotation: Float,
            cube: ModelPart.Cube?
        ) : this(
            pivotX = pivotX,
            pivotY = pivotY,
            pivotZ = pivotZ,
            xRotation = xRotation,
            yRotation = yRotation,
            zRotation = zRotation,
            minX = cube?.minX ?: FALLBACK_MIN_X,
            minY = cube?.minY ?: FALLBACK_MIN_Y,
            minZ = cube?.minZ ?: FALLBACK_MIN_Z,
            maxX = cube?.maxX ?: FALLBACK_MAX_X,
            maxY = cube?.maxY ?: FALLBACK_MAX_Y,
            maxZ = cube?.maxZ ?: FALLBACK_MAX_Z
        )
    }

    private object GhostPlayerRenderTypes {
        private const val BUFFER_SIZE = 4096

        val GHOST: RenderType by lazy(LazyThreadSafetyMode.NONE) {
            RenderType.create(
                "liquidbounce_totem_angel",
                RenderSetup.builder(pipeline())
                    .bufferSize(BUFFER_SIZE)
                    .createRenderSetup()
            )
        }

        private fun pipeline(): RenderPipeline {
            return RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
                .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/totem_angel"))
                .withVertexShader("core/position_color")
                .withFragmentShader("core/position_color")
                .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
                .withDepthStencilState(DepthStencilState.DEFAULT)
                .withCull(false)
                .withVertexFormat(DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS)
                .build()
        }
    }

    private fun firstCube(part: ModelPart): ModelPart.Cube? =
        (part as MixinModelPartAccessor).`liquid_bounce$getCubes`().firstOrNull()

    private const val DURATION_MS = 2000L
    private const val SPEED = 1.0f
    private const val FALLBACK_MIN_X = -4.0f
    private const val FALLBACK_MIN_Y = 0.0f
    private const val FALLBACK_MIN_Z = -2.0f
    private const val FALLBACK_MAX_X = 4.0f
    private const val FALLBACK_MAX_Y = 8.0f
    private const val FALLBACK_MAX_Z = 2.0f
}
