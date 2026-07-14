package ua.babka.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import ua.babka.render.Scissor;
import ua.babka.render.builder.Builder;
import ua.babka.render.builder.BuiltBlur;
import ua.babka.render.builder.BuiltRectangle;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;
import ua.babka.render.font.MsdfFont;
import ua.babka.render.font.MsdfRenderer;
import ua.babka.render.model.IRenderer;
import ua.babka.util.IMinecraft;

public class DrawUtil
implements IMinecraft {
    public static final List<Line> LINE = new ArrayList<Line>();
    public static final List<Line> LINE_DEPTH = new ArrayList<Line>();
    private static final Map<String, Long> scrollTimestamps = new ConcurrentHashMap<String, Long>();

    public static void onRender3D(MatrixStack matrix) {
        Set widths;
        if (!LINE.isEmpty()) {
            GL11.glEnable((int)2881);
            widths = LINE.stream().map(line -> Float.valueOf(line.width)).collect(Collectors.toCollection(LinkedHashSet::new));
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.RENDERTYPE_LINES);
            widths.forEach(width -> {
                RenderSystem.lineWidth((float)width.floatValue());
                BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
                LINE.stream().filter(line -> line.width == width.floatValue()).forEach(line -> DrawUtil.vertexLine(matrix, (VertexConsumer)buffer, line.start, line.end, line.colorStart, line.colorEnd));
                BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
            });
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            LINE.clear();
            GL11.glDisable((int)2881);
        }
        if (!LINE_DEPTH.isEmpty()) {
            GL11.glEnable((int)2881);
            widths = LINE_DEPTH.stream().map(line -> Float.valueOf(line.width)).collect(Collectors.toCollection(LinkedHashSet::new));
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)false);
            RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.RENDERTYPE_LINES);
            widths.forEach(width -> {
                RenderSystem.lineWidth((float)width.floatValue());
                BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
                LINE_DEPTH.stream().filter(line -> line.width == width.floatValue()).forEach(line -> DrawUtil.vertexLine(matrix, (VertexConsumer)buffer, line.start, line.end, line.colorStart, line.colorEnd));
                BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
            });
            RenderSystem.depthMask((boolean)true);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            LINE_DEPTH.clear();
            GL11.glDisable((int)2881);
        }
    }

    public static void vertexLine(@NotNull MatrixStack matrices, @NotNull VertexConsumer buffer, Vec3d start, Vec3d end, int lineColor) {
        DrawUtil.vertexLine(matrices, buffer, start.toVector3f(), end.toVector3f(), lineColor, lineColor);
    }

    public static void vertexLine(@NotNull MatrixStack matrices, @NotNull VertexConsumer buffer, Vec3d start, Vec3d end, int startColor, int endColor) {
        DrawUtil.vertexLine(matrices, buffer, start.toVector3f(), end.toVector3f(), startColor, endColor);
    }

    public static void vertexLine(@NotNull MatrixStack matrices, @NotNull VertexConsumer buffer, Vector3f start, Vector3f end, int startColor, int endColor) {
        matrices.push();
        MatrixStack.Entry entry = matrices.peek();
        Vector3f vec = DrawUtil.getNormal(start.x, start.y, start.z, end.x, end.y, end.z);
        buffer.vertex(entry, start).color(startColor).normal(entry, vec.x(), vec.y(), vec.z());
        buffer.vertex(entry, end).color(endColor).normal(entry, vec.x(), vec.y(), vec.z());
        matrices.pop();
    }

    @NotNull
    public static Vector3f getNormal(float x1, float y1, float z1, float x2, float y2, float z2) {
        float xNormal = x2 - x1;
        float yNormal = y2 - y1;
        float zNormal = z2 - z1;
        float normalSqrt = MathHelper.sqrt((float)(xNormal * xNormal + yNormal * yNormal + zNormal * zNormal));
        return new Vector3f(xNormal / normalSqrt, yNormal / normalSqrt, zNormal / normalSqrt);
    }

    public static void drawLine(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int color, float width, boolean depth) {
        DrawUtil.drawLine(new Vec3d(minX, minY, minZ), new Vec3d(maxX, maxY, maxZ), color, width, depth);
    }

    public static void drawLine(Vec3d start, Vec3d end, int color, float width, boolean depth) {
        DrawUtil.drawLine(start, end, color, color, width, depth);
    }

    public static void drawLine(Vec3d start, Vec3d end, int colorStart, int colorEnd, float width, boolean depth) {
        Vec3d cameraPos = DrawUtil.mc.getEntityRenderDispatcher().camera.getPos();
        Line line = new Line(start.subtract(cameraPos), end.subtract(cameraPos), colorStart, colorEnd, width);
        if (depth) {
            LINE_DEPTH.add(line);
        } else {
            LINE.add(line);
        }
    }

    public static void drawRound(float x2, float y2, float width, float height, float radius, int color) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(radius)).smoothness(1.0f).build();
        rectangle.render(x2, y2);
    }

    public static void drawRound(float x2, float y2, float width, float height, float radius, int color, int color2) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(radius)).smoothness(1.0f).build();
        rectangle.render(x2, y2);
    }

    public static void drawRound(float x2, float y2, float width, float height, float radius, int color, int color2, int color3, int color4) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(radius)).smoothness(1.0f).build();
        rectangle.render(x2, y2);
    }

    public static void drawRound(float x2, float y2, float width, float height, float radius, float smoothness, int color) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(radius)).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, float radius, float smoothness, int color, int color2) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(radius)).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, float radius, float smoothness, int color, int color2, int color3, int color4) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(radius)).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, Vector4f vector4f, int color) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(1.0f).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, Vector4f vector4f, int color, int color2) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(1.0f).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, Vector4f vector4f, int color, int color2, int color3, int color4) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(1.0f).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, Vector4f vector4f, float smoothness, int color) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, Vector4f vector4f, float smoothness, int color, int color2) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRound(float x2, float y2, float width, float height, Vector4f vector4f, float smoothness, int color, int color2, int color3, int color4) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, float radius, int color, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(radius)).blurRadius(blurIntensivity).smoothness(1.0f).build();
        rectangle.render(x2, y2);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, float radius, int color, int color2, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(radius)).blurRadius(blurIntensivity).smoothness(1.0f).build();
        rectangle.render(x2, y2);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, float radius, int color, int color2, int color3, int color4, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(radius)).blurRadius(blurIntensivity).smoothness(1.0f).build();
        rectangle.render(x2, y2);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, float radius, int color, float smoothness, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(radius)).blurRadius(blurIntensivity).smoothness(smoothness).build();
        rectangle.render(x2, y2);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, float radius, int color, int color2, float smoothness, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(radius)).blurRadius(blurIntensivity).smoothness(smoothness).build();
        rectangle.render(x2, y2);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, float radius, int color, int color2, int color3, int color4, float smoothness, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(radius)).blurRadius(blurIntensivity).smoothness(smoothness).build();
        rectangle.render(x2, y2);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, Vector4f vector4f, int color, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).blurRadius(blurIntensivity).smoothness(1.0f).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, Vector4f vector4f, int color, int color2, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).blurRadius(blurIntensivity).smoothness(1.0f).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, Vector4f vector4f, int color, int color2, int color3, int color4, float blurIntensivity) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(1.0f).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, Vector4f vector4f, int color, float smoothness, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).blurRadius(blurIntensivity).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, Vector4f vector4f, int color, int color2, float smoothness, float blurIntensivity) {
        BuiltBlur rectangle = (BuiltBlur)Builder.blur().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color, color2, color2)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).blurRadius(blurIntensivity).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawRoundBlur(float x2, float y2, float width, float height, Vector4f vector4f, int color, int color2, int color3, int color4, float smoothness, float blurIntensivity) {
        BuiltRectangle rectangle = (BuiltRectangle)Builder.rectangle().size(new SizeState(width + 0.5f, height + 0.25f)).color(new QuadColorState(color, color2, color3, color4)).radius(new QuadRadiusState(vector4f.x, vector4f.y, vector4f.z, vector4f.w)).smoothness(smoothness).build();
        rectangle.render(x2 - 0.5f, y2 - 0.5f);
    }

    public static void drawText(MsdfFont font, String text, float x2, float y2, int color, float size) {
        MsdfRenderer.renderText(font, text, size, color, IRenderer.DEFAULT_MATRIX, x2, y2 + 2.0f, 0.0f);
    }

    public static void drawText(MsdfFont font, String text, float x2, float y2, int color, float size, float fadeoutStart, float fadeoutEnd, float maxWidth) {
        MsdfRenderer.renderText(font, text, size, color, IRenderer.DEFAULT_MATRIX, x2, y2, 0.0f, true, fadeoutStart, fadeoutEnd, maxWidth);
    }

    public static void drawText(MsdfFont font, Text text, float x2, float y2, float size, int alpha) {
        MsdfRenderer.renderText(font, text, size, IRenderer.DEFAULT_MATRIX, x2, y2 + 2.0f, 0.0f, alpha);
    }

    public static void drawScrollingText(MsdfFont font, String text, float x2, float y2, int color, float size, float maxWidth, String id) {
        float textWidth = font.getWidth(text, size);
        if (textWidth <= maxWidth + 0.5f) {
            DrawUtil.drawText(font, text, x2, y2, color, size);
            return;
        }
        long now = System.currentTimeMillis();
        scrollTimestamps.putIfAbsent(id, now);
        double elapsed = (double)(now - scrollTimestamps.get(id)) / 1000.0;
        float scrollDist = textWidth - maxWidth + 2.0f;
        double period = 5.0;
        double t2 = elapsed % period / period;
        double phase = t2 < 0.3 ? t2 / 0.3 : (t2 < 0.5 ? 1.0 : (t2 < 0.8 ? 1.0 - (t2 - 0.5) / 0.3 : 0.0));
        float offset = -scrollDist * (float)phase;
        Scissor.push();
        Scissor.setFromComponentCoordinates(x2, y2, maxWidth, size + 4.0f);
        DrawUtil.drawText(font, text, x2 + offset, y2, color, size);
        Scissor.unset();
        Scissor.pop();
    }

    public record Line(Vec3d start, Vec3d end, int colorStart, int colorEnd, float width) {
    }
}

