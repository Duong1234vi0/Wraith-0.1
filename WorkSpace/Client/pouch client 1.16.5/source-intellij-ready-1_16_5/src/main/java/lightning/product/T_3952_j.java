/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class T_3952_j
implements Packet<ServerGamePacketListener> {
    public static boolean n_1700_B;
    private int J_1907_R;
    private n_1700_B R_4764_Y;
    private int G_564_y;

    public T_3952_j() {
    }

    public T_3952_j(N_4263_v entityIn, n_1700_B actionIn) {
        this(entityIn, actionIn, 0);
    }

    public T_3952_j(N_4263_v entityIn, n_1700_B actionIn, int auxDataIn) {
        this.J_1907_R = entityIn.j_276_v();
        this.R_4764_Y = actionIn;
        this.G_564_y = auxDataIn;
        if (actionIn == lightning.product.T_3952_j$n_1700_B.G_564_y) {
            n_1700_B = true;
        } else if (actionIn == lightning.product.T_3952_j$n_1700_B.P_1922_E) {
            n_1700_B = false;
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.J_1907_R = buf.u_1723_Y();
        this.R_4764_Y = buf.n_1700_B(n_1700_B.class);
        this.G_564_y = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
        buf.G_564_y(this.G_564_y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public n_1700_B J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        public static final /* enum */ n_1700_B w_1484_f = new n_1700_B();
        public static final /* enum */ n_1700_B t_148_a = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] s_956_w;

        public static n_1700_B[] values() {
            return (n_1700_B[])s_956_w.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a};
        }

        static {
            s_956_w = lightning.product.T_3952_j$n_1700_B.n_1700_B();
        }
    }
}


