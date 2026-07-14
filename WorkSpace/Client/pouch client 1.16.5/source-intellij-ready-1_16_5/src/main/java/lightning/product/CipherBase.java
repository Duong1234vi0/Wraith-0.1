/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class CipherBase {
    private final Cipher n_1700_B;
    private byte[] J_1907_R = new byte[0];
    private byte[] R_4764_Y = new byte[0];

    protected CipherBase(Cipher cipherIn) {
        this.n_1700_B = cipherIn;
    }

    private byte[] n_1700_B(ByteBuf buf) {
        int i = buf.readableBytes();
        if (this.J_1907_R.length < i) {
            this.J_1907_R = new byte[i];
        }
        buf.readBytes(this.J_1907_R, 0, i);
        return this.J_1907_R;
    }

    protected ByteBuf n_1700_B(ChannelHandlerContext ctx, ByteBuf buffer) throws ShortBufferException {
        int i = buffer.readableBytes();
        byte[] abyte = this.n_1700_B(buffer);
        ByteBuf bytebuf = ctx.alloc().heapBuffer(this.n_1700_B.getOutputSize(i));
        bytebuf.writerIndex(this.n_1700_B.update(abyte, 0, i, bytebuf.array(), bytebuf.arrayOffset()));
        return bytebuf;
    }

    protected void n_1700_B(ByteBuf in, ByteBuf out) throws ShortBufferException {
        int i = in.readableBytes();
        byte[] abyte = this.n_1700_B(in);
        int j = this.n_1700_B.getOutputSize(i);
        if (this.R_4764_Y.length < j) {
            this.R_4764_Y = new byte[j];
        }
        out.writeBytes(this.R_4764_Y, 0, this.n_1700_B.update(abyte, 0, i, this.R_4764_Y));
    }
}


