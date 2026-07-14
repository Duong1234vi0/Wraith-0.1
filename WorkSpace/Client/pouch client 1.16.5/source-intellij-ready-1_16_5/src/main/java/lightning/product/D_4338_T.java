/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class D_4338_T
implements Packet<ServerGamePacketListener> {
    private Z_1993_T n_1700_B;
    private boolean J_1907_R;
    private int R_4764_Y;

    public D_4338_T() {
    }

    public D_4338_T(Z_1993_T p_i242143_1_, boolean p_i242143_2_, int p_i242143_3_) {
        this.n_1700_B = p_i242143_1_.t_148_a();
        this.J_1907_R = p_i242143_2_;
        this.R_4764_Y = p_i242143_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_2550_I();
        this.J_1907_R = buf.readBoolean();
        this.R_4764_Y = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeBoolean(this.J_1907_R);
        buf.G_564_y(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public Z_1993_T J_1907_R() {
        return this.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }
}


