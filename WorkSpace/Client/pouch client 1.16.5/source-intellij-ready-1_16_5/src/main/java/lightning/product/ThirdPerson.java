/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import lightning.product.F_1446_q;
import lightning.product.NumberSetting;
import lightning.product.L_1733_J;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.d_2169_p;
import lightning.product.FreeCam;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.l_1268_F;
import lightning.product.ClientBootstrap;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.r_4790_y;
import lightning.product.t_1920_R;
import lightning.product.ModuleCategory;
import org.joml.Vector2f;

public class ThirdPerson
extends Module {
    private ModeSetting rezhimObzoraMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c \u043e\u0431\u0437\u043e\u0440\u0430", "\u0421\u0437\u0430\u0434\u0438", "\u0421\u043f\u0435\u0440\u0435\u0434\u0438", "\u0421\u0437\u0430\u0434\u0438");
    private NumberSetting distanciyaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 4.0f, 1.0f, 10.0f, 0.1f);
    public KeyBindSetting bindKeyBind = new KeyBindSetting("\u0411\u0438\u043d\u0434");
    private t_1920_R s_956_w = t_1920_R.n_1700_B;
    private boolean u_2550_I;
    private boolean M_588_G;
    private Vector2f P_4830_p = new Vector2f(0.0f, 0.0f);

    public ThirdPerson() {
        super("ThirdPerson", ModuleCategory.R_4764_Y);
        this.addSettings(this.rezhimObzoraMode, this.bindKeyBind, this.distanciyaSetting);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(FreeCam.class).w_1484_f()) {
            return;
        }
        if (e.n_1700_B() == ((Integer)this.bindKeyBind.getKey()).intValue()) {
            this.M_588_G = e.J_1907_R();
        }
    }

    @Y_1740_V
    public void n_1700_B(L_1733_J e) {
        if (this.u_2550_I) {
            e.n_1700_B(((Float)this.distanciyaSetting.getValue()).floatValue());
        }
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        if (!this.u_2550_I) {
            this.s_956_w = ThirdPerson.c_3005_b.P_4830_p.P_4830_p();
            this.P_4830_p.x = d_2169_p.J_1907_R();
            this.P_4830_p.y = d_2169_p.R_4764_Y();
        }
        if (this.M_588_G && ThirdPerson.c_3005_b.Y_1740_V == null) {
            r_4790_y.n_1700_B(new F_1446_q(ThirdPerson.c_3005_b.Y_259_p.p_178_J, ThirdPerson.c_3005_b.Y_259_p.f_4016_n), 360.0f, 1, 1);
            if (this.rezhimObzoraMode.isMode("\u0421\u0437\u0430\u0434\u0438")) {
                ThirdPerson.c_3005_b.P_4830_p.n_1700_B(t_1920_R.J_1907_R);
            } else {
                ThirdPerson.c_3005_b.P_4830_p.n_1700_B(t_1920_R.R_4764_Y);
            }
            this.u_2550_I = true;
        } else if (this.u_2550_I) {
            d_2169_p.n_1700_B(this.P_4830_p.x);
            d_2169_p.J_1907_R(this.P_4830_p.y);
            ThirdPerson.c_3005_b.P_4830_p.n_1700_B(this.s_956_w);
            this.u_2550_I = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(l_1268_F e) {
        if (this.M_588_G) {
            e.n_1700_B(true);
        }
    }
}


