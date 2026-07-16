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
package net.ccbluex.liquidbounce.features.module.modules.misc

import com.mojang.blaze3d.platform.InputConstants
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.KeyboardKeyEvent
import net.ccbluex.liquidbounce.event.events.MouseButtonEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.isAccepted
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.input.InputBind
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Items
import org.lwjgl.glfw.GLFW

/**
 * Strict Argon KeyPearl port.
 *
 * This is deliberately a Hotbar-only hold-to-repeat throw macro. It does not search inventory or Offhand,
 * rotate, refill, or guard the source's missing-Pearl interaction quirk.
 */
object ModuleKeyPearl : ClientModule("KeyPearl", Category.MISC) {

    private val activateKey by bind(
        "ActivateKey",
        InputBind(InputConstants.UNKNOWN, InputBind.BindAction.HOLD)
    )
    private val delay by int("Delay", 0, 0..20, "ticks")
    private val switchBack by boolean("SwitchBack", true)
    private val switchDelay by int("SwitchDelay", 0, 0..20, "ticks")

    private var active = false
    private var hasActivated = false
    private var clock = 0
    private var previousSlot = -1
    private var switchClock = 0
    private var selectedPearlSlot = -1

    // A direct restore after useItem is a HELD_ITEM_CHANGE after USE_ITEM. Grim keeps
    // rightClicking true until the actual client tick-end packet, so the restore must
    // remain pending until that packet has left the client.
    private var waitingForPostUseBoundary = false
    private var postUseBoundarySerial = Long.MIN_VALUE
    private var cycleDiagnosticsEnabled = false

    private var observedPlayer: Any? = null
    private var observedLevel: Any? = null
    private var observedActivationKey = InputConstants.UNKNOWN
    private var activationBindEventDown = false

    override fun enable() {
        observedPlayer = mc.player
        observedLevel = mc.level
        reset()
    }

    override fun disable() {
        observedPlayer = null
        observedLevel = null
        activationBindEventDown = false
        reset()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player
        val level = mc.level

        if (player == null || level == null) {
            if (observedPlayer != null || observedLevel != null) {
                observedPlayer = null
                observedLevel = null
                reset()
            }
            return@handler
        }

        if (observedPlayer !== player || observedLevel !== level) {
            observedPlayer = player
            observedLevel = level
            reset()
        }

        // Argon pauses an already-active cycle while a screen is open instead of clearing it.
        if (mc.screen != null) {
            return@handler
        }

        // A released activation bind never aborts an already-active cycle. Complete its safe
        // post-use restore first, then let the normal activation path decide the next cycle.
        if (waitingForPostUseBoundary) {
            handlePostUseBoundary(gameMode = mc.gameMode ?: return@handler, player = player)
            return@handler
        }

        if (isActivationDown()) {
            active = true
        }

        if (!active) {
            return@handler
        }

        val gameMode = mc.gameMode ?: return@handler

        if (previousSlot == -1) {
            previousSlot = player.inventory.selectedSlot
            cycleDiagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle()
        }

        // Intentionally ignore the result. With no Hotbar pearl, Argon uses the still-selected MainHand item.
        selectFirstHotbarPearl()

        if (clock < delay) {
            clock++
            return@handler
        }

        if (!hasActivated) {
            val action = ActionTickBoundary.beginAction(
                owner = "KeyPearl",
                kind = ActionTickBoundary.ActionKind.USE_ITEM,
                diagnosticsEnabled = cycleDiagnosticsEnabled
            )
            if (selectedPearlSlot in 0..8 && selectedPearlSlot != previousSlot) {
                ActionTickBoundary.expectHeldItemChange(
                    owner = "KeyPearl",
                    purpose = "selected pearl slot",
                    action = action,
                    diagnosticsEnabled = cycleDiagnosticsEnabled
                )
            }
            ActionTickBoundary.diagnostic(
                cycleDiagnosticsEnabled,
                "KeyPearl useItem called selectedSlot=${player.inventory.selectedSlot} " +
                    "previousSlot=$previousSlot switchClock=$switchClock"
            )

            // 26.1.2 useItem calls ensureHasSentCarriedItem itself, matching Argon's selection-then-interact path.
            val result = gameMode.useItem(player, InteractionHand.MAIN_HAND)
            ActionTickBoundary.actionCallCompleted(action)
            if (result.isAccepted) {
                player.swingHand(InteractionHand.MAIN_HAND)
            }

            // The source advances the cycle even when useItem returns a rejected result.
            hasActivated = true
            if (!switchBack) {
                reset()
                return@handler
            }

            postUseBoundarySerial = ActionTickBoundary.currentSerial()
            waitingForPostUseBoundary = true
            ActionTickBoundary.diagnostic(
                cycleDiagnosticsEnabled,
                "KeyPearl waiting post-use boundary serial=$postUseBoundarySerial " +
                    "selectedSlot=${player.inventory.selectedSlot} previousSlot=$previousSlot switchClock=$switchClock"
            )
            return@handler
        }

        switchBack(gameMode, player)
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        observedPlayer = null
        observedLevel = null
        reset()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        observedPlayer = null
        observedLevel = null
        reset()
    }

    @Suppress("unused")
    private val activationKeyboardHandler = handler<KeyboardKeyEvent> { event ->
        updateActivationBindState(
            activateKey,
            activateKey.matchesKey(event.keyCode, event.scanCode),
            event.action
        )
    }

    @Suppress("unused")
    private val activationMouseHandler = handler<MouseButtonEvent> { event ->
        updateActivationBindState(activateKey, activateKey.matchesMouse(event.button), event.action)
    }

    private fun selectFirstHotbarPearl() {
        val player = mc.player ?: return

        for (slot in 0..8) {
            if (player.inventory.getItem(slot).item == Items.ENDER_PEARL) {
                // Selection remains visible and is synchronized by the later useItem call.
                val previousSelectedSlot = player.inventory.selectedSlot
                player.inventory.selectedSlot = slot
                selectedPearlSlot = slot
                if (previousSelectedSlot != slot) {
                    ActionTickBoundary.diagnostic(
                        cycleDiagnosticsEnabled,
                        "KeyPearl selected pearl slot=$slot previousSlot=$previousSlot switchClock=$switchClock"
                    )
                }
                return
            }
        }

        selectedPearlSlot = -1
    }

    private fun handlePostUseBoundary(
        gameMode: net.minecraft.client.multiplayer.MultiPlayerGameMode,
        player: net.minecraft.client.player.LocalPlayer,
    ) {
        if (!ActionTickBoundary.hasPassed(postUseBoundarySerial)) {
            return
        }

        waitingForPostUseBoundary = false
        ActionTickBoundary.diagnostic(
            cycleDiagnosticsEnabled,
            "KeyPearl boundary passed serial=${ActionTickBoundary.currentSerial()} " +
                "postUseSerial=$postUseBoundarySerial selectedSlot=${player.inventory.selectedSlot} " +
                "previousSlot=$previousSlot switchClock=$switchClock"
        )
        switchBack(gameMode, player)
    }

    private fun switchBack(
        gameMode: net.minecraft.client.multiplayer.MultiPlayerGameMode,
        player: net.minecraft.client.player.LocalPlayer,
    ) {
        if (switchClock < switchDelay) {
            switchClock++
            return
        }

        if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                owner = "KeyPearl",
                actionBoundarySerial = postUseBoundarySerial,
                diagnosticsEnabled = cycleDiagnosticsEnabled
            )
        ) {
            // Keep the active cycle and pending restore intact. A later natural tick boundary
            // will retry without emitting an unsafe HELD_ITEM_CHANGE.
            return
        }

        ActionTickBoundary.diagnostic(
            cycleDiagnosticsEnabled,
            "KeyPearl restoring previous slot=$previousSlot selectedSlot=${player.inventory.selectedSlot} " +
                "boundarySerial=$postUseBoundarySerial switchClock=$switchClock"
        )
        val expectation = ActionTickBoundary.expectHeldItemChange(
            owner = "KeyPearl",
            purpose = "restoring previous slot",
            postActionBoundarySerial = postUseBoundarySerial,
            diagnosticsEnabled = cycleDiagnosticsEnabled
        )
        if (!expectation.allowed) {
            return
        }

        player.inventory.selectedSlot = previousSlot
        // Unlike useItem, this direct restoration needs the one explicit selected-slot synchronization.
        gameMode.ensureHasSentCarriedItem()
        ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
        reset()
    }

    private fun reset() {
        if (previousSlot != -1) {
            ActionTickBoundary.diagnostic(
                cycleDiagnosticsEnabled,
                "KeyPearl cycle reset selectedSlot=$selectedPearlSlot previousSlot=$previousSlot " +
                    "boundarySerial=$postUseBoundarySerial switchClock=$switchClock"
            )
        }
        previousSlot = -1
        clock = 0
        switchClock = 0
        selectedPearlSlot = -1
        waitingForPostUseBoundary = false
        postUseBoundarySerial = Long.MIN_VALUE
        cycleDiagnosticsEnabled = false
        active = false
        hasActivated = false
    }

    private fun updateActivationBindState(bind: InputBind, matches: Boolean, action: Int) {
        if (observedActivationKey != bind.boundKey) {
            observedActivationKey = bind.boundKey
            activationBindEventDown = false
        }

        if (matches) {
            activationBindEventDown = action != GLFW.GLFW_RELEASE
        }
    }

    private fun isActivationDown(): Boolean {
        val bind = activateKey
        if (bind.isUnbound) {
            // Unlike V-core AutoCrystal, Argon's default -1 activation bind never activates this module.
            return false
        }

        val key = bind.boundKey
        if (observedActivationKey != key) {
            observedActivationKey = key
            activationBindEventDown = false
        }

        return when (key.type) {
            InputConstants.Type.MOUSE -> isMouseDown(key.value)
            InputConstants.Type.KEYSYM -> InputConstants.isKeyDown(mc.window, key.value)
            InputConstants.Type.SCANCODE -> activationBindEventDown
        }
    }

    private fun isMouseDown(button: Int): Boolean =
        GLFW.glfwGetMouseButton(mc.window.handle(), button) == GLFW.GLFW_PRESS
}
