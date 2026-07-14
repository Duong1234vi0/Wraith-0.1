/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetExperiencePacket
implements Packet<ClientGamePacketListener> {
    private float n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;

    public ClientboundSetExperiencePacket() {
    }

    public ClientboundSetExperiencePacket(float experienceBarIn, int totalExperienceIn, int levelIn) {
        this.n_1700_B = experienceBarIn;
        this.J_1907_R = totalExperienceIn;
        this.R_4764_Y = levelIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readFloat();
        this.R_4764_Y = buf.u_1723_Y();
        this.J_1907_R = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeFloat(this.n_1700_B);
        buf.G_564_y(this.R_4764_Y);
        buf.G_564_y(this.J_1907_R);
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

    public int G_564_y() {
        return this.R_4764_Y;
    }
}


