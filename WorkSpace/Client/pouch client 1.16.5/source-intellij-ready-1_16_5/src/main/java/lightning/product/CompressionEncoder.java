/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToByteEncoder
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;
import lightning.product.b_2585_i;

public class CompressionEncoder
extends MessageToByteEncoder<ByteBuf> {
    private final byte[] n_1700_B = new byte[8192];
    private final Deflater J_1907_R;
    private int R_4764_Y;

    public CompressionEncoder(int thresholdIn) {
        this.R_4764_Y = thresholdIn;
        this.J_1907_R = new Deflater();
    }

    protected void n_1700_B(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception {
        int i = p_encode_2_.readableBytes();
        b_2585_i packetbuffer = new b_2585_i(p_encode_3_);
        if (i < this.R_4764_Y) {
            packetbuffer.G_564_y(0);
            packetbuffer.writeBytes(p_encode_2_);
        } else {
            byte[] abyte = new byte[i];
            p_encode_2_.readBytes(abyte);
            packetbuffer.G_564_y(abyte.length);
            this.J_1907_R.setInput(abyte, 0, i);
            this.J_1907_R.finish();
            while (!this.J_1907_R.finished()) {
                int j = this.J_1907_R.deflate(this.n_1700_B);
                packetbuffer.writeBytes(this.n_1700_B, 0, j);
            }
            this.J_1907_R.reset();
        }
    }

    public void n_1700_B(int thresholdIn) {
        this.R_4764_Y = thresholdIn;
    }

    protected /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.n_1700_B(channelHandlerContext, (ByteBuf)object, byteBuf);
    }
}


