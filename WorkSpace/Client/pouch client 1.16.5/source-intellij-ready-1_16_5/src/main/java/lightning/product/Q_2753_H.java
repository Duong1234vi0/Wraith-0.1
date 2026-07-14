/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.Packet;
import lightning.product.x_607_J;
import lombok.Generated;

public class Q_2753_H
extends d_2427_y
implements x_607_J {
    private final Packet<?> n_1700_B;
    private final n_1700_B J_1907_R;

    public boolean J_1907_R() {
        return this.J_1907_R == lightning.product.Q_2753_H$n_1700_B.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R == lightning.product.Q_2753_H$n_1700_B.J_1907_R;
    }

    @Generated
    public Packet<?> G_564_y() {
        return this.n_1700_B;
    }

    @Generated
    public n_1700_B P_1922_E() {
        return this.J_1907_R;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Q_2753_H)) {
            return false;
        }
        Q_2753_H other = (Q_2753_H)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        Packet<?> this$packet = this.G_564_y();
        Packet<?> other$packet = other.G_564_y();
        if (this$packet == null ? other$packet != null : !this$packet.equals(other$packet)) {
            return false;
        }
        n_1700_B this$packetType = this.P_1922_E();
        n_1700_B other$packetType = other.P_1922_E();
        return !(this$packetType == null ? other$packetType != null : !((Object)((Object)this$packetType)).equals((Object)other$packetType));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof Q_2753_H;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Packet<?> $packet = this.G_564_y();
        result = result * 59 + ($packet == null ? 43 : $packet.hashCode());
        n_1700_B $packetType = this.P_1922_E();
        result = result * 59 + ($packetType == null ? 43 : ((Object)((Object)$packetType)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventPacket(packet=" + String.valueOf(this.G_564_y()) + ", packetType=" + String.valueOf((Object)this.P_1922_E()) + ")";
    }

    @Generated
    public Q_2753_H(Packet<?> packet, n_1700_B packetType) {
        this.n_1700_B = packet;
        this.J_1907_R = packetType;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.Q_2753_H$n_1700_B.n_1700_B();
        }
    }
}


