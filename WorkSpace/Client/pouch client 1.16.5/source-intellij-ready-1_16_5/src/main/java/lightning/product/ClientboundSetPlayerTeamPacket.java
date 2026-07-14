/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.b_2585_i;
import lightning.product.PlayerTeam;
import lightning.product.o_3050_h;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class ClientboundSetPlayerTeamPacket
implements Packet<ClientGamePacketListener> {
    private String n_1700_B = "";
    private x_282_a J_1907_R = U_2871_b.R_4764_Y;
    private x_282_a R_4764_Y = U_2871_b.R_4764_Y;
    private x_282_a G_564_y = U_2871_b.R_4764_Y;
    private String P_1922_E;
    private String u_1723_Y;
    private D_4024_W v_4262_N;
    private final Collection<String> w_1484_f;
    private int t_148_a;
    private int s_956_w;

    public ClientboundSetPlayerTeamPacket() {
        this.P_1922_E = o_3050_h.J_1907_R.n_1700_B.P_1922_E;
        this.u_1723_Y = o_3050_h.n_1700_B.n_1700_B.P_1922_E;
        this.v_4262_N = D_4024_W.Q_2552_b;
        this.w_1484_f = Lists.newArrayList();
    }

    public ClientboundSetPlayerTeamPacket(PlayerTeam teamIn, int actionIn) {
        this.P_1922_E = o_3050_h.J_1907_R.n_1700_B.P_1922_E;
        this.u_1723_Y = o_3050_h.n_1700_B.n_1700_B.P_1922_E;
        this.v_4262_N = D_4024_W.Q_2552_b;
        this.w_1484_f = Lists.newArrayList();
        this.n_1700_B = teamIn.n_1700_B();
        this.t_148_a = actionIn;
        if (actionIn == 0 || actionIn == 2) {
            this.J_1907_R = teamIn.J_1907_R();
            this.s_956_w = teamIn.M_588_G();
            this.P_1922_E = teamIn.t_148_a().P_1922_E;
            this.u_1723_Y = teamIn.u_2550_I().P_1922_E;
            this.v_4262_N = teamIn.P_4830_p();
            this.R_4764_Y = teamIn.G_564_y();
            this.G_564_y = teamIn.P_1922_E();
        }
        if (actionIn == 0) {
            this.w_1484_f.addAll(teamIn.u_1723_Y());
        }
    }

    public ClientboundSetPlayerTeamPacket(PlayerTeam teamIn, Collection<String> playersIn, int actionIn) {
        this.P_1922_E = o_3050_h.J_1907_R.n_1700_B.P_1922_E;
        this.u_1723_Y = o_3050_h.n_1700_B.n_1700_B.P_1922_E;
        this.v_4262_N = D_4024_W.Q_2552_b;
        this.w_1484_f = Lists.newArrayList();
        if (actionIn != 3 && actionIn != 4) {
            throw new IllegalArgumentException("Method must be join or leave for player constructor");
        }
        if (playersIn == null || playersIn.isEmpty()) {
            throw new IllegalArgumentException("Players cannot be null/empty");
        }
        this.t_148_a = actionIn;
        this.n_1700_B = teamIn.n_1700_B();
        this.w_1484_f.addAll(playersIn);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(16);
        this.t_148_a = buf.readByte();
        if (this.t_148_a == 0 || this.t_148_a == 2) {
            this.J_1907_R = buf.P_1922_E();
            this.s_956_w = buf.readByte();
            this.P_1922_E = buf.P_1922_E(40);
            this.u_1723_Y = buf.P_1922_E(40);
            this.v_4262_N = buf.n_1700_B(D_4024_W.class);
            this.R_4764_Y = buf.P_1922_E();
            this.G_564_y = buf.P_1922_E();
        }
        if (this.t_148_a == 0 || this.t_148_a == 3 || this.t_148_a == 4) {
            int i = buf.u_1723_Y();
            for (int j = 0; j < i; ++j) {
                this.w_1484_f.add(buf.P_1922_E(40));
            }
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeByte(this.t_148_a);
        if (this.t_148_a == 0 || this.t_148_a == 2) {
            buf.n_1700_B(this.J_1907_R);
            buf.writeByte(this.s_956_w);
            buf.n_1700_B(this.P_1922_E);
            buf.n_1700_B(this.u_1723_Y);
            buf.n_1700_B(this.v_4262_N);
            buf.n_1700_B(this.R_4764_Y);
            buf.n_1700_B(this.G_564_y);
        }
        if (this.t_148_a == 0 || this.t_148_a == 3 || this.t_148_a == 4) {
            buf.G_564_y(this.w_1484_f.size());
            for (String s : this.w_1484_f) {
                buf.n_1700_B(s);
            }
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public String J_1907_R() {
        return this.n_1700_B;
    }

    public x_282_a R_4764_Y() {
        return this.J_1907_R;
    }

    public Collection<String> G_564_y() {
        return this.w_1484_f;
    }

    public int P_1922_E() {
        return this.t_148_a;
    }

    public int u_1723_Y() {
        return this.s_956_w;
    }

    public D_4024_W v_4262_N() {
        return this.v_4262_N;
    }

    public String w_1484_f() {
        return this.P_1922_E;
    }

    public String t_148_a() {
        return this.u_1723_Y;
    }

    public x_282_a s_956_w() {
        return this.R_4764_Y;
    }

    public x_282_a u_2550_I() {
        return this.G_564_y;
    }
}


