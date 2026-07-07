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
import net.ccbluex.liquidbounce.event.events.BlockSlipperinessMultiplierEvent
import net.ccbluex.liquidbounce.event.events.BlockVelocityMultiplierEvent
import net.ccbluex.liquidbounce.event.events.PlayerUseMultiplier
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Hand

/**
 * Unique 1.7 NoSlow port.
 */
object ModuleNoSlow : ClientModule("NoSlow", Category.MOVEMENT) {

    private val mode by enumChoice("Mode", Mode.GRIM).apply { tagBy(this) }

    private val mainHand by boolean("MainHand", true)
    private val food by boolean("Food", true)
    private val projectiles by boolean("Projectiles", false)
    private val shield by boolean("Shield", true)

    private val soulSand by boolean("SoulSand", false)
    private val honey by boolean("Honey", false)
    private val slime by boolean("Slime", false)
    private val ice by boolean("Ice", true)
    private val sweetBerryBush by boolean("SweetBerryBush", false)
    private val sneak by boolean("Sneak", false)
    private val crawl by boolean("Crawl", false)

    private var grimLatestTicks = 0
    private var grimLatestLastNoSlowTick = -1

    @Suppress("unused")
    private val tickHandler = tickHandler {
        if (mode == Mode.GRIM && !player.isGliding) {
            if (player.isUsingItem) {
                grimLatestTicks++
            } else {
                grimLatestTicks = 0
            }
        }

        if (mode == Mode.MATRIX) {
            val velocity = player.velocity
            if (player.isOnGround && !mc.options.jumpKey.isPressed) {
                player.setVelocity(velocity.x * GROUND_MATRIX_FACTOR, velocity.y, velocity.z * GROUND_MATRIX_FACTOR)
            } else if (player.fallDistance > MATRIX_FALL_DISTANCE) {
                player.setVelocity(velocity.x * AIR_MATRIX_FACTOR, velocity.y, velocity.z * AIR_MATRIX_FACTOR)
            }
        }
    }

    @Suppress("unused")
    private val useMultiplierHandler = handler<PlayerUseMultiplier> { event ->
        if (!running || mode != Mode.MATRIX_NEW || !player.isUsingItem || player.isGliding) {
            return@handler
        }

        val diagonal = player.input.movementForward != 0.0f && player.input.movementSideways != 0.0f
        val multiplier = when {
            player.isOnGround && diagonal -> MATRIX_NEW_GROUND_DIAGONAL
            player.isOnGround -> MATRIX_NEW_GROUND_STRAIGHT
            diagonal -> MATRIX_NEW_AIR_DIAGONAL
            else -> MATRIX_NEW_AIR_STRAIGHT
        }

        event.forward = multiplier
        event.sideways = multiplier
    }

    @Suppress("unused")
    private val velocityMultiplierHandler = handler<BlockVelocityMultiplierEvent> { event ->
        if (!running) {
            return@handler
        }

        if ((soulSand && event.block === Blocks.SOUL_SAND) || (honey && event.block === Blocks.HONEY_BLOCK)) {
            event.multiplier = Blocks.STONE.velocityMultiplier
        }
    }

    @Suppress("unused")
    private val slipperinessHandler = handler<BlockSlipperinessMultiplierEvent> { event ->
        if (!running) {
            return@handler
        }

        if (slime && event.block === Blocks.SLIME_BLOCK) {
            event.slipperiness = Blocks.STONE.slipperiness
            return@handler
        }

        if (ice && event.block in ICE_BLOCKS && !mc.options.jumpKey.isPressed) {
            event.slipperiness = Blocks.STONE.slipperiness
        }
    }

    override fun disable() {
        resetGrim()
    }

    fun canNoSlow(): Boolean {
        val player = mc.player ?: return false
        val activeItem = player.activeItem

        if (!running || mode == Mode.MATRIX_NEW || !canHandle(activeItem)) {
            return false
        }

        if (!food && activeItem.get(DataComponentTypes.FOOD) != null) {
            return false
        }

        if (!shield && activeItem.item == Items.SHIELD) {
            return false
        }

        if (!projectiles && activeItem.item in PROJECTILE_ITEMS) {
            return false
        }

        if (!mainHand && player.activeHand == Hand.MAIN_HAND) {
            return false
        }

        if (mode != Mode.GRIM) {
            return true
        }

        if (player.isGliding) {
            return false
        }

        val tick = player.age
        return when {
            tick == grimLatestLastNoSlowTick -> true
            grimLatestTicks >= GRIM_USE_TICKS -> {
                grimLatestTicks = 0
                grimLatestLastNoSlowTick = tick
                true
            }
            else -> false
        }
    }

    fun shouldCancelPoseSlowdown(crawling: Boolean): Boolean {
        return running && if (crawling) crawl else sneak
    }

    fun shouldCancelSlimeStepping(entity: Entity): Boolean {
        return running && slime && entity === mc.player
    }

    fun shouldCancelSweetBerryCollision(entity: Entity): Boolean {
        return running && sweetBerryBush && entity === mc.player
    }

    private fun canHandle(activeItem: ItemStack): Boolean {
        if (activeItem.isEmpty) {
            resetGrim()
            return false
        }

        return true
    }

    private fun resetGrim() {
        grimLatestTicks = 0
        grimLatestLastNoSlowTick = -1
    }

    enum class Mode(override val choiceName: String) : NamedChoice {
        GRIM("Grim"),
        MATRIX("Matrix"),
        MATRIX_NEW("MatrixNew"),
    }

    private const val GRIM_USE_TICKS = 2
    private const val GROUND_MATRIX_FACTOR = 0.3
    private const val AIR_MATRIX_FACTOR = 0.95
    private const val MATRIX_FALL_DISTANCE = 0.18f
    private const val MATRIX_NEW_GROUND_DIAGONAL = 0.35f
    private const val MATRIX_NEW_GROUND_STRAIGHT = 0.5f
    private const val MATRIX_NEW_AIR_DIAGONAL = 0.47f
    private const val MATRIX_NEW_AIR_STRAIGHT = 0.67f

    private val PROJECTILE_ITEMS = hashSetOf<Item>(Items.CROSSBOW, Items.BOW, Items.TRIDENT)
    private val ICE_BLOCKS = hashSetOf<Block>(Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.FROSTED_ICE)
}
