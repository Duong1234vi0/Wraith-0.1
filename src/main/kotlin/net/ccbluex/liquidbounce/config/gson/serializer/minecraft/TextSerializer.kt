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

package net.ccbluex.liquidbounce.config.gson.serializer.minecraft
import net.ccbluex.liquidbounce.utils.entity.*

import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.mojang.serialization.JsonOps
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.processContent
import net.minecraft.core.RegistryAccess
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.resources.RegistryOps
import java.lang.reflect.Type

object TextSerializer : JsonSerializer<Component> {
    override fun serialize(src: Component?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement =
        ComponentSerialization.CODEC.encodeStart(
            RegistryOps.create(JsonOps.INSTANCE, mc.level?.registryAccess() ?: RegistryAccess.EMPTY),
            src?.processContent() ?: Component.empty()
        ).getOrThrow()
}
