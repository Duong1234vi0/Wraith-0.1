/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundContainerClickPacket
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private short G_564_y;
    private Z_1993_T P_1922_E = Z_1993_T.J_1907_R;
    private a_408_T u_1723_Y;

    public ServerboundContainerClickPacket() {
    }

    public ServerboundContainerClickPacket(int windowIdIn, int slotIdIn, int usedButtonIn, a_408_T modeIn, Z_1993_T clickedItemIn, short actionNumberIn) {
        this.n_1700_B = windowIdIn;
        this.J_1907_R = slotIdIn;
        this.R_4764_Y = usedButtonIn;
        this.P_1922_E = clickedItemIn.t_148_a();
        this.G_564_y = actionNumberIn;
        this.u_1723_Y = modeIn;
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readByte();
        this.J_1907_R = buf.readShort();
        this.R_4764_Y = buf.readByte();
        this.G_564_y = buf.readShort();
        this.u_1723_Y = buf.n_1700_B(a_408_T.class);
        this.P_1922_E = buf.u_2550_I();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.writeShort(this.J_1907_R);
        buf.writeByte(this.R_4764_Y);
        buf.writeShort(this.G_564_y);
        buf.n_1700_B(this.u_1723_Y);
        buf.n_1700_B(this.P_1922_E);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public short P_1922_E() {
        return this.G_564_y;
    }

    public Z_1993_T u_1723_Y() {
        return this.P_1922_E;
    }

    public a_408_T v_4262_N() {
        return this.u_1723_Y;
    }
}


