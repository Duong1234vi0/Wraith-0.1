/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_747_P;
import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.e_2866_D;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.r_4811_B;
import lightning.product.ModuleCategory;

public class F_3698_k
extends Module {
    public static final double v_4262_N = 5.0;
    public static final ModeSetting w_1484_f = new ModeSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442 \u0446\u0435\u043b\u0438", "\u041e\u0431\u044b\u0447\u043d\u043e", "\u041e\u0431\u044b\u0447\u043d\u043e", "\u0421\u043f\u0435\u0440\u0432\u0430 \u044d\u043b\u0438\u0442\u0440\u044b");
    public static final BooleanSetting t_148_a = new BooleanSetting("\u041f\u0435\u0440\u0435\u0433\u043e\u043d", true);
    public static final BooleanSetting s_956_w = new BooleanSetting("BravoFFA", false, () -> t_148_a.t_148_a());
    public static final NumberSetting u_2550_I = new NumberSetting("\u0421\u0438\u043b\u0430 \u043f\u0440\u0435\u0434\u0438\u043a\u0442\u0430", 3.0f, 1.0f, 5.0f, 0.1f);

    public F_3698_k() {
        super("ElytraTarget", "\u0422\u043e\u043f\u043e\u0432\u044b\u0439 \u043f\u0435\u0440\u0435\u0433\u043e\u043d \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", ModuleCategory.J_1907_R);
        this.n_1700_B(w_1484_f, t_148_a, s_956_w, u_2550_I);
    }

    public boolean h_1847_R() {
        return this.w_1484_f() && t_148_a.t_148_a() != false;
    }

    public boolean n_1700_B(N_4263_v target) {
        r_4811_B livingTarget;
        return this.h_1847_R() && target instanceof r_4811_B && (livingTarget = (r_4811_B)target).k_578_l() && F_747_P.J_1907_R(target) > 5.0;
    }

    public boolean Q_4569_t() {
        return this.w_1484_f() && s_956_w.t_148_a() != false && t_148_a.t_148_a() != false;
    }

    public boolean n_1700_B(r_4811_B target) {
        if (F_3698_k.c_3005_b.Y_259_p == null || target == null) {
            return false;
        }
        e_2866_D motion = F_3698_k.c_3005_b.Y_259_p.I_4348_c();
        e_2866_D horizMot = new e_2866_D(motion.J_1907_R, 0.0, motion.G_564_y);
        if (horizMot.v_4262_N() < 0.0064) {
            return false;
        }
        horizMot = horizMot.G_564_y();
        e_2866_D eye = F_3698_k.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D toTarget = target.s_4990_V().J_1907_R(0.0, (double)target.v_165_F() * 0.5, 0.0).G_564_y(eye);
        e_2866_D horizTo = new e_2866_D(toTarget.J_1907_R, 0.0, toTarget.G_564_y);
        if (horizTo.v_4262_N() < 1.0E-6) {
            return false;
        }
        return horizMot.J_1907_R(horizTo = horizTo.G_564_y()) < -0.1;
    }

    public static boolean n_1700_B(F_3698_k sample) {
        return sample != null && sample.w_1484_f();
    }
}


