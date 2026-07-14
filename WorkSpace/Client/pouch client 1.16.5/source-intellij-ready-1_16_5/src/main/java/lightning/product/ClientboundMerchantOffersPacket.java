/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.MerchantOffers;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundMerchantOffersPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private MerchantOffers J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private boolean P_1922_E;
    private boolean u_1723_Y;

    public ClientboundMerchantOffersPacket() {
    }

    public ClientboundMerchantOffersPacket(int id, MerchantOffers offersIn, int levelIn, int xpIn, boolean p_i51539_5_, boolean p_i51539_6_) {
        this.n_1700_B = id;
        this.J_1907_R = offersIn;
        this.R_4764_Y = levelIn;
        this.G_564_y = xpIn;
        this.P_1922_E = p_i51539_5_;
        this.u_1723_Y = p_i51539_6_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = MerchantOffers.J_1907_R(buf);
        this.R_4764_Y = buf.u_1723_Y();
        this.G_564_y = buf.u_1723_Y();
        this.P_1922_E = buf.readBoolean();
        this.u_1723_Y = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        this.J_1907_R.n_1700_B(buf);
        buf.G_564_y(this.R_4764_Y);
        buf.G_564_y(this.G_564_y);
        buf.writeBoolean(this.P_1922_E);
        buf.writeBoolean(this.u_1723_Y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public MerchantOffers R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public int P_1922_E() {
        return this.G_564_y;
    }

    public boolean u_1723_Y() {
        return this.P_1922_E;
    }

    public boolean v_4262_N() {
        return this.u_1723_Y;
    }
}


