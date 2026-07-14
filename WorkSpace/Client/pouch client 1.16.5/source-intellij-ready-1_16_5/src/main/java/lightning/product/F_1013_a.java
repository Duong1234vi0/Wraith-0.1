/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  io.netty.bootstrap.Bootstrap
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelException
 *  io.netty.channel.ChannelFutureListener
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.ChannelOption
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.SimpleChannelInboundHandler
 *  io.netty.channel.socket.nio.NioSocketChannel
 *  io.netty.util.concurrent.GenericFutureListener
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lightning.product.D_3097_e;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.ClientIntentionPacket;
import lightning.product.K_4053_T;
import lightning.product.ServerStatus;
import lightning.product.M_2450_l;
import lightning.product.ServerboundStatusRequestPacket;
import lightning.product.ClientboundPongResponsePacket;
import lightning.product.U_2871_b;
import lightning.product.ServerData;
import lightning.product.a_2587_Z;
import lightning.product.c_1633_k;
import lightning.product.d_4952_K;
import lightning.product.j_3341_s;
import lightning.product.l_3595_o;
import lightning.product.ClientboundStatusResponsePacket;
import lightning.product.t_1786_h;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class F_1013_a {
    private static final Splitter n_1700_B = Splitter.on((char)'\u0000').limit(6);
    private static final Logger J_1907_R = LogManager.getLogger();
    private final List<c_1633_k> R_4764_Y = Collections.synchronizedList(Lists.newArrayList());
    private static final Set<String> G_564_y = Collections.synchronizedSet(new HashSet());
    private static final D_3097_e P_1922_E = new D_3097_e();

    public static void n_1700_B(String serverIP) {
        G_564_y.add(serverIP.toLowerCase());
    }

    public static void J_1907_R(String serverIP) {
        G_564_y.remove(serverIP.toLowerCase());
    }

    public static boolean R_4764_Y(String serverIP) {
        String normalized = serverIP.toLowerCase();
        if (normalized.startsWith("127.0.0.1") || normalized.startsWith("localhost")) {
            return false;
        }
        return G_564_y.contains(normalized) || normalized.endsWith(":bedrock");
    }

    public void n_1700_B(final ServerData server, final Runnable p_147224_2_) throws UnknownHostException {
        if (F_1013_a.R_4764_Y(server.J_1907_R)) {
            this.J_1907_R(server, p_147224_2_);
            return;
        }
        l_3595_o serveraddress = l_3595_o.n_1700_B(server.J_1907_R);
        final c_1633_k networkmanager = c_1633_k.n_1700_B(InetAddress.getByName(serveraddress.n_1700_B()), serveraddress.J_1907_R(), false);
        this.R_4764_Y.add(networkmanager);
        server.G_564_y = new F_2904_S("multiplayer.status.pinging");
        server.P_1922_E = -1L;
        server.t_148_a = null;
        networkmanager.n_1700_B(new M_2450_l(){
            private boolean P_1922_E;
            private boolean u_1723_Y;
            private long v_4262_N;

            @Override
            public void handleServerInfo(ClientboundStatusResponsePacket packetIn) {
                if (this.u_1723_Y) {
                    networkmanager.n_1700_B(new F_2904_S("multiplayer.status.unrequested"));
                } else {
                    this.u_1723_Y = true;
                    ServerStatus serverstatusresponse = packetIn.J_1907_R();
                    server.G_564_y = serverstatusresponse.n_1700_B() != null ? serverstatusresponse.n_1700_B() : U_2871_b.R_4764_Y;
                    if (serverstatusresponse.R_4764_Y() != null) {
                        server.v_4262_N = new U_2871_b(serverstatusresponse.R_4764_Y().n_1700_B());
                        server.u_1723_Y = serverstatusresponse.R_4764_Y().J_1907_R();
                    } else {
                        server.v_4262_N = new F_2904_S("multiplayer.status.old");
                        server.u_1723_Y = 0;
                    }
                    if (serverstatusresponse.J_1907_R() != null) {
                        server.R_4764_Y = F_1013_a.n_1700_B(serverstatusresponse.J_1907_R().J_1907_R(), serverstatusresponse.J_1907_R().n_1700_B());
                        ArrayList list = Lists.newArrayList();
                        if (ArrayUtils.isNotEmpty((Object[])serverstatusresponse.J_1907_R().R_4764_Y())) {
                            for (GameProfile gameprofile : serverstatusresponse.J_1907_R().R_4764_Y()) {
                                list.add(new U_2871_b(gameprofile.getName()));
                            }
                            if (serverstatusresponse.J_1907_R().R_4764_Y().length < serverstatusresponse.J_1907_R().J_1907_R()) {
                                list.add(new F_2904_S("multiplayer.status.and_more", serverstatusresponse.J_1907_R().J_1907_R() - serverstatusresponse.J_1907_R().R_4764_Y().length));
                            }
                            server.t_148_a = list;
                        }
                    } else {
                        server.R_4764_Y = new F_2904_S("multiplayer.status.unknown").n_1700_B(D_4024_W.t_148_a);
                    }
                    String s = null;
                    if (serverstatusresponse.G_564_y() != null) {
                        String s1 = serverstatusresponse.G_564_y();
                        if (s1.startsWith("data:image/png;base64,")) {
                            s = s1.substring("data:image/png;base64,".length());
                        } else {
                            J_1907_R.error("Invalid server icon (unknown format)");
                        }
                    }
                    if (!Objects.equals(s, server.R_4764_Y())) {
                        server.n_1700_B(s);
                        p_147224_2_.run();
                    }
                    this.v_4262_N = j_3341_s.J_1907_R();
                    networkmanager.n_1700_B(new K_4053_T(this.v_4262_N));
                    this.P_1922_E = true;
                }
            }

            @Override
            public void handlePong(ClientboundPongResponsePacket packetIn) {
                long i = this.v_4262_N;
                long j = j_3341_s.J_1907_R();
                server.P_1922_E = j - i;
                networkmanager.n_1700_B(new F_2904_S("multiplayer.status.finished"));
            }

            @Override
            public void onDisconnect(x_282_a reason) {
                if (!this.P_1922_E) {
                    J_1907_R.error("Can't ping {}: {}", (Object)server.J_1907_R, (Object)reason.getString());
                    server.G_564_y = new F_2904_S("multiplayer.status.cannot_connect").n_1700_B(D_4024_W.P_1922_E);
                    server.R_4764_Y = U_2871_b.R_4764_Y;
                    F_1013_a.this.n_1700_B(server);
                }
            }

            @Override
            public c_1633_k getNetworkManager() {
                return networkmanager;
            }

            @Override
            public t_1786_h getBotNetwork() {
                return null;
            }
        });
        try {
            networkmanager.n_1700_B(new ClientIntentionPacket(serveraddress.n_1700_B(), serveraddress.J_1907_R(), d_4952_K.R_4764_Y));
            networkmanager.n_1700_B(new ServerboundStatusRequestPacket());
        }
        catch (Throwable throwable) {
            J_1907_R.error((Object)throwable);
        }
    }

    private void n_1700_B(final ServerData server) {
        final l_3595_o serveraddress = l_3595_o.n_1700_B(server.J_1907_R);
        ((Bootstrap)((Bootstrap)((Bootstrap)new Bootstrap().group((EventLoopGroup)c_1633_k.G_564_y.n_1700_B())).handler((ChannelHandler)new ChannelInitializer<Channel>(this){

            protected void initChannel(Channel p_initChannel_1_) throws Exception {
                try {
                    p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, (Object)true);
                }
                catch (ChannelException channelException) {
                    // empty catch block
                }
                p_initChannel_1_.pipeline().addLast(new ChannelHandler[]{new SimpleChannelInboundHandler<ByteBuf>(){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
                        super.channelActive(p_channelActive_1_);
                        ByteBuf bytebuf = Unpooled.buffer();
                        try {
                            bytebuf.writeByte(254);
                            bytebuf.writeByte(1);
                            bytebuf.writeByte(250);
                            char[] achar = "MC|PingHost".toCharArray();
                            bytebuf.writeShort(achar.length);
                            for (char c0 : achar) {
                                bytebuf.writeChar((int)c0);
                            }
                            bytebuf.writeShort(7 + 2 * serveraddress.n_1700_B().length());
                            bytebuf.writeByte(127);
                            achar = serveraddress.n_1700_B().toCharArray();
                            bytebuf.writeShort(achar.length);
                            for (char c1 : achar) {
                                bytebuf.writeChar((int)c1);
                            }
                            bytebuf.writeInt(serveraddress.J_1907_R());
                            p_channelActive_1_.channel().writeAndFlush((Object)bytebuf).addListener((GenericFutureListener)ChannelFutureListener.CLOSE_ON_FAILURE);
                        }
                        finally {
                            bytebuf.release();
                        }
                    }

                    protected void n_1700_B(ChannelHandlerContext p_channelRead0_1_, ByteBuf p_channelRead0_2_) throws Exception {
                        String s;
                        String[] astring;
                        short short1 = p_channelRead0_2_.readUnsignedByte();
                        if (short1 == 255 && "\u00a71".equals((astring = (String[])Iterables.toArray((Iterable)F_1013_a.n_1700_B.split((CharSequence)(s = new String(p_channelRead0_2_.readBytes(p_channelRead0_2_.readShort() * 2).array(), StandardCharsets.UTF_16BE))), String.class))[0])) {
                            int i = u_530_F.n_1700_B(astring[1], 0);
                            String s1 = astring[2];
                            String s2 = astring[3];
                            int j = u_530_F.n_1700_B(astring[4], -1);
                            int k = u_530_F.n_1700_B(astring[5], -1);
                            server.u_1723_Y = -1;
                            server.v_4262_N = new U_2871_b(s1);
                            server.G_564_y = new U_2871_b(s2);
                            server.R_4764_Y = F_1013_a.n_1700_B(j, k);
                        }
                        p_channelRead0_1_.close();
                    }

                    public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) throws Exception {
                        p_exceptionCaught_1_.close();
                    }

                    protected /* synthetic */ void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
                        this.n_1700_B(channelHandlerContext, (ByteBuf)object);
                    }
                }});
            }
        })).channel(NioSocketChannel.class)).connect(serveraddress.n_1700_B(), serveraddress.J_1907_R());
    }

    private static x_282_a n_1700_B(int p_239171_0_, int p_239171_1_) {
        return new U_2871_b(Integer.toString(p_239171_0_)).n_1700_B(new U_2871_b("/").n_1700_B(D_4024_W.t_148_a)).n_1700_B(Integer.toString(p_239171_1_)).n_1700_B(D_4024_W.w_1484_f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B() {
        List<c_1633_k> list = this.R_4764_Y;
        synchronized (list) {
            Iterator<c_1633_k> iterator = this.R_4764_Y.iterator();
            while (iterator.hasNext()) {
                c_1633_k networkmanager = iterator.next();
                if (networkmanager.u_1723_Y()) {
                    networkmanager.n_1700_B();
                    continue;
                }
                iterator.remove();
                networkmanager.u_2550_I();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void J_1907_R() {
        List<c_1633_k> list = this.R_4764_Y;
        synchronized (list) {
            Iterator<c_1633_k> iterator = this.R_4764_Y.iterator();
            while (iterator.hasNext()) {
                c_1633_k networkmanager = iterator.next();
                if (!networkmanager.u_1723_Y()) continue;
                iterator.remove();
                networkmanager.n_1700_B(new F_2904_S("multiplayer.status.cancelled"));
            }
        }
    }

    private void J_1907_R(ServerData server, Runnable callback) {
        server.G_564_y = new F_2904_S("multiplayer.status.pinging");
        server.P_1922_E = -1L;
        server.t_148_a = null;
        String serverIP = server.J_1907_R.toLowerCase().replace(":bedrock", "");
        l_3595_o serverAddress = l_3595_o.n_1700_B(serverIP);
        String host = serverAddress.n_1700_B();
        int port = serverAddress.J_1907_R();
        if (port == 25565) {
            port = 19132;
        }
        int bedrockPort = port;
        CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();
            try {
                a_2587_Z info = P_1922_E.J_1907_R(host, bedrockPort, 5000);
                long pingTime = System.currentTimeMillis() - startTime;
                server.G_564_y = new U_2871_b("\u00a7b[BE] \u00a7r" + info.R_4764_Y());
                server.v_4262_N = new U_2871_b("\u00a7bBedrock \u00a77" + info.P_1922_E());
                server.u_1723_Y = -1;
                server.R_4764_Y = F_1013_a.n_1700_B(info.u_1723_Y(), info.v_4262_N());
                server.P_1922_E = pingTime;
                server.w_1484_f = true;
                ArrayList playerList = Lists.newArrayList();
                playerList.add(new U_2871_b("\u00a7bBedrock Server"));
                playerList.add(new U_2871_b("\u00a77\u0420\u0435\u0436\u0438\u043c: \u00a7f" + info.s_956_w()));
                playerList.add(new U_2871_b("\u00a77\u0412\u0435\u0440\u0441\u0438\u044f: \u00a7f" + info.P_1922_E()));
                playerList.add(new U_2871_b(""));
                playerList.add(new U_2871_b("\u00a7c\u26a0 Java \u043a\u043b\u0438\u0435\u043d\u0442 \u043d\u0435 \u043c\u043e\u0436\u0435\u0442"));
                playerList.add(new U_2871_b("\u00a7c   \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u044c\u0441\u044f \u043a Bedrock"));
                server.t_148_a = playerList;
                callback.run();
            }
            catch (Exception e) {
                J_1907_R.error("Failed to ping Bedrock server {}: {}", (Object)server.J_1907_R, (Object)e.getMessage());
                server.G_564_y = new U_2871_b("\u00a7c[BE] \u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u044c\u0441\u044f");
                server.R_4764_Y = new U_2871_b("\u00a77Bedrock \u0441\u0435\u0440\u0432\u0435\u0440 \u043d\u0435\u0434\u043e\u0441\u0442\u0443\u043f\u0435\u043d");
                server.P_1922_E = -1L;
                server.w_1484_f = true;
                ArrayList errorList = Lists.newArrayList();
                errorList.add(new U_2871_b("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430: " + e.getMessage()));
                errorList.add(new U_2871_b("\u00a77\u041f\u0440\u043e\u0432\u0435\u0440\u044c\u0442\u0435 IP \u0438 \u043f\u043e\u0440\u0442"));
                server.t_148_a = errorList;
                callback.run();
            }
        });
    }
}


