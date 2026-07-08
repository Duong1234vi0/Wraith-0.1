/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package code.alphadlc.utils.display.render.font;

import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.MsdfFontLibrary;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_310;

public class Fonts {
    private static final Map<FontKey, FontRenderer> fontCache = new HashMap<FontKey, FontRenderer>();

    public static void init() {
        Fonts.clearCache();
    }

    public static void clearCache() {
        fontCache.clear();
        MsdfFontLibrary.clear();
    }

    public static FontRenderer getSize(int size) {
        return Fonts.getSize(size, Type.BOLD);
    }

    public static FontRenderer getSize(int size, Type type) {
        float guiScale = Fonts.currentGuiScale();
        return fontCache.computeIfAbsent(new FontKey(size, type, guiScale), k -> Fonts.create(size, type.getType()));
    }

    private static float currentGuiScale() {
        class_310 client = class_310.method_1551();
        if (client == null || client.method_22683() == null) {
            return 8.0f;
        }
        return Math.max(4.0f, (float)client.method_22683().method_4495()) * 1.5f;
    }

    public static FontRenderer create(float size, String name) {
        return new FontRenderer(name, size / 2.0f);
    }

    public static enum Type {
        DEFAULT("sf_medium"),
        REGULAR("sf_regular"),
        SEMI("sf_semibold"),
        BOLD("sf_bold"),
        ROCKSTAR_REGULAR("SuisseIntl-Regular"),
        ROCKSTAR_MEDIUM("SuisseIntl-Medium"),
        ROCKSTAR_SEMIBOLD("suisseintl-semibold"),
        ICONS("icons"),
        ICONS2("clienticon1"),
        ICONSTYPENEW("icon2"),
        GUIICONS("guiicons"),
        ALPHADLC_ICONS("alphadlcicons"),
        INTER_MEDIUM("icon"),
        SuisseIntlMedium("SuisseIntl-Medium"),
        SuisseIntlRegular("SuisseIntl-Regular"),
        SuisseIntlSemiBold("suisseintl-semibold"),
        ICONSCATEGORY("categoryicons"),
        WAYPOINT_ICONS("waypoint_icons");

        private final String type;

        public String getType() {
            return this.type;
        }

        private Type(String type) {
            this.type = type;
        }
    }

    private record FontKey(int size, Type type, float guiScale) {
    }
}
