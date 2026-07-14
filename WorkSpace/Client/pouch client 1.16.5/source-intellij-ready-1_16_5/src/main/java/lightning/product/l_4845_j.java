/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.util.concurrent.ThreadFactoryBuilder
 *  io.netty.bootstrap.ServerBootstrap
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelException
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.ChannelOption
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.epoll.Epoll
 *  io.netty.channel.epoll.EpollEventLoopGroup
 *  io.netty.channel.epoll.EpollServerSocketChannel
 *  io.netty.channel.local.LocalAddress
 *  io.netty.channel.local.LocalServerChannel
 *  io.netty.channel.nio.NioEventLoopGroup
 *  io.netty.channel.socket.nio.NioServerSocketChannel
 *  io.netty.handler.timeout.ReadTimeoutHandler
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.I_3005_G;
import lightning.product.RateKickingConnection;
import lightning.product.LegacyQueryHandler;
import lightning.product.U_2871_b;
import lightning.product.a_3942_s;
import lightning.product.Varint21FrameDecoder;
import lightning.product.c_1633_k;
import lightning.product.d_1999_S;
import lightning.product.j_2193_r;
import lightning.product.l_52_h;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.o_1758_S;
import lightning.product.r_641_z;
import lightning.product.w_690_m;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class l_4845_j {
    private static final Logger G_564_y = LogManager.getLogger();
    public static final l_52_h<NioEventLoopGroup> n_1700_B = new l_52_h<NioEventLoopGroup>(() -> new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build()));
    public static final l_52_h<EpollEventLoopGroup> J_1907_R = new l_52_h<EpollEventLoopGroup>(() -> new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build()));
    private final G_564_y P_1922_E;
    public volatile boolean R_4764_Y;
    private final List<ChannelFuture> u_1723_Y = Collections.synchronizedList(Lists.newArrayList());
    private final List<c_1633_k> v_4262_N = Collections.synchronizedList(Lists.newArrayList());

    public l_4845_j(G_564_y server) {
        this.P_1922_E = server;
        this.R_4764_Y = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(@Nullable InetAddress address, int port) throws IOException {
        List<ChannelFuture> list = this.u_1723_Y;
        synchronized (list) {
            l_52_h<NioEventLoopGroup> lazyvalue;
            Class<NioServerSocketChannel> oclass;
            if (Epoll.isAvailable() && this.P_1922_E.g_2268_R()) {
                oclass = EpollServerSocketChannel.class;
                lazyvalue = J_1907_R;
                G_564_y.info("Using epoll channel type");
            } else {
                oclass = NioServerSocketChannel.class;
                lazyvalue = n_1700_B;
                G_564_y.info("Using default channel type");
            }
            this.u_1723_Y.add(((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(oclass)).childHandler((ChannelHandler)new ChannelInitializer<Channel>(){

                protected void initChannel(Channel p_initChannel_1_) throws Exception {
                    try {
                        p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, (Object)true);
                    }
                    catch (ChannelException channelException) {
                        // empty catch block
                    }
                    p_initChannel_1_.pipeline().addLast("timeout", (ChannelHandler)new ReadTimeoutHandler(30)).addLast("legacy_query", (ChannelHandler)new LegacyQueryHandler(l_4845_j.this)).addLast("splitter", (ChannelHandler)new Varint21FrameDecoder()).addLast("decoder", (ChannelHandler)new o_1758_S(a_3942_s.n_1700_B)).addLast("prepender", (ChannelHandler)new r_641_z()).addLast("encoder", (ChannelHandler)new I_3005_G(a_3942_s.J_1907_R));
                    int i = l_4845_j.this.P_1922_E.X_933_l();
                    c_1633_k networkmanager = i > 0 ? new RateKickingConnection(i) : new c_1633_k(a_3942_s.n_1700_B);
                    l_4845_j.this.v_4262_N.add(networkmanager);
                    p_initChannel_1_.pipeline().addLast("packet_handler", (ChannelHandler)networkmanager);
                    networkmanager.n_1700_B(new d_1999_S(l_4845_j.this.P_1922_E, networkmanager));
                }
            }).group((EventLoopGroup)lazyvalue.n_1700_B()).localAddress(address, port)).bind().syncUninterruptibly());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SocketAddress n_1700_B() {
        ChannelFuture channelfuture;
        List<ChannelFuture> list = this.u_1723_Y;
        synchronized (list) {
            channelfuture = ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(LocalServerChannel.class)).childHandler((ChannelHandler)new ChannelInitializer<Channel>(){

                protected void initChannel(Channel p_initChannel_1_) throws Exception {
                    c_1633_k networkmanager = new c_1633_k(a_3942_s.n_1700_B);
                    networkmanager.n_1700_B(new j_2193_r(l_4845_j.this.P_1922_E, networkmanager));
                    l_4845_j.this.v_4262_N.add(networkmanager);
                    p_initChannel_1_.pipeline().addLast("packet_handler", (ChannelHandler)networkmanager);
                }
            }).group((EventLoopGroup)n_1700_B.n_1700_B()).localAddress((SocketAddress)LocalAddress.ANY)).bind().syncUninterruptibly();
            this.u_1723_Y.add(channelfuture);
        }
        return channelfuture.channel().localAddress();
    }

    public void J_1907_R() {
        this.R_4764_Y = false;
        for (ChannelFuture channelfuture : this.u_1723_Y) {
            try {
                channelfuture.channel().close().sync();
            }
            catch (InterruptedException interruptedexception) {
                G_564_y.error("Interrupted whilst closing channel");
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void R_4764_Y() {
        List<c_1633_k> list = this.v_4262_N;
        synchronized (list) {
            Iterator<c_1633_k> iterator = this.v_4262_N.iterator();
            while (iterator.hasNext()) {
                c_1633_k networkmanager = iterator.next();
                if (networkmanager.v_4262_N()) continue;
                if (networkmanager.u_1723_Y()) {
                    try {
                        networkmanager.n_1700_B();
                    }
                    catch (Exception exception) {
                        if (networkmanager.G_564_y()) {
                            throw new ReportedException(n_3236_c.n_1700_B(exception, "Ticking memory connection"));
                        }
                        G_564_y.warn("Failed to handle packet for {}", (Object)networkmanager.R_4764_Y(), (Object)exception);
                        U_2871_b itextcomponent = new U_2871_b("Internal server error");
                        networkmanager.n_1700_B(new w_690_m(itextcomponent), (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)p_210474_2_ -> networkmanager.n_1700_B(itextcomponent)));
                        networkmanager.s_956_w();
                    }
                    continue;
                }
                iterator.remove();
                networkmanager.u_2550_I();
            }
        }
    }

    public G_564_y G_564_y() {
        return this.P_1922_E;
    }
}


