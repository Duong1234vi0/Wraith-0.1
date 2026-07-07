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
 * GUI element that draws an anti-aliased SDF rounded rectangle through
 * vanilla 1.26.2's batched GUI render path ({@link
 * net.minecraft.client.renderer.state.gui.GuiRenderState#addGuiElement}).
 *
 * <p>Coordinates are in screen-space pixels. Per-corner colors enable a
 * full bilinear gradient over the quad. {@code radiusPx} controls corner
 * rounding; {@code strokePx} drives the SDF in outline mode (a positive
 * value paints only a band of that width on the inside of the edge).
 *
 * <p>The {@code halfSize} carried by every vertex is the same half-extent
 * pair, so the fragment shader can recover the rectangle dimensions
 * regardless of pose / scissor.
 */
public record RoundedRectGuiElementRenderState(
    float x0,
    float y0,
    float x1,
    float y1,
    int colorTopLeft,
    int colorTopRight,
    int colorBottomRight,
    int colorBottomLeft,
    float radiusPx,
    float bottomRadiusPx,
    float strokePx,
    int cornerMask,
    Matrix3x2f pose,
    @Nullable ScreenRectangle scissorArea,
    @Nullable ScreenRectangle bounds
) implements GuiElementRenderState {

    private static final float FIXED_POINT = 16.0f;
    // #Edit By Claude 2026-06-11: stroke occupies the low 11 bits of RadiusStroke.y
    // (so up to ~127px at px*16 fixed point); the corner mask sits in bits 11..14.
    // 1.26.2's VertexConsumer only exposes UV0/UV1/UV2 (no third ivec2 slot), so the
    // mask has to share the stroke short. See rounded_rect.vsh for the matching unpack.
    private static final int STROKE_BITS = 0x7FF;
    private static final int CORNER_MASK_ALL = 0xF;
    // #Edit By Codex 2026-06-19: negative RadiusStroke.x is a compact sentinel
    // for top/bottom radii. Existing positive values still mean one shared radius.
    private static final float UNEVEN_RADIUS_SCALE = 4.0f;
    private static final int UNEVEN_RADIUS_MASK = 0x7F;

    private static int toFixed(float v) {
        // Clamp so fixed-point conversion never overflows the int16 attribute.
        float clamped = Math.max(-2048.0f, Math.min(2048.0f, v));
        return Math.round(clamped * FIXED_POINT);
    }

    private static int packUnevenRadius(float topRadius, float bottomRadius) {
        int topFixed = Math.max(0, Math.min(UNEVEN_RADIUS_MASK, Math.round(topRadius * UNEVEN_RADIUS_SCALE)));
        int bottomFixed = Math.max(0, Math.min(UNEVEN_RADIUS_MASK, Math.round(bottomRadius * UNEVEN_RADIUS_SCALE)));
        int packed = (bottomFixed << 7) | topFixed;

        return -Math.max(1, packed);
    }

    @Override
    public void buildVertices(VertexConsumer vertices) {
        float halfWidth = Math.max(0.5f, (x1 - x0) * 0.5f);
        float halfHeight = Math.max(0.5f, (y1 - y0) * 0.5f);
        int hsX = toFixed(halfWidth);
        int hsY = toFixed(halfHeight);
        int rs0 = Math.abs(bottomRadiusPx - radiusPx) > 0.01f
            ? packUnevenRadius(radiusPx, bottomRadiusPx)
            : toFixed(radiusPx);
        // #Edit By Claude 2026-06-11: pack (stroke | cornerMask) into the second short.
        int mask = (cornerMask <= 0 ? CORNER_MASK_ALL : cornerMask) & 0xF;
        int strokeFixed = Math.min(toFixed(Math.max(0.0f, strokePx)), STROKE_BITS) & STROKE_BITS;
        int rs1 = strokeFixed | (mask << 11);

        // UV0 is in [-1, 1] across the quad: fragment scales by HalfSize to
        // recover pixel-space coords for the SDF.
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
        return RoundedRectShader.PIPELINE;
    }

    @Override
    public TextureSetup textureSetup() {
        return TextureSetup.noTexture();
    }
}
