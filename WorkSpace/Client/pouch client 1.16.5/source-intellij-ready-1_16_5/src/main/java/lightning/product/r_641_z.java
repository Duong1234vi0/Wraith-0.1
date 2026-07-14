/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandler$Sharable
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToByteEncoder
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lightning.product.b_2585_i;

@ChannelHandler.Sharable
public class r_641_z
extends MessageToByteEncoder<ByteBuf> {
    protected void n_1700_B(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception {
        int i = p_encode_2_.readableBytes();
        int j = b_2585_i.n_1700_B(i);
        if (j > 3) {
            throw new IllegalArgumentException("unable to fit " + i + " into 3");
        }
        b_2585_i packetbuffer = new b_2585_i(p_encode_3_);
        packetbuffer.ensureWritable(j + i);
        packetbuffer.G_564_y(i);
        packetbuffer.writeBytes(p_encode_2_, p_encode_2_.readerIndex(), i);
    }

    protected /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.n_1700_B(channelHandlerContext, (ByteBuf)object, byteBuf);
    }
}

