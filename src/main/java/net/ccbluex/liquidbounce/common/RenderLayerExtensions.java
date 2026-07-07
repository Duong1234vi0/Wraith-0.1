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

package net.ccbluex.liquidbounce.common;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

/**
 * Extensions to RenderType for custom render layers.
 */
public class RenderLayerExtensions {

    /**
     * Render Layer for smoother textures using bilinear filtering.
     */
    private static final Function<Identifier, RenderType> SMOOTH_TEXTURE_LAYER = Util.memoize(
            textureId ->
                    RenderType.create(
                            "smooth_textured",
                            RenderSetup.builder(RenderPipelines.GUI_TEXTURED)
                                    .withTexture("Sampler0", textureId, () -> RenderSystem.getSamplerCache().getRepeat(FilterMode.LINEAR))
                                    .bufferSize(RenderType.SMALL_BUFFER_SIZE)
                                    .createRenderSetup()
                    ));

    /**
     * Render Layer for elements that need to be rendered to the blur framebuffer.
     */
    private static final Function<Identifier, RenderType> BLURRED_TEXTURE_LAYER = Util.memoize(
            textureId ->
                    RenderType.create(
                            "blurred_ui_layer",
                            RenderSetup.builder(RenderPipelines.GUI_TEXTURED_PREMULTIPLIED_ALPHA)
                                    .withTexture("Sampler0", textureId)
                                    .setOutputTarget(OutputTarget.OUTLINE_TARGET)
                                    .bufferSize(RenderType.SMALL_BUFFER_SIZE)
                                    .createRenderSetup()
                    ));

    /**
     * Render Layer for BGRA textures.
     */
    private static final Function<Identifier, RenderType> BGRA_TEXTURE_LAYER = Util.memoize(
            textureId ->
                    RenderType.create(
                            "bgra_texture_layer",
                            RenderSetup.builder(RenderPipelines.GUI_TEXTURED_PREMULTIPLIED_ALPHA)
                                    .withTexture("Sampler0", textureId)
                                    .bufferSize(RenderType.SMALL_BUFFER_SIZE)
                                    .createRenderSetup()
                    ));

    /**
     * Render Layer for BGRA textures that also need blur effect.
     */
    private static final Function<Identifier, RenderType> BGRA_BLURRED_TEXTURE_LAYER = Util.memoize(
            textureId ->
                    RenderType.create(
                            "bgra_blurred_texture_layer",
                            RenderSetup.builder(RenderPipelines.GUI_TEXTURED_PREMULTIPLIED_ALPHA)
                                    .withTexture("Sampler0", textureId)
                                    .setOutputTarget(OutputTarget.OUTLINE_TARGET)
                                    .bufferSize(RenderType.SMALL_BUFFER_SIZE)
                                    .createRenderSetup()
                    ));

    public static RenderType getSmoothTextureLayer(Identifier textureId) {
        return SMOOTH_TEXTURE_LAYER.apply(textureId);
    }

    public static RenderType getBlurredTextureLayer(Identifier textureId) {
        return BLURRED_TEXTURE_LAYER.apply(textureId);
    }

    public static RenderType getBgraTextureLayer(Identifier textureId) {
        return BGRA_TEXTURE_LAYER.apply(textureId);
    }

    public static RenderType getBgraBlurredTextureLayer(Identifier textureId) {
        return BGRA_BLURRED_TEXTURE_LAYER.apply(textureId);
    }

}
