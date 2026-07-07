/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 202 CCBlueX
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
package net.ccbluex.liquidbounce.config.gson.adapter
import net.ccbluex.liquidbounce.utils.entity.*

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import net.minecraft.core.Registry
import net.minecraft.resources.Identifier

abstract class IdentifierAsStringAdapter<T : Any>(val registry: Registry<T>) : TypeAdapter<T>() {

    override fun read(source: JsonReader): T? {
        val id = Identifier.tryParse(source.nextString()) ?: return null
        return registry.getValue(id)
    }

    override fun write(sink: JsonWriter, value: T?) {
        val id = value?.let { registry.getKey(it) }
        if (id == null) {
            sink.nullValue()
        } else {
            sink.value(id.toString())
        }
    }

}
