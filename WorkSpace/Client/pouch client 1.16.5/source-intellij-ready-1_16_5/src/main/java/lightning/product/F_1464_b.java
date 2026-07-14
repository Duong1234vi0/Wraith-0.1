/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.BlockHitResult;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;
import lightning.product.x_1688_C;

public class F_1464_b
implements Packet<ServerGamePacketListener> {
    private BlockHitResult n_1700_B;
    private x_1688_C J_1907_R;

    public F_1464_b() {
    }

    public F_1464_b(x_1688_C p_i50756_1_, BlockHitResult p_i50756_2_) {
        this.J_1907_R = p_i50756_1_;
        this.n_1700_B = p_i50756_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.J_1907_R = buf.n_1700_B(x_1688_C.class);
        this.n_1700_B = buf.Q_4569_t();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.J_1907_R);
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public x_1688_C J_1907_R() {
        return this.J_1907_R;
    }

    public BlockHitResult R_4764_Y() {
        return this.n_1700_B;
    }
}


