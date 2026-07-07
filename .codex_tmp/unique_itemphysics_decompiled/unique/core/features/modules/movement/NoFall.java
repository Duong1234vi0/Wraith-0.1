package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IPlayerMoveC2SPacket;
import unique.core.setting.Setting;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.SearchInvResult;

public class NoFall extends Module {
   public final Setting<NoFall.Mode> mode = new Setting<>("Mode", NoFall.Mode.Items);
   public final Setting<NoFall.FallDistance> fallDistance = new Setting<>("FallDistance", NoFall.FallDistance.Custom);
   public final Setting<Integer> fallDistanceValue = new Setting<>("FallDistanceVal", 10, 2, 100);
   private final Setting<Boolean> powderSnowBucket = new Setting<>("PowderSnowBucket", true, v -> this.mode.getValue() == NoFall.Mode.Items);
   private final Setting<Boolean> waterBucket = new Setting<>("WaterBucket", true, v -> this.mode.getValue() == NoFall.Mode.Items);
   private final Setting<Boolean> retrieve = new Setting<>("Retrieve", true, v -> this.mode.getValue() == NoFall.Mode.Items && this.waterBucket.getValue());
   private final Setting<Boolean> enderPearl = new Setting<>("EnderPearl", true, v -> this.mode.getValue() == NoFall.Mode.Items);
   private final Setting<Boolean> cobweb = new Setting<>("Cobweb", true, v -> this.mode.getValue() == NoFall.Mode.Items);
   private final Setting<Boolean> twistingVines = new Setting<>("TwistingVines", true, v -> this.mode.getValue() == NoFall.Mode.Items);
   private unique.core.utility.Timer pearlCooldown = new unique.core.utility.Timer();
   private boolean retrieveFlag;
   private boolean cancelGround = false;

   public NoFall() {
      super("NoFall", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (!fullNullCheck()) {
         if (this.isFalling()) {
            switch ((NoFall.Mode)this.mode.getValue()) {
               case Items:
                  class_2338 playerPos = class_2338.method_49638(mc.field_1724.method_19538());
                  SearchInvResult snowResult = InventoryUtility.findItemInHotBar(class_1802.field_27876);
                  SearchInvResult pearlResult = InventoryUtility.findItemInHotBar(class_1802.field_8634);
                  SearchInvResult webResult = InventoryUtility.findItemInHotBar(class_1802.field_8786);
                  SearchInvResult vinesResult = InventoryUtility.findItemInHotBar(class_1802.field_23070);
                  SearchInvResult waterResult = InventoryUtility.findItemInHotBar(class_1802.field_8705);
                  if (waterResult.found() && this.waterBucket.getValue()) {
                     mc.field_1724.method_36457(90.0F);
                     this.doWaterCheck(waterResult, playerPos);
                  } else if (pearlResult.found() && this.enderPearl.getValue()) {
                     mc.field_1724.method_36457(90.0F);
                     this.doPearlDrop(pearlResult);
                  } else if (webResult.found() && this.cobweb.getValue()) {
                     mc.field_1724.method_36457(90.0F);
                     this.doWebDrop(webResult, playerPos);
                  } else if (vinesResult.found() && this.twistingVines.getValue()) {
                     mc.field_1724.method_36457(90.0F);
                     this.doVinesDrop(vinesResult, playerPos);
                  } else if (snowResult.found() && this.powderSnowBucket.getValue()) {
                     mc.field_1724.method_36457(90.0F);
                     this.doSnowDrop(snowResult, playerPos);
                  }
                  break;
               case MatrixOffGround:
               case Vanilla:
                  this.cancelGround = true;
                  break;
               case Grim:
                  if (mc.field_1724.method_24828()) {
                     if (mc.field_1724.method_18798().field_1352 == 0.0 && mc.field_1724.method_18798().field_1350 == 0.0) {
                        mc.field_1690.field_1903.method_23481(true);
                     } else {
                        mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12982));
                     }
                  }
            }
         } else if (this.retrieveFlag) {
            InventoryUtility.saveSlot();
            SearchInvResult waterResultx = InventoryUtility.findItemInHotBar(class_1802.field_8550);
            waterResultx.switchTo();
            mc.field_1724.method_36457(90.0F);
            mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
            mc.field_1724.method_6104(class_1268.field_5808);
            InventoryUtility.returnSlot();
            this.retrieveFlag = false;
            if (this.mode.getValue() == NoFall.Mode.Grim) {
               mc.field_1690.field_1903.method_23481(false);
            }
         }
      }
   }

   private void doWaterCheck(SearchInvResult waterResult, class_2338 playerPos) {
      if (mc.field_1687.method_8320(playerPos.method_10074()).method_51367()
         || mc.field_1687.method_8320(playerPos.method_10074().method_10074()).method_51367()) {
         InventoryUtility.saveSlot();
         waterResult.switchTo();
         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
         this.retrieveFlag = this.retrieve.getValue();
      }
   }

   private void doPearlDrop(SearchInvResult pearlResult) {
      if (this.pearlCooldown.passedMs(5000L)) {
         InventoryUtility.saveSlot();
         pearlResult.switchTo();
         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
         this.pearlCooldown.reset();
      }
   }

   private void doWebDrop(SearchInvResult webResult, class_2338 playerPos) {
      if (mc.field_1687.method_8320(playerPos.method_10074()).method_51367()
         || mc.field_1687.method_8320(playerPos.method_10074().method_10074()).method_51367()) {
         InventoryUtility.saveSlot();
         if (mc.field_1687.method_8320(playerPos.method_10074()).method_51367()) {
            InteractionUtility.placeBlock(
               playerPos,
               InteractionUtility.Rotate.None,
               InteractionUtility.Interact.Vanilla,
               InteractionUtility.PlaceMode.Normal,
               webResult.slot(),
               false,
               true
            );
         } else {
            InteractionUtility.placeBlock(
               playerPos.method_10074(),
               InteractionUtility.Rotate.None,
               InteractionUtility.Interact.Vanilla,
               InteractionUtility.PlaceMode.Normal,
               webResult.slot(),
               false,
               true
            );
         }

         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
      }
   }

   private void doVinesDrop(SearchInvResult vinesResult, class_2338 playerPos) {
      if (mc.field_1687.method_8320(playerPos.method_10074()).method_51367()
         || mc.field_1687.method_8320(playerPos.method_10074().method_10074()).method_51367()) {
         InventoryUtility.saveSlot();
         if (mc.field_1687.method_8320(playerPos.method_10074()).method_51367()) {
            InteractionUtility.placeBlock(
               playerPos,
               InteractionUtility.Rotate.None,
               InteractionUtility.Interact.Vanilla,
               InteractionUtility.PlaceMode.Normal,
               vinesResult.slot(),
               false,
               true
            );
         } else {
            InteractionUtility.placeBlock(
               playerPos.method_10074(),
               InteractionUtility.Rotate.None,
               InteractionUtility.Interact.Vanilla,
               InteractionUtility.PlaceMode.Normal,
               vinesResult.slot(),
               false,
               true
            );
         }

         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
      }
   }

   private void doSnowDrop(SearchInvResult snowResult, class_2338 playerPos) {
      if (mc.field_1687.method_8320(playerPos.method_10074()).method_51367()
         || mc.field_1687.method_8320(playerPos.method_10074().method_10074()).method_51367()) {
         InventoryUtility.saveSlot();
         if (mc.field_1687.method_8320(playerPos.method_10074()).method_51367()) {
            InteractionUtility.placeBlock(
               playerPos,
               InteractionUtility.Rotate.None,
               InteractionUtility.Interact.Vanilla,
               InteractionUtility.PlaceMode.Normal,
               snowResult.slot(),
               false,
               true
            );
         } else {
            InteractionUtility.placeBlock(
               playerPos.method_10074(),
               InteractionUtility.Rotate.None,
               InteractionUtility.Interact.Vanilla,
               InteractionUtility.PlaceMode.Normal,
               snowResult.slot(),
               false,
               true
            );
         }

         mc.field_1724.method_6104(class_1268.field_5808);
         InventoryUtility.returnSlot();
      }
   }

   public boolean isFalling() {
      if (mc == null || mc.field_1724 == null || mc.field_1687 == null) {
         return false;
      } else {
         return mc.field_1724.method_6128() ? false : mc.field_1724.field_6017 > this.fallDistanceValue.getValue().intValue();
      }
   }

   @Override
   public String getDisplayInfo() {
      return this.mode.getValue().toString() + " " + (this.isFalling() ? "Ready" : "");
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send e) {
      if (e.getPacket() instanceof class_2828 pac && this.cancelGround) {
         ((IPlayerMoveC2SPacket)pac).setOnGround(false);
      }
   }

   @Override
   public void onEnable() {
      this.cancelGround = false;
   }

   private static enum FallDistance {
      Custom;
   }

   private static enum Mode {
      Items,
      MatrixOffGround,
      Vanilla,
      Grim;
   }
}
