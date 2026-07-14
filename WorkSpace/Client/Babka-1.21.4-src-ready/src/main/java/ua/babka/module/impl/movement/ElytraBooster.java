package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import net.minecraft.util.math.MathHelper;
import ua.babka.event.EventHUD;
import ua.babka.event.FireworkEvent;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.font.Fonts;
import ua.babka.util.Instance;

@ModuleInformation(moduleName="SuperFirework", moduleDesc="Ускорение фейерверками", moduleCategory=ModuleCategory.MOVEMENT)
public final class ElytraBooster
extends Module {
    public static final ElytraBooster INSTANCE = new ElytraBooster();
    private final BooleanSetting showOverlay = new BooleanSetting("Показать инфо", true);
    private final BooleanSetting boost = new BooleanSetting("Ускорение", true);
    private final ModeSetting mode = new ModeSetting("Режим ускорения", "Bravo", "Bravo", "Кастомный", "ReallyWorld").setVisible(this.boost::getValue);
    private final SliderSetting xz0_5 = new SliderSetting("XZ 0-5°", 1.52f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz5_10 = new SliderSetting("XZ 5-10°", 1.53f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz10_15 = new SliderSetting("XZ 10-15°", 1.54f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz15_20 = new SliderSetting("XZ 15-20°", 1.55f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz20_25 = new SliderSetting("XZ 20-25°", 1.56f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz25_30 = new SliderSetting("XZ 25-30°", 1.57f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz30_35 = new SliderSetting("XZ 30-35°", 1.58f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz35_40 = new SliderSetting("XZ 35-40°", 1.59f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting xz40_45 = new SliderSetting("XZ 40-45°", 1.6f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y0_5 = new SliderSetting("Y 0-5°", 1.51f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y5_10 = new SliderSetting("Y 5-10°", 1.52f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y10_15 = new SliderSetting("Y 10-15°", 1.53f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y15_20 = new SliderSetting("Y 15-20°", 1.54f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y20_25 = new SliderSetting("Y 20-25°", 1.55f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y25_30 = new SliderSetting("Y 25-30°", 1.56f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y30_35 = new SliderSetting("Y 30-35°", 1.57f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y35_40 = new SliderSetting("Y 35-40°", 1.58f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting y40_45 = new SliderSetting("Y 40-45°", 1.59f, 1.5, 3.0, 0.01f).setVisible(() -> this.boost.getValue() && this.mode.is("Кастомный"));
    private final SliderSetting[] xzSliders = new SliderSetting[]{this.xz0_5, this.xz5_10, this.xz10_15, this.xz15_20, this.xz20_25, this.xz25_30, this.xz30_35, this.xz35_40, this.xz40_45};
    private final SliderSetting[] ySliders = new SliderSetting[]{this.y0_5, this.y5_10, this.y10_15, this.y15_20, this.y20_25, this.y25_30, this.y30_35, this.y35_40, this.y40_45};
    private static final float[] REALLY_WORLD_XZ = new float[]{1.6f, 1.62f, 1.65f, 1.67f, 1.75f, 1.8f, 1.8f, 1.8f, 1.8f};
    private static final float[] REALLY_WORLD_Y = new float[]{1.6f, 1.6f, 1.6f, 1.62f, 1.63f, 1.67f, 1.77f, 2.02f, 2.07f};
    private static final float[] Bravo_XZ = new float[]{1.65f, 1.69f, 1.7f, 1.75f, 1.79f, 1.83f, 1.86f, 2.01f, 2.01f};
    private static final float[] Bravo_Y = new float[]{1.64f, 1.67f, 1.75f, 1.82f, 1.84f, 1.86f, 1.9f, 2.01f, 2.01f};

    public BoostValues computeBoost(float yaw, float pitch) {
        if (!this.boost.getValue() || this.mc.player == null) {
            return new BoostValues(1.5f, 1.5f);
        }
        AttackAura aura = Instance.get(AttackAura.class);
        if (aura != null && aura.isEnabled()) {
            yaw = MathHelper.wrapDegrees((float)aura.lastYaw);
            pitch = aura.lastPitch;
        }
        if (pitch < -75.0f) {
            return new BoostValues(1.5f, 1.5f);
        }
        if (this.mode.is("Кастомный")) {
            float speedXZ = this.getCustomSpeed(yaw, this.xzSliders);
            float speedY = this.getCustomSpeed(pitch, this.ySliders);
            if (speedY > speedXZ) {
                speedXZ = speedY;
            }
            return new BoostValues(speedXZ, speedY);
        }
        if (this.mode.is("Bravo")) {
            float speedXZ = ElytraBooster.getTableSpeed(Bravo_XZ, yaw, 9);
            float speedY = ElytraBooster.getTableSpeed(Bravo_Y, pitch, 9);
            if (speedY > speedXZ) {
                speedXZ = speedY;
            }
            return new BoostValues(speedXZ, speedY);
        }
        if (this.mode.is("ReallyWorld")) {
            float speedXZ = ElytraBooster.getTableSpeed(REALLY_WORLD_XZ, yaw, 9);
            float speedY = ElytraBooster.getTableSpeed(REALLY_WORLD_Y, pitch, 9);
            if (speedY > speedXZ) {
                speedXZ = speedY;
            }
            return new BoostValues(speedXZ, speedY);
        }
        return new BoostValues(1.5f, 1.5f);
    }

    private float getCustomSpeed(float angle, SliderSetting[] sliders) {
        float converted = this.convertAngle(angle);
        int index = (int)(converted / 5.0f);
        if (index >= sliders.length) {
            index = sliders.length - 1;
        }
        if (index < 0) {
            index = 0;
        }
        return (float)sliders[index].getValue();
    }

    private float convertAngle(float angle) {
        float absAngle = Math.abs(angle);
        if (absAngle > 90.0f) {
            absAngle = 180.0f - absAngle;
        }
        if (absAngle > 45.0f) {
            absAngle = 90.0f - absAngle;
        }
        return absAngle;
    }

    private static float getTableSpeed(float[] table, float angle, int tableLength) {
        int index = (int)(ElytraBooster.convertAngleStatic(angle) / 5.0f);
        if (index >= tableLength) {
            index = tableLength - 1;
        }
        if (index < 0) {
            index = 0;
        }
        return table[index];
    }

    private static float convertAngleStatic(float angle) {
        float absAngle = Math.abs(angle);
        if (absAngle > 90.0f) {
            absAngle = 180.0f - absAngle;
        }
        if (absAngle > 45.0f) {
            absAngle = 90.0f - absAngle;
        }
        return absAngle;
    }

    @Subscribe
    private void onHud(EventHUD event) {
        if (!this.showOverlay.getValue() || this.mc.player == null) {
            return;
        }
        float yaw = this.mc.player.getYaw();
        AttackAura aura = Instance.get(AttackAura.class);
        if (aura != null && aura.isEnabled()) {
            yaw = MathHelper.wrapDegrees((float)aura.lastYaw);
        }
        double dX = this.mc.player.getX() - this.mc.player.prevX;
        double dY = this.mc.player.getY() - this.mc.player.prevY;
        double dZ = this.mc.player.getZ() - this.mc.player.prevZ;
        double bps = Math.hypot(Math.hypot(dX, dZ), dY) * 20.0;
        float converted = this.convertAngle(yaw);
        int idx = (int)(converted / 5.0f);
        if (idx >= 9) {
            idx = 8;
        }
        if (idx < 0) {
            idx = 0;
        }
        String angleText = idx * 5 + "-" + (idx * 5 + 5) + "°";
        String bpsText = String.format(Locale.US, "%.1f bps", bps);
        int color = ColorProvider.getColorClient();
        int sw = this.mc.getWindow().getScaledWidth();
        int sh = this.mc.getWindow().getScaledHeight();
        float angleW = Fonts.SFBOLD.get().getWidth(angleText, 8.0f);
        float bpsW = Fonts.SFBOLD.get().getWidth(bpsText, 8.0f);
        float cx = (float)sw / 2.0f;
        float yOff = (float)sh / 2.0f + 12.0f;
        DrawUtil.drawText(Fonts.SFBOLD.get(), angleText, cx - angleW / 2.0f, yOff, ColorProvider.setAlpha(color, 220), 8.0f);
        DrawUtil.drawText(Fonts.SFBOLD.get(), bpsText, cx - bpsW / 2.0f, yOff + 11.0f, ColorProvider.setAlpha(color, 220), 8.0f);
    }

    @Subscribe
    private void onFirework(FireworkEvent event) {
        if (this.mc.player == null || !this.boost.getValue() || event.getBoostedEntity() != this.mc.player) {
            return;
        }
        float yaw = this.mc.player.getYaw();
        float pitch = this.mc.player.getPitch();
        AttackAura aura = Instance.get(AttackAura.class);
        if (aura != null && aura.isEnabled()) {
            yaw = MathHelper.wrapDegrees((float)aura.lastYaw);
            pitch = aura.lastPitch;
        }
        BoostValues values = this.computeBoost(yaw, pitch);
        event.setSpeed(Math.max(values.speedXZ(), values.speedY()));
    }

    public record BoostValues(float speedXZ, float speedY) {
    }
}

