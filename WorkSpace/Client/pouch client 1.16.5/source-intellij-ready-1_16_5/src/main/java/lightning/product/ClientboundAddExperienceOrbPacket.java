/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.n_4637_L;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundAddExperienceOrbPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private int P_1922_E;

    public ClientboundAddExperienceOrbPacket() {
    }

    public ClientboundAddExperienceOrbPacket(n_4637_L orb) {
        this.n_1700_B = orb.j_276_v();
        this.J_1907_R = orb.O_3598_v();
        this.R_4764_Y = orb.X_2960_b();
        this.G_564_y = orb.l_2647_k();
        this.P_1922_E = orb.P_1922_E();
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.readDouble();
        this.R_4764_Y = buf.readDouble();
        this.G_564_y = buf.readDouble();
        this.P_1922_E = buf.readShort();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.writeDouble(this.J_1907_R);
        buf.writeDouble(this.R_4764_Y);
        buf.writeDouble(this.G_564_y);
        buf.writeShort(this.P_1922_E);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public double R_4764_Y() {
        return this.J_1907_R;
    }

    public double G_564_y() {
        return this.R_4764_Y;
    }

    public double P_1922_E() {
        return this.G_564_y;
    }

    public int u_1723_Y() {
        return this.P_1922_E;
    }
}


