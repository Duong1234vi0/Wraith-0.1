package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.glfw.GLFW;
import sky.core.events.EventAttack;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;

public class CrystalOptimizer extends Module {
   public static boolean Rabotaet = false;
   private final BindSetting bind = new BindSetting("Bind");
   private final BooleanSetting test = new BooleanSetting("Убирать Задержку", false);
   private boolean wasPressed;
   private int previousSlot = -1;

   public CrystalOptimizer() {
      super("CrystalSpam", "Спамит Криссами", Category.Combat);
      this.addSettings(new Setting[]{this.bind, this.test});
   }

   public void onDisable() {
      super.onDisable();
      Rabotaet = false;
   }

   @EventTarget
   private void update(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         int key = (Integer)this.bind.get();
         if (key == -1) {
            Rabotaet = false;
         } else {
            boolean pressed;
            if (key < 8) {
               pressed = GLFW.glfwGetMouseButton(mc.getMainWindow().getHandle(), key) == 1;
            } else {
               pressed = InputMappings.isKeyDown(mc.getMainWindow().getHandle(), key);
            }

            Rabotaet = pressed;
            if (!pressed) {
               if (this.wasPressed) {
                  this.restoreSlot();
                  this.wasPressed = false;
               }

            } else {
               if (!this.wasPressed) {
                  Minecraft var10001 = mc;
                  this.previousSlot = Minecraft.player.inventory.currentItem;
                  this.wasPressed = true;
               }

               if (mc.currentScreen == null) {
                  this.handleCrystalLogic();
               }
            }
         }
      } else {
         Rabotaet = false;
      }
   }

   @EventTarget
   private void onAttack(EventAttack e) {
      if ((Boolean)this.test.get()) {
         Entity var3 = e.getTarget();
         if (var3 instanceof EnderCrystalEntity) {
            EnderCrystalEntity entity = (EnderCrystalEntity)var3;
            entity.remove();
         }
      }

   }

   private void handleCrystalLogic() {
      RayTraceResult var2 = mc.objectMouseOver;
      if (var2 instanceof EntityRayTraceResult ray) {
         if (ray.getEntity() instanceof EnderCrystalEntity) {
            this.attackCrystal(ray.getEntity());
            return;
         }
      }

      var2 = mc.objectMouseOver;
      if (var2 instanceof BlockRayTraceResult ray) {
         BlockPos pos = ray.getPos();
         if (mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN) {
            int crystalSlot = this.getHotbarSlotWithCrystal();
            if (crystalSlot != -1) {
               this.switchToSlot(crystalSlot);
               this.placeCrystalOn(this.obsidianTopPos(pos));
               this.explodeCrystalsNear(this.obsidianTopPos(pos));
            }
         }
      }

   }

   private void attackCrystal(Entity entity) {
      Minecraft var10000 = mc;
      if (Minecraft.player.connection != null) {
         var10000 = mc;
         Minecraft var10004 = mc;
         Minecraft.player.connection.sendPacket(new CUseEntityPacket(entity, Minecraft.player.isSneaking()));
      } else {
         Minecraft var10001 = mc;
         mc.playerController.attackEntity(Minecraft.player, entity);
      }

      var10000 = mc;
      Minecraft.player.swingArm(Hand.MAIN_HAND);
   }

   private BlockPos obsidianTopPos(BlockPos obsidianPos) {
      return obsidianPos.up();
   }

   private void placeCrystalOn(BlockPos placePos) {
      BlockPos basePos = placePos.down();
      if (mc.world.isAirBlock(placePos)) {
         AxisAlignedBB aabb = new AxisAlignedBB((double)placePos.getX(), (double)placePos.getY(), (double)placePos.getZ(), (double)placePos.getX() + 1.0, (double)placePos.getY() + 2.0, (double)placePos.getZ() + 1.0);
         if (mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, aabb).isEmpty()) {
            Vector3d hitVec = new Vector3d((double)basePos.getX() + 0.5, (double)basePos.getY() + 0.5, (double)basePos.getZ() + 0.5);
            BlockRayTraceResult rayTraceResult = new BlockRayTraceResult(hitVec, Direction.UP, basePos, false);
            Minecraft var10001 = mc;
            ActionResultType result = mc.playerController.func_217292_a(Minecraft.player, mc.world, Hand.MAIN_HAND, rayTraceResult);
            if (result == ActionResultType.SUCCESS || result == ActionResultType.CONSUME) {
               Minecraft var10000 = mc;
               Minecraft.player.swingArm(Hand.MAIN_HAND);
            }

         }
      }
   }

   private void explodeCrystalsNear(BlockPos placePos) {
      double range = 4.5;
      AxisAlignedBB aabb = new AxisAlignedBB((double)placePos.getX() - range, (double)placePos.getY() - 2.0, (double)placePos.getZ() - range, (double)placePos.getX() + 1.0 + range, (double)placePos.getY() + 2.0 + range, (double)placePos.getZ() + 1.0 + range);
      List crystals = mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, aabb).stream().filter((entityx) -> {
         return entityx instanceof EnderCrystalEntity;
      }).filter((entityx) -> {
         Minecraft var10000 = mc;
         return Minecraft.player.getDistance(entityx) <= (float)range;
      }).toList();
      Iterator var6 = crystals.iterator();

      while(var6.hasNext()) {
         Entity entity = (Entity)var6.next();
         Minecraft var10000 = mc;
         if (Minecraft.player.connection != null) {
            var10000 = mc;
            Minecraft var10004 = mc;
            Minecraft.player.connection.sendPacket(new CUseEntityPacket(entity, Minecraft.player.isSneaking()));
         } else {
            Minecraft var10001 = mc;
            mc.playerController.attackEntity(Minecraft.player, entity);
         }

         var10000 = mc;
         Minecraft.player.swingArm(Hand.MAIN_HAND);
      }

   }

   private int getHotbarSlotWithCrystal() {
      for(int i = 0; i < 9; ++i) {
         Minecraft var10000 = mc;
         if (Minecraft.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
            return i;
         }
      }

      return -1;
   }

   private void switchToSlot(int hotbarSlot) {
      if (hotbarSlot >= 0 && hotbarSlot <= 8) {
         Minecraft var10000 = mc;
         if (Minecraft.player.inventory.currentItem != hotbarSlot) {
            var10000 = mc;
            Minecraft.player.inventory.currentItem = hotbarSlot;
            var10000 = mc;
            if (Minecraft.player.connection != null) {
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(hotbarSlot));
            }

         }
      }
   }

   private void restoreSlot() {
      if (this.previousSlot >= 0 && this.previousSlot <= 8) {
         this.switchToSlot(this.previousSlot);
         this.previousSlot = -1;
      } else {
         this.previousSlot = -1;
      }
   }
}
