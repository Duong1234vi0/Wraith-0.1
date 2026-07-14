/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.Packet;

public class O_3036_q
implements Packet<ServerGamePacketListener> {
    public static final g_2336_b n_1700_B = new g_2336_b("brand");
    private g_2336_b J_1907_R;
    private b_2585_i R_4764_Y;

    public O_3036_q() {
    }

    public O_3036_q(g_2336_b channelIn, b_2585_i dataIn) {
        this.J_1907_R = channelIn;
        this.R_4764_Y = dataIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.J_1907_R = buf.P_4830_p();
        int i = buf.readableBytes();
        if (i < 0 || i > Short.MAX_VALUE) {
            throw new IOException("Payload may not be larger than 32767 bytes");
        }
        this.R_4764_Y = new b_2585_i(buf.readBytes(i));
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.J_1907_R);
        buf.writeBytes(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
        if (this.R_4764_Y != null) {
            this.R_4764_Y.release();
        }
    }

    public g_2336_b J_1907_R() {
        return this.J_1907_R;
    }

    public b_2585_i R_4764_Y() {
        return this.R_4764_Y;
    }
}


