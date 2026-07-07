package unique.core.features.modules.misc;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2828.class_5911;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.EventTick;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.SearchInvResult;

public class AutoMLG extends Module {
   private final Setting<AutoMLG.MLGMode> mode = new Setting<>("Mode", AutoMLG.MLGMode.Water);
   private final Setting<Boolean> retrieve = new Setting<>("Retrieve", true, v -> this.mode.is(AutoMLG.MLGMode.Water));
   private boolean retrieveFlag;

   public AutoMLG() {
      super("AutoMLG", Module.Category.MISC);
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (!fullNullCheck()) {
         if (mc.field_1724.field_6017 > 3.0F && !mc.field_1724.method_6128() && !mc.field_1724.method_5799()) {
            class_2338 playerPos = class_2338.method_49638(mc.field_1724.method_19538());
            switch ((AutoMLG.MLGMode)this.mode.getValue()) {
               case Water:
                  this.doWaterMLG(playerPos);
                  break;
               case Cobweb:
                  this.doCobwebMLG(playerPos);
                  break;
               case PowderSnow:
                  this.doSnowMLG(playerPos);
                  break;
               case Vines:
                  this.doVinesMLG(playerPos);
                  break;
               case Fake:
                  this.sendPacket(new class_5911(true));
            }
         } else if (this.retrieveFlag && this.mode.is(AutoMLG.MLGMode.Water)) {
            SearchInvResult bucketResult = InventoryUtility.findItemInHotBar(class_1802.field_8550);
            if (bucketResult.found()) {
               InventoryUtility.saveSlot();
               bucketResult.switchTo();
               mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
               mc.field_1724.method_6104(class_1268.field_5808);
               InventoryUtility.returnSlot();
               this.retrieveFlag = false;
            }
         }
      }
   }

   @EventHandler
   public void onTick(EventTick e) {
      if (mc.field_1724.method_24828()) {
         this.retrieveFlag = false;
      }
   }

   private void doWaterMLG(class_2338 pos) {
      SearchInvResult water = InventoryUtility.findItemInHotBar(class_1802.field_8705);
      if (water.found()) {
         mc.field_1724.method_36457(90.0F);
         InventoryUtility.saveSlot();
         water.switchTo();
         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
         this.retrieveFlag = this.retrieve.getValue();
      }
   }

   private void doCobwebMLG(class_2338 pos) {
      SearchInvResult web = InventoryUtility.findItemInHotBar(class_1802.field_8786);
      if (web.found()) {
         mc.field_1724.method_36457(90.0F);
         InventoryUtility.saveSlot();
         InteractionUtility.placeBlock(
            pos.method_10074(),
            InteractionUtility.Rotate.None,
            InteractionUtility.Interact.Vanilla,
            InteractionUtility.PlaceMode.Normal,
            web.slot(),
            false,
            true
         );
         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
      }
   }

   private void doSnowMLG(class_2338 pos) {
      SearchInvResult snow = InventoryUtility.findItemInHotBar(class_1802.field_27876);
      if (snow.found()) {
         mc.field_1724.method_36457(90.0F);
         InventoryUtility.saveSlot();
         InteractionUtility.placeBlock(
            pos.method_10074(),
            InteractionUtility.Rotate.None,
            InteractionUtility.Interact.Vanilla,
            InteractionUtility.PlaceMode.Normal,
            snow.slot(),
            false,
            true
         );
         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
      }
   }

   private void doVinesMLG(class_2338 pos) {
      SearchInvResult vines = InventoryUtility.findItemInHotBar(class_1802.field_23070);
      if (vines.found()) {
         mc.field_1724.method_36457(90.0F);
         InventoryUtility.saveSlot();
         InteractionUtility.placeBlock(
            pos.method_10074(),
            InteractionUtility.Rotate.None,
            InteractionUtility.Interact.Vanilla,
            InteractionUtility.PlaceMode.Normal,
            vines.slot(),
            false,
            true
         );
         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
      }
   }

   public static enum MLGMode {
      Water,
      Cobweb,
      PowderSnow,
      Vines,
      Fake;
   }
}
