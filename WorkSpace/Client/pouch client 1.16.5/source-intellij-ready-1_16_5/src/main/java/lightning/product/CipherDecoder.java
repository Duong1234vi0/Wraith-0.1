/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToMessageDecoder
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;
import lightning.product.CipherBase;

public class CipherDecoder
extends MessageToMessageDecoder<ByteBuf> {
    private final CipherBase n_1700_B;

    public CipherDecoder(Cipher cipher) {
        this.n_1700_B = new CipherBase(cipher);
    }

    protected void n_1700_B(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws Exception {
        p_decode_3_.add(this.n_1700_B.n_1700_B(p_decode_1_, p_decode_2_));
    }

    protected /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, Object object, List list) throws Exception {
        this.n_1700_B(channelHandlerContext, (ByteBuf)object, list);
    }
}


