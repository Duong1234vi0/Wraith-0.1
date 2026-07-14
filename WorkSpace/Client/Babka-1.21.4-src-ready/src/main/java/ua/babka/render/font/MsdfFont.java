package ua.babka.render.font;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import ua.babka.render.ResourceProvider;
import ua.babka.render.font.FontData;
import ua.babka.render.font.MsdfGlyph;
import ua.babka.util.ParseTextUtil;

public final class MsdfFont {
    private final String name;
    private final AbstractTexture texture;
    private final FontData.AtlasData atlas;
    private final FontData.MetricsData metrics;
    private final Map<Integer, MsdfGlyph> glyphs;
    private final Map<Integer, Map<Integer, Float>> kernings;

    private MsdfFont(String name, AbstractTexture texture, FontData.AtlasData atlas, FontData.MetricsData metrics, Map<Integer, MsdfGlyph> glyphs, Map<Integer, Map<Integer, Float>> kernings) {
        this.name = name;
        this.texture = texture;
        this.atlas = atlas;
        this.metrics = metrics;
        this.glyphs = glyphs;
        this.kernings = kernings;
    }

    public int getTextureId() {
        return this.texture.getGlId();
    }

    public void applyGlyphs(Matrix4f matrix, VertexConsumer consumer, String text, float size, float thickness, float spacing, float x2, float y2, float z2, int color) {
        int prevChar = -1;
        for (int i2 = 0; i2 < text.length(); ++i2) {
            char c2 = text.charAt(i2);
            if (c2 == '§' && i2 + 1 < text.length()) {
                ++i2;
                continue;
            }
            char _char = c2;
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) continue;
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                x2 += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            x2 += glyph.apply(matrix, consumer, size, x2, y2, z2, color) + thickness + spacing;
            prevChar = _char;
        }
    }

    public void applyGlyphs(Matrix4f matrix, VertexConsumer consumer, List<ColoredGlyph> glyphs, float size, float thickness, float spacing, float x2, float y2, float z2) {
        int prevChar = -1;
        for (int i2 = 0; i2 < glyphs.size(); ++i2) {
            ColoredGlyph glyphData = glyphs.get(i2);
            char _char = glyphData.c();
            int color = glyphData.color();
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) continue;
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                x2 += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            x2 += glyph.apply(matrix, consumer, size, x2, y2, z2, color) + thickness + spacing;
            prevChar = _char;
        }
    }

    public float getWidth(Text text, float size) {
        List<ColoredGlyph> glyphs = ParseTextUtil.parseTextToColoredGlyphs(text);
        int prevChar = -1;
        float width = 0.0f;
        for (int i2 = 0; i2 < glyphs.size(); ++i2) {
            char _char = glyphs.get(i2).c();
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) continue;
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                width += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            width += glyph.getWidth(size);
            prevChar = _char;
        }
        return width;
    }

    public float getWidth(String text, float size) {
        int prevChar = -1;
        float width = 0.0f;
        for (int i2 = 0; i2 < text.length(); ++i2) {
            char c2 = text.charAt(i2);
            if (c2 == '§' && i2 + 1 < text.length()) {
                ++i2;
                continue;
            }
            char _char = c2;
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) continue;
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                width += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            width += glyph.getWidth(size);
            prevChar = _char;
        }
        return width;
    }

    public String getName() {
        return this.name;
    }

    public FontData.AtlasData getAtlas() {
        return this.atlas;
    }

    public FontData.MetricsData getMetrics() {
        return this.metrics;
    }

    public static Builder builder() {
        return new Builder();
    }

    public record ColoredGlyph(char c, int color) {
    }

    public static class Builder {
        private String name = "?";
        private Identifier dataIdentifer;
        private Identifier atlasIdentifier;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder data(String dataFileName) {
            this.dataIdentifer = Identifier.of((String)"mre", (String)("fonts/" + dataFileName + ".json"));
            return this;
        }

        public Builder atlas(String atlasFileName) {
            this.atlasIdentifier = Identifier.of((String)"mre", (String)("fonts/" + atlasFileName + ".png"));
            return this;
        }

        public MsdfFont build() {
            FontData data = ResourceProvider.fromJsonToInstance(this.dataIdentifer, FontData.class);
            AbstractTexture texture = MinecraftClient.getInstance().getTextureManager().getTexture(this.atlasIdentifier);
            if (data == null) {
                throw new RuntimeException("Failed to read font data file: " + this.dataIdentifer.toString() + "; Are you sure this is json file? Try to check the correctness of its syntax.");
            }
            RenderSystem.recordRenderCall(() -> texture.setFilter(true, false));
            float aWidth = data.atlas().width();
            float aHeight = data.atlas().height();
            Map<Integer, MsdfGlyph> glyphs = data.glyphs().stream().collect(Collectors.toMap(glyphData -> glyphData.unicode(), glyphData -> new MsdfGlyph((FontData.GlyphData)glyphData, aWidth, aHeight)));
            HashMap<Integer, Map<Integer, Float>> kernings = new HashMap<Integer, Map<Integer, Float>>();
            data.kernings().forEach(kerning -> {
                HashMap<Integer, Float> map = (HashMap<Integer, Float>)kernings.get(kerning.leftChar());
                if (map == null) {
                    map = new HashMap<Integer, Float>();
                    kernings.put(kerning.leftChar(), map);
                }
                map.put(kerning.rightChar(), Float.valueOf(kerning.advance()));
            });
            return new MsdfFont(this.name, texture, data.atlas(), data.metrics(), glyphs, kernings);
        }
    }
}

