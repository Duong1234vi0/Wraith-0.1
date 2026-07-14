package ua.babka.render.builder;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
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
import net.minecraft.text.KeybindTextContent;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import org.joml.Matrix4f;
import ua.babka.render.ColorProvider;
import ua.babka.render.ResourceProvider;
import ua.babka.render.font.MsdfFont;
import ua.babka.render.model.IRenderer;

public record BuiltMutableText(MsdfFont font, Text text, float size, float thickness, int color, float smoothness, float spacing, int outlineColor, float outlineThickness, int alpha) implements IRenderer
{
    private static final ShaderProgramKey MSDF_FONT_SHADER_KEY = new ShaderProgramKey(ResourceProvider.getShaderIdentifier("msdf_font"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);

    public List<MsdfFont.ColoredGlyph> parseTextToColoredGlyphs(Text text) {
        ArrayList<MsdfFont.ColoredGlyph> result = new ArrayList<MsdfFont.ColoredGlyph>();
        this.parseTextRecursivePreservingColors(text, -1, result);
        return result;
    }

    private void parseTextRecursivePreservingColors(Text text, int currentColor, List<MsdfFont.ColoredGlyph> result) {
        Style style = text.getStyle();
        int color = style.getColor() != null ? ColorProvider.setAlpha(style.getColor().getRgb(), this.alpha) | ColorProvider.setAlpha(-16777216, this.alpha) : ColorProvider.setAlpha(currentColor, this.alpha);
        String content = BuiltMutableText.extractContent(text);
        int length = content.length();
        int i2 = 0;
        while (i2 < length) {
            char c2 = content.charAt(i2);
            if (c2 == '§' && i2 + 1 < length) {
                char code = Character.toLowerCase(content.charAt(i2 + 1));
                Formatting formatting = Formatting.byCode((char)code);
                if (formatting != null && formatting.isColor()) {
                    color = ColorProvider.setAlpha((int)formatting.getColorValue(), this.alpha) | ColorProvider.setAlpha(-16777216, this.alpha);
                } else if (formatting == Formatting.RESET) {
                    color = ColorProvider.setAlpha(currentColor, this.alpha);
                }
                i2 += 2;
                continue;
            }
            result.add(new MsdfFont.ColoredGlyph(c2, color));
            ++i2;
        }
        for (Text sibling : text.getSiblings()) {
            this.parseTextRecursivePreservingColors(sibling, color, result);
        }
    }

    private static String extractContent(Text text) {
        TextContent content = text.getContent();
        if (content instanceof PlainTextContent.Literal) {
            PlainTextContent.Literal literal = (PlainTextContent.Literal)content;
            return literal.string();
        }
        if (content instanceof TranslatableTextContent) {
            TranslatableTextContent translatable = (TranslatableTextContent)content;
            return translatable.getKey();
        }
        if (content instanceof KeybindTextContent) {
            KeybindTextContent keybind = (KeybindTextContent)content;
            return keybind.getKey();
        }
        return "";
    }

    @Override
    public void render(Matrix4f matrix, float x2, float y2, float z2) {
        List<MsdfFont.ColoredGlyph> glyphs = this.parseTextToColoredGlyphs(this.text);
        if (glyphs.isEmpty()) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShaderTexture((int)0, (int)this.font.getTextureId());
        boolean outlineEnabled = this.outlineThickness > 0.0f;
        ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)MSDF_FONT_SHADER_KEY);
        shader.getUniform("Range").set(this.font.getAtlas().range());
        shader.getUniform("Thickness").set(this.thickness);
        shader.getUniform("Smoothness").set(this.smoothness);
        shader.getUniform("Outline").set(outlineEnabled ? 1 : 0);
        if (outlineEnabled) {
            shader.getUniform("OutlineThickness").set(this.outlineThickness);
            float[] outlineComponents = ColorProvider.normalize(this.outlineColor);
            shader.getUniform("OutlineColor").set(outlineComponents[0], outlineComponents[1], outlineComponents[2], outlineComponents[3]);
        }
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        this.font.applyGlyphs(matrix, (VertexConsumer)builder, this.parseTextToColoredGlyphs(this.text), this.size, (this.thickness + this.outlineThickness * 0.5f) * 0.5f * this.size, this.spacing, x2, y2 + this.font.getMetrics().baselineHeight() * this.size, z2);
        if (builder.building) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builder.end());
        }
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }
}

