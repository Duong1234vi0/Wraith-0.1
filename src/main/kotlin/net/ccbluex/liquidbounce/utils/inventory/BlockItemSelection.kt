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
package net.ccbluex.liquidbounce.utils.inventory

import net.ccbluex.liquidbounce.utils.block.*
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.item.getBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.FallingBlock

object BlockItemSelection {

    fun isValidBlock(stack: ItemStack?): Boolean {
        if (stack == null) {
            return false
        }

        val block = stack.getBlock() ?: return false
        val world = mc.level ?: return false
        val defaultState = block.defaultState

        return when {
            !defaultState.isSolidSurface(world, BlockPos.ZERO, player, Direction.UP) -> false
            block is FallingBlock -> false
            else -> !DISALLOWED_BLOCKS_TO_PLACE.contains(block)
        }
    }

    fun isBlockUnfavourable(stack: ItemStack): Boolean {
        val block = stack.getBlock() ?: return true
        val world = mc.level ?: return true

        return when {
            block.slipperiness > 0.6F -> true
            block.deltaMovementMultiplier < 1.0F -> true
            block.jumpVelocityMultiplier < 1.0F -> true
            block is BaseEntityBlock -> true
            !block.defaultState.isFullCube(world, BlockPos.ZERO) -> true
            else -> block in UNFAVORABLE_BLOCKS_TO_PLACE
        }
    }

}
