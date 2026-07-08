/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_243
 *  net.minecraft.class_332
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 */
package code.alphadlc.utils.display.render;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.math.projection.Projection;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_4184;
import net.minecraft.class_4587;

public final class UseIndicatorRenderer
implements QuickImports {
    private static final double[] SCREEN = new double[3];
    private static final float BACKGROUND_RADIUS = 12.0f;
    private static final float ITEM_SCALE = 0.7f;
    private static final int BACKGROUND_COLOR = -871362544;

    private UseIndicatorRenderer() {
    }

    public static void render(class_332 context, class_1309 entity, class_1799 stack, class_4184 camera) {
        if (context == null || stack == null || stack.method_7960()) {
            return;
        }
        class_243 position = Calculate.interpolate((class_1297)entity).method_1031(0.0, (double)UseIndicatorRenderer.getBodyOffset(entity), 0.0);
        if (!Projection.isInFrontOfCamera(position = UseIndicatorRenderer.offsetTowardCamera(entity, position, camera))) {
            return;
        }
        if (!Projection.worldSpaceToScreenSpaceInto(position.field_1352, position.field_1351, position.field_1350, SCREEN)) {
            return;
        }
        float screenX = (float)SCREEN[0];
        float screenY = (float)SCREEN[1];
        float entityScale = entity.method_6109() ? 0.75f : 1.0f;
        float radius = 12.0f * entityScale;
        float itemScale = 0.7f * entityScale;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        class_4587 matrices = context.method_51448();
        matrices.method_22903();
        matrices.method_46416(screenX, screenY, 0.0f);
        float diameter = radius * 2.0f;
        rectangle.render(ShapeProperties.create(matrices, -radius, -radius, diameter, diameter).round(radius).color(-871362544).build());
        float itemSize = 16.0f * itemScale;
        matrices.method_22903();
        matrices.method_46416(-itemSize / 2.0f, -itemSize / 2.0f, 0.0f);
        matrices.method_22905(itemScale, itemScale, 1.0f);
        context.method_51427(stack, 0, 0);
        matrices.method_22909();
        matrices.method_22909();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private static class_243 offsetTowardCamera(class_1309 entity, class_243 position, class_4184 camera) {
        if (UseIndicatorRenderer.mc.field_1724 == entity && UseIndicatorRenderer.mc.field_1690.method_31044().method_31034()) {
            return position;
        }
        class_243 toCamera = camera.method_19326().method_1020(position);
        double lengthSq = toCamera.method_1027();
        if (lengthSq < 1.0E-6) {
            return position;
        }
        return position.method_1019(toCamera.method_1029().method_1021(0.55));
    }

    private static float getBodyOffset(class_1309 entity) {
        class_1657 player;
        float offset = entity.method_17682() * 0.55f;
        if (entity.method_18276()) {
            offset -= 0.18f;
        }
        if (entity.method_5681()) {
            offset = entity.method_17682() * 0.35f;
        }
        if (entity instanceof class_1657 && (player = (class_1657)entity).method_6128()) {
            offset = entity.method_17682() * 0.42f;
        }
        return offset;
    }
}
