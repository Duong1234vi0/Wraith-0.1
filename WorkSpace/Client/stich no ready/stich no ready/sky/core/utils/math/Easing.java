package sky.core.utils.math;

import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

public enum Easing {
   EASE_IN_QUAD((x) -> {
      return x * x;
   }),
   EASE_OUT_QUAD((x) -> {
      return x * (2.0 - x);
   }),
   EASE_IN_OUT_QUAD((x) -> {
      return x < 0.5 ? 2.0 * x * x : -1.0 + (4.0 - 2.0 * x) * x;
   }),
   EASE_IN_CUBIC((x) -> {
      return x * x * x;
   }),
   EASE_IN_OUT_CUBIC((x) -> {
      return x < 0.5 ? 4.0 * x * x * x : (x - 1.0) * (2.0 * x - 2.0) * (2.0 * x - 2.0) + 1.0;
   }),
   EASE_IN_QUART((x) -> {
      return x * x * x * x;
   }),
   EASE_OUT_QUART((x) -> {
      x = x - 1.0;
      return 1.0 - x * x * x * x;
   }),
   EASE_OUT_BACK((x) -> {
      return 1.0 + 2.70158 * Math.pow(x - 1.0, 3.0) + 1.70158 * Math.pow(x - 1.0, 2.0);
   }),
   EASE_IN_OUT_QUART((x) -> {
      double d;
      if (x < 0.5) {
         d = 8.0 * x * x * x * x;
      } else {
         x = x - 1.0;
         d = 1.0 - 8.0 * x * x * x * x;
      }

      return d;
   }),
   EASE_IN_QUINT((x) -> {
      return x * x * x * x * x;
   }),
   EASE_OUT_QUINT((x) -> {
      x = x - 1.0;
      return 1.0 + x * x * x * x * x;
   }),
   EASE_IN_OUT_QUINT((x) -> {
      double d;
      if (x < 0.5) {
         d = 16.0 * x * x * x * x * x;
      } else {
         x = x - 1.0;
         d = 1.0 + 16.0 * x * x * x * x * x;
      }

      return d;
   }),
   EASE_IN_SINE((x) -> {
      return 1.0 - Math.cos(x * Math.PI / 2.0);
   }),
   EASE_OUT_SINE((x) -> {
      return Math.sin(x * Math.PI / 2.0);
   }),
   EASE_IN_OUT_SINE((x) -> {
      return 1.0 - Math.cos(Math.PI * x / 2.0);
   }),
   EASE_IN_EXPO((x) -> {
      return x == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * x - 10.0);
   }),
   EASE_IN_OUT_EXPO((x) -> {
      return x == 0.0 ? 0.0 : (x == 1.0 ? 1.0 : (x < 0.5 ? Math.pow(2.0, 20.0 * x - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * x + 10.0)) / 2.0));
   }),
   EASE_IN_CIRC((x) -> {
      return 1.0 - Math.sqrt(1.0 - x * x);
   }),
   EASE_OUT_CIRC((x) -> {
      x = x - 1.0;
      return Math.sqrt(1.0 - x * x);
   }),
   EASE_IN_OUT_CIRC((x) -> {
      return x < 0.5 ? (1.0 - Math.sqrt(1.0 - 4.0 * x * x)) / 2.0 : (Math.sqrt(1.0 - 4.0 * (x - 1.0) * x) + 1.0) / 2.0;
   }),
   SIGMOID((x) -> {
      return 1.0 / (1.0 + Math.exp(-x));
   }),
   EASE_OUT_ELASTIC((x) -> {
      return x == 0.0 ? 0.0 : (x == 1.0 ? 1.0 : Math.pow(2.0, -10.0 * x) * Math.sin((x * 10.0 - 0.75) * 2.0943951023931953) * 0.5 + 1.0);
   }),
   EASE_IN_BACK((x) -> {
      return 2.70158 * x * x * x - 1.70158 * x * x;
   }),
   LINEAR((x) -> {
      return x;
   }),
   EASE_OUT_EXPO((x) -> {
      return x.equals(1.0) ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * x);
   }),
   EASE_OUT_CUBIC((x) -> {
      double adjustedX = x - 1.0;
      return adjustedX * adjustedX * adjustedX + 1.0;
   }),
   CIRC_OUT((x) -> {
      return Math.sqrt(1.0 - Math.pow(x - 1.0, 2.0));
   });

   private final Function function;

   private Easing(Function function) {
      this.function = function;
   }

   public String toString() {
      return StringUtils.capitalize(super.toString().toLowerCase().replace("_", " "));
   }

   public Function getFunction() {
      return this.function;
   }

   // $FF: synthetic method
   private static Easing[] $values() {
      return new Easing[]{EASE_IN_QUAD, EASE_OUT_QUAD, EASE_IN_OUT_QUAD, EASE_IN_CUBIC, EASE_IN_OUT_CUBIC, EASE_IN_QUART, EASE_OUT_QUART, EASE_OUT_BACK, EASE_IN_OUT_QUART, EASE_IN_QUINT, EASE_OUT_QUINT, EASE_IN_OUT_QUINT, EASE_IN_SINE, EASE_OUT_SINE, EASE_IN_OUT_SINE, EASE_IN_EXPO, EASE_IN_OUT_EXPO, EASE_IN_CIRC, EASE_OUT_CIRC, EASE_IN_OUT_CIRC, SIGMOID, EASE_OUT_ELASTIC, EASE_IN_BACK, LINEAR, EASE_OUT_EXPO, EASE_OUT_CUBIC, CIRC_OUT};
   }

   public interface Easing2 {
      float ease(float var1);
   }
}
