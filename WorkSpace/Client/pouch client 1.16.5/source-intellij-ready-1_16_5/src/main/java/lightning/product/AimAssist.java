/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.StreamSupport;
import lightning.product.D_686_b;
import lightning.product.AntiBot;
import lightning.product.E_4612_l;
import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.AttackAura;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.SwordItem;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class AimAssist
extends Module {
    private final MultiBooleanSetting kogoNavoditOptions = new MultiBooleanSetting("\u041a\u043e\u0433\u043e \u043d\u0430\u0432\u043e\u0434\u0438\u0442\u044c", new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new BooleanSetting("\u0414\u0440\u0443\u0437\u0435\u0439", false), new BooleanSetting("\u0413\u043e\u043b\u044b\u0445", true), new BooleanSetting("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false), new BooleanSetting("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new BooleanSetting("\u041c\u043e\u0431\u043e\u0432", false));
    private final NumberSetting vremyaBlokirovkiSetting = new NumberSetting("\u0412\u0440\u0435\u043c\u044f \u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u043a\u0438", 2.7f, 0.0f, 8.0f, 0.1f);
    private final NumberSetting distanciyaNavodkiSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043d\u0430\u0432\u043e\u0434\u043a\u0438", 4.0f, 2.0f, 8.0f, 0.1f);
    private final NumberSetting silaNavodkiSetting = new NumberSetting("\u0421\u0438\u043b\u0430 \u043d\u0430\u0432\u043e\u0434\u043a\u0438", 1.25f, 0.1f, 2.0f, 0.01f);
    private final NumberSetting skorostPricelivaniyaSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0440\u0438\u0446\u0435\u043b\u0438\u0432\u0430\u043d\u0438\u044f", 10.0f, 0.1f, 20.0f, 0.1f);
    private final NumberSetting znacheniePlavnostiSetting = new NumberSetting("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043f\u043b\u0430\u0432\u043d\u043e\u0441\u0442\u0438", 8.5f, 0.0f, 10.0f, 0.1f);
    private final NumberSetting fovSetting = new NumberSetting("FOV", 70.0f, 15.0f, 180.0f, 1.0f);
    private final BooleanSetting boleeEnabled = new BooleanSetting("\u0411\u043e\u043b\u0435\u0435 \"\u0447\u0435\u043b\u043e\u0432\u0435\u0447\u0435\u0441\u043a\u0438\u0439\"", false);
    private final BooleanSetting vklyuchitVertikalnuyuNavodkuEnabled = new BooleanSetting("\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u0443\u044e \u043d\u0430\u0432\u043e\u0434\u043a\u0443", true);
    private final NumberSetting skorostVertikalnSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d.", 0.23f, 0.01f, 1.0f, 0.01f, this.vklyuchitVertikalnuyuNavodkuEnabled::isEnabled);
    private final BooleanSetting shumVertikalnoyOsiPriGorizontalnomDvizheniiEnabled = new BooleanSetting("\u0428\u0443\u043c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0439 \u043e\u0441\u0438 \u043f\u0440\u0438 \u0433\u043e\u0440\u0438\u0437\u043e\u043d\u0442\u0430\u043b\u044c\u043d\u043e\u043c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", false, this.vklyuchitVertikalnuyuNavodkuEnabled::isEnabled);
    private final NumberSetting znachenieShumaSetting = new NumberSetting("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0448\u0443\u043c\u0430", 0.13f, 0.0f, 0.5f, 0.01f, this.shumVertikalnoyOsiPriGorizontalnomDvizheniiEnabled::isEnabled);
    private final BooleanSetting generirovatEnabled = new BooleanSetting("\u0413\u0435\u043d\u0435\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \"\u0437\u0430\u0442\u0443\u043f\"", false);
    private final BooleanSetting tolkoPriOruzhiiEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043e\u0440\u0443\u0436\u0438\u0438", true);
    private final BooleanSetting neDvigatPriProtivnikeEnabled = new BooleanSetting("\u041d\u0435 \u0434\u0432\u0438\u0433\u0430\u0442\u044c \u043f\u0440\u0438 \u043f\u0440\u043e\u0442\u0438\u0432\u043d\u0438\u043a\u0435", true);
    private final BooleanSetting otVvodaEnabled = new BooleanSetting("\u041e\u0442 \u0432\u0432\u043e\u0434\u0430", false);
    private final NumberSetting predugadyvaniePoziciiSetting = new NumberSetting("\u041f\u0440\u0435\u0434\u0443\u0433\u0430\u0434\u044b\u0432\u0430\u043d\u0438\u0435 \u043f\u043e\u0437\u0438\u0446\u0438\u0438", 0.0f, 0.0f, 1.5f, 0.05f);
    private final NumberSetting prediktOttalkivaniyaSetting = new NumberSetting("\u041f\u0440\u0435\u0434\u0438\u043a\u0442 \u043e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u043d\u0438\u044f", 0.0f, 0.0f, 1.5f, 0.05f);
    private final BooleanSetting sistemaMultipointEnabled = new BooleanSetting("\u0421\u0438\u0441\u0442\u0435\u043c\u0430 \u043c\u0443\u043b\u044c\u0442\u0438\u043f\u043e\u0438\u043d\u0442", false);
    private final BooleanSetting dovoditPriDvizheniiMyshiEnabled = new BooleanSetting("\u0414\u043e\u0432\u043e\u0434\u0438\u0442\u044c \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438 \u043c\u044b\u0448\u0438", true);
    private final BooleanSetting tolkoVidimyhEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0432\u0438\u0434\u0438\u043c\u044b\u0445", true);
    private final BooleanSetting vyklyuchatSAttackauraEnabled = new BooleanSetting("\u0412\u044b\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0441 AttackAura", true);
    private final Random e_4240_b = new Random();
    private r_4811_B n_3318_d;
    private long d_2427_y;
    private int z_1737_N;

    public AimAssist() {
        super("AimAssist", ModuleCategory.n_1700_B);
        this.addSettings(this.kogoNavoditOptions, this.vremyaBlokirovkiSetting, this.distanciyaNavodkiSetting, this.silaNavodkiSetting, this.skorostPricelivaniyaSetting, this.znacheniePlavnostiSetting, this.fovSetting, this.boleeEnabled, this.vklyuchitVertikalnuyuNavodkuEnabled, this.skorostVertikalnSetting, this.shumVertikalnoyOsiPriGorizontalnomDvizheniiEnabled, this.znachenieShumaSetting, this.generirovatEnabled, this.tolkoPriOruzhiiEnabled, this.neDvigatPriProtivnikeEnabled, this.dovoditPriDvizheniiMyshiEnabled, this.otVvodaEnabled, this.predugadyvaniePoziciiSetting, this.prediktOttalkivaniyaSetting, this.sistemaMultipointEnabled, this.tolkoVidimyhEnabled, this.vyklyuchatSAttackauraEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        String rotationMode;
        boolean auraFov90;
        boolean allowWithAuraFov90Raycast;
        AttackAura aura;
        if (AimAssist.c_3005_b.Y_259_p == null || AimAssist.c_3005_b.Y_601_j == null) {
            return;
        }
        if (AimAssist.c_3005_b.Y_1740_V != null) {
            return;
        }
        if (!AimAssist.c_3005_b.P_4830_p.P_4830_p().n_1700_B()) {
            return;
        }
        if (this.vyklyuchatSAttackauraEnabled.isEnabled().booleanValue() && (aura = ClientBootstrap.Y_601_j().J_1907_R().J_1907_R()) != null && aura.w_1484_f() && aura.v_4262_N != null && !(allowWithAuraFov90Raycast = (auraFov90 = (rotationMode = aura.Q_4569_t() != null ? (String)aura.Q_4569_t().J_1907_R() : "") != null && rotationMode.toLowerCase().contains("fov90")))) {
            return;
        }
        if (this.tolkoPriOruzhiiEnabled.isEnabled().booleanValue() && !(AimAssist.c_3005_b.Y_259_p.A_2714_y().J_1907_R() instanceof SwordItem)) {
            return;
        }
        if (this.otVvodaEnabled.isEnabled().booleanValue() && !AimAssist.c_3005_b.P_4830_p.D_60_a.G_564_y()) {
            return;
        }
        r_4811_B target = this.h_1847_R();
        if (target == null) {
            return;
        }
        if (this.generirovatEnabled.isEnabled().booleanValue()) {
            if (this.z_1737_N > 0) {
                --this.z_1737_N;
                return;
            }
            if (this.e_4240_b.nextFloat() < 0.045f) {
                this.z_1737_N = 1 + this.e_4240_b.nextInt(2);
                return;
            }
        }
        float[] needed = this.J_1907_R(target);
        float yawDiff = u_530_F.v_4262_N(needed[0] - AimAssist.c_3005_b.Y_259_p.p_178_J);
        float pitchDiff = u_530_F.v_4262_N(needed[1] - AimAssist.c_3005_b.Y_259_p.f_4016_n);
        if (Math.abs(yawDiff) > ((Float)this.fovSetting.getValue()).floatValue() * 0.5f) {
            return;
        }
        if (this.neDvigatPriProtivnikeEnabled.isEnabled().booleanValue() && !this.dovoditPriDvizheniiMyshiEnabled.isEnabled().booleanValue() && Math.abs(yawDiff) < 0.35f && Math.abs(pitchDiff) < 0.3f) {
            return;
        }
        float smoothLinear = u_530_F.n_1700_B((10.0f - ((Float)this.znacheniePlavnostiSetting.getValue()).floatValue()) / 10.0f, 0.03f, 1.0f);
        float assistStrength = ((Float)this.silaNavodkiSetting.getValue()).floatValue();
        float yawCap = ((Float)this.skorostPricelivaniyaSetting.getValue()).floatValue();
        float yawStep = u_530_F.n_1700_B(yawDiff * assistStrength * smoothLinear, -yawCap, yawCap);
        if (this.boleeEnabled.isEnabled().booleanValue()) {
            yawStep += (this.e_4240_b.nextFloat() - 0.5f) * 0.22f;
        }
        float pitchStep = 0.0f;
        if (this.vklyuchitVertikalnuyuNavodkuEnabled.isEnabled().booleanValue()) {
            float pitchCap = ((Float)this.skorostVertikalnSetting.getValue()).floatValue();
            pitchStep = u_530_F.n_1700_B(pitchDiff * assistStrength * smoothLinear, -pitchCap, pitchCap);
            if (this.shumVertikalnoyOsiPriGorizontalnomDvizheniiEnabled.isEnabled().booleanValue() && Math.abs(yawStep) > 0.01f) {
                pitchStep += (this.e_4240_b.nextFloat() - 0.5f) * ((Float)this.znachenieShumaSetting.getValue()).floatValue();
            }
            if (this.boleeEnabled.isEnabled().booleanValue()) {
                pitchStep += (this.e_4240_b.nextFloat() - 0.5f) * 0.08f;
            }
        }
        float targetYaw = AimAssist.c_3005_b.Y_259_p.p_178_J + yawStep;
        float targetPitch = u_530_F.n_1700_B(AimAssist.c_3005_b.Y_259_p.f_4016_n + pitchStep, -89.0f, 89.0f);
        float patchedYaw = r_4790_y.n_1700_B(AimAssist.c_3005_b.Y_259_p.p_178_J, targetYaw);
        float patchedPitch = r_4790_y.n_1700_B(AimAssist.c_3005_b.Y_259_p.f_4016_n, targetPitch);
        AimAssist.c_3005_b.Y_259_p.p_178_J = patchedYaw;
        AimAssist.c_3005_b.Y_259_p.f_3449_S = patchedYaw;
        AimAssist.c_3005_b.Y_259_p.C_1162_e = patchedYaw;
        AimAssist.c_3005_b.Y_259_p.f_4016_n = patchedPitch;
    }

    private r_4811_B h_1847_R() {
        r_4811_B found;
        long now = System.currentTimeMillis();
        if (this.n_3318_d != null && now <= this.d_2427_y && this.n_1700_B((N_4263_v)this.n_3318_d)) {
            return this.n_3318_d;
        }
        this.n_3318_d = found = this.Q_4569_t();
        this.d_2427_y = found != null ? now + (long)(((Float)this.vremyaBlokirovkiSetting.getValue()).floatValue() * 1000.0f) : 0L;
        return found;
    }

    private r_4811_B Q_4569_t() {
        return StreamSupport.stream(AimAssist.c_3005_b.Y_601_j.J_1907_R().spliterator(), false).filter(this::n_1700_B).map(ent -> (r_4811_B)ent).min(Comparator.comparingDouble(this::n_1700_B).thenComparingDouble(ent -> AimAssist.c_3005_b.Y_259_p.G_564_y((N_4263_v)ent))).orElse(null);
    }

    private boolean n_1700_B(N_4263_v entity) {
        if (!(entity instanceof r_4811_B)) {
            return false;
        }
        r_4811_B living = (r_4811_B)entity;
        if (entity == AimAssist.c_3005_b.Y_259_p || !entity.RealmsLongRunningMcoTaskScreen() || entity instanceof D_686_b) {
            return false;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            AntiBot antiBot = (AntiBot)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AntiBot.class);
            if (antiBot != null && antiBot.w_1484_f() && player.g_4106_L) {
                return false;
            }
        }
        if (this.tolkoVidimyhEnabled.isEnabled().booleanValue() && !AimAssist.c_3005_b.Y_259_p.c_3005_b(entity)) {
            return false;
        }
        if (AimAssist.c_3005_b.Y_259_p.R_4764_Y(entity) > ((Float)this.distanciyaNavodkiSetting.getValue()).floatValue()) {
            return false;
        }
        if (this.n_1700_B(living) > (double)(((Float)this.fovSetting.getValue()).floatValue() * 0.5f)) {
            return false;
        }
        return E_4612_l.n_1700_B(living, this.kogoNavoditOptions, true) || E_4612_l.n_1700_B(living, this.kogoNavoditOptions) || E_4612_l.J_1907_R(living, this.kogoNavoditOptions) || E_4612_l.R_4764_Y(living, this.kogoNavoditOptions);
    }

    private double n_1700_B(r_4811_B target) {
        float[] needed = this.J_1907_R(target);
        return Math.abs(u_530_F.v_4262_N(needed[0] - AimAssist.c_3005_b.Y_259_p.p_178_J));
    }

    private float[] J_1907_R(r_4811_B target) {
        e_2866_D predict = this.R_4764_Y(target);
        double dx = predict.J_1907_R - AimAssist.c_3005_b.Y_259_p.O_3598_v();
        double dz = predict.G_564_y - AimAssist.c_3005_b.Y_259_p.l_2647_k();
        double targetBodyY = target.X_2960_b() + (double)target.v_165_F() * 0.62;
        if (target.q_2307_F()) {
            targetBodyY -= 0.08;
        }
        double dy = targetBodyY - AimAssist.c_3005_b.Y_259_p.X_2048_Y();
        double dist = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(dy, dist)));
        if (this.sistemaMultipointEnabled.isEnabled().booleanValue()) {
            double altY = target.X_2960_b() + (double)target.v_165_F() * 0.45;
            double altDy = altY - AimAssist.c_3005_b.Y_259_p.X_2048_Y();
            float altPitch = (float)(-Math.toDegrees(Math.atan2(altDy, dist)));
            pitch = (pitch + altPitch) * 0.5f;
        }
        return new float[]{yaw, pitch};
    }

    private e_2866_D R_4764_Y(r_4811_B target) {
        double pred = ((Float)this.predugadyvaniePoziciiSetting.getValue()).floatValue();
        double kb = ((Float)this.prediktOttalkivaniyaSetting.getValue()).floatValue();
        double motionX = target.I_4348_c().J_1907_R * (0.5 + pred);
        double motionZ = target.I_4348_c().G_564_y * (0.5 + pred);
        if (kb > 0.0) {
            motionX += (target.O_3598_v() - target.r_715_M) * kb;
            motionZ += (target.l_2647_k() - target.i_1637_u) * kb;
        }
        return new e_2866_D(target.O_3598_v() + motionX, target.X_2960_b(), target.l_2647_k() + motionZ);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.n_3318_d = null;
        this.d_2427_y = 0L;
        this.z_1737_N = 0;
    }
}



