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
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.betterworld.BetterWorldSkyPipeline
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.fog.FogData
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.client.renderer.state.level.SkyRenderState
import net.minecraft.core.Holder
import net.minecraft.network.protocol.game.ClientboundSetTimePacket
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.clock.ClockNetworkState
import net.minecraft.world.clock.WorldClock
import net.minecraft.world.clock.WorldClocks
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.material.FogType
import org.joml.Matrix4fc
import java.util.LinkedHashMap
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * Xyeta/Wild BetterWorld replacement for the previous CustomAmbience module.
 *
 * The old CustomAmbience world-color, precipitation, fog-density, lightmap, and clear-color
 * features are intentionally not kept because they are not part of Xyeta BetterWorld.
 */
@Suppress("MagicNumber")
object ModuleBetterWorld : ClientModule(
    "BetterWorld",
    Category.RENDER,
    aliases = arrayOf("CustomAmbience", "FogChanger")
) {

    private val timeChangerValue = boolean("TimeChanger", false).onChanged { enabled ->
        if (enabled) {
            applyForcedClientDayTime()
        } else {
            restoreServerTime()
        }
    }
    private val timeChanger by timeChangerValue
    private val time by float("Time", 1.0f, 1.0f..40.0f)
        .visibleWhen { timeChanger }
        .onChange { value -> value.roundToInt().coerceIn(1, 40).toFloat() }
        .onChanged { applyForcedClientDayTime() }

    private val fullBrightValue = boolean("FullBright", false).onChanged { enabled ->
        if (!enabled) {
            restoreNightVision()
        }
    }
    private val fullBright by fullBrightValue

    private val fog by boolean("Fog", false)
    private val startFog by float("StartFog", 3.0f, 0.0f..20.0f)
        .visibleWhen { fog }
        .onChange { value -> value.roundToInt().coerceIn(0, 20).toFloat() }
    private val endFog by float("EndFog", 50.0f, 0.0f..80.0f)
        .visibleWhen { fog }
        .onChange { value -> value.roundToInt().coerceIn(0, 80).toFloat() }
    private val alphaFog by float("AlphaFog", 90.0f, 30.0f..100.0f)
        .visibleWhen { fog }
        .onChange { value -> ((value / 5.0f).roundToInt() * 5).coerceIn(30, 100).toFloat() }

    private val skyShader by boolean("SkyShader", false)

    private val weather by boolean("Weather", false)
    private val weatherMode by enumChoice("WeatherMode", WeatherMode.CLEAR)
        .visibleWhen { weather }

    private var savedNightVision: SavedNightVision? = null
    private var ownsNightVision = false
    private var cachedServerTime: CachedServerTime? = null

    override fun disable() {
        restoreNightVision()
        restoreServerTime()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (fullBright) {
            refreshNightVision()
        } else if (ownsNightVision) {
            restoreNightVision()
        }

        if (timeChanger) {
            applyForcedClientDayTime()
        }
    }

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        val packet = event.packet
        if (timeChanger && event.origin == TransferOrigin.INCOMING && packet is ClientboundSetTimePacket) {
            cachedServerTime = CachedServerTime(packet.gameTime(), LinkedHashMap(packet.clockUpdates()))
            event.cancelEvent()
            applyForcedClientDayTime()
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        restoreNightVision()
        cachedServerTime = null
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        restoreNightVision()
        cachedServerTime = null
    }

    private fun refreshNightVision() {
        val player = mc.player ?: return
        val level = mc.level ?: return
        val current = player.getEffect(MobEffects.NIGHT_VISION)

        if (!ownsNightVision || !isBetterWorldNightVision(current)) {
            savedNightVision = current?.let { SavedNightVision(MobEffectInstance(it), level.gameTime) }
        }

        player.addEffect(MobEffectInstance(MobEffects.NIGHT_VISION, 400, 2), player)
        ownsNightVision = true
    }

    private fun restoreNightVision() {
        val player = mc.player
        val level = mc.level
        val saved = savedNightVision

        if (player != null && ownsNightVision) {
            val current = player.getEffect(MobEffects.NIGHT_VISION)
            if (isBetterWorldNightVision(current)) {
                player.removeEffect(MobEffects.NIGHT_VISION)
                val restored = saved?.agedCopy(level?.gameTime ?: saved.capturedAtGameTime)
                if (restored != null) {
                    player.addEffect(restored, player)
                }
            }
        }

        savedNightVision = null
        ownsNightVision = false
    }

    private fun isBetterWorldNightVision(effect: MobEffectInstance?): Boolean {
        return effect != null &&
            effect.`is`(MobEffects.NIGHT_VISION) &&
            effect.amplifier == 2 &&
            !effect.isAmbient &&
            effect.isVisible &&
            effect.showIcon() &&
            !effect.isInfiniteDuration &&
            effect.duration in 1..400
    }

    @JvmStatic
    fun shouldOverrideWeather(): Boolean = running && weather

    @JvmStatic
    fun rainingOverride(): Boolean = when (weatherMode) {
        WeatherMode.CLEAR -> false
        WeatherMode.RAIN, WeatherMode.STORM -> true
    }

    @JvmStatic
    fun rainLevelOverride(): Float = when (weatherMode) {
        WeatherMode.CLEAR -> 0.0f
        WeatherMode.RAIN, WeatherMode.STORM -> 1.0f
    }

    @JvmStatic
    fun thunderLevelOverride(): Float = when (weatherMode) {
        WeatherMode.CLEAR, WeatherMode.RAIN -> 0.0f
        WeatherMode.STORM -> 1.0f
    }

    @JvmStatic
    fun modifyFogData(fogData: FogData) {
        if (!running || !fog) {
            return
        }

        val color = ClientTheme.accentA
        val red = (color shr 16 and 0xFF) / 255.0f
        val green = (color shr 8 and 0xFF) / 255.0f
        val blue = (color and 0xFF) / 255.0f
        val alpha = (alphaFog / 100.0f).coerceIn(0.0f, 1.0f)
        val start = startFog.coerceAtLeast(0.0f)
        val end = endFog.coerceAtLeast(start)

        fogData.color.set(red, green, blue, alpha)
        fogData.environmentalStart = start
        fogData.environmentalEnd = end
        fogData.renderDistanceStart = start
        fogData.renderDistanceEnd = end
    }

    @JvmStatic
    fun cylinderFogEnvironmentalDistance(original: Float): Float {
        return if (running && fog) {
            CYLINDER_ENVIRONMENTAL_DISTANCE
        } else {
            original
        }
    }

    @JvmStatic
    fun shouldRenderCustomSky(cameraState: CameraRenderState, state: SkyRenderState, level: ClientLevel?): Boolean {
        if (!running || !skyShader || level == null || !BetterWorldSkyPipeline.isAvailable) {
            return false
        }

        if (state.skybox != DimensionType.Skybox.OVERWORLD) {
            return false
        }

        if (cameraState.fogType == FogType.LAVA ||
            cameraState.fogType == FogType.POWDER_SNOW ||
            cameraState.entityRenderState.doesMobEffectBlockSky
        ) {
            return false
        }

        val tickDelta = mc.deltaTracker.getGameTimeDeltaPartialTick(false)
        val skyAngle = skyAngle(effectiveClientDayTime(level), tickDelta)
        return skyAngle >= 0.26f && skyAngle <= 0.74f
    }

    @JvmStatic
    fun renderCustomSky(projectionMatrix: Matrix4fc, viewMatrix: Matrix4fc, fogData: FogData) {
        val level = mc.level ?: return
        BetterWorldSkyPipeline.render(level, projectionMatrix, viewMatrix, fogData)
    }

    private fun applyForcedClientDayTime() {
        if (!running || !timeChanger || mc.player == null) {
            return
        }

        val level = mc.level ?: return
        val updates = clockUpdates(level, forcedDayTime(), rate = 0.0f)
        if (updates.isNotEmpty()) {
            level.clockManager().handleUpdates(level.gameTime, updates)
            level.environmentAttributes().invalidateTickCache()
        }
    }

    private fun restoreServerTime() {
        val level = mc.level ?: run {
            cachedServerTime = null
            return
        }
        val cached = cachedServerTime ?: return
        val updates = cached.agedClockUpdates(level.gameTime)
        if (updates.isNotEmpty()) {
            level.clockManager().handleUpdates(level.gameTime, updates)
            level.environmentAttributes().invalidateTickCache()
        }
        cachedServerTime = null
    }

    private fun clockUpdates(
        level: ClientLevel,
        dayTime: Long,
        partialTick: Float = 0.0f,
        rate: Float
    ): Map<Holder<WorldClock>, ClockNetworkState> {
        val state = ClockNetworkState(dayTime, partialTick, rate)
        val updates = LinkedHashMap<Holder<WorldClock>, ClockNetworkState>()

        level.dimensionType().defaultClock().ifPresent { clock ->
            updates[clock] = state
        }
        level.registryAccess().get(WorldClocks.OVERWORLD).ifPresent { clock ->
            updates[clock] = state
        }

        return updates
    }

    private fun forcedDayTime(): Long = (time * 500.0f).toLong()

    private fun effectiveClientDayTime(level: ClientLevel): Long {
        return if (running && timeChanger) {
            forcedDayTime()
        } else {
            level.defaultClockTime
        }
    }

    private fun skyAngle(dayTime: Long, tickDelta: Float): Float {
        var time = (Math.floorMod(dayTime, 24000L).toFloat() + tickDelta) / 24000.0f - 0.25f
        if (time < 0.0f) {
            time += 1.0f
        }
        if (time > 1.0f) {
            time -= 1.0f
        }

        val eased = 1.0f - ((cos(time * PI.toFloat()) + 1.0f) / 2.0f)
        return time + (eased - time) / 3.0f
    }

    private data class SavedNightVision(
        val instance: MobEffectInstance,
        val capturedAtGameTime: Long
    ) {
        fun agedCopy(nowGameTime: Long): MobEffectInstance? {
            val remaining = if (instance.isInfiniteDuration) {
                MobEffectInstance.INFINITE_DURATION
            } else {
                val elapsed = (nowGameTime - capturedAtGameTime).coerceAtLeast(0L).coerceAtMost(Int.MAX_VALUE.toLong())
                instance.duration - elapsed.toInt()
            }

            if (remaining != MobEffectInstance.INFINITE_DURATION && remaining <= 0) {
                return null
            }

            return MobEffectInstance(
                instance.effect,
                remaining,
                instance.amplifier,
                instance.isAmbient,
                instance.isVisible,
                instance.showIcon()
            )
        }
    }

    private data class CachedServerTime(
        val gameTime: Long,
        val clockUpdates: Map<Holder<WorldClock>, ClockNetworkState>
    ) {
        fun agedClockUpdates(nowGameTime: Long): Map<Holder<WorldClock>, ClockNetworkState> {
            val elapsed = (nowGameTime - gameTime).coerceAtLeast(0L).toDouble()

            return clockUpdates.mapValues { (_, state) ->
                val totalPartialTicks = state.partialTick().toDouble() + elapsed * state.rate().toDouble()
                val wholeTicks = floor(totalPartialTicks).toLong()
                val partialTick = (totalPartialTicks - wholeTicks.toDouble()).toFloat()
                ClockNetworkState(state.totalTicks() + wholeTicks, partialTick, state.rate())
            }
        }
    }

    private enum class WeatherMode(override val choiceName: String) : NamedChoice {
        CLEAR("Clear"),
        RAIN("Rain"),
        STORM("Storm")
    }

    private const val CYLINDER_ENVIRONMENTAL_DISTANCE = 1_000_000.0f
}
