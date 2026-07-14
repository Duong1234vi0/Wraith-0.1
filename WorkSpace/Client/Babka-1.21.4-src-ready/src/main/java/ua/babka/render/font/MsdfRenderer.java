package ua.babka.render.font;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import ua.babka.render.FormattedTextProcessor;
import ua.babka.render.ResourceProvider;
import ua.babka.render.font.MsdfFont;

public final class MsdfRenderer {
    public static final ShaderProgramKey MSDF_FONT_SHADER_KEY = new ShaderProgramKey(ResourceProvider.getShaderIdentifier("msdf_font"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);

    public static void renderText(MsdfFont font, String text, float size, int color, Matrix4f matrix, float x2, float y2, float z2) {
        MsdfRenderer.renderText(font, text, size, color, matrix, x2, y2, z2, false, 0.0f, 1.0f, 0.0f);
    }

    public static void renderText(MsdfFont font, String text, float size, int color, Matrix4f matrix, float x2, float y2, float z2, boolean enableFadeout, float fadeoutStart, float fadeoutEnd, float maxWidth) {
        float thickness = 0.05f;
        float smoothness = 0.5f;
        float spacing = 0.0f;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShaderTexture((int)0, (int)font.getTextureId());
        ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)MSDF_FONT_SHADER_KEY);
        shader.getUniform("Range").set(font.getAtlas().range());
        shader.getUniform("Thickness").set(thickness);
        shader.getUniform("Smoothness").set(0.5f);
        shader.getUniform("EnableFadeout").set(enableFadeout ? 1 : 0);
        shader.getUniform("FadeoutStart").set(fadeoutStart);
        shader.getUniform("FadeoutEnd").set(fadeoutEnd);
        shader.getUniform("MaxWidth").set(maxWidth);
        shader.getUniform("TextPosX").set(x2);
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        font.applyGlyphs(matrix, (VertexConsumer)builder, text, size, thickness * 0.5f * size, spacing, x2 - 0.75f, y2 + size * 0.7f, z2, color);
        BuiltBuffer builtBuffer = builder.endNullable();
        if (builtBuffer != null) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builtBuffer);
        }
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    public static void renderText(MsdfFont font, String text, float size, int color, Matrix4f matrix, float x2, float y2, float z2, boolean enableFadeout, float fadeoutStart, float fadeoutEnd) {
        float maxWidth = font.getWidth(text, size) * 2.0f;
        MsdfRenderer.renderText(font, text, size, color, matrix, x2, y2, z2, enableFadeout, fadeoutStart, fadeoutEnd, maxWidth);
    }

    public static void renderText(MsdfFont font, Text text, float size, Matrix4f matrix, float x2, float y2, float z2) {
        MsdfRenderer.renderText(font, text, size, matrix, x2, y2, z2, false, 0.0f, 1.0f, 0.0f);
    }

    public static void renderText(MsdfFont font, Text text, float size, Matrix4f matrix, float x2, float y2, float z2, int alpha) {
        MsdfRenderer.renderText(font, text, size, matrix, x2, y2, z2, false, 0.0f, 1.0f, 0.0f, alpha);
    }

    public static void renderText(MsdfFont font, Text text, float size, Matrix4f matrix, float x2, float y2, float z2, boolean enableFadeout, float fadeoutStart, float fadeoutEnd, float maxWidth) {
        float thickness = 0.05f;
        float smoothness = 0.5f;
        float spacing = 0.0f;
        List<FormattedTextProcessor.TextSegment> segments = FormattedTextProcessor.processText(text, -1);
        float currentX = x2;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShaderTexture((int)0, (int)font.getTextureId());
        ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)MSDF_FONT_SHADER_KEY);
        shader.getUniform("Range").set(font.getAtlas().range());
        shader.getUniform("Thickness").set(thickness);
        shader.getUniform("Smoothness").set(0.5f);
        shader.getUniform("EnableFadeout").set(enableFadeout ? 1 : 0);
        shader.getUniform("FadeoutStart").set(fadeoutStart);
        shader.getUniform("FadeoutEnd").set(fadeoutEnd);
        shader.getUniform("MaxWidth").set(maxWidth);
        shader.getUniform("TextPosX").set(x2);
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (FormattedTextProcessor.TextSegment segment : segments) {
            font.applyGlyphs(matrix, (VertexConsumer)builder, segment.text(), size, thickness * 0.5f * size, spacing - 0.3f, currentX - 0.75f, y2 + size * 0.7f, z2, segment.color());
            currentX += font.getWidth(segment.text(), size);
        }
        BuiltBuffer builtBuffer = builder.endNullable();
        if (builtBuffer != null) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builtBuffer);
        }
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    public static void renderText(MsdfFont font, Text text, float size, Matrix4f matrix, float x2, float y2, float z2, boolean enableFadeout, float fadeoutStart, float fadeoutEnd, float maxWidth, int alpha) {
        float thickness = 0.05f;
        float smoothness = 0.5f;
        float spacing = 0.0f;
        List<FormattedTextProcessor.TextSegment> segments = FormattedTextProcessor.processText(text, -1);
        float currentX = x2;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShaderTexture((int)0, (int)font.getTextureId());
        ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)MSDF_FONT_SHADER_KEY);
        shader.getUniform("Range").set(font.getAtlas().range());
        shader.getUniform("Thickness").set(thickness);
        shader.getUniform("Smoothness").set(0.5f);
        shader.getUniform("EnableFadeout").set(enableFadeout ? 1 : 0);
        shader.getUniform("FadeoutStart").set(fadeoutStart);
        shader.getUniform("FadeoutEnd").set(fadeoutEnd);
        shader.getUniform("MaxWidth").set(maxWidth);
        shader.getUniform("TextPosX").set(x2);
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (FormattedTextProcessor.TextSegment segment : segments) {
            int color = segment.color();
            if (alpha != 255) {
                color = color & 0xFFFFFF | (alpha & 0xFF) << 24;
            }
            font.applyGlyphs(matrix, (VertexConsumer)builder, segment.text(), size, thickness * 0.5f * size, spacing - 0.3f, currentX - 0.75f, y2 + size * 0.7f, z2, color);
            currentX += font.getWidth(segment.text(), size);
        }
        BuiltBuffer builtBuffer = builder.endNullable();
        if (builtBuffer != null) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builtBuffer);
        }
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    public static void renderText(MsdfFont font, Text text, float size, Matrix4f matrix, float x2, float y2, float z2, boolean enableFadeout, float fadeoutStart, float fadeoutEnd) {
        float maxWidth = font.getWidth(text, size) * 2.0f;
        MsdfRenderer.renderText(font, text, size, matrix, x2, y2, z2, enableFadeout, fadeoutStart, fadeoutEnd, maxWidth);
    }

    private MsdfRenderer() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

