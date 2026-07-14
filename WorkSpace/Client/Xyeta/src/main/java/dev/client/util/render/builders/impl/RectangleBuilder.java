package dev.client.util.render.builders.impl;

import dev.client.util.render.builders.AbstractBuilder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class RectangleBuilder extends AbstractBuilder<BuiltRectangle> {
   private SizeState size;
   private QuadRadiusState radius;
   private QuadColorState color;
   private float smoothness;

   public RectangleBuilder size(SizeState size) {
      this.size = size;
      return this;
   }

   public RectangleBuilder radius(QuadRadiusState radius) {
      this.radius = radius;
      return this;
   }

   public RectangleBuilder color(QuadColorState color) {
      this.color = color;
      return this;
   }

   public RectangleBuilder smoothness(float smoothness) {
      this.smoothness = smoothness;
      return this;
   }

   protected BuiltRectangle _build() {
      return new BuiltRectangle(this.size, this.radius, this.color, this.smoothness);
   }

   protected void reset() {
      this.size = SizeState.NONE;
      this.radius = QuadRadiusState.NO_ROUND;
      this.color = QuadColorState.TRANSPARENT;
      this.smoothness = 1.0F;
   }
}
