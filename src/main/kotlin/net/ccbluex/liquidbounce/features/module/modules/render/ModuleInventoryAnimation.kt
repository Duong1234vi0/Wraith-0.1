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

import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen

/**
 * VCore 0.8 InventoryAnimation port.
 *
 * #Edit By Claude 2026-06-17: replaced the ThunderHack port (9 types + dim + close zoom-out + the
 * InventoryAnimationAddition close bridge) with VCore 0.8's much simpler effect
 * (`vcore.injection.MixinHandledScreen` / `MixinAbstractInventoryScreen`): a single time-based
 * easeOutBack scale-up from the screen centre when a container screen opens. No close animation, no
 * dim, no per-type modes — that is exactly what VCore does.
 *
 * 1.21.11 → 1.26.2 note: VCore wraps the single `render()`/`drawBackground` call. On 1.26.2 the window
 * chrome (`extractBackground`) and the items (`extractRenderState`) are extracted in SEPARATE passes,
 * so the transform is applied in [MixinScreen] around the orchestrator
 * `extractRenderStateWithTooltipAndSubtitles` (which runs both back-to-back) — otherwise only the items
 * would scale, not the window. The easing/duration logic here is VCore's verbatim.
 */
object ModuleInventoryAnimation : ClientModule("InventoryAnimation", Category.RENDER) {

    // VCore hardcodes 250ms; exposed as a setting so it can be tuned.
    private val duration by int("Duration", 250, 50..1000)

    fun shouldAnimate(screen: Screen): Boolean = running && screen is AbstractContainerScreen<*>

    fun getDurationMillis(): Float = duration.toFloat()

    /** VCore `getBackOutScale`: easeOutBack over [progress] in 0..1. */
    fun backOutScale(progress: Float): Float {
        val x = progress - 1.0F
        val c1 = 1.70158F
        val c3 = c1 + 1.0F
        return 1.0F + c3 * (x * x * x) + c1 * (x * x)
    }
}
