/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;
import lombok.Generated;

public class N_3268_u
implements Packet<ServerGamePacketListener> {
    protected double n_1700_B;
    protected double J_1907_R;
    protected double R_4764_Y;
    protected float G_564_y;
    protected float P_1922_E;
    protected boolean u_1723_Y;
    protected boolean v_4262_N;
    protected boolean w_1484_f;

    public N_3268_u() {
    }

    public N_3268_u(boolean onGroundIn) {
        this.u_1723_Y = onGroundIn;
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.u_1723_Y = buf.readUnsignedByte() != 0;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.u_1723_Y ? 1 : 0);
    }

    public double n_1700_B(double defaultValue) {
        return this.v_4262_N ? this.n_1700_B : defaultValue;
    }

    public double J_1907_R(double defaultValue) {
        return this.v_4262_N ? this.J_1907_R : defaultValue;
    }

    public double R_4764_Y(double defaultValue) {
        return this.v_4262_N ? this.R_4764_Y : defaultValue;
    }

    public float n_1700_B(float defaultValue) {
        return this.w_1484_f ? this.G_564_y : defaultValue;
    }

    public float J_1907_R(float defaultValue) {
        return this.w_1484_f ? this.P_1922_E : defaultValue;
    }

    public boolean J_1907_R() {
        return this.u_1723_Y;
    }

    @Generated
    public void R_4764_Y(float yaw) {
        this.G_564_y = yaw;
    }

    @Generated
    public void G_564_y(float pitch) {
        this.P_1922_E = pitch;
    }

    public static class R_4764_Y
    extends N_3268_u {
        public R_4764_Y() {
            this.w_1484_f = true;
        }

        public R_4764_Y(float yawIn, float pitchIn, boolean onGroundIn) {
            this.G_564_y = yawIn;
            this.P_1922_E = pitchIn;
            this.u_1723_Y = onGroundIn;
            this.w_1484_f = true;
        }

        @Override
        public void n_1700_B(b_2585_i buf) throws IOException {
            this.G_564_y = buf.readFloat();
            this.P_1922_E = buf.readFloat();
            super.n_1700_B(buf);
        }

        @Override
        public void J_1907_R(b_2585_i buf) throws IOException {
            buf.writeFloat(this.G_564_y);
            buf.writeFloat(this.P_1922_E);
            super.J_1907_R(buf);
        }
    }

    public static class J_1907_R
    extends N_3268_u {
        public J_1907_R() {
            this.v_4262_N = true;
            this.w_1484_f = true;
        }

        public J_1907_R(double xIn, double yIn, double zIn, float yawIn, float pitchIn, boolean onGroundIn) {
            this.n_1700_B = xIn;
            this.J_1907_R = yIn;
            this.R_4764_Y = zIn;
            this.G_564_y = yawIn;
            this.P_1922_E = pitchIn;
            this.u_1723_Y = onGroundIn;
            this.w_1484_f = true;
            this.v_4262_N = true;
        }

        @Override
        public void n_1700_B(b_2585_i buf) throws IOException {
            this.n_1700_B = buf.readDouble();
            this.J_1907_R = buf.readDouble();
            this.R_4764_Y = buf.readDouble();
            this.G_564_y = buf.readFloat();
            this.P_1922_E = buf.readFloat();
            super.n_1700_B(buf);
        }

        @Override
        public void J_1907_R(b_2585_i buf) throws IOException {
            buf.writeDouble(this.n_1700_B);
            buf.writeDouble(this.J_1907_R);
            buf.writeDouble(this.R_4764_Y);
            buf.writeFloat(this.G_564_y);
            buf.writeFloat(this.P_1922_E);
            super.J_1907_R(buf);
        }
    }

    public static class n_1700_B
    extends N_3268_u {
        public n_1700_B() {
            this.v_4262_N = true;
        }

        public n_1700_B(double xIn, double yIn, double zIn, boolean onGroundIn) {
            this.n_1700_B = xIn;
            this.J_1907_R = yIn;
            this.R_4764_Y = zIn;
            this.u_1723_Y = onGroundIn;
            this.v_4262_N = true;
        }

        @Override
        public void n_1700_B(b_2585_i buf) throws IOException {
            this.n_1700_B = buf.readDouble();
            this.J_1907_R = buf.readDouble();
            this.R_4764_Y = buf.readDouble();
            super.n_1700_B(buf);
        }

        @Override
        public void J_1907_R(b_2585_i buf) throws IOException {
            buf.writeDouble(this.n_1700_B);
            buf.writeDouble(this.J_1907_R);
            buf.writeDouble(this.R_4764_Y);
            super.J_1907_R(buf);
        }
    }
}


