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
 * Textured GUI element whose fragment shader clips the texture to a rounded
 * rectangle. The shader ignores RGB from {@code shaderColor}; red encodes the
 * normalized radius and alpha carries draw opacity.
 */
public record RoundedTextureGuiElementRenderState(
    float x0,
    float y0,
    float x1,
    float y1,
    float u0,
    float v0,
    float u1,
    float v1,
    int argb,
    float radiusPx,
    TextureSetup textureSetup,
    Matrix3x2f pose,
    @Nullable ScreenRectangle scissorArea,
    @Nullable ScreenRectangle bounds
) implements GuiElementRenderState {

    private static final int LOCAL_MIN = -32767;
    private static final int LOCAL_MAX = 32767;

    private int shaderColor() {
        int alpha = (argb >>> 24) & 0xFF;
        float minSide = Math.max(1.0f, Math.min(x1 - x0, y1 - y0));
        float normalizedRadius = Math.max(0.0f, Math.min(1.0f, radiusPx / minSide * 2.0f));
        int radiusByte = Math.round(normalizedRadius * 255.0f) & 0xFF;

        return (alpha << 24) | (radiusByte << 16) | 0xFFFF;
    }

    @Override
    public void buildVertices(VertexConsumer vertices) {
        int color = shaderColor();

        vertices.addVertexWith2DPose(pose, x0, y0).setUv(u0, v0).setColor(color).setUv1(LOCAL_MIN, LOCAL_MIN);
        vertices.addVertexWith2DPose(pose, x0, y1).setUv(u0, v1).setColor(color).setUv1(LOCAL_MIN, LOCAL_MAX);
        vertices.addVertexWith2DPose(pose, x1, y1).setUv(u1, v1).setColor(color).setUv1(LOCAL_MAX, LOCAL_MAX);
        vertices.addVertexWith2DPose(pose, x1, y0).setUv(u1, v0).setColor(color).setUv1(LOCAL_MAX, LOCAL_MIN);
    }

    @Override
    public RenderPipeline pipeline() {
        return RoundedTextureShader.PIPELINE;
    }
}
