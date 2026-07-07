package net.ccbluex.liquidbounce.utils.block

import net.ccbluex.liquidbounce.utils.client.world
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.Vec3i
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.EmptyBlockGetter
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.tags.TagKey
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import java.util.Optional

val Block.defaultState: BlockState
    get() = defaultBlockState()

val Block.hardness: Float
    get() = defaultBlockState().getDestroySpeed(world ?: EmptyBlockGetter.INSTANCE, BlockPos.ZERO)

val Block.translationKey: String
    get() = descriptionId

val Block.blastResistance: Float
    get() = explosionResistance

val Block.slipperiness: Float
    get() = friction

val Block.deltaMovementMultiplier: Float
    get() = speedFactor

val Block.jumpVelocityMultiplier: Float
    get() = jumpFactor

fun BlockState.getOutlineShape(pos: BlockPos): VoxelShape =
    getShape(world ?: EmptyBlockGetter.INSTANCE, pos)

fun BlockState.getOutlineShape(level: net.minecraft.world.level.BlockGetter?, pos: BlockPos): VoxelShape =
    getShape(level ?: EmptyBlockGetter.INSTANCE, pos)

fun BlockState.getOutlineShape(
    level: net.minecraft.world.level.BlockGetter?,
    pos: BlockPos,
    context: CollisionContext
): VoxelShape = getShape(level ?: EmptyBlockGetter.INSTANCE, pos, context)

val BlockState.isAir: Boolean
    get() = isAir()

fun BlockGetter.getStatesInBox(box: net.minecraft.world.phys.AABB) = getBlockStates(box)

fun BlockState.isIn(tag: TagKey<Block>): Boolean = `is`(tag)

fun BlockState.isIn(block: Block): Boolean = `is`(block)

fun BlockState.isIn(block: Holder<Block>): Boolean = `is`(block)

val BlockState.isReplaceable: Boolean
    get() = canBeReplaced()

val BlockState.renderType: RenderShape
    get() = renderShape

operator fun <T : Comparable<T>> BlockState.get(property: Property<T>): T = getValue(property)

fun BlockState.canReplace(context: BlockPlaceContext): Boolean = canBeReplaced(context)

fun BlockState.getHardness(level: net.minecraft.world.level.BlockGetter?, pos: BlockPos): Float =
    getDestroySpeed(level ?: EmptyBlockGetter.INSTANCE, pos)

val Fluid.defaultState: FluidState
    get() = defaultFluidState()

fun BlockState.isSideSolidFullSquare(
    level: net.minecraft.world.level.BlockGetter?,
    pos: BlockPos,
    side: Direction
): Boolean = isFaceSturdy(level ?: EmptyBlockGetter.INSTANCE, pos, side)

fun BlockState.isSideSolid(
    level: net.minecraft.world.level.BlockGetter?,
    pos: BlockPos,
    side: Direction,
    supportType: net.minecraft.world.level.block.SupportType
): Boolean = isFaceSturdy(level ?: EmptyBlockGetter.INSTANCE, pos, side, supportType)

fun BlockState.isFullCube(level: net.minecraft.world.level.BlockGetter?, pos: BlockPos): Boolean =
    isCollisionShapeFullBlock(level ?: EmptyBlockGetter.INSTANCE, pos)

fun BlockState.isSolidBlock(level: net.minecraft.world.level.BlockGetter?, pos: BlockPos): Boolean = isSolidRender

fun BlockState.isSolidSurface(
    level: net.minecraft.world.level.BlockGetter?,
    pos: BlockPos,
    entity: net.minecraft.world.entity.Entity?,
    side: Direction
): Boolean = isFaceSturdy(level ?: EmptyBlockGetter.INSTANCE, pos, side)

fun BlockState.isSideInvisible(otherState: BlockState, side: Direction): Boolean =
    !Block.shouldRenderFace(this, otherState, side)

fun BlockPos.toImmutable(): BlockPos = immutable()

fun BlockPos.up(): BlockPos = above()

fun BlockPos.up(steps: Int): BlockPos = above(steps)

fun BlockPos.down(): BlockPos = below()

fun BlockPos.down(steps: Int): BlockPos = below(steps)

fun BlockPos.getSquaredDistance(vec: Vec3): Double = distToCenterSqr(vec)

fun BlockPos.getSquaredDistance(pos: BlockPos): Double = distSqr(pos)

fun Vec3i.getManhattanDistance(pos: Vec3i): Int = distManhattan(pos)

fun Vec3i.toCenterPos(): Vec3 = Vec3.atCenterOf(this)

fun Vec3i.plus(pos: Vec3i): Vec3i = Vec3i(x + pos.x, y + pos.y, z + pos.z)

fun BlockPos.offset(direction: Direction): BlockPos = relative(direction)

val VoxelShape.boundingBox: net.minecraft.world.phys.AABB
    get() = bounds()

val VoxelShape.boundingBoxes: List<AABB>
    get() = toAabbs()

fun VoxelShape.forEachBox(consumer: (Double, Double, Double, Double, Double, Double) -> Unit) =
    forAllBoxes { minX, minY, minZ, maxX, maxY, maxZ -> consumer(minX, minY, minZ, maxX, maxY, maxZ) }

fun VoxelShape.getClosestPointTo(point: Vec3): Optional<Vec3> = closestPointTo(point)

fun VoxelShape.offset(x: Double, y: Double, z: Double): VoxelShape = move(x, y, z)
