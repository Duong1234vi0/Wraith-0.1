package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MixinHandMyItemsMinecraftAccessor {

    @Accessor("blockModelResolver")
    BlockModelResolver hmi$getBlockModelResolver();
}
