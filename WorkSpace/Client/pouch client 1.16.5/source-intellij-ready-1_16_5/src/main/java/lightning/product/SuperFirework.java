/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.AttackAura;
import lightning.product.s_4990_V;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class SuperFirework
extends Module {
    private final BooleanSetting uskorenieEnabled = new BooleanSetting("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435", true);
    private final ModeSetting rezhimUskoreniyaMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f", "ReallyWorld", this.uskorenieEnabled::isEnabled, "ReallyWorld", "Bravo", "BravoGrief", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439");
    private final NumberSetting xz05Setting = new NumberSetting("XZ 0-5\u00b0", 1.52f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz510Setting = new NumberSetting("XZ 5-10\u00b0", 1.53f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz1015Setting = new NumberSetting("XZ 10-15\u00b0", 1.54f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz1520Setting = new NumberSetting("XZ 15-20\u00b0", 1.55f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz2025Setting = new NumberSetting("XZ 20-25\u00b0", 1.56f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz2530Setting = new NumberSetting("XZ 25-30\u00b0", 1.57f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz3035Setting = new NumberSetting("XZ 30-35\u00b0", 1.58f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz3540Setting = new NumberSetting("XZ 35-40\u00b0", 1.59f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting xz4045Setting = new NumberSetting("XZ 40-45\u00b0", 1.6f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y05Setting = new NumberSetting("Y 0-5\u00b0", 1.51f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y510Setting = new NumberSetting("Y 5-10\u00b0", 1.52f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y1015Setting = new NumberSetting("Y 10-15\u00b0", 1.53f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y1520Setting = new NumberSetting("Y 15-20\u00b0", 1.54f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y2025Setting = new NumberSetting("Y 20-25\u00b0", 1.55f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y2530Setting = new NumberSetting("Y 25-30\u00b0", 1.56f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y3035Setting = new NumberSetting("Y 30-35\u00b0", 1.57f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y3540Setting = new NumberSetting("Y 35-40\u00b0", 1.58f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting y4045Setting = new NumberSetting("Y 40-45\u00b0", 1.59f, 1.5f, 3.0f, 0.01f, () -> this.uskorenieEnabled.isEnabled() != false && this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final NumberSetting[] t_4043_B = new NumberSetting[]{this.xz05Setting, this.xz510Setting, this.xz1015Setting, this.xz1520Setting, this.xz2025Setting, this.xz2530Setting, this.xz3035Setting, this.xz3540Setting, this.xz4045Setting};
    private final NumberSetting[] x_607_J = new NumberSetting[]{this.y05Setting, this.y510Setting, this.y1015Setting, this.y1520Setting, this.y2025Setting, this.y2530Setting, this.y3035Setting, this.y3540Setting, this.y4045Setting};
    private static final float[] e_4240_b = new float[]{1.67f, 1.68f, 1.69f, 1.72f, 1.72f, 1.73f, 1.74f, 1.75f, 1.76f};
    private static final float[] n_3318_d = new float[]{1.72f, 1.72f, 1.72f, 1.72f, 1.72f, 1.73f, 1.74f, 1.76f, 1.8f};
    private static final float[] d_2427_y = new float[]{1.6f, 1.63f, 1.66f, 1.71f, 1.73f, 1.81f, 1.81f, 1.81f, 1.81f};
    private static final float[] z_1737_N = new float[]{1.6f, 1.62f, 1.6f, 1.62f, 1.64f, 1.68f, 1.75f, 1.9f, 2.16f};

    public SuperFirework() {
        super("SuperFirework", ModuleCategory.J_1907_R);
        this.addSettings(this.uskorenieEnabled, this.rezhimUskoreniyaMode, this.xz05Setting, this.xz510Setting, this.xz1015Setting, this.xz1520Setting, this.xz2025Setting, this.xz2530Setting, this.xz3035Setting, this.xz3540Setting, this.xz4045Setting, this.y05Setting, this.y510Setting, this.y1015Setting, this.y1520Setting, this.y2025Setting, this.y2530Setting, this.y3035Setting, this.y3540Setting, this.y4045Setting);
    }

    @Y_1740_V
    public void n_1700_B(s_4990_V event) {
        if (!this.uskorenieEnabled.isEnabled().booleanValue() || SuperFirework.c_3005_b.Y_259_p == null) {
            return;
        }
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().J_1907_R();
        if (aura == null) {
            return;
        }
        float yaw = u_530_F.v_4262_N(SuperFirework.c_3005_b.Y_259_p.p_178_J);
        float pitch = SuperFirework.c_3005_b.Y_259_p.f_4016_n;
        if (aura.w_1484_f() && aura.t_1786_h() != null) {
            yaw = u_530_F.v_4262_N(aura.t_1786_h().t_148_a);
            pitch = aura.t_1786_h().s_956_w;
        }
        if (this.rezhimUskoreniyaMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439")) {
            float speedXZ = this.n_1700_B(yaw);
            float speedY = this.J_1907_R(pitch);
            if (speedY > speedXZ) {
                speedXZ = speedY;
            }
            event.n_1700_B(speedXZ);
            event.G_564_y(speedY);
        } else if (this.rezhimUskoreniyaMode.isMode("Bravo")) {
            float speedXZ = this.J_1907_R(pitch, yaw);
            float speedY = this.v_4262_N(pitch);
            event.n_1700_B(speedXZ);
            event.G_564_y(speedY);
        } else if (this.rezhimUskoreniyaMode.isMode("BravoGrief")) {
            float speedXZ = this.n_1700_B(pitch, yaw);
            float speedY = this.G_564_y(pitch);
            event.n_1700_B(speedXZ);
            event.G_564_y(speedY);
        } else if (this.rezhimUskoreniyaMode.isMode("ReallyWorld")) {
            float speedXZ = this.P_1922_E(yaw);
            float speedY = this.u_1723_Y(pitch);
            if (speedY > speedXZ) {
                speedXZ = speedY;
            }
            event.n_1700_B(speedXZ);
            event.G_564_y(speedY);
        }
        event.n_1700_B(true);
    }

    private float n_1700_B(float yaw) {
        float convertedYaw = this.R_4764_Y(yaw);
        int index = (int)(convertedYaw / 5.0f);
        if (index >= this.t_4043_B.length) {
            index = this.t_4043_B.length - 1;
        }
        if (index < 0) {
            index = 0;
        }
        return ((Float)this.t_4043_B[index].J_1907_R()).floatValue();
    }

    private float J_1907_R(float pitch) {
        float convertedPitch = this.R_4764_Y(pitch);
        int index = (int)(convertedPitch / 5.0f);
        if (index >= this.x_607_J.length) {
            index = this.x_607_J.length - 1;
        }
        if (index < 0) {
            index = 0;
        }
        return ((Float)this.x_607_J[index].J_1907_R()).floatValue();
    }

    private float R_4764_Y(float angle) {
        float absAngle = Math.abs(angle);
        if (absAngle > 90.0f) {
            absAngle = 180.0f - absAngle;
        }
        if (absAngle > 45.0f) {
            absAngle = 90.0f - absAngle;
        }
        return absAngle;
    }

    private static float n_1700_B(float[] table, float bandDegrees) {
        int i = (int)(bandDegrees / 5.0f);
        if (i >= table.length) {
            i = table.length - 1;
        }
        if (i < 0) {
            i = 0;
        }
        return table[i];
    }

    private float n_1700_B(float pitch, float yaw) {
        return SuperFirework.n_1700_B(e_4240_b, this.R_4764_Y(yaw));
    }

    private float G_564_y(float pitch) {
        return SuperFirework.n_1700_B(n_3318_d, this.R_4764_Y(pitch));
    }

    private float P_1922_E(float yaw) {
        return SuperFirework.n_1700_B(d_2427_y, this.R_4764_Y(yaw));
    }

    private float u_1723_Y(float pitch) {
        return SuperFirework.n_1700_B(z_1737_N, this.R_4764_Y(pitch));
    }

    private float J_1907_R(float pitch, float yaw) {
        float absPitch = Math.abs(pitch);
        float absYaw = Math.abs(u_530_F.v_4262_N(yaw) % 90.0f);
        float speed = absPitch >= 38.0f && absPitch <= 52.0f ? 2.0f : (absPitch >= 32.0f && absPitch <= 58.0f ? 1.96f : (absPitch >= 28.0f && absPitch <= 62.0f ? 1.95f : (absYaw >= 29.0f && absYaw <= 61.0f || absPitch >= 29.0f && absPitch <= 61.0f ? 1.963f : (absYaw >= 28.0f && absYaw <= 60.0f || absPitch >= 28.0f && absPitch <= 60.0f ? 1.954f : (absYaw >= 26.0f && absYaw <= 64.0f || absPitch >= 26.0f && absPitch <= 64.0f ? 1.874f : (absYaw >= 24.0f && absYaw <= 66.0f || absPitch >= 24.0f && absPitch <= 66.0f ? 1.75f : (absYaw >= 15.0f && absYaw <= 75.0f || absPitch >= 15.0f && absPitch <= 75.0f ? 1.75f : (absYaw >= 13.0f && absYaw <= 77.0f || absPitch >= 13.0f && absPitch <= 77.0f ? 1.75f : (absYaw >= 12.0f && absYaw <= 78.0f || absPitch >= 12.0f && absPitch <= 78.0f ? 1.75f : (absYaw >= 8.0f && absYaw <= 82.0f || absPitch >= 11.0f && absPitch <= 79.0f ? 1.75f : (absYaw >= 5.0f && absYaw <= 85.0f || absPitch >= 8.0f && absPitch <= 82.0f ? 1.67f : (absYaw <= 90.0f || absPitch <= 90.0f ? 1.67f : 1.66f))))))))))));
        return pitch > 15.0f ? speed - 0.068f : speed;
    }

    private float v_4262_N(float pitch) {
        float absPitch = Math.abs(pitch);
        if (absPitch >= 37.0f && absPitch <= 38.0f) {
            return 2.03f;
        }
        if (absPitch >= 25.0f && absPitch <= 30.0f) {
            return 2.0f;
        }
        if (absPitch >= 35.0f && absPitch <= 45.0f) {
            return 1.99f;
        }
        if (absPitch >= 40.0f && absPitch <= 50.0f) {
            return 1.97f;
        }
        if (absPitch >= 50.0f && absPitch <= 60.0f) {
            return 1.96f;
        }
        if (absPitch >= 51.0f && absPitch <= 61.0f) {
            return 1.85f;
        }
        if (absPitch >= 52.0f && absPitch <= 65.0f) {
            return 1.8f;
        }
        return 1.59f;
    }
}



