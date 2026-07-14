/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.e_2866_D;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.u_530_F;

public class ClientboundSetEntityMotionPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private int G_564_y;

    public ClientboundSetEntityMotionPacket() {
    }

    public ClientboundSetEntityMotionPacket(N_4263_v entityIn) {
        this(entityIn.j_276_v(), entityIn.I_4348_c());
    }

    public ClientboundSetEntityMotionPacket(int p_i50764_1_, e_2866_D p_i50764_2_) {
        this.n_1700_B = p_i50764_1_;
        double d0 = 3.9;
        double d1 = u_530_F.n_1700_B(p_i50764_2_.J_1907_R, -3.9, 3.9);
        double d2 = u_530_F.n_1700_B(p_i50764_2_.R_4764_Y, -3.9, 3.9);
        double d3 = u_530_F.n_1700_B(p_i50764_2_.G_564_y, -3.9, 3.9);
        this.J_1907_R = (int)(d1 * 8000.0);
        this.R_4764_Y = (int)(d2 * 8000.0);
        this.G_564_y = (int)(d3 * 8000.0);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.readShort();
        this.R_4764_Y = buf.readShort();
        this.G_564_y = buf.readShort();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.writeShort(this.J_1907_R);
        buf.writeShort(this.R_4764_Y);
        buf.writeShort(this.G_564_y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
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

    public int P_1922_E() {
        return this.G_564_y;
    }
}


