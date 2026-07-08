/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.utils.display.render;

import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.systemrender.builders.Builder;
import code.alphadlc.utils.display.render.systemrender.builders.states.QuadColorState;
import code.alphadlc.utils.display.render.systemrender.builders.states.QuadRadiusState;
import code.alphadlc.utils.display.render.systemrender.builders.states.SizeState;
import code.alphadlc.utils.display.render.systemrender.renderers.impl.BuiltTexture;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public final class BackgroundIcons {
    public static final float HUD_ICON_SIZE = 9.5f;
    public static final float SIDEBAR_ICON_SIZE = 14.0f;
    public static final float ESP_CATEGORY_ICON_SIZE = 9.0f;
    public static final class_2960 ESP = BackgroundIcons.icon("esp.png");
    public static final class_2960 POTIONS = BackgroundIcons.icon("potions.png");
    public static final class_2960 KEYBINDS = BackgroundIcons.icon("keybinds.png");
    public static final class_2960 STAFFLIST = BackgroundIcons.icon("stafflist.png");
    public static final class_2960 COOLDOWNS = BackgroundIcons.icon("cooldowns.png");
    public static final class_2960 BACKGROUND = BackgroundIcons.icon("background.png");
    public static final class_2960 SINGLE = BackgroundIcons.icon("1.png");
    public static final class_2960 MULTI = BackgroundIcons.icon("2.png");
    public static final class_2960 SOLO = BackgroundIcons.icon("solo.png");
    public static final class_2960 MULTIPLAYER = BackgroundIcons.icon("multiplayer.png");
    public static final class_2960 SETTINGS = BackgroundIcons.icon("settings.png");
    public static final class_2960 ACCOUNT = BackgroundIcons.icon("account.png");
    public static final class_2960 EXIT = BackgroundIcons.icon("exit.png");
    public static final class_2960 TELEGRAM = BackgroundIcons.icon("telegram.png");
    public static final class_2960 YOUTUBE = BackgroundIcons.icon("youtube.png");
    public static final class_2960 DISCORD = BackgroundIcons.icon("discord.png");

    private BackgroundIcons() {
    }

    public static class_2960 icon(String fileName) {
        return class_2960.method_60655((String)"alphadlc", (String)("background/" + fileName));
    }

    public static void draw(class_332 context, class_2960 texture, float x, float y, float size, int color) {
        class_4587 matrices = context.method_51448();
        int glId = class_310.method_1551().method_1531().method_4619(texture).method_4624();
        ((BuiltTexture)Builder.texture().size(new SizeState(size, size)).radius(QuadRadiusState.NO_ROUND).color(new QuadColorState(color)).smoothness(1.0f).texture(0.0f, 0.0f, 1.0f, 1.0f, glId).build()).render(matrices.method_23760().method_23761(), x, y, 0.0f);
    }

    public static void drawSmall(class_332 context, class_2960 texture, float x, float y, int color) {
        BackgroundIcons.draw(context, texture, x, y, 9.5f, color);
    }

    public static void drawSidebar(class_332 context, class_2960 texture, float x, float y, int color) {
        BackgroundIcons.draw(context, texture, x, y, 14.0f, color);
    }

    public static void drawEspCategory(class_332 context, class_2960 texture, float x, float y, int color) {
        BackgroundIcons.draw(context, texture, x, y, 9.0f, color);
    }

    public static void drawSmallCentered(class_332 context, class_2960 texture, float centerX, float y, int color) {
        BackgroundIcons.drawSmall(context, texture, centerX - 4.75f, y, color);
    }

    public static void drawHudHeaderIcon(class_332 context, class_2960 texture, FontRenderer font, String label, float textY, float centerX, int color) {
        float iconY = textY + (font.getStringHeight(label) - 9.5f) / 2.0f;
        BackgroundIcons.drawSmallCentered(context, texture, centerX, iconY, color);
    }
}
