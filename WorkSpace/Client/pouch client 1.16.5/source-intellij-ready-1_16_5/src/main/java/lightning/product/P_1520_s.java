/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class P_1520_s
implements Packet<ServerGamePacketListener> {
    private String n_1700_B;

    public P_1520_s() {
    }

    public P_1520_s(String p_i49546_1_) {
        this.n_1700_B = p_i49546_1_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(Short.MAX_VALUE);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public String J_1907_R() {
        return this.n_1700_B;
    }
}


