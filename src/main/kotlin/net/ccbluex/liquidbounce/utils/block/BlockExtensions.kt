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

@file:Suppress("TooManyFunctions")

package net.ccbluex.liquidbounce.utils.block
import net.ccbluex.liquidbounce.utils.entity.*

import it.unimi.dsi.fastutil.booleans.BooleanObjectPair
import it.unimi.dsi.fastutil.doubles.DoubleObjectPair
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue
import it.unimi.dsi.fastutil.ints.IntObjectPair
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue
import it.unimi.dsi.fastutil.longs.LongOpenHashSet
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.BlockBreakingProgressEvent
import net.ccbluex.liquidbounce.render.FULL_BOX
import net.ccbluex.liquidbounce.utils.client.*
import net.ccbluex.liquidbounce.utils.kotlin.mapArray
import net.ccbluex.liquidbounce.utils.math.rangeTo
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.*
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.boss.enderdragon.EndCrystal
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.ItemStack
import net.minecraft.network.protocol.game.ServerboundSwingPacket
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.world.level.block.state.properties.BedPart
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionHand
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.*
import net.minecraft.world.phys.*
import net.minecraft.util.Mth
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ClipContext
import kotlin.math.ceil
import kotlin.math.floor

val DEFAULT_BLOCK_STATE: BlockState = Blocks.AIR.defaultState

fun Vec3i.toBlockPos() = BlockPos(this)

fun BlockPos.getState() = mc.world?.getBlockState(this)

fun BlockPos.getBlock() = getState()?.block

fun BlockPos.getCenterDistanceSquared() = player.squaredDistanceTo(this.x + 0.5, this.y + 0.5, this.z + 0.5)

fun BlockPos.getCenterDistanceSquaredEyes() = player.eyePos.squaredDistanceTo(this.x + 0.5, this.y + 0.5, this.z + 0.5)

val BlockState.isBed: Boolean
    get() = block is BedBlock

/**
 * Converts this [BlockPos] to an immutable one if needed.
 */
val BlockPos.immutable: BlockPos get() = if (this is BlockPos.MutableBlockPos) this.toImmutable() else this

/**
 * Returns the block box outline of the block at the position. If the block is air, it will return an empty box.
 * Outline AABB should be used for rendering purposes only.
 *
 * Returns [FULL_BOX] when block is air or does not exist.
 */
val BlockPos.outlineBox: AABB
    get() {
        val blockState = getState() ?: return FULL_BOX
        if (blockState.isAir) {
            return FULL_BOX
        }

        val outlineShape = blockState.getOutlineShape(world, this)
        return if (outlineShape.isEmpty) {
            FULL_BOX
        } else {
            outlineShape.boundingBox
        }
    }

val BlockPos.collisionShape: VoxelShape
    get() = this.getState()!!.getCollisionShape(world, this)

/**
 * Shrinks a VoxelShape by the specified amounts on selected axes.
 */
@Suppress("CognitiveComplexMethod")
fun VoxelShape.shrink(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): VoxelShape {
    return when {
        this.isEmpty -> Shapes.empty()
        this == Shapes.block() -> Shapes.create(
            x, y, z,
            1.0 - x, 1.0 - y, 1.0 - z
        )
        else -> {
            var shape = Shapes.empty()

            this.forEachBox { minX, minY, minZ, maxX, maxY, maxZ ->
                val width = maxX - minX
                val height = maxY - minY
                val depth = maxZ - minZ

                val canShrinkX = x == 0.0 || width > x * 2
                val canShrinkY = y == 0.0 || height > y * 2
                val canShrinkZ = z == 0.0 || depth > z * 2

                if (canShrinkX && canShrinkY && canShrinkZ) {
                    val shrunkBox = Shapes.create(
                        minX + (if (x > 0) x else 0.0),
                        minY + (if (y > 0) y else 0.0),
                        minZ + (if (z > 0) z else 0.0),
                        maxX - (if (x > 0) x else 0.0),
                        maxY - (if (y > 0) y else 0.0),
                        maxZ - (if (z > 0) z else 0.0)
                    )

                    shape = Shapes.join(shape, shrunkBox, BooleanOp.OR)
                }
            }

            shape
        }
    }
}


/**
 * Some blocks like slabs or stairs must be placed on upper side in order to be placed correctly.
 */
val Block.mustBePlacedOnUpperSide: Boolean
    get() {
        return this is SlabBlock || this is StairBlock
    }

val BlockPos.hasEntrance: Boolean
    get() {
        val positionsAround = arrayOf(
            this.offset(Direction.NORTH),
            this.offset(Direction.SOUTH),
            this.offset(Direction.EAST),
            this.offset(Direction.WEST),
            this.offset(Direction.UP)
        )

        val block = this.getBlock()
        return positionsAround.any { it.getState()?.isAir == true && it.getBlock() != block }
    }

val BlockPos.weakestBlock: BlockPos?
    get() {
        val positionsAround = arrayOf(
            this.offset(Direction.NORTH),
            this.offset(Direction.SOUTH),
            this.offset(Direction.EAST),
            this.offset(Direction.WEST),
            this.offset(Direction.UP)
        )

        val block = this.getBlock()
        return positionsAround
            .filter { it.getBlock() != block && it.getState()?.isAir == false }
            .sortedBy { player.pos.squaredDistanceTo(it.toCenterPos()) }
            .minByOrNull { it.getBlock()?.hardness ?: 0f }
    }

/**
 * Scan blocks around the position in a cuboid.
 */
fun Vec3.searchBlocksInCuboid(radius: Float): Region {
    val from = BlockPos(
        floor(x - radius).toInt(),
        floor(y - radius).toInt(),
        floor(z - radius).toInt(),
    )

    val to = BlockPos(
        ceil(x + radius).toInt(),
        ceil(y + radius).toInt(),
        ceil(z + radius).toInt(),
    )

    return from..to
}

/**
 * Scan blocks around the position in a cuboid with filtering.
 */
inline fun Vec3.searchBlocksInCuboid(
    radius: Float,
    crossinline filter: (BlockPos, BlockState) -> Boolean
): Sequence<Pair<BlockPos, BlockState>> = sequence {
    searchBlocksInCuboid(radius).forEach {
        val state = it.getState() ?: return@forEach

        if (filter(it, state)) {
            yield(Pair(it.toImmutable(), state))
        }
    }
}

/**
 * Search blocks around the position in a specific [radius]
 */
inline fun Vec3.searchBlocksInRadius(
    radius: Float,
    crossinline filter: (BlockPos, BlockState) -> Boolean,
): Sequence<Pair<BlockPos, BlockState>> = sequence {
    val radiusSquared = (radius * radius).toDouble()
    searchBlocksInCuboid(radius).forEach {
        val state = it.getState() ?: return@forEach

        if (it.getSquaredDistance(this@searchBlocksInRadius) > radiusSquared) {
            return@forEach
        }

        if (filter(it, state)) {
            yield(Pair(it.toImmutable(), state))
        }
    }
}

/**
 * Scan blocks around the position in a cuboid.
 */
fun BlockPos.searchBlocksInCuboid(radius: Int): Region {
    val from = BlockPos(x - radius, y - radius, z - radius)
    val to = BlockPos(x + radius, y + radius, z + radius)
    return from..to
}

/**
 * Scan blocks outwards from a bed
 */
fun BlockPos.searchBedLayer(state: BlockState, layers: Int): Sequence<IntObjectPair<BlockPos>> {
    check(state.isBed) { "This function is only available for Beds" }

    var bedDirection = state.get(BedBlock.FACING)
    var opposite = bedDirection.opposite

    if (state.get(BedBlock.PART) != BedPart.FOOT) {
        opposite = bedDirection
        bedDirection = bedDirection.opposite
    }

    var left = Direction.WEST
    var right = Direction.EAST

    if (bedDirection.axis == Direction.Axis.X) {
        left = Direction.SOUTH
        right = Direction.NORTH
    }

    return searchLayer(layers, bedDirection, Direction.UP, left, right) +
        offset(opposite).searchLayer(layers, opposite, Direction.UP, left, right)
}

/**
 * Scan blocks outwards from center along given [directions], up to [layers]
 */
@Suppress("detekt:CognitiveComplexMethod")
fun BlockPos.searchLayer(layers: Int, vararg directions: Direction): Sequence<IntObjectPair<BlockPos>> =
    sequence {
        val longValueOfThis = this@searchLayer.asLong()
        val initialCapacity = layers * layers * directions.size / 2

        val layerQueue = IntArrayFIFOQueue().apply { enqueue(0) }
        val longValueQueue = LongArrayFIFOQueue().apply { enqueue(longValueOfThis) }
        val visited = LongOpenHashSet(initialCapacity).apply { add(longValueOfThis) }

        val mutable = BlockPos.MutableBlockPos()

        while (!longValueQueue.isEmpty) {
            val layer = layerQueue.dequeueInt()
            val pos = longValueQueue.dequeueLong()

            if (layer > 0) {
                yield(IntObjectPair.of(layer, BlockPos.of(pos)))
            }

            if (layer >= layers) {
                continue
            }

            // Search next layer
            for (direction in directions) {
                mutable.set(pos)
                mutable.move(direction)

                val newLong = mutable.asLong()
                if (visited.add(newLong)) {
                    layerQueue.enqueue(layer + 1)
                    longValueQueue.enqueue(newLong)
                }
            }
        }
    }

/**
 * **Squared Distance** to **BlockPos**
 */
fun BlockPos.getSphere(radius: Float): Sequence<DoubleObjectPair<BlockPos>> = sequence {
    val radiusSq = radius * radius

    searchBlocksInCuboid(Mth.ceil(radius)).forEach {
        val distanceSq = getSquaredDistance(it)
        if (distanceSq <= radiusSq) {
            yield(DoubleObjectPair.of(distanceSq, it.toImmutable()))
        }
    }
}

fun BlockPos.getSortedSphere(radius: Float): Array<BlockPos> {
    return getSphere(radius).toList().sortedBy { it.firstDouble() }.mapArray { it.second() }
}

/**
 * Basically [BlockGetter.raycast] but this method allows us to exclude blocks using [exclude].
 */
@Suppress("SpellCheckingInspection", "CognitiveComplexMethod")
fun BlockGetter.raycast(
    context: ClipContext,
    exclude: Array<BlockPos>?,
    include: BlockPos?,
    maxBlastResistance: Float?
): BlockHitResult {
    return BlockGetter.traverseBlocks(context.getFrom(), context.getTo(), context,
        { raycastContext, pos ->
            val excluded = exclude?.let { pos in it } ?: false

            val blockState = if (excluded) {
                Blocks.VOID_AIR.defaultState
            } else if (include != null && pos == include) {
                Blocks.OBSIDIAN.defaultState
            } else {
                var state = getBlockState(pos)
                maxBlastResistance?.let {
                    if (state.block.blastResistance < it) {
                        state = Blocks.VOID_AIR.defaultState
                    }
                }
                state
            }

            val fluidState = if (excluded) {
                Fluids.EMPTY.defaultState
            } else {
                var state = getFluidState(pos)
                maxBlastResistance?.let {
                    if (state.getExplosionResistance() < it) {
                        state = Fluids.EMPTY.defaultState
                    }
                }
                state
            }

            val vec = raycastContext.getFrom()
            val vec2 = raycastContext.getTo()

            val blockShape = raycastContext.getBlockShape(blockState, this, pos)
            val blockHitResult = clipWithInteractionOverride(vec, vec2, pos, blockShape, blockState)

            val fluidShape = raycastContext.getFluidShape(fluidState, this, pos)
            val fluidHitResult = fluidShape.clip(vec, vec2, pos)

            val blockHitDistance = blockHitResult?.let {
                raycastContext.getFrom().squaredDistanceTo(blockHitResult.pos)
            } ?: Double.MAX_VALUE
            val fluidHitDistance = fluidHitResult?.let {
                raycastContext.getFrom().squaredDistanceTo(fluidHitResult.pos)
            } ?: Double.MAX_VALUE

            if (blockHitDistance <= fluidHitDistance) blockHitResult else fluidHitResult
        },
        { raycastContext ->
            val vec = raycastContext.getFrom().subtract(raycastContext.getTo())
            BlockHitResult.miss(
                raycastContext.getTo(),
                Direction.getApproximateNearest(vec.x, vec.y, vec.z),
                BlockPos.containing(raycastContext.getTo())
            )
        })
}

fun BlockPos.canStandOn(): Boolean {
    return this.getState()!!.isSideSolid(world, this, Direction.UP, SupportType.CENTER)
}

/**
 * Check if box is reaching of specified blocks
 */
inline fun AABB.isBlockAtPosition(
    isCorrectBlock: (Block?) -> Boolean,
): Boolean {
    val blockPos = BlockPos.MutableBlockPos(0, floor(minY).toInt(), 0)

    for (x in floor(minX).toInt()..ceil(maxX).toInt()) {
        for (z in floor(minZ).toInt()..ceil(maxZ).toInt()) {
            blockPos.setX(x)
            blockPos.setZ(z)

            if (isCorrectBlock(blockPos.getBlock())) {
                return true
            }
        }
    }

    return false
}

/**
 * Check if box intersects with bounding box of specified blocks
 */
inline fun AABB.collideBlockIntersects(
    checkCollisionShape: Boolean = true,
    isCorrectBlock: (Block?) -> Boolean
): Boolean {
    collidingRegion.forEach { blockPos ->
        val blockState = blockPos.getState() ?: return@forEach
        val block = blockState.block ?: return@forEach

        if (!isCorrectBlock(block)) {
            return@forEach
        }

        if (!checkCollisionShape) {
            return true
        }

        val shape = blockState.getCollisionShape(world, blockPos)

        if (shape.isEmpty) {
            return@forEach
        }

        val boundingBox = shape.boundingBox

        if (intersects(boundingBox)) {
            return true
        }
    }

    return false
}

val AABB.collidingRegion: Region
    get() {
        val from = BlockPos(this.minX.toInt(), this.minY.toInt(), this.minZ.toInt())
        val to = BlockPos(ceil(this.maxX).toInt(), ceil(this.maxY).toInt(), ceil(this.maxZ).toInt())
        return from..to
    }

fun BlockState.canBeReplacedWith(
    pos: BlockPos,
    usedStack: ItemStack,
): Boolean {
    val placementContext =
        BlockPlaceContext(
            player,
            InteractionHand.MAIN_HAND,
            usedStack,
            BlockHitResult(Vec3.atLowerCornerOf(pos), Direction.UP, pos, false),
        )

    return canReplace(
        placementContext,
    )
}

@Suppress("unused")
enum class SwingMode(
    override val choiceName: String,
    val serverSwing: Boolean,
    val swing: (InteractionHand) -> Unit = { }
): NamedChoice {

    DO_NOT_HIDE("DoNotHide", true, { player.swingHand(it) }),
    HIDE_BOTH("HideForBoth", false),
    HIDE_CLIENT("HideForClient", true, { network.sendPacket(ServerboundSwingPacket(it)) }),
    HIDE_SERVER("HideForServer", false, { player.swingHand(it, false) });

}

fun doPlacement(
    rayTraceResult: BlockHitResult,
    hand: InteractionHand = InteractionHand.MAIN_HAND,
    onPlacementSuccess: () -> Boolean = { true },
    onItemUseSuccess: () -> Boolean = { true },
    swingMode: SwingMode = SwingMode.DO_NOT_HIDE
) {
    val stack = player.getStackInHand(hand)
    val count = stack.count

    val interactionResult = interaction.interactBlock(player, hand, rayTraceResult)

    when {
        interactionResult == InteractionResult.FAIL -> {
            return
        }

        interactionResult == InteractionResult.PASS -> {
            // Ok, we cannot place on the block, so let's just use the item in the direction
            // without targeting a block (for buckets, etc.)
            handlePass(hand, stack, onItemUseSuccess, swingMode)
            return
        }

        interactionResult.isAccepted -> {
            val wasStackUsed = !stack.isEmpty && (stack.count != count || interaction.hasCreativeInventory())

            handleActionsOnAccept(hand, interactionResult, wasStackUsed, onPlacementSuccess, swingMode)
        }
    }
}

/**
 * Swings item, resets equip progress and hand swing progress
 *
 * @param wasStackUsed was an item consumed in order to place the block
 */
private inline fun handleActionsOnAccept(
    hand: InteractionHand,
    interactionResult: InteractionResult,
    wasStackUsed: Boolean,
    onPlacementSuccess: () -> Boolean,
    swingMode: SwingMode = SwingMode.DO_NOT_HIDE,
) {
    if (!interactionResult.shouldSwingHand()) {
        return
    }

    if (onPlacementSuccess()) {
        swingMode.swing(hand)
    }

    if (wasStackUsed) {
        mc.firstPersonRenderer.resetEquipProgress(hand)
    }

    return
}

private fun InteractionResult.shouldSwingHand(): Boolean {
    return this !is InteractionResult.Success ||
        this.swingSource != InteractionResult.SwingSource.SERVER
}


/**
 * Just interacts with the item in the hand instead of using it on the block
 */
private inline fun handlePass(
    hand: InteractionHand,
    stack: ItemStack,
    onItemUseSuccess: () -> Boolean,
    swingMode: SwingMode
) {
    if (stack.isEmpty) {
        return
    }

    val actionResult = interaction.interactItem(player, hand)

    handleActionsOnAccept(hand, actionResult, true, onItemUseSuccess, swingMode)
}

/**
 * Breaks the block
 */
fun doBreak(
    rayTraceResult: BlockHitResult,
    immediate: Boolean = false,
    swingMode: SwingMode = SwingMode.DO_NOT_HIDE
) {
    val direction = rayTraceResult.side
    val blockPos = rayTraceResult.blockPosition()

    if (player.isCreative) {
        if (interaction.attackBlock(blockPos, rayTraceResult.side)) {
            swingMode.swing(InteractionHand.MAIN_HAND)
            return
        }
    }

    if (immediate) {
        EventManager.callEvent(BlockBreakingProgressEvent(blockPos))

        network.sendPacket(
            ServerboundPlayerActionPacket(
                ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK, blockPos, direction
            )
        )
        swingMode.swing(InteractionHand.MAIN_HAND)
        network.sendPacket(
            ServerboundPlayerActionPacket(
                ServerboundPlayerActionPacket.Action.STOP_DESTROY_BLOCK, blockPos, direction
            )
        )
        return
    }

    if (interaction.updateBlockBreakingProgress(blockPos, direction)) {
        swingMode.swing(InteractionHand.MAIN_HAND)
        mc.particleManager.addBlockBreakingParticles(blockPos, direction)
    }
}

fun BlockState.isNotBreakable(pos: BlockPos) = !isBreakable(pos)

fun BlockState.isBreakable(pos: BlockPos): Boolean {
    return !isAir && (player.isCreative || getHardness(world, pos) >= 0f)
}

val FALL_DAMAGE_BLOCKING_BLOCKS = arrayOf(
    Blocks.WATER, Blocks.COBWEB, Blocks.POWDER_SNOW, Blocks.HAY_BLOCK, Blocks.SLIME_BLOCK
)

fun BlockPos?.isFallDamageBlocking(): Boolean {
    if (this == null) {
        return false
    }

    return getBlock() in FALL_DAMAGE_BLOCKING_BLOCKS
}

fun BlockPos.isBlastResistant(): Boolean {
    return getBlock()!!.blastResistance >= 600f
}

@Suppress("UnusedReceiverParameter")
fun RespawnAnchorBlock.isCharged(state: BlockState): Boolean {
    return state.get(RespawnAnchorBlock.CHARGE) > 0
}

/**
 * Returns the second bed block position that might not exist (normally beds are two blocks long tho).
 */
@Suppress("UnusedReceiverParameter")
fun BedBlock.getPotentialSecondBedBlock(state: BlockState, pos: BlockPos): BlockPos {
    return pos.offset((state.get(HorizontalDirectionalBlock.FACING)).opposite)
}

// TODO replace this by an approach that automatically collects the blocks, this would create better mod compatibility
/**
 * Checks if the block can be interacted with, null will be returned as not interactable.
 * The [blockState] is optional but can make the result more accurate, if not provided
 * it will just assume the block is interactable.
 *
 * Note: The player is required to NOT be `null`.
 *
 * This data has been collected by looking at the implementations of [AbstractBlock.onUse].
 */
fun Block?.isInteractable(blockState: BlockState?): Boolean {
    if (this == null) {
        return false
    }

    return this is BedBlock || this is AbstractChestBlock<*> || this is AbstractFurnaceBlock || this is AnvilBlock
        || this is BarrelBlock || this is BeaconBlock || this is BellBlock || this is BrewingStandBlock
        || this is ButtonBlock || this is CakeBlock && player.hungerManager.isNotFull || this is CandleCakeBlock
        || this is CartographyTableBlock || this is CaveVinesPlantBlock && blockState?.get(CaveVines.BERRIES) ?: true
        || this is CaveVinesBlock && blockState?.get(CaveVines.BERRIES) ?: true
        || this is ComparatorBlock || this is ComposterBlock && (blockState?.get(ComposterBlock.LEVEL) ?: 8) == 8
        || this is CrafterBlock || this is CraftingTableBlock || this is DaylightDetectorBlock
        || this is DecoratedPotBlock || this is DispenserBlock || this is DoorBlock || this is DragonEggBlock
        || this is EnchantingTableBlock || this is FenceGateBlock || this is FlowerPotBlock
        || this is GrindstoneBlock || this is HopperBlock || this is GameMasterBlock && player.isCreativeLevelTwoOp
        || this is JukeboxBlock && blockState?.get(JukeboxBlock.HAS_RECORD) == true || this is LecternBlock
        || this is LeverBlock || this is LightBlock && player.isCreativeLevelTwoOp || this is NoteBlock
        || this is RedStoneWireBlock || this is RepeaterBlock || this is RespawnAnchorBlock // this only works
        // when we hold glow stone or are not in the nether and the anchor is charged, but it'd be too error-prone when
        // it would be checked as the player can quickly switch to glow stone
        || this is ShulkerBoxBlock || this is StonecutterBlock
        || this is SweetBerryBushBlock && (blockState?.get(SweetBerryBushBlock.AGE) ?: 2) > 1 || this is TrapDoorBlock
}

fun BlockPos.isBlockedByEntities(): Boolean {
    val posBox = FULL_BOX.offset(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())
    return world.entitiesForRendering().any {
        it.boundingBox.intersects(posBox)
    }
}

inline fun BlockPos.getBlockingEntities(include: (Entity) -> Boolean = { true }): List<Entity> {
    val posBox = FULL_BOX.offset(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())
    return world.entitiesForRendering().filter {
        it.boundingBox.intersects(posBox) &&
            include.invoke(it)
    }
}

/**
 * Like [isBlockedByEntities] but it returns a blocking end crystal if present.
 */
fun BlockPos.isBlockedByEntitiesReturnCrystal(
    box: AABB = FULL_BOX,
    excludeIds : IntArray? = null
): BooleanObjectPair<EndCrystal?> {
    var blocked = false

    val posBox = box.offset(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())
    world.entitiesForRendering().forEach {
        if (it.boundingBox.intersects(posBox) && (excludeIds == null || it.id !in excludeIds)) {
            if (it is EndCrystal) {
                return BooleanObjectPair.of(true, it)
            }

            blocked = true
        }
    }

    return BooleanObjectPair.of(blocked, null)
}
