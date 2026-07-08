/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_10366
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_433
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_5636
 *  net.minecraft.class_5944
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.display.render.shader.SkyShaderUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_10366;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_433;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_5636;
import net.minecraft.class_5944;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public class ShaderFog
extends Module {
    private final SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0420\u0435\u0436\u0438\u043c \u0448\u0435\u0439\u0434\u0435\u0440\u0430 \u043d\u0435\u0431\u0430").value("\u0412\u043e\u0434\u0430", "\u041a\u0430\u0443\u0441\u0442\u0438\u043a\u0430");
    private final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u0448\u0435\u0439\u0434\u0435\u0440\u0430 \u0442\u0443\u043c\u0430\u043d\u0430").value(new Color(150, 130, 255).getRGB()).presets(new Color(150, 130, 255).getRGB(), new Color(120, 180, 255).getRGB(), new Color(235, 120, 220).getRGB(), new Color(255, 255, 255).getRGB());
    private final SliderSettings speed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438").setValue(1.0f).range(0.1f, 5.0f);
    private final SliderSettings scale = new SliderSettings("\u041c\u0430\u0441\u0448\u0442\u0430\u0431", "\u041c\u0430\u0441\u0448\u0442\u0430\u0431 \u0443\u0437\u043e\u0440\u0430").setValue(5.0f).range(1.0f, 20.0f);
    private final SliderSettings intensity = new SliderSettings("\u0418\u043d\u0442\u0435\u043d\u0441\u0438\u0432\u043d\u043e\u0441\u0442\u044c", "\u0418\u043d\u0442\u0435\u043d\u0441\u0438\u0432\u043d\u043e\u0441\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442\u0430").setValue(0.01f).range(0.001f, 0.05f);
    private final SliderSettings alpha = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442\u0430").setValue(1.0f).range(0.3f, 1.0f);
    private static final class_10156 WATER_SHADER = new class_10156(SkyShaderUtil.getShader("post/sky/water"), class_290.field_1592, class_10149.field_53930);
    private static final class_10156 CAUSTIC_SHADER = new class_10156(SkyShaderUtil.getShader("post/sky/caustic"), class_290.field_1592, class_10149.field_53930);
    private long startMillis = -1L;

    public static ShaderFog getInstance() {
        return Instance.get(ShaderFog.class);
    }

    public ShaderFog() {
        super("Shader Fog", ModuleCategory.RENDER);
        this.setup(this.mode, this.color, this.speed, this.scale, this.intensity, this.alpha);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.startMillis = -1L;
    }

    public static boolean shouldReplaceVanillaSky() {
        ShaderFog module = ShaderFog.getInstance();
        if (module == null || !module.isState()) {
            return false;
        }
        return !ShaderFog.isBlockingScreen(class_310.method_1551().field_1755);
    }

    private static boolean isBlockingScreen(class_437 screen) {
        return screen instanceof class_433;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void renderSkyShader() {
        if (!this.isState()) {
            return;
        }
        if (this.startMillis < 0L) {
            this.startMillis = System.currentTimeMillis();
        }
        class_310 mc = class_310.method_1551();
        if (mc.field_1724 == null || mc.field_1687 == null) {
            return;
        }
        if (ShaderFog.isBlockingScreen(mc.field_1755)) {
            return;
        }
        class_4184 guardCam = mc.field_1773.method_19418();
        if (guardCam.method_19334() != class_5636.field_27888) {
            return;
        }
        float time = (float)(System.currentTimeMillis() - this.startMillis) / 1000.0f;
        float fw = mc.method_22683().method_4489();
        float fh = mc.method_22683().method_4506();
        Color tint = new Color(this.color.getColor());
        float cr = (float)tint.getRed() / 255.0f;
        float cg = (float)tint.getGreen() / 255.0f;
        float cb = (float)tint.getBlue() / 255.0f;
        class_10156 key = this.mode.isSelected("\u041a\u0430\u0443\u0441\u0442\u0438\u043a\u0430") ? CAUSTIC_SHADER : WATER_SHADER;
        Matrix4f savedProj = new Matrix4f((Matrix4fc)RenderSystem.getProjectionMatrix());
        try {
            class_5944 shader = RenderSystem.setShader((class_10156)key);
            if (shader == null) {
                return;
            }
            if (shader.method_34582("uTime") != null) {
                shader.method_34582("uTime").method_1251(time);
            }
            if (shader.method_34582("uResolution") != null) {
                shader.method_34582("uResolution").method_1255(fw, fh);
            }
            if (shader.method_34582("uColor") != null) {
                shader.method_34582("uColor").method_1249(cr, cg, cb);
            }
            if (shader.method_34582("uAlpha") != null) {
                shader.method_34582("uAlpha").method_1251(this.alpha.getValue());
            }
            if (shader.method_34582("uSpeed") != null) {
                shader.method_34582("uSpeed").method_1251(this.speed.getValue());
            }
            if (shader.method_34582("uScale") != null) {
                shader.method_34582("uScale").method_1251(this.scale.getValue());
            }
            if (shader.method_34582("uIntensity") != null) {
                shader.method_34582("uIntensity").method_1251(this.intensity.getValue());
            }
            class_4184 cam = mc.field_1773.method_19418();
            float yawRad = (float)Math.toRadians(-cam.method_19330());
            float pitchRad = (float)Math.toRadians(cam.method_19329());
            if (shader.method_34582("uCameraDir") != null) {
                shader.method_34582("uCameraDir").method_1255(yawRad, pitchRad);
            }
            if (shader.method_34582("uFov") != null) {
                shader.method_34582("uFov").method_1251((float)((Integer)mc.field_1690.method_41808().method_41753()).intValue());
            }
            RenderSystem.setProjectionMatrix((Matrix4f)new Matrix4f(), (class_10366)class_10366.field_54954);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            RenderSystem.depthFunc((int)514);
            RenderSystem.depthMask((boolean)false);
            RenderSystem.disableCull();
            Matrix4f identity = new Matrix4f();
            class_287 buf = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1592);
            buf.method_22918(identity, -1.0f, -1.0f, 1.0f);
            buf.method_22918(identity, 1.0f, -1.0f, 1.0f);
            buf.method_22918(identity, 1.0f, 1.0f, 1.0f);
            buf.method_22918(identity, -1.0f, 1.0f, 1.0f);
            class_286.method_43433((class_9801)buf.method_60800());
        }
        finally {
            RenderSystem.setProjectionMatrix((Matrix4f)savedProj, (class_10366)class_10366.field_54953);
            RenderSystem.setShader((class_10156)class_10142.field_53876);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.depthFunc((int)515);
            RenderSystem.depthMask((boolean)true);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
        }
    }

    public void renderSky(class_4587 matrices) {
        this.renderSkyShader();
    }

    public boolean shouldCancelClouds() {
        return this.isState();
    }
}
