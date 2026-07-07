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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.client;

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleTotemParticles;
import net.minecraft.client.Camera;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SingleQuadParticle.class)
public abstract class MixinSingleQuadParticle {

    @Shadow
    protected abstract void setAlpha(float alpha);

    @Redirect(
            method = "setSpriteFromAge",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/particle/SpriteSet;get(II)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"
            )
    )
    private TextureAtlasSprite hideVanillaTotemParticles(SpriteSet sprites, int age, int lifetime) {
        if ((Object) this instanceof TotemParticle && ModuleTotemParticles.shouldHideVanillaParticles()) {
            this.setAlpha(0.0F);
        }

        return sprites.get(age, lifetime);
    }

    @Inject(method = "extract", at = @At("HEAD"))
    private void hideVanillaTotemParticlesBeforeRender(
            QuadParticleRenderState particleTypeRenderState,
            Camera camera,
            float partialTickTime,
            CallbackInfo callbackInfo
    ) {
        // #Edit By Codex 2026-07-02: SimpleAnimatedParticle.tick can rewrite alpha after setSpriteFromAge.
        if ((Object) this instanceof TotemParticle && ModuleTotemParticles.shouldHideVanillaParticles()) {
            this.setAlpha(0.0F);
        }
    }
}
