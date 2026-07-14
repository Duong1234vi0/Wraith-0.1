package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import sky.core.events.EventSwingAnimation;
import sky.core.events.EventSwingSpeed;
import sky.core.events.EventTransformSideFirstPerson;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class HoldMyItems extends Module {
   public final SliderSetting swingSpeed = new SliderSetting("Скорость", 12.0F, 1.0F, 20.0F, 1.0F);
   public final SliderSetting power = new SliderSetting("Сила", 1.0F, 0.0F, 2.0F, 0.05F);
   public final SliderSetting xOffset = new SliderSetting("X", 0.0F, -1.5F, 1.5F, 0.01F);
   public final SliderSetting yOffset = new SliderSetting("Y", 0.0F, -1.5F, 1.5F, 0.01F);
   public final SliderSetting zOffset = new SliderSetting("Z", 0.0F, -1.5F, 1.5F, 0.01F);
   public final SliderSetting rotX = new SliderSetting("Поворот X", 0.0F, -180.0F, 180.0F, 1.0F);
   public final SliderSetting rotY = new SliderSetting("Поворот Y", 0.0F, -180.0F, 180.0F, 1.0F);
   public final SliderSetting rotZ = new SliderSetting("Поворот Z", 0.0F, -180.0F, 180.0F, 1.0F);
   public final SliderSetting scale = new SliderSetting("Размер", 1.0F, 0.2F, 2.5F, 0.01F);
   public final SliderSetting swingX = new SliderSetting("Анимация X", -80.0F, -180.0F, 180.0F, 1.0F);
   public final SliderSetting swingY = new SliderSetting("Анимация Y", -20.0F, -180.0F, 180.0F, 1.0F);
   public final SliderSetting swingZ = new SliderSetting("Анимация Z", -20.0F, -180.0F, 180.0F, 1.0F);

   public HoldMyItems() {
      super("HoldMyItems", "First-person animations", Category.Visuals);
      this.addSettings(new Setting[]{this.swingSpeed, this.power, this.xOffset, this.yOffset, this.zOffset, this.rotX, this.rotY, this.rotZ, this.scale, this.swingX, this.swingY, this.swingZ});
   }

   public void onEnable() {
      super.onEnable();
      this.syncToMod();
      mods.holdmyitems.HoldMyItems.enabled = true;
   }

   public void onDisable() {
      mods.holdmyitems.HoldMyItems.enabled = false;
      super.onDisable();
   }

   private void syncToMod() {
      mods.holdmyitems.HoldMyItems.swingSpeed = ((Float)this.swingSpeed.get()).intValue();
      mods.holdmyitems.HoldMyItems.power = (Float)this.power.get();
      mods.holdmyitems.HoldMyItems.xOffset = (Float)this.xOffset.get();
      mods.holdmyitems.HoldMyItems.yOffset = (Float)this.yOffset.get();
      mods.holdmyitems.HoldMyItems.zOffset = (Float)this.zOffset.get();
      mods.holdmyitems.HoldMyItems.rotX = (Float)this.rotX.get();
      mods.holdmyitems.HoldMyItems.rotY = (Float)this.rotY.get();
      mods.holdmyitems.HoldMyItems.rotZ = (Float)this.rotZ.get();
      mods.holdmyitems.HoldMyItems.itemScale = (Float)this.scale.get();
      mods.holdmyitems.HoldMyItems.swingX = (Float)this.swingX.get();
      mods.holdmyitems.HoldMyItems.swingY = (Float)this.swingY.get();
      mods.holdmyitems.HoldMyItems.swingZ = (Float)this.swingZ.get();
   }

   @EventTarget
   public void onEvent(EventSwingSpeed event) {
      this.syncToMod();
      mods.holdmyitems.HoldMyItems.onSwingSpeed(event);
   }

   @EventTarget
   public void onEvent(EventTransformSideFirstPerson event) {
      this.syncToMod();
      mods.holdmyitems.HoldMyItems.onTransform(event);
   }

   @EventTarget
   public void onEvent(EventSwingAnimation event) {
      this.syncToMod();
      mods.holdmyitems.HoldMyItems.onSwingAnimation(event);
   }
}
