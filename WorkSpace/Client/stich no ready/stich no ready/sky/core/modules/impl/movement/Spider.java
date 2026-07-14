package sky.core.modules.impl.movement;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.TimerTask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import sky.core.events.EventMotion;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.player.InventoryUtil;

public class Spider extends Module {
   public ModeSetting mode = new ModeSetting("Режим", "Байпас", new String[]{"Байпас", "Шар", "Вода", "Пакетная Вода"});
   public SliderSetting speed = new SliderSetting("Скорость", 0.45F, 0.1F, 0.55F, 0.025F, () -> {
      return this.mode.is("Байпас");
   });
   public final BooleanSetting shift = new BooleanSetting("Эксплойт шифта", true, () -> {
      return this.mode.is("Байпас") || this.mode.is("Пакетная Вода");
   });
   public SliderSetting delay = new SliderSetting("Задержка", 0.31F, 0.1F, 1.0F, 0.001F, () -> {
      return this.mode.is("Пакетная Вода");
   });
   private boolean wasSneaking;
   private long lastPlacementTime;
   private java.util.Timer timer = new java.util.Timer();
   private boolean canUse = true;
   private long lastWallJumpMs = 0L;
   private static final long WALL_JUMP_COOLDOWN_MS = 250L;
   private int originalSlot = -1;

   public Spider() {
      super("Spider", "Лазит по стенам", Category.Movement);
      this.addSettings(new Setting[]{this.mode, this.speed, this.shift, this.delay});
   }

   public void onEnable() {
      if (this.mode.is("Пакетная Вода")) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            Minecraft var10001 = mc;
            this.originalSlot = Minecraft.player.inventory.currentItem;
         }
      }

      super.onEnable();
   }

   @EventTarget
   public void onMotion(EventMotion motion) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (this.mode.is("Байпас")) {
            this.handleBypassMode();
         } else if (this.mode.is("Шар")) {
            this.handleSphereMode(motion);
         } else if (this.mode.is("Пакетная Вода")) {
            this.handleWaterBucketMode(motion);
         } else if (this.mode.is("Вода")) {
            this.handleWaterMode();
         }

      }
   }

   private void handleBypassMode() {
      Minecraft var10000 = mc;
      if (!Minecraft.player.collidedHorizontally) {
         if ((Boolean)this.shift.get() && this.wasSneaking && mc.gameSettings.keyBindSneak.isKeyDown()) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKey(), false);
            this.wasSneaking = false;
         }

      } else {
         var10000 = mc;
         ClientPlayerEntity var1 = Minecraft.player;
         Minecraft var10001 = mc;
         double var2 = Minecraft.player.getMotion().x;
         double var10002 = ((Float)this.speed.get()).doubleValue();
         Minecraft var10003 = mc;
         var1.setMotion(var2, var10002, Minecraft.player.getMotion().z);
         if ((Boolean)this.shift.get() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKey(), true);
            this.wasSneaking = true;
         }

      }
   }

   private void handleSphereMode(EventMotion motion) {
      Minecraft var10000 = mc;
      if (Minecraft.player.collidedHorizontally) {
         var10000 = mc;
         if (Minecraft.player.isOnGround()) {
            var10000 = mc;
            Minecraft.player.jump();
         }

         var10000 = mc;
         if (!(Minecraft.player.fallDistance <= 0.0F)) {
            var10000 = mc;
            if (!(Minecraft.player.fallDistance >= 2.0F)) {
               long currentTime = System.currentTimeMillis();
               if (currentTime - this.lastPlacementTime < 100L) {
                  return;
               }

               var10000 = mc;
               ItemStack offHandItem = Minecraft.player.getHeldItemOffhand();
               if (offHandItem.getItem() != Items.PLAYER_HEAD) {
                  return;
               }

               var10000 = mc;
               RayTraceResult trace = Minecraft.player.pick(3.0, 1.0F, false);
               if (trace instanceof BlockRayTraceResult) {
                  BlockRayTraceResult blockTrace = (BlockRayTraceResult)trace;
                  BlockPos hitPos = blockTrace.getPos();
                  if (!mc.world.getBlockState(hitPos).isSolid()) {
                     return;
                  }

                  motion.setPitch(80.0F);
                  Minecraft var10001 = mc;
                  motion.setYaw(Minecraft.player.getHorizontalFacing().getHorizontalAngle());
                  var10000 = mc;
                  Minecraft.player.rotationPitchHead = 80.0F;
                  var10000 = mc;
                  Minecraft.player.swingArm(Hand.OFF_HAND);
                  var10001 = mc;
                  mc.playerController.rightClickBlock(Minecraft.player, mc.world, Hand.OFF_HAND, blockTrace);
                  if (mc.gameSettings.keyBindSneak.isKeyDown()) {
                     KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKey(), false);
                     this.wasSneaking = false;
                  }

                  var10000 = mc;
                  Minecraft.player.fallDistance = 0.0F;
                  this.lastPlacementTime = currentTime;
                  return;
               }

               return;
            }
         }

      }
   }

   private void handleWaterMode() {
      Minecraft var10000 = mc;
      if (Minecraft.player.collidedHorizontally) {
         int bucketSlot = this.findWaterBucketInHotbar();
         if (bucketSlot != -1) {
            var10000 = mc;
            if (Minecraft.player.inventory.currentItem != bucketSlot) {
               var10000 = mc;
               Minecraft.player.inventory.currentItem = bucketSlot;
            }

            Minecraft var10001 = mc;
            mc.playerController.processRightClick(Minecraft.player, mc.world, Hand.MAIN_HAND);
            var10000 = mc;
            var10001 = mc;
            Minecraft var10003 = mc;
            Minecraft.player.setMotion(Minecraft.player.getMotion().x, 0.36, Minecraft.player.getMotion().z);
         }
      }
   }

   private void handleWaterBucketMode(EventMotion motion) {
      Minecraft var10000 = mc;
      Minecraft var10001;
      Minecraft var10003;
      if (!Minecraft.player.isInWater()) {
         var10000 = mc;
         if (!Minecraft.player.isInLava()) {
            int waterSlot = this.findWaterBucketInHotbar();
            boolean hasWaterBucket = waterSlot != -1 || InventoryUtil.getSlot(Items.WATER_BUCKET) != -1;
            if (hasWaterBucket) {
               var10000 = mc;
               if (Minecraft.player.collidedHorizontally) {
                  if ((Boolean)this.shift.get() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
                     KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKey(), true);
                     this.wasSneaking = true;
                  }

                  var10000 = mc;
                  if (Minecraft.player.isOnGround()) {
                     long now = System.currentTimeMillis();
                     if (now - this.lastWallJumpMs > 250L) {
                        var10000 = mc;
                        Minecraft.player.jump();
                        this.lastWallJumpMs = now;
                     }

                     KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKey(), true);
                  }

                  if (!this.canUse) {
                     return;
                  }

                  motion.setPitch(70.0F);
                  var10000 = mc;
                  Minecraft.player.rotationPitchHead = 70.0F;
                  if (waterSlot == -1) {
                     InventoryUtil.inventorySwapClick(Items.WATER_BUCKET);
                  } else {
                     var10000 = mc;
                     int currentSlot = Minecraft.player.inventory.currentItem;
                     if (waterSlot != currentSlot) {
                        var10000 = mc;
                        Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(waterSlot));
                     }

                     var10000 = mc;
                     Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
                     if (waterSlot != currentSlot) {
                        var10000 = mc;
                        Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(currentSlot));
                     }
                  }

                  var10000 = mc;
                  var10001 = mc;
                  var10003 = mc;
                  Minecraft.player.setMotion(Minecraft.player.getMotion().x, 0.43, Minecraft.player.getMotion().z);
                  this.canUse = false;
                  this.timer.schedule(new TimerTask() {
                     public void run() {
                        Spider.this.canUse = true;
                     }
                  }, (long)this.getDelayMs());
                  return;
               }
            }

            if ((Boolean)this.shift.get() && this.wasSneaking && mc.gameSettings.keyBindSneak.isKeyDown()) {
               KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKey(), false);
               this.wasSneaking = false;
            }

            if (mc.gameSettings.keyBindJump.isKeyDown()) {
               KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKey(), false);
            }

            return;
         }
      }

      var10000 = mc;
      var10001 = mc;
      var10003 = mc;
      Minecraft.player.setMotion(Minecraft.player.getMotion().x, 0.30000001192092896, Minecraft.player.getMotion().z);
   }

   private int getDelayMs() {
      return (int)(((Float)this.delay.get()).doubleValue() * 1000.0);
   }

   private int findWaterBucketInHotbar() {
      for(int i = 0; i < 9; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (!stack.isEmpty() && stack.getItem() == Items.WATER_BUCKET) {
            return i;
         }
      }

      return -1;
   }

   @Native
   public void onDisable() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.gameSettings != null) {
         if ((Boolean)this.shift.get() && this.wasSneaking && mc.gameSettings.keyBindSneak.isKeyDown()) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKey(), false);
         }

         this.wasSneaking = false;
         this.lastPlacementTime = 0L;
         var10000 = mc;
         Minecraft.player.fallDistance = 0.0F;
         this.timer.cancel();
         this.timer = new java.util.Timer();
         this.canUse = true;
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKey(), false);
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKey(), false);
         if (this.mode.is("Пакетная Вода") && this.originalSlot != -1) {
            var10000 = mc;
            if (Minecraft.player.inventory.currentItem != this.originalSlot) {
               var10000 = mc;
               Minecraft.player.inventory.currentItem = this.originalSlot;
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(this.originalSlot));
            }
         }

         this.originalSlot = -1;
         super.onDisable();
      } else {
         super.onDisable();
      }
   }
}
