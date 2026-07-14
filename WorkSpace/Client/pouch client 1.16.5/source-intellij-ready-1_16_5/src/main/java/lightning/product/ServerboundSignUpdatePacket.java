/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.Packet;

public class ServerboundSignUpdatePacket
implements Packet<ServerGamePacketListener> {
    private c_1514_x n_1700_B;
    private String[] J_1907_R;

    public ServerboundSignUpdatePacket() {
    }

    public ServerboundSignUpdatePacket(c_1514_x p_i232585_1_, String p_i232585_2_, String p_i232585_3_, String p_i232585_4_, String p_i232585_5_) {
        this.n_1700_B = p_i232585_1_;
        this.J_1907_R = new String[]{p_i232585_2_, p_i232585_3_, p_i232585_4_, p_i232585_5_};
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = new String[4];
        for (int i = 0; i < 4; ++i) {
            this.J_1907_R[i] = buf.P_1922_E(384);
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        for (int i = 0; i < 4; ++i) {
            buf.n_1700_B(this.J_1907_R[i]);
        }
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public String[] R_4764_Y() {
        return this.J_1907_R;
    }
}


