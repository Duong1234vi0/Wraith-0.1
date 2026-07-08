/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  io.netty.handler.proxy.Socks5ProxyHandler
 *  net.minecraft.class_2535
 *  net.minecraft.class_2547
 *  net.minecraft.class_2596
 *  net.minecraft.class_2598
 *  net.minecraft.class_8762
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.common.proxy.Proxy;
import code.alphadlc.common.proxy.ProxyServer;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.client.stealth.ModStealth;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.proxy.Socks5ProxyHandler;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.minecraft.class_2535;
import net.minecraft.class_2547;
import net.minecraft.class_2596;
import net.minecraft.class_2598;
import net.minecraft.class_8762;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2535.class})
public class ClientConnectionMixin {
    @Unique
    private static final ThreadLocal<Boolean> alphadlc$sendingChestBypassPosition = ThreadLocal.withInitial(() -> false);

    @Inject(method={"handlePacket"}, at={@At(value="HEAD")}, cancellable=true)
    private static <T extends class_2547> void handlePacketPre(class_2596<T> packet, class_2547 listener, CallbackInfo info) {
        PacketEvent packetEvent = SafeEvents.dispatch(() -> new PacketEvent(packet, PacketEvent.Type.RECEIVE));
        if (packetEvent != null && packetEvent.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"send(Lnet/minecraft/network/packet/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void sendPre(class_2596<?> packet, CallbackInfo info) {
        if (ModStealth.shouldBlockOutgoing(packet)) {
            info.cancel();
            return;
        }
        PacketEvent packetEvent = SafeEvents.dispatch(() -> new PacketEvent(packet, PacketEvent.Type.SEND));
        if (packetEvent != null && packetEvent.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"addHandlers"}, at={@At(value="RETURN")})
    private static void addHandlersHook(ChannelPipeline pipeline, class_2598 side, boolean local, class_8762 packetSizeLogger, CallbackInfo ci) {
        Proxy proxy = ProxyServer.proxy;
        if (proxy != null && ProxyServer.proxyEnabled && side == class_2598.field_11942 && !local) {
            pipeline.addFirst(new ChannelHandler[]{new Socks5ProxyHandler((SocketAddress)new InetSocketAddress(proxy.getIp(), proxy.getPort()), proxy.username, proxy.password)});
            ProxyServer.lastUsedProxy = proxy;
        }
    }
}
