/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1044
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_5944
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 */
package code.alphadlc.features.impl.render.jumpCircle.handler;

import code.alphadlc.features.impl.render.jumpCircle.data.JumpCircleLayer;
import code.alphadlc.features.impl.render.jumpCircle.receiver.JumpCircleModule;
import code.alphadlc.main.client.Api;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1044;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import org.joml.Matrix4f;

public record JumpCircleHandler(JumpCircleModule module) implements Api
{
    public static class_2960 IDENTIFIER = class_2960.method_60654((String)"textures/circle.png");

    public void provideRender(class_4587 matrixStack, float delta) {
        class_287 bufferBuilder = this.createBuilder();
        this.setupGlConst();
        this.setupTexture();
        class_5944 program = this.setupShader();
        for (JumpCircleLayer layer : this.module.getStorage().getJumpCircleLayers()) {
            this.setupVertices(bufferBuilder, layer, matrixStack);
        }
        this.closeBuffer(bufferBuilder);
        this.returnGlConst();
    }

    private void setupVertices(class_287 builder, JumpCircleLayer layer, class_4587 matrixStack) {
        float animValue = layer.animation().getOutput().floatValue();
        float sinTime = (float)((double)System.nanoTime() / 1.0E9);
        int selectedColor = JumpCircleModule.colorMode.get().equals("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439") ? ThemeManager.primaryColor.getColor() : JumpCircleModule.color.getColor();
        class_4184 camera = JumpCircleHandler.mc.method_1561().field_4686;
        if (camera == null) {
            return;
        }
        int firstColor = ColorAssist.multAlpha(selectedColor, animValue);
        int secondColor = ColorAssist.multAlpha(ColorAssist.multSaturation(selectedColor, -0.3f), animValue);
        matrixStack.method_22903();
        matrixStack.method_61958(layer.vec3d());
        matrixStack.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
        matrixStack.method_22907(class_7833.field_40718.rotationDegrees(360.0f * sinTime));
        Matrix4f matrix4f = matrixStack.method_23760().method_23761();
        float size = this.module.getSize().getValue() / (this.module.getSize().getMax() - this.module.getSize().getMin()) * animValue;
        builder.method_22918(matrix4f, -size, -size, 0.0f).method_22913(0.0f, 0.0f).method_39415(firstColor);
        builder.method_22918(matrix4f, -size, size, 0.0f).method_22913(0.0f, 1.0f).method_39415(firstColor);
        builder.method_22918(matrix4f, size, size, 0.0f).method_22913(1.0f, 1.0f).method_39415(secondColor);
        builder.method_22918(matrix4f, size, -size, 0.0f).method_22913(1.0f, 0.0f).method_39415(secondColor);
        builder.method_22918(matrix4f, -size, -size, 0.0f).method_22913(0.0f, 0.0f).method_39415(firstColor);
        builder.method_22918(matrix4f, -size, size, 0.0f).method_22913(0.0f, 1.0f).method_39415(firstColor);
        builder.method_22918(matrix4f, size, size, 0.0f).method_22913(1.0f, 1.0f).method_39415(secondColor);
        builder.method_22918(matrix4f, size, -size, 0.0f).method_22913(1.0f, 0.0f).method_39415(secondColor);
        matrixStack.method_22909();
    }

    private class_287 createBuilder() {
        return class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
    }

    private void closeBuffer(class_287 bufferBuilder) {
        class_9801 builtBuffer = bufferBuilder.method_60794();
        if (builtBuffer != null && builtBuffer.method_60822().comp_750() > 0) {
            class_286.method_43433((class_9801)builtBuffer);
        }
    }

    private void setupGlConst() {
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
    }

    private void returnGlConst() {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
    }

    private class_5944 setupShader() {
        return RenderSystem.setShader((class_10156)class_10142.field_53880);
    }

    private void setupTexture() {
        class_1044 texture = mc.method_1531().method_4619(IDENTIFIER);
        texture.method_4527(false, false);
        RenderSystem.setShaderTexture((int)0, (int)texture.method_4624());
    }
}
