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
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.injection.mixins.minecraft.render.MixinItemRenderStateAccessor
import net.ccbluex.liquidbounce.injection.mixins.minecraft.render.MixinItemRenderStateLayerAccessor
import net.ccbluex.liquidbounce.interfaces.EntityRenderStateAddition
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.state.ItemEntityRenderState
import net.minecraft.client.render.item.ItemRenderState
import net.minecraft.client.render.model.BakedQuad
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.ItemEntity
import net.minecraft.util.math.Direction
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.random.Random as MinecraftRandom
import java.util.Random
import java.util.UUID
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Unique 1.7 ItemPhysics port.
 */
object ModuleItemPhysics : ClientModule("ItemPhysics", Category.RENDER) {

    private val random = Random()
    private val quadRandom = MinecraftRandom.create()
    private val rotationMapY = hashMapOf<UUID, Float>()
    private val rotationMapX = hashMapOf<UUID, Float>()
    private val speedYMap = hashMapOf<UUID, Float>()
    private val speedXMap = hashMapOf<UUID, Float>()
    private val speedZMap = hashMapOf<UUID, Float>()

    fun renderItemEntity(
        state: ItemEntityRenderState,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int
    ): Boolean {
        if (!running || state.itemRenderState.isEmpty) {
            return false
        }

        val entity = (state as EntityRenderStateAddition).`liquid_bounce$getEntity`() as? ItemEntity
            ?: return false
        val itemRenderState = state.itemRenderState
        val info = getInfo(itemRenderState)

        matrices.push()
        offsetInFluidOrBlock(matrices, entity, info)

        val flat = info.flat
        val tickDelta = state.age - entity.itemAge
        val rotation = getRotationPair(entity, tickDelta)
        val box = entity.boundingBox
        val centerX = (box.minX + box.maxX) / 2.0 - entity.x
        val centerY = (box.minY + box.maxY) / 2.0 - entity.y
        val centerZ = (box.minZ + box.maxZ) / 2.0 - entity.z
        val onGround = entity.isOnGround
        val inWater = entity.isTouchingWater || entity.isInLava

        matrices.translate(centerX, centerY, centerZ)
        if (flat) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(FLAT_ITEM_ROTATION))
            if (!onGround && !inWater) {
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotation.rotZ * FLAT_ROTATION_Z_FACTOR))
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation.rotY))
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotation.rotX * FLAT_ROTATION_X_FACTOR))
            }
        } else if (!onGround && !inWater) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotation.rotX))
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation.rotY))
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotation.rotZ * BLOCK_ROTATION_Z_FACTOR))
        }
        matrices.translate(-centerX, -centerY, -centerZ)

        repeat(state.renderedAmount) { index ->
            matrices.push()
            random.setSeed(entity.uuid.mostSignificantBits + index)

            if ((onGround || inWater) && index > 0) {
                matrices.translate(
                    ((random.nextFloat() * 2.0f - 1.0f) * STACK_SPREAD).toDouble(),
                    (index * STACK_SPREAD).toDouble(),
                    ((random.nextFloat() * 2.0f - 1.0f) * STACK_SPREAD).toDouble()
                )
            }

            itemRenderState.render(matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV)
            matrices.pop()
        }

        matrices.pop()
        return true
    }

    private fun getRotationPair(entity: ItemEntity, tickDelta: Float): RotationPair {
        val id = entity.uuid
        val onGround = entity.isOnGround
        val inWater = entity.isTouchingWater || entity.isInLava

        if (!onGround && !inWater) {
            val speedY = speedYMap.getOrPut(id) { random.nextFloat() * 3.0f + 2.0f }
            val speedX = speedXMap.getOrPut(id) { random.nextFloat() * 2.5f + 1.5f }
            val speedZ = speedZMap.getOrPut(id) { random.nextFloat() * 3.5f + 1.5f }
            val prevY = rotationMapY.getOrDefault(id, 0.0f)
            val prevX = rotationMapX.getOrDefault(id, 0.0f)
            val velocityFactor = sqrt(entity.velocity.lengthSquared()).toFloat() * 5.0f + 1.0f
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

    private fun offsetInFluidOrBlock(matrices: MatrixStack, entity: ItemEntity, info: ModelInfo) {
        when {
            entity.isTouchingWater && !info.flat -> matrices.translate(0.0, WATER_BLOCK_OFFSET, 0.0)
            info.flat && !entity.isOnGround -> matrices.translate(0.0, info.offsetY.toDouble(), 0.0)
        }
    }

    private fun getInfo(itemRenderState: ItemRenderState): ModelInfo {
        val quads = itemRenderState.quads()

        var minX = Float.MAX_VALUE
        var minY = Float.MAX_VALUE
        var minZ = Float.MAX_VALUE
        var maxX = Float.MIN_VALUE
        var maxY = Float.MIN_VALUE
        var maxZ = Float.MIN_VALUE

        for (quad in quads) {
            val vertices = quad.vertexData

            for (index in 0 until VERTEX_COUNT) {
                val x = Float.fromBits(vertices[index * VERTEX_STRIDE])
                val y = Float.fromBits(vertices[index * VERTEX_STRIDE + 1])
                val z = Float.fromBits(vertices[index * VERTEX_STRIDE + 2])

                when (quad.face) {
                    Direction.DOWN -> minY = min(minY, y)
                    Direction.UP -> maxY = max(maxY, y)
                    Direction.NORTH -> minZ = min(minZ, z)
                    Direction.SOUTH -> maxZ = max(maxZ, z)
                    Direction.WEST -> minX = min(minX, x)
                    Direction.EAST -> maxX = max(maxX, x)
                }
            }
        }

        if (minX == Float.MAX_VALUE) {
            minX = 0.0f
            minY = 0.0f
            minZ = 0.0f
        }

        if (maxX == Float.MIN_VALUE) {
            maxX = 1.0f
            maxY = 1.0f
            maxZ = 1.0f
        }

        val sizeX = maxX - minX
        val sizeY = maxY - minY
        val sizeZ = maxZ - minZ
        val flat = sizeX > MIN_MODEL_SIZE && sizeY > MIN_MODEL_SIZE && sizeZ <= MIN_MODEL_SIZE
        val offsetY = if (flat) 0.5f - minY else 0.0f

        return ModelInfo(flat, offsetY, minZ - minY)
    }

    private fun ItemRenderState.quads(): List<BakedQuad> {
        val accessor = this as MixinItemRenderStateAccessor
        val layers = accessor.layers
        val layerCount = accessor.layerCount.coerceAtMost(layers.size)
        val quads = ArrayList<BakedQuad>()

        quadRandom.setSeed(0L)
        for (index in 0 until layerCount) {
            val layer = layers[index] ?: continue
            val model = (layer as MixinItemRenderStateLayerAccessor).model ?: continue

            quads += model.getQuads(null, null, quadRandom)
        }

        return quads
    }

    private data class ModelInfo(val flat: Boolean, val offsetY: Float, val offsetZ: Float)
    private data class RotationPair(val rotY: Float, val rotX: Float, val rotZ: Float)

    private const val VERTEX_COUNT = 4
    private const val VERTEX_STRIDE = 8
    private const val MIN_MODEL_SIZE = 0.0625f
    private const val FLAT_ITEM_ROTATION = 90.0f
    private const val FLAT_ROTATION_Z_FACTOR = 1.5f
    private const val FLAT_ROTATION_X_FACTOR = 0.7f
    private const val BLOCK_ROTATION_Z_FACTOR = 0.8f
    private const val STACK_SPREAD = 0.015f
    private const val WATER_BLOCK_OFFSET = 0.025
}
