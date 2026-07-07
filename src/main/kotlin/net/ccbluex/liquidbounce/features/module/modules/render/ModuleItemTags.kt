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

import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.computedOn
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.engine.type.Vec3 as RenderVec3
import net.ccbluex.liquidbounce.render.renderEnvironmentForGUI
import net.ccbluex.liquidbounce.utils.entity.box
import net.ccbluex.liquidbounce.utils.kotlin.forEachWithSelf
import net.ccbluex.liquidbounce.utils.kotlin.proportionOfValue
import net.ccbluex.liquidbounce.utils.kotlin.valueAtProportion
import net.ccbluex.liquidbounce.utils.math.Easing
import net.ccbluex.liquidbounce.utils.math.average
import net.ccbluex.liquidbounce.utils.math.sq
import net.ccbluex.liquidbounce.utils.render.WorldToScreen
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.Vec3
import java.util.IdentityHashMap
import kotlin.collections.component1
import kotlin.collections.component2

private const val ITEM_SIZE: Int = 16
private const val ITEM_SCALE: Float = 1.0F
private const val BACKGROUND_PADDING: Int = 2

/**
 * ItemTags module
 *
 * Show the names and quantities of items in several boxes.
 */
object ModuleItemTags : ClientModule("ItemTags", Category.RENDER) {

    override val baseKey: String
        get() = "liquidbounce.module.itemTags"

    private val clusterSizeMode = choices("ClusterSizeMode", ClusterSizeMode.Static,
        arrayOf(ClusterSizeMode.Static, ClusterSizeMode.Distance))
    private val scale by float("Scale", 1.5F, 0.25F..4F)
    private val renderY by float("RenderY", 0F, -2F..2F)
    private val maximumDistance by float("MaximumDistance", 128F, 1F..256F)

    private sealed class ClusterSizeMode(name: String) : Choice(name) {
        override val parent: ChoiceConfigurable<*>
            get() = clusterSizeMode

        abstract fun size(entity: ItemEntity): Float

        object Static : ClusterSizeMode("Static") {
            private val size = float("Size", 1F, 0.1F..32F)
            override fun size(entity: ItemEntity): Float = size.get()
        }

        object Distance : ClusterSizeMode("Distance") {
            private val size by floatRange("Size", 1F..16F, 0.1F..32.0F)
            private val range by floatRange("Range", 32F..64F, 1F..256F)
            private val curve by curve("Curve", Easing.LINEAR)

            override fun size(entity: ItemEntity): Float {
                val playerDistance = player.distanceTo(entity)
                return size.valueAtProportion(curve.transform(range.proportionOfValue(playerDistance)))
            }
        }
    }

    private var itemEntities by computedOn<GameTickEvent, Map<Vec3, List<ItemStack>>>(
        initialValue = emptyMap()
    ) { _, _ ->
        val maxDistSquared = maximumDistance.sq()

        @Suppress("UNCHECKED_CAST")
        (world.entitiesForRendering().filter {
            it is ItemEntity && it.squaredDistanceTo(player) < maxDistSquared
        } as List<ItemEntity>).cluster()
    }

    override fun disable() {
        itemEntities = emptyMap()
    }

    @Suppress("unused")
    private val worldHandler = handler<WorldChangeEvent> {
        itemEntities = emptyMap()
    }

    @Suppress("unused")
    private val renderHandler = handler<OverlayRenderEvent> { event ->
        renderEnvironmentForGUI {
            itemEntities.mapNotNull { (center, items) ->
                val renderPos = WorldToScreen.calculateScreenPos(center.add(0.0, renderY.toDouble(), 0.0))
                    ?: return@mapNotNull null
                renderPos to items
            }.forEachWithSelf { (center, items), i, self ->
                val z = 1000.0F * i / self.size
                drawItemTags(event.context, items, center.copy(z = z))
            }
        }
    }

    @JvmStatic
    private fun drawItemTags(
        dc: net.minecraft.client.gui.DrawContext,
        items: List<ItemStack>,
        pos: RenderVec3,
    ) {
        // #Edit By Codex: matrices.translate/scale are no-ops on the fake PoseStack
        // (vanilla 1.26.2 GuiGraphicsExtractor uses Matrix3x2fStack pose, not 3D PoseStack).
        // Compute screen-space coords manually.
        val itemScale = ITEM_SCALE * scale
        val width = (items.size * ITEM_SIZE * itemScale).toInt()
        val height = (ITEM_SIZE * itemScale).toInt()

        val originX = (pos.x - width / 2.0).toInt()
        val originY = (pos.y - height / 2.0).toInt()

        val pad = (BACKGROUND_PADDING * itemScale).toInt()

        // draw background
        dc.fill(
            originX - pad,
            originY - pad,
            originX + width + pad,
            originY + height + pad,
            Color4b(0, 0, 0, 128).toARGB()
        )

        // render stacks at absolute coords (drawItem doesn't honour the PoseStack either,
        // so we can't scale individual items beyond the unscaled 16x16 tile here).
        items.forEachIndexed { index, stack ->
            val leftX = originX + (index * ITEM_SIZE * itemScale).toInt()
            dc.drawItem(stack, leftX, originY)
            dc.drawStackOverlay(mc.textRenderer, stack, leftX, originY)
        }
    }

    @JvmStatic
    private fun List<ItemEntity>.cluster(): Map<Vec3, List<ItemStack>> {
        if (this.isEmpty()) {
            return emptyMap()
        }

        val groups = mutableListOf<Set<ItemEntity>>()
        val visited = hashSetOf<ItemEntity>()

        for (entity in this) {
            if (entity in visited) continue

            val radiusSquared = clusterSizeMode.activeChoice.size(entity).sq()

            // `entity` will also be added
            val group = this.filterTo(hashSetOf()) { other ->
                other !in visited && entity.squaredDistanceTo(other) < radiusSquared
            }

            visited.addAll(group)
            groups.add(group)
        }

        return groups.associate { entities ->
            Pair(
                // Get the center pos of all entities
                entities.map { it.box.center }.average(),
                entities.mergeStacks(),
            )
        }
    }

    /**
     * Merge stacks with same item, order by count desc
     */
    @JvmStatic
    private fun Set<ItemEntity>.mergeStacks(): List<ItemStack> {
        val map = IdentityHashMap<Item, MutableList<ItemStack>>()
        for (itemEntity in this) {
            map.getOrPut(itemEntity.stack.item, ::mutableListOf).add(itemEntity.stack)
        }
        val result = ArrayList<ItemStack>(map.size)
        map.values.forEach { stacks ->
            if (stacks.size == 1) {
                result.add(stacks[0])
            } else {
                result.add(ItemStack(stacks[0].item, stacks.sumOf { it.count }))
            }
        }
        result.sortByDescending { it.count }
        return result
    }

}
