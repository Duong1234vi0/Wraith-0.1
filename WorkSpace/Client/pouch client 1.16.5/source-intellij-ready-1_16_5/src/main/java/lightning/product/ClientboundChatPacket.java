/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.UUID;
import lightning.product.Y_408_h;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class ClientboundChatPacket
implements Packet<ClientGamePacketListener> {
    private x_282_a n_1700_B;
    private Y_408_h J_1907_R;
    private UUID R_4764_Y;

    public ClientboundChatPacket() {
    }

    public ClientboundChatPacket(x_282_a p_i232578_1_, Y_408_h p_i232578_2_, UUID p_i232578_3_) {
        this.n_1700_B = p_i232578_1_;
        this.J_1907_R = p_i232578_2_;
        this.R_4764_Y = p_i232578_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E();
        this.J_1907_R = Y_408_h.n_1700_B(buf.readByte());
        this.R_4764_Y = buf.w_1484_f();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeByte(this.J_1907_R.n_1700_B());
        buf.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public x_282_a J_1907_R() {
        return this.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R == Y_408_h.J_1907_R || this.J_1907_R == Y_408_h.R_4764_Y;
    }

    public Y_408_h G_564_y() {
        return this.J_1907_R;
    }

    public UUID P_1922_E() {
        return this.R_4764_Y;
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }
}


