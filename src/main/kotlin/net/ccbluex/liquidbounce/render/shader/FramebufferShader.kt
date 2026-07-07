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

import com.mojang.blaze3d.platform.GlStateManager
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.common.GlobalFramebuffer
import net.ccbluex.liquidbounce.features.module.MinecraftShortcuts
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.pipeline.RenderTarget
import com.mojang.blaze3d.vertex.VertexBuffer
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import net.minecraft.client.gl.SimpleFramebuffer
import org.lwjgl.opengl.GL13
import java.io.Closeable

/**
 * @author ccetl
 */
open class FramebufferShader(vararg val shaders: Shader) : MinecraftShortcuts, Closeable {

    protected val framebuffers = mutableListOf<SimpleFramebuffer>()
    protected var buffer = VertexBuffer(GlUsage.DYNAMIC_WRITE)

    init {
        val width = mc.window.width
        val height = mc.window.height
        shaders.forEach { _ ->
            val framebuffer = SimpleFramebuffer(width, height, false)
            framebuffer.setClearColor(0f, 0f, 0f, 0f)
            framebuffers.add(framebuffer)
        }

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

    fun prepare() {
        val width = mc.window.width
        val height = mc.window.height
        framebuffers.forEach {
            if (it.textureWidth != width || it.textureHeight != height) {
                it.resize(width, height)
            }
        }

        framebuffers[0].clear()
        framebuffers[0].beginWrite(true)

        GlobalFramebuffer.push(framebuffers[0])
    }

    open fun apply(popFramebufferStack: Boolean = true) {
        if (popFramebufferStack) {
            GlobalFramebuffer.pop()
        }

        val active = GlStateManager._getActiveTexture()

        GlStateManager._bindTexture(0)
        RenderSystem.disableDepthTest()
        enableBlend()

        buffer.bind()

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        shaders.forEachIndexed { i, shader ->
            val inputFramebuffer = framebuffers.getOrNull(i) ?: framebuffers.first()
            val outputFramebuffer = framebuffers.getOrNull(i + 1)

            outputFramebuffer?.clear()
            outputFramebuffer?.beginWrite(true) ?: mc.framebuffer.beginWrite(false)

            GlStateManager._activeTexture(GL13.GL_TEXTURE0 + i)
            GlStateManager._bindTexture(inputFramebuffer.colorAttachment)

            shader.use()
            buffer.draw()
            shader.stop()
        }

        VertexBuffer.unbind()

        endBlend()
        RenderSystem.enableDepthTest()
        GlStateManager._activeTexture(active)
    }

    protected open fun enableBlend() {
        RenderSystem.enableBlend()
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA)
    }

    protected open fun endBlend() {
    }

    fun render(drawAction: () -> Unit) {
        prepare()
        drawAction()
        apply()
    }

    override fun close() {
        shaders.forEach { it.close() }
        buffer.close()
        framebuffers.forEach { it.delete() }
    }

}
