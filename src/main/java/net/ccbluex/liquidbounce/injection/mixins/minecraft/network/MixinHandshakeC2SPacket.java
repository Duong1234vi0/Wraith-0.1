package net.ccbluex.liquidbounce.injection.mixins.minecraft.network;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.ccbluex.liquidbounce.features.spoofer.SpooferBungeeCord;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientIntentionPacket.class)
public class MixinHandshakeC2SPacket {

    @ModifyExpressionValue(method = "write", at = @At(value = "FIELD", target = "Lnet/minecraft/network/protocol/handshake/ClientIntentionPacket;hostName:Ljava/lang/String;", opcode = Opcodes.GETFIELD))
    private String modifyAddress(String original) {
        if (SpooferBungeeCord.INSTANCE.getRunning()) {
            return SpooferBungeeCord.INSTANCE.modifyHandshakeAddress(original);
        }

        return original;
    }

}
