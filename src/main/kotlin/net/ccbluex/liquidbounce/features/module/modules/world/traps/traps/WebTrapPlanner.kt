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
package net.ccbluex.liquidbounce.features.module.modules.world.traps.traps
import net.ccbluex.liquidbounce.utils.entity.*

import it.unimi.dsi.fastutil.doubles.DoubleLongPair
import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.features.module.modules.world.traps.*
import net.ccbluex.liquidbounce.features.module.modules.world.traps.ModuleAutoTrap.targetTracker
import net.ccbluex.liquidbounce.utils.block.collidingRegion
import net.ccbluex.liquidbounce.utils.block.getState
import net.ccbluex.liquidbounce.utils.block.targetfinding.*
import net.ccbluex.liquidbounce.utils.entity.prevPos
import net.ccbluex.liquidbounce.utils.inventory.HotbarItemSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.findClosestSlot
import net.ccbluex.liquidbounce.utils.math.size
import net.ccbluex.liquidbounce.utils.math.toBlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.entity.*
import net.minecraft.world.item.Items
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.core.*
import net.minecraft.world.phys.*
import net.minecraft.util.Mth

class WebTrapPlanner(parent: EventListener) : TrapPlanner<WebTrapPlanner.WebIntentData>(
    parent,
    "AutoWeb",
    true
) {

    private val trapItems = arrayOf(Items.COBWEB)
    private val trapWorthyBlocks = arrayOf(Blocks.COBWEB)

    override fun plan(enemies: List<LivingEntity>): BlockChangeIntent<WebIntentData>? {
        val slot = findItemToWeb() ?: return null

        for (target in enemies) {
            val targetPos = TrapPlayerSimulation.findPosForTrap(
                target, isTargetLocked = targetTracker.target == target
            ) ?: continue

            val placementTarget = generatePlacementInfo(targetPos, target, slot) ?: continue

            targetTracker.target = target
            return BlockChangeIntent(
                BlockChangeInfo.PlaceBlock(placementTarget),
                slot,
                IntentTiming.NEXT_PROPITIOUS_MOMENT,
                WebIntentData(target, target.getDimensions(Pose.STANDING).getBoxAt(targetPos)),
                this
            )
        }

        return null
    }

    private fun generatePlacementInfo(
        targetPos: Vec3,
        target: LivingEntity,
        slot: HotbarItemSlot,
    ): BlockPlacementTarget? {
        val blockPos = targetPos.toBlockPos()

        if (blockPos.getState()?.block in trapWorthyBlocks) {
            return null
        }

        val offsetsForTargets = findOffsetsForTarget(
            targetPos,
            target.getDimensions(Pose.STANDING),
            target.pos.subtract(target.prevPos),
            slot.itemStack.item == Items.COBWEB
        )

        val options = BlockPlacementTargetFindingOptions(
            BlockOffsetOptions(
                offsetsForTargets,
                BlockPlacementTargetFindingOptions.PRIORITIZE_LEAST_BLOCK_DISTANCE,
            ),
            FaceHandlingOptions(
                NearestRotationTargetPositionFactory(PositionFactoryConfiguration(player.eyePos, 0.5))
            ),
            stackToPlaceWith = slot.itemStack,
            PlayerLocationOnPlacement(position = player.pos),
        )

        return findBestBlockPlacementTarget(blockPos, options)
    }

    private fun findOffsetsForTarget(
        pos: Vec3,
        dims: EntityDimensions,
        velocity: Vec3,
        mustBeOnGround: Boolean
    ): List<BlockPos> {
        val ticksToLookAhead = 5
        val blockPos = pos.toBlockPos()
        val normalizedStartBB =
            dims.getBoxAt(pos).offset(-blockPos.x.toDouble(), -blockPos.y.toDouble(), -pos.z.toInt().toDouble())
        val normalizedEnddBB = normalizedStartBB.offset(
            velocity.x * ticksToLookAhead,
            0.0,
            velocity.z * ticksToLookAhead
        )

        val searchBB = normalizedEnddBB

        if (searchBB.size > 30) {
            return listOf(BlockPos.ZERO)
        }

        return findOffsetsBetween(normalizedStartBB, normalizedEnddBB, blockPos, mustBeOnGround)
    }

    private fun findOffsetsBetween(
        startBox: AABB,
        endBox: AABB,
        offsetPos: BlockPos,
        mustBeOnGround: Boolean
    ): List<BlockPos> {
        val offsets = mutableListOf<DoubleLongPair>()

        startBox.collidingRegion.forEach { offset ->
            val bp = offsetPos.add(offset)

            val bb = AABB(offset)

            if (!startBox.intersects(bb) && !endBox.intersects(bb)) {
                return@forEach
            }

            val currentState = bp.getState()?.block

            if (currentState in trapWorthyBlocks || currentState != Blocks.AIR) {
                return@forEach
            }

            if (mustBeOnGround && (bp.down().getState()?.isAir != false)) {
                return@forEach
            }

            val intersect = startBox.intersection(bb).size + endBox.intersection(bb).size * 0.5

            offsets.add(DoubleLongPair.of(intersect, offset.asLong()))
        }

        offsets.sortByDescending { it.leftDouble() }

        return offsets.map { BlockPos.of(it.rightLong()) }
    }

    override fun validate(plan: BlockChangeIntent<WebIntentData>, raycast: BlockHitResult): Boolean {
        if (raycast.type != HitResult.Type.BLOCK) {
            return false
        }

        val actualPos = raycast.blockPosition().add(raycast.side.vector)

        if (!AABB(actualPos).intersects(plan.planningInfo.targetBB)) {
            return false
        }

        return plan.slot.itemStack.item in trapItems
    }

    override fun onIntentFullfilled(intent: BlockChangeIntent<WebIntentData>) {
        targetTracker.target = intent.planningInfo.target
    }

    private fun findItemToWeb(): HotbarItemSlot? {
        return Slots.OffhandWithHotbar.findClosestSlot(items = trapItems)
    }

    class WebIntentData(
        val target: LivingEntity,
        val targetBB: AABB
    )

}
