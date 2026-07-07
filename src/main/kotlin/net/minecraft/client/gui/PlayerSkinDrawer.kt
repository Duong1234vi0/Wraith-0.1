package net.minecraft.client.gui

import net.minecraft.resources.Identifier

object PlayerSkinDrawer {
    @JvmStatic
    fun draw(context: DrawContext, texture: Identifier, x: Int, y: Int, size: Int) {
        // Temporary 26.1.2 compatibility shim. The old helper was replaced by the new GUI face extractor path.
    }

    @JvmStatic
    fun draw(context: DrawContext, texture: Identifier, x: Int, y: Int, size: Int, hat: Boolean, upsideDown: Boolean) {
        draw(context, texture, x, y, size)
    }
}
