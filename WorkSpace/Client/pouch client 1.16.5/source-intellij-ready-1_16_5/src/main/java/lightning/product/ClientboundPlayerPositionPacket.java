/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundPlayerPositionPacket
implements Packet<ClientGamePacketListener> {
    private double R_4764_Y;
    private double G_564_y;
    private double P_1922_E;
    public float n_1700_B;
    public float J_1907_R;
    private Set<n_1700_B> u_1723_Y;
    private int v_4262_N;

    public ClientboundPlayerPositionPacket() {
    }

    public ClientboundPlayerPositionPacket(double xIn, double yIn, double zIn, float yawIn, float pitchIn, Set<n_1700_B> flagsIn, int teleportIdIn) {
        this.R_4764_Y = xIn;
        this.G_564_y = yIn;
        this.P_1922_E = zIn;
        this.n_1700_B = yawIn;
        this.J_1907_R = pitchIn;
        this.u_1723_Y = flagsIn;
        this.v_4262_N = teleportIdIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.R_4764_Y = buf.readDouble();
        this.G_564_y = buf.readDouble();
        this.P_1922_E = buf.readDouble();
        this.n_1700_B = buf.readFloat();
        this.J_1907_R = buf.readFloat();
        this.u_1723_Y = lightning.product.ClientboundPlayerPositionPacket$n_1700_B.n_1700_B(buf.readUnsignedByte());
        this.v_4262_N = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeDouble(this.R_4764_Y);
        buf.writeDouble(this.G_564_y);
        buf.writeDouble(this.P_1922_E);
        buf.writeFloat(this.n_1700_B);
        buf.writeFloat(this.J_1907_R);
        buf.writeByte(lightning.product.ClientboundPlayerPositionPacket$n_1700_B.n_1700_B(this.u_1723_Y));
        buf.G_564_y(this.v_4262_N);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public double J_1907_R() {
        return this.R_4764_Y;
    }

    public double R_4764_Y() {
        return this.G_564_y;
    }

    public double G_564_y() {
        return this.P_1922_E;
    }

    public float P_1922_E() {
        return this.n_1700_B;
    }

    public float u_1723_Y() {
        return this.J_1907_R;
    }

    public int v_4262_N() {
        return this.v_4262_N;
    }

    public Set<n_1700_B> w_1484_f() {
        return this.u_1723_Y;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(0);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(1);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(2);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(3);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(4);
        private final int u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int bitIn) {
            this.u_1723_Y = bitIn;
        }

        private int n_1700_B() {
            return 1 << this.u_1723_Y;
        }

        private boolean J_1907_R(int flags) {
            return (flags & this.n_1700_B()) == this.n_1700_B();
        }

        public static Set<n_1700_B> n_1700_B(int flags) {
            EnumSet<n_1700_B> set = EnumSet.noneOf(n_1700_B.class);
            for (n_1700_B splayerpositionlookpacket$flags : lightning.product.ClientboundPlayerPositionPacket$n_1700_B.values()) {
                if (!splayerpositionlookpacket$flags.J_1907_R(flags)) continue;
                set.add(splayerpositionlookpacket$flags);
            }
            return set;
        }

        public static int n_1700_B(Set<n_1700_B> flags) {
            int i = 0;
            for (n_1700_B splayerpositionlookpacket$flags : flags) {
                i |= splayerpositionlookpacket$flags.n_1700_B();
            }
            return i;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            v_4262_N = lightning.product.ClientboundPlayerPositionPacket$n_1700_B.J_1907_R();
        }
    }
}


