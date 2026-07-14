package sky.core.utils.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class ClientHandler {
   private EventLoopGroup group;

   public void send(String host, int port, Supplier msgSupplier) {
      this.group = new NioEventLoopGroup();

      try {
         Bootstrap bootstrap = new Bootstrap();
         ((Bootstrap)((Bootstrap)bootstrap.group(this.group)).channel(NioSocketChannel.class)).handler(new ChannelInitializer() {
            protected void initChannel(SocketChannel ch) {
               ch.pipeline().addLast(new ChannelHandler[]{new StringEncoder(StandardCharsets.UTF_8)});
            }
         });
         ChannelFuture future = bootstrap.connect(host, port).sync();
         future.channel().writeAndFlush(msgSupplier.get()).addListener(ChannelFutureListener.CLOSE);
         future.channel().closeFuture().sync();
      } catch (InterruptedException var9) {
         var9.printStackTrace();
      } finally {
         if (this.group != null) {
            this.group.shutdownGracefully();
         }

      }

   }
}
