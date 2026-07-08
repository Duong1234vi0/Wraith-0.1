/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_276
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_5498
 *  net.minecraft.class_5636
 *  net.minecraft.class_6367
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.display.render.shader.HandBlurRenderer;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_5498;
import net.minecraft.class_5636;
import net.minecraft.class_6367;

public class HandShader
extends Module {
    public final SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0420\u0435\u0436\u0438\u043c \u044d\u0444\u0444\u0435\u043a\u0442\u0430 \u0448\u0435\u0439\u0434\u0435\u0440\u0430").value("\u041f\u043e\u043b\u043d\u044b\u0439", "\u041d\u0435\u043f\u043e\u043b\u043d\u044b\u0439", "\u0412\u0435\u0431-\u0448\u0435\u0439\u0434\u0435\u0440", "\u041f\u043b\u0430\u0437\u043c\u0430");
    public final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u0448\u0435\u0439\u0434\u0435\u0440\u0430").value(new Color(150, 130, 255).getRGB()).presets(new Color(150, 130, 255).getRGB(), new Color(120, 180, 255).getRGB(), new Color(235, 120, 220).getRGB(), new Color(255, 255, 255).getRGB());
    public final SliderSettings blurRadius = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441 \u0440\u0430\u0437\u043c\u044b\u0442\u0438\u044f", "\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f \u0432\u043e\u043a\u0440\u0443\u0433 \u0440\u0443\u043a\u0438").setValue(18.0f).range(1.0f, 50.0f).visible(() -> this.mode.isSelected("\u041d\u0435\u043f\u043e\u043b\u043d\u044b\u0439"));
    public final SliderSettings opacity = new SliderSettings("\u041d\u0435\u043f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442\u0430").setValue(0.6f).range(0.0f, 1.0f);
    public final SliderSettings threshold = new SliderSettings("\u041f\u043e\u0440\u043e\u0433", "\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u044f\u0440\u043a\u043e\u0441\u0442\u044c \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f").setValue(0.03f).range(0.005f, 0.15f).visible(() -> this.mode.isSelected("\u041d\u0435\u043f\u043e\u043b\u043d\u044b\u0439"));
    public final SliderSettings shaderSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438 \u0448\u0435\u0439\u0434\u0435\u0440\u0430").setValue(1.0f).range(0.1f, 3.0f).visible(() -> this.mode.isSelected("\u0412\u0435\u0431-\u0448\u0435\u0439\u0434\u0435\u0440") || this.mode.isSelected("\u041f\u043b\u0430\u0437\u043c\u0430"));
    public final SliderSettings shaderScale = new SliderSettings("\u041c\u0430\u0441\u0448\u0442\u0430\u0431", "\u041f\u0440\u0438\u0431\u043b\u0438\u0436\u0435\u043d\u0438\u0435 \u0438\u043b\u0438 \u043e\u0442\u0434\u0430\u043b\u0435\u043d\u0438\u0435 \u0443\u0437\u043e\u0440\u0430").setValue(1.0f).range(0.25f, 4.0f).visible(() -> this.mode.isSelected("\u0412\u0435\u0431-\u0448\u0435\u0439\u0434\u0435\u0440") || this.mode.isSelected("\u041f\u043b\u0430\u0437\u043c\u0430"));
    private class_276 savedBefore = null;
    private class_276 savedAfter = null;
    private class_6367 outputFbo = null;
    private boolean captureReady = false;

    public static HandShader getInstance() {
        return Instance.get(HandShader.class);
    }

    public HandShader() {
        super("Hand Shader", ModuleCategory.RENDER);
        this.setup(this.mode, this.color, this.blurRadius, this.opacity, this.threshold, this.shaderSpeed, this.shaderScale);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.invalidateCapture();
    }

    public void invalidateCapture() {
        this.savedBefore = null;
        this.savedAfter = null;
        this.outputFbo = null;
        this.captureReady = false;
    }

    public void beginHandCapture() {
        this.captureReady = false;
    }

    public boolean shouldRender() {
        class_310 mc = class_310.method_1551();
        if (!this.isState() || mc.field_1724 == null) {
            return false;
        }
        if (mc.field_1690.method_31044() != class_5498.field_26664) {
            return false;
        }
        class_4184 camera = mc.field_1773.method_19418();
        if (camera.method_19334() != class_5636.field_27888) {
            return false;
        }
        if (mc.field_1724.method_5869() || mc.field_1724.method_5771()) {
            return false;
        }
        return !mc.field_1724.method_5681() || !mc.field_1724.method_5799();
    }

    public void onShaderCapture(class_276 before, class_276 after) {
        this.savedBefore = before;
        this.savedAfter = after;
        this.captureReady = true;
    }

    public void renderCapturedEffect() {
        if (!this.shouldRender() || !this.captureReady) {
            return;
        }
        class_310 mc = class_310.method_1551();
        class_276 mainFbo = mc.method_1522();
        if (this.savedAfter == null || mainFbo == null) {
            return;
        }
        if (this.savedAfter.method_30277() <= 0) {
            return;
        }
        int depthAttachment = mainFbo.method_30278();
        if (depthAttachment <= 0) {
            return;
        }
        if (this.outputFbo == null) {
            this.outputFbo = new class_6367(this.savedAfter.field_1482, this.savedAfter.field_1481, false);
        }
        if (this.outputFbo.field_1482 != this.savedAfter.field_1482 || this.outputFbo.field_1481 != this.savedAfter.field_1481) {
            this.outputFbo.method_1234(this.savedAfter.field_1482, this.savedAfter.field_1481);
        }
        float[] tint = HandShader.rgba(this.color.getColor(), this.opacity.getValue());
        float[] tint2 = HandShader.rgba(ThemeManager.secondaryColor.getColor(), this.opacity.getValue());
        float time = (float)(System.currentTimeMillis() % 100000L) / 1000.0f * this.shaderSpeed.getValue();
        int modeIndex = switch (this.mode.getSelected()) {
            case "\u041d\u0435\u043f\u043e\u043b\u043d\u044b\u0439" -> 1;
            case "\u0412\u0435\u0431-\u0448\u0435\u0439\u0434\u0435\u0440" -> 2;
            case "\u041f\u043b\u0430\u0437\u043c\u0430" -> 3;
            default -> 0;
        };
        new HandBlurRenderer(this.savedAfter, this.outputFbo, depthAttachment, this.blurRadius.getValue(), this.threshold.getValue(), tint, tint2, modeIndex, time, this.shaderScale.getValue()).renderPostProcess();
    }

    private static float[] rgba(int argb, float alphaOverride) {
        Color c = new Color(argb, true);
        return new float[]{(float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, alphaOverride};
    }
}
