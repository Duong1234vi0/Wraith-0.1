/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.F_1446_q;
import lightning.product.F_1464_b;
import lightning.product.BlockHitResult;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.i_2154_H;
import lightning.product.i_4434_b;
import lightning.product.KeyBindSetting;
import lightning.product.r_4790_y;
import lightning.product.s_3081_t;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class EcSaver
extends Module {
    private final KeyBindSetting otkrytEcKeyBind = new KeyBindSetting("\u041e\u0442\u043a\u0440\u044b\u0442\u044c EC");
    private c_1514_x w_1484_f;

    public EcSaver() {
        super("EcSaver", ModuleCategory.P_1922_E);
        this.addSettings(this.otkrytEcKeyBind);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b event) {
        if (event.n_1700_B() == ((Integer)this.otkrytEcKeyBind.getKey()).intValue() && !event.J_1907_R()) {
            this.w_1484_f = this.h_1847_R();
            if (this.w_1484_f != null) {
                v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.u_2550_I) + "\u041d\u0430\u0439\u0434\u0435\u043d \u044d\u043d\u0434\u0435\u0440-\u0441\u0443\u043d\u0434\u0443\u043a \u043d\u0430 \u043f\u043e\u0437\u0438\u0446\u0438\u0438: " + this.w_1484_f.getX() + ", " + this.w_1484_f.getY() + ", " + this.w_1484_f.getZ(), new Object[0]);
            } else {
                v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.P_4830_p) + "\u042d\u043d\u0434\u0435\u0440-\u0441\u0443\u043d\u0434\u0443\u043a \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!", new Object[0]);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (this.w_1484_f != null && EcSaver.c_3005_b.Y_259_p != null && EcSaver.c_3005_b.Y_601_j != null) {
            e_2866_D eyePos = EcSaver.c_3005_b.Y_259_p.u_2550_I(1.0f);
            e_2866_D target = new e_2866_D((double)this.w_1484_f.getX() + 0.5, (double)this.w_1484_f.getY() + 0.5, (double)this.w_1484_f.getZ() + 0.5);
            e_2866_D dir = target.G_564_y(eyePos);
            double dist = Math.sqrt(dir.J_1907_R * dir.J_1907_R + dir.G_564_y * dir.G_564_y);
            float yaw = (float)(Math.toDegrees(Math.atan2(dir.G_564_y, dir.J_1907_R)) - 90.0);
            float pitch = (float)(-Math.toDegrees(Math.atan2(dir.R_4764_Y, dist)));
            r_4790_y.n_1700_B(new F_1446_q(yaw, pitch), 180.0f, 1, 5);
            if (c_3005_b.k_2293_S() != null) {
                c_3005_b.k_2293_S().n_1700_B(new F_1464_b(x_1688_C.n_1700_B, new BlockHitResult(target, b_257_Y.J_1907_R, this.w_1484_f, false)));
            }
            this.w_1484_f = null;
        }
    }

    private c_1514_x h_1847_R() {
        if (EcSaver.c_3005_b.Y_601_j == null || EcSaver.c_3005_b.Y_259_p == null) {
            return null;
        }
        c_1514_x nearest = null;
        double minDist = Double.MAX_VALUE;
        for (i_2154_H te : EcSaver.c_3005_b.Y_601_j.t_148_a) {
            double d;
            if (!(te instanceof s_3081_t) || !((d = EcSaver.c_3005_b.Y_259_p.v_4262_N(te.x_607_J().getX(), te.x_607_J().getY(), te.x_607_J().getZ())) < minDist)) continue;
            minDist = d;
            nearest = te.x_607_J();
        }
        return nearest;
    }
}



