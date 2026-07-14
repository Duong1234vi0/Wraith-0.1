/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.UUID;
import lightning.product.Painting;
import lightning.product.V_3137_a;
import lightning.product.b_257_Y;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.Motive;

public class ClientboundAddPaintingPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private UUID J_1907_R;
    private c_1514_x R_4764_Y;
    private b_257_Y G_564_y;
    private int P_1922_E;

    public ClientboundAddPaintingPacket() {
    }

    public ClientboundAddPaintingPacket(Painting painting) {
        this.n_1700_B = painting.j_276_v();
        this.J_1907_R = painting.w_2705_t();
        this.R_4764_Y = painting.u_2550_I();
        this.G_564_y = painting.o_2767_H();
        this.P_1922_E = V_3137_a.Z_976_R.n_1700_B(painting.G_564_y);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.w_1484_f();
        this.P_1922_E = buf.u_1723_Y();
        this.R_4764_Y = buf.R_4764_Y();
        this.G_564_y = b_257_Y.J_1907_R(buf.readUnsignedByte());
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.G_564_y(this.P_1922_E);
        buf.n_1700_B(this.R_4764_Y);
        buf.writeByte(this.G_564_y.G_564_y());
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public UUID R_4764_Y() {
        return this.J_1907_R;
    }

    public c_1514_x G_564_y() {
        return this.R_4764_Y;
    }

    public b_257_Y P_1922_E() {
        return this.G_564_y;
    }

    public Motive u_1723_Y() {
        return V_3137_a.Z_976_R.n_1700_B(this.P_1922_E);
    }
}


