package ua.babka.render.builder;

import net.minecraft.client.texture.AbstractTexture;
import ua.babka.render.builder.AbstractBuilder;
import ua.babka.render.builder.BuiltTexture;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;

public final class TextureBuilder
extends AbstractBuilder<BuiltTexture> {
    private SizeState size;
    private QuadRadiusState radius;
    private QuadColorState color;
    private float smoothness;
    private float u;
    private float v;
    private float texWidth;
    private float texHeight;
    private int textureId;

    public TextureBuilder size(SizeState size) {
        this.size = size;
        return this;
    }

    public TextureBuilder radius(QuadRadiusState radius) {
        this.radius = radius;
        return this;
    }

    public TextureBuilder color(QuadColorState color) {
        this.color = color;
        return this;
    }

    public TextureBuilder smoothness(float smoothness) {
        this.smoothness = smoothness;
        return this;
    }

    public TextureBuilder texture(float u2, float v2, float texWidth, float texHeight, AbstractTexture texture) {
        return this.texture(u2, v2, texWidth, texHeight, texture.getGlId());
    }

    public TextureBuilder texture(float u2, float v2, float texWidth, float texHeight, int textureId) {
        this.u = u2;
        this.v = v2;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.textureId = textureId;
        return this;
    }

    @Override
    protected BuiltTexture _build() {
        return new BuiltTexture(this.size, this.radius, this.color, this.smoothness, this.u, this.v, this.texWidth, this.texHeight, this.textureId);
    }

    @Override
    protected void reset() {
        this.size = SizeState.NONE;
        this.radius = QuadRadiusState.NO_ROUND;
        this.color = QuadColorState.WHITE;
        this.smoothness = 1.0f;
        this.u = 0.0f;
        this.v = 0.0f;
        this.texWidth = 0.0f;
        this.texHeight = 0.0f;
        this.textureId = 0;
    }
}

