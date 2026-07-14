/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class F_551_J
implements Packet<ClientGamePacketListener> {
    private double n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private float G_564_y;
    private float P_1922_E;

    public F_551_J() {
    }

    public F_551_J(N_4263_v entityIn) {
        this.n_1700_B = entityIn.O_3598_v();
        this.J_1907_R = entityIn.X_2960_b();
        this.R_4764_Y = entityIn.l_2647_k();
        this.G_564_y = entityIn.p_178_J;
        this.P_1922_E = entityIn.f_4016_n;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readDouble();
        this.J_1907_R = buf.readDouble();
        this.R_4764_Y = buf.readDouble();
        this.G_564_y = buf.readFloat();
        this.P_1922_E = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeDouble(this.n_1700_B);
        buf.writeDouble(this.J_1907_R);
        buf.writeDouble(this.R_4764_Y);
        buf.writeFloat(this.G_564_y);
        buf.writeFloat(this.P_1922_E);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public double J_1907_R() {
        return this.n_1700_B;
    }

    public double R_4764_Y() {
        return this.J_1907_R;
    }

    public double G_564_y() {
        return this.R_4764_Y;
    }

    public float P_1922_E() {
        return this.G_564_y;
    }

    public float u_1723_Y() {
        return this.P_1922_E;
    }
}


