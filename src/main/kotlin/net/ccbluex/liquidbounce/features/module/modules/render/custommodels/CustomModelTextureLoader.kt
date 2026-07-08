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
package net.ccbluex.liquidbounce.features.module.modules.render.custommodels

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.registerDynamicImage
import net.minecraft.resources.Identifier
import java.util.EnumMap
import java.util.EnumSet

object CustomModelTextureLoader {

    private val textures = EnumMap<CustomModelType, Identifier>(CustomModelType::class.java)
    private val failed = EnumSet.noneOf(CustomModelType::class.java)

    fun texture(spec: CustomModelSpec): Identifier? {
        if (spec.type in failed) {
            return null
        }

        textures[spec.type]?.let { return it }

        val textureId = Identifier.fromNamespaceAndPath("liquidbounce", "custommodels/${spec.type.name.lowercase()}")
        return runCatching {
            val path = "/resources/liquidbounce/${spec.textureResource}"
            val stream = LiquidBounce.javaClass.getResourceAsStream(path)
                ?: error("Resource $path was not found")
            stream.use { textureId.registerDynamicImage(it) }
            textureId
        }.onFailure { throwable ->
            failed += spec.type
            logger.error(
                "Failed to load CustomModels texture ${spec.textureResource} for ${spec.displayName}; " +
                    "only that model will be skipped.",
                throwable
            )
        }.getOrNull()?.also { textures[spec.type] = it }
    }
}
