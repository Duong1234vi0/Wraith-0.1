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
package net.ccbluex.liquidbounce.features.module.modules.render.betterworld

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.Std140Builder
import com.mojang.blaze3d.buffers.Std140SizeCalculator
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.shaders.UniformType
import com.mojang.blaze3d.systems.RenderSystem
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.fog.FogData
import net.minecraft.resources.Identifier
import org.joml.Matrix4f
import org.joml.Matrix4fc
import org.joml.Vector3f
import org.lwjgl.system.MemoryStack
import java.awt.Color
import java.util.Optional
import java.util.OptionalInt

/**
 * Current RenderPipeline implementation of Xyeta's legacy `wild:sky` ShaderProgram.
 */
object BetterWorldSkyPipeline : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val PIPELINE_PATH = "pipeline/better_world_sky"
    private val CONFIG_UBO_SIZE = Std140SizeCalculator()
        .putVec4()
        .putVec4()
        .putMat4f()
        .putMat4f()
        .putVec4()
        .putVec4()
        .putVec4()
        .putVec4()
        .putVec4()
        .get()

    private val vertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/better_world_sky")
    private val fragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/better_world_sky")

    private val vertexSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/better_world/sky.vsh")
    }

    private val fragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/better_world/sky.fsh")
    }

    private val pipeline: RenderPipeline = RenderPipeline.builder(RenderPipelines.POST_PROCESSING_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, PIPELINE_PATH))
        .withVertexShader(vertexId)
        .withFragmentShader(fragmentId)
        .withUniform("BetterWorldSkyConfig", UniformType.UNIFORM_BUFFER)
        .withColorTargetState(ColorTargetState.DEFAULT)
        .withDepthStencilState(Optional.empty())
        .withCull(false)
        .build()

    private val configUbo = MappableRingBuffer(
        Suppliers.ofInstance("LiquidBounce BetterWorld sky config"),
        GpuBuffer.USAGE_UNIFORM or GpuBuffer.USAGE_MAP_WRITE,
        CONFIG_UBO_SIZE
    )

    @Volatile
    private var unavailable = false
    private var errorLogged = false
    private var cachedThemeColor = Int.MIN_VALUE
    private var cachedThemeColors = ThemeColors.compute(cachedThemeColor)

    val isAvailable: Boolean
        get() = !unavailable

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
        ShaderType.FRAGMENT -> if (identifier == fragmentId) fragmentSource else null
    }

    fun render(level: ClientLevel, projectionMatrix: Matrix4fc, viewMatrix: Matrix4fc, fogData: FogData): Boolean {
        if (unavailable) {
            return false
        }

        return runCatching {
            RenderSystem.assertOnRenderThread()
            val uniformBuffer = uploadUniforms(level, projectionMatrix, viewMatrix, fogData)
            val target = mc.mainRenderTarget
            val colorAttachment = RenderSystem.outputColorTextureOverride ?: target.colorTextureView ?: return false

            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Suppliers.ofInstance("LiquidBounce BetterWorld sky"),
                colorAttachment,
                OptionalInt.empty()
            ).use { pass ->
                pass.setPipeline(pipeline)
                RenderSystem.bindDefaultUniforms(pass)
                pass.setUniform("BetterWorldSkyConfig", uniformBuffer)
                pass.draw(0, 3)
            }

            configUbo.rotate()
            true
        }.onFailure { throwable ->
            unavailable = true
            logFailure("BetterWorld sky shader failed during rendering; falling back to vanilla sky.", throwable)
        }.getOrDefault(false)
    }

    private fun uploadUniforms(
        level: ClientLevel,
        projectionMatrix: Matrix4fc,
        viewMatrix: Matrix4fc,
        fogData: FogData
    ): GpuBuffer {
        val theme = themeColors()
        val gameTime = ((Math.floorMod(level.gameTime, 24000L).toFloat() +
            mc.deltaTracker.getGameTimeDeltaPartialTick(false)) / 24000.0f)
        val inverseProjection = Matrix4f(projectionMatrix).invert()
        val inverseView = Matrix4f(viewMatrix).invert()

        MemoryStack.stackPush().use { stack ->
            val data = Std140Builder.onStack(stack, CONFIG_UBO_SIZE)
                .putVec4(fogData.color)
                .putVec4(gameTime, fogData.environmentalStart, fogData.environmentalEnd, ThemeColors.NEB_INTENSITY)
                .putMat4f(inverseProjection)
                .putMat4f(inverseView)
                .putVec4(theme.skyZenith.x, theme.skyZenith.y, theme.skyZenith.z, 0.0f)
                .putVec4(theme.skyHorizon.x, theme.skyHorizon.y, theme.skyHorizon.z, 0.0f)
                .putVec4(theme.nebColor1.x, theme.nebColor1.y, theme.nebColor1.z, 0.0f)
                .putVec4(theme.nebColor2.x, theme.nebColor2.y, theme.nebColor2.z, 0.0f)
                .putVec4(theme.starColor.x, theme.starColor.y, theme.starColor.z, 0.0f)
                .get()

            val buffer = configUbo.currentBuffer()
            RenderSystem.getDevice().createCommandEncoder().mapBuffer(buffer, false, true).use { mapped ->
                mapped.data().put(data)
            }

            return buffer
        }
    }

    private fun themeColors(): ThemeColors {
        val color = ClientTheme.accentA
        if (color != cachedThemeColor) {
            cachedThemeColor = color
            cachedThemeColors = ThemeColors.compute(color)
        }
        return cachedThemeColors
    }

    fun precompile() {
        runCatching {
            RenderSystem.getDevice().precompilePipeline(pipeline, this)
            unavailable = false
            errorLogged = false
        }.onFailure { throwable ->
            unavailable = true
            logFailure("Failed to precompile BetterWorld sky pipeline; vanilla sky will be used.", throwable)
        }
    }

    private fun logFailure(message: String, throwable: Throwable) {
        if (!errorLogged) {
            errorLogged = true
            LiquidBounce.logger.error(message, throwable)
        }
    }

    private data class ThemeColors(
        val skyZenith: Vector3f,
        val skyHorizon: Vector3f,
        val nebColor1: Vector3f,
        val nebColor2: Vector3f,
        val starColor: Vector3f
    ) {
        companion object {
            const val NEB_INTENSITY: Float = 0.75f

            fun compute(argb: Int): ThemeColors {
                val red = argb shr 16 and 0xFF
                val green = argb shr 8 and 0xFF
                val blue = argb and 0xFF
                val hsb = Color.RGBtoHSB(red, green, blue, null)
                val hue = hsb[0]
                val saturation = hsb[1]
                val brightness = hsb[2]

                return ThemeColors(
                    skyZenith = hsbToRgb(hue, saturation * 0.9f, brightness * 0.12f),
                    skyHorizon = hsbToRgb(hue, saturation * 0.7f, brightness * 0.28f),
                    nebColor1 = hsbToRgb(shiftHue(hue, 20.0f), saturation * 1.1f, brightness * 0.65f),
                    nebColor2 = hsbToRgb(shiftHue(hue, -15.0f), saturation * 0.85f, brightness * 0.4f),
                    starColor = hsbToRgb(shiftHue(hue, 40.0f), saturation * 0.9f, brightness * 0.85f)
                )
            }

            private fun shiftHue(hue: Float, degrees: Float): Float {
                val shifted = hue + degrees / 360.0f
                return shifted - kotlin.math.floor(shifted)
            }

            private fun hsbToRgb(hue: Float, saturation: Float, brightness: Float): Vector3f {
                val packed = Color.HSBtoRGB(
                    hue,
                    saturation.coerceIn(0.0f, 1.0f),
                    brightness.coerceIn(0.0f, 1.0f)
                )
                return Vector3f(
                    (packed shr 16 and 0xFF) / 255.0f,
                    (packed shr 8 and 0xFF) / 255.0f,
                    (packed and 0xFF) / 255.0f
                )
            }
        }
    }
}
