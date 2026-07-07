@file:Suppress("NOTHING_TO_INLINE")
package net.ccbluex.liquidbounce.utils.client
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.LiquidBounce
import com.mojang.blaze3d.platform.NativeImage
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.resources.Identifier
import java.io.InputStream
import java.util.*

/**
 * @param path prefix /resources/liquidbounce/$path
 */
inline fun Identifier.registerDynamicImageFromResources(path: String) {
    with(LiquidBounce.javaClass.getResourceAsStream("/resources/liquidbounce/$path")!!) {
        this@registerDynamicImageFromResources.registerDynamicImage(this)
    }
}

@Suppress("MagicNumber")
inline fun String.registerAsDynamicImageFromClientResources(): Identifier =
    Identifier.fromNamespaceAndPath("liquidbounce", "dynamic-texture-" + System.currentTimeMillis().toString(36)).apply {
        registerDynamicImageFromResources(this@registerAsDynamicImageFromClientResources)
    }

inline fun Identifier.registerDynamicImage(image: InputStream) {
    this.registerDynamicImage(NativeImage.read(image))
}

inline fun Identifier.registerDynamicImage(image: NativeImage) {
    mc.textureManager.register(this, DynamicTexture({ this.toString() }, image))
}

/**
 * Converts an [Identifier] to a human-readable name without localization.
 */
inline fun Identifier.toName() = toString()
    .split(':')
    .last()
    .replace('.', ' ')
    .replace('_', ' ')
    .split(' ')
    .joinToString(" ") { word ->
        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
    }
