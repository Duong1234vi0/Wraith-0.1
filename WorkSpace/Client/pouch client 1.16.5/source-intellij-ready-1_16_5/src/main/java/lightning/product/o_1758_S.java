/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.ByteToMessageDecoder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.IOException;
import java.util.List;
import lightning.product.a_3942_s;
import lightning.product.b_2585_i;
import lightning.product.c_1633_k;
import lightning.product.d_4952_K;
import lightning.product.Packet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class o_1758_S
extends ByteToMessageDecoder {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Marker J_1907_R = MarkerManager.getMarker((String)"PACKET_RECEIVED", (Marker)c_1633_k.J_1907_R);
    private final a_3942_s R_4764_Y;

    public o_1758_S(a_3942_s direction) {
        this.R_4764_Y = direction;
    }

    protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws Exception {
        if (p_decode_2_.readableBytes() != 0) {
            b_2585_i packetbuffer = new b_2585_i(p_decode_2_);
            int i = packetbuffer.u_1723_Y();
            Packet<?> ipacket = ((d_4952_K)((Object)p_decode_1_.channel().attr(c_1633_k.R_4764_Y).get())).n_1700_B(this.R_4764_Y, i);
            if (ipacket == null) {
                throw new IOException("Bad packet id " + i);
            }
            ipacket.n_1700_B(packetbuffer);
            if (packetbuffer.readableBytes() > 0) {
                throw new IOException("Packet " + ((d_4952_K)((Object)p_decode_1_.channel().attr(c_1633_k.R_4764_Y).get())).n_1700_B() + "/" + i + " (" + ipacket.getClass().getSimpleName() + ") was larger than I expected, found " + packetbuffer.readableBytes() + " bytes extra whilst reading packet " + i);
            }
            p_decode_3_.add(ipacket);
            if (n_1700_B.isDebugEnabled()) {
                n_1700_B.debug(J_1907_R, " IN: [{}:{}] {}", p_decode_1_.channel().attr(c_1633_k.R_4764_Y).get(), (Object)i, (Object)ipacket.getClass().getName());
            }
        }
    }
}


