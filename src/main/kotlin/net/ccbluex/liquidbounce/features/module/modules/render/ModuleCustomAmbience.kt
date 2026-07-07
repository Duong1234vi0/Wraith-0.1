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

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.GpuTexture
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.minecraft.client.renderer.fog.FogData
import net.minecraft.client.renderer.state.LightmapRenderState
import org.joml.Vector3f
import org.joml.Vector4f
import kotlin.math.max

/**
 * CustomAmbience module
 *
 * Override the ambience of the game.
 */
object ModuleCustomAmbience : ClientModule("CustomAmbience", Category.RENDER, aliases = arrayOf("FogChanger")) {

    val weather = enumChoice("Weather", WeatherType.SNOWY)
    private val time = enumChoice("Time", TimeType.NIGHT)

    object Precipitation : ToggleableConfigurable(this, "ModifyPrecipitation", true) {
        val gradient by float("Gradient", 0.7f, 0.1f..1f)
        val layers by int("Layers", 3, 1..14)
    }

    object FogConfigurable : ToggleableConfigurable(this, "Fog", true) {

        private val color by color("Color", Color4b(47, 128, 255, 201))
        private val backgroundColor by color("BackgroundColor", Color4b(47, 128, 255, 201))
        private val fogStart by float("Distance", 0f, -16f..2048f)
        private val density by float("Density", 10f, 0f..2048f)
        private val skyEnd by float("SkyEnd", 256f, 0f..2048f)
        private val cloudEnd by float("CloudEnd", 2048f, 0f..4096f)
        private val disableWorldFog by boolean("DisableWorldFog", false)
        @Suppress("unused")
        private val fogShape by enumChoice("FogShape", Shape.SPHERE)

        fun modifyFogColor(dest: Vector4f): Boolean {
            if (!running || color.a == 0) {
                return false
            }

            color.toVector4f(dest)
            return true
        }

        fun modifyFogData(fogData: FogData) {
            if (!running) {
                return
            }

            val start = fogStart
            val end = max(start, start + density)
            fogData.environmentalStart = start
            fogData.environmentalEnd = end
            fogData.renderDistanceStart = start
            fogData.renderDistanceEnd = end
            fogData.skyEnd = skyEnd
            fogData.cloudEnd = cloudEnd
        }

        fun modifyClearColor(original: Int): Int {
            if (!running || backgroundColor.a == 0) {
                return original
            }

            return backgroundColor.toARGB()
        }

        fun shouldDisableWorldFog(): Boolean = running && disableWorldFog

        @Suppress("unused")
        private enum class Shape(override val choiceName: String) : NamedChoice {
            SPHERE("Sphere"),
            CYLINDER("Cylinder")
        }
    }

    object CustomLightColor : ToggleableConfigurable(this, "CustomLightColor", false) {
        private val lightColor by color("LightColor", Color4b(70, 119, 255, 255))

        fun edit(texture: GpuTexture, renderState: LightmapRenderState): Boolean {
            if (!running || lightColor.a == 0) {
                return false
            }

            val colorVector = lightColor.toRgbVector3f()
            renderState.blockLightTint = colorVector
            renderState.skyLightColor = colorVector
            renderState.ambientColor = colorVector
            renderState.nightVisionColor = colorVector
            renderState.needsUpdate = true

            if (lightColor.a == 255) {
                RenderSystem.getDevice().createCommandEncoder().clearColorTexture(texture, lightColor.toARGB())
                return true
            }

            return false
        }
    }

    object ModifyWorld : ToggleableConfigurable(this, "ModifyWorld", false) {
        private val color by color("Color", Color4b(47, 128, 255, 201))

        fun modifyColor(original: Int): Int {
            if (!running || color.a == 0) {
                return original
            }

            return color.toARGB()
        }
    }

    init {
        tree(Precipitation)
        tree(FogConfigurable)
        tree(CustomLightColor)
        tree(ModifyWorld)
    }

    @JvmStatic
    fun getTime(original: Long): Long {
        return if (running) {
            when (time.get()) {
                TimeType.NO_CHANGE -> original
                TimeType.DAWN -> 23041L
                TimeType.DAY -> 1000L
                TimeType.NOON -> 6000L
                TimeType.DUSK -> 12610L
                TimeType.NIGHT -> 13000L
                TimeType.MID_NIGHT -> 18000L
            }
        } else {
            original
        }
    }

    @Suppress("unused")
    enum class WeatherType(override val choiceName: String) : NamedChoice {
        NO_CHANGE("NoChange"),
        SUNNY("Sunny"),
        RAINY("Rainy"),
        SNOWY("Snowy"),
        THUNDER("Thunder")
    }

    enum class TimeType(override val choiceName: String) : NamedChoice {
        NO_CHANGE("NoChange"),
        DAWN("Dawn"),
        DAY("Day"),
        NOON("Noon"),
        DUSK("Dusk"),
        NIGHT("Night"),
        MID_NIGHT("MidNight")
    }

    private fun Color4b.toVector4f(dest: Vector4f): Vector4f =
        dest.set(r / 255f, g / 255f, b / 255f, a / 255f)

    private fun Color4b.toRgbVector3f(): Vector3f =
        Vector3f(r / 255f, g / 255f, b / 255f)
}
