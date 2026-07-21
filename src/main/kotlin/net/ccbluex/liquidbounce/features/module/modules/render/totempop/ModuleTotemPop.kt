/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.totempop

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.config.types.ValueType
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.player.AvatarRenderer
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.player.PlayerModelType
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.min

@Suppress("MagicNumber")
object ModuleTotemPop : ClientModule("TotemPop", Category.RENDER) {
    val duration by rangedValue(
        "Duration",
        TotemPopState.DEFAULT_DURATION,
        TotemPopState.MIN_DURATION..TotemPopState.MAX_DURATION,
        "ms",
        ValueType.INT,
        TotemPopState.DURATION_STEP,
    )
    val rise by float(
        "Rise",
        TotemPopState.DEFAULT_RISE,
        TotemPopState.MIN_RISE..TotemPopState.MAX_RISE,
        TotemPopState.RISE_STEP,
    )
    val self by boolean("Self", true)

    private val entries = CopyOnWriteArrayList<PopEntry>()

    @Suppress("unused")
    private val popHandler = handler<TotemPopEvent> { event ->
        val entity = event.entity as? AbstractClientPlayer ?: return@handler
        if (!self && entity === mc.player) return@handler
        if (entity.level() !== mc.level) return@handler
        entries += PopEntry.capture(entity, System.currentTimeMillis())
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val level = mc.level
        if (level == null || entries.isEmpty()) return@handler
        val now = System.currentTimeMillis()
        val camera = event.camera.position()
        val buffers = mc.renderBuffers().bufferSource()
        try {
            for (entry in entries) {
                val progress = TotemPopState.progress(now, entry.startTime, duration)
                if (TotemPopState.expired(progress) || entry.entity.isRemoved || entry.entity.level() !== level) {
                    entries.remove(entry)
                    continue
                }
                renderGhost(event.matrixStack, buffers, entry, camera.x, camera.y, camera.z, progress, event.partialTicks)
            }
        } finally {
            buffers.endBatch()
            TotemPopRenderScope.clear()
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> { clearEntries() }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> { clearEntries() }

    override fun disable() {
        clearEntries()
        super.disable()
    }

    private fun clearEntries() {
        entries.clear()
        TotemPopRenderScope.clear()
    }

    @Suppress("UNCHECKED_CAST")
    private fun renderGhost(
        matrices: PoseStack,
        buffers: MultiBufferSource.BufferSource,
        entry: PopEntry,
        cameraX: Double,
        cameraY: Double,
        cameraZ: Double,
        progress: Float,
        partialTicks: Float,
    ) {
        val renderer = mc.entityRenderDispatcher.getRenderer(entry.entity) as? AvatarRenderer<AbstractClientPlayer>
            ?: return
        val alpha = TotemPopState.alpha(progress)
        if (alpha <= 0f) return
        val parameters = TotemPopRenderParameters(alpha, ClientTheme.accentA and 0x00FFFFFF)

        TotemPopRenderScope.withGhost(parameters) {
            withRestoredState(
                capture = { LivePose.capture(entry.entity) },
                apply = { entry.pose.applyTo(entry.entity) },
                restore = { it.restore(entry.entity) },
            ) {
                val state = renderer.createRenderState()
                renderer.extractRenderState(entry.entity, state, partialTicks)
                entry.pose.applyTo(state)
                entry.model.setupAnim(state)
                val renderOffset = renderer.getRenderOffset(state)

                matrices.pushPose()
                try {
                    matrices.translate(
                        entry.x - cameraX + renderOffset.x,
                        TotemPopState.renderedY(entry.y, rise, progress) - cameraY + renderOffset.y,
                        entry.z - cameraZ + renderOffset.z,
                    )
                    matrices.mulPose(Axis.YP.rotationDegrees(180f - entry.pose.bodyYaw))
                    matrices.scale(-1f, -1f, 1f)
                    val scale = TotemPopState.AVATAR_SCALE * state.scale
                    matrices.scale(scale, scale, scale)
                    matrices.translate(0f, TotemPopState.VANILLA_MODEL_Y, 0f)

                    val consumer = buffers.getBuffer(RenderTypes.entityTranslucentEmissive(entry.texture, false))
                    entry.model.renderToBuffer(
                        matrices,
                        consumer,
                        TotemPopState.FULL_BRIGHT_LIGHT,
                        OverlayTexture.NO_OVERLAY,
                        renderColor(parameters),
                    )
                } finally {
                    matrices.popPose()
                }
            }
        }
    }

    private fun renderColor(parameters: TotemPopRenderParameters): Int =
        ((parameters.alpha.coerceIn(0f, 1f) * 255f).toInt() shl 24) or parameters.rgb

    private data class CapturedPose(
        val yaw: Float,
        val bodyYaw: Float,
        val headYaw: Float,
        val pitch: Float,
        val limbAngle: Float,
        val limbSpeed: Float,
        val crouching: Boolean,
    ) {
        fun applyTo(entity: AbstractClientPlayer) {
            entity.setYRot(yaw)
            entity.yRotO = yaw
            entity.setXRot(pitch)
            entity.xRotO = pitch
            entity.yBodyRot = bodyYaw
            entity.yBodyRotO = bodyYaw
            entity.yHeadRot = headYaw
            entity.yHeadRotO = headYaw
            entity.walkAnimation.position = limbAngle
            entity.walkAnimation.setSpeed(limbSpeed)
            entity.attackAnim = 0f
            entity.swinging = false
            entity.swingTime = 0
        }

        fun applyTo(state: AvatarRenderState) {
            state.bodyRot = bodyYaw
            state.yRot = headYaw - bodyYaw
            state.xRot = pitch
            state.walkAnimationPos = limbAngle
            state.walkAnimationSpeed = min(limbSpeed, 1f)
            state.wornHeadAnimationPos = limbAngle
            state.isCrouching = crouching
            state.pose = if (crouching) Pose.CROUCHING else Pose.STANDING
            state.isAutoSpinAttack = false
            state.hasRedOverlay = false
            state.isSpectator = false
            state.showHat = true
            state.showJacket = true
            state.showLeftSleeve = true
            state.showRightSleeve = true
            state.showLeftPants = true
            state.showRightPants = true
            state.showCape = false
        }
    }

    private data class LivePose(
        val yaw: Float,
        val oldYaw: Float,
        val pitch: Float,
        val oldPitch: Float,
        val bodyYaw: Float,
        val oldBodyYaw: Float,
        val headYaw: Float,
        val oldHeadYaw: Float,
        val limbAngle: Float,
        val limbSpeed: Float,
        val attackAnimation: Float,
        val swinging: Boolean,
        val swingTime: Int,
    ) {
        fun restore(entity: AbstractClientPlayer) {
            entity.setYRot(yaw)
            entity.yRotO = oldYaw
            entity.setXRot(pitch)
            entity.xRotO = oldPitch
            entity.yBodyRot = bodyYaw
            entity.yBodyRotO = oldBodyYaw
            entity.yHeadRot = headYaw
            entity.yHeadRotO = oldHeadYaw
            entity.walkAnimation.position = limbAngle
            entity.walkAnimation.setSpeed(limbSpeed)
            entity.attackAnim = attackAnimation
            entity.swinging = swinging
            entity.swingTime = swingTime
        }

        companion object {
            fun capture(entity: AbstractClientPlayer) = LivePose(
                entity.yRot,
                entity.yRotO,
                entity.xRot,
                entity.xRotO,
                entity.yBodyRot,
                entity.yBodyRotO,
                entity.yHeadRot,
                entity.yHeadRotO,
                entity.walkAnimation.position(),
                entity.walkAnimation.speed(),
                entity.attackAnim,
                entity.swinging,
                entity.swingTime,
            )
        }
    }

    private class PopEntry(
        val entity: AbstractClientPlayer,
        val x: Double,
        val y: Double,
        val z: Double,
        val startTime: Long,
        val pose: CapturedPose,
        val texture: Identifier,
        modelType: PlayerModelType,
    ) {
        val model = PlayerModel(
            mc.entityModels.bakeLayer(TotemPopState.modelLayer(modelType)),
            TotemPopState.slim(modelType),
        )

        companion object {
            fun capture(entity: AbstractClientPlayer, now: Long): PopEntry {
                val skin = entity.skin
                return PopEntry(
                    entity,
                    entity.x,
                    entity.y,
                    entity.z,
                    now,
                    CapturedPose(
                        entity.yRot,
                        entity.yBodyRot,
                        entity.yHeadRot,
                        entity.xRot,
                        entity.walkAnimation.position(),
                        entity.walkAnimation.speed(),
                        entity.isShiftKeyDown,
                    ),
                    skin.body().texturePath(),
                    skin.model(),
                )
            }
        }
    }
}
