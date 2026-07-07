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
package net.ccbluex.liquidbounce.features.module.modules.world.fucker
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.utils.inventory.getArmorColor
import net.ccbluex.liquidbounce.utils.math.toVec3d
import net.minecraft.world.level.block.BedBlock
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3

fun isSelfBedChoices(choice: ChoiceConfigurable<IsSelfBedChoice>): Array<IsSelfBedChoice> {
    return arrayOf(
        IsSelfBedNoneChoice(choice),
        IsSelfBedColorChoice(choice),
        IsSelfBedSpawnLocationChoice(choice)
    )
}

sealed class IsSelfBedChoice(name: String, override val parent: ChoiceConfigurable<*>) : Choice(name) {
    abstract fun isSelfBed(block: BedBlock, pos: BlockPos): Boolean
    open fun shouldDefend(block: BedBlock, pos: BlockPos): Boolean = isSelfBed(block, pos)
}

class IsSelfBedNoneChoice(parent: ChoiceConfigurable<*>) : IsSelfBedChoice("None", parent) {
    override fun isSelfBed(block: BedBlock, pos: BlockPos) = false
    override fun shouldDefend(block: BedBlock, pos: BlockPos) = true
}

class IsSelfBedSpawnLocationChoice(parent: ChoiceConfigurable<*>) : IsSelfBedChoice("SpawnLocation", parent) {

    private val bedDistance by float("BedDistance", 24.0f, 16.0f..48.0f)
    private var spawnLocation: Vec3? = null

    override fun isSelfBed(block: BedBlock, pos: BlockPos) =
        spawnLocation?.distanceTo(pos.toVec3d())?.let { it <= bedDistance.toDouble() } ?: false

    @Suppress("unused")
    private val gameStartHandler = handler<PacketEvent> {
        val packet = it.packet

        if (packet is ClientboundPlayerPositionPacket) {
            val pos = packet.change.position
            val packetPos = Vec3(pos.x, pos.y, pos.z)
            val dist = player.pos.distanceTo(packetPos)

            if (dist > 16.0) {
                spawnLocation = packetPos
            }
        }
    }

}

class IsSelfBedColorChoice(parent: ChoiceConfigurable<*>) : IsSelfBedChoice("Color", parent) {
    override fun isSelfBed(block: BedBlock, pos: BlockPos): Boolean {
        val color = block.getColor()
        val colorRgb = color.mapColor.col
        val (_, armorColor) = getArmorColor() ?: return false

        return armorColor == colorRgb
    }
}
