/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.T_1368_k;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.Packet;

public class ServerboundSetCommandBlockPacket
implements Packet<ServerGamePacketListener> {
    private c_1514_x n_1700_B;
    private String J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private boolean P_1922_E;
    private T_1368_k.n_1700_B u_1723_Y;

    public ServerboundSetCommandBlockPacket() {
    }

    public ServerboundSetCommandBlockPacket(c_1514_x p_i49543_1_, String p_i49543_2_, T_1368_k.n_1700_B p_i49543_3_, boolean p_i49543_4_, boolean p_i49543_5_, boolean p_i49543_6_) {
        this.n_1700_B = p_i49543_1_;
        this.J_1907_R = p_i49543_2_;
        this.R_4764_Y = p_i49543_4_;
        this.G_564_y = p_i49543_5_;
        this.P_1922_E = p_i49543_6_;
        this.u_1723_Y = p_i49543_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = buf.P_1922_E(Short.MAX_VALUE);
        this.u_1723_Y = buf.n_1700_B(T_1368_k.n_1700_B.class);
        byte i = buf.readByte();
        this.R_4764_Y = (i & 1) != 0;
        this.G_564_y = (i & 2) != 0;
        this.P_1922_E = (i & 4) != 0;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.n_1700_B(this.u_1723_Y);
        int i = 0;
        if (this.R_4764_Y) {
            i |= 1;
        }
        if (this.G_564_y) {
            i |= 2;
        }
        if (this.P_1922_E) {
            i |= 4;
        }
        buf.writeByte(i);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public String R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    public boolean P_1922_E() {
        return this.G_564_y;
    }

    public boolean u_1723_Y() {
        return this.P_1922_E;
    }

    public T_1368_k.n_1700_B v_4262_N() {
        return this.u_1723_Y;
    }
}


