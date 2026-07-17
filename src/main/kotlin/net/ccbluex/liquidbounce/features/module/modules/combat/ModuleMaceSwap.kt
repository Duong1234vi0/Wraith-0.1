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

import net.ccbluex.liquidbounce.config.types.ChooseListValue
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.Value
import net.ccbluex.liquidbounce.config.types.ValueType
import net.ccbluex.liquidbounce.event.events.RotationUpdateEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.item.getEnchantment
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantments

/** Source-faithful V-Core MaceSwap dependency for Aura and EMaceHelper. */
object ModuleMaceSwap : ClientModule("MaceSwap", Category.COMBAT) {

    private val modeValue = ChooseListValue(
        "Mode",
        defaultValue = Mode.NORMAL,
        choices = enumValues<Mode>(),
        legacyChoiceMapper = { if (it == "None") Mode.NORMAL else null },
    ).also { inner.add(it) }
    private val mode get() = modeValue.get()

    private val maceStunValue = Value(
        "Mace Stun",
        aliases = arrayOf("MaceStun"),
        defaultValue = true,
        valueType = ValueType.BOOLEAN,
    ).also { inner.add(it) }
    private val switchBackValue = Value(
        "Switch Back",
        aliases = arrayOf("SwitchBack"),
        defaultValue = true,
        valueType = ValueType.BOOLEAN,
    ).also { inner.add(it) }
    private val silentValue = Value(
        "Silent",
        defaultValue = false,
        valueType = ValueType.BOOLEAN,
    ).also {
        it.visibleWhen { switchBackValue.get() }
        inner.add(it)
    }
    private val autoDisableValue = Value(
        "AutoDisable",
        defaultValue = false,
        valueType = ValueType.BOOLEAN,
    ).also { inner.add(it) }

    private val maceStun get() = maceStunValue.get()
    private val switchBack get() = switchBackValue.get()
    private val silent get() = silentValue.get()
    private val autoDisable get() = autoDisableValue.get()

    var isSwitching = false
        private set
    var previousSlot = -1
        private set
    var currentSwitchDelay = -1
        private set

    @JvmField
    var calledFromAura = false

    var auraPostAttackTriggered = false
        private set

    private var maceStunAttackInProgress = false

    override fun enable() {
        resetState()
        auraPostAttackTriggered = false
        calledFromAura = false
        maceStunAttackInProgress = false
    }

    override fun disable() {
        resetState()
        auraPostAttackTriggered = false
        calledFromAura = false
        maceStunAttackInProgress = false
    }

    @Suppress("unused")
    private val syncHandler = handler<RotationUpdateEvent> {
        processSwitchBack(manual = false)
        processAutoDisable()
    }

    fun isMaceStun() = maceStun

    fun isSilent() = silent

    fun hasMaceInHotbar() = findMaceInHotbar() != -1

    /** V-Core's source method; Aura owns the call immediately before its attack. */
    fun silentSwapToMace(): Int {
        val player = mc.player ?: return -1
        if (isSwitching) {
            return previousSlot
        }

        val currentSlot = player.inventory.selectedSlot
        val maceSlot = findMaceInHotbar()
        if (maceSlot == -1 || maceSlot == currentSlot) {
            return -1
        }

        previousSlot = currentSlot
        player.inventory.selectedSlot = maceSlot
        interaction.ensureHasSentCarriedItem()
        isSwitching = true
        currentSwitchDelay = -1

        if (!switchBack) {
            resetState()
        }

        return currentSlot
    }

    fun handleSwitchBackManual() = processSwitchBack(manual = true)

    fun beginMaceStunAttack() {
        maceStunAttackInProgress = true
    }

    fun endMaceStunAttack() {
        maceStunAttackInProgress = false
    }

    fun isMaceStunAttackInProgress() = maceStunAttackInProgress

    private fun processAutoDisable() {
        if (!autoDisable) {
            auraPostAttackTriggered = false
        } else if (auraPostAttackTriggered) {
            if (!isSwitching || !switchBack) {
                auraPostAttackTriggered = false
                calledFromAura = false
                disable()
            }
        }
    }

    private fun processSwitchBack(manual: Boolean) {
        if (isSwitching && switchBack) {
            val switchDelay = if (silent) -1 else 2
            if (currentSwitchDelay < switchDelay) {
                currentSwitchDelay++
            } else {
                mc.player?.let { player ->
                    if (previousSlot in 0..8) {
                        player.inventory.selectedSlot = previousSlot
                        interaction.ensureHasSentCarriedItem()
                    }
                }
                resetState()
                if (manual) {
                    calledFromAura = false
                }
            }
        } else if (manual) {
            calledFromAura = false
        }
    }

    private fun findMaceInHotbar(): Int {
        val player = mc.player ?: return -1
        for (slot in 0..8) {
            val stack = player.inventory.getStack(slot)
            if (stack.item == Items.MACE && matchesMode(stack)) {
                return slot
            }
        }
        return -1
    }

    private fun matchesMode(stack: ItemStack): Boolean = when (mode) {
        Mode.NORMAL -> true
        Mode.BREACH -> stack.getEnchantment(Enchantments.BREACH) > 0
        Mode.WIND_BURST -> stack.getEnchantment(Enchantments.WIND_BURST) > 0
    }

    private fun resetState() {
        previousSlot = -1
        currentSwitchDelay = -1
        isSwitching = false
    }

    @JvmStatic
    fun markAuraPostAttack(attacked: Boolean) {
        if (attacked) {
            auraPostAttackTriggered = true
        }
    }

    enum class Mode(override val choiceName: String) : NamedChoice {
        NORMAL("Normal"),
        BREACH("Breach"),
        WIND_BURST("WindBurst"),
    }
}
