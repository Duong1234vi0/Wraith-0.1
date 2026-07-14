/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_747_P;
import lightning.product.J_3992_v;
import lightning.product.N_4263_v;
import lightning.product.O_1043_U;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.e_2866_D;
import lightning.product.m_2262_U;
import lightning.product.q_3115_L;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.ModuleCategory;

public class c_892_d
extends Module {
    public ModeSetting v_4262_N = new ModeSetting("\u041c\u043e\u0434", "RW/Holy", "RW/Holy");
    private O_1043_U t_148_a = new O_1043_U();
    int w_1484_f = 0;

    public c_892_d() {
        super("Grim Glide", "module.grimGlide.desc", ModuleCategory.J_1907_R);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U event) {
        float valuePidor;
        if (c_892_d.c_3005_b.Y_259_p == null || c_892_d.c_3005_b.Y_601_j == null || !c_892_d.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        if (this.h_1847_R() || this.Q_4569_t()) {
            return;
        }
        ++this.w_1484_f;
        e_2866_D pos = c_892_d.c_3005_b.Y_259_p.s_4990_V();
        float yaw = c_892_d.c_3005_b.Y_259_p.p_178_J;
        double forward = 0.087;
        double motion = F_747_P.n_1700_B((N_4263_v)c_892_d.c_3005_b.Y_259_p, 1);
        float f = valuePidor = q_3115_L.n_1700_B("reallyworld") ? 40.0f : 90.0f;
        if (motion >= (double)valuePidor) {
            forward = 0.0;
            motion = 0.0;
        }
        double dx = -Math.sin(Math.toRadians(yaw)) * forward;
        double dz = Math.cos(Math.toRadians(yaw)) * forward;
        c_892_d.c_3005_b.Y_259_p.s_956_w(dx * (double)F_747_P.G_564_y(1.1f, 1.21f), c_892_d.c_3005_b.Y_259_p.I_4348_c().R_4764_Y - (double)0.02f, dz * (double)F_747_P.G_564_y(1.1f, 1.21f));
        if (this.t_148_a.u_1723_Y(50L)) {
            c_892_d.c_3005_b.Y_259_p.J_1907_R(pos.n_1700_B() + dx, pos.J_1907_R(), pos.R_4764_Y() + dz);
            this.t_148_a.J_1907_R();
        }
        c_892_d.c_3005_b.Y_259_p.s_956_w(dx * (double)F_747_P.G_564_y(1.1f, 1.21f), c_892_d.c_3005_b.Y_259_p.I_4348_c().R_4764_Y + (double)0.016f, dz * (double)F_747_P.G_564_y(1.1f, 1.21f));
        event.n_1700_B(event.J_1907_R() + dx);
        event.R_4764_Y(event.G_564_y() + dz);
    }

    private boolean h_1847_R() {
        return c_892_d.c_3005_b.Y_259_p.Y_601_j() && c_892_d.c_3005_b.Y_259_p.B_2580_P().J_1907_R() == Items.FenceBlock;
    }

    private boolean Q_4569_t() {
        return !c_892_d.c_3005_b.Y_601_j.n_1700_B(J_3992_v.class, c_892_d.c_3005_b.Y_259_p.i_601_W().grow(2.0), (? super T firework) -> firework.Y_601_j() == c_892_d.c_3005_b.Y_259_p).isEmpty();
    }
}



