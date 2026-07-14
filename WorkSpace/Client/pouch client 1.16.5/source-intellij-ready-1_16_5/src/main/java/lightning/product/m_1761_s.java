/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.UUID;
import lightning.product.b_2585_i;
import lightning.product.BossEvent;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class m_1761_s
implements Packet<ClientGamePacketListener> {
    private UUID n_1700_B;
    private n_1700_B J_1907_R;
    private x_282_a R_4764_Y;
    private float G_564_y;
    private BossEvent.n_1700_B P_1922_E;
    private BossEvent.J_1907_R u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private boolean t_148_a;

    public m_1761_s() {
    }

    public m_1761_s(n_1700_B operationIn, BossEvent data) {
        this.J_1907_R = operationIn;
        this.n_1700_B = data.w_1484_f();
        this.R_4764_Y = data.t_148_a();
        this.G_564_y = data.n_1700_B();
        this.P_1922_E = data.s_956_w();
        this.u_1723_Y = data.u_2550_I();
        this.v_4262_N = data.M_588_G();
        this.w_1484_f = data.P_4830_p();
        this.t_148_a = data.h_1847_R();
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.w_1484_f();
        this.J_1907_R = buf.n_1700_B(n_1700_B.class);
        switch (this.J_1907_R.ordinal()) {
            case 0: {
                this.R_4764_Y = buf.P_1922_E();
                this.G_564_y = buf.readFloat();
                this.P_1922_E = buf.n_1700_B(BossEvent.n_1700_B.class);
                this.u_1723_Y = buf.n_1700_B(BossEvent.J_1907_R.class);
                this.n_1700_B(buf.readUnsignedByte());
            }
            default: {
                break;
            }
            case 2: {
                this.G_564_y = buf.readFloat();
                break;
            }
            case 3: {
                this.R_4764_Y = buf.P_1922_E();
                break;
            }
            case 4: {
                this.P_1922_E = buf.n_1700_B(BossEvent.n_1700_B.class);
                this.u_1723_Y = buf.n_1700_B(BossEvent.J_1907_R.class);
                break;
            }
            case 5: {
                this.n_1700_B(buf.readUnsignedByte());
            }
        }
    }

    @Override
    private void n_1700_B(int flags) {
        this.v_4262_N = (flags & 1) > 0;
        this.w_1484_f = (flags & 2) > 0;
        this.t_148_a = (flags & 4) > 0;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        switch (this.J_1907_R.ordinal()) {
            case 0: {
                buf.n_1700_B(this.R_4764_Y);
                buf.writeFloat(this.G_564_y);
                buf.n_1700_B(this.P_1922_E);
                buf.n_1700_B(this.u_1723_Y);
                buf.writeByte(this.u_2550_I());
            }
            default: {
                break;
            }
            case 2: {
                buf.writeFloat(this.G_564_y);
                break;
            }
            case 3: {
                buf.n_1700_B(this.R_4764_Y);
                break;
            }
            case 4: {
                buf.n_1700_B(this.P_1922_E);
                buf.n_1700_B(this.u_1723_Y);
                break;
            }
            case 5: {
                buf.writeByte(this.u_2550_I());
            }
        }
    }

    private int u_2550_I() {
        int i = 0;
        if (this.v_4262_N) {
            i |= 1;
        }
        if (this.w_1484_f) {
            i |= 2;
        }
        if (this.t_148_a) {
            i |= 4;
        }
        return i;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public UUID J_1907_R() {
        return this.n_1700_B;
    }

    public n_1700_B R_4764_Y() {
        return this.J_1907_R;
    }

    public x_282_a G_564_y() {
        return this.R_4764_Y;
    }

    public float P_1922_E() {
        return this.G_564_y;
    }

    public BossEvent.n_1700_B u_1723_Y() {
        return this.P_1922_E;
    }

    public BossEvent.J_1907_R v_4262_N() {
        return this.u_1723_Y;
    }

    public boolean w_1484_f() {
        return this.v_4262_N;
    }

    public boolean t_148_a() {
        return this.w_1484_f;
    }

    public boolean s_956_w() {
        return this.t_148_a;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            v_4262_N = lightning.product.m_1761_s$n_1700_B.n_1700_B();
        }
    }
}


