/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.C_1375_J;
import lightning.product.MobEffects;
import lightning.product.Q_2753_H;
import lightning.product.V_4557_X;
import lightning.product.MinecraftAccess;
import lightning.product.ClientboundExplodePacket;
import lightning.product.Packet;
import lightning.product.v_887_r;
import lombok.Generated;

public class W_2770_z
implements MinecraftAccess {
    private final V_4557_X n_1700_B = new V_4557_X();
    private boolean J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private boolean P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;

    public void n_1700_B(Q_2753_H eventPacket) {
        if (W_2770_z.c_3005_b.Y_259_p != null && W_2770_z.c_3005_b.Y_601_j != null) {
            boolean isDamage;
            boolean bl = isDamage = this.R_4764_Y || this.P_1922_E || this.G_564_y || this.u_1723_Y || this.v_4262_N;
            if (!this.s_956_w()) {
                if (eventPacket.G_564_y() instanceof ClientboundExplodePacket) {
                    this.G_564_y = true;
                }
                if (!isDamage) {
                    C_1375_J statusPacket;
                    Packet<?> packet = eventPacket.G_564_y();
                    if (packet instanceof C_1375_J && (statusPacket = (C_1375_J)packet).J_1907_R() == 2 && statusPacket.n_1700_B(W_2770_z.c_3005_b.Y_601_j) == W_2770_z.c_3005_b.Y_259_p) {
                        this.J_1907_R = true;
                    }
                } else if (W_2770_z.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen > 0) {
                    this.J_1907_R = false;
                    this.t_148_a();
                }
            }
        }
    }

    public boolean n_1700_B(long time) {
        if (this.J_1907_R) {
            if (this.n_1700_B.J_1907_R(time)) {
                this.J_1907_R = false;
                this.n_1700_B.n_1700_B();
                return true;
            }
        } else {
            this.n_1700_B.n_1700_B();
        }
        return false;
    }

    public void n_1700_B(v_887_r damageEvent) {
        switch (damageEvent.J_1907_R()) {
            case n_1700_B: {
                this.R_4764_Y = true;
                break;
            }
            case J_1907_R: {
                this.P_1922_E = true;
                break;
            }
            case R_4764_Y: {
                this.u_1723_Y = true;
                break;
            }
            case G_564_y: {
                this.v_4262_N = true;
            }
        }
        this.J_1907_R = false;
    }

    public void n_1700_B() {
        this.J_1907_R = false;
        this.t_148_a();
        this.n_1700_B.n_1700_B();
    }

    private void t_148_a() {
        this.R_4764_Y = false;
        this.G_564_y = false;
        this.P_1922_E = false;
        this.u_1723_Y = false;
        this.v_4262_N = false;
    }

    private boolean s_956_w() {
        if (W_2770_z.c_3005_b.Y_259_p == null) {
            return false;
        }
        return W_2770_z.c_3005_b.Y_259_p.J_1907_R(MobEffects.w_1457_N) || W_2770_z.c_3005_b.Y_259_p.J_1907_R(MobEffects.Y_601_j) || W_2770_z.c_3005_b.Y_259_p.J_1907_R(MobEffects.v_4262_N);
    }

    @Generated
    public V_4557_X J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public boolean R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public boolean P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public boolean u_1723_Y() {
        return this.P_1922_E;
    }

    @Generated
    public boolean v_4262_N() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean w_1484_f() {
        return this.v_4262_N;
    }
}



