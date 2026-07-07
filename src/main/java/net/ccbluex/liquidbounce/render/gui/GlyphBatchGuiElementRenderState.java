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
import net.minecraft.client.renderer.state.gui.GuiElementRenderState;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;

/**
 * Submits MANY glyph quads as ONE batched GUI element.
 *
 * #Edit By Claude 2026-06-18: PERF — the previous {@link GlyphGuiElementRenderState} path submitted one
 * GuiElementRenderState PER GLYPH. Vanilla's GUI render path sorts + state-sets + allocates a TextureSetup
 * per element, so a dense GUI surface (≈800-1500 glyphs/frame) became 800-1500 elements and dropped FPS ~33×
 * (the old {@code text()} 22px gate used to route most strings to {@code mc.font}, which batches a whole
 * string into ONE element). All glyphs in a single drawCustomFontText call share the same font page →
 * same texture, pose and scissor → they collapse into one element whose {@link #buildVertices} emits every
 * glyph's quad. Vertex order matches {@link GlyphGuiElementRenderState}: TL, BL, BR, TR.
 *
 * <p>{@code quads} is a flat array of 8 floats per glyph: x0,y0,x1,y1,u0,v0,u1,v1. {@code colors} holds
 * one ARGB per glyph. {@code count} is the number of glyphs.
 */
public record GlyphBatchGuiElementRenderState(
    float[] quads,
    int[] colors,
    int count,
    RenderPipeline pipeline,
    TextureSetup textureSetup,
    Matrix3x2f pose,
    @Nullable ScreenRectangle scissorArea,
    @Nullable ScreenRectangle bounds
) implements GuiElementRenderState {

    @Override
    public void buildVertices(VertexConsumer vertices) {
        for (int i = 0; i < count; i++) {
            int q = i * 8;
            float x0 = quads[q];
            float y0 = quads[q + 1];
            float x1 = quads[q + 2];
            float y1 = quads[q + 3];
            float u0 = quads[q + 4];
            float v0 = quads[q + 5];
            float u1 = quads[q + 6];
            float v1 = quads[q + 7];
            int c = colors[i];
            vertices.addVertexWith2DPose(pose, x0, y0).setUv(u0, v0).setColor(c);
            vertices.addVertexWith2DPose(pose, x0, y1).setUv(u0, v1).setColor(c);
            vertices.addVertexWith2DPose(pose, x1, y1).setUv(u1, v1).setColor(c);
            vertices.addVertexWith2DPose(pose, x1, y0).setUv(u1, v0).setColor(c);
        }
    }

    @Override
    public RenderPipeline pipeline() {
        return pipeline;
    }

    @Override
    public TextureSetup textureSetup() {
        return textureSetup;
    }
}
