/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  com.google.common.util.concurrent.ThreadFactoryBuilder
 *  com.mojang.authlib.GameProfile
 *  io.netty.bootstrap.Bootstrap
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelFutureListener
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.ChannelOption
 *  io.netty.channel.DefaultEventLoopGroup
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.SimpleChannelInboundHandler
 *  io.netty.channel.epoll.Epoll
 *  io.netty.channel.epoll.EpollEventLoopGroup
 *  io.netty.channel.epoll.EpollSocketChannel
 *  io.netty.channel.local.LocalChannel
 *  io.netty.channel.local.LocalServerChannel
 *  io.netty.channel.nio.NioEventLoopGroup
 *  io.netty.channel.socket.nio.NioSocketChannel
 *  io.netty.handler.proxy.Socks4ProxyHandler
 *  io.netty.handler.proxy.Socks5ProxyHandler
 *  io.netty.handler.timeout.ReadTimeoutHandler
 *  io.netty.handler.timeout.TimeoutException
 *  io.netty.util.AttributeKey
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  javax.annotation.Nullable
 *  lombok.Generated
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 */
package lightning.product;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Queue;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import lightning.product.A_4115_X;
import lightning.product.F_2904_S;
import lightning.product.CipherEncoder;
import lightning.product.I_3005_G;
import lightning.product.CompressionEncoder;
import lightning.product.P_4689_s;
import lightning.product.Y_2080_q;
import lightning.product.a_3942_s;
import lightning.product.Varint21FrameDecoder;
import lightning.product.c_3619_h;
import lightning.product.d_4952_K;
import lightning.product.l_52_h;
import lightning.product.o_1314_v;
import lightning.product.o_1758_S;
import lightning.product.r_641_z;
import lightning.product.RunningOnDifferentThreadException;
import lightning.product.s_4922_C;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.w_690_m;
import lightning.product.x_282_a;
import lightning.product.particlesParticleOptions;
import lightning.product.CipherDecoder;
import lombok.Generated;
import mods.proxy.Proxy;
import mods.proxy.ProxyServer;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class t_1786_h
extends SimpleChannelInboundHandler<Packet<?>> {
    private static final Logger v_4262_N = LogManager.getLogger();
    public static final Marker n_1700_B = MarkerManager.getMarker((String)"NETWORK");
    public static final Marker J_1907_R = MarkerManager.getMarker((String)"NETWORK_PACKETS", (Marker)n_1700_B);
    public static final AttributeKey<d_4952_K> R_4764_Y = AttributeKey.valueOf((String)"protocol");
    public static final l_52_h<NioEventLoopGroup> G_564_y = new l_52_h<Object>(() -> new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Client IO #%d").setDaemon(true).build()));
    public static final l_52_h<EpollEventLoopGroup> P_1922_E = new l_52_h<Object>(() -> new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build()));
    public static final l_52_h<DefaultEventLoopGroup> u_1723_Y = new l_52_h<Object>(() -> new DefaultEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Client IO #%d").setDaemon(true).build()));
    private final a_3942_s w_1484_f;
    private final Queue<n_1700_B> t_148_a = Queues.newConcurrentLinkedQueue();
    private Channel s_956_w;
    private SocketAddress u_2550_I;
    private particlesParticleOptions M_588_G;
    private x_282_a P_4830_p;
    private boolean h_1847_R;
    private boolean Q_4569_t;
    private int M_182_A;
    private int t_1786_h;
    private float multiplayerClientSuggestionProvider;
    private float w_1457_N;
    private int Y_601_j;
    private boolean Y_259_p;
    private GameProfile Q_2552_b;

    public t_1786_h(a_3942_s packetDirection) {
        this.w_1484_f = packetDirection;
    }

    @Nullable
    public GameProfile n_1700_B() {
        return this.Q_2552_b;
    }

    public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
        super.channelActive(p_channelActive_1_);
        this.s_956_w = p_channelActive_1_.channel();
        this.u_2550_I = this.s_956_w.remoteAddress();
        try {
            this.n_1700_B(d_4952_K.n_1700_B);
        }
        catch (Throwable var3) {
            v_4262_N.fatal((Object)var3);
        }
    }

    public void n_1700_B(d_4952_K newState) {
        this.s_956_w.attr(R_4764_Y).set((Object)newState);
        this.s_956_w.config().setAutoRead(true);
        v_4262_N.debug("Enabled auto read");
    }

    public void channelInactive(ChannelHandlerContext p_channelInactive_1_) throws Exception {
        this.n_1700_B(new F_2904_S("disconnect.endOfStream"));
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof P_4689_s) {
            v_4262_N.debug("Skipping packet due to errors", cause.getCause());
            return;
        }
        if (cause instanceof TimeoutException) {
            v_4262_N.debug("Connection timeout", cause);
            this.n_1700_B(new F_2904_S("disconnect.timeout"));
            return;
        }
        if (cause instanceof IOException) {
            v_4262_N.debug("IO error", cause);
            this.n_1700_B(new F_2904_S("disconnect.ioError"));
            return;
        }
        F_2904_S reason = new F_2904_S("disconnect.genericReason", "Internal error: " + cause.getMessage());
        if (!this.Y_259_p) {
            this.Y_259_p = true;
            v_4262_N.debug("Error sending packet", cause);
            this.n_1700_B(new w_690_m(reason), (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)future -> this.n_1700_B(reason)));
            this.u_2550_I();
        } else {
            v_4262_N.debug("Repeated error", cause);
            this.n_1700_B(reason);
        }
    }

    protected void n_1700_B(ChannelHandlerContext p_channelRead0_1_, Packet<?> p_channelRead0_2_) throws Exception {
        if (this.s_956_w.isOpen()) {
            try {
                Y_2080_q packetEvent = new Y_2080_q(p_channelRead0_2_, Y_2080_q.n_1700_B.n_1700_B);
                A_4115_X.n_1700_B(packetEvent);
                if (packetEvent.n_1700_B()) {
                    return;
                }
                lightning.product.t_1786_h.n_1700_B(packetEvent.P_1922_E(), this.M_588_G);
            }
            catch (RunningOnDifferentThreadException r_915_t2) {
                // empty catch block
            }
            ++this.M_182_A;
        }
    }

    private static <T extends particlesParticleOptions> void n_1700_B(Packet<T> p_197664_0_, particlesParticleOptions p_197664_1_) {
        p_197664_0_.n_1700_B(p_197664_1_);
    }

    public void n_1700_B(particlesParticleOptions handler) {
        Validate.notNull((Object)handler, (String)"packetListener", (Object[])new Object[0]);
        this.M_588_G = handler;
    }

    public void n_1700_B(Packet<?> packetIn) {
        this.n_1700_B(packetIn, (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)null));
    }

    public void n_1700_B(Packet<?> packetIn, @Nullable GenericFutureListener<? extends Future<? super Void>> p_201058_2_) {
        Y_2080_q packetEvent = new Y_2080_q(packetIn, Y_2080_q.n_1700_B.J_1907_R);
        A_4115_X.n_1700_B(packetEvent);
        if (!packetEvent.n_1700_B()) {
            if (this.v_4262_N()) {
                this.Q_4569_t();
                this.J_1907_R(packetIn, p_201058_2_);
            } else {
                this.t_148_a.add(new n_1700_B(packetIn, p_201058_2_));
            }
        }
    }

    private void J_1907_R(Packet<?> inPacket, @Nullable GenericFutureListener<? extends Future<? super Void>> futureListeners) {
        d_4952_K protocoltype = d_4952_K.n_1700_B(inPacket);
        d_4952_K protocoltype1 = (d_4952_K)((Object)this.s_956_w.attr(R_4764_Y).get());
        ++this.t_1786_h;
        if (protocoltype1 != protocoltype) {
            v_4262_N.debug("Disabled auto read");
            this.s_956_w.config().setAutoRead(false);
        }
        if (this.s_956_w.eventLoop().inEventLoop()) {
            if (protocoltype != protocoltype1) {
                this.n_1700_B(protocoltype);
            }
            ChannelFuture channelfuture = this.s_956_w.writeAndFlush(inPacket);
            if (futureListeners != null) {
                channelfuture.addListener(futureListeners);
            }
            channelfuture.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        } else {
            this.s_956_w.eventLoop().execute(() -> {
                if (protocoltype != protocoltype1) {
                    this.n_1700_B(protocoltype);
                }
                ChannelFuture channelfuture1 = this.s_956_w.writeAndFlush((Object)inPacket);
                if (futureListeners != null) {
                    channelfuture1.addListener(futureListeners);
                }
                channelfuture1.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            });
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void Q_4569_t() {
        if (this.s_956_w != null && this.s_956_w.isOpen()) {
            Queue<n_1700_B> queue = this.t_148_a;
            synchronized (queue) {
                n_1700_B networkmanager$queuedpacket;
                while ((networkmanager$queuedpacket = this.t_148_a.poll()) != null) {
                    this.J_1907_R(networkmanager$queuedpacket.n_1700_B, networkmanager$queuedpacket.J_1907_R);
                }
            }
        }
    }

    public void J_1907_R() {
        this.Q_4569_t();
        if (this.M_588_G instanceof o_1314_v) {
            ((o_1314_v)this.M_588_G).n_1700_B();
        }
        if (this.M_588_G instanceof s_4922_C) {
            ((s_4922_C)this.M_588_G).n_1700_B();
        }
        if (this.s_956_w != null) {
            this.s_956_w.flush();
        }
        if (this.Y_601_j++ % 20 == 0) {
            this.R_4764_Y();
        }
    }

    protected void R_4764_Y() {
        this.w_1457_N = u_530_F.v_4262_N(0.75f, this.t_1786_h, this.w_1457_N);
        this.multiplayerClientSuggestionProvider = u_530_F.v_4262_N(0.75f, this.M_182_A, this.multiplayerClientSuggestionProvider);
        this.t_1786_h = 0;
        this.M_182_A = 0;
    }

    public SocketAddress G_564_y() {
        return this.u_2550_I;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(x_282_a message) {
        if (this.s_956_w != null && this.s_956_w.isOpen()) {
            try {
                this.P_4830_p = message;
                this.s_956_w.close().syncUninterruptibly();
                if (this.M_588_G != null) {
                    this.M_588_G.onDisconnect(message);
                }
                Queue<n_1700_B> queue = this.t_148_a;
                synchronized (queue) {
                    this.t_148_a.clear();
                }
            }
            catch (Exception e) {
                v_4262_N.error("Error closing channel", (Throwable)e);
            }
        }
    }

    public boolean P_1922_E() {
        return this.s_956_w instanceof LocalChannel || this.s_956_w instanceof LocalServerChannel;
    }

    public static t_1786_h n_1700_B(InetAddress address, int serverPort, boolean useNativeTransport) {
        EventLoopGroup eventLoopGroup;
        Class<NioSocketChannel> channelClass;
        final t_1786_h networkmanager = new t_1786_h(a_3942_s.J_1907_R);
        if (Epoll.isAvailable() && useNativeTransport) {
            channelClass = EpollSocketChannel.class;
            eventLoopGroup = (EventLoopGroup)P_1922_E.n_1700_B();
        } else {
            channelClass = NioSocketChannel.class;
            eventLoopGroup = (EventLoopGroup)G_564_y.n_1700_B();
        }
        ((Bootstrap)((Bootstrap)((Bootstrap)new Bootstrap().group(eventLoopGroup)).channel(channelClass)).handler((ChannelHandler)new ChannelInitializer<Channel>(){

            protected void initChannel(Channel ch) throws Exception {
                ch.config().setOption(ChannelOption.TCP_NODELAY, (Object)true);
                ch.config().setOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, (Object)5000);
                if (ProxyServer.proxyEnabled && ProxyServer.proxy != null && ProxyServer.proxy.ipPort != null && !ProxyServer.proxy.ipPort.isEmpty() && !"none".equalsIgnoreCase(ProxyServer.proxy.ipPort)) {
                    try {
                        Proxy p = ProxyServer.proxy;
                        InetSocketAddress proxyAddr = new InetSocketAddress(p.getIp(), p.getPort());
                        if (p.type == Proxy.ProxyType.SOCKS5) {
                            boolean noAuth;
                            boolean bl = noAuth = !(p.username != null && !p.username.isEmpty() || p.password != null && !p.password.isEmpty());
                            if (noAuth) {
                                ch.pipeline().addFirst("socks_proxy", (ChannelHandler)new Socks5ProxyHandler((SocketAddress)proxyAddr));
                            } else {
                                ch.pipeline().addFirst("socks_proxy", (ChannelHandler)new Socks5ProxyHandler((SocketAddress)proxyAddr, p.username, p.password));
                            }
                        } else if (p.username == null || p.username.isEmpty()) {
                            ch.pipeline().addFirst("socks_proxy", (ChannelHandler)new Socks4ProxyHandler((SocketAddress)proxyAddr));
                        } else {
                            ch.pipeline().addFirst("socks_proxy", (ChannelHandler)new Socks4ProxyHandler((SocketAddress)proxyAddr, p.username));
                        }
                        ProxyServer.lastUsedProxy = p;
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                ch.pipeline().addLast("timeout", (ChannelHandler)new ReadTimeoutHandler(30)).addLast("splitter", (ChannelHandler)new Varint21FrameDecoder()).addLast("decoder", (ChannelHandler)new o_1758_S(a_3942_s.J_1907_R)).addLast("prepender", (ChannelHandler)new r_641_z()).addLast("encoder", (ChannelHandler)new I_3005_G(a_3942_s.n_1700_B)).addLast("packet_handler", (ChannelHandler)networkmanager);
            }
        })).connect(address, serverPort).syncUninterruptibly();
        return networkmanager;
    }

    public static t_1786_h n_1700_B(SocketAddress address) {
        final t_1786_h networkmanager = new t_1786_h(a_3942_s.J_1907_R);
        ((Bootstrap)((Bootstrap)((Bootstrap)new Bootstrap().group((EventLoopGroup)u_1723_Y.n_1700_B())).handler((ChannelHandler)new ChannelInitializer<Channel>(){

            protected void initChannel(Channel p_initChannel_1_) throws Exception {
                p_initChannel_1_.pipeline().addLast("packet_handler", (ChannelHandler)networkmanager);
            }
        })).channel(LocalChannel.class)).connect(address).syncUninterruptibly();
        return networkmanager;
    }

    public void n_1700_B(Cipher p_244777_1_, Cipher p_244777_2_) {
        this.h_1847_R = true;
        this.s_956_w.pipeline().addBefore("splitter", "decrypt", (ChannelHandler)new CipherDecoder(p_244777_1_));
        this.s_956_w.pipeline().addBefore("prepender", "encrypt", (ChannelHandler)new CipherEncoder(p_244777_2_));
    }

    public boolean u_1723_Y() {
        return this.h_1847_R;
    }

    public boolean v_4262_N() {
        return this.s_956_w != null && this.s_956_w.isOpen();
    }

    public boolean w_1484_f() {
        return this.s_956_w == null;
    }

    public particlesParticleOptions t_148_a() {
        return this.M_588_G;
    }

    @Nullable
    public x_282_a s_956_w() {
        return this.P_4830_p;
    }

    public void u_2550_I() {
        this.s_956_w.config().setAutoRead(false);
    }

    public void n_1700_B(int threshold) {
        if (threshold >= 0) {
            if (this.s_956_w.pipeline().get("decompress") instanceof c_3619_h) {
                ((c_3619_h)this.s_956_w.pipeline().get("decompress")).n_1700_B(threshold);
            } else {
                this.s_956_w.pipeline().addBefore("decoder", "decompress", (ChannelHandler)new c_3619_h(threshold));
            }
            if (this.s_956_w.pipeline().get("compress") instanceof CompressionEncoder) {
                ((CompressionEncoder)this.s_956_w.pipeline().get("compress")).n_1700_B(threshold);
            } else {
                this.s_956_w.pipeline().addBefore("encoder", "compress", (ChannelHandler)new CompressionEncoder(threshold));
            }
        } else {
            if (this.s_956_w.pipeline().get("decompress") instanceof c_3619_h) {
                this.s_956_w.pipeline().remove("decompress");
            }
            if (this.s_956_w.pipeline().get("compress") instanceof CompressionEncoder) {
                this.s_956_w.pipeline().remove("compress");
            }
        }
    }

    public void M_588_G() {
        if (this.s_956_w != null && !this.s_956_w.isOpen() && !this.Q_4569_t) {
            this.Q_4569_t = true;
            if (this.s_956_w() != null) {
                this.t_148_a().onDisconnect(this.s_956_w());
            } else if (this.t_148_a() != null) {
                this.t_148_a().onDisconnect(new F_2904_S("multiplayer.disconnect.generic"));
            }
        }
    }

    public float P_4830_p() {
        return this.multiplayerClientSuggestionProvider;
    }

    public float h_1847_R() {
        return this.w_1457_N;
    }

    @Generated
    public void n_1700_B(GameProfile loginGameProfile) {
        this.Q_2552_b = loginGameProfile;
    }

    protected /* synthetic */ void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
        this.n_1700_B(channelHandlerContext, (Packet)object);
    }

    static class n_1700_B {
        private final Packet<?> n_1700_B;
        @Nullable
        private final GenericFutureListener<? extends Future<? super Void>> J_1907_R;

        public n_1700_B(Packet<?> p_i48604_1_, @Nullable GenericFutureListener<? extends Future<? super Void>> p_i48604_2_) {
            this.n_1700_B = p_i48604_1_;
            this.J_1907_R = p_i48604_2_;
        }
    }
}


