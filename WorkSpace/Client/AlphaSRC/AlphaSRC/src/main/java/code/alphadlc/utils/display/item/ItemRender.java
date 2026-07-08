/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10444
 *  net.minecraft.class_1058
 *  net.minecraft.class_1087
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_3532
 *  net.minecraft.class_425
 *  net.minecraft.class_4587
 *  net.minecraft.class_811
 *  net.minecraft.class_9848
 */
package code.alphadlc.utils.display.item;

import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.minecraft.class_10444;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_3532;
import net.minecraft.class_425;
import net.minecraft.class_4587;
import net.minecraft.class_811;
import net.minecraft.class_9848;

public final class ItemRender
implements QuickImports {
    public static final HashMap<class_1799, CachedSprite> SPRITE_CACHE = new HashMap();

    public static void drawItem(class_4587 matrix, class_1799 stack, float x, float y, boolean count, boolean bar) {
        CachedSprite cachedSprite = ItemRender.getSpriteTexture(stack);
        if (cachedSprite != null) {
            Render2D.drawSprite(matrix, cachedSprite.sprite, x, y, 16.0f, 16);
            if (stack != null && stack.method_7958()) {
                ItemRender.drawGlint(matrix, cachedSprite.sprite, x, y);
            }
        }
        if (count) {
            ItemRender.drawCount(matrix, stack, x, y);
        }
        if (bar) {
            ItemRender.drawBar(matrix, stack, x, y);
        }
    }

    private static void drawGlint(class_4587 matrix, class_1058 sprite, float x, float y) {
        long now = System.currentTimeMillis();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        ItemRender.drawGlintLayer(matrix, sprite, x, y, (float)(now % 1300L) / 1300.0f, false, 230, 150, 255, 0.95f);
        ItemRender.drawGlintLayer(matrix, sprite, x, y, (float)(now % 1900L) / 1900.0f, true, 150, 90, 255, 0.85f);
        ItemRender.drawGlintLayer(matrix, sprite, x, y, (float)(now % 850L) / 850.0f, false, 255, 255, 255, 0.5f);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private static void drawGlintLayer(class_4587 matrix, class_1058 sprite, float x, float y, float phase, boolean reversed, int r, int g, int b, float maxIntensity) {
        int tl = ItemRender.glintColor(ItemRender.cornerDiagonal(0.0f, 0.0f, reversed), phase, r, g, b, maxIntensity);
        int bl = ItemRender.glintColor(ItemRender.cornerDiagonal(0.0f, 1.0f, reversed), phase, r, g, b, maxIntensity);
        int br = ItemRender.glintColor(ItemRender.cornerDiagonal(1.0f, 1.0f, reversed), phase, r, g, b, maxIntensity);
        int tr = ItemRender.glintColor(ItemRender.cornerDiagonal(1.0f, 0.0f, reversed), phase, r, g, b, maxIntensity);
        Render2D.drawSpriteColored(matrix, sprite, x, y, 16.0f, 16.0f, tl, bl, br, tr);
    }

    private static float cornerDiagonal(float u, float v, boolean reversed) {
        return reversed ? (u + (1.0f - v)) * 0.5f : (u + v) * 0.5f;
    }

    private static int glintColor(float diagonal, float phase, int r, int g, int b, float maxIntensity) {
        float band = 0.5f + 0.5f * class_3532.method_15362((float)((float)(Math.PI * 2 * (double)(diagonal - phase))));
        float intensity = (0.25f + 0.75f * band * band) * maxIntensity;
        int alpha = class_3532.method_15340((int)((int)(intensity * 255.0f)), (int)0, (int)255);
        return class_9848.method_61324((int)alpha, (int)r, (int)g, (int)b);
    }

    private static void drawCount(class_4587 matrix, class_1799 stack, float x, float y) {
        Object text;
        int count = stack.method_7947();
        Object object = text = count > 1 ? "" + count : "";
        if (!((String)text).isEmpty()) {
            FontRenderer font = Fonts.getSize(16);
            font.drawString(matrix, (String)text, x + 16.0f - font.getStringWidth((String)text), y + 11.0f, ColorAssist.getText());
        }
    }

    private static void drawBar(class_4587 matrix, class_1799 stack, float x, float y) {
        if (stack.method_31578()) {
            rectangle.render(ShapeProperties.create(matrix, x + 1.5f, y + 13.0f, 13.0, 2.0).color(-16777216).build());
            rectangle.render(ShapeProperties.create(matrix, x + 1.5f, y + 13.0f, stack.method_31579(), 1.0).color(class_9848.method_61334((int)stack.method_31580())).build());
        }
    }

    private static CachedSprite getSpriteTexture(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return null;
        }
        if (stack.method_31574(class_1802.field_8251)) {
            return null;
        }
        if (mc == null || mc.method_65386() == null) {
            return null;
        }
        if (mc.method_18506() instanceof class_425) {
            return null;
        }
        return SPRITE_CACHE.computeIfAbsent(stack, key -> {
            class_10444 state = new class_10444();
            try {
                mc.method_65386().method_65596(state, stack, class_811.field_4317, (class_1937)ItemRender.mc.field_1687, null, 0);
            }
            catch (Throwable ignored) {
                return null;
            }
            class_1058 sprite = ItemRender.getFirstParticleSprite(state);
            if (sprite != null) {
                int glId = mc.method_1531().method_4619(sprite.method_45852()).method_4624();
                return new CachedSprite(sprite, glId, 0xFFFFFF);
            }
            return null;
        });
    }

    private static class_1058 getFirstParticleSprite(class_10444 state) {
        if (state == null || state.field_55339 == 0) {
            return null;
        }
        class_1087 model = state.field_55340[0].field_55346;
        if (model == null) {
            return null;
        }
        return model.method_4711();
    }

    private ItemRender() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public record CachedSprite(class_1058 sprite, int glId, int color) {
    }
}
