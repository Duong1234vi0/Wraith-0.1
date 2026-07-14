/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.ServerGamePacketListener;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.Packet;
import lightning.product.x_1688_C;

public class ServerboundInteractPacket
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private n_1700_B J_1907_R;
    private e_2866_D R_4764_Y;
    private x_1688_C G_564_y;
    private boolean P_1922_E;

    public ServerboundInteractPacket() {
    }

    public ServerboundInteractPacket(N_4263_v entityIn, boolean p_i46877_2_) {
        this.n_1700_B = entityIn.j_276_v();
        this.J_1907_R = lightning.product.ServerboundInteractPacket$n_1700_B.J_1907_R;
        this.P_1922_E = p_i46877_2_;
    }

    public ServerboundInteractPacket(N_4263_v entityIn, x_1688_C handIn, boolean p_i46878_3_) {
        this.n_1700_B = entityIn.j_276_v();
        this.J_1907_R = lightning.product.ServerboundInteractPacket$n_1700_B.n_1700_B;
        this.G_564_y = handIn;
        this.P_1922_E = p_i46878_3_;
    }

    public ServerboundInteractPacket(N_4263_v entityIn, x_1688_C handIn, e_2866_D hitVecIn, boolean p_i47098_4_) {
        this.n_1700_B = entityIn.j_276_v();
        this.J_1907_R = lightning.product.ServerboundInteractPacket$n_1700_B.R_4764_Y;
        this.G_564_y = handIn;
        this.R_4764_Y = hitVecIn;
        this.P_1922_E = p_i47098_4_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.n_1700_B(n_1700_B.class);
        if (this.J_1907_R == lightning.product.ServerboundInteractPacket$n_1700_B.R_4764_Y) {
            this.R_4764_Y = new e_2866_D(buf.readFloat(), buf.readFloat(), buf.readFloat());
        }
        if (this.J_1907_R == lightning.product.ServerboundInteractPacket$n_1700_B.n_1700_B || this.J_1907_R == lightning.product.ServerboundInteractPacket$n_1700_B.R_4764_Y) {
            this.G_564_y = buf.n_1700_B(x_1688_C.class);
        }
        this.P_1922_E = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        if (this.J_1907_R == lightning.product.ServerboundInteractPacket$n_1700_B.R_4764_Y) {
            buf.writeFloat((float)this.R_4764_Y.J_1907_R);
            buf.writeFloat((float)this.R_4764_Y.R_4764_Y);
            buf.writeFloat((float)this.R_4764_Y.G_564_y);
        }
        if (this.J_1907_R == lightning.product.ServerboundInteractPacket$n_1700_B.n_1700_B || this.J_1907_R == lightning.product.ServerboundInteractPacket$n_1700_B.R_4764_Y) {
            buf.n_1700_B(this.G_564_y);
        }
        buf.writeBoolean(this.P_1922_E);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Nullable
    public N_4263_v n_1700_B(b_4507_u worldIn) {
        return worldIn.J_1907_R(this.n_1700_B);
    }

    public n_1700_B J_1907_R() {
        return this.J_1907_R;
    }

    @Nullable
    public x_1688_C R_4764_Y() {
        return this.G_564_y;
    }

    public e_2866_D G_564_y() {
        return this.R_4764_Y;
    }

    public boolean P_1922_E() {
        return this.P_1922_E;
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
            G_564_y = lightning.product.ServerboundInteractPacket$n_1700_B.n_1700_B();
        }
    }
}


