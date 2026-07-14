/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  lombok.Generated
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import lightning.product.E_1407_D;
import lightning.product.E_3343_g;
import lightning.product.F_1464_b;
import lightning.product.H_2506_c;
import lightning.product.NumberSetting;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.Q_1187_u;
import lightning.product.Q_2753_H;
import lightning.product.ClientboundSetHealthPacket;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.ServerboundPlayerAbilitiesPacket;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.b_3528_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lightning.product.Packet;
import lightning.product.u_925_K;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class FreeCam
extends Module {
    private final NumberSetting skorostPoXzSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e XZ", 1.0f, 0.1f, 3.0f, 0.1f);
    private final NumberSetting skorostPoYSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e Y", 1.0f, 0.1f, 3.0f, 0.1f);
    private final BooleanSetting uvelichitYarkostEnabled = new BooleanSetting("\u0423\u0432\u0435\u043b\u0438\u0447\u0438\u0442\u044c \u044f\u0440\u043a\u043e\u0441\u0442\u044c", true);
    private final BooleanSetting podmenyatKlikiPoBlokamEnabled = new BooleanSetting("\u041f\u043e\u0434\u043c\u0435\u043d\u044f\u0442\u044c \u043a\u043b\u0438\u043a\u0438 \u043f\u043e \u0431\u043b\u043e\u043a\u0430\u043c", true);
    private final BooleanSetting otobrazhatRaznicuPoziciiEnabled = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0440\u0430\u0437\u043d\u0438\u0446\u0443 \u043f\u043e\u0437\u0438\u0446\u0438\u0438", false);
    private final BooleanSetting otklyuchatPriUroneEnabled = new BooleanSetting("\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u043f\u0440\u0438 \u0443\u0440\u043e\u043d\u0435", false);
    private float h_1847_R;
    private float Q_4569_t;
    private float M_182_A;
    Q_1187_u v_4262_N;
    private double t_1786_h = -1.0;

    public FreeCam() {
        super("FreeCam", ModuleCategory.G_564_y);
        this.addSettings(this.skorostPoXzSetting, this.skorostPoYSetting, this.uvelichitYarkostEnabled, this.podmenyatKlikiPoBlokamEnabled, this.otobrazhatRaznicuPoziciiEnabled, this.otklyuchatPriUroneEnabled);
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u event) {
        if (FreeCam.c_3005_b.Y_259_p == null || !this.otobrazhatRaznicuPoziciiEnabled.isEnabled().booleanValue()) {
            return;
        }
        int xPosition = (int)(FreeCam.c_3005_b.Y_259_p.O_3598_v() - (double)this.h_1847_R);
        int yPosition = (int)(FreeCam.c_3005_b.Y_259_p.X_2960_b() - (double)this.Q_4569_t);
        int zPosition = (int)(FreeCam.c_3005_b.Y_259_p.l_2647_k() - (double)this.M_182_A);
        String pos = "X: " + xPosition + " Y: " + yPosition + " Z: " + zPosition;
        l_3370_o.G_564_y[16].n_1700_B(event.J_1907_R(), pos, (double)((float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f - 28.0f), (double)((float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 20.0f), H_2506_c.n_1700_B(255, 255, 255));
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g event) {
        this.Q_4569_t();
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        Packet<?> packet;
        if (event.R_4764_Y()) {
            packet = event.G_564_y();
            if (packet instanceof N_3268_u || packet instanceof ServerboundPlayerAbilitiesPacket) {
                event.n_1700_B(true);
            }
            if (this.podmenyatKlikiPoBlokamEnabled.isEnabled().booleanValue()) {
                ServerboundPlayerActionPacket digPacket;
                F_1464_b usePacket;
                c_1514_x pos;
                double dist;
                if (packet instanceof F_1464_b && (dist = new e_2866_D(this.h_1847_R, this.Q_4569_t, this.M_182_A).R_4764_Y((double)(pos = (usePacket = (F_1464_b)packet).R_4764_Y().n_1700_B()).getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)) > 36.0) {
                    event.n_1700_B(true);
                }
                if (packet instanceof ServerboundPlayerActionPacket && (dist = new e_2866_D(this.h_1847_R, this.Q_4569_t, this.M_182_A).R_4764_Y((double)(pos = (digPacket = (ServerboundPlayerActionPacket)packet).J_1907_R()).getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)) > 36.0) {
                    event.n_1700_B(true);
                }
            }
        }
        if (event.J_1907_R()) {
            packet = event.G_564_y();
            if (packet instanceof ClientboundPlayerPositionPacket) {
                event.n_1700_B(true);
            }
            if (this.otklyuchatPriUroneEnabled.isEnabled().booleanValue() && packet instanceof ClientboundSetHealthPacket) {
                ClientboundSetHealthPacket healthPacket = (ClientboundSetHealthPacket)packet;
                if (FreeCam.c_3005_b.Y_259_p != null && healthPacket.J_1907_R() < FreeCam.c_3005_b.Y_259_p.g_46_E() && healthPacket.J_1907_R() > 0.0f) {
                    this.Q_4569_t();
                }
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (FreeCam.c_3005_b.Y_259_p == null) {
            this.Q_4569_t();
            return;
        }
        if (FreeCam.c_3005_b.Y_259_p.g_46_E() <= 0.0f || FreeCam.c_3005_b.Y_259_p.O_2151_c > 0 || FreeCam.c_3005_b.Y_1740_V instanceof E_1407_D) {
            this.Q_4569_t();
            return;
        }
        if (!FreeCam.c_3005_b.Y_259_p.q_2307_F() && FreeCam.c_3005_b.P_4830_p.Ping.G_564_y()) {
            FreeCam.c_3005_b.Y_259_p.I_4348_c().R_4764_Y = ((Float)this.skorostPoYSetting.getValue()).floatValue();
        } else if (FreeCam.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
            FreeCam.c_3005_b.Y_259_p.I_4348_c().R_4764_Y = -((Float)this.skorostPoYSetting.getValue()).floatValue();
        } else {
            FreeCam.c_3005_b.Y_259_p.h_1847_R(0.0, 0.0, 0.0);
        }
        u_925_K.n_1700_B((double)((Float)this.skorostPoXzSetting.getValue()).floatValue());
        if (this.uvelichitYarkostEnabled.isEnabled().booleanValue()) {
            FreeCam.c_3005_b.P_4830_p.c_132_F = 100.0;
        }
    }

    private void Q_4569_t() {
        this.R_4764_Y();
    }

    @Override
    public void onEnable() {
        if (FreeCam.c_3005_b.Y_259_p == null || FreeCam.c_3005_b.Y_601_j == null) {
            super.onEnable();
            return;
        }
        this.h_1847_R = (float)FreeCam.c_3005_b.Y_259_p.O_3598_v();
        this.Q_4569_t = (float)FreeCam.c_3005_b.Y_259_p.X_2960_b();
        this.M_182_A = (float)FreeCam.c_3005_b.Y_259_p.l_2647_k();
        this.v_4262_N = new Q_1187_u(FreeCam.c_3005_b.Y_601_j, new GameProfile(UUID.randomUUID(), c_3005_b.z_1737_N().R_4764_Y()));
        this.v_4262_N.l_1268_F = FreeCam.c_3005_b.Y_259_p.l_1268_F;
        this.v_4262_N.t_1786_h(FreeCam.c_3005_b.Y_259_p.g_46_E());
        this.v_4262_N.n_1700_B((double)this.h_1847_R, FreeCam.c_3005_b.Y_259_p.i_601_W().minY, (double)this.M_182_A, FreeCam.c_3005_b.Y_259_p.p_178_J, FreeCam.c_3005_b.Y_259_p.f_4016_n);
        this.v_4262_N.f_3449_S = FreeCam.c_3005_b.Y_259_p.f_3449_S;
        FreeCam.c_3005_b.Y_601_j.n_1700_B(-1337, (N_4263_v)this.v_4262_N);
        FreeCam.c_3005_b.Y_259_p.C_415_h.J_1907_R = true;
        FreeCam.c_3005_b.Y_259_p.j_1564_a = true;
        this.t_1786_h = FreeCam.c_3005_b.P_4830_p.c_132_F;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (FreeCam.c_3005_b.Y_259_p != null && FreeCam.c_3005_b.Y_601_j != null) {
            FreeCam.c_3005_b.Y_259_p.h_1847_R(0.0, 0.0, 0.0);
            FreeCam.c_3005_b.Y_259_p.s_956_w(0.0, 0.0, 0.0);
            if (this.h_1847_R != 0.0f && this.Q_4569_t != 0.0f && this.M_182_A != 0.0f) {
                FreeCam.c_3005_b.Y_259_p.J_1907_R(this.h_1847_R, this.Q_4569_t, this.M_182_A);
            }
            if (this.v_4262_N != null) {
                this.v_4262_N.Ops();
                this.v_4262_N = null;
            }
            FreeCam.c_3005_b.Y_601_j.n_1700_B(-1337);
            FreeCam.c_3005_b.Y_259_p.C_415_h.J_1907_R = false;
            FreeCam.c_3005_b.Y_259_p.j_1564_a = false;
        }
        if (this.t_1786_h >= 0.0) {
            FreeCam.c_3005_b.P_4830_p.c_132_F = this.t_1786_h;
            this.t_1786_h = -1.0;
        }
        super.onDisable();
    }

    @Generated
    public Q_1187_u h_1847_R() {
        return this.v_4262_N;
    }
}



