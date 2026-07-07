package unique.core.features.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1451;
import net.minecraft.class_1531;
import net.minecraft.class_1588;
import net.minecraft.class_1621;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1674;
import net.minecraft.class_1676;
import net.minecraft.class_1678;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1839;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2663;
import net.minecraft.class_2815;
import net.minecraft.class_2824;
import net.minecraft.class_2846;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5134;
import net.minecraft.class_745;
import net.minecraft.class_2828.class_2830;
import net.minecraft.class_2846.class_2847;
import net.minecraft.class_2848.class_2849;
import org.jetbrains.annotations.NotNull;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.core.manager.player.PlayerManager;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.notification.Notification;
import unique.core.injection.accesors.ILivingEntity;
import unique.core.setting.Setting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.Timer;
import unique.core.utility.interfaces.IOtherClientPlayerEntity;
import unique.core.utility.math.MathUtility;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.PlayerUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class Aura extends Module {
   public final Setting<Float> attackRange = new Setting<>("AttackRange", 3.0F, 1.0F, 6.0F);
   public final Setting<Float> wallRange = new Setting<>("WallsRange", 3.0F, 0.0F, 6.0F);
   public final Setting<Float> aimRange = new Setting<>("AimRange", 3.0F, 0.0F, 10.0F);
   public final Setting<Float> elytraAttackRange = new Setting<>("ElytraRange", 0.5F, 0.0F, 6.0F);
   public final Setting<Float> elytraAimRange = new Setting<>("ElytraAimRange", 35.0F, 8.0F, 256.0F);
   public final Setting<Aura.WallsBypass> wallsBypass = new Setting<>("WallsBypass", Aura.WallsBypass.Off, v -> this.getWallRange() > 0.0F);
   public final Setting<Integer> fov = new Setting<>("FOV", 180, 1, 360);
   public final Setting<Aura.Mode> rotationMode = new Setting<>("RotationMode", Aura.Mode.Cosmic);
   public final Setting<Boolean> smartCrit = new Setting<>("SmartCrit", true);
   public final Setting<Boolean> onlyCrit = new Setting<>("OnlyCritical", false);
   public final Setting<Boolean> shieldBreaker = new Setting<>("ShieldBreaker", true);
   public final Setting<Boolean> pauseWhileEating = new Setting<>("PauseWhileEating", false);
   public final Setting<Boolean> tpsSync = new Setting<>("TPSSync", false);
   public final Setting<Boolean> clientLook = new Setting<>("ClientLook", false);
   public final Setting<Boolean> oldDelay = new Setting<>("OldDelay", false);
   public final Setting<Integer> minCPS = new Setting<>("MinCPS", 7, 1, 20, v -> this.oldDelay.getValue());
   public final Setting<Integer> maxCPS = new Setting<>("MaxCPS", 12, 1, 20, v -> this.oldDelay.getValue());
   public final Setting<Aura.Sort> sort = new Setting<>("Sort", Aura.Sort.LowestDistance);
   public final Setting<Boolean> lockTarget = new Setting<>("LockTarget", true);
   public final Setting<Boolean> elytraTarget = new Setting<>("ElytraTarget", true);
   public final Setting<Boolean> useSprintLogic = new Setting<>("Sprint", false);
   public final Setting<Aura.SprintMode> sprintattackaura = new Setting<>("SprintMode", Aura.SprintMode.Off, v -> this.useSprintLogic.getValue());
   public final Setting<SettingGroup> advanced = new Setting<>("Advanced", new SettingGroup(false, 0));
   public final Setting<Boolean> dropSprint = new Setting<>("DropSprint", false).addToGroup(this.advanced);
   public final Setting<Boolean> returnSprint = new Setting<>("ReturnSprint", false, v -> this.dropSprint.getValue()).addToGroup(this.advanced);
   public final Setting<Aura.RayTrace> rayTrace = new Setting<>("RayTrace", Aura.RayTrace.OnlyTarget).addToGroup(this.advanced);
   public final Setting<Boolean> grimRayTrace = new Setting<>("GrimRayTrace", true).addToGroup(this.advanced);
   public final Setting<Boolean> unpressShield = new Setting<>("UnpressShield", true).addToGroup(this.advanced);
   public final Setting<Boolean> deathDisable = new Setting<>("DisableOnDeath", true).addToGroup(this.advanced);
   public final Setting<Aura.AttackHand> attackHand = new Setting<>("AttackHand", Aura.AttackHand.MainHand).addToGroup(this.advanced);
   public final Setting<Aura.Resolver> resolver = new Setting<>("Resolver", Aura.Resolver.Advantage).addToGroup(this.advanced);
   public final Setting<Boolean> resolverVisualisation = new Setting<>("ResolverVisualisation", false, v -> !this.resolver.is(Aura.Resolver.Off))
      .addToGroup(this.advanced);
   public final Setting<Aura.AccelerateOnHit> accelerateOnHit = new Setting<>("AccelerateOnHit", Aura.AccelerateOnHit.Off).addToGroup(this.advanced);
   public final Setting<Integer> minYawStep = new Setting<>("MinYawStep", 65, 1, 180).addToGroup(this.advanced);
   public final Setting<Integer> maxYawStep = new Setting<>("MaxYawStep", 75, 1, 180).addToGroup(this.advanced);
   public final Setting<Float> aimedPitchStep = new Setting<>("AimedPitchStep", 1.0F, 0.0F, 90.0F).addToGroup(this.advanced);
   public final Setting<Float> maxPitchStep = new Setting<>("MaxPitchStep", 8.0F, 1.0F, 90.0F).addToGroup(this.advanced);
   public final Setting<Float> pitchAccelerate = new Setting<>("PitchAccelerate", 1.65F, 1.0F, 10.0F).addToGroup(this.advanced);
   public final Setting<Float> attackCooldown = new Setting<>("AttackCooldown", 0.9F, 0.5F, 1.0F).addToGroup(this.advanced);
   public final Setting<Float> attackBaseTime = new Setting<>("AttackBaseTime", 0.5F, 0.0F, 2.0F).addToGroup(this.advanced);
   public final Setting<Integer> attackTickLimit = new Setting<>("AttackTickLimit", 11, 0, 20).addToGroup(this.advanced);
   public final Setting<Float> critFallDistance = new Setting<>("CritFallDistance", 0.0F, 0.0F, 1.0F).addToGroup(this.advanced);
   public final Setting<SettingGroup> targets = new Setting<>("Targets", new SettingGroup(false, 0));
   public final Setting<Boolean> Players = new Setting<>("Players", true).addToGroup(this.targets);
   public final Setting<Boolean> antiBotNew = new Setting<>("AntiBot", true).addToGroup(this.targets);
   public final Setting<Boolean> Mobs = new Setting<>("Mobs", true).addToGroup(this.targets);
   public final Setting<Boolean> Animals = new Setting<>("Animals", false).addToGroup(this.targets);
   public final Setting<Boolean> Villagers = new Setting<>("Villagers", false).addToGroup(this.targets);
   public final Setting<Boolean> Slimes = new Setting<>("Slimes", false).addToGroup(this.targets);
   public final Setting<Boolean> hostiles = new Setting<>("Hostiles", false).addToGroup(this.targets);
   public final Setting<Boolean> onlyAngry = new Setting<>("OnlyAngryHostiles", false, v -> this.hostiles.getValue()).addToGroup(this.targets);
   public final Setting<Boolean> Projectiles = new Setting<>("Projectiles", false).addToGroup(this.targets);
   public final Setting<Boolean> ignoreInvisible = new Setting<>("IgnoreInvisibleEntities", false).addToGroup(this.targets);
   public final Setting<Boolean> ignoreNamed = new Setting<>("IgnoreNamed", false).addToGroup(this.targets);
   public final Setting<Boolean> ignoreTeam = new Setting<>("IgnoreTeam", false).addToGroup(this.targets);
   public final Setting<Boolean> ignoreCreative = new Setting<>("IgnoreCreative", false).addToGroup(this.targets);
   public final Setting<Boolean> ignoreNaked = new Setting<>("IgnoreNaked", false).addToGroup(this.targets);
   public final Setting<Boolean> ignoreShield = new Setting<>("AttackShieldingEntities", true).addToGroup(this.targets);
   public static class_1297 target;
   private class_1297 lastTarget;
   public float rotationYaw;
   public float rotationPitch;
   public float pitchAcceleration = 1.0F;
   private float lastYaw = 0.0F;
   private float lastPitch = 0.0F;
   private float originalYaw;
   private float originalPitch;
   private boolean restoring = false;
   private class_243 rotationPoint = class_243.field_1353;
   private class_243 rotationMotion = class_243.field_1353;
   private int hitTicks;
   private int trackticks;
   private boolean lookingAtHitbox;
   private final Timer delayTimer = new Timer();
   private final Timer pauseTimer = new Timer();
   private final Timer sprintTimer = new Timer();
   private boolean sprintDisabledByAura = false;
   private class_243 elytraPredictPoint = class_243.field_1353;
   private final Random random = new Random();
   public class_238 resolvedBox;
   static boolean wasTargeted = false;

   private int randomInt(int min, int max) {
      return min + this.random.nextInt(max - min + 1);
   }

   public Aura() {
      super("AttackAura", Module.Category.COMBAT);
   }

   private float getRange() {
      return this.getAttackRange();
   }

   private float getWallRange() {
      return this.wallRange.getValue();
   }

   private boolean isElytra() {
      return mc.field_1724 != null && mc.field_1724.method_6128();
   }

   private float getAimRange() {
      return this.isElytra() ? this.elytraAimRange.getValue() : this.aimRange.getValue();
   }

   private float getAttackRange() {
      return this.isElytra() ? this.elytraAttackRange.getValue() : this.attackRange.getValue();
   }

   public void auraLogic() {
      if (!this.haveWeapon()) {
         target = null;
      } else {
         this.handleKill();
         this.updateTarget();
         if (target != null) {
            boolean readyForAttack;
            if (this.grimRayTrace.getValue()) {
               readyForAttack = this.autoCrit() && (this.lookingAtHitbox || this.skipRayTraceCheck());
               this.calcRotations(this.autoCrit());
            } else {
               this.calcRotations(this.autoCrit());
               readyForAttack = this.autoCrit() && (this.lookingAtHitbox || this.skipRayTraceCheck());
            }

            if (readyForAttack) {
               if (this.shieldBreaker(false)) {
                  return;
               }

               boolean[] playerState = this.preAttack();
               if (!(
                  target instanceof class_1657 pl
                     && pl.method_6115()
                     && pl.method_6079().method_7909() == class_1802.field_8255
                     && !this.ignoreShield.getValue()
               )) {
                  this.attack();
               }

               this.postAttack(playerState[0], playerState[1]);
            }
         }
      }
   }

   private boolean haveWeapon() {
      return true;
   }

   private boolean skipRayTraceCheck() {
      return this.rotationMode.getValue() == Aura.Mode.None || this.rayTrace.getValue() == Aura.RayTrace.OFF || this.rotationMode.is(Aura.Mode.Grim);
   }

   public void attack() {
      Criticals.cancelCrit = true;
      ModuleManager.criticals.doCrit();
      int prevSlot = this.switchMethod();
      mc.field_1761.method_2918(mc.field_1724, target);
      Criticals.cancelCrit = false;
      this.swingHand();
      this.hitTicks = this.getHitTicks();
      if (prevSlot != -1) {
         InventoryUtility.switchTo(prevSlot);
      }
   }

   @NotNull
   private boolean[] preAttack() {
      boolean blocking = mc.field_1724.method_6115()
         && mc.field_1724.method_6030().method_7909().method_7853(mc.field_1724.method_6030()) == class_1839.field_8949;
      if (blocking && this.unpressShield.getValue()) {
         this.sendPacket(new class_2846(class_2847.field_12974, class_2338.field_10980, class_2350.field_11033));
      }

      boolean sprint = mc.field_1724.method_5624();
      if (sprint && this.dropSprint.getValue() && !this.sprintDisabledByAura) {
         double currentVelocity = mc.field_1724.method_18798().method_37268();
         boolean canDrop = mc.field_1724.method_24828()
            && !mc.field_1724.method_6101()
            && !mc.field_1724.method_5799()
            && !mc.field_1724.method_5771()
            && currentVelocity > 0.001
            && mc.field_1724.field_6012 % 2 == 0;
         if (canDrop) {
            this.sprintDisabledByAura = true;
            mc.field_1724.method_5728(false);
            this.sprintTimer.reset();
         }
      }

      if (this.rotationMode.is(Aura.Mode.Grim)) {
         this.sendPacket(
            new class_2830(
               mc.field_1724.method_23317(),
               mc.field_1724.method_23318(),
               mc.field_1724.method_23321(),
               this.rotationYaw,
               this.rotationPitch,
               mc.field_1724.method_24828()
            )
         );
      }

      return new boolean[]{blocking, sprint};
   }

   public void postAttack(boolean block, boolean sprint) {
      if (this.sprintDisabledByAura && this.returnSprint.getValue()) {
         double velocity = mc.field_1724.method_18798().method_37268();
         long dynamicDelay = velocity > 0.05 ? this.randomInt(15, 35) : this.randomInt(30, 55);
         if (mc.field_1724.field_6017 > 0.1) {
            dynamicDelay += 25L;
         }

         if (mc.field_1724.field_6235 > 0) {
            dynamicDelay -= 12L;
         }

         if (this.sprintTimer.passedMs(dynamicDelay)) {
            boolean shouldResume = sprint
               && mc.field_1724.method_24828()
               && !mc.field_1724.field_5976
               && mc.field_1724.method_7344().method_7586() > 6
               && !mc.field_1724.method_6059(class_1294.field_5919)
               && velocity > 8.0E-4;
            if (shouldResume) {
               mc.field_1724.method_5728(true);
            }

            this.sprintDisabledByAura = false;
         }
      }

      if (this.sprintDisabledByAura) {
         boolean forceReset = this.sprintTimer.passedMs(450L) || mc.field_1724.method_5869() || mc.field_1724.method_31549().field_7479 || !sprint;
         if (forceReset) {
            this.sprintDisabledByAura = false;
         }
      }

      if (block && this.unpressShield.getValue()) {
         this.sendSequencedPacket(id -> new class_2886(class_1268.field_5810, id, this.rotationYaw, this.rotationPitch));
      }

      if (this.rotationMode.is(Aura.Mode.Grim)) {
         this.sendPacket(
            new class_2830(
               mc.field_1724.method_23317(),
               mc.field_1724.method_23318(),
               mc.field_1724.method_23321(),
               mc.field_1724.method_36454(),
               mc.field_1724.method_36455(),
               mc.field_1724.method_24828()
            )
         );
      }
   }

   private void disableSprint() {
      mc.field_1724.method_5728(false);
      mc.field_1690.field_1867.method_23481(false);
      this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12985));
   }

   private void enableSprint() {
      mc.field_1724.method_5728(true);
      mc.field_1690.field_1867.method_23481(true);
      this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12981));
   }

   public void resolvePlayers() {
      if (this.resolver.not(Aura.Resolver.Off)) {
         for (class_1657 player : mc.field_1687.method_18456()) {
            if (player instanceof class_745) {
               ((IOtherClientPlayerEntity)player).resolve(this.resolver.getValue());
            }
         }
      }
   }

   public void restorePlayers() {
      if (this.resolver.not(Aura.Resolver.Off)) {
         for (class_1657 player : mc.field_1687.method_18456()) {
            if (player instanceof class_745) {
               ((IOtherClientPlayerEntity)player).releaseResolver();
            }
         }
      }
   }

   public void handleKill() {
      if (target instanceof class_1309 && (((class_1309)target).method_6032() <= 0.0F || ((class_1309)target).method_29504())) {
         Managers.NOTIFICATION
            .publicity("Aura", ClientSettings.isRu() ? "Цель успешно нейтрализована!" : "Target Successfully Neutralized!", 3, Notification.Type.SUCCESS);
      }
   }

   private int switchMethod() {
      return -1;
   }

   private int getHitTicks() {
      return this.oldDelay.getValue()
         ? 1 + (int)(20.0F / MathUtility.random((float)this.minCPS.getValue().intValue(), (float)this.maxCPS.getValue().intValue()))
         : (this.shouldRandomizeDelay() ? (int)MathUtility.random(11.0F, 13.0F) : this.attackTickLimit.getValue());
   }

   @EventHandler
   public void onUpdate(PlayerUpdateEvent e) {
      if (this.pauseTimer.passedMs(1000L)) {
         if (!mc.field_1724.method_6115() || !this.pauseWhileEating.getValue()) {
            if (this.useSprintLogic.getValue()) {
               switch ((Aura.SprintMode)this.sprintattackaura.getValue()) {
                  case Off:
                     mc.field_1724.method_5728(false);
                     mc.field_1690.field_1867.method_23481(false);
                     this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12985));
                     break;
                  case HVH:
                     if (mc.field_1724 == null) {
                        return;
                     }

                     mc.field_1690.field_1867.method_23481(true);
                     if (mc.field_1724.method_7261(0.0F) > 0.6F) {
                        mc.field_1724.method_5728(false);
                        this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12985));
                        mc.field_1724.method_5728(true);
                        this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12981));
                     } else {
                        mc.field_1724.method_5728(true);
                     }
                     break;
                  case Legit:
                     if (mc.field_1724 == null) {
                        return;
                     }

                     boolean canSprintLegit = mc.field_1724.field_3913.field_3905 > 0.0F
                        && mc.field_1724.method_7344().method_7586() > 6
                        && !mc.field_1724.field_5976
                        && !mc.field_1724.method_6115();
                     if (target == null || !(mc.field_1724.method_7261(0.5F) > 0.9F) && this.hitTicks <= 0) {
                        mc.field_1724.method_5728(canSprintLegit);
                        mc.field_1690.field_1867.method_23481(canSprintLegit);
                     } else {
                        mc.field_1724.method_5728(false);
                        mc.field_1690.field_1867.method_23481(false);
                     }
                     break;
                  case LegitExtra:
                     if (mc.field_1724 == null) {
                        return;
                     }

                     boolean canSprint = mc.field_1724.field_3913.field_3905 > 0.0F && !mc.field_1724.method_6115();
                     if (this.hitTicks == this.getHitTicks()) {
                        mc.field_1724.method_5728(false);
                     } else {
                        mc.field_1724.method_5728(canSprint);
                     }
               }
            }

            this.resolvePlayers();
            this.auraLogic();
            this.restorePlayers();
            this.hitTicks--;
         }
      }
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (this.restoring) {
         float step = 3.0F;
         mc.field_1724
            .method_36456(
               class_3532.method_15393(mc.field_1724.method_36454() + class_3532.method_15363(this.originalYaw - mc.field_1724.method_36454(), -step, step))
            );
         mc.field_1724
            .method_36457(
               class_3532.method_15363(
                  mc.field_1724.method_36455() + class_3532.method_15363(this.originalPitch - mc.field_1724.method_36455(), -step, step), -90.0F, 90.0F
               )
            );
         if (Math.abs(mc.field_1724.method_36454() - this.originalYaw) < 0.1F && Math.abs(mc.field_1724.method_36455() - this.originalPitch) < 0.1F) {
            this.restoring = false;
         }

         this.rotationYaw = mc.field_1724.method_36454();
         this.rotationPitch = mc.field_1724.method_36455();
      }

      if (this.pauseTimer.passedMs(1000L)) {
         if (!mc.field_1724.method_6115() || !this.pauseWhileEating.getValue()) {
            if (this.haveWeapon()) {
               if (target != null && this.rotationMode.getValue() != Aura.Mode.None && this.rotationMode.getValue() != Aura.Mode.Grim) {
                  mc.field_1724.method_36456(this.rotationYaw);
                  mc.field_1724.method_36457(this.rotationPitch);
               } else {
                  this.rotationYaw = mc.field_1724.method_36454();
                  this.rotationPitch = mc.field_1724.method_36455();
               }

               if (this.oldDelay.getValue() && this.minCPS.getValue() > this.maxCPS.getValue()) {
                  this.minCPS.setValue(this.maxCPS.getValue());
               }
            }
         }
      }
   }

   @EventHandler
   public void onPacketSend(@NotNull PacketEvent.Send e) {
      if (e.getPacket() instanceof class_2824 pie && Criticals.getInteractType(pie) != Criticals.InteractType.ATTACK && target != null) {
         e.cancel();
      }
   }

   @EventHandler
   public void onPacketReceive(@NotNull PacketEvent.Receive e) {
      if (e.getPacket() instanceof class_2663 status
         && status.method_11470() == 30
         && status.method_11469(mc.field_1687) != null
         && target != null
         && status.method_11469(mc.field_1687) == target) {
         Managers.NOTIFICATION
            .publicity(
               "Aura",
               ClientSettings.isRu()
                  ? "Успешно сломали щит игроку " + target.method_5477().getString()
                  : "Succesfully Destroyed " + target.method_5477().getString() + "Shield",
               2,
               Notification.Type.SUCCESS
            );
      }

      if (e.getPacket() instanceof class_2663 pac
         && pac.method_11470() == 3
         && pac.method_11469(mc.field_1687) == mc.field_1724
         && this.deathDisable.getValue()) {
         this.disable(ClientSettings.isRu() ? "Отключаю из-за смерти!" : "Disabling Due To Death!");
      }
   }

   @Override
   public void onEnable() {
      target = null;
      this.lookingAtHitbox = false;
      this.rotationPoint = class_243.field_1353;
      this.rotationMotion = class_243.field_1353;
      this.rotationYaw = mc.field_1724.method_36454();
      this.rotationPitch = mc.field_1724.method_36455();
      this.delayTimer.reset();
   }

   private boolean autoCrit() {
      boolean reasonForSkipCrit = !this.smartCrit.getValue()
         || mc.field_1724.method_31549().field_7479
         || mc.field_1724.method_6128()
         || ModuleManager.elytraPlus.isEnabled()
         || mc.field_1724.method_6059(class_1294.field_5919)
         || mc.field_1724.method_6059(class_1294.field_5906)
         || Managers.PLAYER.isInWeb();
      if (this.hitTicks > 0) {
         return false;
      } else if (this.onlyCrit.getValue() && mc.field_1724.method_24828()) {
         return false;
      } else if (this.getAttackCooldown() < this.attackCooldown.getValue() && !this.oldDelay.getValue()) {
         return false;
      } else if (ModuleManager.criticals.isEnabled() && ModuleManager.criticals.mode.is(Criticals.Mode.Grim)) {
         return true;
      } else {
         boolean mergeWithTargetStrafe = !ModuleManager.targetStrafe.isEnabled() || !ModuleManager.targetStrafe.jump.getValue();
         boolean mergeWithSpeed = !ModuleManager.speed.isEnabled() || mc.field_1724.method_24828();
         if (!mc.field_1690.field_1903.method_1434() && mergeWithTargetStrafe && mergeWithSpeed) {
            return true;
         } else if (mc.field_1724.method_5771() || mc.field_1724.method_5869()) {
            return true;
         } else if (!mc.field_1690.field_1903.method_1434() && this.isAboveWater()) {
            return true;
         } else if (mc.field_1724.field_6017 > 0.8 && mc.field_1724.field_6017 < 1.2) {
            return false;
         } else if (!reasonForSkipCrit) {
            float minFall = this.shouldRandomizeFallDistance() ? MathUtility.random(0.1F, 0.5F) : this.critFallDistance.getValue();
            return !mc.field_1724.method_24828() && mc.field_1724.field_6017 > minFall;
         } else {
            return true;
         }
      }
   }

   private boolean shieldBreaker(boolean instant) {
      int axeSlot = InventoryUtility.getAxe().slot();
      if (axeSlot != -1 && this.shieldBreaker.getValue() && target instanceof class_1657 targetPlayer) {
         boolean hasShield = targetPlayer.method_6047().method_7909() == class_1802.field_8255
            || targetPlayer.method_6079().method_7909() == class_1802.field_8255;
         if (targetPlayer.method_6115() && hasShield) {
            if (!instant) {
               boolean isFalling = mc.field_1724.field_6017 > 0.05F
                  && !mc.field_1724.method_24828()
                  && !mc.field_1724.method_6101()
                  && !mc.field_1724.method_5799();
               if (!isFalling) {
                  return false;
               }

               if (mc.field_1724.method_7261(0.5F) < 0.95F) {
                  return false;
               }
            }

            int oldSlot = mc.field_1724.method_31548().field_7545;
            if (axeSlot >= 9) {
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, axeSlot, oldSlot, class_1713.field_7791, mc.field_1724);
               mc.field_1761.method_2918(mc.field_1724, target);
               this.swingHand();
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, axeSlot, oldSlot, class_1713.field_7791, mc.field_1724);
               this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
            } else {
               this.sendPacket(new class_2868(axeSlot));
               mc.field_1761.method_2918(mc.field_1724, target);
               this.swingHand();
               this.sendPacket(new class_2868(oldSlot));
            }

            this.hitTicks = 10;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void swingHand() {
      switch ((Aura.AttackHand)this.attackHand.getValue()) {
         case MainHand:
            mc.field_1724.method_6104(class_1268.field_5808);
            break;
         case OffHand:
            mc.field_1724.method_6104(class_1268.field_5810);
      }
   }

   public boolean isAboveWater() {
      return mc.field_1724.method_5869()
         || mc.field_1687.method_8320(class_2338.method_49638(mc.field_1724.method_19538().method_1031(0.0, -0.4, 0.0))).method_26204()
            == class_2246.field_10382;
   }

   public float getAttackCooldownProgressPerTick() {
      return (float)(
         1.0
            / mc.field_1724.method_45325(class_5134.field_23723)
            * (20.0 * UniqueCore.TICK_TIMER * (this.tpsSync.getValue() ? Managers.SERVER.getTPSFactor() : 1.0F))
      );
   }

   public float getAttackCooldown() {
      return class_3532.method_15363(
         (((ILivingEntity)mc.field_1724).getLastAttackedTicks() + this.attackBaseTime.getValue()) / this.getAttackCooldownProgressPerTick(), 0.0F, 1.0F
      );
   }

   private void updateTarget() {
      class_1297 candidat = this.findTarget();
      if (!this.elytraTarget.getValue() || !(target instanceof class_1309 living && living.method_6128() && !this.skipEntity(target))) {
         if (target == null) {
            target = candidat;
            this.lastTarget = target;
         } else {
            if (this.sort.getValue() == Aura.Sort.FOV || !this.lockTarget.getValue()) {
               target = candidat;
            }

            if (candidat instanceof class_1676) {
               target = candidat;
            }

            if (this.skipEntity(target)) {
               target = null;
            }

            if (target != this.lastTarget) {
               this.lastTarget = target;
            }
         }
      }
   }

   private void calcRotations(boolean ready) {
      if (ready) {
         this.trackticks = mc.field_1687
               .method_20812(mc.field_1724, mc.field_1724.method_5829().method_1009(-0.25, 0.0, -0.25).method_989(0.0, 1.0, 0.0))
               .iterator()
               .hasNext()
            ? 1
            : 3;
      } else if (this.trackticks > 0) {
         this.trackticks--;
      }

      if (target != null) {
         class_243 targetVec;
         if (this.isElytra()) {
            targetVec = this.getElytraHitVec(target);
            this.elytraPredictPoint = targetVec;
         } else {
            targetVec = this.getLegitLook(target);
         }

         if (targetVec != null) {
            this.pitchAcceleration = Managers.PLAYER.checkRtx(this.rotationYaw, this.rotationPitch, this.getRange(), this.getRange(), this.rayTrace.getValue())
               ? this.aimedPitchStep.getValue()
               : (
                  this.pitchAcceleration < this.maxPitchStep.getValue()
                     ? this.pitchAcceleration * this.pitchAccelerate.getValue()
                     : this.maxPitchStep.getValue()
               );
            float delta_yaw = class_3532.method_15393(
               (float)class_3532.method_15338(
                     Math.toDegrees(Math.atan2(targetVec.field_1350 - mc.field_1724.method_23321(), targetVec.field_1352 - mc.field_1724.method_23317()))
                        - 90.0
                  )
                  - this.rotationYaw
            );
            if (this.wallsBypass.is(Aura.WallsBypass.V2) && !ready && !mc.field_1724.method_6057(target)) {
               delta_yaw += class_3532.method_15363(delta_yaw * 0.3F, -20.0F, 20.0F);
            }

            float delta_pitch = (float)(
                  -Math.toDegrees(
                     Math.atan2(
                        targetVec.field_1351 - (mc.field_1724.method_19538().field_1351 + mc.field_1724.method_18381(mc.field_1724.method_18376())),
                        Math.sqrt(
                           Math.pow(targetVec.field_1352 - mc.field_1724.method_23317(), 2.0)
                              + Math.pow(targetVec.field_1350 - mc.field_1724.method_23321(), 2.0)
                        )
                     )
                  )
               )
               - this.rotationPitch;
            float jitterScale = this.lookingAtHitbox
               ? class_3532.method_15363(Math.abs(delta_yaw) / 50.0F, 0.05F, 0.3F)
               : class_3532.method_15363(Math.abs(delta_yaw) / 30.0F, 0.15F, 1.0F);
            float microJitterYaw = this.isElytra() ? 0.0F : MathUtility.random(-0.15F, 0.15F) * jitterScale;
            float microJitterPitch = this.isElytra() ? 0.0F : MathUtility.random(-0.08F, 0.08F) * jitterScale;
            float distanceFactor = (float)Math.min(1.0, mc.field_1724.method_5707(target.method_19538()) / 16.0);
            float angleFactor = Math.abs(delta_yaw) / 180.0F;
            float proximityMultiplier = distanceFactor < 0.3F ? 0.6F : 1.0F;
            float baseYawStep = MathUtility.random((float)this.minYawStep.getValue().intValue(), (float)this.maxYawStep.getValue().intValue());
            float yawStep = this.rotationMode.getValue() != Aura.Mode.Cosmic
               ? 360.0F
               : baseYawStep * (0.7F + distanceFactor * 0.3F) * (0.8F + angleFactor * 0.2F) * proximityMultiplier;
            float pitchCurve = (float)(1.0 - Math.exp(-Math.abs(delta_pitch) / 30.0));
            float pitchStep = this.rotationMode.getValue() != Aura.Mode.Cosmic
               ? 360.0F
               : (this.isElytra() ? 180.0F : (this.pitchAcceleration + MathUtility.random(-0.3F, 0.3F)) * (0.6F + pitchCurve * 0.4F));
            if (ready) {
               switch ((Aura.AccelerateOnHit)this.accelerateOnHit.getValue()) {
                  case Yaw:
                     yawStep = Math.min(180.0F, yawStep * 2.5F);
                     break;
                  case Pitch:
                     pitchStep = Math.min(90.0F, pitchStep * 2.0F);
                     break;
                  case Both:
                     yawStep = Math.min(180.0F, yawStep * 2.5F);
                     pitchStep = Math.min(90.0F, pitchStep * 2.0F);
               }
            }

            float deltaYaw = class_3532.method_15363(delta_yaw, -yawStep, yawStep);
            float deltaPitch = class_3532.method_15363(delta_pitch, -pitchStep, pitchStep);
            float newYaw = this.rotationYaw + deltaYaw + microJitterYaw;
            float newPitch = class_3532.method_15363(this.rotationPitch + deltaPitch + microJitterPitch, -90.0F, 90.0F);
            double baseSens = (Double)mc.field_1690.method_42495().method_41753();
            double gcdFix = Math.pow(baseSens * 0.6 + 0.2, 3.0) * 1.2;
            if (this.trackticks <= 0 && this.rotationMode.getValue() != Aura.Mode.Cosmic) {
               this.rotationYaw = mc.field_1724.method_36454();
               this.rotationPitch = mc.field_1724.method_36455();
            } else {
               this.rotationYaw = (float)(newYaw - (newYaw - this.rotationYaw) % gcdFix);
               this.rotationPitch = (float)(newPitch - (newPitch - this.rotationPitch) % gcdFix);
            }

            if (!this.rotationMode.is(Aura.Mode.Grim)) {
               ModuleManager.rotations.fixRotation = class_3532.method_15393(this.rotationYaw);
            }

            this.lookingAtHitbox = this.isElytra()
               ? Managers.PLAYER.checkRtx(this.rotationYaw, this.rotationPitch, this.elytraAttackRange.getValue(), 0.0F, Aura.RayTrace.OnlyTarget)
               : Managers.PLAYER.checkRtx(this.rotationYaw, this.rotationPitch, this.getRange(), this.getWallRange(), this.rayTrace.getValue());
         }
      }
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (this.haveWeapon() && target != null) {
         if (this.resolverVisualisation.getValue() && this.resolvedBox != null) {
            Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(this.resolvedBox, HudEditor.getColor(0), 1.0F));
         }

         if (this.clientLook.getValue() && this.rotationMode.getValue() != Aura.Mode.None) {
            mc.field_1724.method_36456((float)Render2DEngine.interpolate(mc.field_1724.field_5982, this.rotationYaw, Render3DEngine.getTickDelta()));
            mc.field_1724.method_36457((float)Render2DEngine.interpolate(mc.field_1724.field_6004, this.rotationPitch, Render3DEngine.getTickDelta()));
         }
      }
   }

   @Override
   public void onDisable() {
      target = null;
   }

   public float getSquaredRotateDistance() {
      float range = this.getAimRange();
      return range * range;
   }

   public class_243 getLegitLook(class_1297 target) {
      float minMotionXZ = 0.003F;
      float maxMotionXZ = 0.03F;
      float minMotionY = 0.001F;
      float maxMotionY = 0.03F;
      double lenghtX = target.method_5829().method_17939();
      double lenghtY = target.method_5829().method_17940();
      double lenghtZ = target.method_5829().method_17941();
      if (this.rotationMotion.equals(class_243.field_1353)) {
         this.rotationMotion = new class_243(MathUtility.random(-0.05F, 0.05F), MathUtility.random(-0.05F, 0.05F), MathUtility.random(-0.05F, 0.05F));
      }

      this.rotationPoint = this.rotationPoint.method_1019(this.rotationMotion);
      if (this.rotationPoint.field_1352 >= (lenghtX - 0.05) / 2.0) {
         this.rotationMotion = new class_243(
            -MathUtility.random(minMotionXZ, maxMotionXZ), this.rotationMotion.method_10214(), this.rotationMotion.method_10215()
         );
      }

      if (this.rotationPoint.field_1351 >= lenghtY) {
         this.rotationMotion = new class_243(
            this.rotationMotion.method_10216(), -MathUtility.random(minMotionY, maxMotionY), this.rotationMotion.method_10215()
         );
      }

      if (this.rotationPoint.field_1350 >= (lenghtZ - 0.05) / 2.0) {
         this.rotationMotion = new class_243(
            this.rotationMotion.method_10216(), this.rotationMotion.method_10214(), -MathUtility.random(minMotionXZ, maxMotionXZ)
         );
      }

      if (this.rotationPoint.field_1352 <= -(lenghtX - 0.05) / 2.0) {
         this.rotationMotion = new class_243(MathUtility.random(minMotionXZ, 0.03F), this.rotationMotion.method_10214(), this.rotationMotion.method_10215());
      }

      if (this.rotationPoint.field_1351 <= 0.05) {
         this.rotationMotion = new class_243(this.rotationMotion.method_10216(), MathUtility.random(minMotionY, maxMotionY), this.rotationMotion.method_10215());
      }

      if (this.rotationPoint.field_1350 <= -(lenghtZ - 0.05) / 2.0) {
         this.rotationMotion = new class_243(
            this.rotationMotion.method_10216(), this.rotationMotion.method_10214(), MathUtility.random(minMotionXZ, maxMotionXZ)
         );
      }

      this.rotationPoint.method_1031(MathUtility.random(-0.03F, 0.03F), 0.0, MathUtility.random(-0.03F, 0.03F));
      if (!mc.field_1724.method_6057(target)) {
         if (Objects.requireNonNull(this.wallsBypass.getValue()) == Aura.WallsBypass.V1) {
            return target.method_19538().method_1031(MathUtility.random(-0.15, 0.15), lenghtY, MathUtility.random(-0.15, 0.15));
         }

         if (this.wallsBypass.getValue() == Aura.WallsBypass.V3) {
            class_238 box = target.method_5829();
            double minX = box.field_1323;
            double maxX = box.field_1320;
            double minY = box.field_1322;
            double maxY = box.field_1325;
            double minZ = box.field_1321;
            double maxZ = box.field_1324;
            double offsetX = MathUtility.random(-0.05, 0.05);
            double offsetY = MathUtility.random(-0.05, 0.05);
            double offsetZ = MathUtility.random(-0.05, 0.05);
            class_243 center = new class_243((minX + maxX) / 2.0, (minY + maxY) / 2.0, (minZ + maxZ) / 2.0);
            double yRange = Math.min(0.8, maxY - minY);
            double randomY = MathUtility.random(-yRange / 2.0, yRange / 2.0);
            return center.method_1031(offsetX, randomY + offsetY, offsetZ);
         }
      }

      if (!Managers.PLAYER.checkRtx(this.rotationYaw, this.rotationPitch, this.getRange(), this.getWallRange(), this.rayTrace.getValue())) {
         float[] rotation1 = PlayerManager.calcAngle(target.method_19538().method_1031(0.0, target.method_18381(target.method_18376()) / 2.0F, 0.0));
         if (PlayerUtility.squaredDistanceFromEyes(target.method_19538().method_1031(0.0, target.method_18381(target.method_18376()) / 2.0F, 0.0))
               <= this.getAttackRange() * this.getAttackRange()
            && Managers.PLAYER.checkRtx(rotation1[0], rotation1[1], this.getRange(), 0.0F, this.rayTrace.getValue())) {
            this.rotationPoint = new class_243(
               MathUtility.random(-0.1F, 0.1F), target.method_18381(target.method_18376()) / MathUtility.random(1.8F, 2.5F), MathUtility.random(-0.1F, 0.1F)
            );
         } else {
            float halfBox = (float)(lenghtX / 2.0);

            for (float x1 = -halfBox; x1 <= halfBox; x1 += 0.05F) {
               for (float z1 = -halfBox; z1 <= halfBox; z1 += 0.05F) {
                  for (float y1 = 0.05F; y1 <= target.method_5829().method_17940(); y1 += 0.15F) {
                     class_243 v1 = new class_243(target.method_23317() + x1, target.method_23318() + y1, target.method_23321() + z1);
                     if (!(PlayerUtility.squaredDistanceFromEyes(v1) > this.getAttackRange() * this.getAttackRange())) {
                        float[] rotation = PlayerManager.calcAngle(v1);
                        if (Managers.PLAYER.checkRtx(rotation[0], rotation[1], this.getRange(), 0.0F, this.rayTrace.getValue())) {
                           this.rotationPoint = new class_243(x1, y1, z1);
                           break;
                        }
                     }
                  }
               }
            }
         }
      }

      return target.method_19538().method_1019(this.rotationPoint);
   }

   private class_243 getElytraHitVec(class_1297 target) {
      class_243 pos = target.method_19538();
      class_243 vel = target.method_18798();
      double distance = mc.field_1724.method_33571().method_1022(target.method_33571());
      double predictTicks = class_3532.method_15350(distance / 3.2, 1.2, 3.5);
      class_243 predicted = pos.method_1019(vel.method_1021(predictTicks));
      class_238 box = target.method_5829().method_997(predicted.method_1020(pos));
      double y = box.field_1322 + box.method_17940() * 0.85;
      return new class_243(
         class_3532.method_15350(mc.field_1724.method_23317(), box.field_1323, box.field_1320),
         y,
         class_3532.method_15350(mc.field_1724.method_23321(), box.field_1321, box.field_1324)
      );
   }

   public boolean isInRange(class_1297 target) {
      if (PlayerUtility.squaredDistanceFromEyes(target.method_19538().method_1031(0.0, target.method_18381(target.method_18376()), 0.0))
         > this.getSquaredRotateDistance() + 4.0F) {
         return false;
      } else {
         float halfBox = (float)(target.method_5829().method_17939() / 2.0);

         for (float x1 = -halfBox; x1 <= halfBox; x1 += 0.15F) {
            for (float z1 = -halfBox; z1 <= halfBox; z1 += 0.15F) {
               for (float y1 = 0.05F; y1 <= target.method_5829().method_17940(); y1 += 0.25F) {
                  if (!(
                     PlayerUtility.squaredDistanceFromEyes(new class_243(target.method_23317() + x1, target.method_23318() + y1, target.method_23321() + z1))
                        > this.getSquaredRotateDistance()
                  )) {
                     float[] rotation = PlayerManager.calcAngle(
                        new class_243(target.method_23317() + x1, target.method_23318() + y1, target.method_23321() + z1)
                     );
                     if (Managers.PLAYER
                        .checkRtx(rotation[0], rotation[1], (float)Math.sqrt(this.getSquaredRotateDistance()), this.getWallRange(), this.rayTrace.getValue())) {
                        return true;
                     }
                  }
               }
            }
         }

         return false;
      }
   }

   public class_1297 findTarget() {
      List<class_1309> first_stage = new CopyOnWriteArrayList<>();

      for (class_1297 ent : mc.field_1687.method_18112()) {
         if ((ent instanceof class_1678 || ent instanceof class_1674) && ent.method_5805() && this.isInRange(ent) && this.Projectiles.getValue()) {
            return ent;
         }

         if (!this.skipEntity(ent) && ent instanceof class_1309) {
            first_stage.add((class_1309)ent);
         }
      }
      return switch ((Aura.Sort)this.sort.getValue()) {
         case LowestDistance -> (class_1309)first_stage.stream().min(Comparator.comparing(e -> mc.field_1724.method_5707(e.method_19538()))).orElse(null);
         case HighestDistance -> (class_1309)first_stage.stream().max(Comparator.comparing(e -> mc.field_1724.method_5707(e.method_19538()))).orElse(null);
         case LowestHealth -> (class_1309)first_stage.stream().min(Comparator.comparing(e -> e.method_6032() + e.method_6067())).orElse(null);
         case HighestHealth -> (class_1309)first_stage.stream().max(Comparator.comparing(e -> e.method_6032() + e.method_6067())).orElse(null);
         case LowestDurability -> (class_1309)first_stage.stream().min(Comparator.comparing(e -> {
            float v = 0.0F;

            for (class_1799 armor : e.method_5661()) {
               if (armor != null && !armor.method_7909().equals(class_1802.field_8162)) {
                  v += (float)(armor.method_7936() - armor.method_7919()) / armor.method_7936();
               }
            }

            return v;
         })).orElse(null);
         case HighestDurability -> (class_1309)first_stage.stream().max(Comparator.comparing(e -> {
            float v = 0.0F;

            for (class_1799 armor : e.method_5661()) {
               if (armor != null && !armor.method_7909().equals(class_1802.field_8162)) {
                  v += (float)(armor.method_7936() - armor.method_7919()) / armor.method_7936();
               }
            }

            return v;
         })).orElse(null);
         case FOV -> (class_1309)first_stage.stream().min(Comparator.comparing(this::getFOVAngle)).orElse(null);
      };
   }

   private boolean skipEntity(class_1297 entity) {
      if (this.isBullet(entity)) {
         return false;
      } else if (!(entity instanceof class_1309 ent)) {
         return true;
      } else if (ent.method_29504() || !entity.method_5805()) {
         return true;
      } else if (entity instanceof class_1531) {
         return true;
      } else if (entity instanceof class_1451) {
         return true;
      } else if (this.skipNotSelected(entity)) {
         return true;
      } else if (!InteractionUtility.isVecInFOV(ent.method_19538(), this.fov.getValue())) {
         return true;
      } else {
         if (entity instanceof class_1657 player) {
            if (this.antiBotNew.getValue()) {
               if (mc.method_1562().method_2871(player.method_5667()) == null) {
                  return true;
               }

               if (player.method_5767()) {
                  return true;
               }

               if (player.field_6012 < 20) {
                  return true;
               }
            }

            if (ModuleManager.antiBot.isEnabled() && AntiBot.bots.contains(entity)) {
               return true;
            }

            if (player == mc.field_1724 || Managers.FRIEND.isFriend(player)) {
               return true;
            }

            if (player.method_7337() && this.ignoreCreative.getValue()) {
               return true;
            }

            if (player.method_6096() == 0 && this.ignoreNaked.getValue()) {
               return true;
            }

            if (player.method_5767() && this.ignoreInvisible.getValue()) {
               return true;
            }

            if (player.method_22861() == mc.field_1724.method_22861() && this.ignoreTeam.getValue() && mc.field_1724.method_22861() != 16777215) {
               return true;
            }
         }

         return !this.isInRange(entity) || entity.method_16914() && this.ignoreNamed.getValue();
      }
   }

   private boolean isBullet(class_1297 entity) {
      return (entity instanceof class_1678 || entity instanceof class_1674)
         && entity.method_5805()
         && PlayerUtility.squaredDistanceFromEyes(entity.method_19538()) < this.getSquaredRotateDistance()
         && this.Projectiles.getValue();
   }

   private boolean skipNotSelected(class_1297 entity) {
      if (entity instanceof class_1621 && !this.Slimes.getValue()) {
         return true;
      } else {
         if (entity instanceof class_1588 he) {
            if (!this.hostiles.getValue()) {
               return true;
            }

            if (this.onlyAngry.getValue()) {
               return !he.method_7076(mc.field_1724);
            }
         }

         if (entity instanceof class_1657 && !this.Players.getValue()) {
            return true;
         } else if (entity instanceof class_1646 && !this.Villagers.getValue()) {
            return true;
         } else {
            return entity instanceof class_1308 && !this.Mobs.getValue() ? true : entity instanceof class_1429 && !this.Animals.getValue();
         }
      }
   }

   private float getFOVAngle(@NotNull class_1309 e) {
      double difX = e.method_23317() - mc.field_1724.method_23317();
      double difZ = e.method_23321() - mc.field_1724.method_23321();
      float yaw = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0);
      return Math.abs(yaw - class_3532.method_15393(mc.field_1724.method_36454()));
   }

   public void pause() {
      this.pauseTimer.reset();
   }

   private boolean shouldRandomizeDelay() {
      return mc.field_1724.method_24828() || mc.field_1724.field_6017 < 0.12F || mc.field_1724.method_5681() || mc.field_1724.method_6128();
   }

   private boolean shouldRandomizeFallDistance() {
      return !this.shouldRandomizeDelay();
   }

   public static enum AccelerateOnHit {
      Off,
      Yaw,
      Pitch,
      Both;
   }

   public static enum AttackHand {
      MainHand,
      OffHand,
      None;
   }

   public static enum Mode {
      Cosmic,
      Grim,
      None;
   }

   public static class Position {
      private double x;
      private double y;
      private double z;
      private int ticks;

      public Position(double x, double y, double z) {
         this.x = x;
         this.y = y;
         this.z = z;
      }

      public boolean shouldRemove() {
         return false;
      }

      public double getX() {
         return this.x;
      }

      public double getY() {
         return this.y;
      }

      public double getZ() {
         return this.z;
      }
   }

   public static enum RayTrace {
      OFF,
      OnlyTarget,
      AllEntities;
   }

   public static enum Resolver {
      Off,
      Advantage,
      Predictive;
   }

   public static enum Sort {
      LowestDistance,
      HighestDistance,
      LowestHealth,
      HighestHealth,
      LowestDurability,
      HighestDurability,
      FOV;
   }

   public static enum SprintMode {
      Off,
      HVH,
      Legit,
      LegitExtra;
   }

   public static enum WallsBypass {
      Off,
      V1,
      V2,
      V3,
      V4;
   }
}
