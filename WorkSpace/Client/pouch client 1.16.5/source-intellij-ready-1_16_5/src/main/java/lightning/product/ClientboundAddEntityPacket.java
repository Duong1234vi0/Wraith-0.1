/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.UUID;
import lightning.product.N_4263_v;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public class ClientboundAddEntityPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private UUID J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private double P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private int s_956_w;
    private t_5_h<?> u_2550_I;
    private int M_588_G;

    public ClientboundAddEntityPacket() {
    }

    public ClientboundAddEntityPacket(int entityId, UUID uuid, double xPos, double yPos, double zPos, float pitch, float yaw, t_5_h<?> entityType, int entityData, e_2866_D speedVector) {
        this.n_1700_B = entityId;
        this.J_1907_R = uuid;
        this.R_4764_Y = xPos;
        this.G_564_y = yPos;
        this.P_1922_E = zPos;
        this.t_148_a = u_530_F.G_564_y(pitch * 256.0f / 360.0f);
        this.s_956_w = u_530_F.G_564_y(yaw * 256.0f / 360.0f);
        this.u_2550_I = entityType;
        this.M_588_G = entityData;
        this.u_1723_Y = (int)(u_530_F.n_1700_B(speedVector.J_1907_R, -3.9, 3.9) * 8000.0);
        this.v_4262_N = (int)(u_530_F.n_1700_B(speedVector.R_4764_Y, -3.9, 3.9) * 8000.0);
        this.w_1484_f = (int)(u_530_F.n_1700_B(speedVector.G_564_y, -3.9, 3.9) * 8000.0);
    }

    public ClientboundAddEntityPacket(N_4263_v entity) {
        this(entity, 0);
    }

    public ClientboundAddEntityPacket(N_4263_v entityIn, int typeIn) {
        this(entityIn.j_276_v(), entityIn.w_2705_t(), entityIn.O_3598_v(), entityIn.X_2960_b(), entityIn.l_2647_k(), entityIn.f_4016_n, entityIn.p_178_J, entityIn.f_4016_n(), typeIn, entityIn.I_4348_c());
    }

    public ClientboundAddEntityPacket(N_4263_v entity, t_5_h<?> entityType, int entityData, c_1514_x pos) {
        this(entity.j_276_v(), entity.w_2705_t(), pos.getX(), pos.getY(), pos.getZ(), entity.f_4016_n, entity.p_178_J, entityType, entityData, entity.I_4348_c());
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.w_1484_f();
        this.u_2550_I = V_3137_a.g_221_o.n_1700_B(buf.u_1723_Y());
        this.R_4764_Y = buf.readDouble();
        this.G_564_y = buf.readDouble();
        this.P_1922_E = buf.readDouble();
        this.t_148_a = buf.readByte();
        this.s_956_w = buf.readByte();
        this.M_588_G = buf.readInt();
        this.u_1723_Y = buf.readShort();
        this.v_4262_N = buf.readShort();
        this.w_1484_f = buf.readShort();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.G_564_y(V_3137_a.g_221_o.n_1700_B(this.u_2550_I));
        buf.writeDouble(this.R_4764_Y);
        buf.writeDouble(this.G_564_y);
        buf.writeDouble(this.P_1922_E);
        buf.writeByte(this.t_148_a);
        buf.writeByte(this.s_956_w);
        buf.writeInt(this.M_588_G);
        buf.writeShort(this.u_1723_Y);
        buf.writeShort(this.v_4262_N);
        buf.writeShort(this.w_1484_f);
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

    public double G_564_y() {
        return this.R_4764_Y;
    }

    public double P_1922_E() {
        return this.G_564_y;
    }

    public double u_1723_Y() {
        return this.P_1922_E;
    }

    public double v_4262_N() {
        return (double)this.u_1723_Y / 8000.0;
    }

    public double w_1484_f() {
        return (double)this.v_4262_N / 8000.0;
    }

    public double t_148_a() {
        return (double)this.w_1484_f / 8000.0;
    }

    public int s_956_w() {
        return this.t_148_a;
    }

    public int u_2550_I() {
        return this.s_956_w;
    }

    public t_5_h<?> M_588_G() {
        return this.u_2550_I;
    }

    public int P_4830_p() {
        return this.M_588_G;
    }
}


