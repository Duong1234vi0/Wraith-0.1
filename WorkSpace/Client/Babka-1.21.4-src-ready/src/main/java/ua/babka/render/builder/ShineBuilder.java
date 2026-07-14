package ua.babka.render.builder;

import ua.babka.render.builder.AbstractBuilder;
import ua.babka.render.builder.BuiltShine;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;

public final class ShineBuilder
extends AbstractBuilder<BuiltShine> {
    private SizeState size;
    private QuadRadiusState radius;
    private QuadColorState color;
    private float smoothness;
    private float time;
    private float shineWidth;

    public ShineBuilder size(SizeState size) {
        this.size = size;
        return this;
    }

    public ShineBuilder radius(QuadRadiusState radius) {
        this.radius = radius;
        return this;
    }

    public ShineBuilder color(QuadColorState color) {
        this.color = color;
        return this;
    }

    public ShineBuilder smoothness(float smoothness) {
        this.smoothness = smoothness;
        return this;
    }

    public ShineBuilder time(float time) {
        this.time = time;
        return this;
    }

    public ShineBuilder shineWidth(float shineWidth) {
        this.shineWidth = shineWidth;
        return this;
    }

    @Override
    protected BuiltShine _build() {
        return new BuiltShine(this.size, this.radius, this.color, this.smoothness, this.time, this.shineWidth);
    }

    @Override
    protected void reset() {
        this.size = SizeState.NONE;
        this.radius = QuadRadiusState.NO_ROUND;
        this.color = QuadColorState.TRANSPARENT;
        this.smoothness = 1.0f;
        this.time = 0.0f;
        this.shineWidth = 0.06f;
    }
}

