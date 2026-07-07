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

package net.ccbluex.liquidbounce.injection.mixins.minecraft.network;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.ccbluex.liquidbounce.features.misc.HideAppearance;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleSkinChanger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.player.PlayerSkin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerInfo.class)
public abstract class MixinPlayerListEntry {

    @ModifyReturnValue(method = "getSkin", at = @At("RETURN"))
    @SuppressWarnings({"ConstantConditions", "EqualsBetweenInconvertibleTypes", "RedundantCast"})
    private PlayerSkin liquid_bounce$skin(PlayerSkin original) {
        if (HideAppearance.INSTANCE.isDestructed()) {
            return original;
        }

        if (ModuleSkinChanger.INSTANCE.getRunning()) {
            var player = Minecraft.getInstance().player;
            if (player != null) {
                var playerListEntry = Minecraft.getInstance().getConnection() != null
                        ? Minecraft.getInstance().getConnection().getPlayerInfo(player.getUUID())
                        : null;
                if (playerListEntry != null && playerListEntry.equals((PlayerInfo) (Object) this)) {
                    var customSkinTextures = ModuleSkinChanger.INSTANCE.getSkinTextures();
                    if (customSkinTextures != null) {
                        original = customSkinTextures.get();
                    }
                }
            }
        }

        return original;
    }

}
