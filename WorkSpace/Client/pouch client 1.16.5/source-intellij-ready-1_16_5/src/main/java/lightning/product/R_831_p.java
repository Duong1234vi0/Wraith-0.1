/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.T_603_v;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class R_831_p
implements Packet<ClientGamePacketListener> {
    private n_1700_B n_1700_B;
    private int J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private double P_1922_E;
    private double u_1723_Y;
    private long v_4262_N;
    private int w_1484_f;
    private int t_148_a;

    public R_831_p() {
    }

    public R_831_p(T_603_v border, n_1700_B actionIn) {
        this.n_1700_B = actionIn;
        this.R_4764_Y = border.n_1700_B();
        this.G_564_y = border.J_1907_R();
        this.u_1723_Y = border.t_148_a();
        this.P_1922_E = border.u_2550_I();
        this.v_4262_N = border.s_956_w();
        this.J_1907_R = border.P_4830_p();
        this.t_148_a = border.multiplayerClientSuggestionProvider();
        this.w_1484_f = border.t_1786_h();
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(n_1700_B.class);
        switch (this.n_1700_B.ordinal()) {
            case 0: {
                this.P_1922_E = buf.readDouble();
                break;
            }
            case 1: {
                this.u_1723_Y = buf.readDouble();
                this.P_1922_E = buf.readDouble();
                this.v_4262_N = buf.v_4262_N();
                break;
            }
            case 2: {
                this.R_4764_Y = buf.readDouble();
                this.G_564_y = buf.readDouble();
                break;
            }
            case 5: {
                this.t_148_a = buf.u_1723_Y();
                break;
            }
            case 4: {
                this.w_1484_f = buf.u_1723_Y();
                break;
            }
            case 3: {
                this.R_4764_Y = buf.readDouble();
                this.G_564_y = buf.readDouble();
                this.u_1723_Y = buf.readDouble();
                this.P_1922_E = buf.readDouble();
                this.v_4262_N = buf.v_4262_N();
                this.J_1907_R = buf.u_1723_Y();
                this.t_148_a = buf.u_1723_Y();
                this.w_1484_f = buf.u_1723_Y();
            }
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        switch (this.n_1700_B.ordinal()) {
            case 0: {
                buf.writeDouble(this.P_1922_E);
                break;
            }
            case 1: {
                buf.writeDouble(this.u_1723_Y);
                buf.writeDouble(this.P_1922_E);
                buf.n_1700_B(this.v_4262_N);
                break;
            }
            case 2: {
                buf.writeDouble(this.R_4764_Y);
                buf.writeDouble(this.G_564_y);
                break;
            }
            case 5: {
                buf.G_564_y(this.t_148_a);
                break;
            }
            case 4: {
                buf.G_564_y(this.w_1484_f);
                break;
            }
            case 3: {
                buf.writeDouble(this.R_4764_Y);
                buf.writeDouble(this.G_564_y);
                buf.writeDouble(this.u_1723_Y);
                buf.writeDouble(this.P_1922_E);
                buf.n_1700_B(this.v_4262_N);
                buf.G_564_y(this.J_1907_R);
                buf.G_564_y(this.t_148_a);
                buf.G_564_y(this.w_1484_f);
            }
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(T_603_v border) {
        switch (this.n_1700_B.ordinal()) {
            case 0: {
                border.n_1700_B(this.P_1922_E);
                break;
            }
            case 1: {
                border.n_1700_B(this.u_1723_Y, this.P_1922_E, this.v_4262_N);
                break;
            }
            case 2: {
                border.J_1907_R(this.R_4764_Y, this.G_564_y);
                break;
            }
            case 5: {
                border.R_4764_Y(this.t_148_a);
                break;
            }
            case 4: {
                border.J_1907_R(this.w_1484_f);
                break;
            }
            case 3: {
                border.J_1907_R(this.R_4764_Y, this.G_564_y);
                if (this.v_4262_N > 0L) {
                    border.n_1700_B(this.u_1723_Y, this.P_1922_E, this.v_4262_N);
                } else {
                    border.n_1700_B(this.P_1922_E);
                }
                border.n_1700_B(this.J_1907_R);
                border.R_4764_Y(this.t_148_a);
                border.J_1907_R(this.w_1484_f);
            }
        }
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
            v_4262_N = lightning.product.R_831_p$n_1700_B.n_1700_B();
        }
    }
}


