package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2815;
import net.minecraft.class_2846;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2885;
import net.minecraft.class_3965;
import net.minecraft.class_2828.class_2830;
import net.minecraft.class_2846.class_2847;
import net.minecraft.class_2848.class_2849;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.events.impl.EventMove;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.events.impl.EventPostTick;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.EventTick;
import unique.core.events.impl.PostPlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.injection.accesors.IInteractionManager;
import unique.core.setting.Setting;
import unique.core.utility.interfaces.IEntity;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.MovementUtility;
import unique.core.utility.player.SearchInvResult;

public class Speed extends Module {
   public final Setting<Speed.Mode> mode = new Setting<>("Mode", Speed.Mode.NCP);
   public Setting<Boolean> useTimer = new Setting<>("Use Timer", false);
   public Setting<Boolean> pauseInLiquids = new Setting<>("PauseInLiquids", false);
   public Setting<Boolean> pauseWhileSneaking = new Setting<>("PauseWhileSneaking", false);
   public final Setting<Integer> hurttime = new Setting<>("HurtTime", 0, 0, 10, v -> this.mode.is(Speed.Mode.MatrixDamage));
   public final Setting<Float> boostFactor = new Setting<>(
      "BoostFactor", 2.0F, 0.0F, 10.0F, v -> this.mode.is(Speed.Mode.MatrixDamage) || this.mode.is(Speed.Mode.Vanilla)
   );
   public final Setting<Boolean> allowOffGround = new Setting<>("AllowOffGround", true, v -> this.mode.is(Speed.Mode.MatrixDamage));
   public final Setting<Integer> shiftTicks = new Setting<>("ShiftTicks", 0, 0, 10, v -> this.mode.is(Speed.Mode.MatrixDamage));
   public final Setting<Integer> fireWorkSlot = new Setting<>("FireSlot", 1, 1, 9, v -> this.mode.getValue() == Speed.Mode.FireWork);
   public final Setting<Integer> delay = new Setting<>("Delay", 8, 1, 20, v -> this.mode.getValue() == Speed.Mode.FireWork);
   public final Setting<Boolean> strict = new Setting<>("Strict", false, v -> this.mode.is(Speed.Mode.GrimIce));
   public final Setting<Float> matrixJBSpeed = new Setting<>("TimerSpeed", 1.088F, 1.0F, 2.0F, v -> this.mode.is(Speed.Mode.MatrixJB));
   public final Setting<Boolean> armorStands = new Setting<>(
      "ArmorStands", false, v -> this.mode.is(Speed.Mode.GrimCombo) || this.mode.is(Speed.Mode.GrimEntity2)
   );
   public double baseSpeed;
   private int stage;
   private int ticks;
   private int prevSlot;
   private float prevForward = 0.0F;
   private unique.core.utility.Timer elytraDelay = new unique.core.utility.Timer();
   private unique.core.utility.Timer startDelay = new unique.core.utility.Timer();

   public Speed() {
      super("Speed", Module.Category.MOVEMENT);
   }

   @Override
   public void onDisable() {
      UniqueCore.TICK_TIMER = 1.0F;
   }

   @Override
   public void onEnable() {
      this.stage = 1;
      this.ticks = 0;
      this.baseSpeed = 0.2873;
      this.startDelay.reset();
      this.prevSlot = -1;
   }

   @EventHandler
   public void onSync(EventSync e) {
      if ((!mc.field_1724.method_52535() || !this.pauseInLiquids.getValue()) && (!mc.field_1724.method_5715() || !this.pauseWhileSneaking.getValue())) {
         if (this.mode.getValue() == Speed.Mode.MatrixJB) {
            boolean closeToGround = false;

            for (class_265 a : mc.field_1687.method_20812(mc.field_1724, mc.field_1724.method_5829().method_1009(0.5, 0.0, 0.5).method_989(0.0, -1.0, 0.0))) {
               if (a != class_259.method_1073()) {
                  closeToGround = true;
                  break;
               }
            }

            if (MovementUtility.isMoving() && closeToGround && mc.field_1724.field_6017 <= 0.0F) {
               UniqueCore.TICK_TIMER = 1.0F;
               mc.field_1724.method_24830(true);
               mc.field_1724.method_6043();
            } else if (mc.field_1724.field_6017 > 0.0F && this.useTimer.getValue()) {
               UniqueCore.TICK_TIMER = this.matrixJBSpeed.getValue();
               mc.field_1724.method_5762(0.0, -0.003F, 0.0);
            }
         }
      }
   }

   @EventHandler
   public void modifyVelocity(EventPlayerTravel e) {
      if (this.mode.getValue() == Speed.Mode.GrimEntity && !e.isPre() && UniqueCore.core.getSetBackTime() > 1000L) {
         for (class_1657 ent : Managers.ASYNC.getAsyncPlayers()) {
            if (ent != mc.field_1724 && mc.field_1724.method_5858(ent) <= 2.25) {
               float p = mc.field_1687.method_8320(((IEntity)mc.field_1724).thunderHack_Recode$getVelocityBP()).method_26204().method_9499();
               float f = mc.field_1724.method_24828() ? p * 0.91F : 0.91F;
               float f2 = mc.field_1724.method_24828() ? p : 0.99F;
               mc.field_1724
                  .method_18800(
                     mc.field_1724.method_18798().method_10216() / f * f2,
                     mc.field_1724.method_18798().method_10214(),
                     mc.field_1724.method_18798().method_10215() / f * f2
                  );
               break;
            }
         }
      }

      if ((this.mode.is(Speed.Mode.GrimEntity2) || this.mode.is(Speed.Mode.GrimCombo))
         && !e.isPre()
         && UniqueCore.core.getSetBackTime() > 1000L
         && MovementUtility.isMoving()) {
         int collisions = 0;

         for (class_1297 entx : mc.field_1687.method_18112()) {
            if (entx != mc.field_1724
               && (!(entx instanceof class_1531) || this.armorStands.getValue())
               && (entx instanceof class_1309 || entx instanceof class_1690)
               && mc.field_1724.method_5829().method_1014(1.0).method_994(entx.method_5829())) {
               collisions++;
            }
         }

         double[] motion = MovementUtility.forward(0.08 * collisions);
         mc.field_1724.method_5762(motion[0], 0.0, motion[1]);
      }
   }

   @EventHandler
   public void onTick(EventTick e) {
      if ((this.mode.is(Speed.Mode.GrimIce) || this.mode.is(Speed.Mode.GrimCombo)) && mc.field_1724.method_24828()) {
         class_2338 pos = ((IEntity)mc.field_1724).thunderHack_Recode$getVelocityBP();
         SearchInvResult result = InventoryUtility.findBlockInHotBar(class_2246.field_10295, class_2246.field_10225, class_2246.field_10384);
         if (mc.field_1687.method_22347(pos) || !result.found() || !mc.field_1690.field_1903.method_1434()) {
            return;
         }

         this.prevSlot = mc.field_1724.method_31548().field_7545;
         result.switchTo();
         this.sendPacket(
            new class_2830(
               mc.field_1724.method_23317(),
               mc.field_1724.method_23318(),
               mc.field_1724.method_23321(),
               mc.field_1724.method_36454(),
               90.0F,
               mc.field_1724.method_24828()
            )
         );
         if (this.strict.getValue()) {
            this.sendPacket(new class_2846(class_2847.field_12968, pos, class_2350.field_11036));
            this.sendPacket(new class_2846(class_2847.field_12971, pos, class_2350.field_11036));
         }

         this.sendPacket(new class_2846(class_2847.field_12973, pos, class_2350.field_11036));
         this.sendSequencedPacket(
            id -> new class_2885(
               class_1268.field_5808,
               new class_3965(pos.method_10074().method_46558().method_1031(0.0, 0.5, 0.0), class_2350.field_11036, pos.method_10074(), false),
               id
            )
         );
         mc.field_1687.method_8501(pos, class_2246.field_10295.method_9564());
      }
   }

   @EventHandler
   public void onPostTick(EventPostTick e) {
      if ((this.mode.is(Speed.Mode.GrimIce) || this.mode.is(Speed.Mode.GrimCombo)) && this.prevSlot != -1) {
         mc.field_1724.method_31548().field_7545 = this.prevSlot;
         ((IInteractionManager)mc.field_1761).syncSlot();
         this.prevSlot = -1;
      }
   }

   @Override
   public void onUpdate() {
      if ((!mc.field_1724.method_52535() || !this.pauseInLiquids.getValue()) && (!mc.field_1724.method_5715() || !this.pauseWhileSneaking.getValue())) {
         if (this.mode.getValue() == Speed.Mode.FireWork) {
            this.ticks--;
            int ellySlot = InventoryUtility.getElytra();
            int fireSlot = InventoryUtility.findItemInHotBar(class_1802.field_8639).slot();
            boolean inOffHand = mc.field_1724.method_6079().method_7909() == class_1802.field_8639;
            if (fireSlot == -1) {
               int fireInInv = InventoryUtility.findItemInInventory(class_1802.field_8639).slot();
               if (fireInInv != -1) {
                  mc.field_1761
                     .method_2906(mc.field_1724.field_7512.field_7763, fireInInv, this.fireWorkSlot.getValue() - 1, class_1713.field_7791, mc.field_1724);
               }
            }

            if (ellySlot != -1 && (fireSlot != -1 || inOffHand) && !mc.field_1724.method_24828() && mc.field_1724.field_6017 > 0.0F && this.ticks <= 0) {
               if (ellySlot != -2) {
                  mc.field_1761.method_2906(0, ellySlot, 1, class_1713.field_7790, mc.field_1724);
                  mc.field_1761.method_2906(0, 6, 1, class_1713.field_7790, mc.field_1724);
               }

               mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12982));
               int prevSlot = mc.field_1724.method_31548().field_7545;
               if (prevSlot != fireSlot && !inOffHand) {
                  this.sendPacket(new class_2868(fireSlot));
               }

               mc.field_1761.method_2919(mc.field_1724, inOffHand ? class_1268.field_5810 : class_1268.field_5808);
               if (prevSlot != fireSlot && !inOffHand) {
                  this.sendPacket(new class_2868(prevSlot));
               }

               if (ellySlot != -2) {
                  mc.field_1761.method_2906(0, 6, 1, class_1713.field_7790, mc.field_1724);
                  mc.field_1761.method_2906(0, ellySlot, 1, class_1713.field_7790, mc.field_1724);
               }

               mc.field_1724.field_3944.method_52787(new class_2815(mc.field_1724.field_7512.field_7763));
               this.ticks = this.delay.getValue();
            }
         }

         if (this.mode.getValue() == Speed.Mode.ElytraLowHop) {
            if (mc.field_1724.method_24828()) {
               mc.field_1724.method_6043();
               return;
            }

            if (mc.field_1687
                  .method_20812(mc.field_1724, mc.field_1724.method_5829().method_1009(-0.29, 0.0, -0.29).method_989(0.0, -3.0, 0.0))
                  .iterator()
                  .hasNext()
               && this.elytraDelay.passedMs(150L)
               && this.startDelay.passedMs(500L)) {
               int elytra = InventoryUtility.getElytra();
               if (elytra == -1) {
                  this.disable(ClientSettings.isRu() ? "Для этого режима нужна элитра!" : "You need elytra for this mode!");
               } else {
                  Strafe.disabler(elytra);
               }

               mc.field_1724.method_18800(mc.field_1724.method_18798().method_10216(), 0.0, mc.field_1724.method_18798().method_10215());
               if (MovementUtility.isMoving()) {
                  MovementUtility.setMotion(0.85);
               }

               this.elytraDelay.reset();
            }
         }
      }
   }

   @EventHandler
   public void onPostPlayerUpdate(PostPlayerUpdateEvent event) {
      if (this.mode.getValue() == Speed.Mode.MatrixDamage && MovementUtility.isMoving() && mc.field_1724.field_6235 > this.hurttime.getValue()) {
         if (mc.field_1724.method_24828()) {
            MovementUtility.setMotion(0.387F * this.boostFactor.getValue());
         } else if (mc.field_1724.method_5799()) {
            MovementUtility.setMotion(0.346F * this.boostFactor.getValue());
         } else if (!mc.field_1724.method_24828() && this.allowOffGround.getValue()) {
            MovementUtility.setMotion(0.448F * this.boostFactor.getValue());
         }

         if (this.shiftTicks.getValue() > 0) {
            event.cancel();
            event.setIterations(this.shiftTicks.getValue());
         }
      }
   }

   @EventHandler
   public void onMove(EventMove event) {
      if ((!mc.field_1724.method_52535() || !this.pauseInLiquids.getValue()) && (!mc.field_1724.method_5715() || !this.pauseWhileSneaking.getValue())) {
         if (this.mode.getValue() == Speed.Mode.NCP || this.mode.getValue() == Speed.Mode.StrictStrafe) {
            if (!mc.field_1724.method_31549().field_7479) {
               if (!mc.field_1724.method_6128()) {
                  if (mc.field_1724.method_7344().method_7586() > 6) {
                     if (!event.isCancelled()) {
                        event.cancel();
                        if (MovementUtility.isMoving()) {
                           UniqueCore.TICK_TIMER = this.useTimer.getValue() ? 1.088F : 1.0F;
                           float currentSpeed = this.mode.getValue() == Speed.Mode.NCP
                                 && mc.field_1724.field_3913.field_3905 <= 0.0F
                                 && this.prevForward > 0.0F
                              ? Managers.PLAYER.currentPlayerSpeed * 0.66F
                              : Managers.PLAYER.currentPlayerSpeed;
                           boolean canJump = !mc.field_1724.field_5976;
                           if (this.stage == 1 && mc.field_1724.method_24828() && canJump) {
                              mc.field_1724
                                 .method_18800(mc.field_1724.method_18798().field_1352, MovementUtility.getJumpSpeed(), mc.field_1724.method_18798().field_1350);
                              event.setY(MovementUtility.getJumpSpeed());
                              this.baseSpeed *= 2.149;
                              this.stage = 2;
                           } else if (this.stage == 2) {
                              this.baseSpeed = currentSpeed - 0.66 * (currentSpeed - MovementUtility.getBaseMoveSpeed());
                              this.stage = 3;
                           } else {
                              if (mc.field_1687
                                    .method_20812(mc.field_1724, mc.field_1724.method_5829().method_989(0.0, mc.field_1724.method_18798().method_10214(), 0.0))
                                    .iterator()
                                    .hasNext()
                                 || mc.field_1724.field_5992) {
                                 this.stage = 1;
                              }

                              this.baseSpeed = currentSpeed - currentSpeed / 159.0;
                           }

                           this.baseSpeed = Math.max(this.baseSpeed, MovementUtility.getBaseMoveSpeed());
                           double ncpSpeed = this.mode.getValue() != Speed.Mode.StrictStrafe && !(mc.field_1724.field_3913.field_3905 < 1.0F) ? 0.576 : 0.465;
                           double ncpBypassSpeed = this.mode.getValue() != Speed.Mode.StrictStrafe && !(mc.field_1724.field_3913.field_3905 < 1.0F)
                              ? 0.57
                              : 0.44;
                           if (mc.field_1724.method_6059(class_1294.field_5904)) {
                              double amplifier = mc.field_1724.method_6112(class_1294.field_5904).method_5578();
                              ncpSpeed *= 1.0 + 0.2 * (amplifier + 1.0);
                              ncpBypassSpeed *= 1.0 + 0.2 * (amplifier + 1.0);
                           }

                           if (mc.field_1724.method_6059(class_1294.field_5909)) {
                              double amplifier = mc.field_1724.method_6112(class_1294.field_5909).method_5578();
                              ncpSpeed /= 1.0 + 0.2 * (amplifier + 1.0);
                              ncpBypassSpeed /= 1.0 + 0.2 * (amplifier + 1.0);
                           }

                           this.baseSpeed = Math.min(this.baseSpeed, this.ticks > 25 ? ncpSpeed : ncpBypassSpeed);
                           if (this.ticks++ > 50) {
                              this.ticks = 0;
                           }

                           MovementUtility.modifyEventSpeed(event, this.baseSpeed);
                           this.prevForward = mc.field_1724.field_3913.field_3905;
                        } else {
                           UniqueCore.TICK_TIMER = 1.0F;
                           event.setX(0.0);
                           event.setZ(0.0);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static enum Mode {
      StrictStrafe,
      MatrixJB,
      NCP,
      ElytraLowHop,
      MatrixDamage,
      GrimEntity,
      GrimEntity2,
      FireWork,
      Vanilla,
      GrimIce,
      GrimCombo;
   }
}
