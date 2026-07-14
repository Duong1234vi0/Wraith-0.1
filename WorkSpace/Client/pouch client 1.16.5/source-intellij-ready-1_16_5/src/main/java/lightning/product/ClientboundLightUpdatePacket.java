/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import lightning.product.K_4719_o;
import lightning.product.R_1900_x;
import lightning.product.DataLayer;
import lightning.product.Y_1387_d;
import lightning.product.b_2585_i;
import lightning.product.SectionPos;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundLightUpdatePacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private int u_1723_Y;
    private List<byte[]> v_4262_N;
    private List<byte[]> w_1484_f;
    private boolean t_148_a;

    public ClientboundLightUpdatePacket() {
    }

    public ClientboundLightUpdatePacket(Y_1387_d pos, R_1900_x lightManager, boolean p_i50774_3_) {
        this.n_1700_B = pos.J_1907_R;
        this.J_1907_R = pos.R_4764_Y;
        this.t_148_a = p_i50774_3_;
        this.v_4262_N = Lists.newArrayList();
        this.w_1484_f = Lists.newArrayList();
        for (int i = 0; i < 18; ++i) {
            DataLayer nibblearray = lightManager.n_1700_B(K_4719_o.n_1700_B).n_1700_B(SectionPos.n_1700_B(pos, -1 + i));
            DataLayer nibblearray1 = lightManager.n_1700_B(K_4719_o.J_1907_R).n_1700_B(SectionPos.n_1700_B(pos, -1 + i));
            if (nibblearray != null) {
                if (nibblearray.R_4764_Y()) {
                    this.P_1922_E |= 1 << i;
                } else {
                    this.R_4764_Y |= 1 << i;
                    this.v_4262_N.add((byte[])nibblearray.n_1700_B().clone());
                }
            }
            if (nibblearray1 == null) continue;
            if (nibblearray1.R_4764_Y()) {
                this.u_1723_Y |= 1 << i;
                continue;
            }
            this.G_564_y |= 1 << i;
            this.w_1484_f.add((byte[])nibblearray1.n_1700_B().clone());
        }
    }

    public ClientboundLightUpdatePacket(Y_1387_d pos, R_1900_x lightManager, int skyLightUpdateMaskIn, int blockLightUpdateMaskIn, boolean p_i50775_5_) {
        this.n_1700_B = pos.J_1907_R;
        this.J_1907_R = pos.R_4764_Y;
        this.t_148_a = p_i50775_5_;
        this.R_4764_Y = skyLightUpdateMaskIn;
        this.G_564_y = blockLightUpdateMaskIn;
        this.v_4262_N = Lists.newArrayList();
        this.w_1484_f = Lists.newArrayList();
        for (int i = 0; i < 18; ++i) {
            if ((this.R_4764_Y & 1 << i) != 0) {
                DataLayer nibblearray = lightManager.n_1700_B(K_4719_o.n_1700_B).n_1700_B(SectionPos.n_1700_B(pos, -1 + i));
                if (nibblearray != null && !nibblearray.R_4764_Y()) {
                    this.v_4262_N.add((byte[])nibblearray.n_1700_B().clone());
                } else {
                    this.R_4764_Y &= ~(1 << i);
                    if (nibblearray != null) {
                        this.P_1922_E |= 1 << i;
                    }
                }
            }
            if ((this.G_564_y & 1 << i) == 0) continue;
            DataLayer nibblearray1 = lightManager.n_1700_B(K_4719_o.J_1907_R).n_1700_B(SectionPos.n_1700_B(pos, -1 + i));
            if (nibblearray1 != null && !nibblearray1.R_4764_Y()) {
                this.w_1484_f.add((byte[])nibblearray1.n_1700_B().clone());
                continue;
            }
            this.G_564_y &= ~(1 << i);
            if (nibblearray1 == null) continue;
            this.u_1723_Y |= 1 << i;
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.u_1723_Y();
        this.t_148_a = buf.readBoolean();
        this.R_4764_Y = buf.u_1723_Y();
        this.G_564_y = buf.u_1723_Y();
        this.P_1922_E = buf.u_1723_Y();
        this.u_1723_Y = buf.u_1723_Y();
        this.v_4262_N = Lists.newArrayList();
        for (int i = 0; i < 18; ++i) {
            if ((this.R_4764_Y & 1 << i) == 0) continue;
            this.v_4262_N.add(buf.J_1907_R(2048));
        }
        this.w_1484_f = Lists.newArrayList();
        for (int j = 0; j < 18; ++j) {
            if ((this.G_564_y & 1 << j) == 0) continue;
            this.w_1484_f.add(buf.J_1907_R(2048));
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.G_564_y(this.J_1907_R);
        buf.writeBoolean(this.t_148_a);
        buf.G_564_y(this.R_4764_Y);
        buf.G_564_y(this.G_564_y);
        buf.G_564_y(this.P_1922_E);
        buf.G_564_y(this.u_1723_Y);
        for (byte[] abyte : this.v_4262_N) {
            buf.n_1700_B(abyte);
        }
        for (byte[] abyte1 : this.w_1484_f) {
            buf.n_1700_B(abyte1);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public int P_1922_E() {
        return this.P_1922_E;
    }

    public List<byte[]> u_1723_Y() {
        return this.v_4262_N;
    }

    public int v_4262_N() {
        return this.G_564_y;
    }

    public int w_1484_f() {
        return this.u_1723_Y;
    }

    public List<byte[]> t_148_a() {
        return this.w_1484_f;
    }

    public boolean s_956_w() {
        return this.t_148_a;
    }
}


