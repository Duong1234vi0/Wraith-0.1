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
package net.ccbluex.liquidbounce.features.module.modules.combat
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.SilentHotbar
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FINAL_DECISION
import net.ccbluex.liquidbounce.utils.item.getEnchantment
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.entity.wouldBlockHit
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

/**
 * ThunderHack MaceSwap port.
 *
 * Swaps to a matching mace before an attack and optionally switches back afterwards.
 */
object ModuleMaceSwap : ClientModule("MaceSwap", Category.COMBAT) {

    private val mode by enumChoice("Mode", Mode.NONE)
    private val maceStun by boolean("MaceStun", false)
    private val switchBack by boolean("SwitchBack", true)
    private val silent by boolean("Silent", false)

    private var isSwitching = false
    private var previousSlot = -1
    private var currentSwitchDelay = -1
    private var forcedAttackSlot = -1

    override fun enable() {
        resetState()
    }

    override fun disable() {
        SilentHotbar.resetSlot(this)
        resetState()
    }

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent>(priority = FINAL_DECISION) { event ->
        if (forcedAttackSlot != -1) {
            selectSlotForAttack(forcedAttackSlot)
            return@handler
        }

        if (maceStun && tryMaceStun(event)) {
            return@handler
        }

        swapToMace()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        handleSwitchBack()
    }

    private fun swapToMace(): Int {
        if (isSwitching) {
            return previousSlot
        }

        val currentSlot = player.inventory.selectedSlot
        val maceSlot = findMaceInHotbar()

        if (maceSlot == -1 || maceSlot == currentSlot) {
            return -1
        }

        previousSlot = currentSlot

        if (switchBack && silent) {
            SilentHotbar.selectSlotSilently(this, maceSlot, 1)
            interaction.ensureHasSentCarriedItem()
            isSwitching = true
            currentSwitchDelay = 0
            return currentSlot
        }

        swapToSlot(maceSlot)
        interaction.ensureHasSentCarriedItem()

        if (switchBack) {
            isSwitching = true
            currentSwitchDelay = -1
        } else {
            resetState()
        }

        return currentSlot
    }

    private fun tryMaceStun(event: AttackEntityEvent): Boolean {
        val target = event.entity as? LivingEntity ?: return false
        if (!target.isBlockingPlayerHit()) {
            return false
        }

        val axeSlot = findAxeInHotbar()
        val maceSlot = findMaceInHotbar()
        val currentSlot = player.inventory.selectedSlot

        if (axeSlot == -1 || maceSlot == -1) {
            return false
        }

        previousSlot = currentSlot
        event.cancelEvent()

        runForcedAttack(axeSlot, event)
        runForcedAttack(maceSlot, event)

        when {
            !switchBack -> resetState()
            silent -> {
                isSwitching = true
                currentSwitchDelay = 0
            }
            else -> {
                isSwitching = true
                currentSwitchDelay = -1
            }
        }

        return true
    }

    private fun runForcedAttack(slot: Int, event: AttackEntityEvent) {
        forcedAttackSlot = slot
        selectSlotForAttack(slot)
        event.caller()
        forcedAttackSlot = -1
    }

    private fun handleSwitchBack() {
        if (!isSwitching || !switchBack) {
            return
        }

        val switchDelay = if (silent) 1 else 2
        if (currentSwitchDelay < switchDelay) {
            currentSwitchDelay++
            return
        }

        if (silent) {
            restoreSilentSlot()
        } else {
            swapToSlot(previousSlot)
            interaction.ensureHasSentCarriedItem()
        }
        resetState()
    }

    private fun swapToSlot(slot: Int) {
        if (slot in 0..8) {
            player.inventory.selectedSlot = slot
        }
    }

    private fun selectSlotForAttack(slot: Int) {
        if (silent && switchBack) {
            SilentHotbar.selectSlotSilently(this, slot, 1)
        } else {
            swapToSlot(slot)
        }

        interaction.ensureHasSentCarriedItem()
    }

    private fun restoreSilentSlot() {
        SilentHotbar.resetSlot(this)
        interaction.ensureHasSentCarriedItem()
    }

    private fun findMaceInHotbar(): Int {
        for (slot in 0..8) {
            val stack = player.inventory.getStack(slot)
            if (stack.item == Items.MACE && stack.matchesMode()) {
                return slot
            }
        }

        return -1
    }

    /** Narrow bridge for Elytra mace support; keeps this module's existing mode predicate authoritative. */
    fun hasMaceInHotbar() = findMaceInHotbar() != -1

    private fun findAxeInHotbar(): Int {
        for (slot in 0..8) {
            if (player.inventory.getStack(slot).item is AxeItem) {
                return slot
            }
        }

        return -1
    }

    private fun LivingEntity.isBlockingPlayerHit(): Boolean {
        return isBlocking && wouldBlockHit(player)
    }

    private fun ItemStack.matchesMode(): Boolean {
        return when (mode) {
            Mode.NONE -> true
            Mode.BREACH -> getEnchantment(Enchantments.BREACH) > 0
            Mode.WIND_BURST -> getEnchantment(Enchantments.WIND_BURST) > 0
        }
    }

    private fun resetState() {
        previousSlot = -1
        currentSwitchDelay = -1
        isSwitching = false
        forcedAttackSlot = -1
    }

    private enum class Mode(override val choiceName: String) : NamedChoice {
        NONE("None"),
        BREACH("Breach"),
        WIND_BURST("WindBurst"),
    }

}
