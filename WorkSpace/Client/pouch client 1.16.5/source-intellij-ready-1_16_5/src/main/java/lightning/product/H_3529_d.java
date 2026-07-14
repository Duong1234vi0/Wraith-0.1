/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.bootstrap.ServerBootstrap
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.ChannelOption
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.SimpleChannelInboundHandler
 *  io.netty.channel.nio.NioEventLoopGroup
 *  io.netty.channel.socket.SocketChannel
 *  io.netty.channel.socket.nio.NioServerSocketChannel
 *  io.netty.handler.codec.string.StringDecoder
 */
package lightning.product;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import java.net.BindException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class H_3529_d {
    private EventLoopGroup n_1700_B;
    private EventLoopGroup J_1907_R;
    private ChannelFuture R_4764_Y;
    private Consumer<String> G_564_y;

    public boolean n_1700_B(int port, Consumer<String> listener) {
        this.G_564_y = listener;
        this.n_1700_B = new NioEventLoopGroup(1);
        this.J_1907_R = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            ((ServerBootstrap)((ServerBootstrap)b.group(this.n_1700_B, this.J_1907_R).channel(NioServerSocketChannel.class)).option(ChannelOption.SO_REUSEADDR, (Object)true)).childHandler((ChannelHandler)new ChannelInitializer<SocketChannel>(){

                protected void n_1700_B(SocketChannel ch) {
                    ch.pipeline().addLast(new ChannelHandler[]{new StringDecoder(StandardCharsets.UTF_8), new SimpleChannelInboundHandler<String>(){

                        protected void n_1700_B(ChannelHandlerContext ctx, String msg) {
                            H_3529_d.this.G_564_y.accept(msg);
                            ctx.close();
                        }

                        protected /* synthetic */ void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
                            this.n_1700_B(channelHandlerContext, (String)object);
                        }
                    }});
                }

                protected /* synthetic */ void initChannel(Channel channel) throws Exception {
                    this.n_1700_B((SocketChannel)channel);
                }
            });
            this.R_4764_Y = b.bind(port).sync();
            return true;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof BindException) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public void n_1700_B() {
        if (this.R_4764_Y != null) {
            this.R_4764_Y.channel().close();
        }
        if (this.n_1700_B != null) {
            this.n_1700_B.shutdownGracefully();
        }
        if (this.J_1907_R != null) {
            this.J_1907_R.shutdownGracefully();
        }
    }
}

