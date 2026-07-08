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
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.DeathEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaDlcRenderPipelines
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaDlcWorldRenderer
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.block.getOutlineShape
import net.minecraft.client.gui.screens.DeathScreen
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundRespawnPacket
import net.minecraft.util.Mth
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import kotlin.math.max
import kotlin.math.pow

/**
 * AlphaDLC BlockOverlay replacement for the old BlockOutline implementation.
 */
object ModuleBlockOutline : ClientModule("BlockOverlay", Category.RENDER) {

    private val mode by enumChoice("Mode", Mode.SHADER)
    private val style by enumChoice("Style", Style.ENERGY).apply {
        doNotIncludeWhen { mode != Mode.SHADER }
    }
    private val color by color("Color", Color4b(150, 130, 255, 175))
    private val speed by float("Speed", 1.0f, 0.35f..2.75f).apply {
        doNotIncludeWhen { mode != Mode.SHADER }
    }
    private val opacity by float("Opacity", 0.9f, 0.1f..1.0f).apply {
        doNotIncludeWhen { mode != Mode.SHADER }
    }
    private val outline by boolean("Outline", true)
    private val outlineWidth by float("OutlineWidth", 2.0f, 1.0f..4.0f).apply {
        doNotIncludeWhen { !outline }
    }
    private val smoothness by int("Smoothness", 5, 1..10)

    private var smoothX = 0.0
    private var smoothY = 0.0
    private var smoothZ = 0.0
    private var smoothInitialized = false

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val player = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        if (!player.isAlive || player.health <= 0.0f || mc.screen is DeathScreen) {
            return@handler
        }

        val target = mc.hitResult
        if (target !is BlockHitResult || target.type != HitResult.Type.BLOCK) {
            return@handler
        }

        val blockPos = target.blockPos
        val shape = level.getBlockState(blockPos).getOutlineShape(level, blockPos)
        if (shape.isEmpty) {
            return@handler
        }

        updateSmoothPosition(blockPos, event.partialTicks)

        val renderPos = BlockPos.containing(smoothX, smoothY, smoothZ)
        val offsetShape = shape.move(
            smoothX - renderPos.x,
            smoothY - renderPos.y,
            smoothZ - renderPos.z
        )
        val cameraPos = event.camera.position()
        val modelView = event.matrixStack.last().pose()

        var shaderDrawn = false
        if (mode == Mode.SHADER) {
            val time = ((level.gameTime % 1200L) + event.partialTicks) * 0.045f * speed
            shaderDrawn = AlphaDlcWorldRenderer.drawShaderShape(
                modelView,
                cameraPos,
                offsetShape,
                renderPos,
                style.pipelineStyle,
                color,
                time,
                opacity
            )
        }

        if (!shaderDrawn) {
            AlphaDlcWorldRenderer.drawShapeAlternative(
                modelView,
                cameraPos,
                renderPos,
                offsetShape,
                color,
                outlineWidth,
                fill = true,
                lines = outline,
                depth = true
            )
        } else if (outline) {
            AlphaDlcWorldRenderer.drawShapeAlternative(
                modelView,
                cameraPos,
                renderPos,
                offsetShape,
                color.with(a = max(140, color.a)),
                outlineWidth,
                fill = false,
                lines = true,
                depth = true
            )
        }
    }

    @Suppress("unused")
    private val deathHandler = handler<DeathEvent> {
        resetSmoothing()
    }

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin == TransferOrigin.INCOMING && event.packet is ClientboundRespawnPacket) {
            resetSmoothing()
        }
    }

    override fun disable() {
        resetSmoothing()
    }

    private fun updateSmoothPosition(blockPos: BlockPos, partialTicks: Float) {
        val targetX = blockPos.x.toDouble()
        val targetY = blockPos.y.toDouble()
        val targetZ = blockPos.z.toDouble()

        if (!smoothInitialized || smoothness <= 1) {
            smoothX = targetX
            smoothY = targetY
            smoothZ = targetZ
            smoothInitialized = true
            return
        }

        val t = Mth.clamp((11.0 - smoothness) / 10.0, 0.05, 1.0)
        var step = t * t * 0.85 + 0.008
        step = Mth.clamp(step, 0.008, 1.0)
        step = 1.0 - (1.0 - step).pow(partialTicks.toDouble())

        smoothX = Mth.lerp(step, smoothX, targetX)
        smoothY = Mth.lerp(step, smoothY, targetY)
        smoothZ = Mth.lerp(step, smoothZ, targetZ)
    }

    private fun resetSmoothing() {
        smoothInitialized = false
        smoothX = 0.0
        smoothY = 0.0
        smoothZ = 0.0
    }

    @JvmStatic
    fun shouldSuppressVanillaOutline(): Boolean = running

    private enum class Mode(override val choiceName: String) : NamedChoice {
        NORMAL("Normal"),
        SHADER("Shader")
    }

    private enum class Style(
        override val choiceName: String,
        val pipelineStyle: AlphaDlcRenderPipelines.BlockShaderStyle
    ) : NamedChoice {
        ENERGY("Energy", AlphaDlcRenderPipelines.BlockShaderStyle.ENERGY),
        PLASMA("Plasma", AlphaDlcRenderPipelines.BlockShaderStyle.PLASMA),
        HONEYCOMB("Honeycomb", AlphaDlcRenderPipelines.BlockShaderStyle.HONEYCOMB)
    }
}
