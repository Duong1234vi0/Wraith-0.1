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
package net.ccbluex.liquidbounce.features.module.modules.render

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.utils.shiftHue
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.Vec3d
import java.util.UUID
import java.util.concurrent.CopyOnWriteArraySet
import kotlin.math.floor
import kotlin.math.pow

/**
 * ThunderHack JumpCircle port.
 */
@Suppress("MagicNumber")
object ModuleJumpCircle : ClientModule("JumpCircle", Category.RENDER) {

    private val mode by enumChoice("Mode", Mode.DEFAULT)
    private val easeOut by boolean("EaseOut", true)
    private val rotateSpeed by float("RotateSpeed", 2.0F, 0.5F..5.0F)
    private val circleScale by float("CircleScale", 1.0F, 0.5F..5.0F)
    private val onlySelf by boolean("OnlySelf", true)
    private val color by color("Color", Color4b.LIQUID_BOUNCE)
    private val gradient by boolean("Gradient", true)

    private val groundedPlayers = CopyOnWriteArraySet<UUID>()
    private val circles = mutableListOf<Circle>()

    private var lastWorldId = -1

    override fun enable() {
        reset()
        lastWorldId = getWorldId()
    }

    override fun disable() {
        reset()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val world = mc.world ?: run {
            reset()
            return@handler
        }

        val worldId = getWorldId()
        if (worldId != lastWorldId) {
            reset()
            lastWorldId = worldId
        }

        world.players.forEach { playerEntity ->
            if (shouldTrack(playerEntity) && playerEntity.isOnGround && !groundedPlayers.contains(playerEntity.uuid)) {
                groundedPlayers.add(playerEntity.uuid)
            }
        }

        groundedPlayers.toList().forEach { uuid ->
            val playerEntity = world.getPlayerByUuid(uuid)
            if (playerEntity == null) {
                groundedPlayers.remove(uuid)
                return@forEach
            }

            if (!playerEntity.isOnGround) {
                circles.add(Circle(Vec3d(playerEntity.x, floor(playerEntity.y) + 0.001, playerEntity.z)))
                groundedPlayers.remove(uuid)
            }
        }

        val lifetime = if (easeOut) EASE_OUT_LIFETIME_MS else LIFETIME_MS
        circles.removeIf { it.elapsed() >= lifetime }
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (circles.isEmpty()) {
            return@handler
        }

        RenderSystem.disableDepthTest()
        RenderSystem.depthMask(false)
        RenderSystem.disableCull()
        RenderSystem.enableBlend()
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
        RenderSystem.setShaderTexture(0, mode.texture)

        try {
            renderEnvironmentForWorld(event.matrixStack) {
                circles.asReversed().forEach { circle ->
                    renderCircle(circle)
                }
            }
        } finally {
            RenderSystem.depthMask(true)
            RenderSystem.defaultBlendFunc()
            RenderSystem.disableBlend()
            RenderSystem.enableCull()
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F)
            RenderSystem.enableDepthTest()
        }
    }

    private fun shouldTrack(playerEntity: PlayerEntity): Boolean {
        return playerEntity == mc.player || !onlySelf
    }

    private fun getWorldId(): Int {
        return mc.world?.registryKey?.value?.hashCode() ?: -1
    }

    private fun reset() {
        groundedPlayers.clear()
        circles.clear()
        lastWorldId = -1
    }

    private fun WorldRenderEnvironment.renderCircle(circle: Circle) {
        val elapsed = circle.elapsed().toFloat()
        val colorProgress = elapsed / LIFETIME_MS
        val scaleProgress = elapsed * (if (easeOut) 2.0F else 1.0F) / EASE_OUT_LIFETIME_MS
        val size = (circleScale - (1.0F - scaleProgress).pow(4)).coerceAtLeast(0.0F)
        val quadSize = size * 2.0F
        val alpha = (1.0F - colorProgress).coerceIn(0.0F, 1.0F)

        withPositionRelativeToCamera(circle.pos) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F))
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(size * rotateSpeed * 1000.0F))

            drawCustomMesh(
                VertexFormat.DrawMode.QUADS,
                VertexFormats.POSITION_TEXTURE_COLOR,
                ShaderProgramKeys.POSITION_TEX_COLOR
            ) { matrix ->
                vertex(matrix, -size, -size + quadSize, 0.0F)
                    .texture(0.0F, 1.0F)
                    .color(vertexColor(270, alpha))
                vertex(matrix, -size + quadSize, -size + quadSize, 0.0F)
                    .texture(1.0F, 1.0F)
                    .color(vertexColor(0, alpha))
                vertex(matrix, -size + quadSize, -size, 0.0F)
                    .texture(1.0F, 0.0F)
                    .color(vertexColor(180, alpha))
                vertex(matrix, -size, -size, 0.0F)
                    .texture(0.0F, 0.0F)
                    .color(vertexColor(90, alpha))
            }
        }
    }

    private fun vertexColor(hueOffset: Int, alpha: Float): Int {
        val baseColor = if (gradient) shiftHue(color, hueOffset) else color

        return baseColor.fade(alpha).toARGB()
    }

    private class Circle(val pos: Vec3d) {
        private val createdAt = System.currentTimeMillis()

        fun elapsed(): Long {
            return System.currentTimeMillis() - createdAt
        }
    }

    private enum class Mode(
        override val choiceName: String,
        val texture: Identifier
    ) : NamedChoice {
        DEFAULT("Default", "thunderhack/particles/circle.png".registerAsDynamicImageFromClientResources()),
        PORTAL("Portal", "thunderhack/particles/hitbubble.png".registerAsDynamicImageFromClientResources())
    }

    private const val LIFETIME_MS = 6000L
    private const val EASE_OUT_LIFETIME_MS = 5000L
}
