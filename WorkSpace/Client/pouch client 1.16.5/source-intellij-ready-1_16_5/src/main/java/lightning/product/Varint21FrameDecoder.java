/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.ByteToMessageDecoder
 *  io.netty.handler.codec.CorruptedFrameException
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;
import lightning.product.b_2585_i;

public class Varint21FrameDecoder
extends ByteToMessageDecoder {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws Exception {
        p_decode_2_.markReaderIndex();
        byte[] abyte = new byte[3];
        for (int i = 0; i < abyte.length; ++i) {
            if (!p_decode_2_.isReadable()) {
                p_decode_2_.resetReaderIndex();
                return;
            }
            abyte[i] = p_decode_2_.readByte();
            if (abyte[i] < 0) continue;
            b_2585_i packetbuffer = new b_2585_i(Unpooled.wrappedBuffer((byte[])abyte));
            try {
                int j = packetbuffer.u_1723_Y();
                if (p_decode_2_.readableBytes() >= j) {
                    p_decode_3_.add(p_decode_2_.readBytes(j));
                    return;
                }
                p_decode_2_.resetReaderIndex();
            }
            finally {
                packetbuffer.release();
            }
            return;
        }
        throw new CorruptedFrameException("length wider than 21-bit");
    }
}


