/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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
package net.ccbluex.liquidbounce.render.font.msdf

import com.google.gson.Gson
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.resources.Identifier

object MsdfFontLibrary {
    private const val NAMESPACE = "liquidbounce"
    private const val FONT_PATH = "fonts/msdf"

    private val gson = Gson()
    private val cache = LinkedHashMap<String, MsdfFont>()
    private val loadedTextureIds = LinkedHashSet<Identifier>()

    fun get(resourceName: String): MsdfFont {
        val resolvedName = MsdfFont.resolveMsdfResourceName(resourceName)
        return cache.getOrPut(resolvedName) { loadFont(resolvedName) }
    }

    fun resolveType(typeName: String): MsdfFont = get(resolveTypeName(typeName))

    fun preloadRequiredFonts(fonts: Iterable<String>) {
        fonts.forEach(::get)
    }

    fun clearCache() {
        cache.clear()
        loadedTextureIds.forEach { textureId ->
            runCatching { mc.textureManager.release(textureId) }
        }
        loadedTextureIds.clear()
    }

    private fun loadFont(resourceName: String): MsdfFont {
        val dataId = Identifier.fromNamespaceAndPath(NAMESPACE, "$FONT_PATH/$resourceName.json")
        val textureId = Identifier.fromNamespaceAndPath(NAMESPACE, "$FONT_PATH/$resourceName.png")
        val resource = mc.resourceManager.getResource(dataId).orElseThrow {
            IllegalStateException("Required MSDF font JSON missing: $dataId")
        }

        val data = resource.open().use { input ->
            input.bufferedReader().use { reader -> gson.fromJson(reader, FontData::class.java) }
        } ?: throw IllegalStateException("Unable to parse MSDF font JSON: $dataId")

        require(data.atlas.width > 0.0f && data.atlas.height > 0.0f) {
            "Invalid MSDF atlas size in $dataId: ${data.atlas.width}x${data.atlas.height}"
        }

        val texture = runCatching { mc.textureManager.getTexture(textureId) }
            .getOrElse { throw IllegalStateException("Required MSDF font atlas missing: $textureId", it) }
        if (texture.textureView == null) {
            throw IllegalStateException("MSDF font atlas has no GPU texture view: $textureId")
        }

        loadedTextureIds += textureId
        LiquidBounce.logger.debug("Loaded MSDF font $resourceName from $dataId / $textureId")
        return MsdfFont.fromData(resourceName, textureId, data)
    }

    private fun resolveTypeName(typeName: String): String = when (typeName) {
        "sf_medium" -> "medium"
        "sf_regular" -> "regular"
        "sf_bold" -> "bold"
        "sf_semibold" -> "regular_semibold"
        "suisseintl-medium" -> "suisseintlmedium"
        "suisseintl-regular" -> "suisseintlregular"
        "suisseintl-semibold" -> "suisseintlsemibold"
        else -> typeName
    }
}
