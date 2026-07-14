package ua.babka.render.builder;

import ua.babka.render.builder.AbstractBuilder;
import ua.babka.render.builder.BuiltGlow;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;

public final class GlowBuilder
extends AbstractBuilder<BuiltGlow> {
    private SizeState size;
    private QuadRadiusState radius;
    private QuadColorState color;
    private float glowRadius;
    private float softness;
    private float intensity;
    private boolean additive;

    public GlowBuilder size(SizeState size) {
        this.size = size;
        return this;
    }

    public GlowBuilder radius(QuadRadiusState radius) {
        this.radius = radius;
        return this;
    }

    public GlowBuilder color(QuadColorState color) {
        this.color = color;
        return this;
    }

    public GlowBuilder glowRadius(float glowRadius) {
        this.glowRadius = glowRadius;
        return this;
    }

    public GlowBuilder softness(float softness) {
        this.softness = softness;
        return this;
    }

    public GlowBuilder intensity(float intensity) {
        this.intensity = intensity;
        return this;
    }

    public GlowBuilder additive(boolean additive) {
        this.additive = additive;
        return this;
    }

    @Override
    protected BuiltGlow _build() {
        return new BuiltGlow(this.size, this.radius, this.color, this.glowRadius, this.softness, this.intensity, this.additive);
    }

    @Override
    protected void reset() {
        this.size = SizeState.NONE;
        this.radius = QuadRadiusState.NO_ROUND;
        this.color = QuadColorState.TRANSPARENT;
        this.glowRadius = 8.0f;
        this.softness = 1.25f;
        this.intensity = 1.0f;
        this.additive = true;
    }
}

