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

public record MsdfTextBatchGuiElementRenderState(
    float[] quads,
    int[] colors,
    float range,
    float thickness,
    float smoothness,
    TextureSetup textureSetup,
    Matrix3x2f pose,
    @Nullable ScreenRectangle scissorArea,
    @Nullable ScreenRectangle bounds
) implements GuiElementRenderState {

    private static final float FIXED_POINT = 1024.0f;

    private static int toFixed(float value) {
        float clamped = Math.max(-31.0f, Math.min(31.0f, value));
        return Math.round(clamped * FIXED_POINT);
    }

    @Override
    public void buildVertices(VertexConsumer vertices) {
        int rangeFixed = toFixed(range);
        int thicknessFixed = toFixed(thickness);
        int smoothnessFixed = toFixed(smoothness);

        for (int index = 0; index < colors.length; index++) {
            int offset = index * 8;
            float x0 = quads[offset];
            float y0 = quads[offset + 1];
            float x1 = quads[offset + 2];
            float y1 = quads[offset + 3];
            float u0 = quads[offset + 4];
            float v0 = quads[offset + 5];
            float u1 = quads[offset + 6];
            float v1 = quads[offset + 7];
            int color = colors[index];

            vertices.addVertexWith2DPose(pose, x0, y0).setUv(u0, v0).setColor(color)
                .setUv1(rangeFixed, thicknessFixed).setUv2(smoothnessFixed, 0);
            vertices.addVertexWith2DPose(pose, x0, y1).setUv(u0, v1).setColor(color)
                .setUv1(rangeFixed, thicknessFixed).setUv2(smoothnessFixed, 0);
            vertices.addVertexWith2DPose(pose, x1, y1).setUv(u1, v1).setColor(color)
                .setUv1(rangeFixed, thicknessFixed).setUv2(smoothnessFixed, 0);
            vertices.addVertexWith2DPose(pose, x1, y0).setUv(u1, v0).setColor(color)
                .setUv1(rangeFixed, thicknessFixed).setUv2(smoothnessFixed, 0);
        }
    }

    @Override
    public RenderPipeline pipeline() {
        return MsdfFontShader.PIPELINE;
    }
}
