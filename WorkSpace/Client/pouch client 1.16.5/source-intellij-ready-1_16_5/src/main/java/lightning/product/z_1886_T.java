/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.R_2450_T;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class z_1886_T
implements Packet<ServerGamePacketListener> {
    private R_2450_T n_1700_B;

    public z_1886_T() {
    }

    public z_1886_T(R_2450_T p_i50762_1_) {
        this.n_1700_B = p_i50762_1_;
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = R_2450_T.n_1700_B(buf.readUnsignedByte());
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B.n_1700_B());
    }

    public R_2450_T J_1907_R() {
        return this.n_1700_B;
    }
}


