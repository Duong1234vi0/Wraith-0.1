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
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.GlStateManager
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.engine.type.Vec3
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.vertex
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.ccbluex.liquidbounce.utils.client.registerDynamicImage
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.Player
import java.io.File
import java.util.UUID
import java.util.concurrent.CopyOnWriteArraySet
import kotlin.math.floor
import kotlin.math.pow

/**
 * VCore-style JumpCircle.
 *
 * Spawns a circle particle on the ground each time a tracked player
 * leaves contact (jump / fall). Two-pass draw replicates VCore's glow
 * boost (alpha × 0.65 underlay + alpha × 1.0 top). Default texture is
 * VCore's `circle.png`; Custom mode loads
 * `<gamedir>/liquidbounce/jumpcircle/circle.png` lazily.
 *
 * NOTE: textures MUST be RGBA (colorType 6). NativeImage.read() rejects
 * grayscale PNGs, which would crash the module's static init.
 */
@Suppress("MagicNumber")
object ModuleJumpCircle : ClientModule("JumpCircle", Category.RENDER) {

    private val mode by enumChoice("Mode", Mode.DEFAULT)
    private val easeOut by boolean("EaseOut", true)
    private val rotateSpeed by float("RotateSpeed", 2.0F, 0.5F..5.0F)
    private val circleScale by float("CircleScale", 1.0F, 0.5F..5.0F)
    private val onlySelf by boolean("OnlySelf", true)
    private val color by color("Color", Color4b.WHITE)

    private const val LIFETIME_MS = 6000L
    private const val EASE_OUT_LIFETIME_MS = 5000L
    private const val GLOW_BOOST_ALPHA = 0.65F

    private val groundedPlayers = CopyOnWriteArraySet<UUID>()
    private val circles = mutableListOf<Circle>()
    private var lastWorldId = -1
    private var customTexture: Identifier? = null
    private var customWarned = false

    override fun enable() {
        reset()
        lastWorldId = getWorldId()
    }

    override fun disable() {
        reset()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val world = mc.level ?: run {
            reset()
            return@handler
        }

        val worldId = getWorldId()
        if (worldId != lastWorldId) {
            reset()
            lastWorldId = worldId
        }

        if (mode == Mode.CUSTOM && customTexture == null && !customWarned) {
            loadCustomTexture()
        }

        for (playerEntity in world.players()) {
            if (shouldTrack(playerEntity) &&
                playerEntity.onGround() &&
                !groundedPlayers.contains(playerEntity.uuid)
            ) {
                groundedPlayers.add(playerEntity.uuid)
            }
        }

        groundedPlayers.toList().forEach { uuid ->
            val playerEntity = world.getPlayerByUUID(uuid)
            if (playerEntity == null) {
                groundedPlayers.remove(uuid)
                return@forEach
            }
            if (!playerEntity.onGround()) {
                circles.add(Circle(Vec3(playerEntity.x, floor(playerEntity.y) + 0.001, playerEntity.z)))
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
        // #Edit By Claude: textures were rebaked so luminance lives in the
        // alpha channel (RGB = white). Use normal alpha blending so the black
        // background is transparent; SRC_ALPHA/ONE (additive) would only work
        // for the old grayscale-on-black textures meant for additive glow.
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA)
        RenderSystem.setShaderTexture(0, activeTexture())

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

    private fun activeTexture(): Identifier = when (mode) {
        Mode.DEFAULT -> Mode.DEFAULT.texture
        Mode.PORTAL -> Mode.PORTAL.texture
        Mode.CUSTOM -> customTexture ?: Mode.DEFAULT.texture
    }

    private fun shouldTrack(player: Player): Boolean = player == mc.player || !onlySelf

    private fun getWorldId(): Int = mc.level?.dimension()?.hashCode() ?: -1

    private fun reset() {
        groundedPlayers.clear()
        circles.clear()
        lastWorldId = -1
        customWarned = false
    }

    /**
     * Loads `<gamedir>/liquidbounce/jumpcircle/circle.png` once per
     * enable. [customWarned] guards against repeated attempts/spam.
     */
    private fun loadCustomTexture() {
        customWarned = true
        val file = File(mc.gameDirectory, "liquidbounce/jumpcircle/circle.png")
        if (!file.exists()) {
            chat(
                "§c[JumpCircle] Custom texture missing. " +
                    "Place an RGBA PNG at .minecraft/liquidbounce/jumpcircle/circle.png",
            )
            customTexture = null
            return
        }
        runCatching {
            val id = Identifier.fromNamespaceAndPath(
                "liquidbounce",
                "jumpcircle-custom-" + System.currentTimeMillis().toString(36),
            )
            file.inputStream().use { id.registerDynamicImage(it) }
            customTexture = id
        }.onFailure { throwable ->
            logger.error("Failed to load JumpCircle custom texture", throwable)
            customTexture = null
        }
    }

    /**
     * Two-pass draw replicates VCore's glow boost: a softer underlay at
     * 65% alpha plus the full-strength quad on top.
     */
    private fun WorldRenderEnvironment.renderCircle(circle: Circle) {
        val elapsed = circle.elapsed().toFloat()
        val colorProgress = elapsed / LIFETIME_MS
        val scaleProgress = elapsed * (if (easeOut) 2.0F else 1.0F) / EASE_OUT_LIFETIME_MS
        val size = (circleScale - (1.0F - scaleProgress).pow(4)).coerceAtLeast(0.0F)
        val quadSize = size * 2.0F
        val alpha = (1.0F - colorProgress).coerceIn(0.0F, 1.0F)

        withPositionRelativeToCamera(circle.pos) {
            matrixStack.multiply(Axis.XP.rotationDegrees(90.0F))
            matrixStack.multiply(Axis.ZP.rotationDegrees(size * rotateSpeed * 1000.0F))
            drawQuad(size, quadSize, alpha * GLOW_BOOST_ALPHA)
            drawQuad(size, quadSize, alpha)
        }
    }

    private fun WorldRenderEnvironment.drawQuad(size: Float, quadSize: Float, alpha: Float) {
        val argb = color.fade(alpha).toARGB()
        drawCustomMesh(
            VertexFormat.Mode.QUADS,
            DefaultVertexFormat.POSITION_TEX_COLOR,
            ShaderProgramKeys.POSITION_TEX_COLOR,
        ) { matrix ->
            vertex(matrix, -size, -size + quadSize, 0.0F).setUv(0.0F, 1.0F).setColor(argb)
            vertex(matrix, -size + quadSize, -size + quadSize, 0.0F).setUv(1.0F, 1.0F).setColor(argb)
            vertex(matrix, -size + quadSize, -size, 0.0F).setUv(1.0F, 0.0F).setColor(argb)
            vertex(matrix, -size, -size, 0.0F).setUv(0.0F, 0.0F).setColor(argb)
        }
    }

    private class Circle(val pos: Vec3) {
        private val createdAt = System.currentTimeMillis()
        fun elapsed(): Long = System.currentTimeMillis() - createdAt
    }

    private enum class Mode(
        override val choiceName: String,
        val texture: Identifier,
    ) : NamedChoice {
        DEFAULT("Default", "vcore/particles/circle.png".registerAsDynamicImageFromClientResources()),
        PORTAL("Portal", "vcore/particles/hit_bubble.png".registerAsDynamicImageFromClientResources()),

        // CUSTOM's `texture` field is a placeholder; the renderer resolves
        // the user PNG lazily via [ModuleJumpCircle.customTexture].
        CUSTOM("Custom", DEFAULT.texture),
    }
}
