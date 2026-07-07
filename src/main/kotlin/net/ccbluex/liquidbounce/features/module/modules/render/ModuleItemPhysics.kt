package net.ccbluex.liquidbounce.features.module.modules.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.interfaces.EntityRenderStateAddition
import net.ccbluex.liquidbounce.utils.entity.deltaMovement
import net.ccbluex.liquidbounce.utils.entity.isTouchingWater
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.item.ItemEntity
import java.util.UUID
import kotlin.math.sqrt

/**
 * Unique 1.7 ItemPhysics port for the 1.26 render-state pipeline.
 */
object ModuleItemPhysics : ClientModule("ItemPhysics", Category.RENDER) {

    private val random = RandomSource.create()
    private val rotationMapY = hashMapOf<UUID, Float>()
    private val rotationMapX = hashMapOf<UUID, Float>()
    private val speedYMap = hashMapOf<UUID, Float>()
    private val speedXMap = hashMapOf<UUID, Float>()
    private val speedZMap = hashMapOf<UUID, Float>()

    @JvmStatic
    fun submitItemEntity(
        state: ItemEntityRenderState,
        matrices: PoseStack,
        submitNodeCollector: SubmitNodeCollector
    ): Boolean {
        if (!running || state.item.isEmpty) {
            return false
        }

        val entity = (state as EntityRenderStateAddition).`liquid_bounce$getEntity`() as? ItemEntity
            ?: return false
        val itemRenderState = state.item
        val boundingBox = itemRenderState.modelBoundingBox
        val flat = boundingBox.xsize > MIN_MODEL_SIZE &&
            boundingBox.ysize > MIN_MODEL_SIZE &&
            boundingBox.zsize <= MIN_MODEL_SIZE
        val minOffsetY = -boundingBox.minY.toFloat() + ITEM_MIN_HOVER_HEIGHT
        val onGround = entity.onGround()
        val inFluid = entity.isTouchingWater || entity.isInLava
        val tickDelta = state.ageInTicks - entity.tickCount
        val rotation = getRotationPair(entity, tickDelta)

        matrices.pushPose()
        if (entity.isTouchingWater && !flat) {
            matrices.translate(0.0, WATER_BLOCK_OFFSET, 0.0)
        } else {
            matrices.translate(0.0, minOffsetY.toDouble(), 0.0)
        }

        if (flat) {
            matrices.mulPose(Axis.XP.rotationDegrees(FLAT_ITEM_ROTATION))
            if (!onGround && !inFluid) {
                matrices.mulPose(Axis.ZP.rotationDegrees(rotation.rotZ * FLAT_ROTATION_Z_FACTOR))
                matrices.mulPose(Axis.YP.rotationDegrees(rotation.rotY))
                matrices.mulPose(Axis.XP.rotationDegrees(rotation.rotX * FLAT_ROTATION_X_FACTOR))
            }
        } else if (!onGround && !inFluid) {
            matrices.mulPose(Axis.XP.rotationDegrees(rotation.rotX))
            matrices.mulPose(Axis.YP.rotationDegrees(rotation.rotY))
            matrices.mulPose(Axis.ZP.rotationDegrees(rotation.rotZ * BLOCK_ROTATION_Z_FACTOR))
        }

        if (flat) {
            submitFlatStack(state, matrices, submitNodeCollector)
        } else {
            submitThickStack(state, matrices, submitNodeCollector, onGround || inFluid)
        }

        matrices.popPose()
        return true
    }

    private fun submitThickStack(
        state: ItemEntityRenderState,
        matrices: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        settled: Boolean
    ) {
        repeat(state.count) { index ->
            matrices.pushPose()
            random.setSeed(state.seed.toLong() + index)
            if (settled && index > 0) {
                matrices.translate(
                    ((random.nextFloat() * 2.0f - 1.0f) * STACK_SPREAD).toDouble(),
                    (index * STACK_SPREAD).toDouble(),
                    ((random.nextFloat() * 2.0f - 1.0f) * STACK_SPREAD).toDouble()
                )
            } else if (index > 0) {
                matrices.translate(
                    ((random.nextFloat() * 2.0f - 1.0f) * VANILLA_STACK_SPREAD).toDouble(),
                    ((random.nextFloat() * 2.0f - 1.0f) * VANILLA_STACK_SPREAD).toDouble(),
                    ((random.nextFloat() * 2.0f - 1.0f) * VANILLA_STACK_SPREAD).toDouble()
                )
            }
            state.item.submit(matrices, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor)
            matrices.popPose()
        }
    }

    private fun submitFlatStack(
        state: ItemEntityRenderState,
        matrices: PoseStack,
        submitNodeCollector: SubmitNodeCollector
    ) {
        val modelDepth = state.item.modelBoundingBox.zsize.toFloat()
        val offsetZ = modelDepth * 1.5f
        matrices.translate(0.0f, 0.0f, -(offsetZ * (state.count - 1) / 2.0f))

        repeat(state.count) { index ->
            matrices.pushPose()
            random.setSeed(state.seed.toLong() + index)
            if (index > 0) {
                matrices.translate(
                    ((random.nextFloat() * 2.0f - 1.0f) * VANILLA_STACK_SPREAD * 0.5f).toDouble(),
                    ((random.nextFloat() * 2.0f - 1.0f) * VANILLA_STACK_SPREAD * 0.5f).toDouble(),
                    0.0
                )
            }
            state.item.submit(matrices, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor)
            matrices.popPose()
            matrices.translate(0.0f, 0.0f, offsetZ)
        }
    }

    private fun getRotationPair(entity: ItemEntity, tickDelta: Float): RotationPair {
        val id = entity.uuid
        val onGround = entity.onGround()
        val inFluid = entity.isTouchingWater || entity.isInLava

        if (!onGround && !inFluid) {
            val speedY = speedYMap.getOrPut(id) { random.nextFloat() * 3.0f + 2.0f }
            val speedX = speedXMap.getOrPut(id) { random.nextFloat() * 2.5f + 1.5f }
            val speedZ = speedZMap.getOrPut(id) { random.nextFloat() * 3.5f + 1.5f }
            val prevY = rotationMapY.getOrDefault(id, 0.0f)
            val prevX = rotationMapX.getOrDefault(id, 0.0f)
            val velocityFactor = sqrt(entity.deltaMovement.lengthSqr()).toFloat() * 5.0f + 1.0f
            val rotY = prevY + tickDelta * speedY * velocityFactor
            val rotX = prevX + tickDelta * speedX * velocityFactor
            val rotZ = prevY + tickDelta * speedZ * velocityFactor

            rotationMapY[id] = rotY
            rotationMapX[id] = rotX
            return RotationPair(rotY, rotX, rotZ)
        }

        rotationMapY[id] = 0.0f
        rotationMapX[id] = 0.0f
        speedYMap -= id
        speedXMap -= id
        speedZMap -= id
        return RotationPair(0.0f, 0.0f, 0.0f)
    }

    private data class RotationPair(val rotY: Float, val rotX: Float, val rotZ: Float)

    private const val ITEM_MIN_HOVER_HEIGHT = 0.0625f
    private const val MIN_MODEL_SIZE = 0.0625
    private const val FLAT_ITEM_ROTATION = 90.0f
    private const val FLAT_ROTATION_Z_FACTOR = 1.5f
    private const val FLAT_ROTATION_X_FACTOR = 0.7f
    private const val BLOCK_ROTATION_Z_FACTOR = 0.8f
    private const val STACK_SPREAD = 0.015f
    private const val VANILLA_STACK_SPREAD = 0.15f
    private const val WATER_BLOCK_OFFSET = 0.025
}
