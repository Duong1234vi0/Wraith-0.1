package sky.core.utils.net;

import io.netty.bootstrap.ServerBootstrap;
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

public class ServerHandler {
   private EventLoopGroup bossGroup;
   private EventLoopGroup workerGroup;
   private ChannelFuture channelFuture;
   private Consumer messageListener;

   public boolean start(int port, Consumer listener) {
      this.messageListener = listener;
      this.bossGroup = new NioEventLoopGroup(1);
      this.workerGroup = new NioEventLoopGroup();

      try {
         ServerBootstrap b = new ServerBootstrap();
         ((ServerBootstrap)((ServerBootstrap)b.group(this.bossGroup, this.workerGroup).channel(NioServerSocketChannel.class)).option(ChannelOption.SO_REUSEADDR, true)).childHandler(new ChannelInitializer() {
            protected void initChannel(SocketChannel ch) {
               ch.pipeline().addLast(new ChannelHandler[]{new StringDecoder(StandardCharsets.UTF_8), new SimpleChannelInboundHandler() {
                  protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                     ServerHandler.this.messageListener.accept(msg);
                     ctx.close();
                  }
               }});
            }
         });
         this.channelFuture = b.bind(port).sync();
         return true;
      } catch (InterruptedException var5) {
         var5.printStackTrace();
         return false;
      } catch (Exception var6) {
         Throwable cause = var6.getCause();
         if (cause instanceof BindException) {
            return false;
         } else {
            var6.printStackTrace();
            return false;
         }
      }
   }

   public void shutdown() {
      if (this.channelFuture != null) {
         this.channelFuture.channel().close();
      }

      if (this.bossGroup != null) {
         this.bossGroup.shutdownGracefully();
      }

      if (this.workerGroup != null) {
         this.workerGroup.shutdownGracefully();
      }

   }
}
