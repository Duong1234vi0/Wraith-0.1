package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.item.minecart.TNTMinecartEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.component.impl.MoveComponent;
import sky.core.utils.player.MoveUtil;

public class AutoTotem extends Module {
   private static final int SWAP_COOLDOWN_TICKS = 5;
   private final SliderSetting health = new SliderSetting("Уровень здоровья", 4.0F, 1.0F, 20.0F, 0.5F);
   private final SliderSetting elytraHealth = new SliderSetting("Здоровье на элитре", 8.0F, 1.0F, 20.0F, 0.5F);
   private final BooleanSetting swapBack = new BooleanSetting("Возвращать предмет", true);
   private final BooleanSetting noBallSwitch = new BooleanSetting("Игнорировать проверки, если в руке шар", false);
   private final BooleanSetting saveEnchanted = new BooleanSetting("Сохранять зачарованный", true);
   private final BooleanSetting ignoreDragonidTotem = new BooleanSetting("Не брать тотем Драконида", true);
   private final BooleanSetting stop = new BooleanSetting("Синхронизировать с GuiMove", false);
   private final MultiBooleanSetting mode = new MultiBooleanSetting("Проверки на", new BooleanSetting[]{new BooleanSetting("Золотые сердца", true), new BooleanSetting("Кристаллы", true), new BooleanSetting("Падение", true), new BooleanSetting("Динамит", true), new BooleanSetting("Трезубец", false)});
   private final SliderSetting distancecrystall = new SliderSetting("Дистанция проверки на кристалл", 6.0F, 1.0F, 6.0F, 1.0F, () -> {
      return this.mode.is("Кристаллы");
   });
   private int nonEnchantedTotems;
   private int oldItem = -1;
   private int ticks;
   private ItemStack oldOffhandStack;
   private boolean totemLocked;
   int slotToSwap;

   public AutoTotem() {
      super("Auto Totem", "У этого модуля нет описания!", Category.Combat);
      this.oldOffhandStack = ItemStack.EMPTY;
      this.slotToSwap = -1;
      this.addSettings(new Setting[]{this.health, this.elytraHealth, this.swapBack, this.noBallSwitch, this.saveEnchanted, this.ignoreDragonidTotem, this.stop, this.mode, this.distancecrystall});
   }

   @EventTarget
   private void totemSwap(EventUpdate e) {
      if (this.slotToSwap != -1) {
         this.doSwap(this.slotToSwap);
      }

   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      ++this.ticks;
      Slot slot = this.findTotemSlotAndUpdateCount();
      if (this.isTotemLockActive() && !this.hasTotemInHand() && slot != null && this.ticks >= 5 && !this.isSwapBlockedByBall()) {
         this.doSwap(slot.slotNumber);
      } else {
         int returnSlot;
         if (this.shouldReturnSavedItem(slot)) {
            returnSlot = this.findReturnSlot();
            if (returnSlot != -1) {
               this.doSwap(returnSlot);
               return;
            }
         }

         label92: {
            if (this.ticks >= 5) {
               Minecraft var10002;
               if (!this.isSwapBlockedByBall() && !this.hasTotemInHand()) {
                  if (this.mode.is("Кристаллы")) {
                     var10002 = mc;
                     if (!mc.world.getEntitiesWithinAABB(EnderCrystalEntity.class, Minecraft.player.getBoundingBox().grow((double)(Float)this.distancecrystall.get())).isEmpty() && slot != null) {
                        this.doSwap(slot.slotNumber);
                        return;
                     }
                  }

                  if (this.mode.is("Динамит")) {
                     var10002 = mc;
                     if (!mc.world.getEntitiesWithinAABB(TNTEntity.class, Minecraft.player.getBoundingBox().grow(8.0)).isEmpty() && slot != null) {
                        this.doSwap(slot.slotNumber);
                        return;
                     }

                     var10002 = mc;
                     if (!mc.world.getEntitiesWithinAABB(TNTMinecartEntity.class, Minecraft.player.getBoundingBox().grow(8.0)).isEmpty() && slot != null) {
                        this.doSwap(slot.slotNumber);
                        return;
                     }
                  }

                  if (this.mode.is("Трезубец")) {
                     var10002 = mc;
                     List nearbyTridents = mc.world.getEntitiesWithinAABB(TridentEntity.class, Minecraft.player.getBoundingBox().grow(10.0));
                     boolean isAnyTridentMoving = nearbyTridents.stream().anyMatch((trident) -> {
                        return trident.getPosX() != trident.prevPosX || trident.getPosY() != trident.prevPosY || trident.getPosZ() != trident.prevPosZ;
                     });
                     if (isAnyTridentMoving) {
                        this.doSwap(slot.slotNumber);
                        return;
                     }
                  }
               }

               if (this.needTotem()) {
                  if (slot != null && !this.hasTotemInHand()) {
                     this.doSwap(slot.slotNumber);
                  }
               } else if (this.canSwapBack()) {
                  returnSlot = this.findReturnSlot();
                  if (returnSlot == -1) {
                     this.oldItem = -1;
                     this.oldOffhandStack = ItemStack.EMPTY;
                     this.slotToSwap = -1;
                     return;
                  }

                  if (this.mode.is("Кристаллы")) {
                     var10002 = mc;
                     if (!mc.world.getEntitiesWithinAABB(EnderCrystalEntity.class, Minecraft.player.getBoundingBox().grow((double)(Float)this.distancecrystall.get())).isEmpty()) {
                        this.slotToSwap = -1;
                        return;
                     }
                  }

                  if (this.mode.is("Динамит")) {
                     var10002 = mc;
                     if (!mc.world.getEntitiesWithinAABB(TNTMinecartEntity.class, Minecraft.player.getBoundingBox().grow(8.0)).isEmpty()) {
                        break label92;
                     }

                     var10002 = mc;
                     if (!mc.world.getEntitiesWithinAABB(TNTEntity.class, Minecraft.player.getBoundingBox().grow(8.0)).isEmpty()) {
                        break label92;
                     }
                  }

                  if (this.mode.is("Трезубец")) {
                     var10002 = mc;
                     List nearbyTridents = mc.world.getEntitiesWithinAABB(TridentEntity.class, Minecraft.player.getBoundingBox().grow(10.0));
                     boolean isAnyTridentMoving = nearbyTridents.stream().anyMatch((trident) -> {
                        return trident.getPosX() != trident.prevPosX || trident.getPosY() != trident.prevPosY || trident.getPosZ() != trident.prevPosZ;
                     });
                     if (isAnyTridentMoving) {
                        this.slotToSwap = -1;
                        return;
                     }
                  }

                  this.doSwap(returnSlot);
               }
            }

            return;
         }

         this.slotToSwap = -1;
      }
   }

   @EventTarget
   private void onPacket(EventPacket event) {
      if (event.isReceive()) {
         if (event.getPacket() instanceof SSpawnObjectPacket) {
            SSpawnObjectPacket packet = (SSpawnObjectPacket)event.getPacket();
            if (packet.getType() != EntityType.END_CRYSTAL) {
               return;
            }

            if (!this.mode.is("Кристаллы")) {
               return;
            }

            if (this.isSwapBlockedByBall()) {
               return;
            }

            Minecraft var10000 = mc;
            if (Minecraft.player.getDistance(new EnderCrystalEntity(mc.world, packet.getX(), packet.getY(), packet.getZ())) > (Float)this.distancecrystall.get() || this.hasTotemInHand()) {
               return;
            }

            Slot crystalSlot = this.findTotemSlotAndUpdateCount();
            if (crystalSlot == null) {
               return;
            }

            this.doSwap(crystalSlot.slotNumber);
         }

      }
   }

   private boolean hasTotemInHand() {
      Hand[] var1 = Hand.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Hand hand = var1[var3];
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.getHeldItem(hand);
         if (this.isUsableTotem(stack)) {
            return true;
         }
      }

      return false;
   }

   private boolean isUsableTotem(ItemStack stack) {
      if (!stack.isEmpty() && stack.getItem() == Items.TOTEM_OF_UNDYING && !this.isDragonidTotem(stack)) {
         return !(Boolean)this.saveEnchanted.get() || !stack.isEnchanted() || this.nonEnchantedTotems <= 0;
      } else {
         return false;
      }
   }

   private boolean isDragonidTotem(ItemStack stack) {
      return (Boolean)this.ignoreDragonidTotem.get() && !stack.isEmpty() && stack.getItem() == Items.TOTEM_OF_UNDYING ? stack.getDisplayName().getString().contains("Тотем Драконида") : false;
   }

   private boolean needTotem() {
      boolean blockByBall = this.isSwapBlockedByBall();
      if (!blockByBall && this.mode.is("Падение")) {
         Minecraft var10000 = mc;
         if (!Minecraft.player.isInWater()) {
            var10000 = mc;
            if (!Minecraft.player.isElytraFlying()) {
               var10000 = mc;
               if (Minecraft.player.fallDistance > 10.0F) {
                  return true;
               }
            }
         }
      }

      return this.getEffectiveHealth() <= this.getTotemHealthThreshold();
   }

   private boolean shouldReturnSavedItem(Slot totemSlot) {
      if ((Boolean)this.swapBack.get() && this.oldItem != -1) {
         Minecraft var10001 = mc;
         if (this.isSameReturnItem(Minecraft.player.getHeldItemOffhand(), this.oldOffhandStack)) {
            this.oldItem = -1;
            this.oldOffhandStack = ItemStack.EMPTY;
            this.totemLocked = false;
            return false;
         } else if (this.isTotemLockActive()) {
            return false;
         } else {
            boolean var10000;
            if (totemSlot == null) {
               var10001 = mc;
               if (!this.isUsableTotem(Minecraft.player.getHeldItemOffhand())) {
                  var10000 = true;
                  return var10000;
               }
            }

            var10000 = false;
            return var10000;
         }
      } else {
         return false;
      }
   }

   private boolean canSwapBack() {
      if (this.totemLocked && this.oldItem != -1 && (Boolean)this.swapBack.get()) {
         if (this.isCriticalHalfHeart()) {
            return false;
         } else {
            return this.getEffectiveHealth() >= this.getSwapBackHealthThreshold();
         }
      } else {
         return false;
      }
   }

   private boolean isTotemLockActive() {
      return this.totemLocked && this.getEffectiveHealth() < this.getSwapBackHealthThreshold();
   }

   private float getEffectiveHealth() {
      Minecraft var10000 = mc;
      float currentHealth = Minecraft.player.getHealth();
      if (this.mode.is("Золотые сердца")) {
         var10000 = mc;
         if (!Minecraft.player.isElytraFlying()) {
            var10000 = mc;
            if (Minecraft.player.isPotionActive(Effects.ABSORPTION)) {
               Minecraft var10001 = mc;
               currentHealth += Minecraft.player.getAbsorptionAmount();
            }
         }
      }

      return currentHealth;
   }

   private float getTotemHealthThreshold() {
      Minecraft var10000 = mc;
      return Minecraft.player.isElytraFlying() ? (Float)this.elytraHealth.get() : (Float)this.health.get();
   }

   private float getSwapBackHealthThreshold() {
      return this.getTotemHealthThreshold() + 2.0F;
   }

   private boolean isCriticalHalfHeart() {
      Minecraft var10000 = mc;
      return Minecraft.player.getHealth() <= 1.0F;
   }

   private int findReturnSlot() {
      if (this.isValidSwapSlot(this.oldItem)) {
         Minecraft var10001 = mc;
         if (this.isSameReturnItem(Minecraft.player.openContainer.getSlot(this.oldItem).getStack(), this.oldOffhandStack)) {
            return this.oldItem;
         }
      }

      if (this.oldOffhandStack.isEmpty()) {
         return this.isValidSwapSlot(this.oldItem) ? this.oldItem : -1;
      } else {
         Minecraft var10000 = mc;
         Iterator var1 = Minecraft.player.openContainer.inventorySlots.iterator();

         Slot slot;
         do {
            if (!var1.hasNext()) {
               return -1;
            }

            slot = (Slot)var1.next();
         } while(slot.slotNumber == 45 || !this.isSameReturnItem(slot.getStack(), this.oldOffhandStack));

         this.oldItem = slot.slotNumber;
         return slot.slotNumber;
      }
   }

   private boolean isSameReturnItem(ItemStack current, ItemStack expected) {
      if (!current.isEmpty() && !expected.isEmpty()) {
         return current.getItem() == expected.getItem() && ItemStack.areItemStackTagsEqual(current, expected);
      } else {
         return false;
      }
   }

   private Slot findTotemSlotAndUpdateCount() {
      Minecraft var10001 = mc;
      this.nonEnchantedTotems = (int)Minecraft.player.openContainer.inventorySlots.stream().filter((s) -> {
         return s.getStack().getItem() == Items.TOTEM_OF_UNDYING && !this.isDragonidTotem(s.getStack()) && !s.getStack().isEnchanted();
      }).count();
      Minecraft var10000 = mc;
      return (Slot)Minecraft.player.openContainer.inventorySlots.stream().filter((s) -> {
         return s.getStack().getItem() == Items.TOTEM_OF_UNDYING && !this.isDragonidTotem(s.getStack()) && (!(Boolean)this.saveEnchanted.get() || !s.getStack().isEnchanted() || this.nonEnchantedTotems <= 0);
      }).findFirst().orElse((Object)null);
   }

   private void doSwap(int fromSlot) {
      if (!this.isValidSwapSlot(fromSlot)) {
         this.slotToSwap = -1;
      } else {
         Minecraft var10000 = mc;
         ItemStack fromStack = Minecraft.player.openContainer.getSlot(fromSlot).getStack();
         boolean swappingTotemToOffhand = fromStack.getItem() == Items.TOTEM_OF_UNDYING;
         boolean swappingSavedItemBack = this.oldItem != -1 && this.isSameReturnItem(fromStack, this.oldOffhandStack);
         if (MoveUtil.isMoving()) {
            MoveComponent.stopTicks = 3;
         }

         if ((Boolean)this.stop.get()) {
            MoveComponent.stop = true;
         }

         if (MoveUtil.isMoving()) {
            label81: {
               if (!(Boolean)this.stop.get()) {
                  var10000 = mc;
                  if (!Minecraft.player.isSprinting()) {
                     break label81;
                  }
               }

               this.slotToSwap = fromSlot;
               return;
            }
         }

         Minecraft var10001;
         if (this.oldItem != -1 && this.oldItem == fromSlot) {
            this.oldItem = -1;
            this.oldOffhandStack = ItemStack.EMPTY;
            this.totemLocked = false;
         } else {
            var10000 = mc;
            if (!Minecraft.player.getHeldItemOffhand().isEmpty() && this.oldItem == -1) {
               this.oldItem = fromSlot;
               var10001 = mc;
               this.oldOffhandStack = Minecraft.player.getHeldItemOffhand().copy();
            }
         }

         var10001 = mc;
         Minecraft var10005 = mc;
         mc.playerController.windowClick(Minecraft.player.openContainer.windowId, fromSlot, 40, ClickType.SWAP, Minecraft.player);
         if (swappingTotemToOffhand) {
            this.totemLocked = true;
         } else if (swappingSavedItemBack) {
            this.totemLocked = false;
         }

         this.slotToSwap = -1;
         this.ticks = 0;
      }
   }

   private boolean isValidSwapSlot(int slot) {
      boolean var10000;
      if (slot >= 0) {
         Minecraft var10001 = mc;
         if (slot < Minecraft.player.openContainer.inventorySlots.size()) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private boolean isSwapBlockedByBall() {
      boolean var1;
      label25: {
         if ((Boolean)this.noBallSwitch.get()) {
            Minecraft var10000 = mc;
            if (Minecraft.player.getHeldItemOffhand().getItem() == Items.PLAYER_HEAD) {
               if (!this.mode.is("Падение")) {
                  break label25;
               }

               var10000 = mc;
               if (!(Minecraft.player.fallDistance > 5.0F)) {
                  break label25;
               }
            }
         }

         var1 = false;
         return var1;
      }

      var1 = true;
      return var1;
   }

   public void onDisable() {
      this.oldItem = -1;
      this.oldOffhandStack = ItemStack.EMPTY;
      this.totemLocked = false;
      this.ticks = 0;
      super.onDisable();
   }
}
