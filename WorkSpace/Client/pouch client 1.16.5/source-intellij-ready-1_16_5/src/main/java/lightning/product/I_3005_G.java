/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToByteEncoder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 */
package lightning.product;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import lightning.product.P_4689_s;
import lightning.product.a_3942_s;
import lightning.product.b_2585_i;
import lightning.product.c_1633_k;
import lightning.product.d_4952_K;
import lightning.product.Packet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class I_3005_G
extends MessageToByteEncoder<Packet<?>> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Marker J_1907_R = MarkerManager.getMarker((String)"PACKET_SENT", (Marker)c_1633_k.J_1907_R);
    private final a_3942_s R_4764_Y;

    public I_3005_G(a_3942_s direction) {
        this.R_4764_Y = direction;
    }

    protected void n_1700_B(ChannelHandlerContext p_encode_1_, Packet<?> p_encode_2_, ByteBuf p_encode_3_) throws Exception {
        d_4952_K protocoltype = (d_4952_K)((Object)p_encode_1_.channel().attr(c_1633_k.R_4764_Y).get());
        if (protocoltype == null) {
            throw new RuntimeException("ConnectionProtocol unknown: " + String.valueOf(p_encode_2_));
        }
        Integer integer = protocoltype.n_1700_B(this.R_4764_Y, p_encode_2_);
        if (n_1700_B.isDebugEnabled()) {
            n_1700_B.debug(J_1907_R, "OUT: [{}:{}] {}", p_encode_1_.channel().attr(c_1633_k.R_4764_Y).get(), (Object)integer, (Object)p_encode_2_.getClass().getName());
        }
        if (integer == null) {
            throw new IOException("Can't serialize unregistered packet");
        }
        b_2585_i packetbuffer = new b_2585_i(p_encode_3_);
        packetbuffer.G_564_y(integer);
        try {
            p_encode_2_.J_1907_R(packetbuffer);
        }
        catch (Throwable throwable) {
            n_1700_B.error((Object)throwable);
            if (p_encode_2_.n_1700_B()) {
                throw new P_4689_s(throwable);
            }
            throw throwable;
        }
    }

    protected /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.n_1700_B(channelHandlerContext, (Packet)object, byteBuf);
    }
}


