/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.m_2262_U;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.u_925_K;
import lightning.product.ModuleCategory;

public class Flight
extends Module {
    public final ModeSetting tipMode = new ModeSetting("\u0422\u0438\u043f", "\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435", "\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435", "\u041f\u0440\u044b\u0436\u043a\u0438", "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0435", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "ReallyWorld Dragon");
    public final NumberSetting skorostSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 1.5f, 0.1f, 10.0f, 0.1f, () -> !this.tipMode.isMode("\u041f\u0440\u044b\u0436\u043a\u0438") && !this.tipMode.isMode("ReallyWorld Dragon"));
    public final NumberSetting skorostPoYSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e Y", 1.5f, 0.1f, 10.0f, 0.1f, () -> !this.tipMode.isMode("\u041f\u0440\u044b\u0436\u043a\u0438") && !this.tipMode.isMode("ReallyWorld Dragon"));
    public final BooleanSetting letetVperedEnabled = new BooleanSetting("\u041b\u0435\u0442\u0435\u0442\u044c \u0432\u043f\u0435\u0440\u0435\u0434", false, () -> this.tipMode.isMode("ReallyWorld Dragon"));

    public Flight() {
        super("Flight", ModuleCategory.J_1907_R);
        this.addSettings(this.tipMode, this.skorostSetting, this.skorostPoYSetting, this.letetVperedEnabled);
    }

    @Override
    public void onDisable() {
        if (this.letetVperedEnabled.isEnabled().booleanValue() && this.tipMode.isMode("ReallyWorld Dragon")) {
            Flight.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
        }
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        boolean isSneaking = Flight.c_3005_b.P_4830_p.p_178_J.G_564_y();
        boolean isJumping = Flight.c_3005_b.P_4830_p.Ping.G_564_y();
        float motionSpeed = ((Float)this.skorostPoYSetting.getValue()).floatValue();
        switch ((String)this.tipMode.getValue()) {
            case "\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435": {
                Flight.c_3005_b.Y_259_p.h_1847_R(0.0, -0.005f, 0.0);
                if (isSneaking) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = -motionSpeed;
                } else if (isJumping) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = motionSpeed;
                }
                if (Flight.c_3005_b.Y_259_p.M_1641_O()) {
                    Flight.c_3005_b.Y_259_p.e_837_t();
                }
                u_925_K.n_1700_B((double)((Float)this.skorostSetting.getValue()).floatValue());
                break;
            }
            case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439": {
                Flight.c_3005_b.Y_259_p.h_1847_R(0.0, 0.0, 0.0);
                if (isSneaking) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = -motionSpeed;
                } else if (isJumping) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = motionSpeed;
                }
                if (Flight.c_3005_b.Y_259_p.M_1641_O()) {
                    Flight.c_3005_b.Y_259_p.e_837_t();
                }
                u_925_K.n_1700_B((double)((Float)this.skorostSetting.getValue()).floatValue());
                break;
            }
            case "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0435": {
                if (isSneaking) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = -motionSpeed;
                } else if (isJumping) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = motionSpeed;
                }
                u_925_K.n_1700_B((double)((Float)this.skorostSetting.getValue()).floatValue());
                break;
            }
            case "\u041f\u0440\u044b\u0436\u043a\u0438": {
                if (!isJumping) break;
                Flight.c_3005_b.Y_259_p.e_837_t();
                break;
            }
            case "ReallyWorld Dragon": {
                boolean vertical;
                if (!Flight.c_3005_b.Y_259_p.C_415_h.J_1907_R) break;
                if (this.letetVperedEnabled.isEnabled().booleanValue()) {
                    Flight.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                    Flight.c_3005_b.Y_259_p.G_564_y.moveForward = 1.0f;
                }
                Flight.c_3005_b.Y_259_p.h_1847_R(0.0, 0.0, 0.0);
                boolean moving = u_925_K.n_1700_B();
                boolean bl = vertical = isSneaking || isJumping;
                if (moving) {
                    u_925_K.n_1700_B(vertical ? 0.7 : 1.05);
                }
                if (isSneaking) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = -0.74;
                    break;
                }
                if (isJumping) {
                    Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = 0.74;
                    break;
                }
                if (moving) break;
                Flight.c_3005_b.Y_259_p.Ping.R_4764_Y = 0.0;
                break;
            }
        }
    }
}



