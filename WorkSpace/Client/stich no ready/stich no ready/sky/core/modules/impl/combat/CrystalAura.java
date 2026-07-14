package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mods.viaversion.viamcp.fixes.AttackOrder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket.Action;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventPacket;
import sky.core.events.EventRender3D;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.player.InventoryUtil;

public class CrystalAura extends Module {
   private static final double CRYSTAL_POWER = 12.0;
   private static final int PENDING_TICKS = 8;
   private final BooleanSetting autoSwitch = new BooleanSetting("Авто свап", true);
   private final BooleanSetting placeCrystals = new BooleanSetting("Ставить кристалл", true);
   private final BooleanSetting breakCrystals = new BooleanSetting("Взрывать кристалл", true);
   private final BooleanSetting placeObsidian = new BooleanSetting("Ставить обсидиан", true);
   private final BooleanSetting mineBlocks = new BooleanSetting("Подкоп", true);
   private final BooleanSetting elytraSetup = new BooleanSetting("Сетап элитр", true);
   private final BooleanSetting throughWalls = new BooleanSetting("Через стены", true);
   private final BooleanSetting antiSelf = new BooleanSetting("Не взрывать себя", true);
   private final BooleanSetting antiFriend = new BooleanSetting("Не дамажить друзей", true);
   private final BooleanSetting fastSpawn = new BooleanSetting("Быстрый взрыв", true);
   private final BooleanSetting render = new BooleanSetting("Подсветка", true);
   private final SliderSetting placeRange = new SliderSetting("Дистанция установки", 4.8F, 1.0F, 6.0F, 0.1F);
   private final SliderSetting breakRange = new SliderSetting("Дистанция взрыва", 4.8F, 1.0F, 6.0F, 0.1F);
   private final SliderSetting wallRange = new SliderSetting("Дистанция стен", 3.0F, 1.0F, 6.0F, 0.1F);
   private final SliderSetting targetRange = new SliderSetting("Дистанция цели", 12.0F, 3.0F, 18.0F, 0.5F);
   private final SliderSetting minDamage = new SliderSetting("Мин. урон", 4.0F, 0.0F, 20.0F, 0.5F);
   private final SliderSetting facePlaceHealth = new SliderSetting("Добивание HP", 8.0F, 1.0F, 20.0F, 0.5F);
   private final SliderSetting maxSelfDamage = new SliderSetting("Макс. себе", 7.0F, 0.0F, 20.0F, 0.5F);
   private final SliderSetting predictTicks = new SliderSetting("Предикт", 2.0F, 0.0F, 6.0F, 0.5F);
   private final SliderSetting placeDelay = new SliderSetting("Задержка установки", 0.0F, 0.0F, 10.0F, 1.0F);
   private final SliderSetting breakDelay = new SliderSetting("Задержка взрыва", 0.0F, 0.0F, 10.0F, 1.0F);
   private final SliderSetting mineDelay = new SliderSetting("Задержка подкопа", 2.0F, 0.0F, 12.0F, 1.0F);
   private CrystalPlaceResult currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
   private BlockPos pendingCrystalBase;
   private Vector3d pendingCrystalPos;
   private int pendingCrystalId = -1;
   private int pendingTicks;
   private int placeCooldown;
   private int breakCooldown;
   private int mineCooldown;
   private List nearbyFriends = Collections.emptyList();

   public CrystalAura() {
      super("CrystalAura", "Автоматически ставит, копает и взрывает кристаллы", Category.Combat);
      this.addSettings(new Setting[]{this.autoSwitch, this.placeCrystals, this.breakCrystals, this.placeObsidian, this.mineBlocks, this.elytraSetup, this.throughWalls, this.antiSelf, this.antiFriend, this.fastSpawn, this.render, this.placeRange, this.breakRange, this.wallRange, this.targetRange, this.minDamage, this.facePlaceHealth, this.maxSelfDamage, this.predictTicks, this.placeDelay, this.breakDelay, this.mineDelay});
   }

   public void onDisable() {
      this.resetState();
      super.onDisable();
   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && mc.currentScreen == null) {
         this.tickCooldowns();
         PlayerEntity target = this.findTarget();
         if (target == null) {
            this.resetState();
            this.nearbyFriends = Collections.emptyList();
         } else if (!this.tryAttackPendingCrystal(target)) {
            if (this.pendingTicks <= 0 || this.pendingCrystalBase == null) {
               List entities = this.collectRelevantEntities();
               this.nearbyFriends = this.collectNearbyFriends(entities);
               Minecraft var10002 = mc;
               CrystalPlaceResult strict = this.findStrictPlacePosition(target, Minecraft.player, entities);
               CrystalPlaceResult var8;
               if ((Boolean)this.placeCrystals.get()) {
                  var10002 = mc;
                  var8 = this.findPlacePosition(target, Minecraft.player, entities);
               } else {
                  var8 = CrystalAura.CrystalPlaceResult.none();
               }

               CrystalPlaceResult direct = var8;
               var10002 = mc;
               CrystalPlaceResult crystal = this.findCrystalTarget(target, Minecraft.player, entities);
               CrystalPlaceResult selected = this.selectResult(strict, direct, crystal);
               this.currentPlaceResult = selected;
               if (selected.getActionType() != CrystalAura.CrystalActionType.NONE) {
                  this.rotateTo(selected.getAimVec());
                  this.execute(selected, target);
               }
            }
         }
      }
   }

   @EventTarget
   private void onPacket(EventPacket event) {
      if ((Boolean)this.fastSpawn.get() && event.isReceive() && this.pendingTicks > 0) {
         if (event.getPacket() instanceof SSpawnObjectPacket) {
            SSpawnObjectPacket packet = (SSpawnObjectPacket)event.getPacket();
            if (packet.getType() == EntityType.END_CRYSTAL && this.pendingCrystalBase != null) {
               Vector3d spawn = new Vector3d(packet.getX(), packet.getY(), packet.getZ());
               if (this.pendingCrystalPos == null || !(spawn.squareDistanceTo(this.pendingCrystalPos) > 4.0)) {
                  this.pendingCrystalId = packet.getEntityID();
                  Entity entity = mc.world == null ? null : mc.world.getEntityByID(this.pendingCrystalId);
                  if (entity instanceof EnderCrystalEntity) {
                     PlayerEntity target = this.findTarget();
                     if (target != null && this.canAttackPending((EnderCrystalEntity)entity, target)) {
                        this.attackCrystal((EnderCrystalEntity)entity);
                     }

                     this.clearPendingCrystal();
                  }

               }
            }
         }
      }
   }

   @EventTarget
   private void onRender(EventRender3D event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && (Boolean)this.render.get() && this.currentPlaceResult != null && this.currentPlaceResult.getActionType() != CrystalAura.CrystalActionType.NONE) {
         Vector3d camera = mc.getRenderManager().info.getProjectedView();
         RenderSystem.pushMatrix();
         RenderSystem.translated(-camera.x, -camera.y, -camera.z);
         RenderSystem.enableBlend();
         RenderSystem.disableTexture();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.disableCull();
         GL11.glEnable(2848);
         AxisAlignedBB box = this.getRenderBox(this.currentPlaceResult, event.getPartialTicks());
         if (box != null) {
            int color = this.getActionColor(this.currentPlaceResult.getActionType());
            this.drawBox(box, color, 0.24F);
            this.drawOutline(box, color, 1.4F);
         }

         GL11.glDisable(2848);
         RenderSystem.depthMask(true);
         RenderSystem.enableTexture();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
         RenderSystem.enableCull();
         RenderSystem.popMatrix();
      }
   }

   private CrystalPlaceResult selectResult(CrystalPlaceResult strict, CrystalPlaceResult direct, CrystalPlaceResult crystal) {
      CrystalPlaceResult selected = strict;
      if (this.scorePlaceResult(direct) > this.scorePlaceResult(strict)) {
         selected = direct;
      }

      if ((crystal.getActionType() == CrystalAura.CrystalActionType.BREAK || (Boolean)this.autoSwitch.get()) && this.scorePlaceResult(crystal) > this.scorePlaceResult(selected)) {
         selected = crystal;
      }

      if (strict.getActionType() == CrystalAura.CrystalActionType.NONE && strict.isHardStop()) {
         selected = strict;
      }

      return selected;
   }

   private CrystalPlaceResult findPlacePosition(PlayerEntity target, PlayerEntity self, Iterable entities) {
      CrystalPlaceResult best = CrystalAura.CrystalPlaceResult.none();
      Vector3d predicted = this.predict(target);
      BlockPos center = new BlockPos(predicted);
      int horizontal = target.isElytraFlying() && (Boolean)this.elytraSetup.get() ? 3 : 4;
      int minY = target.isElytraFlying() && (Boolean)this.elytraSetup.get() ? -8 : -3;
      int maxY = target.isElytraFlying() && (Boolean)this.elytraSetup.get() ? 1 : 3;
      double maxRange = (double)(Float)this.placeRange.get() + 0.75;
      double maxRangeSq = maxRange * maxRange;

      for(int x = -horizontal; x <= horizontal; ++x) {
         for(int y = minY; y <= maxY; ++y) {
            for(int z = -horizontal; z <= horizontal; ++z) {
               BlockPos base = center.add(x, y, z);
               Minecraft var10000 = mc;
               if (!(Minecraft.player.getDistanceSq((double)base.getX() + 0.5, (double)base.getY() + 0.5, (double)base.getZ() + 0.5) > maxRangeSq)) {
                  CrystalPlaceResult result = this.evaluateCrystalBase(base, target, self, entities, CrystalAura.CrystalActionType.PLACE_AND_BREAK);
                  if (this.scorePlaceResult(result) > this.scorePlaceResult(best)) {
                     best = result;
                  }
               }
            }
         }
      }

      return best;
   }

   private CrystalPlaceResult findStrictPlacePosition(PlayerEntity target, PlayerEntity self, Iterable entities) {
      if (!(Boolean)this.placeObsidian.get() && !(Boolean)this.mineBlocks.get()) {
         return CrystalAura.CrystalPlaceResult.none();
      } else {
         CrystalPlaceResult best = CrystalAura.CrystalPlaceResult.none();
         Vector3d predicted = this.predict(target);
         BlockPos center = new BlockPos(predicted);
         int horizontal = target.isElytraFlying() && (Boolean)this.elytraSetup.get() ? 3 : 2;
         int minY = target.isElytraFlying() && (Boolean)this.elytraSetup.get() ? -9 : -3;
         int maxY = target.isElytraFlying() && (Boolean)this.elytraSetup.get() ? 0 : 2;
         double maxRange = (double)(Float)this.placeRange.get() + 1.25;
         double maxRangeSq = maxRange * maxRange;

         for(int x = -horizontal; x <= horizontal; ++x) {
            for(int y = minY; y <= maxY; ++y) {
               for(int z = -horizontal; z <= horizontal; ++z) {
                  BlockPos obsidianPos = center.add(x, y, z);
                  Minecraft var10000 = mc;
                  if (!(Minecraft.player.getDistanceSq((double)obsidianPos.getX() + 0.5, (double)obsidianPos.getY() + 0.5, (double)obsidianPos.getZ() + 0.5) > maxRangeSq)) {
                     CrystalPlaceResult result = this.evaluateSetupPosition(obsidianPos, target, self, entities);
                     if (result.isHardStop()) {
                        return result;
                     }

                     if (this.scorePlaceResult(result) > this.scorePlaceResult(best)) {
                        best = result;
                     }
                  }
               }
            }
         }

         return best;
      }
   }

   private CrystalPlaceResult findCrystalTarget(PlayerEntity target, PlayerEntity self, Iterable entities) {
      CrystalPlaceResult best = CrystalAura.CrystalPlaceResult.none();
      if (!(Boolean)this.breakCrystals.get()) {
         return best;
      } else {
         Iterator var5 = entities.iterator();

         while(true) {
            Entity entity;
            Vector3d crystalPos;
            Vector3d aim;
            double targetDamage;
            double selfDamage;
            do {
               do {
                  do {
                     do {
                        do {
                           do {
                              if (!var5.hasNext()) {
                                 return best;
                              }

                              entity = (Entity)var5.next();
                           } while(!(entity instanceof EnderCrystalEntity));
                        } while(!entity.isAlive());
                     } while(self.getDistance(entity) > (Float)this.breakRange.get());

                     crystalPos = entity.getPositionVec();
                     aim = crystalPos.add(0.0, 0.5, 0.0);
                  } while(!this.canReach(aim, (double)(Float)this.breakRange.get(), (double)(Float)this.wallRange.get()));

                  targetDamage = this.calculateDamage(crystalPos, target);
                  selfDamage = this.calculateDamage(crystalPos, self);
               } while(!this.isDamageAllowed(crystalPos, target, targetDamage, selfDamage));
            } while((Boolean)this.antiFriend.get() && this.hurtsFriend(crystalPos));

            double score = targetDamage - selfDamage * 0.55 + 0.35;
            CrystalPlaceResult result = new CrystalPlaceResult((BlockPos)null, Direction.UP, targetDamage, selfDamage, score, CrystalAura.CrystalActionType.BREAK, entity, aim, (BlockPlacement)null, false);
            if (this.scorePlaceResult(result) > this.scorePlaceResult(best)) {
               best = result;
            }
         }
      }
   }

   private CrystalPlaceResult evaluateCrystalBase(BlockPos base, PlayerEntity target, PlayerEntity self, Iterable entities, CrystalActionType action) {
      Vector3d aim = this.blockCenter(base);
      if (!this.canReach(aim, (double)(Float)this.placeRange.get(), (double)(Float)this.wallRange.get())) {
         return CrystalAura.CrystalPlaceResult.none();
      } else if (!this.canPlaceCrystalOn(base, entities)) {
         return CrystalAura.CrystalPlaceResult.none();
      } else {
         Vector3d crystalPos = this.crystalVec(base);
         if (action == CrystalAura.CrystalActionType.PLACE_AND_BREAK && !this.canReach(crystalPos, (double)(Float)this.breakRange.get(), (double)(Float)this.wallRange.get())) {
            return CrystalAura.CrystalPlaceResult.none();
         } else {
            double targetDamage = this.calculateDamage(crystalPos, target);
            double selfDamage = this.calculateDamage(crystalPos, self);
            if (!this.isDamageAllowed(crystalPos, target, targetDamage, selfDamage)) {
               return CrystalAura.CrystalPlaceResult.none();
            } else if ((Boolean)this.antiFriend.get() && this.hurtsFriend(crystalPos)) {
               return CrystalAura.CrystalPlaceResult.none();
            } else {
               double score = this.scoreDamage(base, target, targetDamage, selfDamage);
               return new CrystalPlaceResult(base, Direction.UP, targetDamage, selfDamage, score, action, (Entity)null, crystalPos, (BlockPlacement)null, false);
            }
         }
      }
   }

   private CrystalPlaceResult evaluateSetupPosition(BlockPos obsidianPos, PlayerEntity target, PlayerEntity self, Iterable entities) {
      BlockState state = mc.world.getBlockState(obsidianPos);
      Block block = state.getBlock();
      if (block != Blocks.OBSIDIAN && block != Blocks.BEDROCK) {
         BlockPlacement placement = null;
         boolean canPlaceObsidian = false;
         if ((Boolean)this.placeObsidian.get() && this.isReplaceable(obsidianPos)) {
            placement = this.findBlockPlacement(obsidianPos);
            canPlaceObsidian = placement != null && this.canReach(placement.hitVec, (double)(Float)this.placeRange.get(), (double)(Float)this.wallRange.get());
         }

         PlaceScanResult scan = this.scanCrystalSpace(entities, obsidianPos.up());
         boolean crystalSpaceCanOpen = scan == CrystalAura.PlaceScanResult.VALID || scan == CrystalAura.PlaceScanResult.OUT_OF_RANGE;
         Vector3d mineAim = this.blockCenter(obsidianPos);
         boolean canMine = crystalSpaceCanOpen && (Boolean)this.mineBlocks.get() && this.canMineAsSetup(obsidianPos) && this.canReach(mineAim, (double)(Float)this.placeRange.get(), (double)(Float)this.wallRange.get());
         if (!canPlaceObsidian && !canMine) {
            return CrystalAura.CrystalPlaceResult.none();
         } else {
            Vector3d crystalPos = this.crystalVec(obsidianPos);
            double targetDamage = this.calculateDamage(crystalPos, target);
            double selfDamage = this.calculateDamage(crystalPos, self);
            if (!this.isDamageAllowed(crystalPos, target, targetDamage, selfDamage)) {
               return CrystalAura.CrystalPlaceResult.none();
            } else if ((Boolean)this.antiFriend.get() && this.hurtsFriend(crystalPos)) {
               return CrystalAura.CrystalPlaceResult.none();
            } else {
               double score;
               if (canPlaceObsidian) {
                  score = this.scoreDamage(obsidianPos, target, targetDamage, selfDamage) - 0.45;
                  return new CrystalPlaceResult(obsidianPos, placement.face, targetDamage, selfDamage, score, CrystalAura.CrystalActionType.PLACE, (Entity)null, placement.hitVec, placement, false);
               } else if (canMine) {
                  score = this.scoreDamage(obsidianPos, target, targetDamage, selfDamage) - 0.75;
                  return new CrystalPlaceResult(obsidianPos, Direction.UP, targetDamage, selfDamage, score, CrystalAura.CrystalActionType.ATTACK, (Entity)null, mineAim, (BlockPlacement)null, false);
               } else {
                  return CrystalAura.CrystalPlaceResult.none();
               }
            }
         }
      } else {
         return this.evaluateCrystalBase(obsidianPos, target, self, entities, CrystalAura.CrystalActionType.PLACE_AND_BREAK);
      }
   }

   private boolean canMineAsSetup(BlockPos pos) {
      BlockState state = mc.world.getBlockState(pos);
      if (!state.isAir() && state.getBlock() != Blocks.BEDROCK && state.getBlock() != Blocks.OBSIDIAN) {
         if (InventoryUtil.findBestToolForBlock(state) == -1 && !state.getMaterial().isReplaceable()) {
            return false;
         } else {
            BlockPos crystal = pos.up();
            BlockState first = mc.world.getBlockState(crystal);
            BlockState second = mc.world.getBlockState(crystal.up());
            if (!first.isAir() && !first.getMaterial().isReplaceable()) {
               return false;
            } else {
               return second.isAir() || second.getMaterial().isReplaceable();
            }
         }
      } else {
         return false;
      }
   }

   private PlaceScanResult scanCrystalSpace(Iterable entities, BlockPos crystalPos) {
      AxisAlignedBB box = this.crystalBox(crystalPos);
      Iterator var4 = mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, box).iterator();

      while(var4.hasNext()) {
         Entity entity = (Entity)var4.next();
         if (entity != null && entity.isAlive() && !entity.isSpectator()) {
            Minecraft var10001 = mc;
            if (entity == Minecraft.player) {
               if (entity.getBoundingBox().intersects(box)) {
                  return CrystalAura.PlaceScanResult.OUT_OF_RANGE;
               }
            } else if (entity.getBoundingBox().intersects(box) && !(entity instanceof EnderCrystalEntity)) {
               if (entity instanceof PlayerEntity) {
                  return CrystalAura.PlaceScanResult.OUT_OF_RANGE;
               }

               if (entity instanceof ItemEntity) {
                  return CrystalAura.PlaceScanResult.BLOCKED;
               }

               return CrystalAura.PlaceScanResult.BLOCKED;
            }
         }
      }

      return CrystalAura.PlaceScanResult.VALID;
   }

   private boolean canPlaceCrystalOn(BlockPos base, Iterable entities) {
      Block block = mc.world.getBlockState(base).getBlock();
      if (block != Blocks.OBSIDIAN && block != Blocks.BEDROCK) {
         return false;
      } else if (mc.world.isAirBlock(base.up()) && mc.world.isAirBlock(base.up(2))) {
         return this.scanCrystalSpace(entities, base.up()) == CrystalAura.PlaceScanResult.VALID;
      } else {
         return false;
      }
   }

   private void execute(CrystalPlaceResult result, PlayerEntity target) {
      switch (result.getActionType()) {
         case BREAK:
            if (this.breakCooldown <= 0) {
               this.executeBreak(result, target);
            }
            break;
         case PLACE_AND_BREAK:
            if (this.placeCooldown <= 0) {
               this.executeCrystalPlace(result, target);
            }
            break;
         case PLACE:
            if (this.placeCooldown <= 0) {
               this.executeObsidianPlace(result);
            }
            break;
         case ATTACK:
            if (this.mineCooldown <= 0) {
               this.executeMine(result);
            }
      }

   }

   private void executeBreak(CrystalPlaceResult result, PlayerEntity target) {
      if (result.getEntity() instanceof EnderCrystalEntity && result.getEntity().isAlive()) {
         EnderCrystalEntity crystal = (EnderCrystalEntity)result.getEntity();
         Vector3d crystalPos = crystal.getPositionVec();
         double targetDamage = this.calculateDamage(crystalPos, target);
         Minecraft var10002 = mc;
         double selfDamage = this.calculateDamage(crystalPos, Minecraft.player);
         if (this.isDamageAllowed(crystalPos, target, targetDamage, selfDamage)) {
            this.attackCrystal(crystal);
            this.breakCooldown = Math.round((Float)this.breakDelay.get());
            this.currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
         }
      }
   }

   private void executeCrystalPlace(CrystalPlaceResult result, PlayerEntity target) {
      if (result.getBlockPos() != null && this.canUseItem(Items.END_CRYSTAL)) {
         if (!this.canPlaceCrystalOn(result.getBlockPos(), this.collectRelevantEntities())) {
            this.currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
         } else {
            SlotSwap swap = this.switchToItem(Items.END_CRYSTAL);
            if (swap.valid) {
               BlockPos base = result.getBlockPos();
               Vector3d hitVec = this.blockCenter(base);
               Minecraft var10001 = mc;
               mc.playerController.func_217292_a(Minecraft.player, mc.world, swap.hand, new BlockRayTraceResult(hitVec, Direction.UP, base, false));
               Minecraft var10000 = mc;
               Minecraft.player.swingArm(swap.hand);
               this.restoreSwap(swap);
               this.placeCooldown = Math.round((Float)this.placeDelay.get());
               this.schedulePendingCrystal(base);
               this.tryAttackPendingCrystal(target);
            }
         }
      }
   }

   private void executeObsidianPlace(CrystalPlaceResult result) {
      if (result.getBlockPos() != null && result.getPlacement() != null && this.canUseItem(Items.OBSIDIAN)) {
         if (!this.isReplaceable(result.getBlockPos())) {
            this.currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
         } else {
            SlotSwap swap = this.switchToItem(Items.OBSIDIAN);
            if (swap.valid) {
               BlockPlacement placement = result.getPlacement();
               Minecraft var10001 = mc;
               mc.playerController.func_217292_a(Minecraft.player, mc.world, swap.hand, new BlockRayTraceResult(placement.hitVec, placement.face, placement.supportPos, false));
               Minecraft var10000 = mc;
               Minecraft.player.swingArm(swap.hand);
               this.restoreSwap(swap);
               this.placeCooldown = Math.max(1, Math.round((Float)this.placeDelay.get()));
               this.currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
            }
         }
      }
   }

   private void executeMine(CrystalPlaceResult result) {
      if (result.getBlockPos() != null) {
         BlockState state = mc.world.getBlockState(result.getBlockPos());
         if (state.isAir()) {
            this.currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
         } else {
            Minecraft var10000 = mc;
            int oldSlot = Minecraft.player.inventory.currentItem;
            int toolSlot = InventoryUtil.findBestToolForBlock(state);
            if (toolSlot != -1 && toolSlot != oldSlot) {
               this.switchToHotbar(toolSlot);
            }

            Direction face = this.getMiningFace(result.getBlockPos());
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CPlayerDiggingPacket(Action.START_DESTROY_BLOCK, result.getBlockPos(), face));
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CPlayerDiggingPacket(Action.STOP_DESTROY_BLOCK, result.getBlockPos(), face));
            var10000 = mc;
            Minecraft.player.swingArm(Hand.MAIN_HAND);
            if (toolSlot != -1 && oldSlot != toolSlot) {
               this.switchToHotbar(oldSlot);
            }

            this.mineCooldown = Math.round((Float)this.mineDelay.get());
            this.currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
         }
      }
   }

   private boolean tryAttackPendingCrystal(PlayerEntity target) {
      if (this.pendingTicks > 0 && this.pendingCrystalBase != null && (Boolean)this.breakCrystals.get()) {
         --this.pendingTicks;
         Entity byId = this.pendingCrystalId == -1 ? null : mc.world.getEntityByID(this.pendingCrystalId);
         if (byId instanceof EnderCrystalEntity && this.canAttackPending((EnderCrystalEntity)byId, target)) {
            this.attackCrystal((EnderCrystalEntity)byId);
            this.clearPendingCrystal();
            return true;
         } else {
            AxisAlignedBB box = (new AxisAlignedBB(this.pendingCrystalBase.up())).grow(1.0, 1.5, 1.0);
            Iterator var4 = mc.world.getEntitiesWithinAABB(EnderCrystalEntity.class, box).iterator();

            EnderCrystalEntity crystal;
            do {
               if (!var4.hasNext()) {
                  if (this.pendingTicks <= 0) {
                     this.clearPendingCrystal();
                  }

                  return false;
               }

               crystal = (EnderCrystalEntity)var4.next();
            } while(!this.canAttackPending(crystal, target));

            this.attackCrystal(crystal);
            this.clearPendingCrystal();
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean canAttackPending(EnderCrystalEntity crystal, PlayerEntity target) {
      if (crystal != null && crystal.isAlive()) {
         Minecraft var10000 = mc;
         if (!(Minecraft.player.getDistance(crystal) > (Float)this.breakRange.get())) {
            if (target == null) {
               return false;
            }

            Vector3d pos = crystal.getPositionVec();
            double targetDamage = this.calculateDamage(pos, target);
            Minecraft var10002 = mc;
            double selfDamage = this.calculateDamage(pos, Minecraft.player);
            return this.isDamageAllowed(pos, target, targetDamage, selfDamage);
         }
      }

      return false;
   }

   private void attackCrystal(EnderCrystalEntity crystal) {
      this.rotateTo(crystal.getPositionVec().add(0.0, 0.5, 0.0));
      Minecraft var10000 = mc;
      AttackOrder.sendFixedAttack(Minecraft.player, crystal);
      var10000 = mc;
      Minecraft.player.swingArm(Hand.MAIN_HAND);
      this.breakCooldown = Math.round((Float)this.breakDelay.get());
   }

   private List collectRelevantEntities() {
      double range = (double)Math.max((Float)this.placeRange.get(), (Float)this.breakRange.get()) + 4.0;
      Minecraft var10004 = mc;
      return new ArrayList(mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, Minecraft.player.getBoundingBox().grow(range)));
   }

   private List collectNearbyFriends(List entities) {
      if (!(Boolean)this.antiFriend.get()) {
         return Collections.emptyList();
      } else {
         List friends = new ArrayList();
         Iterator var3 = entities.iterator();

         while(var3.hasNext()) {
            Entity entity = (Entity)var3.next();
            if (entity instanceof PlayerEntity) {
               Minecraft var10001 = mc;
               if (entity != Minecraft.player) {
                  PlayerEntity player = (PlayerEntity)entity;
                  if (player.isAlive() && this.isFriend(player)) {
                     friends.add(player);
                  }
               }
            }
         }

         return friends;
      }
   }

   private PlayerEntity findTarget() {
      PlayerEntity best = null;
      double bestScore = Double.MAX_VALUE;
      Minecraft var10002 = mc;
      Iterator var4 = mc.world.getEntitiesWithinAABB(PlayerEntity.class, Minecraft.player.getBoundingBox().grow((double)(Float)this.targetRange.get())).iterator();

      while(var4.hasNext()) {
         PlayerEntity player = (PlayerEntity)var4.next();
         Minecraft var10001 = mc;
         if (player != Minecraft.player && player.isAlive() && !player.isSpectator() && !this.isFriend(player) && !this.isBot(player)) {
            Minecraft var10000 = mc;
            double distance = (double)Minecraft.player.getDistance(player);
            if (!(distance > (double)(Float)this.targetRange.get())) {
               double score = distance;
               if (this.canSee(player.getPositionVec().add(0.0, (double)player.getHeight() * 0.5, 0.0))) {
                  score = distance - 2.0;
               }

               score += (double)(player.getHealth() + player.getAbsorptionAmount()) * 0.02;
               if (score < bestScore) {
                  bestScore = score;
                  best = player;
               }
            }
         }
      }

      return best;
   }

   private boolean isBot(PlayerEntity player) {
      AntiBot antiBot = (AntiBot)SkyCore.getInstance().getModuleManager().getModule(AntiBot.class);
      return antiBot != null && antiBot.isEnabled() && AntiBot.bot.contains(player);
   }

   private boolean isFriend(PlayerEntity player) {
      return SkyCore.getInstance().getFriendManager().isFriend(player.getName().getString());
   }

   private boolean hurtsFriend(Vector3d crystalPos) {
      Iterator var2 = this.nearbyFriends.iterator();

      PlayerEntity player;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         player = (PlayerEntity)var2.next();
      } while(!(this.calculateDamage(crystalPos, player) > 3.5));

      return true;
   }

   private boolean isDamageAllowed(Vector3d crystalPos, PlayerEntity target, double targetDamage, double selfDamage) {
      if (targetDamage < this.requiredDamage(target)) {
         return false;
      } else if (!(Boolean)this.antiSelf.get()) {
         return true;
      } else {
         Minecraft var10000 = mc;
         float var9 = Minecraft.player.getHealth();
         Minecraft var10001 = mc;
         double health = (double)(var9 + Minecraft.player.getAbsorptionAmount());
         if (selfDamage >= health - 1.0) {
            return false;
         } else if ((Float)this.maxSelfDamage.get() > 0.0F && selfDamage > (double)(Float)this.maxSelfDamage.get()) {
            return false;
         } else {
            return targetDamage > selfDamage * 0.72 || targetDamage >= (double)(target.getHealth() + target.getAbsorptionAmount());
         }
      }
   }

   private double requiredDamage(PlayerEntity target) {
      return target.getHealth() + target.getAbsorptionAmount() <= (Float)this.facePlaceHealth.get() ? Math.min(2.0, (double)(Float)this.minDamage.get()) : (double)(Float)this.minDamage.get();
   }

   private double scoreDamage(BlockPos base, PlayerEntity target, double targetDamage, double selfDamage) {
      Minecraft var10000 = mc;
      double distance = Minecraft.player.getDistanceSq((double)base.getX() + 0.5, (double)base.getY() + 0.5, (double)base.getZ() + 0.5);
      double score = targetDamage - selfDamage * 0.55 - distance * 0.015;
      if ((double)(base.getY() + 1) < target.getPosY()) {
         score += 0.35;
      }

      if (target.isElytraFlying() && (Boolean)this.elytraSetup.get()) {
         score += 0.5;
      }

      return score;
   }

   private double scorePlaceResult(CrystalPlaceResult result) {
      if (result != null && result.getActionType() != CrystalAura.CrystalActionType.NONE) {
         double multiplier = result.getActionType() == CrystalAura.CrystalActionType.ATTACK ? 0.88 : 1.0;
         return Math.max(0.0, result.getScore() * multiplier);
      } else {
         return 0.0;
      }
   }

   private double calculateDamage(Vector3d pos, LivingEntity target) {
      if (target != null && !target.isInvulnerable()) {
         double distance = target.getPositionVec().distanceTo(pos);
         if (distance > 12.0) {
            return 0.0;
         } else {
            double density = (double)Explosion.getBlockDensity(pos, target);
            double exposure = (1.0 - distance / 12.0) * density;
            double damage = (exposure * exposure + exposure) * 0.5 * 7.0 * 12.0 + 1.0;
            damage = Math.floor(damage);
            damage = (double)CombatRules.getDamageAfterAbsorb((float)damage, (float)target.getTotalArmorValue(), (float)target.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
            damage -= (double)EnchantmentHelper.getEnchantmentModifierDamage(target.getArmorInventoryList(), DamageSource.causeExplosionDamage((LivingEntity)null));
            return Math.max(0.0, damage);
         }
      } else {
         return 0.0;
      }
   }

   private Vector3d predict(PlayerEntity target) {
      Vector3d motion = target.getMotion();
      double ticks = (double)(Float)this.predictTicks.get();
      if (target.isElytraFlying() && (Boolean)this.elytraSetup.get()) {
         ++ticks;
      }

      return target.getPositionVec().add(motion.x * ticks, motion.y * ticks, motion.z * ticks);
   }

   private void rotateTo(Vector3d target) {
      if (target != null && target != Vector3d.ZERO) {
         Minecraft var10000 = mc;
         Vector3d eye = Minecraft.player.getEyePosition(1.0F);
         Vector3d offset = target.subtract(eye);
         float yaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(offset.z, offset.x)) - 90.0);
         float pitch = (float)(-Math.toDegrees(Math.atan2(offset.y, Math.hypot(offset.x, offset.z))));
         RotationComponent.update(new Rotation(yaw, pitch), 180.0F, 1, 8);
      }
   }

   private boolean canReach(Vector3d point, double range, double wall) {
      if (point == null) {
         return false;
      } else {
         Minecraft var10000 = mc;
         double distanceSq = Minecraft.player.getEyePosition(1.0F).squareDistanceTo(point);
         if (distanceSq > range * range) {
            return false;
         } else if ((Boolean)this.throughWalls.get()) {
            return true;
         } else if (this.canSee(point)) {
            return true;
         } else {
            return distanceSq <= wall * wall;
         }
      }
   }

   private boolean canSee(Vector3d point) {
      ClientWorld var10000 = mc.world;
      Minecraft var10003 = mc;
      Vector3d var3 = Minecraft.player.getEyePosition(1.0F);
      Minecraft var10007 = mc;
      RayTraceResult result = var10000.rayTraceBlocks(new RayTraceContext(var3, point, BlockMode.COLLIDER, FluidMode.NONE, Minecraft.player));
      return result == null || result.getType() == Type.MISS || result.getHitVec().squareDistanceTo(point) < 0.7;
   }

   private BlockPlacement findBlockPlacement(BlockPos placePos) {
      if (!this.isReplaceable(placePos)) {
         return null;
      } else {
         Direction[] order = new Direction[]{Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP};
         Direction[] var3 = order;
         int var4 = order.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Direction direction = var3[var5];
            BlockPos support = placePos.offset(direction);
            if (!this.isReplaceable(support)) {
               Direction face = direction.getOpposite();
               Vector3d hit = this.faceHitVec(support, face);
               return new BlockPlacement(placePos, support, face, hit);
            }
         }

         return null;
      }
   }

   private Direction getMiningFace(BlockPos pos) {
      Minecraft var10000 = mc;
      Vector3d eye = Minecraft.player.getEyePosition(1.0F);
      Vector3d center = this.blockCenter(pos);
      Vector3d diff = eye.subtract(center);
      return Direction.getFacingFromVector(diff.x, diff.y, diff.z);
   }

   private boolean isReplaceable(BlockPos pos) {
      BlockState state = mc.world.getBlockState(pos);
      return state.isAir() || state.getMaterial().isReplaceable();
   }

   private boolean canUseItem(Item item) {
      return this.getHandWith(item) != null || (Boolean)this.autoSwitch.get() && InventoryUtil.getItemSlot(item) != -1;
   }

   private Hand getHandWith(Item item) {
      Minecraft var10000 = mc;
      if (Minecraft.player.getHeldItemOffhand().getItem() == item) {
         return Hand.OFF_HAND;
      } else {
         var10000 = mc;
         return Minecraft.player.getHeldItemMainhand().getItem() == item ? Hand.MAIN_HAND : null;
      }
   }

   private SlotSwap switchToItem(Item item) {
      Hand hand = this.getHandWith(item);
      if (hand != null) {
         return new SlotSwap(true, hand, -1, -1);
      } else if (!(Boolean)this.autoSwitch.get()) {
         return CrystalAura.SlotSwap.invalid();
      } else {
         int slot = InventoryUtil.getItemSlot(item);
         if (slot == -1) {
            return CrystalAura.SlotSwap.invalid();
         } else {
            Minecraft var10000 = mc;
            int current = Minecraft.player.inventory.currentItem;
            if (slot >= 36 && slot <= 44) {
               int hotbar = slot - 36;
               this.switchToHotbar(hotbar);
               return new SlotSwap(true, Hand.MAIN_HAND, -1, current);
            } else {
               Minecraft var10005 = mc;
               mc.playerController.windowClick(0, slot, current, ClickType.SWAP, Minecraft.player);
               return new SlotSwap(true, Hand.MAIN_HAND, slot, current);
            }
         }
      }
   }

   private void restoreSwap(SlotSwap swap) {
      if (swap != null && swap.valid) {
         if (swap.inventorySlot != -1) {
            Minecraft var10005 = mc;
            mc.playerController.windowClick(0, swap.inventorySlot, swap.previousHotbar, ClickType.SWAP, Minecraft.player);
         } else {
            if (swap.previousHotbar != -1) {
               Minecraft var10000 = mc;
               if (Minecraft.player.inventory.currentItem != swap.previousHotbar) {
                  this.switchToHotbar(swap.previousHotbar);
               }
            }

         }
      }
   }

   private void switchToHotbar(int hotbar) {
      if (hotbar >= 0 && hotbar <= 8) {
         Minecraft var10000 = mc;
         if (Minecraft.player.inventory.currentItem != hotbar) {
            var10000 = mc;
            Minecraft.player.inventory.currentItem = hotbar;
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(hotbar));
            return;
         }
      }

   }

   private void schedulePendingCrystal(BlockPos base) {
      this.pendingCrystalBase = base;
      this.pendingCrystalPos = this.crystalVec(base);
      this.pendingCrystalId = -1;
      this.pendingTicks = 8;
   }

   private void clearPendingCrystal() {
      this.pendingCrystalBase = null;
      this.pendingCrystalPos = null;
      this.pendingCrystalId = -1;
      this.pendingTicks = 0;
   }

   private void tickCooldowns() {
      if (this.placeCooldown > 0) {
         --this.placeCooldown;
      }

      if (this.breakCooldown > 0) {
         --this.breakCooldown;
      }

      if (this.mineCooldown > 0) {
         --this.mineCooldown;
      }

   }

   private void resetState() {
      this.currentPlaceResult = CrystalAura.CrystalPlaceResult.none();
      this.clearPendingCrystal();
      this.nearbyFriends = Collections.emptyList();
      this.placeCooldown = 0;
      this.breakCooldown = 0;
      this.mineCooldown = 0;
   }

   private AxisAlignedBB crystalBox(BlockPos crystalPos) {
      return new AxisAlignedBB((double)crystalPos.getX(), (double)crystalPos.getY(), (double)crystalPos.getZ(), (double)crystalPos.getX() + 1.0, (double)crystalPos.getY() + 2.0, (double)crystalPos.getZ() + 1.0);
   }

   private Vector3d crystalVec(BlockPos base) {
      return new Vector3d((double)base.getX() + 0.5, (double)base.getY() + 1.0, (double)base.getZ() + 0.5);
   }

   private Vector3d blockCenter(BlockPos pos) {
      return new Vector3d((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
   }

   private Vector3d faceHitVec(BlockPos pos, Direction face) {
      return this.blockCenter(pos).add((double)face.getXOffset() * 0.5, (double)face.getYOffset() * 0.5, (double)face.getZOffset() * 0.5);
   }

   private AxisAlignedBB getRenderBox(CrystalPlaceResult result, float partialTicks) {
      if (result.getEntity() instanceof EnderCrystalEntity && result.getEntity().isAlive()) {
         Entity entity = result.getEntity();
         double x = MathHelper.lerp((double)partialTicks, entity.lastTickPosX, entity.getPosX());
         double y = MathHelper.lerp((double)partialTicks, entity.lastTickPosY, entity.getPosY());
         double z = MathHelper.lerp((double)partialTicks, entity.lastTickPosZ, entity.getPosZ());
         return new AxisAlignedBB(x - 0.5, y, z - 0.5, x + 0.5, y + 1.0, z + 0.5);
      } else {
         return result.getBlockPos() != null ? new AxisAlignedBB(result.getBlockPos()) : null;
      }
   }

   private int getActionColor(CrystalActionType actionType) {
      switch (actionType) {
         case BREAK:
            return 16724016;
         case PLACE_AND_BREAK:
            return 4915062;
         case PLACE:
            return 6979839;
         case ATTACK:
            return 16756782;
         default:
            return 16777215;
      }
   }

   private void drawBox(AxisAlignedBB box, int color, float alpha) {
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      GL11.glColor4f(r, g, b, alpha);
      GL11.glBegin(7);
      GL11.glVertex3d(box.minX, box.minY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.maxZ);
      GL11.glVertex3d(box.minX, box.maxY, box.minZ);
      GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
      GL11.glVertex3d(box.minX, box.minY, box.minZ);
      GL11.glVertex3d(box.minX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.minZ);
      GL11.glVertex3d(box.minX, box.maxY, box.minZ);
      GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.minZ);
      GL11.glEnd();
   }

   private void drawOutline(AxisAlignedBB box, int color, float width) {
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      GL11.glColor4f(r, g, b, 1.0F);
      GL11.glLineWidth(width);
      GL11.glBegin(1);
      GL11.glVertex3d(box.minX, box.minY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.minZ);
      GL11.glVertex3d(box.minX, box.minY, box.minZ);
      GL11.glVertex3d(box.minX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.minZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.minY, box.maxZ);
      GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
      GL11.glVertex3d(box.minX, box.maxY, box.minZ);
      GL11.glEnd();
   }

   private static final class CrystalPlaceResult {
      private final BlockPos blockPos;
      private final Direction direction;
      private final double damage;
      private final double selfDamage;
      private final double score;
      private final CrystalActionType actionType;
      private final Entity entity;
      private final Vector3d aimVec;
      private final BlockPlacement placement;
      private final boolean hardStop;

      private CrystalPlaceResult(BlockPos blockPos, Direction direction, double damage, double selfDamage, double score, CrystalActionType actionType, Entity entity, Vector3d aimVec, BlockPlacement placement, boolean hardStop) {
         this.blockPos = blockPos;
         this.direction = direction;
         this.damage = damage;
         this.selfDamage = selfDamage;
         this.score = score;
         this.actionType = actionType;
         this.entity = entity;
         this.aimVec = aimVec;
         this.placement = placement;
         this.hardStop = hardStop;
      }

      private static CrystalPlaceResult none() {
         return new CrystalPlaceResult((BlockPos)null, (Direction)null, 0.0, 0.0, 0.0, CrystalAura.CrystalActionType.NONE, (Entity)null, Vector3d.ZERO, (BlockPlacement)null, false);
      }

      private BlockPos getBlockPos() {
         return this.blockPos;
      }

      private Direction getDirection() {
         return this.direction;
      }

      private double getDamage() {
         return this.damage;
      }

      private double getSelfDamage() {
         return this.selfDamage;
      }

      private double getScore() {
         return this.score;
      }

      private CrystalActionType getActionType() {
         return this.actionType;
      }

      private Entity getEntity() {
         return this.entity;
      }

      private Vector3d getAimVec() {
         return this.aimVec;
      }

      private BlockPlacement getPlacement() {
         return this.placement;
      }

      private boolean isHardStop() {
         return this.hardStop;
      }
   }

   private static enum CrystalActionType {
      PLACE,
      BREAK,
      PLACE_AND_BREAK,
      ATTACK,
      NONE;

      // $FF: synthetic method
      private static CrystalActionType[] $values() {
         return new CrystalActionType[]{PLACE, BREAK, PLACE_AND_BREAK, ATTACK, NONE};
      }
   }

   private static final class BlockPlacement {
      private final BlockPos placePos;
      private final BlockPos supportPos;
      private final Direction face;
      private final Vector3d hitVec;

      private BlockPlacement(BlockPos placePos, BlockPos supportPos, Direction face, Vector3d hitVec) {
         this.placePos = placePos;
         this.supportPos = supportPos;
         this.face = face;
         this.hitVec = hitVec;
      }
   }

   private static enum PlaceScanResult {
      VALID,
      BLOCKED,
      OUT_OF_RANGE;

      // $FF: synthetic method
      private static PlaceScanResult[] $values() {
         return new PlaceScanResult[]{VALID, BLOCKED, OUT_OF_RANGE};
      }
   }

   private static final class SlotSwap {
      private final boolean valid;
      private final Hand hand;
      private final int inventorySlot;
      private final int previousHotbar;

      private SlotSwap(boolean valid, Hand hand, int inventorySlot, int previousHotbar) {
         this.valid = valid;
         this.hand = hand;
         this.inventorySlot = inventorySlot;
         this.previousHotbar = previousHotbar;
      }

      private static SlotSwap invalid() {
         return new SlotSwap(false, Hand.MAIN_HAND, -1, -1);
      }
   }
}
