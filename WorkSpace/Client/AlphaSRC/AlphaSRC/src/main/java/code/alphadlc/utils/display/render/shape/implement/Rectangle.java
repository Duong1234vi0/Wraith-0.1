/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_5944
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 *  org.joml.Vector4f
 *  org.joml.Vector4fc
 *  org.lwjgl.opengl.GL11
 */
package code.alphadlc.utils.display.render.shape.implement;

import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.draw.DrawEngine;
import code.alphadlc.utils.display.render.draw.DrawEngineImpl;
import code.alphadlc.utils.display.render.shape.Shape;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5944;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.lwjgl.opengl.GL11;

public class Rectangle
implements Shape,
QuickImports {
    private static final class_10156 SHADER_KEY = new class_10156(class_2960.method_60655((String)"minecraft", (String)"core/round"), class_290.field_1592, class_10149.field_53930);
    private static final DrawEngine BATCH_DRAW_ENGINE = new DrawEngineImpl();
    private static final ThreadLocal<Boolean> BATCHING = ThreadLocal.withInitial(() -> Boolean.FALSE);
    private static final ArrayList<BatchEntry> BATCH = new ArrayList(64);
    private static float[] rectX = new float[64];
    private static float[] rectY = new float[64];
    private static float[] rectW = new float[64];
    private static float[] rectH = new float[64];
    private static float[] rectR = new float[64];
    private static int curCount;
    private static int lastCount;

    public static void beginBatch() {
        BATCHING.set(Boolean.TRUE);
        BATCH.clear();
        curCount = 0;
    }

    private static void recordRect(ShapeProperties shape) {
        if (curCount >= rectX.length) {
            int n = rectX.length * 2;
            rectX = Arrays.copyOf(rectX, n);
            rectY = Arrays.copyOf(rectY, n);
            rectW = Arrays.copyOf(rectW, n);
            rectH = Arrays.copyOf(rectH, n);
            rectR = Arrays.copyOf(rectR, n);
        }
        Rectangle.rectX[Rectangle.curCount] = shape.getX();
        Rectangle.rectY[Rectangle.curCount] = shape.getY();
        Rectangle.rectW[Rectangle.curCount] = shape.getWidth();
        Rectangle.rectH[Rectangle.curCount] = shape.getHeight();
        Rectangle.rectR[Rectangle.curCount] = shape.getRound().x;
        ++curCount;
    }

    public static int panelCount() {
        return lastCount;
    }

    public static float panelX(int i) {
        return rectX[i];
    }

    public static float panelY(int i) {
        return rectY[i];
    }

    public static float panelWidth(int i) {
        return rectW[i];
    }

    public static float panelHeight(int i) {
        return rectH[i];
    }

    public static float panelRound(int i) {
        return rectR[i];
    }

    public static void flushPending() {
        if (BATCH.isEmpty()) {
            return;
        }
        boolean depthTest = GL11.glIsEnabled((int)2929);
        boolean cull = GL11.glIsEnabled((int)2884);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        float alpha = RenderSystem.getShaderColor()[3];
        class_5944 shader = RenderSystem.setShader((class_10156)SHADER_KEY);
        class_289 tessellator = class_289.method_1348();
        int count = BATCH.size();
        for (int i = 0; i < count; ++i) {
            Rectangle.drawEntry(BATCH.get(i), shader, tessellator, alpha);
        }
        RenderSystem.disableBlend();
        if (!depthTest) {
            RenderSystem.disableDepthTest();
        }
        if (!cull) {
            RenderSystem.disableCull();
        }
        BATCH.clear();
    }

    public static void endBatch() {
        Rectangle.flushPending();
        BATCHING.set(Boolean.FALSE);
        lastCount = curCount;
    }

    public static void flushBatch() {
        Rectangle.endBatch();
    }

    public static boolean isBatching() {
        return BATCHING.get();
    }

    @Override
    public void render(ShapeProperties shape) {
        if (BATCHING.get().booleanValue()) {
            Rectangle.recordRect(shape);
            BATCH.add(BatchEntry.from(shape));
            return;
        }
        this.renderImmediate(shape);
    }

    private void renderImmediate(ShapeProperties shape) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        float alpha = RenderSystem.getShaderColor()[3];
        class_5944 shader = RenderSystem.setShader((class_10156)SHADER_KEY);
        Rectangle.drawEntry(BatchEntry.from(shape), shader, class_289.method_1348(), alpha);
        RenderSystem.disableBlend();
    }

    private static void drawEntry(BatchEntry entry, class_5944 shader, class_289 tessellator, float alpha) {
        class_287 buffer = tessellator.method_60827(class_293.class_5596.field_27382, class_290.field_1592);
        float softness = entry.softness;
        BATCH_DRAW_ENGINE.quad(entry.matrix, buffer, entry.x - softness / 2.0f, entry.y - softness / 2.0f, entry.width + softness, entry.height + softness);
        shader.method_35785("size").method_1255(entry.scaledWidth, entry.scaledHeight);
        shader.method_35785("location").method_1255(entry.locationX, entry.locationY);
        shader.method_35785("radius").method_35652(entry.round);
        shader.method_35785("softness").method_1251(softness);
        shader.method_35785("thickness").method_1251(entry.thickness);
        shader.method_35785("color1").method_35657(ColorAssist.redf(entry.color1), ColorAssist.greenf(entry.color1), ColorAssist.bluef(entry.color1), ColorAssist.alphaf(ColorAssist.multAlpha(entry.color1, alpha)));
        shader.method_35785("color2").method_35657(ColorAssist.redf(entry.color2), ColorAssist.greenf(entry.color2), ColorAssist.bluef(entry.color2), ColorAssist.alphaf(ColorAssist.multAlpha(entry.color2, alpha)));
        shader.method_35785("color3").method_35657(ColorAssist.redf(entry.color3), ColorAssist.greenf(entry.color3), ColorAssist.bluef(entry.color3), ColorAssist.alphaf(ColorAssist.multAlpha(entry.color3, alpha)));
        shader.method_35785("color4").method_35657(ColorAssist.redf(entry.color4), ColorAssist.greenf(entry.color4), ColorAssist.bluef(entry.color4), ColorAssist.alphaf(ColorAssist.multAlpha(entry.color4, alpha)));
        shader.method_35785("outlineColor").method_35657(ColorAssist.redf(entry.outlineColor), ColorAssist.greenf(entry.outlineColor), ColorAssist.bluef(entry.outlineColor), ColorAssist.alphaf(ColorAssist.multAlpha(entry.outlineColor, alpha)));
        class_286.method_43433((class_9801)buffer.method_60800());
    }

    private record BatchEntry(Matrix4f matrix, float x, float y, float width, float height, float softness, float thickness, float scaledWidth, float scaledHeight, float locationX, float locationY, Vector4f round, int color1, int color2, int color3, int color4, int outlineColor) {
        private static BatchEntry from(ShapeProperties shape) {
            class_310 mc = class_310.method_1551();
            float scale = (float)mc.method_22683().method_4495();
            Matrix4f matrix4f = new Matrix4f((Matrix4fc)shape.getMatrix().method_23760().method_23761());
            Vector3f pos = matrix4f.transformPosition(shape.getX(), shape.getY(), 0.0f, new Vector3f()).mul(scale);
            Vector3f size = matrix4f.getScale(new Vector3f()).mul(scale);
            Vector4f round = new Vector4f((Vector4fc)shape.getRound()).mul(size.y);
            float scaledWidth = shape.getWidth() * size.x;
            float scaledHeight = shape.getHeight() * size.y;
            return new BatchEntry(matrix4f, shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight(), shape.getSoftness(), shape.getThickness(), scaledWidth, scaledHeight, pos.x, (float)mc.method_22683().method_4507() - scaledHeight - pos.y, round, shape.getColor().x, shape.getColor().y, shape.getColor().z, shape.getColor().w, shape.getOutlineColor());
        }
    }
}
