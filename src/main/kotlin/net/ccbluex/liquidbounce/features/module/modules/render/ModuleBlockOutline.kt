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
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.injection.mixins.minecraft.render.MixinWorldRenderer
import net.ccbluex.liquidbounce.render.BoxRenderer
import net.ccbluex.liquidbounce.render.drawBoxSide
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.utils.block.boundingBox
import net.ccbluex.liquidbounce.utils.block.getOutlineShape
import net.ccbluex.liquidbounce.utils.math.Easing
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.AABB
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.phys.shapes.VoxelShape

/**
 * Block Outline module
 *
 * Changes the way Minecraft highlights blocks.
 *
 * TODO: Implement GUI Information Panel
 *
 * [MixinWorldRenderer.cancelBlockOutline]
 */
object ModuleBlockOutline : ClientModule("BlockOutline", Category.RENDER, aliases = arrayOf("BlockOverlay")) {

    private val sideOnly by boolean("SideOnly", true)
    private val color by color("Color", Color4b(68, 117, 255, 70))
    private val outlineColor by color("Outline", Color4b(68, 117, 255, 150))

    private object Slide : ToggleableConfigurable(this, "Slide", true) {
        val time by int("Time", 150, 1..1000, "ms")
        val easing by curve("Easing", Easing.LINEAR)
    }

    init {
        tree(Slide)
    }

    private var currentPosition: AABB? = null
    private var previousPosition: AABB? = null
    private var lastChange = 0L

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val target = mc.crosshairTarget
        if (target !is BlockHitResult || target.getType() == HitResult.Type.MISS) {
            resetPositions()
            return@handler
        }

        val blockPos = target.blockPosition()
        val blockState = world.getBlockState(blockPos)
        if (blockState.isAir || !world.worldBorder.isWithinBounds(blockPos)) {
            resetPositions()
            return@handler
        }

        val side = target.side
        val box = blockState.getOutlineShape(world, blockPos)
        val finalPosition = (if (sideOnly) flatBox(box, side) else box.boundingBox)
            .offset(blockPos.x.toDouble(), blockPos.y.toDouble(), blockPos.z.toDouble())
        if (currentPosition != finalPosition) {
            previousPosition = currentPosition
            currentPosition = finalPosition
            lastChange = System.currentTimeMillis()
        }

        val renderPosition = if (previousPosition != null && Slide.running) {
            val factor = Slide.easing.getFactor(lastChange, System.currentTimeMillis(), Slide.time.toFloat()).toDouble()

            val previousPosition = previousPosition!!
            AABB(
                Mth.lerp(factor, previousPosition.minX, finalPosition.minX),
                Mth.lerp(factor, previousPosition.minY, finalPosition.minY),
                Mth.lerp(factor, previousPosition.minZ, finalPosition.minZ),
                Mth.lerp(factor, previousPosition.maxX, finalPosition.maxX),
                Mth.lerp(factor, previousPosition.maxY, finalPosition.maxY),
                Mth.lerp(factor, previousPosition.maxZ, finalPosition.maxZ)
            )
        } else {
            finalPosition
        }

        val translatedPosition = renderPosition.move(-event.camera.position().x, -event.camera.position().y, -event.camera.position().z)
        renderEnvironmentForWorld(event.matrixStack) {
            if (sideOnly) {
                drawBoxSide(translatedPosition, side, color, outlineColor)
            } else {
                BoxRenderer.drawWith(this) {
                    drawBox(translatedPosition, color, outlineColor)
                }
            }
        }
    }

    private fun flatBox(shape: VoxelShape, side: Direction) = when (side) {
        Direction.UP -> shape.boxWithBoundsY(shape.getMax(Direction.Axis.Y))
        Direction.DOWN -> shape.boxWithBoundsY(shape.getMin(Direction.Axis.Y))
        Direction.NORTH -> shape.boxWithBoundsZ(shape.getMin(Direction.Axis.Z))
        Direction.SOUTH -> shape.boxWithBoundsZ(shape.getMax(Direction.Axis.Z))
        Direction.WEST -> shape.boxWithBoundsX(shape.getMin(Direction.Axis.X))
        Direction.EAST -> shape.boxWithBoundsX(shape.getMax(Direction.Axis.X))
    }

    private fun VoxelShape.boxWithBoundsX(x: Double) = AABB(
        x,
        getMin(Direction.Axis.Y),
        getMin(Direction.Axis.Z),
        x,
        getMax(Direction.Axis.Y),
        getMax(Direction.Axis.Z)
    )

    private fun VoxelShape.boxWithBoundsY(y: Double) = AABB(
        getMin(Direction.Axis.X),
        y,
        getMin(Direction.Axis.Z),
        getMax(Direction.Axis.X),
        y,
        getMax(Direction.Axis.Z)
    )

    private fun VoxelShape.boxWithBoundsZ(z: Double) = AABB(
        getMin(Direction.Axis.X),
        getMin(Direction.Axis.Y),
        z,
        getMax(Direction.Axis.X),
        getMax(Direction.Axis.Y),
        z
    )

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetPositions()
        lastChange = System.currentTimeMillis()
    }

    private fun resetPositions() {
        currentPosition = null
        previousPosition = null
    }

}
