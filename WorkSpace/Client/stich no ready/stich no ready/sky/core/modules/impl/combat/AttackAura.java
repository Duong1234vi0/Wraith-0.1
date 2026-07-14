package sky.core.modules.impl.combat;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.network.play.client.CEntityActionPacket.Action;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.events.EventAttack;
import sky.core.events.EventGameUpdate;
import sky.core.events.EventInput;
import sky.core.events.EventInteract;
import sky.core.events.EventPacket;
import sky.core.events.EventPostUpdate;
import sky.core.events.EventSwapWorld;
import sky.core.events.EventUpdate;
import sky.core.events.EventWillLand;
import sky.core.handlers.impl.LookHandler;
import sky.core.handlers.impl.Rotation;
import sky.core.handlers.impl.TPSHandler;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.miscellaneous.TargetPearl;
import sky.core.modules.impl.movement.AirStuck;
import sky.core.modules.impl.movement.ElytraTarget;
import sky.core.modules.impl.movement.Speed;
import sky.core.modules.impl.movement.Sprint;
import sky.core.utils.component.RotationAccess;
import sky.core.utils.component.impl.ElytraComponent;
import sky.core.utils.component.impl.MoveComponent;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.component.impl.RotationUtil;
import sky.core.utils.component.impl.SmoothRotationComponent;
import sky.core.utils.math.AuraUtil;
import sky.core.utils.math.NewPredictRotations;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.player.MoveUtil;
import sky.core.utils.player.PerfectDelay;
import sky.core.utils.player.PlayerUtil;
import sky.core.utils.player.RayTraceUtil;

public class AttackAura extends Module {
   private static float lonyJirSpeed;
   public static LivingEntity target = null;
   public static Vector2f rotate = new Vector2f(0.0F, 0.0F);
   private static boolean visualReverseYaw;
   private boolean visualReverseWasActive;
   public final ModeSetting componentMode = new ModeSetting("Режим ротации", "Reallyworld", new String[]{"Reallyworld", "Reallyworld2", "LonyGrief", "LonyGrief#2", "Grim", "GrimMatrix", "Sloth test", "FunTime", "Snap", "Spooky", "HolyWorld", "Jitter"});
   public final ModeSetting smoothAimType = new ModeSetting("Вид наводки", "Плавный", () -> {
      return this.componentMode.is("Reallyworld") || this.componentMode.is("Reallyworld2") || this.componentMode.is("HolyWorld");
   }, new String[]{"Плавный", "Резкий"});
   public final MultiBooleanSetting targets = new MultiBooleanSetting("Цели", new BooleanSetting[]{new BooleanSetting("Игроки", true), new BooleanSetting("Друзья", false), new BooleanSetting("Голые", true), new BooleanSetting("Животные", false), new BooleanSetting("Мобы", false)});
   public final SliderSetting attackRange = new SliderSetting("Радиус атаки", 3.0F, 2.5F, 6.0F, 0.1F);
   public final SliderSetting lerpRotationDistance = new SliderSetting("Радиус преследования", 1.5F, 0.0F, 3.0F, 0.1F);
   public final BooleanSetting disableOnDeath = new BooleanSetting("Выключить после смерти", true);
   public final BooleanSetting ignoreEat = new BooleanSetting("Не бить когда ешь", false);
   public final BooleanSetting onlyWeapon = new BooleanSetting("Бить только с оружием", false);
   public final BooleanSetting tpsSync = new BooleanSetting("TPSSync", false);
   public final BooleanSetting jitterEnabled = new BooleanSetting("Джиттер", false, () -> {
      return this.componentMode.is("Jitter") || this.componentMode.is("Reallyworld");
   });
   public final SliderSetting jitterStrength = new SliderSetting("Сила джиттера", 2.0F, 0.5F, 8.0F, 0.5F, () -> {
      return (Boolean)this.jitterEnabled.get() || this.componentMode.is("Jitter") || this.componentMode.is("Reallyworld");
   });
   private BooleanSetting onlycrit = new BooleanSetting("Бить только критами", true);
   private BooleanSetting bypassWalls = new BooleanSetting("Обход стен RW", false);
   public final BooleanSetting ray = new BooleanSetting("Проверка наведения", false, () -> {
      return this.componentMode.is("Reallyworld") || this.componentMode.is("Reallyworld2");
   });
   public final ModeSetting correctionType = new ModeSetting("Коррекция движения", "Свободная", new String[]{"Свободная", "Сфокусированная", "Таргет"});
   private final BooleanSetting throughWalls = new BooleanSetting("Сквозь стены", true);
   private int tickssnap;
   private int tickssnapMatrix;
   private final BooleanSetting SHEIS = new BooleanSetting("Отжимать Щит ", true);
   public final BooleanSetting setPitch = new BooleanSetting("Поворачивать pitch", false);
   public final BooleanSetting onlySpaceCritical = new BooleanSetting("Умные криты", false);
   private long lastTickTime2 = 0L;
   public static double bpsTarget = 0.0;
   public boolean canCrit;
   PerfectDelay perfectDelay = new PerfectDelay();
   TimeUtil stopWatch = new TimeUtil();
   int rayTicks = 0;
   private Vector2f lerpRotation;
   private Vector2f lerpRot;
   private Vector2f lerprRot;
   private int count;
   private int counter;
   public float yawDelta;
   public double lastSpeed;
   public static float acceleration;
   public static boolean isBack;
   public static float lastYaw;
   public static float lastPitch;
   private boolean lonyReverseSmooth;
   private float lonySmooth;
   private float lonyLastYaw;
   private float lonyLastPitch;
   private LivingEntity lonyLastTarget;
   public static float randomOffsetX;
   public static float randomOffsetY;
   public static float randomOffsetZ;
   private boolean lony2ReverseSmooth;
   private float lony2Smooth;
   private float lony2LastYaw;
   private float lony2LastPitch;
   private LivingEntity lony2LastTarget;
   private float funTimeYaw;
   private float funTimePitch;
   private float funTimeSpeed;
   private float spookyLastYaw;
   private float spookyLastPitch;
   private float spookyClampedYaw;
   private float spookyClampedPitch;
   private LivingEntity spookyLastTarget;
   private boolean holyDecelerating;
   private float holyProgress;
   private float holyLastYaw;
   private float holyLastPitch;
   private LivingEntity holyLastTarget;
   private float jitterOffsetYaw;
   private float jitterOffsetPitch;
   private long jitterLastSwitch;
   private boolean jitterPositive;
   private long cps;
   private int legitSprintResetTicks;
   public double prevSpeed;
   private Vector3d defensivePos;
   private final TimeUtil defensiveTimer;
   private Vector3d leaveVec;
   private Vector3d lastVec;
   private final NewPredictRotations newPredictRotations;
   private float lastSpookyYaw;
   private float lastSpookyPitch;
   private LivingEntity selected;
   private static final TimeUtil tickStopWatch = new TimeUtil();
   private boolean slothReverseSmooth;
   private float slothSmooth;
   private float slothLastYaw;
   private float slothLastPitch;
   private LivingEntity slothLastTarget;
   private boolean slothInitialized;
   private boolean needsReset;
   private float resetYaw;
   private float resetPitch;
   private int resetDelay;
   final Map previousPositions;
   private float hitShakeIntensity;
   private float shakeIncreaseSpeed;
   private float shakeDecreaseSpeed;
   private float deltaTime;
   private boolean isHitting;

   public AttackAura() {
      super("Attack Aura", "Бьет маму ирибы берцами", Category.Combat);
      this.lerpRotation = Vector2f.ZERO;
      this.lerpRot = Vector2f.ZERO;
      this.lerprRot = Vector2f.ZERO;
      this.lastSpeed = 0.0;
      this.cps = 0L;
      this.prevSpeed = 0.0;
      this.defensiveTimer = new TimeUtil();
      this.leaveVec = Vector3d.ZERO;
      this.lastVec = Vector3d.ZERO;
      this.newPredictRotations = new NewPredictRotations();
      this.lastSpookyYaw = 0.0F;
      this.lastSpookyPitch = 0.0F;
      this.selected = null;
      this.slothReverseSmooth = false;
      this.slothSmooth = 0.0F;
      this.slothLastYaw = 0.0F;
      this.slothLastPitch = 0.0F;
      this.slothLastTarget = null;
      this.slothInitialized = false;
      this.needsReset = false;
      this.resetDelay = 0;
      this.previousPositions = new HashMap();
      this.hitShakeIntensity = 0.0F;
      this.shakeIncreaseSpeed = 0.15F;
      this.shakeDecreaseSpeed = 0.1F;
      this.deltaTime = 0.05F;
      this.isHitting = false;
      this.addSettings(new Setting[]{this.componentMode, this.smoothAimType, this.targets, this.correctionType, this.attackRange, this.lerpRotationDistance, this.disableOnDeath, this.ignoreEat, this.onlyWeapon, this.tpsSync, this.onlycrit, this.onlySpaceCritical, this.SHEIS, this.bypassWalls, this.ray, this.jitterEnabled, this.jitterStrength});
   }

   @EventTarget
   public void onEvent(EventSwapWorld event) {
      this.reset();
   }

   public double getEntitySpeed(Entity entity) {
      Vector3d currentPos = entity.getPositionVec();
      Vector3d previousPos = (Vector3d)this.previousPositions.getOrDefault(entity, currentPos);
      double dx = currentPos.x - previousPos.x;
      double dz = currentPos.z - previousPos.z;
      double speed = Math.sqrt(dx * dx + dz * dz) * 20.0;
      this.previousPositions.put(entity, currentPos);
      return speed;
   }

   @EventTarget
   public void onEvent(EventInput event) {
      if (!TargetPearl.isRotationActive()) {
         if ((RotationComponent.getInstance().isRotating() || SmoothRotationComponent.getInstance().isRotating()) && target != null) {
            if (this.correctionType.is("Свободная")) {
               MoveUtil.fixMovement(event, LookHandler.getFreeYaw());
            } else {
               Vector3d targetVector;
               if (this.correctionType.is("Таргет")) {
                  targetVector = target.getPositionVec();
                  if (SkyCore.getInstance().getModuleManager().getModule(Speed.class).isEnabled() && this.getEntitySpeed(target) > 5.0) {
                     targetVector = target.getPositionVec().add(target.getForward().normalize().scale(2.0));
                  }

                  if (!MoveComponent.isStop()) {
                     Minecraft var10002 = mc;
                     MoveUtil.moveToPosition(event, targetVector, Minecraft.player.rotationYaw);
                  }
               } else {
                  targetVector = AuraUtil.getClosestVec(target);
                  float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(targetVector.z, targetVector.x)) - 90.0);
                  MoveUtil.fixMovement(event, targetYaw);
               }
            }
         }

         if (this.legitSprintResetTicks > 0) {
            event.setForward(0.0F);
            event.setStrafe(0.0F);
            --this.legitSprintResetTicks;
         }

      }
   }

   @EventTarget
   public void onEvent(EventInteract event) {
      if (target != null) {
         event.setCancelled(true);
      }

   }

   @EventTarget
   private void attack(EventAttack e) {
      if (target != null) {
         ElytraComponent.attack(e);
      }
   }

   @EventTarget
   public void onEvent(EventWillLand event) {
      if (target == null) {
         this.canCrit = false;
      } else if (SkyCore.getInstance().getModuleManager().getModule(AirStuck.class).isEnabled()) {
         this.canCrit = true;
      } else {
         Minecraft var10001 = mc;
         this.canCrit = !(Minecraft.player.fallDistance > 0.1F) || !event.isWillLand();
      }
   }

   @EventTarget
   public void onEvent(EventUpdate e) {
      Minecraft var10000;
      if ((Boolean)this.disableOnDeath.get()) {
         var10000 = mc;
         if (!Minecraft.player.isAlive()) {
            this.performInvisibleReset();
            this.toggle();
            return;
         }
      }

      if (target == null || !this.isValidTarget(target)) {
         target = this.findTarget();
         if (target == null) {
            this.performInvisibleReset();
         }
      }

      if (target != null) {
         var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            if (target != null) {
               RotationAccess.updateTarget(target);
               ElytraComponent.smartPredict();
               ElytraComponent.processTargetLogic(SkyCore.getInstance().getModuleManager().getElytraTarget());
            }

            ElytraComponent.updateDefensiveState(target);
            if (SkyCore.getInstance().getModuleManager().getModule(AirStuck.class).isEnabled()) {
               this.canCrit = true;
            }

            if (!SkyCore.getInstance().getModuleManager().getModule(PacketCriticals.class).isEnabled() && target != null && this.canAttack() && (Boolean)this.onlycrit.get() && this.cps <= System.currentTimeMillis()) {
               this.updateAttack();
               this.cps = System.currentTimeMillis() + 460L;
            }

            boolean isElytraFlying;
            if ((this.componentMode.is("Reallyworld") || this.componentMode.is("Reallyworld2")) && (Boolean)this.ray.get() && target != null) {
               label72: {
                  var10000 = mc;
                  isElytraFlying = Minecraft.player.isElytraFlying();
                  if (!isElytraFlying) {
                     var10000 = mc;
                     Minecraft var10001 = mc;
                     if (RayTraceUtil.rayTraceSingleEntity(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch, this.attackDistance(), target)) {
                        ++this.rayTicks;
                        break label72;
                     }
                  }

                  if (!isElytraFlying) {
                     this.rayTicks = 0;
                  }
               }
            } else {
               this.rayTicks = 0;
            }

            if (this.shouldBlockAttack()) {
               return;
            }

            if (!this.componentMode.is("Reallyworld") && !this.componentMode.is("Reallyworld2")) {
               this.updateAttack();
            } else if ((Boolean)this.ray.get()) {
               var10000 = mc;
               isElytraFlying = Minecraft.player.isElytraFlying();
               if (isElytraFlying) {
                  this.updateAttack();
               } else if (this.rayTicks > 1) {
                  this.updateAttack();
               }
            } else {
               this.updateAttack();
            }

            this.updateVisualReverseYawState();
            this.applyInvisibleReset();
            return;
         }
      }

      this.performInvisibleReset();
      this.reset();
   }

   private void performInvisibleReset() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         this.needsReset = true;
         Minecraft var10001 = mc;
         this.resetYaw = Minecraft.player.rotationYaw;
         this.resetPitch = 0.0F;
         this.resetDelay = 1;
      }
   }

   private void applyInvisibleReset() {
      if (this.needsReset) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (this.resetDelay > 0) {
               --this.resetDelay;
               return;
            }

            RotationComponent.update(new Rotation(this.resetYaw, this.resetPitch), 0.0F, 0.0F, 0.0F, 0.0F, 0, 0, true);
            this.lerpRotation = new Vector2f(this.resetYaw, this.resetPitch);
            lastYaw = this.resetYaw;
            lastPitch = this.resetPitch;
            LookHandler.setFreeYaw(this.resetYaw);
            LookHandler.setFreePitch(this.resetPitch);
            this.resetLonyGriefRotation(this.resetYaw, this.resetPitch);
            this.resetLonyGrief2Rotation(this.resetYaw, this.resetPitch);
            this.funTimeYaw = this.resetYaw;
            this.funTimePitch = this.resetPitch;
            this.spookyLastYaw = this.resetYaw;
            this.spookyLastPitch = this.resetPitch;
            this.holyLastYaw = this.resetYaw;
            this.holyLastPitch = this.resetPitch;
            this.slothInitialized = false;
            this.slothReverseSmooth = false;
            this.slothSmooth = 0.0F;
            this.slothLastYaw = this.resetYaw;
            this.slothLastPitch = this.resetPitch;
            RotationComponent.getInstance().stopRotation();
            SmoothRotationComponent.getInstance().stopRotation();
            this.needsReset = false;
            return;
         }
      }

   }

   private void updateVisualReverseYawState() {
      visualReverseYaw = false;
      Minecraft var10000 = mc;
      if (Minecraft.player != null && target != null) {
         ElytraTarget elytraTargetModule = SkyCore.getInstance().getModuleManager().getElytraTarget();
         if (elytraTargetModule != null && elytraTargetModule.isEnabled() && elytraTargetModule.usesVisualReverseLogic() && (Boolean)elytraTargetModule.visualReverse.get()) {
            if (!elytraTargetModule.shouldTarget(target)) {
               this.visualReverseWasActive = false;
            } else {
               var10000 = mc;
               float distanceToTarget = Minecraft.player.getDistance(target);
               float enableDistance = 2.5F;
               float predictDistance = Math.max(enableDistance, (Float)elytraTargetModule.distance.get());
               if (!this.visualReverseWasActive && distanceToTarget <= enableDistance) {
                  this.visualReverseWasActive = true;
               }

               if (this.visualReverseWasActive && distanceToTarget >= predictDistance) {
                  this.visualReverseWasActive = false;
               }

               visualReverseYaw = this.visualReverseWasActive;
            }
         } else {
            this.visualReverseWasActive = false;
         }
      } else {
         this.visualReverseWasActive = false;
      }
   }

   public static float getVisualYaw(float yaw) {
      return visualReverseYaw ? MathHelper.wrapDegrees(yaw + 180.0F) : yaw;
   }

   @EventTarget
   public void onEvent(EventPacket event) {
      IPacket packet = event.getPacket();
      if (packet instanceof CHeldItemChangePacket) {
         this.perfectDelay.reset(650L);
      } else if (packet instanceof CAnimateHandPacket) {
         this.perfectDelay.reset(500L);
      }

   }

   @EventTarget
   public void Event(EventPostUpdate event) {
      if (SkyCore.getInstance().getModuleManager().getModule(PacketCriticals.class).isEnabled() && target != null && this.canAttack() && (Boolean)this.onlycrit.get() && this.cps <= System.currentTimeMillis()) {
         this.updateAttack();
         this.cps = System.currentTimeMillis() + 460L;
      }

   }

   @EventTarget
   public void onEvent(EventGameUpdate event) {
      Minecraft var10000;
      if (target != null) {
         var10000 = mc;
         ElytraComponent.setVector(ElytraComponent.getVector3d(Minecraft.player, target));
      }

      var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (target != null) {
            var10000 = mc;
            if (Minecraft.player != null && mc.world != null) {
               if (this.shouldBlockRotationUpdate()) {
                  return;
               }

               this.updateRotation();
               return;
            }
         }

         this.performInvisibleReset();
         this.reset();
      }
   }

   private void updateRotation() {
      double maxHeight = AuraUtil.getStrictDistance(target) / this.attackDistance();
      Vector3d var10000 = target.getPositionVec();
      Minecraft var10002 = mc;
      var10000 = var10000.add(0.0, MathHelper.clamp(Minecraft.player.getEyePosition(mc.getRenderPartialTicks()).y - target.getPosY(), 0.0, maxHeight), 0.0);
      Minecraft var10001 = mc;
      Vector3d vec = var10000.subtract(Minecraft.player.getEyePosition(mc.getRenderPartialTicks())).normalize();
      float rawYaw = (float)Math.toDegrees(Math.atan2(-vec.x, vec.z));
      float rawPitch = (float)MathHelper.clamp(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))), -90.0, 90.0);
      float speed = (new SecureRandom()).nextBoolean() ? this.randomLerp(0.3F, 0.4F) : this.randomLerp(0.5F, 0.6F);
      float cos = (float)Math.cos((double)System.currentTimeMillis() / 70.0);
      float sin = (float)Math.sin((double)System.currentTimeMillis() / 115.0);
      float cosF = (float)Math.cos((double)System.currentTimeMillis() / 44.0);
      float yawF = (float)Math.ceil((double)(this.randomLerp(25.0F, 35.0F) * cosF));
      float yaw = (float)Math.ceil((double)(this.randomLerp(1.0F, 3.0F) * cos));
      float pitch = (float)Math.ceil((double)(this.randomLerp(1.0F, 2.0F) * sin));
      float pitchF = (float)Math.ceil((double)(this.randomLerp(7.0F, 15.0F) * sin));
      this.lerpRotation = new Vector2f(this.wrapLerp(speed, this.lerpRotation.x, rawYaw + yaw), this.wrapLerp(speed / 2.0F, this.lerpRotation.y, MathHelper.clamp(rawPitch + pitch, -90.0F, 90.0F)));
      this.lerpRot = new Vector2f(this.wrapLerp(speed, this.lerpRot.x, LookHandler.getFreeYaw() + yawF), this.wrapLerp(speed / 2.0F, this.lerpRot.y, MathHelper.clamp(pitchF, -90.0F, 90.0F)));
      this.lerprRot = new Vector2f(this.wrapLerp(speed, this.lerprRot.x, RotationUtil.calculateLimitedAim(target, 12.0).x + yaw), this.wrapLerp(speed, this.lerprRot.y, RotationUtil.calculateLimitedAim(target, 12.0).y + pitch));
      var10002 = mc;
      Minecraft var10004 = mc;
      float var23 = Minecraft.player.rotationYaw + (float)Math.ceil((double)(this.lerprRot.x - Minecraft.player.rotationYaw));
      Minecraft var10003 = mc;
      Minecraft var10005 = mc;
      new Rotation(var23, Minecraft.player.rotationPitch + (float)Math.ceil((double)(this.lerprRot.y - Minecraft.player.rotationPitch)));
      Rotation var20 = new Rotation;
      if (!this.canFTlerpRotation()) {
         var23 = LookHandler.getFreeYaw();
      } else {
         var10002 = mc;
         var10004 = mc;
         var23 = Minecraft.player.rotationYaw + (float)Math.ceil((double)(this.lerprRot.x - Minecraft.player.rotationYaw));
      }

      float var24;
      if (!this.canFTlerpRotation()) {
         var24 = LookHandler.getFreePitch();
      } else {
         var10003 = mc;
         var10005 = mc;
         var24 = Minecraft.player.rotationPitch + (float)Math.ceil((double)(this.lerprRot.y - Minecraft.player.rotationPitch));
      }

      var20.<init>(var23, var24);
      var10002 = mc;
      var10004 = mc;
      var23 = Minecraft.player.rotationYaw + (float)Math.ceil((double)(this.lerpRotation.x - Minecraft.player.rotationYaw));
      var10003 = mc;
      var24 = Minecraft.player.rotationPitch;
      float var25 = MathHelper.wrapDegrees(this.lerpRotation.y);
      var10005 = mc;
      Rotation rotation = new Rotation(var23, var24 + (float)Math.ceil((double)(var25 - MathHelper.wrapDegrees(Minecraft.player.rotationPitch))));
      float fov = (float)AuraUtil.calculateFOVFromCamera(target);
      float baseFov = 360.0F;
      float var22 = rotation.getYaw();
      var10002 = mc;
      this.yawDelta = ((var22 - Minecraft.player.rotationYaw) % 360.0F + 540.0F) % 360.0F - 180.0F;
      if (Math.abs(fov) < baseFov) {
         Minecraft var21 = mc;
         if (Minecraft.player.isElytraFlying()) {
            var10002 = mc;
            var10003 = mc;
            ElytraComponent.updateRotation(new Vector2f(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch));
            return;
         }

         String mode = (String)this.componentMode.get();
         if (mode.equals("Reallyworld")) {
            this.applyReallyWorldRotation(target);
         } else if (mode.equals("Reallyworld2")) {
            this.applyReallyWorld2Rotation(target);
         } else if (mode.equals("LonyGrief")) {
            this.updateLonyGriefRotation(target);
         } else if (mode.equals("LonyGrief#2")) {
            this.updateLonyGrief2Rotation(target);
         } else if (mode.equals("Grim")) {
            this.Grim(vec);
         } else if (mode.equals("GrimMatrix")) {
            this.GrimMatrix(vec);
         } else if (mode.equals("Sloth test")) {
            this.applySlothRotation(target);
         } else if (mode.equals("FunTime")) {
            this.applyFunTimeRotation(target);
         } else if (mode.equals("Snap")) {
            this.applySnapRotation(vec);
         } else if (mode.equals("Spooky")) {
            this.applySpookyRotation(target);
         } else if (mode.equals("HolyWorld")) {
            this.applyHolyWorldRotation(target);
         } else if (mode.equals("Jitter")) {
            this.applyJitterRotation(target);
         }
      }

      rotate = new Vector2f(this.lerpRotation.x, this.lerpRotation.y);
   }

   private void applyReallyWorldRotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            Vector3d targetPos = target.getPositionVec().add(0.0, (double)target.getHeight() / 2.0, 0.0);
            var10000 = mc;
            Vector3d playerPos = Minecraft.player.getEyePosition(1.0F);
            Vector3d direction = targetPos.subtract(playerPos);
            float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(direction.y, Math.hypot(direction.x, direction.z))));
            targetPitch = MathHelper.clamp(targetPitch, -89.0F, 89.0F);
            float randomYaw = (float)(ThreadLocalRandom.current().nextDouble() - 0.5) * (Float)this.jitterStrength.get();
            float randomPitch = (float)(ThreadLocalRandom.current().nextDouble() - 0.5) * ((Float)this.jitterStrength.get() / 2.0F);
            float finalYaw = targetYaw + ((Boolean)this.jitterEnabled.get() ? randomYaw : 0.0F);
            float finalPitch = targetPitch + ((Boolean)this.jitterEnabled.get() ? randomPitch : 0.0F);
            float gcd = getGCDValue();
            finalYaw -= (finalYaw - this.lerpRotation.x) % gcd;
            finalPitch -= (finalPitch - this.lerpRotation.y) % gcd;
            this.lerpRotation = new Vector2f(finalYaw, finalPitch);
            RotationComponent.update(new Rotation(finalYaw, finalPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
            return;
         }
      }

   }

   private void applyReallyWorld2Rotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            Vector3d targetPos = target.getPositionVec().add(0.0, (double)target.getHeight() / 2.0, 0.0);
            var10000 = mc;
            Vector3d playerPos = Minecraft.player.getEyePosition(1.0F);
            Vector3d direction = targetPos.subtract(playerPos);
            float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(direction.y, Math.hypot(direction.x, direction.z))));
            targetPitch = MathHelper.clamp(targetPitch, -89.0F, 89.0F);
            float gcd = getGCDValue();
            targetYaw -= (targetYaw - this.lerpRotation.x) % gcd;
            targetPitch -= (targetPitch - this.lerpRotation.y) % gcd;
            this.lerpRotation = new Vector2f(targetYaw, targetPitch);
            RotationComponent.update(new Rotation(targetYaw, targetPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
            return;
         }
      }

   }

   private void resetLonyGriefRotation(float currentYaw, float currentPitch) {
      this.lonyReverseSmooth = false;
      this.lonySmooth = 0.0F;
      this.lonyLastYaw = currentYaw;
      this.lonyLastPitch = currentPitch;
   }

   private void updateLonyGriefRotation(LivingEntity target) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && target != null) {
         Minecraft var10001;
         Minecraft var10002;
         if (this.lonyLastTarget != target) {
            var10001 = mc;
            var10002 = mc;
            this.resetLonyGriefRotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
            this.lonyLastTarget = target;
         }

         AxisAlignedBB box = target.getBoundingBox();
         var10000 = mc;
         Vector3d eyePos = Minecraft.player.getEyePosition(1.0F);
         Vector3d center = box.getCenter();
         Vector3d toTarget = center.subtract(eyePos);
         float targetYaw = MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(toTarget.z, toTarget.x)) - 90.0F);
         float targetPitch = (float)(-Math.toDegrees(Math.atan2(toTarget.y, Math.hypot(toTarget.x, toTarget.z))));
         var10000 = mc;
         var10001 = mc;
         var10002 = mc;
         Vector3d lookVec = Minecraft.player.getVectorForRotation(Minecraft.player.rotationPitch, Minecraft.player.rotationYaw);
         Vector3d rayEnd = eyePos.add(lookVec.scale(999.0));
         boolean lookingIntoBox = box.shrink(0.5).rayTrace(eyePos, rayEnd).isPresent();
         if (this.lonyReverseSmooth) {
            if (this.lonySmooth >= -0.01F) {
               this.lonySmooth -= Math.abs(MathHelper.wrapDegrees(targetYaw - this.lonyLastYaw)) > 80.0F ? 0.1F : 0.01F;
            }

            if (this.lonySmooth <= -0.01F) {
               this.lonyReverseSmooth = false;
            }
         } else {
            this.lonySmooth += 0.005F;
            if (this.lonySmooth >= 0.22F || lookingIntoBox) {
               this.lonyReverseSmooth = true;
            }
         }

         float yawDelta = MathHelper.wrapDegrees(targetYaw - this.lonyLastYaw);
         float pitchDelta = targetPitch - this.lonyLastPitch;
         float positiveSmooth = Math.max(this.lonySmooth, 0.0F);
         float newYaw = this.lonyLastYaw + yawDelta * MathHelper.clamp(positiveSmooth * 10.3F, 0.0F, 1.0F);
         float newPitch = this.lonyLastPitch + pitchDelta * MathHelper.clamp(positiveSmooth / 10.7F, 0.0F, 1.0F);
         float gcd = this.getSlothGCD();
         newYaw -= (newYaw - this.lonyLastYaw) % gcd;
         newPitch -= (newPitch - this.lonyLastPitch) % gcd;
         newPitch = MathHelper.clamp(newPitch, -89.0F, 89.0F);
         this.lonyLastYaw = newYaw;
         this.lonyLastPitch = newPitch;
         this.lerpRotation = new Vector2f(newYaw, newPitch);
         RotationComponent.update(new Rotation(newYaw, newPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
      }
   }

   private void resetLonyGrief2Rotation(float currentYaw, float currentPitch) {
      this.lony2ReverseSmooth = false;
      this.lony2Smooth = 0.0F;
      this.lony2LastYaw = currentYaw;
      this.lony2LastPitch = currentPitch;
   }

   private void updateLonyGrief2Rotation(LivingEntity target) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && target != null) {
         Minecraft var10001;
         Minecraft var10002;
         if (this.lony2LastTarget != target) {
            var10001 = mc;
            var10002 = mc;
            this.resetLonyGrief2Rotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
            this.lony2LastTarget = target;
         }

         AxisAlignedBB box = target.getBoundingBox();
         var10000 = mc;
         Vector3d eyePos = Minecraft.player.getEyePosition(1.0F);
         Vector3d center = box.getCenter();
         Vector3d toTarget = center.subtract(eyePos);
         float targetYaw = MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(toTarget.z, toTarget.x)) - 90.0F);
         float targetPitch = (float)(-Math.toDegrees(Math.atan2(toTarget.y, Math.hypot(toTarget.x, toTarget.z))));
         targetPitch = MathHelper.clamp(targetPitch, -89.0F, 89.0F);
         var10000 = mc;
         var10001 = mc;
         var10002 = mc;
         Vector3d lookVec = Minecraft.player.getVectorForRotation(Minecraft.player.rotationPitch, Minecraft.player.rotationYaw);
         Vector3d rayEnd = eyePos.add(lookVec.scale(999.0));
         boolean lookingIntoBox = box.shrink(0.5).rayTrace(eyePos, rayEnd).isPresent();
         float yawDelta;
         if (this.lony2ReverseSmooth) {
            yawDelta = Math.abs(MathHelper.wrapDegrees(targetYaw - this.lony2LastYaw)) > 80.0F ? 0.15F : 0.02F;
            this.lony2Smooth -= yawDelta;
            if (this.lony2Smooth <= -0.02F) {
               this.lony2ReverseSmooth = false;
            }
         } else {
            this.lony2Smooth += 0.008F;
            if (this.lony2Smooth >= 0.35F || lookingIntoBox) {
               this.lony2ReverseSmooth = true;
            }
         }

         yawDelta = MathHelper.wrapDegrees(targetYaw - this.lony2LastYaw);
         float pitchDelta = targetPitch - this.lony2LastPitch;
         float positiveSmooth = Math.max(this.lony2Smooth, 0.0F);
         float newYaw = this.lony2LastYaw + yawDelta * MathHelper.clamp(positiveSmooth * 15.0F, 0.0F, 1.0F);
         float newPitch = this.lony2LastPitch + pitchDelta * MathHelper.clamp(positiveSmooth / 5.0F, 0.0F, 1.0F);
         float gcd = this.getSlothGCD();
         newYaw -= (newYaw - this.lony2LastYaw) % gcd;
         newPitch -= (newPitch - this.lony2LastPitch) % gcd;
         newPitch = MathHelper.clamp(newPitch, -89.0F, 89.0F);
         this.lony2LastYaw = newYaw;
         this.lony2LastPitch = newPitch;
         this.lerpRotation = new Vector2f(newYaw, newPitch);
         RotationComponent.update(new Rotation(newYaw, newPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
      }
   }

   private void Grim(Vector3d vec) {
      if (this.canAttack() && vec.length() <= (double)(Float)this.attackRange.get()) {
         this.tickssnap = 1;
      }

      float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0);
      float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
      float finalyaw;
      float finalpitch;
      if (this.tickssnap > 0) {
         finalyaw = targetYaw;
         finalpitch = targetPitch;
      } else {
         finalyaw = LookHandler.getFreeYaw();
         finalpitch = LookHandler.getFreePitch();
      }

      RotationComponent.update(new Rotation(finalyaw, finalpitch), 255.0F, 1, 6);
      if (this.tickssnap > 0) {
         --this.tickssnap;
      }

   }

   private void GrimMatrix(Vector3d vec) {
      if (this.canAttack() && vec.length() <= (double)(Float)this.attackRange.get()) {
         this.tickssnapMatrix = 2;
      }

      float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0);
      float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
      float finalYaw;
      float finalPitch;
      if (this.tickssnapMatrix > 0) {
         finalYaw = targetYaw;
         finalPitch = targetPitch;
         --this.tickssnapMatrix;
      } else {
         float yawDiff = MathHelper.wrapDegrees(LookHandler.getFreeYaw() - this.lerpRotation.x);
         float pitchDiff = LookHandler.getFreePitch() - this.lerpRotation.y;
         float speed = 0.15F;
         finalYaw = this.lerpRotation.x + yawDiff * speed;
         finalPitch = this.lerpRotation.y + pitchDiff * speed;
         if (Math.abs(yawDiff) < 0.5F && Math.abs(pitchDiff) < 0.5F) {
            finalYaw = LookHandler.getFreeYaw();
            finalPitch = LookHandler.getFreePitch();
         }
      }

      RotationComponent.update(new Rotation(finalYaw, finalPitch), 255.0F, 1, 6);
   }

   private void initSlothRotation() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         this.slothReverseSmooth = false;
         this.slothSmooth = 0.0F;
         Minecraft var10001 = mc;
         this.slothLastYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.slothLastPitch = Minecraft.player.rotationPitch;
         this.slothInitialized = true;
      }
   }

   private void applySlothRotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            double distance = (double)Minecraft.player.getDistance(target);
            float maxDist = (Float)this.attackRange.get() + (Float)this.lerpRotationDistance.get();
            if (distance > (double)maxDist) {
               if (this.slothInitialized) {
                  this.slothInitialized = false;
               }

               return;
            }

            if (this.slothLastTarget != target) {
               this.initSlothRotation();
               this.slothLastTarget = target;
            }

            if (!this.slothInitialized) {
               this.initSlothRotation();
            }

            AxisAlignedBB box = target.getBoundingBox();
            var10000 = mc;
            Vector3d eyePos = Minecraft.player.getEyePosition(1.0F);
            Vector3d center = box.getCenter();
            Vector3d toTarget = center.subtract(eyePos);
            float targetYaw = MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(toTarget.z, toTarget.x)) - 90.0F);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(toTarget.y, Math.hypot(toTarget.x, toTarget.z))));
            targetPitch = MathHelper.clamp(targetPitch, -89.0F, 89.0F);
            var10000 = mc;
            Minecraft var10001 = mc;
            Minecraft var10002 = mc;
            Vector3d lookVec = Minecraft.player.getVectorForRotation(Minecraft.player.rotationPitch, Minecraft.player.rotationYaw);
            Vector3d rayEnd = eyePos.add(lookVec.scale(999.0));
            boolean lookingIntoBox = box.shrink(0.5).rayTrace(eyePos, rayEnd).isPresent();
            float yawDelta = MathHelper.wrapDegrees(targetYaw - this.slothLastYaw);
            float pitchDelta = targetPitch - this.slothLastPitch;
            float positiveSmooth;
            if (this.slothReverseSmooth) {
               positiveSmooth = Math.abs(yawDelta) > 80.0F ? 0.1F : 0.01F;
               this.slothSmooth -= positiveSmooth;
               if (this.slothSmooth <= -0.01F) {
                  this.slothReverseSmooth = false;
               }
            } else {
               this.slothSmooth += 0.005F;
               if (this.slothSmooth >= 0.22F || lookingIntoBox) {
                  this.slothReverseSmooth = true;
               }
            }

            positiveSmooth = Math.max(this.slothSmooth, 0.0F);
            float newYaw = this.slothLastYaw + yawDelta * MathHelper.clamp(positiveSmooth * 10.3F, 0.0F, 1.0F);
            float newPitch = this.slothLastPitch + pitchDelta * MathHelper.clamp(positiveSmooth / 10.7F, 0.0F, 1.0F);
            float gcd = this.getSlothGCD();
            newYaw -= (newYaw - this.slothLastYaw) % gcd;
            newPitch -= (newPitch - this.slothLastPitch) % gcd;
            newPitch = MathHelper.clamp(newPitch, -89.0F, 89.0F);
            this.slothLastYaw = newYaw;
            this.slothLastPitch = newPitch;
            this.lerpRotation = new Vector2f(newYaw, newPitch);
            RotationComponent.update(new Rotation(newYaw, newPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
            return;
         }
      }

      if (this.slothInitialized) {
         this.slothInitialized = false;
      }

   }

   private float getSlothGCD() {
      double sens = mc.gameSettings.mouseSensitivity;
      double value = sens * 0.6 + 0.2;
      return (float)(Math.pow(value, 3.0) * 0.8) * 0.15F;
   }

   private static float getGCDValue() {
      float sensitivity = (float)(mc.gameSettings.mouseSensitivity * 0.6000000238418579 + 0.20000000298023224);
      float gcd = sensitivity * sensitivity * sensitivity * 8.0F;
      return gcd * 0.15F;
   }

   private void applyFunTimeRotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            Vector3d targetPos = target.getPositionVec().add(0.0, (double)(target.getHeight() / 2.0F), 0.0);
            var10000 = mc;
            Vector3d playerPos = Minecraft.player.getEyePosition(1.0F);
            Vector3d direction = targetPos.subtract(playerPos);
            float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(direction.y, Math.hypot(direction.x, direction.z))));
            targetPitch = MathHelper.clamp(targetPitch, -89.0F, 89.0F);
            float yawDiff = MathHelper.wrapDegrees(targetYaw - this.funTimeYaw);
            float pitchDiff = targetPitch - this.funTimePitch;
            this.funTimeSpeed = Math.min(this.funTimeSpeed + 0.02F, 0.8F);
            if (Math.abs(yawDiff) < 2.0F && Math.abs(pitchDiff) < 2.0F) {
               this.funTimeSpeed = Math.max(this.funTimeSpeed - 0.01F, 0.1F);
            }

            float newYaw = this.funTimeYaw + yawDiff * Math.min(this.funTimeSpeed, 1.0F);
            float newPitch = this.funTimePitch + pitchDiff * Math.min(this.funTimeSpeed / 2.0F, 1.0F);
            float gcd = getGCDValue();
            newYaw -= (newYaw - this.funTimeYaw) % gcd;
            newPitch -= (newPitch - this.funTimePitch) % gcd;
            newPitch = MathHelper.clamp(newPitch, -89.0F, 89.0F);
            this.funTimeYaw = newYaw;
            this.funTimePitch = newPitch;
            this.lerpRotation = new Vector2f(newYaw, newPitch);
            RotationComponent.update(new Rotation(newYaw, newPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
            return;
         }
      }

   }

   private void applySnapRotation(Vector3d vec) {
      if (this.canAttack() && vec.length() <= (double)(Float)this.attackRange.get()) {
         this.tickssnap = 1;
      }

      float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0);
      float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
      float finalYaw;
      float finalPitch;
      if (this.tickssnap > 0) {
         finalYaw = targetYaw;
         finalPitch = targetPitch;
      } else {
         finalYaw = LookHandler.getFreeYaw();
         finalPitch = LookHandler.getFreePitch();
      }

      RotationComponent.update(new Rotation(finalYaw, finalPitch), 255.0F, 1, 6);
      if (this.tickssnap > 0) {
         --this.tickssnap;
      }

   }

   private void applySpookyRotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (this.spookyLastTarget != target) {
               Minecraft var10001 = mc;
               this.spookyLastYaw = Minecraft.player.rotationYaw;
               var10001 = mc;
               this.spookyLastPitch = Minecraft.player.rotationPitch;
               this.spookyLastTarget = target;
            }

            Vector3d targetPos = target.getPositionVec().add(0.0, (double)(target.getEyeHeight() - 0.3F), 0.0);
            var10000 = mc;
            Vector3d playerPos = Minecraft.player.getEyePosition(1.0F);
            Vector3d direction = targetPos.subtract(playerPos);
            float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(direction.y, Math.hypot(direction.x, direction.z))));
            float yawDelta = MathHelper.wrapDegrees(targetYaw - this.spookyLastYaw);
            float pitchDelta = targetPitch - this.spookyLastPitch;
            float clampedYaw = Math.min(Math.max(Math.abs(yawDelta), 0.001F), 22.5F);
            float clampedPitch = Math.min(Math.max(Math.abs(pitchDelta), 0.001F), 17.0F);
            float randomYaw = (float)(Math.random() * 2.5 - 1.5);
            float randomPitch = (float)(Math.random() * 2.5 - 1.0);
            if (this.spookyLastTarget == target) {
               clampedPitch /= 3.0F;
            }

            float newYaw = this.spookyLastYaw + (yawDelta > 0.0F ? clampedYaw : -clampedYaw) + randomYaw;
            float newPitch = this.spookyLastPitch + (pitchDelta > 0.0F ? clampedPitch : -clampedPitch) + randomPitch;
            float gcd = getGCDValue();
            newYaw -= (newYaw - this.spookyLastYaw) % gcd;
            newPitch -= (newPitch - this.spookyLastPitch) % gcd;
            newPitch = MathHelper.clamp(newPitch, -80.0F, 70.0F);
            this.spookyLastYaw = newYaw;
            this.spookyLastPitch = newPitch;
            this.lerpRotation = new Vector2f(newYaw, newPitch);
            RotationComponent.update(new Rotation(newYaw, newPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
            return;
         }
      }

   }

   private void initHolyWorldRotation() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         this.holyDecelerating = false;
         this.holyProgress = 0.0F;
         Minecraft var10001 = mc;
         this.holyLastYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.holyLastPitch = Minecraft.player.rotationPitch;
      }
   }

   private void applyHolyWorldRotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (this.holyLastTarget != target) {
               this.initHolyWorldRotation();
               this.holyLastTarget = target;
            }

            AxisAlignedBB box = target.getBoundingBox();
            var10000 = mc;
            Vector3d eyePos = Minecraft.player.getEyePosition(1.0F);
            Vector3d center = box.getCenter();
            Vector3d toTarget = center.subtract(eyePos);
            float targetYaw = MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(toTarget.z, toTarget.x)) - 90.0F);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(toTarget.y, Math.hypot(toTarget.x, toTarget.z))));
            targetPitch = MathHelper.clamp(targetPitch, -40.0F, 40.0F);
            var10000 = mc;
            Minecraft var10001 = mc;
            Minecraft var10002 = mc;
            Vector3d lookVec = Minecraft.player.getVectorForRotation(Minecraft.player.rotationPitch, Minecraft.player.rotationYaw);
            Vector3d rayEnd = eyePos.add(lookVec.scale(999.0));
            boolean lookingIntoBox = box.shrink(0.5).rayTrace(eyePos, rayEnd).isPresent();
            float yawDelta = MathHelper.wrapDegrees(targetYaw - this.holyLastYaw);
            float pitchDelta = targetPitch - this.holyLastPitch;
            float positiveProgress;
            if (this.holyDecelerating) {
               positiveProgress = Math.abs(yawDelta) > 80.0F ? 0.055F : 0.0055F;
               this.holyProgress -= positiveProgress;
               if (this.holyProgress <= -0.01F) {
                  this.holyDecelerating = false;
               }
            } else {
               this.holyProgress += 0.0034F;
               if (this.holyProgress >= 0.36F || lookingIntoBox) {
                  this.holyDecelerating = true;
               }
            }

            positiveProgress = Math.max(this.holyProgress, 0.0F);
            float newYaw = this.holyLastYaw + yawDelta * MathHelper.clamp(positiveProgress * 1.3F, 0.0F, 1.0F);
            float newPitch = this.holyLastPitch + pitchDelta * MathHelper.clamp(positiveProgress / 1.7F, 0.0F, 1.0F);
            float gcd = this.getSlothGCD();
            newYaw -= (newYaw - this.holyLastYaw) % gcd;
            newPitch -= (newPitch - this.holyLastPitch) % gcd;
            newPitch = MathHelper.clamp(newPitch, -40.0F, 40.0F);
            this.holyLastYaw = newYaw;
            this.holyLastPitch = newPitch;
            this.lerpRotation = new Vector2f(newYaw, newPitch);
            RotationComponent.update(new Rotation(newYaw, newPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
            return;
         }
      }

      if (this.holyLastTarget != null) {
         this.holyLastTarget = null;
      }

   }

   private void applyJitterRotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            Vector3d targetPos = target.getPositionVec().add(0.0, (double)target.getHeight() / 2.0, 0.0);
            var10000 = mc;
            Vector3d playerPos = Minecraft.player.getEyePosition(1.0F);
            Vector3d direction = targetPos.subtract(playerPos);
            float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(direction.y, Math.hypot(direction.x, direction.z))));
            targetPitch = MathHelper.clamp(targetPitch, -89.0F, 89.0F);
            long now = System.currentTimeMillis();
            if (now - this.jitterLastSwitch > 200L) {
               this.jitterPositive = !this.jitterPositive;
               this.jitterLastSwitch = now;
               this.jitterOffsetYaw = (float)(Math.random() * (double)(Float)this.jitterStrength.get() - (double)((Float)this.jitterStrength.get() / 2.0F));
               this.jitterOffsetPitch = (float)(Math.random() * (double)((Float)this.jitterStrength.get() / 2.0F) - (double)((Float)this.jitterStrength.get() / 4.0F));
            }

            float finalYaw = targetYaw + (this.jitterPositive ? this.jitterOffsetYaw : -this.jitterOffsetYaw);
            float finalPitch = targetPitch + (this.jitterPositive ? this.jitterOffsetPitch : -this.jitterOffsetPitch);
            float gcd = getGCDValue();
            finalYaw -= (finalYaw - this.lerpRotation.x) % gcd;
            finalPitch -= (finalPitch - this.lerpRotation.y) % gcd;
            this.lerpRotation = new Vector2f(finalYaw, finalPitch);
            RotationComponent.update(new Rotation(finalYaw, finalPitch), 360.0F, 360.0F, 0.0F, 5.0F, 1, 5, false);
            return;
         }
      }

   }

   public static Rotation rotation(AttackAura aura) {
      AxisAlignedBB box = target.getBoundingBox();
      Minecraft var10000 = mc;
      Vector3d eyes = Minecraft.player.getEyePosition(1.0F);
      Vector3d centerPoint = box.getCenter().add(0.0, (double)randomOffsetY, 0.0);
      Vector3d toTarget = centerPoint.subtract(eyes);
      int forwardTicks = 3;
      Vector3d xuesos = NewPredictRotations.predict(target, target.getBoundingBox().getCenter().subtract(eyes), forwardTicks);
      var10000 = mc;
      Vector2f targetRot;
      float deltaYaw;
      float deltaPitch;
      if (Minecraft.player.isElytraFlying() && target.isElytraFlying()) {
         deltaYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(xuesos.z, xuesos.x)) - 90.0);
         deltaPitch = (float)(-Math.toDegrees(Math.atan2(xuesos.y, Math.hypot(xuesos.x, xuesos.z))));
         targetRot = new Vector2f(deltaYaw, deltaPitch);
      } else {
         deltaYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(toTarget.z, toTarget.x)) - 90.0);
         deltaPitch = (float)(-Math.toDegrees(Math.atan2(toTarget.y, Math.hypot(toTarget.x, toTarget.z))));
         targetRot = new Vector2f(deltaYaw, deltaPitch);
      }

      var10000 = mc;
      Minecraft var10001;
      if (Minecraft.player.isElytraFlying() && target.isElytraFlying()) {
         if (isBack) {
            if (acceleration >= -0.02F) {
               acceleration -= Math.abs(MathHelper.wrapDegrees(targetRot.x - lastYaw)) > 80.0F ? 0.1F : 0.01F;
            }

            if (acceleration <= -0.02F) {
               isBack = false;
               updateRandomOffset();
            }
         } else {
            label51: {
               acceleration = (float)((double)acceleration + 0.010499999858438969 + (Math.random() * 0.019999999552965164 - 0.0010000000474974513));
               if (!(acceleration >= 0.305F)) {
                  var10000 = mc;
                  var10001 = mc;
                  if (!RayTraceUtil.rayTraceSingleEntity(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch, 1488.0, target)) {
                     break label51;
                  }
               }

               isBack = true;
            }
         }
      } else {
         float slowdownspeed;
         if (isBack) {
            if (acceleration >= -0.15F) {
               slowdownspeed = Math.abs(MathHelper.wrapDegrees(targetRot.x - lastYaw)) > 80.0F ? 0.1F : 0.01F;
               slowdownspeed = (float)((double)slowdownspeed * (0.9 + Math.random() * 0.20000000298023224));
               acceleration -= slowdownspeed;
            }

            if (acceleration <= -0.15F) {
               isBack = false;
               updateRandomOffset();
            }
         } else {
            label46: {
               slowdownspeed = (float)(0.8600000143051147 + (Math.random() * 0.019999999552965164 - 0.0010000000474974513));
               acceleration += slowdownspeed;
               float threshold = 0.185F + ((float)Math.random() * 0.03F - 0.012F);
               if (!(acceleration >= threshold)) {
                  var10000 = mc;
                  var10001 = mc;
                  if (!RayTraceUtil.rayTraceSingleEntityWithCustomBox(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch, 1488.0, target, 0.0F)) {
                     break label46;
                  }
               }

               isBack = true;
            }
         }
      }

      deltaYaw = MathHelper.wrapDegrees(targetRot.x - lastYaw);
      deltaPitch = targetRot.y - lastPitch;
      float smooth = Math.max(acceleration, 0.0F);
      float newYaw = lastYaw + deltaYaw * Math.min(Math.max(smooth, 0.0F), 1.0F);
      float newPitch = (float)((double)lastPitch + (double)deltaPitch * Math.min(Math.max((double)smooth / (2.0 * Math.random() * 0.019999999552965164), 0.0), 1.0));
      newYaw -= (newYaw - lastYaw) % getGCDValue();
      newPitch -= (newPitch - lastPitch) % getGCDValue();
      Rotation smoothRot = new Rotation(newYaw, newPitch);
      lastYaw = smoothRot.getYaw();
      lastPitch = smoothRot.getPitch();
      return smoothRot;
   }

   public static void updateRandomOffset() {
      randomOffsetX = (float)(Math.random() * 0.20000000298023224 - 0.10000000149011612);
      randomOffsetY = (float)(Math.random() * 0.6000000238418579 - 0.10000000149011612);
      randomOffsetZ = (float)(Math.random() * 0.20000000298023224 - 0.10000000149011612);
   }

   private boolean isTargetVisible(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            Vector3d eyesPos = Minecraft.player.getEyePosition(1.0F);
            Vector3d targetPos = target.getPositionVec().add(0.0, (double)(target.getHeight() / 2.0F), 0.0);
            Minecraft var10006 = mc;
            RayTraceContext context = new RayTraceContext(eyesPos, targetPos, BlockMode.OUTLINE, FluidMode.NONE, Minecraft.player);
            BlockRayTraceResult result = mc.world.rayTraceBlocks(context);
            return result == null || result.getType() == Type.MISS;
         }
      }

      return false;
   }

   private void LegitRotation(LivingEntity target) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (!this.isTargetVisible(target)) {
               return;
            }

            var10000 = mc;
            Vector3d eyePos = Minecraft.player.getEyePosition(1.0F);
            var10000 = mc;
            Vector3d lookVec = Minecraft.player.getLook(1.0F);
            AxisAlignedBB innerBox = target.getBoundingBox().grow(-0.15);
            double dist = eyePos.distanceTo(target.getPositionVec().add(0.0, (double)target.getHeight() / 2.0, 0.0));
            Vector3d projectedView = eyePos.add(lookVec.scale(dist));
            double shakeX = 0.0;
            double shakeY = 0.0;
            double shakeZ = 0.0;
            if (this.isHitting) {
               this.hitShakeIntensity = Math.min(1.0F, this.hitShakeIntensity + this.shakeIncreaseSpeed * this.deltaTime);
            } else {
               this.hitShakeIntensity = Math.max(0.0F, this.hitShakeIntensity - this.shakeDecreaseSpeed * this.deltaTime);
            }

            double x;
            double y;
            double z;
            if (this.hitShakeIntensity > 0.0F) {
               x = (double)System.currentTimeMillis() / 100.0;
               y = Math.sin(x * 8.0) * 0.1 * (double)this.hitShakeIntensity;
               z = Math.cos(x * 7.0) * 0.05 * (double)this.hitShakeIntensity;
               double bodyShakeZ = Math.sin(x * 9.0) * 0.1 * (double)this.hitShakeIntensity;
               double headShakeX = (Math.sin(x * 15.0) * 0.3 + Math.cos(x * 12.0) * 0.2) * (double)this.hitShakeIntensity;
               double headShakeY = (Math.sin(x * 20.0) * 0.2 + Math.cos(x * 18.0) * 0.15) * (double)this.hitShakeIntensity;
               double headShakeZ = (Math.sin(x * 17.0) * 0.25 + Math.cos(x * 14.0) * 0.2) * (double)this.hitShakeIntensity;
               shakeX = y + headShakeX * 0.5;
               shakeY = z + headShakeY * 0.5;
               shakeZ = bodyShakeZ + headShakeZ * 0.5;
               if (ThreadLocalRandom.current().nextFloat() < 0.1F * this.hitShakeIntensity) {
                  shakeX *= 2.5;
                  shakeY *= 2.5;
                  shakeZ *= 2.5;
               }
            }

            x = MathHelper.clamp(projectedView.x + shakeX, innerBox.minX, innerBox.maxX);
            y = MathHelper.clamp(projectedView.y + shakeY, innerBox.minY, innerBox.maxY);
            z = MathHelper.clamp(projectedView.z + shakeZ, innerBox.minZ, innerBox.maxZ);
            Vector3d targetPoint = new Vector3d(x, y, z);
            Vector3d diff = targetPoint.subtract(eyePos);
            float neededYaw = (float)Math.toDegrees(Math.atan2(diff.z, diff.x)) - 90.0F;
            float neededPitch = (float)(-Math.toDegrees(Math.atan2(diff.y, Math.hypot(diff.x, diff.z))));
            float yawDiff = MathHelper.wrapDegrees(neededYaw - this.lerpRotation.x);
            float pitchDiff = neededPitch - this.lerpRotation.y;
            float minSpeed = 0.1F;
            float maxSpeed = 0.6F;
            float speed = minSpeed + ThreadLocalRandom.current().nextFloat() * (maxSpeed - minSpeed);
            Vector2f var33 = this.lerpRotation;
            var33.x += yawDiff * speed;
            var33 = this.lerpRotation;
            var33.y += pitchDiff * speed;
            this.lerpRotation.y = MathHelper.clamp(this.lerpRotation.y, -90.0F, 90.0F);
            float gcd = getGCDValue();
            var33 = this.lerpRotation;
            Minecraft var10003 = mc;
            var33.x -= (this.lerpRotation.x - Minecraft.player.rotationYaw) % gcd;
            var33 = this.lerpRotation;
            var10003 = mc;
            var33.y -= (this.lerpRotation.y - Minecraft.player.rotationPitch) % gcd;
            SmoothRotationComponent.update(new Rotation(this.lerpRotation.x, this.lerpRotation.y), 2.0F, 3.0F, 2.0F, 4.0F, 1, 0, false);
            return;
         }
      }

   }

   private Vector2f calculateSpookyRotation(Vector2f currentRot, boolean attack) {
      if (target == null) {
         return currentRot;
      } else {
         float gcd = getGCDValue();
         float neckHeight = target.getEyeHeight() - 0.3F;
         Vector3d targetPos = target.getPositionVec().add(0.0, (double)neckHeight, 0.0);
         float yawToTarget;
         if (this.selected == target) {
            float randomOffsetX = ((new Random()).nextFloat() - 0.5F) * 0.1F;
            yawToTarget = ((new Random()).nextFloat() - 0.5F) * 0.1F;
            targetPos = targetPos.add((double)randomOffsetX, 0.0, (double)yawToTarget);
         }

         Minecraft var10001 = mc;
         Vector3d vecToNeck = targetPos.subtract(Minecraft.player.getEyePosition(1.0F));
         yawToTarget = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vecToNeck.z, vecToNeck.x)) - 90.0);
         float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(vecToNeck.y, Math.hypot(vecToNeck.x, vecToNeck.z))));
         float yawDelta = MathHelper.wrapDegrees(yawToTarget - currentRot.x);
         float pitchDelta = MathHelper.wrapDegrees(pitchToTarget - currentRot.y);
         float clampedYaw = Math.min(Math.max(Math.abs(yawDelta), 1.0E-4F), 22.5F);
         float clampedPitch = Math.min(Math.max(Math.abs(pitchDelta), 1.0E-4F), 17.0F);
         float randomYawFactor = (float)(Math.random() * 2.5 - 1.5);
         float randomPitchFactor = (float)(Math.random() * 2.5 - 1.0);
         float randomThreshold = (float)(Math.random() * 2.5);
         float randomAddition = (float)(Math.random() * 3.5 + 2.5);
         if (this.selected != target) {
            clampedPitch = Math.max(Math.abs(pitchDelta), 1.0F);
         } else {
            clampedPitch /= 3.0F;
         }

         if (Math.abs(clampedYaw - this.lastSpookyYaw) <= randomThreshold) {
            clampedYaw = this.lastSpookyYaw + randomAddition;
         }

         clampedYaw += randomYawFactor;
         clampedPitch += randomPitchFactor;
         float yaw = currentRot.x + (yawDelta > 0.0F ? clampedYaw : -clampedYaw);
         float pitch = MathHelper.clamp(currentRot.y + (pitchDelta > 0.0F ? clampedPitch : -clampedPitch), -80.0F, 70.0F);
         yaw -= (yaw - currentRot.x) % gcd;
         pitch -= (pitch - currentRot.y) % gcd;
         this.lastSpookyYaw = clampedYaw;
         this.lastSpookyPitch = clampedPitch;
         this.selected = target;
         return new Vector2f(yaw, pitch);
      }
   }

   public float getAICooldown() {
      Minecraft var10000 = mc;
      if (Minecraft.player.getHeldItemMainhand().getItem() == Items.AIR) {
         return 0.9F;
      } else {
         var10000 = mc;
         if (!(Minecraft.player.getHeldItemMainhand().getItem() instanceof AxeItem)) {
            var10000 = mc;
            if (!(Minecraft.player.getHeldItemMainhand().getItem() instanceof ShovelItem)) {
               return 0.93F;
            }
         }

         return 0.95F;
      }
   }

   private void fastRotation() {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            var10000 = mc;
            float currentYaw = Minecraft.player.rotationYaw;
            var10000 = mc;
            float currentPitch = Minecraft.player.rotationPitch;
            float deltaYaw = MathHelper.wrapDegrees(this.lerpRotation.x - currentYaw);
            float deltaPitch = MathHelper.wrapDegrees(this.lerpRotation.y - currentPitch);
            float newYaw = currentYaw + deltaYaw;
            float newPitch = currentPitch + deltaPitch;
            RotationComponent.update(new Rotation(newYaw, newPitch), 180.0F, 180.0F, 0, 5);
            return;
         }
      }

   }

   @Native
   private Rotation rotka(Rotation currentAngle, Rotation targetAngle) {
      int count = this.counter;
      float angleYaw = MathHelper.wrapDegrees(targetAngle.getYaw() - currentAngle.getYaw());
      float anglePitch = MathHelper.wrapDegrees(targetAngle.getPitch() - currentAngle.getPitch());
      Rotation angleDelta = new Rotation(angleYaw, anglePitch);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      float speed;
      float random;
      float yaw;
      if (this.canFTlerpRotation()) {
         float speed = this.canCrit ? 1.0F : ((new SecureRandom()).nextBoolean() ? 0.2F : 0.1F);
         speed = Math.abs(yawDelta / rotationDifference) * 180.0F;
         random = Math.abs(pitchDelta / rotationDifference) * 180.0F;
         float moveYaw = MathHelper.clamp(yawDelta, -speed, speed);
         yaw = MathHelper.clamp(pitchDelta, -random, random);
         Rotation moveAngle = new Rotation(currentAngle.getYaw(), currentAngle.getPitch());
         moveAngle.setYaw(MathHelper.lerp(this.randomLerp(speed, speed + 0.2F), currentAngle.getYaw(), currentAngle.getYaw() + moveYaw));
         moveAngle.setPitch(MathHelper.lerp(this.randomLerp(speed, speed + 0.2F), currentAngle.getPitch(), currentAngle.getPitch() + yaw));
         return moveAngle;
      } else {
         int suck = count % 3;
         speed = this.stopWatch.finished(400L) ? ((new SecureRandom()).nextBoolean() ? 0.4F : 0.2F) : -0.2F;
         random = (float)this.stopWatch.getElapsedTime() / 40.0F + (float)(count % 6);
         Rotation var10000;
         switch (suck) {
            case 0:
               var10000 = new Rotation((float)Math.cos((double)random), (float)Math.sin((double)random));
               break;
            case 1:
               var10000 = new Rotation((float)Math.sin((double)random), (float)Math.cos((double)random));
               break;
            case 2:
               var10000 = new Rotation((float)Math.sin((double)random), (float)(-Math.cos((double)random)));
               break;
            default:
               var10000 = new Rotation((float)(-Math.cos((double)random)), (float)Math.sin((double)random));
         }

         Rotation randomAngle = var10000;
         yaw = this.randomLerp(5.0F, 8.0F) * randomAngle.getYaw();
         float pitch2 = this.randomLerp(0.0F, 2.0F) * (float)Math.cos((double)System.currentTimeMillis() / 5000.0);
         float pitch = this.randomLerp(2.0F, 6.0F) * randomAngle.getPitch() + pitch2;
         float lineYaw = Math.abs(yawDelta / rotationDifference) * 180.0F;
         float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0F;
         float moveYaw = MathHelper.clamp(yawDelta, -lineYaw, lineYaw);
         float movePitch = MathHelper.clamp(pitchDelta, -linePitch, linePitch);
         Rotation moveAngle = new Rotation(currentAngle.getYaw(), currentAngle.getPitch());
         moveAngle.setYaw(MathHelper.lerp(MathHelper.clamp(this.randomLerp(speed, speed + 0.2F), 0.0F, 1.0F), currentAngle.getYaw(), currentAngle.getYaw() + moveYaw) + yaw);
         moveAngle.setPitch(MathHelper.lerp(MathHelper.clamp(this.randomLerp(speed, speed + 0.2F), 0.0F, 1.0F), currentAngle.getPitch(), currentAngle.getPitch() + movePitch) + pitch);
         return moveAngle;
      }
   }

   private boolean canFTlerpRotation() {
      if (this.perfectDelay.cooldownComplete() && this.cooldownComplete()) {
         Minecraft var10000;
         boolean isBlockAboveHead;
         boolean var4;
         label73: {
            isBlockAboveHead = PlayerUtil.isBlockAboveHead();
            var10000 = mc;
            if (!Minecraft.player.isActualySwimming()) {
               label71: {
                  var10000 = mc;
                  if (Minecraft.player.isSwimming()) {
                     var10000 = mc;
                     if (Minecraft.player.areEyesInFluid(FluidTags.WATER)) {
                        break label71;
                     }
                  }

                  var10000 = mc;
                  if (!Minecraft.player.areEyesInFluid(FluidTags.LAVA)) {
                     var4 = false;
                     break label73;
                  }
               }
            }

            var4 = true;
         }

         boolean isInLiquid = var4;
         if (isInLiquid) {
            return true;
         } else {
            label88: {
               var10000 = mc;
               if (Minecraft.player.isOnGround() || !this.stopWatch.finished(400L)) {
                  label82: {
                     var10000 = mc;
                     if (Minecraft.player.fallDistance >= 0.0F && this.canCrit) {
                        var10000 = mc;
                        if (Minecraft.player.getMotion().y == 0.0) {
                           break label82;
                        }
                     }

                     var10000 = mc;
                     if (!(Minecraft.player.fallDistance > 0.0F) || !this.canCrit) {
                        label83: {
                           if (isBlockAboveHead) {
                              var10000 = mc;
                              if (Minecraft.player.fallDistance >= 0.0F) {
                                 var10000 = mc;
                                 if (!Minecraft.player.isOnGround()) {
                                    break label83;
                                 }
                              }
                           }

                           var4 = false;
                           break label88;
                        }
                     }
                  }
               }

               var4 = true;
            }

            boolean canDefaultCrit = var4;
            if (!(Boolean)this.onlycrit.get()) {
               return AuraUtil.getStrictDistance(target) < this.attackDistance();
            } else {
               return this.shouldCritical() && canDefaultCrit;
            }
         }
      } else {
         return false;
      }
   }

   public void onDisable() {
      super.onDisable();
      this.performInvisibleReset();
      target = null;
      ElytraComponent.pos = Vector3d.ZERO;
      RotationComponent.getInstance().stopRotation();
      SmoothRotationComponent.getInstance().stopRotation();
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         LookHandler.setFreeYaw(Minecraft.player.rotationYaw);
         var10000 = mc;
         LookHandler.setFreePitch(Minecraft.player.rotationPitch);
      }

      if (this.componentMode.is("FunTime")) {
         RotationComponent.update(new Rotation(LookHandler.getFreeYaw(), LookHandler.getFreePitch()), 20.0F, 20.0F, 30.0F, 30.0F, 0, 30, false);
      }

      this.resetTargetRotationState();
      this.counter = 9;
      this.lerpRotation = Vector2f.ZERO;
      this.lerpRot = Vector2f.ZERO;
      this.slothInitialized = false;
      this.slothReverseSmooth = false;
      this.slothSmooth = 0.0F;
      this.lonyLastTarget = null;
      var10000 = mc;
      Minecraft var10001;
      Minecraft var10002;
      if (Minecraft.player != null) {
         var10001 = mc;
         var10002 = mc;
         this.resetLonyGriefRotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
      }

      this.lony2LastTarget = null;
      var10000 = mc;
      if (Minecraft.player != null) {
         var10001 = mc;
         var10002 = mc;
         this.resetLonyGrief2Rotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
      }

      var10000 = mc;
      if (Minecraft.player != null) {
         var10001 = mc;
         this.funTimeYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.funTimePitch = Minecraft.player.rotationPitch;
      }

      this.funTimeSpeed = 0.1F;
      this.spookyLastTarget = null;
      this.holyLastTarget = null;
      this.holyDecelerating = false;
      this.holyProgress = 0.0F;
      this.jitterLastSwitch = 0L;
      this.jitterPositive = false;
      this.jitterOffsetYaw = 0.0F;
      this.jitterOffsetPitch = 0.0F;
      visualReverseYaw = false;
      this.visualReverseWasActive = false;
      this.tickssnap = 0;
      this.tickssnapMatrix = 0;
      this.needsReset = false;
      this.resetDelay = 0;
   }

   public void onEnable() {
      super.onEnable();
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         this.needsReset = false;
         this.resetDelay = 0;
         Minecraft var10003 = mc;
         Minecraft var10004 = mc;
         this.lerpRotation = new Vector2f(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
         var10003 = mc;
         var10004 = mc;
         this.lerpRot = new Vector2f(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
         var10000 = mc;
         lastYaw = Minecraft.player.rotationYaw;
         var10000 = mc;
         lastPitch = Minecraft.player.rotationPitch;
         acceleration = 0.0F;
         isBack = false;
         this.lonyLastTarget = null;
         Minecraft var10001 = mc;
         Minecraft var10002 = mc;
         this.resetLonyGriefRotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
         this.lony2LastTarget = null;
         var10001 = mc;
         var10002 = mc;
         this.resetLonyGrief2Rotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
         var10001 = mc;
         this.funTimeYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.funTimePitch = Minecraft.player.rotationPitch;
         this.funTimeSpeed = 0.1F;
         this.spookyLastTarget = null;
         var10001 = mc;
         this.spookyLastYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.spookyLastPitch = Minecraft.player.rotationPitch;
         this.holyLastTarget = null;
         this.initHolyWorldRotation();
         this.jitterLastSwitch = System.currentTimeMillis();
         this.jitterPositive = true;
         this.jitterOffsetYaw = 0.0F;
         this.jitterOffsetPitch = 0.0F;
         visualReverseYaw = false;
         this.visualReverseWasActive = false;
         this.tickssnap = 0;
         this.tickssnapMatrix = 0;
         if (this.componentMode.is("Sloth test")) {
            this.initSlothRotation();
         }

      }
   }

   public float wrapLerp(float step, float input, float target) {
      return input + step * MathHelper.wrapDegrees(target - input);
   }

   public float randomLerp(float min, float max) {
      return MathHelper.lerp((new SecureRandom()).nextFloat(), min, max);
   }

   public float cooldownFromLastSwing() {
      Minecraft var10000 = mc;
      return MathHelper.clamp((float)Minecraft.player.ticksSinceLastSwing / this.randomLerp(8.0F, 12.0F), 0.0F, 1.0F);
   }

   private void updateAttack() {
      Sprint autoSprint = (Sprint)SkyCore.getInstance().getModuleManager().getModule(Sprint.class);
      if (this.tickssnap <= 0 && this.tickssnapMatrix <= 0) {
         Minecraft var10000;
         if (this.canAttack() && AuraUtil.getStrictDistance(target) < this.attackDistance()) {
            boolean var13;
            label72: {
               var10000 = mc;
               if (Minecraft.player.serverSprintState) {
                  var10000 = mc;
                  if (!Minecraft.player.isInWater()) {
                     var10000 = mc;
                     if (!Minecraft.player.isInLava()) {
                        var10000 = mc;
                        if (!Minecraft.player.isSwimming()) {
                           var13 = true;
                           break label72;
                        }
                     }
                  }
               }

               var13 = false;
            }

            boolean sprint = var13;
            if (autoSprint != null && autoSprint.isEnabled() && autoSprint.getMode().is("Пакетный") && sprint) {
               this.legitSprintResetTicks = 1;
               var10000 = mc;
               if (Minecraft.player.serverSprintState) {
                  var10000 = mc;
                  Minecraft.player.setServerSprintState(false);
                  var10000 = mc;
                  Minecraft.player.setSprinting(false);
                  var10000 = mc;
                  Minecraft var10003 = mc;
                  Minecraft.player.connection.sendPacket(new CEntityActionPacket(Minecraft.player, Action.STOP_SPRINTING));
               }
            }

            if ((Boolean)this.bypassWalls.get() && !this.isBypassWallsBlockedByFluid()) {
               var10000 = mc;
               Vector3d startVec = Minecraft.player.getEyePosition(mc.getRenderPartialTicks());
               Vector3d targetPos = target.getEyePosition(mc.getRenderPartialTicks());
               Vector3d direction = targetPos.subtract(startVec);
               double distance = direction.length();
               if (distance < 0.001) {
                  return;
               }

               Vector3d normalizedDir = direction.normalize();

               for(double i = 0.0; i < distance; i += 0.5) {
                  Vector3d point = startVec.add(normalizedDir.scale(i));
                  BlockPos pos = new BlockPos(point);
                  if (!mc.world.isAirBlock(pos)) {
                     var10000 = mc;
                     Minecraft.player.connection.sendPacket(new CPlayerDiggingPacket(net.minecraft.network.play.client.CPlayerDiggingPacket.Action.START_DESTROY_BLOCK, pos, Direction.UP));
                     var10000 = mc;
                     Minecraft.player.connection.sendPacket(new CPlayerDiggingPacket(net.minecraft.network.play.client.CPlayerDiggingPacket.Action.STOP_DESTROY_BLOCK, pos, Direction.UP));
                  }
               }
            }

            if ((Boolean)this.SHEIS.get()) {
               var10000 = mc;
               if (Minecraft.player.isBlocking()) {
                  Minecraft var10001 = mc;
                  mc.playerController.onStoppedUsingItem(Minecraft.player);
               }
            }

            this.attackEntity(target);
            this.stopWatch.reset();
            this.count = (this.count + 1) % 2;
            ++this.counter;
            this.canCrit = false;
         } else if (!this.shouldBlockRotationUpdate()) {
            var10000 = mc;
            if (!Minecraft.player.canEntityBeSeen(target) && ServerUtil.isConnectedToServer("holyworld")) {
               var10000 = mc;
               if (Minecraft.player.getServerBrand().contains("HolyWorld")) {
                  RotationComponent.update(new Rotation(Rotation.cameraYaw(), 90.0F), 360.0F, 360.0F, 0, 5);
               }
            }
         }

      }
   }

   private boolean shouldBlockAttack() {
      Minecraft var10000;
      boolean var4;
      label47: {
         if (SkyCore.getInstance().getModuleManager().getElytraTarget().isEnabled() && target != null) {
            var10000 = mc;
            if (Minecraft.player.isElytraFlying() && target.isElytraFlying()) {
               var4 = true;
               break label47;
            }
         }

         var4 = false;
      }

      boolean blockWhileEating;
      label34: {
         boolean elytraPursuitActive = var4;
         var10000 = mc;
         blockWhileEating = Minecraft.player.isHandActive() && (Boolean)this.ignoreEat.get() && !elytraPursuitActive;
         var10000 = mc;
         if (!(Minecraft.player.getHeldItemMainhand().getItem() instanceof AxeItem)) {
            var10000 = mc;
            if (!(Minecraft.player.getHeldItemMainhand().getItem() instanceof SwordItem) && (Boolean)this.onlyWeapon.get()) {
               var4 = true;
               break label34;
            }
         }

         var4 = false;
      }

      boolean weaponCheckFailed = var4;
      return blockWhileEating || weaponCheckFailed;
   }

   private boolean shouldBlockRotationUpdate() {
      if (TargetPearl.isRotationActive()) {
         return true;
      } else {
         Minecraft var10000 = mc;
         boolean var1;
         if (!(Minecraft.player.getHeldItemMainhand().getItem() instanceof AxeItem)) {
            var10000 = mc;
            if (!(Minecraft.player.getHeldItemMainhand().getItem() instanceof SwordItem) && (Boolean)this.onlyWeapon.get()) {
               var1 = true;
               return var1;
            }
         }

         var1 = false;
         return var1;
      }
   }

   private void attackEntity(Entity entity) {
      if (entity == target) {
         Minecraft var10001 = mc;
         mc.playerController.attackEntity(Minecraft.player, entity);
         Minecraft var10000 = mc;
         Minecraft.player.swingArm(Hand.MAIN_HAND);
      }
   }

   public boolean canAttack() {
      Minecraft var10000;
      boolean var5;
      label107: {
         if (this.stopWatch.hasTimeElapsed(450L)) {
            var10000 = mc;
            if (Minecraft.player.getCooledAttackStrength((Boolean)this.tpsSync.get() ? TPSHandler.getAdjustTicks() : 1.5F) > this.getAICooldown()) {
               var5 = true;
               break label107;
            }
         }

         var5 = false;
      }

      boolean ready;
      label101: {
         ready = var5;
         var10000 = mc;
         if (!Minecraft.player.movementInput.jump) {
            var10000 = mc;
            if (Minecraft.player.isOnGround()) {
               var5 = false;
               break label101;
            }
         }

         var5 = true;
      }

      boolean air = var5;
      if (this.componentMode.is("SpookyTime")) {
         boolean spookyDelay = this.stopWatch.getElapsedTime() >= (long)(350 + (this.counter % 10 == 0 ? 255 : 0));
         var10000 = mc;
         boolean spookyStrength = Minecraft.player.getCooledAttackStrength(1.5F) >= 0.92F;
         if (!spookyDelay || !spookyStrength) {
            return false;
         }
      }

      if (AuraUtil.isJumpBlockedByCeiling()) {
         return ready;
      } else if (SkyCore.getInstance().getModuleManager().getModule(PacketCriticals.class).isEnabled()) {
         label133: {
            if (ready) {
               if (!(Boolean)this.onlycrit.get()) {
                  break label133;
               }

               var10000 = mc;
               if (!Minecraft.player.isValidAttackCondition() || (Boolean)this.onlySpaceCritical.get() && !air) {
                  break label133;
               }

               var10000 = mc;
               if (Minecraft.player.canCritical()) {
                  break label133;
               }
            }

            var5 = false;
            return var5;
         }

         var5 = true;
         return var5;
      } else {
         label135: {
            if (ready) {
               if (!(Boolean)this.onlycrit.get()) {
                  break label135;
               }

               var10000 = mc;
               if (!Minecraft.player.isValidAttackCondition() || (Boolean)this.onlySpaceCritical.get() && !air) {
                  break label135;
               }

               var10000 = mc;
               if (Minecraft.player.canCritical() && this.canCrit) {
                  break label135;
               }
            }

            var5 = false;
            return var5;
         }

         var5 = true;
         return var5;
      }
   }

   private boolean shouldCritical() {
      Minecraft var10000;
      boolean var7;
      label57: {
         var10000 = mc;
         if (!Minecraft.player.isPotionActive(Effects.LEVITATION)) {
            var10000 = mc;
            if (!Minecraft.player.isPotionActive(Effects.BLINDNESS)) {
               var10000 = mc;
               if (!Minecraft.player.isPotionActive(Effects.SLOW_FALLING)) {
                  var7 = false;
                  break label57;
               }
            }
         }

         var7 = true;
      }

      boolean isDeBuffed;
      label51: {
         isDeBuffed = var7;
         var10000 = mc;
         if (!Minecraft.player.isActualySwimming()) {
            label49: {
               var10000 = mc;
               if (Minecraft.player.isSwimming()) {
                  var10000 = mc;
                  if (Minecraft.player.areEyesInFluid(FluidTags.WATER)) {
                     break label49;
                  }
               }

               var10000 = mc;
               if (!Minecraft.player.areEyesInFluid(FluidTags.LAVA)) {
                  var7 = false;
                  break label51;
               }
            }
         }

         var7 = true;
      }

      boolean isInLiquid;
      label42: {
         isInLiquid = var7;
         var10000 = mc;
         if (!Minecraft.player.abilities.isFlying) {
            var10000 = mc;
            if (!Minecraft.player.isElytraFlying()) {
               var7 = false;
               break label42;
            }
         }

         var7 = true;
      }

      boolean isFlying = var7;
      var10000 = mc;
      boolean isClimbing = Minecraft.player.isOnLadder();
      var10000 = mc;
      boolean isCantJump = Minecraft.player.isPassenger();
      boolean isOnWeb = PlayerUtil.isPlayerInWeb();
      return !isInLiquid && !isFlying && !isClimbing && !isCantJump && !isOnWeb || isDeBuffed;
   }

   private LivingEntity findTarget() {
      LivingEntity nearest = null;
      double nearestDistance = Double.MAX_VALUE;
      boolean scanPlayers = this.targets.is("Игроки") || this.targets.is("Голые") || this.targets.is("Друзья");
      Minecraft var10000;
      if (scanPlayers) {
         Iterator var5 = mc.world.getPlayers().iterator();

         while(var5.hasNext()) {
            PlayerEntity player = (PlayerEntity)var5.next();
            Minecraft var10001 = mc;
            if (player != Minecraft.player && this.isValidTarget(player)) {
               var10000 = mc;
               double distance = (double)Minecraft.player.getDistance(player);
               if (distance < nearestDistance) {
                  nearestDistance = distance;
                  nearest = player;
               }
            }
         }
      }

      boolean scanMobs = this.targets.is("Мобы") || this.targets.is("Животные");
      if (!scanMobs) {
         return (LivingEntity)nearest;
      } else {
         Iterator var12 = mc.world.getAllEntities().iterator();

         while(var12.hasNext()) {
            Entity entity = (Entity)var12.next();
            if (entity instanceof LivingEntity) {
               LivingEntity living = (LivingEntity)entity;
               if (!(living instanceof PlayerEntity) && this.isValidTarget(living)) {
                  var10000 = mc;
                  double distance = (double)Minecraft.player.getDistance(living);
                  if (distance < nearestDistance) {
                     nearestDistance = distance;
                     nearest = living;
                  }
               }
            }
         }

         return (LivingEntity)nearest;
      }
   }

   public double getEntityArmor(PlayerEntity target) {
      double totalArmor = 0.0;
      Iterator var4 = target.inventory.armorInventory.iterator();

      while(var4.hasNext()) {
         ItemStack armorStack = (ItemStack)var4.next();
         if (armorStack != null && armorStack.getItem() instanceof ArmorItem) {
            totalArmor += this.getProtectionLvl(armorStack);
         }
      }

      return totalArmor;
   }

   public double getEntityHealth(Entity ent) {
      if (ent instanceof PlayerEntity player) {
         double armorValue = this.getEntityArmor(player) / 20.0;
         return (double)(player.getHealth() + player.getAbsorptionAmount()) * armorValue;
      } else if (ent instanceof LivingEntity livingEntity) {
         return (double)(livingEntity.getHealth() + livingEntity.getAbsorptionAmount());
      } else {
         return 0.0;
      }
   }

   private double getProtectionLvl(ItemStack stack) {
      ArmorItem armor = (ArmorItem)stack.getItem();
      double damageReduce = (double)armor.getDamageReduceAmount();
      if (stack.isEnchanted()) {
         damageReduce += (double)EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack) * 0.25;
      }

      return damageReduce;
   }

   private boolean isValidTarget(LivingEntity entity) {
      if (entity instanceof ClientPlayerEntity) {
         return false;
      } else if (entity.ticksExisted < 3) {
         return false;
      } else {
         Minecraft var10000 = mc;
         if (Minecraft.player.getDistanceEyePos(entity) >= this.getMaxAimRange()) {
            return false;
         } else {
            if (entity instanceof PlayerEntity) {
               PlayerEntity playerEntity = (PlayerEntity)entity;
               if (SkyCore.getInstance().getModuleManager().getModule(AntiBot.class).isEnabled() && AntiBot.bot.contains(playerEntity)) {
                  return false;
               }

               if (!this.targets.is("Друзья") && SkyCore.getInstance().getFriendManager().isFriend(playerEntity.getName().getString())) {
                  return false;
               }

               String var3 = playerEntity.getName().getString();
               Minecraft var10001 = mc;
               if (var3.equalsIgnoreCase(Minecraft.player.getName().getString())) {
                  return false;
               }
            }

            if (entity instanceof PlayerEntity && entity.getTotalArmorValue() == 0 && !this.targets.is("Голые")) {
               return false;
            } else if (entity instanceof PlayerEntity && !this.targets.is("Игроки")) {
               return false;
            } else if ((entity instanceof MonsterEntity || entity instanceof PhantomEntity || entity instanceof BatEntity || entity instanceof ShulkerEntity || entity instanceof VillagerEntity || entity instanceof WanderingTraderEntity || entity instanceof SlimeEntity || entity instanceof IronGolemEntity) && !this.targets.is("Мобы")) {
               return false;
            } else if ((entity instanceof AnimalEntity || entity instanceof SalmonEntity || entity instanceof TropicalFishEntity || entity instanceof CodEntity || entity instanceof SquidEntity || entity instanceof DolphinEntity) && !this.targets.is("Животные")) {
               return false;
            } else {
               return !entity.isInvulnerable() && entity.isAlive() && !(entity instanceof ArmorStandEntity);
            }
         }
      }
   }

   public boolean cooldownComplete() {
      Minecraft var10000 = mc;
      float attackStrength = Minecraft.player.getCooledAttackStrength(1.5F);
      if (this.componentMode.is("SpookyTime")) {
         if (this.stopWatch.getElapsedTime() >= (long)(350 + (this.counter % 10 == 0 ? 255 : 0))) {
            return !(attackStrength < 0.92F);
         }
      } else if (this.stopWatch.getElapsedTime() >= 500L) {
         return !(attackStrength < 0.92F);
      }

      return false;
   }

   public double getMaxAimRange() {
      float attackDist = (Float)this.attackRange.get();
      float lerpRotationDist = (Float)this.lerpRotationDistance.get();
      float originalAimDistance = this.componentMode.is("Легитная") ? 0.2F : 0.0F;
      boolean isGrim = this.componentMode.is("Grim") || this.componentMode.is("GrimMatrix") || this.componentMode.is("Snap");
      float elytraAimDist = SkyCore.getInstance().getModuleManager().getElytraTarget().isEnabled() ? (Float)ElytraTarget.pursuitdistance.get() : 0.0F;
      float test = isGrim ? attackDist : attackDist + lerpRotationDist - originalAimDistance;
      Minecraft var10000 = mc;
      return Minecraft.player.isElytraFlying() ? (double)(attackDist + elytraAimDist) : (double)test;
   }

   public double attackDistance() {
      return Math.max(mc.playerController.extendedReach() ? 6.0 : 3.0, (double)(Float)this.attackRange.get());
   }

   private boolean isBypassWallsBlockedByFluid() {
      Minecraft var10000 = mc;
      if (!Minecraft.player.isInWater()) {
         var10000 = mc;
         if (!Minecraft.player.isInLava()) {
            var10000 = mc;
            if (!Minecraft.player.areEyesInFluid(FluidTags.WATER)) {
               var10000 = mc;
               if (!Minecraft.player.areEyesInFluid(FluidTags.LAVA)) {
                  if (target == null) {
                     return false;
                  }

                  if (!target.isInWater() && !target.isInLava() && !target.areEyesInFluid(FluidTags.WATER) && !target.areEyesInFluid(FluidTags.LAVA)) {
                     var10000 = mc;
                     Vector3d startVec = Minecraft.player.getEyePosition(mc.getRenderPartialTicks());
                     Vector3d targetPos = target.getEyePosition(mc.getRenderPartialTicks());
                     Vector3d direction = targetPos.subtract(startVec);
                     double distance = direction.length();
                     if (distance < 0.001) {
                        return false;
                     }

                     Vector3d normalizedDir = direction.normalize();

                     for(double i = 0.0; i < distance; i += 0.5) {
                        BlockPos pos = new BlockPos(startVec.add(normalizedDir.scale(i)));
                        FluidState fluid = mc.world.getFluidState(pos);
                        if (fluid.isTagged(FluidTags.WATER) || fluid.isTagged(FluidTags.LAVA)) {
                           return true;
                        }
                     }

                     return false;
                  }

                  return true;
               }
            }
         }
      }

      return true;
   }

   private void reset() {
      this.performInvisibleReset();
      target = null;
      this.canCrit = false;
      this.resetTargetRotationState();
      ElytraComponent.resetState();
      this.slothInitialized = false;
      this.slothReverseSmooth = false;
      this.slothSmooth = 0.0F;
      visualReverseYaw = false;
      this.visualReverseWasActive = false;
      this.tickssnap = 0;
      this.tickssnapMatrix = 0;
      this.funTimeSpeed = 0.1F;
      this.spookyLastTarget = null;
      this.holyLastTarget = null;
      this.holyDecelerating = false;
      this.holyProgress = 0.0F;
      this.jitterLastSwitch = 0L;
      this.jitterPositive = false;
      this.needsReset = false;
      this.resetDelay = 0;
   }

   private void resetTargetRotationState() {
      RotationComponent.getInstance().stopRotation();
      SmoothRotationComponent.getInstance().stopRotation();
      this.lonyLastTarget = null;
      this.lony2LastTarget = null;
      this.slothLastTarget = null;
      this.spookyLastTarget = null;
      this.holyLastTarget = null;
      this.slothInitialized = false;
      this.slothReverseSmooth = false;
      this.slothSmooth = 0.0F;
      visualReverseYaw = false;
      this.visualReverseWasActive = false;
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         this.performInvisibleReset();
         Minecraft var10001 = mc;
         Minecraft var10002 = mc;
         this.resetLonyGriefRotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
         var10001 = mc;
         var10002 = mc;
         this.resetLonyGrief2Rotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch);
         var10001 = mc;
         this.funTimeYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.funTimePitch = Minecraft.player.rotationPitch;
         var10001 = mc;
         this.spookyLastYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.spookyLastPitch = Minecraft.player.rotationPitch;
         var10001 = mc;
         this.holyLastYaw = Minecraft.player.rotationYaw;
         var10001 = mc;
         this.holyLastPitch = Minecraft.player.rotationPitch;
      }
   }

   public static LivingEntity getTarget() {
      return target;
   }

   public static Vector2f getRotate() {
      return rotate;
   }

   public BooleanSetting getOnlycrit() {
      return this.onlycrit;
   }

   public ModeSetting getCorrectionType() {
      return this.correctionType;
   }

   public BooleanSetting getThroughWalls() {
      return this.throughWalls;
   }

   public int getTickssnap() {
      return this.tickssnap;
   }

   public int getTickssnapMatrix() {
      return this.tickssnapMatrix;
   }

   public static double getBpsTarget() {
      return bpsTarget;
   }

   public Vector2f getLerpRotation() {
      return this.lerpRotation;
   }

   public long getCps() {
      return this.cps;
   }
}
