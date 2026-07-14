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

public class Y_2080_q
extends d_2427_y
implements x_607_J {
    private Packet<?> n_1700_B;
    private final n_1700_B J_1907_R;

    public boolean J_1907_R() {
        return this.J_1907_R == lightning.product.Y_2080_q$n_1700_B.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R == lightning.product.Y_2080_q$n_1700_B.J_1907_R;
    }

    @Generated
    public n_1700_B G_564_y() {
        return this.J_1907_R;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Y_2080_q)) {
            return false;
        }
        Y_2080_q other = (Y_2080_q)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        Packet<?> this$packet = this.P_1922_E();
        Packet<?> other$packet = other.P_1922_E();
        if (this$packet == null ? other$packet != null : !this$packet.equals(other$packet)) {
            return false;
        }
        n_1700_B this$type = this.G_564_y();
        n_1700_B other$type = other.G_564_y();
        return !(this$type == null ? other$type != null : !((Object)((Object)this$type)).equals((Object)other$type));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof Y_2080_q;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Packet<?> $packet = this.P_1922_E();
        result = result * 59 + ($packet == null ? 43 : $packet.hashCode());
        n_1700_B $type = this.G_564_y();
        result = result * 59 + ($type == null ? 43 : ((Object)((Object)$type)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventPacketBot(packet=" + String.valueOf(this.P_1922_E()) + ", type=" + String.valueOf((Object)this.G_564_y()) + ")";
    }

    @Generated
    public Y_2080_q(Packet<?> packet, n_1700_B type) {
        this.n_1700_B = packet;
        this.J_1907_R = type;
    }

    @Generated
    public Packet<?> P_1922_E() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(Packet<?> packet) {
        this.n_1700_B = packet;
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
            R_4764_Y = lightning.product.Y_2080_q$n_1700_B.n_1700_B();
        }
    }
}


