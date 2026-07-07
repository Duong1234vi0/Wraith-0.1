package unique.core.features.modules.render;

import unique.core.core.Managers;
import unique.core.features.hud.impl.ArmorHud;
import unique.core.features.hud.impl.Coords;
import unique.core.features.hud.impl.Hotbar;
import unique.core.features.hud.impl.InventoryHud;
import unique.core.features.hud.impl.KeyBinds;
import unique.core.features.hud.impl.PingHud;
import unique.core.features.hud.impl.PotionHud;
import unique.core.features.hud.impl.Speedometer;
import unique.core.features.hud.impl.StaffBoard;
import unique.core.features.hud.impl.TPSCounter;
import unique.core.features.hud.impl.TargetHud;
import unique.core.features.hud.impl.WaterMark;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class Interface extends Module {
   public Setting<Boolean> watermark = new Setting<>("Watermark", false);
   public Setting<Boolean> keybind = new Setting<>("Keybind", false);
   public Setting<Boolean> potionHud = new Setting<>("PotionHud", false);
   public Setting<Boolean> pingHud = new Setting<>("PingHud", false);
   public Setting<Boolean> armorHud = new Setting<>("ArmorHud", false);
   public Setting<Boolean> targetHud = new Setting<>("TargetHud", false);
   public Setting<Boolean> inventoryHud = new Setting<>("InventoryHud", false);
   public Setting<Boolean> tpsCounter = new Setting<>("TPSCounter", false);
   public Setting<Boolean> staffBoard = new Setting<>("StaffBoard", false);
   public Setting<Boolean> coords = new Setting<>("Coords", false);
   public Setting<Boolean> speedometer = new Setting<>("Speedometer", false);
   public Setting<Boolean> hotbar = new Setting<>("Hotbar", false);

   public Interface() {
      super("Interface", Module.Category.CLIENT);
   }

   @Override
   public void onUpdate() {
      this.sync(this.watermark.getValue(), WaterMark.class);
      this.sync(this.keybind.getValue(), KeyBinds.class);
      this.sync(this.potionHud.getValue(), PotionHud.class);
      this.sync(this.pingHud.getValue(), PingHud.class);
      this.sync(this.armorHud.getValue(), ArmorHud.class);
      this.sync(this.targetHud.getValue(), TargetHud.class);
      this.sync(this.inventoryHud.getValue(), InventoryHud.class);
      this.sync(this.tpsCounter.getValue(), TPSCounter.class);
      this.sync(this.staffBoard.getValue(), StaffBoard.class);
      this.sync(this.coords.getValue(), Coords.class);
      this.sync(this.speedometer.getValue(), Speedometer.class);
      this.sync(this.hotbar.getValue(), Hotbar.class);
   }

   private void sync(boolean shouldEnable, Class<? extends Module> clazz) {
      Module module = Managers.MODULE.getModuleByClass(clazz);
      if (module != null) {
         if (shouldEnable && !module.isEnabled()) {
            module.enable();
         }

         if (!shouldEnable && module.isEnabled()) {
            module.disable();
         }
      }
   }
}
