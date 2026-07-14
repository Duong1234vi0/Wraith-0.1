package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Comparator;
import java.util.stream.StreamSupport;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.math.AuraUtil;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.InventoryUtil;

public class AutoTrap extends Module {
   private final SliderSetting range = new SliderSetting("Range", 3.5F, 2.0F, 6.0F, 0.1F);
   private final SliderSetting tickDelay = new SliderSetting("Delay (ticks)", 4.0F, 0.0F, 20.0F, 1.0F);
   private final BooleanSetting ignoreOpenInventory = new BooleanSetting("Ignore open inventory", true);
   private final TimeUtil timer = new TimeUtil();
   private int lastSwitchedSlot = -1;

   public AutoTrap() {
      super("AutoTrap", Category.Combat);
      this.addSettings(new Setting[]{this.range, this.tickDelay, this.ignoreOpenInventory});
   }

   public void onDisable() {
      if (this.lastSwitchedSlot != -1) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            Minecraft.player.inventory.currentItem = this.lastSwitchedSlot;
            mc.playerController.syncCurrentPlayItem();
            this.lastSwitchedSlot = -1;
         }
      }

      super.onDisable();
   }

   @EventTarget
   private void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if ((Boolean)this.ignoreOpenInventory.get() || mc.currentScreen == null || mc.currentScreen == SkyCore.getInstance().getDropDown()) {
            if (this.timer.hasTimeElapsed((long)((Float)this.tickDelay.get() * 50.0F))) {
               LivingEntity target = this.findTarget();
               if (target != null) {
                  BlockPos placePos = this.simpleTrapPos(target);
                  if (placePos != null) {
                     if (mc.world.isBlockPresent(placePos) && mc.world.getBlockState(placePos).isAir()) {
                        int webSlot = InventoryUtil.getSlot(Items.COBWEB);
                        if (webSlot != -1) {
                           var10000 = mc;
                           int old = Minecraft.player.inventory.currentItem;
                           boolean needSyncBack = false;
                           if (webSlot < 9) {
                              if (old != webSlot) {
                                 var10000 = mc;
                                 Minecraft.player.inventory.currentItem = webSlot;
                                 mc.playerController.syncCurrentPlayItem();
                                 needSyncBack = true;
                              }

                              BlockPos support = this.findPlaceSupport(placePos);
                              if (support == null) {
                                 if (needSyncBack) {
                                    var10000 = mc;
                                    Minecraft.player.inventory.currentItem = old;
                                    mc.playerController.syncCurrentPlayItem();
                                 }

                              } else {
                                 BlockRayTraceResult hit = this.buildHitResultTowards(placePos, support);
                                 if (hit == null) {
                                    if (needSyncBack) {
                                       var10000 = mc;
                                       Minecraft.player.inventory.currentItem = old;
                                       mc.playerController.syncCurrentPlayItem();
                                    }

                                 } else {
                                    var10000 = mc;
                                    Vector3d eyeVec = Minecraft.player.getEyePosition(1.0F);
                                    Vector3d hitVec = hit.getHitVec();
                                    Vector3d offset = hitVec.subtract(eyeVec);
                                    float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(offset.z, offset.x)) - 90.0);
                                    float targetPitch = (float)(-Math.toDegrees(Math.atan2(offset.y, Math.hypot(offset.x, offset.z))));
                                    RotationComponent.update(new Rotation(targetYaw, targetPitch), 180.0F, 1, 6);
                                    Minecraft var10001 = mc;
                                    ActionResultType result = mc.playerController.func_217292_a(Minecraft.player, mc.world, Hand.MAIN_HAND, hit);
                                    var10000 = mc;
                                    Minecraft.player.swingArm(Hand.MAIN_HAND);
                                    if (needSyncBack) {
                                       var10000 = mc;
                                       Minecraft.player.inventory.currentItem = old;
                                       mc.playerController.syncCurrentPlayItem();
                                    }

                                    if (result != null && result.isSuccessOrConsume()) {
                                       this.timer.reset();
                                    }

                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private LivingEntity findTarget() {
      return (LivingEntity)StreamSupport.stream(mc.world.getAllEntities().spliterator(), false).filter((e) -> {
         return e instanceof LivingEntity;
      }).map((e) -> {
         return (LivingEntity)e;
      }).filter(this::isValidTarget).sorted(Comparator.comparingDouble((entity) -> {
         Minecraft var10001 = mc;
         return (double)entity.getDistance(Minecraft.player);
      })).findFirst().orElse((Object)null);
   }

   private boolean isValidTarget(LivingEntity entity) {
      if (entity != null && entity.isAlive()) {
         Minecraft var10001 = mc;
         if (entity != Minecraft.player) {
            if (AuraUtil.getVector(entity).length() > (double)(Float)this.range.get()) {
               return false;
            }

            if (entity instanceof PlayerEntity) {
               PlayerEntity player = (PlayerEntity)entity;
               if (SkyCore.getInstance().getFriendManager().isFriend(player.getGameProfile().getName())) {
                  return false;
               }
            }

            return true;
         }
      }

      return false;
   }

   private BlockPos simpleTrapPos(LivingEntity entity) {
      Vector3d pos = entity.getPositionVec();
      BlockPos feet = new BlockPos(pos.x, Math.floor(pos.y), pos.z);
      if (mc.world.getBlockState(feet).isAir()) {
         return feet;
      } else {
         BlockPos eye = new BlockPos(pos.x, Math.floor(pos.y + (double)entity.getEyeHeight()), pos.z);
         return mc.world.getBlockState(eye).isAir() ? eye : null;
      }
   }

   private BlockPos findPlaceSupport(BlockPos target) {
      if (!mc.world.getBlockState(target).isAir()) {
         return null;
      } else {
         Direction[] var2 = Direction.values();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Direction dir = var2[var4];
            if (dir != Direction.UP) {
               BlockPos against = target.offset(dir);
               if (!mc.world.getBlockState(against).isAir() && mc.world.getBlockState(against).getBlock() != Blocks.COBWEB) {
                  return against;
               }
            }
         }

         return null;
      }
   }

   private BlockRayTraceResult buildHitResultTowards(BlockPos target, BlockPos against) {
      Direction face = Direction.getFacingFromVector((float)(target.getX() - against.getX()), (float)(target.getY() - against.getY()), (float)(target.getZ() - against.getZ()));
      Vector3d faceCenter = new Vector3d((double)against.getX() + 0.5 + (double)face.getXOffset() * 0.5, (double)against.getY() + 0.5 + (double)face.getYOffset() * 0.5, (double)against.getZ() + 0.5 + (double)face.getZOffset() * 0.5);
      return new BlockRayTraceResult(faceCenter, face, against, false);
   }
}
