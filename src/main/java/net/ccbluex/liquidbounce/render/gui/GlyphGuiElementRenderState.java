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
package net.ccbluex.liquidbounce.render.gui;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.state.gui.GuiElementRenderState;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;

/**
 * Submits a single glyph quad through vanilla 1.26.2's batched GUI render
 * path. Each glyph carries its own atlas UVs and ARGB color so a string
 * lowers to a sequence of these elements grouped by glyph page (texture).
 *
 * <p>Coordinates are absolute screen-space pixels; the current 2D pose is
 * captured at submission time and re-applied during flush.
 */
public record GlyphGuiElementRenderState(
    float x0,
    float y0,
    float x1,
    float y1,
    float u0,
    float v0,
    float u1,
    float v1,
    int argb,
    RenderPipeline pipeline,
    TextureSetup textureSetup,
    Matrix3x2f pose,
    @Nullable ScreenRectangle scissorArea,
    @Nullable ScreenRectangle bounds
) implements GuiElementRenderState {

    @Override
    public void buildVertices(VertexConsumer vertices) {
        vertices.addVertexWith2DPose(pose, x0, y0).setUv(u0, v0).setColor(argb);
        vertices.addVertexWith2DPose(pose, x0, y1).setUv(u0, v1).setColor(argb);
        vertices.addVertexWith2DPose(pose, x1, y1).setUv(u1, v1).setColor(argb);
        vertices.addVertexWith2DPose(pose, x1, y0).setUv(u1, v0).setColor(argb);
    }

    public static RenderPipeline defaultPipeline() {
        return RenderPipelines.GUI_TEXTURED;
    }
}
