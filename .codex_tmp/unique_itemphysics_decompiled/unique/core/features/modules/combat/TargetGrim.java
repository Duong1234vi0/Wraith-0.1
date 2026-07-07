package unique.core.features.modules.combat;

import java.util.List;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1690;
import net.minecraft.class_243;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;

public class TargetGrim extends Module {
   public final Setting<TargetGrim.Mode> mode = new Setting<>("Mode", TargetGrim.Mode.Legit);
   public final Setting<Float> distance = new Setting<>("Distance", 3.0F, 1.0F, 5.0F);
   public final Setting<Float> speed = new Setting<>("Speed", 3.0F, 1.0F, 5.0F);
   public final Setting<Integer> rollback = new Setting<>("Rollback", 8, 1, 20);
   public final Setting<Boolean> armorStands = new Setting<>("ArmorStands", false);
   public final Setting<TargetGrim.BoostMode> boost = new Setting<>("Boost", TargetGrim.BoostMode.None);
   public final Setting<Integer> predictTicks = new Setting<>("PredictTicks", 2, 1, 5, v -> this.boost.is(TargetGrim.BoostMode.Predict));
   private class_243 lastTargetPos;

   public TargetGrim() {
      super("TargetGrim", Module.Category.COMBAT);
   }

   @EventHandler
   public void onTravel(EventPlayerTravel e) {
      if (!e.isPre()) {
         if (this.mode.getValue() == TargetGrim.Mode.Legit) {
            if (Aura.target != null) {
               if (MovementUtility.isMoving()) {
                  class_1297 target = Aura.target;
                  if (target instanceof class_1309) {
                     class_243 targetPos = target.method_19538();
                     if (this.boost.getValue() == TargetGrim.BoostMode.Predict) {
                        if (this.lastTargetPos != null) {
                           class_243 delta = targetPos.method_1020(this.lastTargetPos);
                           targetPos = targetPos.method_1019(delta.method_1021(this.predictTicks.getValue().intValue() * 0.45));
                        }

                        this.lastTargetPos = target.method_19538();
                     } else {
                        this.lastTargetPos = null;
                     }

                     class_243 playerPos = mc.field_1724.method_19538();
                     class_243 diff = targetPos.method_1020(playerPos);
                     double dist = diff.method_1033();
                     if (!(dist > this.distance.getValue().floatValue())) {
                        if (!(dist < 0.35)) {
                           List<class_1297> entities = mc.field_1687.method_8335(mc.field_1724, mc.field_1724.method_5829().method_1014(1.0));
                           int collisions = 0;

                           for (class_1297 ent : entities) {
                              if (ent != mc.field_1724
                                 && (ent instanceof class_1309 || ent instanceof class_1690)
                                 && (!(ent instanceof class_1531) || this.armorStands.getValue())) {
                                 collisions++;
                              }
                           }

                           if (collisions != 0) {
                              class_243 dir = diff.method_1029();
                              double distScale = dist / this.distance.getValue().floatValue();
                              distScale = Math.min(distScale, 1.0);
                              double baseSpeed = this.speed.getValue().floatValue() * 0.018;
                              double finalSpeed = baseSpeed * distScale;
                              class_243 vel = mc.field_1724.method_18798();
                              double currentSpeed = Math.hypot(vel.field_1352, vel.field_1350);
                              double rollbackFactor = 1.0 - this.rollback.getValue().intValue() / 25.0;
                              rollbackFactor = Math.max(rollbackFactor, 0.55);
                              if (currentSpeed > 0.34) {
                                 mc.field_1724.method_18800(vel.field_1352 * rollbackFactor, vel.field_1351, vel.field_1350 * rollbackFactor);
                              } else {
                                 mc.field_1724
                                    .method_5762(
                                       dir.field_1352 * finalSpeed * Math.min(collisions, 2), 0.0, dir.field_1350 * finalSpeed * Math.min(collisions, 2)
                                    );
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

   public static enum BoostMode {
      None,
      Predict;
   }

   public static enum Mode {
      Legit;
   }
}
