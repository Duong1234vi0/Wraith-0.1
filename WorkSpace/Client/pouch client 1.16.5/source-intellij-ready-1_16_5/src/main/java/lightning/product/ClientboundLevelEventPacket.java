/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundLevelEventPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private c_1514_x J_1907_R;
    private int R_4764_Y;
    private boolean G_564_y;

    public ClientboundLevelEventPacket() {
    }

    public ClientboundLevelEventPacket(int soundTypeIn, c_1514_x soundPosIn, int soundDataIn, boolean serverWideIn) {
        this.n_1700_B = soundTypeIn;
        this.J_1907_R = soundPosIn.toImmutable();
        this.R_4764_Y = soundDataIn;
        this.G_564_y = serverWideIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readInt();
        this.J_1907_R = buf.R_4764_Y();
        this.R_4764_Y = buf.readInt();
        this.G_564_y = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeInt(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeInt(this.R_4764_Y);
        buf.writeBoolean(this.G_564_y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public boolean J_1907_R() {
        return this.G_564_y;
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public c_1514_x P_1922_E() {
        return this.J_1907_R;
    }
}


