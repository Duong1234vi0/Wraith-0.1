/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.g_422_i;
import lightning.product.k_2610_C;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundUpdateMobEffectPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private byte J_1907_R;
    private byte R_4764_Y;
    private int G_564_y;
    private byte P_1922_E;

    public ClientboundUpdateMobEffectPacket() {
    }

    public ClientboundUpdateMobEffectPacket(int entityIdIn, k_2610_C effect) {
        this.n_1700_B = entityIdIn;
        this.J_1907_R = (byte)(g_422_i.n_1700_B(effect.n_1700_B()) & 0xFF);
        this.R_4764_Y = (byte)(effect.R_4764_Y() & 0xFF);
        this.G_564_y = effect.J_1907_R() > Short.MAX_VALUE ? Short.MAX_VALUE : effect.J_1907_R();
        this.P_1922_E = 0;
        if (effect.G_564_y()) {
            this.P_1922_E = (byte)(this.P_1922_E | 1);
        }
        if (effect.P_1922_E()) {
            this.P_1922_E = (byte)(this.P_1922_E | 2);
        }
        if (effect.u_1723_Y()) {
            this.P_1922_E = (byte)(this.P_1922_E | 4);
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.readByte();
        this.R_4764_Y = buf.readByte();
        this.G_564_y = buf.u_1723_Y();
        this.P_1922_E = buf.readByte();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.writeByte(this.J_1907_R);
        buf.writeByte(this.R_4764_Y);
        buf.G_564_y(this.G_564_y);
        buf.writeByte(this.P_1922_E);
    }

    public boolean J_1907_R() {
        return this.G_564_y == Short.MAX_VALUE;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }

    public byte G_564_y() {
        return this.J_1907_R;
    }

    public byte P_1922_E() {
        return this.R_4764_Y;
    }

    public int u_1723_Y() {
        return this.G_564_y;
    }

    public boolean v_4262_N() {
        return (this.P_1922_E & 2) == 2;
    }

    public boolean w_1484_f() {
        return (this.P_1922_E & 1) == 1;
    }

    public boolean t_148_a() {
        return (this.P_1922_E & 4) == 4;
    }
}


