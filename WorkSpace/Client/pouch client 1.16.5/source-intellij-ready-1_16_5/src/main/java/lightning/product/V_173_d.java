/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientLoginPacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class V_173_d
implements Packet<ClientLoginPacketListener> {
    private x_282_a n_1700_B;

    public V_173_d() {
    }

    public V_173_d(x_282_a p_i46853_1_) {
        this.n_1700_B = p_i46853_1_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = x_282_a.n_1700_B.J_1907_R(buf.P_1922_E(262144));
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ClientLoginPacketListener handler) {
        handler.n_1700_B(this);
    }

    public x_282_a J_1907_R() {
        return this.n_1700_B;
    }
}


