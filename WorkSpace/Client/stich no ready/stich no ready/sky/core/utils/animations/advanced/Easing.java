package sky.core.utils.animations.advanced;

public enum Easing {
   LINEAR((x) -> {
      return x;
   }),
   BOTH_SINE((x) -> {
      return -(Math.cos(Math.PI * x) - 1.0) / 2.0;
   }),
   SINE_IN_OUT((x) -> {
      return -(Math.cos(Math.PI * x) - 1.0) / 2.0;
   }),
   BOTH_CIRC((x) -> {
      return x < 0.5 ? (1.0 - Math.sqrt(1.0 - Math.pow(2.0 * x, 2.0))) / 2.0 : (Math.sqrt(1.0 - Math.pow(-2.0 * x + 2.0, 2.0)) + 1.0) / 2.0;
   }),
   BOTH_CUBIC((x) -> {
      return x < 0.5 ? 4.0 * x * x * x : 1.0 - Math.pow(-2.0 * x + 2.0, 3.0) / 2.0;
   }),
   EASE_IN_OUT_QUART((x) -> {
      return x < 0.5 ? 8.0 * x * x * x * x : 1.0 - Math.pow(-2.0 * x + 2.0, 4.0) / 2.0;
   }),
   EASE_OUT_BACK((x) -> {
      return 1.0 + 2.70158 * Math.pow(x - 1.0, 3.0) + 1.70158 * Math.pow(x - 1.0, 2.0);
   }),
   TARGETESP_EASE_OUT_BACK((x) -> {
      return 1.0 + 3.70158 * Math.pow(x - 1.0, 3.0) + 2.70158 * Math.pow(x - 1.0, 2.0);
   }),
   EASE_OUT_CIRC((x) -> {
      return Math.sqrt(1.0 - Math.pow(x - 1.0, 2.0));
   }),
   SMOOTH_STEP((x) -> {
      return -2.0 * Math.pow(x, 3.0) + 3.0 * Math.pow(x, 2.0);
   });

   private final Function function;

   private Easing(Function function) {
      this.function = function;
   }

   public double apply(double arg) {
      return this.function.apply(arg);
   }

   // $FF: synthetic method
   private static Easing[] $values() {
      return new Easing[]{LINEAR, BOTH_SINE, SINE_IN_OUT, BOTH_CIRC, BOTH_CUBIC, EASE_IN_OUT_QUART, EASE_OUT_BACK, TARGETESP_EASE_OUT_BACK, EASE_OUT_CIRC, SMOOTH_STEP};
   }
}
