/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class W_1158_a
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private g_2336_b J_1907_R;

    public W_1158_a() {
    }

    public W_1158_a(int p_i47615_1_, Recipe<?> p_i47615_2_) {
        this.n_1700_B = p_i47615_1_;
        this.J_1907_R = p_i47615_2_.u_1723_Y();
    }

    public g_2336_b J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readByte();
        this.J_1907_R = buf.P_4830_p();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }
}


