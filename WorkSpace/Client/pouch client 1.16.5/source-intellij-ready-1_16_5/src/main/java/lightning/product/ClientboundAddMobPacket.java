/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.UUID;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.e_2866_D;
import lightning.product.ClientGamePacketListener;
import lightning.product.r_4811_B;
import lightning.product.Packet;
import lightning.product.u_530_F;

public class ClientboundAddMobPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private UUID J_1907_R;
    private int R_4764_Y;
    private double G_564_y;
    private double P_1922_E;
    private double u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private byte s_956_w;
    private byte u_2550_I;
    private byte M_588_G;

    public ClientboundAddMobPacket() {
    }

    public ClientboundAddMobPacket(r_4811_B entityIn) {
        this.n_1700_B = entityIn.j_276_v();
        this.J_1907_R = entityIn.w_2705_t();
        this.R_4764_Y = V_3137_a.g_221_o.n_1700_B(entityIn.f_4016_n());
        this.G_564_y = entityIn.O_3598_v();
        this.P_1922_E = entityIn.X_2960_b();
        this.u_1723_Y = entityIn.l_2647_k();
        this.s_956_w = (byte)(entityIn.p_178_J * 256.0f / 360.0f);
        this.u_2550_I = (byte)(entityIn.f_4016_n * 256.0f / 360.0f);
        this.M_588_G = (byte)(entityIn.f_3449_S * 256.0f / 360.0f);
        double d0 = 3.9;
        e_2866_D vector3d = entityIn.I_4348_c();
        double d1 = u_530_F.n_1700_B(vector3d.J_1907_R, -3.9, 3.9);
        double d2 = u_530_F.n_1700_B(vector3d.R_4764_Y, -3.9, 3.9);
        double d3 = u_530_F.n_1700_B(vector3d.G_564_y, -3.9, 3.9);
        this.v_4262_N = (int)(d1 * 8000.0);
        this.w_1484_f = (int)(d2 * 8000.0);
        this.t_148_a = (int)(d3 * 8000.0);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.w_1484_f();
        this.R_4764_Y = buf.u_1723_Y();
        this.G_564_y = buf.readDouble();
        this.P_1922_E = buf.readDouble();
        this.u_1723_Y = buf.readDouble();
        this.s_956_w = buf.readByte();
        this.u_2550_I = buf.readByte();
        this.M_588_G = buf.readByte();
        this.v_4262_N = buf.readShort();
        this.w_1484_f = buf.readShort();
        this.t_148_a = buf.readShort();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.G_564_y(this.R_4764_Y);
        buf.writeDouble(this.G_564_y);
        buf.writeDouble(this.P_1922_E);
        buf.writeDouble(this.u_1723_Y);
        buf.writeByte(this.s_956_w);
        buf.writeByte(this.u_2550_I);
        buf.writeByte(this.M_588_G);
        buf.writeShort(this.v_4262_N);
        buf.writeShort(this.w_1484_f);
        buf.writeShort(this.t_148_a);
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

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public double P_1922_E() {
        return this.G_564_y;
    }

    public double u_1723_Y() {
        return this.P_1922_E;
    }

    public double v_4262_N() {
        return this.u_1723_Y;
    }

    public int w_1484_f() {
        return this.v_4262_N;
    }

    public int t_148_a() {
        return this.w_1484_f;
    }

    public int s_956_w() {
        return this.t_148_a;
    }

    public byte u_2550_I() {
        return this.s_956_w;
    }

    public byte M_588_G() {
        return this.u_2550_I;
    }

    public byte P_4830_p() {
        return this.M_588_G;
    }
}


