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
package net.ccbluex.liquidbounce.features.command.commands.ingame.creative
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.features.command.Command
import net.ccbluex.liquidbounce.features.command.CommandException
import net.ccbluex.liquidbounce.features.command.CommandFactory
import net.ccbluex.liquidbounce.features.command.builder.CommandBuilder
import net.ccbluex.liquidbounce.features.command.builder.ParameterBuilder
import net.ccbluex.liquidbounce.utils.client.*
import net.ccbluex.liquidbounce.utils.item.isNothing
import net.minecraft.core.component.DataComponents
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand

/**
 * ItemRename Command
 *
 * Allows you to rename an item held in the player's hand.
 */
object CommandItemRename : CommandFactory {

    override fun createCommand(): Command {
        return CommandBuilder
            .begin("rename")
            .requiresIngame()
            .parameter(
                ParameterBuilder
                    .begin<String>("name")
                    .verifiedBy(ParameterBuilder.STRING_VALIDATOR)
                    .optional()
                    .vararg()
                    .build()
            )
            .handler { command, args ->
                if (!player.hasInfiniteMaterials()) {
                    throw CommandException(command.result("mustBeCreative"))
                }

                val itemStack = player.getItemInHand(InteractionHand.MAIN_HAND)
                if (itemStack.isNothing()) {
                    throw CommandException(command.result("mustHoldItem"))
                }

                val name = (args.getOrElse(0, defaultValue = { arrayOf("") }) as Array<*>)
                    .joinToString(" ") { it as String }
                when (name) {
                    "" -> {
                        itemStack!!.remove(DataComponents.CUSTOM_NAME)
                        chat(regular(command.result("nameReset")), command)
                    }
                    else -> {
                        itemStack!!.set<Component>(DataComponents.CUSTOM_NAME, name.translateColorCodes().asText())
                        chat(regular(command.result("renamedItem", itemStack.hoverName, variable(name))), command)
                    }
                }
                network.send(ServerboundSetCreativeModeSlotPacket(36 + mc.player!!.inventory.selectedSlot, itemStack))
            }
            .build()
    }

}
