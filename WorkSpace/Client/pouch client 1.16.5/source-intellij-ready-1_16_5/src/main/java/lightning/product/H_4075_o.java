/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.Packet;

public class H_4075_o
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private g_2336_b J_1907_R;
    private boolean R_4764_Y;

    public H_4075_o() {
    }

    public H_4075_o(int p_i47614_1_, Recipe<?> p_i47614_2_, boolean p_i47614_3_) {
        this.n_1700_B = p_i47614_1_;
        this.J_1907_R = p_i47614_2_.u_1723_Y();
        this.R_4764_Y = p_i47614_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readByte();
        this.J_1907_R = buf.P_4830_p();
        this.R_4764_Y = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeBoolean(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public g_2336_b R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }
}


