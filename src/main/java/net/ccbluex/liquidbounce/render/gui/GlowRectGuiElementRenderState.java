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
 * GUI element that draws a soft SDF outer-glow halo around a rounded
 * rectangle through vanilla 1.26.2's batched GUI render path.
 *
 * <p>The submitted quad must already be EXPANDED by {@code spreadPx} on every
 * side (the caller pads the panel bounds); {@code halfSize} carries the
 * expanded half-extents and {@code RadiusStroke.y} carries the spread so the
 * fragment shader can recover the true panel edge and fade outward from it.
 * Uses {@link GlowRectShader#PIPELINE} (ADDITIVE blend) so layered glows
 * accumulate into a bloom.
 */
public record GlowRectGuiElementRenderState(
    float x0,
    float y0,
    float x1,
    float y1,
    int colorTopLeft,
    int colorTopRight,
    int colorBottomRight,
    int colorBottomLeft,
    float radiusPx,
    float spreadPx,
    Matrix3x2f pose,
    @Nullable ScreenRectangle scissorArea,
    @Nullable ScreenRectangle bounds
) implements GuiElementRenderState {

    private static final float FIXED_POINT = 16.0f;

    private static int toFixed(float v) {
        float clamped = Math.max(-2048.0f, Math.min(2048.0f, v));
        return Math.round(clamped * FIXED_POINT);
    }

    @Override
    public void buildVertices(VertexConsumer vertices) {
        float halfWidth = Math.max(0.5f, (x1 - x0) * 0.5f);
        float halfHeight = Math.max(0.5f, (y1 - y0) * 0.5f);
        int hsX = toFixed(halfWidth);
        int hsY = toFixed(halfHeight);
        int rs0 = toFixed(radiusPx);
        int rs1 = toFixed(spreadPx);

        vertices.addVertexWith2DPose(pose, x0, y0).setUv(-1.0f, -1.0f).setColor(colorTopLeft)
            .setUv1(hsX, hsY).setUv2(rs0, rs1);
        vertices.addVertexWith2DPose(pose, x0, y1).setUv(-1.0f, 1.0f).setColor(colorBottomLeft)
            .setUv1(hsX, hsY).setUv2(rs0, rs1);
        vertices.addVertexWith2DPose(pose, x1, y1).setUv(1.0f, 1.0f).setColor(colorBottomRight)
            .setUv1(hsX, hsY).setUv2(rs0, rs1);
        vertices.addVertexWith2DPose(pose, x1, y0).setUv(1.0f, -1.0f).setColor(colorTopRight)
            .setUv1(hsX, hsY).setUv2(rs0, rs1);
    }

    @Override
    public RenderPipeline pipeline() {
        return GlowRectShader.PIPELINE;
    }

    @Override
    public TextureSetup textureSetup() {
        return TextureSetup.noTexture();
    }
}
