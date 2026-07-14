/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.JigsawBlockEntity;
import lightning.product.Packet;

public class ServerboundSetJigsawBlockPacket
implements Packet<ServerGamePacketListener> {
    private c_1514_x n_1700_B;
    private g_2336_b J_1907_R;
    private g_2336_b R_4764_Y;
    private g_2336_b G_564_y;
    private String P_1922_E;
    private JigsawBlockEntity.n_1700_B u_1723_Y;

    public ServerboundSetJigsawBlockPacket() {
    }

    public ServerboundSetJigsawBlockPacket(c_1514_x p_i232584_1_, g_2336_b p_i232584_2_, g_2336_b p_i232584_3_, g_2336_b p_i232584_4_, String p_i232584_5_, JigsawBlockEntity.n_1700_B p_i232584_6_) {
        this.n_1700_B = p_i232584_1_;
        this.J_1907_R = p_i232584_2_;
        this.R_4764_Y = p_i232584_3_;
        this.G_564_y = p_i232584_4_;
        this.P_1922_E = p_i232584_5_;
        this.u_1723_Y = p_i232584_6_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = buf.P_4830_p();
        this.R_4764_Y = buf.P_4830_p();
        this.G_564_y = buf.P_4830_p();
        this.P_1922_E = buf.P_1922_E(Short.MAX_VALUE);
        this.u_1723_Y = JigsawBlockEntity.n_1700_B.n_1700_B(buf.P_1922_E(Short.MAX_VALUE)).orElse(JigsawBlockEntity.n_1700_B.J_1907_R);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
        buf.n_1700_B(this.G_564_y);
        buf.n_1700_B(this.P_1922_E);
        buf.n_1700_B(this.u_1723_Y.n_1700_B());
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public g_2336_b R_4764_Y() {
        return this.J_1907_R;
    }

    public g_2336_b G_564_y() {
        return this.R_4764_Y;
    }

    public g_2336_b P_1922_E() {
        return this.G_564_y;
    }

    public String u_1723_Y() {
        return this.P_1922_E;
    }

    public JigsawBlockEntity.n_1700_B v_4262_N() {
        return this.u_1723_Y;
    }
}


