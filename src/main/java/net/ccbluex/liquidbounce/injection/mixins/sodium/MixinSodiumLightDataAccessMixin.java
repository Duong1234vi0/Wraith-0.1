package net.ccbluex.liquidbounce.injection.mixins.sodium;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = "net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess", remap = false)
public class MixinSodiumLightDataAccessMixin {
}
