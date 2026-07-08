/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_276
 *  net.minecraft.class_284
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_5944
 *  net.minecraft.class_6367
 *  net.minecraft.class_9801
 */
package code.alphadlc.utils.display.render.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_276;
import net.minecraft.class_284;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5944;
import net.minecraft.class_6367;
import net.minecraft.class_9801;

public class HandBlurRenderer {
    private static final class_10156 HAND_BLUR_KEY = new class_10156(class_2960.method_60655((String)"alphadlc", (String)"core/hand_blur"), class_290.field_29336, class_10149.field_53930);
    private final class_276 sourceFbo;
    private final class_6367 outputFbo;
    private final int depthAttachment;
    private final float blurRadius;
    private final float threshold;
    private final float[] tintColor;
    private final float[] tintColor2;
    private final int mode;
    private final float time;
    private final float shaderScale;

    public HandBlurRenderer(class_276 sourceFbo, class_6367 outputFbo, int depthAttachment, float blurRadius, float threshold, float[] tintColor, float[] tintColor2, int mode, float time, float shaderScale) {
        this.sourceFbo = sourceFbo;
        this.outputFbo = outputFbo;
        this.depthAttachment = depthAttachment;
        this.blurRadius = blurRadius;
        this.threshold = threshold;
        this.tintColor = tintColor;
        this.tintColor2 = tintColor2;
        this.mode = mode;
        this.time = time;
        this.shaderScale = shaderScale;
    }

    public void renderPostProcess() {
        class_310 mc = class_310.method_1551();
        class_276 mainFbo = mc.method_1522();
        if (mainFbo == null || mainFbo.field_1482 <= 0 || mainFbo.field_1481 <= 0) {
            return;
        }
        if (this.sourceFbo.method_30277() <= 0 || this.outputFbo.method_30277() <= 0 || this.depthAttachment <= 0) {
            return;
        }
        this.renderStandardPass();
        mainFbo.method_1235(false);
        RenderSystem.viewport((int)0, (int)0, (int)mainFbo.field_1482, (int)mainFbo.field_1481);
        this.outputFbo.method_1233(mainFbo.field_1482, mainFbo.field_1481);
    }

    private void renderStandardPass() {
        int sourceTex = this.sourceFbo.method_30277();
        int depthTex = this.depthAttachment;
        this.outputFbo.method_1235(false);
        RenderSystem.viewport((int)0, (int)0, (int)this.outputFbo.field_1482, (int)this.outputFbo.field_1481);
        RenderSystem.disableBlend();
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShaderTexture((int)0, (int)sourceTex);
        RenderSystem.setShaderTexture((int)1, (int)depthTex);
        class_5944 shader = RenderSystem.setShader((class_10156)HAND_BLUR_KEY);
        if (shader != null) {
            shader.method_62899("Sampler0", sourceTex);
            shader.method_62899("DepthSampler", depthTex);
            this.applyBlurUniforms(shader);
            class_287 builder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_29336);
            builder.method_22912(0.0f, 0.0f, 0.0f);
            builder.method_22912(1.0f, 0.0f, 0.0f);
            builder.method_22912(1.0f, 1.0f, 0.0f);
            builder.method_22912(0.0f, 1.0f, 0.0f);
            class_286.method_43433((class_9801)builder.method_60800());
        }
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.setShaderTexture((int)1, (int)0);
        this.outputFbo.method_1240();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void applyBlurUniforms(class_5944 shader) {
        class_284 blurU = shader.method_34582("BlurRadius");
        class_284 thrU = shader.method_34582("Threshold");
        class_284 tintU = shader.method_34582("TintColor");
        class_284 tint2U = shader.method_34582("TintColor2");
        class_284 modeU = shader.method_34582("Mode");
        class_284 timeU = shader.method_34582("Time");
        class_284 scaleU = shader.method_34582("ShaderScale");
        if (blurU != null) {
            blurU.method_1251(this.blurRadius);
        }
        if (thrU != null) {
            thrU.method_1251(this.threshold);
        }
        if (tintU != null) {
            tintU.method_35657(this.tintColor[0], this.tintColor[1], this.tintColor[2], this.tintColor[3]);
        }
        if (tint2U != null) {
            tint2U.method_35657(this.tintColor2[0], this.tintColor2[1], this.tintColor2[2], this.tintColor2[3]);
        }
        if (modeU != null) {
            modeU.method_35649(this.mode);
        }
        if (timeU != null) {
            timeU.method_1251(this.time);
        }
        if (scaleU != null) {
            scaleU.method_1251(this.shaderScale);
        }
    }
}
