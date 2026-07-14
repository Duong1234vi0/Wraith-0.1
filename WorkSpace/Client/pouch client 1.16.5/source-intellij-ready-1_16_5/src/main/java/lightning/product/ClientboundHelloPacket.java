/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.security.PublicKey;
import lightning.product.Y_2605_X;
import lightning.product.b_2585_i;
import lightning.product.ClientLoginPacketListener;
import lightning.product.Packet;
import lightning.product.Crypt;

public class ClientboundHelloPacket
implements Packet<ClientLoginPacketListener> {
    private String n_1700_B;
    private byte[] J_1907_R;
    private byte[] R_4764_Y;

    public ClientboundHelloPacket() {
    }

    public ClientboundHelloPacket(String p_i242142_1_, byte[] p_i242142_2_, byte[] p_i242142_3_) {
        this.n_1700_B = p_i242142_1_;
        this.J_1907_R = p_i242142_2_;
        this.R_4764_Y = p_i242142_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(20);
        this.J_1907_R = buf.n_1700_B();
        this.R_4764_Y = buf.n_1700_B();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ClientLoginPacketListener handler) {
        handler.n_1700_B(this);
    }

    public String J_1907_R() {
        return this.n_1700_B;
    }

    public PublicKey R_4764_Y() throws Y_2605_X {
        return Crypt.n_1700_B(this.J_1907_R);
    }

    public byte[] G_564_y() {
        return this.R_4764_Y;
    }
}


