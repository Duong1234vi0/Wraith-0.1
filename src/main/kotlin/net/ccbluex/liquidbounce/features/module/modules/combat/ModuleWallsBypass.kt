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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule

/** V-Core WallsBypass dependency queried by Aura Track/Grim. */
object ModuleWallsBypass : ClientModule("WallsBypass", Category.COMBAT) {

    private val mode by enumChoice("Mode", Mode.YAW_OFFSET)

    fun isYawOffset() = mode == Mode.YAW_OFFSET

    fun isPeekHigh() = mode == Mode.PEEK_HIGH

    enum class Mode(override val choiceName: String) : NamedChoice {
        YAW_OFFSET("YawOffset"),
        PEEK_HIGH("PeekHigh"),
    }
}
