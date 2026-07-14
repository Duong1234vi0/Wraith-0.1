/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetHealthPacket
implements Packet<ClientGamePacketListener> {
    private float n_1700_B;
    private int J_1907_R;
    private float R_4764_Y;

    public ClientboundSetHealthPacket() {
    }

    public ClientboundSetHealthPacket(float healthIn, int foodLevelIn, float saturationLevelIn) {
        this.n_1700_B = healthIn;
        this.J_1907_R = foodLevelIn;
        this.R_4764_Y = saturationLevelIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readFloat();
        this.J_1907_R = buf.u_1723_Y();
        this.R_4764_Y = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeFloat(this.n_1700_B);
        buf.G_564_y(this.J_1907_R);
        buf.writeFloat(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public float J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public float G_564_y() {
        return this.R_4764_Y;
    }
}


