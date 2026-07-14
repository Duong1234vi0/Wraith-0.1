package sky.core.modules.api.constructors;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class Setting {
   Object defaultVal;
   String settingName;
   private Runnable onChange;
   public boolean visible = true;
   private Supplier visibleSupplier = null;

   public Setting(String name, Object defaultVal) {
      this.settingName = name;
      this.defaultVal = defaultVal;
   }

   public String getName() {
      return this.settingName;
   }

   public void set(Object value) {
      this.defaultVal = value;
      if (this.onChange != null) {
         this.onChange.run();
      }

   }

   public Object get() {
      return this.defaultVal;
   }

   public Setting visible(Supplier bool) {
      this.visibleSupplier = bool;
      return this;
   }

   public Setting visible(BooleanSupplier bool) {
      Objects.requireNonNull(bool);
      this.visibleSupplier = bool::getAsBoolean;
      return this;
   }

   public boolean isVisible() {
      return this.visibleSupplier != null ? Boolean.TRUE.equals(this.visibleSupplier.get()) : this.visible;
   }

   public void setDefaultVal(Object defaultVal) {
      this.defaultVal = defaultVal;
   }

   public void setSettingName(String settingName) {
      this.settingName = settingName;
   }

   public void setOnChange(Runnable onChange) {
      this.onChange = onChange;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }

   public void setVisibleSupplier(Supplier visibleSupplier) {
      this.visibleSupplier = visibleSupplier;
   }

   public Object getDefaultVal() {
      return this.defaultVal;
   }

   public String getSettingName() {
      return this.settingName;
   }

   public Runnable getOnChange() {
      return this.onChange;
   }

   public Supplier getVisibleSupplier() {
      return this.visibleSupplier;
   }
}
