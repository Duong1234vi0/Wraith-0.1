package ua.babka.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.Babka;
import ua.babka.event.EventPacket;
import ua.babka.module.Module;
import ua.babka.util.network.NetworkUtils;

@Mixin(value={ClientConnection.class})
public class MixinClientConnection {
    @Inject(method={"send(Lnet/minecraft/network/packet/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void send(Packet<?> packet, CallbackInfo ci) {
        if (NetworkUtils.getSilentPackets().contains(packet)) {
            NetworkUtils.getSilentPackets().remove(packet);
            return;
        }
        if (MixinClientConnection.hasActiveListeners()) {
            EventPacket event = new EventPacket(packet, EventPacket.Type.SEND);
            Babka.getInstance().getEventBus().post((Object)event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onReceivePacket(ChannelHandlerContext ctx, Packet<?> packet, CallbackInfo ci) {
        if (MixinClientConnection.hasActiveListeners()) {
            EventPacket event = new EventPacket(packet, EventPacket.Type.RECEIVE);
            Babka.getInstance().getEventBus().post((Object)event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    private static boolean hasActiveListeners() {
        return Babka.getInstance().getModuleStorage().getModules().stream().anyMatch(Module::isEnabled);
    }
}

