/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_276
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_5944
 *  net.minecraft.class_6367
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 */
package code.alphadlc.utils.display.render.post;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.GuiRenderState;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_276;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_6367;
import net.minecraft.class_9801;
import org.joml.Matrix4f;

public class KawaseBlur
implements QuickImports {
    public static final KawaseBlur INSTANCE = new KawaseBlur();
    private static final class_10156 upShader = new class_10156(class_2960.method_60655((String)"minecraft", (String)"core/blur/upscale"), class_290.field_1592, class_10149.field_53930);
    private static final class_10156 downShader = new class_10156(class_2960.method_60655((String)"minecraft", (String)"core/blur/downscale"), class_290.field_1592, class_10149.field_53930);
    public final List<class_6367> fbos = new ArrayList<class_6367>();
    private int steps;

    private void checkupFbo() {
        for (class_6367 fbo : this.fbos) {
            if (KawaseBlur.mc.method_1522().field_1482 == fbo.field_1482 && KawaseBlur.mc.method_1522().field_1481 == fbo.field_1481) continue;
            fbo.method_1234(KawaseBlur.mc.method_1522().field_1482, KawaseBlur.mc.method_1522().field_1481);
        }
    }

    public void recreate() {
        this.fbos.forEach(class_276::method_1238);
        this.fbos.clear();
        for (int i = 0; i <= this.steps; ++i) {
            this.fbos.add(this.createFbo());
        }
    }

    public void render(class_4587 matrixStack, int steps, float offset) {
        int i;
        if (this.steps != steps) {
            this.steps = steps;
            this.recreate();
        }
        this.checkupFbo();
        int actualPasses = Math.max(this.fbos.size() - 1, 1);
        this.applyBlurPass(matrixStack, downShader, mc.method_1522(), (class_276)this.fbos.getFirst(), 0, offset);
        for (i = 0; i < actualPasses; ++i) {
            this.applyBlurPass(matrixStack, downShader, (class_276)this.fbos.get(i), (class_276)this.fbos.get(i + 1), i + 1, offset);
        }
        for (i = actualPasses; i > 0; --i) {
            this.applyBlurPass(matrixStack, upShader, (class_276)this.fbos.get(i), (class_276)this.fbos.get(i - 1), i, offset);
        }
        mc.method_1522().method_1235(false);
        GuiRenderState.restoreAfterCustomDraw();
    }

    private void applyBlurPass(class_4587 matrixStack, class_10156 shaderKey, class_276 source, class_276 destination, int pass, float offset) {
        destination.method_1235(false);
        RenderSystem.setShaderTexture((int)0, (int)source.method_30277());
        class_5944 shader = RenderSystem.setShader((class_10156)shaderKey);
        shader.method_34582("uHalfTexelSize").method_1255(0.5f / (float)source.field_1482, 0.5f / (float)source.field_1481);
        shader.method_34582("uOffset").method_1251(offset * ((float)pass / (float)this.steps));
        this.drawFullQuad(matrixStack.method_23760().method_23761());
        destination.method_1240();
    }

    private void drawFullQuad(Matrix4f matrix4f) {
        float width = mc.method_22683().method_4486();
        float height = mc.method_22683().method_4502();
        class_287 builder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1592);
        builder.method_22918(matrix4f, 0.0f, 0.0f, 0.0f);
        builder.method_22918(matrix4f, 0.0f, height, 0.0f);
        builder.method_22918(matrix4f, width, height, 0.0f);
        builder.method_22918(matrix4f, width, 0.0f, 0.0f);
        class_286.method_43433((class_9801)builder.method_60800());
    }

    private class_6367 createFbo() {
        return new class_6367(mc.method_22683().method_4489(), mc.method_22683().method_4506(), false);
    }

    public int getBlurTexture() {
        return this.fbos.isEmpty() ? 0 : this.fbos.getFirst().method_30277();
    }
}
