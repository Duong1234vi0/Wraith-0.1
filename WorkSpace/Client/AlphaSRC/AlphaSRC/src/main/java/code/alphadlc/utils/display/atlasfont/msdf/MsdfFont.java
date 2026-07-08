/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1044
 *  net.minecraft.class_1049
 *  net.minecraft.class_10537
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_4588
 *  org.joml.Matrix4f
 */
package code.alphadlc.utils.display.atlasfont.msdf;

import code.alphadlc.utils.display.atlasfont.msdf.FontData;
import code.alphadlc.utils.display.atlasfont.msdf.MsdfGlyph;
import code.alphadlc.utils.display.atlasfont.providers.ResourceProvider;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.class_1044;
import net.minecraft.class_1049;
import net.minecraft.class_10537;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4588;
import org.joml.Matrix4f;

public final class MsdfFont {
    private final String name;
    private final class_1044 texture;
    private final FontData.AtlasData atlas;
    private final FontData.MetricsData metrics;
    private final Map<Integer, MsdfGlyph> glyphs;
    private final Map<Integer, Map<Integer, Float>> kernings;

    private static String resolveMsdfResourceName(String name) {
        return switch (name) {
            case "suisseintl-semibold", "suisseintlsemibold" -> "suisseintlsemibold";
            default -> name;
        };
    }

    private MsdfFont(String name, class_1044 texture, FontData.AtlasData atlas, FontData.MetricsData metrics, Map<Integer, MsdfGlyph> glyphs, Map<Integer, Map<Integer, Float>> kernings) {
        this.name = name;
        this.texture = texture;
        this.atlas = atlas;
        this.metrics = metrics;
        this.glyphs = glyphs;
        this.kernings = kernings;
    }

    public int getTextureId() {
        return this.texture.method_4624();
    }

    public void applyGlyphs(Matrix4f matrix, class_4588 consumer, String text, float size, float thickness, float spacing, float x, float y, float z, int color) {
        int prevChar = -1;
        for (int i = 0; i < text.length(); ++i) {
            char _char = text.charAt(i);
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) {
                if (_char != ' ') continue;
                x += this.getSpaceAdvance(size) + thickness + spacing;
                prevChar = _char;
                continue;
            }
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                x += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            x += glyph.apply(matrix, consumer, size, x, y, z, color) + thickness + spacing;
            prevChar = _char;
        }
    }

    public void applyRainbowGlyphs(Matrix4f matrix, class_4588 consumer, String text, float size, float thickness, float spacing, float x, float y, float z, int color1, int color2) {
        int prevChar = -1;
        long time = System.currentTimeMillis();
        for (int i = 0; i < text.length(); ++i) {
            char _char = text.charAt(i);
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) {
                if (_char != ' ') continue;
                x += this.getSpaceAdvance(size) + thickness + spacing;
                prevChar = _char;
                continue;
            }
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                x += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            float t = (float)((Math.sin((double)(time + (long)(i * 100)) / 500.0) + 1.0) / 2.0);
            t = Math.max(0.0f, Math.min(1.0f, t));
            int color = this.interpolateColor(color1, color2, t);
            x += glyph.apply(matrix, consumer, size, x, y, z, color) + thickness + spacing;
            prevChar = _char;
        }
    }

    private int interpolateColor(int colorStart, int colorEnd, float t) {
        float startAlpha = (float)(colorStart >> 24 & 0xFF) / 255.0f;
        float startRed = (float)(colorStart >> 16 & 0xFF) / 255.0f;
        float startGreen = (float)(colorStart >> 8 & 0xFF) / 255.0f;
        float startBlue = (float)(colorStart & 0xFF) / 255.0f;
        float endAlpha = (float)(colorEnd >> 24 & 0xFF) / 255.0f;
        float endRed = (float)(colorEnd >> 16 & 0xFF) / 255.0f;
        float endGreen = (float)(colorEnd >> 8 & 0xFF) / 255.0f;
        float endBlue = (float)(colorEnd & 0xFF) / 255.0f;
        float alpha = startAlpha + t * (endAlpha - startAlpha);
        float red = startRed + t * (endRed - startRed);
        float green = startGreen + t * (endGreen - startGreen);
        float blue = startBlue + t * (endBlue - startBlue);
        return (int)(alpha * 255.0f) << 24 | (int)(red * 255.0f) << 16 | (int)(green * 255.0f) << 8 | (int)(blue * 255.0f);
    }

    public float getWidth(String text, float size) {
        return this.getLayoutWidth(text, size, 0.0f, 0.0f);
    }

    public float getLayoutWidth(String text, float size, float thickness, float spacing) {
        if (text == null || text.isEmpty()) {
            return 0.0f;
        }
        int prevChar = -1;
        float width = 0.0f;
        for (int i = 0; i < text.length(); ++i) {
            char _char = text.charAt(i);
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) {
                if (_char != ' ') continue;
                width += this.getSpaceAdvance(size) + thickness + spacing;
                prevChar = _char;
                continue;
            }
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                width += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            width += glyph.getWidth(size) + thickness + spacing;
            prevChar = _char;
        }
        return width;
    }

    private float getSpaceAdvance(float size) {
        MsdfGlyph space = this.glyphs.get(Character.valueOf(' '));
        return space != null ? space.getWidth(size) : 0.25f * size;
    }

    public float getVisualRightEdge(String text, float size, float effectiveThickness, float spacing) {
        if (text == null || text.isEmpty()) {
            return 0.0f;
        }
        int prevChar = -1;
        float x = 0.0f;
        for (int i = 0; i < text.length(); ++i) {
            char _char = text.charAt(i);
            MsdfGlyph glyph = this.glyphs.get(_char);
            if (glyph == null) continue;
            Map<Integer, Float> kerning = this.kernings.get(prevChar);
            if (kerning != null) {
                x += kerning.getOrDefault(_char, Float.valueOf(0.0f)).floatValue() * size;
            }
            x = i < text.length() - 1 ? (x += glyph.getWidth(size) + effectiveThickness + spacing) : (x += glyph.getVisualWidth(size));
            prevChar = _char;
        }
        return x;
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

    public static class Builder {
        private String name = "?";
        private class_2960 dataIdentifer;
        private class_2960 atlasIdentifier;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder data(String dataFileName) {
            String resolvedName = MsdfFont.resolveMsdfResourceName(dataFileName);
            this.dataIdentifer = class_2960.method_60655((String)"mre", (String)("fonts/" + resolvedName + ".json"));
            return this;
        }

        public Builder atlas(String atlasFileName) {
            String resolvedName = MsdfFont.resolveMsdfResourceName(atlasFileName);
            this.atlasIdentifier = class_2960.method_60655((String)"mre", (String)("fonts/" + resolvedName + ".png"));
            return this;
        }

        public MsdfFont build() {
            FontData data = null;
            try {
                data = ResourceProvider.fromJsonToInstance(this.dataIdentifer, FontData.class);
            }
            catch (Exception e) {
                System.err.println("[MsdfFont] CRITICAL: Failed to load font data: " + String.valueOf(this.dataIdentifer));
                e.printStackTrace();
                throw new RuntimeException("Failed to read font data file: " + this.dataIdentifer.toString() + "; Make sure the file exists in assets/mre/fonts/", e);
            }
            if (data == null) {
                throw new RuntimeException("Failed to read font data file: " + this.dataIdentifer.toString() + "; Are you sure this is json file? Try to check the correctness of its syntax.");
            }
            class_310 client = class_310.method_1551();
            class_1044 texture = client.method_1531().method_4619(this.atlasIdentifier);
            if (texture == null) {
                System.out.println("[MsdfFont] Registering font texture: " + String.valueOf(this.atlasIdentifier));
                try {
                    if (client.method_1478().method_14486(this.atlasIdentifier).isEmpty()) {
                        throw new RuntimeException("Font texture resource not found: " + this.atlasIdentifier.toString() + "; Make sure the file exists at assets/mre/fonts/" + this.atlasIdentifier.method_12832());
                    }
                    client.method_1531().method_65876(this.atlasIdentifier, (class_10537)new class_1049(this.atlasIdentifier));
                    texture = client.method_1531().method_4619(this.atlasIdentifier);
                    if (texture != null) {
                        System.out.println("[MsdfFont] Successfully registered font texture: " + String.valueOf(this.atlasIdentifier));
                    }
                }
                catch (Exception e) {
                    System.err.println("[MsdfFont] CRITICAL: Failed to register texture: " + String.valueOf(this.atlasIdentifier));
                    e.printStackTrace();
                    throw new RuntimeException("Failed to register texture: " + this.atlasIdentifier.toString() + "; Make sure the texture file exists at assets/mre/fonts/" + this.atlasIdentifier.method_12832(), e);
                }
            }
            if (texture == null) {
                throw new RuntimeException("Failed to load texture after registration: " + this.atlasIdentifier.toString() + "; Make sure the texture file exists at assets/mre/fonts/" + this.atlasIdentifier.method_12832());
            }
            class_1044 finalTexture = texture;
            RenderSystem.recordRenderCall(() -> finalTexture.method_4527(true, false));
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
            return new MsdfFont(this.name, finalTexture, data.atlas(), data.metrics(), glyphs, kernings);
        }
    }
}
