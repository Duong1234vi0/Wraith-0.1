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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.ccbluex.liquidbounce.render.gui.GlowRectShader;
import net.ccbluex.liquidbounce.render.gui.RoundedRectShader;
import net.ccbluex.liquidbounce.render.gui.RoundedTextureShader;
import net.minecraft.client.renderer.ShaderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShaderManager.class)
public abstract class MixinShaderManager {

    /**
     * Vanilla's {@code apply(Configs, ResourceManager, ProfilerFiller)} runs
     * after every resource reload. Precompile our custom GUI rounded-rect
     * pipeline here so the first HUD/render UI draw doesn't stall the render
     * thread on shader compilation.
     */
    @Inject(
        method = "apply(Lnet/minecraft/client/renderer/ShaderManager$Configs;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
        at = @At("TAIL")
    )
    private void liquidbounce$reloadClientPipelines(CallbackInfo info) {
        RoundedRectShader.INSTANCE.precompile();
        GlowRectShader.INSTANCE.precompile();
        RoundedTextureShader.INSTANCE.precompile();
        // #Edit By Codex 2026-06-22: custom main-menu pipelines were removed with the custom main screen.
    }
}
