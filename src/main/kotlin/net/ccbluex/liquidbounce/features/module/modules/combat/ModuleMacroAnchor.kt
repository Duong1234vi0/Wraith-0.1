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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.ItemUseEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.blockPosition
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.getStack
import net.ccbluex.liquidbounce.utils.entity.interactBlock
import net.ccbluex.liquidbounce.utils.entity.isAccepted
import net.ccbluex.liquidbounce.utils.entity.mainHandItem
import net.ccbluex.liquidbounce.utils.entity.offhandItem
import net.ccbluex.liquidbounce.utils.entity.setPressed
import net.ccbluex.liquidbounce.utils.entity.side
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.useKey
import net.ccbluex.liquidbounce.utils.input.MacroAnchorClickSimulation
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.item.ShieldItem
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.RespawnAnchorBlock
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import org.lwjgl.glfw.GLFW
import kotlin.random.Random

/**
 * Strict Argon AnchorMacro port.
 *
 * This intentionally remains a crosshair-driven charge/detonation macro. It neither places anchors nor searches,
 * rotates, calculates damage, or selects targets.
 */
object ModuleMacroAnchor : ClientModule("MacroAnchor", Category.COMBAT) {

    private val whileUse by boolean("WhileUse", true)
    private val stopOnKill by boolean("StopOnKill", false)
    private val clickSimulation by boolean("ClickSimulation", false)
    private val placeChance by int("PlaceChance", 100, 0..100)
    private val switchDelay by int("SwitchDelay", 0, 0..20, "ticks")
    private val switchChance by int("SwitchChance", 100, 0..100)
    private val glowstoneDelay by int("GlowstoneDelay", 0, 0..20, "ticks")
    private val glowstoneChance by int("GlowstoneChance", 100, 0..100)
    private val explodeDelay by int("ExplodeDelay", 0, 0..20, "ticks")
    private val explodeChance by int("ExplodeChance", 100, 0..100)
    private val explodeSlot by int("ExplodeSlot", 1, 1..9)
    private val onlyOwn by boolean("OnlyOwn", false)
    private val onlyCharge by boolean("OnlyCharge", false)

    private var switchClock = 0
    private var glowstoneClock = 0
    private var explodeClock = 0

    // Argon intentionally retains ownership while this module is toggled within one world.
    private val ownedAnchors = hashSetOf<BlockPos>()

    override fun init() {
        // Keep lifecycle cleanup active even while MacroAnchor itself is disabled.
        MacroAnchorWorldLifecycle.initialize()
    }

    override fun enable() {
        resetClocks()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player ?: return@handler
        val world = mc.level ?: return@handler

        if (mc.screen != null) {
            return@handler
        }

        if (whileUse && isRightMouseDown() && isHoldingFoodOrShield(player)) {
            return@handler
        }

        if (stopOnKill && isDeadPlayerNearby(player)) {
            return@handler
        }

        // Preserve Argon's upper-bound-exclusive 1..99 random interval and initial charged-path roll.
        var randomValue = Random.Default.nextInt(1, 100)

        if (!isRightMouseDown()) {
            return@handler
        }

        val hit = mc.crosshairTarget as? BlockHitResult ?: return@handler
        val anchorPos = hit.blockPosition()

        if (world.getBlockState(anchorPos).block != Blocks.RESPAWN_ANCHOR) {
            return@handler
        }

        if (onlyOwn && anchorPos !in ownedAnchors) {
            return@handler
        }

        // This only suppresses vanilla's logical use key; it never changes raw GLFW input state.
        mc.options.useKey.setPressed(false)

        if (isUnchargedAnchor(world, anchorPos)) {
            randomValue = Random.Default.nextInt(1, 100)

            if (randomValue <= placeChance) {
                if (player.mainHandItem.item != Items.GLOWSTONE) {
                    if (switchClock != switchDelay) {
                        switchClock++
                        return@handler
                    }

                    randomValue = Random.Default.nextInt(1, 100)

                    if (randomValue <= switchChance) {
                        switchClock = 0
                        selectFirstGlowstone(player)
                    }
                }

                if (player.mainHandItem.item == Items.GLOWSTONE) {
                    if (glowstoneClock != glowstoneDelay) {
                        glowstoneClock++
                        return@handler
                    }

                    randomValue = Random.Default.nextInt(1, 100)

                    if (randomValue <= glowstoneChance) {
                        glowstoneClock = 0
                        interactAnchor(hit)
                    }
                }
            }
        }

        // Re-read block state after charging: useItemOn prediction may have changed it this tick.
        if (isChargedAnchor(world, anchorPos)) {
            val slot = explodeSlot - 1

            if (player.inventory.selectedSlot != slot) {
                if (switchClock != switchDelay) {
                    switchClock++
                    return@handler
                }

                // Argon intentionally reuses the current macro-path roll here.
                if (randomValue <= switchChance) {
                    switchClock = 0
                    player.inventory.selectedSlot = slot
                }
            }

            if (player.inventory.selectedSlot == slot) {
                if (explodeClock != explodeDelay) {
                    explodeClock++
                    return@handler
                }

                randomValue = Random.Default.nextInt(1, 100)

                if (randomValue <= explodeChance) {
                    explodeClock = 0

                    if (!onlyCharge) {
                        interactAnchor(hit)
                        // Argon removes ownership after the direct interaction attempt, accepted or not.
                        ownedAnchors.remove(anchorPos)
                    }
                }
            }
        }
    }

    /**
     * ItemUseEvent is fired only for physical Minecraft.startUseItem input. Direct macro interactions deliberately
     * bypass it, matching Argon's doItemUse hook placement.
     */
    @Suppress("unused")
    private val itemUseHandler = handler<ItemUseEvent> {
        val player = mc.player ?: return@handler
        val world = mc.level ?: return@handler
        val hit = mc.crosshairTarget as? BlockHitResult ?: return@handler

        if (hit.type != HitResult.Type.BLOCK) {
            return@handler
        }

        val anchorPos = hit.blockPosition()

        if (player.mainHandItem.item == Items.RESPAWN_ANCHOR) {
            val ownedPos = if (world.getBlockState(anchorPos).canBeReplaced()) {
                anchorPos
            } else {
                anchorPos.relative(hit.side)
            }

            // This is intentionally optimistic: no server-confirmation or later-event-cancellation check.
            ownedAnchors += ownedPos
        }

        if (isChargedAnchor(world, anchorPos)) {
            ownedAnchors.remove(anchorPos)
        }
    }

    private fun resetClocks() {
        switchClock = 0
        glowstoneClock = 0
        explodeClock = 0
    }

    private fun resetWorldState() {
        resetClocks()
        ownedAnchors.clear()
    }

    internal fun resetForWorldLifecycle() {
        resetWorldState()
    }

    private fun isHoldingFoodOrShield(player: Player): Boolean =
        player.mainHandItem.components.has(DataComponents.FOOD) ||
            player.offhandItem.components.has(DataComponents.FOOD) ||
            player.mainHandItem.item is ShieldItem ||
            player.offhandItem.item is ShieldItem

    private fun isDeadPlayerNearby(player: Player): Boolean = mc.level?.players()?.any { otherPlayer ->
        otherPlayer !== player && otherPlayer.distanceToSqr(player) <= 36.0 &&
            (otherPlayer.isDeadOrDying || otherPlayer.health <= 0.0f)
    } == true

    private fun isUnchargedAnchor(world: net.minecraft.client.multiplayer.ClientLevel, pos: BlockPos): Boolean {
        val state = world.getBlockState(pos)
        return state.block == Blocks.RESPAWN_ANCHOR && state.getValue(RespawnAnchorBlock.CHARGE) == 0
    }

    private fun isChargedAnchor(world: net.minecraft.client.multiplayer.ClientLevel, pos: BlockPos): Boolean {
        val state = world.getBlockState(pos)
        return state.block == Blocks.RESPAWN_ANCHOR && state.getValue(RespawnAnchorBlock.CHARGE) != 0
    }

    private fun selectFirstGlowstone(player: Player) {
        for (slot in 0..8) {
            if (player.inventory.getStack(slot).item == Items.GLOWSTONE) {
                // Keep Argon's visible selection behavior. useItemOn synchronizes it before a later interaction.
                player.inventory.selectedSlot = slot
                return
            }
        }
    }

    private fun interactAnchor(hit: BlockHitResult) {
        val player = mc.player ?: return
        val interaction = mc.gameMode ?: return

        if (clickSimulation) {
            MacroAnchorClickSimulation.recordRightClick()
        }

        val result = interaction.interactBlock(player, InteractionHand.MAIN_HAND, hit)
        if (result.isAccepted) {
            player.swingHand(InteractionHand.MAIN_HAND)
        }
    }

    private fun isRightMouseDown(): Boolean =
        GLFW.glfwGetMouseButton(mc.window.handle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS
}

/** Clears per-world ownership even when the toggleable module is currently disabled. */
private object MacroAnchorWorldLifecycle : EventListener {

    fun initialize() = Unit

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        ModuleMacroAnchor.resetForWorldLifecycle()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        ModuleMacroAnchor.resetForWorldLifecycle()
    }
}
