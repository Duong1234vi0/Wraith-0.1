/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_5944
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 *  org.joml.Vector4i
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.features.impl.render.Particles;
import code.alphadlc.features.impl.render.TargetESP;
import code.alphadlc.features.impl.render.TargetRenderProvider;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4i;

public record CrystalTargetProvider(TargetESP targetEspModule) implements TargetRenderProvider
{
    private static final Vector3f[] VERTICES = new Vector3f[]{new Vector3f(0.0f, 1.5f, 0.0f), new Vector3f(0.0f, -1.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(-1.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector3f(0.0f, 0.0f, -1.0f)};
    private static final int[][] FACES = new int[][]{{0, 2, 4}, {0, 4, 3}, {0, 3, 5}, {0, 5, 2}, {1, 4, 2}, {1, 3, 4}, {1, 5, 3}, {1, 2, 5}};

    @Override
    public void render(class_4587 matrixStack, class_1309 living, class_243 vec3d, float delta) {
        this.setupGlConst();
        class_5944 program = this.setupShader();
        class_4184 camera = class_310.method_1551().field_1773.method_19418();
        class_287 builder = this.createBuilder();
        double time = (double)System.nanoTime() / 1.0E9;
        double lengthX = living.method_5829().method_17939();
        double lengthZ = living.method_5829().method_17941();
        float animValue = this.targetEspModule.getCrystal2FadeAnim().getOutput().floatValue();
        for (int i = 0; i < 32; ++i) {
            double cos = Math.cos(time + (double)i) * (lengthX + (double)0.2f);
            double sin = Math.sin(time + (double)i) * (lengthZ + (double)0.2f);
            class_243 crystalPos = vec3d.method_1031(cos, (double)((float)i / 8.0f % living.method_17682()), sin);
            class_243 locationDelta = new class_243(vec3d.field_1352 - crystalPos.field_1352, 0.0, vec3d.field_1350 - crystalPos.field_1350);
            Turns rotation = MathAngle.fromVec3d(locationDelta);
            class_243 targetPos = crystalPos.method_1020(rotation.toVector().method_1021((double)(1.0f - animValue)));
            matrixStack.method_22903();
            matrixStack.method_61958(targetPos);
            matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-rotation.getYaw()));
            matrixStack.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
            matrixStack.method_61958(targetPos.method_22882());
            this.drawCrystal(matrixStack, builder, targetPos, 0.1f);
            matrixStack.method_22909();
            class_4587 matrices = new class_4587();
            matrices.method_22903();
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
            matrices.method_22904(targetPos.field_1352 - camera.method_19326().method_10216(), targetPos.field_1351 - camera.method_19326().method_10214(), targetPos.field_1350 - camera.method_19326().method_10215());
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            float glowSize = 0.2f;
            Render3D.drawTexture(matrices.method_23760(), Particles.ParticleType.shariki.texture(), -glowSize * 2.0f, -glowSize * 2.0f, glowSize * 4.0f, glowSize * 4.0f, new Vector4i(ColorAssist.multAlpha(ColorAssist.getClientColor(), 0.2f * animValue)), true);
            matrices.method_22909();
        }
        this.closeBuffer(builder);
        this.returnGlConst();
    }

    private void drawCrystal(class_4587 matrixStack, class_287 builder, class_243 vec3d, float size) {
        matrixStack.method_22903();
        matrixStack.method_61958(vec3d);
        matrixStack.method_22905(size, size, size);
        Matrix4f matrix4f = matrixStack.method_23760().method_23761();
        for (int[] face : FACES) {
            Vector3f v1 = VERTICES[face[0]];
            Vector3f v2 = VERTICES[face[1]];
            Vector3f v3 = VERTICES[face[2]];
            int color = this.targetEspModule.getCrystalFill().isValue() ? ColorAssist.clampSaturation(ColorAssist.getClientColor(), 1.0f, 1.1f) : -1;
            color = ColorAssist.multAlpha(color, this.targetEspModule.getCrystal2FadeAnim().getOutput().floatValue());
            builder.method_22918(matrix4f, v1.x, v1.y, v1.z).method_39415(color);
            builder.method_22918(matrix4f, v2.x, v2.y, v2.z).method_39415(color);
            builder.method_22918(matrix4f, v3.x, v3.y, v3.z).method_39415(color);
        }
        matrixStack.method_22909();
    }

    private class_287 createBuilder() {
        return class_289.method_1348().method_60827(class_293.class_5596.field_27379, class_290.field_1576);
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
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
    }

    private void returnGlConst() {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
    }

    private class_5944 setupShader() {
        return RenderSystem.setShader((class_10156)class_10142.field_53876);
    }
}
