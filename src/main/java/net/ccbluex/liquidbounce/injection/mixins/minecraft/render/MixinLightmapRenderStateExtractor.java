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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFullBright;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleXRay;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LightmapRenderStateExtractor.class)
public abstract class MixinLightmapRenderStateExtractor {

    /**
     * Target:
     * <pre>
     *     float brightnessOption = ((Double)this.minecraft.options.gamma().get()).floatValue();
     * </pre>
     */
    @ModifyExpressionValue(
            method = "extract",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;", ordinal = 1)
    )
    private Object injectFullBrightGamma(Object original) {
        if (ModuleFullBright.FullBrightGamma.INSTANCE.getRunning()) {
            return ModuleFullBright.FullBrightGamma.INSTANCE.getGamma();
        }

        ModuleXRay module = ModuleXRay.INSTANCE;
        if (module.getRunning() && module.getFullBright()) {
            return (double) Float.MAX_VALUE;
        }

        return original;
    }

}
