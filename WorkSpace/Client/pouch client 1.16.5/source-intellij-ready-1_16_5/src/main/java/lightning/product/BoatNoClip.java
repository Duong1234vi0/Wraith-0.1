/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_1462_f;
import lightning.product.h_1015_G;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class BoatNoClip
extends Module {
    private final NumberSetting skorostVverhSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u0432\u0435\u0440\u0445", 0.4f, 0.05f, 5.0f, 0.05f);
    private final NumberSetting skorostVnizSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u043d\u0438\u0437", 0.4f, 0.05f, 5.0f, 0.05f);
    private final NumberSetting skorostVStoronySetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432 \u0441\u0442\u043e\u0440\u043e\u043d\u044b", 0.6f, 0.05f, 5.0f, 0.05f);

    public BoatNoClip() {
        super("BoatNoClip", ModuleCategory.J_1907_R);
        this.addSettings(this.skorostVverhSetting, this.skorostVnizSetting, this.skorostVStoronySetting);
    }

    @Override
    public void onDisable() {
        if (BoatNoClip.c_3005_b.Y_259_p != null && BoatNoClip.c_3005_b.Y_259_p.l_3609_d() instanceof g_1462_f) {
            g_1462_f boat = (g_1462_f)BoatNoClip.c_3005_b.Y_259_p.l_3609_d();
            boat.j_1564_a = false;
            boat.w_1484_f(false);
        }
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (BoatNoClip.c_3005_b.Y_259_p == null || BoatNoClip.c_3005_b.Y_601_j == null) {
            return;
        }
        N_4263_v entity = BoatNoClip.c_3005_b.Y_259_p.l_3609_d();
        if (!(entity instanceof g_1462_f)) {
            return;
        }
        g_1462_f boat = (g_1462_f)entity;
        boat.j_1564_a = true;
        boat.w_1484_f(true);
        boolean insideBlock = this.h_1847_R();
        double horizontalSpeed = insideBlock ? 0.25 : (double)((Float)this.skorostVStoronySetting.getValue()).floatValue();
        boat.j_276_v = boat.p_178_J = BoatNoClip.c_3005_b.Y_259_p.p_178_J;
        double motionX = 0.0;
        double motionY = 0.0;
        double motionZ = 0.0;
        float yaw = boat.p_178_J;
        double rad = Math.toRadians(yaw);
        if (BoatNoClip.c_3005_b.P_4830_p.Ping.G_564_y()) {
            motionY += (double)((Float)this.skorostVverhSetting.getValue()).floatValue();
        }
        if (BoatNoClip.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
            motionY -= (double)((Float)this.skorostVnizSetting.getValue()).floatValue();
        }
        if (BoatNoClip.c_3005_b.P_4830_p.O_508_d.G_564_y()) {
            motionX -= (double)u_530_F.n_1700_B((float)rad) * horizontalSpeed;
            motionZ += (double)u_530_F.J_1907_R((float)rad) * horizontalSpeed;
        }
        if (BoatNoClip.c_3005_b.P_4830_p.r_715_M.G_564_y()) {
            motionX -= (double)u_530_F.J_1907_R((float)rad) * horizontalSpeed;
            motionZ -= (double)u_530_F.n_1700_B((float)rad) * horizontalSpeed;
        }
        if (BoatNoClip.c_3005_b.P_4830_p.i_1637_u.G_564_y()) {
            motionX += (double)u_530_F.J_1907_R((float)rad) * horizontalSpeed;
            motionZ += (double)u_530_F.n_1700_B((float)rad) * horizontalSpeed;
        }
        boat.v_4262_N(new e_2866_D(motionX, motionY, motionZ));
        if (!BoatNoClip.c_3005_b.Y_259_p.y_2772_m()) {
            BoatNoClip.c_3005_b.Y_259_p.n_1700_B((N_4263_v)boat, true);
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J event) {
        if (BoatNoClip.c_3005_b.Y_259_p == null || BoatNoClip.c_3005_b.Y_601_j == null) {
            return;
        }
        N_4263_v entity = BoatNoClip.c_3005_b.Y_259_p.l_3609_d();
        if (entity instanceof g_1462_f) {
            event.u_1723_Y(false);
        }
    }

    private boolean h_1847_R() {
        if (BoatNoClip.c_3005_b.Y_259_p == null) {
            return false;
        }
        I_4817_s box = BoatNoClip.c_3005_b.Y_259_p.i_601_W().grow(0.001);
        int minX = u_530_F.R_4764_Y(box.minX);
        int minY = u_530_F.R_4764_Y(box.minY);
        int minZ = u_530_F.R_4764_Y(box.minZ);
        int maxX = u_530_F.R_4764_Y(box.maxX);
        int maxY = u_530_F.R_4764_Y(box.maxY);
        int maxZ = u_530_F.R_4764_Y(box.maxZ);
        c_1514_x.n_1700_B mutablePos = new c_1514_x.n_1700_B();
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    mutablePos.n_1700_B(x, y, z);
                    K_4074_S state = BoatNoClip.c_3005_b.Y_601_j.getBlockState(mutablePos);
                    if (!state.M_588_G()) continue;
                    return true;
                }
            }
        }
        return false;
    }
}



