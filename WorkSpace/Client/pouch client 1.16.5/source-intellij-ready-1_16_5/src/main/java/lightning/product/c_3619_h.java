/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.ByteToMessageDecoder
 *  io.netty.handler.codec.DecoderException
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import java.util.List;
import java.util.zip.Inflater;
import lightning.product.b_2585_i;

public class c_3619_h
extends ByteToMessageDecoder {
    private final Inflater n_1700_B;
    private int J_1907_R;

    public c_3619_h(int thresholdIn) {
        this.J_1907_R = thresholdIn;
        this.n_1700_B = new Inflater();
    }

    protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws Exception {
        if (p_decode_2_.readableBytes() != 0) {
            b_2585_i packetbuffer = new b_2585_i(p_decode_2_);
            int i = packetbuffer.u_1723_Y();
            if (i == 0) {
                p_decode_3_.add(packetbuffer.readBytes(packetbuffer.readableBytes()));
            } else {
                if (i < this.J_1907_R) {
                    throw new DecoderException("Badly compressed packet - size of " + i + " is below server threshold of " + this.J_1907_R);
                }
                if (i > 0x200000) {
                    throw new DecoderException("Badly compressed packet - size of " + i + " is larger than protocol maximum of 2097152");
                }
                byte[] abyte = new byte[packetbuffer.readableBytes()];
                packetbuffer.readBytes(abyte);
                this.n_1700_B.setInput(abyte);
                byte[] abyte1 = new byte[i];
                this.n_1700_B.inflate(abyte1);
                p_decode_3_.add(Unpooled.wrappedBuffer((byte[])abyte1));
                this.n_1700_B.reset();
            }
        }
    }

    public void n_1700_B(int thresholdIn) {
        this.J_1907_R = thresholdIn;
    }
}

