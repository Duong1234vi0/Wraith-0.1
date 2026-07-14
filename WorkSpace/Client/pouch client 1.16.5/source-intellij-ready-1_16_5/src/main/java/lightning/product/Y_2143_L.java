/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.bootstrap.Bootstrap
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelFutureListener
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.nio.NioEventLoopGroup
 *  io.netty.channel.socket.SocketChannel
 *  io.netty.channel.socket.nio.NioSocketChannel
 *  io.netty.handler.codec.string.StringEncoder
 *  io.netty.util.concurrent.GenericFutureListener
 */
package lightning.product;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GenericFutureListener;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class Y_2143_L {
    private EventLoopGroup n_1700_B;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(String host, int port, Supplier<String> msgSupplier) {
        this.n_1700_B = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            ((Bootstrap)((Bootstrap)bootstrap.group(this.n_1700_B)).channel(NioSocketChannel.class)).handler((ChannelHandler)new ChannelInitializer<SocketChannel>(this){

                protected void n_1700_B(SocketChannel ch) {
                    ch.pipeline().addLast(new ChannelHandler[]{new StringEncoder(StandardCharsets.UTF_8)});
                }

                protected /* synthetic */ void initChannel(Channel channel) throws Exception {
                    this.n_1700_B((SocketChannel)channel);
                }
            });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().writeAndFlush((Object)msgSupplier.get()).addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
            future.channel().closeFuture().sync();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if (this.n_1700_B != null) {
                this.n_1700_B.shutdownGracefully();
            }
        }
    }
}

