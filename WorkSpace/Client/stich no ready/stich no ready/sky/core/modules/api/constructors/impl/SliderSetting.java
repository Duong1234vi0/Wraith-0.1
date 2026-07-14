package sky.core.modules.api.constructors.impl;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.minecraft.util.math.MathHelper;
import sky.core.modules.api.constructors.Setting;
import sky.core.utils.math.MathUtil;

public class SliderSetting extends Setting {
   public float min;
   public float max;
   public float increment;
   public float defaultVal;
   private final Supplier maxSupplier;
   private Supplier minSupplier;

   private SliderSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible, Supplier minSupplier, Supplier maxSupplier) {
      super(name, defaultVal);
      this.min = min;
      this.max = max;
      this.defaultVal = defaultVal;
      this.increment = increment;
      if (visible != null) {
         this.visible(visible);
      }

      this.minSupplier = minSupplier;
      this.maxSupplier = maxSupplier;
   }

   public SliderSetting(String name, float defaultVal, float min, float max, float increment) {
      this(name, defaultVal, min, max, increment, (BooleanSupplier)null, (Supplier)null, (Supplier)null);
   }

   public SliderSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible) {
      this(name, defaultVal, min, max, increment, visible, (Supplier)null, (Supplier)null);
   }

   public SliderSetting(String name, float defaultVal, float min, float max, float increment, Supplier maxSupplier) {
      this(name, defaultVal, min, max, increment, (BooleanSupplier)null, (Supplier)null, maxSupplier);
   }

   public SliderSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible, Supplier maxSupplier) {
      this(name, defaultVal, min, max, increment, visible, (Supplier)null, maxSupplier);
   }

   public SliderSetting(String name, float defaultVal, float min, float max, float increment, SliderSetting maxValue) {
      Objects.requireNonNull(maxValue);
      this(name, defaultVal, min, max, increment, (BooleanSupplier)null, (Supplier)null, maxValue::get);
      maxValue.min(this);
   }

   public SliderSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible, SliderSetting maxValue) {
      Objects.requireNonNull(maxValue);
      this(name, defaultVal, min, max, increment, visible, (Supplier)null, maxValue::get);
      maxValue.min(this);
   }

   public void set(Float value) {
      float effMin = this.minSupplier != null ? (Float)this.minSupplier.get() : this.min;
      float effMax = this.maxSupplier != null ? (Float)this.maxSupplier.get() : this.max;
      float rounded = MathUtil.round(value, this.increment);
      float clamped = MathHelper.clamp(rounded, effMin, effMax);
      if (!(Math.abs((Float)this.get() - clamped) < 1.0E-6F)) {
         super.set(clamped);
      }
   }

   public SliderSetting min(SliderSetting minValue) {
      Objects.requireNonNull(minValue);
      this.minSupplier = minValue::get;
      return this;
   }
}
