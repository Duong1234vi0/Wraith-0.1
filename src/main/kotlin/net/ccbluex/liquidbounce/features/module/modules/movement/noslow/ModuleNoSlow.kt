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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.event.events.BlockSlipperinessMultiplierEvent
import net.ccbluex.liquidbounce.event.events.BlockVelocityMultiplierEvent
import net.ccbluex.liquidbounce.event.events.PlayerSneakMultiplier
import net.ccbluex.liquidbounce.event.events.PlayerUseMultiplier
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.core.component.DataComponents
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks

/**
 * V-Core 0.8 NoSlow port.
 */
object ModuleNoSlow : ClientModule("NoSlow", Category.MOVEMENT) {

    private enum class Mode(override val choiceName: String) : NamedChoice {
        Grim("Grim"),
        GrimTick("GrimTick"),
        Matrix("Matrix"),
        Normal("Normal")
    }

    private val mode by enumChoice("Mode", Mode.Normal)
    private val mainHand by boolean("MainHand", true)

    private object Selection : Configurable("Selection") {
        val food by boolean("Food", true)
        val projectiles by boolean("Projectiles", false)
        val shield by boolean("Shield", true)
        val soulSand by boolean("SoulSand", false)
        val honey by boolean("Honey", false)
        val slime by boolean("Slime", false)
        val ice by boolean("Ice", false)
        val sweetBerryBush by boolean("SweetBerryBush", false)
        val sneak by boolean("Sneak", false)
        val crawl by boolean("Crawl", false)
    }

    init {
        tree(Selection)
    }

    private var grimTickCounter = 0
    private var grimTickReady = false

    private var cachedTick = Int.MIN_VALUE
    private var cachedUsingItem = false
    private var cachedHand: InteractionHand? = null
    private var cachedItem: Item? = null
    private var cachedUseTime = Int.MIN_VALUE
    private var cachedSettingsFingerprint = 0
    private var cachedNoSlowDecision = false

    private var preserveNextUseItemPacketRotation = false

    @Suppress("unused")
    private val tickUpdater = tickHandler {
        val localPlayer = mc.player ?: return@tickHandler

        updateGrimTickState()

        if (!isUsingSlowItem()) {
            return@tickHandler
        }

        if (mode == Mode.Grim) {
            if (localPlayer.usedItemHand == InteractionHand.OFF_HAND) {
                val selectedSlot = localPlayer.inventory.selectedSlot
                network.sendPacket(ServerboundSetCarriedItemPacket(selectedSlot % 8 + 1))
                network.sendPacket(ServerboundSetCarriedItemPacket(selectedSlot % 7 + 2))
                network.sendPacket(ServerboundSetCarriedItemPacket(selectedSlot))
            } else if (mainHand && (localPlayer.ticksUsingItem <= 3 || localPlayer.tickCount % 2 == 0)) {
                interaction.sendSequencedPacket(world) { sequence ->
                    createGrimUseItemPacket(sequence)
                }
            }
        }
    }

    @Suppress("unused")
    private val useMultiplierHandler = handler<PlayerUseMultiplier> { event ->
        if (!shouldApplyMatrixInput()) {
            return@handler
        }

        val localPlayer = mc.player ?: return@handler
        val multiplier = matrixInputMultiplier(localPlayer.input.movementForward, localPlayer.input.movementSideways)

        event.forward = multiplier
        event.sideways = multiplier
    }

    @Suppress("unused")
    private val sneakMultiplierHandler = handler<PlayerSneakMultiplier> { event ->
        val localPlayer = mc.player ?: return@handler
        if (localPlayer.isVisuallyCrawling) {
            if (Selection.crawl) {
                event.multiplier = 1.0
            }
        } else if (Selection.sneak) {
            event.multiplier = 1.0
        }
    }

    @Suppress("unused")
    private val blockVelocityHandler = handler<BlockVelocityMultiplierEvent> { event ->
        if (Selection.soulSand && event.block === Blocks.SOUL_SAND) {
            event.multiplier = Blocks.DIRT.speedFactor
        }

        if (Selection.honey && event.block === Blocks.HONEY_BLOCK) {
            event.multiplier = Blocks.DIRT.speedFactor
        }
    }

    @Suppress("unused")
    private val blockSlipperinessHandler = handler<BlockSlipperinessMultiplierEvent> { event ->
        if (Selection.slime && event.block === Blocks.SLIME_BLOCK) {
            event.slipperiness = Blocks.DIRT.friction
        }

        if (Selection.ice && isIceBlock(event.block) && !mc.options.jumpKey.isPressed) {
            event.slipperiness = Blocks.DIRT.friction
        }
    }

    override fun disable() {
        clearDecisionCache()
    }

    fun canNoSlow(): Boolean {
        val localPlayer = mc.player ?: return true

        return mode != Mode.Matrix &&
            consumeGrimTickWindow() &&
            allowsActiveItem() &&
            allowsActiveHand() &&
            !isGrimNewBlocked(localPlayer)
    }

    fun shouldBypassItemSlowdownForCurrentTick(): Boolean {
        val localPlayer = mc.player
        if (!running || localPlayer == null) {
            clearDecisionCache()
            return false
        }

        val usingItem = localPlayer.isUsingItem
        val hand = if (usingItem) localPlayer.usedItemHand else null
        val item = if (usingItem) localPlayer.useItem.item else null
        val useTime = if (usingItem) localPlayer.ticksUsingItem else 0
        val settingsFingerprint = settingsFingerprint()

        if (cachedTick == localPlayer.tickCount &&
            cachedUsingItem == usingItem &&
            cachedHand == hand &&
            cachedItem === item &&
            cachedUseTime == useTime &&
            cachedSettingsFingerprint == settingsFingerprint) {
            return cachedNoSlowDecision
        }

        cachedTick = localPlayer.tickCount
        cachedUsingItem = usingItem
        cachedHand = hand
        cachedItem = item
        cachedUseTime = useTime
        cachedSettingsFingerprint = settingsFingerprint
        cachedNoSlowDecision = canNoSlow()
        return cachedNoSlowDecision
    }

    fun shouldBypassSneakSprintCheck(): Boolean {
        val localPlayer = mc.player ?: return false
        return running && Selection.sneak && localPlayer.isCrouching && !localPlayer.isVisuallyCrawling
    }

    fun shouldCancelSlimeStep(): Boolean = running && Selection.slime

    fun shouldCancelSweetBerryBushCollision(): Boolean = running && Selection.sweetBerryBush

    fun shouldPreserveUseItemPacketRotation(): Boolean = preserveNextUseItemPacketRotation

    fun shouldApplyMatrixKeyboardInput(): Boolean {
        val localPlayer = mc.player ?: return false
        return shouldApplyMatrixInput() && localPlayer.isPassenger
    }

    fun matrixInputMultiplier(movementForward: Float, movementSideways: Float): Float {
        val localPlayer = mc.player ?: return 1.0f
        val movingDiagonally = movementForward != 0.0f && movementSideways != 0.0f

        return if (localPlayer.onGround()) {
            if (movingDiagonally) 0.35f else 0.5f
        } else {
            if (movingDiagonally) 0.47f else 0.67f
        }
    }

    private fun updateGrimTickState() {
        if (mode == Mode.GrimTick && isUsingSlowItem()) {
            if (grimTickCounter < 20) {
                grimTickCounter++
            }

            grimTickReady = grimTickCounter > 1
        } else {
            grimTickCounter = 0
            grimTickReady = false
        }
    }

    private fun consumeGrimTickWindow(): Boolean {
        if (mode != Mode.GrimTick) {
            return true
        }

        if (!grimTickReady) {
            return false
        }

        grimTickReady = false
        grimTickCounter = 0
        return true
    }

    private fun isUsingSlowItem(): Boolean {
        val localPlayer = mc.player ?: return false
        return localPlayer.isUsingItem && !localPlayer.isPassenger && !localPlayer.isFallFlying
    }

    private fun allowsActiveItem(): Boolean {
        val localPlayer = mc.player ?: return false
        val active = localPlayer.useItem

        if (!Selection.food && active.components.has(DataComponents.FOOD)) {
            return false
        }

        if (!Selection.shield && active.item === Items.SHIELD) {
            return false
        }

        return Selection.projectiles ||
            active.item !== Items.CROSSBOW &&
            active.item !== Items.BOW &&
            active.item !== Items.TRIDENT
    }

    private fun allowsActiveHand(): Boolean {
        val localPlayer = mc.player ?: return false
        return mainHand || localPlayer.usedItemHand != InteractionHand.MAIN_HAND
    }

    private fun isGrimNewBlocked(localPlayer: net.minecraft.client.player.LocalPlayer): Boolean {
        val offhand = localPlayer.offhandItem
        return mode == Mode.Grim &&
            localPlayer.usedItemHand == InteractionHand.MAIN_HAND &&
            (offhand.components.has(DataComponents.FOOD) || offhand.item === Items.SHIELD)
    }

    private fun shouldApplyMatrixInput(): Boolean {
        val localPlayer = mc.player ?: return false
        return mode == Mode.Matrix && localPlayer.isUsingItem && !localPlayer.isFallFlying
    }

    private fun createGrimUseItemPacket(sequence: Int): ServerboundUseItemPacket {
        val localPlayer = mc.player
        preserveNextUseItemPacketRotation = true
        try {
            return ServerboundUseItemPacket(
                InteractionHand.OFF_HAND,
                sequence,
                localPlayer?.yaw ?: 0.0f,
                localPlayer?.pitch ?: 0.0f
            )
        } finally {
            preserveNextUseItemPacketRotation = false
        }
    }

    private fun settingsFingerprint(): Int {
        var result = mode.ordinal
        result = 31 * result + mainHand.hashCode()
        result = 31 * result + Selection.food.hashCode()
        result = 31 * result + Selection.projectiles.hashCode()
        result = 31 * result + Selection.shield.hashCode()
        return result
    }

    private fun clearDecisionCache() {
        cachedTick = Int.MIN_VALUE
        cachedUsingItem = false
        cachedHand = null
        cachedItem = null
        cachedUseTime = Int.MIN_VALUE
        cachedSettingsFingerprint = 0
        cachedNoSlowDecision = false
        preserveNextUseItemPacketRotation = false
    }

    private fun isIceBlock(block: net.minecraft.world.level.block.Block): Boolean {
        return block === Blocks.ICE ||
            block === Blocks.PACKED_ICE ||
            block === Blocks.BLUE_ICE ||
            block === Blocks.FROSTED_ICE
    }
}
