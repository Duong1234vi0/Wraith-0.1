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
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.config.AutoConfig.loadingNow
import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleClickGui
import net.ccbluex.liquidbounce.utils.client.inGame
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import kotlin.math.pow

/**
 * Celestial-style module toggle sounds, implemented through Minecraft's native sound manager.
 */
object ModuleClientSound : ClientModule(
    "ClientSound",
    Category.MISC,
    aliases = arrayOf("ClientSounds", "ModuleSound")
) {

    private val soundMode = choices("SoundMode", Wav, arrayOf(Wav, Button)).apply(::tagBy)
    private val volume by int("Volume", 50, 1..100)

    /**
     * Called by [ClientModule] after a real module state change. The source module is explicit so this module can
     * play its own one-shot enable/disable preview without relying on the still-changing `enabled` state.
     */
    fun playToggleSound(sourceModule: ClientModule, enabled: Boolean) {
        val ownToggle = sourceModule === this

        if (!ownToggle) {
            if (!this.enabled || sourceModule === ModuleClickGui) {
                return
            }
        }

        if (loadingNow || !inGame) {
            return
        }

        runCatching {
            soundMode.activeChoice.play(enabled)
        }.onFailure {
            logger.warn("Failed to play module toggle sound.", it)
        }
    }

    private object Wav : SoundMode("Wav") {

        private val enableSound = SoundEvent.createVariableRangeEvent(
            Identifier.fromNamespaceAndPath("liquidbounce", "module_enable")
        )
        private val disableSound = SoundEvent.createVariableRangeEvent(
            Identifier.fromNamespaceAndPath("liquidbounce", "module_disable")
        )

        override fun play(enabled: Boolean) {
            val sound = if (enabled) enableSound else disableSound

            // #Edit By Codex 2026-07-02: preserve Celestial's dB curve as linear Minecraft UI volume.
            mc.soundManager.play(SimpleSoundInstance.forUI(sound, 1.0f, wavVolume()))
        }

        private fun wavVolume(): Float {
            val gainDb = -30.0 + volume.coerceIn(1, 100) * 0.3
            return 10.0.pow(gainDb / 20.0).toFloat().coerceIn(0.0f, 1.0f)
        }

    }

    private object Button : SoundMode("Button") {

        private val pitch by float("Pitch", 2.0f, 0.5f..2.0f)

        override fun play(enabled: Boolean) {
            mc.soundManager.play(
                SimpleSoundInstance.forUI(
                    SoundEvents.NOTE_BLOCK_PLING.value(),
                    pitch.coerceIn(0.5f, 2.0f),
                    (volume.coerceIn(1, 100) / 100.0f).coerceIn(0.0f, 1.0f)
                )
            )
        }

    }

    private sealed class SoundMode(name: String) : Choice(name) {
        override val parent: ChoiceConfigurable<*>
            get() = soundMode

        abstract fun play(enabled: Boolean)
    }

}
