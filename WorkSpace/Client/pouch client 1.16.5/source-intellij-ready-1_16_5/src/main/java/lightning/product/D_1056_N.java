/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class D_1056_N
implements Packet<ClientGamePacketListener> {
    private x_282_a n_1700_B;
    private x_282_a J_1907_R;

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E();
        this.J_1907_R = buf.P_1922_E();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public x_282_a J_1907_R() {
        return this.n_1700_B;
    }

    public x_282_a R_4764_Y() {
        return this.J_1907_R;
    }
}


