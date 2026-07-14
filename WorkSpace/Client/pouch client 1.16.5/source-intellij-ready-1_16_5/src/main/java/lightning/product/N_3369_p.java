/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.Collection;
import lightning.product.F_3620_e;
import lightning.product.J_2020_G;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class N_3369_p
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private byte J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private J_2020_G[] P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private byte[] s_956_w;

    public N_3369_p() {
    }

    public N_3369_p(int p_i50772_1_, byte p_i50772_2_, boolean p_i50772_3_, boolean p_i50772_4_, Collection<J_2020_G> p_i50772_5_, byte[] p_i50772_6_, int p_i50772_7_, int p_i50772_8_, int p_i50772_9_, int p_i50772_10_) {
        this.n_1700_B = p_i50772_1_;
        this.J_1907_R = p_i50772_2_;
        this.R_4764_Y = p_i50772_3_;
        this.G_564_y = p_i50772_4_;
        this.P_1922_E = p_i50772_5_.toArray(new J_2020_G[p_i50772_5_.size()]);
        this.u_1723_Y = p_i50772_7_;
        this.v_4262_N = p_i50772_8_;
        this.w_1484_f = p_i50772_9_;
        this.t_148_a = p_i50772_10_;
        this.s_956_w = new byte[p_i50772_9_ * p_i50772_10_];
        for (int i = 0; i < p_i50772_9_; ++i) {
            for (int j = 0; j < p_i50772_10_; ++j) {
                this.s_956_w[i + j * p_i50772_9_] = p_i50772_6_[p_i50772_7_ + i + (p_i50772_8_ + j) * 128];
            }
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.readByte();
        this.R_4764_Y = buf.readBoolean();
        this.G_564_y = buf.readBoolean();
        this.P_1922_E = new J_2020_G[buf.u_1723_Y()];
        for (int i = 0; i < this.P_1922_E.length; ++i) {
            J_2020_G.n_1700_B mapdecoration$type = buf.n_1700_B(J_2020_G.n_1700_B.class);
            this.P_1922_E[i] = new J_2020_G(mapdecoration$type, buf.readByte(), buf.readByte(), (byte)(buf.readByte() & 0xF), buf.readBoolean() ? buf.P_1922_E() : null);
        }
        this.w_1484_f = buf.readUnsignedByte();
        if (this.w_1484_f > 0) {
            this.t_148_a = buf.readUnsignedByte();
            this.u_1723_Y = buf.readUnsignedByte();
            this.v_4262_N = buf.readUnsignedByte();
            this.s_956_w = buf.n_1700_B();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.writeByte(this.J_1907_R);
        buf.writeBoolean(this.R_4764_Y);
        buf.writeBoolean(this.G_564_y);
        buf.G_564_y(this.P_1922_E.length);
        for (J_2020_G mapdecoration : this.P_1922_E) {
            buf.n_1700_B(mapdecoration.J_1907_R());
            buf.writeByte(mapdecoration.R_4764_Y());
            buf.writeByte(mapdecoration.G_564_y());
            buf.writeByte(mapdecoration.P_1922_E() & 0xF);
            if (mapdecoration.v_4262_N() != null) {
                buf.writeBoolean(true);
                buf.n_1700_B(mapdecoration.v_4262_N());
                continue;
            }
            buf.writeBoolean(false);
        }
        buf.writeByte(this.w_1484_f);
        if (this.w_1484_f > 0) {
            buf.writeByte(this.t_148_a);
            buf.writeByte(this.u_1723_Y);
            buf.writeByte(this.v_4262_N);
            buf.n_1700_B(this.s_956_w);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Override
    public void n_1700_B(F_3620_e mapdataIn) {
        mapdataIn.u_1723_Y = this.J_1907_R;
        mapdataIn.G_564_y = this.R_4764_Y;
        mapdataIn.w_1484_f = this.G_564_y;
        mapdataIn.s_956_w.clear();
        for (int i = 0; i < this.P_1922_E.length; ++i) {
            J_2020_G mapdecoration = this.P_1922_E[i];
            mapdataIn.s_956_w.put("icon-" + i, mapdecoration);
        }
        for (int j = 0; j < this.w_1484_f; ++j) {
            for (int k = 0; k < this.t_148_a; ++k) {
                mapdataIn.v_4262_N[this.u_1723_Y + j + (this.v_4262_N + k) * 128] = this.s_956_w[j + k * this.w_1484_f];
            }
        }
    }
}


