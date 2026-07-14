/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.Packet;

public class ServerboundSeenAdvancementsPacket
implements Packet<ServerGamePacketListener> {
    private n_1700_B n_1700_B;
    private g_2336_b J_1907_R;

    public ServerboundSeenAdvancementsPacket() {
    }

    public ServerboundSeenAdvancementsPacket(n_1700_B p_i47595_1_, @Nullable g_2336_b p_i47595_2_) {
        this.n_1700_B = p_i47595_1_;
        this.J_1907_R = p_i47595_2_;
    }

    public static ServerboundSeenAdvancementsPacket n_1700_B(A_2629_w p_194163_0_) {
        return new ServerboundSeenAdvancementsPacket(lightning.product.ServerboundSeenAdvancementsPacket$n_1700_B.n_1700_B, p_194163_0_.w_1484_f());
    }

    public static ServerboundSeenAdvancementsPacket J_1907_R() {
        return new ServerboundSeenAdvancementsPacket(lightning.product.ServerboundSeenAdvancementsPacket$n_1700_B.J_1907_R, null);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(n_1700_B.class);
        if (this.n_1700_B == lightning.product.ServerboundSeenAdvancementsPacket$n_1700_B.n_1700_B) {
            this.J_1907_R = buf.P_4830_p();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        if (this.n_1700_B == lightning.product.ServerboundSeenAdvancementsPacket$n_1700_B.n_1700_B) {
            buf.n_1700_B(this.J_1907_R);
        }
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public n_1700_B R_4764_Y() {
        return this.n_1700_B;
    }

    public g_2336_b G_564_y() {
        return this.J_1907_R;
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
            R_4764_Y = lightning.product.ServerboundSeenAdvancementsPacket$n_1700_B.n_1700_B();
        }
    }
}


