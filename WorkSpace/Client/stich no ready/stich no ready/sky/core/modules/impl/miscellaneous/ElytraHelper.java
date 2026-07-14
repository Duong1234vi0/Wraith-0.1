package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraft.network.play.client.CEntityActionPacket.Action;
import sky.core.events.EventInput;
import sky.core.events.EventKey;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.player.MoveUtil;

public class ElytraHelper extends Module {
   private final BindSetting keySwap = new BindSetting("Кнопка свапа");
   private final BooleanSetting equipchestplate = new BooleanSetting("Снимать элитры при приземлении", false, () -> {
      return (Integer)this.keySwap.get() != -1;
   });
   private final BindSetting fireworkKey = new BindSetting("Кнопка фейера");
   private final BooleanSetting autoFly = new BooleanSetting("Автоматически взлетать", false);
   private final BooleanSetting autoJump = new BooleanSetting("Авто прыжок", false);
   private final BooleanSetting usefirework;
   private final BooleanSetting fireworkOffhand;
   private boolean swapRequested;
   private boolean fireworkUsed;
   private boolean wasOnGround;
   private boolean wasInElytraFlight;
   private int tickswap;
   private boolean hasFiredOnStart;

   public ElytraHelper() {
      super("Elytra Helper", "Позволяет быстро свапать элитру или нагрудник", Category.Player);
      Boolean var10004 = false;
      BooleanSetting var10005 = this.autoFly;
      Objects.requireNonNull(var10005);
      this.usefirework = new BooleanSetting("Автоматически использовать феерверк", var10004, var10005::get);
      this.fireworkOffhand = new BooleanSetting("Фейер в левую руку", false);
      this.swapRequested = false;
      this.fireworkUsed = false;
      this.wasOnGround = false;
      this.wasInElytraFlight = false;
      this.hasFiredOnStart = false;
      this.addSettings(new Setting[]{this.keySwap, this.equipchestplate, this.fireworkKey, this.fireworkOffhand, this.autoFly, this.autoJump, this.usefirework});
   }

   @EventTarget
   public void onTick(EventUpdate event) {
      if (this.tickswap > 0) {
         --this.tickswap;
      }

      Minecraft var10000;
      Minecraft var10003;
      if (this.swapRequested && !MoveUtil.isMoving()) {
         this.executeSwap();
         this.swapRequested = false;
         var10000 = mc;
         var10003 = mc;
         Minecraft.player.connection.sendPacket(new CCloseWindowPacket(Minecraft.player.openContainer.windowId));
      }

      var10000 = mc;
      if (Minecraft.player.isElytraFlying()) {
         this.wasInElytraFlight = true;
      }

      if ((Boolean)this.equipchestplate.get()) {
         var10000 = mc;
         if (Minecraft.player.isOnGround() && !this.wasOnGround && this.wasInElytraFlight) {
            var10000 = mc;
            if (Minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.ELYTRA) {
               if (InventoryUtil.getChestPlateSlot() != -1) {
                  InventoryUtil.moveToArmor(InventoryUtil.getChestPlateSlot(), 6);
               } else {
                  InventoryUtil.pickupItem(6, 0);
                  if (InventoryUtil.findEmptyInventorySlot() != -1) {
                     InventoryUtil.pickupItem(InventoryUtil.findEmptyInventorySlot(), 0);
                  }
               }

               this.wasInElytraFlight = false;
            }
         }

         Minecraft var10001 = mc;
         this.wasOnGround = Minecraft.player.isOnGround();
      }

      if ((Boolean)this.autoJump.get()) {
         var10000 = mc;
         if (!Minecraft.player.abilities.isFlying) {
            var10000 = mc;
            if (Minecraft.player.isOnGround()) {
               var10000 = mc;
               if (Minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.ELYTRA && !mc.gameSettings.keyBindJump.isKeyDown()) {
                  var10000 = mc;
                  if (!Minecraft.player.isInWater()) {
                     var10000 = mc;
                     if (!Minecraft.player.isInLava()) {
                        var10000 = mc;
                        Minecraft.player.jump();
                     }
                  }
               }
            }
         }
      }

      if ((Boolean)this.autoFly.get()) {
         var10000 = mc;
         if (!Minecraft.player.abilities.isFlying) {
            var10000 = mc;
            if (!Minecraft.player.isInWater()) {
               var10000 = mc;
               if (!Minecraft.player.isInLava()) {
                  var10000 = mc;
                  if (!Minecraft.player.isOnGround()) {
                     var10000 = mc;
                     if (!Minecraft.player.isElytraFlying()) {
                        var10000 = mc;
                        if (Minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.ELYTRA) {
                           var10000 = mc;
                           if (ElytraItem.isUsable(Minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST))) {
                              var10000 = mc;
                              Minecraft.player.startFallFlying();
                              var10000 = mc;
                              var10003 = mc;
                              Minecraft.player.connection.sendPacket(new CEntityActionPacket(Minecraft.player, Action.START_FALL_FLYING));
                              this.wasInElytraFlight = true;
                              if ((Boolean)this.usefirework.get() && !this.hasFiredOnStart && InventoryUtil.getSlot(Items.FIREWORK_ROCKET) != -1) {
                                 InventoryUtil.inventorySwapClick(Items.FIREWORK_ROCKET, (Boolean)this.fireworkOffhand.get());
                                 this.hasFiredOnStart = true;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      var10000 = mc;
      if (!Minecraft.player.isOnGround()) {
         var10000 = mc;
         if (!Minecraft.player.isInWater()) {
            var10000 = mc;
            if (!Minecraft.player.isInLava()) {
               return;
            }
         }
      }

      this.hasFiredOnStart = false;
      this.fireworkUsed = false;
   }

   @EventTarget
   public void onEvent(EventKey event) {
      if (event.isHold()) {
         if (event.getKey() == (Integer)this.keySwap.get()) {
            boolean hasElytra = InventoryUtil.getSlot(Items.ELYTRA) != -1;
            boolean hasChestplate = InventoryUtil.getChestPlateSlot() != -1;
            if (!hasElytra && !hasChestplate) {
               return;
            }

            this.tickswap = 2;
            this.swapRequested = true;
         }

         if (event.getKey() == (Integer)this.fireworkKey.get()) {
            InventoryUtil.inventorySwapClick(Items.FIREWORK_ROCKET, (Boolean)this.fireworkOffhand.get());
         }

      }
   }

   @EventTarget
   private void onEvent(EventInput eventInput) {
      if (this.tickswap > 0) {
         eventInput.setForward(0.0F);
         eventInput.setStrafe(0.0F);
         eventInput.setSneak(false);
         eventInput.setJump(false);
      }

   }

   private void executeSwap() {
      Minecraft var10000 = mc;
      int targetSlot;
      if (Minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.ELYTRA) {
         targetSlot = InventoryUtil.getChestPlateSlot();
      } else {
         targetSlot = InventoryUtil.getSlot(Items.ELYTRA);
      }

      if (targetSlot != -1) {
         InventoryUtil.moveItemtest(targetSlot, 6);
      }
   }
}
