/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.Abilities;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundPlayerAbilitiesPacket
implements Packet<ClientGamePacketListener> {
    private boolean n_1700_B;
    private boolean J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private float P_1922_E;
    private float u_1723_Y;

    public ClientboundPlayerAbilitiesPacket() {
    }

    public ClientboundPlayerAbilitiesPacket(Abilities capabilities) {
        this.n_1700_B = capabilities.n_1700_B;
        this.J_1907_R = capabilities.J_1907_R;
        this.R_4764_Y = capabilities.R_4764_Y;
        this.G_564_y = capabilities.G_564_y;
        this.P_1922_E = capabilities.n_1700_B();
        this.u_1723_Y = capabilities.J_1907_R();
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        byte b0 = buf.readByte();
        this.n_1700_B = (b0 & 1) != 0;
        this.J_1907_R = (b0 & 2) != 0;
        this.R_4764_Y = (b0 & 4) != 0;
        this.G_564_y = (b0 & 8) != 0;
        this.P_1922_E = buf.readFloat();
        this.u_1723_Y = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        byte b0 = 0;
        if (this.n_1700_B) {
            b0 = (byte)(b0 | 1);
        }
        if (this.J_1907_R) {
            b0 = (byte)(b0 | 2);
        }
        if (this.R_4764_Y) {
            b0 = (byte)(b0 | 4);
        }
        if (this.G_564_y) {
            b0 = (byte)(b0 | 8);
        }
        buf.writeByte(b0);
        buf.writeFloat(this.P_1922_E);
        buf.writeFloat(this.u_1723_Y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public boolean J_1907_R() {
        return this.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    public boolean P_1922_E() {
        return this.G_564_y;
    }

    public float u_1723_Y() {
        return this.P_1922_E;
    }

    public float v_4262_N() {
        return this.u_1723_Y;
    }
}


