package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket.Action;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.events.EventMotion;
import sky.core.events.EventPacket;
import sky.core.events.EventPostUpdate;
import sky.core.events.EventRender3D;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.component.impl.SensUtil;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class AimBot extends Module {
   private final MultiBooleanSetting targetTypes = new MultiBooleanSetting("Типы целей", new BooleanSetting[]{new BooleanSetting("Игроки", true), new BooleanSetting("В броне", true), new BooleanSetting("Без брони", false), new BooleanSetting("Мобы", false), new BooleanSetting("Зомби", false)});
   private final MultiBooleanSetting items = new MultiBooleanSetting("Предметы", new BooleanSetting[]{new BooleanSetting("Лук", true), new BooleanSetting("Арбалет", true), new BooleanSetting("Трезубец", true)});
   private final SliderSetting range = new SliderSetting("Дистанция", 40.0F, 10.0F, 100.0F, 1.0F);
   private final SliderSetting aimTime = new SliderSetting("Время наводки (тики)", 10.0F, 0.0F, 40.0F, 1.0F);
   private final BooleanSetting silentRotations = new BooleanSetting("Тихие повороты", true);
   private final BooleanSetting showCrosshair = new BooleanSetting("Показать прицел", true);
   private final SliderSetting crosshairSize = new SliderSetting("Размер прицела", 1.0F, 0.3F, 3.0F, 0.1F, () -> {
      return (Boolean)this.showCrosshair.get();
   });
   private LivingEntity target;
   private boolean aiming;
   private float aimProgress;
   private Rotation targetRotation;
   private int lastUseTicks;
   private boolean sendingDeferredRelease;
   private int deferredReleaseTicks;
   private CPlayerDiggingPacket deferredReleasePacket;
   private float deferredYaw;
   private float deferredPitch;

   public AimBot() {
      super("Projectile Helper", "Авто-наведение для лука и арбалета", Category.Combat);
      this.addSettings(new Setting[]{this.targetTypes, this.items, this.range, this.aimTime, this.silentRotations, this.showCrosshair, this.crosshairSize});
   }

   public LivingEntity getTarget() {
      return this.target;
   }

   public boolean isAiming() {
      return this.aiming;
   }

   private boolean isHoldingProjectile() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         var10000 = mc;
         ItemStack main = Minecraft.player.getHeldItemMainhand();
         var10000 = mc;
         ItemStack off = Minecraft.player.getHeldItemOffhand();
         return this.isProjectileItem(main) || this.isProjectileItem(off);
      }
   }

   private boolean isProjectileItem(ItemStack stack) {
      if (stack.isEmpty()) {
         return false;
      } else if (this.items.is("Лук") && stack.getItem() instanceof BowItem) {
         return true;
      } else if (this.items.is("Арбалет") && stack.getItem() instanceof CrossbowItem) {
         return true;
      } else {
         return this.items.is("Трезубец") && stack.getItem() instanceof TridentItem;
      }
   }

   private boolean isUsingProjectile() {
      Minecraft var10000 = mc;
      boolean var1;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.isHandActive() && this.isHoldingProjectile()) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   private boolean isBot(LivingEntity entity) {
      AntiBot antiBot = (AntiBot)SkyCore.getInstance().getModuleManager().getModule(AntiBot.class);
      return antiBot != null && antiBot.isEnabled() && AntiBot.bot.contains(entity);
   }

   private boolean isFriendPlayer(PlayerEntity player) {
      if (player != null && SkyCore.getInstance().getFriendManager() != null) {
         if (player.getGameProfile() != null) {
            String profileName = player.getGameProfile().getName();
            if (profileName != null && SkyCore.getInstance().getFriendManager().isFriend(profileName)) {
               return true;
            }
         }

         return SkyCore.getInstance().getFriendManager().isFriend(player.getName().getString());
      } else {
         return false;
      }
   }

   private boolean isValidTarget(LivingEntity entity) {
      if (entity != null) {
         Minecraft var10001 = mc;
         if (entity != Minecraft.player) {
            if (entity.isAlive() && !(entity.getHealth() <= 0.0F)) {
               if (this.isBot(entity)) {
                  return false;
               }

               if (!(entity instanceof PlayerEntity)) {
                  if (entity instanceof ZombieEntity) {
                     return this.targetTypes.is("Зомби");
                  }

                  if (entity instanceof MonsterEntity) {
                     return this.targetTypes.is("Мобы");
                  }

                  return false;
               }

               PlayerEntity player = (PlayerEntity)entity;
               if (!this.targetTypes.is("Игроки")) {
                  return false;
               }

               if (this.isFriendPlayer(player)) {
                  return false;
               }

               boolean hasArmor = false;
               Iterator var4 = player.getArmorInventoryList().iterator();

               while(var4.hasNext()) {
                  ItemStack armor = (ItemStack)var4.next();
                  if (!armor.isEmpty()) {
                     hasArmor = true;
                     break;
                  }
               }

               if (this.targetTypes.is("В броне") && hasArmor) {
                  return true;
               }

               if (this.targetTypes.is("Без брони") && !hasArmor) {
                  return true;
               }

               return !this.targetTypes.is("В броне") && !this.targetTypes.is("Без брони");
            }

            return false;
         }
      }

      return false;
   }

   private LivingEntity findBestTarget() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         float maxRange = (Float)this.range.get();
         var10000 = mc;
         AxisAlignedBB box = Minecraft.player.getBoundingBox().grow((double)maxRange);
         List targets = new ArrayList();
         Iterator var4 = mc.world.getEntitiesWithinAABB(LivingEntity.class, box, (e) -> {
            return true;
         }).iterator();

         while(var4.hasNext()) {
            LivingEntity entity = (LivingEntity)var4.next();
            if (this.isValidTarget(entity)) {
               var10000 = mc;
               if (!(Minecraft.player.getDistanceSq(entity) > (double)(maxRange * maxRange))) {
                  var10000 = mc;
                  if (Minecraft.player.canEntityBeSeen(entity)) {
                     targets.add(entity);
                  }
               }
            }
         }

         if (targets.isEmpty()) {
            return null;
         } else {
            Minecraft var10001 = mc;
            ClientPlayerEntity var6 = Minecraft.player;
            Objects.requireNonNull(var6);
            targets.sort(Comparator.comparingDouble(var6::getDistance));
            return (LivingEntity)targets.get(0);
         }
      } else {
         return null;
      }
   }

   private Rotation calculateRotation(LivingEntity aimTarget) {
      Minecraft var10000 = mc;
      ItemStack active = Minecraft.player.getActiveItemStack();
      if (!active.isEmpty() && active.getItem() instanceof TridentItem && this.items.is("Трезубец")) {
         Vector2f rot = this.calculateTridentRotation(aimTarget);
         return new Rotation(rot.x, rot.y);
      } else {
         var10000 = mc;
         Vector3d eyes = Minecraft.player.getEyePosition(1.0F);
         Vector3d targetPos = aimTarget.getBoundingBox().getCenter();
         double dx = targetPos.x - eyes.x;
         double dy = targetPos.y - eyes.y;
         double dz = targetPos.z - eyes.z;
         double dist = Math.sqrt(dx * dx + dz * dz);
         float yaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
         float pitch = (float)(-Math.toDegrees(Math.atan2(dy, dist)));
         return new Rotation(yaw, pitch);
      }
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if (!Minecraft.player.isElytraFlying()) {
            var10000 = mc;
            boolean offHandBow = Minecraft.player.getHeldItemOffhand().getItem() instanceof BowItem && this.items.is("Лук");
            var10000 = mc;
            boolean mainHandBow = Minecraft.player.getHeldItemMainhand().getItem() instanceof BowItem && this.items.is("Лук");
            var10000 = mc;
            boolean offHandTrident = Minecraft.player.getHeldItemOffhand().getItem() instanceof TridentItem && this.items.is("Трезубец");
            var10000 = mc;
            boolean mainHandTrident = Minecraft.player.getHeldItemMainhand().getItem() instanceof TridentItem && this.items.is("Трезубец");
            if (offHandBow || mainHandBow || offHandTrident || mainHandTrident) {
               var10000 = mc;
               if (Minecraft.player.isHandActive()) {
                  Minecraft var10001 = mc;
                  this.lastUseTicks = Minecraft.player.getItemInUseMaxCount();
               }
            }

            this.aiming = this.isUsingProjectile();
            if (this.aiming) {
               LivingEntity newTarget = this.findBestTarget();
               if (newTarget != null) {
                  if (this.target != newTarget) {
                     this.target = newTarget;
                     this.aimProgress = 0.0F;
                  }

                  Rotation newRotation = this.calculateRotation(this.target);
                  float maxStep = 1.0F / Math.max(1.0F, (Float)this.aimTime.get());
                  this.aimProgress = Math.min(this.aimProgress + maxStep, 1.0F);
                  var10000 = mc;
                  float currentYaw = Minecraft.player.rotationYaw;
                  var10000 = mc;
                  float currentPitch = Minecraft.player.rotationPitch;
                  float yawDiff = MathHelper.wrapDegrees(newRotation.getYaw() - currentYaw);
                  float pitchDiff = newRotation.getPitch() - currentPitch;
                  float stepYaw = yawDiff * this.aimProgress;
                  float stepPitch = pitchDiff * this.aimProgress;
                  this.targetRotation = new Rotation(currentYaw + stepYaw, currentPitch + stepPitch);
               }
            } else {
               this.target = null;
               this.targetRotation = null;
               this.aimProgress = 0.0F;
            }

            if (this.target != null && this.aiming && this.targetRotation != null) {
               if ((Boolean)this.silentRotations.get()) {
                  float var17 = this.targetRotation.getYaw();
                  Minecraft var10002 = mc;
                  float yaw = this.applyGcd(var17, Minecraft.player.rotationYaw);
                  var17 = this.targetRotation.getPitch();
                  var10002 = mc;
                  float pitch = this.applyGcd(var17, Minecraft.player.rotationPitch);
                  RotationComponent.update(new Rotation(yaw, pitch), 180.0F, 180.0F, 45.0F, 45.0F, 0, 2, false);
               } else {
                  var10000 = mc;
                  Minecraft.player.rotationYaw = this.targetRotation.getYaw();
                  var10000 = mc;
                  Minecraft.player.rotationPitch = this.targetRotation.getPitch();
               }
            }

            return;
         }
      }

      this.aiming = false;
      this.target = null;
   }

   @EventTarget
   public void onRender3D(EventRender3D event) {
      if (this.isEnabled() && (Boolean)this.showCrosshair.get() && this.target != null && this.aiming) {
         Vector3d targetPos = MathUtil.interpolate(this.target, event.getPartialTicks()).add(0.0, (double)this.target.getHeight() * 0.5, 0.0);
         Vector3d cameraPos = mc.gameRenderer.getActiveRenderInfo().getProjectedView();
         MatrixStack matrices = event.getMatrixStack();
         double renderX = targetPos.x - cameraPos.x;
         double renderY = targetPos.y - cameraPos.y;
         double renderZ = targetPos.z - cameraPos.z;
         ActiveRenderInfo camera = mc.gameRenderer.getActiveRenderInfo();
         float size = (Float)this.crosshairSize.get() * 0.5F;
         int alpha = (int)(255.0F * this.aimProgress);
         int color = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), (float)alpha / 255.0F);
         matrices.push();
         matrices.translate(renderX, renderY, renderZ);
         matrices.rotate(camera.getRotation().copy());
         RenderSystem.pushMatrix();
         RenderSystem.multMatrix(matrices.getLast().getMatrix());
         RenderSystem.disableDepthTest();
         RenderSystem.disableAlphaTest();
         RenderSystem.disableCull();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderUtil.drawImage3D(new ResourceLocation("minecraft", "SkyCore/icons/world_render/target.png"), -size / 2.0F, -size / 2.0F, 0.0F, size, size, color, true);
         RenderSystem.enableCull();
         RenderSystem.enableAlphaTest();
         RenderSystem.enableDepthTest();
         RenderSystem.popMatrix();
         matrices.pop();
      }
   }

   @EventTarget
   public void onSync(EventMotion event) {
      if (this.deferredReleasePacket != null) {
         event.setYaw(this.deferredYaw);
         event.setPitch(this.deferredPitch);
      }
   }

   @EventTarget
   public void onTick(EventPostUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.connection != null && this.deferredReleasePacket != null) {
            if (this.deferredReleaseTicks > 0) {
               --this.deferredReleaseTicks;
            }

            if (this.deferredReleaseTicks <= 0) {
               try {
                  this.sendingDeferredRelease = true;
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(this.deferredReleasePacket);
               } finally {
                  this.sendingDeferredRelease = false;
               }

               this.deferredReleasePacket = null;
            }

            return;
         }
      }

   }

   @EventTarget
   public void onSendPacket(EventPacket event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.connection != null && !this.sendingDeferredRelease && this.deferredReleasePacket == null) {
            IPacket var3 = event.getPacket();
            if (var3 instanceof CPlayerDiggingPacket) {
               CPlayerDiggingPacket diggingPacket = (CPlayerDiggingPacket)var3;
               if (diggingPacket.getAction() != Action.RELEASE_USE_ITEM) {
                  return;
               }

               if (!this.isHoldingProjectile()) {
                  return;
               }

               LivingEntity releaseTarget = this.findBestTarget();
               if (releaseTarget == null) {
                  return;
               }

               this.target = releaseTarget;
               Rotation rotation = this.calculateRotation(releaseTarget);
               this.deferredYaw = rotation.getYaw();
               this.deferredPitch = rotation.getPitch();
               RotationComponent.update(rotation, 360.0F, 360.0F, 360.0F, 360.0F, 1, 10, false);
               event.setCancelled(true);
               this.deferredReleasePacket = diggingPacket;
               this.deferredReleaseTicks = 1;
               return;
            }

            return;
         }
      }

   }

   private float applyGcd(float targetAngle, float currentAngle) {
      float delta = targetAngle - currentAngle;
      float gcd = SensUtil.getGCDValue();
      return currentAngle + delta - delta % gcd;
   }

   private Vector2f calculateTridentRotation(LivingEntity aimTarget) {
      Minecraft var10000 = mc;
      int var12;
      if (Minecraft.player.isHandActive()) {
         var10000 = mc;
         var12 = Minecraft.player.getItemInUseMaxCount();
      } else {
         var12 = this.lastUseTicks;
      }

      int useTicks = var12;
      float currentDuration = (float)useTicks / 20.0F;
      currentDuration = (currentDuration * currentDuration + currentDuration * 2.0F) / 3.0F;
      if (currentDuration >= 1.0F) {
         currentDuration = 1.0F;
      }

      float pitch = (float)(-Math.toDegrees((double)this.calculateArc(aimTarget, (double)(currentDuration * 3.0F))));
      double iX = aimTarget.getPosX() - aimTarget.prevPosX;
      double iZ = aimTarget.getPosZ() - aimTarget.prevPosZ;
      var10000 = mc;
      double distance = (double)Minecraft.player.getDistance(aimTarget);
      distance -= distance % 2.0;
      double var13 = distance / 2.0 * iX;
      Minecraft var10001 = mc;
      iX = var13 * (Minecraft.player.isSprinting() ? 1.3 : 1.1);
      var13 = distance / 2.0 * iZ;
      var10001 = mc;
      iZ = var13 * (Minecraft.player.isSprinting() ? 1.3 : 1.1);
      var13 = aimTarget.getPosZ() + iZ;
      var10001 = mc;
      var13 -= Minecraft.player.getPosZ();
      double var14 = aimTarget.getPosX() + iX;
      Minecraft var10002 = mc;
      float yaw = (float)Math.toDegrees(Math.atan2(var13, var14 - Minecraft.player.getPosX())) - 90.0F;
      return new Vector2f(yaw, pitch);
   }

   private float calculateArc(LivingEntity aimTarget, double duration) {
      double var10000 = aimTarget.getPosY() + (double)aimTarget.getEyeHeight();
      Minecraft var10001 = mc;
      double var12 = Minecraft.player.getPosY();
      Minecraft var10002 = mc;
      double yArc = var10000 - (var12 + (double)Minecraft.player.getEyeHeight());
      var10000 = aimTarget.getPosX();
      var10001 = mc;
      double dX = var10000 - Minecraft.player.getPosX();
      var10000 = aimTarget.getPosZ();
      var10001 = mc;
      double dZ = var10000 - Minecraft.player.getPosZ();
      double dirRoot = Math.sqrt(dX * dX + dZ * dZ);
      return this.calculateArc(duration, dirRoot, yArc);
   }

   private float calculateArc(double d, double dr, double y) {
      y = 2.0 * y * d * d;
      y = 0.05 * (0.05 * dr * dr + y);
      y = Math.sqrt(d * d * d * d - y);
      d = d * d - y;
      y = Math.atan2(d * d + y, 0.05 * dr);
      d = Math.atan2(d, 0.05 * dr);
      return (float)Math.min(y, d);
   }

   public void onEnable() {
      super.onEnable();
      this.target = null;
      this.aiming = false;
      this.aimProgress = 0.0F;
      this.targetRotation = null;
      this.deferredReleasePacket = null;
      this.deferredReleaseTicks = 0;
   }

   public void onDisable() {
      super.onDisable();
      this.target = null;
      this.aiming = false;
      this.aimProgress = 0.0F;
      this.targetRotation = null;
      this.deferredReleasePacket = null;
      this.deferredReleaseTicks = 0;
   }
}
