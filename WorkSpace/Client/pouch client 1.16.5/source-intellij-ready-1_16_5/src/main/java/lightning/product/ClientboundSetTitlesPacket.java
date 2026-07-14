/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class ClientboundSetTitlesPacket
implements Packet<ClientGamePacketListener> {
    private n_1700_B n_1700_B;
    private x_282_a J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private int P_1922_E;

    public ClientboundSetTitlesPacket() {
    }

    public ClientboundSetTitlesPacket(n_1700_B typeIn, x_282_a messageIn) {
        this(typeIn, messageIn, -1, -1, -1);
    }

    public ClientboundSetTitlesPacket(int fadeInTimeIn, int displayTimeIn, int fadeOutTimeIn) {
        this(lightning.product.ClientboundSetTitlesPacket$n_1700_B.G_564_y, null, fadeInTimeIn, displayTimeIn, fadeOutTimeIn);
    }

    public ClientboundSetTitlesPacket(n_1700_B typeIn, @Nullable x_282_a messageIn, int fadeInTimeIn, int displayTimeIn, int fadeOutTimeIn) {
        this.n_1700_B = typeIn;
        this.J_1907_R = messageIn;
        this.R_4764_Y = fadeInTimeIn;
        this.G_564_y = displayTimeIn;
        this.P_1922_E = fadeOutTimeIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(n_1700_B.class);
        if (this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.n_1700_B || this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.J_1907_R || this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.R_4764_Y) {
            this.J_1907_R = buf.P_1922_E();
        }
        if (this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.G_564_y) {
            this.R_4764_Y = buf.readInt();
            this.G_564_y = buf.readInt();
            this.P_1922_E = buf.readInt();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        if (this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.n_1700_B || this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.J_1907_R || this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.R_4764_Y) {
            buf.n_1700_B(this.J_1907_R);
        }
        if (this.n_1700_B == lightning.product.ClientboundSetTitlesPacket$n_1700_B.G_564_y) {
            buf.writeInt(this.R_4764_Y);
            buf.writeInt(this.G_564_y);
            buf.writeInt(this.P_1922_E);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public n_1700_B J_1907_R() {
        return this.n_1700_B;
    }

    public x_282_a R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public int P_1922_E() {
        return this.G_564_y;
    }

    public int u_1723_Y() {
        return this.P_1922_E;
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
            v_4262_N = lightning.product.ClientboundSetTitlesPacket$n_1700_B.n_1700_B();
        }
    }
}


