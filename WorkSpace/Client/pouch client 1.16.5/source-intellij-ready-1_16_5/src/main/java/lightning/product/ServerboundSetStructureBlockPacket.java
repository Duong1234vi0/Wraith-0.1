/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.M_3212_T;
import lightning.product.W_2163_m;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.j_2644_e;
import lightning.product.q_4099_E;
import lightning.product.Packet;
import lightning.product.u_530_F;

public class ServerboundSetStructureBlockPacket
implements Packet<ServerGamePacketListener> {
    private c_1514_x n_1700_B;
    private j_2644_e.n_1700_B J_1907_R;
    private M_3212_T R_4764_Y;
    private String G_564_y;
    private c_1514_x P_1922_E;
    private c_1514_x u_1723_Y;
    private q_4099_E v_4262_N;
    private W_2163_m w_1484_f;
    private String t_148_a;
    private boolean s_956_w;
    private boolean u_2550_I;
    private boolean M_588_G;
    private float P_4830_p;
    private long h_1847_R;

    public ServerboundSetStructureBlockPacket() {
    }

    public ServerboundSetStructureBlockPacket(c_1514_x p_i49541_1_, j_2644_e.n_1700_B p_i49541_2_, M_3212_T p_i49541_3_, String p_i49541_4_, c_1514_x p_i49541_5_, c_1514_x p_i49541_6_, q_4099_E p_i49541_7_, W_2163_m p_i49541_8_, String p_i49541_9_, boolean p_i49541_10_, boolean p_i49541_11_, boolean p_i49541_12_, float p_i49541_13_, long p_i49541_14_) {
        this.n_1700_B = p_i49541_1_;
        this.J_1907_R = p_i49541_2_;
        this.R_4764_Y = p_i49541_3_;
        this.G_564_y = p_i49541_4_;
        this.P_1922_E = p_i49541_5_;
        this.u_1723_Y = p_i49541_6_;
        this.v_4262_N = p_i49541_7_;
        this.w_1484_f = p_i49541_8_;
        this.t_148_a = p_i49541_9_;
        this.s_956_w = p_i49541_10_;
        this.u_2550_I = p_i49541_11_;
        this.M_588_G = p_i49541_12_;
        this.P_4830_p = p_i49541_13_;
        this.h_1847_R = p_i49541_14_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = buf.n_1700_B(j_2644_e.n_1700_B.class);
        this.R_4764_Y = buf.n_1700_B(M_3212_T.class);
        this.G_564_y = buf.P_1922_E(Short.MAX_VALUE);
        int i = 48;
        this.P_1922_E = new c_1514_x(u_530_F.n_1700_B((int)buf.readByte(), -48, 48), u_530_F.n_1700_B((int)buf.readByte(), -48, 48), u_530_F.n_1700_B((int)buf.readByte(), -48, 48));
        int j = 48;
        this.u_1723_Y = new c_1514_x(u_530_F.n_1700_B((int)buf.readByte(), 0, 48), u_530_F.n_1700_B((int)buf.readByte(), 0, 48), u_530_F.n_1700_B((int)buf.readByte(), 0, 48));
        this.v_4262_N = buf.n_1700_B(q_4099_E.class);
        this.w_1484_f = buf.n_1700_B(W_2163_m.class);
        this.t_148_a = buf.P_1922_E(12);
        this.P_4830_p = u_530_F.n_1700_B(buf.readFloat(), 0.0f, 1.0f);
        this.h_1847_R = buf.v_4262_N();
        byte k = buf.readByte();
        this.s_956_w = (k & 1) != 0;
        this.u_2550_I = (k & 2) != 0;
        this.M_588_G = (k & 4) != 0;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
        buf.n_1700_B(this.G_564_y);
        buf.writeByte(this.P_1922_E.getX());
        buf.writeByte(this.P_1922_E.getY());
        buf.writeByte(this.P_1922_E.getZ());
        buf.writeByte(this.u_1723_Y.getX());
        buf.writeByte(this.u_1723_Y.getY());
        buf.writeByte(this.u_1723_Y.getZ());
        buf.n_1700_B(this.v_4262_N);
        buf.n_1700_B(this.w_1484_f);
        buf.n_1700_B(this.t_148_a);
        buf.writeFloat(this.P_4830_p);
        buf.n_1700_B(this.h_1847_R);
        int i = 0;
        if (this.s_956_w) {
            i |= 1;
        }
        if (this.u_2550_I) {
            i |= 2;
        }
        if (this.M_588_G) {
            i |= 4;
        }
        buf.writeByte(i);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public j_2644_e.n_1700_B R_4764_Y() {
        return this.J_1907_R;
    }

    public M_3212_T G_564_y() {
        return this.R_4764_Y;
    }

    public String P_1922_E() {
        return this.G_564_y;
    }

    public c_1514_x u_1723_Y() {
        return this.P_1922_E;
    }

    public c_1514_x v_4262_N() {
        return this.u_1723_Y;
    }

    public q_4099_E w_1484_f() {
        return this.v_4262_N;
    }

    public W_2163_m t_148_a() {
        return this.w_1484_f;
    }

    public String s_956_w() {
        return this.t_148_a;
    }

    public boolean u_2550_I() {
        return this.s_956_w;
    }

    public boolean M_588_G() {
        return this.u_2550_I;
    }

    public boolean P_4830_p() {
        return this.M_588_G;
    }

    public float h_1847_R() {
        return this.P_4830_p;
    }

    public long Q_4569_t() {
        return this.h_1847_R;
    }
}


