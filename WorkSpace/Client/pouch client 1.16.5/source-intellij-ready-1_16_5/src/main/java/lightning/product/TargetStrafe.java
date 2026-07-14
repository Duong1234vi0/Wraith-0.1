/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.AirBlock;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.AttackAura;
import lightning.product.r_4811_B;
import lightning.product.u_925_K;
import lightning.product.ModuleCategory;

public class TargetStrafe
extends Module {
    private static final double w_1484_f = 1.0E-4;
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Collision", "Collision", "Default", "Legit");
    private final BooleanSetting avtoPryzhokEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u041f\u0440\u044b\u0436\u043e\u043a", true, () -> this.rezhimMode.isMode("Default"));
    private final NumberSetting distanciyaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 2.4f, 0.1f, 6.0f, 0.1f, () -> this.rezhimMode.isMode("Default"));
    private final NumberSetting skorostSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.23f, 0.1f, 1.0f, 0.01f, () -> this.rezhimMode.isMode("Default"));
    private final BooleanSetting bustPriUdareEnabled = new BooleanSetting("\u0411\u0443\u0441\u0442 \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435", false, () -> this.rezhimMode.isMode("Default"));
    private final NumberSetting skorostBustaSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0431\u0443\u0441\u0442\u0430", 0.5f, 0.1f, 1.5f, 0.1f, () -> this.rezhimMode.isMode("Default") && this.bustPriUdareEnabled.isEnabled() != false);
    private final NumberSetting silaSetting = new NumberSetting("\u0421\u0438\u043b\u0430", 0.03f, 0.01f, 0.1f, 0.01f, () -> this.rezhimMode.isMode("Holyworld"));
    private final NumberSetting radiusUskoreniyaSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441 \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f", 0.4f, 0.1f, 1.0f, 0.1f, () -> this.rezhimMode.isMode("Holyworld"));
    private final NumberSetting prediktSetting = new NumberSetting("\u041f\u0440\u0435\u0434\u0438\u043a\u0442", 5.0f, 1.0f, 10.0f, 1.0f, () -> this.rezhimMode.isMode("Holyworld"));
    private final NumberSetting silaSetting2 = new NumberSetting("\u0421\u0438\u043b\u0430", 0.03f, 0.01f, 0.1f, 0.01f, () -> this.rezhimMode.isMode("Holyworld New"));
    private final NumberSetting radiusUskoreniyaSetting2 = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441 \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f", 0.4f, 0.1f, 1.0f, 0.1f, () -> this.rezhimMode.isMode("Holyworld New"));
    private final NumberSetting prediktSetting2 = new NumberSetting("\u041f\u0440\u0435\u0434\u0438\u043a\u0442", 5.0f, 1.0f, 10.0f, 1.0f, () -> this.rezhimMode.isMode("Holyworld New"));
    private final BooleanSetting avtomaticheskiPrygatEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u043f\u0440\u044b\u0433\u0430\u0442\u044c", true, () -> this.rezhimMode.isMode("Legit"));
    private final BooleanSetting vzhimatsyaVCelEnabled = new BooleanSetting("\u0412\u0436\u0438\u043c\u0430\u0442\u044c\u0441\u044f \u0432 \u0446\u0435\u043b\u044c", true, () -> this.rezhimMode.isMode("Legit"));
    private final NumberSetting distanciyaStreyfaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0441\u0442\u0440\u0435\u0439\u0444\u0430", 2.5f, 0.5f, 5.0f, 0.1f, () -> this.rezhimMode.isMode("Legit"));
    public static boolean v_4262_N;
    private boolean k_2293_S = true;

    public TargetStrafe() {
        super("TargetStrafe", ModuleCategory.n_1700_B);
        this.addSettings(this.rezhimMode, this.avtoPryzhokEnabled, this.distanciyaSetting, this.skorostSetting, this.bustPriUdareEnabled, this.skorostBustaSetting, this.silaSetting, this.radiusUskoreniyaSetting, this.prediktSetting, this.silaSetting2, this.radiusUskoreniyaSetting2, this.prediktSetting2, this.avtomaticheskiPrygatEnabled, this.vzhimatsyaVCelEnabled, this.distanciyaStreyfaSetting);
    }

    private boolean h_1847_R() {
        for (int j = (int)TargetStrafe.c_3005_b.Y_259_p.X_2960_b(); j > 0; --j) {
            c_1514_x pos = new c_1514_x(TargetStrafe.c_3005_b.Y_259_p.O_3598_v(), (double)j, TargetStrafe.c_3005_b.Y_259_p.l_2647_k());
            if (TargetStrafe.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R() instanceof AirBlock) continue;
            return false;
        }
        return true;
    }

    public boolean n_1700_B(double x, double z) {
        if (TargetStrafe.c_3005_b.Y_259_p.D_60_a || TargetStrafe.c_3005_b.P_4830_p.r_715_M.G_564_y() || TargetStrafe.c_3005_b.P_4830_p.i_1637_u.G_564_y()) {
            return true;
        }
        for (int j = (int)(TargetStrafe.c_3005_b.Y_259_p.X_2960_b() + 4.0); j >= 0; --j) {
            c_1514_x blockPos = new c_1514_x(x, (double)j, z);
            if (TargetStrafe.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R().equals(a_3742_W.H_2857_Y) || TargetStrafe.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R().equals(a_3742_W.x_612_B)) {
                return true;
            }
            if (TargetStrafe.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R() == a_3742_W.y_1700_S) {
                return true;
            }
            if (this.h_1847_R()) {
                return true;
            }
            if (TargetStrafe.c_3005_b.Y_601_j.u_1723_Y(blockPos)) continue;
            return false;
        }
        return true;
    }

    private float n_1700_B(float targetX, float targetZ) {
        double dx = (double)targetX - TargetStrafe.c_3005_b.Y_259_p.O_3598_v();
        double dz = (double)targetZ - TargetStrafe.c_3005_b.Y_259_p.l_2647_k();
        return (float)(Math.atan2(dz, dx) * 180.0 / Math.PI - 90.0);
    }

    private float n_1700_B(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (this.rezhimMode.isMode("Collision")) {
            this.t_1786_h();
        } else if (this.rezhimMode.isMode("Default")) {
            this.multiplayerClientSuggestionProvider();
        } else if (this.rezhimMode.isMode("Holyworld New")) {
            this.w_1457_N();
        } else if (this.rezhimMode.isMode("Legit")) {
            this.Q_4569_t();
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        boolean pressing;
        r_4811_B target;
        if (!this.rezhimMode.isMode("Legit")) {
            return;
        }
        AttackAura attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || !target.RealmsLongRunningMcoTaskScreen()) {
            return;
        }
        double distance = TargetStrafe.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > 6.0) {
            return;
        }
        double dx = target.O_3598_v() - TargetStrafe.c_3005_b.Y_259_p.O_3598_v();
        double dz = target.l_2647_k() - TargetStrafe.c_3005_b.Y_259_p.l_2647_k();
        float toTargetYaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        boolean bl = pressing = e.n_1700_B() != 0.0f || e.J_1907_R() != 0.0f;
        if (this.vzhimatsyaVCelEnabled.isEnabled().booleanValue() && distance > (double)((Float)this.distanciyaStreyfaSetting.getValue()).floatValue()) {
            e.n_1700_B(1.0f);
            e.J_1907_R(0.0f);
            e.n_1700_B(true);
            u_925_K.n_1700_B(e, toTargetYaw);
        } else if (pressing) {
            float perpOffset = this.k_2293_S ? 90.0f : -90.0f;
            float strafeYaw = toTargetYaw + perpOffset;
            u_925_K.n_1700_B(e, strafeYaw);
        }
    }

    private void Q_4569_t() {
        double nextZ;
        double nextX;
        r_4811_B target;
        AttackAura attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || !target.RealmsLongRunningMcoTaskScreen()) {
            v_4262_N = false;
            return;
        }
        double distance = TargetStrafe.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > 6.0) {
            v_4262_N = false;
            return;
        }
        v_4262_N = true;
        if (TargetStrafe.c_3005_b.Y_259_p.D_60_a) {
            boolean bl = this.k_2293_S = !this.k_2293_S;
        }
        if (this.n_1700_B(nextX = TargetStrafe.c_3005_b.Y_259_p.O_3598_v() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().J_1907_R * 3.0, nextZ = TargetStrafe.c_3005_b.Y_259_p.l_2647_k() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().G_564_y * 3.0)) {
            boolean bl = this.k_2293_S = !this.k_2293_S;
        }
        if (this.avtomaticheskiPrygatEnabled.isEnabled().booleanValue() && distance <= (double)((Float)this.distanciyaStreyfaSetting.getValue()).floatValue() + 1.0) {
            if (TargetStrafe.c_3005_b.Y_259_p.M_1641_O()) {
                TargetStrafe.c_3005_b.P_4830_p.Ping.n_1700_B(true);
            } else if (!TargetStrafe.c_3005_b.P_4830_p.Ping.G_564_y()) {
                TargetStrafe.c_3005_b.P_4830_p.Ping.n_1700_B(false);
            }
        }
    }

    private void M_182_A() {
        r_4811_B target;
        if (!u_925_K.n_1700_B()) {
            return;
        }
        AttackAura attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null) {
            return;
        }
        e_2866_D targetPos = target.s_4990_V();
        if (((Float)this.prediktSetting.getValue()).floatValue() > 0.0f) {
            e_2866_D predictedMotion = target.I_4348_c();
            targetPos = targetPos.P_1922_E(predictedMotion.n_1700_B((double)((Float)this.prediktSetting.getValue()).floatValue() * 0.05));
        }
        e_2866_D playerPos = TargetStrafe.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D direction = targetPos.G_564_y(playerPos).G_564_y();
        double distance = TargetStrafe.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > (double)(((Float)this.radiusUskoreniyaSetting.getValue()).floatValue() + 1.0f)) {
            return;
        }
        e_2866_D motionPos = new e_2866_D(TargetStrafe.c_3005_b.Y_259_p.O_3598_v() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().J_1907_R, TargetStrafe.c_3005_b.Y_259_p.X_2960_b() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, TargetStrafe.c_3005_b.Y_259_p.l_2647_k() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        c_1514_x motionBlockPos = new c_1514_x(motionPos);
        float p = TargetStrafe.c_3005_b.Y_601_j.getBlockState(motionBlockPos).J_1907_R().h_1847_R();
        float f = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? p : 0.91f;
        float f2 = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? p : 0.99f;
        double motionY = TargetStrafe.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        float strength = ((Float)this.silaSetting.getValue()).floatValue();
        if (distance < (double)((Float)this.radiusUskoreniyaSetting.getValue()).floatValue()) {
            float accelerationFactor = 1.0f + (float)((double)((Float)this.radiusUskoreniyaSetting.getValue()).floatValue() - distance) / ((Float)this.radiusUskoreniyaSetting.getValue()).floatValue();
            strength *= accelerationFactor;
        }
        if (target.D_60_a) {
            strength *= 1.2f;
        }
        float ground = strength;
        float falling = strength;
        float jump = strength;
        double speed = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? (double)ground : (TargetStrafe.c_3005_b.Y_259_p.U_1241_n > 0.0f ? (double)falling : (double)jump);
        double newX = direction.J_1907_R * speed * (double)f2 / (double)f;
        double newZ = direction.G_564_y * speed * (double)f2 / (double)f;
        TargetStrafe.c_3005_b.Y_259_p.h_1847_R(TargetStrafe.c_3005_b.Y_259_p.I_4348_c().J_1907_R + newX, motionY, TargetStrafe.c_3005_b.Y_259_p.I_4348_c().G_564_y + newZ);
    }

    private void t_1786_h() {
        r_4811_B target;
        if (!u_925_K.n_1700_B()) {
            return;
        }
        AttackAura attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || TargetStrafe.c_3005_b.Y_259_p.R_4764_Y(target) > 2.75f) {
            return;
        }
        e_2866_D targetMotion = target.I_4348_c();
        double targetHorizontalSpeedSq = targetMotion.J_1907_R * targetMotion.J_1907_R + targetMotion.G_564_y * targetMotion.G_564_y;
        if (targetHorizontalSpeedSq <= 1.0E-4) {
            return;
        }
        e_2866_D playerPos = TargetStrafe.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D targetPos = target.s_4990_V();
        e_2866_D direction = targetPos.G_564_y(playerPos).G_564_y();
        e_2866_D motionPos = new e_2866_D(TargetStrafe.c_3005_b.Y_259_p.O_3598_v() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().J_1907_R, TargetStrafe.c_3005_b.Y_259_p.X_2960_b() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, TargetStrafe.c_3005_b.Y_259_p.l_2647_k() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        c_1514_x motionBlockPos = new c_1514_x(motionPos);
        float p = TargetStrafe.c_3005_b.Y_601_j.getBlockState(motionBlockPos).J_1907_R().h_1847_R();
        float f = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? p : 0.91f;
        float f2 = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? p : 0.99f;
        double motionY = TargetStrafe.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        float ground = 0.05f;
        float falling = 0.05f;
        float jump = 0.05f;
        float gradus = (float)System.currentTimeMillis() / 100.0f;
        float centrifugal = 0.0f;
        float deviationX = (float)Math.cos(Math.toDegrees(gradus)) * centrifugal;
        float deviationZ = (float)Math.sin(Math.toDegrees(gradus)) * centrifugal;
        direction = direction.J_1907_R(deviationX, 0.0, deviationZ);
        double speed = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? (double)ground : (TargetStrafe.c_3005_b.Y_259_p.U_1241_n > 0.0f ? (double)falling : (double)jump);
        double newX = direction.J_1907_R * speed * (double)f2 / (double)f;
        double newZ = direction.G_564_y * speed * (double)f2 / (double)f;
        TargetStrafe.c_3005_b.Y_259_p.h_1847_R(TargetStrafe.c_3005_b.Y_259_p.I_4348_c().J_1907_R + newX, motionY, TargetStrafe.c_3005_b.Y_259_p.I_4348_c().G_564_y + newZ);
    }

    private void multiplayerClientSuggestionProvider() {
        float targetZ;
        r_4811_B target;
        AttackAura attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || !target.RealmsLongRunningMcoTaskScreen()) {
            v_4262_N = false;
            return;
        }
        if (this.avtoPryzhokEnabled.isEnabled().booleanValue() && TargetStrafe.c_3005_b.Y_259_p.M_1641_O()) {
            TargetStrafe.c_3005_b.P_4830_p.Ping.n_1700_B(false);
            TargetStrafe.c_3005_b.Y_259_p.e_837_t();
            return;
        }
        float maxDistance = 6.0f;
        double distance = TargetStrafe.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > (double)maxDistance) {
            v_4262_N = false;
            return;
        }
        TargetStrafe.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
        float speed = ((Float)this.skorostSetting.getValue()).floatValue();
        if (this.bustPriUdareEnabled.isEnabled().booleanValue() && TargetStrafe.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen > 0 && TargetStrafe.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen()) {
            speed += ((Float)this.skorostBustaSetting.getValue()).floatValue();
        }
        float distRatio = this.n_1700_B((float)distance / maxDistance, 0.01f, 1.0f);
        double angle = Math.atan2(TargetStrafe.c_3005_b.Y_259_p.l_2647_k() - target.l_2647_k(), TargetStrafe.c_3005_b.Y_259_p.O_3598_v() - target.O_3598_v());
        float angleStep = this.n_1700_B(speed / distRatio, 0.01f, 1.0f);
        float targetX = (float)(target.O_3598_v() + (double)((Float)this.distanciyaSetting.getValue()).floatValue() * Math.cos(angle += this.k_2293_S ? (double)angleStep : (double)(-angleStep)));
        if (this.n_1700_B((double)targetX, (double)(targetZ = (float)(target.l_2647_k() + (double)((Float)this.distanciyaSetting.getValue()).floatValue() * Math.sin(angle))))) {
            this.k_2293_S = !this.k_2293_S;
            targetX = (float)(target.O_3598_v() + (double)((Float)this.distanciyaSetting.getValue()).floatValue() * Math.cos(angle += (double)(2.0f * (this.k_2293_S ? angleStep : -angleStep))));
            targetZ = (float)(target.l_2647_k() + (double)((Float)this.distanciyaSetting.getValue()).floatValue() * Math.sin(angle));
        }
        v_4262_N = true;
        float yaw = this.n_1700_B(targetX, targetZ);
        double motionX = (double)speed * -Math.sin(Math.toRadians(yaw));
        double motionZ = (double)speed * Math.cos(Math.toRadians(yaw));
        if (Double.isNaN(motionX) || Double.isNaN(motionZ)) {
            return;
        }
        TargetStrafe.c_3005_b.Y_259_p.h_1847_R(motionX, TargetStrafe.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, motionZ);
    }

    private void w_1457_N() {
        r_4811_B target;
        if (!u_925_K.n_1700_B()) {
            return;
        }
        AttackAura attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null) {
            return;
        }
        e_2866_D targetPos = target.s_4990_V();
        if (((Float)this.prediktSetting2.getValue()).floatValue() > 0.0f) {
            e_2866_D predictedMotion = target.I_4348_c();
            targetPos = targetPos.P_1922_E(predictedMotion.n_1700_B((double)((Float)this.prediktSetting2.getValue()).floatValue() * 0.05));
        }
        e_2866_D playerPos = TargetStrafe.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D direction = targetPos.G_564_y(playerPos).G_564_y();
        double distance = TargetStrafe.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > (double)(((Float)this.radiusUskoreniyaSetting2.getValue()).floatValue() + 1.0f)) {
            return;
        }
        e_2866_D motionPos = new e_2866_D(TargetStrafe.c_3005_b.Y_259_p.O_3598_v() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().J_1907_R, TargetStrafe.c_3005_b.Y_259_p.X_2960_b() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, TargetStrafe.c_3005_b.Y_259_p.l_2647_k() + TargetStrafe.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        c_1514_x motionBlockPos = new c_1514_x(motionPos);
        float p = TargetStrafe.c_3005_b.Y_601_j.getBlockState(motionBlockPos).J_1907_R().h_1847_R();
        float f = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? p : 0.91f;
        float f2 = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? p : 0.99f;
        double motionY = TargetStrafe.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        float strength = ((Float)this.silaSetting2.getValue()).floatValue() * 0.3f;
        if (distance < (double)((Float)this.radiusUskoreniyaSetting2.getValue()).floatValue()) {
            float accelerationFactor = 1.0f + (float)((double)((Float)this.radiusUskoreniyaSetting2.getValue()).floatValue() - distance) / ((Float)this.radiusUskoreniyaSetting2.getValue()).floatValue() * 0.5f;
            strength *= accelerationFactor;
        }
        if (target.D_60_a) {
            strength *= 0.9f;
        }
        float ground = strength;
        float falling = strength * 0.8f;
        float jump = strength * 0.9f;
        double speed = TargetStrafe.c_3005_b.Y_259_p.M_1641_O() ? (double)ground : (TargetStrafe.c_3005_b.Y_259_p.U_1241_n > 0.0f ? (double)falling : (double)jump);
        double newX = direction.J_1907_R * speed * (double)f2 / (double)f;
        double newZ = direction.G_564_y * speed * (double)f2 / (double)f;
        TargetStrafe.c_3005_b.Y_259_p.h_1847_R(TargetStrafe.c_3005_b.Y_259_p.I_4348_c().J_1907_R + newX, motionY, TargetStrafe.c_3005_b.Y_259_p.I_4348_c().G_564_y + newZ);
    }
}



