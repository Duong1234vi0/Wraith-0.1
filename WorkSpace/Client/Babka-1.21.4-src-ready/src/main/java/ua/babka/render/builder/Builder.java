package ua.babka.render.builder;

import ua.babka.render.builder.BlurBuilder;
import ua.babka.render.builder.BorderBuilder;
import ua.babka.render.builder.GlowBuilder;
import ua.babka.render.builder.MutableTextBuilder;
import ua.babka.render.builder.RectangleBuilder;
import ua.babka.render.builder.ShineBuilder;
import ua.babka.render.builder.TextBuilder;
import ua.babka.render.builder.TextureBuilder;

public final class Builder {
    private static final RectangleBuilder RECTANGLE_BUILDER = new RectangleBuilder();
    private static final BorderBuilder BORDER_BUILDER = new BorderBuilder();
    private static final TextureBuilder TEXTURE_BUILDER = new TextureBuilder();
    private static final TextBuilder TEXT_BUILDER = new TextBuilder();
    private static final MutableTextBuilder MUTABLE_TEXT_BUILDER = new MutableTextBuilder();
    private static final BlurBuilder BLUR_BUILDER = new BlurBuilder();
    private static final GlowBuilder GLOW_BUILDER = new GlowBuilder();
    private static final ShineBuilder SHINE_BUILDER = new ShineBuilder();

    public static GlowBuilder glow() {
        return GLOW_BUILDER;
    }

    public static RectangleBuilder rectangle() {
        return RECTANGLE_BUILDER;
    }

    public static BorderBuilder border() {
        return BORDER_BUILDER;
    }

    public static TextureBuilder texture() {
        return TEXTURE_BUILDER;
    }

    public static TextBuilder text() {
        return TEXT_BUILDER;
    }

    public static MutableTextBuilder mutableText() {
        return MUTABLE_TEXT_BUILDER;
    }

    public static BlurBuilder blur() {
        return BLUR_BUILDER;
    }

    public static ShineBuilder shine() {
        return SHINE_BUILDER;
    }
}

