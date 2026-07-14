package ua.babka.render.font;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

public class SkycoreIcons {
    private static final Map<Character, IconEntry> icons = new HashMap<Character, IconEntry>();
    private static boolean loaded = false;

    public static void load() {
        if (loaded) {
            return;
        }
        loaded = true;
        try {
            Font font = Font.createFont(0, SkycoreIcons.class.getResourceAsStream("/assets/mre/fonts/skycore.ttf"));
            font = font.deriveFont(40.0f);
            SkycoreIcons.renderIcon(font, 'l', "skycore_combat");
            SkycoreIcons.renderIcon(font, 'm', "skycore_movement");
            SkycoreIcons.renderIcon(font, 'b', "skycore_render");
            SkycoreIcons.renderIcon(font, 'i', "skycore_player");
            SkycoreIcons.renderIcon(font, 'j', "skycore_misc");
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void renderIcon(Font font, char ch, String name) {
        BufferedImage img = new BufferedImage(64, 64, 2);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, 64, 64);
        g2.setColor(Color.WHITE);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        int w2 = fm.charWidth(ch);
        int h2 = fm.getHeight();
        int x2 = (64 - w2) / 2;
        int y2 = 64 - h2 / 2;
        g2.drawString(String.valueOf(ch), x2, y2);
        g2.dispose();
        int[] pixels = new int[4096];
        for (int py = 0; py < 64; ++py) {
            for (int px = 0; px < 64; ++px) {
                int argb;
                pixels[py * 64 + px] = argb = img.getRGB(px, py);
            }
        }
        NativeImage nativeImage = new NativeImage(64, 64, false);
        for (int py = 0; py < 64; ++py) {
            for (int px = 0; px < 64; ++px) {
                int argb = pixels[py * 64 + px];
                int a2 = argb >> 24 & 0xFF;
                int r2 = argb >> 16 & 0xFF;
                int g22 = argb >> 8 & 0xFF;
                int b2 = argb & 0xFF;
                nativeImage.setColorArgb(px, py, a2 << 24 | r2 << 16 | g22 << 8 | b2);
            }
        }
        RenderSystem.recordRenderCall(() -> {
            Identifier id = Identifier.of((String)"babka", (String)name);
            NativeImageBackedTexture tex = new NativeImageBackedTexture(nativeImage);
            MinecraftClient.getInstance().getTextureManager().registerTexture(id, (AbstractTexture)tex);
            icons.put(Character.valueOf(ch), new IconEntry(tex.getGlId(), 64, 64));
        });
    }

    public static IconEntry getIcon(char ch) {
        return icons.get(Character.valueOf(ch));
    }

    public static int getIconTexture(char ch) {
        IconEntry entry = icons.get(Character.valueOf(ch));
        return entry != null ? entry.textureId : 0;
    }

    public record IconEntry(int textureId, int width, int height) {
    }
}

