package net.ccbluex.liquidbounce.injection.mixins.minecraft.network;

import net.minecraft.network.FriendlyByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FriendlyByteBuf.class)
public abstract class MixinPacketByteBuf {

    @Shadow
    public abstract FriendlyByteBuf writeVarInt(int value);

    @Inject(
            method = "writeEnum",
            at = @At("HEAD"),
            cancellable = true
    )
    public void writeEnumConstant(Enum<?> instance, CallbackInfoReturnable<FriendlyByteBuf> cir) {
        if (instance == null) {
            cir.setReturnValue(this.writeVarInt(-1));
        }
    }

}
