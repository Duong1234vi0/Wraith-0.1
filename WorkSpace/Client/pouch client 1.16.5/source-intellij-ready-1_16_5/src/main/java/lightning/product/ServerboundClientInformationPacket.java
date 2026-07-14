/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.g_4418_P;
import lightning.product.k_4231_L;
import lightning.product.Packet;

public class ServerboundClientInformationPacket
implements Packet<ServerGamePacketListener> {
    private String n_1700_B;
    private int J_1907_R;
    private g_4418_P R_4764_Y;
    private boolean G_564_y;
    private int P_1922_E;
    private k_4231_L u_1723_Y;

    public ServerboundClientInformationPacket() {
    }

    public ServerboundClientInformationPacket(String lang, int view, g_4418_P chatVisibility, boolean enableColors, int modelPartFlags, k_4231_L mainHand) {
        this.n_1700_B = lang;
        this.J_1907_R = view;
        this.R_4764_Y = chatVisibility;
        this.G_564_y = enableColors;
        this.P_1922_E = modelPartFlags;
        this.u_1723_Y = mainHand;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(16);
        this.J_1907_R = buf.readByte();
        this.R_4764_Y = buf.n_1700_B(g_4418_P.class);
        this.G_564_y = buf.readBoolean();
        this.P_1922_E = buf.readUnsignedByte();
        this.u_1723_Y = buf.n_1700_B(k_4231_L.class);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeByte(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
        buf.writeBoolean(this.G_564_y);
        buf.writeByte(this.P_1922_E);
        buf.n_1700_B(this.u_1723_Y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public g_4418_P J_1907_R() {
        return this.R_4764_Y;
    }

    public boolean R_4764_Y() {
        return this.G_564_y;
    }

    public int G_564_y() {
        return this.P_1922_E;
    }

    public k_4231_L P_1922_E() {
        return this.u_1723_Y;
    }
}


