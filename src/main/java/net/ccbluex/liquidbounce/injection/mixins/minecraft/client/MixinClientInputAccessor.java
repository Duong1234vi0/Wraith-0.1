package net.ccbluex.liquidbounce.injection.mixins.minecraft.client;

import net.minecraft.client.player.ClientInput;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientInput.class)
public interface MixinClientInputAccessor {

    @Accessor("moveVector")
    void liquid_bounce$setMoveVector(Vec2 moveVector);

}
