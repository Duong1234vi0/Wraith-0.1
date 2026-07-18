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
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.item.getEnchantment
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.READ_FINAL_STATE
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
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

    private var restoreReady = false
    private var lastMaceAttackBoundarySerial = Long.MIN_VALUE
    private var diagnosticsEnabled = false
    private var restoreWaitLogged = false

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

    /**
     * Restore emission must happen before Aura's next attack, but after the previous Mace-owned
     * attack has crossed Grim's natural client-tick boundary.
     */
    @Suppress("unused")
    private val preAuraRestoreHandler = handler<GameTickEvent>(priority = MACE_SWAP_PRE_AURA_PRIORITY) {
        emitReadySwitchBack()
    }

    @Suppress("unused")
    private val syncHandler = handler<GameTickEvent>(priority = MACE_SWAP_POST_AURA_PRIORITY) {
        runVCoreMaceSwapPostAuraSync(
            processSwitchBack = { processSwitchBack(manual = false) },
            processAutoDisable = ::processAutoDisable,
        )
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
        restoreReady = false
        lastMaceAttackBoundarySerial = Long.MIN_VALUE
        restoreWaitLogged = false
        diagnosticsEnabled = switchBack && ActionTickBoundary.claimDiagnosticCycle()
        ActionTickBoundary.diagnostic(
            diagnosticsEnabled,
            "MaceSwap selected mace previousSlot=$previousSlot maceSlot=$maceSlot " +
                "silent=$silent switchDelay=${if (silent) -1 else 2} currentSwitchDelay=$currentSwitchDelay",
        )

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

    /**
     * Own the natural action boundary only when this attack is part of an active MaceSwap switch.
     * The attack itself is never delayed or suppressed.
     */
    internal inline fun <T> withMaceAttackBoundary(block: () -> T): T {
        if (!isSwitching || !switchBack) {
            return block()
        }

        val action = ActionTickBoundary.beginAction(
            owner = "MaceSwap",
            kind = ActionTickBoundary.ActionKind.ATTACK,
            diagnosticsEnabled = diagnosticsEnabled,
        )
        ActionTickBoundary.diagnostic(
            diagnosticsEnabled,
            "MaceSwap attack boundary begin previousSlot=$previousSlot " +
                "currentSwitchDelay=$currentSwitchDelay restoreReady=$restoreReady",
        )

        return try {
            block()
        } finally {
            ActionTickBoundary.actionCallCompleted(action)
            if (ActionTickBoundary.wasActionObserved(action)) {
                lastMaceAttackBoundarySerial = ActionTickBoundary.currentSerial()
                restoreReady = false
                restoreWaitLogged = false
                ActionTickBoundary.diagnostic(
                    diagnosticsEnabled,
                    "MaceSwap attack observed boundarySerial=$lastMaceAttackBoundarySerial",
                )
            } else {
                ActionTickBoundary.cancelAction(action)
                ActionTickBoundary.diagnostic(
                    diagnosticsEnabled,
                    "MaceSwap attack not observed; restore uses next pre-Aura phase",
                )
            }
        }
    }

    private fun processAutoDisable() {
        when (maceSwapAutoDisableAction(autoDisable, auraPostAttackTriggered, isSwitching, switchBack)) {
            MaceSwapAutoDisableAction.CLEAR_TRIGGER -> auraPostAttackTriggered = false
            MaceSwapAutoDisableAction.WAIT -> Unit
            MaceSwapAutoDisableAction.DISABLE -> {
                auraPostAttackTriggered = false
                calledFromAura = false
                enabled = false
            }
        }
    }

    private fun processSwitchBack(manual: Boolean) {
        if (isSwitching && switchBack) {
            if (!restoreReady) {
                val switchDelay = if (silent) -1 else 2
                if (currentSwitchDelay < switchDelay) {
                    currentSwitchDelay++
                } else {
                    restoreReady = true
                    ActionTickBoundary.diagnostic(
                        diagnosticsEnabled,
                        "MaceSwap restoreReady tick=${mc.player?.tickCount ?: -1} " +
                            "previousSlot=$previousSlot maceSlot=${mc.player?.inventory?.selectedSlot ?: -1} " +
                            "silent=$silent currentSwitchDelay=$currentSwitchDelay " +
                            "attackBoundarySerial=$lastMaceAttackBoundarySerial",
                    )
                }
            }
            if (manual && restoreReady) {
                calledFromAura = false
            }
        } else if (manual) {
            calledFromAura = false
        }
    }

    private fun emitReadySwitchBack() {
        if (!restoreReady || !isSwitching || !switchBack) {
            return
        }

        val player = mc.player ?: return
        if (previousSlot !in 0..8) {
            ActionTickBoundary.diagnostic(
                diagnosticsEnabled,
                "MaceSwap cleared stale restore previousSlot=$previousSlot reason=invalid-slot",
            )
            resetState()
            return
        }

        if (lastMaceAttackBoundarySerial != Long.MIN_VALUE &&
            !ActionTickBoundary.hasPassed(lastMaceAttackBoundarySerial)
        ) {
            if (!restoreWaitLogged) {
                restoreWaitLogged = true
                ActionTickBoundary.diagnostic(
                    diagnosticsEnabled,
                    "MaceSwap restore waiting for natural boundary serial=$lastMaceAttackBoundarySerial " +
                        "currentSerial=${ActionTickBoundary.currentSerial()}",
                )
            }
            return
        }

        val expectation = ActionTickBoundary.expectHeldItemChange(
            owner = "MaceSwap",
            purpose = "switch-back",
            postActionBoundarySerial = lastMaceAttackBoundarySerial.takeIf { it != Long.MIN_VALUE },
            diagnosticsEnabled = diagnosticsEnabled,
        )
        if (!expectation.allowed) {
            return
        }

        try {
            player.inventory.selectedSlot = previousSlot
            interaction.ensureHasSentCarriedItem()
        } finally {
            ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
        }

        ActionTickBoundary.diagnostic(
            diagnosticsEnabled,
            "MaceSwap pre-Aura restored slot=$previousSlot " +
                "boundarySerial=$lastMaceAttackBoundarySerial auraMayReselectMace=true",
        )
        resetState()
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
        restoreReady = false
        lastMaceAttackBoundarySerial = Long.MIN_VALUE
        diagnosticsEnabled = false
        restoreWaitLogged = false
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

internal const val MACE_SWAP_POST_AURA_PRIORITY: Short = READ_FINAL_STATE
internal val MACE_SWAP_PRE_AURA_PRIORITY: Short = (FIRST_PRIORITY + 2).toShort()

internal inline fun runVCoreMaceSwapPostAuraSync(
    processSwitchBack: () -> Unit,
    processAutoDisable: () -> Unit,
) {
    processSwitchBack()
    processAutoDisable()
}

internal enum class MaceSwapAutoDisableAction {
    CLEAR_TRIGGER,
    WAIT,
    DISABLE,
}

internal fun maceSwapAutoDisableAction(
    autoDisable: Boolean,
    postAttackTriggered: Boolean,
    isSwitching: Boolean,
    switchBack: Boolean,
): MaceSwapAutoDisableAction = when {
    !autoDisable -> MaceSwapAutoDisableAction.CLEAR_TRIGGER
    !postAttackTriggered -> MaceSwapAutoDisableAction.WAIT
    !isSwitching || !switchBack -> MaceSwapAutoDisableAction.DISABLE
    else -> MaceSwapAutoDisableAction.WAIT
}
