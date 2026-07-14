/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.M_1462_J;
import lightning.product.Objective;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class ClientboundSetObjectivePacket
implements Packet<ClientGamePacketListener> {
    private String n_1700_B;
    private x_282_a J_1907_R;
    private M_1462_J.n_1700_B R_4764_Y;
    private int G_564_y;

    public ClientboundSetObjectivePacket() {
    }

    public ClientboundSetObjectivePacket(Objective objective, int actionIn) {
        this.n_1700_B = objective.J_1907_R();
        this.J_1907_R = objective.G_564_y();
        this.R_4764_Y = objective.u_1723_Y();
        this.G_564_y = actionIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(16);
        this.G_564_y = buf.readByte();
        if (this.G_564_y == 0 || this.G_564_y == 2) {
            this.J_1907_R = buf.P_1922_E();
            this.R_4764_Y = buf.n_1700_B(M_1462_J.n_1700_B.class);
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeByte(this.G_564_y);
        if (this.G_564_y == 0 || this.G_564_y == 2) {
            buf.n_1700_B(this.J_1907_R);
            buf.n_1700_B(this.R_4764_Y);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public String J_1907_R() {
        return this.n_1700_B;
    }

    public x_282_a R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.G_564_y;
    }

    public M_1462_J.n_1700_B P_1922_E() {
        return this.R_4764_Y;
    }
}


