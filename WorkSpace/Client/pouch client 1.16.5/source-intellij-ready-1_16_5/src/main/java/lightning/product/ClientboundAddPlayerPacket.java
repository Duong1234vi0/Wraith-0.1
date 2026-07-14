/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.UUID;
import lightning.product.a_3913_L;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundAddPlayerPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private UUID J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private double P_1922_E;
    private byte u_1723_Y;
    private byte v_4262_N;

    public ClientboundAddPlayerPacket() {
    }

    public ClientboundAddPlayerPacket(a_3913_L player) {
        this.n_1700_B = player.j_276_v();
        this.J_1907_R = player.y_4642_Y().getId();
        this.R_4764_Y = player.O_3598_v();
        this.G_564_y = player.X_2960_b();
        this.P_1922_E = player.l_2647_k();
        this.u_1723_Y = (byte)(player.p_178_J * 256.0f / 360.0f);
        this.v_4262_N = (byte)(player.f_4016_n * 256.0f / 360.0f);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.w_1484_f();
        this.R_4764_Y = buf.readDouble();
        this.G_564_y = buf.readDouble();
        this.P_1922_E = buf.readDouble();
        this.u_1723_Y = buf.readByte();
        this.v_4262_N = buf.readByte();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeDouble(this.R_4764_Y);
        buf.writeDouble(this.G_564_y);
        buf.writeDouble(this.P_1922_E);
        buf.writeByte(this.u_1723_Y);
        buf.writeByte(this.v_4262_N);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public UUID R_4764_Y() {
        return this.J_1907_R;
    }

    public double G_564_y() {
        return this.R_4764_Y;
    }

    public double P_1922_E() {
        return this.G_564_y;
    }

    public double u_1723_Y() {
        return this.P_1922_E;
    }

    public byte v_4262_N() {
        return this.u_1723_Y;
    }

    public byte w_1484_f() {
        return this.v_4262_N;
    }
}


