/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_1058
 *  net.minecraft.class_1799
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_5944
 *  net.minecraft.class_9801
 *  net.minecraft.class_9848
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 *  org.joml.Vector4f
 */
package code.alphadlc.utils.display.render.geometry;

import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.item.ItemRender;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2DQuad;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_1058;
import net.minecraft.class_1799;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_9801;
import net.minecraft.class_9848;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public final class Render2D
implements QuickImports {
    private static final List<Render2DQuad> QUAD = new ArrayList<Render2DQuad>();
    static final class_10156 SHADER_KEY = new class_10156(class_2960.method_60655((String)"minecraft", (String)"core/sampler_texture/sampler_texture"), class_290.field_1592, class_10149.field_53930);

    public static void onRender(class_332 context) {
        Render2D.flushQuads(context.method_51448());
    }

    public static void flushQuads(class_4587 matrix) {
        if (QUAD.isEmpty()) {
            return;
        }
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        QUAD.forEach(quad -> drawEngine.quad(matrix4f, buffer, quad.x(), quad.y(), quad.width(), quad.height(), quad.color()));
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.disableBlend();
        QUAD.clear();
    }

    public static void defaultDrawStack(class_332 context, class_1799 stack, float x, float y, boolean rect, boolean drawItemInSlot, float scale) {
        Render2D.defaultDrawStack(context, stack, x, y, rect, drawItemInSlot, scale, 0.0f);
    }

    public static void defaultDrawStack(class_332 context, class_1799 stack, float x, float y, boolean rect, boolean drawItemInSlot, float scale, float z) {
        int count;
        if (Rectangle.isBatching()) {
            Rectangle.flushPending();
        }
        class_4587 matrix = context.method_51448();
        matrix.method_22903();
        matrix.method_46416(x + 1.0f, y + 1.0f, z);
        matrix.method_22905(scale, scale, 1.0f);
        context.method_51427(stack, 0, 0);
        if (drawItemInSlot && (count = stack.method_7947()) > 1) {
            String countText = String.valueOf(count);
            FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
            float textWidth = font.getStringWidth(countText);
            float textX = 16.0f - textWidth - 1.0f;
            float textY = 16.0f - font.getStringHeight(countText) - 1.0f;
            font.drawString(matrix, countText, textX - 0.5f, textY - 0.5f, -16777216);
            font.drawString(matrix, countText, textX + 0.5f, textY - 0.5f, -16777216);
            font.drawString(matrix, countText, textX - 0.5f, textY + 0.5f, -16777216);
            font.drawString(matrix, countText, textX + 0.5f, textY + 0.5f, -16777216);
            font.drawString(matrix, countText, textX, textY, -1);
        }
        matrix.method_22909();
    }

    public static void rectangleWithMask(Matrix4f matrix4f, float x, float y, float w, float h, float round, int color, int texture) {
        RenderSystem.setShaderTexture((int)0, (int)texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1592);
        class_5944 shader = RenderSystem.setShader((class_10156)SHADER_KEY);
        float scale = (float)mc.method_22683().method_4495();
        Vector3f pos = matrix4f.transformPosition(x, y, 0.0f, new Vector3f()).mul(scale);
        Vector3f size = matrix4f.getScale(new Vector3f()).mul(scale);
        Vector4f r = new Vector4f(round).mul(size.y);
        float width = w * size.x;
        float height = h * size.y;
        shader.method_34582("size").method_1255(width, height);
        shader.method_34582("location").method_1255(pos.x, (float)mc.method_22683().method_4507() - height - pos.y);
        shader.method_34582("texSize").method_1255((float)Render2D.mc.method_1522().field_1482, (float)Render2D.mc.method_1522().field_1481);
        shader.method_34582("color").method_35657(class_9848.method_65101((int)color), class_9848.method_65102((int)color), class_9848.method_65103((int)color), RenderSystem.getShaderColor()[3]);
        shader.method_34582("round").method_35652(r);
        drawEngine.quad(matrix4f, buffer, x, y, w, h);
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.disableBlend();
    }

    public static void drawStack(class_4587 matrix, class_1799 stack, float x, float y, boolean rect, float scale) {
        if (Rectangle.isBatching()) {
            Rectangle.flushPending();
        }
        float posX = x + 1.0f;
        float posY = y + 1.0f;
        float padding = 1.0f;
        matrix.method_22903();
        matrix.method_46416(posX, posY, 0.0f);
        if (rect) {
            blur.render(ShapeProperties.create(matrix, -padding, -padding, 16.0f * scale + padding * 2.0f, 16.0f * scale + padding * 2.0f).round(1.5f).color(ColorAssist.HALF_BLACK).build());
        }
        matrix.method_22905(scale, scale, 1.0f);
        ItemRender.drawItem(matrix, stack, 0.0f, 0.0f, true, true);
        matrix.method_22909();
    }

    public static void drawTexture(class_332 context, class_2960 id, float x, float y, int size) {
        class_4587 matrix = context.method_51448();
        if (id != null) {
            matrix.method_22903();
            matrix.method_46416(x, y, 0.0f);
            matrix.method_22905((float)size, (float)size, 1.0f);
            RenderSystem.enableBlend();
            Render2D.drawTexture(matrix, id, 0, 0, 1.0f, 1.0f, size, size, size, size, size, size, -1);
            RenderSystem.disableBlend();
            matrix.method_46416(-x, -y, 0.0f);
            matrix.method_22909();
        }
    }

    public static Color applyOpacity(Color color, float opacity) {
        opacity = Math.min(1.0f, Math.max(0.0f, opacity));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * opacity));
    }

    public static int applyOpacity(int color_int, float opacity) {
        opacity = Math.min(1.0f, Math.max(0.0f, opacity));
        Color color = new Color(color_int);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * opacity)).getRGB();
    }

    public static void drawTexture(class_332 context, class_2960 id, float x, float y, float size, float round, int uvSize, int regionSize, int textureSize, int backgroundColor) {
        Render2D.drawTexture(context, id, x, y, size, round, uvSize, regionSize, textureSize, backgroundColor, -1);
    }

    public static void drawTexture(class_332 context, class_2960 id, float x, float y, float size, float round, int uvSize, int regionSize, int textureSize, int backgroundColor, int color) {
        if (Rectangle.isBatching()) {
            Rectangle.flushPending();
        }
        class_4587 matrix = context.method_51448();
        rectangle.render(ShapeProperties.create(matrix, x, y, size, size).round(round).color(backgroundColor).build());
        if (id != null) {
            matrix.method_22903();
            matrix.method_46416(x, y, 0.0f);
            matrix.method_22905(size, size, 1.0f);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc((int)772, (int)773);
            Render2D.drawTexture(matrix, id, 0, 0, 1.0f, 1.0f, uvSize, uvSize, regionSize, regionSize, textureSize, textureSize, color);
            RenderSystem.defaultBlendFunc();
            matrix.method_46416(-x, -y, 0.0f);
            matrix.method_22909();
        }
    }

    public static void drawSprite(class_4587 matrix, class_1058 sprite, float x, float y, float width, int height) {
        if (width != 0.0f && height != 0) {
            Render2D.drawTexturedQuad(matrix, sprite.method_45852(), x, x + width, y, y + (float)height, sprite.method_4594(), sprite.method_4577(), sprite.method_4593(), sprite.method_4575(), -1);
        }
    }

    public static void drawSprite(class_4587 matrix, class_1058 sprite, float x, float y, float width, int height, int color) {
        if (width != 0.0f && height != 0) {
            Render2D.drawTexturedQuad(matrix, sprite.method_45852(), x, x + width, y, y + (float)height, sprite.method_4594(), sprite.method_4577(), sprite.method_4593(), sprite.method_4575(), color);
        }
    }

    public static void drawTexture(class_4587 matrix, class_2960 texture, int x, int y, float width, float height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight, int color) {
        Render2D.drawTexture(matrix, texture, x, (float)x + width, y, (float)y + height, 0.0f, regionWidth, regionHeight, u, v, textureWidth, textureHeight, color);
    }

    public static void drawTexture(class_4587 matrix, class_2960 texture, float x1, float x2, float y1, float y2, float z, int regionWidth, int regionHeight, float u, float v, int textureWidth, int textureHeight, int color) {
        Render2D.drawTexturedQuad(matrix, texture, x1, x2, y1, y2, (u + 0.0f) / (float)textureWidth, (u + (float)regionWidth) / (float)textureWidth, (v + 0.0f) / (float)textureHeight, (v + (float)regionHeight) / (float)textureHeight, color);
    }

    public static void drawSpriteColored(class_4587 matrix, class_1058 sprite, float x, float y, float width, float height, int colorTL, int colorBL, int colorBR, int colorTR) {
        if (Rectangle.isBatching()) {
            Rectangle.flushPending();
        }
        RenderSystem.setShaderTexture((int)0, (class_2960)sprite.method_45852());
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        buffer.method_22918(matrix4f, x, y, 0.0f).method_22913(sprite.method_4594(), sprite.method_4593()).method_39415(colorTL);
        buffer.method_22918(matrix4f, x, y + height, 0.0f).method_22913(sprite.method_4594(), sprite.method_4575()).method_39415(colorBL);
        buffer.method_22918(matrix4f, x + width, y + height, 0.0f).method_22913(sprite.method_4577(), sprite.method_4575()).method_39415(colorBR);
        buffer.method_22918(matrix4f, x + width, y, 0.0f).method_22913(sprite.method_4577(), sprite.method_4593()).method_39415(colorTR);
        class_286.method_43433((class_9801)buffer.method_60800());
    }

    public static void drawTexturedQuad(class_4587 matrix, class_2960 texture, float x1, float x2, float y1, float y2, float u1, float u2, float v1, float v2, int color) {
        if (Rectangle.isBatching()) {
            Rectangle.flushPending();
        }
        RenderSystem.setShaderTexture((int)0, (class_2960)texture);
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        buffer.method_22918(matrix4f, x1, y1, 0.0f).method_22913(u1, v1).method_39415(color);
        buffer.method_22918(matrix4f, x1, y2, 0.0f).method_22913(u1, v2).method_39415(color);
        buffer.method_22918(matrix4f, x2, y2, 0.0f).method_22913(u2, v2).method_39415(color);
        buffer.method_22918(matrix4f, x2, y1, 0.0f).method_22913(u2, v1).method_39415(color);
        class_286.method_43433((class_9801)buffer.method_60800());
    }

    public static void drawCircle(class_4587 matrix, float x, float y, float radius, int color) {
        int segments = 16;
        float angleStep = (float)(Math.PI * 2 / (double)segments);
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        for (int i = 0; i < segments; ++i) {
            float angle1 = (float)i * angleStep;
            float angle2 = (float)(i + 1) * angleStep;
            float x1 = x + radius * (float)Math.cos(angle1);
            float y1 = y + radius * (float)Math.sin(angle1);
            float x2 = x + radius * (float)Math.cos(angle2);
            float y2 = y + radius * (float)Math.sin(angle2);
            buffer.method_22918(matrix4f, x, y, 0.0f).method_39415(color);
            buffer.method_22918(matrix4f, x1, y1, 0.0f).method_39415(color);
            buffer.method_22918(matrix4f, x2, y2, 0.0f).method_39415(color);
            buffer.method_22918(matrix4f, x, y, 0.0f).method_39415(color);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.disableBlend();
    }

    public static void drawCircleOutline(class_4587 matrix, float x, float y, float radius, float thickness, int color) {
        int segments = 48;
        float angleStep = (float)(Math.PI * 2 / (double)segments);
        float inner = Math.max(0.0f, radius - thickness);
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        for (int i = 0; i < segments; ++i) {
            float angle1 = (float)i * angleStep;
            float angle2 = (float)(i + 1) * angleStep;
            float cos1 = (float)Math.cos(angle1);
            float sin1 = (float)Math.sin(angle1);
            float cos2 = (float)Math.cos(angle2);
            float sin2 = (float)Math.sin(angle2);
            float ox1 = x + radius * cos1;
            float oy1 = y + radius * sin1;
            float ox2 = x + radius * cos2;
            float oy2 = y + radius * sin2;
            float ix1 = x + inner * cos1;
            float iy1 = y + inner * sin1;
            float ix2 = x + inner * cos2;
            float iy2 = y + inner * sin2;
            buffer.method_22918(matrix4f, ox1, oy1, 0.0f).method_39415(color);
            buffer.method_22918(matrix4f, ox2, oy2, 0.0f).method_39415(color);
            buffer.method_22918(matrix4f, ix2, iy2, 0.0f).method_39415(color);
            buffer.method_22918(matrix4f, ix1, iy1, 0.0f).method_39415(color);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.disableBlend();
    }

    public static void endBuilding(class_287 bb) {
        class_9801 builtBuffer = bb.method_60794();
        if (builtBuffer != null) {
            class_286.method_43433((class_9801)builtBuffer);
        }
    }

    public static void drawLine(class_4587 matrix, float x1, float y1, float x2, float y2, float thickness, int color) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float length = (float)Math.sqrt(dx * dx + dy * dy);
        if (length < 1.0E-4f) {
            return;
        }
        float half = thickness * 0.5f;
        float nx = -dy / length * half;
        float ny = dx / length * half;
        int finalColor = ColorAssist.multAlpha(color, RenderSystem.getShaderColor()[3]);
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        buffer.method_22918(matrix4f, x1 + nx, y1 + ny, 0.0f).method_39415(finalColor);
        buffer.method_22918(matrix4f, x1 - nx, y1 - ny, 0.0f).method_39415(finalColor);
        buffer.method_22918(matrix4f, x2 - nx, y2 - ny, 0.0f).method_39415(finalColor);
        buffer.method_22918(matrix4f, x2 + nx, y2 + ny, 0.0f).method_39415(finalColor);
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.disableBlend();
    }

    public static void drawQuad(float x, float y, float width, float height, int color) {
        QUAD.add(new Render2DQuad(x, y, width, height, ColorAssist.multAlpha(color, RenderSystem.getShaderColor()[3])));
    }

    private Render2D() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
