package sky.core.modules.impl.combat;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import mods.viaversion.viamcp.fixes.AttackOrder;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventClick;
import sky.core.events.EventPacket;
import sky.core.events.EventPlaceBlock;
import sky.core.events.EventPostUpdate;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.math.AuraUtil;
import sky.core.utils.player.InventoryUtil;

public class AutoExplosion extends Module {
   private BlockPos targetPos;
   private int targetSlot;
   private int oldSlot;
   private boolean needSync;
   private AxisAlignedBB crystalArea;
   private boolean blocked;
   private int explodeTicks;
   private int pendingCrystalId = -1;
   private Vector3d pendingCrystalPos;
   private int pendingCrystalTicks;
   private BlockPos pkmPlacePos;
   private int pkmCrystalSlot = -1;
   private boolean pkmBlocked;
   private final BooleanSetting test = new BooleanSetting("Взрыв по пкм", true);
   private final BooleanSetting fast = new BooleanSetting("Быстрый", true);
   private final BooleanSetting noExplodeResources = new BooleanSetting("Не взрывать ресурсы", false);

   public AutoExplosion() {
      super("Auto Explosion", "Автоматически взрывает кристаллы в радиусе 3 блоков", Category.Combat);
      this.addSettings(new Setting[]{this.test, this.fast, this.noExplodeResources});
   }

   public void onDisable() {
      this.clearExplosionState();
      this.targetPos = null;
      this.pkmPlacePos = null;
      this.pkmCrystalSlot = -1;
      this.pkmBlocked = false;
      super.onDisable();
   }

   private boolean proverkacrystala() {
      return CrystalOptimizer.Rabotaet;
   }

   @EventTarget
   public void onEvent(EventPlaceBlock e) {
      if (!this.proverkacrystala()) {
         if (e.getBlock() == Blocks.OBSIDIAN) {
            Minecraft var10000 = mc;
            if (Minecraft.player.getCooldownTracker().hasCooldown(Items.END_CRYSTAL)) {
               return;
            }

            int slotInHotBar = InventoryUtil.getSlot(Items.END_CRYSTAL);
            if (slotInHotBar == -1) {
               return;
            }

            if (slotInHotBar < 9) {
               this.targetPos = e.getPos();
               this.targetSlot = slotInHotBar;
               this.scheduleCrystalExplosion(e.getPos());
               this.blocked = true;
            }
         }

      }
   }

   @EventTarget
   @Native
   private void onEvent(EventUpdate e) {
      if (!this.proverkacrystala()) {
         this.handlePkmPlace();
         this.handleObsidianPlace();
      }

      if (this.explodeTicks > 0) {
         --this.explodeTicks;
      }

      this.tryAttackPendingCrystal();
      if (this.crystalArea != null) {
         this.attackCrystalInArea();
      }

   }

   private void handleObsidianPlace() {
      if (this.needSync) {
         this.needSync = false;
         Minecraft var10000 = mc;
         Minecraft.player.inventory.currentItem = this.oldSlot;
         mc.playerController.syncCurrentPlayItem();
      } else if (this.targetPos != null) {
         if (mc.world.getBlockState(this.targetPos).isAir()) {
            this.targetPos = null;
         } else if (this.blocked) {
            this.blocked = false;
         } else {
            this.placeCrystalOnObsidian(this.targetPos, this.targetSlot, Hand.MAIN_HAND);
            this.needSync = true;
            this.targetPos = null;
         }
      }
   }

   private void handlePkmPlace() {
      if ((Boolean)this.test.get() && this.pkmPlacePos != null) {
         if (mc.world.getBlockState(this.pkmPlacePos).getBlock() == Blocks.OBSIDIAN && mc.world.getBlockState(this.pkmPlacePos.up()).isAir()) {
            if (this.pkmBlocked) {
               this.pkmBlocked = false;
            } else if (this.pkmCrystalSlot == -1) {
               this.pkmPlacePos = null;
            } else {
               this.rotateToObsidian(this.pkmPlacePos);
               Vector3d hitVec = new Vector3d((double)this.pkmPlacePos.getX() + 0.5, (double)this.pkmPlacePos.getY() + 0.5, (double)this.pkmPlacePos.getZ() + 0.5);
               BlockRayTraceResult rayTrace = new BlockRayTraceResult(hitVec, Direction.UP, this.pkmPlacePos, false);
               int var10002 = this.pkmCrystalSlot < 9 ? this.pkmCrystalSlot + 36 : this.pkmCrystalSlot;
               Minecraft var10005 = mc;
               mc.playerController.windowClick(0, var10002, 40, ClickType.SWAP, Minecraft.player);
               Minecraft var10001 = mc;
               mc.playerController.func_217292_a(Minecraft.player, mc.world, Hand.OFF_HAND, rayTrace);
               var10002 = this.pkmCrystalSlot < 9 ? this.pkmCrystalSlot + 36 : this.pkmCrystalSlot;
               var10005 = mc;
               mc.playerController.windowClick(0, var10002, 40, ClickType.SWAP, Minecraft.player);
               this.scheduleCrystalExplosion(this.pkmPlacePos);
               this.pkmPlacePos = null;
               this.pkmCrystalSlot = -1;
            }
         } else {
            this.pkmPlacePos = null;
            this.pkmCrystalSlot = -1;
            this.pkmBlocked = false;
         }
      }
   }

   private void placeCrystalOnObsidian(BlockPos obsidianPos, int crystalSlot, Hand hand) {
      Minecraft var10000 = mc;
      Vector3d eyeVec = Minecraft.player.getEyePosition(1.0F);
      Vector3d hitVec = AuraUtil.getClosestVec(eyeVec, new AxisAlignedBB(obsidianPos));
      this.rotateToObsidian(obsidianPos);
      Minecraft var10001 = mc;
      this.oldSlot = Minecraft.player.inventory.currentItem;
      var10000 = mc;
      Minecraft.player.inventory.currentItem = crystalSlot;
      mc.playerController.syncCurrentPlayItem();
      Vector3d offset = hitVec.subtract(eyeVec).inverse();
      var10001 = mc;
      mc.playerController.func_217292_a(Minecraft.player, mc.world, hand, new BlockRayTraceResult(hitVec, Direction.getFacingFromVector(offset.x, offset.y, offset.z), obsidianPos, false));
      var10000 = mc;
      Minecraft.player.swingArm(hand);
      this.scheduleCrystalExplosion(obsidianPos);
   }

   private void rotateToObsidian(BlockPos obsidianPos) {
      Minecraft var10000 = mc;
      Vector3d eyeVec = Minecraft.player.getEyePosition(1.0F);
      Vector3d hitVec = AuraUtil.getClosestVec(eyeVec, new AxisAlignedBB(obsidianPos));
      this.applyRotationToPoint(hitVec);
   }

   private void applyRotationToPoint(Vector3d target) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         Vector3d eyeVec = Minecraft.player.getEyePosition(1.0F);
         Vector3d offset = target.subtract(eyeVec);
         float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(offset.z, offset.x)) - 90.0);
         float targetPitch = (float)(-Math.toDegrees(Math.atan2(offset.y, Math.hypot(offset.x, offset.z))));
         RotationComponent.update(new Rotation(targetYaw, targetPitch), 180.0F, 1, 6);
      }
   }

   private void rotateToCrystal(Entity entity) {
      if (entity != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (!(Boolean)this.fast.get()) {
               AxisAlignedBB var5 = entity.getBoundingBox();
               Minecraft var10001 = mc;
               if (!var5.contains(Minecraft.player.getEyePosition(1.0F))) {
                  Vector3d direction = AuraUtil.getVector(entity);
                  float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0);
                  float targetPitch = (float)(-Math.toDegrees(Math.atan2(direction.y, Math.hypot(direction.x, direction.z))));
                  RotationComponent.update(new Rotation(targetYaw, targetPitch), 180.0F, 1, 6);
               }
            }

            return;
         }
      }

   }

   private void scheduleCrystalExplosion(BlockPos obsidianPos) {
      BlockPos crystalPos = obsidianPos.up();
      this.crystalArea = (new AxisAlignedBB(crystalPos)).grow((Boolean)this.fast.get() ? 0.5 : 0.1);
      this.explodeTicks = (Boolean)this.fast.get() ? 7 : 5;
      this.pendingCrystalId = -1;
      this.pendingCrystalPos = new Vector3d((double)crystalPos.getX() + 0.5, (double)crystalPos.getY() + 0.5, (double)crystalPos.getZ() + 0.5);
      this.pendingCrystalTicks = (Boolean)this.fast.get() ? 8 : 6;
      this.attackCrystalInArea();
   }

   private void clearExplosionState() {
      this.crystalArea = null;
      this.pendingCrystalId = -1;
      this.pendingCrystalPos = null;
      this.pendingCrystalTicks = 0;
   }

   @Native
   private void attackCrystalInArea() {
      if (this.crystalArea != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            Iterator var1 = mc.world.getEntitiesWithinAABB(EnderCrystalEntity.class, this.crystalArea.grow(0.25)).iterator();
            if (var1.hasNext()) {
               EnderCrystalEntity entity = (EnderCrystalEntity)var1.next();
               if (this.shouldNotExplodeBecauseOfResources(entity)) {
                  this.clearExplosionState();
                  return;
               }

               this.rotateToCrystal(entity);
               this.attackCrystal(entity);
               this.clearExplosionState();
               return;
            }

            return;
         }
      }

   }

   @Native
   private void attackCrystal(Entity entity) {
      if (entity != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            AttackOrder.sendFixedAttack(Minecraft.player, entity);
            return;
         }
      }

   }

   private void tryAttackPendingCrystal() {
      if (this.pendingCrystalTicks > 0 && mc.world != null) {
         --this.pendingCrystalTicks;
         Entity byId = this.pendingCrystalId == -1 ? null : mc.world.getEntityByID(this.pendingCrystalId);
         if (byId instanceof EnderCrystalEntity) {
            EnderCrystalEntity crystalById = (EnderCrystalEntity)byId;
            if (!this.shouldNotExplodeBecauseOfResources(crystalById)) {
               this.rotateToCrystal(crystalById);
               this.attackCrystal(crystalById);
               this.clearExplosionState();
               return;
            }
         }

         if (this.pendingCrystalPos != null) {
            AxisAlignedBB spawnAabb = new AxisAlignedBB(this.pendingCrystalPos.x - 0.8, this.pendingCrystalPos.y - 0.8, this.pendingCrystalPos.z - 0.8, this.pendingCrystalPos.x + 0.8, this.pendingCrystalPos.y + 1.8, this.pendingCrystalPos.z + 0.8);
            Iterator var3 = mc.world.getEntitiesWithinAABB(EnderCrystalEntity.class, spawnAabb).iterator();

            while(var3.hasNext()) {
               EnderCrystalEntity crystal = (EnderCrystalEntity)var3.next();
               if (!this.shouldNotExplodeBecauseOfResources(crystal)) {
                  this.rotateToCrystal(crystal);
                  this.attackCrystal(crystal);
                  this.clearExplosionState();
                  return;
               }
            }
         }

      }
   }

   @EventTarget
   private void onPacket(EventPacket event) {
      if (event.isReceive()) {
         IPacket var3 = event.getPacket();
         if (var3 instanceof SSpawnObjectPacket) {
            SSpawnObjectPacket packet = (SSpawnObjectPacket)var3;
            if (packet.getType() == EntityType.END_CRYSTAL) {
               if (this.crystalArea != null) {
                  double px = packet.getX();
                  double py = packet.getY();
                  double pz = packet.getZ();
                  if (this.crystalArea.grow(0.5).contains(new Vector3d(px, py, pz))) {
                     if ((Boolean)this.fast.get() && mc.world != null) {
                        this.pendingCrystalId = packet.getEntityID();
                        this.pendingCrystalPos = new Vector3d(px, py, pz);
                        this.pendingCrystalTicks = 8;
                        this.tryAttackPendingCrystal();
                        Entity spawned = mc.world.getEntityByID(packet.getEntityID());
                        if (spawned instanceof EnderCrystalEntity) {
                           EnderCrystalEntity crystal = (EnderCrystalEntity)spawned;
                           if (!this.shouldNotExplodeBecauseOfResources(crystal)) {
                              this.rotateToCrystal(crystal);
                              this.attackCrystal(crystal);
                              this.clearExplosionState();
                              return;
                           }
                        }
                     }

                     this.explodeTicks = (Boolean)this.fast.get() ? 7 : 3;
                     this.attackCrystalInArea();
                  }
               }
            }
         }
      }
   }

   @EventTarget
   private void onClick(EventClick e) {
      if (!this.proverkacrystala()) {
         if ((Boolean)this.test.get()) {
            int slotCrystal = InventoryUtil.getItemSlot(Items.END_CRYSTAL);
            if (slotCrystal != -1) {
               Minecraft var10000 = mc;
               if (Minecraft.player.getHeldItemMainhand().getItem() instanceof BlockItem) {
                  var10000 = mc;
                  if (Minecraft.player.getHeldItemMainhand().getItem() != Items.PLAYER_HEAD) {
                     return;
                  }
               }

               var10000 = mc;
               if (Minecraft.player.getHeldItemOffhand().getItem() instanceof BlockItem) {
                  var10000 = mc;
                  if (Minecraft.player.getHeldItemOffhand().getItem() != Items.PLAYER_HEAD) {
                     return;
                  }
               }

               if (mc.objectMouseOver instanceof BlockRayTraceResult) {
                  BlockPos obsidianPos = ((BlockRayTraceResult)mc.objectMouseOver).getPos();
                  if (mc.world.getBlockState(obsidianPos).getBlock() == Blocks.OBSIDIAN && mc.world.getBlockState(obsidianPos.up()).getBlock() == Blocks.AIR) {
                     this.pkmPlacePos = obsidianPos;
                     this.pkmCrystalSlot = slotCrystal;
                     this.pkmBlocked = true;
                     this.scheduleCrystalExplosion(obsidianPos);
                     e.setCancelled(true);
                  }
               }
            }
         }
      }
   }

   private boolean shouldNotExplodeBecauseOfResources(Entity crystalEntity) {
      if (!(Boolean)this.noExplodeResources.get()) {
         return false;
      } else {
         return mc.world == null ? false : mc.world.getEntitiesWithinAABB(ItemEntity.class, crystalEntity.getBoundingBox().grow(6.0)).stream().map((itemEntity) -> {
            return itemEntity.getItem().getItem();
         }).anyMatch((item) -> {
            return item == Items.TOTEM_OF_UNDYING || item == Items.END_CRYSTAL || item == Items.ENCHANTED_GOLDEN_APPLE || item == Items.NETHERITE_HELMET || item == Items.NETHERITE_CHESTPLATE || item == Items.NETHERITE_LEGGINGS || item == Items.NETHERITE_BOOTS || item == Items.NETHERITE_SWORD || item == Items.DIAMOND_SWORD || item == Items.ELYTRA || item == Items.TRIDENT;
         });
      }
   }

   @EventTarget
   private void onEvent(EventPostUpdate e) {
   }
}
