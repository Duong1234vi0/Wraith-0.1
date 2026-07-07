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
package net.ccbluex.liquidbounce.render.shader
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.compat.RenderSystem
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.vertex.VertexBuffer
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.DefaultVertexFormat

open class BlitShader(vertex: String, fragment: String, uniforms: Array<UniformProvider> = emptyArray()) :
    Shader(vertex, fragment, uniforms) {

    companion object {

        private var buffer = VertexBuffer(GlUsage.DYNAMIC_WRITE)

        init {
            val builder = Tesselator.getInstance()
            val bufferBuilder = builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX)
            bufferBuilder.addVertex(-1f, -1f, 0f).setUv(0f, 0f)
            bufferBuilder.addVertex(1f, -1f, 0f).setUv(1f, 0f)
            bufferBuilder.addVertex(1f, 1f, 0f).setUv(1f, 1f)
            bufferBuilder.addVertex(-1f, 1f, 0f).setUv(0f, 1f)
            buffer.bind()
            buffer.upload(bufferBuilder.buildOrThrow())
            VertexBuffer.unbind()
        }

    }

    fun blit() {
        RenderSystem.disableBlend()
        use()
        buffer.bind()
        buffer.draw()
        VertexBuffer.unbind()
        stop()
        RenderSystem.enableBlend()
    }

}
