package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class TPLoot extends Module {
   private final ModeSetting returnMode = new ModeSetting("Возврат", "Назад", new String[]{"Назад", "/home", "Никуда"});
   private final SliderSetting commandDelay = new SliderSetting("Задержка /home", 100.0F, 50.0F, 200.0F, 10.0F, () -> {
      return this.returnMode.is("/home");
   });
   private final SliderSetting radius = new SliderSetting("Радиус", 100.0F, 10.0F, 150.0F, 5.0F);
   private final Set valuable;
   private Vector3d deathPos;
   private Vector3d startPos;
   private boolean pickedLoot;
   private int cooldown;

   public TPLoot() {
      super("TP Loot", "Телепортирует к вещам", Category.Movement);
      this.valuable = new HashSet(Arrays.asList(Items.TOTEM_OF_UNDYING, Items.CRYING_OBSIDIAN, Items.ENCHANTED_GOLDEN_APPLE, Items.ELYTRA, Items.NETHERITE_INGOT, Items.PLAYER_HEAD));
      this.addSettings(new Setting[]{this.returnMode, this.commandDelay, this.radius});
   }

   public void onDisable() {
      this.reset();
      super.onDisable();
   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (this.cooldown > 0) {
            --this.cooldown;
         }

         var10000 = mc;
         if (!Minecraft.player.abilities.allowFlying) {
            var10000 = mc;
            if (!Minecraft.player.abilities.isFlying) {
               this.reset();
               return;
            }
         }

         var10000 = mc;
         Minecraft var10001;
         if (Minecraft.player.getHealth() <= 0.0F) {
            var10001 = mc;
            this.deathPos = Minecraft.player.getPositionVec();
            this.pickedLoot = false;
            this.startPos = null;
         } else if (this.deathPos != null) {
            var10001 = mc;
            this.tp(Minecraft.player, this.deathPos);
            this.deathPos = null;
         } else {
            var10001 = mc;
            ItemEntity target = this.getTarget(Minecraft.player);
            if (target != null) {
               if (this.startPos == null) {
                  var10001 = mc;
                  this.startPos = Minecraft.player.getPositionVec();
               }

               this.pickedLoot = true;
               var10001 = mc;
               Minecraft var10003 = mc;
               this.tp(Minecraft.player, this.findSafePos(Minecraft.player, target.getPositionVec()));
            } else if (this.pickedLoot) {
               if (this.returnMode.is("Назад") && this.startPos != null) {
                  var10001 = mc;
                  this.tp(Minecraft.player, this.startPos);
                  this.reset();
               } else if (this.returnMode.is("/home")) {
                  var10001 = mc;
                  this.sendHome(Minecraft.player);
               } else {
                  this.reset();
               }
            } else {
               this.startPos = null;
            }

         }
      }
   }

   private ItemEntity getTarget(ClientPlayerEntity p) {
      Vector3d pos = p.getPositionVec();
      double r = (double)(Float)this.radius.get();
      AxisAlignedBB box = new AxisAlignedBB(pos.x - r, pos.y - r, pos.z - r, pos.x + r, pos.y + r, pos.z + r);
      return (ItemEntity)mc.world.getEntitiesWithinAABB(ItemEntity.class, box, this::isValid).stream().max(Comparator.comparingDouble((i) -> {
         return (double)(this.getPriority(i.getItem().getItem()) * 10000) - i.getDistanceSq(pos);
      })).orElse((Object)null);
   }

   private void tp(ClientPlayerEntity p, Vector3d pos) {
      if (!(p.getDistanceSq(pos) < 0.01)) {
         p.setMotion(0.0, 0.0, 0.0);
         p.setPosition(pos.x, pos.y, pos.z);
         if (mc.getConnection() != null) {
            mc.getConnection().sendPacket(new CPlayerPacket.PositionPacket(pos.x, pos.y, pos.z, true));
            mc.getConnection().sendPacket(new CPlayerPacket.PositionRotationPacket(pos.x, pos.y, pos.z, p.rotationYaw, p.rotationPitch, true));
         }

      }
   }

   private void sendHome(ClientPlayerEntity p) {
      if (this.pickedLoot && this.cooldown <= 0) {
         p.sendChatMessage("/home home");
         this.reset();
         this.cooldown = (int)((Float)this.commandDelay.get() / 50.0F);
      }
   }

   private boolean isValid(ItemEntity e) {
      return e.isAlive() && this.valuable.contains(e.getItem().getItem());
   }

   private int getPriority(Item i) {
      if (i == Items.PLAYER_HEAD) {
         return 10;
      } else if (i == Items.ENCHANTED_GOLDEN_APPLE) {
         return 8;
      } else if (i != Items.TOTEM_OF_UNDYING && i != Items.ELYTRA) {
         return i == Items.NETHERITE_INGOT ? 4 : 1;
      } else {
         return 5;
      }
   }

   private Vector3d findSafePos(ClientPlayerEntity p, Vector3d pos) {
      int x = (int)Math.floor(pos.x);
      int y = (int)Math.floor(pos.y);
      int z = (int)Math.floor(pos.z);
      double w = (double)p.getWidth();
      double h = (double)p.getHeight();
      int[][] offs = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
      int[][] var11 = offs;
      int var12 = offs.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         int[] o = var11[var13];
         double nx = (double)(x + o[0]) + 0.5;
         double ny = (double)y;
         double nz = (double)(z + o[1]) + 0.5;
         AxisAlignedBB bb = new AxisAlignedBB(nx - w / 2.0, ny, nz - w / 2.0, nx + w / 2.0, ny + h, nz + w / 2.0);
         if (mc.world.getCollisionShapes(p, bb).count() == 0L) {
            return new Vector3d(nx, ny, nz);
         }
      }

      return new Vector3d((double)x + 0.5, (double)(y + 1), (double)z + 0.5);
   }

   private void reset() {
      this.deathPos = null;
      this.startPos = null;
      this.pickedLoot = false;
      this.cooldown = 0;
   }
}
