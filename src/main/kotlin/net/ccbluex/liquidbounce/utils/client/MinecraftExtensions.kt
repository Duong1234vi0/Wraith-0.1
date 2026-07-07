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

import net.minecraft.client.Minecraft
import net.minecraft.client.User
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.components.ChatComponent
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.client.player.LocalPlayer
import net.minecraft.client.multiplayer.MultiPlayerGameMode
import com.mojang.blaze3d.platform.Window
import net.minecraft.client.multiplayer.ClientLevel


val Window.dimensions
    get() = Pair(width, height)

val Window.scaledDimension
    get() = Pair(guiScaledWidth, guiScaledHeight)

val Minecraft.world: ClientLevel?
    inline get() = level

val Minecraft.networkHandler: ClientPacketListener?
    inline get() = connection

val Minecraft.interactionManager: MultiPlayerGameMode?
    inline get() = gameMode

val Minecraft.inGameHud: Gui
    inline get() = gui

val Gui.chatHud: ChatComponent
    inline get() = chat

var Minecraft.session: User
    inline get() = user
    inline set(value) {
        user = value
    }

val mc: Minecraft
    inline get() = Minecraft.getInstance()!!
val player: LocalPlayer
    inline get() = mc.player!!
val world: ClientLevel
    inline get() = mc.world!!
val network: ClientPacketListener
    inline get() = mc.connection!!
val interaction: MultiPlayerGameMode
    inline get() = mc.gameMode!!
