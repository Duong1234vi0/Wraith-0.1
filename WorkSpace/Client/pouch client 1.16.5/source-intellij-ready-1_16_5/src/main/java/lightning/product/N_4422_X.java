/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.u_530_F;

public class N_4422_X
implements Packet<ClientGamePacketListener> {
    protected int n_1700_B;
    protected short J_1907_R;
    protected short R_4764_Y;
    protected short G_564_y;
    protected byte P_1922_E;
    protected byte u_1723_Y;
    protected boolean v_4262_N;
    protected boolean w_1484_f;
    protected boolean t_148_a;

    public static long n_1700_B(double p_218743_0_) {
        return u_530_F.G_564_y(p_218743_0_ * 4096.0);
    }

    public static double n_1700_B(long p_244299_0_) {
        return (double)p_244299_0_ / 4096.0;
    }

    public e_2866_D n_1700_B(e_2866_D p_244300_1_) {
        double d0 = this.J_1907_R == 0 ? p_244300_1_.J_1907_R : N_4422_X.n_1700_B(N_4422_X.n_1700_B(p_244300_1_.J_1907_R) + (long)this.J_1907_R);
        double d1 = this.R_4764_Y == 0 ? p_244300_1_.R_4764_Y : N_4422_X.n_1700_B(N_4422_X.n_1700_B(p_244300_1_.R_4764_Y) + (long)this.R_4764_Y);
        double d2 = this.G_564_y == 0 ? p_244300_1_.G_564_y : N_4422_X.n_1700_B(N_4422_X.n_1700_B(p_244300_1_.G_564_y) + (long)this.G_564_y);
        return new e_2866_D(d0, d1, d2);
    }

    public static e_2866_D n_1700_B(long x, long y, long z) {
        return new e_2866_D(x, y, z).n_1700_B(2.44140625E-4);
    }

    public N_4422_X() {
    }

    public N_4422_X(int entityIdIn) {
        this.n_1700_B = entityIdIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public String toString() {
        return "Entity_" + super.toString();
    }

    @Nullable
    public N_4263_v n_1700_B(b_4507_u worldIn) {
        return worldIn.J_1907_R(this.n_1700_B);
    }

    public byte J_1907_R() {
        return this.P_1922_E;
    }

    public byte R_4764_Y() {
        return this.u_1723_Y;
    }

    public boolean G_564_y() {
        return this.w_1484_f;
    }

    public boolean P_1922_E() {
        return this.t_148_a;
    }

    public boolean u_1723_Y() {
        return this.v_4262_N;
    }

    public static class R_4764_Y
    extends N_4422_X {
        public R_4764_Y() {
            this.t_148_a = true;
        }

        public R_4764_Y(int entityId, short posX, short posY, short posZ, boolean onGround) {
            super(entityId);
            this.J_1907_R = posX;
            this.R_4764_Y = posY;
            this.G_564_y = posZ;
            this.v_4262_N = onGround;
            this.t_148_a = true;
        }

        @Override
        public void n_1700_B(b_2585_i buf) throws IOException {
            super.n_1700_B(buf);
            this.J_1907_R = buf.readShort();
            this.R_4764_Y = buf.readShort();
            this.G_564_y = buf.readShort();
            this.v_4262_N = buf.readBoolean();
        }

        @Override
        public void J_1907_R(b_2585_i buf) throws IOException {
            super.J_1907_R(buf);
            buf.writeShort(this.J_1907_R);
            buf.writeShort(this.R_4764_Y);
            buf.writeShort(this.G_564_y);
            buf.writeBoolean(this.v_4262_N);
        }
    }

    public static class J_1907_R
    extends N_4422_X {
        public J_1907_R() {
            this.w_1484_f = true;
            this.t_148_a = true;
        }

        public J_1907_R(int entityId, short posX, short posY, short posZ, byte yaw, byte pitch, boolean onGroundIn) {
            super(entityId);
            this.J_1907_R = posX;
            this.R_4764_Y = posY;
            this.G_564_y = posZ;
            this.P_1922_E = yaw;
            this.u_1723_Y = pitch;
            this.v_4262_N = onGroundIn;
            this.w_1484_f = true;
            this.t_148_a = true;
        }

        @Override
        public void n_1700_B(b_2585_i buf) throws IOException {
            super.n_1700_B(buf);
            this.J_1907_R = buf.readShort();
            this.R_4764_Y = buf.readShort();
            this.G_564_y = buf.readShort();
            this.P_1922_E = buf.readByte();
            this.u_1723_Y = buf.readByte();
            this.v_4262_N = buf.readBoolean();
        }

        @Override
        public void J_1907_R(b_2585_i buf) throws IOException {
            super.J_1907_R(buf);
            buf.writeShort(this.J_1907_R);
            buf.writeShort(this.R_4764_Y);
            buf.writeShort(this.G_564_y);
            buf.writeByte(this.P_1922_E);
            buf.writeByte(this.u_1723_Y);
            buf.writeBoolean(this.v_4262_N);
        }
    }

    public static class n_1700_B
    extends N_4422_X {
        public n_1700_B() {
            this.w_1484_f = true;
        }

        public n_1700_B(int entityIdIn, byte yawIn, byte pitchIn, boolean onGroundIn) {
            super(entityIdIn);
            this.P_1922_E = yawIn;
            this.u_1723_Y = pitchIn;
            this.w_1484_f = true;
            this.v_4262_N = onGroundIn;
        }

        @Override
        public void n_1700_B(b_2585_i buf) throws IOException {
            super.n_1700_B(buf);
            this.P_1922_E = buf.readByte();
            this.u_1723_Y = buf.readByte();
            this.v_4262_N = buf.readBoolean();
        }

        @Override
        public void J_1907_R(b_2585_i buf) throws IOException {
            super.J_1907_R(buf);
            buf.writeByte(this.P_1922_E);
            buf.writeByte(this.u_1723_Y);
            buf.writeBoolean(this.v_4262_N);
        }
    }
}


