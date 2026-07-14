/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.U_2871_b;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.r_4811_B;
import lightning.product.Packet;
import lightning.product.x_282_a;
import lightning.product.x_937_q;

public class ClientboundPlayerCombatPacket
implements Packet<ClientGamePacketListener> {
    public n_1700_B n_1700_B;
    public int J_1907_R;
    public int R_4764_Y;
    public int G_564_y;
    public x_282_a P_1922_E;

    public ClientboundPlayerCombatPacket() {
    }

    public ClientboundPlayerCombatPacket(x_937_q tracker, n_1700_B eventIn) {
        this(tracker, eventIn, U_2871_b.R_4764_Y);
    }

    public ClientboundPlayerCombatPacket(x_937_q p_i49825_1_, n_1700_B p_i49825_2_, x_282_a p_i49825_3_) {
        this.n_1700_B = p_i49825_2_;
        r_4811_B livingentity = p_i49825_1_.R_4764_Y();
        switch (p_i49825_2_.ordinal()) {
            case 1: {
                this.G_564_y = p_i49825_1_.G_564_y();
                this.R_4764_Y = livingentity == null ? -1 : livingentity.j_276_v();
                break;
            }
            case 2: {
                this.J_1907_R = p_i49825_1_.u_1723_Y().j_276_v();
                this.R_4764_Y = livingentity == null ? -1 : livingentity.j_276_v();
                this.P_1922_E = p_i49825_3_;
            }
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(n_1700_B.class);
        if (this.n_1700_B == lightning.product.ClientboundPlayerCombatPacket$n_1700_B.J_1907_R) {
            this.G_564_y = buf.u_1723_Y();
            this.R_4764_Y = buf.readInt();
        } else if (this.n_1700_B == lightning.product.ClientboundPlayerCombatPacket$n_1700_B.R_4764_Y) {
            this.J_1907_R = buf.u_1723_Y();
            this.R_4764_Y = buf.readInt();
            this.P_1922_E = buf.P_1922_E();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        if (this.n_1700_B == lightning.product.ClientboundPlayerCombatPacket$n_1700_B.J_1907_R) {
            buf.G_564_y(this.G_564_y);
            buf.writeInt(this.R_4764_Y);
        } else if (this.n_1700_B == lightning.product.ClientboundPlayerCombatPacket$n_1700_B.R_4764_Y) {
            buf.G_564_y(this.J_1907_R);
            buf.writeInt(this.R_4764_Y);
            buf.n_1700_B(this.P_1922_E);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B == lightning.product.ClientboundPlayerCombatPacket$n_1700_B.R_4764_Y;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.ClientboundPlayerCombatPacket$n_1700_B.n_1700_B();
        }
    }
}


