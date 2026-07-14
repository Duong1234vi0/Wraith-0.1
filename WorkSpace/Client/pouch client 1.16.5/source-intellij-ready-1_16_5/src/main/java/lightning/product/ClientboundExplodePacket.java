/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.u_530_F;

public class ClientboundExplodePacket
implements Packet<ClientGamePacketListener> {
    private double n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private float G_564_y;
    private List<c_1514_x> P_1922_E;
    private float u_1723_Y;
    private float v_4262_N;
    private float w_1484_f;

    public ClientboundExplodePacket() {
    }

    public ClientboundExplodePacket(double xIn, double yIn, double zIn, float strengthIn, List<c_1514_x> affectedBlockPositionsIn, e_2866_D motion) {
        this.n_1700_B = xIn;
        this.J_1907_R = yIn;
        this.R_4764_Y = zIn;
        this.G_564_y = strengthIn;
        this.P_1922_E = Lists.newArrayList(affectedBlockPositionsIn);
        if (motion != null) {
            this.u_1723_Y = (float)motion.J_1907_R;
            this.v_4262_N = (float)motion.R_4764_Y;
            this.w_1484_f = (float)motion.G_564_y;
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readFloat();
        this.J_1907_R = buf.readFloat();
        this.R_4764_Y = buf.readFloat();
        this.G_564_y = buf.readFloat();
        int i = buf.readInt();
        this.P_1922_E = Lists.newArrayListWithCapacity((int)i);
        int j = u_530_F.R_4764_Y(this.n_1700_B);
        int k = u_530_F.R_4764_Y(this.J_1907_R);
        int l = u_530_F.R_4764_Y(this.R_4764_Y);
        for (int i1 = 0; i1 < i; ++i1) {
            int j1 = buf.readByte() + j;
            int k1 = buf.readByte() + k;
            int l1 = buf.readByte() + l;
            this.P_1922_E.add(new c_1514_x(j1, k1, l1));
        }
        this.u_1723_Y = buf.readFloat();
        this.v_4262_N = buf.readFloat();
        this.w_1484_f = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeFloat((float)this.n_1700_B);
        buf.writeFloat((float)this.J_1907_R);
        buf.writeFloat((float)this.R_4764_Y);
        buf.writeFloat(this.G_564_y);
        buf.writeInt(this.P_1922_E.size());
        int i = u_530_F.R_4764_Y(this.n_1700_B);
        int j = u_530_F.R_4764_Y(this.J_1907_R);
        int k = u_530_F.R_4764_Y(this.R_4764_Y);
        for (c_1514_x blockpos : this.P_1922_E) {
            int l = blockpos.getX() - i;
            int i1 = blockpos.getY() - j;
            int j1 = blockpos.getZ() - k;
            buf.writeByte(l);
            buf.writeByte(i1);
            buf.writeByte(j1);
        }
        buf.writeFloat(this.u_1723_Y);
        buf.writeFloat(this.v_4262_N);
        buf.writeFloat(this.w_1484_f);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public float J_1907_R() {
        return this.u_1723_Y;
    }

    public float R_4764_Y() {
        return this.v_4262_N;
    }

    public float G_564_y() {
        return this.w_1484_f;
    }

    public double P_1922_E() {
        return this.n_1700_B;
    }

    public double u_1723_Y() {
        return this.J_1907_R;
    }

    public double v_4262_N() {
        return this.R_4764_Y;
    }

    public float w_1484_f() {
        return this.G_564_y;
    }

    public List<c_1514_x> t_148_a() {
        return this.P_1922_E;
    }
}


