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
package net.ccbluex.liquidbounce.utils.client
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.client.gui.components.ChatComponent
import net.minecraft.client.multiplayer.chat.GuiMessage
import net.minecraft.client.multiplayer.chat.GuiMessageSource
import net.minecraft.client.multiplayer.chat.GuiMessageTag
import net.minecraft.network.chat.Component
import java.util.WeakHashMap

private val messageIds = WeakHashMap<GuiMessage, String?>()
private val messageCounts = WeakHashMap<GuiMessage, Int>()

/**
 * Adds a message and assigns the ID to it.
 */
fun ChatComponent.addMessage(message: Component, id: String?, count: Int) {
    val tag = if (mc.isLocalServer) GuiMessageTag.systemSinglePlayer() else GuiMessageTag.system()
    val chatMessage = GuiMessage(mc.gui.guiTicks, message, null, GuiMessageSource.SYSTEM_CLIENT, tag)
    messageIds[chatMessage] = id
    messageCounts[chatMessage] = count
    this.logChatMessage(chatMessage)
    this.addMessageToDisplayQueue(chatMessage)
    this.addMessageToQueue(chatMessage)
}

/**
 * Removes all messages with the given ID.
 */
fun ChatComponent.removeMessage(id: String?) {
    allMessages.removeIf { messageIds[it] == id }
    trimmedMessages.removeIf { messageIds[it.parent()] == id }
}
