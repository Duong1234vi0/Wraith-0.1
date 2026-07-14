/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundBlockEventPacket
implements Packet<ClientGamePacketListener> {
    private c_1514_x n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private T_2915_h G_564_y;

    public ClientboundBlockEventPacket() {
    }

    public ClientboundBlockEventPacket(c_1514_x pos, T_2915_h blockIn, int instrumentIn, int pitchIn) {
        this.n_1700_B = pos;
        this.G_564_y = blockIn;
        this.J_1907_R = instrumentIn;
        this.R_4764_Y = pitchIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = buf.readUnsignedByte();
        this.R_4764_Y = buf.readUnsignedByte();
        this.G_564_y = V_3137_a.q_4610_l.n_1700_B(buf.u_1723_Y());
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeByte(this.J_1907_R);
        buf.writeByte(this.R_4764_Y);
        buf.G_564_y(V_3137_a.q_4610_l.n_1700_B(this.G_564_y));
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public T_2915_h P_1922_E() {
        return this.G_564_y;
    }
}


