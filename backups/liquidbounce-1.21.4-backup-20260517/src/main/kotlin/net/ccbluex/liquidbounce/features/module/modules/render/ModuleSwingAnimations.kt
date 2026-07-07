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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule

/**
 * Dreamcore Swing Animations port.
 */
object ModuleSwingAnimations : ClientModule("SwingAnimations", Category.RENDER) {

    val mode by enumChoice("Mode", Mode.SMOOTH_TRANSLATE)
    val animationSmooth by int("AnimationSmooth", 5, 0..20)
    val animationStrength by int("AnimationStrength", 70, 10..100)
    val rotationAngle by int("RotationAngle", 40, 0..70)

    fun isSwingless() = running && mode == Mode.SWINGLESS

    enum class Mode(
        override val choiceName: String
    ) : NamedChoice {
        SMOOTH_TRANSLATE("Smooth Translate"),
        JUMP_TRANSLATE("Jump Translate"),
        SMOOTH_OLD("Smooth Old"),
        SMOOTH("Smooth"),
        DEFAULT("Default"),
        BIG("Big"),
        FAP("Fap"),
        SNAP("Snap"),
        SMOOTH_BACK("Smooth Back"),
        SWINGLESS("Swingless")
    }

}
