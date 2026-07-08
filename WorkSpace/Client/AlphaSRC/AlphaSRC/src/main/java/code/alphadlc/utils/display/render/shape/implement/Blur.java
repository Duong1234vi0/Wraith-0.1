/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager
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
 *  net.minecraft.class_5944
 *  net.minecraft.class_6367
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector2f
 *  org.joml.Vector3f
 *  org.joml.Vector4f
 */
package code.alphadlc.utils.display.render.shape.implement;

import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.shape.Shape;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_276;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_5944;
import net.minecraft.class_6367;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Blur
implements Shape,
QuickImports {
    private final class_10156 SHADER_KEY = new class_10156(class_2960.method_60655((String)"mre", (String)"core/blur"), class_290.field_1576, class_10149.field_53930);
    public class_276 input;
    public Vector2f resolution = new Vector2f();

    @Override
    public void render(ShapeProperties shape) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        float scale = (float)mc.method_22683().method_4495();
        float alpha = RenderSystem.getShaderColor()[3];
        Matrix4f matrix4f = shape.getMatrix().method_23760().method_23761();
        Vector3f pos = matrix4f.transformPosition(shape.getX(), shape.getY(), 0.0f, new Vector3f()).mul(scale);
        Vector3f size = matrix4f.getScale(new Vector3f()).mul(scale);
        Vector4f round = shape.getRound().mul(size.y);
        float quality = shape.getQuality();
        float softness = shape.getSoftness();
        float thickness = shape.getThickness();
        float width = shape.getWidth() * size.x;
        float height = shape.getHeight() * size.y;
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        int color = ColorAssist.rgba(255, 255, 255, (int)(alpha * 255.0f));
        drawEngine.quad(matrix4f, buffer, shape.getX() - softness / 2.0f, shape.getY() - softness / 2.0f, shape.getWidth() + softness, shape.getHeight() + softness, color);
        GlStateManager._activeTexture((int)33984);
        if (this.input != null) {
            RenderSystem.bindTexture((int)this.input.method_30277());
        }
        class_5944 shader = RenderSystem.setShader((class_10156)this.SHADER_KEY);
        shader.method_35785("Size").method_1255(width, height);
        shader.method_35785("Radius").method_35652(round);
        shader.method_35785("Smoothness").method_1251(softness);
        shader.method_35785("BlurRadius").method_1251(shape.getBlurRadius());
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.disableBlend();
    }

    public void setup() {
        class_276 buffer = mc.method_1522();
        if (this.input == null) {
            this.input = new class_6367(mc.method_22683().method_4486(), mc.method_22683().method_4502(), false);
        }
        this.input.method_1235(false);
        buffer.method_1237(this.input.field_1482, this.input.field_1481);
        buffer.method_1235(false);
        if (this.input != null && (this.input.field_1482 != mc.method_22683().method_4489() || this.input.field_1481 != mc.method_22683().method_4506())) {
            this.input.method_1234(mc.method_22683().method_4489(), mc.method_22683().method_4506());
        }
        this.resolution.set((float)buffer.field_1482, (float)buffer.field_1481);
    }
}
