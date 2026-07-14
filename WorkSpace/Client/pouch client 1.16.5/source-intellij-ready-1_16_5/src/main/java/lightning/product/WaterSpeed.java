/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.c_1514_x;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.Material;
import lightning.product.u_925_K;
import lightning.product.ModuleCategory;

public class WaterSpeed
extends Module {
    public ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Legit", "Legit", "Motion", "Grim", "PolarBack");
    private final NumberSetting skorostSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.5f, 0.1f, 2.0f, 0.05f, () -> this.rezhimMode.isMode("Motion"));
    private final BooleanSetting avtoProbelShiftEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u043f\u0440\u043e\u0431\u0435\u043b/\u0448\u0438\u0444\u0442", false, () -> this.rezhimMode.isMode("Legit"));
    private final BooleanSetting neVsplyvatEnabled = new BooleanSetting("\u041d\u0435 \u0432\u0441\u043f\u043b\u044b\u0432\u0430\u0442\u044c", false, () -> this.avtoProbelShiftEnabled.isEnabled());
    private int u_2550_I = 0;
    private boolean M_588_G = false;

    public WaterSpeed() {
        super("WaterSpeed", ModuleCategory.J_1907_R);
        this.addSettings(this.rezhimMode, this.skorostSetting, this.avtoProbelShiftEnabled, this.neVsplyvatEnabled);
    }

    @Override
    public void onDisable() {
        this.u_2550_I = 0;
        if (WaterSpeed.c_3005_b.Y_259_p != null) {
            WaterSpeed.c_3005_b.P_4830_p.Ping.n_1700_B(false);
            WaterSpeed.c_3005_b.P_4830_p.p_178_J.n_1700_B(false);
        }
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (WaterSpeed.c_3005_b.Y_259_p == null || WaterSpeed.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.rezhimMode.isMode("PolarBack")) {
            this.Y_601_j();
            return;
        }
        this.M_182_A();
        if (this.rezhimMode.isMode("Legit") && this.avtoProbelShiftEnabled.isEnabled().booleanValue()) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        boolean nearSurface;
        if (!WaterSpeed.c_3005_b.Y_259_p.RowButton()) {
            if (this.M_588_G) {
                WaterSpeed.c_3005_b.P_4830_p.Ping.n_1700_B(WaterSpeed.c_3005_b.P_4830_p.Ping.G_564_y());
                WaterSpeed.c_3005_b.P_4830_p.p_178_J.n_1700_B(WaterSpeed.c_3005_b.P_4830_p.p_178_J.G_564_y());
                this.M_588_G = false;
            }
            this.u_2550_I = 0;
            return;
        }
        this.M_588_G = true;
        ++this.u_2550_I;
        boolean bl = nearSurface = this.neVsplyvatEnabled.isEnabled() != false && this.Q_4569_t();
        if (nearSurface) {
            WaterSpeed.c_3005_b.P_4830_p.Ping.n_1700_B(false);
            WaterSpeed.c_3005_b.P_4830_p.p_178_J.n_1700_B(true);
            return;
        }
        int cycle = this.u_2550_I % 10;
        if ((float)cycle < 5.5f) {
            WaterSpeed.c_3005_b.P_4830_p.Ping.n_1700_B(true);
            WaterSpeed.c_3005_b.P_4830_p.p_178_J.n_1700_B(false);
        } else {
            WaterSpeed.c_3005_b.P_4830_p.Ping.n_1700_B(false);
            WaterSpeed.c_3005_b.P_4830_p.p_178_J.n_1700_B(true);
        }
    }

    private boolean Q_4569_t() {
        c_1514_x above = WaterSpeed.c_3005_b.Y_259_p.b_2312_j().up();
        return WaterSpeed.c_3005_b.Y_601_j.getBlockState(above).v_4262_N();
    }

    @Override
    public void onEnable() {
        this.u_2550_I = 0;
        super.onEnable();
    }

    private void M_182_A() {
        if (!WaterSpeed.c_3005_b.Y_259_p.RowButton()) {
            return;
        }
        if (!WaterSpeed.c_3005_b.Y_259_p.C_1269_X() && !this.rezhimMode.isMode("Grim")) {
            return;
        }
        if (this.rezhimMode.isMode("Legit")) {
            this.t_1786_h();
        } else if (this.rezhimMode.isMode("Motion")) {
            this.multiplayerClientSuggestionProvider();
        } else if (this.rezhimMode.isMode("Grim")) {
            this.w_1457_N();
        }
    }

    private void t_1786_h() {
        if (WaterSpeed.c_3005_b.Y_259_p.RealmsWorldResetDto % (15 + WaterSpeed.c_3005_b.Y_259_p.j_276_v() % 11) != 0) {
            return;
        }
        double motionX = WaterSpeed.c_3005_b.Y_259_p.I_4348_c().J_1907_R;
        double motionY = WaterSpeed.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        double motionZ = WaterSpeed.c_3005_b.Y_259_p.I_4348_c().G_564_y;
        double horizontalSpeed = Math.sqrt(motionX * motionX + motionZ * motionZ);
        double multiplier = 1.01 + Math.random() * 0.015;
        double maxSpeed = 0.209;
        if (horizontalSpeed > 0.01) {
            if (horizontalSpeed * multiplier > maxSpeed) {
                double scale = maxSpeed / horizontalSpeed;
                WaterSpeed.c_3005_b.Y_259_p.s_956_w(motionX * scale, motionY, motionZ * scale);
            } else {
                WaterSpeed.c_3005_b.Y_259_p.s_956_w(motionX * multiplier, motionY, motionZ * multiplier);
            }
        }
    }

    private void multiplayerClientSuggestionProvider() {
        float yaw = (float)Math.toRadians(WaterSpeed.c_3005_b.Y_259_p.p_178_J);
        double forward = WaterSpeed.c_3005_b.Y_259_p.G_564_y.moveForward;
        double strafe = WaterSpeed.c_3005_b.Y_259_p.G_564_y.moveStrafe;
        if (forward == 0.0 && strafe == 0.0) {
            return;
        }
        double speed = ((Float)this.skorostSetting.getValue()).floatValue();
        double motionX = -Math.sin(yaw) * speed;
        double motionZ = Math.cos(yaw) * speed;
        if (forward < 0.0) {
            motionX = -motionX;
            motionZ = -motionZ;
        }
        WaterSpeed.c_3005_b.Y_259_p.h_1847_R(motionX, WaterSpeed.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, motionZ);
    }

    private void w_1457_N() {
        double waterLevel;
        if (!WaterSpeed.c_3005_b.P_4830_p.Ping.G_564_y()) {
            return;
        }
        if (!WaterSpeed.c_3005_b.Y_259_p.RowButton()) {
            return;
        }
        c_1514_x playerPos = new c_1514_x(WaterSpeed.c_3005_b.Y_259_p.O_3598_v(), WaterSpeed.c_3005_b.Y_259_p.X_2960_b(), WaterSpeed.c_3005_b.Y_259_p.l_2647_k());
        try {
            waterLevel = WaterSpeed.c_3005_b.Y_259_p.O_508_d.getFluidState(playerPos).n_1700_B((BlockGetter)WaterSpeed.c_3005_b.Y_259_p.O_508_d, playerPos);
        }
        catch (NoSuchMethodError e) {
            waterLevel = WaterSpeed.c_3005_b.Y_259_p.O_508_d.getBlockState(playerPos).R_4764_Y() == Material.s_956_w ? (double)playerPos.x + 1.0 : (double)playerPos.y;
        }
        double playerEyeY = WaterSpeed.c_3005_b.Y_259_p.X_2960_b() + (double)WaterSpeed.c_3005_b.Y_259_p.X_1313_W();
        if (playerEyeY >= waterLevel - 0.2 && playerEyeY <= waterLevel + 0.2) {
            WaterSpeed.c_3005_b.Y_259_p.h_1847_R(WaterSpeed.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.2, WaterSpeed.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            double horizontal = Math.sqrt(WaterSpeed.c_3005_b.Y_259_p.I_4348_c().J_1907_R * WaterSpeed.c_3005_b.Y_259_p.I_4348_c().J_1907_R + WaterSpeed.c_3005_b.Y_259_p.I_4348_c().G_564_y * WaterSpeed.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            u_925_K.n_1700_B(horizontal * 5.0);
        }
    }

    private void Y_601_j() {
        if (WaterSpeed.c_3005_b.Y_259_p.C_1269_X() || WaterSpeed.c_3005_b.Y_259_p.k_578_l() || WaterSpeed.c_3005_b.Y_259_p.q_2307_F()) {
            return;
        }
        double height = WaterSpeed.c_3005_b.Y_259_p.i_601_W().maxY - WaterSpeed.c_3005_b.Y_259_p.i_601_W().minY;
        if (height >= 1.5) {
            return;
        }
        float motion = WaterSpeed.c_3005_b.Y_259_p.J_1907_R(MobEffects.n_1700_B) ? 0.32f : 0.3f;
        u_925_K.n_1700_B((double)motion);
    }
}



