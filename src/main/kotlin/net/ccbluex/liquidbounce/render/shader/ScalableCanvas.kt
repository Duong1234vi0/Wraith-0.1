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
 *
 *
 */
package net.ccbluex.liquidbounce.render.shader
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.Minecraft
import com.mojang.blaze3d.pipeline.RenderTarget
import com.mojang.blaze3d.vertex.VertexBuffer
import net.minecraft.client.gl.SimpleFramebuffer
import org.joml.Matrix4f
import java.io.Closeable

class ScalableCanvas : Closeable {

    private val identity = Matrix4f()
    private val output = Minecraft.getInstance().framebuffer
    private val input = SimpleFramebuffer(output.textureWidth, output.textureHeight, false)

    fun resize(width: Int, height: Int) {
        if (width() != width && height() != height && width > 0 && height > 0) {
            input.resize(width, height)
        }
    }

    fun width() = input.textureWidth

    fun height() = input.textureHeight

    fun write() = input.beginWrite(true)

    fun read() = input.beginRead()

    fun blit(buffer: VertexBuffer, alpha: Float = 1f) {
        output.beginWrite(true)

        RenderSystem.setShaderTexture(0, input.colorAttachment)
        RenderSystem.setShaderColor(1f, 1f, 1f, alpha)

        RenderSystem.enableBlend()
        @Suppress("DEPRECATION")
        buffer.draw()
    }

    override fun close() {
        input.delete()
    }

}
