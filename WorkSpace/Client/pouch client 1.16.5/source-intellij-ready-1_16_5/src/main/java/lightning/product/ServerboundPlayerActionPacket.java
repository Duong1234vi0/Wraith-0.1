/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_257_Y;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.Packet;

public class ServerboundPlayerActionPacket
implements Packet<ServerGamePacketListener> {
    private c_1514_x n_1700_B;
    private b_257_Y J_1907_R;
    private n_1700_B R_4764_Y;

    public ServerboundPlayerActionPacket() {
    }

    public ServerboundPlayerActionPacket(n_1700_B actionIn, c_1514_x posIn, b_257_Y facingIn) {
        this.R_4764_Y = actionIn;
        this.n_1700_B = posIn.toImmutable();
        this.J_1907_R = facingIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.R_4764_Y = buf.n_1700_B(n_1700_B.class);
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = b_257_Y.n_1700_B(buf.readUnsignedByte());
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.R_4764_Y);
        buf.n_1700_B(this.n_1700_B);
        buf.writeByte(this.J_1907_R.R_4764_Y());
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public b_257_Y R_4764_Y() {
        return this.J_1907_R;
    }

    public n_1700_B G_564_y() {
        return this.R_4764_Y;
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
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            w_1484_f = lightning.product.ServerboundPlayerActionPacket$n_1700_B.n_1700_B();
        }
    }
}


