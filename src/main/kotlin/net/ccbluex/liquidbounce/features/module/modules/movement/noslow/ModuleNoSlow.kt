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
package net.ccbluex.liquidbounce.features.module.modules.movement.noslow

import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Items

/**
 * NoSlow — port of VCore 0.8 NoSlow GrimTick mode.
 *
 * GrimTick: counts ticks the player is using a slow item (capped at 20).
 * Becomes "ready" after 2 consecutive ticks. canNoSlow() consumes the window
 * (one no-slow pulse, then resets), so the predictor sees normal slowdown
 * on the surrounding ticks and a single mismatch lands inside Grim's
 * uncertainty buffer.
 */
object ModuleNoSlow : ClientModule("NoSlow", Category.MOVEMENT) {

    private val mainHand by boolean("MainHand", true)
    private val food by boolean("Food", true)
    private val projectiles by boolean("Projectiles", false)
    private val shield by boolean("Shield", true)

    private var grimTickCounter = 0
    private var grimTickReady = false

    @Suppress("unused")
    private val tickUpdater = tickHandler {
        if (isUsingSlowItem()) {
            if (grimTickCounter < 20) {
                grimTickCounter++
            }
            grimTickReady = grimTickCounter > 1
        } else {
            resetGrimTickState()
        }
    }

    override fun disable() {
        resetGrimTickState()
    }

    fun canNoSlow(): Boolean {
        if (!running || mc.player == null) {
            return false
        }
        if (!grimTickReady) {
            return false
        }
        if (!allowsActiveItem() || !allowsActiveHand()) {
            return false
        }
        grimTickReady = false
        grimTickCounter = 0
        return true
    }

    private fun isUsingSlowItem(): Boolean {
        val p = mc.player ?: return false
        return p.isUsingItem && !p.isPassenger && !p.isFallFlying
    }

    private fun allowsActiveItem(): Boolean {
        val p = mc.player ?: return false
        val active = p.useItem
        if (!food && active.components.has(DataComponents.FOOD)) {
            return false
        }
        if (!shield && active.item === Items.SHIELD) {
            return false
        }
        if (!projectiles &&
            (active.item === Items.CROSSBOW || active.item === Items.BOW || active.item === Items.TRIDENT)) {
            return false
        }
        return true
    }

    private fun allowsActiveHand(): Boolean {
        val p = mc.player ?: return false
        return mainHand || p.usedItemHand != InteractionHand.MAIN_HAND
    }

    private fun resetGrimTickState() {
        grimTickCounter = 0
        grimTickReady = false
    }
}
