/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.Packet;

public class V_182_a
implements Packet<ServerGamePacketListener> {
    private c_1514_x n_1700_B;
    private int J_1907_R;
    private boolean R_4764_Y;

    public V_182_a() {
    }

    public V_182_a(c_1514_x p_i232583_1_, int p_i232583_2_, boolean p_i232583_3_) {
        this.n_1700_B = p_i232583_1_;
        this.J_1907_R = p_i232583_2_;
        this.R_4764_Y = p_i232583_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = buf.u_1723_Y();
        this.R_4764_Y = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.G_564_y(this.J_1907_R);
        buf.writeBoolean(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }
}


