/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1306
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.item.HandAnimationEvent;
import code.alphadlc.events.item.SwingDurationEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1306;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class SwingAnimations
extends Module {
    private boolean swimmingAnimation = true;
    private boolean climbAndCrawl = true;
    private boolean mb3DCompat = false;
    private final BooleanSetting hmiEnable = new BooleanSetting("\u041c\u043e\u0434 \u043d\u0430 \u043a\u0440\u0430\u0441\u0438\u0432\u044b\u0435 \u0440\u0443\u043a\u0438", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439 \u0440\u0435\u043d\u0434\u0435\u0440 \u0440\u0443\u043a").setValue(false);
    private final SelectSetting hmiAnimationType = new SelectSetting("\u0412\u0438\u0434 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438", "\u0412\u0438\u0434 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438").value("\u041a\u043b\u0430\u0441\u0441\u0438\u043a", "\u0428\u0430\u0440\u043f").visible(this.hmiEnable::isValue);
    private final MultiSelectSetting hmiClassicSwing = new MultiSelectSetting("\u0421\u0442\u0438\u043b\u044c \u0443\u0434\u0430\u0440\u0430", "\u041a\u0430\u043a\u0438\u0435 \u0443\u0434\u0430\u0440\u044b \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0432 \u043a\u043b\u0430\u0441\u0441\u0438\u0447\u0435\u0441\u043a\u043e\u043c \u0440\u0435\u0436\u0438\u043c\u0435").value("\u0427\u0435\u0440\u0435\u0437 \u043b\u0435\u0432\u043e", "\u0427\u0435\u0440\u0435\u0437 \u043f\u0440\u0430\u0432\u043e").selected("\u0427\u0435\u0440\u0435\u0437 \u043b\u0435\u0432\u043e", "\u0427\u0435\u0440\u0435\u0437 \u043f\u0440\u0430\u0432\u043e").visible(() -> this.hmiEnable.isValue() && this.hmiAnimationType.isSelected("\u041a\u043b\u0430\u0441\u0441\u0438\u043a"));
    private final SliderSettings hmiSmoothness = new SliderSettings("\u041f\u043b\u0430\u0432\u043d\u043e\u0441\u0442\u044c", "\u041f\u043b\u0430\u0432\u043d\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u0443\u0434\u0430\u0440\u0430. 1 \u2014 \u043a\u0430\u043a \u0441\u0435\u0439\u0447\u0430\u0441, 100 \u2014 \u043c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e \u043f\u043b\u0430\u0432\u043d\u043e").setValue(1.0f).range(1.0f, 100.0f).step(1.0f).visible(this.hmiEnable::isValue);
    private final BooleanSetting swingEnabled = new BooleanSetting("\u0410\u043d\u0438\u043c\u0430\u0446\u0438\u044f \u0441\u0432\u0438\u043d\u0433\u0430", "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u044e \u0441\u0432\u0438\u043d\u0433\u0430").setValue(true).visible(() -> !this.hmiEnable.isValue());
    private final SelectSetting swingType = new SelectSetting("\u0422\u0438\u043f \u0441\u0432\u0438\u043d\u0433\u0430", "\u0422\u0438\u043f \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438 \u0441\u0432\u0438\u043d\u0433\u0430").value("Smooth", "Static", "Down", "DropDown", "Poke", "SelfBack", "Feast", "ToBack", "Block", "Akrien", "Break", "Pander", "Slant").visible(() -> !this.hmiEnable.isValue() && this.swingEnabled.isValue());
    private final SliderSettings swingStrength = new SliderSettings("\u0421\u0438\u043b\u0430 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438", "\u0421\u0438\u043b\u0430 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438 \u0441\u0432\u0438\u043d\u0433\u0430").setValue(1.0f).range(0.1f, 3.0f).step(0.01f).visible(() -> !this.hmiEnable.isValue() && this.swingEnabled.isValue() && !this.swingType.isSelected("Pander"));
    private final SliderSettings corner = new SliderSettings("\u0423\u0433\u043e\u043b DropDown", "\u0423\u0433\u043e\u043b \u0434\u043b\u044f DropDown").setValue(12.0f).range(1.0f, 360.0f).step(1.0f).visible(() -> !this.hmiEnable.isValue() && this.swingEnabled.isValue() && this.swingType.isSelected("DropDown"));
    private final SliderSettings slant = new SliderSettings("\u041d\u0430\u043a\u043b\u043e\u043d DropDown", "\u041d\u0430\u043a\u043b\u043e\u043d \u0434\u043b\u044f DropDown").setValue(12.0f).range(1.0f, 360.0f).step(1.0f).visible(() -> !this.hmiEnable.isValue() && this.swingEnabled.isValue() && this.swingType.isSelected("DropDown"));
    private final BooleanSetting smoothEnabled = new BooleanSetting("\u041f\u043b\u0430\u0432\u043d\u0430\u044f \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u044f", "\u0417\u0430\u043c\u0435\u0434\u043b\u0435\u043d\u0438\u0435 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438").setValue(false).visible(() -> !this.hmiEnable.isValue());
    private final SliderSettings slowAnimationSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043b\u0430\u0432\u043d\u043e\u0439 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438").setValue(12.0f).range(1.0f, 50.0f).step(1.0f).visible(() -> !this.hmiEnable.isValue() && this.smoothEnabled.isValue());
    private final BooleanSetting auraTargetOnly = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 Aura", "\u0410\u043d\u0438\u043c\u0430\u0446\u0438\u044f \u0442\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u0446\u0435\u043b\u0438 Aura").setValue(false).visible(() -> !this.hmiEnable.isValue());

    public SwingAnimations() {
        super("SwingAnimations", "SwingAnimations", ModuleCategory.RENDER);
        this.setup(this.hmiEnable, this.hmiAnimationType, this.hmiClassicSwing, this.hmiSmoothness, this.swingEnabled, this.swingType, this.swingStrength, this.corner, this.slant, this.smoothEnabled, this.slowAnimationSpeed, this.auraTargetOnly);
    }

    public static SwingAnimations getInstance() {
        return Instance.get(SwingAnimations.class);
    }

    public boolean isHmiMode() {
        return this.hmiEnable.isValue();
    }

    public boolean shouldRestartSwingOnRetrigger() {
        return this.isState() && (this.hmiEnable.isValue() || this.smoothEnabled.isValue());
    }

    public boolean isOnlyAuraActive() {
        return this.auraTargetOnly.isValue();
    }

    public boolean isSharpAnimation() {
        return this.hmiAnimationType.isSelected("\u0428\u0430\u0440\u043f");
    }

    public boolean shouldAlternateClassicSwordSwing() {
        return !this.isSharpAnimation() && this.hmiClassicSwing.isSelected("\u0427\u0435\u0440\u0435\u0437 \u043b\u0435\u0432\u043e") && this.hmiClassicSwing.isSelected("\u0427\u0435\u0440\u0435\u0437 \u043f\u0440\u0430\u0432\u043e");
    }

    public boolean resolveClassicSwordLeft(boolean alternateLeft) {
        boolean allowLeft = this.hmiClassicSwing.isSelected("\u0427\u0435\u0440\u0435\u0437 \u043b\u0435\u0432\u043e");
        boolean allowRight = this.hmiClassicSwing.isSelected("\u0427\u0435\u0440\u0435\u0437 \u043f\u0440\u0430\u0432\u043e");
        if (allowLeft && allowRight) {
            return alternateLeft;
        }
        if (allowLeft) {
            return true;
        }
        if (allowRight) {
            return false;
        }
        return alternateLeft;
    }

    public float getHmiSmoothness() {
        return this.hmiSmoothness.getValue();
    }

    public int getHmiSwingDuration() {
        float value = this.hmiSmoothness.getValue();
        if (value <= 1.0f) {
            return 6;
        }
        return Math.round(class_3532.method_16439((float)((value - 1.0f) / 99.0f), (float)6.0f, (float)80.0f));
    }

    public float remapHmiSwingProgress(float progress) {
        float t = class_3532.method_15363((float)progress, (float)0.0f, (float)1.0f);
        float value = this.hmiSmoothness.getValue();
        if (value <= 1.0f) {
            return t;
        }
        float amount = (value - 1.0f) / 99.0f;
        float eased = t * t * (3.0f - 2.0f * t);
        return class_3532.method_16439((float)(amount * 0.35f), (float)t, (float)eased);
    }

    public boolean isSwimmingAnimation() {
        return this.swimmingAnimation;
    }

    public boolean isClimbAndCrawl() {
        return this.climbAndCrawl;
    }

    public boolean isMb3DCompat() {
        return this.mb3DCompat;
    }

    private boolean hasAuraTarget() {
        AttackAura aura = AttackAura.getInstance();
        return aura != null && aura.isState() && aura.getTarget() != null && aura.getTarget().method_5805();
    }

    @EventHandler
    public void onSwingDuration(SwingDurationEvent e) {
        if (!this.isState()) {
            return;
        }
        if (this.hmiEnable.isValue()) {
            if (this.hmiSmoothness.getValue() > 1.0f) {
                e.setAnimation(this.getHmiSwingDuration());
                e.cancel();
            }
            return;
        }
        if (this.smoothEnabled.isValue()) {
            e.setAnimation(this.slowAnimationSpeed.getValue());
            e.cancel();
        }
    }

    @EventHandler
    public void onHandAnimation(HandAnimationEvent e) {
        if (!this.isState() || this.hmiEnable.isValue() || !this.swingEnabled.isValue()) {
            return;
        }
        if (this.auraTargetOnly.isValue() && !this.hasAuraTarget()) {
            return;
        }
        if (SwingAnimations.mc.field_1724 != null) {
            class_1306 expectedSwingArm = SwingAnimations.mc.field_1724.method_6068();
            if (e.getArm() != expectedSwingArm) {
                return;
            }
        }
        class_4587 matrices = e.getMatrices();
        float swingProgress = e.getSwingProgress();
        float equipProgress = e.getEquipProgress();
        int i = e.getArm() == class_1306.field_6183 ? 1 : -1;
        float strength = this.swingStrength.getValue();
        float sin1 = class_3532.method_15374((float)(swingProgress * swingProgress * (float)Math.PI));
        float sin2 = class_3532.method_15374((float)(class_3532.method_15355((float)swingProgress) * (float)Math.PI));
        boolean handled = true;
        switch (this.swingType.getSelected()) {
            case "Down": {
                matrices.method_46416((float)i * 0.56f, -0.32f, -0.72f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(76 * i)));
                matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * -5.0f * strength));
                matrices.method_22907(class_7833.field_40713.rotationDegrees(sin2 * -100.0f * strength));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -155.0f * strength));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-100.0f));
                break;
            }
            case "Poke": {
                float anim = (float)Math.sin((double)swingProgress * 1.5707963267948966 * 2.0);
                float tilt = strength / 3.0f;
                matrices.method_46416((float)i * 0.56f, -0.52f, -0.72f);
                matrices.method_46416(0.0f, 0.0f, tilt * -anim);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(75.0f * (float)i));
                matrices.method_22907(class_7833.field_40718.rotationDegrees((-75.0f * (strength / 4.0f) * anim - 60.0f) * (float)i));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-75.0f));
                break;
            }
            case "Static": {
                matrices.method_46416((float)i * 0.56f, -0.42f, -0.72f);
                matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -60.0f * strength));
                matrices.method_22904(0.0, -0.1, 0.0);
                break;
            }
            case "Feast": {
                matrices.method_46416((float)i * 0.56f, -0.32f, -0.72f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(30 * i)));
                matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * 75.0f * (float)i * strength));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -65.0f * strength));
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(30 * i)));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0f));
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(35 * i)));
                break;
            }
            case "Akrien": {
                matrices.method_46416((float)i * 0.65f, -0.32f, -0.72f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(76 * i)));
                matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * -5.0f * strength));
                matrices.method_22907(class_7833.field_40713.rotationDegrees(sin2 * -100.0f * strength));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -155.0f * strength));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-100.0f));
                matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * 25.0f * strength));
                matrices.method_22907(class_7833.field_40713.rotationDegrees(sin2 * -25.0f * strength));
                matrices.method_22907(class_7833.field_40713.rotationDegrees(sin1 * 15.0f * strength));
                matrices.method_46416(sin2 * 0.18f * strength, sin2 * 0.59f * strength, 0.0f);
                break;
            }
            case "Smooth": {
                this.applySwingOffset(matrices, i, swingProgress, strength);
                break;
            }
            case "Block": {
                if (swingProgress > 0.0f) {
                    float g = class_3532.method_15374((float)(class_3532.method_15355((float)swingProgress) * (float)Math.PI));
                    matrices.method_46416(0.56f * (float)i, equipProgress * -0.2f - 0.5f, -0.7f);
                    matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * i)));
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(g * -85.0f * strength));
                    matrices.method_46416(-0.1f * (float)i, 0.28f, 0.2f);
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(-85.0f));
                    break;
                }
                float n = -0.4f * class_3532.method_15374((float)(class_3532.method_15355((float)swingProgress) * (float)Math.PI));
                float m = 0.2f * class_3532.method_15374((float)(class_3532.method_15355((float)swingProgress) * ((float)Math.PI * 2)));
                float f1 = -0.2f * class_3532.method_15374((float)(swingProgress * (float)Math.PI));
                matrices.method_46416(n * (float)i * strength, m * strength, f1 * strength);
                this.applyEquipOffset(matrices, i, equipProgress);
                this.applySwingOffset(matrices, i, swingProgress, strength);
                break;
            }
            case "ToBack": {
                float g = class_3532.method_15374((float)(class_3532.method_15355((float)swingProgress) * (float)Math.PI));
                matrices.method_46416(0.65f * (float)i, -0.45f, -0.9f);
                matrices.method_22907(class_7833.field_40714.rotationDegrees(50.0f));
                matrices.method_22907(class_7833.field_40716.rotationDegrees((-30.0f * (1.0f - g * strength) - 30.0f) * (float)i));
                matrices.method_22907(class_7833.field_40718.rotationDegrees(110.0f * (float)i));
                break;
            }
            case "SelfBack": {
                float anim = (float)Math.sin((double)swingProgress * 1.5707963267948966 * 2.0);
                matrices.method_46416(0.65f * (float)i, -0.3f, -0.8f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(90 * i)));
                matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-70 * i)));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-100.0f - 60.0f * strength * anim));
                break;
            }
            case "Break": 
            case "\u0411\u0440\u0438\u043a": {
                matrices.method_46416(0.66f * (float)i, -0.3f, -0.38f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(270 * i)));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * 10.0f * strength));
                matrices.method_22905(0.5f, 0.5f, 0.5f);
                matrices.method_46416(-0.1f * (float)i, 0.2f, 0.0f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(-10.0f * (float)i));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
                matrices.method_22907(class_7833.field_40716.rotationDegrees(-105.0f * (float)i));
                break;
            }
            case "DropDown": {
                float anim = (float)Math.sin((double)swingProgress * 1.5707963267948966 * 2.0);
                this.applyEquipOffset(matrices, i, 0.0f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(80.0f));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(this.corner.getValue()));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-this.slant.getValue() * anim * strength));
                break;
            }
            case "Pander": {
                float panderAnim = class_3532.method_15374((float)(swingProgress * (float)Math.PI));
                float panderF = 1.0f - equipProgress;
                matrices.method_46416((float)i * 0.56f, -0.52f, -0.72f);
                matrices.method_46416((0.3f - panderAnim * 0.15f) * (float)i, 0.2f - panderF * 0.12f, -0.15f - panderAnim * 0.13f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((76.0f - 10.0f * panderAnim) * (float)i));
                matrices.method_22907(class_7833.field_40718.rotationDegrees((-16.0f - 8.0f * panderAnim) * (float)i));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-83.0f - 26.0f * panderAnim));
                break;
            }
            case "Slant": {
                float anim = (float)Math.sin((double)swingProgress * 1.5707963267948966 * 2.0);
                float rotate = 35.0f * strength;
                matrices.method_46416((float)i * 0.56f, -0.52f, -0.72f);
                matrices.method_46416(0.0f, 0.0f, -0.3f * anim * strength);
                matrices.method_22907(class_7833.field_40714.rotationDegrees(anim * -rotate));
                matrices.method_22907(class_7833.field_40718.rotationDegrees(anim * rotate));
                break;
            }
            default: {
                handled = false;
            }
        }
        if (handled) {
            e.cancel();
        }
    }

    private void applyEquipOffset(class_4587 matrices, int i, float equipProgress) {
        matrices.method_46416((float)i * 0.56f, -0.52f + equipProgress * -0.6f, -0.72f);
    }

    private void applySwingOffset(class_4587 matrices, int i, float swingProgress, float strength) {
        float f = class_3532.method_15374((float)(swingProgress * swingProgress * (float)Math.PI));
        matrices.method_46416(0.56f * (float)i, -0.52f, -0.72f);
        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * (45.0f + f * -20.0f * strength)));
        float g = class_3532.method_15374((float)(class_3532.method_15355((float)swingProgress) * (float)Math.PI));
        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)i * g * -20.0f * strength));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(g * -80.0f * strength));
        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * -45.0f));
    }
}
