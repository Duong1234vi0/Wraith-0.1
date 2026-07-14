package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import ua.babka.event.EventAttack;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="Swing Animations", moduleDesc="Кастомные анимации удара", moduleCategory=ModuleCategory.RENDER)
public final class SwingAnimations
extends Module {
    public final BooleanSetting hmiEnable = new BooleanSetting("Мод на красивые руки", false);
    public final ModeSetting hmiAnimationType = new ModeSetting("Вид анимации", "Классик", "Классик", "Шарп").setVisible(() -> this.hmiEnable.getValue());
    public final SliderSetting hmiSmoothness = new SliderSetting("Плавность анимации", 1.0, 0.35, 2.5, 0.05).setVisible(() -> this.hmiEnable.getValue());
    public boolean swimmingAnimation = true;
    public boolean climbAndCrawl = true;
    public boolean mb3DCompat = false;
    public final ModeSetting animationMode = new ModeSetting("Режим", "Smooth", "Smooth", "Self", "Self2", "Down", "Forward", "Touch", "Pander", "Curt", "BlockHit").setVisible(() -> !this.hmiEnable.getValue());
    public final SliderSetting swingPower = new SliderSetting("Сила", 5.0, 1.0, 10.0, 1.0);
    public final SliderSetting speed = new SliderSetting("Скорость", 7.0, 0.0, 10.0, 1.0);
    public final SliderSetting angle = new SliderSetting("Угол", 0.0, 0.0, 360.0, 1.0);
    public final BooleanSetting onlyOnHit = new BooleanSetting("Только при Ударе", false);
    public final BooleanSetting swapHands = new BooleanSetting("Свап рук", false).setVisible(() -> !this.hmiEnable.getValue());
    private volatile long lastHitTime = 0L;

    public SwingAnimations() {
        this.swingPower.setVisible(() -> !this.hmiEnable.getValue() && !this.animationMode.is("BlockHit") && !this.animationMode.is("Pander") && !this.animationMode.is("Curt"));
        this.speed.setVisible(() -> !this.hmiEnable.getValue());
        this.angle.setVisible(() -> !this.hmiEnable.getValue() && (this.animationMode.is("Self") || this.animationMode.is("Self2")));
        this.onlyOnHit.setVisible(() -> !this.hmiEnable.getValue());
    }

    @Subscribe
    private void onAttack(EventAttack event) {
        this.lastHitTime = System.currentTimeMillis();
    }

    public boolean shouldUseCustom() {
        if (this.hmiEnable.getValue()) {
            return false;
        }
        return !this.onlyOnHit.getValue() || System.currentTimeMillis() - this.lastHitTime < 600L;
    }

    public void renderSwordAnimation(MatrixStack matrices, float swingProgress, float equipProgress, Arm arm) {
        float anim = MathHelper.sin((float)(swingProgress * 1.5707964f * 2.0f));
        float sin2 = MathHelper.sin((float)(MathHelper.sqrt((float)swingProgress) * (float)Math.PI));
        int i2 = arm == Arm.RIGHT ? 1 : -1;
        switch (this.animationMode.getValue()) {
            case "Smooth": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                float f2 = this.swingPower.getFloatValue() * 10.0f;
                float g2 = MathHelper.sin((float)(swingProgress * swingProgress * (float)Math.PI));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i2 * (45.0f + g2 * (-f2 / 4.0f))));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float)i2 * (sin2 * -(f2 / 4.0f))));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(sin2 * -f2));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i2 * -45.0f));
                break;
            }
            case "Self": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-30.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-this.angle.getFloatValue() - this.swingPower.getFloatValue() * 10.0f * anim));
                break;
            }
            case "Self2": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-60.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-this.angle.getFloatValue() - this.swingPower.getFloatValue() * 10.0f * anim));
                break;
            }
            case "Forward": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                float f2 = 35.0f;
                matrices.translate(0.0, 0.0, -0.3 * (double)sin2);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(sin2 * -f2));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(sin2 * f2));
                break;
            }
            case "Down": {
                matrices.translate(0.56f * (float)i2, -0.52f - anim * this.swingPower.getFloatValue() / 24.0f, -0.72f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-30.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
                break;
            }
            case "Touch": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                matrices.scale(1.0f, 1.0f, 1.0f + anim * this.swingPower.getFloatValue() / 4.0f);
                matrices.translate(0.0f, 0.0f, -0.265f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-100.0f));
                break;
            }
            case "Curt": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                float sqrtSwing = MathHelper.sqrt((float)swingProgress);
                float sinMain = MathHelper.sin((float)(sqrtSwing * (float)Math.PI));
                float sinExtra = MathHelper.sin((float)(swingProgress * (float)Math.PI));
                matrices.translate((0.4f - sinMain * 0.2f) * (float)i2, -0.2f + sinMain * 0.3f, -0.5f - sinExtra * 0.2f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(91.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((-40.0f + sinMain * -100.0f) * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-60.0f));
                break;
            }
            case "Pander": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                matrices.scale(0.8f, 0.8f, 0.8f);
                float equip = 1.0f - MathHelper.lerp((float)this.mc.getRenderTickCounter().getTickDelta(true), (float)this.mc.gameRenderer.firstPersonRenderer.prevEquipProgressMainHand, (float)this.mc.gameRenderer.firstPersonRenderer.equipProgressMainHand);
                matrices.translate((0.3 - (double)anim * 0.15) * (double)i2, (double)(0.2f - equip * 0.12f), (double)(-0.15f - anim * 0.13f));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((76.0f - 10.0f * anim) * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((-16.0f - 8.0f * anim) * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-83.0f - 26.0f * anim));
                break;
            }
            case "BlockHit": {
                matrices.translate(0.56f * (float)i2, -0.52f, -0.72f);
                float sinProg = MathHelper.sin((float)(swingProgress * swingProgress * (float)Math.PI));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45.0f * (float)i2));
                float sqrtProg = MathHelper.sin((float)(MathHelper.sqrt((float)swingProgress) * (float)Math.PI));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(sinProg * -20.0f));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(sqrtProg * -20.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(sqrtProg * -80.0f));
                matrices.translate(0.4f * (float)i2, 0.2f, 0.2f);
                matrices.translate(-0.5f * (float)i2, 0.08f, 0.0f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(20.0f * (float)i2));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-80.0f));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(20.0f * (float)i2));
            }
        }
    }
}

