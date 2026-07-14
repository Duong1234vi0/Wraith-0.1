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
import javax.crypto.Cipher;
import lightning.product.CipherBase;

public class CipherEncoder
extends MessageToByteEncoder<ByteBuf> {
    private final CipherBase n_1700_B;

    public CipherEncoder(Cipher cipher) {
        this.n_1700_B = new CipherBase(cipher);
    }

    protected void n_1700_B(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception {
        this.n_1700_B.n_1700_B(p_encode_2_, p_encode_3_);
    }

    protected /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.n_1700_B(channelHandlerContext, (ByteBuf)object, byteBuf);
    }
}


