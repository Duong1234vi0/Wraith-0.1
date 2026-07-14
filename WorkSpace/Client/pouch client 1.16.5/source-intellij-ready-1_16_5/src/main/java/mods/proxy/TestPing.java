/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.ThreadFactoryBuilder
 *  io.netty.bootstrap.Bootstrap
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelException
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.ChannelOption
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.socket.nio.NioSocketChannel
 *  io.netty.handler.proxy.Socks4ProxyHandler
 *  io.netty.handler.proxy.Socks5ProxyHandler
 *  io.netty.handler.timeout.ReadTimeoutHandler
 */
package mods.proxy;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.I_3005_G;
import lightning.product.ClientIntentionPacket;
import lightning.product.K_4053_T;
import lightning.product.M_2450_l;
import lightning.product.ServerboundStatusRequestPacket;
import lightning.product.ClientboundPongResponsePacket;
import lightning.product.a_3942_s;
import lightning.product.Varint21FrameDecoder;
import lightning.product.c_1633_k;
import lightning.product.d_4952_K;
import lightning.product.j_3341_s;
import lightning.product.o_1758_S;
import lightning.product.ClientboundStatusResponsePacket;
import lightning.product.r_641_z;
import lightning.product.t_1786_h;
import lightning.product.x_282_a;
import mods.proxy.Proxy;

public class TestPing {
    public String state = "";
    private long pingSentAt;
    private c_1633_k pingDestination = null;
    private Proxy proxy;
    private Listener listener;
    private static final ThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build());

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void run(String ip, int port, Proxy proxy) {
        this.proxy = proxy;
        EXECUTOR.submit(() -> this.ping(ip, port));
    }

    private void ping(final String ip, int port) {
        c_1633_k clientConnection;
        this.state = "Pinging " + ip + "...";
        try {
            clientConnection = this.createTestClientConnection(InetAddress.getByName(ip), port);
        }
        catch (UnknownHostException e) {
            this.state = String.valueOf((Object)D_4024_W.P_4830_p) + "Can't connect to proxy";
            return;
        }
        catch (Exception e) {
            this.state = String.valueOf((Object)D_4024_W.P_4830_p) + "Can't ping " + ip;
            return;
        }
        this.pingDestination = clientConnection;
        clientConnection.n_1700_B(new M_2450_l(){
            private boolean successful;

            @Override
            public void handleServerInfo(ClientboundStatusResponsePacket packet) {
                TestPing.this.pingSentAt = j_3341_s.J_1907_R();
                clientConnection.n_1700_B(new K_4053_T(TestPing.this.pingSentAt));
            }

            @Override
            public void handlePong(ClientboundPongResponsePacket packet) {
                this.successful = true;
                TestPing.this.pingDestination = null;
                long pingToServer = j_3341_s.J_1907_R() - TestPing.this.pingSentAt;
                TestPing.this.state = "Ping: " + pingToServer;
                if (TestPing.this.listener != null) {
                    try {
                        TestPing.this.listener.onSuccess(TestPing.this.proxy, pingToServer);
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                clientConnection.n_1700_B(new F_2904_S("multiplayer.status.finished"));
            }

            @Override
            public void onDisconnect(x_282_a reason) {
                TestPing.this.pingDestination = null;
                if (!this.successful) {
                    TestPing.this.state = String.valueOf((Object)D_4024_W.P_4830_p) + "Can't ping " + ip + ": " + reason.getString();
                }
            }

            @Override
            public c_1633_k getNetworkManager() {
                return clientConnection;
            }

            @Override
            public t_1786_h getBotNetwork() {
                return null;
            }
        });
        try {
            clientConnection.n_1700_B(new ClientIntentionPacket(ip, port, d_4952_K.R_4764_Y));
            clientConnection.n_1700_B(new ServerboundStatusRequestPacket());
        }
        catch (Throwable throwable) {
            this.state = String.valueOf((Object)D_4024_W.P_4830_p) + "Can't ping " + ip;
        }
    }

    private c_1633_k createTestClientConnection(InetAddress address, int port) {
        final c_1633_k clientConnection = new c_1633_k(a_3942_s.J_1907_R);
        ((Bootstrap)((Bootstrap)((Bootstrap)new Bootstrap().group((EventLoopGroup)c_1633_k.G_564_y.n_1700_B())).handler((ChannelHandler)new ChannelInitializer<Channel>(){

            protected void initChannel(Channel channel) {
                try {
                    channel.config().setOption(ChannelOption.TCP_NODELAY, (Object)true);
                }
                catch (ChannelException channelException) {
                    // empty catch block
                }
                channel.pipeline().addLast("timeout", (ChannelHandler)new ReadTimeoutHandler(30)).addLast("splitter", (ChannelHandler)new Varint21FrameDecoder()).addLast("decoder", (ChannelHandler)new o_1758_S(a_3942_s.J_1907_R)).addLast("prepender", (ChannelHandler)new r_641_z()).addLast("encoder", (ChannelHandler)new I_3005_G(a_3942_s.n_1700_B)).addLast("packet_handler", (ChannelHandler)clientConnection);
                if (TestPing.this.proxy.type == Proxy.ProxyType.SOCKS5) {
                    channel.pipeline().addFirst(new ChannelHandler[]{new Socks5ProxyHandler((SocketAddress)new InetSocketAddress(TestPing.this.proxy.getIp(), TestPing.this.proxy.getPort()), TestPing.this.proxy.username.isEmpty() ? null : TestPing.this.proxy.username, TestPing.this.proxy.password.isEmpty() ? null : TestPing.this.proxy.password)});
                } else {
                    channel.pipeline().addFirst(new ChannelHandler[]{new Socks4ProxyHandler((SocketAddress)new InetSocketAddress(TestPing.this.proxy.getIp(), TestPing.this.proxy.getPort()), TestPing.this.proxy.username.isEmpty() ? null : TestPing.this.proxy.username)});
                }
            }
        })).channel(NioSocketChannel.class)).connect(address, port).syncUninterruptibly();
        return clientConnection;
    }

    public void pingPendingNetworks() {
        if (this.pingDestination != null) {
            if (this.pingDestination.u_1723_Y()) {
                this.pingDestination.n_1700_B();
            } else {
                this.pingDestination.u_2550_I();
            }
        }
    }

    public static interface Listener {
        public void onSuccess(Proxy var1, long var2);
    }
}


